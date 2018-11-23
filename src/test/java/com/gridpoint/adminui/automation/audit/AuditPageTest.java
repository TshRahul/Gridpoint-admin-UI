package com.gridpoint.adminui.automation.audit;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;

import com.gridpoint.adminui.automation.commonSteps.CommonStepsImplementation;
import com.gridpoint.adminui.automation.loginAndInitilizeResources.LoginPageTest;
import com.gridpoint.adminui.automation.testdata.ManageTestData;
import com.gridpoint.automation.constants.PathConstants;
import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.util.BaseUtil;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AuditPageTest extends CommonInit {

	private Logger logger = Logger.getLogger(LoginPageTest.class);
	private AuditPage auditPage;

	private Boolean checkExistingEndpointFirmware = true;
	CommonStepsImplementation commonStepsImplementation = new CommonStepsImplementation();

	private static boolean isSitePresent, isEndpointFirmwarePresent,
			isTenantPresent, isUserPresent, isEndpointPresent;

	@Given("^User is already logged in to Admin URL and is already present at the AuditPage Tab for \"(.*?)\"$")
	public void user_is_already_logged_in_to_Admin_URL_and_is_already_present_at_the_Commissioning_Tool_s_General_Tab_for_Scenario_(
			String arg1) throws Throwable {

		auditPage = new AuditPage();
		BaseUtil.explicitWait(8000);
		BaseUtil.waitForSpinner();
		BaseUtil.overrideTimeout(getProps().getProperty("auditTimeout"));
		BaseUtil.overridePollingfrequency(getProps().getProperty(
				"auditPollingFrequency"));
		BaseUtil.explicitWait(2000);
		String message = "Unsuccessful login with valid credentials";
		BaseUtil.assertElementDisplayed(getProps()
				.getProperty("optionDropDown"), message);

	}

	@Given("^we are on Audit page and search audit field is enabled and date picker is visible$")
	public void we_are_on_Audit_page_and_search_audit_field_is_enabled()
			throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.explicitWait(8000);
		BaseUtil.clickAndWait(getProps().getProperty("auditTab"));
		BaseUtil.explicitWait(3000);
		BaseUtil.waitForSpinner();
		BaseUtil.instantElementCheck("Display",
				getProps().getProperty("auditDatePicker"));
	}

	@Then("^click on logout button and redirect to login page$")
	public void Verify_Logout_After_Successful_Login()
			throws InterruptedException {
		BaseUtil.explicitWait(8000);
		if (!BaseUtil
				.checkIfClickable(getProps().getProperty("optionDropDown"))) {
			Assert.fail("Not able to login in the portal.");
		}
		BaseUtil.clickAndWait(getProps().getProperty("logout"));

		String message = "Error while logging out";
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("adminUserName_Locator"), message);
	}

	@And("^login with following roles with valid username and password and verify these user should not be able to view Audit Page$")
	public void login_with_following_roles_with_valid_username_and_password_and_verify_these_user_should_not_be_able_to_view_Audit_Page(
			DataTable table) throws Throwable {
		BaseUtil.explicitWait(8000);
		List<String> lstData = table.asList(String.class);
		BaseUtil.waitForSpinner();
		for (String userName : lstData) {

			auditPage.verify_AuditTab(userName);
			if (!BaseUtil.checkIfClickable(getProps().getProperty(
					"optionDropDown"))) {
				Assert.fail("Not able to login in the portal for the user : "
						+ userName);
			}
		}
	}

	@Given("^Initialize resources in the beginning of Audit Page$")
	public void Initialize_Resources() throws IOException, InterruptedException {
		auditPage = new AuditPage();
		BaseUtil.waitForSpinner();
		BaseUtil.explicitWait(8000);
	}

	@Then("^verify the following columns in audit details grid$")
	public void verify_the_following_columns_in_audit_details_grid(
			DataTable args) throws Throwable {
		BaseUtil.explicitWait(8000);
		List<String> lstData = args.asList(String.class);
		args.asList(String.class);
		boolean isVisible = true;
		for (String header : lstData) {
			boolean visible = false;
			if (header.trim().equals("Entity ID")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("auditPageEntityIdHeader"));
			}
			if (header.trim().equals("Entity Name")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("auditPageEntityNameHeader"));
			}
			if (header.trim().equals("Domain Type")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("auditPageDomainTypeHeader"));
			}
			if (header.trim().equals("Property")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("auditPagePropertyHeader"));
			}
			if (header.trim().equals("Action")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("auditPageActionHeader"));
			}
			if (header.trim().equals("Original Value")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("auditPageOriginalValueHeader"));
			}
			if (header.trim().equals("Updated Value")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("auditPageUpdatedValueHeader"));
			}
			if (header.trim().equals("User")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("auditPageUserHeader"));
			}
			if (header.trim().equals("Date Stamp")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("auditPageDateStampHeader"));
			}
			if (visible == false) {
				if (visible == false) {
					isVisible = false;
					Assert.assertTrue("Header [" + header
							+ "] is not visible in User Grid", isVisible);
					break;
				}
			}
		}
	}

	@Then("^verify the sorting functionality of columns in audit details grid$")
	public void verify_the_sorting_functionality_of_columns_in_audit_details_grid(
			DataTable args) throws Throwable {
		BaseUtil.explicitWait(8000);
		List<String> lstData = args.asList(String.class);
		for (String header : lstData) {
			auditPage.verifySorting_AuditPage("Ascending", header);
			auditPage.verifySorting_AuditPage("Descending", header);
		}
	}

	@When("^click on \"(.*?)\" link in audit grid$")
	public void click_on_link_in_audit_grid(String sLinkName) throws Throwable {
		BaseUtil.explicitWait(8000);
		auditPage.clickPaginationAuditPage(sLinkName);
	}

	@Then("^\"(.*?)\" page should displayed in audit grid$")
	public void page_should_displayed_in_audit_grid(String task)
			throws Throwable {
		BaseUtil.explicitWait(8000);
		auditPage.verifyAuditDetailGridPagination(task);
	}

	@When("^enter random page number in page link text in audit grid$")
	public void enter_random_page_number_in_page_link_text_in_audit_grid()
			throws Throwable {
		BaseUtil.explicitWait(8000);
		String totalPages = BaseUtil.getElementText(getProps().getProperty(
				"auditPaginationTotalPages"));
		totalPages = totalPages.substring(totalPages.indexOf("/") + 1).trim();
		int pageCount = Integer.parseInt(totalPages);
		if (pageCount <= 1) {
			Assert.fail("Indufficient data for this test step. All the available audits are on the first page only.");
		}
		Random random = new Random();
		int randomPage = random.nextInt(pageCount + 1);
		if (randomPage == 0 || randomPage == 1)
			randomPage = 2;
		boolean bSet = auditPage.setRandomPage(Integer.parseInt(totalPages),
				randomPage);
		BaseUtil.waitForSpinner();
		Assert.assertTrue("Page" + randomPage + " is not selected.", bSet);
	}

	@Then("^user should be navigated on the given random page in audit grid$")
	public void user_should_be_navigated_on_the_given_random_page_in_audit_grid()
			throws Throwable {
		BaseUtil.explicitWait(8000);
		BaseUtil.waitForSpinner();
		BaseUtil.getMultipleElementTextAfterLoaded(getProps().getProperty(
				"auditPageEntityIdHeader"));
		BaseUtil.setElementAttribute(
				getProps().getProperty("auditPaginationCurrentPage"), "1");
		BaseUtil.waitForSpinner();
		BaseUtil.getMultipleElementTextAfterLoaded(getProps().getProperty(
				"auditPageEntityIdHeader"));
	}

	@Then("^delete the \"(.*?)\" data used as test data for audit page$")
	public void add_delete_data_used_as_test_data_for_audit_page(
			String dataType, List<String> listAuditTestData) throws Throwable {
		BaseUtil.explicitWait(8000);
		ManageTestData objTestdata = new ManageTestData();
		if (dataType.equals("Endpoint Firmware")) {
			for (String endpointFirmwareTestData : listAuditTestData) {
				isEndpointFirmwarePresent = objTestdata.manageTestData(
						"Endpoint_Firmware_Present", endpointFirmwareTestData);
				if (!isEndpointFirmwarePresent) {
					return;
				}
				BaseUtil.waitForSpinner(1000);
				String tabName = "Firmware", subTabName = "Endpoint Firmware";

				Assert.assertTrue("Clicking on " + tabName + " is failed",
						auditPage.clickOperationOnAuditpage(tabName));
				BaseUtil.waitForSpinner(1000);
				Assert.assertTrue("Clicking on " + subTabName + " is failed",
						auditPage.clickOperationOnAuditpage(subTabName));
				BaseUtil.waitForSpinner(1000);

				commonStepsImplementation.enter_the_test_Data_in_the_search_box_and_click_on_the_search_button(
						subTabName, endpointFirmwareTestData);
				commonStepsImplementation.select_the_given_from_the_list_and_verify_the_confirmation_box(
						subTabName, endpointFirmwareTestData);

				BaseUtil.explicitWait(PathConstants.WAIT_HIGH);
				commonStepsImplementation.click_on_button(subTabName, "Delete Firmware");
				BaseUtil.explicitWait(PathConstants.WAIT_LOW);
				commonStepsImplementation.confirmation_box_should_displayed_with_message(
						subTabName,
						"Are you sure you want to delete this firmware version?",
						endpointFirmwareTestData);
				commonStepsImplementation.click_on_button(subTabName, "Endpoint Firmware:Delete_OK");
				BaseUtil.waitForSpinner(1000);
				commonStepsImplementation.user_should_be_navigated_to_page_with_a_label_saying(
						"Endpoint Firmware ##### deleted.", subTabName,
						endpointFirmwareTestData);
				isEndpointFirmwarePresent = false;

				logger.info("*****("
						+ endpointFirmwareTestData
						+ ")Endpoint Firmware has been deleted successfully*****");
			}

		} else if (dataType.equals("Tenant")) {

			for (String tenantTestData : listAuditTestData) {
				isTenantPresent = objTestdata.manageTestData("Tenant_Present",
						tenantTestData);
				if (!isTenantPresent) {
					return;
				}
				BaseUtil.waitForSpinner(1000);
				String tabName = "Tenant";
				Assert.assertTrue("Clicking on " + tabName + " is failed",
						auditPage.clickOperationOnAuditpage(tabName));
				BaseUtil.waitForSpinner(1000);

				commonStepsImplementation.enter_the_test_Data_in_the_search_box_and_click_on_the_search_button(
						tabName, tenantTestData);
				commonStepsImplementation.select_the_given_from_the_list_and_verify_the_confirmation_box(
						tabName, tenantTestData);

				BaseUtil.explicitWait(PathConstants.WAIT_HIGH);
				commonStepsImplementation.click_on_button(tabName, "Delete Tenant");
				BaseUtil.explicitWait(PathConstants.WAIT_LOW);
				commonStepsImplementation.confirmation_box_should_displayed_with_message(tabName,
						"Are you sure you want to delete this Tenant?",
						tenantTestData);
				commonStepsImplementation.click_on_button(tabName, "Tenant:Delete_OK");
				BaseUtil.waitForSpinner(1000);
				commonStepsImplementation.user_should_be_navigated_to_page_with_a_label_saying(
						"Tenant ##### deleted.", tabName, tenantTestData);
				isTenantPresent = false;

				logger.info("*****(" + tenantTestData
						+ ")Tenant has been deleted successfully*****");
			}

		} else if (dataType.equals("Site")) {
			for (String sitesTestData : listAuditTestData) {
				isSitePresent = objTestdata.manageTestData("Site_Present",
						sitesTestData);
				if (!isSitePresent) {
					return;
				}
				BaseUtil.waitForSpinner(1000);
				String tabName = "Site";
				Assert.assertTrue("Clicking on " + tabName + " is failed",
						auditPage.clickOperationOnAuditpage(tabName));
				BaseUtil.waitForSpinner(1000);

				commonStepsImplementation.enter_the_test_Data_in_the_search_box_and_click_on_the_search_button(
						tabName, sitesTestData);
				commonStepsImplementation.select_the_given_from_the_list_and_verify_the_confirmation_box(
						tabName, sitesTestData);

				BaseUtil.explicitWait(PathConstants.WAIT_HIGH);
				commonStepsImplementation.click_on_button(tabName, "Delete Site");
				BaseUtil.explicitWait(PathConstants.WAIT_LOW);
				commonStepsImplementation.confirmation_box_should_displayed_with_message(tabName,
						"Do you want to delete the site ##### ?", sitesTestData);
				commonStepsImplementation.click_on_button(tabName, "Site:Delete_OK");
				BaseUtil.waitForSpinner(1000);
				commonStepsImplementation.user_should_be_navigated_to_page_with_a_label_saying(
						"##### is deleted successfully.", tabName,
						sitesTestData);
				isSitePresent = false;

				logger.info("*****(" + sitesTestData
						+ ")Site has been deleted successfully*****");
			}

		} else if (dataType.equals("Endpoint")) {
			for (String endpointTestData : listAuditTestData) {
				isEndpointPresent = objTestdata.manageTestData(
						"Endpoint_Present", endpointTestData);
				if (!isEndpointPresent) {
					return;
				}
				Assert.assertTrue("Data should be deleted ", objTestdata
						.manageTestData("Endpoint_Delete", endpointTestData));
			}

		} else if (dataType.equals("Users")) {
			for (String enduserTestData : listAuditTestData) {
				isUserPresent = objTestdata.manageTestData("User_Present",
						enduserTestData);
				if (!isUserPresent) {
					return;
				}
				Assert.assertTrue("Data should be deleted ", objTestdata
						.manageTestData("Delete_Resources", enduserTestData));
			}

		}

	}
	
	
//TODO : Need to delete as this method is shifted to commonstep definition file
//	@Then("^enter_the_test_Data_in_the_search_box_and_click_on_the_search_button$")
//	public void enter_the_test_Data_in_the_search_box_and_click_on_the_search_button(
//			String type, String testData) {
//		BaseUtil.explicitWait(8000);
//		String fieldLocator = null;
//		if (type.equalsIgnoreCase("Endpoint Firmware")) {
//			fieldLocator = getProps().getProperty(
//					"endpointFirmwareTabSearchField");
//
//		} else if (type.equalsIgnoreCase("Site")) {
//			fieldLocator = getProps().getProperty("siteTabSearchField");
//
//		} else if (type.equalsIgnoreCase("Tenant")) {
//			fieldLocator = getProps().getProperty("tenantTabSearchField");
//
//		} else if (type.equalsIgnoreCase("Peripheral Firmware")) {
//			fieldLocator = getProps().getProperty(
//					"peripheralFirmwareSearchField");
//
//		}
//
//		BaseUtil.enterText(fieldLocator, testData);
//		BaseUtil.waitForSpinner(1000);
//	}

	
	//TODO : Need to delete as this method is shifted to commonstep definition file
//	@Then("^select given \"(.*?)\" from the list and verify the Confirmation box$")
//	public void select_the_given_from_the_list_and_verify_the_confirmation_box(
//			String sourceNameFeature, String TestData) {
//		boolean bResult = false;
//		String gridColumnLocator = null;
//		BaseUtil.explicitWait(8000);
//		if (sourceNameFeature.equalsIgnoreCase("Endpoint Firmware")) {
//			BaseUtil.click(getProps().getProperty(
//					"endpointFirmwareSearchButton"));
//			BaseUtil.waitForSpinner();
//			gridColumnLocator = getProps().getProperty(
//					"endpointFirmwarePageNameColumn");
//
//		} else if (sourceNameFeature.equalsIgnoreCase("Site")) {
//			BaseUtil.click(getProps().getProperty("siteTabSearchButton"));
//			BaseUtil.waitForSpinner();
//			gridColumnLocator = getProps().getProperty("sitePageNameColumn");
//
//		} else if (sourceNameFeature.equalsIgnoreCase("Tenant")) {
//			BaseUtil.click(getProps().getProperty("tenantPageSearchButton"));
//			BaseUtil.waitForSpinner();
//			gridColumnLocator = getProps().getProperty("tenantPageNameColumn");
//
//		} else if (sourceNameFeature.equalsIgnoreCase("Peripheral Firmware")) {
//			BaseUtil.click(getProps().getProperty(
//					"peripheralFirmwarePageSearchButton"));
//			BaseUtil.waitForSpinner();
//			gridColumnLocator = getProps().getProperty(
//					"peripheralFirmwarePageNameColumn");
//
//		}
//
//		try {
//			List<WebElement> lstSourcesNames = BaseUtil
//					.getMultipleElementsAfterLoaded(gridColumnLocator);
//			for (WebElement webElement : lstSourcesNames) {
//				if (webElement.getText().trim().equalsIgnoreCase(TestData)) {
//					webElement.click();
//					BaseUtil.waitForSpinner(1000);
//					bResult = true;
//					break;
//				}
//			}
//			if (!bResult) {
//				Assert.fail("Given test " + sourceNameFeature
//						+ " is not available");
//			}
//			logger.debug("Searched source :" + TestData + " is selected.");
//		} catch (Exception e) {
//			logger.error("Failed to select source: " + TestData
//					+ " from grid , see detail error message: "
//					+ e.getStackTrace().toString());
//			Assert.fail("Failed to select the: " + TestData
//					+ ", see detail error message: \n"
//					+ e.getStackTrace().toString());
//		} finally {
//			BaseUtil.explicitWait(3000);
//		}
//	}

	
	//TODO : Need to delete as this method is shifted to commonstep definition file
//	@Then("^click on \"(.*?)\" button_tab$")
//	public void click_on_button_tab(String tabName, String buttonName)
//			throws Throwable {
//		BaseUtil.explicitWait(8000);
//		if (tabName.equals("Endpoint Firmware")) {
//			Assert.assertTrue("Clicking on " + buttonName + " is failed",
//					auditPage.clickOperationOnAuditpage(buttonName));
//		} else if (tabName.equals("Site")) {
//			Assert.assertTrue("Clicking on " + buttonName + " is failed",
//					auditPage.clickOperationOnAuditpage(buttonName));
//		} else if (tabName.equals("Tenant")) {
//			Assert.assertTrue("Clicking on " + buttonName + " is failed",
//					auditPage.clickOperationOnAuditpage(buttonName));
//		} else if (tabName.equals("Peripheral Firmware")) {
//			Assert.assertTrue("Clicking on " + buttonName + " is failed",
//					auditPage.clickOperationOnAuditpage(buttonName));
//		}
//
//	}

	//TODO : Need to delete as this method is shifted to commonstep definition file
//	@Then("^verify user should be navigated to sites page with a label saying \"(.*?)\"$")
//	public void user_should_be_navigated_to_page_with_a_label_saying(
//			String message, String type, String testData) throws Throwable {
//		BaseUtil.explicitWait(2000);
//		BaseUtil.explicitWait(8000);
//		String messageOnUI = null;
//		if (type.equalsIgnoreCase("Endpoint Firmware")) {
//			message = message.replace("#####", testData);
//			BaseUtil.waitForSpinner();
//			BaseUtil.assertElementDisplayed(
//					getProps().getProperty("addEndpointFirmwareSaveMsg"),
//					"Message is not visible on the Endpoint Firmware page");
//			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
//					"addEndpointFirmwareSaveMsg"));
//		} else if (type.equalsIgnoreCase("Site")) {
//			message = message.replace("#####", testData);
//			BaseUtil.waitForSpinner();
//			BaseUtil.assertElementDisplayed(
//					getProps().getProperty("addSiteSaveMsg"),
//					"Message is not visible on the Site page");
//			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
//					"addSiteSaveMsg"));
//		} else if (type.equalsIgnoreCase("Tenant")) {
//			message = message.replace("#####", testData);
//			BaseUtil.waitForSpinner();
//			BaseUtil.assertElementDisplayed(
//					getProps().getProperty("addTenantSaveMsg"),
//					"Message is not visible on the Tenant page");
//			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
//					"addTenantSaveMsg"));
//		} else if (type.equalsIgnoreCase("Peripheral Firmware")) {
//			message = message.replace("#####", testData);
//			BaseUtil.waitForSpinner();
//			BaseUtil.assertElementDisplayed(
//					getProps().getProperty("addPeripheralFirmwareSaveMsg"),
//					"Message is not visible on the Tenant page");
//			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
//					"addPeripheralFirmwareSaveMsg"));
//		}
//
//		Assert.assertTrue(" Message on UI(" + messageOnUI
//				+ ") is not expected(" + message + ") message",
//				message.equals(messageOnUI));
//	}

	//TODO : Need to delete as this method is shifted to commonstep definition file
//	@Then("^confirmation box should displayed with message saying \"(.*?)\"$")
//	public void confirmation_box_should_displayed_with_message(String type,
//			String expectedMessage, String testData) throws Throwable {
//		BaseUtil.explicitWait(8000);
//		String messageOnUI = null;
//		if (type.equals("Endpoint Firmware")) {
//			BaseUtil.assertElementDisplayed(
//					getProps().getProperty(
//							"editEndpointFirmwareDeleteConfirmationPopup"),
//					"Delete Confirmation box is not displayed");
//			BaseUtil.assertElementDisplayed(
//					getProps().getProperty(
//							"editEndpointFirmwareDeleteConfirmationPopupTitle"),
//					"Delete Confirmation box Title is not displayed");
//			BaseUtil.assertElementDisplayed(
//					getProps()
//							.getProperty(
//									"editEndpointFirmwareDeleteConfirmationPopupMessage"),
//					"Delete Confirmation box Message is not displayed");
//			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
//					"editEndpointFirmwareDeleteConfirmationPopupMessage"));
//
//		} else if (type.equals("Site")) {
//			expectedMessage = expectedMessage.replace("#####", testData);
//			BaseUtil.assertElementDisplayed(
//					getProps().getProperty("editSiteDeleteConfirmationPopup"),
//					"Delete Confirmation box is not displayed");
//			BaseUtil.assertElementDisplayed(
//					getProps().getProperty(
//							"editSiteDeleteConfirmationPopupTitle"),
//					"Delete Confirmation box Title is not displayed");
//			BaseUtil.assertElementDisplayed(
//					getProps().getProperty(
//							"editSiteDeleteConfirmationPopupMessage"),
//					"Delete Confirmation box Message is not displayed");
//			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
//					"editSiteDeleteConfirmationPopupMessage"));
//
//		} else if (type.equals("Tenant")) {
//			BaseUtil.assertElementDisplayed(
//					getProps().getProperty("editTenantDeleteConfirmationPopup"),
//					"Delete Confirmation box is not displayed");
//			BaseUtil.assertElementDisplayed(
//					getProps().getProperty(
//							"editTenantDeleteConfirmationPopupTitle"),
//					"Delete Confirmation box Title is not displayed");
//			BaseUtil.assertElementDisplayed(
//					getProps().getProperty(
//							"editTenantDeleteConfirmationPopupMessage"),
//					"Delete Confirmation box Message is not displayed");
//			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
//					"editTenantDeleteConfirmationPopupMessage"));
//
//		} else if (type.equals("Peripheral Firmware")) {
//			BaseUtil.assertElementDisplayed(
//					getProps().getProperty(
//							"editPeripheralFirmwareDeleteConfirmationPopup"),
//					"Delete Confirmation box is not displayed");
//			BaseUtil.assertElementDisplayed(
//					getProps()
//							.getProperty(
//									"editPeripheralFirmwareDeleteConfirmationPopupTitle"),
//					"Delete Confirmation box Title is not displayed");
//			BaseUtil.assertElementDisplayed(
//					getProps()
//							.getProperty(
//									"editPeripheralFirmwareDeleteConfirmationPopupMessage"),
//					"Delete Confirmation box Message is not displayed");
//			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
//					"editPeripheralFirmwareDeleteConfirmationPopupMessage"));
//
//		}
//
//		Assert.assertTrue("Confirmation message on UI(" + messageOnUI
//				+ ") is not expected(" + expectedMessage + ") message",
//				expectedMessage.equals(messageOnUI));
//	}

	
	
	@Then("^click on \"(.*?)\" tab/button from Audit page$")
	public void click_on_tab_from_Audit_page(String sElement) throws Throwable {
		String message = "Failed to click tab/button " + sElement;
		Assert.assertTrue(message,
				auditPage.clickOperationOnAuditpage(sElement));
	}

	@Then("^click on the \"(.*?)\" button on add \"(.*?)\" popup and select Valid file for auditing$")
	public void click_on_the_button_on_add_endpoint_firmware_popup_and_select_an_invalid_file(
			String buttonName, String tabName) throws Throwable {
		BaseUtil.explicitWait(8000);
		String firmwareFile = null, filePath = null, absolutePath = null;
		File f = null;
		switch (tabName.toUpperCase()) {
		case "ENDPOINT FIRMWARE":
			firmwareFile = "live_firmware.tar.gz";
			f = new File(firmwareFile);
			absolutePath = f.getAbsolutePath();
			filePath = absolutePath.substring(0,
					absolutePath.lastIndexOf(File.separator))
					+ "\\downloadDir\\" + firmwareFile;
			break;
		case "PERIPHERAL FIRMWARE":
			firmwareFile = "TS102_400.bin";
			f = new File(firmwareFile);
			absolutePath = f.getAbsolutePath();
			filePath = absolutePath.substring(0,
					absolutePath.lastIndexOf(File.separator))
					+ "\\downloadDir\\" + firmwareFile;
			break;
		}
		String message = "Failed to click on button :" + buttonName + " "
				+ " on add firmware popup.";
		Assert.assertTrue(message, auditPage
				.clickChooseButtonAndSelectInvalidFile(buttonName, tabName,
						filePath));
	}

	@Then("^enter the test data for the following fields to create new testData for auditing$")
	public void enter_the_test_data_for_the_following_fields_to_create_new_Test_Data_for_auditing(
			DataTable arg1) throws Throwable {
		List<String> fieldList = arg1.asList(String.class);
		for (String list : fieldList) {
			auditPage.enterTestData(list);
		}
	}

	@Then("^user should be navigated to endpoint firware page with a label saying \"(.*?)\" for \"(.*?)\" for auditing$")
	public void user_should_be_navigated_to_endpoint_firware_page_with_a_label_saying_for_auditing(
			String endpointFirmwareMsg, String type) throws Throwable {
		BaseUtil.explicitWait(8000);
		if (!checkExistingEndpointFirmware) {
			return;
		}
		String message = "Expected message not matched with actual message.";
		BaseUtil.waitForSpinner();
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("addEndpointFirmwareSaveMsg"),
				"Message is not visible on the page");
		Assert.assertTrue(message, endpointFirmwareMsg.equals(BaseUtil
				.getElementText(getProps().getProperty(
						"addEndpointFirmwareSaveMsg"))));
		BaseUtil.waitForSpinner();
	}

	@Then("^user should navigate to Tenant page with message \"(.*?)\"$")
	public void user_should_navigate_to_Tenant_page_with_message(
			String sTenantSaveMsg) throws Throwable {
		String message = "Expected message not matched with actual message.";
		Assert.assertEquals(
				message,
				sTenantSaveMsg,
				BaseUtil.getElementText(getProps().getProperty(
						"addTenantSaveMsg")));
	}

	@Then("^select the following check boxes$")
	public void select_the_following_check_box(List<String> checkBoxList)
			throws Throwable {
		BaseUtil.explicitWait(8000);
		BaseUtil.explicitWait(1000);
		auditPage.checkCheckBox(checkBoxList);
	}

	@When("^click on \"(.*?)\" button of add site popup for auditing$")
	public void click_on_button_on_add_site_popup(String buttonElement)
			throws Throwable {
		if (buttonElement.equalsIgnoreCase("Save And Close")) {
			BaseUtil.click(getProps().getProperty("addSiteSaveAndCloseButton"));
		} else if (buttonElement.equalsIgnoreCase("Cancel")) {
			BaseUtil.click(getProps().getProperty("addSiteCancelButton"));
		} else if (buttonElement.equalsIgnoreCase("Accept Verified Data")) {
			BaseUtil.click(getProps().getProperty(
					"addSiteAcceptVerifiedDataButton"));
			BaseUtil.explicitWait(3000);
		} else if (buttonElement.equalsIgnoreCase("Save")) {
			BaseUtil.click(getProps().getProperty("addSiteSaveButton"));
			BaseUtil.waitForSpinner();
		} else {
			throw new Exception("Error in if");
		}

		BaseUtil.waitForSpinner();
	}

	@Then("^user should navigate to Site page with message \"(.*?)\"$")
	public void user_should_navigate_to_Site_page_with_message(
			String sSiteSaveMsg) throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "Expected message not matched with actual message.";
		Assert.assertEquals(message, sSiteSaveMsg, BaseUtil
				.getElementText(getProps().getProperty("addSiteSaveMsg")));
	}

	@Then("^user should navigate to endpoint page with message \"(.*?)\"$")
	public void user_should_navigate_to_endpoint_page_with_message(
			String sEndpointSaveMsg) throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "Expected message not matched with actual message.";
		Assert.assertEquals(
				message,
				sEndpointSaveMsg,
				BaseUtil.getElementText(getProps().getProperty(
						"addEndpointSaveMsg")));
		BaseUtil.waitForSpinner(10000);
	}

	@Then("^user is navigated on Edit User Page with the created user$")
	public void user_is_navigated_on_Edit_User_Page_with_the_created_user()
			throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "User is not getting created";
		BaseUtil.assertElementDisplayed(getProps().getProperty("editUserPage"),
				message);
	}

	@Then("^verify the search output by entering \"(.*?)\" in audit search field$")
	public void verify_the_search_output_by_entering_in_audit_search_field(
			String args) throws Throwable {
		BaseUtil.explicitWait(8000);
		auditPage.verifySearchFunctionality(args);
	}

	@Then("^verify the pagination by entering value \"(.*?)\" in audit search field$")
	public void verify_the_pagination_by_entering_value_in_audit_search_field(
			String args) throws Throwable {
		BaseUtil.explicitWait(8000);
		auditPage.verifySearchFunctionality(args);
	}

	@Then("^enter start date and end date and click apply button$")
	public void enter_start_date_and_end_date_and_click_apply_button(
			DataTable table) throws Throwable {
		BaseUtil.explicitWait(8000);
		List<Map<String, String>> startAndEndDates = table.asMaps(String.class,
				String.class);
		Assert.assertTrue("datepicker should be update date ",
				auditPage.validateDatePicker(startAndEndDates));

		BaseUtil.click(getProps().getProperty("auditDatePickerApplyButton"));
	}

	@When("^enter site name \"(.*?)\" in audit search field")
	public void enter_site_name_in_audit_search_field(String sRootSiteName)
			throws Throwable {
		BaseUtil.explicitWait(8000);
		auditPage.enterText_AuditPage("SITE_SEARCH", sRootSiteName);
	}

	@Then("^verify that the \"(.*?)\" test data details should be displayed in the following columns")
	public void verify_that_the_newly_created_test_data_details_should_be_displayed_in_the_following_columns(
			String testDataType, DataTable table) {
		BaseUtil.explicitWait(8000);
		List<String> listElements = table.asList(String.class);
		for (String columnName : listElements) {
			auditPage.verifyDetails(columnName, testDataType);
		}
	}

	@Then("^select the audit record \"(.*?)\" from the audit details grid$")
	public void select_the_audit_record_of_firmware_from_the_audit_details_grid(
			String testDataName) throws Throwable {
		BaseUtil.explicitWait(8000);
		auditPage.selectAuditFromGrid_AuditPage(testDataName);
	}

	@Then("^user should navigate to \"(.*?)\" page$")
	public void user_should_navigate_to_page(String sPopupName)
			throws Throwable {
		BaseUtil.explicitWait(25000);
		String message = "Failed to check the popup existence" + sPopupName;
		Assert.assertTrue(message,
				auditPage.checkPopupExistence("EDIT_SITE", ""));
	}

	@And("^modify \"(.*?)\" name to \"(.*?)\"")
	public void modify_name_to(String attribut1, String attribut2) {
		BaseUtil.explicitWait(8000);
		BaseUtil.setElementAttribute(
				getProps().getProperty("auditPageEditSiteNameField"), attribut2);
		BaseUtil.setElementAttribute(
				getProps().getProperty("auditPageEditSiteDescriptionField"),
				attribut2);
	}

	@Then("^change the time of date picker on audit details grid$")
	public void change_the_time_of_audit_details_grid() throws Throwable {
		BaseUtil.explicitWait(8000);
		BaseUtil.click(getProps().getProperty("auditPagegpdateRange"));
		BaseUtil.selectDropDownByValue(
				getProps().getProperty("auditPagegEndTimeHEntry"), "23");
		BaseUtil.clickAndWait(getProps().getProperty(
				"auditPagegDateRanteTimePickerApplyBtn"));
	}

	@When("^enter firmware name \"(.*?)\" in audit search field")
	public void enter_firmware_name_in_audit_search_field(String firmwareName)
			throws Throwable {
		BaseUtil.explicitWait(8000);
		auditPage.enterText_AuditPage("ENDPOINT_FIRMWARE_SEARCH", firmwareName);
	}

	@When("^modify release date field and name field for the selected endpoint firmware for Auditing$")
	public void modify_relase_date_field_and_version_field_for_the_selected_endpoint_firmware_for_Auditing(
			DataTable arg1) throws Throwable {
		BaseUtil.explicitWait(8000);
		List<String> fieldList = arg1.asList(String.class);
		for (String list : fieldList) {
			auditPage.modifyTextEndpointFirmwareAudit(list);
		}
	}

	@Then("^user should navigate to \"(.*?)\" page for auditing$")
	public void user_should_navigate_to_page_for_auditing(String sPopupName)
			throws Throwable {
		BaseUtil.explicitWait(8000);
		BaseUtil.waitForSpinner();
		BaseUtil.explicitWait(2000);
		String message = "Failed to check the popup existence" + sPopupName;
		Assert.assertTrue(message,
				auditPage.checkPopupExistence("EDIT_ENDPOINTFIRMWARE", ""));
	}

	@Then("^confirmation box on edit endpoint firmware popup should displayed with message \"(.*?)\" for auditing$")
	public void confirmation_box_on_edit_endpoint_firmware_popup_should_displayed_with_message_for_auditing(
			String confirmationboxMessage) throws Throwable {
		BaseUtil.explicitWait(8000);
		if (!checkExistingEndpointFirmware) {
			return;
		}
		if (confirmationboxMessage.toLowerCase().startsWith("are you sure")) {
			boolean okButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("DeleteEndpointFirmwareOkConfirmationButton"));
			boolean cancelButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty(
							"DeleteEndpointFirmwareCancelConfirmationButton"));
			Assert.assertTrue(
					"Behavior confirmation popup did not appear when clicked on delete button of edit Endpoint Firmware",
					(okButton && cancelButton));
		} else if (confirmationboxMessage.toLowerCase().endsWith(
				"are you sure?")) {
			boolean okButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("DeleteEndpointFirmwareOkConfirmationButton"));
			boolean cancelButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty(
							"DeleteEndpointFirmwareCancelConfirmationButton"));
			Assert.assertTrue(
					"Confirmation popup did not appear when clicked on cancel button",
					(okButton && cancelButton));
		}
	}

	@Then("^it shows a confirmation popup with a message \"(.*?)\"$")
	public void it_shows_a_popup_with_a_message(String expectedMessage)
			throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "Expected message not matched with actual message.";
		Assert.assertEquals(
				message,
				expectedMessage,
				BaseUtil.getElementText(getProps().getProperty(
						"deleteEndpointFirmwareMessage")));
	}

	@Then("verify Export icon is display on Audit tab$")
	public void verify_Export_icon_is_display_on_Audit_tab() throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "Export Icon is not available on Audit Tab";
		BaseUtil.assertElementDisplayed(getProps().getProperty("exportIcon"),
				message);
	}

	@Then("click on Export icon on Audit tab$")
	public void click_on_Export_icon_on_Audit_tab() throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.explicitWait(8000);
		if (!BaseUtil.checkIfClickable(getProps().getProperty("exportIcon"))) {
			Assert.fail("Not able to click on export icon.");
		}
		BaseUtil.clickAndWait(getProps().getProperty("exportIcon"));

		String message = "Error while downloading a report";
		BaseUtil.assertElementDisplayed(getProps().getProperty("AuditTab"),
				message);
	}
	
	@And("^open the downloaded report for audit tab$")
	public void open_the_downloaded_report_for_audit_tab() throws Throwable {
		try {
			String ReportName = "Site_Report_"
					+ RandomStringUtils.randomAlphabetic(5) + ".xlsx";
			String updatedReportsName = downLoadReports("Site Report",
					ReportName);
			Assert.assertTrue("Report is getting downloaded with the name [ "
					+ updatedReportsName + " +].", true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String downLoadReports(String reportName, String downloadFileName) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info("Executing test step::" + methodName);

		downloadFileName = downloadFileName.replaceAll(" ", "_");

		try {
			String filePath = BaseUtil.getDownloadsPath()
					+ "/target/generated-report-temp-files";
			File fileToBeDeleted = new File(filePath);
			if (!fileToBeDeleted.exists()) {
				fileToBeDeleted.mkdir();
			} else {
				FileUtils.cleanDirectory(fileToBeDeleted);
			}
			return downloadFileName;
		} catch (Exception e) {
			commonStepsImplementation.reportError("Error while downloading report");
			logger.info("Error while downloading report");
			return downloadFileName.replaceAll(" ", "_");
		}
	}
}
