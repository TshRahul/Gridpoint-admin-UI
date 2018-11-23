package com.gridpoint.adminui.automation.audit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.gridpoint.automation.constants.PathConstants;
import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.util.BaseUtil;

public class AuditPage extends CommonInit {

	private Logger logger = Logger.getLogger(AuditPage.class);

	public boolean verify_AuditTab(String userName) {
		try {
			BaseUtil.enterText(getProps().getProperty("adminUserName_Locator"),
					userName);
			BaseUtil.enterText(getProps().getProperty("adminPassword_Locator"),
					PathConstants.DEFAULT_USER_PASSWORD);
			BaseUtil.clickAndWait(getProps().getProperty(
					"adminLoginbutton_Locator"));
			BaseUtil.waitForSpinner(2000);

			boolean flag = BaseUtil.isElementClickable(getProps().getProperty(
					"auditTab"));
			System.out.println("flag     =    " + flag);

			if (BaseUtil.isElementClickable(getProps().getProperty("auditTab"))) {
				Assert.fail("Expected : Audit Tab should not be Available. Actual : Audit tab is available for user ["
						+ userName + "]");
			}
		} catch (Exception e) {
			logger.error("Failed to verify Audit Tab for user [" + userName
					+ "]" + e.getStackTrace().toString());
			Assert.fail("Failed to verify Audit Tab for user [" + userName
					+ "]" + e.getStackTrace().toString());
			return false;
		}
		return false;
	}

	public void verifySorting_AuditPage(String sSortingType, String sColName) {
		List<String> listColumnValues = null;
		try {
			switch (sColName.toUpperCase()) {
			case "DOMAIN TYPE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"auditPageDomainTypeHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"auditPageDomainTypeHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("auditPageDomainTypeHeader"));
				break;
			case "PROPERTY":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"auditPagePropertyHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"auditPagePropertyHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("auditPagePropertyHeader"));
				break;
			case "ACTION":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"auditPageActionHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"auditPageActionHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("auditPageActionHeader"));
				break;
			case "ORIGINAL VALUE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"auditPageOriginalValueHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"auditPageOriginalValueHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("auditPageOriginalValueHeader"));
				break;
			case "UPDATED VALUE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"auditPageUpdatedValueHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"auditPageUpdatedValueHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("auditPageUpdatedValueHeader"));
				break;
			case "USER":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps()
							.getProperty("auditPageUserHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps()
							.getProperty("auditPageUserHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("auditPageUserHeader"));
				break;
			case "DATE STAMP":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"auditPageDateStampHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"auditPageDateStampHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("auditPageDateStampHeader"));
				break;
			default:
				logger.error("Switch Case["
						+ sColName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid column name: "
						+ sColName
						+ ", Please pass valid column name from feature file or test class.");
			}

			Assert.assertTrue(sColName + " is not in " + sSortingType
					+ " order",
					BaseUtil.isSorted(sSortingType, listColumnValues));

		} catch (Exception e) {
			logger.error("Failed to sort the column :" + sColName
					+ " Sorting order:" + sSortingType + " , detail message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to sort the column :" + sColName
					+ " Sorting order:" + sSortingType + " , detail message: "
					+ e.getStackTrace().toString());
		}
	}

	public void clickPaginationAuditPage(String sLinkName) {
		try {
			switch (sLinkName.toUpperCase()) {
			case "NEXT":
				if (BaseUtil.verifyElementEnabled(getProps().getProperty(
						"auditPaginationNext"))) {
					BaseUtil.click(getProps()
							.getProperty("auditPaginationNext"));
					BaseUtil.waitForSpinner();
					BaseUtil.explicitWait(2000);
				} else {
					Assert.fail("Required data is not present. Next button is not enabled");
				}
				break;
			case "PREV":
				if (BaseUtil.verifyElementEnabled(getProps().getProperty(
						"auditPaginationPrev"))) {
					BaseUtil.click(getProps()
							.getProperty("auditPaginationPrev"));
					BaseUtil.waitForSpinner();
					BaseUtil.explicitWait(2000);
				} else {
					Assert.fail("Required data is not present. Prev button is not enabled");
				}
				break;
			case "LAST":
				if (BaseUtil.verifyElementEnabled(getProps().getProperty(
						"auditPaginationLast"))) {
					BaseUtil.click(getProps()
							.getProperty("auditPaginationLast"));
					BaseUtil.waitForSpinner();
					BaseUtil.explicitWait(2000);
				} else {
					Assert.fail("Required data is not present. Last button is not enabled");
				}
				break;
			case "FIRST":
				if (BaseUtil.verifyElementEnabled(getProps().getProperty(
						"auditPaginationFirst"))) {
					BaseUtil.click(getProps().getProperty(
							"auditPaginationFirst"));
					BaseUtil.waitForSpinner();
					BaseUtil.explicitWait(2000);
				} else {
					Assert.fail("Required data is not present. First button is not enabled");
				}
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
		}
	}

	public void verifyAuditDetailGridPagination(String task) {
		String totalPages = null;
		String currentPage = null;
		try {
			switch (task.toUpperCase()) {
			case "NEXT":
				String pageNumber = BaseUtil.getElementAttribute(getProps()
						.getProperty("auditPaginationCurrentPage"), "value");
				Assert.assertTrue("Next page is not displayed",
						Integer.parseInt(pageNumber) > 1);
				break;
			case "PREVIOUS":
				totalPages = BaseUtil.getElementText(getProps().getProperty(
						"auditPaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1)
						.trim();
				currentPage = BaseUtil.getElementAttribute(getProps()
						.getProperty("auditPaginationCurrentPage"), "value");
				Assert.assertTrue("Previous page is not displayed", Integer
						.parseInt(currentPage) < Integer.parseInt(totalPages));
				break;
			case "LAST":
				totalPages = BaseUtil.getElementText(getProps().getProperty(
						"auditPaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1)
						.trim();
				currentPage = BaseUtil.getElementAttribute(getProps()
						.getProperty("auditPaginationCurrentPage"), "value");
				Assert.assertTrue(
						"Last page is not displayed",
						(Integer.parseInt(totalPages) == Integer
								.parseInt(currentPage)
								&& !BaseUtil.verifyElementEnabled(getProps()
										.getProperty("auditPaginationNext")) && !BaseUtil
								.verifyElementEnabled(getProps().getProperty(
										"auditPaginationLast"))));
				break;
			case "FIRST":
				currentPage = BaseUtil.getElementAttribute(getProps()
						.getProperty("auditPaginationCurrentPage"), "value");
				Assert.assertTrue(
						"First page is not displayed",
						(Integer.parseInt(currentPage) == 1
								&& !BaseUtil.verifyElementEnabled(getProps()
										.getProperty("auditPaginationFirst")) && !BaseUtil
								.verifyElementEnabled(getProps().getProperty(
										"auditPaginationPrev"))));
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
					getProps().getProperty("auditPaginationCurrentPage"),
					"value"));
			if (iCurrentPage >= iPageNum) {
				iDiff = iCurrentPage - iPageNum;
				sArrow = "DOWN";
			} else {
				iDiff = iPageNum - iCurrentPage;
				sArrow = "UP";
			}
			WebElement element = BaseUtil.getElementAfterLoaded(getProps()
					.getProperty("auditPaginationCurrentPage"));
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

	public boolean clickOperationOnAuditpage(String buttonName) {
		try {
			String locator = null;
			switch (buttonName.toUpperCase()) {
			case "AUDIT":
				locator = getProps().getProperty("auditTab");
				break;
			case "FIRMWARE":
				locator = getProps().getProperty("FirmwareTab");
				break;
			case "DATE PICKER":
				locator = getProps().getProperty("auditDatePicker");
				break;

			// Endpoint Firmware
			case "ENDPOINT FIRMWARE":
				locator = getProps().getProperty("EndpointFirmwareTab");
				break;
			case "ADD ENDPOINT FIRMWARE":
				locator = getProps().getProperty("addEndpointFirmwareButton");
				break;
			case "SAVE ENDPOINT FIRMWARE":
				locator = getProps().getProperty(
						"addEndpointFirmwarePopupSaveButton");
				break;
			case "DELETE FIRMWARE":
				locator = getProps()
						.getProperty("deleteEndpointFirmwareButton");
				break;
			case "ENDPOINT FIRMWARE:DELETE_OK":
				locator = getProps().getProperty(
						"DeleteEndpointFirmwareOkConfirmationButton");
				break;
			case "SAVE EDIT ENDPOINTFIRMWARE":
				locator = getProps().getProperty(
						"editEndpointFirmwareSaveButton");
				break;

			// Sites
			case "SITE":
				locator = getProps().getProperty("sitesTab");
				break;
			case "ADD SITE":
				locator = getProps().getProperty("addSiteButton");
				break;
			case "DELETE SITE":
				locator = getProps().getProperty("deleteSiteButton");
				break;
			case "SITE:DELETE_OK":
				locator = getProps().getProperty(
						"deleteSiteOkConfirmationButton");
				break;

			// Tenant
			case "TENANT":
				locator = getProps().getProperty("tenantTab");
				break;
			case "ADD TENANT":
				locator = getProps().getProperty("addTenantButton");
				break;
			case "DELETE TENANT":
				locator = getProps().getProperty("deleteTenantButton");
				break;
			case "TENANT:DELETE_OK":
				locator = getProps().getProperty(
						"deleteTenantOkConfirmationButton");
				break;
			case "SAVE TENANT":
				locator = getProps().getProperty("addTenantPopupSaveButton");
				break;

			// Endpoint
			case "ENDPOINT":
				locator = getProps().getProperty("endpointTab");
				break;
			case "ADD ENDPOINT":
				BaseUtil.waitForSpinner(10000);
				locator = getProps().getProperty("addEndpointButton");
				break;
			case "SAVE ENDPOINT":
				locator = getProps().getProperty("addEndpointSaveButton");
				break;

			// Users
			case "USERS":
				locator = getProps().getProperty("userTab");
				break;
			case "ADD USERS":
				locator = getProps().getProperty("addUserButton");
				break;
			case "SAVE USER":
				locator = getProps().getProperty("addUsersSaveButton");
				break;
			case "USERS:X":
				locator = getProps().getProperty("editUserCloseButton");
				break;

			case "AUDIT:SEARCH":
				locator = getProps().getProperty("auditSearchButton");
				break;

			default:
				logger.error("Switch Case["
						+ buttonName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
			}

			BaseUtil.clickAndWait(locator);
			return true;

		} catch (Exception e) {
			logger.error("Failed to click on button : " + buttonName
					+ " , detail message: " + e.getMessage());
			return false;
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public boolean clickChooseButtonAndSelectInvalidFile(String buttonName,
			String tabName, String filePath) {
		try {
			switch (tabName.toUpperCase()) {
			case "ENDPOINT FIRMWARE":
				BaseUtil.uploadFile(
						getProps().getProperty("endpointFirmwareBrowseButton"),
						filePath);
				BaseUtil.explicitWait(2000);
				break;
			case "PERIPHERAL FIRMWARE":
				BaseUtil.uploadFile(
						getProps()
								.getProperty("peripheralFirmwareBrowseButton"),
						filePath);
				BaseUtil.explicitWait(2000);
				break;
			}
		} catch (Exception e) {
			logger.error("Failed to click on button :" + buttonName
					+ " , detail message: " + e.getMessage());
			return false;
		}
		return true;
	}

	public void enterTestData(String list) {
		try {
			Calendar calendar;
			Date releaseDate;
			DateFormat outputFormatter;
			String enteredDate = null, flag = null, locator = null, testData = null;
			calendar = Calendar.getInstance();
			outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
			releaseDate = calendar.getTime();
			enteredDate = outputFormatter.format(releaseDate);
			switch (list.toUpperCase()) {

			// Endpoint Firmware Test Data
			case "NAME":
				flag = "Field";
				locator = getProps()
						.getProperty("addEndpointFirmwareNameField");
				testData = getProps().getProperty(
						"endpointFirmwareNameTestData");
				break;
			case "VERSION":
				flag = "Field";
				locator = getProps().getProperty(
						"addEndpointFirmwareVersionField");
				testData = getProps().getProperty(
						"endpointFirmwareVersionTestData");
				break;
			case "ENDPOINT TYPE":
				flag = "Dropdown";
				locator = getProps().getProperty(
						"addEndpointFirmwareEndpointTypeField");
				testData = getProps().getProperty(
						"endpointFirmwareEndpointTypeTestData");
				break;
			case "ENDPOINT FIRMWARE:RELEASE DATE":
				flag = "Field";
				locator = getProps().getProperty(
						"addEndpointFirmwareReleaseDateField");
				testData = getProps().getProperty(
						"endpointFirmwareReleaseDateTestData");
				break;

			// Tenant Test Data
			case "TENANT NAME":
				flag = "Field";
				locator = getProps().getProperty("addTenantNameField");
				testData = getProps().getProperty("addTenantNameTestData");
				break;
			case "TENANT PARENT":
				flag = "ComboBox";
				BaseUtil.clickAndWait(getProps().getProperty(
						"addTenantParentField"));
				locator = getProps().getProperty(
						"addTenantTenantDropdownListField");
				testData = getProps().getProperty("addTenantParentTestData");
				break;
			case "TENANT TYPE":
				flag = "Dropdown";
				locator = getProps().getProperty("addTenantTenantTypeField");
				testData = getProps()
						.getProperty("addTenantTenantTypeTestData");
				break;

			// Site Test Data
			case "TENANT DROPDOWN":
				flag = "ComboBox";
				BaseUtil.clickAndWait(getProps().getProperty(
						"addSiteTenantDropdownField"));
				locator = getProps().getProperty(
						"addSiteTenantDropdownListField");
				testData = getProps().getProperty(
						"addSiteTenantDropdownFieldTestData");
				break;
			case "SITE ID":
				flag = "Field";
				locator = getProps().getProperty("addSiteSiteIDField");
				testData = getProps().getProperty("addSiteSiteIDFieldTestData");
				break;
			case "SITE DESCRIPTION":
				flag = "Field";
				locator = getProps().getProperty("addSiteSiteNameField");
				testData = getProps().getProperty(
						"addSiteSiteNameFieldTestData");
				break;
			case "ADDRESS1":
				flag = "Field";
				locator = getProps().getProperty("addSiteAddress1Field");
				testData = getProps().getProperty(
						"addSiteAddress1FieldTestData");
				break;
			case "CITY":
				flag = "Field";
				locator = getProps().getProperty("addSiteCityField");
				testData = getProps().getProperty("addSiteCityFieldTestData");
				break;
			case "STATE":
				flag = "Dropdown";
				locator = getProps().getProperty("addSiteStateField");
				testData = getProps().getProperty("addSiteStateFieldTestData");
				break;
			case "POSTAL CODE":
				flag = "Field";
				locator = getProps().getProperty("addSitePostalCodeField");
				testData = getProps().getProperty(
						"addSitePostalCodeFieldTestData");
				break;
			case "COUNTRY":
				flag = "Dropdown";
				locator = getProps().getProperty("addSiteCountryField");
				testData = getProps()
						.getProperty("addSiteCountryFieldTestData");
				break;
			case "PHONE 1":
				flag = "Field";
				locator = getProps().getProperty("addSitePhone1Field");
				testData = getProps().getProperty("addSitePhone1FieldTestData");
				break;
			case "COMMISSION DATE":
				flag = "Field";
				locator = getProps().getProperty("addSiteCommissionDateField");
				testData = getProps().getProperty(
						"addSiteCommissionDateFieldTestData");
				break;
			case "AREA":
				flag = "Field";
				locator = getProps().getProperty("addSiteAreaField");
				testData = getProps().getProperty("addSiteAreaFieldTestData");
				break;
			case "PATH":
				flag = "Field";
				locator = getProps().getProperty("addSitePathField");
				testData = getProps().getProperty("addSitePathFieldTestData");
				break;

			// Endpoint TestData
			case "SERIAL":
				flag = "Field";
				locator = getProps().getProperty("addEndpointSerialField");
				testData = getProps().getProperty(
						"addEndpointSerialFieldTestData");
				break;
			case "TYPE":
				flag = "Dropdown";
				locator = getProps().getProperty("addEndpointTypeField");
				testData = getProps().getProperty(
						"addEndpointTypeFieldTestData");
				break;

			// Users TestData
			case "USERNAME":
				flag = "Field";
				locator = getProps().getProperty("addUserUsernameField");
				testData = getProps().getProperty(
						"addUserUsernameFieldTestData");
				break;
			case "FIRST NAME":
				flag = "Field";
				locator = getProps().getProperty("addUserFirstnameField");
				testData = getProps().getProperty(
						"addUserFirstnameFieldTestData");
				break;
			case "LAST NAME":
				flag = "Field";
				locator = getProps().getProperty("addUserLastNameField");
				testData = getProps().getProperty(
						"addUserLastNameFieldTestData");
				break;
			case "EMAIL":
				flag = "Field";
				locator = getProps().getProperty("addUserEmailField");
				testData = getProps().getProperty("addUserEmailFieldTestData");
				break;
			case "LOCALE":
				flag = "Dropdown";
				locator = getProps().getProperty("addUserLocaleField");
				testData = getProps().getProperty("addUserLocaleFieldTestData");
				break;
			case "UNIT OF TEMPERATURE":
				flag = "Dropdown";
				locator = getProps().getProperty(
						"addUserUnitOfTemperatureField");
				testData = getProps().getProperty(
						"addUserUnitOfTemperatureFieldTestData");
				break;
			case "ROLE":
				flag = "Dropdown";
				locator = getProps().getProperty("addUserRoleField");
				testData = getProps().getProperty("addUserRoleFieldTestData");
				break;
			default:
				logger.error("Switch Case["
						+ list
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
			}

			testData = testData.replace("#index#", enteredDate);

			if (flag.equalsIgnoreCase("Field")) {
				BaseUtil.enterText(locator, testData);
			} else if (flag.equalsIgnoreCase("Dropdown")) {
				BaseUtil.selectDropDownByValue(locator, testData);
			} else if (flag.equalsIgnoreCase("ComboBox")) {
				BaseUtil.selectComboBoxDropDownByValue(locator, testData);
			}
		} catch (Exception e) {
			logger.error("Failed to enter text in field :" + list
					+ " see detail message : " + e.getMessage());
		}
	}

	public void checkCheckBox(List<String> checkBoxList) {
		String message = null, locator = null;
		for (String checkbox : checkBoxList) {
			message = "[" + checkbox
					+ "] is not available in the general template";
			try {
				switch (checkbox.toUpperCase()) {
				case "ACTIVE":
					locator = getProps().getProperty("addSiteActiveCheckbox");
					break;
				case "HAS CONTROL":
					locator = getProps().getProperty(
							"addSiteHasControlCheckbox");
					break;
				default:
					logger.error("Switch Case["
							+ checkbox
							+ "] is not matched in class["
							+ getClass().getName()
							+ "] , Method["
							+ Thread.currentThread().getStackTrace()[1]
									.getMethodName() + "]");
					Assert.fail("Invalid element name: " + checkbox
							+ " selected");
				}

				BaseUtil.checkCheckBox(locator);
			} catch (Exception e) {
				logger.error("Failed to Check the Checkbox: " + checkbox
						+ " see detail message: " + e.getMessage());
				Assert.fail(message);
			}
		}
	}

	public void verifySearchFunctionality(String arg) {
		String[] arr = arg.split(":");
		String keyword = arr[1];
		String totalItemsText = null;
		List<WebElement> listAuditNames = null;
		int initialTotalItemsCount = 0;
		int laterTotalItemsCount = 0;
		WebElement searchBox = null;
		try {
			switch (keyword.toUpperCase()) {
			case "INIT3CHAR":
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("auditTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());

				BaseUtil.enterText(getProps().getProperty("auditSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("auditTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				Assert.assertTrue("Search results after entering 3 characters "
						+ arr[0] + " is not as expected",
						laterTotalItemsCount < initialTotalItemsCount);
				break;
			case "RANDOM":

				String randomSearchString = RandomStringUtils
						.randomAlphanumeric(17).toLowerCase();
				BaseUtil.enterText(getProps().getProperty("auditSearchField"),
						randomSearchString);
				BaseUtil.waitForSpinner(1000);
				BaseUtil.click(getProps().getProperty("auditSearchButton"));
				BaseUtil.waitForSpinner(1000);
				try {
					Assert.assertTrue(
							"Multiple results found for the string : "
									+ randomSearchString,
							BaseUtil.getElementText(
									getProps().getProperty(
											"auditGridZeroRecord")).equals(
									getProps().getProperty(
											"auditGridZeroRecordText")));
				} catch (Exception ex) {
					listAuditNames = BaseUtil
							.getMultipleElementsAfterLoaded(getProps()
									.getProperty("auditGridNameColumn"));
					logger.info("In case RANDOM the list size is : "
							+ listAuditNames.size());
					for (WebElement tenantNameElement : listAuditNames) {
						if (!tenantNameElement.getText().contains(
								randomSearchString)) {
							Assert.fail("Tenant search results are not as expected for string : "
									+ randomSearchString);
						}
					}
				}
				break;
			case "LONGTEXT":
				String longSearchString = RandomStringUtils.randomAlphanumeric(
						25).toLowerCase();
				BaseUtil.enterText(getProps().getProperty("auditSearchField"),
						longSearchString);
				BaseUtil.waitForSpinner(3000);
				BaseUtil.click(getProps().getProperty("auditSearchButton"));
				BaseUtil.waitForSpinner();
				try {
					Assert.assertTrue(
							"Multiple results found for the string : "
									+ longSearchString,
							BaseUtil.getElementText(
									getProps().getProperty(
											"auditGridZeroRecord")).equals(
									getProps().getProperty(
											"auditGridZeroRecordText")));
				} catch (Exception ex) {
					listAuditNames = BaseUtil
							.getMultipleElementsAfterLoaded(getProps()
									.getProperty("auditGridNameColumn"));
					logger.info("In case LONGTEXT the list size is : "
							+ listAuditNames.size());
					for (WebElement siteNameElement : listAuditNames) {
						if (!siteNameElement.getText().contains(
								longSearchString)) {
							Assert.fail("Audit search results are not as expected for string : "
									+ longSearchString);
						}
					}
				}
				break;
			case "BACKSPACE":
				BaseUtil.enterText(getProps().getProperty("auditSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner(1000);
				BaseUtil.click(getProps().getProperty("auditSearchButton"));
				BaseUtil.waitForSpinner(2000);

				listAuditNames = BaseUtil
						.getMultipleElementsAfterLoaded(getProps().getProperty(
								"auditGridNameColumn"));
				logger.info("Search size before enter backspace: "
						+ listAuditNames.size());
				searchBox = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("auditSearchField"));
				searchBox.sendKeys(Keys.BACK_SPACE);
				BaseUtil.waitForSpinner(2000);
				List<WebElement> newSearchListAuditNames = BaseUtil
						.getMultipleElementsAfterLoaded(getProps().getProperty(
								"auditGridNameColumn"));
				logger.info("Search size after enter backspace: "
						+ newSearchListAuditNames.size());
				if (newSearchListAuditNames.size() < listAuditNames.size()) {
					Assert.fail("Audit results are not as expected when backspace is clicked on site search");
				}
				break;
			case "DELETE":
				BaseUtil.enterText(getProps().getProperty("auditSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner(1000);
				BaseUtil.click(getProps().getProperty("auditSearchButton"));
				BaseUtil.waitForSpinner(2000);
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("auditTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				searchBox = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("auditSearchButton"));
				searchBox.sendKeys(Keys.CONTROL + "a");
				searchBox.sendKeys(Keys.DELETE);
				BaseUtil.waitForSpinner(2000);
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("auditTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				if (laterTotalItemsCount < initialTotalItemsCount) {
					Assert.fail("Audit search results after deleting the keyword "
							+ arr[0] + " is not as expected");
				}
				break;
			case "PAGINATION":
				BaseUtil.enterText(getProps().getProperty("auditSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				boolean nextFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("auditPaginationNext"));
				boolean lastFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("auditPaginationLast"));
				boolean prevFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("auditPaginationPrev"));
				boolean firstFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("auditPaginationFirst"));
				if (!(nextFlg && lastFlg && prevFlg && firstFlg)) {
					Assert.fail("All the pagination buttons are not visible");
				}
				break;
			case "DATESTAMP":
				searchBox = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("auditSearchField"));
				searchBox.sendKeys(Keys.CONTROL + "a");
				searchBox.sendKeys(Keys.DELETE);
				BaseUtil.waitForSpinner(2000);

				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("auditTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());

				// enterTextAndWait(auditPageObjectRepo, arr[0]);
				BaseUtil.enterText(getProps().getProperty("auditSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner(2000);
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("auditTotalItemsCount"));
				try {
					if (BaseUtil.instantElementCheck(
							"Display",
							By.xpath(getProps().getProperty(
									"auditGridZeroRecord").split("===")[1]))) {
						if (BaseUtil
								.getElementText(
										getProps().getProperty(
												"auditGridZeroRecord"))
								.equalsIgnoreCase(
										BaseUtil.getElementText(getProps()
												.getProperty(
														"auditGridZeroRecordText")))) {
							logger.info("No record available during tenant search. condition : [DATESTAMP]");
						}
					}
				} catch (Exception e) {
					logger.info("Sufficient records are available");
				}
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				Assert.assertTrue("Search results after entering 3 characters "
						+ arr[0] + " with date stamp is not as expected",
						laterTotalItemsCount <= initialTotalItemsCount);
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

	public boolean validateDatePicker(List<Map<String, String>> dates) {
		try {
			BaseUtil.enterText(getProps().getProperty("auditStartDateText"),
					dates.get(0).get("startDate"));
			BaseUtil.explicitWait(3000);
			BaseUtil.enterText(getProps().getProperty("auditEndDateText"),
					dates.get(0).get("endDate"));
			BaseUtil.explicitWait(3000);

			return true;
		} catch (Exception e) {
			logger.error("Some error occured while setting the date["
					+ dates.get(0) + "] in datepicker : " + e.getMessage());
			return false;
		}
	}

	public void enterText_AuditPage(String sElementName, String sText) {
		String sCaseOptions = "sElementName, ";
		try {
			switch (sElementName.toUpperCase()) {
			case "SITE_SEARCH":
				BaseUtil.enterText(getProps().getProperty("auditSearchField"),
						sText);
				BaseUtil.waitForSpinner();
				break;
			case "ENDPOINT_FIRMWARE_SEARCH":
				BaseUtil.enterText(getProps().getProperty("auditSearchField"),
						sText);
				BaseUtil.waitForSpinner();
				BaseUtil.explicitWait(10000);
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

	public void verifyDetails(String scolumnName, String caseType) {
		try {
			Calendar calendar;
			Date releaseDate;
			DateFormat outputFormatter;
			String enteredDate = null;

			calendar = Calendar.getInstance();

			String elementTestData = null;
			switch (scolumnName.toUpperCase()) {
			case "SITE ID":
				elementTestData = BaseUtil.getElementAttribute(getProps()
						.getProperty("auditPageEditSiteNameField"), "value");
				if (caseType.equalsIgnoreCase("New")) {
					Assert.assertTrue(elementTestData
							.equalsIgnoreCase(getProps().getProperty(
									"addSiteNameTestData")));
				} else if (caseType.equalsIgnoreCase("Updated")) {
					Assert.assertTrue(elementTestData
							.equalsIgnoreCase(getProps().getProperty(
									"addSiteUpdatedNameTestData")));
				}
				break;
			case "SITE DESCRIPTION":
				elementTestData = BaseUtil.getElementAttribute(getProps()
						.getProperty("auditPageEditSiteDescriptionField"),
						"value");
				Assert.assertTrue(elementTestData.equalsIgnoreCase(getProps()
						.getProperty("addSiteDescriptionTestData")));
				break;
			case "ADDRESS":
				elementTestData = BaseUtil
						.getElementAttribute(
								getProps().getProperty(
										"auditPageEditSiteAddress1Field"),
								"value");
				Assert.assertTrue(elementTestData.equalsIgnoreCase(getProps()
						.getProperty("addSiteAddress1TestData")));
				break;
			case "CITY":
				elementTestData = BaseUtil.getElementAttribute(getProps()
						.getProperty("auditPageEditSiteCityField"), "value");
				Assert.assertTrue(elementTestData.equalsIgnoreCase(getProps()
						.getProperty("addSiteCityTestData")));
				break;
			case "POSTAL CODE":
				elementTestData = BaseUtil.getElementAttribute(getProps()
						.getProperty("auditPageEditSitePostalCodeField"),
						"value");
				Assert.assertTrue(elementTestData.equalsIgnoreCase(getProps()
						.getProperty("addSitePostalCodeTestData")));
				break;
			case "ENDPOINT FIRMWARE:NAME":
				elementTestData = BaseUtil.getElementAttribute(getProps()
						.getProperty("editEndpointFirmwareNameTextField"),
						"value");
				if (caseType.equalsIgnoreCase("New")) {
					Assert.assertTrue(elementTestData
							.equalsIgnoreCase(getProps().getProperty(
									"endpointFirmwareNameTestData")));
				} else if (caseType.equalsIgnoreCase("Updated")) {
					Assert.assertTrue(elementTestData
							.equalsIgnoreCase(getProps().getProperty(
									"endpointFirmwareVersionModifiedTestData")));
				}
			case " ENDPOINT FIRMWARE:VERSION":
				elementTestData = BaseUtil.getElementAttribute(getProps()
						.getProperty("editEndpointFirmwareVersionTextField"),
						"value");
				if (caseType.equalsIgnoreCase("New")) {
					Assert.assertTrue(elementTestData
							.equalsIgnoreCase(getProps().getProperty(
									"endpointFirmwareVersionTestData")));
				} else if (caseType.equalsIgnoreCase("Updated")) {
					Assert.assertTrue(elementTestData
							.equalsIgnoreCase(getProps().getProperty(
									"endpointFirmwareVersionModifiedTestData")));
				}
				break;
			case "ENDPOINT FIRMWARE:RELEASE DATE":
				elementTestData = BaseUtil.getElementAttribute(
						getProps().getProperty(
								"editEndpointFirmwareReleaseDateTextField"),
						"value");
				if (caseType.equalsIgnoreCase("New")) {
					outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
					releaseDate = calendar.getTime();
					enteredDate = outputFormatter.format(releaseDate);
					Assert.assertTrue(elementTestData
							.equalsIgnoreCase(getProps().getProperty(
									"endpointFirmwareReleaseDateTestData")
									.replace("#index#", enteredDate)));
				} else if (caseType.equalsIgnoreCase("Updated")) {
					calendar.add(Calendar.DAY_OF_YEAR, 7);
					outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
					releaseDate = calendar.getTime();
					enteredDate = outputFormatter.format(releaseDate);
					Assert.assertTrue(elementTestData
							.equalsIgnoreCase(getProps()
									.getProperty(
											"endpointFirmwareReleaseDateModifiedTestData")
									.replace("#index#", enteredDate)));
				}
				break;
			default:
				logger.error("Switch Case["
						+ scolumnName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Failed to check verification [" + scolumnName
						+ "] because you passed wrong parameter[" + scolumnName
						+ "] either in feature file or test class.");
			}
		} catch (Exception e) {
			logger.error("Failed to check existence of popup:" + scolumnName
					+ "in audit page. , see detail error message \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to check existence of popup: [" + scolumnName
					+ "] in audit page. see detail error message \n"
					+ e.getStackTrace().toString());
		}

	}

	public void selectAuditFromGrid_AuditPage(String sSiteName) {
		boolean bResult = false;
		logger.debug("Selecting the site :" + sSiteName);
		try {
			List<WebElement> lstSiteNames = BaseUtil
					.getMultipleElementsAfterLoaded(getProps().getProperty(
							"auditGridUpdatedValueColumn"));
			for (WebElement webElement : lstSiteNames) {
				if (webElement.getText().trim().equalsIgnoreCase(sSiteName)) {
					webElement.click();
					BaseUtil.waitForSpinner();
					BaseUtil.explicitWait(10000);
					bResult = true;
					break;
				}
			}
			if (!bResult) {
				Assert.fail("Given test site is not available");
			}
			logger.debug("site :" + sSiteName + " is selected.");
		} catch (Exception e) {
			logger.error("Failed to select site: " + sSiteName
					+ " from site grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the site : " + sSiteName
					+ ", see detail error message: \n"
					+ e.getStackTrace().toString());
		}
	}

	public boolean checkPopupExistence(String sPopName, String sExpMsg) {
		try {
			switch (sPopName.toUpperCase()) {
			case "EDIT_SITE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSitePagePopup"));
			case "EDIT_ENDPOINTFIRMWARE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editEndpointFirmwarePagePopup"));
			default:
				logger.error("Switch Case["
						+ sPopName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to check existence of popup:" + sPopName
					+ " , detail message: " + e.getMessage());
			return false;
		}
	}

	public void modifyTextEndpointFirmwareAudit(String list) {
		try {
			Calendar calendar;
			Date releaseDate;
			DateFormat outputFormatter;
			String enteredDate = null, flag = null, locator = null, testData = null;

			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, 7);
			outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
			releaseDate = calendar.getTime();
			enteredDate = outputFormatter.format(releaseDate);
			switch (list.toUpperCase()) {
			case "NAME":
				flag = "Field";
				locator = getProps().getProperty(
						"editEndpointFirmwareNameTextField");
				testData = getProps().getProperty(
						"endpointFirmwareVersionModifiedTestData");
				break;
			case "RELEASE DATE":
				flag = "Field";
				locator = getProps().getProperty(
						"editEndpointFirmwareReleaseDateTextField");
				testData = getProps().getProperty(
						"endpointFirmwareReleaseDateModifiedTestData");
				break;
			case "VERSION":
				flag = "Field";
				locator = getProps().getProperty(
						"editEndpointFirmwareVersionTextField");
				testData = getProps().getProperty(
						"endpointFirmwareVersionModifiedTestData");
				break;
			default:
				logger.error("Switch Case["
						+ list
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
			}

			testData = testData.replace("#index#", enteredDate);

			if (flag.equalsIgnoreCase("Field")) {
				BaseUtil.enterText(locator, testData);
			}
		} catch (Exception e) {
			logger.error("Failed to enter text in field :" + list
					+ " see detail message : " + e.getMessage());
		}

	}

}
