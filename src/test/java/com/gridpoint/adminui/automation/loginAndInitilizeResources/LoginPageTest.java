package com.gridpoint.adminui.automation.loginAndInitilizeResources;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;

import com.gridpoint.adminui.automation.testdata.ManageTestData;
import com.gridpoint.automation.constants.PathConstants;
import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.util.BaseUtil;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LoginPageTest extends CommonInit {

	private Logger logger = Logger.getLogger(LoginPageTest.class);

	Map<String, String> readResource;

	@Then("^\"(.*?)\" the user used as test data$")
	public void delete_the_user_used_as_test_data(String action,
			List<String> listUserTestData) throws Throwable {
		int i = 0;
		BaseUtil.waitForSpinner();
		ManageTestData objDelTestdata = new ManageTestData();
		if (action.equals("delete")) {
			for (String userName : listUserTestData) {
				Assert.assertTrue(
						"Error occured while deleting test Data for user page",
						objDelTestdata.manageTestData("Delete_Resources",
								userName.trim(), getTestBase()));
				logger.info("*****(" + userName
						+ ")User has been deleted successfully*****");
			}
		} else if (action.equalsIgnoreCase("add")) {
			for (String userName : listUserTestData) {
				i++;
				logger.info("User Name : " + userName);
				if (objDelTestdata.manageTestData("User_Present", userName,
						getTestBase())) {
					logger.info("*****Test User(" + userName
							+ ") is already present*****");
					continue;
				}
				BaseUtil.waitForSpinner(2000);
				String addUser = "Add User", tabName = "Users Tab";
				if (BaseUtil.assertElementDisplayed(
						getProps().getProperty("userTab"), "Tab [" + tabName
								+ "] is not visible")) {
					BaseUtil.click(getProps().getProperty("userTab"));
				}
				BaseUtil.waitForSpinner(1000);
				if (BaseUtil.assertElementDisplayed(
						getProps().getProperty("UsersAddUserButton"),
						"Button [" + addUser + "] is not visible")) {
					BaseUtil.click(getProps().getProperty("UsersAddUserButton"));
				}
				BaseUtil.waitForSpinner(1000);
				BaseUtil.waitTillElementIsVisible(
						getProps().getProperty("userNameField"), 1200);
				BaseUtil.enterText(getProps().getProperty("userNameField"),
						getProps().getProperty("addUserName" + i + "TestData"));
				BaseUtil.enterText(getProps().getProperty("firstNameField"),
						getProps().getProperty("addUserFirstNameTestData"));
				BaseUtil.enterText(getProps().getProperty("lastNameField"),
						getProps().getProperty("addUserLastNameTestData"));
				BaseUtil.enterText(getProps().getProperty("emailField"),
						getProps().getProperty("addUserEmailTestData"));
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("roleField"), getProps()
								.getProperty("addUserRole" + i + "TestData"));
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("addUserUnitOfTemparature"),
						getProps().getProperty(
								"addUserUnitOfTemparatureTestData"));
				BaseUtil.click(getProps().getProperty("UsersSaveButton"));
				BaseUtil.waitForSpinner(1000);
				if (userName.equals("autoCSM_DisabledTest")) {
					BaseUtil.unCheckCheckBox(getProps().getProperty(
							"UsersEnabledCheckBox"));
					BaseUtil.click(getProps().getProperty(
							"UsersSaveButton"));
					BaseUtil.waitForSpinner(1000);
				}
				BaseUtil.waitTillElementIsVisible(
						getProps().getProperty("UsersPermissionAccordion"), 600);
				BaseUtil.click(getProps().getProperty(
						"UsersPermissionAccordion"));
				BaseUtil.waitForElementEnabled(getProps().getProperty(
						"UsersAddAllButton"));
				BaseUtil.explicitWait(PathConstants.WAIT_VERY_LOW);
				
				BaseUtil.waitForSpinner(2000);
				if (BaseUtil.isElementClickable(getProps().getProperty(
						"UsersAddAllButton"))) {
					BaseUtil.click(getProps().getProperty("UsersAddAllButton"));
				}
				BaseUtil.explicitWait(PathConstants.WAIT_HIGH);
				BaseUtil.click(getProps().getProperty(
						"UsersSavePermissionButton"));
				BaseUtil.waitForSpinner(1000);
				BaseUtil.waitTillElementIsVisible(
						getProps().getProperty("UsersPopupCloseButton"), 1000);
				BaseUtil.click(getProps().getProperty("UsersPopupCloseButton"));
				BaseUtil.waitForSpinner(1000);

				ManageTestData manageData = new ManageTestData();
				Assert.assertTrue(
						"Failed to update the password for the User ("
								+ userName + ")", manageData.manageTestData(
								"User_Password_Reset", userName, getTestBase()));
				logger.info("*****(" + userName
						+ ")User has been created successfully*****");
			}
		}
	}
	
	@Given("^we are on login page and we use valid username and password for login$")
	public void Insert_Credentials(DataTable table) {
		List<Map<String, String>> lstData = table.asMaps(String.class,
				String.class);
		BaseUtil.enterText(getProps().getProperty("adminUserName_Locator"),
				lstData.get(0).get("username").trim());
		BaseUtil.enterText(getProps().getProperty("adminPassword_Locator"),
				lstData.get(0).get("password").trim());
		BaseUtil.click(getProps().getProperty("adminLoginbutton_Locator"));
	}
	
	@Then("^click on the logout button and verify the login page is displayed$")
	public void Verify_Logout_After_Successful_Login()
			throws InterruptedException {
		BaseUtil.explicitWait(500);
		BaseUtil.click(getProps().getProperty("optionDropDown"));
		BaseUtil.explicitWait(1000);
		BaseUtil.click(getProps().getProperty("logout"));
		String message = "Error while logging out";
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("adminUserName_Locator"), message);
	}

	@Given("^user is on login page to perform \"(.*?)\"$")
	public void user_is_login_page_to_perform(String arg1) throws Throwable {
		BaseUtil.waitForSpinner();
	}

	@Then("^verify the availability of username, password field and login button on login page$")
	public void Verify_The_Availability_Of_Username_Field() throws Throwable {
		BaseUtil.explicitWait(3000);
		BaseUtil.explicitWait(3000);
		boolean flag1 = BaseUtil.verifyElementDisplayed(getProps().getProperty(
				"adminUserName_Locator"));
		boolean flag2 = BaseUtil.verifyElementDisplayed(getProps().getProperty(
				"adminPassword_Locator"));
		boolean flag4 = BaseUtil.verifyElementDisplayed(getProps().getProperty(
				"adminLoginbutton_Locator"));

		if (!(flag1 && flag2 && flag4)) {
			Assert.fail("Login Page not loaded Successfully");
		}
	}

	@Then("^verify the error message with username and password below$")
	public void Verify_the_error_message_with_username_and_Password_given_below(
			DataTable table) {
		List<Map<String, String>> values = table.asMaps(String.class,
				String.class);
		BaseUtil.waitForSpinner(3000);
		BaseUtil.explicitWait(2000);
		// BaseUtil.checkPageIsReady(readResource.get("loginspinner"),
		for (Map<String, String> value : values) {
			BaseUtil.enterText(
					getProps().getProperty("adminUserName_Locator"),
					value.get("username").trim().equals("RANDOM") ? RandomStringUtils
							.randomAlphabetic(5) : value.get("username").trim());
			BaseUtil.enterText(
					getProps().getProperty("adminPassword_Locator"),
					value.get("password").trim().equals("RANDOM") ? RandomStringUtils
							.randomAlphabetic(5) : value.get("password").trim());
			BaseUtil.explicitWait(3000);
			BaseUtil.explicitWait(3000);
			// BaseUtil.waitForSpinner(3000);
			// BaseUtil.waitTillElementIsVisible(getProps().getProperty("adminLoginbutton_Locator"),
			BaseUtil.click(getProps().getProperty("adminLoginbutton_Locator"));
			BaseUtil.explicitWait(3000);
			String errorMessage = "Error message is not as expected.";
			Assert.assertTrue(
					errorMessage,
					value.get("message").equals(
							BaseUtil.getElementText(
									getProps().getProperty(
											"invalidUsernamePassword")).trim()));

		}
	}

	

	@Then("^verify the successful login by checking the availability of home tab on portal home page$")
	public void Verify_Login_On_Portal_With_Non_Admin_credentials() {
		String message = "Unsuccessful login to portal with non admin credentials";
		BaseUtil.waitForSpinner(6000);
		BaseUtil.explicitWait(2000);
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("portalHomeTab"), message);
	}

	

	@Then("^verify the successful login by checking the availability of logout button on site page$")
	public void Verify_Succeful_Login_With_Valid_Credentials() throws Throwable {
		String message = "Unsuccessful login with valid credentials";
		BaseUtil.waitForSpinner();
		BaseUtil.assertElementDisplayed(getProps()
				.getProperty("optionDropDown"), message);
	}

	@After("@LoginPage")
	public void afterClass(Scenario scenario) {
		BaseUtil.closeResources(scenario);
	}

	public boolean clickButtons(String sButtonName,
			Map<String, String> objectRepo) {
		String locator = null;
		try {
			switch (sButtonName.toUpperCase()) {
			case "USERS TAB":
				locator = objectRepo.get("userTab");
				break;
			case "ADD USER":
				locator = objectRepo.get("UsersAddUserButton");
				break;
			default:
				logger.error("Switch Case["
						+ sButtonName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid element name : " + sButtonName
						+ " selected");
			}
			BaseUtil.clickAndWait(locator);
			return true;
		} catch (Exception e) {
			logger.error("Failed to click on button :" + sButtonName
					+ " , detail message: " + e.getMessage());
			Assert.fail(e.getMessage());
		}
		return false;
	}

}
