package com.gridpoint.adminui.automation.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.gridpoint.automation.constants.PathConstants;
import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.util.BaseUtil;

public class UserPage extends CommonInit {
	private Logger logger = Logger.getLogger(UserPage.class);

	public boolean verifyElementsUserPage(String elementName) {
		String locator = null;
		try {
			switch (elementName.toUpperCase()) {
			case "USERNAME":
				locator = getProps().getProperty("gridHeaderUsername");
				break;
			case "EMAIL":
				locator = getProps().getProperty("gridHeaderUserEmail");
				break;
			case "FIRST NAME":
				locator = getProps().getProperty("gridHeaderFirstName");
				break;
			case "LAST NAME":
				locator = getProps().getProperty("gridHeaderLastName");
				break;
			case "ENABLED":
				locator = getProps().getProperty("gridHeaderEnabled");
				break;
			case "LOCKED":
				locator = getProps().getProperty("gridHeaderLocked");
				break;
			case "ROLE":
				locator = getProps().getProperty("gridHeaderRole");
				break;
			case "*":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"StarMark"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"StarMarkWithTenantName"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"ActualStarMark"));
				Assert.assertTrue(
						elementName + "is not present on UI",
						BaseUtil.getElementText(
								getProps().getProperty("ActualStarMark"))
								.contains(elementName.trim()));
				return true;
			case "PROVISIONING":
				locator = getProps().getProperty("StarMark");
				break;
			case "PROPERTIES":
				locator = getProps().getProperty(
						"editUserPagePropertiesSection");
				break;
			default:
				logger.error("Switch Case["
						+ elementName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid column name: "
						+ elementName
						+ ", Please pass valid column name e.g.[Username | Email | First Name | Last Name | Enabled | Locked | Role] from user feature file or test class.");
			}

			return BaseUtil.verifyElementDisplayed(locator);
		} catch (Exception e) {
			logger.error("Failed to verify element :" + elementName
					+ " , detail message: " + e.getMessage());
			return false;
		}

	}
	public boolean clickOperationOnUserPage(String sButtonName) {
		String locator = null;
		try {
			switch (sButtonName.toUpperCase()) {
			case "ADD USER":
				locator = getProps().getProperty("addUserButton");
				break;
			case "SAVE":
				BaseUtil.clickAndWait(getProps().getProperty("saveButton"));
				BaseUtil.waitForSpinner();
				return true;
			case "PERMISSION":
				BaseUtil.clickAndWait(getProps().getProperty(
						"editUserModelPermissionAccordion"));
				BaseUtil.explicitWait(1000);
				return true;
			case "ADD ALL":
				BaseUtil.explicitWait(3000);
				BaseUtil.clickAndWait(getProps().getProperty("AddAllButton"));
				BaseUtil.explicitWait(1000);
				// BaseUtil.verifyElementEnabled(getProps().getProperty("RemoveButton"));
				return true;
			case "SELECTED SITES":
				locator = getProps().getProperty("SelectedSites");
				break;
			case "REMOVE":
				locator = getProps().getProperty("RemoveButton");
				break;
			case "PROVISIONING":
				locator = getProps().getProperty("ProvisioningSiteCheckbox");
				break;
			case "ADD":
				locator = getProps().getProperty("AddButton");
				break;
			case "PERMISSION:SAVE":
				locator = getProps().getProperty("savePermissionUserBtn");
				break;
			case "CANCEL":
				locator = getProps().getProperty("cancelButton");
				break;
			case "SAVE AND CLOSE":
				BaseUtil.clickAndWait(getProps().getProperty(
						"editUsersaveAndCloseUserBtn"));
				BaseUtil.explicitWait(2000);
				return true;
			case "CAPABILITIES":
				BaseUtil.clickAndWait(getProps().getProperty(
						"userCapabilitiesAccordion"));
				BaseUtil.explicitWait(2000);
				return true;
			case "CAPABILITIES:SAVE AND CLOSE":
				locator = getProps().getProperty(
						"saveAndCloseCapabilitiesUserBtn");
				break;
			case "X":
				BaseUtil.clickAndWait(getProps().getProperty(
						"UserPopupCloseButton"));
				BaseUtil.explicitWait(3000);
				return true;

			case "HIDE DISABLED":
				BaseUtil.click(getProps()
						.getProperty("userinActiveUsersChkBox"));
				return true;
			case "TENANT:HIDE DISABLED":
				BaseUtil.click(getProps().getProperty(
						"TenantshideInactiveCheckBox"));
				return true;
			case "OK":
				BaseUtil.clickAndWait(getProps().getProperty(
						"addUserPopupOkButton"));
				return true;
			case "CAPABILITIES:SAVE":
				BaseUtil.clickAndWait(getProps().getProperty(
						"saveCapabilitiesUserBtn"));
				return true;
			case "LOGOUT":
				BaseUtil.clickAndWait(getProps().getProperty(
						"addUseroptionDropDown"));
				BaseUtil.clickAndWait(getProps().getProperty("addUserlogout"));
				return true;
			case "SEARCH":
				BaseUtil.clickAndWait(getProps()
						.getProperty("userSearchButton"));
				return true;
			case "RESET PASSWORD":
				BaseUtil.clickAndWait(getProps().getProperty(
						"addUserResetPasswordButton"));
				return true;
			case "CANCEL_RESET":
				BaseUtil.clickAndWait(getProps().getProperty(
						"userPageResetPasswordCancelButton"));
				return true;
			case "SUBMIT_RESET":
				BaseUtil.clickAndWait(getProps().getProperty(
						"userPageResetPasswordSubmitButton"));
				return true;
			case "TENANT":
				BaseUtil.clickAndWait(getProps().getProperty("TenantDropdown"));
				return true;
			case "GRIDPOINT TEST AUTOMATION":
				BaseUtil.clickAndWait(getProps().getProperty(
						"GridpointTestAutomation"));
				return true;
			case "PERMISSION:SAVE AND CLOSE":
				BaseUtil.clickAndWait(getProps().getProperty(
						"saveAndClosePermissionUserBtn"));
				return true;
			default:
				logger.error("Switch Case["
						+ sButtonName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}

			BaseUtil.clickAndWait(locator);
			return true;
		} catch (Exception e) {
			logger.error("Failed to click on button :" + sButtonName
					+ " , detail message: " + e.getMessage());
			return false;
		}
	}

	public boolean isElementEnabledOnUserPage(String elementName) {
		String locator = null;
		try {
			switch (elementName.toUpperCase()) {
			case "SAVE":
				locator = getProps().getProperty("saveButton");
				break;
			case "CANCEL":
				locator = getProps().getProperty("cancelButton");
				break;
			case "SEND WELCOME EMAIL":
				locator = getProps().getProperty("SendWelcomeEmailButton");
				break;
			case "REMOVE":
				locator = getProps().getProperty("RemoveButton");
				break;
			case "ADD ALL":
				locator = getProps().getProperty("AddAllButton");
				break;
			case "PROVISIONING CHECKBOX":
				// assertElementDisplayed(getProps().getProperty("ProvisioningSiteCheckboxDisable"),
				// elementName + " is enabled but it should be disabled");
				// return true;
				locator = getProps().getProperty(
						"ProvisioningSiteCheckboxDisable");
				break;
			case "SAVE AND CLOSE":
				locator = getProps().getProperty("editUsersaveAndCloseUserBtn");
				break;
			case "ROLE":
				locator = getProps().getProperty("addUserRoleField");
				break;

			case "RESET PASSWORD":
				locator = getProps().getProperty("addUserResetPasswordButton");
				break;
			default:
				logger.error("Switch Case["
						+ elementName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}

			return BaseUtil.verifyElementEnabled(locator);
		} catch (Exception e) {
			logger.error("Failed to verify that Element [" + elementName
					+ "] is enabled.  see detail message: " + e.getMessage());
			return false;
		}
	}

	public void enterTestData(String userNo, String fieldName) {
		String locator = null;
		String testData = null;
		String flag = null;
		try {
			switch (fieldName.toUpperCase()) {
			// case "USER_SEARCH":
			// flag = "SearchField";
			// locator = getProps().getProperty("userSearchField");
			// testData = getProps().getProperty("username");
			// break;
			case "USERNAME":
				flag = "Field";
				locator = getProps().getProperty("userNameField");
				if (userNo.equalsIgnoreCase("first")) {
					testData = getProps().getProperty("addUserName1TestData");
				}
				else if(userNo.equalsIgnoreCase("@!-*:=_")){
					testData = getProps().getProperty("addUserNameValidSpecialChar");
				}
				else if(userNo.equalsIgnoreCase("$  & + .")){
					testData = getProps().getProperty("addUserNameInValidSpecialChar");
				}
				else {
					testData = getProps().getProperty("addUserName2TestData");
				}
				break;
			case "FIRST NAME":
				flag = "Field";
				locator = getProps().getProperty("firstNameField");
				testData = getProps().getProperty("addUserFirstNameTestData");
				break;
			case "LAST NAME":
				flag = "Field";
				locator = getProps().getProperty("lastNameField");
				testData = getProps().getProperty("addUserLastNameTestData");
				break;
			case "EMAIL":
				flag = "Field";
				locator = getProps().getProperty("emailField");
				testData = getProps().getProperty("addUserEmailTestData");
				break;
			case "ROLE":
				flag = "Dropdown";
				locator = getProps().getProperty("roleField");
				testData = getProps().getProperty("addUserRole1TestData");
				break;
			case "UNIT OF TEMPERATURE":
				flag = "Dropdown";
				locator = getProps().getProperty("unitOfTemperatureField");
				if (userNo.equalsIgnoreCase("first")) {
					testData = getProps().getProperty(
							"unitOfTemperature1TestData");
				} else {
					testData = getProps().getProperty(
							"unitOfTemperature2TestData");
				}
				break;

			default:
				logger.error("Switch Case["
						+ fieldName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Failed to enter test data  in field [" + fieldName
						+ "] ");
			}
			if (flag.equalsIgnoreCase("Field")) {
				BaseUtil.enterText(locator, testData);
			} else if (flag.equalsIgnoreCase("Dropdown")) {
				BaseUtil.selectDropDownByValue(locator, testData);
			}

		} catch (Exception e) {
			logger.error("Failed to enter the value in the [" + fieldName
					+ "] field in site page. see detail error message : \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to enter the value in the [" + fieldName
					+ "] field in site page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
	}

	public void enterText_UserPage(String sElementName, String sText) {
		String sCaseOptions = "sElementName, ";
		try {
			switch (sElementName.toUpperCase()) {
			case "USER_SEARCH":
				BaseUtil.enterText(getProps().getProperty("userSearchField"),
						sText);
				BaseUtil.waitForSpinner();
				break;
			default:
				logger.error("Switch Case["
						+ sElementName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Failed to enter the text in field ["
						+ sElementName
						+ "] because you passed wrong parameter["
						+ sElementName
						+ "] either in feature file or test class. \n please choose valid parameter from the following list: \n ["
						+ sCaseOptions + "]");
			}
		} catch (Exception e) {
			logger.error("Failed to enter the value in the [" + sElementName
					+ "] field in site page. see detail error message : \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to enter the value in the [" + sElementName
					+ "] field in site page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
	}

	public void selectUserFromGrid_UserPage(String userName) {
		boolean bResult = false;
		logger.debug("Selecting the user :" + userName);
		try {
			List<WebElement> lstUserNames = BaseUtil
					.getMultipleElementsAfterLoaded(getProps().getProperty(
							"gridUserNameColumn"));
			for (WebElement webElement : lstUserNames) {
				if (webElement.getText().trim().equalsIgnoreCase(userName)) {
					webElement.click();
					// BaseUtil.waitForSpinner();
					bResult = true;
					break;
				}
			}
			if (!bResult) {
				Assert.fail("Given test user is not available");
			}
			logger.debug("user :" + userName + " is selected.");
		} catch (Exception e) {
			logger.error("Failed to select user: " + userName
					+ " from user grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the user : " + userName
					+ ", see detail error message: \n"
					+ e.getStackTrace().toString());
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public void clickPaginationUserPage(String sLinkName) {
		try {
			switch (sLinkName.toUpperCase()) {
			case "NEXT":
				Assert.assertTrue(
						"Required data is not present. Next button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"userPaginationNext")));
				BaseUtil.click(getProps().getProperty("userPaginationNext"));
				break;
			case "PREV":
				Assert.assertTrue(
						"Required data is not present. Prev button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"userPaginationPrev")));
				BaseUtil.click(getProps().getProperty("userPaginationPrev"));
				break;
			case "LAST":
				Assert.assertTrue(
						"Required data is not present. Last button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"userPaginationLast")));
				BaseUtil.click(getProps().getProperty("userPaginationLast"));
				break;
			case "FIRST":
				Assert.assertTrue(
						"Required data is not present. First button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"userPaginationFirst")));
				BaseUtil.click(getProps().getProperty("userPaginationFirst"));
				break;
			default:
				logger.error("Switch Case["
						+ sLinkName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid button clicked");
			}
		} catch (Exception e) {
			logger.error("Failed to click on link :" + sLinkName
					+ " , detail message: " + e.getMessage());
			Assert.fail(e.getMessage());
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public void verifySiteDetailGridPagination(String task) {
		String totalPages = null;
		String currentPage = null;
		try {
			switch (task.toUpperCase()) {
			case "NEXT":
				String pageNumber = BaseUtil.getElementAttribute(getProps()
						.getProperty("userPaginationCurrentPage"), "value");
				Assert.assertTrue(
						"Next page is not displayed",
						(null != pageNumber && Integer.parseInt(pageNumber) > 1));
				break;
			case "PREVIOUS":
				totalPages = BaseUtil.getElementText(getProps().getProperty(
						"userPaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1)
						.trim();
				currentPage = BaseUtil.getElementAttribute(getProps()
						.getProperty("userPaginationCurrentPage"), "value");
				Assert.assertTrue("Previous page is not displayed",
						(null != totalPages && null != currentPage && Integer
								.parseInt(currentPage) < Integer
								.parseInt(totalPages)));
				break;
			case "LAST":
				totalPages = BaseUtil.getElementText(getProps().getProperty(
						"userPaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1)
						.trim();
				currentPage = BaseUtil.getElementAttribute(getProps()
						.getProperty("userPaginationCurrentPage"), "value");
				Assert.assertTrue(
						"Last page is not displayed",
						(Integer.parseInt(totalPages) == Integer
								.parseInt(currentPage)
								&& !BaseUtil.verifyElementEnabled(getProps()
										.getProperty("userPaginationNext")) && !BaseUtil
								.verifyElementEnabled(getProps().getProperty(
										"userPaginationLast"))));
				break;
			case "FIRST":
				currentPage = BaseUtil.getElementAttribute(getProps()
						.getProperty("userPaginationCurrentPage"), "value");
				Assert.assertTrue(
						"First page is not displayed",
						(Integer.parseInt(currentPage) == 1)
								&& !BaseUtil.verifyElementEnabled(getProps()
										.getProperty("userPaginationFirst"))
								&& !BaseUtil.verifyElementEnabled(getProps()
										.getProperty("userPaginationPrev")));
				break;
			default:
				logger.error("Switch Case["
						+ task
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid task clicked");
			}
		} catch (Exception e) {
			logger.error("Failed to click on link :" + task
					+ " , detail message: " + e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	public boolean setRandomPage(int iTotalPage, int iPageNum) {
		String sArrow = "";
		int iDiff = 0;
		try {
			int iCurrentPage = Integer.parseInt(BaseUtil.getElementAttribute(
					getProps().getProperty("userPaginationCurrentPage"),
					"value"));
			if (iCurrentPage >= iPageNum) {
				iDiff = iCurrentPage - iPageNum;
				sArrow = "DOWN";
			} else {
				iDiff = iPageNum - iCurrentPage;
				sArrow = "UP";
			}
			WebElement element = BaseUtil.getElementAfterLoaded(getProps()
					.getProperty("userPaginationCurrentPage"));
			element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			for (int iCount = 1; iCount < iDiff + 1; iCount++) {
				if (sArrow.equals("UP")) {
					element.sendKeys(Keys.chord(Keys.ARROW_UP));
				} else {
					element.sendKeys(Keys.chord(Keys.ARROW_DOWN));
				}
			}
			return true;
		} catch (Exception e) {
			logger.error("Some error occured while setting the number["
					+ iPageNum + "] in page number input box : "
					+ e.getMessage());
			return false;
		}
	}

	public void verifySearchFunctionality(String arg) {
		String[] arr = arg.split(":");
		String keyword = arr[1];
		List<WebElement> listUserNames = null;
		String totalItemsText = null;
		int initialTotalItemsCount = 0;
		int laterTotalItemsCount = 0;
		try {
			switch (keyword.toUpperCase()) {
			case "INIT3CHAR":
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("userTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());

				BaseUtil.enterText(getProps().getProperty("userSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("userTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				Assert.assertTrue("Search results after entering 3 characters "
						+ arr[0] + " is not as expected",
						laterTotalItemsCount < initialTotalItemsCount);
				break;
			case "RANDOM":
				String randomSearchString = RandomStringUtils.randomAscii(10);
				BaseUtil.enterText(getProps().getProperty("userSearchField"),
						randomSearchString);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("userSearchButton"));
				BaseUtil.waitForSpinner();
				String elementText = BaseUtil.getElementText(getProps()
						.getProperty("gridZeroRecord"));
				Assert.assertTrue(
						"Multiple results found for the string : "
								+ randomSearchString,
						elementText.equals(getProps().getProperty(
								"userGridZeroRecordText")));
				break;
			case "BACKSPACE":
				BaseUtil.enterText(getProps().getProperty("userSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("userSearchButton"));
				BaseUtil.waitForSpinner();
				listUserNames = BaseUtil
						.getMultipleElementsAfterLoaded(getProps().getProperty(
								"gridUserNameColumn"));
				logger.info("Search size before enter backspace: "
						+ listUserNames.size());

				WebElement searchBox = BaseUtil
						.getElementAfterLoaded(getProps().getProperty(
								"userSearchField"));
				searchBox.clear();
				BaseUtil.waitForSpinner();
				List<WebElement> newSearchListUserNames = BaseUtil
						.getMultipleElementsAfterLoaded(getProps().getProperty(
								"gridUserNameColumn"));
				logger.info("Search size after enter backspace: "
						+ newSearchListUserNames.size());
				Assert.assertTrue(
						"Search results are not as expected when backspace is clicked",
						newSearchListUserNames.size() > listUserNames.size());
				break;
			case "DELETE":
				BaseUtil.enterText(getProps().getProperty("userSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("userSearchButton"));
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("userTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				BaseUtil.enterText(getProps().getProperty("userSearchField"),
						"");
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("userTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				Assert.assertTrue("Search results after deleting the keyword "
						+ arr[0] + " is not as expected",
						laterTotalItemsCount > initialTotalItemsCount);
				break;
			case "PAGINATION":
				BaseUtil.enterText(getProps().getProperty("userSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("userSearchButton"));
				BaseUtil.waitForSpinner();
				boolean nextFlg = BaseUtil.verifyElementEnabled(getProps()
						.getProperty("userPaginationNext"));
				boolean lastFlg = BaseUtil.verifyElementEnabled(getProps()
						.getProperty("userPaginationLast"));
				boolean prevFlg = BaseUtil.verifyElementEnabled(getProps()
						.getProperty("userPaginationPrev"));
				boolean firstFlg = BaseUtil.verifyElementEnabled(getProps()
						.getProperty("userPaginationFirst"));
				Assert.assertFalse(
						"Expected : All the pagination buttons should be disable, Actual : one or more buttons are enabled",
						(nextFlg || lastFlg || prevFlg || firstFlg));
				break;
			}
		} catch (Exception e) {
			logger.error("Some error occured while verifying the search results : "
					+ e.getMessage());
			Assert.fail(e.getMessage());
		} finally {
			BaseUtil.explicitWait(2000);
		}
	}

	public boolean isBlank(String fieldName) {
		WebElement blankText;
		boolean isBlank = false;
		switch (fieldName.toUpperCase()) {
		case "USERNAME":
			blankText = BaseUtil.getElementAfterLoaded(getProps().getProperty(
					"userNameField"));
			blankText.clear();
			clickOperationOnUserPage("save");
			BaseUtil.explicitWait(1000);
			isBlank = getProps().getProperty("addUserUserNameErrorMessageText")
					.equals(BaseUtil.getElementText(getProps().getProperty(
							"addUserUserNameErrorMessage")));
			break;

		case "FIRST NAME":
			blankText = BaseUtil.getElementAfterLoaded(getProps().getProperty(
					"firstNameField"));
			blankText.clear();
			BaseUtil.enterText(getProps().getProperty("userNameField"),
					"TestUser");
			clickOperationOnUserPage("save");
			BaseUtil.explicitWait(1000);
			isBlank = getProps()
					.getProperty("addUserFirstNameErrorMessageText").equals(
							BaseUtil.getElementText(getProps().getProperty(
									"addUserFirstNameErrorMessage")));
			break;
		case "LAST NAME":
			blankText = BaseUtil.getElementAfterLoaded(getProps().getProperty(
					"lastNameField"));
			blankText.clear();
			BaseUtil.enterText(getProps().getProperty("firstNameField"),
					"TestFirstName");
			clickOperationOnUserPage("save");
			BaseUtil.explicitWait(1000);
			isBlank = getProps().getProperty("addUserLastNameErrorMessageText")
					.equals(BaseUtil.getElementText(getProps().getProperty(
							"addUserLastNameErrorMessage")));
			break;
		case "EMAIL":
			blankText = BaseUtil.getElementAfterLoaded(getProps().getProperty(
					"emailField"));
			blankText.clear();
			BaseUtil.enterText(getProps().getProperty("lastNameField"),
					"TestLastName");
			clickOperationOnUserPage("save");
			BaseUtil.explicitWait(1000);
			isBlank = getProps().getProperty("addUserEmailBlankMessageText")
					.equals(BaseUtil.getElementText(getProps().getProperty(
							"addUserEmailErrorMessage")));
			break;

		}

		return isBlank;
	}

	public boolean clickOnUserPopup(String sButtonName) {
		try {
			String locator = null;
			switch (sButtonName.toUpperCase()) {
			case "CANCEL":
				locator = getProps().getProperty("addUserPopupCancelButton");
				break;
			case "OK":
				locator = getProps().getProperty("addUserPopupOkButton");
				break;

			default:
				logger.error("Switch Case["
						+ sButtonName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}

			BaseUtil.clickAndWait(locator);
			return true;
		} catch (Exception e) {
			logger.error("Failed to click on button :" + sButtonName
					+ " , detail message: " + e.getMessage());
			return false;
		}
	}

	public boolean verifyFieldValues(String fieldType, String userNo) {
		String locator = null;
		String flag = null;
		String defaultValue = null;
		try {
			switch (fieldType.toUpperCase()) {
			case "ROLE":
				flag = "Dropdown";
				locator = getProps().getProperty("roleField");
				if (userNo.equalsIgnoreCase("first")) {
					defaultValue = getProps().getProperty("role_DefaultValue1");
				} else {
					defaultValue = getProps().getProperty("role_DefaultValue2");
				}

				break;
			case "UNIT OF TEMPERATURE":
				flag = "Dropdown";
				locator = getProps().getProperty("unitOfTemperatureField");
				if (userNo.equalsIgnoreCase("first")) {
					defaultValue = getProps().getProperty(
							"unitOfTemperature_DefaultValue1");
				} else {
					defaultValue = getProps().getProperty(
							"unitOfTemperature_DefaultValue2");
				}
				break;

			default:
				logger.error("Switch Case["
						+ fieldType
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid element name : " + fieldType + " selected");
			}

			if (flag.equalsIgnoreCase("Dropdown")) {
				return BaseUtil.getSelectedDropDownValues(locator).equals(
						defaultValue);
			} else if (flag.equalsIgnoreCase("Field")) {
				return BaseUtil.getElementText(locator).equals(defaultValue);
			}
		} catch (Exception e) {
			logger.error("Failed to verify value of field " + fieldType
					+ " see detail message : " + e.getMessage());
			Assert.fail(e.getMessage());
		}
		return false;
	}

	public void verifyDropdownStringValues(String dropdownType,
			List<String> valueList) {
		String locator = null;
		List<String> actualValueList = null;
		List<String> comparableList = null;
		try {
			switch (dropdownType.toUpperCase()) {
			case "ROLE":
				locator = getProps().getProperty("addUserRoleField");
				break;
			case "UNIT OF TEMPERATURE":
				locator = getProps().getProperty("unitOfTemperatureField");
				break;

			default:
				logger.error("Switch Case["
						+ dropdownType
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid element name: " + dropdownType
						+ " selected");
			}

			actualValueList = BaseUtil.getDropDownText(locator);
			comparableList = new ArrayList<String>(valueList);
			actualValueList.removeAll(Arrays.asList("", null));
			comparableList.removeAll(Arrays.asList("", null));
			Collections.sort(comparableList);
			Collections.sort(actualValueList);
			Assert.assertTrue("Failed to verify values of dropdown "
					+ dropdownType, comparableList.equals(actualValueList));

		} catch (Exception e) {
			logger.error("Failed to verify values of dropdown " + dropdownType
					+ " see detail message : " + e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	public boolean verifyUserSectionDisplayed(String elementName) {
		try {
			String labelLocator = null;
			String elementLocator = null;
			String flag = null;
			switch (elementName.toUpperCase()) {
			case "FIRST NAME":
				flag = "Field";
				labelLocator = getProps().getProperty(
						"propertiesSectionFirstNameField");
				break;
			case "LAST NAME":
				flag = "Field";
				labelLocator = getProps().getProperty(
						"propertiesSectionLastNameField");
				break;
			case "EMAIL":
				flag = "Field";
				labelLocator = getProps().getProperty(
						"propertiesSectionEmailField");
				break;
			case "LOCKED":
				flag = "Field";
				labelLocator = getProps().getProperty(
						"propertiesSectionLockedField");
				break;
			case "ENABLED":
				flag = "Field";
				labelLocator = getProps().getProperty(
						"propertiesSectionEnabledField");
				break;
			case "LOCALE":
				flag = "Field";
				labelLocator = getProps().getProperty(
						"propertiesSectionlocaleType");
				break;
			case "UNIT OF TEMPERATURE":
				flag = "Field";
				labelLocator = getProps().getProperty(
						"addUserUnitOfTemparature");
				break;
			case "ROLE":
				flag = "Field";
				labelLocator = getProps().getProperty("roleField");
				break;

			case "PROPERTY":
				flag = "Column";
				labelLocator = getProps().getProperty(
						"editUserPagePopupAuditGridPropertyHeader");
				break;
			case "ACTION":
				flag = "Column";
				labelLocator = getProps().getProperty(
						"editUserPagePopupAuditGridActionHeader");
				break;
			case "ORIGINAL VALUE":
				flag = "Column";
				labelLocator = getProps().getProperty(
						"editUserPagePopupAuditGridOriginalValueHeader");
				break;
			case "UPDATED VALUE":
				flag = "Column";
				labelLocator = getProps().getProperty(
						"editUserPagePopupAuditGridUpdatedValueHeader");
				break;

			case "TENANT":
				flag = "Element";
				labelLocator = getProps().getProperty("PermissionTenantLabel");
				elementLocator = getProps().getProperty(
						"PermissionTenantDropdown");
				break;
			case "SELECTED TENANT TYPE":
				flag = "Element";
				labelLocator = getProps().getProperty(
						"PermissionSelectedTenantTypeLabel");
				elementLocator = getProps().getProperty(
						"PermissionSelectedTenantTypeValue");
				break;
			case "AVAILABLE SITES":
				flag = "Element";
				labelLocator = getProps().getProperty(
						"PermissionAvailableSitesLabel");
				elementLocator = getProps().getProperty(
						"PermissionAvailableSitesValue");
				break;
			case "ELECTED TENANT/SITE PERMISSION":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"PermissionSelectedTenantLabel"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"PermissionSitePermissionLabel"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"PermissionSelectedTenantValue"));
				return true;
			case "ADD":
				flag = "Button";
				labelLocator = getProps().getProperty("AddButton");
				break;
			case "ADD ALL":
				flag = "Button";
				labelLocator = getProps().getProperty("AddAllButton");
				break;
			case "REMOVE":
				flag = "Button";
				labelLocator = getProps().getProperty("RemoveButton");
				break;
			case "PERMISSION:CANCEL":
				flag = "Button";
				labelLocator = getProps().getProperty("PermissionCancelButton");
				return true;
			case "PERMISSION:SAVE":
				flag = "Button";
				labelLocator = getProps().getProperty("PermissionSaveButton");
				break;
			default:
				logger.error("Switch Case["
						+ elementName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}

			if (flag.equalsIgnoreCase("Field")
					|| flag.equalsIgnoreCase("Button")) {
				BaseUtil.verifyElementDisplayed(labelLocator);

			} else if (flag.equalsIgnoreCase("Column")) {
				return BaseUtil.getElementText(labelLocator).trim()
						.equals(elementName);
			} else if (flag.equalsIgnoreCase("Element")) {
				BaseUtil.verifyElementDisplayed(labelLocator);
				BaseUtil.verifyElementDisplayed(elementLocator);
			}
			return true;
		} catch (Exception e) {
			logger.error("Failed to displayed the element :" + elementName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public void verifyAccordionToggle_UserPage(String sAccordionName) {

		String sCaseOptions = "Details,";
		try {
			switch (sAccordionName.toUpperCase()) {
			case "DETAILS":
				if (BaseUtil.getElementAfterLoaded(
						getProps().getProperty("firstNameField")).isDisplayed()) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"editUserPagePoupDetailsAccordion"));
					BaseUtil.explicitWait(2000);
				}
				try {
					if (BaseUtil.getElementAfterLoaded(
							getProps().getProperty("firstNameField"))
							.isDisplayed()) {
						Assert.fail("Failed to toggle the accordion: "
								+ sAccordionName);
					}
				} catch (Exception e) {
					logger.info("Accordion[" + sAccordionName + "] is toggled.");
					BaseUtil.clickAndWait(getProps().getProperty(
							"editUserPagePoupDetailsAccordion"));
				}
				break;

			case "AUDIT":
				BaseUtil.clickAndWait(getProps().getProperty(
						"editUserPagePopupAuditAccordion"));
				BaseUtil.explicitWait(1000);
				if (!BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editUserPagePopupAuditAccordion"))) {
					Assert.fail("Failed to toggle the accordion: "
							+ sAccordionName);
				}
				break;
			case "PERMISSION":
				BaseUtil.clickAndWait(getProps().getProperty(
						"editUserModelPermissionAccordion"));
				BaseUtil.explicitWait(1000);
				if (!BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"permissionAccordionFrame"))) {
					Assert.fail("Failed to toggle the accordion: "
							+ sAccordionName);
				}
				break;
			default:
				logger.error("Switch Case["
						+ sAccordionName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Failed to check existence of popup ["
						+ sAccordionName
						+ "] because you passed wrong parameter["
						+ sAccordionName
						+ "] either in feature file or test class. \n please choose valid parameter from the following list: \n ["
						+ sCaseOptions + "]");
			}
		} catch (Exception e) {
			logger.error("Failed to check existence of popup:" + sAccordionName
					+ "in site page. , see detail error message \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to check existence of popup: ["
					+ sAccordionName
					+ "] in site page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
	}

	public void checkPopupExistence_cancelUserPage(String userPopupName,
			String sExpMsg) {
		String sCaseOptions = "EDIT USER,";
		try {
			switch (userPopupName.toUpperCase()) {
			
			case "EDIT_USERNAME_CANCEL_CONFORMATION":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editUserCancelConfirmationPopup"));
				if (!(BaseUtil.getElementText(getProps().getProperty(
						"editUserCancelConfirmationPopupMessage"))
						.equalsIgnoreCase(sExpMsg)))
					Assert.fail("Failed to display the confirmation box with expected message: +"
							+ sExpMsg
							+ ", but actual message was: "
							+ BaseUtil.getElementText(getProps().getProperty(
									"editSiteCancelConfirmationPopupMessage")));
				break;
			default:
				logger.error("Switch Case["
						+ userPopupName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Failed to check existence of popup ["
						+ userPopupName
						+ "] because you passed wrong parameter["
						+ userPopupName
						+ "] either in feature file or test class. \n please choose valid parameter from the following list: \n ["
						+ sCaseOptions + "]");
			}
		} catch (Exception e) {
			logger.error("Failed to check existence of popup:" + userPopupName
					+ "in site page. , see detail error message \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to check existence of popup: [" + userPopupName
					+ "] in site page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
	}
	
	public void checkPopupExistence_UserPage(String userPopupName,
			String sExpMsg) {
		String sCaseOptions = "EDIT USER,";
		try {
			switch (userPopupName.toUpperCase()) {
			case "EDIT USER":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editUserPagePopup"));
				break;
			case "EDIT_USER_CONFIRMATION":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editUserCancelConfirmationPopup"));
				if (!(BaseUtil.getElementText(getProps().getProperty(
						"editUserCancelConfirmationPopupMessage"))
						.equalsIgnoreCase(sExpMsg)))
					Assert.fail("Failed to display the confirmation box with expected message: +"
							+ sExpMsg
							+ ", but actual message was: "
							+ BaseUtil.getElementText(getProps().getProperty(
									"editSiteCancelConfirmationPopupMessage")));
				break;
			default:
				logger.error("Switch Case["
						+ userPopupName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Failed to check existence of popup ["
						+ userPopupName
						+ "] because you passed wrong parameter["
						+ userPopupName
						+ "] either in feature file or test class. \n please choose valid parameter from the following list: \n ["
						+ sCaseOptions + "]");
			}
		} catch (Exception e) {
			logger.error("Failed to check existence of popup:" + userPopupName
					+ "in site page. , see detail error message \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to check existence of popup: [" + userPopupName
					+ "] in site page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
	}
	public void isHomePageAvailable(String userName) {
		BaseUtil.enterText(getProps().getProperty("adminUserName_Locator"),
				userName);
		BaseUtil.enterText(getProps().getProperty("adminUserPassword_Locator"),
				PathConstants.DEFAULT_USER_PASSWORD);
		BaseUtil.click(getProps().getProperty("adminLoginbutton_Locator"));
		BaseUtil.waitForSpinner();

		if (userName.equals("autoSupportTest")) {
			Assert.assertTrue(
					" Expected : User Tab should be Available. Actual : User tab is not available for user ["
							+ userName + "] ",
					BaseUtil.isElementClickable(getProps().getProperty(
							"UserTab")));
		} else if (userName.equals("botRoleCustomerAnalyst")) {
			Assert.assertTrue(
					" Expected : Dashboard Tab should be Available. Actual : Dashboard tab is not available for user ["
							+ userName + "] ",
					!BaseUtil.isElementClickable(getProps().getProperty(
							"DashboardTab")));
		} else if (userName.equals("botRoleCustomerSiteManager")) {
			Assert.assertFalse(
					" Expected : Home Tab should be Available. Actual : Home tab is not available for user ["
							+ userName + "] ",
					!BaseUtil.isElementClickable(getProps().getProperty(
							"homeTab")));
		}}
	
	public boolean verifyAccordionMessage(String accordionMessage, String accordion) {
		try {
			WebElement messageField = null;
			String message = "";
			switch (accordion.toUpperCase()) {
			case "DETAILS":
				messageField = BaseUtil
						.getElementAfterLoaded(getProps().getProperty("EditUserDetailsAccordionMessageField"));
				message = messageField.getText();
				if (message.equals(accordionMessage))
					Assert.assertTrue(" The expected message is given as [" + message + "] ", BaseUtil
							.verifyElementDisplayed(getProps().getProperty("EditUserDetailsAccordionMessageField")));
				return true;
			case "CAPABILITY":
				messageField = BaseUtil
						.getElementAfterLoaded(getProps().getProperty("EditUserCapabilityAccordionMessageField"));
				message = messageField.getText();
				if (message.equals(accordionMessage))
					Assert.assertTrue(" The expected message is given as [" + message + "] ", BaseUtil
							.verifyElementDisplayed(getProps().getProperty("EditUserCapabilityAccordionMessageField")));
				return true;
			case "PERMISSION":
				messageField = BaseUtil
						.getElementAfterLoaded(getProps().getProperty("editUserModelPermissionAccordion"));
				message = messageField.getText();
				if (message.equals(accordionMessage))
					Assert.assertTrue(" The expected message is given as [" + message + "] ", BaseUtil
							.verifyElementDisplayed(getProps().getProperty("editUserModelPermissionAccordion")));
				return true;
			default:
				logger.error("Switch Case[" + accordion + "] is not matched in class[" + getClass().getName()
						+ "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return false;
			}
		} catch (NullPointerException e) {
			logger.error("Accordion :" + accordion + " does not exist in the audit grid, see detail message : "
					+ e.getMessage());
			return false;
		} catch (Exception e) {
			logger.error("Failed to displayed the message :" + accordionMessage + " in the " + accordion
					+ " accordion. See detail message : " + e.getMessage());
			return false;
		}
	}

			
			
			

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// public void verifySorting_UserPage(String sSortingType, String sColName,
	// ) {
	// List<WebElement> lstUserColValues = null;
	// boolean bSort = false;
	// SortArrayList sortArrayList = null;
	// ArrayList<String> sortedList = null;
	// ArrayList<String> unsortedList = new ArrayList<String>();
	// if (changeSortDirection(sSortingType.toUpperCase(), sColName,
	// getProps().getProperty)) {
	// try {
	// switch (sColName.toUpperCase()) {
	// case "USERNAME":
	// lstUserColValues =
	// BaseUtil.getMultipleElementsAfterLoaded(getProps().getProperty("gridUserNameColumn"));
	// break;
	// case "EMAIL":
	// lstUserColValues =
	// BaseUtil.getMultipleElementsAfterLoaded(getProps().getProperty("gridEmailColumn"));
	// break;
	// case "FIRST NAME":
	// lstUserColValues =
	// BaseUtil.getMultipleElementsAfterLoaded(getProps().getProperty("gridFirsNameColumn"));
	// break;
	// case "LAST NAME":
	// lstUserColValues =
	// BaseUtil.getMultipleElementsAfterLoaded(getProps().getProperty("gridLastNameColumn"));
	// break;
	// case "ENABLED":
	// lstUserColValues =
	// BaseUtil.getMultipleElementsAfterLoaded(getProps().getProperty("gridEnabledColumn"));
	// break;
	// case "LOCKED":
	// lstUserColValues =
	// BaseUtil.getMultipleElementsAfterLoaded(getProps().getProperty("gridLockedColumn"));
	// break;
	// case "ROLE":
	// lstUserColValues =
	// BaseUtil.getMultipleElementsAfterLoaded(getProps().getProperty("gridRoleColumn"));
	// break;
	// default:
	// logger.error("Switch Case[" + sColName + "] is not matched in class[" +
	// getClass().getName()
	// + "] , Method[" +
	// Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
	// Assert.fail("Invalid column name: " + sColName
	// +
	// ", Please pass valid column name e.g.[Username | Email | First Name | Last Name | Enabled | Locked | Role] from feature file or test class.");
	// }
	// for (int i = 0; i < lstUserColValues.size(); i++) {
	// unsortedList.add(lstUserColValues.get(i).getText());
	// }
	// sortArrayList = new SortArrayList(unsortedList);
	// switch (sSortingType.toUpperCase()) {
	// case "ASCENDING":
	// sortedList = sortArrayList.sortAscending();
	// break;
	// case "DESCENDING":
	// sortedList = sortArrayList.sortDescending();
	// break;
	// default:
	// logger.error("Switch Case[" + sSortingType + "] is not matched in class["
	// + getClass().getName()
	// + "] , Method[" +
	// Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
	// Assert.fail("Invalid sorting type:" + sSortingType
	// +
	// ", Please pass valid keyword[Sort Descending,Sort Ascending] from feature file or test class.");
	// }
	// logger.info("Actual ArrayList: " + unsortedList);
	// logger.info("Sorted ArrayList: " + sortedList);
	// for (int i = 0; i < sortedList.size(); i++) {
	// // for blank columns
	// if (sortedList.get(i).length() == unsortedList.get(i).length() &&
	// sortedList.get(i).length() == 0) {
	// bSort = true;
	// } else if (sortedList.get(i).toLowerCase().charAt(0) ==
	// unsortedList.get(i).toLowerCase()
	// .charAt(0)) {
	// bSort = true;
	// } else {
	// bSort = false;
	// }
	// if (!bSort)
	// break;
	// }
	// if (bSort == false)
	// Assert.fail("Sorting failed for column: " + sColName +
	// " , Sorting order:" + sSortingType);
	// } catch (Exception e) {
	// logger.error("Failed to sort the column :" + sColName + " Sorting order:"
	// + sSortingType
	// + " , detail message: " + e.getStackTrace().toString());
	// Assert.fail(e.getStackTrace().toString());
	// }
	// } else {
	// Assert.fail("Failed to set sort direction for column:" + sColName);
	// }
	//
	// }
	//
	// private boolean changeSortDirection(String sRequiredSortDir, String
	// sColName,
	// ) {
	//
	// WebElement eleBtn = null;
	// String sCurrentSortDir = "";
	// String sLocatorValue = "";
	// String sLocator2Value = "";
	// switch (sColName.toUpperCase()) {
	// case "USERNAME":
	// sLocatorValue = getProps().getProperty("gridUsernameSortButtonNew");
	// sLocator2Value = getProps().getProperty("gridHeaderUsername");
	// break;
	// case "EMAIL":
	// sLocatorValue = getProps().getProperty("gridEmailSortButtonNew");
	// sLocator2Value = getProps().getProperty("gridHeaderUserEmailNew");
	// break;
	// case "FIRST NAME":
	// sLocatorValue = getProps().getProperty("gridFirstNameSortButtonNew");
	// sLocator2Value = getProps().getProperty("gridHeaderFirstNameNew");
	// break;
	// case "LAST NAME":
	// sLocatorValue = getProps().getProperty("gridLastNameSortButtonNew");
	// sLocator2Value = getProps().getProperty("gridHeaderLastNameNew");
	// break;
	// case "ENABLED":
	// sLocatorValue = getProps().getProperty("gridEnabledSortButtonNew");
	// sLocator2Value = getProps().getProperty("gridHeaderEnabledNew");
	// break;
	// case "LOCKED":
	// sLocatorValue = getProps().getProperty("gridLockedSortButtonNew");
	// sLocator2Value = getProps().getProperty("gridHeaderLockedNew");
	// break;
	// case "ROLE":
	// sLocatorValue = getProps().getProperty("gridRoleSortButtonNew");
	// sLocator2Value = getProps().getProperty("gridHeaderRoleNew");
	// break;
	// default:
	// logger.error("Switch Case[" + sColName + "] is not matched in class[" +
	// getClass().getName() + "] , Method["
	// + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
	// Assert.fail("Invalid column name: " + sColName
	// +
	// ", Please pass valid column name e.g.[Username | Email | First Name | Last Name | Enabled | Locked | Role] from user feature file or test class.");
	// }
	// BaseUtil.clickAndWait(sLocator2Value);
	// BaseUtil.explicitWait(1000);
	// if (isElementEnabled(getLocator(sLocatorValue))) {
	// BaseUtil.click(sLocatorValue);
	// BaseUtil.explicitWait(1000);
	// eleBtn = BaseUtil.getElementAfterLoaded(sLocatorValue);
	// sCurrentSortDir = eleBtn.getAttribute("aria-hidden");
	// if (sCurrentSortDir.toUpperCase().contains(sRequiredSortDir)) {
	// return true;
	// } else {
	// BaseUtil.click(sLocatorValue);
	// BaseUtil.waitForSpinner();
	// sCurrentSortDir = eleBtn.getAttribute("aria-hidden");
	// if (sCurrentSortDir.toUpperCase().contains(sRequiredSortDir)) {
	// return true;
	// } else {
	// return false;
	// }
	// }
	// } else {
	// Assert.fail("Sort arrow button is not enabled for column:" + sColName +
	// "Locator value:" + sLocatorValue);
	// return false;
	// }
	// }
	//
	//
	// public boolean selectValueFromDropDown(String roleSelector, Map<String,
	// String> getProps().getProperty) {
	// try {
	// switch (roleSelector.toUpperCase()) {
	// case "ROLE":
	// BaseUtil.clickAndWait(getProps().getProperty("roleField"));
	// return true;
	// case "C&I CUSTOMER ANALYST":
	// selectDropDownByValue(getProps().getProperty("addUserRoleTypeCAType"),
	// roleSelector);
	// return true;
	// case "ENGLISH (UNITED STATE)":
	// selectDropDownByValue(getProps().getProperty("addUserLocalType"),
	// roleSelector);
	// return true;
	// case "SITE TREE":
	// BaseUtil.clickAndWait(getProps().getProperty("addUserSiteTreeType"));
	// return true;
	// default:
	// logger.error("Switch Case[" + roleSelector + "] is not matched in class["
	// + getClass().getName()
	// + "] , Method[" +
	// Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
	// return false;
	// }
	// } catch (Exception e) {
	// logger.error("Failed to click on button :" + roleSelector +
	// " , detail message: " + e.getMessage());
	// return false;
	// }
	// }
	//
	// public void clickElement(String objLocator) {
	// BaseUtil.click(objLocator);
	// }
	//
	//
	// public boolean verifyUserSectionDisplayedNew(String elementName,
	// ) {
	// try {
	// switch (elementName.toUpperCase()) {
	// case "USER NAME":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("propertiesSectionEmailField"));
	// if
	// (BaseUtil.getElementText(getProps().getProperty("editUserPageUserName")).equalsIgnoreCase(""))
	//
	// return true;
	// case "PROPERTIES":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("editUserPagePropertiesSection"));
	// return true;
	// case "SITE TREE":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("editUserPageSiteTreeSection"));
	// return true;
	//
	// case "EMAIL":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("propertiesSectionEmailField"));
	// return true;
	// case "FIRST NAME":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("propertiesSectionFirstNameField"));
	// return true;
	// case "LAST NAME":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("propertiesSectionLastNameField"));
	// return true;
	// case "ENABLED":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("propertiesSectionEnabledField"));
	// return true;
	// case "LOCKED":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("propertiesSectionLockedField"));
	// return true;
	// case "ROLE":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("propertiesSectionRoleField"));
	// return true;
	// case "LOCALE":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("propertiesSectionlocaleType"));
	// return true;
	// case "BYPASS THROTTLING":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("propertiesSectionBypassThrottlingField"));
	// return true;
	// case "DISABLE CIDASHBOARD":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("propertiesSectionDisableCIDashboardField"));
	// return true;
	// case "MSTR CAPABLE":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("propertiesSectionMSTRCapableField"));
	// return true;
	// case "HAS PROJECT TRACKING":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("propertiesSectionHasProjectTrackingField"));
	// return true;
	// case "MEASUREMENT SYSTEM":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("propertiesSectionMeasurementSystemField"));
	// return true;
	// case "UNIT OF TEMPERATURE":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("propertiesSectionUnitOfTemperature"));
	// return true;
	// default:
	// logger.error("Switch Case[" + elementName + "] is not matched in class["
	// + getClass().getName()
	// + "] , Method[" +
	// Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
	// return false;
	// }
	// } catch (Exception e) {
	// logger.error("Failed to displayed the element :" + elementName +
	// " see detail message : " + e.getMessage());
	// return false;
	// }
	// }
	//
	// public boolean verifyDefaultValuesOfUserProperties(String elementName,
	// ) {
	// try {
	// switch (elementName.toUpperCase()) {
	// case "Locale":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("propertySectionDefaultValueLocale"));
	// return true;
	// case "Measurement System":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("propertySectionDefaultValueMeasurementSystem"));
	// return true;
	// case "Role":
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("propertySectionDefaultValueRole"));
	// return true;
	// default:
	// logger.error("Switch Case[" + elementName + "] is not matched in class["
	// + getClass().getName()
	// + "] , Method[" +
	// Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
	// return false;
	// }
	// } catch (Exception e) {
	// logger.error("Failed to displayed the element :" + elementName +
	// " see detail message : " + e.getMessage());
	// return false;
	// }
	// }
	//
	// public boolean checkValidationMessage(Map<String, String>
	// pageObjectRepository, String validationMessage) {
	// return
	// BaseUtil.getElementText(pageObjectRepository("userDisabledValidationMessage")).equals(validationMessage);
	// }
	//
	// public boolean isEditUserAuditGridHeaderDisplayed(String headerName,
	// ,
	// Map<String, String> sitePageObjectText) {
	// try {
	// switch (headerName.toUpperCase()) {
	// case "PROPERTY":
	// return
	// BaseUtil.getElementText(getProps().getProperty("editUserPagePopupAuditGridPropertyHeader")).trim()
	// .equals(headerName);
	// case "ACTION":
	// return
	// BaseUtil.getElementText(getProps().getProperty("editUserPagePopupAuditGridActionHeader")).trim()
	// .equals(headerName);
	// case "ORIGINAL VALUE":
	// return
	// BaseUtil.getElementText(getProps().getProperty("editUserPagePopupAuditGridOriginalValueHeader")).trim()
	// .equals(headerName);
	// case "UPDATED VALUE":
	// return
	// BaseUtil.getElementText(getProps().getProperty("editUserPagePopupAuditGridUpdatedValueHeader")).trim()
	// .equals(headerName);
	// case "USER":
	// return
	// BaseUtil.getElementText(getProps().getProperty("editUserPagePopupAuditGridUserHeader")).trim()
	// .equals(headerName);
	// case "DATE STAMP":
	// return
	// BaseUtil.getElementText(getProps().getProperty("editUserPagePopupAuditGridDateStampHeader")).trim()
	// .equals(headerName);
	// default:
	// logger.error("Switch Case[" + headerName + "] is not matched in class[" +
	// getClass().getName()
	// + "] , Method[" +
	// Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
	// return false;
	// }
	// } catch (NullPointerException e) {
	// logger.error("Header :" + headerName +
	// " does not exist in the audit grid, see detail message : "
	// + e.getMessage());
	// return false;
	// } catch (Exception e) {
	// logger.error("Failed to displayed the header :" + headerName +
	// " see detail message : " + e.getMessage());
	// return false;
	// }
	// }
	//
	// public boolean instantElementCheckOnUserPage(String buttonName,
	// ) {
	//
	// try {
	// switch (buttonName.toUpperCase()) {
	// case "SELECTED SITES":
	// return instantElementCheck("Display",
	// getProps().getProperty("SelectedSites"));
	// default:
	// logger.error("Switch Case[" + buttonName + "] is not matched in class[" +
	// getClass().getName()
	// + "] , Method[" +
	// Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
	// return false;
	// }
	// } catch (Exception e) {
	// logger.error("Failed to displayed the header :" + buttonName +
	// " see detail message : " + e.getMessage());
	// return false;
	// }
	// }
	//
	//
	// public void isDashboardTabAvailable(String userName, Map<String, String>
	// getProps().getProperty) {
	// BaseUtil.enterText(getProps().getProperty("adminUserName_Locator"),
	// userName);
	// BaseUtil.enterText(getProps().getProperty("adminUserPassword_Locator"),
	// PathConstants.DEFAULT_USER_PASSWORD);
	// BaseUtil.click(getProps().getProperty("adminLoginbutton_Locator"));
	// BaseUtil.waitForSpinner();
	//
	// }
	//
	// 
	
	public void explicitWait(long milliSec) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		try {
			logger.debug("Waiting for [" + milliSec
					+ "] millisec before the next step.");
			Thread.sleep(milliSec);
		} catch (Exception e) {
			logger.error("Error while doing explicit wait : " + e.getMessage());
		}
	}
}
