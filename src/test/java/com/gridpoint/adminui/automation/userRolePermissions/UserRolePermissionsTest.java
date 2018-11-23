package com.gridpoint.adminui.automation.userRolePermissions;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.gridpoint.adminui.automation.site.SitePage;
import com.gridpoint.adminui.automation.testdata.ManageTestData;
import com.gridpoint.automation.constants.PathConstants;
import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.util.BaseUtil;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserRolePermissionsTest extends CommonInit {

	private Logger logger = Logger.getLogger(UserRolePermissionsTest.class);

//	private Boolean checkExistingEndpointFirmware = true;
//	private Boolean checkExistingPeripheralFirmware = true;

	private static boolean isSitePresent, isUserPresent, isTenantPresent,
			isEndpointPresent, isEndpointFirmwarePresent;

	@Given("^User is already logged in to Admin URL and is already present at the Site Tab for \"(.*?)\"$")
	public void user_is_already_logged_in_to_Admin_URL_and_is_already_present_at_the_Site_Tab_for_Scenario_(
			String arg1) throws Throwable {

		BaseUtil.waitForSpinner();
		BaseUtil.overrideTimeout(getProps().getProperty(
				"userRolePermissionsTimeout"));
		BaseUtil.overridePollingfrequency(getProps().getProperty(
				"userRolePermissionsPollingFrequency"));
		String message = "Unsuccessful login with valid credentials";
		BaseUtil.assertElementDisplayed(getProps()
				.getProperty("optionDropDown"), message);

	}

	@Given("^we are on \"(.*?)\" page and search field is enabled and visible for URP$")
	public void we_are_on_page_and_search_field_is_enabled_and_visible_for_URP(
			String subTabName) throws Throwable {
		if (subTabName.equals("User")) {
			BaseUtil.clickAndWait(getProps().getProperty("usersTab"));
			BaseUtil.instantElementCheck("Display",
					getProps().getProperty("userSearchField"));

		} else if (subTabName.equals("Site")) {
			// BaseUtil.clickAndWait(getProps().getProperty("sitesTab"));
			BaseUtil.instantElementCheck("Display",
					getProps().getProperty("siteSearchField"));

		} else if (subTabName.equals("Tenant")) {
			BaseUtil.clickAndWait(getProps().getProperty("tenantsTab"));
			BaseUtil.instantElementCheck("Display",
					getProps().getProperty("tenantSearchField"));
		} else if (subTabName.equals("Endpoint")) {
			BaseUtil.clickAndWait(getProps().getProperty("endpointsTab"));
			BaseUtil.instantElementCheck("Display",
					getProps().getProperty("endpointTabSearchField"));
		} else if (subTabName.equals("Endpoint Firmware")) {
			BaseUtil.waitForSpinner();
			BaseUtil.clickAndWait(getProps().getProperty("firmwareTab"));
			BaseUtil.waitForSpinner();
			BaseUtil.instantElementCheck("Display",
					getProps().getProperty("endpointFirmwareSearchField"));
		} else if (subTabName.equals("Peripheral Firmware")) {
			BaseUtil.waitForSpinner();
			BaseUtil.clickAndWait(getProps().getProperty("firmwareTab"));
			BaseUtil.waitForSpinner();
			BaseUtil.instantElementCheck("Display",
					getProps().getProperty("peripheralFirmwareSearchField"));
		}

	}

	@When("^login with user \"(.*?)\" in admin ui$")
	public void we_will_login_with_credential_and_verify_dashboard_tab(
			String userName) throws Throwable {
		UserRolePermissionsPage.isHomePageAvailable(userName);
		BaseUtil.waitForSpinner();
	}

	@Then("^click on the portal button to logout from application$")
	public void click_on_portal_button_to_logout() throws Throwable {
		BaseUtil.waitForSpinner();
		if (!BaseUtil
				.checkIfClickable(getProps().getProperty("optionDropDown"))) {
			Assert.fail("Not able to click on portal navigator.");
		}
		BaseUtil.click(getProps().getProperty("optionDropDown"));
		BaseUtil.click(getProps().getProperty("logout"));
		BaseUtil.waitForSpinner();
	}

	@Then("^verify the default available tabs for role \"(.*?)\" user$")
	public void verify_below_are_the_default_avilable_tabs_for_role_user(
			String userRole, DataTable table) throws Throwable {
		List<String> list = table.asList(String.class);
		for (String elementName : list) {
			String message = "Failed to verify element" + elementName;
			BaseUtil.explicitWait(3000);
			Assert.assertTrue(message,
					UserRolePermissionsPage.verifyElementsURP(elementName));
		}
	}

	@Then("^click on \"(.*?)\" tab$")
	public void click_user_tabOperation_UserRolePermissions(String tab)
			throws Throwable {
		Assert.assertTrue(tab + " tab is not clicked",
				UserRolePermissionsPage.clickOperationUserRolePermissions(tab));
	}

	@When("^enter user name \"(.*?)\" in search field$")
	public void enter_the_user_name_in_search_field(String userName)
			throws Throwable {
		UserRolePermissionsPage.enterText_URP("USER_SEARCH", userName);
	}

	@Then("^select user \"(.*?)\" from the \"(.*?)\" details grid$")
	public void select_the_user_from_the_user_details_grid(String userName,
			String gridName) throws Throwable {
		BaseUtil.waitForSpinner();
		UserRolePermissionsPage.selectResourceFromGrid_URP(userName, gridName);
	}

	@Then("^click \"(.*?)\" accordion on edit user$")
	public void click_on_edit_user_UserRolePermissions(String button)
			throws Throwable {
		Assert.assertTrue(button + " Button is not clicked",
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(button));
	}

	@Then("^verify the default available capilibiltys of role \"(.*?)\"$")
	public void verify_available_capilibiltys(String role) throws Throwable {
		Assert.assertTrue("User default capabilitys is not as expected",
				UserRolePermissionsPage.verifyDefaultCapilibiltys(role));
	}

	@Then("^verify default selected capabilities and then select all available capabilities of role \"(.*?)\"$")
	public void verify_select_all_capilibiltys(String userRole)
			throws Throwable {
		Assert.assertTrue("User default capabilitys is not as expected",
				UserRolePermissionsPage.isSelectDefaultCapilibiltys(userRole));
	}

	@When("^click \"(.*?)\" button on edit user page$")
	public void click_button_On_Edit_User(String sButtonName) throws Throwable {
		String message = "Failed to click on button :" + sButtonName + " "
				+ " on User Page";
		Assert.assertTrue(message, UserRolePermissionsPage
				.clickOperationUserRolePermissions(sButtonName));
	}

	@Then("^click \"(.*?)\" button on \"(.*?)\" list page$")
	public void click_on_user_page(String button, String listPageName)
			throws Throwable {
		BaseUtil.waitForSpinner();
		Assert.assertTrue(button + " Button is not clicked",
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(button));

	}

	@Then("^enter valid values on add user page$")
	public void enter_valid_values_for_user(DataTable dataTable)
			throws Throwable {
		Map<String, String> data = dataTable.asMap(String.class, String.class);
		String userName = data.get("UserName").trim();
		String firstName = data.get("FirstName").trim();
		String lastName = data.get("LastName").trim();
		String email = data.get("Email").trim();
		String role = data.get("Role").trim();
		// ManageTestData objDelTestdata = new ManageTestData();
		// Assert.assertTrue("Error occured while deleting test Data for user page",
		// objDelTestdata.manageTestData("User", userName));
		BaseUtil.enterText(getProps().getProperty("userNameField"), userName);
		BaseUtil.enterText(getProps().getProperty("addUserFirstNameField"),
				firstName);
		BaseUtil.enterText(getProps().getProperty("addUserLastNameField"),
				lastName);
		BaseUtil.enterText(getProps().getProperty("addUserEmailField"), email);
		BaseUtil.selectDropDownByValue(getProps().getProperty("roleField"),
				role);
	}

	@Then("^click on the \"(.*?)\" button on add user popup$")
	public void click_on_button_on_add_user_popup(String sButtonName)
			throws Throwable {
		String message = "Failed to click on button :" + sButtonName + " "
				+ " on User Page";
		Assert.assertTrue(message, UserRolePermissionsPage
				.clickOperationUserRolePermissions(sButtonName));
		BaseUtil.waitForSpinner();
	}

	@Then("^verify on \"(.*?)\" model have below \"(.*?)\" values$")
	public void verify_that_Section_have_following_fields(String arg1,
			String pageName, DataTable editDataTable) throws Throwable {
		Map<String, String> data = editDataTable.asMap(String.class,
				String.class);
		Assert.assertTrue("Data is not expected as saved on Add User",
				UserRolePermissionsPage.editUserValueVerification(data));
	}

	@Then("^click \"(.*?)\" button on edit user$")
	public void click_button_on_edit_user_UserRolePermissions(String button)
			throws Throwable {
		Assert.assertTrue(button + " Button is not clicked",
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(button));
	}

	@Then("^delete the \"(.*?)\" data used as test data for URP page$")
	public void add_delete_data_used_as_test_data_for_URP_page(String dataType,
			List<String> listURPTestData) throws Throwable {
		boolean flag = false;
		ManageTestData objTestdata = new ManageTestData();
		if (dataType.equals("Sites")) {
			for (String sitesTestData : listURPTestData) {
				isSitePresent = objTestdata.manageTestData("Site_Present",
						sitesTestData);
				if (!isSitePresent) {
					return;
				}
				BaseUtil.waitForSpinner(1000);
				String tabName = "Sites";
				Assert.assertTrue("Clicking on " + tabName + " is failed",
						UserRolePermissionsPage
								.clickOperationUserRolePermissions(tabName));
				BaseUtil.waitForSpinner(1000);

				enter_the_test_Data_in_the_search_box_and_click_on_the_search_button_for_URP(
						tabName, sitesTestData);
				select_given_from_the_list_and_verify_the_confirmation_box_for_URP(
						tabName, sitesTestData);

				BaseUtil.explicitWait(PathConstants.WAIT_HIGH);
				click_on_button_URP(tabName, "Delete Site");
				BaseUtil.explicitWait(PathConstants.WAIT_LOW);
				confirmation_box_should_displayed_with_message_URP(tabName,
						"Do you want to delete the site ##### ?", sitesTestData);
				click_on_button_URP(tabName, "Site:Delete_OK");
				BaseUtil.waitForSpinner(1000);
				user_should_be_navigated_to_page_with_a_label_saying(
						"##### is deleted successfully.", tabName,
						sitesTestData);
				isSitePresent = false;

				logger.info("*****(" + sitesTestData
						+ ")Site has been deleted successfully*****");
			}

		} else if (dataType.equals("Users")) {
			for (String enduserTestData : listURPTestData) {
				isUserPresent = objTestdata.manageTestData("User_Present",
						enduserTestData);
				if (!isUserPresent) {
					return;
				}
				Assert.assertTrue("Data should be deleted ", objTestdata
						.manageTestData("Delete_Resources", enduserTestData));
			}

		} else if (dataType.equals("Tenant")) {

			for (String tenantTestData : listURPTestData) {
				isTenantPresent = objTestdata.manageTestData("Tenant_Present",
						tenantTestData);
				if (!isTenantPresent) {
					return;
				}
				BaseUtil.waitForSpinner(1000);
				String tabName = "Tenant";
				Assert.assertTrue("Clicking on " + tabName + " is failed",
						UserRolePermissionsPage
								.clickOperationUserRolePermissions(tabName));
				BaseUtil.waitForSpinner(1000);

				enter_the_test_Data_in_the_search_box_and_click_on_the_search_button_for_URP(
						tabName, tenantTestData);
				select_given_from_the_list_and_verify_the_confirmation_box_for_URP(
						tabName, tenantTestData);

				BaseUtil.explicitWait(PathConstants.WAIT_HIGH);
				click_on_button_URP(tabName, "Delete Tenant");
				BaseUtil.explicitWait(PathConstants.WAIT_LOW);
				confirmation_box_should_displayed_with_message_URP(tabName,
						"Are you sure you want to delete this Tenant?",
						tenantTestData);
				click_on_button_URP(tabName, "Tenant:Delete_OK");
				BaseUtil.waitForSpinner(1000);
				user_should_be_navigated_to_page_with_a_label_saying(
						"Tenant ##### deleted.", tabName, tenantTestData);
				isTenantPresent = false;

				logger.info("*****(" + tenantTestData
						+ ")Tenant has been deleted successfully*****");
			}

		} else if (dataType.equals("Endpoint")) {
			for (String endpointTestData : listURPTestData) {
				isEndpointPresent = objTestdata.manageTestData(
						"Endpoint_Present", endpointTestData);
				if (!isEndpointPresent) {
					return;
				}
				Assert.assertTrue("Data should be deleted ", objTestdata
						.manageTestData("Endpoint_Delete", endpointTestData));
			}

		} else if (dataType.equals("Endpoint Firmware")) {
			for (String endpointFirmwareTestData : listURPTestData) {
				isEndpointFirmwarePresent = objTestdata.manageTestData(
						"Endpoint_Firmware_Present", endpointFirmwareTestData);
				if (!isEndpointFirmwarePresent) {
					return;
				}
				BaseUtil.waitForSpinner(1000);
				String tabName = "Firmware", subTabName = "Endpoint Firmware";

				Assert.assertTrue("Clicking on " + tabName + " is failed",
						UserRolePermissionsPage
								.clickOperationUserRolePermissions(tabName));
				BaseUtil.waitForSpinner(1000);
				Assert.assertTrue("Clicking on " + subTabName + " is failed",
						UserRolePermissionsPage
								.clickOperationUserRolePermissions(subTabName));
				BaseUtil.waitForSpinner(1000);

				enter_the_test_Data_in_the_search_box_and_click_on_the_search_button_for_URP(
						subTabName, endpointFirmwareTestData);
				flag = select_given_from_the_list_and_verify_the_confirmation_box_for_URP(
						subTabName, endpointFirmwareTestData);

				if (flag == true) {
					BaseUtil.explicitWait(PathConstants.WAIT_HIGH);
					click_on_button_URP(subTabName, "Delete Endpoint Firmware");
					BaseUtil.explicitWait(PathConstants.WAIT_LOW);
					confirmation_box_should_displayed_with_message_URP(
							subTabName,
							"Are you sure you want to delete this firmware version?",
							endpointFirmwareTestData);
					click_on_button_URP(subTabName,
							"Endpoint Firmware:Delete_OK");
					BaseUtil.waitForSpinner(1000);
					user_should_be_navigated_to_page_with_a_label_saying(
							"Endpoint Firmware ##### deleted.", subTabName,
							endpointFirmwareTestData);
					isEndpointFirmwarePresent = false;

					logger.info("*****("
							+ endpointFirmwareTestData
							+ ")Endpoint Firmware has been deleted successfully*****");
				} else {
					logger.info("*****("
							+ endpointFirmwareTestData
							+ ")Endpoint Firmware is already deleted or not in the database*****");
				}
			}

		} else if (dataType.equals("Peripheral Firmware")) {

			for (String peripheralFirmwareTestData : listURPTestData) {
				BaseUtil.waitForSpinner(1000);
				String tabName = "Firmware", subTabName = "Peripheral Firmware";

				Assert.assertTrue("Clicking on " + tabName + " is failed",
						UserRolePermissionsPage
								.clickOperationUserRolePermissions(tabName));
				BaseUtil.waitForSpinner(1000);
				Assert.assertTrue("Clicking on " + subTabName + " is failed",
						UserRolePermissionsPage
								.clickOperationUserRolePermissions(subTabName));
				BaseUtil.waitForSpinner(1000);

				enter_the_test_Data_in_the_search_box_and_click_on_the_search_button_for_URP(
						tabName, peripheralFirmwareTestData);
				flag = select_given_from_the_list_and_verify_the_confirmation_box_for_URP(
						tabName, peripheralFirmwareTestData);

				if (flag == true) {
					BaseUtil.explicitWait(PathConstants.WAIT_HIGH);
					click_on_button_URP(subTabName,
							"Delete Peripheral Firmware");
					BaseUtil.explicitWait(PathConstants.WAIT_LOW);
					confirmation_box_should_displayed_with_message_URP(
							subTabName,
							"Are you sure you want to delete the peripheral?",
							peripheralFirmwareTestData);
					click_on_button_URP(subTabName,
							"Peripheral Firmware:Delete_OK");
					BaseUtil.waitForSpinner(1000);
					user_should_be_navigated_to_page_with_a_label_saying(
							"Peripheral Firmware ####deleted .", subTabName,
							peripheralFirmwareTestData);
					isEndpointFirmwarePresent = false;

					logger.info("*****("
							+ peripheralFirmwareTestData
							+ ")Peripheral Firmware has been deleted successfully*****");
				} else {
					logger.info("*****("
							+ peripheralFirmwareTestData
							+ ")Peripheral Firmware is already deleted or not in the database*****");
				}
			}
		}

	}

	@Then("^enter_the_test_Data_in_the_search_box_and_click_on_the_search_button_for_URP$")
	public void enter_the_test_Data_in_the_search_box_and_click_on_the_search_button_for_URP(
			String type, String testData) {
		String fieldLocator = null;
		if (type.equalsIgnoreCase("Sites")) {
			fieldLocator = getProps().getProperty("siteSearchField");

		} else if (type.equalsIgnoreCase("Tenant")) {
			fieldLocator = getProps().getProperty("tenantSearchField");

		} else if (type.equalsIgnoreCase("Endpoint Firmware")) {
			fieldLocator = getProps()
					.getProperty("endpointFirmwareSearchField");

		} else if (type.equalsIgnoreCase("Peripheral Firmware")) {
			fieldLocator = getProps().getProperty(
					"peripheralFirmwareSearchField");

		}

		BaseUtil.enterText(fieldLocator, testData);
		BaseUtil.waitForSpinner(1000);
	}

	@Then("^select given \"(.*?)\" from the list and verify the Confirmation box for URP$")
	public boolean select_given_from_the_list_and_verify_the_confirmation_box_for_URP(
			String sourceNameFeature, String TestData) {
		boolean bResult = false;
		String gridColumnLocator = null;

		if (sourceNameFeature.equalsIgnoreCase("Sites")) {
			BaseUtil.click(getProps().getProperty("siteSearchField"));
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty("siteGridNameColumn");

		} else if (sourceNameFeature.equalsIgnoreCase("Tenant")) {
			BaseUtil.click(getProps().getProperty("tenantPageSearchButton"));
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty("tenantPageNameColumn");

		} else if (sourceNameFeature.equalsIgnoreCase("Endpoint Firmware")) {
			BaseUtil.click(getProps().getProperty(
					"endpointFirmwareSearchButton"));
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty(
					"endpointFirmwarePageNameColumn");

		} else if (sourceNameFeature.equalsIgnoreCase("Peripheral  Firmware")) {
			BaseUtil.click(getProps().getProperty(
					"peripheralFirmwareSearchButton"));
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty(
					"periperalFirmwarePagePeripheralTypeColumn");

		}
		try {
			List<WebElement> lstSourcesNames = BaseUtil
					.getMultipleElementsAfterLoaded(gridColumnLocator);
			for (WebElement webElement : lstSourcesNames) {
				if (webElement.getText().trim().equalsIgnoreCase(TestData)) {
					webElement.click();
					BaseUtil.waitForSpinner(1000);
					bResult = true;
					break;
				}
			}
			if (!bResult) {
				Assert.fail("Given test " + sourceNameFeature
						+ " is not available");
			}
			logger.debug("Searched source :" + TestData + " is selected.");
		} catch (Exception e) {
			logger.error("Failed to select source: " + TestData
					+ " from grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the: " + TestData
					+ ", see detail error message: \n"
					+ e.getStackTrace().toString());
		} finally {
			BaseUtil.explicitWait(3000);
		}
		return bResult;
	}

	@Then("^click on  button URP$")
	public void click_on_button_URP(String tabName, String buttonName)
			throws Throwable {
		if (tabName.equals("Sites")) {
			Assert.assertTrue("Clicking on " + buttonName + " is failed",
					UserRolePermissionsPage
							.clickOperationUserRolePermissions(buttonName));
		} else if (tabName.equals("Tenant")) {
			Assert.assertTrue("Clicking on " + buttonName + " is failed",
					UserRolePermissionsPage
							.clickOperationUserRolePermissions(buttonName));
		} else if (tabName.equals("Endpoint Firmware")) {
			Assert.assertTrue("Clicking on " + buttonName + " is failed",
					UserRolePermissionsPage
							.clickOperationUserRolePermissions(buttonName));
		} else if (tabName.equals("Peripheral Firmware")) {
			Assert.assertTrue("Clicking on " + buttonName + " is failed",
					UserRolePermissionsPage
							.clickOperationUserRolePermissions(buttonName));
		}

	}

	@Then("^confirmation box should displayed with message saying \"(.*?)\" for URP$")
	public void confirmation_box_should_displayed_with_message_URP(String type,
			String expectedMessage, String testData) throws Throwable {
		String messageOnUI = null;
		if (type.equals("Sites")) {
			expectedMessage = expectedMessage.replace("#####", testData);
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("editSiteDeleteConfirmationPopup"),
					"Delete Confirmation box is not displayed");
			BaseUtil.assertElementDisplayed(
					getProps().getProperty(
							"editSiteDeleteConfirmationPopupTitle"),
					"Delete Confirmation box Title is not displayed");
			BaseUtil.assertElementDisplayed(
					getProps().getProperty(
							"editSiteDeleteConfirmationPopupMessage"),
					"Delete Confirmation box Message is not displayed");
			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
					"editSiteDeleteConfirmationPopupMessage"));

		} else if (type.equals("Tenant")) {
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("editTenantDeleteConfirmationPopup"),
					"Delete Confirmation box is not displayed");
			BaseUtil.assertElementDisplayed(
					getProps().getProperty(
							"editTenantDeleteConfirmationPopupTitle"),
					"Delete Confirmation box Title is not displayed");
			BaseUtil.assertElementDisplayed(
					getProps().getProperty(
							"editTenantDeleteConfirmationPopupMessage"),
					"Delete Confirmation box Message is not displayed");
			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
					"editTenantDeleteConfirmationPopupMessage"));

		} else if (type.equals("Endpoint Firmware")) {
			BaseUtil.assertElementDisplayed(
					getProps().getProperty(
							"editEndpointFirmwareDeleteConfirmationPopup"),
					"Delete Confirmation box is not displayed");
			BaseUtil.assertElementDisplayed(
					getProps().getProperty(
							"editEndpointFirmwareDeleteConfirmationPopupTitle"),
					"Delete Confirmation box Title is not displayed");
			BaseUtil.assertElementDisplayed(
					getProps()
							.getProperty(
									"editEndpointFirmwareDeleteConfirmationPopupMessage"),
					"Delete Confirmation box Message is not displayed");
			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
					"editEndpointFirmwareDeleteConfirmationPopupMessage"));

		} else if (type.equals("Peripheral Firmware")) {
			expectedMessage = expectedMessage.replace("#####", testData);
			BaseUtil.assertElementDisplayed(
					getProps().getProperty(
							"editPeripheralFirmwareDeleteConfirmationPopup"),
					"Delete Confirmation box is not displayed");
			BaseUtil.assertElementDisplayed(
					getProps()
							.getProperty(
									"editPeripheralFirmwareDeleteConfirmationPopupTitle"),
					"Delete Confirmation box Title is not displayed");
			BaseUtil.assertElementDisplayed(
					getProps()
							.getProperty(
									"editPeripheralFirmwareDeleteConfirmationPopupMessage"),
					"Delete Confirmation box Message is not displayed");
			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
					"editPeripheralFirmwareDeleteConfirmationPopupMessage"));

		}

		Assert.assertTrue("Confirmation message on UI(" + messageOnUI
				+ ") is not expected(" + expectedMessage + ") message",
				expectedMessage.equals(messageOnUI));
	}

	@Then("^verify user should be navigated to  page with a label saying \"(.*?)\"$")
	public void user_should_be_navigated_to_page_with_a_label_saying(
			String message, String type, String testData) throws Throwable {
		BaseUtil.explicitWait(2000);
		String messageOnUI = null;
		if (type.equalsIgnoreCase("Site")) {
			message = message.replace("#####", testData);
			BaseUtil.waitForSpinner();
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("addSiteSaveMsg"),
					"Message is not visible on the Site page");
			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
					"addSiteSaveMsg"));
		} else if (type.equalsIgnoreCase("Tenant")) {
			message = message.replace("#####", testData);
			BaseUtil.waitForSpinner();
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("addTenantSaveMsg"),
					"Message is not visible on the Tenant page");
			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
					"addTenantSaveMsg"));
		} else if (type.equalsIgnoreCase("Endpoint Firmware")) {
			message = message.replace("#####", testData);
			BaseUtil.waitForSpinner();
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("addEndpointFirmwareSaveMsg"),
					"Message is not visible on the Endpoint Firmware page");
			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
					"addEndpointFirmwareSaveMsg"));
		} else if (type.equalsIgnoreCase("Peripheral Firmware")) {
			message = message.replace("#####", testData);
			BaseUtil.waitForSpinner();
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("addPeripheralFirmwareSaveMsg"),
					"Message is not visible on the Site page");
			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
					"addPeripheralFirmwareSaveMsg"));
		}

		Assert.assertTrue(" Message on UI(" + messageOnUI
				+ ") is not expected(" + message + ") message",
				message.equals(messageOnUI));
	}

	@Then("^enter the test data \"(.*?)\" for the following fields to create new testData for URP$")
	public void enter_the_test_data_for_the_following_fields_to_create_new_Test_Data_for_URP(
			String num, DataTable arg1) throws Throwable {
		List<String> fieldList = arg1.asList(String.class);
		for (String list : fieldList) {
			UserRolePermissionsPage.enterTestData(num, list);
		}
	}

	@Then("^select the following check boxes for URP$")
	public void select_the_following_check_box(List<String> checkBoxList)
			throws Throwable {
		BaseUtil.explicitWait(1000);
		UserRolePermissionsPage.checkCheckBox(checkBoxList);
	}

	@Then("^click \"(.*?)\" button on add_edit site model$")
	public void click_save_button_UserRolePermissions(String buttonName)
			throws Throwable {
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message, UserRolePermissionsPage
				.clickOperationUserRolePermissions(buttonName));
		BaseUtil.waitForSpinner();
	}

	@Then("^click \"(.*?)\" button on address verification model$")
	public void click_address_verification_button_UserRolePermissions(
			String button) throws Throwable {
		Assert.assertTrue(button + " Button is not clicked",
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(button));
	}

	@Then("^user should be navigated on sites page with a label saying \"(.*?)\"$")
	public void user_should_be_navigated_on_sites_page_with_a_label_saying(
			String siteSaveMessage) throws Throwable {
		String message = "Expected message not matched with actual message.";
		Assert.assertTrue(message, siteSaveMessage.equals(BaseUtil
				.getElementText(getProps().getProperty("addSiteSaveMsg"))));
		BaseUtil.waitForSpinner();
	}

	@When("^enter the site name \"(.*?)\" into search field$")
	public void enter_the_root_tenant_in_search_field(String rootSiteName)
			throws Throwable {
		// sitePage.waitForSpinner(1000);
		UserRolePermissionsPage.enterText_URP("SITE_SEARCH", rootSiteName);
	}

	@Then("^select the site \"(.*?)\" from the \"(.*?)\" details grid for URP$")
	public void select_the_site_from_the_details_grid(String sSiteName,
			String gridName) throws Throwable {
		UserRolePermissionsPage.selectResourceFromGrid_URP(sSiteName, gridName);
		BaseUtil.explicitWait(3000);
	}

	@When("^verify \"(.*?)\" page should be open$")
	public void verify_page_should_be_displayed(String popupName)
			throws Throwable {
		BaseUtil.waitForSpinner(1000);
		UserRolePermissionsPage.checkPopupExistence(popupName, "");
	}

	@Then("^click on \"(.*?)\" accordion of site$")
	public void click_onaccordion(String arg1) throws Throwable {
		UserRolePermissionsPage.clickOperationUserRolePermissions(arg1);

		// TODO : check the below code
		BaseUtil.explicitWait(2000);
		if (BaseUtil.instantElementCheck(PathConstants.TYPE_DISPLAY, getProps()
				.getProperty("channelAccordianAddVirtualChannelButton"))) {
			return;
		} else {
			SitePage sitePage = new SitePage();
			sitePage.clickOperationOnSitePage(arg1);
		}
	}

	@Then("^verify \"(.*?)\" text box and \"(.*?)\" button is avilable on edit site model$")
	public void verify_site_path_on_edit_site(String textBox,
			String addSiteButton) throws Throwable {
		String message1 = "[" + textBox + "] text box is not avilable.";
		Assert.assertTrue(message1, BaseUtil.isElementLoaded((BaseUtil
				.getLocator(getProps().getProperty(
						"editSitePagePathsAccordianPathTextField")))));
		String message2 = "Failed to click on [" + addSiteButton
				+ "] to search GPSupport";
		Assert.assertTrue(message2, BaseUtil.isElementLoaded((BaseUtil
				.getLocator(getProps().getProperty("addSiteAddPathsButton")))));
	}

	@Then("^click in the \"(.*?)\" text box$")
	public void click_inthe_search_site_textbox_UserRolePermissions(
			String button) throws Throwable {
		BaseUtil.explicitWait(1000);
		Assert.assertTrue(button + " Button is not clicked",
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(button));
	}

	@Then("^updates the site path values \"(.*?)\" on edit site model$")
	public void verify_the_update_site(String sitePath) throws Throwable {
		BaseUtil.enterText(
				getProps().getProperty(
						"editSitePagePathsAccordianPathTextField"), sitePath);
		BaseUtil.explicitWait(3000);
	}

	@Then("^verify updated site path is \"(.*?)\" on edit site model$")
	public void verify_updated_site_path(String updateSitePath)
			throws Throwable {
		Assert.assertTrue(
				"Update Site Path is not equal",
				BaseUtil.getElementAttribute(
						getProps().getProperty(
								"editSitePagePathsAccordianPathTextField"),
						"value").equalsIgnoreCase(updateSitePath));
		BaseUtil.explicitWait(3000);

	}

	@When("^search virtual channel \"(.*?)\" before add by \"(.*?)\"$")
	public void search_virtual_channel_before_add_by(String virtualChannelName,
			String role) throws Throwable {
		UserRolePermissionsPage.enterText_URP("SEARCH_CHANNEL_NAME",
				virtualChannelName);
		if (role.equalsIgnoreCase("GPAdmin")) {
			if (BaseUtil.instantElementCheck("Display",
					getProps().getProperty("viewVirtualChannelBtn"))) {
				BaseUtil.click(getProps().getProperty("viewVirtualChannelBtn"));
				BaseUtil.click(getProps()
						.getProperty("removeVirtualChannelBtn"));
				BaseUtil.click(getProps().getProperty(
						"removeVirtualChannelOKBtn"));
				BaseUtil.waitForSpinner();
			} else {
				return;
			}
		} else if (role.equalsIgnoreCase("GPSupport")) {
			BaseUtil.explicitWait(1000);
			UserRolePermissionsPage.enterText_URP("SEARCH_CHANNEL_NAME",
					virtualChannelName);
			if (BaseUtil.instantElementCheck(PathConstants.TYPE_DISPLAY,
					getProps().getProperty("virtualChannelNoRecords"))) {
				return;
			} else {
				ManageTestData objDelTestdata = new ManageTestData();
				objDelTestdata.manageTestData("deleteVirtualChannel",
						virtualChannelName);
			}
		}
	}

	@Then("^click \"(.*?)\" button$")
	public void click_button_userRolePermissions(String button)
			throws Throwable {
		BaseUtil.waitForSpinner();
		Assert.assertTrue(button + " Button is not clicked",
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(button));
	}

	@Then("^select \"(.*?)\" from type selection box on add virtual channel popup$")
	public void select_endpoint_UOM(String UOM) throws IOException,
			SQLException {
		BaseUtil.explicitWait(2000);
		// Assert.assertTrue("Unit of Measure(UOM) is not display",
		// UserRolePermissionsPage
		// .verifyElementDisplayed(getProps().getProperty("addVirtualChannelSelectUOM")));
		// BaseUtil.explicitWait(1000);
		
		// TODO : Below step added by k 18 may 2018 20:34..temporary commenting to avoid compilation issues..verify...
//		userRolePermissionsPage.selectDropDownByValue(userRolePermissionsPageObjectRepo.get("addVirtualChannelSelectUOM"), UOM);
		
		BaseUtil.waitForSpinner();
		BaseUtil.explicitWait(2000);
		BaseUtil.selectDropDownByValue(
				getProps().getProperty("addVirtualChannelSelectUOM"), UOM);
	}

	@Then("^click \"(.*?)\" buttons of available channels$")
	public void click_add_button_for_ch1(String ch1) throws Throwable {
		Assert.assertTrue(ch1 + " Button is not clicked",
				UserRolePermissionsPage.clickOperationUserRolePermissions(ch1));
	}

	@When("^enter the virtual channel name \"(.*?)\" into name field$")
	public void enter_the_channel_name_field(String channelName)
			throws Throwable {
		UserRolePermissionsPage.enterText_URP("CHANNEL_NAME", channelName);
	}

	@Then("^select measure type \"(\\d+)\" on add virtual channel popup$")
	public void select_measure_type(int testData) throws IOException,
			SQLException {
		BaseUtil.selectDropDownByIndex(
				getProps().getProperty("addVirtualChannelSelectMeasureType"),
				testData);
	}

	@Then("^select metadata category on edit virtual channel popup$")
	public void select_metadata_category_on_edit_virtual_channel_popup()
			throws IOException, SQLException, InterruptedException {
		Thread.sleep(3000);
		List<WebElement> wel = BaseUtil.getDropDownValues(getProps()
				.getProperty("addVirtualChannelSelectMetadataCategory"));
		wel.get(0).click();
	}

	@Then("^click \"(.*?)\" button on add virtual channel model$")
	public void click_save_button_on_add_VC(String buttonName) throws Throwable {
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message, UserRolePermissionsPage
				.clickOperationUserRolePermissions(buttonName));
		BaseUtil.explicitWait(4000);
	}

	@Then("^verify \"(.*?)\" message popup window is displayed$")
	public void error_window_shown_duplicat_vc(String message) throws Throwable {
		Assert.assertTrue(
				"Channel already exists with name is not shown",
				BaseUtil.getElementText(
						getProps().getProperty("addVirtualChannelerrorPopup"))
						.equalsIgnoreCase(
								"Please correct the following errors:"));
	}

	@Then("^click \"(.*?)\" button of virtual channel$")
	public void click_edit_button_for_vch1(String ch1) throws Throwable {
		Assert.assertTrue(ch1 + " Button is not clicked",
				UserRolePermissionsPage.clickOperationUserRolePermissions(ch1));
		BaseUtil.waitForSpinner();
	}

	@When("^update the virtual channel name \"(.*?)\"$")
	public void update_the_virtual_channel_name(String virtualChannelName)
			throws Throwable {
		UserRolePermissionsPage.enterText_URP("UPDATE_CHANNEL_NAME",
				virtualChannelName);
	}

	@Then("^click \"(.*?)\" button on tenant tab$")
	public void click_button_on_TenantTab_userRolePermissions(String button)
			throws Throwable {
		BaseUtil.waitForSpinner();
		Assert.assertTrue(button + " Button is not clicked",
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(button));
	}

	@Then("^enter the \"(.*?)\" in \"(.*?)\" textbox on add tenant model$")
	public void inter_the_text_field_on_add_tenant_popup(String tenantName,
			String fieldName) throws IOException, SQLException {
		BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"),
				tenantName);

	}

	@Then("^click \"(.*?)\" button on add tenant popup$")
	public void click_button_on_addTenant_userRolePermissions(String button)
			throws Throwable {
		BaseUtil.waitForSpinner();
		Assert.assertTrue(button + " Button is not clicked",
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(button));
	}

	@When("^enter the tenant name \"(.*?)\" in the search field$")
	public void enter_the_tenant_in_search_field(String sRootTenantName)
			throws Throwable {
		String message2 = "Failed to enter the value in the [Search] field in tenant detail grid.";
		Assert.assertTrue(message2, UserRolePermissionsPage.enterText_URP(
				"TENANT_SEARCH", sRootTenantName));
	}

	@Then("^select the tenant \"(.*?)\" from \"(.*?)\" details grid for URP$")
	public void select_root_tenant_from_the_tenant_details_grid(
			String sTenantName, String gridName) throws Throwable {
		UserRolePermissionsPage.selectResourceFromGrid_URP(sTenantName,
				gridName);
	}

	@Then("^update the tenant name \"(.*?)\" on tenant detail model$")
	public void update_tenant_detail_URP(String TeanantName)
			throws IOException, SQLException {
		BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"),
				TeanantName);
	}

	@Then("^click \"(.*?)\" button on edit tenant popup$")
	public void click_cross_icon(String button) throws Throwable {
		Assert.assertTrue(button + "is not clicked", UserRolePermissionsPage
				.clickOperationUserRolePermissions(button));
	}

	@Then("^verify the tenant \"(.*?)\" is \"(.*?)\" on tenant detail model$")
	public void verify_update_tenant_detail_URP(String fieldName,
			String TenantName) throws IOException, SQLException {
		Assert.assertTrue(
				"Tenant Name is not update",
				BaseUtil.getElementAttribute(
						getProps().getProperty("addTenantPopupNameField"),
						"value").equalsIgnoreCase(TenantName));
	}

	@Then("^click \"(.*?)\" button for delete confirmation$")
	public void click_ok_URP(String button) throws Throwable {
		Assert.assertTrue(button + " Button is not clicked",
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(button));
	}

	@When("^enter the tenant name \"(.*?)\" in the search field and verify \"(.*?)\"$")
	public void enter_tenant_in_search_field(String sRootTenantName, String msg)
			throws Throwable {
		String message = "Failed to enter the value in the [Search] field in tenant detail grid.";
		Assert.assertTrue(message, UserRolePermissionsPage.enterText_URP(
				"TENANT_SEARCH", sRootTenantName));

		String message2 = "Tenant" + sRootTenantName + "is not deleted yet";
		Assert.assertTrue(
				message2,
				BaseUtil.getElementText(
						getProps().getProperty("tenantGridZeroRecord"))
						.equalsIgnoreCase(msg));
	}

	@Then("^click the \"(.*?)\" tab$")
	public void click_on_the_tab(String endpointsTab) throws Throwable {
		BaseUtil.assertElementDisplayed(getProps().getProperty("endpointTab"),
				"Failed to verify the endpoint tab availability");
		String message = "Failed to click tab:" + endpointsTab;
		Assert.assertTrue(message, UserRolePermissionsPage
				.clickOperationUserRolePermissions(endpointsTab));
	}

	@Then("^click \"(.*?)\" button on endpoint page$")
	public void click_search_button_on_endpoint_page(String buttonName)
			throws Throwable {
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message, UserRolePermissionsPage
				.clickOperationUserRolePermissions(buttonName));
		BaseUtil.waitForSpinner();
	}

	@Then("^enter the \"(.*?)\" in \"(.*?)\" field on add endpoint model$")
	public void inter_the_text_in_field_on_add_tenant_popup(
			String EndpointName, String fieldName) throws IOException,
			SQLException {
		BaseUtil.enterText(getProps()
				.getProperty("addEndpointPopupSerialField"), EndpointName);
	}

	@Then("^select \"(.*?)\" from type selection box on \"(.*?)\" enpoint popup$")
	public void select_endpoint_type(String endpointType, String page)
			throws IOException, SQLException {
		if (page.equalsIgnoreCase("ADD")) {
			BaseUtil.selectDropDownByValue(
					getProps().getProperty("addEndpointTypeDropDown"),
					endpointType);
		} else if (page.equalsIgnoreCase("EDIT"))
			BaseUtil.selectDropDownByValue(
					getProps().getProperty("endpointDetailsPageTypeValue"),
					endpointType);
	}

	@When("^click the \"(.*?)\" button on the add endpoint popup$")
	public void click_on_button_on_add_endpoint_popup(String buttonName)
			throws Throwable {
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message, UserRolePermissionsPage
				.clickOperationUserRolePermissions(buttonName));
	}

	@Then("^select the entered endpoint id \"(.*?)\" from the \"(.*?)\" details grid page$")
	public void select_the_entered_endpoint_id_from_the_endpoint_details_grid(
			String sEndpointName, String gridName) {
		UserRolePermissionsPage.selectResourceFromGrid_URP(sEndpointName,
				gridName);
		// endpointPage.waitForSpinner();
		UserRolePermissionsPage.checkAlert();
		// BaseUtil.explicitWait(2000);
		
		// TODO : added by k on 9 Map 2018 9:32 AM. currently comenting to avoid compilation issues..verify
//		TestBase.isAlertPresentClickOk();
//		userRolePermissionsPage.explicitWait(4000);
//		TestBase.isAlertPresentClickOk();
	}

	@Then("^verify the endpoint name \"(.*?)\" and type \"(.*?)\" is on edit endpoint model$")
	public void verify_endpoint_name_on_the_endpoint_details_grid(
			String sEndpointName, String EndpointType) {
		Assert.assertTrue(
				"Endpoint Name is not same as given on Add Endpoint",
				BaseUtil.getElementText(
						getProps().getProperty("editEndpointSerial"))
						.equalsIgnoreCase(sEndpointName));
		Assert.assertTrue(
				"Endpoint type is not same as given on Add Endpoint",
				BaseUtil.getElementText(
						getProps().getProperty("endpointDetailsPageTypeValue"))
						.equalsIgnoreCase(EndpointType));
	}

	@When("^click the \"(.*?)\" button on the edit endpoint popup$")
	public void click_on_button_on_edit_endpoint_popup(String buttonName)
			throws Throwable {
		String message = "Failed to click " + buttonName
				+ "button on Edit Endpoint model:";
		Assert.assertTrue(message, UserRolePermissionsPage
				.clickOperationUserRolePermissions(buttonName));
	}

	@Then("^click \"(.*?)\" button on endpoint firmware page$")
	public void click_add_button_on_add_EPFW_page(String buttonName)
			throws Throwable {
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("addEndpointFirmwareButton"),
				"Failed to verify endpointfirmware grid availability.");
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message, UserRolePermissionsPage
				.clickOperationUserRolePermissions(buttonName));
	}

	@Then("^click on the \"(.*?)\" button on add \"(.*?)\" popup and select \"(.*?)\" file for URP$")
	public void click_on_the_button_on_add_endpoint_firmware_popup_and_select_an_invalid_file(
			String buttonName, String tabName, String fileType)
			throws Throwable {
		String firmwareFile = null, filePath = null, absolutePath = null;
		File f = null;
		if (tabName.equals("Endpoint Firmware")) {
			switch (fileType.toUpperCase()) {
			case "VALID":
				firmwareFile = "live_firmware.tar.gz";
				f = new File(firmwareFile);
				absolutePath = f.getAbsolutePath();
				// TODO : updated by k as \\ replaced with / on 8 may 2018
				filePath = absolutePath.substring(0,
						absolutePath.lastIndexOf(File.separator))
						+ "/downloadDir/" + firmwareFile;
				break;
			case "INVALID":
				firmwareFile = "InvalidPeripheralFirmwareFile.xlsx";
				f = new File(firmwareFile);
				absolutePath = f.getAbsolutePath();
				// TODO : updated by k as \\ replaced with / on 8 may 2018
				filePath = absolutePath.substring(0,
						absolutePath.lastIndexOf(File.separator))
						+ "/downloadDir/" + firmwareFile;
				break;
			}
		} else if (tabName.equals("Peripheral Firmware")) {
			switch (fileType.toUpperCase()) {
			case "VALID":
				firmwareFile = "TS102_400.bin";
				f = new File(firmwareFile);
				absolutePath = f.getAbsolutePath();
				// TODO : updated by k as \\ replaced with / on 8 may 2018
				filePath = absolutePath.substring(0,
						absolutePath.lastIndexOf(File.separator))
						+ "/downloadDir/" + firmwareFile;
				break;
			case "INVALID":
				firmwareFile = "InvalidPeripheralFirmwareFile.xlsx";
				f = new File(firmwareFile);
				absolutePath = f.getAbsolutePath();
				// TODO : updated by k as \\ replaced with / on 8 may 2018
				filePath = absolutePath.substring(0,
						absolutePath.lastIndexOf(File.separator))
						+ "/downloadDir/" + firmwareFile;
				break;
			}
		}
		String message = "Failed to click on button :" + buttonName + " "
				+ " on add endpoint firmware popup.";
		Assert.assertTrue(message, UserRolePermissionsPage
				.clickChooseButtonAndSelectFile(buttonName, tabName, fileType,
						filePath));
	}

	@Then("^enter \"(.*?)\" in \"(.*?)\" field on add endpointfriamware model$")
	public void inter_the_text_in_field_on_add_endpointFW_popup(
			String EndpointFWName, String fieldName) throws IOException,
			SQLException {
		BaseUtil.enterText(
				getProps().getProperty("editEndpointFirmwareNameField"),
				EndpointFWName);
	}

	@Then("^enter the test data \"(.*?)\" for the following fields to create new firmware for URP$")
	public void enter_the_test_data_for_the_following_fields_to_create_new_firmware_for_URP(
			String num, DataTable arg1) throws Throwable {
		List<String> fieldList = arg1.asList(String.class);
		for (String list : fieldList) {
			UserRolePermissionsPage.enterTestData(num, list);
		}
	}

	@When("^click the \"(.*?)\" button on the add endpointfirware popup$")
	public void click_on_button_on_add_endpointfirware_popup(String buttonName)
			throws Throwable {
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message, UserRolePermissionsPage
				.clickOperationUserRolePermissions(buttonName));
		BaseUtil.waitForSpinner();
	}

	@When("^enter firmware name \"(.*?)\" in \"(.*?)\" search field$")
	public void enter_site_name_in_endpoint_firmware_search_field(
			String endpointFirmwareName, String subTabName) throws Throwable {
		UserRolePermissionsPage.enterText_URP(subTabName, endpointFirmwareName);
	}

	@Then("^click on \"(.*?)\" button on Endpointfirmware page$")
	public void click_on_button_on_Endpoint_firmware_page(
			String endpointFirmwareButtonName) throws Throwable {
		String message = "Failed to click on button :"
				+ endpointFirmwareButtonName + " "
				+ " on endpoint firmware detail grid.";
		Assert.assertTrue(message, UserRolePermissionsPage
				.clickOperationUserRolePermissions(endpointFirmwareButtonName));
	}

	@Then("^select the endpoint firmware \"(.*?)\" from the \"(.*?)\" details grid for URP$")
	public void select_the_endpoint_firmware_from_the_endpoint_firmware_details_grid(
			String endpointFirmwareName, String gridName) throws Throwable {
		UserRolePermissionsPage.selectResourceFromGrid_URP(
				endpointFirmwareName, gridName);
	}

	@Then("^verify the endpoint firmware name \"(.*?)\" on edit endpoint firmware model$")
	public void verify_endpointfw_on_the_endpointfw_details_grid(
			String sEndpointFWName) {
		Assert.assertTrue(
				"Endpoint Firmware Name is not same as given on Add Endpoint Firmware",
				BaseUtil.getElementAttribute(
						getProps().getProperty("editEndpointFirmwareNameField"),
						"value").equalsIgnoreCase(sEndpointFWName));
	}

	@Then("^update the endpoint firmware name \"(.*?)\" on edit enpoint firmware detail model$")
	public void update_endpointFW_detail_URP(String endpointFWName)
			throws IOException, SQLException {
		BaseUtil.enterText(
				getProps().getProperty("editEndpointFirmwareNameField"),
				endpointFWName);
	}

	@When("^click the \"(.*?)\" button on the edit endpointfirware popup$")
	public void click_on_button_on_edit_endpointfirware_popup(String buttonName)
			throws Throwable {
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message, UserRolePermissionsPage
				.clickOperationUserRolePermissions(buttonName));
	}

	@When("^enter endpoint friamware name \"(.*?)\" in the search field and verify \"(.*?)\"$")
	public void enter_endpointFW_in_search_field(String sRootendpointFWName,
			String arg2) throws Throwable {
		String message = "Endpoint Friamware is not deleted.";
		Assert.assertTrue(
				message,
				BaseUtil.getElementText(
						getProps().getProperty("endpointFirmwareZeroRecord"))
						.equals(arg2));
	}

	@Then("^verify the avilable sub tabs of \"(.*?)\" tab for role \"(.*?)\" users$")
	public void verify_below_are_the_default_avilable_sub_tabs_for_role_user(
			String subTabName, String userRole, DataTable table)
			throws Throwable {
		List<String> tabList = table.asList(String.class);
		for (String tabName : tabList) {
			String message = "Failed to verify tab" + tabName;
			Assert.assertTrue(message,
					UserRolePermissionsPage.verifyElementsURP(tabName));
		}
	}

	@Then("^click on the \"(.*?)\" tab of adminui$")
	public void click_on_the_FWTab(String firmwarTab) throws Throwable {
		BaseUtil.assertElementDisplayed(getProps().getProperty("FirmwareTab"),
				"Failed to verify the endpoint grid availability");
		String message = "Failed to click tab:" + firmwarTab;
		Assert.assertTrue(message, UserRolePermissionsPage
				.clickOperationUserRolePermissions(firmwarTab));
	}

	@Then("^select \"(.*?)\" checkbox on update endpoint sub tab$")
	public void click_on_update_endpoint_subtab(String checkBox)
			throws Throwable {
		UserRolePermissionsPage.checkCheckBox(Arrays.asList(checkBox));
	}

	@When("^verify the \"(.*?)\" button on \"(.*?)\" tab for role \"(.*?)\"$")
	public void verify_button_(String buttonName, String tabName,
			String userRole) throws Throwable {
		if (userRole.equalsIgnoreCase("GPADMIN")
				&& (userRole.equalsIgnoreCase("GPSUPPORT")))
			Assert.assertTrue(
					"" + buttonName + ": is not showing for role:" + userRole
							+ " ",
					BaseUtil.verifyElementDisplayed(getProps().getProperty(
							"updateFirmwareBtn")));
	}

	@Then("^click \"(.*?)\" button on update endpoint firmware$")
	public void click_on_expend_button_userRolePermissions(String button)
			throws Throwable {
		BaseUtil.explicitWait(3000);
		Assert.assertTrue(button + " Button is not clicked",
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(button));

	}

	@Then("^verify \"(.*?)\" popup is open and below objects are available on this popup$")
	public void verify_the_objectson(String popupName, DataTable table)
			throws Throwable {
		List<String> objectList = table.asList(String.class);
		for (String objectName : objectList) {
			String message = "Failed to verify " + objectList + "on popup"
					+ popupName + "";
			Assert.assertTrue(message, UserRolePermissionsPage
					.verifyUpdateEndpointFirmwareObjects(objectName));
		}
	}

	@Then("^click \"(.*?)\" button on peripheral firmware page$")
	public void click_add_button_on_add_PFW_page(String buttonName)
			throws Throwable {
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("addPeripheralFirmwareButton"),
				"Failed to verify peripheralfirmware grid availability.");
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message, UserRolePermissionsPage
				.clickOperationUserRolePermissions(buttonName));
	}

	@Then("^click on the \"(.*?)\" button on add peripheralfirmware popup$")
	public void click_on_the_button_on_add_peripheral_firmware_popup(String arg1)
			throws Throwable {
		String message = "Failed to click on button :" + arg1 + " "
				+ " on add peripheralfirmware popup .";
		Assert.assertTrue(message,
				UserRolePermissionsPage.clickOperationUserRolePermissions(arg1));
	}

	@When("^enter controller min version \"(.*?)\" in peripheralfirmware search field$")
	public void enter_controller_min_version_in_peripheral_firmware_search_field(
			String version) throws Throwable {
		UserRolePermissionsPage.enterText_URP("PERIPHERALFIRMWARE_SEARCH",
				version);
	}

	@Then("^click on \"(.*?)\" button on Peripheralfirmware page$")
	public void click_on_button_on_Peripheral_firmware_page(
			String peripheralFirmwarepagebutton) throws Throwable {
		String message = "Failed to click on button :"
				+ peripheralFirmwarepagebutton + " "
				+ " on peripheral firmware detail grid.";
		Assert.assertTrue(
				message,
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(peripheralFirmwarepagebutton));
	}

	@Then("^select the peripheral firmware \"(.*?)\" from the \"(.*?)\" details grid$")
	public void select_the_peripheral_firmware_from_the_peripheral_firmware_details_grid(
			String peripheralFirmwareName, String gridName) throws Throwable {
		UserRolePermissionsPage.selectResourceFromGrid_URP(
				peripheralFirmwareName, gridName);
	}

	@Then("^verify the details of the selected firmware in following elements on firmware detail popup for URP$")
	public void verify_the_details_of_the_selected_firmware_in_following_elements_on_firmware_detail_popup(
			List<String> listHeaderNames) throws Throwable {
		for (String headerName : listHeaderNames) {
			UserRolePermissionsPage.verifyElementsURP(headerName);
		}
	}

	@When("^click on \"(.*?)\" button of selected peripheral firmware$")
	public void click_on_button_on_peripheral_firmware_detail_popup(
			String peripheralFirmwareButton) throws Throwable {
		String message = "Failed to click on button :"
				+ peripheralFirmwareButton + " "
				+ " on peripheral Firmware detail grid.";
		Assert.assertTrue(message, UserRolePermissionsPage
				.clickOperationUserRolePermissions(peripheralFirmwareButton));
	}

	@Then("^click on the portal button which navigate to HTML Portal$")
	public void click_on_portal_button_which_navigate_to_HTML_Portal()
			throws Throwable {
		BaseUtil.waitForSpinner();
		if (!BaseUtil
				.checkIfClickable(getProps().getProperty("optionDropDown"))) {
			Assert.fail("Not able to click on portal navigator.");
		}
		BaseUtil.click(getProps().getProperty("optionDropDown"));
		BaseUtil.click(getProps().getProperty("PortalNavigate"));

		String message = "Error while navigating to Portal";
		BaseUtil.assertElementDisplayed(getProps().getProperty("Home"), message);
		BaseUtil.waitForSpinner();
	}

	@Then("^click site tree \"(.*?)\" button$")
	public void click_on_site_tree_clear_button_userRolePermissions(
			String button) throws Throwable {
		Assert.assertTrue(button + " Button is not clicked",
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(button));
	}

	@Then("^click the \"(.*?)\" tab in the portal$")
	public void click_on_HVAC_tab_UserRolePermissions(String button)
			throws Throwable {
		BaseUtil.explicitWait(2000);
		Assert.assertTrue(button + " Button is not clicked",
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(button));
	}

	@When("^enter the \"(.*?)\" site name in the site search box and select it to check \"(.*?)\" capability$")
	public void enter_the_site_name_in_search_field(String siteType,
			String tabName) throws Throwable {
		Assert.assertTrue("Entered site is not selected.",
				UserRolePermissionsPage.isSiteSelected(siteType, tabName));
	}

	@Then("^click on \"(.*?)\"$")
	public void click_Operation_UserRolePermissions(String button)
			throws Throwable {
		BaseUtil.explicitWait(1000);
		Assert.assertTrue(button + " Button is not clicked",
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(button));
		BaseUtil.waitForSpinner();
	}

	@Then("^verify following buttons are enabled$")
	public void verify_following_button_are_enabled_on_URP_models(
			DataTable table) throws Throwable {
		List<String> lstData = table.asList(String.class);
		table.asList(String.class);
		for (String button : lstData) {
			String message = button + " should  be enabled";
			Assert.assertTrue(message,
					UserRolePermissionsPage.isElementEnabledOnURPPage(button));
		}
	}

	@Then("^click the \"(.*?)\" button and verify is device \"(.*?)\" model open successfully$")
	public void click_the_button_and_verify_is_device_model_open_successfully(
			String arg1, String deviceName) throws Throwable {
		BaseUtil.click(getProps().getProperty("editHVACSchedulebtn"));
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("editHVACModel"),
				"Edit HVAC model is not open for " + deviceName + "");
	}

	@Then("^verify \"(.*?)\" button should be disabled and \"(.*?)\" button should be enabled$")
	public void verify_button_should_be_disabled_and_button_should_be_enabled(
			String savebutton, String cancelButton) throws Throwable {
		Assert.assertFalse(
				"" + savebutton + " is enabled",
				BaseUtil.verifyElementEnabled(getProps().getProperty(
						"editHVACSavebtn")));
		Assert.assertTrue(
				"" + cancelButton + " is disabled",
				BaseUtil.verifyElementEnabled(getProps().getProperty(
						"editHVACCanceltn")));
	}

	@Then("^click the \"(.*?)\" button of edit hvac$")
	public void click_the_cancel_button_of_HVAC(String button) throws Throwable {
		BaseUtil.explicitWait(1000);
		Assert.assertTrue(button + " Button is not clicked",
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(button));
	}

	@Then("^verify following reports are display$")
	public void verify_following_report_are_available_on_URP_Report(
			DataTable table) throws Throwable {
		List<String> lstData = table.asList(String.class);
		table.asList(String.class);
		for (String reportName : lstData) {
			String message = reportName + " is not displayed";
			Assert.assertTrue(message,
					UserRolePermissionsPage.verifyElementsURP(reportName));
		}
	}

	@Then("^click in the \"(.*?)\" text box in the portal$")
	public void click_inthe_portal_search_site_textbox_UserRolePermissions(
			String button) throws Throwable {
		BaseUtil.explicitWait(1000);
		Assert.assertTrue(button + " Button is not clicked",
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(button));
	}

	@When("^verify the \"(.*?)\" button on \"(.*?)\" tab$")
	public void verify_button(String buttonName, String tabName)
			throws Throwable {
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("editRegularHoursbtn"),
				" Edit button is not avilable on[" + tabName + "] tab");
	}

	@Given("^click on the admin button which navigate to Admin UI$")
	public void click_on_Admin_button_which_navigate_to_Admin_UI()
			throws Throwable {
		BaseUtil.waitForSpinner();
		if (!BaseUtil
				.checkIfClickable(getProps().getProperty("optionDropDown"))) {
			Assert.fail("Not able to click on Admin navigator.");
		}
		BaseUtil.click(getProps().getProperty("optionDropDown"));
		BaseUtil.click(getProps().getProperty("adminNavigate"));
		BaseUtil.waitForSpinner(10000);
		String message = "Error while navigating to Admin UI";
		BaseUtil.assertElementDisplayed(getProps().getProperty("userTab"),
				message);

	}

	@Then("^click \"(.*?)\" button on edit user tab$")
	public void click_on_user_tabn_userRolePermissions(String button)
			throws Throwable {
		BaseUtil.waitForSpinner();
		Assert.assertTrue(button + " Button is not clicked",
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(button));
	}

	@Then("^click the \"(.*?)\" icon of site tree in the portal$")
	public void click_sitree_icon_UserRolePermissions(String button)
			throws Throwable {
		BaseUtil.explicitWait(1000);
		Assert.assertTrue(button + " Button is not clicked",
				UserRolePermissionsPage
						.clickOperationUserRolePermissions(button));
	}

	@When("^verify the \"(.*?)\" button should not be available on \"(.*?)\" tab$")
	public void verify_edit_button_should_not_URP(String buttonName,
			String tabName) throws Throwable {
		Assert.assertFalse("Expected: " + buttonName
				+ " button should not available on" + tabName + "",
				UserRolePermissionsPage
						.instantElementCheckOnControlTab(buttonName));
	}

	@Then("^verify following buttons should not be available on \"(.*?)\" tab$")
	public void verify_following_button_are_enabled_on_URP_models(String tab,
			DataTable table) throws Throwable {
		List<String> lstData = table.asList(String.class);
		table.asList(String.class);
		for (String button : lstData) {
			String message = button + "should not be available on " + tab + "";
			Assert.assertFalse(message, UserRolePermissionsPage
					.instantElementCheckOnControlTab(button));
		}
	}

	@Then("^enter \"(.*?)\" in \"(.*?)\" notes text area$")
	public void enter_text_in_hvac_text_area(String textData, String key)
			throws Throwable {
		UserRolePermissionsPage.enterText_URP(key, textData);
	}

	@When("^enter the endpoint name \"(.*?)\" in the search field$")
	public void enter_the_endpoint_in_search_field(String EndpointName)
			throws Throwable {
		UserRolePermissionsPage.enterText_URP("ENDPOINT_SEARCH", EndpointName);
		BaseUtil.waitForSpinner();
	}

	@When("^login with user \"(.*?)\" in portal ui$")
	public void we_will_login_with_credential_and_verify_home_tab(
			String userName) throws Throwable {
		UserRolePermissionsPage.isHomePageAvailable(userName);
		BaseUtil.waitForSpinner();
	}

	@When("^enter the virtual channel name \"(.*?)\" into search field$")
	public void enter_the_virtual_channel_name_in_search_field(
			String virtualChannelName) throws Throwable {
		UserRolePermissionsPage.enterText_URP("SEARCH_CHANNEL_NAME",
				virtualChannelName);
		Assert.assertTrue(
				"Search channel is not found:",
				BaseUtil.instantElementCheck("Display",
						getProps().getProperty("editVirtualChannelBtn")));
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// @Given("^Initialize resources in the beginning of each scenario for userRolePermissions Page$")
	// public void
	// Initialize_resources_in_the_beginning_of_each_scenario_for_userRolePermissions_Page()
	// throws Throwable {
	// TestBase.getInstance();
	// TestBase.initializeGlobalVariables();
	//
	// sitePageObjectRepo =
	// TestBase.getReadResources().getValuesFromXml("SitePage.xml",
	// "siteObjectRepository");
	// userPageObjectRepo =
	// TestBase.getReadResources().getValuesFromXml("UserPage.xml",
	// "userObjectRepository");
	// tenantPageObjectRepo =
	// TestBase.getReadResources().getValuesFromXml("TenantPage.xml",
	// "tenantObjectRepository");
	// endpointPageObjectRepo =
	// TestBase.getReadResources().getValuesFromXml("EndpointPage.xml",
	// "endpointObjectRepository");
	// getProps().getProperty =
	// TestBase.getReadResources().getValuesFromXml("UserRolePermissions.xml",
	// "userRolePermissionsObjectRepository");
	// endpointFirmwarePageObjectRepo =
	// TestBase.getReadResources().getValuesFromXml("EndpointFirmwarePage.xml",
	// "endpointFirmwareObjectRepository");
	// peripheralFirmwarePageObjectRepo =
	// TestBase.getReadResources().getValuesFromXml("PeripheralFirmwarePage.xml",
	// "peripheralfirmwareObjectRepository");
	//
	// userRolePermissionsPageTestData =
	// TestBase.getReadResources().getValuesFromXml("UserRolePermissions.xml",
	// "userRolePermissionsObjectTestData");
	// sitePageObjectTestData =
	// TestBase.getReadResources().getValuesFromXml("SitePage.xml",
	// "siteObjectTestData");
	// userPageObjectTestData =
	// TestBase.getReadResources().getValuesFromXml("UserPage.xml",
	// "userObjectTestData");
	// tenantPageObjectTestData =
	// TestBase.getReadResources().getValuesFromXml("TenantPage.xml",
	// "tenantPageObjectTestData");
	// endpointPageObjectTestData =
	// TestBase.getReadResources().getValuesFromXml("EndpointPage.xml",
	// "endpointObjectTestData");
	// endpointFirmwarePageObjectTestData =
	// TestBase.getReadResources().getValuesFromXml("EndpointFirmwarePage.xml",
	// "endpointFirmwarePageObjectTestData");
	// peripheralFirmwarePageObjectTestData = TestBase.getReadResources()
	// .getValuesFromXml("PeripheralFirmwarePage.xml",
	// "peripheralfirmwareObjectTestData");
	// sitePageObjectText =
	// TestBase.getReadResources().getValuesFromXml("SitePage.xml",
	// "siteObjectText");
	// tenantPageObjectText =
	// TestBase.getReadResources().getValuesFromXml("TenantPage.xml",
	// "tenantObjectText");
	//
	// String envUrl = System.getProperty("url");
	// if (envUrl == null || envUrl.trim().isEmpty()) {
	// envUrl =
	// TestBase.getReadEnvironmentUrls().get(getProps().getProperty("env"));
	// }
	// envUrl=envUrl+"/login";
	// TestBase.getWebDriver().get(envUrl);
	// ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>()
	// {
	// public Boolean apply(WebDriver driver) {
	// return ((JavascriptExecutor)
	// driver).executeScript("return document.readyState").equals("complete");
	// }
	// };
	// TestBase.getWebDriverWait().until(expectation);
	// }
	//
	// @When("^create dummy test data and assocated with dummy users$")
	// public void
	// create_dummy_test_data_and_assocated_with_dummy_users(List<String>
	// lstData) throws Throwable {
	// ManageTestData objDelTestdata = new ManageTestData();
	// for (String data : lstData) {
	// Assert.assertTrue("Error occured while inserting userRolePermission page test data",
	// objDelTestdata.manageTestData("userRolePermission_Insert1", data));
	// }
	// }
	
	// TODO : above code updated as given beow in k branch by sonu on 11 sep 2018.... curretly commenting it to avoid compilation issues....verify
//	@When("^create dummy test data and associated with dummy users$")
//	public void create_dummy_test_data_and_associated_with_dummy_users(List<String> lstData) throws Throwable {
//		DeleteTestData objDelTestdata = new DeleteTestData();
//		for (String data : lstData) {
//			Assert.assertTrue("Error occured while inserting userRolePermission page test data",
//					objDelTestdata.deleteTestData("userRolePermission_Insert1", data));
//		}
//	}
	////////////////////////////////////////////////
	//
	// @When("^update test users$")
	// public void update_dummy_test_users(List<String> lstData) throws
	// Throwable {
	// ManageTestData objDelTestdata = new ManageTestData();
	// for (String data : lstData) {
	// Assert.assertTrue("Error occured while updating userRolePermission page test data",
	// objDelTestdata.manageTestData("userRolePermission_Insert", data));
	// }
	// }
	//
	// @Then("^delete the \"(.*?)\" test data of adminui$")
	// public void delete_the_site_used_as_test_data_for_endpoint(String
	// testData, List<String> listSiteTestData)
	// throws Throwable {
	// ManageTestData objDelTestdata = new ManageTestData();
	// if (testData.equalsIgnoreCase("Endpoints")) {
	// for (String endpointName : listSiteTestData) {
	// Assert.assertTrue("Data should be deleted ",
	// objDelTestdata.manageTestData("Endpoint_Delete", endpointName));
	// }
	// } else if (testData.equalsIgnoreCase("Tenants")) {
	// for (String tenantsName : listSiteTestData) {
	// Assert.assertTrue("Data should be deleted ",
	// objDelTestdata.manageTestData("Tenant", tenantsName));
	// }
	// } else if (testData.equalsIgnoreCase("Sites")) {
	// for (String sitesName : listSiteTestData) {
	// Assert.assertTrue("Data should be deleted ",
	// objDelTestdata.manageTestData("Site", sitesName));
	// }
	// }
	//
	// }
	//
	// @Then("^click the \"(.*?)\" button on endpoint page$")
	// public void click_on_the_button_on_endpoint_page(String buttonName)
	// throws Throwable {
	// endpointPage.assertElementDisplayed(endpointPageObjectRepo("addEndpointButton"),
	// "Failed to verify endpoint grid availability.");
	// String message = "Failed to click on button :" + buttonName;
	// Assert.assertTrue(message,
	// endpointPage.clickOperationOnEndpointPage(buttonName,
	// endpointPageObjectRepo));
	// }
	//
	// @Then("^enter test values for new endpoint \"(.*?)\"$")
	// public void enter_the_value_for_new_endpoint(String endpointNumber)
	// throws Throwable {
	// if (endpointNumber.equals("1")) {
	// endpointPage.enterText(endpointPageObjectRepo("addEndpointPopupSerialField"),
	// endpointPageObjectTestData("addEndpointSerialTestData1"));
	// endpointPage.selectDropDownByValue(endpointPageObjectRepo("addEndpointTypeDropDown"),
	// endpointPageObjectTestData("addEndpointTypeDropDownTestData1"));
	// } else if (endpointNumber.equals("2")) {
	// endpointPage.enterText(endpointPageObjectRepo("addEndpointPopupSerialField"),
	// endpointPageObjectTestData("addEndpointSerialTestData2"));
	// endpointPage.selectDropDownByValue(endpointPageObjectRepo("addEndpointTypeDropDown"),
	// endpointPageObjectTestData("addEndpointTypeDropDownTestData2"));
	// } else if (endpointNumber.equals("3")) {
	// endpointPage.enterText(endpointPageObjectRepo("addEndpointPopupSerialField"),
	// endpointPageObjectTestData("addEndpointSerialTestData3"));
	// endpointPage.selectDropDownByValue(endpointPageObjectRepo("addEndpointTypeDropDown"),
	// endpointPageObjectTestData("addEndpointTypeDropDownTestData3"));
	// } else if (endpointNumber.equals("4")) {
	// endpointPage.enterText(endpointPageObjectRepo("addEndpointPopupSerialField"),
	// endpointPageObjectTestData("addEndpointSerialTestData4"));
	// endpointPage.selectDropDownByValue(endpointPageObjectRepo("addEndpointTypeDropDown"),
	// endpointPageObjectTestData("addEndpointTypeDropDownTestData4"));
	// } else if (endpointNumber.equals("5")) {
	// endpointPage.enterText(endpointPageObjectRepo("addEndpointPopupSerialField"),
	// endpointPageObjectTestData("addEndpointSerialTestData5"));
	// endpointPage.selectDropDownByValue(endpointPageObjectRepo("addEndpointTypeDropDown"),
	// endpointPageObjectTestData("addEndpointTypeDropDownTestData1"));
	// }
	//
	// }
	//
	// @When("^click on the \"(.*?)\" button on add endpoint popup$")
	// public void click_button_on_add_endpoint_popup(String buttonName) throws
	// Throwable {
	// String message = "Failed to click on button :" + buttonName;
	// Assert.assertTrue(message,
	// endpointPage.clickOperationOnEndpointPage(buttonName,
	// endpointPageObjectRepo));
	// endpointPage.explicitWait(2000);
	// }
	//
	// @When("^we are on firmware tab, click on peripheral firmware tab$")
	// public void we_are_on_firmware_tab_click_on_peripheral_firmware_tab()
	// throws Throwable {
	// peripheralFirmwarePage.click(peripheralFirmwarePageObjectRepo("peripheralfirmwareTab"));
	// }
	//
	// @Then("^delete all the dependencies associated with \"(.*?)\" used as test data for endpointfirmware page$")
	// public void delete_used_as_test_data_for_endpoint_firmware_page(String
	// endpointFirmwareName) throws Throwable {
	// ManageTestData objDelTestdata = new ManageTestData();
	// objDelTestdata.manageTestData("EndpointFirmware", endpointFirmwareName);
	// checkExistingEndpointFirmware =
	// objDelTestdata.manageTestData("Endpoint_Firmware_Check",
	// endpointFirmwareName);
	// }
	//
	// @Then("^click on the \"(.*?)\" tab of firmware$")
	// public void click_on_the_PFWTab(String firmwarTab) throws Throwable {
	// peripheralFirmwarePage.click(peripheralFirmwarePageObjectRepo("peripheralfirmwareTab"));
	// }
	//
	// @Given("^delete the data used as test data for peripheralfirmware page$")
	// public void
	// delete_the_data_used_as_test_data_for_peripheral_firmware_page(DataTable
	// args) throws Throwable {
	// List<String> lstData = args.asList(String.class);
	// for (String peripheralFirmware : lstData) {
	// ManageTestData objDelTestdata = new ManageTestData();
	// Assert.assertTrue("Error occured while Deleting test data for peripheral firmware table",
	// objDelTestdata.manageTestData("PeripheralFirmware", peripheralFirmware));
	// }
	// }
	//
	// @Then("^click \"(.*?)\" button on update endpoint firmware page$")
	// public void click_update_button_on_UEPFW_page(String buttonName) throws
	// Throwable {
	// String message = "Failed to click on button :" + buttonName;
	// Assert.assertTrue(message,
	// endpointFirmwarePage.clickOperationOnUpdateEndpointFirmwareSubTab(buttonName,
	// endpointFirmwarePageObjectRepo));
	// }
	//
	// @Then("^click on the \"(.*?)\" button on add peripheralfirmware popup and select \"(.*?)\" file$")
	// public void
	// click_on_the_button_on_add_peripheral_firmware_popup_and_select_an_valid_file(String
	// buttonName,
	// String fileType) throws Throwable {
	// String firmwareFile = null, filePath = null, absolutePath = null;
	// File f = null;
	// switch (fileType.toUpperCase()) {
	// case "VALID":
	// firmwareFile = "TS102_400.bin";
	// f = new File(firmwareFile);
	// absolutePath = f.getAbsolutePath();
	// filePath = absolutePath.substring(0,
	// TODO : updated by k as \\ replaced with / on 8 may 2018
	// absolutePath.lastIndexOf(File.separator)) + "/downloadDir/"
	// + firmwareFile;
	// break;
	// case "INVALID":
	// firmwareFile = "InvalidPeripheralFirmwareFile.xlsx";
	// f = new File(firmwareFile);
	// absolutePath = f.getAbsolutePath();
	// TODO : updated by k as \\ replaced with / on 8 may 2018
	// filePath = absolutePath.substring(0,
	// absolutePath.lastIndexOf(File.separator)) + "/downloadDir/"
	// + firmwareFile;
	// break;
	// }
	// String message = "Failed to click on button :" + buttonName + " " +
	// " on add endpoint firmware popup.";
	// Assert.assertTrue(message,
	// peripheralFirmwarePage.clickChooseButtonAndSelectInvalidFile(buttonName,
	// peripheralFirmwarePageObjectRepo, fileType, filePath));
	// }
	//
	// @Then("^enter the test data for the following fields to create new peripheralfirmware$")
	// public void
	// enter_the_test_data_for_the_following_fields_to_create_new_peripheral_firware(DataTable
	// arg1)
	// throws Throwable {
	// List<String> fieldList = arg1.asList(String.class);
	// for (String list : fieldList) {
	// peripheralFirmwarePage.enterTextPeripheralFirmwarePage(list,
	// peripheralFirmwarePageObjectRepo,
	// peripheralFirmwarePageObjectTestData);
	// }
	// }
	//
	// @Then("^enter controller min version \"(.*?)\" and click the \"(.*?)\" button if found version then \"(.*?)\" the same$")
	// public boolean click_on_search_button_on_Peripheral_firmware_page(String
	// peripheralFirmwareName,
	// String peripheralFirmwarepagebutton, String peripheralFirmwareButton)
	// throws Throwable {
	// String message = "Failed to click on button :" +
	// peripheralFirmwarepagebutton + " "
	// + " on peripheral firmware detail grid.";
	// Assert.assertTrue(message,
	// peripheralFirmwarePage.clickOperationOnPeripheralFirmwarePage(
	// peripheralFirmwarepagebutton, peripheralFirmwarePageObjectRepo));
	//
	// if (peripheralFirmwarePage
	// .getElementText(peripheralFirmwarePageObjectRepo("peripheralfirmwareGridNoRecords"))
	// != null) {
	//
	// peripheralFirmwarePage.selectPeripheralFirmwareFromGrid_PeripheralFirmwarePage(peripheralFirmwareName,
	// peripheralFirmwarePageObjectRepo);
	// peripheralFirmwarePage.clickOperationOnPeripheralFirmwarePage(peripheralFirmwarepagebutton,
	// peripheralFirmwarePageObjectRepo);
	// String message2 = "Failed to click on button :" +
	// peripheralFirmwareButton + " "
	// + " on peripheral Firmware detail grid.";
	// Assert.assertTrue(message2,
	// peripheralFirmwarePage.clickOperationOnPeripheralFirmwarePagePopup(
	// peripheralFirmwareButton, peripheralFirmwarePageObjectRepo));
	//
	// } else {
	// return true;
	// }
	// return checkExistingEndpointFirmware;
	//
	// }
	//
	// @Then("^verify the details of the selected firmware in following elements on peripheralfirmware detail popup$")
	// public void
	// verify_the_details_of_the_selected_firmware_in_following_elements_on_peripheral_firmware_detail_popup(
	// DataTable arg1) throws Throwable {
	// List<String> lstData = arg1.asList(String.class);
	// arg1.asList(String.class);
	// boolean isVisible = true;
	// for (String header : lstData) {
	// boolean visible = false;
	// if (header.trim().equals("Peripheral Type")) {
	// visible = peripheralFirmwarePage.verifyElementDisplayed(
	// peripheralFirmwarePageObjectRepo("peripheralFirmwarePageDetailPopupPeripheralType"));
	// }
	// if (header.trim().equals("Peripheral Type ID")) {
	// visible = peripheralFirmwarePage.verifyElementDisplayed(
	// peripheralFirmwarePageObjectRepo("peripheralFirmwarePageDetailPopupPeripheralTypeID"));
	// }
	// if (header.trim().equals("Release Date")) {
	// visible = peripheralFirmwarePage.verifyElementDisplayed(
	// peripheralFirmwarePageObjectRepo("peripheralFirmwarePageDetailPopupReleaseDate"));
	// }
	// if (header.trim().equals("Version")) {
	// visible = peripheralFirmwarePage.verifyElementDisplayed(
	// peripheralFirmwarePageObjectRepo("peripheralFirmwarePageDetailPopupVersion"));
	// }
	// if (header.trim().equals("Min Controller Version")) {
	// visible = peripheralFirmwarePage.verifyElementDisplayed(
	// peripheralFirmwarePageObjectRepo("peripheralFirmwarePageDetailPopupMinControllerVersion"));
	// }
	// if (visible == false) {
	// if (visible == false) {
	// isVisible = false;
	// Assert.assertTrue("Header [" + header +
	// "] is not visible in Peripheral Firmware Grid", isVisible);
	// break;
	// }
	// }
	// }
	//
	// }
	//
	// @When("^verify the \"(.*?)\" page should be displayed for peripheralfirmware $")
	// public void verify_page_should_displayed(String
	// peripheralfirmwarePopupName) throws Throwable {
	// String message =
	// "Failed to check existence of edit endpoint Firmware popup.";
	// Assert.assertTrue(message,
	// peripheralFirmwarePage.checkPopupExistence("DETAIL_PERIPHERALFIRMWARE",
	// "",
	// peripheralFirmwarePageObjectRepo));
	// }
	//
	//
	// // @Then("^verify \"(.*?)\" capabilitie, click on \"(.*?)\" tab then
	// // \"(.*?)\" button$")
	// // public boolean click_on(String capibility, String tab, String
	// // sButtonName) throws Throwable {
	// // switch (capibility.toUpperCase()) {
	// // case "ADD USER":
	// // String message1 = "To verify [" + capibility + "] " + tab + " is not
	// // clicked";
	// // Assert.assertTrue(message1,
	// // BaseUtil.clickOperationUserRolePermissions(tab,
	// // getProps().getProperty, userPageObjectRepo,
	// // sitePageObjectRepo, tenantPageObjectRepo));
	// // String message2 = "To verify [" + capibility + "] " + sButtonName + "
	// is
	// // not clicked";
	// // Assert.assertTrue(message2,
	// // BaseUtil.clickOperationUserRolePermissions(sButtonName,
	// // getProps().getProperty, userPageObjectRepo,
	// // sitePageObjectRepo, tenantPageObjectRepo));
	// // return true;
	// // default:
	// // logger.error("Switch Case[" + sButtonName +
	// "] is not matched in class["
	// // + getClass().getName()
	// // + "] , Method[" +
	// // Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
	// // return false;
	// // }
	// // }
	//
	// @Then("^uncheck \"(.*?)\" checkbox on user$")
	// public void uncheck_checkbox_is_unchecked(String checkBox) throws
	// Throwable {
	// userPage.unCheckCheckBox(userPageObjectRepo("userinActiveUsersChkBox"));
	// userPage.explicitWait(1000);
	// }
	//
	// @When("^enter tenant name \"(.*?)\" in \"(.*?)\" field$")
	// public void enter_the_tenant_name_in_search_field(String tenantName,
	// String sElementName) throws Throwable {
	// tenantPage.enterText_TenantPage(sElementName, tenantName,
	// tenantPageObjectRepo);
	// }
	//
	// @When("^enter the \"(.*?)\" site name in the site search box and verify is buttons \"(.*?)\" tab$")
	// public void enter_the_hide_site_name_in_search_field(String siteType,
	// String tabName) throws Throwable {
	// Assert.assertFalse("Site checkbox is not selected for site.",
	// UserRolePermissionsPage.isSiteSelected(siteType, tabName,
	// ));
	// }
	//
	// @Then("^verify the \"(.*?)\" button is enabled for device \"(.*?)\"$")
	// public void verify_the_button_is_enabled_for_device(String buttonName,
	// String deviceName) throws Throwable {
	//
	// BaseUtil.assertElementDisplayed(getProps().getProperty("editHVACSchedulebtn"),
	// "" + buttonName + "is not available for selected" + deviceName + "");
	// }
	//
	// @Then("^verify following buttons should not be are enabled$")
	// public void verify_following_button_should_not_be_URP_models(DataTable
	// table) throws Throwable {
	// List<String> lstData = table.asList(String.class);
	// table.asList(String.class);
	// for (String button : lstData) {
	// String message = button + " should  be enabled";
	// Assert.assertTrue(message,
	// UserRolePermissionsPage.isElementEnabledOnURPPage(button,
	// ));
	// }
	// }
	//
	// @Then("^click the \"(.*?)\" button and delete created user \"(.*?)\"$")
	// public void close_edit_user(String button, String userName) throws
	// Throwable {
	// BaseUtil.clickAndWait(userPageObjectRepo("editUsersaveAndCloseUserBtn"));
	// ManageTestData objDelTestdata = new ManageTestData();
	// Assert.assertTrue("Error occured while deleting test Data for user page",
	// objDelTestdata.manageTestData("User", userName));
	// }
	//
	// @Then("^close the \"(.*?)\" model$")
	// public void close_edit_user_model(String model) throws Throwable {
	//
	// UserRolePermissionsPage.scrollTab(TestBase.getWebDriver(),
	// getProps().getProperty("editUsercloseUserBtn"), 2000);
	// BaseUtil.clickAndWait(getProps().getProperty("editUsercloseUserBtn"));
	// BaseUtil.waitForSpinner();
	// }
	//
	// public void pressEscape() {
	// TestBase.getWebDriver().findElement(By.xpath("//*[@id='admin']/div[5]/div/div")).sendKeys(Keys.ESCAPE);
	// }
	//
	// @Then("^verify the ROLE_ADMIN users no longer can see other \"(.*?)\"$")
	// public void verify_site_tab_list(String tabName) throws Throwable {
	// UserRolePermissionsPage.verifyTabsGrid(tabName,
	// );
	// }
	//
	// @Then("^verify the \"(.*?)\" for user \"(.*?)\"$")
	// public void verify_site_list(String tabName, String userName) throws
	// Throwable {
	// UserRolePermissionsPage.verifyTabsGridNew(tabName, userName,
	// );
	// }
	//
	// //
	// ====================================================================================================================================
	//
	//
	// @Then("^click \"(.*?)\" checkbox on sites tab$")
	// public void click_on_site_tab(String button) throws Throwable {
	// Assert.assertTrue(button + "is not clicked",
	// sitePage.clickOperationOnSitePage(button, sitePageObjectRepo));
	// }
	//
	//
	// @Then("^click \"(.*?)\" checkbox on tenants tab$")
	// public void click_on_tanants_tab(String button) throws Throwable {
	// Assert.assertTrue(button + "is not clicked",
	// BaseUtil.clickOperationOnTenantPage(button, tenantPageObjectRepo));
	// }
	//
	// @Then("^click \"(.*?)\" checkbox on user tab$")
	// public void click_on_user_tab(String button) throws Throwable {
	// Assert.assertTrue(button + "is not clicked",
	// userPage.clickOperationOnUserPage(button,
	// ));
	// }
	//
	// @Then("^click \"(.*?)\" checkbox$")
	// public void click_checkbox_UserRolePermissions(String button) throws
	// Throwable {
	// Assert.assertTrue(button + "is not clicked",
	// BaseUtil.clickOperationUserRolePermissions(button,
	// getProps().getProperty, userPageObjectRepo,
	// sitePageObjectRepo, tenantPageObjectRepo));
	// }
	//
	// @Then("^click the \"(.*?)\" icon of site detail$")
	// public void click_on_close_Icon(String button) throws Throwable {
	// Assert.assertTrue(button + " Button is not clicked",
	// BaseUtil.clickOperationUserRolePermissions(button,
	// getProps().getProperty,
	// userPageObjectRepo, sitePageObjectRepo, tenantPageObjectRepo));
	// }
	//
	//
	// @Then("^\"(.*?)\"$")
	// public void select_all_devices_userRolePermissions(String button) throws
	// Throwable {
	// Assert.assertTrue(button + " Button is not clicked",
	// BaseUtil.clickOperationUserRolePermissions(button,
	// getProps().getProperty,
	// userPageObjectRepo, sitePageObjectRepo, tenantPageObjectRepo));
	// }
	//
	// @Then("^click \"(.*?)\" tenant button$")
	// public void click_save_button_onTenant_UserRolePermissions(String button)
	// throws Throwable {
	// Assert.assertTrue(button + " Button is not clicked",
	// BaseUtil.clickOperationUserRolePermissions(button,
	// getProps().getProperty,
	// userPageObjectRepo, sitePageObjectRepo, tenantPageObjectRepo));
	// BaseUtil.waitForSpinner();
	// }
	//
	// @Then("^uncheck the \"(.*?)\" checkbox of site$")
	// public void uncheck_checkbox_is_unchecked_site(String checkBox) throws
	// Throwable {
	// Assert.assertTrue(checkBox +
	// " checkbox is not unchecked before searching site",
	// sitePage.clickOperationOnSitePage(checkBox, sitePageObjectRepo));
	// }
	//
	// @Then("^click \"(.*?)\" accordion edit site$")
	// public void click_permission_accordion_UserRolePermissions(String button)
	// throws Throwable {
	// Assert.assertTrue(button + " Button is not clicked",
	// sitePage.clickOperationOnSitePage(button,
	// ));
	// }
	//
	// @Then("^enter text \"(.*?)\" in the site search text box$")
	// public void
	// enter_text_inthe_search_site_textbox_UserRolePermissions(String
	// searctText) throws Throwable {
	// BaseUtil.enterText(getProps().getProperty("siteTreeSearchBox"),
	// searctText);
	// BaseUtil.explicitWait(1000);
	// }
	//
	// @Then("^click \"(.*?)\" icon of site tree$")
	// public void click_on_expend_icon_site_tree_userRolePermissions(String
	// button) throws Throwable {
	// BaseUtil.explicitWait(3000);
	// Assert.assertTrue(button + " Button is not clicked",
	// BaseUtil.clickOperationUserRolePermissions(button,
	// getProps().getProperty,
	// userPageObjectRepo, sitePageObjectRepo, tenantPageObjectRepo));
	// }
	//
	// @Then("^delete the \"(.*?)\" used as urp test data$")
	// public void delete_the_data_used_as_test_data(String tab, List<String>
	// listSiteTestData) throws Throwable {
	// ManageTestData objDelTestdata = new ManageTestData();
	// if (tab.equalsIgnoreCase("Sites")) {
	// for (String sitesName : listSiteTestData) {
	// Assert.assertTrue("Data should be deleted ",
	// objDelTestdata.manageTestData("Site", sitesName));
	// }
	// } else if (tab.equalsIgnoreCase("Tenants")) {
	// for (String tenantsName : listSiteTestData) {
	// Assert.assertTrue("Data should be deleted ",
	// objDelTestdata.manageTestData("Tenant", tenantsName));
	// }
	// }
	// }
	//
	//
	//
	// @When("^verify channel \"(.*?)\" is deleted successfully$")
	// public void enter_the_deleted_virtual_channel_name_in_search_field(String
	// virtualChannelName) throws Throwable {
	// sitePage.enterText_SitePage("SEARCH_CHANNEL_NAME", virtualChannelName,
	// sitePageObjectRepo);
	// Assert.assertTrue("Channel is not deleted successfully:",
	// sitePage.instantElementCheck("Display",
	// sitePageObjectRepo("virtualChannelNoRecords")));
	// }
	//
	// @When("^verify channel \"(.*?)\" is not deleted$")
	// public void enter_no_deleted_for_GPSupport(String virtualChannelName)
	// throws Throwable {
	// sitePage.enterText_SitePage("SEARCH_CHANNEL_NAME", virtualChannelName,
	// sitePageObjectRepo);
	// Assert.assertFalse("Channel is deleted successfully by GPSupport:",
	// sitePage.instantElementCheck("Display",
	// sitePageObjectRepo("virtualChannelNoRecords")));
	// }
	//
	// @Then("^click \"(.*?)\" button of channel2$")
	// public void click_add_button_for_ch2(String ch2) throws Throwable {
	// Assert.assertTrue(ch2 + " Button is not clicked",
	// BaseUtil.clickOperationUserRolePermissions(ch2,
	// getProps().getProperty, userPageObjectRepo,
	// sitePageObjectRepo, tenantPageObjectRepo));
	// }
	//
	// @Then("^click \"(.*?)\" button of $")
	// public void click_add_selectall_button_userRolePermissions(String button)
	// throws Throwable {
	// Assert.assertTrue(button + " Button is not clicked",
	// BaseUtil.clickOperationUserRolePermissions(button,
	// getProps().getProperty,
	// userPageObjectRepo, sitePageObjectRepo, tenantPageObjectRepo));
	// }
	//
	// @Given("^delete the following test data before creating new test data on tean$")
	// public void manageTestData(List<String> lstTestData) throws SQLException,
	// IOException {
	// ManageTestData objDelTestdata = new ManageTestData();
	// for (String tenantName : lstTestData) {
	// if (tenantName.equalsIgnoreCase("tenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("pTenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("cPTenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("p_TenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("cP_TenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("c_TenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("p_pTenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("c_pTenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("cP_pTenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("p_cPTenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("c_cPTenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("cP_cPTenantPag_tenantBot1")) {
	// objDelTestdata.manageTestData("Tenant", tenantName);
	// } else if (tenantName.equalsIgnoreCase("sitePage_TenantSiteBot1")) {
	// objDelTestdata.manageTestData("Site", tenantName);
	// }
	// }
	// }
	//
	// @When("^click on \"(.*?)\" button of edit endpoint firmware popup$")
	// public void click_on_button_on_edit_endpoint_firmware_popup(String
	// endpointFirmwareButton) throws Throwable {
	// if (!checkExistingEndpointFirmware) {
	// return;
	// }
	// String message = "Failed to click on button :" + endpointFirmwareButton +
	// " "
	// + " on endpoint Firmware detail grid.";
	// Assert.assertTrue(message,
	// endpointFirmwarePage.clickOperationOnEditEndpointFirmwarePage(endpointFirmwareButton,
	// endpointFirmwarePageObjectRepo));
	// }
	//
	// @When("^click on \"(.*?)\" button of confirmation box on edit endpoint firmware popup$")
	// public void
	// click_on_button_on_confirmation_box_on_edit_endpoint_firmware_popup(String
	// buttonName)
	// throws Throwable {
	// String message = "Failed to click on button :" + buttonName + " " +
	// " on confirmation box.";
	// Assert.assertTrue(message,
	// endpointFirmwarePage.clickOperationOnConfirmationBox(buttonName,
	// endpointFirmwarePageObjectRepo));
	// }
	//
	// // @Then("^click the logout button and verify the login page$")
	// // public void Verify_Logout_After_Successful_Login() throws
	// InterruptedException {
	// // // loginPage.explicitWait(2000);
	// // loginPage.click(getProps().getProperty("optionDropDown"));
	// // loginPage.click(getProps().getProperty("logout"));
	// //
	// // String message = "Error while logging out";
	// //
	// loginPage.assertElementDisplayed(getProps().getProperty("adminUserName_Locator"),
	// message);
	// // }
	//
	//
	// @When("^enter endpoint friamware name \"(.*?)\" in \"(.*?)\" search field if available then select else add new$")
	// public void enter_name_in_endpoint_firmware_search_field(String
	// endpointFirmwareName, String subTabName)
	// throws Throwable {
	// endpointFirmwarePage.enterText_endpointFirmwarePage(subTabName,
	// endpointFirmwareName,
	// endpointFirmwarePageObjectRepo);
	// if (endpointFirmwarePage.instantElementCheck("Display",
	// endpointFirmwarePageObjectRepo("endpointFirmwareTotalItemsCount")) ==
	// true) {
	//
	// endpointFirmwarePage.selectEndpointFirmwareFromGrid_EndpointFirmwarePage(endpointFirmwareName,
	// endpointFirmwarePageObjectRepo);
	// }
	// }
	//
	// @When("^if endpoint firmware \"(.*?)\" is already exist then \"(.*?)\" this and click the \"(.*?)\" button to confirme$")
	// public void search_endpoint_name_in_endpoint_firmware_search_field(String
	// endpointFirmwareName, String deleteButton,
	// String okButton) throws Throwable {
	// endpointFirmwarePage.enterText_endpointFirmwarePage("ENDPOINTFIRMWARE_SEARCH",
	// endpointFirmwareName,
	// endpointFirmwarePageObjectRepo);
	//
	// endpointFirmwarePage.selectEndpointFirmwareFromGrid_EndpointFirmwarePage(endpointFirmwareName,
	// endpointFirmwarePageObjectRepo);
	// String message = "Failed to click on button :" + deleteButton;
	// Assert.assertTrue(message,
	// endpointFirmwarePage.clickOperationOnEditEndpointFirmwarePage(deleteButton,
	// endpointFirmwarePageObjectRepo));
	// Assert.assertTrue(okButton + " Button is not clicked",
	// tenantPage.clickOperationOnTenantPage(okButton, tenantPageObjectRepo));
	// }
	//
	// @When("^enter peripheral friamware name \"(.*?)\" in endpointfirmware search field$")
	// public void enter_the_name_in_pf_firmware_search_field(String
	// sPeripheralFirmwareName) throws Throwable {
	// if (!checkExistingPeripheralFirmware) {
	// return;
	// }
	// peripheralFirmwarePage.selectPeripheralFirmwareFromGrid_PeripheralFirmwarePage(sPeripheralFirmwareName,
	// peripheralFirmwarePageObjectRepo);
	// }
	//
	//
	// @When("^enter the endpointfirware name \"(.*?)\" in the search field$")
	// public void enter_the_endpointfw_in_search_field(String EndpointFWName)
	// throws Throwable {
	// endpointFirmwarePage.enterText_endpointFirmwarePage("ENDPOINTFIRMWARE_SEARCH",
	// EndpointFWName,
	// endpointFirmwarePageObjectRepo);
	// }
	//
	// @Then("^update site \"(.*?)\" tenant name \"(.*?)\"$")
	// public void update_site_tenant(String siteName, String tenantName) throws
	// Throwable {
	// ManageTestData objDelTestdata = new ManageTestData();
	// objDelTestdata.updateSiteTenant(tenantName, siteName);
	// BaseUtil.explicitWait(2000);
	// }
	//
	// @Then("^assign tenant \"(.*?)\" to user \"(.*?)\"$")
	// public void assign_site_to_user(String tenantName, String userName)
	// throws Throwable {
	// ManageTestData objDelTestdata = new ManageTestData();
	// objDelTestdata.updateUser(tenantName, userName);
	// }
	//
	// @Then("^click \"(.*?)\" button on edit site model$")
	// public void click_delete_button_UserRolePermissions(String button) throws
	// Throwable {
	// Assert.assertTrue(button + " Button is not clicked",
	// sitePage.clickOperationOnSitePage(button,
	// ));
	// }
	//
	// @Then("^click on the \"(.*?)\" button on edit user model$")
	// public void click_add_all_UserRolePermissions(String button) throws
	// Throwable {
	// Assert.assertTrue(button + " Button is not clicked",
	// BaseUtil.clickOperationUserRolePermissions(button,
	// getProps().getProperty,
	// userPageObjectRepo, sitePageObjectRepo, tenantPageObjectRepo));
	// }
	//
	// @Then("^verify the \"(.*?)\" button is enabled$")
	// public void then_verify_isbutton_enabled(String button) throws Throwable
	// {
	// Assert.assertTrue("[" + button + "] is not enabled",
	// UserRolePermissionsPage.isElementEnabled(
	// UserRolePermissionsPage.getLocator(getProps().getProperty("editRegularHoursSavebtn"))));
	// }
	//
	// @Then("^change the \"(.*?)\"$")
	// public void change_open_time(String time) throws Throwable {
	// UserRolePermissionsPage.selectDropDownByIndex(getProps().getProperty("regularHoursOpenHours"),
	// 5);
	// }
	//
	//
	// @Then("^insert one more site path values \"(.*?)\" on edit site model$")
	// public void insert_site_path(String sitePath) throws Throwable {
	// Assert.assertTrue("Add Path button is not working on Edit Site Model",
	// UserRolePermissionsPage.isElementLoaded(
	// UserRolePermissionsPage.getLocator(getProps().getProperty("editSitePathTextBox1"))));
	// BaseUtil.enterText(getProps().getProperty("editSitePathTextBox1"),
	// sitePath);
	// BaseUtil.explicitWait(1000);
	// }
	//
	//
	// @Then("^delete the site used as test data for user role$")
	// public void delete_the_site_used_as_test_data(List<String>
	// listSiteTestData) throws Throwable {
	// ManageTestData objDelTestdata = new ManageTestData();
	// if
	// (listSiteTestData.get(0).equalsIgnoreCase(sitePageObjectTestData("addEndpointSiteNameTestData"))
	// || listSiteTestData.get(0)
	// .equalsIgnoreCase(sitePageObjectTestData("addSiteGPSupportUserRoleTestData")))
	// {
	// for (String siteName : listSiteTestData) {
	// Assert.assertTrue("Data should be deleted ",
	// objDelTestdata.manageTestData("Site", siteName));
	//
	// }
	// } else if (listSiteTestData.get(0)
	// .equalsIgnoreCase(sitePageObjectTestData("addSiteTenantDropDownTestData")))
	// {
	// for (String tenantName : listSiteTestData) {
	// Assert.assertTrue("Data should be deleted ",
	// objDelTestdata.manageTestData("Tenant", tenantName));
	// }
	// }
	// }
	//
	// @Then("^enter valid values for the test site \"(.*?)\"$")
	// public void enter_existing_name_as(String siteNumber) throws Throwable {
	// if (siteNumber.equals("1")) {
	// sitePage.enterText(sitePageObjectRepo("addSiteNameField"),
	// sitePageObjectTestData("addSiteNameTestData"));
	// sitePage.enterText(sitePageObjectRepo("addSiteDescriptionField"),
	// sitePageObjectTestData("addSiteDescriptionTestData"));
	// } else if (siteNumber.equals("2")) {
	// sitePage.enterText(sitePageObjectRepo("addSiteNameField"),
	// sitePageObjectTestData("addSiteAdditonalNameTestData"));
	// sitePage.enterText(sitePageObjectRepo("addSiteDescriptionField"),
	// sitePageObjectTestData("addSiteadditionalDescriptionTestData"));
	// sitePage.checkCheckBox(sitePageObjectRepo("addSiteActiveCheckBox"));
	// } else if (siteNumber.equals("3")) {
	// sitePage.enterText(sitePageObjectRepo("addSiteNameField"),
	// sitePageObjectTestData("addSiteNameActiveCheckFalseTestData"));
	// sitePage.enterText(sitePageObjectRepo("addSiteDescriptionField"),
	// sitePageObjectTestData("addSiteDescriptionActiveCheckFalseTestData"));
	// } else if (siteNumber.equals("4")) {
	// ManageTestData objDelTestdata = new ManageTestData();
	// String siteName =
	// sitePageObjectTestData("addSiteGPUP-26329TestData");
	// objDelTestdata.manageTestData("Site", siteName);
	// sitePage.enterText(sitePageObjectRepo("addSiteNameField"),
	// sitePageObjectTestData("addSiteGPUP-26329TestData"));
	// sitePage.enterText(sitePageObjectRepo("addSiteDescriptionField"),
	// sitePageObjectTestData("addSiteGPUP-26329TestData"));
	// } else if (siteNumber.equals("5")) {
	// ManageTestData objDelTestdata = new ManageTestData();
	// String siteName =
	// sitePageObjectTestData("addSiteGPSupportUserRoleTestData");
	// objDelTestdata.manageTestData("Site", siteName);
	// sitePage.enterText(sitePageObjectRepo("addSiteNameField"),
	// sitePageObjectTestData("addSiteGPAdminTestData"));
	// sitePage.enterText(sitePageObjectRepo("addSiteDescriptionField"),
	// sitePageObjectTestData("addSiteGPAdminTestData"));
	// } else if (siteNumber.equals("6")) {
	// ManageTestData objDelTestdata = new ManageTestData();
	// String siteName = sitePageObjectTestData("addSiteGPAdminTestData");
	// objDelTestdata.manageTestData("Site", siteName);
	// sitePage.enterText(sitePageObjectRepo("addSiteNameField"),
	// sitePageObjectTestData("addSiteGPAdminTestData"));
	// sitePage.enterText(sitePageObjectRepo("addSiteDescriptionField"),
	// sitePageObjectTestData("addSiteGPAdminTestData"));
	// }
	// sitePage.enterText(sitePageObjectRepo("addSiteAddress1Field"),
	// sitePageObjectTestData("addSiteAddress1TestData"));
	// sitePage.enterText(sitePageObjectRepo("addSiteAddress2Field"),
	// sitePageObjectTestData("addSiteAddress2TestData"));
	// sitePage.enterText(sitePageObjectRepo("addSiteCityField"),
	// sitePageObjectTestData("addSiteCityTestData"));
	// // GPUP-29213:Add Site Modal Should Have Smart Dropdowns for
	// // State/Provinces (based on Country selection)
	// sitePage.selectDropDownByValue(sitePageObjectRepo("addSiteProvinceField"),
	// sitePageObjectTestData("addSiteStateDropDownTestData"));
	// //
	// ------------------------------------------------------------------------------------
	// sitePage.enterText(sitePageObjectRepo("addSitePostalCodeField"),
	// sitePageObjectTestData("addSitePostalCodeTestData"));
	// sitePage.selectDropDownByValue(sitePageObjectRepo("addSiteCountryDropDown"),
	// sitePageObjectTestData("addSiteCountryDropDownTestData"));
	// sitePage.enterText(sitePageObjectRepo("addSiteAreaField"),
	// sitePageObjectTestData("addSiteAreaTestData"));
	// sitePage.checkCheckBox(sitePageObjectRepo("addSiteControlCheckBox"));
	// sitePage.checkCheckBox(sitePageObjectRepo("addSiteSolarPanelCheckBox"));
	// sitePage.checkCheckBox(sitePageObjectRepo("addSiteActiveCheckBox"));
	// sitePage.explicitWait(1000);
	// sitePage.enterText(sitePageObjectRepo("addSiteSolarPanelSystemSizeField"),
	// sitePageObjectTestData("addSiteSolarPanelSystemSizeTestData"));
	// sitePage.click(sitePageObjectRepo("addSitePathsField"));
	// sitePage.enterText(sitePageObjectRepo("addSitePathsField"),
	// sitePageObjectTestData("addSitePathsFieldTestData"));
	// sitePage.explicitWait(3000);
	// }
	
	// TODO : above commented method is updated by k on 31 aug 2018..commenting to avoid compilation issues....verify
//	@Then("^enter valid values for the test site \"(.*?)\"$")
//	public void enter_existing_name_as(String siteNumber) throws Throwable {
//		if (siteNumber.equals("1")) {
//			sitePage.enterText(sitePageObjectRepo.get("addSiteNameField"),
//					sitePageObjectTestData.get("addSiteNameTestData"));
//			sitePage.enterText(sitePageObjectRepo.get("addSiteDescriptionField"),
//					sitePageObjectTestData.get("addSiteDescriptionTestData"));
//		} else if (siteNumber.equals("0")) {
//			sitePage.enterText(sitePageObjectRepo.get("addSiteNameField"),
//					sitePageObjectTestData.get("addSiteNameTestDataURP"));
//			sitePage.enterText(sitePageObjectRepo.get("addSiteDescriptionField"),
//					sitePageObjectTestData.get("addSiteDescriptionTestDataURP"));
//		}else if (siteNumber.equals("2")) {
//			sitePage.enterText(sitePageObjectRepo.get("addSiteNameField"),
//					sitePageObjectTestData.get("addSiteAdditonalNameTestData"));
//			sitePage.enterText(sitePageObjectRepo.get("addSiteDescriptionField"),
//					sitePageObjectTestData.get("addSiteadditionalDescriptionTestData"));
//			sitePage.checkCheckBox(sitePageObjectRepo.get("addSiteActiveCheckBox"));
//		} else if (siteNumber.equals("3")) {
//			sitePage.enterText(sitePageObjectRepo.get("addSiteNameField"),
//					sitePageObjectTestData.get("addSiteNameActiveCheckFalseTestData"));
//			sitePage.enterText(sitePageObjectRepo.get("addSiteDescriptionField"),
//					sitePageObjectTestData.get("addSiteDescriptionActiveCheckFalseTestData"));
//		} else if (siteNumber.equals("4")) {
//			DeleteTestData objDelTestdata = new DeleteTestData();
//			String siteName = sitePageObjectTestData.get("addSiteGPUP-26329TestData");
//			objDelTestdata.deleteTestData("Site", siteName);
//			sitePage.enterText(sitePageObjectRepo.get("addSiteNameField"),
//					sitePageObjectTestData.get("addSiteGPUP-26329TestData"));
//			sitePage.enterText(sitePageObjectRepo.get("addSiteDescriptionField"),
//					sitePageObjectTestData.get("addSiteGPUP-26329TestData"));
//		} else if (siteNumber.equals("5")) {
//			DeleteTestData objDelTestdata = new DeleteTestData();
//			String siteName = sitePageObjectTestData.get("addSiteGPSupportUserRoleTestData");
//			objDelTestdata.deleteTestData("Site", siteName);
//			sitePage.enterText(sitePageObjectRepo.get("addSiteNameField"),
//					sitePageObjectTestData.get("addSiteGPAdminTestData"));
//			sitePage.enterText(sitePageObjectRepo.get("addSiteDescriptionField"),
//					sitePageObjectTestData.get("addSiteGPAdminTestData"));
//		} else if (siteNumber.equals("6")) {
//			DeleteTestData objDelTestdata = new DeleteTestData();
//			String siteName = sitePageObjectTestData.get("addSiteGPAdminTestData");
//			objDelTestdata.deleteTestData("Site", siteName);
//			sitePage.enterText(sitePageObjectRepo.get("addSiteNameField"),
//					sitePageObjectTestData.get("addSiteGPAdminTestData"));
//			sitePage.enterText(sitePageObjectRepo.get("addSiteDescriptionField"),
//					sitePageObjectTestData.get("addSiteGPAdminTestData"));
//		}
//		sitePage.enterText(sitePageObjectRepo.get("addSiteAddress1Field"),
//				sitePageObjectTestData.get("addSiteAddress1TestData"));
//		sitePage.enterText(sitePageObjectRepo.get("addSiteAddress2Field"),
//				sitePageObjectTestData.get("addSiteAddress2TestData"));
//		sitePage.enterText(sitePageObjectRepo.get("addSiteCityField"),
//				sitePageObjectTestData.get("addSiteCityTestData"));
//		// GPUP-29213:Add Site Modal Should Have Smart Dropdowns for
//		// State/Provinces (based on Country selection)
//		sitePage.selectDropDownByValue(sitePageObjectRepo.get("addSiteProvinceField"),
//				sitePageObjectTestData.get("addSiteStateDropDownTestData"));
//		// ------------------------------------------------------------------------------------
//		sitePage.enterText(sitePageObjectRepo.get("addSitePostalCodeField"),
//				sitePageObjectTestData.get("addSitePostalCodeTestData"));
//		sitePage.selectDropDownByValue(sitePageObjectRepo.get("addSiteCountryDropDown"),
//				sitePageObjectTestData.get("addSiteCountryDropDownTestData"));
//		sitePage.enterText(sitePageObjectRepo.get("addSiteAreaField"),
//				sitePageObjectTestData.get("addSiteAreaTestData"));
//		sitePage.checkCheckBox(sitePageObjectRepo.get("addSiteControlCheckBox"));
//		sitePage.checkCheckBox(sitePageObjectRepo.get("addSiteSolarPanelCheckBox"));
//		sitePage.checkCheckBox(sitePageObjectRepo.get("addSiteActiveCheckBox"));
//		sitePage.explicitWait(1000);
//		sitePage.enterText(sitePageObjectRepo.get("addSiteSolarPanelSystemSizeField"),
//				sitePageObjectTestData.get("addSiteSolarPanelSystemSizeTestData"));
//		sitePage.click(sitePageObjectRepo.get("addSitePathsField"));
//		sitePage.enterText(sitePageObjectRepo.get("addSitePathsField"),
//				sitePageObjectTestData.get("addSitePathsFieldTestData"));
//		sitePage.explicitWait(3000);
//	}
	////////////////////////////////////////////////////////////////
	
	
	
	//
	//
	// @When("^search virtual channel \"(.*?)\" before add by GPSupport$")
	// public boolean search_virtual_channel_before_addbyGPS(String
	// virtualChannelName) throws Throwable {
	// sitePage.explicitWait(1000);
	// sitePage.enterText_SitePage("SEARCH_CHANNEL_NAME", virtualChannelName,
	// sitePageObjectRepo);
	// if (sitePage.instantElementCheck(PathConstants.TYPE_DISPLAY,
	// sitePageObjectRepo("virtualChannelNoRecords"))) {
	// return true;
	// } else {
	// ManageTestData objDelTestdata = new ManageTestData();
	// objDelTestdata.manageTestData("deleteVirtualChannel",
	// virtualChannelName);
	// }
	// return true;
	// }
	//
	// @When("^enter the site name \"(.*?)\" into name field$")
	// public void enter_the_root_tenant_in_name_field(String rootSiteName)
	// throws Throwable {
	//
	// sitePage.enterText_SitePage("CHANNEL_NAME", rootSiteName,
	// sitePageObjectRepo);
	// }
	//
	// @Then("^select the tenant \"(.*?)\" from the tenant grid$")
	// public void select_the_tenant_from_the_tenant_details_grid(String
	// sTenantName) throws Throwable {
	// String message = "Tenant [" + sTenantName +
	// "] is not found in tenant detail grid.";
	// Assert.assertTrue(message,
	// tenantPage.selectTenantFromGrid_TenantPage(sTenantName,
	// tenantPageObjectRepo));
	// }
	//
	// @When("^verify site \"(.*?)\" is deleted successfully$")
	// public void enter_the_deleted_site_namein_search_field(String
	// rootSiteName) throws Throwable {
	// sitePage.enterText_SitePage("SITE_SEARCH", rootSiteName,
	// sitePageObjectRepo);
	// Assert.assertTrue("Selected site is not deleted",
	// sitePage.verifyPresenceOfText(sitePageObjectRepo("siteGridZeroRecord"),
	// "No records found."));
	// }
	//
	// @When("^click the \"(.*?)\" button on site page$")
	// public void click_on_button(String buttonName) throws Throwable {
	// String message = "Failed to click on button :" + buttonName;
	// Assert.assertTrue(message, sitePage.clickOperationOnSitePage(buttonName,
	// sitePageObjectRepo));
	// }
	//
	// @Then("^verify the presence of the following data on site details page$")
	// public void
	// verify_the_presence_of_following_site_details_section_on_site_detail_grid_page(List<String>
	// listProps)
	// throws Throwable {
	// for (String propsName : listProps) {
	// propsName = propsName.replaceAll("( )+", "");
	// String message = "Property[" + propsName +
	// "] is not displayed on details section";
	// if (propsName.toLowerCase().contains("map") ||
	// propsName.toLowerCase().contains("satellite")) {
	// sitePage.assertElementDisplayed(sitePageObjectRepo("editSitePagePopupDetailMapSection"),
	// message);
	// } else if (propsName.toLowerCase().contains("date")) {
	// Assert.assertTrue("[" + propsName + "] label text is not as expected",
	// sitePage.getElementText(sitePageObjectRepo("editSitePagePopupDetail"
	// + propsName))
	// .contains(sitePageObjectText("editSitePagePopupDetail" + propsName +
	// "LabelText")));
	// } else {
	// Assert.assertTrue("[" + propsName + "] label text is not as expected",
	// sitePage.getElementText(sitePageObjectRepo("editSitePagePopupDetail"
	// + propsName))
	// .equals(sitePageObjectText("editSitePagePopupDetail" + propsName +
	// "LabelText")));
	// }
	// }
	// }
	//
	// @Then("^verify the availability of username, password field and login button for userRolePermissions Page$")
	// public void
	// Verify_The_Availability_Of_Username_Field_For_userRolePermissions_Page()
	// throws Throwable {
	// boolean flag1 = UserRolePermissionsPage
	// .verifyElementDisplayed(getProps().getProperty("adminUserName_Locator"));
	// boolean flag2 = UserRolePermissionsPage
	// .verifyElementDisplayed(getProps().getProperty("adminPassword_Locator"));
	// boolean flag4 = UserRolePermissionsPage
	// .verifyElementDisplayed(getProps().getProperty("adminLoginbutton_Locator"));
	//
	// if (flag1 && flag2 && flag4) {
	// Assert.assertTrue("Login Page loaded Successfully", true);
	// } else {
	// Assert.fail("Login Page not loaded Successfully");
	// }
	// }
	//
	// @When("^initialize dummy users in database for different roles and remove dependency with \"(.*?)\"$")
	// public void initialize_dummy_data_in_database_for_different_roles(String
	// tenantName, DataTable table)
	// throws Throwable {
	// List<String> lstData = table.asList(String.class);
	// ManageTestData objDelTestdata = new ManageTestData();
	// Assert.assertTrue("Error occured while deleting userRolePermission page test data",
	// objDelTestdata.manageTestData("userRolePermission_Delete", lstData,
	// tenantName));
	// for (String userName : lstData) {
	// Assert.assertTrue("Error occured while inserting userRolePermission page test data",
	// objDelTestdata.manageTestData("userRolePermission_Insert", userName));
	// }
	// }
	//
	// @When("^initialize dummy data in database for following site and tenant$")
	// public void
	// initialize_dummy_data_in_database_for_following_site_and_tenant(DataTable
	// table) throws Throwable {
	// List<String> lstData = table.asList(String.class);
	// BaseUtil.waitForSpinner();
	// ManageTestData objDelTestdata = new ManageTestData();
	// Assert.assertTrue("Error occured while deleting userRolePermission page test data",
	// objDelTestdata.manageTestData("userRolePermission_AssociatedSiteTenant_Delete",
	// lstData));
	// for (String userName : lstData) {
	// Assert.assertTrue("Error occured while inserting userRolePermission page test data",
	// objDelTestdata.manageTestData("userRolePermission_AssociatedSiteTenant_Insert",
	// userName));
	// }
	// }
	//
	// @When("^we will login with \"(.*?)\" credential and verify \"(.*?)\" tab$")
	// public void we_will_login_with_credential(String role, String tab,
	// DataTable table) throws Throwable {
	// List<String> lstData = table.asList(String.class);
	// String userName = lstData.get(0);
	// UserRolePermissionsPage.isDashboardTabAvailable(userName, tab,
	// );
	// }
	//
	// @Then("^verify that \"(.*?)\" button is not displayed for \"(.*?)\"$")
	// public void verify_that_button_is_not_displayed_for(String buttonName,
	// String roleName) throws Throwable {
	// UserRolePermissionsPage.verifyAddTenantButton(roleName,
	// );
	// }
	//
	// @Then("^User should be navigated to site page with a label saying \"(.*?)\"$")
	// public void
	// User_should_be_navigated_to_site_page_with_a_label_saying(String saveMsg)
	// throws Throwable {
	// String message = "Expected message not matched with actual message.";
	// Assert.assertEquals(message, saveMsg,
	// UserRolePermissionsPage.getElementText(getProps().getProperty("addSiteSaveMsg")));
	// }
	//
	// @Then("^enter \"(.*?)\" in user search field$")
	// public void enter_in_user_serach_field(String userName) throws Throwable
	// {
	// BaseUtil.enterText_userRolePermissionsPage("USER_SEARCH", userName,
	// );
	// }
	//
	// @Then("^select the user \"(.*?)\"$")
	// public void select_the_user(String userName) {
	// UserRolePermissionsPage.selectUserFromGrid_AuditPage(userName,
	// );
	// }
	//
	// @When("^we will login with \"(.*?)\" Credential and verify home page$")
	// public void we_will_login_with_Credentials_and_verify_home_page(String
	// roleName, DataTable table) throws Throwable {
	// List<String> lstData = table.asList(String.class);
	// String userName = lstData.get(0);
	// if (roleName.equalsIgnoreCase("C&I Customer Analyst")) {
	// UserRolePermissionsPage.isHomePageAvailable(userName,
	// );
	// if
	// (!UserRolePermissionsPage.checkIfClickable(getProps().getProperty("optionDropDown")))
	// {
	// Assert.fail("Not able to login in the portal for the user : " +
	// roleName);
	// }
	// }
	// }
	//
	// @Then("^enter the test data for the following fields to create new site for \"(.*?)\" role$")
	// public void
	// enter_the_test_data_for_the_following_fields_to_create_new_site(String
	// roleName, DataTable table)
	// throws Throwable {
	// BaseUtil.explicitWait(4000);
	// List<String> listElements = table.asList(String.class);
	// for (String field : listElements) {
	// UserRolePermissionsPage.enterTestData(field, roleName,
	// getProps().getProperty,
	// userRolePermissionsPageTestData);
	// }
	// }
	//
	// @Then("^verify the following tabs$")
	// public void verify_the_following_tabs(DataTable table) throws Throwable {
	// List<String> list = table.asList(String.class);
	// for (String tabList : list) {
	// String message = "Failed to verify tabs";
	// Assert.assertTrue(message,
	// UserRolePermissionsPage.verifyTabs(tabList,
	// getProps().getProperty, sitePageObjectRepo));
	// }
	// }
	//
	// @Then("^verify that \"(.*?)\" button is displayed for \"(.*?)\" role$")
	// public void verify_that_button_is_displayed_for_role(String button,
	// String roleName) throws Throwable {
	// String message = "Failed to verify that [" + button + "] button";
	// Assert.assertTrue(message,
	// UserRolePermissionsPage.verifyAddTenantButton(roleName,
	// ));
	// }
	//
	// @Then("^click on the \"(.*?)\" button for \"(.*?)\" role$")
	// public void click_on_the_button_for_role(String button, String roleName)
	// throws Throwable {
	// String message = "Failed to click on add endpoint button";
	// Assert.assertTrue(message,
	// endpointPage.clickOperationOnEndpointPage(button,
	// ));
	// }
	//
	// // @Then("^enter the values for new endpoint$")
	// // public void enter_the_values_for_new_endpoint() throws Throwable {
	// // String message = "Failed to create new endpoint";
	// // Assert.assertTrue(message,
	// // UserRolePermissionsPage.enterValues(getProps().getProperty,
	// // userRolePermissionsPageTestData));
	// // }
	//
	// @When("^click on Save button on add endpoint popup for Provisioner role$")
	// public void
	// click_on_Save_button_on_add_endpoint_popup_for_Provisioner_role() throws
	// Throwable {
	// BaseUtil.click(getProps().getProperty("endpointSaveButton"));
	// }
	//
	// @Then("^user should be navigated to Endpoint page with a label saying \"(.*?)\"$")
	// public void
	// user_should_be_navigated_to_Endpoint_page_with_a_abel_saying(String
	// saveMsg) throws Throwable {
	// BaseUtil.waitForSpinner();
	// BaseUtil.assertElementDisplayed(getProps().getProperty("addEndpointSaveMsg"),
	// "Message is not visible on the page");
	// String message = "Expected message not matched with actual message.";
	// Assert.assertTrue(message, saveMsg.equalsIgnoreCase(
	// UserRolePermissionsPage.getElementText(getProps().getProperty("addEndpointSaveMsg"))));
	// BaseUtil.waitForSpinner();
	// BaseUtil.explicitWait(5000);
	// }
	//
	// @When("^enter endpoint id \"(.*?)\" in search text box on the endpoint list page$")
	// public void
	// enter_endpoint_id_in_search_text_box_on_the_endpoint_list_page(String
	// endpointName) throws Throwable {
	// BaseUtil.enterText_ProvisionerRoleEndpointPage("ENDPOINT_SEARCH",
	// endpointName,
	// );
	// }
	//
	// @Then("^user should be navigated to endpoints details page$")
	// public void user_should_be_navigated_to_endpoints_list_page() {
	// BaseUtil.explicitWait(5000);
	// String message = "Failed to verify the availability of endpoint grid";
	// BaseUtil.assertElementDisplayed(getProps().getProperty("endpointGrid"),
	// message);
	// }
	//
	// @When("^user modify type field$")
	// public void user_modify_field(String type) throws Throwable {
	// String message = "Failed to modify fields of endpoint";
	// Assert.assertTrue(message,
	// UserRolePermissionsPage.modify_Endpoint(getProps().getProperty,
	// userRolePermissionsPageTestData));
	// }
	//
	// @Then("^add tenant popup should be displayed with following fields for CI Customer Ananlyst Role$")
	// public void
	// add_tenant_popup_should_be_displayed_with_following_fields_for_CI_Customer_Ananlyst_Role(
	// List<String> lstAddTenantFields) throws Throwable {
	// for (String sField : lstAddTenantFields) {
	// String message = "Failed to display the field[" + sField +
	// "] on add tenant popup.";
	// Assert.assertTrue(message, UserRolePermissionsPage
	// .isElementDisplayedOnTenantPageUserRolePermissionsPage(sField,
	// ));
	// }
	// }
	//
	// @When("^enter the values for the following$")
	// public void enter_the_value_for_the_following(DataTable table) throws
	// Throwable {
	// List<Map<String, String>> lstData = table.asMaps(String.class,
	// String.class);
	// String parentName = lstData.get(0)("Parent").trim();
	// String hostName = lstData.get(0)("Hostname").trim();
	// String tenantName = lstData.get(0)("Name").trim();
	// String message =
	// "Failed to Enter the value in field [Name] on add tenant popup.";
	// Assert.assertTrue(message, BaseUtil.enterText_EndpointTenant("Name",
	// tenantName,
	// ));
	// message =
	// "Failed to Enter the value in field [Hostname] on add tenant popup.";
	// Assert.assertTrue(message, BaseUtil.enterText_EndpointTenant("Hostname",
	// hostName,
	// ));
	// message =
	// "Failed to Enter the value in field [Parent] on add tenant popup.";
	// Assert.assertTrue(message, BaseUtil.enterText_EndpointTenant("Parent",
	// parentName,
	// ));
	// }
	//
	// @When("^check the 'Inherit from parent tenant' Checkbox against hostname$")
	// public void
	// check_the_Inherit_from_parent_tenant_Checkbox_against_Hostname() throws
	// Throwable {
	// UserRolePermissionsPage.checkCheckBox(getProps().getProperty("addTenantPopupInheritCheckBox"));
	// }
	//
	// @Then("^a valid hostname should be displayed as per the Parent Selected$")
	// public void
	// a_valid_hostname_should_be_displayed_as_per_the_parent_Selected(DataTable
	// table) throws Throwable {
	// List<Map<String, String>> lstData = table.asMaps(String.class,
	// String.class);
	// String message = "Failed to display valid hostname[" +
	// lstData.get(0)("Hostname").trim()
	// + "] as per selected parent[" + lstData.get(0)("Parent").trim() +
	// "].";
	// Assert.assertTrue(message,
	// UserRolePermissionsPage.verifyText_EndpointTenantPage("Hostname",
	// lstData.get(0)("Hostname").trim(),
	// ));
	// }
	//
	// @And("^Verify \"(.*?)\" field is Disabled$")
	// public void Verify_field_is_disabled(String sFieldName) throws Throwable
	// {
	// String message = "Failed to verify that [" + sFieldName +
	// "] is disabled.";
	// Assert.assertFalse(message,
	// UserRolePermissionsPage.isElementEnabledOnTenantPage(sFieldName,
	// ));
	// }
	//
	// @Then("^User should be navigated to Tenant page with a label saying \"(.*?)\"$")
	// public void
	// User_should_be_navigated_to_Tenants_page_with_a_label_saying_Tenant_tenant_name_saved(
	// String sTenantSaveMsg) throws Throwable {
	// BaseUtil.waitForSpinner();
	// BaseUtil.explicitWait(5000);
	// String message = "Expected message not matched with actual message.";
	// Assert.assertEquals(message, sTenantSaveMsg,
	// UserRolePermissionsPage.getElementText(getProps().getProperty("addTenantSaveMsg")));
	// }
	//
	// @Then("^associate following tenant and site with this$")
	// public void associate_following_tenant_and_site_with_this(DataTable
	// table) throws Throwable {
	// List<String> lstData = table.asList(String.class);
	// for (String name : lstData) {
	// String message = "Failed to associate tenant and site with this";
	// Assert.assertTrue(message,
	// UserRolePermissionsPage.associateSiteTenant(name,
	// getProps().getProperty, userRolePermissionsPageTestData));
	// }
	// }
	//
	// @Then("^verify that Edit button is not displayed$")
	// public void verify_that_Edit_button_is_not_displayed() throws Throwable {
	// String message = "Failed to verify the display of edit button";
	// Assert.assertTrue(message,
	// !UserRolePermissionsPage.verifyElementDisplayed(getProps().getProperty("editButton")));
	// }
	//
	// @When("^click on Control tab and verify the control grid$")
	// public void click_on_Control_tab_verify_the_control_grid() throws
	// Throwable {
	// BaseUtil.click(getProps().getProperty("controlTab"));
	// String message = "Failed to verify the control grid";
	// Assert.assertTrue(message, UserRolePermissionsPage
	// .verifyElementDisplayed(getProps().getProperty("controlTabGrid")));
	// }

	@After("@UserRolePermissionsPage")
	public void afterClass(Scenario scenario) {
		BaseUtil.closeResources(scenario);
	}
	
	// TODO : added by k.currently commenting to avoid compilation issues....verify
	// later removed by k only
//	@Given("^click on \"(.*?)\" Tab$")
//	public void click_on_following_Tab(String tab) throws Throwable {
//		try {
//			switch (tab.toUpperCase()) {
//			case "SITES":
//				tenantPage.click(tenantPageObjectRepo.get("sitesTab"));
//				break;
//			case "TENANTS":
//				tenantPage.explicitWait(3000);
//				tenantPage.click(tenantPageObjectRepo.get("tenantTab"));
//				break;
//			case "USERS":
//				tenantPage.click(tenantPageObjectRepo.get("userTab"));
//				break;
//			case "DASHBOARD":
//				tenantPage.click(tenantPageObjectRepo.get("dashboardTab"));
//				break;
//			case "ENDPOINTS":
//				tenantPage.click(tenantPageObjectRepo.get("endpointTab"));
//				tenantPage.waitForSpinner();
//				tenantPage.explicitWait(60000);
//				tenantPage.waitForSpinner();
//				tenantPage.explicitWait(20000);
//				break;
//			case "DATAFIX":
//				tenantPage.click(tenantPageObjectRepo.get("datafixTab"));
//				break;
//			case "COMMISSIONING":
//				tenantPage.click(tenantPageObjectRepo.get("commissioningTab"));
//				break;
//			}
//		} catch (Exception e) {
//			Assert.fail("Failed to Navigate on the following Page");
//		} finally {
//			tenantPage.waitForSpinner();
//		}

//	}
	
	//TODO : added by k.currently commenting to avoid compilation issues....verify
//	@When("^click \"(.*?)\" button on endpoint popup$")
//	public void click_button_on_endpoint_popup(String buttonName) throws Throwable {
//		String message = "Failed to click on button :" + buttonName;
//		Assert.assertTrue(message, endpointPage.clickOperationOnEndpointPage(buttonName, endpointPageObjectRepo));
//		endpointPage.explicitWait(2000);
//	}
	
//	@Then("^close alert \"(.*?)\"$")
//	public void close_alert() throws Throwable {
//		userRolePermissionsPage.SendKeys(Keys.ENTER);
//		throw new PendingException();
//	}
	
//	@Then("^click on \"(.*?)\" for edit endpoint page$")
//	public void click_on_for_edit_endpoint_page(String sElement) throws Throwable {
//		String message = "Failed to click tab" + sElement;
//		Assert.assertTrue(message, endpointPage.clickOperationOnEndpointpage(sElement, endpointPageObjectRepo));
//	}

	
//	 TODO : Added by sonu on 11 sep 2018.... curretly commenting it to avoid compilation issues....verify
//	@When("^initialize dummy users in database for Partner Commissioner roles$")
//	public void initialize_dummy_users_in_database_for_Partner_Commissioner_roles(String tenantName, DataTable table)
//			throws Throwable {
//		List<String> lstData = table.asList(String.class);
//		DeleteTestData objDelTestdata = new DeleteTestData();
//		Assert.assertTrue("Error occured while deleting userRolePermission page test data",
//				objDelTestdata.deleteTestData("userRolePermission_Delete", lstData, tenantName));
//		for (String userName : lstData) {
//			Assert.assertTrue("Error occured while inserting userRolePermission page test data",
//					objDelTestdata.deleteTestData("userRolePermission_Insert", userName));
//		}
//	}
//	
//	//Sonu
//	
//		@Then("^enter valid values for following fields for the site which is \"(.*?)\" for \"(.*?)\"$")
//		public void enter_valid_values_for_following_field_for_site_which_is_for(String siteType, String tenantName, DataTable arg1) throws Throwable {
//			List<String> fieldList = arg1.asList(String.class);
//			for (String list : fieldList) {
//				userRolePermissionsPage.enterTestDataPartnerComm(siteType,tenantName, list, userRolePermissionsPageObjectRepo);
//			}
//		}
//		
//		@Then("^select the following check boxes for Site Page for Partner Commissioner$")
//		public void select_the_following_check_boxes_for_Site_Page_for_Partner_Commissioner(List<String> checkBoxList) throws Throwable {
//			userRolePermissionsPage.explicitWait(1000);
//			userRolePermissionsPage.checkCheckBox(checkBoxList, userRolePermissionsPageObjectRepo);
//		}
//		
//		@Then ("^select tenant \"(.*?)\" from tenant combo Box$")
//		public void select_tenant_from_tenant_combo_Box(String tenantName) throws Throwable {
//			userRolePermissionsPage.clickAndWait(userRolePermissionsPageObjectRepo.get("tenantDropDownPermissionAccordion"));
//			userRolePermissionsPage.selectComboBoxDropDownByValue(userRolePermissionsPageObjectRepo.get("editUserTenantDropdown"),tenantName);
//		}
//		
//		@When("^click on \"(.*?)\" checkbox in Available Sites area on Permission Accordion$")
//		public void click_on_checkbox_in_Available_Sites_area_on_PermissionAccordion(String dropdown) throws Throwable {
//			String message = "Failed to click on checkbox :" + dropdown + " " + " on User Page";
//			Assert.assertTrue(message, userPage.clickOperationOnUserPage(dropdown, userPageObjectRepo));
//			userPage.explicitWait(2000);
//		}
//		
//		@When("^click on \"(.*?)\" button on add user popup on Permission Accordion$")
//		public void click_on_button_on_add_user_popup_on_Permission_Accordion(String sButtonName) throws Throwable {
//			String message = "Failed to click on button :" + sButtonName + " " + " on User Page";
//			Assert.assertTrue(message, userPage.clickOperationOnUserPage(sButtonName, userPageObjectRepo));
//			userPage.explicitWait(3000);
//		}
//		
//		@When("^user modify following fields with site \"(.*?)\"$")
//		public void user_modify_following_fields_with_site(String siteName,List<String> sFields) throws Throwable {
//			for (String fields : sFields) {
//				String message = "Failed to modify fields of endpoint";
//				Assert.assertTrue(message,
//						userRolePermissionsPage.associate_TenantsAndSites(fields,siteName, userRolePermissionsPageObjectRepo, userRolePermissionsPageTestData));
//			}
//		}
//		
//		@And("^verify following endpoints should be shown in the list$")
//		public void verify_following_endpoints_should_be_shown_in_the_list(List<String> endpointList) throws Throwable {
//			for (String endpointName : endpointList) {
//				String message = "Failed to verify expected endpoint";
//				Assert.assertTrue(message,
//						userRolePermissionsPage.verifyExpectedEndpoint(endpointName, userRolePermissionsPageObjectRepo));
//			}
//		}
//		
//		@And("^verify following Sites should be shown in the list$")
//		public void verify_following_Sites_should_be_shown_in_the_list(List<String> siteList) throws Throwable {
//			for (String siteName : siteList) {
//				String message = "Failed to verify expected sites";
//				Assert.assertTrue(message,
//						userRolePermissionsPage.verifyExpectedSite(siteName, userRolePermissionsPageObjectRepo));
//			}
//		}
//		
//		@Then("^select the entered endpoint id \"(.*?)\" from the endpoint details grid page which is associated with Provisioning Site$")
//		public void select_the_entered_endpoint_id_from_the_endpoint_details_grid_page_which_is_associated_with_Provisioning_Site(String sEndpointName) {
//			userRolePermissionsPage.selectEndpointFromGrid_EndpointPage(sEndpointName, endpointPageObjectRepo);
//			// endpointPage.waitForSpinner();
//		}
//		
//		@Then("^verify a pop up for Site Assosiation is displayed with message \"(.*?)\"$")
//		public void verify_a_pop_up_for_Site_Assosiation_is_displayed_with_message(String popupMessage) throws Throwable {
//			String message = "Failed to verify popup message on SIte association popuop";
//			Assert.assertTrue(message,
//					userRolePermissionsPage.verifypopupMessage(popupMessage, userRolePermissionsPageObjectRepo));
//		}
//		  
//		@Then("^verify succussfull message \"(.*?)\" after associateing with any site$")
//		public void verify_succussfull_message_after_associateing_with_any_site(String succussfullMessage) {
//			String message = "Failed to verify succussfull message";
//			Assert.assertTrue(message,
//					userRolePermissionsPage.verifSuccussfullMessage(succussfullMessage, userRolePermissionsPageObjectRepo));
//		}
//		
//		@Then("^click on \"(.*?)\" button on Site Association Model$")
//		public void click_on_button_on_Site_Association_Model(String buttonName) throws Throwable {
//			userRolePermissionsPage.click(userRolePermissionsPageObjectRepo.get("okButtonSiteAssociationModel"));
//		}
//		
//		@Then("^a block should appear saying \"(.*?)\"$")
//		public void a_block_should_appear_saying(String message) throws Throwable {
//			boolean flg = false;
//			for (int i = 0; i < PathConstants.defaultTimeout / PathConstants.defaultPollingFrequency; i++) {
//				if (userRolePermissionsPage.instantElementCheck("Display", By.xpath("//*[contains(text(), '" + message + "')]"))) {
//					flg = true;
//					break;
//				}
//			}
//			if (flg) {
//				if (message.equals("Endpoint is succussfully associated to a site")) {
//					Assert.assertTrue("Block message is not matched with the actual message",
//							userRolePermissionsPage.getElementText(userRolePermissionsPageObjectRepo.get("savedValueBlock")).equals(message));
//				} 
//			} else {
//				userRolePermissionsPage.getElementAfterLoaded("xpath===//*[@id='commsHeader']/div[1]/div/ul/li/div").click();
//				Assert.fail("'" + message + "' not displayed");
//			}
//			userRolePermissionsPage.explicitWait(2000);
//			userRolePermissionsPage.getElementAfterLoaded("xpath===//*[contains(text(), '" + message + "')]").click();
//		}
		
}
