package com.gridpoint.adminui.automation.endpoint;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gridpoint.automation.constants.PathConstants;
import com.gridpoint.automation.database.GPConnection;
import com.gridpoint.automation.database.GPDataBaseUtil;
import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.automation.tests.base.TestBase;
import com.gridpoint.util.BaseUtil;

public class EndpointPage extends CommonInit {

	private Logger logger = Logger.getLogger(EndpointPage.class);

	String endpointPage;

	public void enterText_endpointPage(String sElementName, String sText) {
		String sCaseOptions = "ENDPOINT_SEARCH, ";
		try {
			switch (sElementName.toUpperCase()) {
			case "ENDPOINT_SEARCH":
				BaseUtil.enterText(getProps()
						.getProperty("endpointSearchField"), sText);
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
					+ "] field in endpoint page. see detail error message : \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to enter the value in the [" + sElementName
					+ "] field in endpoint page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
	}
	

	public void selectEndpointFromGrid_EndpointPage(String sEndpointName) {
		boolean bResult = false;
		logger.debug("Selecting the endpoint :" + sEndpointName);
		try {
//			List<WebElement> lstEndpointNames = BaseUtil.getMultipleElementsAfterLoaded(getProps().getProperty(
//							"endpointGridSerialColumn"));
			WebElement endpoint = BaseUtil.getElementAfterLoaded(getProps().getProperty("endpointGridColumn"));
			if(endpoint!=null)
			{
				endpoint.click();
				bResult=true;
			}
			else
			{
				bResult=true;
			}
//			for (WebElement webElement : lstEndpointNames) {
//				if (webElement.getText().trim().equalsIgnoreCase(sEndpointName)) {
//					webElement.click();
//					bResult = true;
//					break;
//				}
//			}
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

	public void selectSiteFromGrid_SitePage(String siteName)
	{
		boolean bResult1 = false;
		logger.debug("Selecting the site :" + siteName);
		try {
//			List<WebElement> lstEndpointNames = BaseUtil.getMultipleElementsAfterLoaded(getProps().getProperty(
//							"endpointGridSerialColumn"));
			WebElement endpoint = BaseUtil.getElementAfterLoaded(getProps().getProperty("siteGridColumn"));
			if(endpoint!=null)
			{
				endpoint.click();
				bResult1=true;
			}
			else
			{
				bResult1=true;
			}
//			for (WebElement webElement : lstEndpointNames) {
//				if (webElement.getText().trim().equalsIgnoreCase(sEndpointName)) {
//					webElement.click();
//					bResult = true;
//					break;
//				}
//			}
			if (!bResult1) {
				Assert.fail("Given test endpoint is not available");
			}
			logger.debug("site :" + siteName + " is selected.");

		} catch (Exception e) {
			logger.error("Failed to select site: " + siteName
					+ " from endpoint grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the site : " + siteName
					+ ", see detail error message: \n"
					+ e.getStackTrace().toString());
		} finally {
			BaseUtil.waitForSpinner();
		}
	}
	public boolean isEndpointsDetailsDisplayed(String element, ResultSet rs)
			throws SQLException {
		try {
			switch (element.toUpperCase()) {
			case "SERIAL":
				if (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"endpointDetailsSerialLabel"))) {
					return BaseUtil.getElementText(
							getProps()
									.getProperty("endpointDetailsSerialValue"))
							.equals(getProps().getProperty(
									"addEndpointSerialTestData1"));
				}
				break;
			case "REFERENCE ID":
				return (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"endpointDetailsReferenceIdLabel")));
			case "LAST MESSAGE RECEIVED (UTC)":
				return (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"endpointDetailsLastMessageReceivedLabel")));
			case "ASSOCIATION TENANT/SITE":
				return (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"endpointDetailsAssociationTenantSiteLabel")));
			case "PASSWORD":
				return (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"endpointDetailsPasswordLabel")));
			case "TYPE":
				if (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"endpointDetailsTypeLabel"))) {
					return BaseUtil.getSelectedDropDownValues(
							getProps().getProperty(
									"endpointDetailsPageTypeValue")).equals(
							rs.getString(3));
				}
				break;
			case "MAC ADDRESS":
				return (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"endpointDetailsMACAddressLabel")));
			case "COMMISSION STATE":
				return (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"endpointDetailsCommissionStateLabel")));
			case "CONNECTION STATUS (UTC)":
				return (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"endpointDetailsConnectionStatusUTCLabel")));
			case "UBOOT VERSION":
				return (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"endpointDetailsUbootVersionLabel")));
			case "LINUX VERSION":
				return (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"endpointDetailsLinuxVersionLabel")));
			case "ROOT FILE SYSTEM VERSION":
				return (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"endpointDetailsRootFileSystemVersionLabel")));
			case "MONITORING":
				return (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"endpointDetailsMonitoringLabel")));
			default:
				logger.error("Switch Case["
						+ element
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Switch Case[" + element
					+ "] is not matched in class[" + getClass().getName()
					+ "] , Method["
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ "]");
			return false;
		}
		return false;
	}

	public boolean isElementEnabledSiteSectionEditEndpointPopup(String attribute) {
		try {
			switch (attribute.toUpperCase()) {
			case "TENANT":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"SiteInformationTenantAttribute"));
			case "SITE ID(NAME)":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"SiteInformationSiteIdAttribute"));
			case "SITE NAME(DESCRIPTION)":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"SiteInformationSiteNameAttribute"));
			case "ADDRESS":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"SiteInformationAddressAttribute"));
			case "POSTAL CODE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"SiteInformationPostalCodeAttribute"));
			case "LATITUDE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"SiteInformationLatitudeAttribute"));
			case "LONGITUDE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"SiteInformationLongitudeAttribute"));
			case "TIME ZONE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"SiteInformationTimeZoneAttribute"));
			default:
				logger.error("Switch Case["
						+ attribute
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Switch Case[" + attribute
					+ "] is not matched in class[" + getClass().getName()
					+ "] , Method["
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ "]");
			return false;
		}
	}
	
	public int getTableRowCount(String objLocater)
	{
		
		WebElement element= BaseUtil.getElementAfterLoaded(objLocater);
		List<WebElement> tableRows = element.findElements(By.tagName("tr"));
		int rows=tableRows.size();
		return rows;
		
	}

	public boolean clickOperationOnEndpointPage(String buttonName) {
		try {
			switch (buttonName.toUpperCase()) {
			case "ENDPOINTS":
				BaseUtil.clickAndWait(getProps().getProperty("endpointTab"));
				BaseUtil.waitForSpinner();
				return true;
			case "TENANTS":
				BaseUtil.clickAndWait(getProps().getProperty("tenantTab"));
				return true;
			case "SITE":
				BaseUtil.clickAndWait(getProps().getProperty("siteTab"));
				BaseUtil.waitForSpinner();
				return true;
			case "ADD ENDPOINT":
				BaseUtil.clickAndWait(getProps().getProperty(
						"addEndpointButton"));
				return true;
			case "SAVE":
				BaseUtil.clickAndWait(getProps().getProperty(
						"addEndpointSaveButton"));
				BaseUtil.waitForSpinner();
				return true;
			case "EDIT:SAVE":
				BaseUtil.clickAndWait(getProps().getProperty(
						"editEndpointSaveButton"));
				BaseUtil.waitForSpinner();
				return true;
			case "CANCEL":
				BaseUtil.clickAndWait(getProps().getProperty(
						"addEndpointCancelButton"));
				return true;
			case "CANCEL_POPUP":
				BaseUtil.clickAndWait(getProps().getProperty(
						"addEndpointCancelConfirmationButton"));
				return true;
			case "X":
				BaseUtil.clickAndWait(getProps().getProperty(
						"editEndpointCloseIcon"));
				return true;
			case "LOGOUT":
				BaseUtil.explicitWait(2000);
				BaseUtil.clickElement(getProps().getProperty("optionDropDown"));
				BaseUtil.clickAndWait(getProps().getProperty("logout"));
				BaseUtil.assertElementDisplayed(
						getProps().getProperty("adminUserName_Locator"),
						"Error while logging out for user");
				return true;
			case "OK_POPUP":
				BaseUtil.clickAndWait(getProps().getProperty(
						"addEndpointOkConfirmationButton"));
				return true;
			case "SEARCH":
				BaseUtil.clickAndWait(getProps().getProperty(
						"endpointSearchButton"));
				BaseUtil.waitForSpinner();
				return true;
			case "SAVE AND CLOSE":
				BaseUtil.clickAndWait(getProps().getProperty(
						"editEndpointSaveAndCloseButton"));
				BaseUtil.waitForSpinner();
				return true;
			case "EDIT ENDPOINT CANCEL":
				BaseUtil.clickAndWait(getProps().getProperty(
						"editEndpointCancelButton"));
				return true;
			case "EDITENDPOINTENABLEEDITING":
				BaseUtil.clickAndWait(getProps().getProperty(
						"enableEditingButton"));
				return true;
			case "RESET ENDPOINT":
				BaseUtil.clickAndWait(getProps().getProperty(
						"resetEndpointButton"));
				BaseUtil.explicitWait(10000);
				return true;
			case "REAL TIME DATA":
				BaseUtil.clickAndWait(getProps().getProperty(
						"realTimeDataButton"));
				BaseUtil.explicitWait(10000);
				return true;
			case "SUSPEND CONNECTIONS":
				BaseUtil.clickAndWait(getProps().getProperty(
						"suspendConnections"));
				BaseUtil.explicitWait(10000);
				return true;
			case "ENABLE CONNECTIONS":
				BaseUtil.clickAndWait(getProps().getProperty(
						"enableConnections"));
				BaseUtil.explicitWait(15000);
				return true;
			case "REPLACE CONTROLLER":
				BaseUtil.clickAndWait(getProps().getProperty(
						"replaceController"));
				BaseUtil.explicitWait(5000);
				return true;
			case "REPLACE":
				BaseUtil.clickAndWait(getProps().getProperty("Replace"));
				BaseUtil.explicitWait(5000);
				return true;
			case "GRAPH":
				BaseUtil.clickAndWait(getProps().getProperty("graphIcon"));
				BaseUtil.waitForSpinner();
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
			BaseUtil.waitForSpinner(2000);
		}
	}

	public boolean isElementDisplayedOnEndpointPage(String elementName) {
		try {
			switch (elementName.toUpperCase()) {
			case "ADD ENDPOINT":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addEndpointTitleHeading"));
			case "SERIAL":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addEndpointSerialHeading"));
			case "TYPE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addEndpointTypeHeading"));
			case "SAVE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addEndpointSaveButton"));
			case "CANCEL":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addEndpointCancelButton"));
			case "EDIT:CANCEL":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editEndpointCancelButton"));
			case "EDIT:SAVE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editEndpointSaveButton"));
			case "SAVE AND CLOSE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editEndpointSaveAndCloseButton"));
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
			logger.error("Failed to displayed the element :" + elementName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public boolean verifyErrorMessage_SerialRequired(String errorMessage) {
		return BaseUtil.getElementText(
				getProps().getProperty("SerialRequiredErrorMessage"))
				.equalsIgnoreCase(errorMessage);
	}

	public boolean clickOperationOnConfirmationBox(String buttonName) {

		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 10);
			switch (buttonName.toUpperCase()) {
			case "OK":
				Alert okAlert = wait.until(ExpectedConditions.alertIsPresent());
				okAlert.accept();
				BaseUtil.explicitWait(2000);
				return true;
			case "CANCEL":
				Alert cancelAlert = wait.until(ExpectedConditions
						.alertIsPresent());
				cancelAlert.dismiss();
				BaseUtil.explicitWait(2000);
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
		}
	}

	public boolean checkPopupExistence(String sPopName, String sExpMsg) {
		try {
			switch (sPopName.toUpperCase()) {
			case "ADDENDPOINT_CONFIRMATION":
				if (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addEndpointPopupAlert"))) {
					return BaseUtil.getElementText(
							getProps().getProperty(
									"addEndpointPopupAlertMessage"))
							.equalsIgnoreCase(sExpMsg);
				} else {
					return false;
				}
			case "ADDENDPOINT":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addEndpointPopupAlert"));
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

	public boolean enterText_SerialColumn(String columnName, String sValue) {
		try {
			switch (columnName.toUpperCase()) {
			case "SERIAL":
				BaseUtil.enterText(
						getProps().getProperty("addEndpointPopupSerialField"),
						sValue);
				BaseUtil.explicitWait(1000);
				return true;
			default:
				logger.error("Switch Case["
						+ columnName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the element :" + columnName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public boolean verifyErrorMessage_SerialColumnRequiredString(
			String errorMessage) {
		return BaseUtil.getElementText(
				getProps()
						.getProperty("SerialColumnRequiredStringErrorMessage"))
				.equalsIgnoreCase(errorMessage);
	}

	public void checkPopupExistence_EndpointPage(String sPopupName,
			String sExpMsg) {
		String sCaseOptions = "EDIT ENDPOINT,";
		try {
			switch (sPopupName.toUpperCase()) {
			case "EDIT ENDPOINT":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editEndpointPagePopup"));
				break;
			case "EDIT_ENDPOINT_CONFIRMATION":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editEndpointCancelConfirmationPopup"));
				if (!(BaseUtil.getElementText(getProps().getProperty(
						"editEndpointCancelConfirmationPopupMessage"))
						.equalsIgnoreCase(sExpMsg)))
					Assert.fail("Failed to display the confirmation box with expected message: +"
							+ sExpMsg
							+ ", but actual message was: "
							+ BaseUtil
									.getElementText(getProps()
											.getProperty(
													"editEndpointCancelConfirmationPopupMessage")));
				break;
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
					+ "in endpoint page. , see detail error message \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to check existence of popup: [" + sPopupName
					+ "] in endpoint page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
	}

	public boolean modify_FirstEndpoint(String fields) {
		try {
			GPConnection gpcon = new GPConnection();
			GPDataBaseUtil dbutil = new GPDataBaseUtil();
			Connection conn = gpcon.getConnection(TestBase.getDbUrl(),
					TestBase.getDbUsername(), TestBase.getDbPassword());
			switch (fields.toUpperCase()) {
			case "TYPE":
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("modifyEndpointTypeDropDown"),
						getProps().getProperty(
								"modifyEndpointTypeDropDownTestData2"));
				return true;
			case "ASSOCIATION TENANT/SITE":
				BaseUtil.clickAndWait(getProps().getProperty(
						"modifyEndpointTenantDropDown"));
				BaseUtil.explicitWait(1000);
				BaseUtil.selectComboBoxDropDownByValue(
						getProps().getProperty(
								"modifyEndpointTenantDropDownNew"),
						getProps().getProperty(
								"modifyEndpointTenantDropDownTestData3").trim());

				String getPremisesIdQuery = "SELECT premises_id FROM premises WHERE name='endpointPage_siteBot1'";
				logger.info("getPremisesIdQuery : " + getPremisesIdQuery);
				String premisesId = dbutil
						.getIdFromDB(getPremisesIdQuery, conn);
				if (null == premisesId) {
					Assert.fail("Given site [endpointPage_siteBot1] is not available");
				}
				String premisesName = "endpointPage_siteBot1";
				String premisesNameWithId = premisesName.concat("/").concat(
						premisesId);
				BaseUtil.enterText(
						getProps().getProperty("modifyEndpointAssociateSiteId"),
						premisesNameWithId);
				BaseUtil.explicitWait(2000);
				return true;

			case "TENANT:ASSOCIATION TENANT/SITE":
				BaseUtil.clickAndWait(getProps().getProperty(
						"modifyEndpointTenantDropDown"));
				BaseUtil.explicitWait(1000);
				BaseUtil.selectComboBoxDropDownByValue(
						getProps().getProperty(
								"editEndpointTenantPage_tenantBot1"),
						getProps()
								.getProperty(
										"modifyEndpointTenantDropDowntenantPage_tenantBot1")
								.trim());
				// String getPremisesIdQuery1 = "SELECT premises_id FROM
				// premises WHERE
				// name='endpointPage_siteBot1'";
				// logger.info("getPremisesIdQuery : " + getPremisesIdQuery1);
				// String premisesId1 = dbutil.getIdFromDB(getPremisesIdQuery1,
				// conn);
				// if (null == premisesId1) {
				// Assert.fail("Given site [endpointPage_siteBot1] is not available");
				// }
				// String premisesName1 = "tenantPage_endpointBot1";
				// String premisesNameWithId1 =
				// premisesName1.concat("/").concat(premisesId1);
				// BaseUtil.BaseUtil.enterText(getProps().getProperty("modifyEndpointAssociateSiteId"),
				// premisesNameWithId1);
				// BaseUtil.explicitWait(2000);
				return true;
			}
		} catch (Exception e) {
			logger.error("Failed to modify field [" + fields
					+ "] in endpoint page. , see detail error message \n"
					+ e.getMessage());
			Assert.fail("Failed to modify field [" + fields
					+ "] in endpoint page. see detail error message \n"
					+ e.getMessage());
		}
		return false;
	}

	public void verifySearchFunctionality(String args) {
		String[] arr = args.split(":");
		String keyword = arr[1];
		String totalItemsText = null;
		int initialTotalItemsCount = 0;
		int laterTotalItemsCount = 0;
		WebElement searchBox = null;
		try {
			switch (keyword.toUpperCase()) {
			case "INIT3CHAR":
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("endpointTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				BaseUtil.enterText(getProps()
						.getProperty("endpointSearchField"), arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("endpointSearchButton"));
				try {
					if (BaseUtil
							.getElementText(
									getProps().getProperty(
											"endpointGridZeroRecord"))
							.equalsIgnoreCase(
									BaseUtil.getElementText(getProps()
											.getProperty(
													"endpointGridZeroRecordText")))) {
						Assert.fail("No record available. Test cannot be continue.");
					}
				} catch (Exception e) {
					logger.info("Sufficient records are available");
				}

				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("endpointTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				if (laterTotalItemsCount > initialTotalItemsCount) {
					Assert.fail("Endpoint search results after entering 3 characters "
							+ arr[0] + " is not as expected");
				}
				break;
			case "RANDOM":
				String randomSearchString = RandomStringUtils
						.randomAlphanumeric(10);
				BaseUtil.enterText(getProps()
						.getProperty("endpointSearchField"), randomSearchString);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("endpointSearchButton"));
				BaseUtil.waitForSpinner();
				String elementText = BaseUtil.getElementText(getProps()
						.getProperty("endpointGridZeroRecord"));
				Assert.assertTrue(
						"Multiple results found for the string : "
								+ randomSearchString,
						elementText.equalsIgnoreCase(getProps().getProperty(
								"endpointGridZeroRecordText")));
				break;
			case "LONGTEXT":
				String longSearchString = RandomStringUtils
						.randomAlphanumeric(25);
				BaseUtil.enterText(getProps()
						.getProperty("endpointSearchField"), longSearchString);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("endpointSearchButton"));
				BaseUtil.waitForSpinner();
				String elementLongText = BaseUtil.getElementText(getProps()
						.getProperty("endpointGridZeroRecord"));
				Assert.assertTrue("Multiple results found for the string : "
						+ longSearchString, elementLongText
						.equalsIgnoreCase(getProps().getProperty(
								"endpointGridZeroRecordText")));
				break;
			case "BACKSPACE":
				BaseUtil.enterText(getProps()
						.getProperty("endpointSearchField"), arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("endpointSearchButton"));
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("endpointTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				searchBox = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("endpointSearchField"));
				searchBox.sendKeys(Keys.BACK_SPACE);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("endpointSearchButton"));
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("endpointTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				if (laterTotalItemsCount < initialTotalItemsCount) {
					Assert.fail("Endpoint search results are not as expected when backspace is clicked");
				}
				break;
			case "DELETE":
				BaseUtil.enterText(getProps()
						.getProperty("endpointSearchField"), arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("endpointSearchButton"));
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("endpointTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());

				searchBox = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("endpointSearchField"));
				searchBox.sendKeys(Keys.CONTROL + "a");
				searchBox.sendKeys(Keys.DELETE);
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("endpointTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				if (laterTotalItemsCount < initialTotalItemsCount) {
					Assert.fail("Endpoint search results after deleting the keyword "
							+ arr[0] + " is not as expected");
				}
				break;
			case "PAGINATION":
				BaseUtil.enterText(getProps()
						.getProperty("endpointSearchField"), arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("endpointSearchButton"));
				BaseUtil.waitForSpinner();
				boolean nextFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("endpointPaginationNext"));
				boolean lastFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("endpointPaginationLast"));
				boolean prevFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("endpointPaginationPrev"));
				boolean firstFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("endpointPaginationFirst"));
				Assert.assertTrue(
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

	public void clickPaginationEndpointPage(String sLinkName) {
		try {
			switch (sLinkName.toUpperCase()) {
			case "NEXT":
				Assert.assertTrue(
						"Required data is not present. Next button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"endpointPaginationNext")));
				BaseUtil.click(getProps().getProperty("endpointPaginationNext"));
				break;
			case "PREV":
				Assert.assertTrue(
						"Required data is not present. Prev button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"endpointPaginationPrev")));
				BaseUtil.click(getProps().getProperty("endpointPaginationPrev"));
				break;
			case "LAST":
				Assert.assertTrue(
						"Required data is not present. Last button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"endpointPaginationLast")));
				BaseUtil.click(getProps().getProperty("endpointPaginationLast"));
				break;
			case "FIRST":
				Assert.assertTrue(
						"Required data is not present. First button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"endpointPaginationFirst")));
				BaseUtil.click(getProps()
						.getProperty("endpointPaginationFirst"));
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

	public void verifyEndpointsHistoryAccordionPagination(String task) {
		String totalPages = null;
		String currentPage = null;
		try {
			switch (task.toUpperCase()) {
			case "NEXT":
				String pageNumber = BaseUtil
						.getElementAttribute(
								getProps()
										.getProperty(
												"endpointHistoryAccordionPaginationCurrentPage"),
								"value");
				Assert.assertTrue("Next page is not displayed",
						Integer.parseInt(pageNumber) > 1);
				break;
			case "PREVIOUS":
				totalPages = BaseUtil.getElementText(getProps().getProperty(
						"endpointHistoryAccordionPaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1)
						.trim();
				currentPage = BaseUtil
						.getElementAttribute(
								getProps()
										.getProperty(
												"endpointHistoryAccordionPaginationCurrentPage"),
								"value");
				Assert.assertTrue("Previous page is not displayed", Integer
						.parseInt(currentPage) < Integer.parseInt(totalPages));
				break;
			case "LAST":
				totalPages = BaseUtil.getElementText(getProps().getProperty(
						"endpointHistoryAccordionPaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1)
						.trim();
				currentPage = BaseUtil
						.getElementAttribute(
								getProps()
										.getProperty(
												"endpointHistoryAccordionPaginationCurrentPage"),
								"value");
				Assert.assertTrue(
						"Last page is not displayed",
						(Integer.parseInt(totalPages) == Integer
								.parseInt(currentPage)
								&& !BaseUtil
										.verifyElementEnabled(getProps()
												.getProperty(
														"endpointHistoryAccordionPaginationNext")) && !BaseUtil
								.verifyElementEnabled(getProps()
										.getProperty(
												"endpointHistoryAccordionPaginationLast"))));
				break;
			case "FIRST":
				currentPage = BaseUtil
						.getElementAttribute(
								getProps()
										.getProperty(
												"endpointHistoryAccordionPaginationCurrentPage"),
								"value");
				Assert.assertTrue(
						"First page is not displayed",
						(Integer.parseInt(currentPage) == 1
								&& !BaseUtil
										.verifyElementEnabled(getProps()
												.getProperty(
														"endpointHistoryAccordionPaginationFirst")) && !BaseUtil
								.verifyElementEnabled(getProps()
										.getProperty(
												"endpointHistoryAccordionPaginationPrev"))));
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

	public boolean setRandomPage(int iTotalPage, int iPageNum, String accordion) {
		String sArrow = "";
		int iDiff = 0;
		try {
			int iCurrentPage;
			WebElement element;
			Boolean success = false;
			switch (accordion.toUpperCase()) {
			case "ENDPOINT":
				iCurrentPage = Integer.parseInt(BaseUtil
						.getElementAttribute(
								getProps().getProperty(
										"endpointPaginationCurrentPage"),
								"value"));
				if (iCurrentPage >= iPageNum) {
					iDiff = iCurrentPage - iPageNum;
					sArrow = "DOWN";
				} else {
					iDiff = iPageNum - iCurrentPage;
					sArrow = "UP";
				}
				element = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("endpointPaginationCurrentPage"));
				element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				for (int iCount = 1; iCount < iDiff + 1; iCount++) {
					if (sArrow.equals("UP")) {
						element.sendKeys(Keys.chord(Keys.ARROW_UP));
					} else {
						element.sendKeys(Keys.chord(Keys.ARROW_DOWN));
					}
				}
				success = true;
				break;
			case "DEVICES":
				iCurrentPage = Integer
						.parseInt(BaseUtil
								.getElementAttribute(
										getProps()
												.getProperty(
														"endpointDevicesAccordionPaginationCurrentPage"),
										"value"));
				if (iCurrentPage >= iPageNum) {
					iDiff = iCurrentPage - iPageNum;
					sArrow = "DOWN";
				} else {
					iDiff = iPageNum - iCurrentPage;
					sArrow = "UP";
				}
				element = BaseUtil
						.getElementAfterLoaded(getProps()
								.getProperty(
										"endpointDevicesAccordionPaginationCurrentPage"));
				element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				for (int iCount = 1; iCount < iDiff + 1; iCount++) {
					if (sArrow.equals("UP")) {
						element.sendKeys(Keys.chord(Keys.ARROW_UP));
					} else {
						element.sendKeys(Keys.chord(Keys.ARROW_DOWN));
					}
				}
				success = true;
				break;
			}
			return success;
		} catch (Exception e) {
			logger.error("Some error occured while setting the number["
					+ iPageNum + "] in page number input box : "
					+ e.getMessage());
			return false;
		}
	}

	public void clickOnEndpointAccordian(String accordionName) {
		try {
			if (BaseUtil.verifyElementEnabled(getProps().getProperty(
					"editSitePagePopupEndpointAccordian"))) {
				BaseUtil.click(getProps().getProperty(
						"editSitePagePopupEndpointAccordian"));
				BaseUtil.explicitWait(2000);
			}

		} catch (Exception e) {
			logger.error("Failed to check existence of popup:" + accordionName
					+ "in site page. , see detail error message \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to check existence of popup: [" + accordionName
					+ "] in site page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
	}

	public void verifyAssociatedEndpoint(String sEndpointName, String sSiteName) {
		try {
			String endpointname = BaseUtil.getElementText(getProps()
					.getProperty("editSiteEndpointGridSerialColumn"));
			if (endpointname.equalsIgnoreCase(sEndpointName)) {
				Assert.assertTrue("Endpoint [" + sEndpointName
						+ "]  associated with site [" + sSiteName
						+ "] is displaying in endpoint grid",
						endpointname.equalsIgnoreCase(sEndpointName));

			} else {
				Assert.assertTrue(
						"Endpoint ["
								+ sEndpointName
								+ "] associated with site ["
								+ sSiteName
								+ "] is not displaying in endpoint grid after changing the site",
						false);
			}
		} catch (Exception e) {
			logger.error("Failed to verify that endpoint is associated with site ["
					+ sSiteName
					+ " in endpoint accordion of site eit popup \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to verify that endpoint is associated with site ["
					+ sSiteName
					+ " in endpoint accordion of site eit popup \n"
					+ e.getStackTrace().toString());
		}
	}

	public void clickOnEditEndpointPageAccordion(String accordionName) {
		try {
			switch (accordionName.toUpperCase()) {
			case "DEVICES":
				if (BaseUtil.verifyElementEnabled(getProps().getProperty(
						"editEndpointPageDevicesAccordion"))) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"editEndpointPageDevicesAccordion"));
				}
				break;
			default:
				logger.error("Switch Case["
						+ accordionName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Failed to check existence of popup ["
						+ accordionName
						+ "] because you passed wrong parameter["
						+ accordionName
						+ "] either in feature file or test class");

			}
		} catch (Exception e) {
			logger.error("Failed to check existence of popup:" + accordionName
					+ "in endpoint page. , see detail error message \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to check existence of popup: [" + accordionName
					+ "] in endpoint page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
	}

	public boolean clickOperationOnEndpointpage(String sElement) {
		try {
			switch (sElement.toUpperCase()) {
			case "TENANTS":
				BaseUtil.clickAndWait(getProps().getProperty("tenantTab"));
				BaseUtil.explicitWait(10000);
				return true;
			case "ADD TENANT":
				BaseUtil.clickAndWait(getProps().getProperty("addTenantButton"));
				BaseUtil.explicitWait(10000);
				return true;
			case "SAVE BUTTON":
				BaseUtil.clickAndWait(getProps().getProperty(
						"addTenantPopupSaveButton"));
				return true;
			case "SITES":
				BaseUtil.clickAndWait(getProps().getProperty("siteTab"));
				BaseUtil.explicitWait(2000);
				return true;
			case "ADD SITE":
				BaseUtil.clickAndWait(getProps().getProperty("addSiteButton"));
				BaseUtil.explicitWait(2000);
				return true;
			default:
				logger.error("Switch Case["
						+ sElement
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to click on button :" + sElement
					+ " , detail message: " + e.getMessage());
			return false;
		} finally {
			BaseUtil.explicitWait(3000);
			BaseUtil.waitForSpinner();
		}
	}

	public boolean isElementDisplayedOnTenantPageEndpointPage(
			String sElementName) {

		try {
			switch (sElementName.toUpperCase()) {
			case "NAME":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addTenantPopupNameField"));
				// case "HOSTNAME":
				// return
				// BaseUtil.verifyElementDisplayed(getProps().getProperty("addTenantPopupHostnameField"));
			case "PARENT":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addTenantPopupParentSelection"));
			case "SAVE_BUTTON":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addTenantPopupSaveButton"));
			case "CANCEL_BUTTON":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addTenantPopupCancelButton"));
			case "INHERIT":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addTenantPopupInheritCheckBox"));
			case "FAULT_TEXT":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addTenantPopupInheritCheckBox"));
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

	public boolean clickOnEditEndpointAccordion(String accordionName) {
		try {
			switch (accordionName.toUpperCase()) {
			case "CONFIGURATION":
				BaseUtil.clickAndWait(getProps().getProperty(
						"configurationAcoordion"));
				BaseUtil.explicitWait(2000);
				return true;
			default:
				logger.error("Switch Case["
						+ accordionName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to click the accordion :" + accordionName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public boolean xmlVerification(String xmlName) {
		try {
			if (xmlName
					.equalsIgnoreCase("driver.xml, schedule.xml and peripheral.xml")) {
				Assert.assertTrue(
						"No such xml file exits in configuration accordion",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"XmlLocation")));
				return true;
			}

		} catch (Exception e) {
			logger.error("Failed to verify the xml location:" + xmlName
					+ " , detail message: " + e.getMessage());
			return false;
		}
		return false;
	}

	public void clickOnXmlShowButton(String buttonName, String xmlName) {
		try {
			if (xmlName
					.equalsIgnoreCase("driver.xml, schedule.xml and peripheral.xml")) {
				if (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"showButtonCombinedXml"))) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"showButtonCombinedXml"));
					Assert.assertTrue("Textarea is not blank", BaseUtil
							.verifyElementDisplayed(getProps().getProperty(
									"textAreaWithBlankValue")));

				}
			} else if (xmlName.equalsIgnoreCase("endpoint.xml")) {
				if (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"showButtonEndpointXml"))) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"showButtonEndpointXml"));
					Assert.assertTrue("Textarea is not blank", BaseUtil
							.verifyElementDisplayed(getProps().getProperty(
									"textAreaWithBlankValue")));
				}
			}
		} catch (Exception e) {
			logger.error("Failed to click on button [" + buttonName
					+ " in configuraion accordion \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to click on button [" + buttonName
					+ " in configuraion accordion \n"
					+ e.getStackTrace().toString());
		}
	}

	public void clickEnableEditingButton(String buttonName, String type) {
		try {
			switch (type.toUpperCase()) {
			case "COMBINED XML":
				if (BaseUtil.verifyElementEnabled(getProps().getProperty(
						"enableButtonCombinedXml"))) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"enableButtonCombinedXml"));
					BaseUtil.explicitWait(3000);
					// click(getProps().getProperty("editableTextArea"));
					BaseUtil.setElementAttribute(
							getProps().getProperty("editableTextArea"), "xyz");
				}
				break;
			case "ENDPOINT XML":
				if (BaseUtil.verifyElementEnabled(getProps().getProperty(
						"enableButtonEndpointXml"))) {
					BaseUtil.click(getProps().getProperty(
							"enableButtonEndpointXml"));
				}
				break;
			}
		} catch (Exception e) {
			logger.error("Failed to verify whether textarea is editable or not when click on button :"
					+ buttonName + " , detail message: " + e.getMessage());
			Assert.fail(e.getMessage());
			// Assert.assertTrue(e.getMessage(), true);
		}
	}

	public void verifyValueAddableInTextArea(String xmlName) {
		try {
			if (xmlName
					.equalsIgnoreCase("driver.xml, schedule.xml and peripheral.xml")) {
				if (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editableTextAreaDriverXML"))) {
					BaseUtil.enterText(
							getProps().getProperty("editableTextAreaDriverXML"),
							"xyz");
				} else {
					Assert.fail("Combined.XML Text Area is not editable");
				}
			} else if (xmlName.equalsIgnoreCase("endpoint.xml")) {
				if (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editableTextAreaEndpointXML"))) {
					BaseUtil.enterText(
							getProps().getProperty(
									"editableTextAreaEndpointXML"), "xyz");
				} else {
					Assert.fail("Endpoint.XML Text Area is not editable");
				}
			}
		} catch (Exception e) {
			logger.error("Failed to add some values in text area of :"
					+ xmlName + " , detail message: " + e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	public void verifyButtonsIsDisable(String buttonName) {
		try {
			switch (buttonName.toUpperCase()) {
			case "VALIDATECONFIG":
				Assert.assertTrue(
						"Failed to verify the disability of button ["
								+ buttonName + "]",
						!(BaseUtil.verifyElementEnabled(getProps().getProperty(
								"validateConfigButton"))));
				break;
			case "SAVECONFIG":
				Assert.assertTrue(
						"Failed to verify the disability of button ["
								+ buttonName + "]",
						!(BaseUtil.verifyElementEnabled(getProps().getProperty(
								"saveConfigButton"))));
				break;
			case "Download XML Schema":
				Assert.assertTrue(
						"Failed to verify the disability of button ["
								+ buttonName + "]",
						!(BaseUtil.verifyElementEnabled(getProps().getProperty(
								"downloadXmlSchemaButton"))));
				break;
			}
		} catch (Exception e) {
			logger.error("Failed to verify the disability of button :"
					+ buttonName + " , detail message: " + e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	public boolean verifyButtonsOnConfigurationAccor(String buttonName) {
		try {
			switch (buttonName.toUpperCase()) {
			case "DOWNLOAD XML SCHEMA":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"downloadXmlSchemaButton"));
			case "CONFIG HISTORY":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"configHistoryButton"));
			case "VALIDATE CONFIG":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"validateConfigButton"));
			case "SAVE CONFIG":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"saveConfigButton"));
			case "UPLOAD CONFIG FILE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"uploadConfigFileButton"));
			case "DOWNLOAD CONFIG FILE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"downloadConfigFileButton"));
			case "ENABLE EDITING(COMBINEDXML)":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"enableEditingCombinedXmlButton"));
			case "ENABLE EDITING(ENDPOINTXML)":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"enableEditingEndpointXmlButton"));
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
			logger.error("Failed to verify the button:" + buttonName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public boolean enterText_EndpointTenant(String sElementName, String sText) {
		try {
			switch (sElementName.toUpperCase()) {
			case "NAME":
				BaseUtil.enterText(
						getProps().getProperty("addTenantPopupNameField"),
						sText);
				return true;
			case "HOSTNAME":
				BaseUtil.enterText(
						getProps().getProperty("addTenantPopupHostnameField"),
						sText);
				return true;
			case "PARENT":
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("addTenantPopupParentField"),
						sText);
				return true;
			case "TENANT_SEARCH":
				BaseUtil.enterText(
						getProps().getProperty("tenantsSearchField"), sText);
				return true;
			case "ENDPOINT_SEARCH":
				BaseUtil.enterText(getProps()
						.getProperty("endpointSearchField"), sText);
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

	public boolean verifyText_EndpointTenantPage(String sElementName,
			String sExpText) {

		try {
			switch (sElementName.toUpperCase()) {
			case "HOSTNAME":
				return BaseUtil.getElementAttribute(
						getProps().getProperty("addTenantPopupHostnameField"),
						"value").equalsIgnoreCase(sExpText);
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
			logger.error("Failed to get the text of the element :"
					+ getProps().getProperty("addTenantPopupHostnameField")
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public boolean isElementEnabledOnTenantPage(String sElementName) {
		try {
			switch (sElementName.toUpperCase()) {
			case "SAVE":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"addTenantPopupSaveButton"));
			case "CANCEL":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"addTenantPopupCancelButton"));
			case "TENANT_DETAIL_GRID":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"tenantDetailGrid"));
			case "HOSTNAME":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"addTenantPopupHostnameField"));
			case "EDITTENANT_PARENT":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"editTenantPopupParentField"));
			case "NAME":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"addTenantPopupNameField"));
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
			logger.error("Failed to verify that Element [" + sElementName
					+ "] is enabled.  see detail message: " + e.getMessage());
			return false;
		}
	}

	public void enterEndpointType(String type) {
		String message = "Failed to select the endpoint type  [" + type
				+ "] from the dropdown";
		try {
			switch (type.toUpperCase()) {
			case "EC1000":
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("addEndpointTypeDropDown"),
						getProps().getProperty(
								"addEndpointTypeDropDownTestData1"));
				break;
			}
		} catch (Exception e) {
			logger.error(message + " , detail message: " + e.getMessage());
			Assert.fail(message);
		}
	}

	public void clickOperationOnConfigurationAccordion(String buttonName) {
		try {
			if(buttonName.equalsIgnoreCase("Download-Xml"))
			{
			BaseUtil.clickAndWait(getProps().getProperty(
					"downloadXmlSchemaButton"));
			}
			else if(buttonName.equalsIgnoreCase("Config History"))
			{
				BaseUtil.clickAndWait(getProps().getProperty(
						"configHistoryButton"));
			}
			return;
		} catch (Exception e) {
			logger.error("Failed to click on button  [" + buttonName
					+ "] is enabled.  see detail message: " + e.getMessage());

		}
	}
	public void clickOnEditEndpointConfigurationConfigHistory(String element)
	{
		if(element.equalsIgnoreCase("closeButton"))
		{
			BaseUtil.clickAndWait(getProps().getProperty("editEndpointConfigHistoryClose"));
		}
	}

	public boolean verifyErrorMessage_editEndpointReadOnly(String errorMessage) {
		return BaseUtil.getElementText(
				getProps().getProperty("readOnlyEndpointErrorMessage"))
				.equalsIgnoreCase(errorMessage);
	}

	public boolean verifyUpdatedFields(String fields) {
		try {
			switch (fields.toUpperCase()) {
			case "TYPE":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"updatedEndpointTypeDropDown"))
						.trim()
						.equals(getProps().getProperty(
								"modifyEndpointTypeDropDownTestData"));
			case "ASSOCIATION TENANT/SITE":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"updatedEndpointTenantDropDown"))
						.trim()
						.equals(getProps().getProperty(
								"updatedValueOfAssociationsTestData"));
			default:
				logger.error("Switch Case["
						+ fields
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to verify updated fields [" + fields
					+ "] is enabled.  see detail message: " + e.getMessage());
			return false;
		}
	}

	public boolean modify_SecondEndpoint(String fields) {
		try {
			switch (fields.toUpperCase()) {
			case "TYPE":
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("modifyEndpointTypeDropDown"),
						getProps().getProperty(
								"modifyEndpointTypeDropDownTestData2"));
				return true;

			case "TENANT:ASSOCIATION TENANT/SITE":
				BaseUtil.instantClick(getProps().getProperty(
						"modifyEndpointTenantDropDown"));
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("updateEndpointTenantDropDown"),
						getProps().getProperty("modifyTenantDropDownTestData"));
				GPConnection gpcon = new GPConnection();
				GPDataBaseUtil dbutil = new GPDataBaseUtil();
				Connection conn = gpcon.getConnection(TestBase.getDbUrl(),
						TestBase.getDbUsername(), TestBase.getDbPassword());
				String getPremisesIdQuery = "SELECT premises_id FROM premises WHERE name='endpointPage_siteBot2'";
				logger.info("getPremisesIdQuery : " + getPremisesIdQuery);
				String premisesId = dbutil
						.getIdFromDB(getPremisesIdQuery, conn);
				String premisesName = "endpointPage_siteBot2";
				String premisesNameWithId = premisesName.concat("/").concat(
						premisesId);
				BaseUtil.enterText(
						getProps().getProperty("modifyEndpointAssociateSiteId"),
						premisesNameWithId);
				BaseUtil.explicitWait(2000);
				return true;

			case "ENDPOINT: ASSOCIATION TENANT/SITE":
				BaseUtil.instantClick(getProps().getProperty(
						"modifyEndpointTenantDropDown"));
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("updateEndpointTenantDropDown"),
						getProps().getProperty(
								"modifyEndpointTenantDropDownTestData"));
				GPDataBaseUtil dbutil1 = new GPDataBaseUtil();
				GPConnection gpcon1 = new GPConnection();
				Connection conn1 = gpcon1.getConnection(TestBase.getDbUrl(),
						TestBase.getDbUsername(), TestBase.getDbPassword());
				String getPremisesIdQuery1 = "SELECT premises_id FROM premises WHERE name='endpointPage_siteBot2'";
				logger.info("getPremisesIdQuery : " + getPremisesIdQuery1);
				String premisesId1 = dbutil1.getIdFromDB(getPremisesIdQuery1,
						conn1);
				String premisesName1 = "endpointPage_siteBot2";
				String premisesNameWithId1 = premisesName1.concat("/").concat(
						premisesId1);
				BaseUtil.enterText(
						getProps().getProperty("modifyEndpointAssociateSiteId"),
						premisesNameWithId1);
				BaseUtil.explicitWait(2000);
				return true;
			}
		} catch (Exception e) {
			logger.error("Failed to modify field " + fields
					+ "in endpoint page. , see detail error message \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to modify field [" + fields
					+ "] in endpoint page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
		return false;
	}

	public boolean clickOperationsOnSitePage(String buttonName) {
		try {
			switch (buttonName.toUpperCase()) {
			case "SEARCH":
				BaseUtil.clickAndWait(getProps()
						.getProperty("siteSearchButton"));
				return true;
			case "EDIT SITE CANCEL":
				BaseUtil.clickAndWait(getProps().getProperty(
						"editSitePageCancelButton"));
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

	public void selectSiteFromSiteGrid_SitePage(String sSiteName) {
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
		}
	}

	public void popupVerification(String sPopupName, String sExpMsg) {
		String sCaseOptions = "EDIT SITE,";
		try {
			switch (sPopupName.toUpperCase()) {
			case "EDIT SITE":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSitePagePopup"));
				BaseUtil.explicitWait(5000);
				break;
			case "EDIT_SITE_CONFIRMATION":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSiteCancelConfirmationPopup"));
				if (!(BaseUtil.getElementText(getProps().getProperty(
						"editSiteCancelConfirmationPopupMessage"))
						.equalsIgnoreCase(sExpMsg)))
					Assert.fail("Failed to display the confirmation box with expected message: +"
							+ sExpMsg
							+ ", but actual message was: "
							+ BaseUtil.getElementText(getProps().getProperty(
									"editSiteCancelConfirmationPopupMessage")));
				break;
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

	public void enterText_SitePage(String sElementName, String sText) {
		String sCaseOptions = "SITE_SEARCH, ";
		try {
			switch (sElementName.toUpperCase()) {
			case "SITE_SEARCH":
				BaseUtil.enterText(getProps().getProperty("siteSearchField"),
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

	public boolean modifyAssociateSiteName(String fieldName) {
		try {
			switch (fieldName.toUpperCase()) {
			case "ASSOCIATION TENANT/SITES":
				BaseUtil.enterText(
						getProps().getProperty("modifyEndpointAssociateSiteId"),
						getProps().getProperty(
								"modifyEndpointTenantDropDownTestDataNew"));
				BaseUtil.explicitWait(2000);
				return true;
			}
		} catch (Exception e) {
			logger.error("Failed to modify field " + fieldName
					+ "in endpoint page. , see detail error message \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to modify field [" + fieldName
					+ "] in endpoint page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
		return false;
	}

	public boolean clickOnAccordionOfEditEndpointPopup(String accordionName) {
		try {
			switch (accordionName.toUpperCase()) {
			case "AUDIT":
				BaseUtil.explicitWait(2000);
				BaseUtil.clickAndWait(getProps().getProperty("auditAccordion"));
				BaseUtil.explicitWait(2000);
				return true;
			case "DETAILS":
				BaseUtil.clickAndWait(getProps()
						.getProperty("detailsAccordion"));
				BaseUtil.explicitWait(2000);
				return true;
			case "CONFIGURATION":
				BaseUtil.clickAndWait(getProps().getProperty(
						"configurationAccordion"));
				BaseUtil.explicitWait(10000);
				return true;
			case "HISTORY":
				BaseUtil.clickAndWait(getProps()
						.getProperty("historyAccordion"));
				BaseUtil.explicitWait(2000);
				return true;
			case "DEVICES":
				BaseUtil.clickAndWait(getProps().getProperty("deviceAccordion"));
				BaseUtil.explicitWait(2000);
				return true;
			case "PERIPHERALS":
				BaseUtil.clickAndWait(getProps().getProperty(
						"peripheralsAccordion"));
				BaseUtil.explicitWait(2000);
				return true;
			default:
				logger.error("Switch Case["
						+ accordionName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Failed to check existence of popup ["
						+ accordionName
						+ "] because you passed wrong parameter["
						+ accordionName
						+ "] either in feature file or test class. \n please choose valid parameter from the following list: \n ["
						+ "]");
			}
		} catch (Exception e) {
			logger.error("Failed to click on accordion  [" + accordionName
					+ "] see detail error message : \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to click on accordion [" + accordionName
					+ "]  see detail error message \n"
					+ e.getStackTrace().toString());
		}
		return false;
	}

	public boolean verifyAccordionOpened(String accordionName) {
		try {
			switch (accordionName.toUpperCase()) {
			case "AUDIT":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"auditAccordionGridEditEndpoint"));
			case "DETAILS":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"detailsAccordionGridEditEndpoint"));
			case "HISTORY":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"historyAccordionGridEditEndpoint"));
			case "DEVICES":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"deviceAccordionGridEditEndpoint"));
			case "PERIPHERALS":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"peripheralsAccordionGridEditEndpoint"));
			}
		} catch (Exception e) {
			logger.error("Failed to verify the accordion  [" + accordionName
					+ "] see detail error message : \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to verify the accordion [" + accordionName
					+ "]  see detail error message \n"
					+ e.getStackTrace().toString());
		}
		return false;
	}

	public boolean verifyColumns(String list, String accordionName) {
		try {
			if (accordionName.equalsIgnoreCase("Audit")) {
				try {
					switch (list.toUpperCase()) {
					case "PROPERTY":
						return BaseUtil
								.getElementText(
										getProps().getProperty(
												"auditGridPropertyColumn"))
								.trim()
								.equals(getProps().getProperty(
										"auditGridPropertyColumnText"));
					case "ACTION":
						return BaseUtil
								.getElementText(
										getProps().getProperty(
												"auditGridActionColumn"))
								.trim()
								.equals(getProps().getProperty(
										"auditGridActionColumnText"));
					case "ORIGINAL VALUE":
						return BaseUtil
								.getElementText(
										getProps().getProperty(
												"auditGridOriginalValueColumn"))
								.trim()
								.equals(getProps().getProperty(
										"auditGridOriginalValueColumnText"));
					case "UPDATED VALUE":
						return BaseUtil
								.getElementText(
										getProps().getProperty(
												"auditGridUpdatedValueColumn"))
								.trim()
								.equals(getProps().getProperty(
										"auditGridUpdatedValueColumnText"));
					case "USER":
						return BaseUtil
								.getElementText(
										getProps().getProperty(
												"auditGridUserColumn"))
								.trim()
								.equals(getProps().getProperty(
										"auditGridUserColumnText"));
					case "DATE STAMP":
						return BaseUtil
								.getElementText(
										getProps().getProperty(
												"auditGridDateStampColumn"))
								.trim()
								.equals(getProps().getProperty(
										"auditGridDateStampColumnText"));
					}
				} catch (Exception e) {
					logger.error("Failed to verify the column  [" + list
							+ "]  see detail error message : \n"
							+ e.getStackTrace().toString());
					Assert.fail("Failed to verify column [" + list
							+ "]  see detail error message \n"
							+ e.getStackTrace().toString());
				}
			}
			if (accordionName.equalsIgnoreCase("History")) {
				try {
					switch (list.toUpperCase()) {
					case "TIMESTAMP":
						return BaseUtil.verifyElementDisplayed(getProps()
								.getProperty("historyGridTimestampColumn"));
					case "LEVEL":
						return BaseUtil.verifyElementDisplayed(getProps()
								.getProperty("historyGridLevelColumn"));
					case "MODULE":
						return BaseUtil.verifyElementDisplayed(getProps()
								.getProperty("historyGridModuleColumn"));
					case "MESSAGE":
						return BaseUtil.verifyElementDisplayed(getProps()
								.getProperty("historyGridMessageColumn"));
					}
				} catch (Exception e) {
					logger.error("Failed to verify the column  [" + list
							+ "] see detail error message : \n"
							+ e.getStackTrace().toString());
					Assert.fail("Failed to verify column [" + list
							+ "]  see detail error message \n"
							+ e.getStackTrace().toString());
				}
			}
			if (accordionName.equalsIgnoreCase("Devices")) {
				try {
					switch (list.toUpperCase()) {
					case "DEVICE ID":
						return BaseUtil.verifyElementDisplayed(getProps()
								.getProperty("deviceGridDeviceIdColumn"));
					case "DEVICE TYPE":
						return BaseUtil.verifyElementDisplayed(getProps()
								.getProperty("deviceGridDeviceTypeColumn"));
					case "NAME":
						return BaseUtil.verifyElementDisplayed(getProps()
								.getProperty("deviceGridNameColumn"));
					case "ADDRESS":
						return BaseUtil.verifyElementDisplayed(getProps()
								.getProperty("deviceGridAddressColumn"));
					case "CHANNEL COUNT":
						return BaseUtil.verifyElementDisplayed(getProps()
								.getProperty("deviceGridChannelCountColumn"));
					}
				} catch (Exception e) {
					logger.error("Failed to verify the column  [" + list
							+ "] see detail error message : \n"
							+ e.getStackTrace().toString());
					Assert.fail("Failed to verify column [" + list
							+ "]  see detail error message \n"
							+ e.getStackTrace().toString());
				}
			}
			if (accordionName.equalsIgnoreCase("Audit")) {
				try {
					switch (list.toUpperCase()) {
					case "ID":
						return BaseUtil.verifyElementDisplayed(getProps()
								.getProperty("PeripheralsGridIdColumn"));
					case "GPEC PERIPHERAL ID":
						return BaseUtil
								.verifyElementDisplayed(getProps()
										.getProperty(
												"PeripheralsGridGpecPeripheralIdColumn"));
					case "NAME":
						return BaseUtil.verifyElementDisplayed(getProps()
								.getProperty("PeripheralsGridNameColumn"));
					case "ADDRESS":
						return BaseUtil.verifyElementDisplayed(getProps()
								.getProperty("PeripheralsGridAddressColumn"));
					case "TYPE":
						return BaseUtil.verifyElementDisplayed(getProps()
								.getProperty("PeripheralsGridTypeColumn"));
					case "FIRMWARE VERSION":
						return BaseUtil
								.verifyElementDisplayed(getProps().getProperty(
										"PeripheralsGridFirmwareVersionColumn"));
					}
				} catch (Exception e) {
					logger.error("Failed to verify the column  [" + list
							+ "]  see detail error message : \n"
							+ e.getStackTrace().toString());
					Assert.fail("Failed to verify column [" + list
							+ "]  see detail error message \n"
							+ e.getStackTrace().toString());
				}
			}

		} catch (Exception e) {
			logger.error("Failed to verify the accordion  [" + accordionName
					+ "] see detail error message : \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to verify the accordion [" + accordionName
					+ "]  see detail error message \n"
					+ e.getStackTrace().toString());
		}
		return false;
	}

	public boolean verifyPagination(String link, String accordionName) {
		try {
			if (accordionName.equalsIgnoreCase("Audit")) {
				boolean nextFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("auditPaginationNext"));
				System.out.println("nextFlg =" + nextFlg);
				boolean lastFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("auditPaginationLast"));
				System.out.println("lastFlg =" + lastFlg);
				boolean prevFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("auditPaginationPrev"));
				System.out.println("prevFlg =" + prevFlg);
				boolean firstFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("auditPaginationFirst"));
				System.out.println("firstFlg =" + firstFlg);
				Assert.assertTrue(
						"Expected : All the pagination buttons should be disable, Actual : one or more buttons are enabled",
						(nextFlg || lastFlg || prevFlg || firstFlg));
				return true;
			}
			if (accordionName.equalsIgnoreCase("History")) {
				boolean nextFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("historyGridPaginationNext"));
				boolean lastFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("historyGridPaginationLast"));
				boolean prevFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("historyGridPaginationPrev"));
				boolean firstFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("historyGridPaginationFirst"));
				Assert.assertTrue(
						"Expected : All the pagination buttons should be displayed, Actual : one or more buttons are displayed",
						(nextFlg || lastFlg || prevFlg || firstFlg));
				return true;
			}
			if (accordionName.equalsIgnoreCase("Devices")) {
				boolean nextFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("devicesGridPaginationNext"));
				boolean lastFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("devicesGridPaginationLast"));
				boolean prevFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("devicesGridPaginationPrev"));
				boolean firstFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("devicesGridPaginationFirst"));
				Assert.assertTrue(
						"Expected : All the pagination buttons should be displayed, Actual : one or more buttons are displaying",
						(nextFlg || lastFlg || prevFlg || firstFlg));
				return true;
			}
			if (accordionName.equalsIgnoreCase("Peripherals")) {
				boolean nextFlg = BaseUtil.verifyElementEnabled(getProps()
						.getProperty("peripheralsGridPaginationNext"));
				boolean lastFlg = BaseUtil.verifyElementEnabled(getProps()
						.getProperty("peripheralsGridPaginationLast"));
				boolean prevFlg = BaseUtil.verifyElementEnabled(getProps()
						.getProperty("peripheralsGridPaginationPrev"));
				boolean firstFlg = BaseUtil.verifyElementEnabled(getProps()
						.getProperty("peripheralsGridPaginationFirst"));
				Assert.assertTrue(
						"Expected : All the pagination buttons should be disable, Actual : one or more buttons are enabled",
						(nextFlg || lastFlg || prevFlg || firstFlg));
				return true;
			}
		} catch (Exception e) {
			logger.error("Failed to verify pegination  for the link  " + link
					+ " see detail error message \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to modify field [" + link
					+ "] in endpoint page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
		return false;
	}

	public boolean verifyDatePicker(String list) {
		try {
			switch (list.toUpperCase()) {
			case "START DATE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"auditStartDateText"));
			case "START TIME":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"auditStartTimeHourText"));
			case "END DATE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"auditEndDateText"));
			case "END TIME":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"auditEndTimeHourText"));
			}
		} catch (Exception e) {
			logger.error("Failed to verify date picker element  " + list
					+ " see detail error message \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to verify date picker element [" + list
					+ "] in endpoint page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
		return false;
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

	public boolean verifyButtons(String buttonName) {
		try {
			switch (buttonName.toUpperCase()) {
			case "RESET ENDPOINT":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"resetEndpointButton"));
			case "REAL TIME DATA":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"realTimeDataButton"));
			}
		} catch (Exception e) {
			logger.error("Failed to verify existance of button button  "
					+ buttonName + " see detail error message \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to verify existance of button button ["
					+ buttonName
					+ "] in endpoint page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
		return false;
	}

	public boolean isHistoryGridHeaderDisplayed(String historyAccordionHeader) {
		try {
			switch (historyAccordionHeader.toUpperCase()) {
			case "TIMESTAMP":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"historyGridTimestampColumn")).trim()
						.equals(historyAccordionHeader);
			case "LEVEL":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty("historyGridLevelColumn"))
						.trim().equals(historyAccordionHeader);

			case "MODULE":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"historyGridModuleColumn")).trim()
						.equals(historyAccordionHeader);
			case "MESSAGE":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"historyGridMessageColumn")).trim()
						.equals(historyAccordionHeader);
			default:
				logger.error("Switch Case["
						+ historyAccordionHeader
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (NullPointerException e) {
			logger.error("Header :"
					+ historyAccordionHeader
					+ " does not exist in the audit grid, see detail message : "
					+ e.getMessage());
			return false;
		} catch (Exception e) {
			logger.error("Failed to displayed the header :"
					+ historyAccordionHeader + " see detail message : "
					+ e.getMessage());
			return false;
		}
	}

	public boolean isPeripheralsGridHeaderDisplayed(
			String peripheralsAccordionHeader) {
		try {
			switch (peripheralsAccordionHeader.toUpperCase()) {
			case "ID":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"PeripheralsGridIdColumn")).trim()
						.equals(peripheralsAccordionHeader);
			case "GPEC PERIPHERAL ID":
				return BaseUtil
						.getElementText(
								getProps()
										.getProperty(
												"PeripheralsGridGpecPeripheralIdColumn"))
						.trim().equals(peripheralsAccordionHeader);
			case "NAME":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"PeripheralsGridNameColumn")).trim()
						.equals(peripheralsAccordionHeader);
			case "ADDRESS":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"PeripheralsGridAddressColumn")).trim()
						.equals(peripheralsAccordionHeader);
			case "TYPE":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"PeripheralsGridTypeColumn")).trim()
						.equals(peripheralsAccordionHeader);
			case "FIRMWARE VERSION":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"PeripheralsGridFirmwareVersionColumn"))
						.trim().equals(peripheralsAccordionHeader);
			case "VALIDATED":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"PeripheralsGridValidatedColumn"))
						.trim().equals(peripheralsAccordionHeader);
			case "RESPONSIVE":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"PeripheralsGridResponsiveColumn"))
						.trim().equals(peripheralsAccordionHeader);
			case "COMM ID":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"PeripheralsGridCommIdColumn")).trim()
						.equals(peripheralsAccordionHeader);
			}
		} catch (Exception e) {
			logger.error("Failed to verify the column  ["
					+ peripheralsAccordionHeader
					+ "]  see detail error message : \n"
					+ e.getStackTrace().toString());
			return false;
		}
		return false;
	}

	public boolean isAuditGridHeaderDisplayed(String auditAccordionHeader) {
		try {
			switch (auditAccordionHeader.toUpperCase()) {
			case "PROPERTY":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"auditGridPropertyColumn")).trim()
						.equals(auditAccordionHeader);
			case "ACTION":
				return BaseUtil
						.getElementText(
								getProps().getProperty("auditGridActionColumn"))
						.trim().equals(auditAccordionHeader);
			case "ORIGINAL VALUE":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"auditGridOriginalValueColumn")).trim()
						.equals(auditAccordionHeader);
			case "UPDATED VALUE":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"auditGridUpdatedValueColumn")).trim()
						.equals(auditAccordionHeader);
			case "USER":
				return BaseUtil
						.getElementText(
								getProps().getProperty("auditGridUserColumn"))
						.trim().equals(auditAccordionHeader);
			case "DATE STAMP":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"auditGridDateStampColumn")).trim()
						.equals(auditAccordionHeader);
			}
		} catch (Exception e) {
			logger.error("Failed to verify the column  ["
					+ auditAccordionHeader + "]  see detail error message : \n"
					+ e.getStackTrace().toString());
			return false;
		}
		return false;
	}

	public boolean isDevicesGridHeaderDisplayed(String devicesColumnHeader) {
		try {
			switch (devicesColumnHeader.toUpperCase()) {
			case "DEVICE ID":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"deviceGridDeviceIdColumn")).trim()
						.equals(devicesColumnHeader);
			case "DEVICE TYPE":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"deviceGridDeviceTypeColumn")).trim()
						.equals(devicesColumnHeader);
			case "NAME":
				return BaseUtil
						.getElementText(
								getProps().getProperty("deviceGridNameColumn"))
						.trim().equals(devicesColumnHeader);
			case "ADDRESS":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"deviceGridAddressColumn")).trim()
						.equals(devicesColumnHeader);
			case "CHANNEL COUNT":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"deviceGridChannelCountColumn")).trim()
						.equals(devicesColumnHeader);
			}
		} catch (Exception e) {
			logger.error("Failed to verify the column  [" + devicesColumnHeader
					+ "]  see detail error message : \n"
					+ e.getStackTrace().toString());
			return false;
		}
		return false;
	}

	public boolean verifyEnabledButtons(String buttonList) {
		try {
			switch (buttonList.toUpperCase()) {
			case "VALIDATE CONFIG":
				Assert.assertTrue(
						"Failed to verify the disability of button ["
								+ buttonList + "]",
						(BaseUtil.verifyElementEnabled(getProps().getProperty(
								"validateConfigButton"))));
				break;
			case "SAVE CONFIG":
				Assert.assertTrue(
						"Failed to verify the disability of button ["
								+ buttonList + "]",
						(BaseUtil.verifyElementEnabled(getProps().getProperty(
								"saveConfigButton"))));
				break;
			}
		} catch (Exception e) {
			logger.error("Failed to verify the disability of button :"
					+ buttonList + " , detail message: " + e.getMessage());
			Assert.fail(e.getMessage());
		}
		return false;
	}

	public boolean clickOnEnableEditingButton(String buttonName,
			String xmlLocation) {
		try {
			if (xmlLocation
					.equalsIgnoreCase("driver.xml, schedule.xml peripheral.xml and config.xml")) {
				if (BaseUtil.verifyElementEnabled(getProps().getProperty(
						"enableButtonCombinedXml"))) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"enableButtonCombinedXml"));
					return true;
				}
			} else if (xmlLocation.equalsIgnoreCase("endpoint.xml")) {
				if (BaseUtil.verifyElementEnabled(getProps().getProperty(
						"enableButtonEndpointXml"))) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"enableButtonEndpointXml"));
					return true;
				}
			}

		} catch (Exception e) {
			logger.error("Failed to click on show button of xml location :"
					+ xmlLocation + ", detail message: " + e.getMessage());
			return false;
		}
		return false;
	}

	public void clickPaginationEndpointPageHistoryAccordion(String sLinkName) {
		try {
			switch (sLinkName.toUpperCase()) {
			case "NEXT":
				Assert.assertTrue(
						"Required data is not present. Next button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"endpointHistoryAccordionPaginationNext")));
				BaseUtil.click(getProps().getProperty(
						"endpointHistoryAccordionPaginationNext"));
				break;
			case "PREV":
				Assert.assertTrue(
						"Required data is not present. Prev button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"endpointHistoryAccordionPaginationPrev")));
				BaseUtil.click(getProps().getProperty(
						"endpointHistoryAccordionPaginationPrev"));
				break;
			case "LAST":
				Assert.assertTrue(
						"Required data is not present. Last button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"endpointHistoryAccordionPaginationLast")));
				BaseUtil.click(getProps().getProperty(
						"endpointHistoryAccordionPaginationLast"));
				break;
			case "FIRST":
				Assert.assertTrue(
						"Required data is not present. First button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"endpointHistoryAccordionPaginationFirst")));
				BaseUtil.click(getProps().getProperty(
						"endpointHistoryAccordionPaginationFirst"));
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
			BaseUtil.waitForSpinner(2000);
		}
	}

	public void clickPaginationEndpointPageAuditAccordion(String sLinkName) {
		try {

			switch (sLinkName.toUpperCase()) {
			case "NEXT":
				if (BaseUtil.isElementClickable(getProps().getProperty(
						"auditPaginationNext"))) {
					Assert.assertTrue(
							"Required data is not present. Next button is not enabled",
							BaseUtil.verifyElementEnabled(getProps()
									.getProperty("auditPaginationNext")));
					BaseUtil.click(getProps()
							.getProperty("auditPaginationNext"));
				}
				System.out.println("Next button is not enable");
				break;
			case "PREV":
				Assert.assertTrue(
						"Required data is not present. Prev button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"auditPaginationPrev")));
				BaseUtil.click(getProps().getProperty("auditPaginationPrev"));
				break;
			case "LAST":
				Assert.assertTrue(
						"Required data is not present. Last button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"auditPaginationLast")));
				BaseUtil.click(getProps().getProperty("auditPaginationLast"));
				break;
			case "FIRST":
				Assert.assertTrue(
						"Required data is not present. First button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"auditPaginationFirst")));
				BaseUtil.click(getProps().getProperty("auditPaginationFirst"));
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
			BaseUtil.waitForSpinner(2000);
		}
	}

	public void verifyEndpointsDetailGridPagination(String task) {
		String totalPages = null;
		String currentPage = null;
		try {
			switch (task.toUpperCase()) {
			case "NEXT":
				String pageNumber = BaseUtil.getElementAttribute(getProps()
						.getProperty("endpointPaginationCurrentPage"), "value");
				Assert.assertTrue("Next page is not displayed",
						Integer.parseInt(pageNumber) > 1);
				break;
			case "PREVIOUS":
				totalPages = BaseUtil.getElementText(getProps().getProperty(
						"endpointPaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1)
						.trim();
				currentPage = BaseUtil.getElementAttribute(getProps()
						.getProperty("endpointPaginationCurrentPage"), "value");
				Assert.assertTrue("Previous page is not displayed", Integer
						.parseInt(currentPage) < Integer.parseInt(totalPages));
				break;
			case "LAST":
				totalPages = BaseUtil.getElementText(getProps().getProperty(
						"endpointPaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1)
						.trim();
				currentPage = BaseUtil.getElementAttribute(getProps()
						.getProperty("endpointPaginationCurrentPage"), "value");
				Assert.assertTrue(
						"Last page is not displayed",
						(Integer.parseInt(totalPages) == Integer
								.parseInt(currentPage)
								&& !BaseUtil.verifyElementEnabled(getProps()
										.getProperty("endpointPaginationNext")) && !BaseUtil
								.verifyElementEnabled(getProps().getProperty(
										"endpointPaginationLast"))));
				break;
			case "FIRST":
				currentPage = BaseUtil.getElementAttribute(getProps()
						.getProperty("endpointPaginationCurrentPage"), "value");
				Assert.assertTrue(
						"First page is not displayed",
						(Integer.parseInt(currentPage) == 1
								&& !BaseUtil
										.verifyElementEnabled(getProps()
												.getProperty(
														"endpointPaginationFirst")) && !BaseUtil
								.verifyElementEnabled(getProps().getProperty(
										"endpointPaginationPrev"))));
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

	public void clickPaginationEndpointPageDevicesAccordion(String sLinkName) {
		try {
			switch (sLinkName.toUpperCase()) {
			case "NEXT":
				Assert.assertTrue(
						"Required data is not present. Next button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"devicesGridPaginationNext")));
				BaseUtil.click(getProps().getProperty(
						"devicesGridPaginationNext"));
				break;
			case "PREV":
				Assert.assertTrue(
						"Required data is not present. Prev button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"devicesGridPaginationPrev")));
				BaseUtil.click(getProps().getProperty(
						"devicesGridPaginationPrev"));
				break;
			case "LAST":
				Assert.assertTrue(
						"Required data is not present. Last button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"devicesGridPaginationLast")));
				BaseUtil.click(getProps().getProperty(
						"devicesGridPaginationLast"));
				break;
			case "FIRST":
				Assert.assertTrue(
						"Required data is not present. First button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"devicesGridPaginationFirst")));
				BaseUtil.click(getProps().getProperty(
						"devicesGridPaginationFirst"));
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
			BaseUtil.waitForSpinner(2000);
		}
	}

	public void verifyEndpointsDevicesAccordionPagination(String task) {
		String totalPages = null;
		String currentPage = null;
		try {
			switch (task.toUpperCase()) {
			case "NEXT":
				String pageNumber = BaseUtil
						.getElementAttribute(
								getProps()
										.getProperty(
												"endpointDevicesAccordionPaginationCurrentPage"),
								"value");
				Assert.assertTrue("Next page is not displayed",
						Integer.parseInt(pageNumber) > 1);
				break;
			case "PREVIOUS":
				totalPages = BaseUtil.getElementText(getProps().getProperty(
						"endpointDevicesAccordionPaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1)
						.trim();
				currentPage = BaseUtil
						.getElementAttribute(
								getProps()
										.getProperty(
												"endpointDevicesAccordionPaginationCurrentPage"),
								"value");
				Assert.assertTrue("Previous page is not displayed", Integer
						.parseInt(currentPage) < Integer.parseInt(totalPages));
				break;
			case "LAST":
				totalPages = BaseUtil.getElementText(getProps().getProperty(
						"endpointDevicesAccordionPaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1)
						.trim();
				currentPage = BaseUtil
						.getElementAttribute(
								getProps()
										.getProperty(
												"endpointDevicesAccordionPaginationCurrentPage"),
								"value");
				Assert.assertTrue(
						"Last page is not displayed",
						(Integer.parseInt(totalPages) == Integer
								.parseInt(currentPage)
								&& !BaseUtil.verifyElementEnabled(getProps()
										.getProperty(
												"devicesGridPaginationNext")) && !BaseUtil
								.verifyElementEnabled(getProps().getProperty(
										"devicesGridPaginationLast"))));
				break;
			case "FIRST":
				currentPage = BaseUtil
						.getElementAttribute(
								getProps()
										.getProperty(
												"endpointDevicesAccordionPaginationCurrentPage"),
								"value");
				Assert.assertTrue(
						"First page is not displayed",
						(Integer.parseInt(currentPage) == 1
								&& !BaseUtil.verifyElementEnabled(getProps()
										.getProperty(
												"devicesGridPaginationFirst")) && !BaseUtil
								.verifyElementEnabled(getProps().getProperty(
										"devicesGridPaginationPrev"))));
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

	public boolean verifyTotalNumberOfRecords() {
		Boolean flag = false;
		try {
			String totalItemsText = null;
			int initialTotalItemsCount = 0;
			totalItemsText = BaseUtil.getElementText(getProps().getProperty(
					"endpointDevicesAccordionTotalItemsCount"));
			initialTotalItemsCount = Integer.parseInt(totalItemsText.substring(
					totalItemsText.indexOf("of") + 2,
					totalItemsText.indexOf("items")).trim());
			flag = initialTotalItemsCount > 0 ? true : false;
		} catch (Exception e) {
			logger.error("Some error occured while verifying total no of records : "
					+ e.getMessage());
			Assert.fail(e.getMessage());
		}
		return flag;

	}

	public void verifySorting_AllAccordion(String sortingType, String colName) {
		List<String> listColumnValues = null;
		try {
			switch (colName.toUpperCase()) {
			case "PROPERTY":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"auditGridPropertyColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"auditGridPropertyColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("auditGridPropertyColumn"));
				break;
			case "ACTION":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"auditGridActionColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"auditGridActionColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("auditGridActionColumn"));
				break;
			case "ORIGINAL VALUE":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"auditGridOriginalValueColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"auditGridOriginalValueColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("auditGridOriginalValueColumn"));
				break;
			case "UPDATED VALUE":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"auditGridUpdatedValueColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"auditGridUpdatedValueColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("auditGridUpdatedValueColumn"));
				break;
			case "USER":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps()
							.getProperty("auditGridUserColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps()
							.getProperty("auditGridUserColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("auditGridUserColumn"));
				break;
			case "DATE STAMP":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"auditGridDateStampColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"auditGridDateStampColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("auditGridDateStampColumn"));
				break;
			case "TIMESTAMP":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"historyGridTimestampColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"historyGridTimestampColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("historyGridTimestampColumn"));
				break;
			case "LEVEL":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"historyGridLevelColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"historyGridLevelColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("historyGridLevelColumn"));
				break;
			case "MODULE":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"historyGridModuleColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"historyGridModuleColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("historyGridModuleColumn"));
				break;
			case "MESSAGE":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"historyGridMessageColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"historyGridMessageColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("historyGridMessageColumn"));
				break;
			case "DEVICE ID":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"deviceGridDeviceIdColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"deviceGridDeviceIdColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("deviceGridDeviceIdColumn"));
				break;
			case "DEVICE TYPE":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"deviceGridDeviceTypeColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"deviceGridDeviceTypeColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("deviceGridDeviceTypeColumn"));
				break;
			case "NAME":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"deviceGridNameColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"deviceGridNameColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("deviceGridNameColumn"));
				break;
			case "ADDRESS":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"deviceGridAddressColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"deviceGridAddressColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("deviceGridAddressColumn"));
				break;
			case "CHANNEL COUNT":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"deviceGridChannelCountColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"deviceGridChannelCountColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("deviceGridChannelCountColumn"));
				break;
			case "ID":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"PeripheralsGridIdColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"PeripheralsGridIdColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("PeripheralsGridIdColumn"));
				break;
			case "GPEC PERIPHERAL ID":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"PeripheralsGridGpecPeripheralIdColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"PeripheralsGridGpecPeripheralIdColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"PeripheralsGridGpecPeripheralIdColumn"));
				break;
			case "PERIPHERAL NAME":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"PeripheralsGridNameColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"PeripheralsGridNameColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("PeripheralsGridNameColumn"));
				break;
			case "PERIPHERAL ADDRESS":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"PeripheralsGridAddressColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"PeripheralsGridAddressColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("PeripheralsGridAddressColumn"));
				break;
			case "TYPE":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"PeripheralsGridTypeColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"PeripheralsGridTypeColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("PeripheralsGridTypeColumn"));
				break;
			case "FIRMWARE VERSION":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"PeripheralsGridFirmwareVersionColumn"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"PeripheralsGridFirmwareVersionColumn"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"PeripheralsGridFirmwareVersionColumn"));
				break;
			default:
				logger.error("Switch Case["
						+ colName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid column name: "
						+ colName
						+ ", Please pass valid column name e.g.[Property,Action] from feature file or test class.");
			}

			Assert.assertTrue(colName + " is not in " + sortingType + " order",
					BaseUtil.isSorted(sortingType, listColumnValues));

		} catch (Exception e) {
			logger.error("Failed to sort the column :" + colName
					+ " Sorting order:" + sortingType + " , detail message: "
					+ e.getStackTrace().toString());
			Assert.fail(e.getStackTrace().toString());
		}
	}

	public boolean verifyLogsOfEndpoint(String typeOfLog, String updatedItem) {
		boolean bResult = false;
		List<WebElement> lstAuditAccordionUpdatedvalue = null;
		logger.debug("Selecting the endpoint :" + updatedItem);
		try {
			switch (typeOfLog.toUpperCase()) {
			case "NEW":
				lstAuditAccordionUpdatedvalue = BaseUtil
						.getMultipleElementsAfterLoaded(getProps().getProperty(
								"editEndpointAuditAccordionUpdatedValueColumn"));
				for (WebElement webElement : lstAuditAccordionUpdatedvalue) {
					if (webElement.getText().trim()
							.equalsIgnoreCase(updatedItem)) {
						return true;
					}
				}
				if (!bResult) {
					Assert.fail("Given test new endpoint's log is not available in ");
				}
				logger.debug("endpoint :" + updatedItem + " is selected.");
			case "UPDATED":
				lstAuditAccordionUpdatedvalue = BaseUtil
						.getMultipleElementsAfterLoaded(getProps().getProperty(
								"editEndpointAuditAccordionUpdatedValueColumn"));
				for (WebElement webElement : lstAuditAccordionUpdatedvalue) {
					if (webElement.getText().trim()
							.equalsIgnoreCase(updatedItem)) {
						return true;
					}
				}
				if (!bResult) {
					Assert.fail("Given test updated endpoint's log is not available in ");
				}
				logger.debug("endpoint :" + updatedItem + " is selected.");
			default:
				logger.error("Switch Case["
						+ typeOfLog
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("failed to verify the logs of endpoint in Audit accordion");
			}
		} catch (Exception e) {
			logger.error("Failed to select endpoint: " + updatedItem
					+ " from endpoint grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the endpoint : " + updatedItem
					+ ", see detail error message: \n"
					+ e.getStackTrace().toString());
		} finally {
			BaseUtil.waitForSpinner(2000);
		}
		return false;
	}

	public void verifyEndpointsAuditAccordionPagination(String task) {
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

	public void createTestEndpointFirmware(String firmwareName) {
		String firmwareFile = "live_firmware.tar.gz";
		File f = new File(firmwareFile);
		String absolutePath = f.getAbsolutePath();
		String filePath = absolutePath.substring(0,
				absolutePath.lastIndexOf(File.separator))
				+ "\\downloadDir\\" + firmwareFile;
		BaseUtil.clickAndWait(getProps().getProperty("FirmwareTab"));
		BaseUtil.clickAndWait(getProps().getProperty("endpointFirmwareTab"));
		BaseUtil.clickAndWait(getProps().getProperty(
				"addEndpointFirmwareButton"));
		BaseUtil.uploadFile(getProps().getProperty("browseButton"), filePath);
		BaseUtil.explicitWait(2000);
		BaseUtil.enterText(
				getProps().getProperty("addEndpointFirmwareNameField"),
				getProps().getProperty("endpointFirmwareNameTestData"));
		BaseUtil.enterText(
				getProps().getProperty("addEndpointFirmwareVersionField"),
				getProps().getProperty("endpointFirmwareVersionTestData"));
		BaseUtil.selectDropDownByValue(
				getProps().getProperty("addEndpointFirmwareEndpointTypeField"),
				getProps().getProperty("endpointFirmwareEndpointTypeTestData"));
		BaseUtil.clickAndWait(getProps().getProperty(
				"addEndpointFirmwareSaveButton"));
		BaseUtil.waitForSpinner();
		BaseUtil.explicitWait(2000);
		BaseUtil.clickAndWait(getProps().getProperty("endpointTab"));
		BaseUtil.waitForSpinner();
	}

	public boolean checkRealTimeDataTabMessage() {
		BaseUtil.switchToNewBrowserTab(0);
		try {
			return BaseUtil
					.getElementText(
							getProps().getProperty("realTimeDataBrowserTab"))
					.equalsIgnoreCase(
							"Error connecting to on-site controller. Please try again later.");
		} catch (Exception e) {
			return false;
		}
	}

	public boolean checkPresentRealTimeData(String endpointName)
			throws SQLException, IOException {
		// switchToNewBrowserTab(0);
		boolean result1 = BaseUtil.getElementText(
				getProps().getProperty("mainTitle")).equalsIgnoreCase(
				"Real Time Data" + " :: " + endpointName);
		System.out.println("Boolean 1" + result1);
		String query = "getEndpointReferenceId=SELECT reference_id FROM endpoint WHERE serial='"
				+ endpointName + "'";
		boolean result2 = BaseUtil.getElementText(
				getProps().getProperty("tableTitle")).equalsIgnoreCase(
				"Endpoint Id " + getEndpointReferenceId(query));
		System.out.println("Boolean 2" + result2);
		boolean result3 = BaseUtil.verifyElementDisplayed(getProps()
				.getProperty("realTimeDataTable"));
		System.out.println("Boolean 3" + result3);
		return (result1 && result2 && result3) ? true : false;
	}

	public boolean closeModel() {
		boolean result;
		try {
			BaseUtil.clickAndWait(getProps().getProperty(
					"editEndpointCloseIcon"));
			result = true;
		} catch (Exception e) {
			result = false;
			Assert.fail("Model is not closed");
		}
		return result;
	}

	public String getEndpointId(String query) throws SQLException, IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Connection conn = getConnection();
		String endpointId = null;
		try {
			endpointId = dbutil.getIdFromDB(query, conn);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return endpointId;
	}

	private String getEndpointReferenceId(String query) throws SQLException,
			IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Connection conn = getConnection();
		String getEndpointReferenceId = null;
		try {
			getEndpointReferenceId = dbutil.getIdFromDB(query, conn);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return getEndpointReferenceId;
	}

	public void selectEndpointFromGrid_EndpointPage_ec1k(String sEndpointName) {
		boolean bResult = false;
		logger.debug("Selecting the endpoint :" + sEndpointName);
		try {
			List<WebElement> lstEndpointNames = BaseUtil
					.getMultipleElementsAfterLoaded(getProps().getProperty(
							"editSiteEndpointGridSerialColumn_ec1k").replace(
							"#index#", sEndpointName));
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

	public boolean verifyEnableButton(String list) {
		try {
			switch (list) {
			case "Enable Connections":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"enableConnections"));
			case "Replace Controller":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"replaceController"));
			case "SUSPEND CONNNECTIONS":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"suspendConnection"));
			}

		} catch (Exception e) {
			logger.error("Failed to verify  " + list
					+ " see detail error message \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to verify [" + list
					+ "] in endpoint page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
		return false;
	}

	public boolean verifyControllerConnection(String status,
			String connectionStatus) {
		try {
			switch (status.toUpperCase()) {

			case "STATUS":
				return BaseUtil
						.getElementText(
								getProps().getProperty(
										"connectionStatusConnected")).trim()
						.equalsIgnoreCase(connectionStatus);
				// case "CONNECTED (SUSPENDED)":
				// return
				// BaseUtil.getElementText(getProps().getProperty("connectionStatusConnectedSuspended")).trim()
				// .equalsIgnoreCase(connectionStatus);
				// case "DISCONNECTED (SUSPENDED)":
				// return
				// BaseUtil.getElementText(getProps().getProperty("connectionStatusDisConnectedSuspended")).trim()
				// .equalsIgnoreCase(connectionStatus);
				// case "DISCONNECTED":
				// return
				// BaseUtil.getElementText(getProps().getProperty("connectionStatusDisConnected")).trim()
				// .equalsIgnoreCase(connectionStatus);
			default:
				logger.error("Switch Case["
						+ connectionStatus
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Switch Case[" + connectionStatus
					+ "] is not matched in class[" + getClass().getName()
					+ "] , Method["
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ "]");
			return false;
		}
	}

	private Connection getConnection() throws SQLException {
		GPConnection gpcon = new GPConnection();
		return gpcon.getConnection(TestBase.getDbUrl(),
				TestBase.getDbUsername(), TestBase.getDbPassword());
	}

	public boolean verifyTextOnJavaScriptPopup(String confirmationboxMessage) {
		boolean flag = false;
		try {
			BaseUtil.explicitWait(5000);
			WebDriverWait wait = new WebDriverWait(getDriver(), 10);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			String alertText = alert.getText().trim();
			if (alertText.equalsIgnoreCase(confirmationboxMessage)) {
				flag = true;
				logger.info("The Text shown on Java Script popup is :"
						+ alertText + " , on Java Script Popup.");
			}
		} catch (Exception e) {
			logger.error("Failed to verify the text :" + confirmationboxMessage
					+ " , on Java Script Popup: " + e.getMessage());
			return flag;
		}
		return flag;
	}

	public void verifySorting_EndpointPage(String sSortingType,
			String endpointHeaderheader) {
		List<String> listColumnValues = null;
		try {
			switch (endpointHeaderheader.toUpperCase()) {
			case "ENDPOINT ID":
				if (sSortingType.equals("Descending")) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"endpointPageEndpointIdHeader"));

					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"endpointPageEndpointIdHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("endpointPageEndpointIdHeader"));
				break;
			case "CONNECTION STATUS":
				if (sSortingType.equals("Descending")) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"endpointPageConnectionStatusHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"endpointPageConnectionStatusHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"endpointPageConnectionStatusHeader"));
				break;
			case "TYPE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"endpointPageTypeHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"endpointPageTypeHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("endpointPageTypeHeader"));
				break;
			case "SERIAL":
				if (sSortingType.equals("Descending")) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"endpointPageSerialHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"endpointPageSerialHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("endpointPageSerialHeader"));
				break;
			case "REFERENCE ID":
				if (sSortingType.equals("Descending")) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"endpointPageReferenceIDHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"endpointPageReferenceIDHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("endpointPageReferenceIDHeader"));
				break;
			case "LIVE FIRMWARE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"endpointPageLiveFirmwareHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"endpointPageLiveFirmwareHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("endpointPageLiveFirmwareHeader"));
				break;
			case "LAST MESSAGE RECEIVED (UTC)":
				if (sSortingType.equals("Descending")) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"endpointPageLastMessageRecievedUTCHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"endpointPageLastMessageRecievedUTCHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"endpointPageLastMessageRecievedUTCHeader"));
				break;
			default:
				logger.error("Switch Case["
						+ endpointHeaderheader
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid column name: "
						+ endpointHeaderheader
						+ ", Please pass valid column name e.g.[Name,Hostname, parent] from feature file or test class.");
			}

			Assert.assertTrue(endpointHeaderheader + " is not in "
					+ sSortingType + " order",
					BaseUtil.isSorted(sSortingType, listColumnValues));

		} catch (Exception e) {
			logger.error("Failed to sort the column :" + endpointHeaderheader
					+ " Sorting order:" + sSortingType + " , detail message: "
					+ e.getStackTrace().toString());
			Assert.fail(e.getStackTrace().toString());
		}
	}

	public boolean isElementDisplayedOnEndpointType(String sField) {
		try {
			switch (sField.toUpperCase()) {
			case "BLANK":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addEndpointPopupTypeOption1Field"));
			case "EC100":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addEndpointPopupTypeOption2Field"));
			case "EC1000":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addEndpointPopupTypeOption3Field"));
			case "TRIDIUM ADAPTER":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addEndpointPopupTypeOption4Field"));
			default:
				logger.error("Switch Case["
						+ sField
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the element :" + sField
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public void isEndpointTabAvailable(String userName) {
		BaseUtil.enterText(getProps().getProperty("adminUserName_Locator"),
				userName);
		BaseUtil.enterText(getProps().getProperty("adminUserPassword_Locator"),
				PathConstants.DEFAULT_USER_PASSWORD);
		BaseUtil.click(getProps().getProperty("adminLoginbutton_Locator"));
		BaseUtil.waitForSpinner();
		BaseUtil.click(getProps().getProperty("endpointTab"));
		if (userName.equals("endpointPage_GPSupportUserBot")) {
			Assert.assertTrue(
					" Expected : Endpoint Tab should be Available. Actual : Endpoint tab is not available for user ["
							+ userName + "] ",
					BaseUtil.isElementClickable(getProps().getProperty(
							"endpointTab")));
		}
	}

	public void checkAlert() {
		try {

			if (BaseUtil.isAlertPresent()) {
				BaseUtil.moveToAlert().accept();
			}
			// WebDriverWait wait = new WebDriverWait(getDriver(),
			// 3);
			// wait.until(ExpectedConditions.alertIsPresent());
			// Alert alert = getDriver().switchTo().alert();
			// BaseUtil.explicitWait(3000);
			// alert.accept();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verifyTextOnJavaScriptPopupForUncommissionedEndpoint(
			String confirmationboxMessage, String endpointName) {
		boolean flag = false;
		try {
			BaseUtil.waitForSpinner();
			WebDriverWait wait = new WebDriverWait(getDriver(), 10);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			String alertText = alert.getText();
			String query = "SELECT endpoint_id FROM endpoint WHERE serial='"
					+ endpointName + "'";
			confirmationboxMessage = confirmationboxMessage.replace("########",
					getEndpointId(query));
			BaseUtil.waitForSpinner();

			if (alertText.equals(confirmationboxMessage)) {
				flag = true;
				logger.info("The Text shown on Java Script popup is :"
						+ alertText + " , on Java Script Popup.");
			}
		} catch (Exception e) {
			logger.error("Failed to verify the text :" + confirmationboxMessage
					+ " , on Java Script Popup: " + e.getMessage());
			return flag;
		}
		return flag;
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

	public void enterTestData(String siteNumber, String field) {
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
				if (siteNumber.equals("1")) {
					BaseUtil.enterText(
							getProps().getProperty("idAddSiteSiteName"),
							getProps().getProperty(
									"modifyEndpointAssociateSiteIdTestData"));
				} else if (siteNumber.equals("2")) {
					BaseUtil.enterText(
							getProps().getProperty("auditPageSiteNameField"),
							getProps().getProperty(
									"addSiteAdditionalNameTestData"));
				}
				break;
			case "SITE DESCRIPTION":
				if (siteNumber.equals("1")) {
					BaseUtil.enterText(
							getProps().getProperty("idAddSiteSiteDescription"),
							getProps().getProperty(
									"addSiteAdditionalNameTestData"));
				} else if (siteNumber.equals("2")) {
					BaseUtil.enterText(
							getProps().getProperty(
									"auditPageSiteDescriptionField"),
							getProps().getProperty(
									"addSiteAdditionalDescriptionTestData"));
				}
				break;
			case "ADDRESS1":
				BaseUtil.enterText(
						getProps().getProperty("auditPageSiteAddress1Field"),
						getProps().getProperty("addSiteAddress1TestData"));
				break;
			case "CITY":
				BaseUtil.enterText(
						getProps().getProperty("auditPageSiteCityField"),
						getProps().getProperty("addSiteCityTestData"));
				break;
			case "STATE":
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("auditPageSiteProvinceField"),
						getProps().getProperty("addSiteProvinceTestData"));
				break;
			case "POSTAL CODE":
				BaseUtil.enterText(
						getProps().getProperty("auditPageSitePostalCodeField"),
						getProps().getProperty("addSitePostalCodeTestData"));
				break;
			// case "COUNTRY":
			// selectDropDownByValue(getProps().getProperty("countrydropdown"),
			// getProps().getProperty("addSiteCountryDropDownTestData"));
			// break;
			case "PHONE 1":
				BaseUtil.enterText(
						getProps().getProperty("auditPageSitePhone1Field"),
						getProps().getProperty("addSitePhone1FieldTestData"));
				break;
			case "PHONE 2":
				BaseUtil.enterText(
						getProps().getProperty("auditPageSitePhone2Field"),
						getProps().getProperty("addSitePhone2FieldTestData"));
				break;
			case "PATHS":
				BaseUtil.enterText(
						getProps().getProperty("auditPageSitePathsField"),
						getProps().getProperty("addSitePathsFieldTestData"));
				break;
			case "NAME":
				BaseUtil.enterText(
						getProps().getProperty("addTenantPopupNameField"),
						getProps().getProperty("addSiteTenantDropDownTestData"));
				break;
			case "TENANT PARENT":
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("addTenantPopupParentField"),
						getProps().getProperty("auditTenantParentTestData"));
				break;
			}
		} catch (Exception e) {
			logger.error(message + " , detail message: " + e.getMessage());
			Assert.fail(message);
		}
	}

}