package com.gridpoint.adminui.automation.firmware;

//import com.gridpoint.adminui.automation.testdata.DeleteTestData;

public class EndpointFirmwarePageTest {/*
	// private static Logger logger = Logger.getLogger(AuditPageTest.class);

	private EndpointFirmwarePage endpointFirmwarePage;
	private Map<String, String> endpointFirmwarePageObjectRepo;
	private Map<String, String> endpointFirmwarePageObjectText;
	private Map<String, String> endpointFirmwarePageObjectTestData;
	private static Boolean checkExistingEndpointFirmware = true;

	// TestBase testBase;
	static int i;

	@Given("^Initialize resources in the beginning of each scenario for firmware page$")
	public void initialize_resources_in_the_beginning_of_each_scenario_for_endpoint_firmware_Page() throws Throwable {
		TestBase.getInstance();
		endpointFirmwarePageObjectRepo = TestBase.getReadResources().getValuesFromXml("EndpointFirmwarePage.xml",
				"endpointFirmwareObjectRepository");
		endpointFirmwarePageObjectText = TestBase.getReadResources().getValuesFromXml("EndpointFirmwarePage.xml",
				"endpointFirmwareObjectText");
		endpointFirmwarePageObjectTestData = TestBase.getReadResources().getValuesFromXml("EndpointFirmwarePage.xml",
				"endpointFirmwarePageObjectTestData");
		endpointFirmwarePage = new EndpointFirmwarePage();
		endpointFirmwarePage.overrideTimeout(endpointFirmwarePageObjectRepo.get("timeout"));
		endpointFirmwarePage.overridePollingfrequency(endpointFirmwarePageObjectRepo.get("pollingFrequency"));
		TestBase.userLogin();
		TestBase.setWebDriverWait(TestBase.getWebDriverWait());
		endpointFirmwarePage.waitForSpinner();

	}

	@When("^we will land on sites tab, we will click on firmware tab$")
	public void we_will_land_on_Dashboard_page_we_will_click_on_firmware_tab() throws Throwable {
		endpointFirmwarePage.waitForSpinner();
		// endpointFirmwarePage.explicitWait(3000);
		endpointFirmwarePage.click(endpointFirmwarePageObjectRepo.get("FirmwareTab"));
	}

	@When("^verify firmware tab has two sub option:$")
	public void verify_firmware_tab_has_two_sub_option(DataTable args) throws Throwable {
		List<String> lstData = args.asList(String.class);
		args.asList(String.class);
		boolean isVisible = true;
		for (String header : lstData) {
			boolean visible = false;
			if (header.trim().equals("Enpoint Firmware")) {
				visible = endpointFirmwarePage
						.verifyElementDisplayed(endpointFirmwarePageObjectRepo.get("endpointFirmwareTab"));
			}
			if (header.trim().equals("Peripheral Firmware")) {
				visible = endpointFirmwarePage
						.verifyElementDisplayed(endpointFirmwarePageObjectRepo.get("peripheralFirmwareTab"));
			}

			if (visible == false) {
				if (visible == false) {
					isVisible = false;
					Assert.assertTrue("Header [" + header + "] is not visible in Endpoint Firmware Grid", isVisible);
					break;
				}
			}
		}
	}

	@When("^we will click on firmware tab, we will click on endpoint firmware tab$")
	public void we_will_click_on_firmware_tab_we_will_click_on_endpoint_firmware_tab() throws Throwable {
		endpointFirmwarePage.waitForSpinner();
		endpointFirmwarePage.click(endpointFirmwarePageObjectRepo.get("endpointFirmwareTab"));
	}

	@When("^verify by default Endpoint Firmware page should display on FirmwareTab$")
	public void verify_by_default_Endpoint_Firmware_page_should_display_on_FirmwareTab() throws Throwable {
		String message = "Failed to verify endpointFirmware grid availablity.  ";
		endpointFirmwarePage.assertElementDisplayed(endpointFirmwarePageObjectRepo.get("endpointFirmwareDetailGrid"),
				message);
	}

	@When("^verify that \"(.*?)\" exist or not$")
	public void verify(String endpointFirmwareName) throws Throwable {
		DeleteTestData objDelTestdata = new DeleteTestData();
		checkExistingEndpointFirmware = objDelTestdata.deleteTestData("Endpoint_Firmware_Check", endpointFirmwareName);
	}

	@Then("^verify the endpoint firmware page by confirming the availability of endpoint firmware grid$")
	public void verify_the_endpoint_firmware_page_by_confirming_the_availability_of_endpoint_firmware_grid()
			throws Throwable {
		String message = "Failed to verify endpointFirmware grid availablity.  ";
		endpointFirmwarePage.assertElementDisplayed(endpointFirmwarePageObjectRepo.get("endpointFirmwareDetailGrid"),
				message);
	}

	@Given("^we are on Endpoint firmware page and search endpoint firmware field is enabled$")
	public void we_are_on_Endpoint_firmware_page_and_search_endpoint_firmware_field_is_enabled() throws Throwable {
		endpointFirmwarePage.clickAndWait(endpointFirmwarePageObjectRepo.get("endpointFirmwareTab"));
		endpointFirmwarePage.waitForSpinner();
		String message = "Failed to verify endpointFirmware grid availablity.  ";
		endpointFirmwarePage.assertElementDisplayed(TestBase.getReadConfigData().get("optionDropDown"), message);
		endpointFirmwarePage.assertElementDisplayed(endpointFirmwarePageObjectRepo.get("endpointFirmwareDetailGrid"),
				message);

	}

	@Then("^verify the following columns on \"(.*?)\" sub tab$")
	public void verify_the_following_columns_on_updateEndpointFirmware_grid(String tabName, DataTable args)
			throws Throwable {

		List<String> lstData = args.asList(String.class);
		args.asList(String.class);
		boolean isVisible = true;
		for (String header : lstData) {
			boolean visible = false;
			if (header.trim().equals("Select")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("updateEndpointFirmwarePageColumnSelect"));
			}
			if (header.trim().equals("Endpoint ID")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("updateEndpointFirmwarePageColumnEndpointID"));
			}
			if (header.trim().equals("Tenant")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("updateEndpointFirmwarePageColumnTenant"));
			}
			if (header.trim().equals("Premises")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("updateEndpointFirmwarePageColumnPremises"));
			}
			if (header.trim().equals("Connection Status")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("updateEndpointFirmwarePageColumnConnectionStatus"));
			}
			if (header.trim().equals("Type")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("updateEndpointFirmwarePageColumnType"));
			}
			if (header.trim().equals("Serial")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("updateEndpointFirmwarePageColumnSerial"));
			}
			if (header.trim().equals("Live Firmware")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("updateEndpointFirmwarePageColumnLiveFirmware"));
			}
			if (header.trim().equals("Last Message Received (UTC)")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("updateEndpointFirmwarePageColumnLastMessageReceived"));
			}
			if (visible == false) {
				if (visible == false) {
					isVisible = false;
					Assert.assertTrue("Header [" + header + "] is not visible in Endpoint Firmware Grid", isVisible);
					break;
				}
			}
		}
	}

	@Then("^verify the following columns in endpoint firmware details grid$")
	public void verify_the_following_columns_in_endpoint_firmware_details_grid(DataTable args) throws Throwable {

		List<String> lstData = args.asList(String.class);
		args.asList(String.class);
		boolean isVisible = true;
		for (String header : lstData) {
			boolean visible = false;
			if (header.trim().equals("Name")) {
				visible = endpointFirmwarePage
						.verifyElementDisplayed(endpointFirmwarePageObjectRepo.get("endpointFirmwarePageNameHeader"));
			}
			if (header.trim().equals("Version")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("endpointFirmwarePageVersionHeader"));
			}
			if (header.trim().equals("Endpoint type")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("endpointFirmwarePageEndpointTypeHeader"));
			}
			if (header.trim().equals("Release date")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("endpointFirmwarePageReleaseDateHeader"));
			}
			if (header.trim().equals("Active")) {
				visible = endpointFirmwarePage
						.verifyElementDisplayed(endpointFirmwarePageObjectRepo.get("endpointFirmwarePageActiveHeader"));
			}

			if (visible == false) {
				if (visible == false) {
					isVisible = false;
					Assert.assertTrue("Header [" + header + "] is not visible in Endpoint Firmware Grid", isVisible);
					break;
				}
			}
		}
	}

	@Then("^verify the sorting functionality of columns in endpoint firmware audit grid$")
	public void verify_the_sorting_functionality_of_columns_in_endpoint_firmware_audit_grid(DataTable args)
			throws Throwable {
		List<String> lstData = args.asList(String.class);
		for (String header : lstData) {
			endpointFirmwarePage.verifySorting_EndpointFirmwarePage("ASCENDING", header,
					endpointFirmwarePageObjectRepo);
			endpointFirmwarePage.verifySorting_EndpointFirmwarePage("DESCENDING", header,
					endpointFirmwarePageObjectRepo);
		}
	}

	@Then("^verify sorting functionality of following columns on \"(.*?)\" sub tab$")
	public void verify_sorting_following_columns(String tabName, DataTable args) throws Throwable {
		List<String> lstData = args.asList(String.class);
		for (String header : lstData) {
			endpointFirmwarePage.verifySortingOnUpdateEndpointFirmwarePage("ASCENDING", header,
					endpointFirmwarePageObjectRepo);
			endpointFirmwarePage.verifySortingOnUpdateEndpointFirmwarePage("DESCENDING", header,
					endpointFirmwarePageObjectRepo);
		}
	}

	@Then("^verify the sorting functionality of columns in endpoint firmware tab$")
	public void verify_the_sorting_functionality_of_columns_in_endpoint_firmware_tab(DataTable args) throws Throwable {
		List<String> lstData = args.asList(String.class);
		for (String header : lstData) {
			endpointFirmwarePage.verifySorting_EndpointFirmwarePage("ASCENDING", header,
					endpointFirmwarePageObjectRepo);
			endpointFirmwarePage.verifySorting_EndpointFirmwarePage("DESCENDING", header,
					endpointFirmwarePageObjectRepo);
		}
	}

	@Then("^delete all the dependencies associated with \"(.*?)\" used as test data for endpoint firmware page$")
	public void delete_used_as_test_data_for_endpoint_firmware_page(String endpointFirmwareName) throws Throwable {
		DeleteTestData objDelTestdata = new DeleteTestData();
		objDelTestdata.deleteTestData("EndpointFirmware", endpointFirmwareName);
		checkExistingEndpointFirmware = objDelTestdata.deleteTestData("Endpoint_Firmware_Check", endpointFirmwareName);
	}

	@Then("^create test data for Endpoint firmware page$")
	public void create_test_data_on_Endpoint_firmware_page() throws Throwable {
		String tempString = endpointFirmwarePage
				.getElementText(endpointFirmwarePageObjectRepo.get("endpointFirmwarePaginationTotalPages"));
		String total = tempString.substring(tempString.indexOf('/') + 1).trim();
		int newTotal = Integer.parseInt(total);
		if (newTotal <= 1) {
			String tempTotalItem = endpointFirmwarePage
					.getElementText(endpointFirmwarePageObjectRepo.get("endpointFirmwareTotalItemsCount"));
			String totalItem = tempTotalItem.substring(tempTotalItem.indexOf('-') + 1, tempTotalItem.indexOf("of") - 1)
					.trim();
			int newTotalItem = Integer.parseInt(totalItem);
			int reqItem = 22 - newTotalItem;
			String firmwareFile = null, filePath = null, absolutePath = null;
			for (i = 1; i <= reqItem; i++) {
				endpointFirmwarePage.clickAndWait(endpointFirmwarePageObjectRepo.get("addEndpointFirmwareButton"));
				firmwareFile = "live_firmware.tar.gz";
				File f = new File(firmwareFile);
				absolutePath = f.getAbsolutePath();
				// TODO: updated by k as \\ replaced with / on 8 may 2018 9:38 AM
				filePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator)) + "/downloadDir/"
						+ firmwareFile;
				endpointFirmwarePage.uploadFile(endpointFirmwarePageObjectRepo.get("browseButton"), filePath);
				endpointFirmwarePage.explicitWait(2000);
				endpointFirmwarePage.enterText(endpointFirmwarePageObjectRepo.get("addEndpointFirmwareNameField"),
						"endpointFirmwareTestDataBot" + i);
				endpointFirmwarePage.enterText(endpointFirmwarePageObjectRepo.get("addEndpointFirmwareVersionField"),
						"-3." + i);
				endpointFirmwarePage.selectDropDownByValue(
						endpointFirmwarePageObjectRepo.get("addEndpointFirmwareEndpointTypeField"),
						endpointFirmwarePageObjectTestData.get("endpointFirmwareEndpointTypeTestData"));
				endpointFirmwarePage.enterText(
						endpointFirmwarePageObjectRepo.get("addEndpointFirmwareReleaseDateField"),
						endpointFirmwarePageObjectTestData.get("endpointFirmwareReleaseDateTestData"));
				endpointFirmwarePage.clickAndWait(endpointFirmwarePageObjectRepo.get("addEndpointFirmwareSaveButton"));
				endpointFirmwarePage.waitForSpinner();
			}
		}
	}

	@Then("^click on \"(.*?)\" button on Endpoint firmware page$")
	public void click_on_button_on_Endpoint_firmware_page(String endpointFirmwareButtonName) throws Throwable {
		String message = "Failed to click on button :" + endpointFirmwareButtonName + " "
				+ " on endpoint firmware detail grid.";
		Assert.assertTrue(message, endpointFirmwarePage.clickOperationOnEndpointFirmwarePage(endpointFirmwareButtonName,
				endpointFirmwarePageObjectRepo));
	}

	@Then("^click on \"(.*?)\" sub tab$")
	public void click_on_sub_tab_of_Endpoint_firmware_page(String firmwaresubtabname) throws Throwable {
		String message = "Failed to click on sub tab :" + firmwaresubtabname + " " + " on firmware tab.";
		Assert.assertTrue(message, endpointFirmwarePage.clickOperationOnUpdateEndpointFirmwareSubTab(firmwaresubtabname,
				endpointFirmwarePageObjectRepo));
	}

	@Then("^add endpoint firmware popup should be displayed with following fields:$")
	public void add_endpoint_firmware_popup_should_be_displayed_with_following_fields(DataTable arg1) throws Throwable {
		List<String> lstData = arg1.asList(String.class);
		arg1.asList(String.class);
		boolean isVisible = true;
		for (String header : lstData) {
			boolean visible = false;
			if (header.trim().equals("File")) {
				visible = endpointFirmwarePage
						.verifyElementDisplayed(endpointFirmwarePageObjectRepo.get("EndpointFirmwareFileField"));
			}
			if (header.trim().equals("Name")) {
				visible = endpointFirmwarePage
						.verifyElementDisplayed(endpointFirmwarePageObjectRepo.get("EndpointFirmwareNameField"));
			}
			if (header.trim().equals("Version")) {
				visible = endpointFirmwarePage
						.verifyElementDisplayed(endpointFirmwarePageObjectRepo.get("EndpointFirmwareVersionField"));
			}
			if (header.trim().equals("Endpoint Type")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("EndpointFirmwareEndpointTypeField"));
			}
			if (header.trim().equals("Release Date")) {
				visible = endpointFirmwarePage
						.verifyElementDisplayed(endpointFirmwarePageObjectRepo.get("EndpointFirmwareReleaseDateField"));
			}
			// break if any column is not visible
			if (visible == false) {
				isVisible = false;
				Assert.assertTrue("Header [" + header + "] is not visible in Endpoint Firmware Grid", isVisible);
				break;
			}
			endpointFirmwarePage.explicitWait(2000);
		}
	}

	@Then("^verify following button are enabled on add endpoint firmware popup$")
	public void verify_following_button_are_enabled_on_add_endpoint_firmware_popup(DataTable table) throws Throwable {
		List<String> lstData = table.asList(String.class);
		table.asList(String.class);
		for (String button : lstData) {
			String message = button + "should  be enabled on endpoint firmware page ";
			Assert.assertTrue(message, endpointFirmwarePage.isElementEnabledOnEndpointFirmwarePage(button,
					endpointFirmwarePageObjectRepo));
		}
	}

	@Then("^click on the \"(.*?)\" button on add endpoint firmware popup$")
	public void click_on_the_button_on_add_endpoint_firmware_popup(String endpointFirmwareButtonName) throws Throwable {
		String message = "Failed to click on button :" + endpointFirmwareButtonName + " "
				+ " on add endpoint firmware popup .";
		Assert.assertTrue(message, endpointFirmwarePage.clickOperationOnEndpointFirmwarePage(endpointFirmwareButtonName,
				endpointFirmwarePageObjectRepo));
	}

	@And("^verify the validation messages \"(.*?)\" for \"(.*?)\" field on add Endpoint Firmware popup$")
	public void verify_the_validation_messages_for_following_fields(String errorMessage, String fieldName)
			throws Throwable {
		String message = "Failed to verify error message[" + errorMessage + "] for field [" + fieldName
				+ "] on add Endpoint Firmware popup.";
		Assert.assertTrue(message, endpointFirmwarePage.verifyErrorMessageOnAddEndpointFirmwarePopup(errorMessage,
				fieldName, endpointFirmwarePageObjectRepo));
	}

	@Then("^click on the \"(.*?)\" button on add endpoint firmware popup and select \"(.*?)\" file$")
	public void click_on_the_button_on_add_endpoint_firmware_popup_and_select_an_invalid_file(String buttonName,
			String fileType) throws Throwable {
		String firmwareFile = null, filePath = null, absolutePath = null;
		File f = null;
		switch (fileType.toUpperCase()) {
		case "VALID":
			firmwareFile = "live_firmware.tar.gz";
			f = new File(firmwareFile);
			absolutePath = f.getAbsolutePath();
			// TODO: updated by k as \\ replaced with / on 8 may 2018 9:38 AM
			filePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator)) + "/downloadDir/"
					+ firmwareFile;
			break;
		case "INVALID":
			firmwareFile = "InvalidEndpointFirmwareFile.xlsx";
			f = new File(firmwareFile);
			absolutePath = f.getAbsolutePath();
			// TODO: updated by k as \\ replaced with / on 8 may 2018 9:38 AM
			filePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator)) + "/downloadDir/"
					+ firmwareFile;
			break;
		}
		String message = "Failed to click on button :" + buttonName + " " + " on add endpoint firmware popup.";
		Assert.assertTrue(message, endpointFirmwarePage.clickChooseButtonAndSelectInvalidFile(buttonName,
				endpointFirmwarePageObjectRepo, fileType, filePath));
	}
	
	// TODO : Above method is updatedby k on 8 May 2018. Verify ...
//	  @Then("^click on the \"(.*?)\" button on add endpoint firmware popup and select \"(.*?)\" file for auditing$")
//	public void click_on_the_button_on_add_endpoint_firmware_popup_and_select_an_invalid_file(String buttonName,
//			String fileType) throws Throwable {
//		String firmwareFile = null, filePath = null, absolutePath = null;
//		File f = null;
//		switch (fileType.toUpperCase()) {
//		case "VALID":
//			firmwareFile = "live_firmware.tar.gz";
//			f = new File(firmwareFile);
//			absolutePath = f.getAbsolutePath();
//			if (System.getProperty("browser") == "chrome_headless")
//			{	
//				
//			filePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator)) + "/downloadDir/"
//					+ firmwareFile;
//			}
//			else
//			{// TODO: updated by k as \\ replaced with / on 8 may 2018 9:38 AM
//				filePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator)) + "/downloadDir/"
//						+ firmwareFile;
//			}
//			break;
//		case "INVALID":
//			firmwareFile = "InvalidEndpointFirmwareFile.xlsx";
//			f = new File(firmwareFile);
//			absolutePath = f.getAbsolutePath();
//			if (System.getProperty("browser") == "chrome_headless")
//			{	
//				
//			filePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator)) + "/downloadDir/"
//					+ firmwareFile;
//			}
//			else
//			{// TODO: updated by k as \\ replaced with / on 8 may 2018 9:38 AM
//				filePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator)) + "/downloadDir/"
//						+ firmwareFile;
//			}
//			break;
//		}
//		String message = "Failed to click on button :" + buttonName + " " + " on add endpoint firmware popup.";
//		Assert.assertTrue(message,
//				auditPage.clickChooseButtonAndSelectInvalidFile(buttonName, auditPageObjectRepo, fileType, filePath));
//		auditPage.explicitWait(20000);
//	}

	@Then("^verify the message \"(.*?)\" will display when endpoint firmware file with wrong name is uploaded$")
	public void verify_the_message_will_display_when_endpoint_firmware_file_with_wrong_name_is_uploaded(
			String errorMessage) throws Throwable {
		String message = "Failed to display error message";
		Assert.assertTrue(message, endpointFirmwarePage.verifyErrorMessage_InvalidFileChoosen(errorMessage,
				endpointFirmwarePageObjectRepo));
	}

	@When("^click on \"(.*?)\" button on add endpoint firmware popup$")
	public void click_on_button_on_add_endpoint_firmware_popup(String endpointFirmwareButtonName) throws Throwable {
		String message = "Failed to click on button :" + endpointFirmwareButtonName + " "
				+ " on add endpoint firmware popup .";
		Assert.assertTrue(message, endpointFirmwarePage.clickOperationOnEndpointFirmwarePage(endpointFirmwareButtonName,
				endpointFirmwarePageObjectRepo));
	}

	@Then("^confirmation box on endpoint firmware popup should displayed with message \"(.*?)\"$")
	public void confirmation_box_on_endpoint_firmware_popup_should_displayed_with_message(
			String endpointFirmwareMessage) throws Throwable {
		String message = "Failed to display the confirmation dialog with message: " + endpointFirmwareMessage;
		Assert.assertTrue(message, endpointFirmwarePage.checkPopupExistence("ADDENDPOINTFIRMWARE_CONFIRMATION",
				endpointFirmwareMessage, endpointFirmwarePageObjectRepo));
	}

	@When("^click on \"(.*?)\" button on confirmation box on endpoint firmware$")
	public void click_on_button_on_confirmation_box_on_endpoint_firmware(String endpointFirmwareButtonName)
			throws Throwable {
		String message = "Failed to click on button :" + endpointFirmwareButtonName + " " + " on confirmation box.";
		Assert.assertTrue(message, endpointFirmwarePage.clickOperationOnConfirmationBox(endpointFirmwareButtonName,
				endpointFirmwarePageObjectRepo));
	}

	@Then("^user should remain on add endpoint firmware popup$")
	public void user_should_remain_on_add_endpoint_firmware_popup() throws Throwable {
		String message = "Failed to check existence of  add endpoint firmware popup.";
		Assert.assertTrue(message,
				endpointFirmwarePage.checkPopupExistence("ADDENDPOINTFIRMWARE", "", endpointFirmwarePageObjectRepo));
	}

	@Then("^confirmation box on endpoint firmware should displayed with message \"(.*?)\"$")
	public void confirmation_box_on_endpoint_firmware_should_displayed_with_message(String eFMessage) throws Throwable {
		String message = "Failed to display the confirmation dialog with message: " + eFMessage;
		Assert.assertTrue(message, endpointFirmwarePage.checkPopupExistence("ADDENDPOINTFIRMWARE_CONFIRMATION",
				eFMessage, endpointFirmwarePageObjectRepo));
	}

	@Then("^user should be navigated on endpoint firmware detail grid$")
	public void user_should_be_navigated_on_endpoint_firmware_detail_grid() throws Throwable {
		String message = "Failed to verify endpoint firmware grid availablity.  ";
		endpointFirmwarePage.assertElementDisplayed(endpointFirmwarePageObjectRepo.get("endpointFirmwareDetailGrid"),
				message);
	}

	@When("^click on \"(.*?)\" button  on endpoint firmware page$")
	public void click_on_button_on_endpoint_firmware_page(String endpointFirmwareButtonName) throws Throwable {
		String message = "Failed to click on button :" + endpointFirmwareButtonName + " "
				+ " on endpoint firmware detail grid.";
		Assert.assertTrue(message, endpointFirmwarePage.clickOperationOnEndpointFirmwarePage(endpointFirmwareButtonName,
				endpointFirmwarePageObjectRepo));
	}

	@Then("^enter the test data for the following fields to create new endpoint firware$")
	public void enter_the_test_data_for_the_following_fields_to_create_new_endpoint_firware(DataTable arg1)
			throws Throwable {
		List<String> fieldList = arg1.asList(String.class);
		for (String list : fieldList) {
			endpointFirmwarePage.enterTextEndpointFirmware(list, endpointFirmwarePageObjectRepo,
					endpointFirmwarePageObjectTestData);

		}
	}

	@When("^click on \"(.*?)\" button on add endpoint firware popup$")
	public void click_on_button_on_add_endpoint_firware_popup(String endpointFirmwareButtonName) throws Throwable {
		String message = "Failed to click on button :" + endpointFirmwareButtonName + " "
				+ " on add endpoint firmware popup .";
		Assert.assertTrue(message, endpointFirmwarePage.clickOperationOnEndpointFirmwarePage(endpointFirmwareButtonName,
				endpointFirmwarePageObjectRepo));
	}

	@Then("^user should be navigated to endpoint firware page with a label saying \"(.*?)\" for \"(.*?)\"$")
	public void user_should_be_navigated_to_endpoint_firware_page_with_a_label_saying_for(String endpointFirmwareMsg,
			String type) throws Throwable {
		if (!checkExistingEndpointFirmware) {
			return;
		}
		String message = "Expected message not matched with actual message.";
		switch (type.toUpperCase()) {
		case "SAVING":
			endpointFirmwarePage.waitForSpinner();
			endpointFirmwarePage.assertElementDisplayed(
					endpointFirmwarePageObjectRepo.get("addEndpointFirmwareSaveMsg"),
					"Message is not visible on the page");
			Assert.assertTrue(message, endpointFirmwareMsg.equals(endpointFirmwarePage
					.getElementText(endpointFirmwarePageObjectRepo.get("addEndpointFirmwareSaveMsg"))));
			endpointFirmwarePage.waitForSpinner();
			endpointFirmwarePage.explicitWait(5000);
			break;
		case "DELETION":
			endpointFirmwarePage.waitForSpinner();
			endpointFirmwarePage.assertElementDisplayed(
					endpointFirmwarePageObjectRepo.get("addEndpointFirmwareSaveMsg"),
					"Message is not visible on the page");
			Assert.assertTrue(message, endpointFirmwareMsg.equals(endpointFirmwarePage
					.getElementText(endpointFirmwarePageObjectRepo.get("addEndpointFirmwareSaveMsg"))));
			endpointFirmwarePage.waitForSpinner();
			endpointFirmwarePage.explicitWait(5000);
			break;
		}
	}

	@Then("^verify that error message will be displayed \"(.*?)\"$")
	public void verify_that_error_message_will_be_displayed(String errorMessage) throws Throwable {
		String message = "Failed to display error message";
		Assert.assertTrue(message, endpointFirmwarePage.verifyErrorMessage_DuplicateFirmware(errorMessage,
				endpointFirmwarePageObjectRepo));
	}

	@Then("^verify the search output by entering \"(.*?)\" in endpoint firmware search field$")
	public void verify_the_search_output_by_entering_in_endpoint_firmware_search_field(String args) throws Throwable {
		endpointFirmwarePage.verifySearchFunctionality(args, endpointFirmwarePageObjectRepo,
				endpointFirmwarePageObjectTestData);
	}

	@Then("^verify the search output by entering \"(.*?)\" in update endpoint firmware search field$")
	public void verify_the_search_output_by_entering_in_uendpoint_firmware_search_field(String args) throws Throwable {
		endpointFirmwarePage.verifyUpdateEndpointFirmwareSearchFunctionality(args, endpointFirmwarePageObjectRepo,
				endpointFirmwarePageObjectTestData);
	}

	@Then("^verify the search output by entering \"(.*?)\" which is a non existing endpoint firmware in endpoint firmware search field$")
	public void verify_the_search_output_by_entering_which_is_a_non_existing_endpoint_firmware_in_endpoint_firmware_search_field(
			String args) throws Throwable {
		endpointFirmwarePage.verifySearchFunctionality(args, endpointFirmwarePageObjectRepo,
				endpointFirmwarePageObjectTestData);
	}

	@Then("^verify the search output by entering \"(.*?)\" which is a non existing update endpoint firmware in endpoint firmware search field$")
	public void verify_the_search_output_by_entering_which_is_a_non_existing_uendpoint_firmware_in_endpoint_firmware_search_field(
			String args) throws Throwable {
		endpointFirmwarePage.verifyUpdateEndpointFirmwareSearchFunctionality(args, endpointFirmwarePageObjectRepo,
				endpointFirmwarePageObjectTestData);
	}

	@Then("^verify the search output by entering \"(.*?)\" more than three character and click on backspace button in endpoint firmware search field$")
	public void verify_the_search_output_by_entering_more_than_three_character_and_click_on_backspace_button_in_endpoint_firmware_search_field(
			String args) throws Throwable {
		endpointFirmwarePage.verifySearchFunctionality(args, endpointFirmwarePageObjectRepo,
				endpointFirmwarePageObjectTestData);
	}

	@Then("^verify the search output by entering \"(.*?)\" more than three character and click on backspace button in update endpoint firmware search field$")
	public void verify_the_search_output_by_entering_more_than_three_character_and_click_on_backspace_button_in_uendpoint_firmware_search_field(
			String args) throws Throwable {
		endpointFirmwarePage.verifyUpdateEndpointFirmwareSearchFunctionality(args, endpointFirmwarePageObjectRepo,
				endpointFirmwarePageObjectTestData);
	}

	@Then("^verify the search output by entering string \"(.*?)\" and enter delete button in endpoint firmware search field$")
	public void verify_the_search_output_by_entering_string_and_enter_delete_button_in_endpoint_firmware_search_field(
			String args) throws Throwable {
		endpointFirmwarePage.verifySearchFunctionality(args, endpointFirmwarePageObjectRepo,
				endpointFirmwarePageObjectTestData);
	}

	@Then("^verify the search output by entering string \"(.*?)\" and enter delete button in update endpoint firmware search field$")
	public void verify_the_search_output_by_entering_string_and_enter_delete_button_in_uendpoint_firmware_search_field(
			String args) throws Throwable {
		endpointFirmwarePage.verifyUpdateEndpointFirmwareSearchFunctionality(args, endpointFirmwarePageObjectRepo,
				endpointFirmwarePageObjectTestData);
	}

	@Then("^verify the pagination by entering value \"(.*?)\" in endpoint firmware search field$")
	public void verify_the_pagination_by_entering_value_in_endpoint_firmware_search_field(String args)
			throws Throwable {
		endpointFirmwarePage.verifySearchFunctionality(args, endpointFirmwarePageObjectRepo,
				endpointFirmwarePageObjectTestData);
	}

	@Then("^verify the pagination by entering value \"(.*?)\" in update endpoint firmware search field$")
	public void verify_the_pagination_by_entering_value_in_udendpoint_firmware_search_field(String args)
			throws Throwable {
		endpointFirmwarePage.verifyUpdateEndpointFirmwareSearchFunctionality(args, endpointFirmwarePageObjectRepo,
				endpointFirmwarePageObjectTestData);
	}

	@Then("^verify the view pagination button by entering value \"(.*?)\" in the update endpoint firmware search field$")
	public void verify_the_pagination_by_entering_value_in_uendpoint_firmware_search_field(String args)
			throws Throwable {
		endpointFirmwarePage.verifyUpdateEndpointFirmwareSearchFunctionality(args, endpointFirmwarePageObjectRepo,
				endpointFirmwarePageObjectTestData);

	}

	@When("^click on \"(.*?)\" link in endpoint firmware grid$")
	public void click_on_link_in_endpoint_firmware_grid(String endpointFirmwareLinkName) throws Throwable {
		endpointFirmwarePage.explicitWait(2000);
		endpointFirmwarePage.getElementAfterLoaded(endpointFirmwarePageObjectRepo.get("endpointFirmwareTab"))
				.sendKeys(Keys.PAGE_DOWN);
		endpointFirmwarePage.clickPaginationEndpointFirmwarePage(endpointFirmwareLinkName,
				endpointFirmwarePageObjectRepo);
	}

	@When("^click on \"(.*?)\" link in update endpoint firmware grid$")
	public void click_on_link_in_uendpoint_firmware_grid(String endpointFirmwareLinkName) throws Throwable {
		endpointFirmwarePage.explicitWait(20000);
		endpointFirmwarePage.getElementAfterLoaded(endpointFirmwarePageObjectRepo.get("endpointFirmwareTab"))
				.sendKeys(Keys.PAGE_DOWN);
		endpointFirmwarePage.clickPaginationEndpointFirmwarePage(endpointFirmwareLinkName,
				endpointFirmwarePageObjectRepo);
	}

	@Then("^\"(.*?)\" page should displayed in endpoint firmware grid$")
	public void page_should_displayed_in_endpoint_firmware_grid(String task) throws Throwable {
		endpointFirmwarePage.verifyEndpointFirmwareDetailGridPagination(task, endpointFirmwarePageObjectRepo);
	}

	@Then("^\"(.*?)\" page should displayed in update endpoint firmware grid$")
	public void page_should_displayed_in_uendpoint_firmware_grid(String task) throws Throwable {
		endpointFirmwarePage.verifyEndpointFirmwareDetailGridPagination(task, endpointFirmwarePageObjectRepo);
	}

	@When("^enter random page number in page link text in endpoint firmware grid$")
	public void enter_random_page_number_in_page_link_text_in_endpoint_firmware_grid() throws Throwable {
		String totalPages = endpointFirmwarePage
				.getElementText(endpointFirmwarePageObjectRepo.get("endpointFirmwarePaginationTotalPages"));
		totalPages = totalPages.substring(totalPages.indexOf("/") + 1).trim();
		int pageCount = Integer.parseInt(totalPages);
		if (pageCount <= 1) {
			Assert.fail(
					"Indufficient data for this test step. All the available Endpoint Firwmare are on the first page only.");
		}
		Random random = new Random();
		int randomPage = random.nextInt(pageCount + 1);
		if (randomPage == 0 || randomPage == 1)
			randomPage = 2;
		boolean bSet = endpointFirmwarePage.setRandomPage(Integer.parseInt(totalPages), randomPage,
				endpointFirmwarePageObjectRepo);
		endpointFirmwarePage.waitForSpinner();
		Assert.assertTrue("Page" + randomPage + " is not selected.", bSet);
	}

	@When("^enter random page number in page link text in update endpoint firmware grid$")
	public void enter_random_page_number_in_page_link_text_in_uendpoint_firmware_grid() throws Throwable {
		String totalPages = endpointFirmwarePage
				.getElementText(endpointFirmwarePageObjectRepo.get("endpointFirmwarePaginationTotalPages"));
		totalPages = totalPages.substring(totalPages.indexOf("/") + 1).trim();
		int pageCount = Integer.parseInt(totalPages);
		if (pageCount <= 1) {
			Assert.fail(
					"Indufficient data for this test step. All the available Endpoint Firwmare are on the first page only.");
		}
		Random random = new Random();
		int randomPage = random.nextInt(pageCount + 1);
		if (randomPage == 0 || randomPage == 1)
			randomPage = 2;
		boolean bSet = endpointFirmwarePage.setRandomPage(Integer.parseInt(totalPages), randomPage,
				endpointFirmwarePageObjectRepo);
		endpointFirmwarePage.waitForSpinner();
		Assert.assertTrue("Page" + randomPage + " is not selected.", bSet);
	}

	@Then("^user should be navigated on the given random page in endpoint firmware grid$")
	public void user_should_be_navigated_on_the_given_random_page_in_endpoint_firmware_grid() throws Throwable {
		endpointFirmwarePage.waitForSpinner();
		List<String> lstSelectedPageEndpointFirmwareNames = endpointFirmwarePage.getMultipleElementTextAfterLoaded(
				endpointFirmwarePageObjectRepo.get("endpointFirmwarePageNameColumn"));
		endpointFirmwarePage
				.setElementAttribute(endpointFirmwarePageObjectRepo.get("endpointFirmwarePaginationCurrentPage"), "1");
		endpointFirmwarePage.waitForSpinner();
		List<String> lstFirstPageEndpointFirmwareNames = endpointFirmwarePage.getMultipleElementTextAfterLoaded(
				endpointFirmwarePageObjectRepo.get("endpointFirmwarePageNameColumn"));
		if (lstFirstPageEndpointFirmwareNames.equals(lstSelectedPageEndpointFirmwareNames)) {
			Assert.fail("Error while navigating to given random page");
		}
	}

	@Then("^user should be navigated on the given random page in update endpoint firmware grid$")
	public void user_should_be_navigated_on_the_given_random_page_in_uendpoint_firmware_grid() throws Throwable {
		endpointFirmwarePage.waitForSpinner();
		List<String> lstSelectedPageEndpointFirmwareNames = endpointFirmwarePage.getMultipleElementTextAfterLoaded(
				endpointFirmwarePageObjectRepo.get("endpointFirmwarePageNameColumn"));
		endpointFirmwarePage
				.setElementAttribute(endpointFirmwarePageObjectRepo.get("endpointFirmwarePaginationCurrentPage"), "1");
		endpointFirmwarePage.waitForSpinner();
		List<String> lstFirstPageEndpointFirmwareNames = endpointFirmwarePage.getMultipleElementTextAfterLoaded(
				endpointFirmwarePageObjectRepo.get("endpointFirmwarePageNameColumn"));
		if (lstFirstPageEndpointFirmwareNames.equals(lstSelectedPageEndpointFirmwareNames)) {
			Assert.fail("Error while navigating to given random page");
		}
	}

	@When("^enter friamware name \"(.*?)\" in endpoint firmware search field$")
	public void enter_site_name_in_endpoint_firmware_search_field(String endpointFirmwareName) throws Throwable {
		if (!checkExistingEndpointFirmware) {
			return;
		}
		endpointFirmwarePage.enterText_endpointFirmwarePage("ENDPOINTFIRMWARE_SEARCH", endpointFirmwareName,
				endpointFirmwarePageObjectRepo);
	}

	@When("^delete endpoint friamware \"(.*?)\" from friamware tab$")
	public void delete_endpoint_firmware_search_field(String endpointFirmwareName) throws Throwable {
		if (!checkExistingEndpointFirmware) {
			return;
		}
		String temp = "endpointFirmwareTestDataBot";
		int i=5;
		do {
			endpointFirmwarePage.enterText_endpointFirmwarePage("ENDPOINTFIRMWARE_SEARCH", endpointFirmwareName,
					endpointFirmwarePageObjectRepo);
			try {
				endpointFirmwarePage.selectEndpointFirmwareFromGrid_EndpointFirmwarePageNew(endpointFirmwareName,
						endpointFirmwarePageObjectRepo);
			} catch (Exception e) {
				 endpointFirmwareName = temp + (i - 1);
				continue;
			}
			endpointFirmwarePage.clickAndWait(endpointFirmwarePageObjectRepo.get("editEndpointFirmwareDeleteButton"));
			endpointFirmwarePage
					.clickAndWait(endpointFirmwarePageObjectRepo.get("DeleteEndpointFirmwareOkConfirmationButton"));
			endpointFirmwarePage.explicitWait(2000);
			 endpointFirmwareName = temp + (i - 1);
		} while (endpointFirmwareName .equalsIgnoreCase(temp+"0"));
		

	}

	@When("^delete test endpoint firmware like \"(.*?)\" from endpoint firmware page$")
	public void enter_EF_name_in_endpoint_firmware_search_field(String endpointFirmwareName) throws Throwable {
		if (!checkExistingEndpointFirmware) {
			return;
		}
		endpointFirmwarePage.selectEndpointFirmwareFromGrid_EndpointFirmwarePage(endpointFirmwareName,
				endpointFirmwarePageObjectRepo);
	}

	@Then("^select the endpoint firmware \"(.*?)\" from the endpoint firmware details grid$")
	public void select_the_endpoint_firmware_from_the_endpoint_firmware_details_grid(String endpointFirmwareName)
			throws Throwable {
		if (!checkExistingEndpointFirmware) {
			return;
		}
		endpointFirmwarePage.selectEndpointFirmwareFromGrid_EndpointFirmwarePage(endpointFirmwareName,
				endpointFirmwarePageObjectRepo);

	}

	@Then("^verify the presence of following elements in details section on edit endpoint firmware popup$")
	public void verify_the_presence_of_following_elements_in_details_section_on_edit_endpoint_firmware_popup(
			DataTable args) throws Throwable {
		List<String> lstData = args.asList(String.class);
		args.asList(String.class);
		boolean isVisible = true;
		for (String header : lstData) {
			boolean visible = false;
			if (header.trim().equals("Name")) {
				visible = endpointFirmwarePage
						.verifyElementDisplayed(endpointFirmwarePageObjectRepo.get("endpointFirmwarePageNameHeader"));
			}
			if (header.trim().equals("Version")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("endpointFirmwarePageVersionHeader"));
			}
			if (header.trim().equals("Endpoint Type")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("endpointFirmwarePageEndpointTypeHeader"));
			}
			if (header.trim().equals("Release Date")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("endpointFirmwarePageReleaseDateHeader"));
			}
			if (header.trim().equals("Active")) {
				visible = endpointFirmwarePage
						.verifyElementDisplayed(endpointFirmwarePageObjectRepo.get("endpointFirmwarePageActiveHeader"));
			}
			if (visible == false) {
				if (visible == false) {
					isVisible = false;
					Assert.assertTrue("Header [" + header + "] is not visible in endpointFirmare Grid", isVisible);
					break;
				}
			}
		}
	}

	@Then("^click on Edit Endpoint Save Button$")
	public void click_on_Edit_Endpoint_Save_Button() throws Throwable {
		endpointFirmwarePage.click(endpointFirmwarePageObjectRepo.get("saveEndpointFirmwareButton"));
	}

	@Then("^verify following button are enabled on edit endpoint firmware popup$")
	public void verify_following_button_are_enabled_on_edit_endpoint_firmware_popup(DataTable arg1) throws Throwable {
		List<String> list = arg1.asList(String.class);
		for (String buttonList : list) {
			endpointFirmwarePage.verifyButtonEnabledOnEditEndpointFirmwarePopup(buttonList,
					endpointFirmwarePageObjectRepo);
		}
	}

	@Then("^enter invalid value in \"(.*?)\" field on endpoint firmware edit popup$")
	public void enter_invalid_value_in_field_on_endpoint_firmware_edit_popup(String invalidName) throws Throwable {
		String message = "Failed to enter invalid value in [" + invalidName
				+ "] field on endpoint firmware edit popup ";
		Assert.assertTrue(message, endpointFirmwarePage.enterInvalidValueInNameField(invalidName,
				endpointFirmwarePageObjectRepo, endpointFirmwarePageObjectTestData));
	}

	@Then("^verify that \"(.*?)\" message is displayed for \"(.*?)\" field on edit endpoint firmware popup$")
	public void verify_that_message_is_displayed_on_edit_endpoint_firmware_popup(String errorMessage, String fieldName)
			throws Throwable {
		String message = "Failed to verify error message[" + errorMessage + "] for field [" + fieldName
				+ "] on add Endpoint Firmware popup.";
		Assert.assertTrue(message, endpointFirmwarePage.verifyErrorMessageOnEditEndpointFirmwarePopup(errorMessage,
				fieldName, endpointFirmwarePageObjectRepo));
	}

	@When("^click on \"(.*?)\" button on edit endpoint firmware popup$")
	public void click_on_button_on_edit_endpoint_firmware_popup(String endpointFirmwareButton) throws Throwable {
		if (!checkExistingEndpointFirmware) {
			return;
		}
		String message = "Failed to click on button :" + endpointFirmwareButton + " "
				+ " on endpoint Firmware detail grid.";
		Assert.assertTrue(message, endpointFirmwarePage.clickOperationOnEditEndpointFirmwarePage(endpointFirmwareButton,
				endpointFirmwarePageObjectRepo));
	}

	@Then("^confirmation box on edit endpoint firmware popup should displayed with message \"(.*?)\"$")
	public void confirmation_box_on_edit_endpoint_firmware_popup_should_displayed_with_message(
			String confirmationboxMessage) throws Throwable {
		if (!checkExistingEndpointFirmware) {
			return;
		}
		if (confirmationboxMessage.toLowerCase().startsWith("are you sure")) {
			boolean okButton = endpointFirmwarePage.verifyElementDisplayed(
					endpointFirmwarePageObjectRepo.get("DeleteEndpointFirmwareOkConfirmationButton"));
			boolean cancelButton = endpointFirmwarePage.verifyElementDisplayed(
					endpointFirmwarePageObjectRepo.get("DeleteEndpointFirmwareCancelConfirmationButton"));
			Assert.assertTrue(
					"Behavior confirmation popup did not appear when clicked on delete button of edit Endpoint Firmware",
					(okButton && cancelButton));
		} else if (confirmationboxMessage.toLowerCase().endsWith("are you sure?")) {
			boolean okButton = endpointFirmwarePage
					.verifyElementDisplayed(endpointFirmwarePageObjectRepo.get("saveEndpointFirmwareButton"));
			boolean cancelButton = endpointFirmwarePage
					.verifyElementDisplayed(endpointFirmwarePageObjectRepo.get("cancelEndpointFirmwareButton"));
			Assert.assertTrue("Confirmation popup did not appear when clicked on cancel button",
					(okButton && cancelButton));
		}
	}

	@When("^click on \"(.*?)\" button on confirmation box on edit endpoint firmware popup$")
	public void click_on_button_on_confirmation_box_on_edit_endpoint_firmware_popup(String buttonName)
			throws Throwable {
		String message = "Failed to click on button :" + buttonName + " " + " on confirmation box.";
		Assert.assertTrue(message,
				endpointFirmwarePage.clickOperationOnConfirmationBox(buttonName, endpointFirmwarePageObjectRepo));
	}

	@Then("^user should remain on edit endpoint firmware popup$")
	public void user_should_remain_on_edit_endpoint_firmware_popup() throws Throwable {
		if (!checkExistingEndpointFirmware) {
			return;
		}
		String message = "Failed to check existence of edit endpoint Firmware popup.";
		Assert.assertTrue(message,
				endpointFirmwarePage.checkPopupExistence("EDIT_ENDPOINTFIRMWARE", "", endpointFirmwarePageObjectRepo));
	}

	@Then("^verify the expansion and shrinkage while click on \"(.*?)\" accordion on edit endpoint firmware popup$")
	public void verify_the_expansion_and_shrinkage_while_click_on_accordion_on_edit_endpoint_firmware_popup(
			String endpointFirmwareAccordionName) throws Throwable {
		endpointFirmwarePage.verifyAccordionToggle_EndpointFirmwarePage(endpointFirmwareAccordionName,
				endpointFirmwarePageObjectRepo);
	}

	@Then("^verify the audit details grid is available inside \"(.*?)\" accordion in edit endpoint firmware popup$")
	public void verify_the_details_grid_is_available_inside_accordion_in_edit_endpoint_firmware_popup(String accordion)
			throws Throwable {
		String message = "Failed to verify endpoint firmware grid availablity.  ";
		endpointFirmwarePage.click(endpointFirmwarePageObjectRepo.get("editEndpointFirmwareAuditAccordion"));
		endpointFirmwarePage.assertElementDisplayed(
				endpointFirmwarePageObjectRepo.get("editEndpointFirmwareAuditAccordionDetailGrid"), message);
	}

	@Then("^verify the following columns in \"(.*?)\" details grid in \"(.*?)\" accordion$")
	public void verify_the_following_columns_in_details_grid_in_accordion(String arg1, String arg2, DataTable table)
			throws Throwable {
		List<String> lstData = table.asList(String.class);
		table.asList(String.class);
		boolean isVisible = true;
		endpointFirmwarePage.click(endpointFirmwarePageObjectRepo.get("editEndpointFirmwareAuditAccordion"));
		for (String header : lstData) {
			boolean visible = false;
			if (header.trim().equals("Property")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("endpointFirmwareAuditAccordionPropertiyField"));
			}
			if (header.trim().equals("Action")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("endpointFirmwareAuditAccordionActionField"));
			}
			if (header.trim().equals("Original Value")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("endpointFirmwareAuditAccordionOriginalValueField"));
			}
			if (header.trim().equals("Updated value")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("endpointFirmwareAuditAccordionUpdatedValueField"));
			}
			if (header.trim().equals("User")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("endpointFirmwareAuditAccordionUserField"));
			}
			if (header.trim().equals("Date Stamp")) {
				visible = endpointFirmwarePage.verifyElementDisplayed(
						endpointFirmwarePageObjectRepo.get("EndpointFirmwareAuditAccordionDateStampField"));
			}
			// break if any column is not visible
			if (visible == false) {
				isVisible = false;
				Assert.assertTrue("Header [" + header + "] is not visible in Endpoint Firmware audit accordion Grid",
						isVisible);
				break;
			}
			endpointFirmwarePage.explicitWait(2000);
		}
	}

	@Then("^verify that newly created \"(.*?)\" logs are present in \"(.*?)\" details grid$")
	public void verify_that_newly_created_logs_are_present_in_details_grid(String endpointFirmware, String accordion)
			throws Throwable {
		String message = "Failed to verify logs of  newly created Endpoint Firmware [" + endpointFirmware + "]";
		Assert.assertTrue(message, endpointFirmwarePage.verifyLogsOfNewlyCreatedFirmware(endpointFirmware, accordion,
				endpointFirmwarePageObjectRepo));
	}

	@When("^Verify \"(.*?)\" page should be displayed for endpoint firmware$")
	public void Verify_page_should_be_displayed_for_endpoint_firmware(String sPopupName) throws Throwable {
		String message = "Failed to check existence of edit endpoint Firmware popup.";
		Assert.assertTrue(message,
				endpointFirmwarePage.checkPopupExistence("EDIT_ENDPOINTFIRMWARE", "", endpointFirmwarePageObjectRepo));
	}

	@When("^modify release date field and version field for the selected endpoint firmware$")
	public void modify_relase_date_field_and_version_field_for_the_selected_endpoint_firmware(DataTable arg1)
			throws Throwable {
		List<String> fieldList = arg1.asList(String.class);
		for (String list : fieldList) {
			endpointFirmwarePage.modifyTextEndpointFirmware(list, endpointFirmwarePageObjectRepo,
					endpointFirmwarePageObjectTestData);
		}
	}

	@When("^modify \"(.*?)\" release date to \"(.*?)\"$")
	public void modify_release_date(String attribut1, String attribut2) throws Throwable {
		endpointFirmwarePage.setElementAttribute(
				endpointFirmwarePageObjectRepo.get("endpointFirmwareReleaseDateTestData"), attribut2);
	}

	@When("^modify \"(.*?)\" version to \"(.*?)\"$")
	public void modify_version_to(String attribut1, String attribut2) throws Throwable {
		endpointFirmwarePage.setElementAttribute(endpointFirmwarePageObjectRepo.get("endpointFirmwareVersionTestData"),
				attribut2);
	}

	@When("^verify Active field is read only and it shows Yes for newly created firmware$")
	public void verify_Active_fields_is_read_only_and_it_shows_yes_for_newly_created_firmware() throws Throwable {

	}

	@Then("^verify that pagination buttons are available in \"(.*?)\" details grid$")
	public void verify_that_pagination_buttons_are_available_in_details_grid(String arg1) throws Throwable {
		boolean nextFlg = endpointFirmwarePage.verifyElementDisplayed(
				endpointFirmwarePageObjectRepo.get("editEndpointFirmwareAuditAccordionPaginationNext"));
		boolean lastFlg = endpointFirmwarePage.verifyElementDisplayed(
				endpointFirmwarePageObjectRepo.get("editEndpointFirmwareAuditAccordionPaginationLast"));
		boolean prevFlg = endpointFirmwarePage.verifyElementDisplayed(
				endpointFirmwarePageObjectRepo.get("editEndpointFirmwareAuditAccordionPaginationPrev"));
		boolean firstFlg = endpointFirmwarePage.verifyElementDisplayed(
				endpointFirmwarePageObjectRepo.get("editEndpointFirmwareAuditAccordionPaginationFirst"));
		if (!(nextFlg && lastFlg && prevFlg && firstFlg)) {
			Assert.fail("Pagination buttons are not visible");
		}
	}

	@When("^click on \"(.*?)\" button on delete confirmation box on edit endpoint firmware popup$")
	public void click_on_button_on_delete_confirmation_box_on_edit_endpoint_firmware_popup(String buttonName)
			throws Throwable {
		if (!checkExistingEndpointFirmware) {
			return;
		}
		String message = "Failed to click on button :" + buttonName + " "
				+ " on delete Endpoint Firmware confirmation box.";
		Assert.assertTrue(message, endpointFirmwarePage
				.clickOperationOnDeleteEndpointFirmwareConfirmationBox(buttonName, endpointFirmwarePageObjectRepo));
	}

	@Given("^we launch the browser with admin-UI URL and login with valid admin credentials for endpointFirmware Page$")
	public void we_launch_the_browser_with_admin_UI_URL_and_login_with_valid_admin_credentials() throws Throwable {
		String message = "Unsuccessful login with valid credentials";
		endpointFirmwarePage.waitForSpinner();
		endpointFirmwarePage.assertElementDisplayed(TestBase.getReadConfigData().get("optionDropDown"), message);
	}

	@Then("^enter valid value in \"(.*?)\" field$")
	public void enter_valid_name_in_name_field(String field) throws Throwable {
		// String message = "Failed to enter valid value in field " + field;
		endpointFirmwarePage.enterTextEndpointFirmware(field, endpointFirmwarePageObjectRepo,
				endpointFirmwarePageObjectTestData);
	}

	@Then("verify Export icon is display on EndpointFirmware tab$")
	public void verify_Export_icon_is_display_on_EndpointFirmware_tab() throws Throwable {
		String message = "Export Icon is not available on Endpoint Firmware Tab";
		endpointFirmwarePage.assertElementDisplayed(TestBase.getReadConfigData().get("exportIcon"), message);
	}

	@Then("click on Export icon on EndpointFirmware tab$")
	public void click_on_Export_icon_on_EndpointFirmware_tab() throws Throwable {
		endpointFirmwarePage.waitForSpinner();
		if (!endpointFirmwarePage.checkIfClickable(TestBase.getReadConfigData().get("exportIcon"))) {
			Assert.fail("Not able to click on export icon.");
		}
		endpointFirmwarePage.clickAndWait(TestBase.getReadConfigData().get("exportIcon"));

		String message = "Error while downloading a report";
		endpointFirmwarePage.assertElementDisplayed(TestBase.getReadConfigData().get("peripheralfirmwareTab"), message);
	}

	@After("@EndpointFirmwarePage")
	public void afterClass(Scenario scenario) {
		endpointFirmwarePage.closeResources(scenario);
	}
*/}

