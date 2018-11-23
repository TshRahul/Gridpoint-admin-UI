package com.gridpoint.adminui.automation.site;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.gridpoint.automation.constants.PathConstants;
import com.gridpoint.automation.database.GPConnection;
import com.gridpoint.automation.database.GPDataBaseUtil;
import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.automation.tests.base.TestBase;
import com.gridpoint.util.BaseUtil;

public class SitePage extends CommonInit {
	private Logger logger = Logger.getLogger(SitePage.class);

	PathConstants path;

	public boolean clickOperationOnSitePage(String buttonName) {
		try {
			String locator = null;
			switch (buttonName.toUpperCase()) {

			// Sites
			case "SITES":
				BaseUtil.explicitWait(1000);
				BaseUtil.clickAndWait(getProps().getProperty("sitesTab"));
				BaseUtil.explicitWait(2000);
				BaseUtil.waitForSpinner();
				return true;
			case "CLEAR":
				locator = getProps().getProperty("siteTreeClearbtn");
				break;
			case "ADD SITE":
				BaseUtil.explicitWait(1000);
				BaseUtil.clickAndWait(getProps().getProperty("addSiteButton"));
				BaseUtil.explicitWait(4000);
				BaseUtil.waitForSpinner();
				return true;
			case "PORTAL:SITE SEARCH":
				BaseUtil.explicitWait(1000);
				BaseUtil.clickAndWait(getProps()
						.getProperty("siteSearchButton"));
				BaseUtil.waitForSpinner();
				return true;
			case "SELECT ALL":
				BaseUtil.click(getProps().getProperty("siteTreeSelectAllbtn"));
				BaseUtil.waitForSpinner();
				return true;
			case "DELETE SITE":
				locator = getProps().getProperty("deleteSiteButton");
				break;
			case "SITE:DELETE_OK":
				locator = getProps().getProperty(
						"deleteSiteOkConfirmationButton");
				break;
			case "GET COORDINATES":
				locator = getProps().getProperty(
						"addressVerificationGetCordinatesbutton");
				break;
			case "OVERRIDE":
				BaseUtil.checkCheckBox(getProps().getProperty(
						"addressVerificationSuppliedOverrideCheckbox"));
				BaseUtil.verifyElementEnabled(getProps().getProperty(
						"addressVerificationSuppliedOverrideCheckbox"));
				return true;
			case "TIMEZONE WARNING:CANCEL":
				locator = getProps().getProperty(
						"addSiteAbnormalBehaviorCancelConfirmationButton");
				break;
			case "TIMEZONE WARNING:OK":
				locator = getProps().getProperty("addSiteOkConfirmationButton");
				break;
			case "CANCEL":
				locator = getProps().getProperty("addSiteCancelButton");
				break;
			case "HIDE INACTIVE":
				BaseUtil.unCheckCheckBox(getProps().getProperty(
						"hideInactiveCheckBox"));
				return true;
			case "SAVE AND CLOSE":
				BaseUtil.clickAndWait(getProps().getProperty(
						"editSitePageSaveAndCloseSiteButton"));
				BaseUtil.waitForSpinner();
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addSiteSaveMsg"));
				return true;
			case "SEARCH":
				locator = getProps().getProperty("siteSearchButton");
				break;
			case "AUDIT:SEARCH":
				locator = getProps().getProperty("auditSearchButton");
				break;	
			case "DECOMMISSION DATE:CHECKED":
				BaseUtil.explicitWait(3000);
				BaseUtil.checkCheckBox(getProps().getProperty(
						"editSiteDecommissionDateCheckbox"));
				return true;
			case "DELETE":
				locator = getProps().getProperty("editSiteDeleteButton");
				break;
			case "PV_AUDIT:CANCEL":
				locator = getProps().getProperty("pvAuditPopupCancelButton");
				break;
			case "EXPORT:ICON":
				BaseUtil.explicitWait(2000);
				BaseUtil.clickAndWait(getProps().getProperty("exportIcon"));
				return true;
			case "ADD VIRTUAL CHANNEL":
				BaseUtil.clickAndWait(getProps().getProperty(
						"channelAccordianAddVirtualChannelButton"));
				BaseUtil.waitForSpinner();
				return true;
			case "ACTIVE:UNCHECKED":
				BaseUtil.unCheckCheckBox(getProps().getProperty(
						"editSitePagePopupDetailActiveCheckbox"));
				return true;
			case "ADDRESS VERIFICATION:CANCEL":
				BaseUtil.unCheckCheckBox(getProps().getProperty(
						"editSitePageAddressVerificationCancelButton"));
				return true;	
					

				// Tenant
			case "TENANT":
				locator = getProps().getProperty("tenantTab");
				break;
			case "ADD TENANT":
				BaseUtil.explicitWait(1000);
				BaseUtil.clickAndWait(getProps().getProperty("addTenantButton"));
				BaseUtil.explicitWait(2000);
				BaseUtil.waitForSpinner();
				return true;
			case "DELETE TENANT":
				BaseUtil.explicitWait(3000);
				locator = getProps().getProperty("deleteTenantButton");
				break;
			case "TENANT:DELETE_OK":
				BaseUtil.explicitWait(2000);
				locator = getProps().getProperty(
						"deleteTenantOkConfirmationButton");
				break;
			case "SAVE TENANT":
				BaseUtil.explicitWait(1000);
				BaseUtil.clickAndWait(getProps().getProperty("addTenantPopupSaveButton"));
				BaseUtil.explicitWait(2000);
				BaseUtil.waitForSpinner();
				return true;
			case "ENDPOINTS":
				locator = getProps().getProperty("endpointsTab");
				break;
			case "ADD ENDPOINT":
				locator = getProps().getProperty("addEndpointButton");
				break;
			case "ENDPOINT SAVE":
				locator = getProps().getProperty("addEndpointPopupSaveButton");
				break;
			case "ENDPOINT SEARCH":
				locator = getProps().getProperty("endpointSearchButton");
				break;
			case "ENDPOINT:SAVE AND CLOSE	":
				locator = getProps().getProperty(
						"editEndpointSaveAndCloseButton");
				break;
			case "X":
				locator = getProps().getProperty("editSitePagecloseBtn");
				break;
			case "EXPORT:CHANNEL":
				locator = getProps().getProperty("exportChannelBtn");
				break;

			// Audit
			case "AUDIT":
				locator = getProps().getProperty("auditTab");
				break;

			// case "PATHS":
			// instantClick(getProps().getProperty("editSitePagePopupPathsAccordion"));
			// return true;
			// case "BULK SITE IMPORT":
			// clickAndWait(getProps().getProperty("bulkSiteImportButton"));
			// return true;
			// case "CHANNELS":
			// explicitWait(2000);
			// instantClick(getProps().getProperty("editSitePagePopupChannelAccordion"));
			// explicitWait(2000);
			// return true;
			// case "DETAILS":
			// instantClick(getProps().getProperty("editSitePagePoupDetailsAccordion"));
			// explicitWait(2000);
			// return true;
			// case "SAVE":
			// clickAndWait(getProps().getProperty("addSiteSaveButton"));
			// waitForSpinner(3000);
			// return true;
			// case "SAVE IMPORT":
			// clickAndWait(getProps().getProperty("bulkImportPopupImportButton"));
			// waitForSpinner();
			// return true;
			// case "CLOSE IMPORT":
			// clickAndWait(getProps().getProperty("bulkImportPopupCloseButton"));
			// return true;
			// case "EDIT:SAVE":
			// clickAndWait(getProps().getProperty("editSitePageSaveButton"));
			// return true;
			// case "VIRTUAL CHANNEL:SAVE":
			// explicitWait(1000);
			// clickAndWait(getProps().getProperty("addVirtualChannelSavebtn"));
			// return true;
			// case "VIRTUAL CHANNEL:EDIT":
			// clickAndWait(getProps().getProperty("editVirtualChannelBtn"));
			// return true;
			// case "VIRTUAL CHANNEL:UPDATE":
			// clickAndWait(getProps().getProperty("updateVirtualChannelBtn"));
			// return true;
			// case "VIRTUAL CHANNEL:VIEW":
			// clickAndWait(getProps().getProperty("viewVirtualChannelBtn"));
			// waitForSpinner();
			// return true;
			// case "VIRTUAL CHANNEL:CLOSE":
			// clickAndWait(getProps().getProperty("addVirtualChannelErrorMessageCloseButton"));
			// return true;
			// case "EDIT SITE CANCEL":
			// clickAndWait(getProps().getProperty("editSitePageCancelButton"));
			// return true;
			// case "X VIRTUAL CHANNEL":
			// clickAndWait(getProps().getProperty("addVirtualChannelWindowCloseButton"));
			// return true;
			// case "ACCEPT SUPPLIED DATA":
			// explicitWait(2000);
			// clickAndWait(getProps().getProperty("addressVerificationAcceptSuppliedDataButton"));
			// return true;
			// case "ACCEPT VERIFIED DATA":
			// clickAndWait(getProps().getProperty("addressVerificationAcceptVerifiedDataButton"));
			// return true;
			// case "X:CLOSE":
			// clickAndWait(getProps().getProperty("editSitePagecloseBtn"));
			// return true;
			// case "DELETE:OK":
			// clickAndWait(getProps().getProperty("oKConfirmationButton"));
			// return true;
			// case "ADD PATH":
			// clickAndWait(getProps().getProperty("addSiteAddPathsButton"));
			// return true;
			// case "HIDE INACTIVE:UNCHECKED":
			// label =
			// getElementText(getProps().getProperty("hideInactiveCheckBoxLabel"));
			// if (label.equals(buttonName)) {
			// Assert.assertTrue("The label text is given as =" + label, true);
			// }
			// unCheckCheckBox(getProps().getProperty("hideInactiveCheckBox"));
			// return true;
			// case "HIDE INACTIVE:CHECKED":
			// label =
			// getElementText(getProps().getProperty("hideInactiveCheckBoxLabel"));
			// if (label.equals(buttonName)) {
			// Assert.assertTrue("The label text is given as =" + label, true);
			// }
			// checkCheckBox(getProps().getProperty("hideInactiveCheckBox"));
			// return true;
			// case "DECOMMISSION DATE:UNCHECKED":
			// unCheckCheckBox(getProps().getProperty("editSiteDecommissionDateCheckbox"));
			// return true;
			// case "LOGOUT":
			// explicitWait(2000);
			// clickAndWait(getProps().getProperty("optionDropDown"));
			// explicitWait(1000);
			// clickAndWait(getProps().getProperty("logout"));
			// assertElementDisplayed(getProps().getProperty("adminUserName_Locator"),
			// "Error while logging out for user");
			// return true;
			// case "VIRTUAL CHANNEL:REMOVE":
			// if (instantElementCheck("Display",
			// getProps().getProperty("removeVirtualChannelRemoveButton"))) {
			// clickAndWait(getProps().getProperty("removeVirtualChannelRemoveButton"));
			// return true;
			// }
			// return true;
			// case "VIRTUAL CHANNEL:OK":
			// if (instantElementCheck("Display",
			// getProps().getProperty("removeVirtualChannelOkButton"))) {
			// clickAndWait(getProps().getProperty("removeVirtualChannelOkButton"));
			// explicitWait(3000);
			// return true;
			// }
			// explicitWait(2000);
			// clickAndWait(getProps().getProperty("editSitePagePopupChannelAccordion"));
			// return true;
			// case "EXPAND ARROW":
			// clickAndWait(getProps().getProperty("expandArrowHomeTab"));
			// return true;
			default:
				logger.error("Switch Case["
						+ buttonName
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
			logger.error("Failed to click on button :" + buttonName
					+ " , detail message: " + e.getMessage());
			return false;
		}
	}

	public void verifySorting_SitePage(String sSortingType, String sColName) {
		List<String> listColumnValues = null;
		try {
			switch (sColName.toUpperCase()) {
			case "SITE NAME":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty("siteGridNameHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty("siteGridNameHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("siteGridNameHeader"));
				break;
			case "SITE DESCRIPTION":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"siteGridDescriptionHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"siteGridDescriptionHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("siteGridDescriptionHeader"));
				break;
			case "ADDRESS":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"siteGridAddressHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"siteGridAddressHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("siteGridAddressHeader"));
				break;
			case "POSTAL CODE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"siteGridPostalCodeHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"siteGridPostalCodeHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("siteGridPostalCodeHeader"));
				break;
			case "TIMEZONE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"siteGridTimeZoneHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"siteGridTimeZoneHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("siteGridTimeZoneHeader"));
				break;
			case "TENANT":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"siteGridTenantHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"siteGridTenantHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("siteGridTenantHeader"));
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

	public void clickPagination(String sLinkName) {
		try {
			// waitForElementPresentVisible(By.xpath("//*[@id='sitesTable']/div[2]/div[2]/div/span"));
			BaseUtil.verifyElementEnabled(getProps().getProperty(
					"siteGridTotalItems"));
			switch (sLinkName.toUpperCase()) {
			case "NEXT":
				BaseUtil.explicitWait(5000);
				if (BaseUtil.verifyElementEnabled(getProps().getProperty(
						"sitePaginationNext"))) {
					BaseUtil.click(getProps().getProperty("sitePaginationNext"));
					BaseUtil.explicitWait(2000);
				} else {
					Assert.fail("Required data is not present. Next button is not enabled");
				}
				break;
			case "PREV":
				if (BaseUtil.verifyElementEnabled(getProps().getProperty(
						"sitePaginationPrev"))) {
					BaseUtil.click(getProps().getProperty("sitePaginationPrev"));
					BaseUtil.explicitWait(2000);
				} else {
					Assert.fail("Required data is not present. Prev button is not enabled");
				}
				break;
			case "LAST":
				if (BaseUtil.verifyElementEnabled(getProps().getProperty(
						"sitePaginationLast"))) {
					BaseUtil.click(getProps().getProperty("sitePaginationLast"));
					BaseUtil.explicitWait(2000);
				} else {
					Assert.fail("Required data is not present. Last button is not enabled");
				}
				break;
			case "FIRST":
				if (BaseUtil.verifyElementEnabled(getProps().getProperty(
						"sitePaginationFirst"))) {
					BaseUtil.click(getProps()
							.getProperty("sitePaginationFirst"));
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
						.getProperty("sitePaginationCurrentPage"), "value");
				Assert.assertTrue(
						"Next page is not displayed",
						(null != pageNumber && Integer.parseInt(pageNumber) > 1));
				break;
			case "PREVIOUS":
				totalPages = BaseUtil.getElementText(getProps().getProperty(
						"sitePaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1)
						.trim();
				currentPage = BaseUtil.getElementAttribute(getProps()
						.getProperty("sitePaginationCurrentPage"), "value");
				Assert.assertTrue("Previous page is not displayed",
						(null != totalPages && null != currentPage && Integer
								.parseInt(currentPage) < Integer
								.parseInt(totalPages)));
				break;
			case "LAST":
				totalPages = BaseUtil.getElementText(getProps().getProperty(
						"sitePaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1)
						.trim();
				currentPage = BaseUtil.getElementAttribute(getProps()
						.getProperty("sitePaginationCurrentPage"), "value");
				Assert.assertTrue(
						"Last page is not displayed",
						(Integer.parseInt(totalPages) == Integer
								.parseInt(currentPage)
								&& !BaseUtil.verifyElementEnabled(getProps()
										.getProperty("sitePaginationNext")) && !BaseUtil
								.verifyElementEnabled(getProps().getProperty(
										"sitePaginationLast"))));
				break;
			case "FIRST":
				currentPage = BaseUtil.getElementAttribute(getProps()
						.getProperty("sitePaginationCurrentPage"), "value");
				Assert.assertTrue(
						"First page is not displayed",
						(Integer.parseInt(currentPage) == 1)
								&& !BaseUtil.verifyElementEnabled(getProps()
										.getProperty("sitePaginationFirst"))
								&& !BaseUtil.verifyElementEnabled(getProps()
										.getProperty("sitePaginationPrev")));
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
					getProps().getProperty("sitePaginationCurrentPage"),
					"value"));
			if (iCurrentPage >= iPageNum) {
				iDiff = iCurrentPage - iPageNum;
				sArrow = "DOWN";
			} else {
				iDiff = iPageNum - iCurrentPage;
				sArrow = "UP";
			}
			WebElement element = BaseUtil.getElementAfterLoaded(getProps()
					.getProperty("sitePaginationCurrentPage"));
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

	public void enterTestData(String num, String list) {
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

			// Tenant Test Data
			case "TENANT NAME":
				flag = "Field";
				locator = getProps().getProperty("addTenantNameField");
				if (num.equalsIgnoreCase("First")) {
					testData = getProps().getProperty("addTenantNameTestData");
				} else {
					testData = getProps().getProperty("addTenantNameTestData2");
				}
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
				if (num.equalsIgnoreCase("First")) {
					testData = getProps().getProperty(
							"addSiteTenantDropdownFieldTestData");
				} else {
					testData = getProps().getProperty(
							"addSiteTenantDropdownFieldTestData2");
				}

				break;
			case "SITE ID":
				flag = "Field";
				locator = getProps().getProperty("addSiteSiteIDField");
				if (num.equalsIgnoreCase("First")) {
					testData = getProps().getProperty(
							"addSiteSiteIDFieldTestData");
				} else {
					testData = getProps().getProperty(
							"addSiteSiteIDFieldTestData2");
				}
				break;

			case "SITE DESCRIPTION":
				flag = "Field";
				locator = getProps().getProperty("addSiteSiteNameField");
				if (num.equalsIgnoreCase("First")) {
					testData = getProps().getProperty(
							"addSiteSiteNameFieldTestData");
				} else {
					testData = getProps().getProperty(
							"addSiteSiteNameFieldTestData2");
				}
				break;

			case "ADDRESS1":
				flag = "Field";
				locator = getProps().getProperty("addSiteAddress1Field");
				testData = getProps().getProperty(
						"addSiteAddress1FieldTestData");
				break;
			case "ADDRESS2":
				flag = "Field";
				locator = getProps().getProperty("addSiteAddress2Field");
				testData = getProps().getProperty(
						"addSiteAddress2FieldTestData");
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
			case "PHONE 2":
				flag = "Field";
				locator = getProps().getProperty("addSitePhone2Field");
				testData = getProps().getProperty("addSitePhone2FieldTestData");
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
				if (num.equalsIgnoreCase("First")) {
					testData = getProps().getProperty(
							"addSitePathsFieldTestData");
				} else {
					testData = getProps().getProperty(
							"addSitePathsFieldTestData2");
				}
				break;
			case "SYSTEM SIZE":
				flag = "Field";
				locator = getProps().getProperty(
						"addSiteSolarPanelSystemSizeField");
				testData = getProps().getProperty(
						"addSiteSolarPanelSystemSizeTestData");
				break;
			case "LATITUDE":
				flag = "Field";
				locator = getProps().getProperty(
						"editSitePagePopupDetailLatitude");
				testData = num;
				break;	
			case "longitude":
				flag = "Field";
				locator = getProps().getProperty(
						"editSitePagePopupDetailLongitude");
				testData = num;
				break;		

			// Endpoint TestData
			case "SERIAL":
				flag = "Field";
				locator = getProps().getProperty("addEndpointSerialField");
				if (num.equalsIgnoreCase("First")) {
					testData = getProps().getProperty(
							"addEndpointSerialFieldTestData");
				} else {
					testData = getProps().getProperty(
							"addEndpointSerialFieldTestData1");
				}
				break;
			case "TYPE":
				flag = "Dropdown";
				locator = getProps().getProperty("addEndpointTypeField");
				testData = getProps().getProperty(
						"addEndpointTypeFieldTestData");
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

	public boolean isElementDisplayedOnSitePage(String elementName) {
		String locator = null;
		try {
			switch (elementName.toUpperCase()) {
			case "TENANT DROPDOWN":
				locator = getProps().getProperty("addSiteTenantDropDown");
				break;
			case "SITE NAME":
				locator = getProps().getProperty("addSiteNameField");
				break;
			case "SITE DESCRIPTION":
				locator = getProps().getProperty("addSiteDescriptionField");
				break;
			case "ADDRESS1":
				locator = getProps().getProperty("addSiteAddress1Field");
				break;
			case "ADDRESS2":
				locator = getProps().getProperty("addSiteAddress2Field");
				break;
			case "PHONE1":
				locator = getProps().getProperty("addSitePhone1Field");
				break;
			case "PHONE2":
				locator = getProps().getProperty("addSitePhone2Field");
				break;
			case "CITY":
				locator = getProps().getProperty("addSiteCityField");
				break;
			case "STATE/PROVINCE":
				locator = getProps().getProperty("addSiteProvinceField");
				break;
			case "POSTAL CODE":
				locator = getProps().getProperty("addSitePostalCodeField");
				break;
			case "COUNTRY":
				locator = getProps().getProperty("addSiteCountryDropDown");
				break;
			case "COMMISSION DATE":
				locator = getProps().getProperty("addSiteCommissionDate");
				break;
			case "CONTROL DATE":
				locator = getProps().getProperty("addSiteControlDate");
				break;
			case "AREA":
				locator = getProps().getProperty("addSiteAreaField");
				break;
			case "HAS CONTROL":
				locator = getProps().getProperty("addSiteControlCheckBox");
				break;
			case "HAS SOLAR PANEL":
				locator = getProps().getProperty("addSiteSolarPanelCheckBox");
				break;
			case "ACTIVE":
				locator = getProps().getProperty("addSiteActiveCheckBox");
				break;
			case "DECOMMISSIONED":
				locator = getProps().getProperty(
						"addSiteDECOMMISSIONEDCheckBox");
				break;
			case "DECOMMISSIONED DATE":
				locator = getProps().getProperty("addSiteDECOMMISSIONEDDate");
				break;
			case "PATHS":
				locator = getProps().getProperty("addSitePathsField");
				break;
			case "ADD PATH BUTTON":
				locator = getProps().getProperty("addSiteAddPathsButton");
				break;
			case "SAVE":
				locator = getProps().getProperty("addSiteSaveButton");
				break;
			case "CANCEL":
				locator = getProps().getProperty("addSiteCancelButton");
				break;
			case "SUPPLIED ADDRESS1":
				locator = getProps().getProperty(
						"addressVerificationSuppliedAddress1");
				break;
			case "SUPPLIED CITY":
				locator = getProps().getProperty(
						"addressVerificationSuppliedCity");
				break;
			case "SUPPLIED STATE/PROVINCE":
				locator = getProps().getProperty(
						"addressVerificationSuppliedStateProvince");
				break;
			case "SUPPLIED POSTAL CODE":
				locator = getProps().getProperty(
						"addressVerificationSuppliedPostalCode");
				break;
			case "SUPPLIED COUNTRY":
				locator = getProps().getProperty(
						"addressVerificationSuppliedCountry");
				break;
			case "LATITUDE":
				locator = getProps().getProperty("addressVerificationLatitude");
				break;
			case "LONGITUDE":
				locator = getProps()
						.getProperty("addressVerificationLongitude");
				break;
			case "TIMEZONE":
				locator = getProps().getProperty(
						"addressVerificationTimeZoneDropDown");
				break;
			case "OVERRIDE CHECKBOX":
				locator = getProps().getProperty(
						"addressVerificationOverrideCheckbox");
				break;
			case "GET CORDINATES":
				locator = getProps().getProperty(
						"addressVerificationGetCordinatesbutton");
				break;
			case "ACCEPT SUPPLIED DATA":
				locator = getProps().getProperty(
						"addressVerificationAcceptSuppliedDataButton");
				break;
			case "ACCEPT VERIFIED DATA":
				locator = getProps().getProperty(
						"addressVerificationAcceptVerifiedDataButton");
				break;
			case "SITES":
				locator = getProps().getProperty(
						"siteTab");
				break;
			case "USERS":
				locator = getProps().getProperty(
						"userTab");
				break;
			case "TENANTS":
				locator = getProps().getProperty(
						"tenantTab");
				break;
			case "COMMISSIONING":
				locator = getProps().getProperty(
						"commissioningTab");
				break;
			case "ENDPOINTS":
				locator = getProps().getProperty(
						"endpointTab");
				break;
			case "FIRMWARE":
				locator = getProps().getProperty(
						"firmwareTab");
				break;
			case "BULK SITE CREATION":
				locator = getProps().getProperty(
						"bulkSiteImportButton");
				break;
			case "ADD SITE":
				locator = getProps().getProperty(
						"addSiteButton");
				break;
			case "EXPORT ICON":
				locator = getProps().getProperty(
						"exportIcon");
				break;
			case "DATAFIX":
				locator = getProps().getProperty(
						"datafixTab");
				break;
			case "AUDIT":
				locator = getProps().getProperty(
						"auditTab");
				break;	
				
			// case "IMPORT NEW SITES":
			// locator = getProps().getProperty("importSitesPopupLabel");
			// break;
			// case "FILE NAME":
			// locator = getProps().getProperty("fileNameLabel");
			// break;
			// case "NO FILE CHOOSEN":
			// locator = getProps().getProperty("NoFileChoosenWarning");
			// break;
			// case "SAMPLE FILE LINK":
			// locator = getProps().getProperty("sampleFileLink");
			// break;
			// case "CHOOSE FILE":
			// locator = getProps().getProperty("chooseFileWindow");
			// break;
			// case "IMPORT":
			// locator = getProps().getProperty("bulkImportPopupImportButton");
			// break;
			// case "CLOSE":
			// locator = getProps().getProperty("bulkImportPopupCloseButton");
			// break;
			// case "COPY RESPONSE":
			// locator =
			// getProps().getProperty("bulkImportPopupCopyResponseButton");
			// break;
			case "MESSAGE BOX":
				locator = getProps().getProperty(
						"addressVerificationMessageBox");
				break;
			case "EDIT SITE PATH0":
				locator = getProps().getProperty(
						"editSitePagePathsAccordianPathTextField");
				break;	
			case "ADD PATH":
				locator = getProps().getProperty(
						"addSiteAddPathsButton");
				break;	
			case "REMOVE PATH0":
				locator = getProps().getProperty(
						"editSiteRemovePath0");
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

			return BaseUtil.verifyElementDisplayed(locator);
		} catch (Exception e) {
			logger.error("Failed to displayed the element :" + elementName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public void verifyDropdownStringValues(String dropdownType,
			List<String> valueList) {
		String locator = null;
		List<String> actualValueList = null;
		List<String> comparableList = null;
		try {
			switch (dropdownType.toUpperCase()) {
			case "COUNTRY":
				locator = getProps().getProperty("addSiteCountryDropDown");
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
				case "SOLAR PANEL":
					locator = getProps().getProperty(
							"addSiteSolarPanelCheckBox");
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

	public boolean verifyValueForAddressVerification(String fieldName) {
		WebElement fieldElement;
		String fieldValue = null;
		boolean result = false;
		try {
			switch (fieldName.toUpperCase()) {
			case "LATITUDE":
				fieldElement = TestBase.getWebDriver().findElement(
						By.id("idVerifiedAddSiteLatitide"));
				fieldValue = fieldElement.getText();
				result = true;
				Assert.assertTrue(" The value returned for Latitude is ["
						+ fieldValue + "]", result);
				return result;
			case "LONGITUDE":
				fieldElement = TestBase.getWebDriver().findElement(
						By.id("idVerifiedAddSiteLongitude"));
				fieldValue = fieldElement.getText();
				result = true;
				Assert.assertTrue(" The value returned for Longitude is ["
						+ fieldValue + "]", result);
				return result;
			case "TIME ZONE":
				fieldElement = TestBase.getWebDriver().findElement(
						By.id("idAddSiteTimeZone"));
				fieldValue = fieldElement.getText();
				result = true;
				Assert.assertTrue(" The value returned for Time zone is ["
						+ fieldValue + "]", result);
				return result;
			default:
				logger.error("Switch Case["
						+ fieldName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return result;
			}
		} catch (Exception e) {
			logger.error("Failed to verify value for :"
					+ fieldName
					+ ""
					+ " field on Address Verification while adding a site, detail message: "
					+ e.getMessage());
			return result;
		}
	}

	public boolean verifyAddressMessage(String scenario, String addressMessage) {
		try {
			WebElement messageField;
			String checkedText = null;
			switch (scenario.toUpperCase()) {
			case "VALID":
				messageField = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("addressVerificationMessageBox"));
				checkedText = messageField.getText();
				if (addressMessage.equals(checkedText)) {
					Assert.assertTrue("Message is verified", BaseUtil
							.verifyElementDisplayed(getProps().getProperty(
									"addressVerificationMessageBox")));
				}
				return true;
			case "INVALID":
				messageField = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("addressVerificationMessageBox"));
				checkedText = messageField.getText();
				if (addressMessage.equals(checkedText)) {
					Assert.assertTrue("Message is verified", BaseUtil
							.verifyElementDisplayed(getProps().getProperty(
									"addressVerificationMessageBox")));
				}
				return true;
			default:
				logger.error("Switch Case["
						+ scenario
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid message : " + addressMessage
						+ " is displayed");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to verify the message " + addressMessage
					+ " for " + scenario + " case. See detail message : "
					+ e.getMessage());
			Assert.fail(e.getMessage());
			return false;

		}

	}

	public void verifySearchFunctionality(String arg) {
		String[] arr = arg.split(":");
		String keyword = arr[1];
		List<WebElement> listSiteNames = null;
		String totalItemsText = null;
		int initialTotalItemsCount = 0;
		int laterTotalItemsCount = 0;
		WebElement searchBox = null;
		try {
			switch (keyword.toUpperCase()) {
			case "INIT3CHAR":
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("siteTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				BaseUtil.enterText(getProps().getProperty("siteSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("siteSearchButton"));
				BaseUtil.waitForSpinner();
				try {
					if (BaseUtil.getElementText(
							getProps().getProperty("siteGridZeroRecord"))
							.equalsIgnoreCase(
									BaseUtil.getElementText(getProps()
											.getProperty(
													"siteGridZeroRecordText")))) {
						Assert.fail("No record available. Test cannot be continue.");
					}
				} catch (Exception e) {
					logger.info("Sufficient records are available");
				}

				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("siteTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				if (laterTotalItemsCount > initialTotalItemsCount) {
					Assert.fail("Site search results after entering 3 characters "
							+ arr[0] + " is not as expected");
				}
				break;
			case "RANDOM":
				String randomSearchString = RandomStringUtils.randomAscii(10);
				BaseUtil.enterText(getProps().getProperty("siteSearchField"),
						randomSearchString);

				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("siteSearchButton"));
				BaseUtil.waitForSpinner();
				try {
					Assert.assertTrue(
							"Multiple results found for the string : "
									+ randomSearchString,
							BaseUtil.getElementText(
									getProps()
											.getProperty("siteGridZeroRecord"))
									.equals(getProps().getProperty(
											"siteGridZeroRecordText")));
				} catch (Exception ex) {
					listSiteNames = BaseUtil
							.getMultipleElementsAfterLoaded(getProps()
									.getProperty("siteGridNameColumn"));
					logger.info("In case RANDOM the list size is : "
							+ listSiteNames.size());
					for (WebElement siteNameElement : listSiteNames) {
						if (!siteNameElement.getText().contains(
								randomSearchString)) {
							Assert.fail("Site search results are not as expected for string : "
									+ randomSearchString);
						}
					}
				}
				break;
			case "LONGTEXT":
				String longTextSearchString = RandomStringUtils
						.randomAlphanumeric(50);
				BaseUtil.enterText(getProps().getProperty("siteSearchField"),
						longTextSearchString);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("siteSearchButton"));
				BaseUtil.waitForSpinner();
				try {
					Assert.assertTrue(
							"No results found for the string : "
									+ longTextSearchString,
							BaseUtil.getElementText(
									getProps()
											.getProperty("siteGridZeroRecord"))
									.equals(getProps().getProperty(
											"siteGridZeroRecordText")));
				} catch (Exception ex) {
					listSiteNames = BaseUtil
							.getMultipleElementsAfterLoaded(getProps()
									.getProperty("siteGridNameColumn"));
					logger.info("In case LONGTEXT the list size is : "
							+ listSiteNames.size());
					for (WebElement tenantNameElement : listSiteNames) {
						if (!tenantNameElement.getText().contains(
								longTextSearchString)) {
							Assert.fail("Tenant search results are not as expected for string : "
									+ longTextSearchString);
						}
					}
				}
				break;
			case "BACKSPACE":
				BaseUtil.enterText(getProps().getProperty("siteSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner(1000);
				BaseUtil.click(getProps().getProperty("siteSearchButton"));
				BaseUtil.waitForSpinner(2000);
				listSiteNames = BaseUtil
						.getMultipleElementsAfterLoaded(getProps().getProperty(
								"siteGridNameColumn"));
				logger.info("Search size before enter backspace: "
						+ listSiteNames.size());
				searchBox = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("siteSearchField"));
				searchBox.sendKeys(Keys.CLEAR);
				BaseUtil.waitForSpinner(2000);
				List<WebElement> newSearchListSiteNames = BaseUtil
						.getMultipleElementsAfterLoaded(getProps().getProperty(
								"siteGridNameColumn"));
				logger.info("Search size after enter backspace: "
						+ newSearchListSiteNames.size());
				if (newSearchListSiteNames.size() < listSiteNames.size()) {
					Assert.fail("Search results are not as expected when backspace is clicked on site search");
				}
				break;
			case "DELETE":
				BaseUtil.enterText(getProps().getProperty("siteSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner(1000);
				BaseUtil.click(getProps().getProperty("siteSearchButton"));
				BaseUtil.waitForSpinner(2000);
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("siteTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				searchBox = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("siteSearchButton"));
				searchBox.sendKeys(Keys.CONTROL + "a");
				searchBox.sendKeys(Keys.DELETE);
				BaseUtil.waitForSpinner(2000);
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("siteTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				if (laterTotalItemsCount < initialTotalItemsCount) {
					Assert.fail("Site search results after deleting the keyword "
							+ arr[0] + " is not as expected");
				}
				break;
			case "PAGINATION":
				BaseUtil.enterText(getProps().getProperty("siteSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				boolean nextFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("sitePaginationNext"));
				boolean lastFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("sitePaginationLast"));
				boolean prevFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("sitePaginationPrev"));
				boolean firstFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("sitePaginationFirst"));
				if (!(nextFlg && lastFlg && prevFlg && firstFlg)) {
					Assert.fail("All the pagination buttons are not visible");
				}
				break;

			}
		} catch (Exception e) {
			logger.error("Some error occured while verifying the search results : "
					+ e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	public boolean isBlank(String fieldName) {
		WebElement blankText;
		boolean isBlank = false;
		switch (fieldName.toUpperCase()) {
		case "SITE NAME":
			blankText = BaseUtil.getElementAfterLoaded(getProps().getProperty(
					"addSiteNameField"));
			blankText.clear();
			isBlank = getProps()
					.getProperty("addSiteNameBlankErrorMessageText").equals(
							BaseUtil.getElementText(getProps().getProperty(
									"addSiteNameBlankErrorMessage")));
			break;
		case "SITE DESCRIPTION":
			blankText = BaseUtil.getElementAfterLoaded(getProps().getProperty(
					"addSiteDescriptionField"));
			blankText.clear();
			isBlank = getProps().getProperty(
					"addSiteDescriptionBlankErrorMessageText").equals(
					BaseUtil.getElementText(getProps().getProperty(
							"addSiteDescriptionErrorMessage")));
			break;
		case "CITY":
			blankText = BaseUtil.getElementAfterLoaded(getProps().getProperty(
					"addSiteCityField"));
			blankText.clear();
			isBlank = getProps()
					.getProperty("addSiteCityBlankErrorMessageText").equals(
							BaseUtil.getElementText(getProps().getProperty(
									"addSiteCityErrorMessage")));
			break;
		case "POSTAL CODE":
			blankText = BaseUtil.getElementAfterLoaded(getProps().getProperty(
					"addSitePostalCodeField"));
			blankText.clear();
			isBlank = getProps().getProperty(
					"addSitePostalCodeBlankErrorMessageText").equals(
					BaseUtil.getElementText(getProps().getProperty(
							"addSitePostalCodeErrorMessage")));
			break;
		case "AREA":
			blankText = BaseUtil.getElementAfterLoaded(getProps().getProperty(
					"addSiteAreaField"));
			blankText.clear();
			isBlank = getProps()
					.getProperty("addSiteAreaBlankErrorMessageText").equals(
							BaseUtil.getElementText(getProps().getProperty(
									"addSiteAreaBlankErrorMessage")));
			break;
		case "PATHS":
			blankText = BaseUtil.getElementAfterLoaded(getProps().getProperty(
					"addSitePathsField"));
			blankText.clear();
			isBlank = getProps()
					.getProperty("addSitePathBlankErrorMessageText").equals(
							BaseUtil.getElementText(getProps().getProperty(
									"addSitePathBlankErrorMessage")));
			break;
		}

		return isBlank;
	}

	public void enterText_SitePage(String sElementName, String sText) {
		String sCaseOptions = "SITE_SEARCH, ";
		try {

			switch (sElementName.toUpperCase()) {
			case "SITE_SEARCH":
				BaseUtil.enterText(getProps().getProperty("siteSearchField"),
						sText);
				BaseUtil.waitForSpinner();
				BaseUtil.explicitWait(1000);
				break;
			case "ENDPOINT_SEARCH":
				BaseUtil.enterText(getProps()
						.getProperty("endpointSearchField"), sText);
				BaseUtil.waitForSpinner();
				BaseUtil.explicitWait(1000);
				break;

			// case "CHANNEL_SEARCH":
			// enterText(getProps().getProperty("siteSearchField"), sText);
			// waitForSpinner();
			// break;
			// case "CHANNEL_NAME":
			// enterText(getProps().getProperty("addVirtualChanneName"), sText);
			// break;
			// case "SEARCH_CHANNEL_NAME":
			// enterText(getProps().getProperty("searchVirtualChannelTextField"),
			// sText);
			// explicitWait(2000);
			// if (instantElementCheck("Display",
			// getProps().getProperty("zeroRecordsFoundChannelGrid"))) {
			// break;
			// }
			// break;
			// case "UPDATE_CHANNEL_NAME":
			// enterText(getProps().getProperty("editVirtualChannelDisplayNameTextField"),
			// sText);
			// break;
			// case "MEASURE_TYPE":
			// selectDropDownByValue(getProps().getProperty("addVirtualChannelMeasureTypeDropDown"),
			// sText);
			// break;
			// case "METADATA_CATEGORY":
			// selectDropDownByValue(getProps().getProperty("addVirtualChannelMetadataCategoryDropDown"),
			// sText);
			// break;
			// case "CHANNEL_FILTER":
			// selectDropDownByValue(getProps().getProperty("addVirtualChannelChannelFilterDropDown"),
			// sText);
			// break;
			// case "FILTER:CHANNEL_FILTER":
			// selectDropDownByValue(
			// getProps().getProperty("addVirtualChannelChannelFilterDropDownAvailableChannelsSection"),
			// sText);
			// break;
			// case "FILTER:MEASURE_TYPE":
			// selectDropDownByValue(
			// getProps().getProperty("addVirtualChannelMeasureTypeDropDownAvailableChannelsSection"),
			// sText);
			// break;
			// case "TENANT_DROPDOWN":
			// click(getProps().getProperty("editSitePageTenantDropDown"));
			// selectComboBoxDropDownByValue(getProps().getProperty("gridpointValueTenantDropDown"),
			// sText);
			// break;
			// case "SEARCH_CHANNEL_NAME_AVC":
			// enterText(getProps().getProperty("addVirtualChannelChannelSearchField"),
			// sText);
			// break;
			// case "OPERATOR":
			// selectDropDownByValue(getProps().getProperty("addVirtualChannelOperatorDropDown"),
			// sText);
			// break;

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
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public void selectSiteFromGrid_SitePage(String sSiteName,
			String resourceName) {
		int iTotalNumOfPage = 0;
		boolean bResult = false;
		logger.debug("Selecting the site :" + sSiteName);
		try {
			iTotalNumOfPage = Integer.parseInt(BaseUtil
					.getElementText(
							getProps().getProperty("sitePaginationTotalPages"))
					.trim().replace("/", "").trim());
			if (iTotalNumOfPage == 1)
				iTotalNumOfPage++;
			for (int iNumPage = 1; iNumPage <= iTotalNumOfPage - 1; iNumPage++) {
				List<WebElement> lstSiteNames = BaseUtil
						.getMultipleElementsAfterLoaded(getProps().getProperty(
								"siteGridNameColumn"));
				for (WebElement webElement : lstSiteNames) {
					if (webElement.getText().trim().equalsIgnoreCase(sSiteName)) {
						webElement.click();
						BaseUtil.waitForSpinner();
						bResult = true;
						break;
					}
				}
				if (!bResult) {
					Assert.fail("Given test site is not available");
				}
			}
			logger.debug("site :" + sSiteName + " is selected.");
		} catch (Exception e) {
			logger.error("Failed to select site: " + sSiteName
					+ " from site grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the site : " + sSiteName
					+ ", see detail error message: \n"
					+ e.getStackTrace().toString());
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public void selectEndpointFromGrid_EndpointPage(String sEndpointName) {
		boolean bResult = false;
		logger.debug("Selecting the endpoint :" + sEndpointName);
		try {
			List<WebElement> lstEndpointNames = BaseUtil
					.getMultipleElementsAfterLoaded(getProps().getProperty(
							"endpointGridSerialColumn"));
			for (WebElement webElement : lstEndpointNames) {
				if (webElement.getText().trim().equalsIgnoreCase(sEndpointName)) {
					webElement.click();
					bResult = true;
					break;
				}
			}
			if (!bResult) {
				Assert.fail("Given test endpoint is not available");
			}
			logger.debug("endpoint :" + sEndpointName + " is selected.");

		} catch (Exception e) {
			logger.error("Failed to select endpoint: " + sEndpointName
					+ " from endpoint grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the endpoint : " + sEndpointName
					+ ", see detail error message: \n"
					+ e.getStackTrace().toString());
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public void checkAlert() {
		try {

			if (BaseUtil.isAlertPresent()) {
				BaseUtil.moveToAlert().accept();
			}
			// WebDriverWait wait = new WebDriverWait(TestBase.getWebDriver(),
			// 3);
			// wait.until(ExpectedConditions.alertIsPresent());
			// Alert alert = TestBase.getWebDriver().switchTo().alert();
			// explicitWait(3000);
			// alert.accept();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean enterDateAsCompareWithOtherDate(String date1, String date2) {
		boolean dateResult = false;
		try {
			Calendar calendar;
			Date beforeDate;
			DateFormat outputFormatter;
			String output = null;
			String commissionDateValue = null;
			String controlDateValue = null;
			Date commissionDate;
			Date controlDate;
			WebElement commissionDateElement;
			WebElement controlDateElement;

			switch (date1.toUpperCase()) {
			case "CONTROL DATE":
				commissionDateElement = TestBase.getWebDriver()
						.findElement(
								By.id(getProps().getProperty(
										"editSitePageCommissionDateField")
										.split("===")[1].trim()));
				commissionDateValue = commissionDateElement
						.getAttribute("value");
				System.out.println("commissionDateValue ="
						+ commissionDateValue);
				commissionDate = new SimpleDateFormat("MM/dd/yyyy")
						.parse(commissionDateValue);
				calendar = Calendar.getInstance();
				calendar.setTime(commissionDate);
				calendar.add(Calendar.DAY_OF_YEAR, -7);
				// now get 7 days before date from commissioning date"
				beforeDate = calendar.getTime();
				outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
				output = outputFormatter.format(beforeDate);
				BaseUtil.enterText(
						getProps().getProperty("editSitePageControlDateField"),
						output);
				dateResult = true;
				return dateResult;
			case "DECOMMISSION DATE":
				switch (date2.toUpperCase()) {
				case "CONTROL DATE":
					controlDateElement = TestBase.getWebDriver().findElement(
							By.id("idEditSiteControlDate"));
					controlDateValue = controlDateElement.getAttribute("value");
					controlDate = new SimpleDateFormat("MM/dd/yyyy")
							.parse(controlDateValue);
					calendar = Calendar.getInstance();
					calendar.setTime(controlDate);
					calendar.add(Calendar.DAY_OF_YEAR, -20);
					// now get 7 days before date from commissioning date"
					beforeDate = calendar.getTime();
					outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
					output = outputFormatter.format(beforeDate);
					System.out.println("controlDateValue =" + controlDateValue);
					System.out.println("Decommission date value =" + output);
					BaseUtil.enterText(
							getProps().getProperty(
									"editSitePageDeCommissionDateField"),
							output);
					dateResult = true;
					return dateResult;
				case "COMMISSION DATE":
					commissionDateElement = TestBase.getWebDriver()
							.findElement(By.id("idEditSiteCommissionDate"));
					commissionDateValue = commissionDateElement
							.getAttribute("value");
					commissionDate = new SimpleDateFormat("MM/dd/yyyy")
							.parse(commissionDateValue);
					calendar = Calendar.getInstance();
					calendar.setTime(commissionDate);
					calendar.add(Calendar.DAY_OF_YEAR, -20);
					// now get 7 days before date from commissioning date"
					beforeDate = calendar.getTime();
					outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
					output = outputFormatter.format(beforeDate);
					controlDateElement = TestBase.getWebDriver().findElement(
							By.id("idEditSiteControlDate"));
					controlDateElement.sendKeys(Keys.CONTROL + "a");
					controlDateElement.sendKeys(Keys.DELETE);
					System.out.println("commissionDateValue ="
							+ commissionDateValue);
					System.out.println("Decommission date value =" + output);
					BaseUtil.enterText(
							getProps().getProperty(
									"editSitePageDeCommissionDateField"),
							output);
					dateResult = true;
					return dateResult;
				}
				calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_YEAR, 7);
				// now get 7 days later date from current date"
				beforeDate = calendar.getTime();
				outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
				output = outputFormatter.format(beforeDate);
				BaseUtil.enterText(
						getProps().getProperty(
								"editSitePageDeCommissionDateField"), output);
				break;
			default:
				logger.error("Switch Case["
						+ date1
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return dateResult;
	}

	public boolean verifyErrorMessageDecommissionDate(String errorMessage,
			String dateType) {
		try {
			WebElement messageField = null;
			String message = null;
			switch (dateType.toUpperCase()) {
			case "DECOMMISSION DATE":
				messageField = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("editSiteDecommissionDateErrorMessage"));
				message = messageField.getText();
				if (message.equals(errorMessage))
					Assert.assertTrue(" The expected message is given as ["
							+ message + "] ", BaseUtil
							.verifyElementDisplayed(getProps().getProperty(
									"editSiteDecommissionDateErrorMessage")));
				return true;
			case "CONTROL DATE":
				messageField = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("editSiteControlDateErrorMessage"));
				message = messageField.getText();
				if (message.equals(errorMessage))
					Assert.assertTrue(" The expected message is given as ["
							+ message + "] ", BaseUtil
							.verifyElementDisplayed(getProps().getProperty(
									"editSiteControlDateErrorMessage")));
				return true;
			case "LATITUDE":
				messageField = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("editSiteLatitudeErrorMsg"));
				message = messageField.getText();

				Assert.assertTrue(
						errorMessage + " is not displayed on UI",
						BaseUtil.verifyElementDisplayed(getProps().getProperty(
								"editSiteLatitudeErrorMsg")));
				return message.equals(errorMessage);
			case "LONGITUDE":
				messageField = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("editSiteLongitudeErrorMsg"));
				message = messageField.getText();

				Assert.assertTrue(
						errorMessage + " is not displayed on UI",
						BaseUtil.verifyElementDisplayed(getProps().getProperty(
								"editSiteLongitudeErrorMsg")));
				return message.equals(errorMessage);
			default:
				logger.error("Switch Case["
						+ dateType
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (NullPointerException e) {
			logger.error("Accordion :"
					+ dateType
					+ " does not exist in the audit grid, see detail message : "
					+ e.getMessage());
			return false;
		} catch (Exception e) {
			logger.error("Failed to displayed the message :" + errorMessage
					+ " in the " + dateType
					+ " accordion. See detail message : " + e.getMessage());
			return false;
		}
	}

	public boolean verifyElementEditable(String fieldName) {
		String locator = null;
		try {
			switch (fieldName.toUpperCase()) {
			case "LATITUDE":
				locator = getProps().getProperty(
						"editSiteAddressVerificationSuppliedLatitudeField");
				break;
			case "LONGITUDE":
				locator = getProps().getProperty(
						"editSiteAddressVerificationSuppliedLongitudeField");
				break;	
			case "SITE NAME":
				locator = getProps().getProperty("editSiteSiteNameField");
				break;
			case "SITE DESCRIPTION":
				locator = getProps()
						.getProperty("editSiteSiteDescriptionField");
				break;
			case "ADDRESS1":
				locator = getProps().getProperty("editSiteAddress1Field");
				break;
			case "CITY":
				locator = getProps().getProperty("editSiteCityField");
				break;
			case "POSTAL CODE":
				locator = getProps().getProperty("editSitePostalCodeField");
				break;
			case "PATHS TEXT FIELD":
				locator = getProps().getProperty(
						"editSitePagePathsAccordianPathTextField");
				break;
			// case "DISPLAY NAME":
			// return
			// verifyElementEnabled(getProps().getProperty("channelAccordionEditDisplayNameField"));
			// case "MEASURE TYPE":
			// return
			// verifyElementEnabled(getProps().getProperty("channelAccordionEditMeasureTypeField"));
			// case "METADATA CATEGORY":
			// return
			// verifyElementEnabled(getProps().getProperty("channelAccordionEditMetadataCategoryField"));
			// case "TOTAL TYPE":
			// return
			// verifyElementEnabled(getProps().getProperty("channelAccordionEditTotalTypeField"));
			// case "TENANT DROPDOWN":
			// return
			// !(verifyElementIsReadOnly(getProps().getProperty("editEndpointPageTenantDropDown")));
			default:
				logger.error("Switch Case["
						+ fieldName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid column name : " + fieldName + " selected");
			}

			return BaseUtil.verifyElementIsReadOnly(locator);

		} catch (Exception e) {
			logger.error("Failed to verify editablity of field " + fieldName
					+ " see detail message : " + e.getMessage());
			Assert.fail(e.getMessage());
		}
		return false;
	}

	public void checkPopupExistence_SitePage(String sPopupName, String sExpMsg) {
		String sCaseOptions = "EDIT SITE,";
		try {
			switch (sPopupName.toUpperCase()) {
			case "DELETE_SITE_CONFIRMATION":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSiteDeleteConfirmationPopup"));
				if (!(BaseUtil.getElementText(getProps().getProperty(
						"editSiteDeleteConfirmationPopupMessage"))
						.equalsIgnoreCase(sExpMsg)))
					Assert.fail("Failed to display the confirmation box with expected message: +"
							+ sExpMsg
							+ ", but actual message was: "
							+ BaseUtil.getElementText(getProps().getProperty(
									"editSiteDeleteConfirmationPopupMessage")));
				break;
			// case "EDIT SITE":
			// verifyElementDisplayed(getProps().getProperty("editSitePagePopup"));
			// break;
			// case "EDIT_SITE_CONFIRMATION":
			// verifyElementDisplayed(getProps().getProperty("editSiteCancelConfirmationPopup"));
			// if
			// (!(getElementText(getProps().getProperty("editSiteCancelConfirmationPopupMessage"))
			// .equalsIgnoreCase(sExpMsg)))
			// Assert.fail("Failed to display the confirmation box with expected message: +"
			// + sExpMsg
			// + ", but actual message was: "
			// +
			// getElementText(getProps().getProperty("editSiteCancelConfirmationPopupMessage")));
			// break;

			default:
				logger.error("Switch Case["
						+ sPopupName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Failed to check existence of popup ["
						+ sPopupName
						+ "] because you passed wrong parameter["
						+ sPopupName
						+ "] either in feature file or test class. \n please choose valid parameter from the following list: \n ["
						+ sCaseOptions + "]");
			}
		} catch (Exception e) {
			logger.error("Failed to check existence of popup:" + sPopupName
					+ "in site page. , see detail error message \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to check existence of popup: [" + sPopupName
					+ "] in site page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
	}

	public boolean verifyTenantOnEditSite(String sSiteName, String tenantname) {
		try {
			WebElement tenantField = null;
			String tenant = null;
			switch (sSiteName.toUpperCase()) {
			case "PROVISIONING":
				tenantField = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("editSitePagePopupDetailTenantField"));
				tenant = tenantField.getText();
				if (tenant.equals(tenantname))
					Assert.assertTrue(" The expected tenant is given as ["
							+ tenant + "] ", BaseUtil
							.verifyElementDisplayed(getProps().getProperty(
									"editSitePagePopupDetailTenantField")));
				return true;
			default:
				logger.error("Switch Case["
						+ sSiteName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (NullPointerException e) {
			logger.error("Accordion :"
					+ sSiteName
					+ " does not exist in the audit grid, see detail message : "
					+ e.getMessage());
			return false;
		} catch (Exception e) {
			logger.error("Failed to displayed the tenant :" + tenantname
					+ " for the site " + sSiteName + ". See detail message : "
					+ e.getMessage());
			return false;
		}
	}

	public void verifyAccordionToggle_SitePage(String sAccordionName) {

		String sCaseOptions = "Details,";
		try {
			switch (sAccordionName.toUpperCase()) {
			case "DETAILS":
				if (BaseUtil.getElementAfterLoaded(
						getProps().getProperty(
								"editSitePagePopupDetailMapSection"))
						.isEnabled()) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"editSitePagePoupDetailsAccordion"));
					BaseUtil.explicitWait(2000);
					BaseUtil.clickAndWait(getProps().getProperty(
							"editSitePagePoupDetailsAccordion"));
				}
				try {
					if (BaseUtil.getElementAfterLoaded(
							getProps().getProperty(
									"editSitePagePopupDetailMapSection"))
							.isEnabled()) {
						Assert.fail("Failed to toggle the accordion: "
								+ sAccordionName);
					}
				} catch (Exception e) {
					logger.info("Accordion[" + sAccordionName + "] is toggled.");
					BaseUtil.clickAndWait(getProps().getProperty(
							"editSitePagePoupDetailsAccordion"));
				}
				break;
			case "AUDIT":
				// clickAndWait(getProps().getProperty("editSitePagePoupDetailsAccordion"));
				// explicitWait(1000);
				BaseUtil.clickAndWait(getProps().getProperty(
						"editSitePagePopupAuditAccordion"));
				BaseUtil.explicitWait(1000);
				if (!BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSitePagePopupAuditGrid"))) {
					Assert.fail("Failed to toggle the accordion: "
							+ sAccordionName);
				}
				break;
			case "PATHS":
				try {
					BaseUtil.clickAndWait(getProps().getProperty(
							"editSitePagePoupDetailsAccordion"));
					BaseUtil.explicitWait(1000);
					BaseUtil.clickAndWait(getProps().getProperty(
							"editSitePagePopupPathsAccordion"));
					BaseUtil.explicitWait(1000);
					Assert.assertTrue("Failed to toggle the accordion: "
							+ sAccordionName, BaseUtil
							.verifyElementDisplayed(getProps().getProperty(
									"editSitePagePathsAccordianPathField")));
				} catch (Exception e) {
					logger.info("Accordion[" + sAccordionName + "] is toggled.");
					BaseUtil.clickAndWait(getProps().getProperty(
							"editSitePagePoupDetailsAccordion"));
				}
				break;
			case "ENDPOINTS":
				BaseUtil.clickAndWait(getProps().getProperty(
						"editSitePagePoupDetailsAccordion"));
				BaseUtil.explicitWait(1000);
				BaseUtil.clickAndWait(getProps().getProperty(
						"editSitePagePopupEndpointsAccordion"));
				BaseUtil.explicitWait(1000);
				if (!BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSitePagePopupEnpointsGrid"))) {
					Assert.fail("Failed to toggle the accordion: "
							+ sAccordionName);
				}
				break;
			case "CHANNEL":
				BaseUtil.explicitWait(2000);
				BaseUtil.clickAndWait(getProps().getProperty(
						"editSitePagePoupDetailsAccordion"));
				BaseUtil.explicitWait(1000);
				BaseUtil.clickAndWait(getProps().getProperty(
						"editSitePagePopupChannelAccordion"));
				if (!BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSitePagePopupChannelGrid"))) {
					Assert.fail("Failed to toggle the accordion: "
							+ sAccordionName);
				}
				break;
			case "PV AUDIT":
				BaseUtil.clickAndWait(getProps().getProperty(
						"editSitePagePoupDetailsAccordion"));
				BaseUtil.explicitWait(1000);
				BaseUtil.clickAndWait(getProps().getProperty(
						"editSitePagePopupPVAuditAccordion"));
				BaseUtil.explicitWait(1000);
				if (!BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSitePagePopupPVAuditGrid"))) {
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

	public void verifySorting_EndpointAccordian(String sSortingType,
			String sColName) {
		List<String> listColumnValues = null;
		try {
			switch (sColName.toUpperCase()) {
			case "ENDPOINT ID":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupEndpointGridIDHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupEndpointGridIDHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupEndpointGridIDHeader"));
				break;
			case "CONNECTION STATUS":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupEndpointGridConnectionStatus"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupEndpointGridConnectionStatus"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupEndpointGridConnectionStatus"));
				break;
			case "TYPE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupEndpointGridConnectionType"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupEndpointGridConnectionType"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil.getMultipleElementTextAfterLoaded(
						getProps().getProperty(
								"editSitePagePopupEndpointGridConnectionType"))
						.subList(0, 0);
				break;
			case "SERIAL":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupEndpointGridSerialHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupEndpointGridSerialHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupEndpointGridSerialHeader"));
				break;
			case "REFERENCE ID":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupEndpointGridReferenceIDHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupEndpointGridReferenceIDHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupEndpointGridReferenceIDHeader"));
				break;
			case "LIVE FIRMWARE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupEndpointGridLiveFirmwareHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupEndpointGridLiveFirmwareHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupEndpointGridLiveFirmwareHeader"));
				break;
			case "LAST MESSAGE RECEIVED (UTC)":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps()
							.getProperty(
									"editSitePagePopupEndpointGridLastMessReceivedHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps()
							.getProperty(
									"editSitePagePopupEndpointGridLastMessReceivedHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupEndpointGridLastMessReceivedHeader"));
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
						+ ", Please pass valid column name e.g.[Name,Hostname, parent] from feature file or test class.");
			}
			Assert.assertTrue(sColName + " is not in " + sSortingType
					+ " order",
					BaseUtil.isSorted(sSortingType, listColumnValues));

		} catch (Exception e) {
			logger.error("Failed to sort the column :" + sColName
					+ " Sorting order:" + sSortingType + " , detail message: "
					+ e.getStackTrace().toString());
			Assert.fail(e.getStackTrace().toString());
		}
	}

	public void verifySorting_AuditAccordian(String sSortingType,
			String sColName) {
		List<String> listColumnValues = null;
		try {
			switch (sColName.toUpperCase()) {
			case "PROPERTY":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupAuditGridPropertyHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupAuditGridPropertyHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupAuditGridPropertyHeader"));
				break;
			case "ACTION":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupAuditGridActionHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupAuditGridActionHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil.getMultipleElementTextAfterLoaded(
						getProps().getProperty(
								"editSitePagePopupAuditGridActionHeader"))
						.subList(0, 0);
				break;
			case "ORIGINAL VALUE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupAuditGridOriginalValueHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupAuditGridOriginalValueHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupAuditGridOriginalValueHeader"));
				break;
			case "UPDATED VALUE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupAuditGridUpdatedValueHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupAuditGridUpdatedValueHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupAuditGridUpdatedValueHeader"));
				break;
			case "USER":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupAuditGridUserHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupAuditGridUserHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupAuditGridUserHeader"));
				break;
			case "DATE STAMP":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupAuditGridDateStampHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupAuditGridDateStampHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupAuditGridDateStampHeader"));
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
						+ ", Please pass valid column name e.g.[Name,Hostname, parent] from feature file or test class.");
			}

			Assert.assertTrue(sColName + " is not in " + sSortingType
					+ " order",
					BaseUtil.isSorted(sSortingType, listColumnValues));

		} catch (Exception e) {
			logger.error("Failed to sort the column :" + sColName
					+ " Sorting order:" + sSortingType + " , detail message: "
					+ e.getStackTrace().toString());
			Assert.fail(e.getStackTrace().toString());
		}
	}

	public void verifySorting_PVAuditAccordian(String sSortingType,
			String sColName) {
		List<String> listColumnValues = null;
		try {
			switch (sColName.toUpperCase()) {
			case "ID":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupPVAuditGridIDHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupPVAuditGridIDHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupPVAuditGridIDHeader"));
				break;
			case "SITENAME":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupPVAuditGridSiteNameHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupPVAuditGridSiteNameHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupPVAuditGridSiteNameHeader"));
				break;
			case "STARTDATE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupPVAuditGridStartDateHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupPVAuditGridStartDateHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupPVAuditGridStartDateHeader"));
				break;
			case "ENDDATE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupPVAuditGridEndDateHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupPVAuditGridEndDateHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupPVAuditGridEndDateHeader"));
				break;
			case "SUBMITTEDDATE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupPVAuditGridSubmittedDateHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupPVAuditGridSubmittedDateHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupPVAuditGridSubmittedDateHeader"));
				break;
			case "STATUS":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupPVAuditGridStatusHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupPVAuditGridStatusHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupPVAuditGridStatusHeader"));
				break;
			case "REQUEST":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupPVAuditGridRequestHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupPVAuditGridRequestHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupPVAuditGridRequestHeader"));
				break;
			case "RESPONSE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupPVAuditGridResponseHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupPVAuditGridResponseHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupPVAuditGridResponseHeader"));
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
						+ ", Please pass valid column name e.g.[Name,Hostname, parent] from feature file or test class.");
			}

			Assert.assertTrue(sColName + " is not in " + sSortingType
					+ " order",
					BaseUtil.isSorted(sSortingType, listColumnValues));

		} catch (Exception e) {
			logger.error("Failed to sort the column :" + sColName
					+ " Sorting order:" + sSortingType + " , detail message: "
					+ e.getStackTrace().toString());
			Assert.fail(e.getStackTrace().toString());
		}
	}

	public void verifySorting_ChannelAccordian(String sSortingType,
			String sColName) {
		List<String> listColumnValues = null;
		try {
			switch (sColName.toUpperCase()) {
			case "CHANNEL ID":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupChannelGridIDHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupChannelGridIDHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil.getMultipleElementTextAfterLoaded(
						getProps().getProperty(
								"editSitePagePopupChannelGridIDHeader"))
						.subList(0, 0);
				break;
			case "CHANNEL NAME":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupChannelGridNameHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupChannelGridNameHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupChannelGridNameHeader"));
				break;
			case "DISPLAY NAME":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupChannelGridDisplayNameHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupChannelGridDisplayNameHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupChannelGridDisplayNameHeader"));
				break;
			case "DEVICE NAME":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupChannelGridDeviceNameHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupChannelGridDeviceNameHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupChannelGridDeviceNameHeader"));
				break;

			case "MEASURE TYPE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupChannelGridMeasureTypeHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupChannelGridMeasureTypeHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupChannelGridMeasureTypeHeader"));
				break;

			case "UNIT":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupChannelGridUnitHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupChannelGridUnitHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupChannelGridUnitHeader"));
				break;

			case "METADATA CATEGORY":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps()
							.getProperty(
									"editSitePagePopupChannelGridMetadataCategoryHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps()
							.getProperty(
									"editSitePagePopupChannelGridMetadataCategoryHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupChannelGridMetadataCategoryHeader"));
				break;

			case "TOTAL TYPE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupChannelGridTotalTypeHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editSitePagePopupChannelGridTotalTypeHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editSitePagePopupChannelGridTotalTypeHeader"));
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
						+ ", Please pass valid column name e.g.[Channel Id, Channel Name] from feature file or test class.");
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

	public boolean selectDetailsFromAccordion(String accordianDetails,
			String accordianName) {
		int iTotalNumOfPage = 0;
		boolean bResult = false;
		try {
			switch (accordianName.toUpperCase()) {
			case "PV AUDIT":
				iTotalNumOfPage = Integer.parseInt(BaseUtil
						.getElementText(
								getProps().getProperty(
										"editSitePagePVAuditGridTotalPages"))
						.trim().replace("/", "").trim());
				if (iTotalNumOfPage == 1)
					iTotalNumOfPage++;
				for (int iNumPage = 1; iNumPage <= iTotalNumOfPage - 1;) {
					List<WebElement> lstTenantNames = BaseUtil
							.getMultipleElementsAfterLoaded(getProps()
									.getProperty(
											"editSitePagePVAuditGridNameColumn"));

					for (WebElement webElement : lstTenantNames) {
						String accordionDetailsName = webElement.getText();
						System.out.println("accordionDetailsName ="
								+ accordionDetailsName);
						System.out.println("accordianDetails ="
								+ accordianDetails);
						if (accordionDetailsName.equals(accordianDetails)) {
							webElement.click();
							BaseUtil.waitForSpinner();
							BaseUtil.verifyElementDisplayed(getProps()
									.getProperty("editSitePagePVAuditAccordion"));
							bResult = true;
							break;
						}

					}
					break;
				}
				return bResult;

			}

		} catch (Exception e) {
			logger.error("Failed to select details from the :"
					+ accordianDetails
					+ " accordion."
					+ " from Site Section of tenant detail grid , see detail error message: "
					+ e.getMessage());
			return false;
		}
		return bResult;
	}

	public boolean verifyColumns_ChooseColumnPopup(String chooseColumnHeader) {
		String labelLocator = null, flag = null;

		try {
			String realElementName;
			if (chooseColumnHeader.split(":").length == 2) {
				realElementName = chooseColumnHeader.split(":")[1].trim();
			} else {
				realElementName = chooseColumnHeader;
			}

			switch (chooseColumnHeader.toUpperCase()) {
			case "SITE ID":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupSiteIdColumn");
				break;
			case "SITE NAME":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupSiteNameColumn");
				break;
			case "ADDRESS":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupAddressColumn");
				break;
			case "POSTAL CODE":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupPostalCodeColumn");
				break;

			case "ACTIVE":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupActiveColumn");
				break;
			case "TIME ZONE":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupTimeZoneColumn");
				break;

			case "TENANT":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupTenantColumn");
				break;

			case "TENANT ID":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupTenantIDColumn");
				break;

			case "STATE/PROVINCE":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupStateColumn");
				break;

			case "COUNTRY":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupCountryColumn");
				break;

			case "CITY":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupCityColumn");
				break;

			case "ADDRESS 2":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupAddress2Column");
				break;
			case "PHONE 1":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupPhone1Column");
				break;

			case "PHONE 2":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupPhone2Column");
				break;

			case "HAS SOLAR PANELS":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupHasSolarPanelsColumn");
				break;

			case "COMMISSION DATE":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupCommissionDateColumn");
				break;
			case "CONTROL DATE":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupControlDateColumn");
				break;

			case "DECOMMISSIONED DATE":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupDecommissionDateColumn");
				break;

			case "AREA":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupAreaColumn");
				break;

			case "HAS CONTROL":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupHasControlColumn");
				break;

			case "LATITUDE":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupLatitudeColumn");
				break;

			case "LONGITUDE":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupLongitudeColumn");
				break;

			case "R:SITE ID":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupSiteIdColumnR");
				break;
			case "R:SITE NAME":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupSiteNameColumnR");
				break;
			case "R:ADDRESS":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupAddressColumnR");
				break;
			case "R:POSTAL CODE":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupPostalCodeColumnR");
				break;
			case "R:ACTIVE":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupActiveColumnR");
				break;
			case "R:TIME ZONE":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupTimeZoneColumnR");
				break;
			case "R:TENANT":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupTenantColumnR");
				break;
			case "R:TENANT ID":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupTenantIDColumnR");
				break;
			case "R:STATE/PROVINCE":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupStateColumnR");
				break;
			case "R:COUNTRY":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupCountryColumnR");
				break;
			case "R:CITY":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupCityColumnR");
				break;
			case "R:ADDRESS 2":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupAddress2ColumnR");
				break;
			case "R:PHONE 1":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupPhone1ColumnR");
				break;
			case "R:PHONE 2":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupPhone2ColumnR");
				break;
			case "R:HAS SOLAR PANELS":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupHasSolarPanelsColumnR");
				break;
			case "R:COMMISSION DATE":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupCommissionDateColumnR");
				break;
			case "R:CONTROL DATE":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupControlDateColumnR");
				break;
			case "R:DECOMMISSIONED DATE":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupDecommissionDateColumnR");
				break;
			case "R:AREA":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupAreaColumnR");
				break;
			case "R:HAS CONTROL":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupHasControlColumnR");
				break;
			case "R:LATITUDE":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupLatitudeColumnR");
				break;
			case "R:LONGITUDE":
				flag = "RLabel";
				labelLocator = getProps().getProperty(
						"chooseChoumnPopupLongitudeColumnR");
				break;

			default:
				logger.error("Switch Case["
						+ chooseColumnHeader
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}

			if (flag.equalsIgnoreCase("RLabel")) {
				labelLocator = labelLocator.replace("#index#", getProps()
						.getProperty("SelectedColumnsPrefix"));
			} else if (flag.equalsIgnoreCase("SLabel")) {
				labelLocator = labelLocator.replace("#index#", getProps()
						.getProperty("AvailableColumnsPrefix"));
			}

			if (flag.equalsIgnoreCase("Label")
					|| flag.equalsIgnoreCase("RLabel")
					|| flag.equalsIgnoreCase("SLabel")) {
				if (BaseUtil.verifyElementDisplayed(labelLocator)) {
					return BaseUtil.getElementText(labelLocator).trim()
							.equals(realElementName);
				}
			}
		} catch (NullPointerException e) {
			logger.error("Header :" + chooseColumnHeader
					+ " does not exist in the grid, see detail message : "
					+ e.getMessage());
			return false;
		} catch (Exception e) {
			logger.error("Failed to displayed the column :"
					+ chooseColumnHeader
					+ " in choose column popup. See detail message : "
					+ e.getMessage());
			return false;
		}
		return false;
	}

	public boolean verifyTenantListsOnTenantDropdownS(String tenantName) {
		BaseUtil.waitForSpinner();
		String defaultTenantValue;
		try {
			String message = "Default tenant value is different from actual value";
			defaultTenantValue = BaseUtil.getElementText(
					getProps().getProperty("sitePageTenantDropdownSelected"))
					.trim();
			if (defaultTenantValue.equalsIgnoreCase("GridPoint")) {
				Assert.assertTrue(
						message,
						defaultTenantValue.trim().equalsIgnoreCase(
								getProps()
										.getProperty("newTenantValueTestData")));
				// selectComboBoxDropDownByValue(getProps().getProperty("siteListPageTenantDropdown"),
				// sitePageObjectText("newTenantValueTestData"));
			} else if (defaultTenantValue.equals(tenantName)) {
				Assert.assertTrue(
						message,
						defaultTenantValue.trim().equalsIgnoreCase(
								getProps().getProperty(
										"actualTenantValueTestData")));
				BaseUtil.selectComboBoxDropDownByValue(
						getProps().getProperty("sitePageValueTenantDropDown"),
						getProps().getProperty("newTenantValueTestData"));
			}

			// String newTenantValue = getSelectedDropDownValues(
			// getProps().getProperty("editSitePageTenantDropDownSelected"));
			// Assert.assertTrue(message,
			// newTenantValue.trim().equalsIgnoreCase(sitePageObjectText("newTenantValueTestData")));
		} catch (Exception e) {
			logger.error("Failed to verify tenant list from tenant dropdown. see detail error message : \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to verify tenant list from tenant dropdown. see detail error message : \n"
					+ e.getStackTrace().toString());
			return false;
		}
		return defaultTenantValue.trim().equalsIgnoreCase(
				getProps().getProperty("newTenantValueTestData"));
	}

	public void selectADMSiteFromGrid_SitePage(String sSiteName) {
		int iTotalNumOfPage = 0;
		boolean bResult = false;
		logger.debug("Selecting the site :" + sSiteName);
		try {
			iTotalNumOfPage = Integer.parseInt(BaseUtil
					.getElementText(
							getProps().getProperty("sitePaginationTotalPages"))
					.trim().replace("/", "").trim());
			if (iTotalNumOfPage == 1)
				iTotalNumOfPage++;
			for (int iNumPage = 1; iNumPage <= iTotalNumOfPage - 1; iNumPage++) {
				List<WebElement> lstSiteNames = BaseUtil
						.getMultipleElementsAfterLoaded(getProps().getProperty(
								"siteGridNameColumnADM"));
				for (WebElement webElement : lstSiteNames) {
					if (webElement.getText().trim().equalsIgnoreCase(sSiteName)) {
						webElement.click();
						BaseUtil.waitForSpinner();
						bResult = true;
						break;
					}
				}
				if (!bResult) {
					Assert.fail("Given test site is not available");
				}
			}
			logger.debug("site :" + sSiteName + " is selected.");
		} catch (Exception e) {
			logger.error("Failed to select site: " + sSiteName
					+ " from site grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the site : " + sSiteName
					+ ", see detail error message: \n"
					+ e.getStackTrace().toString());
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public boolean verifyPostalCode() {
		try {
			String value = BaseUtil.getElementText(getProps().getProperty(
					"editSitePostalCodeField"));
			if (value != "") {
				return true;
			} else {
				Assert.fail("Postal code does not contain any value");
			}

		} catch (Exception e) {
			logger.error("Failed to verify Contained postal code. see detail error message : \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to verify Contained postal code. see detail error message : \n"
					+ e.getStackTrace().toString());
		}
		return false;
	}

	public boolean clickOnTab(String tabName) {
		try {
			switch (tabName.toUpperCase()) {
			case "HOME":
				BaseUtil.instantClick(getProps().getProperty("tabHome"));
				BaseUtil.waitForSpinner();
				return true;
			case "CONTROL":
				BaseUtil.instantClick(getProps().getProperty("tabControl"));
				BaseUtil.waitForSpinner();
				return true;
			case "CONTROL:HVAC":
				BaseUtil.instantClick(getProps().getProperty("tabHVAC"));
				BaseUtil.waitForSpinner();
				return true;
			case "CONTROL:LIGHTING":
				BaseUtil.instantClick(getProps().getProperty("tabLighting"));
				BaseUtil.waitForSpinner();
				return true;
			case "ALARMS":
				BaseUtil.instantClick(getProps().getProperty("tabAlarms"));
				BaseUtil.waitForSpinner();
				return true;
			case "PROJECTS":
				BaseUtil.instantClick(getProps().getProperty("tabProjects"));
				BaseUtil.waitForSpinner();
				return true;
			case "GRAPHS":
				BaseUtil.instantClick(getProps().getProperty("tabGraphs"));
				BaseUtil.explicitWait(TestBase.getTimeout());
				return true;
			case "DATA":
				BaseUtil.instantClick(getProps().getProperty("tabData"));
				BaseUtil.waitForSpinner();
				return true;
			case "REPORTS":
				BaseUtil.instantClick(getProps().getProperty("tabReports"));
				BaseUtil.waitForSpinner();
				return true;
			case "SAVINGS":
				BaseUtil.instantClick(getProps().getProperty("tabSavings"));
				BaseUtil.waitForSpinner();
				return true;
			case "REAL TIME":
				BaseUtil.instantClick(getProps().getProperty("realTimeTab"));
				BaseUtil.waitForSpinner();
				return true;
			default:
				logger.error("Switch Case["
						+ tabName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				break;
			}
		} catch (Exception e) {
			logger.error("Failed to click on tab :" + tabName
					+ " , detail message: " + e.getMessage());
			return false;
		}
		return false;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public boolean clickOperationOnSitePagePVAuditpopup(String buttonName) {
		try {
			switch (buttonName.toUpperCase()) {
			case "CANCEL":
				BaseUtil.clickAndWait(getProps().getProperty(
						"pvAuditPopupCancelButton"));
				return true;
			default:
				logger.error("Switch Case["
						+ buttonName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to click on button :" + buttonName
					+ " , detail message: " + e.getMessage());
			return false;
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public boolean clickOperationOnSitePageChennalAccordion(String buttonName) {
		try {
			switch (buttonName.toUpperCase()) {
			case "EDIT":
				BaseUtil.clickAndWait(getProps().getProperty(
						"detailAccordionEditButton"));
				return true;
			case "UPDATE":
				BaseUtil.clickAndWait(getProps().getProperty(
						"detailAccordionUpdateButton"));
				return true;
			case "VIEW":
				if (BaseUtil.instantElementCheck("Display", getProps()
						.getProperty("detailAccordionViewButton"))) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"detailAccordionViewButton"));
					return true;
				}
				return true;
			case "CANCEL":
				BaseUtil.clickAndWait(getProps().getProperty(
						"detailAccordionCancelButton"));
				return true;
			default:
				logger.error("Switch Case["
						+ buttonName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to click on button :" + buttonName
					+ " , detail message: " + e.getMessage());
			return false;
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public boolean isElementEnabledOnSitePage(String elementName) {
		try {
			switch (elementName.toUpperCase()) {
			case "SAVE":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"addSiteSaveButton"));
			case "DELETE":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"editSiteDeleteButton"));	
			case "CANCEL":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"addSiteCancelButton"));
			case "SAVE AND CLOSE":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"addSiteSaveAndCloseButton"));
			case "ACCEPT SUPPLIED DATA":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"addressVerificationAcceptSuppliedDataButton"));
			case "ACCEPT VERIFIED DATA":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"addressVerificationAcceptVerifiedDataButton"));
			case "BULK SITE CREATION":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"bulkSiteImportButton"));
			case "ADD SITE":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"addSiteButton"));
			case "EXPORT ICON":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"exportIcon"));
					
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
		} catch (Exception e) {
			logger.error("Failed to verify that Element [" + elementName
					+ "] is enabled.  see detail message: " + e.getMessage());
			return false;
		}
	}

	public boolean isSiteGridHeaderDisplayed(String headerName) {
		try {
			switch (headerName.toUpperCase()) {
			case "SITE NAME":
				return BaseUtil
						.getElementText(
								getProps().getProperty("siteGridNameHeader"))
						.trim()
						.equals(getProps()
								.getProperty("siteGridNameHeaderText"));
			case "SITE DESCRIPTION":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"siteGridDescriptionHeader"))
						.trim()
						.equals(getProps().getProperty(
								"siteGridDescriptionHeaderText"));
			case "ADDRESS":
				return BaseUtil
						.getElementText(
								getProps().getProperty("siteGridAddressHeader"))
						.trim()
						.equals(getProps().getProperty(
								"siteGridAddressHeaderText"));
			case "POSTAL CODE":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"siteGridPostalCodeHeader"))
						.trim()
						.equals(getProps().getProperty(
								"siteGridPostalCodeHeaderText"));
			case "TIMEZONE":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty("siteGridTimeZoneHeader"))
						.trim()
						.equals(getProps().getProperty(
								"siteGridTimeZoneHeaderText"));
			case "TENANT":
				return BaseUtil
						.getElementText(
								getProps().getProperty("siteGridTenantHeader"))
						.trim()
						.equals(getProps().getProperty(
								"siteGridTenantHeaderText"));
			default:
				logger.error("Switch Case["
						+ headerName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (NullPointerException e) {
			logger.error("Header :" + headerName
					+ " does not exist in the grid, see detail message : "
					+ e.getMessage());
			return false;
		} catch (Exception e) {
			logger.error("Failed to displayed the header :" + headerName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public boolean isAddSiteBlankErrorMessageDisplayed(
			String errorMessageElement) {
		try {
			switch (errorMessageElement.toUpperCase()) {
			case "SITE NAME":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"addSiteNameErrorMessage"))
						.trim()
						.equals(getProps().getProperty(
								"addSiteNameBlankErrorMessageText"));
			case "SITE DESCRIPTION":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"addSiteDescriptionErrorMessage"))
						.trim()
						.equals(getProps().getProperty(
								"addSiteDescriptionBlankErrorMessageText"));
			case "CITY":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"addSiteCityErrorMessage"))
						.trim()
						.equals(getProps().getProperty(
								"addSiteCityBlankErrorMessageText"));
			case "STATE/PROVINCE":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"addSiteProvinceErrorMessage"))
						.trim()
						.equals(getProps().getProperty(
								"addSiteProvinceBlankErrorMessageText"));
			case "POSTAL CODE":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"addSitePostalCodeErrorMessage"))
						.trim()
						.equals(getProps().getProperty(
								"addSitePostalCodeBlankErrorMessageText"));
			case "PATHS":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"addSitePathErrorMessage"))
						.trim()
						.equals(getProps().getProperty(
								"addSitePathBlankErrorMessageText"));
			default:
				logger.error("Switch Case["
						+ errorMessageElement
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (NullPointerException e) {
			logger.error("Error message is not displayed for element :"
					+ errorMessageElement + ", see detail message : "
					+ e.getMessage());
			return false;
		} catch (Exception e) {
			logger.error("Failed to displayed the error message :"
					+ errorMessageElement + " see detail message : "
					+ e.getMessage());
			return false;
		}
	}

	public boolean IsGridHeaderDisplayed(String headerName) {
		String locator;
		String flag = null;
		try {
			switch (headerName.toUpperCase()) {
			case "SITE NAME":
				flag = "Header";
				locator = getProps().getProperty("siteGridNameHeader");
				break;
			case "SITE DESCRIPTION":
				flag = "Header";
				locator = getProps().getProperty("siteGridDescriptionHeader");
				break;
			case "ADDRESS":
				flag = "Header";
				locator = getProps().getProperty("siteGridAddressHeader");
				break;
			case "POSTAL CODE":
				flag = "Header";
				locator = getProps().getProperty("siteGridPostalCodeHeader");
				break;
			case "TIMEZONE":
				flag = "Header";
				locator = getProps().getProperty("siteGridTimeZoneHeader");
				break;
			case "TENANT":
				flag = "Header";
				locator = getProps().getProperty("siteGridTenantHeader");
				break;
			case "ENDPOINT ID":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupEndpointGridIDHeader");
				break;
			case "CONNECTION STATUS":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupEndpointGridConnectionStatus");
				break;
			case "TYPE":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupEndpointGridConnectionType");
				break;
			case "SERIAL":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupEndpointGridSerialHeader");
				break;
			case "REFERENCE ID":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupEndpointGridReferenceIDHeader");
				break;
			case "LIVE FIRMWARE":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupEndpointGridLiveFirmwareHeader");
				break;
			case "LAST MESSAGE RECEIVED (UTC)":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupEndpointGridLastMessReceivedHeader");
				break;
			case "PROPERTY":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupAuditGridPropertyHeader");
				break;
			case "ACTION":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupAuditGridActionHeader");
				break;
			case "ORIGINAL VALUE":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupAuditGridOriginalValueHeader");
				break;
			case "UPDATED VALUE":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupAuditGridUpdatedValueHeader");
				break;
			case "USER":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupAuditGridUserHeader");
				break;
			case "DATE STAMP":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupAuditGridDateStampHeader");
				break;
			case "CHANNEL ID":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupChannelGridIDHeader");
				break;
			case "CHANNEL NAME":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupChannelGridNameHeader");
				break;
			case "DISPLAY NAME":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupChannelGridDisplayNameHeader");
				break;
			case "DEVICE NAME":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupChannelGridDeviceNameHeader");
				break;
			case "MEASURE TYPE":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupChannelGridMeasureTypeHeader");
				break;
			case "UNIT":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupChannelGridUnitHeader");
				break;
			case "METADATA CATEGORY":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupChannelGridMetadataCategoryHeader");
				break;
			case "TOTAL TYPE":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupChannelGridTotalTypeHeader");
				break;
			case "SHOW IN GRAPHS":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupChannelGridShowInGraphsHeader");
				break;
			case "CHANNEL:ACTION":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupChannelGridActionHeader");
				break;
			case "ID":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupPVAuditGridIDHeader");
				break;
			case "SITENAME":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupPVAuditGridSiteNameHeader");
				break;
			case "STARTDATE":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupPVAuditGridStartDateHeader");
				break;
			case "ENDDATE":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupPVAuditGridEndDateHeader");
				break;
			case "SUBMITTEDDATE":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupPVAuditGridSubmittedDateHeader");
				break;
			case "REQUEST":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupPVAuditGridRequestHeader");
				break;
			case "RESPONSE":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupPVAuditGridResponseHeader");
				break;
			case "STATUS":
				flag = "Header";
				locator = getProps().getProperty(
						"editSitePagePopupPVAuditGridStatusHeader");
				break;
			case "PVAUDITSUMMARY":
				flag = "Attribute";
				locator = getProps().getProperty("pvAuditPopupPVAuditSummary");
				break;
			case "REQUESTXML":
				flag = "Attribute";
				locator = getProps().getProperty("pvAuditPopupRequestXML");
				break;
			case "RESPONSEXML":
				flag = "Attribute";
				locator = getProps().getProperty("pvAuditPopupResponseXML");
				break;

			default:
				logger.error("Switch Case["
						+ headerName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
			if (flag.equalsIgnoreCase("Attribute")) {
				// Assert.assertTrue("Failed to displayed the header :" +
				// headerName, verifyElementDisplayed(locator));
				return BaseUtil.verifyElementDisplayed(locator);
			} else if (flag.equalsIgnoreCase("Header")) {
				return BaseUtil.getElementText(locator).trim()
						.equals(headerName);
			}
		} catch (NullPointerException e) {
			logger.error("Header :"
					+ headerName
					+ " does not exist in the endpoint grid, see detail message : "
					+ e.getMessage());
			return false;
		} catch (Exception e) {
			logger.error("Failed to displayed the header :" + headerName
					+ " see detail message : " + e.getMessage());
			return false;
		}
		return false;
	}

	public boolean isAuditGridHeaderDisplayed(String headerName) {
		try {
			switch (headerName.toUpperCase()) {
			/*
			 * case "EntityID": return
			 * getElementText(getProps().getProperty.get(
			 * "auditPageEntityIdHeader")) .trim() .equals(headerName); case
			 * "EntityName": return getElementText(getProps().getProperty.get(
			 * "auditPageEntityNameHeader" )).trim() .equals(headerName); case
			 * "DomainType": return getElementText(getProps().getProperty.get(
			 * "auditPageDomainTypeHeader" )).trim() .equals(headerName);
			 */
			case "PROPERTY":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupAuditGridPropertyHeader"))
						.trim().equals(headerName);
			case "ACTION":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupAuditGridActionHeader"))
						.trim().equals(headerName);

			case "ORIGINAL VALUE":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupAuditGridOriginalValueHeader"))
						.trim().equals(headerName);
			case "UPDATED VALUE":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupAuditGridUpdatedValueHeader"))
						.trim().equals(headerName);
			case "USER":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"editSitePagePopupAuditGridUserHeader"))
						.trim().equals(headerName);
			case "DATE STAMP":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupAuditGridDateStampHeader"))
						.trim().equals(headerName);
			default:
				logger.error("Switch Case["
						+ headerName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (NullPointerException e) {
			logger.error("Header :"
					+ headerName
					+ " does not exist in the audit grid, see detail message : "
					+ e.getMessage());
			return false;
		} catch (Exception e) {
			logger.error("Failed to displayed the header :" + headerName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public boolean isChannelGridHeaderDisplayed(String headerName) {
		try {
			switch (headerName.toUpperCase()) {
			case "CHANNEL ID":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"editSitePagePopupChannelGridIDHeader"))
						.trim().equals(headerName);
			case "CHANNEL NAME":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupChannelGridNameHeader"))
						.trim().equals(headerName);
			case "DISPLAY NAME":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupChannelGridDisplayNameHeader"))
						.trim().equals(headerName);
			case "DEVICE NAME":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupChannelGridDeviceNameHeader"))
						.trim().equals(headerName);
			case "MEASURE TYPE":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupChannelGridMeasureTypeHeader"))
						.trim().equals(headerName);
			case "UNIT":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupChannelGridUnitHeader"))
						.trim().equals(headerName);
			case "METADATA CATEGORY":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupChannelGridMetadataCategoryHeader"))
						.trim().equals(headerName);
			case "TOTAL TYPE":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupChannelGridTotalTypeHeader"))
						.trim().equals(headerName);
			case "SHOW IN GRAPHS":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupChannelGridShowInGraphsHeader"))
						.trim().equals(headerName);
			case "ACTION":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupChannelGridActionHeader"))
						.trim().equals(headerName);
			default:
				logger.error("Switch Case["
						+ headerName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (NullPointerException e) {
			logger.error("Header :"
					+ headerName
					+ " does not exist in the channel grid, see detail message : "
					+ e.getMessage());
			return false;
		} catch (Exception e) {
			logger.error("Failed to displayed the header :" + headerName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public boolean isPVAuditGridHeaderDisplayed(String headerName) {
		try {
			switch (headerName.toUpperCase()) {
			case "ID":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"editSitePagePopupPVAuditGridIDHeader"))
						.trim().equals(headerName);
			case "SITENAME":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupPVAuditGridSiteNameHeader"))
						.trim().equals(headerName);
			case "STARTDATE":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupPVAuditGridStartDateHeader"))
						.trim().equals(headerName);
			case "ENDDATE":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupPVAuditGridEndDateHeader"))
						.trim().equals(headerName);
			case "SUBMITTEDDATE":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupPVAuditGridSubmittedDateHeader"))
						.trim().equals(headerName);
			case "REQUEST":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupPVAuditGridRequestHeader"))
						.trim().equals(headerName);
			case "RESPONSE":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupPVAuditGridResponseHeader"))
						.trim().equals(headerName);
			case "STATUS":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"editSitePagePopupPVAuditGridStatusHeader"))
						.trim().equals(headerName);
			default:
				logger.error("Switch Case["
						+ headerName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (NullPointerException e) {
			logger.error("Header :"
					+ headerName
					+ " does not exist in the PV audit grid, see detail message : "
					+ e.getMessage());
			return false;
		} catch (Exception e) {
			logger.error("Failed to displayed the header :" + headerName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public void selectCurrentDate() {
		DateFormat dateFormat2 = new SimpleDateFormat("dd");
		Date date2 = new Date();

		String today = dateFormat2.format(date2);

		// find the calendar
		WebElement dateWidget = TestBase
				.getWebDriver()
				.findElement(
						By.xpath("//*[@id='addSitesSection']/div/form/div[1]/div[2]/section/div[13]/div/div/span[2]/ul"));
		List<WebElement> columns = dateWidget.findElements(By.tagName("td"));

		// comparing the text of cell with today's date and clicking it.
		for (WebElement cell : columns) {
			if (cell.getText().equals(today)) {
				cell.click();
				break;
			}
		}

	}

	// public void waitForElement() {
	// explicitWait(2000);
	// long iCount = 0;
	// logger.debug("Waiting for the element to be displayed...");
	// while (iCount < getTimeout()) {
	// if (!instantElementCheck("Display",
	// getProps().getProperty("siteTotalItems"))) {
	// try {
	// Thread.sleep(getPollingFrequency());
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// } else {
	// break;
	// }
	// iCount += getPollingFrequency();
	// }
	// explicitWait(2000);
	// }

	public boolean verifyModifiedValues(String fieldName) {
		try {
			switch (fieldName.toUpperCase()) {
			case "DISPLAY NAME":
				BaseUtil.click(getProps().getProperty(
						"channelAccordionEditDisplayNameField"));
				return BaseUtil
						.getElementAttribute(
								getProps().getProperty(
										"channelAccordionEditDisplayNameField"),
								"value")
						.trim()
						.equals(getProps().getProperty(
								"channelAccordionEditDisplayNameFieldText"));
			case "MEASURE TYPE":
				return BaseUtil
						.getSelectedDropDownValues(
								getProps().getProperty(
										"channelAccordionEditMeasureTypeField"))
						.trim()
						.equals(getProps().getProperty(
								"channelAccordionEditMeasureTypeFieldText"));
			case "METADATA CATEGORY":
				BaseUtil.explicitWait(2000);
				return BaseUtil
						.getSelectedDropDownValues(
								getProps()
										.getProperty(
												"channelAccordionEditMetadataCategoryField"))
						.trim()
						.equals(getProps()
								.getProperty(
										"channelAccordionEditMetadataCategoryFieldText"));
			case "TOTAL TYPE":
				return BaseUtil
						.getSelectedDropDownValues(
								getProps().getProperty(
										"channelAccordionEditTotalTypeField"))
						.trim()
						.equals(getProps().getProperty(
								"channelAccordionEditTotalTypeFieldText"));
			case "NVC:DISPLAY NAME":
				BaseUtil.click(getProps().getProperty(
						"channelAccordionEditDisplayNameField"));
				return BaseUtil
						.getElementAttribute(
								getProps().getProperty(
										"channelAccordionEditDisplayNameField"),
								"value")
						.trim()
						.equals(getProps().getProperty(
								"channelAccordionEditDisplayNameFieldNVCText"));
			case "NVC:MEASURE TYPE":
				return BaseUtil
						.getSelectedDropDownValues(
								getProps().getProperty(
										"channelAccordionEditMeasureTypeField"))
						.trim()
						.equals(getProps().getProperty(
								"channelAccordionEditMeasureTypeFieldNVCText"));
			case "NVC:TOTAL TYPE":
				return BaseUtil
						.getSelectedDropDownValues(
								getProps().getProperty(
										"channelAccordionEditTotalTypeField"))
						.trim()
						.equals(getProps().getProperty(
								"channelAccordionEditTotalTypeFieldNVCText"));
			case "NAME":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"editSitePagePopupDetailSiteName"))
						.trim()
						.equals(getProps().getProperty(
								"modifiedSiteNameEditPage"));
			case "ADDRESS1":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"editSiteAddress1Field"))
						.trim()
						.equals(getProps().getProperty(
								"modifiedAddress1EditPage"));	
			case "CITY":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"editSiteCityField"))
						.trim()
						.equals(getProps().getProperty(
								"modifiedCityEditPage"));	
			case "POSTAL CODE":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"editSitePostalCodeField"))
						.trim()
						.equals(getProps().getProperty(
								"modifiedPostalCodeEditPage"));	
			case "COUNTRY":
				return BaseUtil
						.getSelectedDropDownValues(
								getProps().getProperty(
										"editSiteCountryDropDown"))
						.trim()
						.equals(getProps().getProperty(
								"modifiedAddress1EditPage"));	

			default:
				logger.error("Switch Case["
						+ fieldName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid field name : " + fieldName + " selected");
			}

		} catch (Exception e) {
			logger.error("Failed to verify editablity of field " + fieldName
					+ " see detail message : " + e.getMessage());
			Assert.fail(e.getMessage());
		}
		return false;
	}

	public boolean enterText_EndpointPage(String sElementName, String sText) {
		try {
			List<WebElement> options;
			switch (sElementName.toUpperCase()) {
			case "SELECT_PARENT":
				BaseUtil.clickAndWait(getProps().getProperty(
						"addSiteTenantDropDown"));
				options = TestBase
						.getWebDriver()
						.findElements(
								By.xpath("//*[@id='addSitesSection']/div/form/div[1]/div[1]/section/div[1]/div/div/div/ul/li/ul/li[14]/a"));
				for (WebElement opt : options) {
					WebElement selectedOption = opt
							.findElement(By
									.xpath("//span[contains(text(), 'endpointPage_tenantBot1')]"));
					String checkedText = selectedOption.getText();
					if (sText.equals(checkedText)) {
						opt.click();
					}
				}
				return true;
			default:
				logger.error("Switch Case["
						+ sElementName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the element :" + sElementName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public void enterSiteTestData(String field) {
		String message = "Failed to Enter the value in field [" + field + "]";
		try {
			switch (field.toUpperCase()) {
			case "TENANT DROPDOWN": {
				BaseUtil.click(getProps().getProperty("addSiteTenantDropDown"));
				BaseUtil.explicitWait(1000);
				BaseUtil.selectComboBoxDropDownByValue(
						getProps().getProperty("addSiteTenantDropDownValue"),
						getProps().getProperty(
								"addSiteTenantDropDownTestDataForEndpoint"));
				break;

			}
			case "SITE ID":
				BaseUtil.enterText(
						getProps().getProperty("addSiteNameField"),
						getProps().getProperty(
								"addSiteNameTestDataForEndpoint1"));
				break;
			case "SITE DESCRIPTION":
				BaseUtil.enterText(
						getProps().getProperty("addSiteDescriptionField"),
						getProps().getProperty(
								"addSiteDescriptionTestDataForEndpoint1"));
				break;
			case "ADDRESS1":
				BaseUtil.enterText(
						getProps().getProperty("addSiteAddress1Field"),
						getProps().getProperty(
								"addSiteAddress1TestDataForEndpoint"));
				break;
			case "ADDRESS2":
				BaseUtil.enterText(
						getProps().getProperty("addSiteAddress2Field"),
						getProps().getProperty(
								"addSiteAddress2TestDataForEndpoint"));
				break;
			case "CITY":
				BaseUtil.enterText(getProps().getProperty("addSiteCityField"),
						getProps()
								.getProperty("addSiteCityTestDataForEndpoint"));
				break;
			case "STATE":
				BaseUtil.enterText(
						getProps().getProperty("addSiteProvinceField"),
						getProps().getProperty(
								"addSiteProvinceTestDataForEndpoint"));
				break;
			case "POSTAL CODE":
				BaseUtil.enterText(
						getProps().getProperty("addSitePostalCodeField"),
						getProps().getProperty(
								"addSitePostalCodeTestDataForEndpoint"));
				break;
			case "COUNTRY":
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("addSiteCountryDropDown"),
						getProps().getProperty(
								"addSiteCountryDropDownTestDataForEndpoint"));
				break;
			case "PHONE 1":
				BaseUtil.enterText(
						getProps().getProperty("addSitePhone1Field"),
						getProps().getProperty(
								"addSitePhone1TestDataForEndpoint"));
				break;
			case "PHONE 2":
				BaseUtil.enterText(
						getProps().getProperty("addSitePhone2Field"),
						getProps().getProperty(
								"addSitePhone2TestDataForEndpoint"));
				break;
			case "TIMEZONE":
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("addSiteTimeZoneDropDown"),
						getProps().getProperty(
								"addSiteTimeZoneDropDownTestDataForEndpoint"));
				break;
			case "AREA":
				BaseUtil.enterText(getProps().getProperty("addSiteAreaField"),
						getProps()
								.getProperty("addSiteAreaTestDataForEndpoint"));
				break;
			case "ACTIVE":
				BaseUtil.checkCheckBox(getProps().getProperty(
						"addSiteActiveCheckBox"));
				break;
			case "HAS CONTROL":
				BaseUtil.checkCheckBox(getProps().getProperty(
						"addSiteControlCheckBox"));
				break;
			case "HAS SOLAR PANEL":
				BaseUtil.checkCheckBox(getProps().getProperty(
						"addSiteSolarPanelCheckBox"));
				BaseUtil.explicitWait(1000);
				BaseUtil.enterText(
						getProps().getProperty(
								"addSiteSolarPanelSystemSizeField"),
						getProps()
								.getProperty(
										"addSiteSolarPanelSystemSizeTestDataForEndpoint"));
				break;
			case "PATH":
				BaseUtil.enterText(
						getProps().getProperty("addSitePathsField"),
						getProps().getProperty(
								"addSitePathsFieldTestDataForEndpoint"));
				break;
			}
		} catch (Exception e) {
			logger.error(message + " , detail message: " + e.getMessage());
			Assert.fail(message);
		}
	}

	public boolean isAttributeDisplayedPVAuditPage(String siteGridHeader) {
		try {
			switch (siteGridHeader.toUpperCase()) {
			case "PVAUDITSUMMARY":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"pvAuditPopupPVAuditSummary"));
			case "REQUESTXML":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"pvAuditPopupRequestXML"));
			case "RESPONSEXML":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"pvAuditPopupResponseXML"));
			default:
				logger.error("Switch Case["
						+ siteGridHeader
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the header :" + siteGridHeader
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public void isSiteTabAvailable(String userName) {
		BaseUtil.enterText(getProps().getProperty("adminUserName_Locator"),
				userName);
		BaseUtil.enterText(getProps().getProperty("adminPassword_Locator"),
				PathConstants.DEFAULT_USER_PASSWORD);
		BaseUtil.click(getProps().getProperty("adminLoginbutton_Locator"));

		BaseUtil.waitForSpinner();
		if (userName.equals("SitePage_GPSupportUserBot")) {
			Assert.assertTrue(
					" Expected : Site Tab Tab should be Available. Actual : siteTab tab is not available for user ["
							+ userName + "] ",
					BaseUtil.isElementClickable(getProps().getProperty(
							"siteTab")));
			BaseUtil.clickAndWait(getProps().getProperty("sitesTab"));
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

	public void enterText_AuditPage(String sElementName, String sText) {
		String sCaseOptions = "sElementName, ";
		try {
			switch (sElementName.toUpperCase()) {
			case "SITE_SEARCH":
				BaseUtil.enterText(getProps().getProperty("auditSearchField"),
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

	public boolean verifyErrorMessage(String errorMsg) {
		try {
			BaseUtil.explicitWait(20000);
			return BaseUtil
					.getElementText(
							getProps().getProperty("siteGridZeroRecord"))
					.trim()
					.equals(getProps().getProperty("siteGridZeroRecordText"));
		} catch (Exception e) {
			logger.error("Failed to verify error message :" + errorMsg
					+ " , detail message: " + e.getMessage());
			return false;
		}

	}

	public String getPhoneNo(String query) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Connection conn = getConnection();
		String phoneNo = null;
		try {
			phoneNo = dbutil.getIdFromDB(query, conn);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return phoneNo;
	}

	public String getDecommissionDate(String query) throws SQLException,
			IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Connection conn = getConnection();
		String phoneNo = null;
		try {
			phoneNo = dbutil.getIdFromDB(query, conn);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return phoneNo;
	}

	private Connection getConnection() throws SQLException {
		GPConnection gpcon = new GPConnection();
		return gpcon.getConnection(TestBase.getDbUrl(),
				TestBase.getDbUsername(), TestBase.getDbPassword());
	}

	public void isHomePageAvailable(String userName) {
		BaseUtil.enterText(getProps().getProperty("adminUserName_Locator"),
				userName);
		BaseUtil.enterText(getProps().getProperty("adminPassword_Locator"),
				PathConstants.DEFAULT_USER_PASSWORD);
		BaseUtil.click(getProps().getProperty("adminLoginbutton_Locator"));
		BaseUtil.waitForSpinner();

		if (userName.equals("SitePage_CustomerAnalystUserBot")) {
			Assert.assertTrue(
					" Expected : Home Tab should be Available. Actual : Home tab is not available for user ["
							+ userName + "] ",
					BaseUtil.isElementClickable(getProps().getProperty(
							"HomeTab")));
		}

	}

	public boolean verifyChannelsOnChannelAccordion() {
		boolean bResult = false;
		try {
			List<WebElement> lstChannelName = BaseUtil
					.getMultipleElementsAfterLoaded(getProps().getProperty(
							"channelGridChannelNameColumn"));
			for (WebElement webElement : lstChannelName) {
				bResult = true;
				String channelName = webElement.getText();
				Assert.assertTrue(
						"Channels available in channel accordion are: [[ "
								+ channelName + " ]].", bResult);
			}
			return bResult;

		} catch (Exception e) {
			logger.error("Failed to verify channels on channel accordion , see detail error message: "
					+ e.getMessage());
			return false;
		}
	}

	public boolean verifyNewlyCreatedChannel(String channelName) {
		try {
			WebElement element = BaseUtil.getElementAfterLoaded(getProps()
					.getProperty("channelGridChannelNameColumn"));
			return element.equals(channelName);
		} catch (Exception e) {
			logger.error("Failed to verify newly created channel. see detail error message : \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to verify verify newly created channel. see detail error message : \n"
					+ e.getStackTrace().toString());
		}
		return false;
	}

	public String getChannelId(String query) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Connection conn = getConnection();
		String channelId = null;
		try {
			channelId = dbutil.getIdFromDB(query, conn);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return channelId;
	}

	public String getShowInGraphResult(String query) throws SQLException,
			IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Connection conn = getConnection();
		String showInGraph = null;
		try {
			showInGraph = dbutil.getIdFromDB(query, conn);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return showInGraph;
	}

	public boolean verifyTenantName(String tenantName) {
		try {
			WebElement element = BaseUtil.getElementAfterLoaded(getProps()
					.getProperty("siteGridTenantColumn"));
			return element.equals(tenantName);
		} catch (Exception e) {
			logger.error("Failed to verify newly created channel. see detail error message : \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to verify verify newly created channel. see detail error message : \n"
					+ e.getStackTrace().toString());
		}

		return false;
	}

	public boolean verifyTenantLists() {
		try {
			String message = "Default tenant value is different from actual value";
			String defaultTenantValue = BaseUtil.getElementText(
					getProps()
							.getProperty("editSitePageTenantDropDownSelected"))
					.trim();
			Assert.assertTrue(
					message,
					defaultTenantValue.equalsIgnoreCase(getProps().getProperty(
							"actualTenantValueTestData").trim()));
			// selectComboBoxDropDownByValue(getProps().getProperty("gridpointValueTenantDropDown"),
			// getProps().getProperty("newTenantValueTestData"));
			// String newTenantValue = getSelectedDropDownValues(
			// getProps().getProperty("editSitePageTenantDropDownSelected"));
			// Assert.assertTrue(message,
			// newTenantValue.trim().equalsIgnoreCase(getProps().getProperty("newTenantValueTestData")));
		} catch (Exception e) {
			logger.error("Failed to verify tenant list from tenant dropdown. see detail error message : \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to verify tenant list from tenant dropdown. see detail error message : \n"
					+ e.getStackTrace().toString());
			return false;
		}
		return true;
	}

	public boolean verifyVirtualEndpoint(String virtualEndpoint) {
		try {
			List<WebElement> lstVirtualEndpoint = BaseUtil
					.getMultipleElementsAfterLoaded(getProps().getProperty(
							"editSitePageEndpointsGridTypeColumn"));
			for (WebElement webElement : lstVirtualEndpoint) {
				String virtualEndpointName = webElement.getText();
				Assert.assertTrue("Virtual Endpoint  [[ " + virtualEndpointName
						+ " ]] is not available", virtualEndpointName.trim()
						.equalsIgnoreCase(virtualEndpoint));
			}

		} catch (Exception e) {
			logger.error("Failed to verify virtual endpoint on endpoints accordion , see detail error message: "
					+ e.getMessage());
			Assert.fail("Failed to verify virtual endpoint on endpoints accordion. see detail error message : \n"
					+ e.getStackTrace().toString());
		}
		return virtualEndpoint.trim().equalsIgnoreCase(virtualEndpoint);
	}

	public boolean verifySitesPermissionAccrodion() {
		boolean bResult = false;
		try {
			List<WebElement> lstChannelName = BaseUtil
					.getMultipleElementsAfterLoaded(getProps().getProperty(
							"editUserPermissionAccordionSiteList"));
			for (WebElement webElement : lstChannelName) {
				bResult = true;
				String siteName = webElement.getText();

				Assert.assertTrue(
						"Sites available in Permission accordion are: [[ "
								+ siteName + " ]].", bResult);
			}
			return bResult;

		} catch (Exception e) {
			logger.error("Failed to verify channels on channel accordion , see detail error message: "
					+ e.getMessage());
			return false;
		}
	}

	public boolean verifyFilteredChannel(String channelName) {
		try {
			List<WebElement> lstChannelName = BaseUtil
					.getMultipleElementsAfterLoaded(getProps()
							.getProperty(
									"addVirtualChannelAvailablechannelGridChannelNameColumn"));
			for (WebElement webElement : lstChannelName) {
				String filteredChanneName = webElement.getText();
				Assert.assertTrue("Filtered channel  [[ " + filteredChanneName
						+ " ]] is not available", channelName.trim()
						.equalsIgnoreCase(filteredChanneName));
			}

		} catch (Exception e) {
			logger.error("Failed to verify virtual endpoint on endpoints accordion , see detail error message: "
					+ e.getMessage());
			Assert.fail("Failed to verify virtual endpoint on endpoints accordion. see detail error message : \n"
					+ e.getStackTrace().toString());

		}
		return false;

	}

	public boolean isElementDisplayedOnChannelTable(String buttonName) {
		try {
			switch (buttonName.toUpperCase()) {
			case "EDIT":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"detailAccordionEditButton"));
			case "VIEW":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"detailAccordionViewButton"));
			case "UPDATE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"detailAccordionUpdateButton"));
			case "CANCEL":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"detailAccordionCancelButton"));
			default:
				logger.error("Switch Case["
						+ buttonName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				break;
			}
		} catch (Exception e) {
			logger.error("Failed to verify button [" + buttonName
					+ "]  in channel table. see detail error message : \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to verify button  [" + buttonName
					+ "] in channel table. see detail error message \n"
					+ e.getStackTrace().toString());
		}
		return false;
	}

	public boolean verifyDropDownValues(String dropDownName,
			List<String> valueList) {
		List<String> actualValueList = null, comparableList = null;
		try {
			switch (dropDownName.toUpperCase()) {
			case "OPERATOR":
				actualValueList = BaseUtil
						.getDropDownText(getProps().getProperty(
								"addVirtualChannelSecondOperatorDropDown"));
				comparableList = new ArrayList<String>(valueList);
				actualValueList.removeAll(Arrays.asList("", null));
				comparableList.removeAll(Arrays.asList("", null));
				Collections.sort(comparableList);
				Collections.sort(actualValueList);
				Assert.assertTrue("Failed to verify values of dropdown "
						+ dropDownName, comparableList.equals(actualValueList));
				break;

			default:
				logger.error("Switch Case["
						+ dropDownName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				break;
			}
		} catch (Exception e) {
			logger.error("Failed to verify dropdown values of dropdown ["
					+ dropDownName
					+ "]  in channel table. see detail error message : \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to verify button  [" + dropDownName
					+ "] in channel table. see detail error message \n"
					+ e.getStackTrace().toString());
		}
		return false;
	}

	public void reportError(String sValue) {
		int i = 1;
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		logger.info("<font color='red'>Step-" + i + ": " + sValue
				+ "</font></br>");
		i++;
	}

	public void isElementDisplayedOnAddSiteStateDropdown(String countryValue) {
		boolean flag = false;
		try {
			Select oSelect = new Select(TestBase.getWebDriver().findElement(
					By.id("idAddSiteProvince")));
			List<WebElement> elementCount = oSelect.getOptions();
			int iSize = elementCount.size();
			for (int i = 1; i <= iSize; i++) {
				String sValue = elementCount.get(i).getText();
				flag = true;
				logger.info("State for the country :" + countryValue
						+ " given are : " + sValue);
				Assert.assertTrue("The state displayed in state dropdown is : "
						+ sValue, flag);
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the element see detail message : "
					+ e.getMessage());

		}
	}

	public void isElementDisplayedOnCountryDropdown() {
		boolean flag = false;
		try {
			Select oSelect = new Select(TestBase.getWebDriver().findElement(
					By.id("idAddSiteCountry")));
			List<WebElement> elementCount = oSelect.getOptions();
			int iSize = elementCount.size();
			String defaultValue = elementCount.get(0).getText();
			logger.info("The default value for country dropdown is : "
					+ defaultValue);
			for (int i = 0; i <= iSize; i++) {
				String sValue = elementCount.get(i).getText();
				flag = true;
				logger.info("State for the country dropdown are given as : "
						+ sValue);
				Assert.assertTrue(
						"The countries displayed in country dropdown is : "
								+ sValue, flag);
			}

		} catch (Exception e) {
			logger.error("Failed to displayed the element see detail message : "
					+ e.getMessage());

		}
	}

	public void isElementDisplayedOnEditSiteStateDropdown(String countryValue) {
		boolean flag = false;
		try {
			Select oSelect = new Select(TestBase.getWebDriver().findElement(
					By.id("idEditSiteProvince")));
			List<WebElement> elementCount = oSelect.getOptions();
			int iSize = elementCount.size();
			for (int i = 1; i <= iSize; i++) {
				String sValue = elementCount.get(i).getText();
				flag = true;
				logger.info("State for the country :" + countryValue
						+ " given are : " + sValue);
				Assert.assertTrue("The state displayed in state dropdown is : "
						+ sValue, flag);
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the element see detail message : "
					+ e.getMessage());

		}
	}

	public boolean clickChooseButtonAndSelectInvalidFile(String fileType,
			String buttonName, String filePath) {
		try {
			BaseUtil.uploadFile(getProps().getProperty("chooseFileWindow"),
					filePath);
			BaseUtil.explicitWait(2000);
			return true;
		} catch (Exception e) {
			logger.error("Failed to click on button :" + buttonName
					+ " , detail message: " + e.getMessage());
			return false;
		}
	}

	public boolean verifyFieldsValue(String sSiteName) {
		try {
			String tenantNameValue = TestBase.getWebDriver()
					.findElement(By.id("tenantName")).getText();
			String siteIDValue = TestBase.getWebDriver()
					.findElement(By.id("tenantName")).getText();
			String siteNameValue = TestBase.getWebDriver()
					.findElement(By.id("tenantName")).getText();
			String address1Value = TestBase.getWebDriver()
					.findElement(By.id("tenantName")).getText();
			String cityValue = TestBase.getWebDriver()
					.findElement(By.id("tenantName")).getText();
			String stateValue = TestBase.getWebDriver()
					.findElement(By.id("tenantName")).getText();
			String postalCodeValue = TestBase.getWebDriver()
					.findElement(By.id("tenantName")).getText();
			String countryValue = TestBase.getWebDriver()
					.findElement(By.id("tenantName")).getText();

			if (tenantNameValue.equals("GridPoint Test Automation")
					&& siteIDValue.equals("bulkSiteImportSite_01")
					&& siteNameValue.equals("bulkSiteImportSite_01")
					&& address1Value.equals("bulkSiteImportSite_01_Address")
					&& cityValue.equals("Arlington")
					&& stateValue.equals("New York")
					&& postalCodeValue.equals("22201")
					&& countryValue.equals("United States")) {
				logger.info("The Site is imported successfully with correct values verified on Edit Site Modal");
			}
			return true;
		} catch (Exception e) {
			logger.error("Failed to verify the values for the Site :"
					+ sSiteName + " see detail message : " + e.getMessage());
			return false;
		}
	}

	public boolean verifyErrorMessageBulkImport(String fileType) {

		try {
			switch (fileType.toUpperCase()) {
			case "INVALID HEADER":
				return BaseUtil.getElementText(
						getProps().getProperty(
								"bulkImportHeaderSiteErrorMessage")).equals(
						getProps().getProperty(
								"bulkImportHeaderErrorMessageText"));
			case "INVALID":
				if (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"bulkSiteImportErrorWindow"))) {
					Assert.assertTrue(BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"bulkImportHeaderTenantMissingErrorMessage"))
							.equals(getProps()
									.getProperty(
											"bulkImportHeaderTenantMissingErrorMessageText")));

					Assert.assertTrue(BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"bulkImportHeaderSiteIDMissingErrorMessage"))
							.equals(getProps()
									.getProperty(
											"bulkImportHeaderSiteIDMissingErrorMessageText")));

					Assert.assertTrue(BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"bulkImportHeaderSiteNameMissingErrorMessage"))
							.equals(getProps()
									.getProperty(
											"bulkImportHeaderSiteNameMissingErrorMessageText")));

					Assert.assertTrue(BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"bulkImportHeaderAddressMissingErrorMessage"))
							.equals(getProps()
									.getProperty(
											"bulkImportHeaderAddressMissingErrorMessageText")));

					Assert.assertTrue(BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"bulkImportHeaderCityMissingErrorMessage"))
							.equals(getProps()
									.getProperty(
											"bulkImportHeaderCityMissingErrorMessageText")));

					Assert.assertTrue(BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"bulkImportHeaderStateMissingErrorMessage"))
							.trim()
							.equals(getProps()
									.getProperty(
											"bulkImportHeaderStateMissingErrorMessageText")));

					Assert.assertTrue(BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"bulkImportHeaderPostalCodeMissingErrorMessage"))
							.equals(getProps()
									.getProperty(
											"bulkImportHeaderPostalCodeMissingErrorMessageText")));

					Assert.assertTrue(BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"bulkImportHeaderCountryMissingErrorMessage"))
							.equals(getProps()
									.getProperty(
											"bulkImportHeaderCountryMissingErrorMessageText")));

					Assert.assertTrue(BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"bulkImportHeaderInvalidCountryValue1ErrorMessage"))
							.equals(getProps()
									.getProperty(
											"bulkImportHeaderInvalidCountryValue1ErrorMessageText")));

					Assert.assertTrue(BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"bulkImportHeaderInvalidCountryValue2ErrorMessage"))
							.equals(getProps()
									.getProperty(
											"bulkImportHeaderInvalidCountryValue2ErrorMessageText")));

					Assert.assertTrue(BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"bulkImportHeaderInvalidCountryStateErrorMessage"))
							.equals(getProps()
									.getProperty(
											"bulkImportHeaderInvalidCountryStateErrorMessageText")));

					Assert.assertTrue(BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"bulkImportHeaderInvalidPostalCodeValueErrorMessage"))
							.equals(getProps()
									.getProperty(
											"bulkImportHeaderInvalidPostalCodeValueErrorMessageText")));

					Assert.assertTrue(BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"bulkImportHeaderInvalidStateValue1ErrorMessage"))
							.equals(getProps()
									.getProperty(
											"bulkImportHeaderInvalidStateValue1ErrorMessageText")));

					Assert.assertTrue(BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"bulkImportHeaderInvalidStateFullValueErrorMessage"))
							.equals(getProps()
									.getProperty(
											"bulkImportHeaderInvalidStateFullValueErrorMessageText")));

					Assert.assertTrue(BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"bulkImportHeaderInvalidStateValue2ErrorMessage"))
							.equals(getProps()
									.getProperty(
											"bulkImportHeaderInvalidStateValue2ErrorMessageText")));

					Assert.assertTrue(BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"bulkImportHeaderSiteAlreadyExistErrorMessage"))
							.equals(getProps()
									.getProperty(
											"bulkImportHeaderSiteAlreadyExistErrorMessageText")));

					Assert.assertTrue(BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"bulkImportHeaderSiteInvalidTenantErrorMessage"))
							.equals(getProps()
									.getProperty(
											"bulkImportHeaderSiteInvalidTenantErrorMessageText")));
				}

				return BaseUtil.getElementText(
						getProps().getProperty(
								"bulkImportHeaderSiteErrorMessage")).equals(
						getProps().getProperty(
								"bulkImportHeaderSiteErrorMessageText"));

			default:
				logger.error("Switch Case["
						+ fileType
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (NullPointerException e) {
			logger.error("Header :" + fileType
					+ " does not exist in the grid, see detail message : "
					+ e.getMessage());
			return false;
		} catch (Exception e) {
			logger.error("Failed to displayed the header :" + fileType
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}
	
	public void verifyDetails(String scolumnName, String caseType) {
		try {
			String elementTestData = null;
			switch (scolumnName.toUpperCase()) {
			case "SITE ID":
				elementTestData = BaseUtil.getElementAttribute(getProps()
						.getProperty("editSiteNameField"), "value");
				if (caseType.equalsIgnoreCase("New")) {
					Assert.assertTrue(elementTestData
							.equalsIgnoreCase(getProps().getProperty(
									"addSiteAdditonalNameTestData")));
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
						.getProperty("addSiteadditionalDescriptionTestData")));
				break;
			case "ADDRESS":
				elementTestData = BaseUtil
						.getElementAttribute(
								getProps().getProperty(
										"editSiteAddress1Field"),
								"value");
				Assert.assertTrue(elementTestData.equalsIgnoreCase(getProps()
						.getProperty("addSiteAddress1TestData")));
				break;
			case "CITY":
				elementTestData = BaseUtil.getElementAttribute(getProps()
						.getProperty("editSiteCityField"), "value");
				Assert.assertTrue(elementTestData.equalsIgnoreCase(getProps()
						.getProperty("addSiteCityTestData")));
				break;
			case "POSTAL CODE":
				elementTestData = BaseUtil.getElementAttribute(getProps()
						.getProperty("editSitePostalCodeField"),
						"value");
				Assert.assertTrue(elementTestData.equalsIgnoreCase(getProps()
						.getProperty("addSitePostalCodeTestData")));
				break;
			case "ACTIVE":
				Assert.assertTrue(BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("editSitePagePopupDetailActive")).isSelected());
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
	
	public boolean verifyChannelsOnVCCreationTool(List<String> channelList) {
		boolean bResult = false;
		try {
			List<WebElement> lstChannelName = BaseUtil
					.getMultipleElementsAfterLoaded(getProps().getProperty(
							"channelGridChannelNameColumn"));
			for (WebElement webElement : lstChannelName) {
				bResult = true;
				String channelName = webElement.getText();
				Assert.assertTrue(
						"Channels available in channel accordion are: [[ "
								+ channelName + " ]].", bResult);
			}
			return bResult;

		} catch (Exception e) {
			logger.error("Failed to verify channels on channel accordion , see detail error message: "
					+ e.getMessage());
			return false;
		}
	}

}
