package com.gridpoint.adminui.automation.testdata;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import com.gridpoint.automation.constants.PathConstants;
import com.gridpoint.automation.database.GPConnection;
import com.gridpoint.automation.database.GPDataBaseUtil;
import com.gridpoint.automation.tests.base.TestBase;
import com.gridpoint.util.ReadResources;

public class ManageTestData {
	private static Logger logger = Logger.getLogger(ManageTestData.class);
	private static final PasswordEncoder myEncoder = new ShaPasswordEncoder(256);
	private static final String fileName = "testDataQueries.properties";
	private final Properties properties;
	String filePath;
	private static final String DEFAULT_USER_PASSWORD = PathConstants.DEFAULT_USER_PASSWORD;
	private static final String DEFAULT_USER_EMAIL = PathConstants.DEFAULT_USER_EMAIL;

	public ManageTestData() throws IOException {
		filePath = new ReadResources().getFilePath(fileName);
		properties = getProperties();
	}

	@SuppressWarnings("unchecked")
	public boolean manageTestData(String task, Object value, Object... argList) throws SQLException, IOException {
		Connection conn = getConnection();

		if (task.equalsIgnoreCase("Delete_Resources")) {
			return deleteResources(conn, (String) value);
		} else if (task.equalsIgnoreCase("User_Password_Reset")) {
			return resetUserPassword(conn, (String) value);
		} else if (task.equalsIgnoreCase("User_Present")) {
			return isUserPresent(conn, (String) value);
		} else if (task.equalsIgnoreCase("Endpoint_Firmware_Present")) {
			return isEndpointFirmwarePresent(conn, (String) value);
		} else if (task.equalsIgnoreCase("Tenant_Present")) {
			return isTenantPresent(conn, (String) value);
		} else if (task.equalsIgnoreCase("Site_Present")) {
			return isSitePresent(conn, (String) value);
		} else if (task.equalsIgnoreCase("Endpoint_Present")) {
			return isEndpointPresent(conn, (String) value);   
		} else if (task.equalsIgnoreCase("Endpoint_Delete")) {
			return deleteEndpointData(conn, (String) value);
		} else if (task.equalsIgnoreCase("User")) {
			return deleteUserData(conn, (String) value);
		}else if (task.equalsIgnoreCase("DeleteBulkImportSites")) {
			return deleteImportSiteData(conn, (String) value);
		} 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		else if (task.equalsIgnoreCase("User_Insert")) {
			return insertUserData(conn, (String) value);
		} else if (task.equalsIgnoreCase("Tenant")) {
			return deleteTenantData(conn, (String) value);
		} else if (task.equalsIgnoreCase("Site_Tenant_User_Insert")) {
			return insertSiteTenantData(conn, (String) value);
		} else if (task.equalsIgnoreCase("Site_Tenant_User_Delete")) {
			return deleteSiteTenantUserData(conn, (String) value);
		} else if (task.equalsIgnoreCase("Site")) {
			return deleteSiteData(conn, (String) value);
		} else if (task.equalsIgnoreCase("Datafix_Delete")) {
			return deleteDatafixData(conn, (String) value);
		} else if (task.equalsIgnoreCase("Datafix_Insert")) {
			return insertDatafixData(conn, (String) value);
		} else if (task.equalsIgnoreCase("Endpoint_Delete")) {
			return deleteEndpointData(conn, (String) value);
		} else if (task.equalsIgnoreCase("Endpoint_User_Delete")) {
			return deleteEndpointUserData(conn, (String) value);
		} else if (task.equalsIgnoreCase("Endpoint_Insert")) {
			return insertEndpointData(conn, (String) value);
		} else if (task.equalsIgnoreCase("Audit_Insert")) {
			return insertAuditData(conn, (String) value);
		} else if (task.equalsIgnoreCase("Audit_User_Delete")) {
			return deleteAuditUserData(conn, (String) value);
		} else if (task.equalsIgnoreCase("Endpoint_Firmware_Check")) {
			return checkEndpointFirmwareData(conn, (String) value);
		} else if (task.equalsIgnoreCase("EndpointFirmware")) {
			return deleteEndpointFirmwareData(conn, (String) value);
		} else if (task.equalsIgnoreCase("PeripheralFirmware")) {
			return deletePeripheralFirmwareData(conn, (String) value);
		} else if (task.equalsIgnoreCase("Endpoint_Delete_VariousAccordion")) {
			return deleteEndpointPageVariousAccordionData(conn, (String) value, (String) argList[0]);
		} else if (task.equalsIgnoreCase("Endpoint_Insert_VariousAccordion")) {
			return insertEndpointPageVariousAccordionData(conn, (String) value, (String) argList[0]);
		} else if (task.equalsIgnoreCase("PVAudit_Insert")) {
			return insertPVAuditData(conn, (String) value);
		} else if (task.equalsIgnoreCase("PVAudit_Delete")) {
			return deletePVAuditData(conn, (List<String>) value);
		} else if (task.equalsIgnoreCase("userRolePermission_Delete")) {
			return deleteUserRolePermissionData(conn, (List<String>) value, (String) argList[0]);
		} else if (task.equalsIgnoreCase("userRolePermission_Insert")) {
			return insertUserRolePermissionData(conn, (String) value);
		} else if (task.equalsIgnoreCase("userRolePermission_Insert1")) {
			return insertURP(conn, value.toString());
		} else if (task.equalsIgnoreCase("deleteVirtualChannel")) {
			return deleteVirtualChannel(conn, value.toString());
		}
		return true;
	}

	private boolean deleteResources(Connection conn, String userName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			String userID = dbutil.getIdFromDB(
					getProperties().getProperty("getEndUserIDQuery").replace("%userName%", userName), conn);
			Properties properties = getProperties();
			conn.setAutoCommit(false);
			if (null != userID) {
				dbutil.execute(properties.getProperty("deleteEnduserECLCommandAuditQuery").replace("%endUserID%", userID),
						conn);
				dbutil.execute(properties.getProperty("deleteEnduserCapabilityExceptionQuery").replace("%endUserID%", userID),
						conn);
				dbutil.execute(properties.getProperty("deleteEnduserAuditDeltaQuery").replace("%endUserID%", userID),
						conn);
				dbutil.execute(properties.getProperty("deleteEnduserAuditQuery").replace("%endUserID%", userID),
						conn);
				dbutil.execute(properties.getProperty("deleteEnduserXTenantQuery").replace("%endUserID%", userID),
						conn);
				dbutil.execute(properties.getProperty("DeleteWelcomeEmailQuery").replace("%endUserID%", userID), conn);
			}
			dbutil.execute(properties.getProperty("deleteEnduserQuery").replace("%username%", userName), conn);
			conn.commit();
			success = true;
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean resetUserPassword(Connection conn, String userName) throws SQLException, IOException {
		logger.info("Resetting Password for the created User(" + userName + ")");
		GPDataBaseUtil dbutil = new GPDataBaseUtil();

		String userID = dbutil
				.getIdFromDB(getProperties().getProperty("getEndUserIDQuery").replace("%userName%", userName), conn);
		if (null == userID) {
			logger.info("*******(" + userName + ") user does not exist in the database*******");
			return true;
		}

		try {
			conn.setAutoCommit(false);
			dbutil.execute(getProperties().getProperty("usersUpdatePasswordQuery").replace("%username%", userName)
					.replace("%email%", DEFAULT_USER_EMAIL)
					.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName)), conn);

			conn.commit();
		} catch (SQLException ex) {
			logger.error("Error while updating Password for the User : " + ex.getMessage());
			if (conn != null) {
				try {
					logger.error("Transaction is being rolled back");
					conn.rollback();
				} catch (SQLException excep) {
					excep.printStackTrace();
				}
			}
			return false;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return true;
	}

	private boolean isUserPresent(Connection conn, String userName) throws SQLException, IOException {
		logger.info("looking for the User in database: " + userName);
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		try {
			String userID = dbutil.getIdFromDB(
					getProperties().getProperty("getEndUserIDQuery").replace("%userName%", userName), conn);
			if (null == userID) {
				logger.info("*******(" + userName + ") User does not exist in the database*******");
				return false;
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return true;
	}

	private boolean isEndpointFirmwarePresent(Connection conn, String endpointFirmware)
			throws SQLException, IOException {
		logger.info("looking for the Endpoint Firmware(" + endpointFirmware + ") in database");
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		try {
			String endpointFirmwareID = dbutil.getIdFromDB(getProperties().getProperty("getEndpointFirmwareId")
					.replace("%endpointFirmwareName%", endpointFirmware), conn);
			if (null == endpointFirmwareID) {
				logger.info(
						"*******(" + endpointFirmware + ") Endpoint Firmware does not exist in the database*******");
				return false;
			} else {
				logger.info("*******(" + endpointFirmware + ") Endpoint Firmware exists in the database*******");
				return true;
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	private boolean isTenantPresent(Connection conn, String tenantName) throws SQLException, IOException {
		logger.info("looking for the Site(" + tenantName + ") in database");
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		try {
			String tenantId = dbutil
					.getIdFromDB(getProperties().getProperty("getTenantId").replace("%tenantName%", tenantName), conn);
			if (null == tenantId) {
				logger.info("*******(" + tenantName + ") site does not exist in the database*******");
				return false;
			} else {
				logger.info("*******(" + tenantName + ") Tenant exists in the database*******");
				return true;
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	private boolean isSitePresent(Connection conn, String siteName) throws SQLException, IOException {
		logger.info("looking for the Site(" + siteName + ") in database");
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		try {
			String siteId = dbutil.getIdFromDB(
					getProperties().getProperty("getPremisesId").replace("%premisesName%", siteName), conn);
			if (null == siteId) {
				logger.info("*******(" + siteName + ") site does not exist in the database*******");
				return false;
			} else {
				logger.info("*******(" + siteName + ") Sites exists in the database*******");
				return true;
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	private boolean isEndpointPresent(Connection conn, String endpointName)
			throws SQLException, IOException {
		logger.info("looking for the Endpointe(" + endpointName + ") in database");
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		try {
			String endpointId = dbutil.getIdFromDB(getProperties().getProperty("getEndpointId")
					.replace("%endpointName%", endpointName), conn);
			if (null == endpointId) {
				logger.info("*******(" + endpointName
						+ ") Endpoint does not exist in the database*******");
				return false;
			} else {
				logger.info(
						"*******(" + endpointName + ") Endpoint exists in the database*******");
				return true;
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	private boolean deleteEndpointData(Connection conn, String endpointName) throws SQLException, IOException {
		logger.info("Deleteing endpoint " + endpointName);
		
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			String endpointID = dbutil.getIdFromDB(
					getProperties().getProperty("getEndpointId").replace("%endpointName%", endpointName), conn);
			Properties properties = getProperties();
			conn.setAutoCommit(false);
			if (null != endpointID) {
				 dbutil.execute(
				 properties.getProperty("deleteEndpointQuery").replace("%endpoint_id%", endpointID), conn);

			} else {
				logger.info("*******(" + endpointName
						+ ") Endpoint does not exist in the database*******");
				return false;
			}
			conn.commit();
			success = true;
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}
	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	private boolean insertSiteTenantData(Connection conn, String userName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			Properties properties = getProperties();
			conn.setAutoCommit(false);
			String query = null;
			Calendar calendar;
			Date currentDate;
			DateFormat outputFormatter;
			calendar = Calendar.getInstance();
			currentDate = calendar.getTime();
			outputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:MM:YY");
			String dateValue = outputFormatter.format(currentDate);
			if (userName.equalsIgnoreCase("tenantPage_GPSupportUserBot")) {
				query = properties.getProperty("insertGPSupporTenantQuery").replace("%username%", userName)
						.replace("%activationDate%", dateValue)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName));
			} else if (userName.equalsIgnoreCase("SitePage_GPSupportUserBot")) {
				query = properties.getProperty("insertGPSupporSiteQuery").replace("%username%", userName)
						.replace("%activationDate%", dateValue)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName));
			} else if (userName.equalsIgnoreCase("SitePage_CustomerAnalystUserBot")) {
				query = properties.getProperty("insertCustomerAnalystSiteQuery").replace("%username%", userName)
						.replace("%activationDate%", dateValue)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName));
			} else if (userName.equalsIgnoreCase("SitePage_GPAnalystUserBot")) {
				query = properties.getProperty("insertGPAnalystSiteQuery").replace("%username%", userName)
						.replace("%activationDate%", dateValue)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName));
			}

			dbutil.execute(query, conn);
			conn.commit();
			success = true;
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean deleteSiteTenantUserData(Connection conn, String userName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			Properties properties = getProperties();
			conn.setAutoCommit(false);
			String query1 = null;
			String query2 = null;
			String query3 = null;
			String enduserId = dbutil
					.getIdFromDB(getProperties().getProperty("getEnduserId").replace("%username%", userName), conn);
			if (null == enduserId) {
				logger.info("Enduser does not exist in the database");
				return true;
			}
			query1 = properties.getProperty("deleteTenantUserPermissionQuery").replace("%enduser_id%", enduserId);
			query2 = properties.getProperty("deleteTenantUserAuditQuery").replace("%enduser_id%", enduserId);
			query3 = properties.getProperty("deleteTenantUserQuery").replace("%username%", userName);
			
			// TODO : avaiable in k branch....verify
			String query0 = null;
			query0 = properties.getProperty("deleteTenantUserEmailQuery").replace("%enduser_id%", enduserId);
			dbutil.execute(query0, conn);
			////////////////////////////////////

			System.out.println("Query =" + query1);
			dbutil.execute(query1, conn);
			dbutil.execute(query2, conn);
			dbutil.execute(query3, conn);
			conn.commit();

			success = true;
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean deleteImportSiteData(Connection conn, String importSitesName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			Properties properties = getProperties();
			conn.setAutoCommit(false);
			String query1 = null;
			String query2 = null;
			String query3 = null;
			String query4 = null;
			String query5 = null;

			query1 = properties.getProperty("deleteBulkImportSitesChannelDataQuery").replace("%premise_Name%",
					importSitesName);
			query2 = properties.getProperty("deleteBulkImportSitesDevicelDataQuery").replace("%premise_Name%",
					importSitesName);
			query3 = properties.getProperty("deleteBulkImportSitesEndpointDataQuery").replace("%premise_Name%",
					importSitesName);
			query4 = properties.getProperty("deleteBulkImportSitesPremisesPathQuery").replace("%premise_Name%",
					importSitesName);
			query5 = properties.getProperty("deleteBulkImportSitesQuery").replace("%premise_Name%", importSitesName);

			System.out.println("Query =" + query1);
			dbutil.execute(query1, conn);
			dbutil.execute(query2, conn);
			dbutil.execute(query3, conn);
			dbutil.execute(query4, conn);
			dbutil.execute(query5, conn);
			conn.commit();

			success = true;
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean deleteEndpointUserData(Connection conn, String userName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			Properties properties = getProperties();
			conn.setAutoCommit(false);
			String query1 = null;
			System.out.println("userName =" + userName);
			query1 = properties.getProperty("deleteEndpointUserQuery").replace("%username%", userName);
			System.out.println("Query =" + query1);
			dbutil.execute(query1, conn);
			conn.commit();

			success = true;
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean insertEndpointData(Connection conn, String userName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			Properties properties = getProperties();
			conn.setAutoCommit(false);
			String query = null;
			if (userName.equalsIgnoreCase("endpointPage_GPSupportUserBot")) {
				query = properties.getProperty("insertGPSupportEndpointQuery").replace("%username%", userName)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName));
			}
			dbutil.execute(query, conn);
			conn.commit();
			success = true;
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean deleteUserData(Connection conn, String userName) throws SQLException, IOException {
		logger.info("Deleting all the data for User.");
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		try {
			conn.setAutoCommit(false);
			dbutil.execute(getProperties().getProperty("deleteUserWelcomeEmail").replace("%username%", userName), conn);
			dbutil.execute(
					getProperties().getProperty("deleteUserCapabilityExceptions").replace("%username%", userName),
					conn);
			dbutil.execute(getProperties().getProperty("deleteUserXTenant").replace("%username%", userName), conn);
			dbutil.execute(getProperties().getProperty("deleteUserAuditDelta").replace("%username%", userName), conn);
			dbutil.execute(getProperties().getProperty("deleteUserAudit").replace("%username%", userName), conn);
			dbutil.execute(getProperties().getProperty("deleteUserEclCommandAudit").replace("%username%", userName),
					conn);
			dbutil.execute(getProperties().getProperty("deleteUserEclCommand").replace("%username%", userName), conn);
			dbutil.execute(getProperties().getProperty("deleteReportContent").replace("%username%", userName), conn);
			dbutil.execute(getProperties().getProperty("deleteUserReportTask").replace("%username%", userName), conn);
			dbutil.execute(getProperties().getProperty("deleteUserLabelXPremises").replace("%username%", userName),
					conn);
			dbutil.execute(getProperties().getProperty("deleteUserLabel").replace("%username%", userName), conn);
			dbutil.execute(getProperties().getProperty("deleteUser").replace("%username%", userName), conn);
			conn.commit();
		} catch (SQLException ex) {
			logger.error("Error while deleting the data for user : " + ex.getMessage());
			if (conn != null) {
				try {
					logger.error("Transaction is being rolled back");
					conn.rollback();
				} catch (SQLException excep) {
					excep.printStackTrace();
				}
			}
			return false;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return true;
	}

	private boolean deleteTenantData(Connection conn, String tenantName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		// Getting object id from database if exist. If not, the method will
		// return nothing
		// and new tenant will be created.
		String tenantId = dbutil
				.getIdFromDB(getProperties().getProperty("getTenantId").replace("%tenantName%", tenantName), conn);
		if (null == tenantId) {
			logger.info("Tenant does not exist in the database");
			return true;
		}

		String tenantChildrenId = dbutil.getIdFromDB(
				getProperties().getProperty("getTenantChildrenId").replace("%tenantName%", tenantName), conn);
		if (null != tenantChildrenId) {
			tenantId = tenantId + "," + tenantChildrenId;
		}

		logger.info("Deleting automation test Tenant: " + tenantId);
		try {
			conn.setAutoCommit(false);
			dbutil.execute(getProperties().getProperty("deleteTenantPremisesEndpointDeviceChannel")
					.replace("%tenant_id%", tenantId), conn);
			dbutil.execute(getProperties().getProperty("deleteTenantPremisesEndpointDeviceMetadata")
					.replace("%tenant_id%", tenantId), conn);
			dbutil.execute(
					getProperties().getProperty("deleteTenantPremisesEndpointDevice").replace("%tenant_id%", tenantId),
					conn);
			dbutil.execute(
					getProperties().getProperty("deleteTenantEndpointECLCommand").replace("%tenant_id%", tenantId),
					conn);
			dbutil.execute(getProperties().getProperty("deleteTenantECLCommand").replace("%tenant_id%", tenantId),
					conn);
			dbutil.execute(getProperties().getProperty("deleteTenantPremisesEndpointMetadata").replace("%tenant_id%",
					tenantId), conn);
			dbutil.execute(getProperties().getProperty("deleteTenantPremisesEndpoint").replace("%tenant_id%", tenantId),
					conn);
			dbutil.execute(getProperties().getProperty("deleteTenantPremisesPath").replace("%tenant_id%", tenantId),
					conn);
			dbutil.execute(getProperties().getProperty("deleteTenantPremises").replace("%tenant_id%", tenantId), conn);
			dbutil.execute(getProperties().getProperty("deleteTenantEndUserXTenant").replace("%tenant_id%", tenantId),
					conn);
			dbutil.execute(getProperties().getProperty("deleteTenantCommissioningConfigTemplates")
					.replace("%tenant_id%", tenantId), conn);
			dbutil.execute(getProperties().getProperty("deleteTenant").replace("%tenant_id%", tenantId), conn);

			conn.commit();
		} catch (SQLException ex) {
			logger.error("Error while deleting the data for tenant : " + ex.getMessage());
			if (conn != null) {
				try {
					logger.error("Transaction is being rolled back");
					conn.rollback();
				} catch (SQLException excep) {
					excep.printStackTrace();
				}
			}
			return false;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return true;
	}

	private boolean deleteSiteData(Connection conn, String siteName) throws SQLException, IOException {
		logger.info("Deleting the automation test site Site: " + siteName);
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		String siteId = dbutil
				.getIdFromDB(getProperties().getProperty("getPremisesId").replace("%premisesName%", siteName), conn);
		if (null == siteId) {
			logger.info("Site does not exist in the database");
			return true;
		}

		List<String> endPointId = dbutil
				.getDeviceIdFromDB(getProperties().getProperty("getEndPointId").replace("%premisesID%", siteId), conn);
		if (endPointId.size() == 0) {
			logger.info("EndPoint ID does not exist in the database");
		}

		List<String> deviceIdList = new ArrayList<String>();
		for (String endpoint : endPointId) {
			deviceIdList.addAll(dbutil.getDeviceIdFromDB(
					getProperties().getProperty("getDeviceId").replace("%endPointId%", endpoint), conn));
		}

		try {
			conn.setAutoCommit(false);

			for (int i = 0; i < deviceIdList.size(); i++) {
				dbutil.execute(getProperties().getProperty("deleteChannelXVirtualChannelQuery1").replace("%deviceId%",
						deviceIdList.get(i)), conn);
				dbutil.execute(getProperties().getProperty("deleteChannelXVirtualChannelQuery").replace("%deviceId%",
						deviceIdList.get(i)), conn);
				dbutil.execute(getProperties().getProperty("deleteVirtualChannelQuery").replace("%deviceId%",
						deviceIdList.get(i)), conn);
				dbutil.execute(getProperties().getProperty("deleteChannel").replace("%deviceId%", deviceIdList.get(i)),
						conn);
			}
			for (String endpoint : endPointId) {
				dbutil.execute(getProperties().getProperty("deleteDeviceMetaData").replace("%endPointId%", endpoint),
						conn);
				dbutil.execute(getProperties().getProperty("deleteDevice").replace("%endPointId%", endpoint), conn);
				dbutil.execute(
						getProperties().getProperty("deleteSiteEndpointECLCommand").replace("%endPointId%", endpoint),
						conn);
			}
			dbutil.execute(getProperties().getProperty("deleteEndPointMetadata").replace("%premisesID%", siteId), conn);
			dbutil.execute(getProperties().getProperty("deleteEndPoint").replace("%premisesID%", siteId), conn);
			dbutil.execute(getProperties().getProperty("deletePremisesPath").replace("%premisesID%", siteId), conn);
			dbutil.execute(
					getProperties().getProperty("deletePremisesUserLabelXPremises").replace("%premisesID%", siteId),
					conn);
			dbutil.execute(getProperties().getProperty("deletePremisesTag").replace("%premisesID%", siteId), conn);
			dbutil.execute(getProperties().getProperty("deleteSiteECLCommand").replace("%premisesID%", siteId), conn);
			dbutil.execute(getProperties().getProperty("deletePremises").replace("%premisesID%", siteId), conn);

			conn.commit();
		} catch (SQLException ex) {
			logger.error("Error while deleting the data for sitew211  : " + ex.getMessage());
			if (conn != null) {
				try {
					logger.error("Transaction is being rolled back");
					conn.rollback();
				} catch (SQLException excep) {
					excep.printStackTrace();
				}
			}
			return false;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return true;
	}

	private boolean insertDatafixData(Connection conn, String value) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			Properties properties = getProperties();
			conn.setAutoCommit(false);
			if (value.equalsIgnoreCase("DATAFIX")) {
				dbutil.execute(properties.getProperty("insertTenantQuery"), conn);
				dbutil.execute(properties.getProperty("insertPremisesQuery"), conn);
				dbutil.execute(properties.getProperty("insertPremisesPathQuery"), conn);
				dbutil.execute(properties.getProperty("insertEndpointQuery"), conn);
				dbutil.execute(properties.getProperty("insertDeviceQuery"), conn);
				dbutil.execute(properties.getProperty("channelQuery1"), conn);
				// dbutil.execute(properties.getProperty("EC1KRepChannelQuery1"),
				// conn);
				dbutil.execute(properties.getProperty("updateCalcType"), conn);
			} else if (value.equalsIgnoreCase("EC1KREPLACEMENT")) {
				dbutil.execute(properties.getProperty("ec1kinsertTenantQuery"), conn);
				dbutil.execute(properties.getProperty("ec1kinsertPremisesQuery"), conn);
				dbutil.execute(properties.getProperty("ec1kinsertPremisesPathQuery"), conn);
				dbutil.execute(properties.getProperty("ec1kinsertEndpointQuery"), conn);
				dbutil.execute(properties.getProperty("ec1kinsertDeviceQuery"), conn);
				dbutil.execute(properties.getProperty("ec1kchannelQuery1"), conn);
				// dbutil.execute(properties.getProperty("ec1kEC1KRepChannelQuery1"),
				// conn);
				// dbutil.execute(properties.getProperty("ec1kupdateCalcType"),
				// conn);
			}
			conn.commit();
			success = true;
		} catch (SQLException e) {
			logger.error("Transaction is being rolled back");
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	public boolean updateSiteTenant(String tenantName, String siteName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			Properties properties = getProperties();
			conn.setAutoCommit(false);
			String tenantID = dbutil.getIdFromDB(
					getProperties().getProperty("getTenantIdForURP").replace("%tenantName%", tenantName), conn);
			// System.out.println("===================Tennt ID is:"+tenantID);
			dbutil.execute(properties.getProperty("updatePremisesQueryGPUP26329").replace("%tenantID%", tenantID)
					.replace("%siteName%", siteName), conn);
			conn.commit();
			success = true;
		} catch (SQLException e) {
			logger.error("Transaction is being rolled back");
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	public boolean updateUserTenant(String tenantName, String userName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			Properties properties = getProperties();
			conn.setAutoCommit(false);
			String tenantID = dbutil.getIdFromDB(
					getProperties().getProperty("getTenantIdForURP").replace("%tenantName%", tenantName), conn);
			// System.out.println("===================Tennt ID is:"+tenantID);
			dbutil.execute(properties.getProperty("updatePremisesQueryGPUP26329").replace("%tenantID%", tenantID)
					.replace("%siteName%", userName), conn);
			conn.commit();
			success = true;
		} catch (SQLException e) {
			logger.error("Transaction is being rolled back");
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean deleteDatafixData(Connection conn, String value) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			conn.setAutoCommit(false);
			Properties properties = getProperties();
			if (value.equalsIgnoreCase("DATAFIX")) {
				dbutil.execute(properties.getProperty("deleteQuery1"), conn);
				dbutil.execute(properties.getProperty("deleteQuery2"), conn);
				dbutil.execute(properties.getProperty("deleteQuery3"), conn);
				dbutil.execute(properties.getProperty("deleteQuery4"), conn);
				dbutil.execute(properties.getProperty("deleteQuery5"), conn);
				dbutil.execute(properties.getProperty("deleteQuery6"), conn);
				dbutil.execute(properties.getProperty("deleteQuery7"), conn);
				dbutil.execute(properties.getProperty("deleteQuery8"), conn);
				dbutil.execute(properties.getProperty("deleteQuery9"), conn);
				dbutil.execute(properties.getProperty("deleteQuery10"), conn);
				dbutil.execute(properties.getProperty("deleteQuery11"), conn);
				dbutil.execute(properties.getProperty("deleteQuery12"), conn);
				dbutil.execute(properties.getProperty("deleteQuery13"), conn);
				dbutil.execute(properties.getProperty("deleteQuery14"), conn);

			} else if (value.equalsIgnoreCase("EC1KREPLACEMENT")) {
				dbutil.execute(properties.getProperty("ec1kdeleteQuery2"), conn);
				dbutil.execute(properties.getProperty("ec1kdeleteQuery3"), conn);
				dbutil.execute(properties.getProperty("ec1kdeleteQuery4"), conn);
				dbutil.execute(properties.getProperty("ec1kdeleteQuery5"), conn);
				dbutil.execute(properties.getProperty("ec1kdeleteQuery6"), conn);
				dbutil.execute(properties.getProperty("ec1kdeleteQuery7"), conn);
				dbutil.execute(properties.getProperty("ec1kdeleteQuery8"), conn);
				dbutil.execute(properties.getProperty("ec1kdeleteQuery9"), conn);
				dbutil.execute(properties.getProperty("ec1kdeleteQuery10"), conn);
				dbutil.execute(properties.getProperty("ec1kdeleteQuery11"), conn);
				dbutil.execute(properties.getProperty("ec1kdeleteQuery12"), conn);
				dbutil.execute(properties.getProperty("ec1kdeleteQuery13"), conn);
				dbutil.execute(properties.getProperty("ec1kdeleteQuery14"), conn);
			}
			conn.commit();
			success = true;

		} catch (SQLException e) {
			logger.error("Transaction is being rolled back");
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	

	private boolean insertUserData(Connection conn, String userName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			Properties properties = getProperties();
			String insertUserQuery = properties.getProperty("insertUserQuery").replace("%username%", userName)
					.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName));
			conn.setAutoCommit(false);
			dbutil.execute(insertUserQuery, conn);
			conn.commit();
			success = true;
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean insertAuditData(Connection conn, String userName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			Properties properties = getProperties();
			conn.setAutoCommit(false);
			String query = null;
			String dateValue = null;
			Calendar calendar;
			Date currentDate;
			DateFormat outputFormatter;
			calendar = Calendar.getInstance();
			currentDate = calendar.getTime();
			outputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:MM:YY");
			dateValue = outputFormatter.format(currentDate);
			if (userName.toLowerCase().contains("provisioner")) {
				query = properties.getProperty("insertAuditProvisionerUserQuery").replace("%username%", userName)
						.replace("%activationDate%", dateValue)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName));
			} else if (userName.toLowerCase().contains("auditpage_gpsupportuserbot")) {
				query = properties.getProperty("insertAuditGPSupportUserQuery").replace("%username%", userName)
						.replace("%activationDate%", dateValue)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName));
			}
			dbutil.execute(query, conn);
			conn.commit();
			success = true;
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean deleteAuditUserData(Connection conn, String attribute) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			Properties properties = getProperties();
			conn.setAutoCommit(false);
			dbutil.execute(properties.getProperty("deleteAuditUserQuery").replace("%username%", attribute), conn);
			conn.commit();
			success = true;
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean deleteEndpointFirmwareData(Connection conn, String endpointFirmwareName)
			throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			conn.setAutoCommit(false);
			Properties properties = getProperties();
			dbutil.execute(properties.getProperty("deleteEndpointFirmwareDataQuery1").replace("%endpointFirmwareName%",
					endpointFirmwareName), conn);
			dbutil.execute(properties.getProperty("deleteEndpointFirmwareDataQuery2").replace("%endpointFirmwareName%",
					endpointFirmwareName), conn);
			dbutil.execute(properties.getProperty("deleteEndpointFirmwareDataQuery3").replace("%endpointFirmwareName%",
					endpointFirmwareName), conn);
			dbutil.execute(properties.getProperty("deleteEndpointFirmwareDataQuery4").replace("%endpointFirmwareName%",
					endpointFirmwareName), conn);
			dbutil.execute(properties.getProperty("deleteEndpointFirmwareDataQuery5").replace("%endpointFirmwareName%",
					endpointFirmwareName), conn);
			conn.commit();
			success = true;
			System.out.println("Endpoint Firmware Deleted");
		} catch (SQLException e) {
			logger.error("Transaction is being rolled back");
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean checkEndpointFirmwareData(Connection conn, String endpointFirmwareName)
			throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		try {
			String firmwareId = dbutil.getIdFromDB(getProperties().getProperty("getEndpointFirmwareId")
					.replace("%endpointFirmwareName%", endpointFirmwareName), conn);
			if (null == firmwareId) {
				return false;
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return true;
	}

	/*
	 * public String getCount(String tabName, String userName, String tenantName,
	 * TestBase testBase) throws SQLException, IOException { Connection conn =
	 * getConnection(testBase); GPDataBaseUtil dbutil = new GPDataBaseUtil(); String
	 * sitecount = null; //String siteID=null; switch (tabName.toUpperCase()) { case
	 * "SITES": try { sitecount = dbutil.getIdFromDB(
	 * getProperties().getProperty("getTotalSites").replace("%userName%", userName),
	 * conn);
	 * 
	 * return sitecount;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * case "ENDPOINTS": try {
	 * 
	 * String tenantID = dbutil.getIdFromDB(
	 * getProperties().getProperty("getTotalSites").replace("%userName%", userName),
	 * conn); String siteID = dbutil.getIdFromDB(
	 * getProperties().getProperty("getTotalSites").replace("%userName%", userName),
	 * conn);
	 * 
	 * return sitecount;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * finally { if (conn != null) { conn.close(); } }
	 * 
	 * logger.error("Switch Case[" + tabName + "] is not matched in class[" +
	 * getClass().getName() + "] , Method[" +
	 * Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
	 * Assert.fail("Invalid element name : " + tabName + " selected"); } return
	 * sitecount;
	 * 
	 * }
	 */

	private boolean deleteEndpointPageVariousAccordionData(Connection conn, String endpointName, String accordion)
			throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		Properties properties = getProperties();
		String endpointId;
		boolean success = false;
		conn.setAutoCommit(false);
		try {
			switch (accordion.toUpperCase().trim()) {
			case "HISTORY":
				endpointId = dbutil.getIdFromDB(
						getProperties().getProperty("getEndpointId").replace("%endpointName%", endpointName), conn);
				if (endpointId != null) {
					dbutil.execute(properties.getProperty("deleteEndpointPageHistoryAccordion").replace("%endpoint_id%",
							endpointId), conn);
					conn.commit();
				} else {
					logger.info("endpoint id is Null for " + endpointName);
				}
				success = true;
				break;
			case "DEVICES":
				endpointId = dbutil.getIdFromDB(
						getProperties().getProperty("getEndpointId").replace("%endpointName%", endpointName), conn);
				System.out.println("Endpoint ID" + endpointId);
				if (endpointId != null) {
					dbutil.execute(properties.getProperty("deleteEndpointPageDevicesAccordion").replace("%endpoint_id%",
							endpointId), conn);
					conn.commit();
				} else {
					logger.info("endpoint id is Null for " + endpointName);
				}
				success = true;
				break;
			case "AUDIT":
				logger.info("endpoint Firmware Name: " + endpointName);
				dbutil.execute(properties.getProperty("deleteEndpointPageAuditAccordion")
						.replace("%endpointFirmwareName%", endpointName), conn);
				conn.commit();
				success = true;
				break;
			default:
				logger.info("switch case is not matched");
				success = false;
				break;
			}
		} catch (SQLException e) {
			logger.error("Transaction is being rolled back");
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean insertEndpointPageVariousAccordionData(Connection conn, String endpointName, String accordion)
			throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		Properties properties = getProperties();
		String endpointId = dbutil.getIdFromDB(
				getProperties().getProperty("getEndpointId").replace("%endpointName%", endpointName), conn);
		System.out.println("Endpoint Id = " + endpointId);
		conn.setAutoCommit(false);
		try {
			switch (accordion.toUpperCase()) {
			case "HISTORY":
				if (endpointId != null) {
					dbutil.execute(properties.getProperty("insertEndpointPageHistoryAccordion").replace("%endpoint_id%",
							endpointId), conn);
					conn.commit();
				} else {
					logger.info("endpoint id is Null for " + endpointName);
				}
				success = true;
				break;
			case "DEVICES":
				if (endpointId != null) {
					System.out.println("ENtered in If statemnt under Device Accordion");
					dbutil.execute(properties.getProperty("insertEndpointPageDevicesAccordion").replace("%endpoint_id%",
							endpointId), conn);
					conn.commit();
				} else {
					logger.info("endpoint id is Null for " + endpointName);
				}
				success = true;
				break;
			default:
				logger.info("switch case is not matched");
				success = false;
				break;
			}

		} catch (SQLException e) {
			logger.error("Transaction is being rolled back");
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean insertPVAuditData(Connection conn, String pvAuditSiteName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {

			Properties properties = getProperties();

			conn.setAutoCommit(false);
			String query = null;
			int pvAuditID = -630001;
			if (pvAuditSiteName.equalsIgnoreCase("sitePage_siteBot1")) {
				for (int i = 1; i <= 9; i++) {
					query = properties.getProperty("insertPVAuditQuery").replace("%pv_audit_id%",
							String.valueOf(pvAuditID));
					pvAuditID--;
					dbutil.execute(query, conn);
				}
				conn.commit();
				success = true;
			}
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean deletePVAuditData(Connection conn, List<String> sitename) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			Properties properties = getProperties();
			conn.setAutoCommit(false);
			String query = null;
			for (String pvAuditSitenames : sitename) {
				query = properties.getProperty("deletePVAuditQuery").replace("%site_name%", pvAuditSitenames);
				dbutil.execute(query, conn);
				conn.commit();
			}
			success = true;
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean insertUserRolePermissionData(Connection conn, String userName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			Properties properties = getProperties();
			conn.setAutoCommit(false);
			String query = null;
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String currentdatetime = dateFormat.format(cal.getTime());
			if (userName.equalsIgnoreCase("URP_GPAdmin_testBot")) {
				query = properties.getProperty("insertGPAdminUserRolePermissionsQuery").replace("%username%", userName)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName))
						.replace("%activation_date%", currentdatetime);
			} else if (userName.equalsIgnoreCase("URP_GPSupport_testBot")) {
				query = properties.getProperty("insertGPSupportUserRolePermissionsQuery")
						.replace("%username%", userName)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName))
						.replace("%activation_date%", currentdatetime);
				query = properties.getProperty("insertGPSupportUserRolePermissionsQuery")
						.replace("%username%", userName)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName))
						.replace("%activation_date%", currentdatetime);
			} else if (userName.equalsIgnoreCase("URP_GPAnalyst_testBot")) {
				query = properties.getProperty("insertGPAnalystUserRolePermissionsQuery")
						.replace("%username%", userName)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName))
						.replace("%activation_date%", currentdatetime);
			} else if (userName.equalsIgnoreCase("URP_CustomerSiteManager_testBot")) {
				query = properties.getProperty("insertCustomerSiteManagerUserRolePermissionsQuery")
						.replace("%username%", userName)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName))
						.replace("%activation_date%", currentdatetime);
			} else if (userName.equalsIgnoreCase("URP_CustomerAnalyst_testBot")) {
				query = properties.getProperty("insertCustomerAnalystUserRolePermissionsQuery")
						.replace("%username%", userName)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName))
						.replace("%activation_date%", currentdatetime);
			} else if (userName.equalsIgnoreCase("userRolePermissions_CommissionerUserBot")) {
				query = properties.getProperty("insertCommissionerUserRolePermissionsQuery")
						.replace("%username%", userName)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName))
						.replace("%activation_date%", currentdatetime);
			} else if (userName.equalsIgnoreCase("userRolePermissions_ProvisionerUserBot")) {
				query = properties.getProperty("insertProvisionerAdminUserRolePermissionsQuery")
						.replace("%username%", userName)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName))
						.replace("%activation_date%", currentdatetime);
			} else if (userName.equalsIgnoreCase("GPUP-26329_testUser")
					|| userName.equalsIgnoreCase("GPUP-30805_testUser")) {
				query = properties.getProperty("updateGPUP26329testUserQuery").replace("%username%", userName)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName));
			}
//			TODO : Added by sonu on 11 sep 2018.... curretly commenting it to avoid compilation issues....verify
			else if (userName.equalsIgnoreCase("URP_GPAdmin_testBot1")) {
				query = properties.getProperty("insertGPAdminUserRolePermissionsQuery1").replace("%username%", userName)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName))
						.replace("%activation_date%", currentdatetime);
			}else if (userName.equalsIgnoreCase("testUser_1")) {
				query = properties.getProperty("insertPartnerCommissionerUserRolePermissionQuery1").replace("%username%", userName)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName));
			}  else if (userName.equalsIgnoreCase("testUser_2")) {
				query = properties.getProperty("insertPartnerCommissionerUserRolePermissionQuery2").replace("%username%", userName)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName));
			} else if (userName.equalsIgnoreCase("testUser_3")) {
				query = properties.getProperty("insertPartnerCommissionerUserRolePermissionQuery3").replace("%username%", userName)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName));
			} else if (userName.equalsIgnoreCase("testUser_4")) {
				query = properties.getProperty("insertPartnerCommissionerUserRolePermissionQuery4").replace("%username%", userName)
						.replace("%password%", myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName));
			} 
			//////////////////////////////////////////
			dbutil.execute(query, conn);
			conn.commit();
			success = true;
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	public boolean updateUser(String tenantName, String userName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			Properties properties = getProperties();
			conn.setAutoCommit(false);
			// String tenantName;
			// tenantName = "GridPoint Test Automation";
			// tenantName = "GridpointTestSites";
			if (userName.equalsIgnoreCase("URP_GPAdmin_testBot") || userName.equalsIgnoreCase("GPUP-30805_testUser")) {
				// TODO : updated by k on 10 May 2018 10:18 PM....verify
				//tenantName = "GridPoint Test Automation";
				tenantName = "GridPoint";
			} else if (userName.equalsIgnoreCase("GPUP-26329_testUser")) {
				tenantName = "GPUP-26329_testTenantBot";
			}
			String tenantID = dbutil.getIdFromDB(
					getProperties().getProperty("getTenantIdForURP").replace("%tenantName%", tenantName), conn);
			String userID = dbutil
					.getIdFromDB(getProperties().getProperty("getUsersIdForURP").replace("%userName%", userName), conn);
			if (tenantID != null && userID != null) {
				dbutil.execute(properties.getProperty("associateUserToTenantAndSite").replace("%tenantID%", tenantID)
						.replace("%enduserID%", userID), conn);
			} else {
				logger.error("Tenant ID/User ID is null");
			}
			conn.commit();
			success = true;

		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean insertURP(Connection conn, String userName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			Properties properties = getProperties();
			conn.setAutoCommit(false);
			String tenantName;
			tenantName = "GridpointTestSites";
			
			
			if (userName.equalsIgnoreCase("URP_GPAdmin_testBot") || userName.equalsIgnoreCase("URP_GPSupport_testBot")
					|| userName.equalsIgnoreCase("URP_GPAnalyst_testBot")
					|| userName.equalsIgnoreCase("URP_CustomerAnalyst_testBot")
					|| userName.equalsIgnoreCase("URP_CustomerSiteManager_testBot")) {
				tenantName = "GridPoint Test Automation";
			} else if (userName.equalsIgnoreCase("GPUP-26329_testUser")) {
				tenantName = "GPUP-26329_testTenantBot";
			}
			
			// TODO : above code updated as given beow in k branch by sonu on 11 sep 2018.... curretly commenting it to avoid compilation issues....verify
//			if (userName.equalsIgnoreCase("URP_GPAnalyst_testBot")
//					|| userName.equalsIgnoreCase("URP_CustomerAnalyst_testBot")
//					|| userName.equalsIgnoreCase("URP_CustomerSiteManager_testBot")) {
//				tenantName = "GridPoint Test Automation";
//
//			} else if (userName.equalsIgnoreCase("URP_GPAdmin_testBot")
//					|| userName.equalsIgnoreCase("URP_GPSupport_testBot")
//					|| userName.equalsIgnoreCase("URP_GPAdmin_testBot1")
//					|| userName.equalsIgnoreCase("testUser_1")
//					|| userName.equalsIgnoreCase("testUser_2")
//					|| userName.equalsIgnoreCase("testUser_3")
//					|| userName.equalsIgnoreCase("testUser_4")) {
//				tenantName = "GridPoint";
//			} else if (userName.equalsIgnoreCase("GPUP-26329_testUser")) {
//				tenantName = "GPUP-26329_testTenantBot";
//			}
			
			// TODO : above code is changed by k as given below on 10 May 2018 10:18 PM....verify
			if (userName.equalsIgnoreCase("URP_GPAnalyst_testBot")|| userName.equalsIgnoreCase("URP_CustomerAnalyst_testBot")
					|| userName.equalsIgnoreCase("URP_CustomerSiteManager_testBot")) {
				tenantName = "GridPoint Test Automation";
			
			}else if (userName.equalsIgnoreCase("URP_GPAdmin_testBot") || userName.equalsIgnoreCase("URP_GPSupport_testBot")) 
			{
				tenantName = "GridPoint";
			}
			else if (userName.equalsIgnoreCase("GPUP-26329_testUser")) {
				tenantName = "GPUP-26329_testTenantBot";
			}
			//////////////////////////////////////////////////////////
			
			String tenantID = dbutil.getIdFromDB(
					getProperties().getProperty("getTenantIdForURP").replace("%tenantName%", tenantName), conn);
			String userID = dbutil
					.getIdFromDB(getProperties().getProperty("getUsersIdForURP").replace("%userName%", userName), conn);
			if (tenantID != null && userID != null) {
				dbutil.execute(properties.getProperty("associateUserToTenantAndSite").replace("%tenantID%", tenantID)
						.replace("%enduserID%", userID), conn);
			} else {
				logger.error("Tenant ID/User ID is null");
			}
			conn.commit();
			success = true;

		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean deleteUserRolePermissionData(Connection conn, List<String> userName, String tenantName)
			throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			conn.setAutoCommit(false);
			String query0 = null, query1 = null, query2 = null, query3 = null, query4 = null, query5 = null,
					query6 = null, query7 = null;
			for (String userNamee : userName) {
				String tenantID = dbutil.getIdFromDB(
						getProperties().getProperty("getTenantIdForURP").replace("%tenantName%", tenantName), conn);
				String userID = dbutil.getIdFromDB(
						getProperties().getProperty("getUsersIdForURP").replace("%userName%", userNamee), conn);
				if (tenantID != null && userID != null) {
					query0 = properties.getProperty("deleteUserRolePermissionsQuery0").replace("%enduserID%", userID)
							.replace("%tenantID%", tenantID);
					dbutil.execute(query0, conn);
				}
				if (userID != null) {
					query0 = properties.getProperty("deleteUserRolePermissionsQuery0").replace("%enduserID%", userID);
					dbutil.execute(query0, conn);
					query1 = properties.getProperty("deleteUserRolePermissionsQuery1").replace("%enduserID%", userID);
					dbutil.execute(query1, conn);
				}
				query2 = properties.getProperty("deleteUserRolePermissionsQuery2").replace("%username%", userNamee);
				query3 = properties.getProperty("deleteUserRolePermissionsQuery3").replace("%username%", userNamee);
				query4 = properties.getProperty("deleteUserRolePermissionsQuery4").replace("%username%", userNamee);
				query5 = properties.getProperty("deleteUserRolePermissionsQuery5").replace("%username%", userNamee);
				query6 = properties.getProperty("deleteUserRolePermissionsQuery6").replace("%username%", userNamee);
				query7 = properties.getProperty("deleteUserRolePermissionsQuery7").replace("%username%", userNamee);

				dbutil.execute(query2, conn);
				dbutil.execute(query3, conn);
				dbutil.execute(query4, conn);
				dbutil.execute(query5, conn);
				dbutil.execute(query6, conn);
				dbutil.execute(query7, conn);
				conn.commit();
			}
			success = true;
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	public String getID(String type, String name, String... obj) throws SQLException, IOException {
		Connection conn = getConnection();
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		try {
			conn.setAutoCommit(false);
			switch (type.toUpperCase()) {
			case "SITE":
				return dbutil.getIdFromDB(getProperties().getProperty("getPremisesId").replace("%premisesName%", name),
						conn);
			case "ENDPOINT":
				return dbutil.getIdFromDB(getProperties().getProperty("getEndPointId").replace("%premisesID%", name),
						conn);
			case "DEVICE":
				return dbutil.getIdFromDB(getProperties().getProperty("getUniqEndpointId").replace("%premisesID%", name)
						.replace("%deviceName%", obj[0]), conn);

			default:
				break;
			}
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();

			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return null;
	}

	public boolean ec1kReplacementControllerStatus(String task, Object value, String endpointSerial)
			throws SQLException, IOException {
		Connection conn = getConnection();
		if (task.equalsIgnoreCase("Replace_controller")) {
			return updateDisconnectDate(conn, (String) value, endpointSerial);
		}
		return true;
	}

	private boolean updateDisconnectDate(Connection conn, String controllerStatus, String endpointSerial)
			throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			Properties properties = getProperties();
			conn.setAutoCommit(false);
			String query = null;
			String query2 = null;
			if (controllerStatus.equals("DISCONNECTED (Suspended)")) {
				query = properties.getProperty("replaceController").replace("%endpointSerial%", endpointSerial);
			} else if (controllerStatus.equals("CONNECTED")) {
				query = properties.getProperty("liveController").replace("%endpointSerial%", endpointSerial);
				query2 = properties.getProperty("connectDate").replace("%endpointSerial%", endpointSerial);
				dbutil.execute(query2, conn);
			}
			dbutil.execute(query, conn);

			conn.commit();
			success = true;
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	private boolean deletePeripheralFirmwareData(Connection conn, String peripheralFirmwareName)
			throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		try {
			conn.setAutoCommit(false);
			Properties properties = getProperties();
			dbutil.execute(properties.getProperty("deletePeripheralFirmwareDataQuery1"), conn);
			conn.commit();
			success = true;
		} catch (SQLException e) {
			logger.error("Transaction is being rolled back");
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	public Properties getProperties() throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = new FileInputStream(filePath);
		properties.load(inputStream);
		return properties;
	}

	private Connection getConnection() throws SQLException {
		GPConnection gpcon = new GPConnection();
		return gpcon.getConnection(TestBase.getDbUrl(), TestBase.getDbUsername(), TestBase.getDbPassword());
	}

	public boolean deleteVirtualChannel(Connection conn, String vChannelName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		boolean success = false;
		String query0 = null, query1 = null, query2 = null, query3 = null;
		try {
			conn.setAutoCommit(false);
			Properties properties = getProperties();
			query0 = properties.getProperty("deleteUserRolePermissionsVCQuery0").replace("%channel_name%",
					vChannelName);
			query1 = properties.getProperty("deleteUserRolePermissionsVCQuery1").replace("%channel_name%",
					vChannelName);
			query2 = properties.getProperty("deleteUserRolePermissionsVCQuery2").replace("%channel_name%",
					vChannelName);
			query3 = properties.getProperty("deleteUserRolePermissionsVCQuery3").replace("%channel_name%",
					vChannelName);

			dbutil.execute(query0, conn);
			dbutil.execute(query1, conn);
			dbutil.execute(query2, conn);
			dbutil.execute(query3, conn);

			conn.commit();
			success = true;
		} catch (SQLException e) {
			logger.error("Transaction is being rolled back");
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
		return success;
	}

	public int[] selectSite(String tenantName) throws SQLException, IOException {
		Connection conn = getConnection();
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Statement stmt = conn.createStatement();
		@SuppressWarnings("unused")
		int TotalSiteCount, TotalActiveSiteCount, TotalsubTenantsSite;
		String getSubTenantId = null, rootTenantSiteCount = null, rootTenantActiveSiteCount = null,
				subTenantsSiteCount = null, activeSubTenantsSiteCount = null, queryGetGCTenantId = null;
		@SuppressWarnings("unused")
		List<String> tenantsList = null;
		List<String> subTenantsList = null;
		List<String> gChildTenantsList1 = null;
		List<String> gChildTenantsList = null;
		try {
			conn.setAutoCommit(false);
			Properties properties = getProperties();
			rootTenantSiteCount = properties.getProperty("testQuery2").replace("%tenant%", tenantName);
			rootTenantActiveSiteCount = properties.getProperty("activeSitestestQuery2").replace("%tenant%", tenantName);
			TotalSiteCount = Integer.parseInt(dbutil.getIdFromDB(rootTenantSiteCount, conn));
			TotalActiveSiteCount = Integer.parseInt(dbutil.getIdFromDB(rootTenantActiveSiteCount, conn));
			getSubTenantId = properties.getProperty("testQuery");
			subTenantsList = dbutil.getDeviceIdFromDB(getSubTenantId.replace("%mainTenant%", tenantName), conn);
			while (subTenantsList != null) {
				for (int i = 0; i < subTenantsList.size(); i++) {
					subTenantsSiteCount = properties.getProperty("siteCountInSubTenant").replace("%SubTenantId%",
							subTenantsList.get(i));

					activeSubTenantsSiteCount = properties.getProperty("activeSiteCountInSubTenant")
							.replace("%SubTenantId%", subTenantsList.get(i));

					TotalSiteCount = TotalSiteCount + Integer.parseInt(dbutil.getIdFromDB(subTenantsSiteCount, conn));
					TotalActiveSiteCount = TotalActiveSiteCount
							+ Integer.parseInt(dbutil.getIdFromDB(activeSubTenantsSiteCount, conn));
				}
				for (int j = 0; j < subTenantsList.size(); j++) {
					queryGetGCTenantId = properties.getProperty("subTenantList2").replace("%subTenantList1Id%",
							subTenantsList.get(j));
					gChildTenantsList1 = dbutil.getDeviceIdFromDB(
							queryGetGCTenantId.replace("%subTenantList1Id%", subTenantsList.get(j)), conn);
					if (gChildTenantsList1 != null && !gChildTenantsList1.isEmpty()) {
						if (gChildTenantsList == null) {
							gChildTenantsList = gChildTenantsList1;
						} else {
							gChildTenantsList.addAll(gChildTenantsList1);
						}
					}
				}
				subTenantsList = null;
				subTenantsList = gChildTenantsList;
				gChildTenantsList = null;
			}
			conn.commit();
			int[] intArray = new int[2];
			intArray[0] = TotalSiteCount;
			intArray[1] = TotalActiveSiteCount;
			return intArray;
		} catch (SQLException e) {
			logger.error("Transaction is being rolled back");
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
	}

	// public boolean deleteVirtualChannel(Connection conn, String vChannelName,
	// TestBase testBase) throws SQLException, IOException {
	// GPDataBaseUtil dbutil = new GPDataBaseUtil();
	// Statement stmt = conn.createStatement();
	// boolean success = false;
	// String query0 = null,query1 = null;
	// try {
	// conn.setAutoCommit(false);
	// Properties properties = getProperties();
	// query0 =
	// properties.getProperty("deleteUserRolePermissionsVCQuery0").replace("%channel_name%",
	// vChannelName);
	// query1 =
	// properties.getProperty("deleteUserRolePermissionsVCQuery1").replace("%channel_name%",
	// vChannelName);
	//
	// dbutil.execute(query0, conn);
	// dbutil.execute(query1, conn);
	//
	// conn.commit();
	// success = true;
	// } catch (SQLException e) {
	// logger.error("Transaction is being rolled back");
	// conn.rollback();
	// throw new SQLException(e);
	// } finally {
	// if (null != conn) {
	// conn.close();
	// }
	// if (null != stmt) {
	// stmt.close();
	// }
	// }
	// return success;
	// }

	public void updateUserPassword(String userName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		try {
			Properties properties = getProperties();
			String updateUserPassword = properties.getProperty("updateUserPassword").replace("%password%",
					myEncoder.encodePassword(DEFAULT_USER_PASSWORD, userName).replace("%username%", userName));
			conn.setAutoCommit(false);
			dbutil.execute(updateUserPassword, conn);
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
	}

	//
	public void updateTempUserPassword(String userName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		try {
			Properties properties = getProperties();
			String updateUserPassword = properties.getProperty("updateTempUserPassword").replace("%username%",
					userName);
			conn.setAutoCommit(false);
			dbutil.execute(updateUserPassword, conn);
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
	}

	//
	public void deleteTestUser(String userName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		try {
			Properties properties = getProperties();
			String deleteTestUser = properties.getProperty("deleteTestUser").replace("%username%", userName);

			conn.setAutoCommit(false);
			dbutil.execute(deleteTestUser, conn);
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				logger.error("Transaction is being rolled back");
				conn.rollback();
			}
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
	}

	//
	public int getAssocatedEndpointCount(String userName) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		try {
			conn.setAutoCommit(false);

			int endpointCount = dbutil.getRowsCountFromDB(
					getProperties().getProperty("getTenantIdQueryGPUP30805").replace("%userName%", userName), conn);
			conn.commit();
			return endpointCount;

		} catch (SQLException e) {
			logger.error("Transaction is being rolled back");
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (null != conn) {
				conn.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}

	}

}
