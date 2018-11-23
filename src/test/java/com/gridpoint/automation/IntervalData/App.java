package com.gridpoint.automation.IntervalData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;

import com.gridpoint.automation.tests.base.TestBase;

public class App {
	private Map<String, String> records;
	private Map<String, String> records2;
	private Map<String, String> records3;

	public App() {
	}

	public void createIntervalData() {
		try {
			records = TestBase.getReadResources().getValuesFromXml("Configuration.xml", "apiconfig");
			records2 = TestBase.getReadResources().getValuesFromXml("Configuration.xml", "gridpoint");
			records3 = TestBase.getReadResources().getValuesFromXml("Configuration.xml", "environment_url");
			SerializeData sd = new SerializeData();
			IntervalJSON json = new IntervalJSON();
			String envName = records2.get("env").toString();
			String host = records3.get(envName).toString();
			String userName = records.get("adminUsername").toString();
			String password = records.get("adminPassword").toString();
			String dataFile = records.get("dataFile").toString();
			sd.autorizeConnection(host, userName, password);

			// Writing data to file
			FileWriter file = new FileWriter("intervalData.txt");
			BufferedWriter bw = new BufferedWriter(file);
			PrintWriter out = new PrintWriter(bw);
			sd.provisionTypeThree(dataFile, json, records.get("durationMsecs").toString(), host, out);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public static void main(String[] args) {
	// new App().createIntervalData();
	// }

}
