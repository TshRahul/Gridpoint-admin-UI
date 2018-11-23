package com.gridpoint.automation.IntervalData;

import org.json.simple.*;

public class IntervalJSON {

	@SuppressWarnings("unchecked")
	public String createJSON(int value, int minChannelType, int maxChannelType, int devicesPerEP,
			int deviceStartAddress, int deviceEndAddress, long endTime, int durationMsecs) {
		JSONObject root = new JSONObject();
		JSONObject intervalObjecct = new JSONObject();
		intervalObjecct.put("endTime", endTime);
		intervalObjecct.put("durationMsecs", durationMsecs);

		JSONArray channelArray = new JSONArray();
		for (int i = minChannelType; i <= maxChannelType; i++) {
			JSONObject channelObject = new JSONObject();
			channelObject.put("value", Integer.valueOf(value));
			channelObject.put("channelType", Integer.valueOf(i));

			channelArray.add(channelObject);
		}
		JSONArray deviceArray = new JSONArray();

		for (int i = deviceStartAddress; i > deviceStartAddress - devicesPerEP; i--) {
			JSONObject deviceObject = new JSONObject();
			deviceObject.put("deviceAddress", i);
			deviceObject.put("channels", channelArray);
			deviceArray.add(deviceObject);
		}

		intervalObjecct.put("devices", deviceArray);

		JSONArray courses = new JSONArray();
		courses.add(intervalObjecct);

		root.put("intervals", courses);
		return root.toJSONString();
	}

	@SuppressWarnings("unchecked")
	public String createJSON(int value, String channelType, int deviceAddress, long endTime, int durationMsecs) {
		JSONObject root = new JSONObject();
		JSONObject intervalObjecct = new JSONObject();
		intervalObjecct.put("endTime", endTime);
		intervalObjecct.put("durationMsecs", durationMsecs);

		JSONArray channelArray = new JSONArray();
		JSONObject channelObject = new JSONObject();
		channelObject.put("value", Integer.valueOf(value));
		channelObject.put("channelType", channelType);

		channelArray.add(channelObject);

		JSONArray deviceArray = new JSONArray();
		JSONObject deviceObject = new JSONObject();
		deviceObject.put("deviceAddress", deviceAddress);
		deviceObject.put("channels", channelArray);
		deviceArray.add(deviceObject);

		intervalObjecct.put("devices", deviceArray);

		JSONArray courses = new JSONArray();
		courses.add(intervalObjecct);

		root.put("intervals", courses);
		/*
		 * try (FileWriter file = new FileWriter("intervalData.txt")) {
		 * 
		 * file.write(root.toJSONString()); file.flush();
		 * 
		 * } catch (IOException e) { e.printStackTrace(); }
		 */
		return root.toJSONString();
	}
}
