package com.gridpoint.automation.IntervalData;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.net.ssl.HttpsURLConnection;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

//import RegisterThread;
public class SerializeData {

	private static final String USER_AGENT = "Mozilla/5.0";

	private Map<String, Map<String, Map<String, String>>> store;

	private static final String SET_COOKIE = "Set-Cookie";
	private static final String COOKIE_VALUE_DELIMITER = ";";
	private static final String PATH = "path";
	private static final String EXPIRES = "expires";
	private static final String DATE_FORMAT = "EEE, dd-MMM-yyyy hh:mm:ss z";
	private static final String SET_COOKIE_SEPARATOR = "; ";
	private static final String COOKIE = "Cookie";

	private static final char NAME_VALUE_SEPARATOR = '=';
	private static final char DOT = '.';

	private DateFormat dateFormat;

	public SerializeData() {
		store = new HashMap<String, Map<String, Map<String, String>>>();
		dateFormat = new SimpleDateFormat(DATE_FORMAT);
	}

	/**
	 * Retrieves and stores cookies returned by the host on the other side of
	 * the the open java.net.URLConnection.
	 *
	 * The connection MUST have been opened using the connect() method or a
	 * IOException will be thrown.
	 *
	 * @param conn
	 *            a java.net.URLConnection - must be open, or IOException will
	 *            be thrown
	 * @throws java.io.IOException
	 *             Thrown if conn is not open.
	 */
	public void storeCookies(URLConnection conn) throws IOException {

		// let's determine the domain from where these cookies are being sent
		String domain = getDomainFromHost(conn.getURL().getHost());

		Map<String, Map<String, String>> domainStore; // this is where we will
														// store cookies for
														// this domain

		// now let's check the store to see if we have an entry for this domain
		if (store.containsKey(domain)) {
			// we do, so lets retrieve it from the store
			domainStore = store.get(domain);
		} else {
			// we don't, so let's create it and put it in the store
			domainStore = new HashMap<String, Map<String, String>>();
			store.put(domain, domainStore);
		}

		// OK, now we are ready to get the cookies out of the URLConnection

		String headerName = null;
		for (int i = 1; (headerName = conn.getHeaderFieldKey(i)) != null; i++) {
			if (headerName.equalsIgnoreCase(SET_COOKIE)) {
				Map<String, String> cookie = new HashMap<String, String>();
				StringTokenizer st = new StringTokenizer(
						conn.getHeaderField(i), COOKIE_VALUE_DELIMITER);

				// the specification dictates that the first name/value pair
				// in the string is the cookie name and value, so let's handle
				// them as a special case:

				if (st.hasMoreTokens()) {
					String token = st.nextToken();
					String name = token.substring(0,
							token.indexOf(NAME_VALUE_SEPARATOR));
					String value = token.substring(
							token.indexOf(NAME_VALUE_SEPARATOR) + 1,
							token.length());
					domainStore.put(name, cookie);
					cookie.put(name, value);
				}

				while (st.hasMoreTokens()) {
					String t1 = "";
					String t2 = "";
					String token = st.nextToken();
					int seperatorPosition = token.indexOf(NAME_VALUE_SEPARATOR);
					if (seperatorPosition >= 0) {
						t1 = token.substring(0,
								token.indexOf(NAME_VALUE_SEPARATOR))
								.toLowerCase();
					} else {
						t1 = "";
					}
					t2 = token.substring(
							token.indexOf(NAME_VALUE_SEPARATOR) + 1,
							token.length());
					/*
					 * cookie.put(token.substring(0,
					 * token.indexOf(NAME_VALUE_SEPARATOR)).toLowerCase(),
					 * token.substring(token.indexOf(NAME_VALUE_SEPARATOR) + 1,
					 * token.length()));
					 */
					cookie.put(t1, t2);
				}
			}
		}
	}

	/**
	 * Prior to opening a URLConnection, calling this method will set all
	 * unexpired cookies that match the path or subpaths for thi underlying URL
	 *
	 * The connection MUST NOT have been opened method or an IOException will be
	 * thrown.
	 *
	 * @param conn
	 *            a java.net.URLConnection - must NOT be open, or IOException
	 *            will be thrown
	 * @throws java.io.IOException
	 *             Thrown if conn has already been opened.
	 */
	public void setCookies(URLConnection conn) throws IOException {

		// let's determine the domain and path to retrieve the appropriate
		// cookies
		URL url = conn.getURL();
		String domain = getDomainFromHost(url.getHost());
		String path = url.getPath();

		Map<String, Map<String, String>> domainStore = store.get(domain);
		if (domainStore == null)
			return;
		StringBuffer cookieStringBuffer = new StringBuffer();

		Iterator<String> cookieNames = domainStore.keySet().iterator();
		while (cookieNames.hasNext()) {
			String cookieName = cookieNames.next();
			Map<String, String> cookie = (Map<String, String>) domainStore
					.get(cookieName);
			// check cookie to ensure path matches and cookie is not expired
			// if all is cool, add cookie to header string
			if (comparePaths((String) cookie.get(PATH), path)
					&& isNotExpired((String) cookie.get(EXPIRES))) {
				cookieStringBuffer.append(cookieName);
				cookieStringBuffer.append("=");
				cookieStringBuffer.append((String) cookie.get(cookieName));
				if (cookieNames.hasNext())
					cookieStringBuffer.append(SET_COOKIE_SEPARATOR);
			}
		}
		try {
			conn.setRequestProperty(COOKIE, cookieStringBuffer.toString());
		} catch (java.lang.IllegalStateException ise) {
			IOException ioe = new IOException(
					"Illegal State! Cookies cannot be set on a URLConnection that is already connected. "
							+ "Only call setCookies(java.net.URLConnection) AFTER calling java.net.URLConnection.connect().");
			throw ioe;
		}
	}

	private String getDomainFromHost(String host) {
		if (host.indexOf(DOT) != host.lastIndexOf(DOT)) {
			return host.substring(host.indexOf(DOT) + 1);
		} else {
			return host;
		}
	}

	private boolean isNotExpired(String cookieExpires) {
		if (cookieExpires == null)
			return true;
		Date now = new Date();
		try {
			return (now.compareTo(dateFormat.parse(cookieExpires))) <= 0;
		} catch (java.text.ParseException pe) {
			pe.printStackTrace();
			return false;
		}
	}

	private boolean comparePaths(String cookiePath, String targetPath) {
		if (cookiePath == null) {
			return true;
		} else if (cookiePath.equals("/")) {
			return true;
		} else if (targetPath.regionMatches(0, cookiePath, 0,
				cookiePath.length())) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Returns a string representation of stored cookies organized by domain.
	 */

	public String toString() {
		return store.toString();
	}

	public boolean autorizeConnection(String targetHost, String userName,
			String password) {
		int responseCode = 0;
		String url = targetHost + "/api/v1/login?username=" + userName
				+ "&password=" + password + "";
		try {
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Content-Type", "text/plain");
			con.setRequestProperty("charset", "UTF-8");
			// con.setRequestProperty("Authorization","Basic "+encoding);

			String urlParameters = "username=" + userName + "&password="
					+ password + "";
			// Send post request
			con.setDoOutput(true);
			con.connect();
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

			storeCookies(con);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		if (responseCode == 200)
			return true;
		else
			return false;
	}

	// public void provisionTypeOne(int epStId, int epEndId, int noOfDevice, int
	// startAddress, int endAddress,
	// String targetHost, int intVal, int minchnl, int mxchnl, int dur,
	// IntervalJSON payLoad, PrintWriter outFile,
	// String[] dataLoads) {
	// // int endPointId = -10001;
	// try {
	// DateFormat formatter2 = new SimpleDateFormat("MM-dd-yyyy HH:mm");
	// formatter2.setTimeZone(TimeZone.getTimeZone("UTC"));
	// Date enddate = formatter2.parse(formatter2.format(new Date()));
	// long now = enddate.toInstant().toEpochMilli();
	// long endTime = now;
	// int addr = startAddress;
	//
	// int loop = 0;
	// for (int ep = epStId; ep >= epEndId; ep--) {
	// dataLoads[loop] = payLoad.createJSON(intVal, minchnl, mxchnl, noOfDevice,
	// addr, (addr + noOfDevice - 1),
	// endTime, dur);
	// addr -= noOfDevice;
	// loop++;
	// }
	// // String url2=
	// // "https://ems-qa-master.gridpoint.com/api/v1/endpoints/" +
	// // String.valueOf(endPointId) + "/multipleIntervalData";
	// // int loopItem=0;
	// // for(int ep=epStId;ep>=epEndId;ep--){
	// // String url2= targetHost + "/api/v1/endpoints/" +
	// // String.valueOf(ep) + "/multipleIntervalData";
	// // URL obj2 = new URL(url2);
	// // HttpsURLConnection con2 = (HttpsURLConnection)
	// // obj2.openConnection();
	// // setCookies(con2);
	// //
	// // con2.setRequestMethod("POST");
	// // con2.setRequestProperty("User-Agent", USER_AGENT);
	// // con2.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	// // con2.setRequestProperty("Content-Type", "text/plain");
	// // //con2.setRequestProperty("Authorization","Basic "+encoding);
	// //
	// // con2.setDoOutput(true);
	// // DataOutputStream wr2 = new
	// // DataOutputStream(con2.getOutputStream());
	// //
	// // //for(int addr=startAddress;addr>=endAddress; addr-=noOfDevice){
	// //
	// // outFile.println(dataLoads[loopItem]);
	// // System.out.println("Endpoint: " + String.valueOf(ep));
	// // System.out.println(payLoads[loopItem]);
	// // wr2.writeBytes(payLoads[loopItem]);
	// //
	// // //wr2.writeBytes(urlParameters2);
	// // wr2.flush();
	// // wr2.close();
	// // //System.out.println(json.createJSON(val, channel, deviceAddr,
	// // deviceAddr - 49, endTime, duration));
	// // int responseCode2 = con2.getResponseCode();
	// // System.out.println("\nSending 'POST' request to URL : " + url2);
	// // //System.out.println("Post parameters : " + urlParameters2);
	// // System.out.println("Response Code : " + responseCode2);
	// // System.out.println("Response Code : " +
	// // con2.getResponseMessage());
	// // System.out.println("");
	// // //System.out.println("Response Code : " +
	// // con2.getResponseMessage());
	// // //endPointId-=1;
	// // //}
	// // } //end of for
	// // outFile.flush();
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// }

	// public boolean serializePayload(int epStId, int epEndId, int noOfDevice,
	// int startAddress, int endAddress,
	// String targetHost, int intVal, int minchnl, int mxchnl, int dur,
	// IntervalJSON payLoad,
	// PrintWriter outFile) {
	// // Chjecking if epoch time is 0 or 15 or 30 or 45
	// // this flag will not allow to push more than once for the same water
	// // marked data
	// boolean flag = false;
	// try {
	// DateFormat formatter = new SimpleDateFormat("mm");
	// formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	// int totalEP = Math.abs(epEndId) - Math.abs(epStId) + 1;
	// String[] payLoads = new String[totalEP];
	// for (int k = 0; k < 5000; k++) {
	// String currentminute = formatter.format(new Date());
	// int minute = Integer.valueOf(currentminute);
	// System.out.println("Current UTC time minutes is ==== " + currentminute);
	// provisionTypeOne(epStId, epEndId, noOfDevice, startAddress, endAddress,
	// targetHost, intVal, minchnl,
	// mxchnl, dur, payLoad, outFile, payLoads);
	// if (minute == 00 || minute == 15 || minute == 30 || minute == 45) {
	// // if(minute!=00 || minute!=15 || minute!=30 || minute!=45){
	// if (flag) {
	// sendPayload(epStId, epEndId, targetHost, payLoads);
	//
	// flag = false;
	// }
	// } else {
	// flag = true;
	// Thread.sleep(10000);
	// }
	// }
	// } catch (Exception ex) {
	//
	// }
	// return true;
	// }

	// /*
	// * Overloaded method to handle non-sequential endpoint, devices and
	// channel
	// * type
	// */
	// public boolean serializePayload(String dFile, IntervalJSON pLoad, String
	// grnty, String tHost, PrintWriter oFile) {
	// boolean flag = false;
	// try {
	// DateFormat formatter = new SimpleDateFormat("mm");
	// formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	// for (int k = 0; k < 5000; k++) {
	// String currentminute = formatter.format(new Date());
	// int minute = Integer.valueOf(currentminute);
	// // System.out.println("Current UTC time minutes is ==== "
	// // +currentminute);
	// // if(minute==00 || minute==15 || minute==30 || minute==45){
	// if (flag) {
	// provisionTypeTwo(dFile, pLoad, grnty, tHost, oFile);
	// flag = false;
	// }
	// // }
	// // else{
	// // flag=true;
	// // Thread.sleep(10000);
	// // }
	// }
	// } catch (Exception ex) {
	//
	// }
	// return true;
	// }

	// public boolean serializePayload_new(String dFile, IntervalJSON pLoad,
	// String grnty, String tHost,
	// PrintWriter oFile) {
	// boolean flag = false;
	// try {
	// DateFormat formatter = new SimpleDateFormat("mm");
	// formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	//
	// for (int k = 0; k < 5000; k++) {
	// String currentminute = formatter.format(new Date());
	// int minute = Integer.valueOf(currentminute);
	// // System.out.println("Current UTC time minutes is ==== "
	// // +currentminute);
	// // if(minute==00 || minute==15 || minute==30 || minute==45){
	// // if (flag) {
	// // provisionTypeThree(dFile, pLoad, grnty, tHost, oFile);
	// // flag = false;
	// // }
	// // }
	// // else{
	// // flag=true;
	// // Thread.sleep(10000);
	// // }
	// }
	// } catch (Exception ex) {
	//
	// }
	// return true;
	// }

	/*
	 * Method to read excel file and push data through multiIntervalData API
	 */
	public void provisionTypeThree(String dataFile, IntervalJSON payLoad,
			String granularity, String host, PrintWriter outFile) {
		String fileName = dataFile;
		File file = new File(fileName);
		FileInputStream fileInputStream;
		Workbook workbook = null;
		Sheet sheet;
		Iterator<Row> rowIterator;

		try {
			DateFormat formatter2 = new SimpleDateFormat("MM-dd-yyyy HH:mm");
			formatter2.setTimeZone(TimeZone.getTimeZone("UTC"));
			Date enddate = formatter2.parse(formatter2.format(new Date()));
			long now = enddate.toInstant().toEpochMilli();
			long endTime = now;

			fileInputStream = new FileInputStream(file);
			String fileExtension = fileName.substring(fileName.indexOf("."));
			System.out.println(fileExtension);
			if (fileExtension.equals(".xls")) {
				workbook = new HSSFWorkbook(
						new POIFSFileSystem(fileInputStream));
			} else {
				System.out.println("Wrong File Type");
			}

			FormulaEvaluator evaluator = workbook.getCreationHelper()
					.createFormulaEvaluator();
			sheet = workbook.getSheetAt(0);
			rowIterator = sheet.iterator();
			int rowNo = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row
						.cellIterator();
				int colNo = 0;
				String endpoint = "";
				String deviceAddress = "";
				String channelType = "";
				String value = "";
				String epocTime = "";

				if (rowNo > 0) {
					while (cellIterator.hasNext()) {
						org.apache.poi.ss.usermodel.Cell cell = cellIterator
								.next();
						if (cell.getStringCellValue().isEmpty())
							break;
						switch (evaluator.evaluateInCell(cell).getCellType()) {
						case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
							System.out.print(cell.getNumericCellValue() + " ");
							break;
						case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING:
							switch (colNo) {
							case 0:
								endpoint = cell.getStringCellValue();
								break;
							case 1:
								deviceAddress = cell.getStringCellValue();
								break;
							case 2:
								channelType = cell.getStringCellValue();
								break;
							case 3:
								value = cell.getStringCellValue();
								break;
							case 4:
								granularity = cell.getStringCellValue();
								break;
							case 5:
								epocTime = cell.getStringCellValue();
								endTime = Long.parseLong(epocTime.trim());
								break;
							}
							System.out.print(cell.getStringCellValue() + " ");
							break;
						case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA:
							// Not again
							break;
						case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK:
							break;
						}
						colNo++;
					}
					if (endpoint.isEmpty() || deviceAddress.isEmpty()
							|| channelType.isEmpty() || value.isEmpty()) {
						continue;
					}
					String url2 = host + "/api/v1/endpoints/"
							+ String.valueOf(endpoint)
							+ "/multipleIntervalData";
					URL obj2 = new URL(url2);
					HttpsURLConnection con2 = (HttpsURLConnection) obj2
							.openConnection();
					setCookies(con2);

					con2.setRequestMethod("POST");
					con2.setRequestProperty("User-Agent", USER_AGENT);
					con2.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
					con2.setRequestProperty("Content-Type", "text/plain");
					con2.setDoOutput(true);
					DataOutputStream wr2 = new DataOutputStream(
							con2.getOutputStream());
					String intervalPayload = payLoad.createJSON(
							Integer.parseInt(value),
							Integer.parseInt(channelType),
							Integer.parseInt(channelType), 1,
							Integer.parseInt(deviceAddress),
							Integer.parseInt(deviceAddress), endTime,
							Integer.parseInt(granularity));
					outFile.println(intervalPayload);
					System.out.println();
					System.out.println(intervalPayload);
					wr2.writeBytes(intervalPayload);
					wr2.flush();
					wr2.close();
					int responseCode2 = con2.getResponseCode();
					System.out.println("\nSending 'POST' request to URL : "
							+ url2);
					System.out.println("Response Code : " + responseCode2);
					System.out.println("Response Code : "
							+ con2.getResponseMessage());
					outFile.flush();
					System.out.println("\n");
					System.out.println("---------------" + rowNo
							+ "-------------------");
				} // end of if
				rowNo++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Method to read excel file and push data through multiIntervalData API
	 */
	// public void provisionTypeTwo(String dataFile, IntervalJSON payLoad,
	// String granularity, String host,
	// PrintWriter outFile) {
	// String fileName = dataFile;
	// File file = new File(fileName);
	// FileInputStream fileInputStream;
	// Workbook workbook = null;
	// Sheet sheet;
	// Iterator<Row> rowIterator;
	//
	// try {
	// DateFormat formatter2 = new SimpleDateFormat("MM-dd-yyyy HH:mm");
	// formatter2.setTimeZone(TimeZone.getTimeZone("UTC"));
	// Date enddate = formatter2.parse(formatter2.format(new Date()));
	// long now = enddate.toInstant().toEpochMilli();
	// long endTime = now;
	//
	// fileInputStream = new FileInputStream(file);
	// String fileExtension = fileName.substring(fileName.indexOf("."));
	// System.out.println(fileExtension);
	// if (fileExtension.equals(".xls")) {
	// workbook = new HSSFWorkbook(new POIFSFileSystem(fileInputStream));
	// }
	// // else if(fileExtension.equals(".xlsx")){
	// // workbook = new XSSFWorkbook(fileInputStream);
	// // }
	// else {
	// System.out.println("Wrong File Type");
	// }
	//
	// FormulaEvaluator evaluator =
	// workbook.getCreationHelper().createFormulaEvaluator();
	// sheet = workbook.getSheetAt(0);
	// rowIterator = sheet.iterator();
	// int rowNo = 0;
	// while (rowIterator.hasNext()) {
	// Row row = rowIterator.next();
	// Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator =
	// row.cellIterator();
	// int colNo = 0;
	// String endpoint = "";
	// String deviceAddress = "";
	// String channelType = "";
	// String value = "";
	// String epocTime = "";
	//
	// if (rowNo > 0) {
	// while (cellIterator.hasNext()) {
	// org.apache.poi.ss.usermodel.Cell cell = cellIterator.next();
	// if (cell.getStringCellValue().isEmpty())
	// break;
	// // Check the cell type after evaluating formulae
	// // If it is formula cell, it will be evaluated otherwise
	// // no change will happen
	// switch (evaluator.evaluateInCell(cell).getCellType()) {
	// case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
	// System.out.print(cell.getNumericCellValue() + " ");
	// break;
	// case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING:
	// switch (colNo) {
	// case 0:
	// endpoint = cell.getStringCellValue();
	// break;
	// case 1:
	// deviceAddress = cell.getStringCellValue();
	// break;
	// case 2:
	// channelType = cell.getStringCellValue();
	// break;
	// case 3:
	// value = cell.getStringCellValue();
	// break;
	// case 4:
	// granularity = cell.getStringCellValue();
	// break;
	// case 5:
	// epocTime = cell.getStringCellValue();
	// break;
	// }
	// System.out.print(cell.getStringCellValue() + " ");
	// break;
	// case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA:
	// // Not again
	// break;
	// case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK:
	// break;
	// }
	// colNo++;
	// }
	// if (endpoint.isEmpty() || deviceAddress.isEmpty() ||
	// channelType.isEmpty() || value.isEmpty()) {
	// continue;
	// }
	// String url2 = host + "/api/v1/endpoints/" + String.valueOf(endpoint) +
	// "/multipleIntervalData";
	// URL obj2 = new URL(url2);
	// HttpsURLConnection con2 = (HttpsURLConnection) obj2.openConnection();
	// setCookies(con2);
	//
	// con2.setRequestMethod("POST");
	// con2.setRequestProperty("User-Agent", USER_AGENT);
	// con2.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	// con2.setRequestProperty("Content-Type", "text/plain");
	// // con2.setRequestProperty("Authorization","Basic
	// // "+encoding);
	//
	// con2.setDoOutput(true);
	// DataOutputStream wr2 = new DataOutputStream(con2.getOutputStream());
	//
	// String intervalPayload = payLoad.createJSON(Integer.parseInt(value),
	// Integer.parseInt(channelType),
	// Integer.parseInt(channelType), 1, Integer.parseInt(deviceAddress),
	// Integer.parseInt(deviceAddress), endTime, Integer.parseInt(granularity));
	// outFile.println(intervalPayload);
	// System.out.println();
	// System.out.println(intervalPayload);
	// wr2.writeBytes(intervalPayload);
	// wr2.flush();
	// wr2.close();
	// int responseCode2 = con2.getResponseCode();
	// System.out.println("\nSending 'POST' request to URL : " + url2);
	// System.out.println("Response Code : " + responseCode2);
	// System.out.println("Response Code : " + con2.getResponseMessage());
	// outFile.flush();
	// System.out.println("\n");
	// System.out
	// .println("--------------------------" + rowNo +
	// "----------------------------------------");
	// } // end of if
	// rowNo++;
	// }
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (ParseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	// public void sendPayload(int epStId, int epEndId, String targetHost,
	// String[] pLoads) {
	// try {
	// int loopItem = 0;
	// String[] arrURL = new String[Math.abs(epEndId) - Math.abs(epStId) + 1];
	// for (int ep = epStId; ep >= epEndId; ep--) {
	// String url2 = targetHost + "/api/v1/endpoints/" + String.valueOf(ep) +
	// "/multipleIntervalData";
	// // arrURL[loopItem] = targetHost + "/api/v1/endpoints/" +
	// // String.valueOf(ep) + "/multipleIntervalData";
	//
	// // URL obj2 = new URL(url2);
	// // HttpsURLConnection con2 = (HttpsURLConnection)
	// // obj2.openConnection();
	// // setCookies(con2);
	// //
	// // con2.setRequestMethod("POST");
	// // con2.setRequestProperty("User-Agent", USER_AGENT);
	// // con2.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	// // con2.setRequestProperty("Content-Type", "text/plain");
	// // //con2.setRequestProperty("Authorization","Basic "+encoding);
	// //
	// // con2.setDoOutput(true);
	// // DataOutputStream wr2 = new
	// // DataOutputStream(con2.getOutputStream());
	// //
	// // //for(int addr=startAddress;addr>=endAddress;
	// // addr-=noOfDevice){
	// //
	// // outFile.println(pLoads[loopItem]);
	// // System.out.println("Endpoint: " + String.valueOf(ep));
	// // System.out.println(pLoads[loopItem]);
	// // wr2.writeBytes(pLoads[loopItem]);
	// //
	// // //wr2.writeBytes(urlParameters2);
	// // wr2.flush();
	// // wr2.close();
	// // //System.out.println(json.createJSON(val, channel,
	// // deviceAddr, deviceAddr - 49, endTime, duration));
	// // int responseCode2 = con2.getResponseCode();
	// // System.out.println("\nSending 'POST' request to URL : " +
	// // url2);
	// // //System.out.println("Post parameters : " + urlParameters2);
	// // System.out.println("Response Code : " + responseCode2);
	// // System.out.println("Response Code : " +
	// // con2.getResponseMessage());
	// // System.out.println("");
	// // Thread regThread = new Thread(new UrlTread(ep, url2,
	// pLoads[loopItem]));
	// // regThread.start();
	// // loopItem++;
	// } // end of for
	// // outFile.flush();
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// }
}
