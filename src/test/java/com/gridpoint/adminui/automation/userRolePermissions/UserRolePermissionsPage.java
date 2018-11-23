package com.gridpoint.adminui.automation.userRolePermissions;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.gridpoint.adminui.automation.audit.AuditPage;
import com.gridpoint.adminui.automation.tenant.TenantPage;
import com.gridpoint.adminui.automation.testdata.ManageTestData;
import com.gridpoint.automation.constants.PathConstants;
import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.util.BaseUtil;

public class UserRolePermissionsPage extends CommonInit {
	TenantPage tp;

	private static Logger logger = Logger.getLogger(AuditPage.class);

	// TODO : updated by k on 12 may 2018 2:58 AM....verify
//	private static final List<String> sitelstDataGPEC = Arrays.asList("qak4",
//			"devMahesh", "devtest2k1", "devk1", "devk2", "devk3", "devk4",
//			"devk5", "devk6", "devk10");
//	private static final List<String> sitelstDataADM = Arrays.asList("devarl1",
//			"devarl2", "devarl3", "devarl4", "devarl5", "devarl6", "devarl7",
//			"devarl8", "devarl9", "devarl10", "devk11");
	List<String> sitelstDataGPEC = Arrays.asList("devk2", "devk3", "devk4", "devk11");
	// above list further updated by k on 24 aug 2018 23:23 as Arrays.asList("devk3", "devk2");....verify
	List<String> sitelstDataADM = Arrays.asList("devarl3", "devarl10", "devk11");

	public static void isHomePageAvailable(String userName) {
		BaseUtil.explicitWait(3000);
		BaseUtil.enterText(getProps().getProperty("adminUserPassword_Locator"),
				userName);
		if (userName.equals("autoAdminTest")) {
			BaseUtil.enterText(
					getProps().getProperty("adminUserPassword_Locator"),
					PathConstants.GADMIN_USER_PASSWORD);
			BaseUtil.click(getProps().getProperty("adminLoginbutton_Locator"));
			BaseUtil.waitForSpinner();
		} else if (userName.equals("autoSupportTest")) {
			BaseUtil.enterText(
					getProps().getProperty("adminUserPassword_Locator"),
					PathConstants.DEFAULT_USER_PASSWORD);
			BaseUtil.click(getProps().getProperty("adminLoginbutton_Locator"));
			BaseUtil.waitForSpinner();

		} else if (userName.equals("URP_CustomerSiteManager_testBot")) {
			BaseUtil.enterText(
					getProps().getProperty("adminUserPassword_Locator"),
					PathConstants.DEFAULT_USER_PASSWORD);
			BaseUtil.click(getProps().getProperty("adminLoginbutton_Locator"));
			BaseUtil.waitForSpinner();
		}
		
//		 TODO : Added by sonu on 11 sep 2018.... curretly commenting it to avoid compilation issues....verify
//		else if (userName.equals("testUser_1")) {
//			Assert.assertFalse(" Expected : User Tab should be Available. Actual : User tab is not available for user ["
//					+ userName + "] ",
//					isElementClickable(getLocator(userRolePermissionsPageObjectRepo.get("UserTab"))));
//		}else if (userName.equals("testUser_2")) {
//			Assert.assertFalse(" Expected : User Tab should be Available. Actual : User tab is not available for user ["
//					+ userName + "] ",
//					isElementClickable(getLocator(userRolePermissionsPageObjectRepo.get("UserTab"))));
//		} else if (userName.equals("testUser_3")) {
//			Assert.assertFalse(" Expected : User Tab should be Available. Actual : User tab is not available for user ["
//					+ userName + "] ",
//					isElementClickable(getLocator(userRolePermissionsPageObjectRepo.get("UserTab"))));
//		}
///////////////////////////////////////
		
		// else if (userName.equals("gpadmin")) {
		// enterText(getProps().getProperty("adminUserPassword_Locator"),
		// PathConstants.GADMIN_USER_PASSWORD);
		// click(getProps().getProperty("adminLoginbutton_Locator"));
		// BaseUtil.waitForSpinner();
		// } else if (userName.equals("URP_GPAdmin_testBot")) {
		// enterText(getProps().getProperty("adminUserPassword_Locator"),
		// PathConstants.DEFAULT_USER_PASSWORD);
		// click(getProps().getProperty("adminLoginbutton_Locator"));
		// BaseUtil.waitForSpinner();
		//
		// } else if (userName.equals("userRGPSuppoerBot1")) {
		// Assert.assertTrue(" Expected : User Tab should be Available. Actual : User tab is not available for user ["
		// + userName + "] ",
		// isElementClickable(getLocator(getProps().getProperty("UserTab"))));
		// } else if (userName.equals("botRoleCustomerAnalyst")) {
		// Assert.assertTrue(
		// " Expected : Dashboard Tab should be Available. Actual : Dashboard tab is not available for user ["
		// + userName + "] ",
		// !isElementClickable(getLocator(getProps().getProperty("DashboardTab"))));
		// } else if (userName.equals("botRoleCustomerSiteManager")) {
		// Assert.assertFalse(" Expected : Home Tab should be Available. Actual : Home tab is not available for user ["
		// + userName + "] ",
		// !isElementClickable(getLocator(getProps().getProperty("homeTab"))));
		// } else {
		// enterText(getProps().getProperty("adminUserPassword_Locator"),
		// PathConstants.DEFAULT_USER_PASSWORD);
		// click(getProps().getProperty("adminLoginbutton_Locator"));
		// BaseUtil.waitForSpinner();
		// }
	}

	public static boolean clickOperationUserRolePermissions(String button) {
		String locator = null, flag = null;
		try {
			switch (button.toUpperCase()) {
			case "USERS":
				flag = "Tab";
				locator = getProps().getProperty("usersTab");
				break;
			case "SITES":
				flag = "Tab";
				locator = getProps().getProperty("sitesTab");
				break;
			case "ENDPOINTS":
				flag = "Tab";
				locator = getProps().getProperty("endpointsTab");
				break;
			case "FIRMWARE":
				flag = "Tab";
				locator = getProps().getProperty("firmwareTab");
				break;
			case "ENDPOINT FIRMWARE":
				flag = "Tab";
				locator = getProps().getProperty("endpointFirmwareTab");
				break;
			case "PERIPHERAL FIRMWARE":
				flag = "Tab";
				locator = getProps().getProperty("peripheralFirmwareTab");
				break;
			case "CONTROL":
				flag = "Tab";
				locator = getProps().getProperty("controlsTab");
				break;
			case "HVAC":
				flag = "Tab";
				locator = getProps().getProperty("hvacTab");
				break;
			case "LIGHTING/LOADS":
				flag = "Tab";
				locator = getProps().getProperty("tabLighting");
				break;
			case "DATA":
				flag = "Tab";
				locator = getProps().getProperty("dataTab");
				
				// in k branch the code is updated on 12 may 2018 2:58 AM and lokks like below...commenting to avoid compilation issues....verify
//				instantClick(userRolePermissionsPageObjectRepo.get("dataTab"));
//				waitForSpinner();
//				explicitWait(10000);
//				instantClick(userPageObjectRepo.get("graphsTab"));
//				waitForSpinner();
//				explicitWait(5000);
//				isElementClickable(getLocator(userPageObjectRepo.get("selectGraphsButton")));
//				waitForSpinner();
//				explicitWait(5000);
				break;
			case "REPORTS":
				flag = "Tab";
				locator = getProps().getProperty("reportSubTab");
				break;
			case "EDIT HVAC:CANCEL":
				BaseUtil.instantClick(getProps()
						.getProperty("editHVACCanceltn"));
				BaseUtil.waitForSpinner();
				return true;
			case "CROSS ICON":
				BaseUtil.click(getProps().getProperty("siteCrossIcon"));
				BaseUtil.explicitWait(1000);
				return true;
			case "SELECT ALL":
				BaseUtil.click(getProps().getProperty("siteTreeSelectAllbtn"));
				BaseUtil.waitForSpinner();
				return true;

			case "CAPABILITIES:SAVE AND CLOSE":
				flag = "Button";
				locator = getProps().getProperty(
						"saveAndCloseCapabilitiesUserBtn");
				break;
			case "CLEAR":
				flag = "Button";
				locator = getProps().getProperty("siteTreeClearbtn");
				break;
			case "SELECT ALL:DEVICES":
				flag = "Button";
				locator = getProps().getProperty("deviceSelectAllbtn");
				break;
			case "PORTAL:SITE SEARCH":
				BaseUtil.instantClick(getProps().getProperty(
						"siteTreeSearchBox"));
				BaseUtil.explicitWait(2000);
				return true;
			case "EDIT REGULAR HOURS BUTTON":
				BaseUtil.instantClick(getProps().getProperty(
						"editRegularHoursbtn"));
				BaseUtil.waitForSpinner();
				return true;
			case "REGULAR HOURS CANCEL BUTTON":
				BaseUtil.instantClick(getProps().getProperty(
						"editRegularHoursCancelbtn"));
				BaseUtil.waitForSpinner();
				return true;
			case "EDIT CUSTOM HOURS BUTTON":
				BaseUtil.instantClick(getProps().getProperty(
						"editCustomHoursbtn"));
				BaseUtil.waitForSpinner();
				return true;
			case "CUSTOM HOURS CANCEL BUTTON":
				BaseUtil.instantClick(getProps().getProperty(
						"editCustomHoursCancelbtn"));
				BaseUtil.waitForSpinner();
				return true;
			case "CUSTOM HOURS TAB":
				BaseUtil.instantClick(getProps().getProperty(
						"editCustomHoursTab"));
				return true;

				// Users
			case "CAPABILITIES":
				flag = "Accordion";
				locator = getProps().getProperty("userCapabilitiesAccordion");
				break;
			case "USER:SAVE AND CLOSE":
				flag = "Button";
				locator = getProps().getProperty("editUsersaveAndCloseUserBtn");
				break;
			case "ADD USER":
				flag = "Button";
				locator = getProps().getProperty("addUserButton");
				break;
			case "USER:SAVE":
				flag = "Button";
				locator = getProps().getProperty("userSaveButton");
				break;
			case "PERMISSION":
				flag = "Accordion";
				locator = getProps().getProperty(
						"editUserModelPermissionAccordion");
				break;
			case "PERMISSION:SAVE AND CLOSE":
				flag = "Button";
				locator = getProps().getProperty(
						"saveAndClosePermissionUserBtn");
				break;

			// Sites
			case "DELETE SITE":
				flag = "Button";
				locator = getProps().getProperty("editSiteDeleteButton");
				break;
			case "SITE:DELETE_OK:":
				flag = "Button";
				locator = getProps().getProperty(
						"deleteSiteOkConfirmationButton");
				break;
			case "ADD SITE":
				BaseUtil.instantClick(getProps().getProperty("addSiteButton"));
				BaseUtil.waitForSpinner();
				return true;
			case "SITE:SAVE":
				BaseUtil.explicitWait(2000);
				BaseUtil.instantClick(getProps().getProperty(
						"addSiteSaveButton"));
				BaseUtil.waitForSpinner();
				return true;
			case "ACCEPT VERIFIED DATA":
				flag = "Button";
				locator = getProps().getProperty("addSiteAddVerificationb");
				break;
			case "SITE:PATHS":
				flag = "Accordion";
				locator = getProps().getProperty(
						"editSitePagePopupPathsAccordion");
				break;
			case "SITE PATH":
				Assert.assertTrue(
						"Site Path text box is not clickable",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"addSiteAddPathsButton")));
				BaseUtil.instantClick(getProps().getProperty(
						"editSitePagePathsAccordianPathTextField"));
				return true;
			case "SITE:SAVE AND CLOSE":
				flag = "Button";
				locator = getProps().getProperty(
						"editSitePageSaveAndCloseSiteButton");
				break;
			case "DETAILS":
				flag = "Accordion";
				locator = getProps().getProperty(
						"editSitePagePoupDetailsAccordion");
				break;

			// VirtualChannel
			case "CHANNELS":
				flag = "Accordion";
				locator = getProps().getProperty(
						"editSitePagePopupChannelAccordion");
				break;
			case "ADD VIRTUAL CHANNEL":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSitePageAddVirtualChannels"));
				BaseUtil.instantClick(getProps().getProperty(
						"editSitePageAddVirtualChannels"));
				BaseUtil.waitForSpinner();
				return true;
			case "ADD":
				BaseUtil.explicitWait(1000);
				List<WebElement> allLinks = getDriver().findElements(
						BaseUtil.getLocator(getProps().getProperty(
								"addVirtualChanne1")));
				for (int i = 0; i < 2; i++) {
					allLinks.get(i).click();
				}
				return true;
			case "VIRTUAL CHANNEL:SAVE":
				flag = "Button";
				locator = getProps().getProperty("editVirtualChannelBtn");
				break;
			case "VIRTUAL CHANNEL:CLOSE":
				flag = "Button";
				locator = getProps().getProperty(
						"addVirtualChannelErrorMessageCloseButton");
				break;
			case "X VIRTUAL CHANNEL":
				flag = "Button";
				locator = getProps().getProperty(
						"addVirtualChannelWindowCloseButton");
				break;
			case "VIRTUAL CHANNEL:EDIT":
				flag = "Button";
				locator = getProps().getProperty("editVirtualChannelBtn");
				break;
			case "VIRTUAL CHANNEL:UPDATE":
				flag = "Button";
				locator = getProps().getProperty("updateVirtualChannelBtn");
				break;
			case "VIRTUAL CHANNEL:VIEW":
				locator = getProps().getProperty("viewVirtualChannelBtn");
				BaseUtil.waitForSpinner();
				return true;
			case "VIRTUAL CHANNEL:REMOVE":
				if (BaseUtil.instantElementCheck("Display", getProps()
						.getProperty("removeVirtualChannelRemoveButton"))) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"removeVirtualChannelRemoveButton"));
					return true;
				}
				return true;
			case "VIRTUAL CHANNEL:OK":
				if (BaseUtil.instantElementCheck("Display", getProps()
						.getProperty("removeVirtualChannelOkButton"))) {
					BaseUtil.clickAndWait(getProps().getProperty(
							"removeVirtualChannelOkButton"));
					BaseUtil.explicitWait(3000);
					return true;
				}
				BaseUtil.explicitWait(2000);
				BaseUtil.clickAndWait(getProps().getProperty(
						"editSitePagePopupChannelAccordion"));
				return true;

				// Tenants
			case "TENANT":
				flag = "Tab";
				locator = getProps().getProperty("tenantsTab");
				break;
			case "ADD TENANT":
				flag = "Button";
				locator = getProps().getProperty("addTenantButton");
				break;
			case "DELETE TENANT":
				flag = "Button";
				locator = getProps().getProperty("deleteTenantButton");
				break;
			case "TENANT:DELETE_OK":
				flag = "Button";
				locator = getProps().getProperty(
						"deleteTenantOkConfirmationButton");
				break;
			case "TENANT:SAVE":
				flag = "Button";
				locator = getProps().getProperty("addTenantPopupSaveButton");
				break;

			// Endpoints
			case "ADD ENDPOINT":
				flag = "Button";
				locator = getProps().getProperty("addEndpointButton");
				break;
			case "ENDPOINT:SAVE":
				flag = "Button";
				locator = getProps().getProperty("addEndpointSaveButton");
				break;
			case "ENDPOINT:SEARCH":
				flag = "Button";
				locator = getProps().getProperty("endpointTabSearchButton");
				break;
			case "ENDPOINT:SAVE AND CLOSE":
				flag = "Button";
				locator = getProps().getProperty(
						"editEndpointSaveAndCloseButton");
				break;

			// Endpoint Firmware
			case "ADD ENDPOINT FIRMWARE":
				flag = "Button";
				locator = getProps().getProperty("addEndpointFirmwareButton");
				break;
			case "DELETE ENDPOINT FIRMWARE":
				flag = "Button";
				locator = getProps()
						.getProperty("deleteEndpointFirmwareButton");
				break;
			case "ENDPOINT FIRMWARE:DELETE_OK":
				flag = "Button";
				locator = getProps().getProperty(
						"DeleteEndpointFirmwareOkConfirmationButton");
				break;
			case "SAVE ENDPOINT FIRMWARE":
				flag = "Button";
				locator = getProps().getProperty(
						"addEndpointFirmwareSaveButton");
				break;
			case "ENDPOINT FIRMWARE:SEARCH":
				flag = "Button";
				locator = getProps()
						.getProperty("endpointFirmwareSearchButton");
				break;
			case "EDIT ENDPOINT FIRMWARE:SAVE":
				flag = "Button";
				locator = getProps().getProperty(
						"editEndpointFirmwareSaveButton");
				break;
			case "UPDATE ENDPOINT FIRMWARE":
				flag = "Tab";
				locator = getProps().getProperty("updateEndpointFirmwareTab");
				break;
			case "UPDATE FIRMWARE":
				flag = "Button";
				locator = getProps().getProperty("updateFirmwareBtn");
				break;

			// Peripheral Firmware
			case "ADD PERIPHERAL FIRMWARE":
				flag = "Button";
				locator = getProps().getProperty("addPeripheralFirmwareButton");
				break;
			case "DELETE PERIPHERAL FIRMWARE":
				flag = "Button";
				locator = getProps().getProperty(
						"deletePeripheralFirmwareButton");
				break;
			case "PERIPHERAL FIRMWARE:DELETE_OK":
				flag = "Button";
				locator = getProps().getProperty(
						"DeletePeripheralFirmwareOkConfirmationButton");
				break;
			case "PERIPHERAL FIRMWARE:APPLY":
				locator = getProps().getProperty(
						"addPeripheralFirmwareApplyButton");
				break;
			case "PERIPHERAL FIRMWARE:SEARCH":
				flag = "Button";
				locator = getProps().getProperty(
						"peripheralFirmwareSearchButton");
				break;

			// case "USER:SEARCH":
			// BaseUtil.instantClick(getProps().getProperty("userSearchButton"));
			// BaseUtil.waitForSpinner();
			// return true;
			// case "SITE:CLOSE":
			// BaseUtil.instantClick(sitePageObjectRepo("editSitePagecloseBtn"));
			// explicitWait(1000);
			// return true;
			// case "PORTAL:SITE SEARCH":
			// BaseUtil.instantClick(getProps().getProperty("siteTreeSearchBox"));
			// explicitWait(1000);
			// return true;
			// case "VIRTUAL CHANNEL:EDIT":
			// BaseUtil.instantClick(sitePageObjectRepo("editVirtualChannelBtn"));
			// return true;
			// case "VIRTUAL CHANNEL:UPDATE":
			// BaseUtil.instantClick(sitePageObjectRepo("updateVirtualChannelBtn"));
			// BaseUtil.waitForSpinner();
			// return true;
			// case "VIRTUAL CHANNEL:VIEW":
			// BaseUtil.instantClick(sitePageObjectRepo("viewVirtualChannelBtn"));
			// BaseUtil.waitForSpinner();
			// return true;
			// case "VIRTUAL CHANNEL:REMOVE":
			// BaseUtil.instantClick(sitePageObjectRepo("removeVirtualChannelBtn"));
			// BaseUtil.waitForSpinner();
			// return true;
			// case "VIRTUAL CHANNEL:OK":
			// BaseUtil.instantClick(sitePageObjectRepo("removeVirtualChannelOKBtn"));
			// explicitWait(1000);
			// return true;
			// case "VIRTUAL CHANNEL:CANCEL":
			// BaseUtil.instantClick(sitePageObjectRepo("viewVirtualChannelCancelButton"));
			// explicitWait(1000);
			// return true;
			// case "EXPAND":
			// BaseUtil.waitForSpinner();
			// BaseUtil.instantClick(getProps().getProperty("siteTreeExpandIcon"));
			// return true;
			// case "HVAC SCOPE":
			// BaseUtil.instantClick(getProps().getProperty("HvacScopeBtn"));
			// BaseUtil.waitForSpinner();
			// return true;
			// case "SELECT ALL:DEVICES":
			// explicitWait(3000);
			// BaseUtil.instantClick(getProps().getProperty("deviceSelectAllbtn"));
			// BaseUtil.waitForSpinner();
			// return true;
			// case "REGULAR HOURS CHECK BOXES":
			// explicitWait(3000);
			// BaseUtil.instantClick(getProps().getProperty("regularHourscheckbox"));
			// return true;
			// case "REGULAR HOURS TEXT AREA":
			// BaseUtil.instantClick(getProps().getProperty("regularHourstextarea"));
			// enterText(getProps().getProperty("regularHourstextarea"),
			// "Automation test data");
			// BaseUtil.waitForSpinner();
			// return true;
			// case "CLEAR":
			// BaseUtil.waitForSpinner();
			// verifyElementEnabled(getProps().getProperty("siteTreeClearbtn"));
			// BaseUtil.instantClick(getProps().getProperty("siteTreeClearbtn"));
			// explicitWait(2000);
			// return true;
			// case "SITE:SEARCH":
			// explicitWait(1000);
			// clickAndWait(sitePageObjectRepo("siteSearchButton"));
			// BaseUtil.waitForSpinner();
			// return true;
			// case "ADD PATH":
			// BaseUtil.instantClick(sitePageObjectRepo("editSiteAddPathbtn"));
			// BaseUtil.waitForSpinner();
			// return true;
			// case "SITES":
			// BaseUtil.instantClick(sitePageObjectRepo("siteTab"));
			// BaseUtil.waitForSpinner();
			// return true;
			// case "PERMISSION":
			// BaseUtil.instantClick(getProps().getProperty("editUserModelPermissionAccordion"));
			// BaseUtil.waitForSpinner();
			// return true;
			// case "CAPABILITIES":
			// BaseUtil.instantClick(getProps().getProperty("editUserModelCapabilitiesAccordion"));
			// explicitWait(1000);
			// return true;
			// case "CHANNELS":
			// explicitWait(2000);
			// clickAndWait(sitePageObjectRepo("editSitePagePopupChannelAccordion"));
			// return true;
			// case "HIDE INACTIVE":
			// explicitWait(2000);
			// BaseUtil.instantClick(sitePageObjectRepo("hideInactiveCheckBox"));
			// return true;
			// case "OK":
			// // BaseUtil.waitForSpinner();
			// BaseUtil.instantClick(sitePageObjectRepo("addSiteOkConfirmationButton"));
			// return true;
			// case "DELETE SITE":
			// BaseUtil.waitForSpinner();
			// verifyElementDisplayed(sitePageObjectRepo("editSiteDeleteSiteButton"));
			// BaseUtil.instantClick(sitePageObjectRepo("editSiteDeleteSiteButton"));
			// return true;
			// case "BOTH":
			// BaseUtil.instantClick(getProps().getProperty("HvacScopeBothBtn"));
			// return true;
			// case "CAPABILITIES:SAVEBUTTON":
			// BaseUtil.instantClick(getProps().getProperty("saveCapabilitiesUserBtn"));
			// BaseUtil.waitForSpinner();
			// return true;
			// case "DATA":
			// BaseUtil.instantClick(getProps().getProperty("dataTab"));
			// isElementClickable(getLocator(getProps().getProperty("selectGraphsButton")));
			// BaseUtil.waitForSpinner();
			// explicitWait(10000);
			// return true;
			// case "REPORTS":
			// verifyElementEnabled(getProps().getProperty("reportSubTab"));
			// BaseUtil.instantClick(getProps().getProperty("reportSubTab"));
			// explicitWait(5000);
			// return true;
			// case "SOLAR TAB":
			// return
			// verifyElementDisplayed(getProps().getProperty("solarTab"));
			// case "CONTROL":
			// BaseUtil.instantClick(getProps().getProperty("controlTab"));
			// BaseUtil.waitForSpinner();
			// return true;
			// case "LOGOUT BUTTON":
			// BaseUtil.instantClick(getProps().getProperty("logoutButton"));
			// return true;
			// case "HVAC":
			// BaseUtil.instantClick(getProps().getProperty("tabHVAC"));
			// BaseUtil.waitForSpinner();
			// return true;
			// case "HOURS":
			// BaseUtil.instantClick(getProps().getProperty("hours"));
			// BaseUtil.waitForSpinner();
			// return true;
			// case "ADD ALL":
			// verifyElementDisplayed(getProps().getProperty("saveAllSiteBtn"));
			// if
			// (verifyElementEnabled(getProps().getProperty("saveAllSiteBtn")))
			// {
			// BaseUtil.instantClick(getProps().getProperty("saveAllSiteBtn"));
			// BaseUtil.waitForSpinner();
			// return true;
			// }
			// System.out.println("ADD ALL Button is disabled");
			//
			// case "SAVE PERMISSION BUTTON":
			// BaseUtil.instantClick(getProps().getProperty("savePermissionUserBtn"));
			// BaseUtil.waitForSpinner();
			// return true;
			// case "ENDPOINTS":
			// BaseUtil.instantClick(getProps().getProperty("endpointsTab"));
			// BaseUtil.waitForSpinner();
			// return true;

			default:
				logger.error("Switch Case["
						+ button
						+ "] is not matched in class["
						+ new Object().getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				break;
			}

			if (flag.equalsIgnoreCase("Tab")) {
				BaseUtil.explicitWait(4000);
				BaseUtil.instantClick(locator);
				BaseUtil.waitForSpinner();
				return true;
			} else if (flag.equalsIgnoreCase("Accordion")) {
				BaseUtil.instantClick(locator);
				BaseUtil.explicitWait(2000);
				return true;
			} else if (flag.equalsIgnoreCase("Button")) {
				Assert.assertTrue(
						"ACCEPT VERIFIED DATA button is not Clickable",
						BaseUtil.isElementClickable(locator));
				BaseUtil.clickAndWait(locator);
				BaseUtil.explicitWait(2000);
			}
		} catch (Exception e) {
			Assert.fail("Failed to click on [" + button + "]");
		}
		return false;
	}

	public static boolean verifyElementsURP(String elementName) {
		String labelLocator = null, flag = null, labelCheckErrorMessage = null, clickableErrorMsg = null;
		String message = null;
		String realElementName;
		if (elementName.split(":").length == 2) {
			realElementName = elementName.split(":")[1].trim();
		} else {
			realElementName = elementName;
		}
		message = "[" + elementName + "] is not available";
		labelCheckErrorMessage = "[" + elementName
				+ "] is not having text as expected";
		clickableErrorMsg = "[" + elementName + "] is not clickable";

		try {
			switch (elementName.toUpperCase()) {
			case "SITES":
				flag = "Tab";
				labelLocator = getProps().getProperty("sitesTab");
				break;
			case "USERS":
				flag = "Tab";
				labelLocator = getProps().getProperty("usersTab");
				break;
			case "TENANTS":
				flag = "Tab";
				labelLocator = getProps().getProperty("tenantsTab");
				break;
			case "COMMISSIONING":
				flag = "Tab";
				labelLocator = getProps().getProperty("commissioningTab");
				break;
			case "ENDPOINTS":
				flag = "Tab";
				labelLocator = getProps().getProperty("endpointsTab");
				break;
			case "FIRMWARE":
				flag = "Tab";
				labelLocator = getProps().getProperty("firmwareTab");
				break;
			case "DATAFIX":
				flag = "Tab";
				labelLocator = getProps().getProperty("datafixTab");
				break;
			case "AUDIT":
				flag = "Tab";
				labelLocator = getProps().getProperty("auditTab");
				break;
			case "ENDPOINT FIRMWARE":
				flag = "Tab";
				labelLocator = getProps().getProperty("endpointFirmwareTab");
				break;
			case "PERIPHERAL FIRMWARE":
				flag = "Tab";
				labelLocator = getProps().getProperty(
						"updateEndpointFirmwareTab");
				break;
			case "UPDATE ENDPOINT FIRMWARE":
				flag = "Tab";
				labelLocator = getProps().getProperty(
						"updateEndpointFirmwareTab");
				break;
			case "PERIPHERAL FIRMWARE:PERIPHERAL TYPE":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"peripheralFirmwarePageDetailPopupPeripheralType");
				break;
			case "PERIPHERAL FIRMWARE:PERIPHERAL TYPE ID":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"peripheralFirmwarePageDetailPopupPeripheralTypeID");
				break;
			case "PERIPHERAL FIRMWARE:RELEASE DATE":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"peripheralFirmwarePageDetailPopupReleaseDate");
				break;
			case "PERIPHERAL FIRMWARE:VERSION":
				flag = "Label";
				labelLocator = getProps().getProperty(
						"peripheralFirmwarePageDetailPopupVersion");
				break;
			case "PERIPHERAL FIRMWARE:MIN CONTROLLER VERSION":
				flag = "Label";
				labelLocator = getProps()
						.getProperty(
								"peripheralFirmwarePageDetailPopupMinControllerVersion");
				break;

			case "HOME":
				flag = "Tab";
				labelLocator = getProps().getProperty("homeTab");
				break;
			case "CONTROLS":
				flag = "Tab";
				labelLocator = getProps().getProperty("controlsTab");
				break;
			case "ALARMS":
				flag = "Tab";
				labelLocator = getProps().getProperty("alarmsTab");
				break;
			case "PROJECTS":
				flag = "Tab";
				labelLocator = getProps().getProperty("projects");
				break;
			case "DATA":
				flag = "Tab";
				labelLocator = getProps().getProperty("dataTab");
				break;

			case "BILLING COMPARISON REPORT":
				flag = "Report";
				labelLocator = getProps().getProperty("reportBCReport");
				break;
			case "ENERGY USAGE REPORT":
				flag = "Report";
				labelLocator = getProps().getProperty("reportEUReport");
				break;
			case "VOLTAGE IMBALANCE REPORT":
				flag = "Report";
				labelLocator = getProps().getProperty("reportVEReport");
				break;
			case "SOLAR REPORT":
				flag = "Report";
				labelLocator = getProps().getProperty("reportSLReport");
				break;
			case "OVERRIDE REPORT":
				flag = "Report";
				labelLocator = getProps().getProperty("reportORReport");
				break;
			case "SITE SCHEDULES REPORT":
				flag = "Report";
				labelLocator = getProps().getProperty("reportSSReport");
				break;
			case "SITE SCHEDULES HISTORY REPORT":
				flag = "Report";
				labelLocator = getProps().getProperty("reportSSHReport");
				break;
			case "HVAC TEST REPORT":
				flag = "Report";
				labelLocator = getProps().getProperty("reportHVTReport");
				break;
			// case "SAVINGS REPORT":
			// flag = "Report";
			// labelLocator =
			// getProps().getProperty("reportSReport");
			// break;

			// case "HOURS":
			// return
			// verifyElementDisplayed(getProps().getProperty("hours"));
			// case "HVAC":
			// return
			// verifyElementDisplayed(getProps().getProperty("hvac"));
			// case "LIGHTING/LOADS":
			// return
			// verifyElementDisplayed(getProps().getProperty("lightingLoads"));
			// case "REGULAR HOURS":
			// return
			// verifyElementDisplayed(getProps().getProperty("regularHours"));
			// case "CUSTOM HOURS":
			// return
			// verifyElementDisplayed(getProps().getProperty("customHours"));
			// case "EDIT BUTTON":
			// return
			// verifyElementDisplayed(getProps().getProperty("editButton"));
			// case "EXPORT":
			// return
			// verifyElementDisplayed(getProps().getProperty("export"));
			default:
				logger.error("Switch Case["
						+ elementName
						+ "] is not matched in class["
						+ new Object().getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
			}

			if (flag.equalsIgnoreCase("Tab") || flag.equalsIgnoreCase("Label")
					|| flag.equalsIgnoreCase("Report")) {
				BaseUtil.verifyElementDisplayed(labelLocator);
				Assert.assertTrue(labelCheckErrorMessage, realElementName
						.equals(BaseUtil.getElementText(labelLocator)));
				return true;
			} else if (flag.equalsIgnoreCase("Button")) {
				BaseUtil.assertElementDisplayed(labelLocator, message);
				Assert.assertTrue(labelCheckErrorMessage, realElementName
						.equals(BaseUtil.getElementText(labelLocator)));
				Assert.assertTrue(clickableErrorMsg,
						BaseUtil.isElementClickable(labelLocator));
				return true;
			}
		} catch (Exception e) {
			Assert.fail("failed to verify element [" + elementName + "]");
		}
		return false;
	}

	public static boolean enterText_URP(String sElementName, String sText) {
		try {
			switch (sElementName.toUpperCase()) {
			case "USER_SEARCH":
				BaseUtil.enterText(getProps().getProperty("userSearchField"),
						sText);
				return true;
			case "SITE_SEARCH":
				BaseUtil.enterText(getProps().getProperty("siteSearchField"),
						sText);
				return true;
			case "TENANT_SEARCH":
				BaseUtil.enterText(getProps().getProperty("tenantSearchField"),
						sText);
				return true;
			case "SEARCH_CHANNEL_NAME":
				BaseUtil.enterText(
						getProps().getProperty("searchVirtualChannelTextField"),
						sText);
				BaseUtil.explicitWait(2000);
				if (BaseUtil.instantElementCheck("Display", getProps()
						.getProperty("zeroRecordsFoundChannelGrid"))) {
					return true;
				}
				return true;
			case "CHANNEL_NAME":
				BaseUtil.enterText(
						getProps().getProperty("addVirtualChanneName"), sText);
				return true;
			case "UPDATE_CHANNEL_NAME":
				BaseUtil.enterText(
						getProps().getProperty(
								"editVirtualChannelDisplayNameTextField"),
						sText);
				return true;
			case "ENDPOINTFIRMWARE_SEARCH":
				BaseUtil.enterText(
						getProps().getProperty("endpointFirmwareSearchField"),
						sText);
				return true;
			case "PERIPHERALFIRMWARE_SEARCH":
				BaseUtil.enterText(
						getProps().getProperty("peripheralFirmwareSearchField"),
						sText);
				return true;
			case "SCHEDULE":
				BaseUtil.enterText(getProps().getProperty("editHVACtextarea"),
						sText);
				return true;

			default:
				logger.error("Switch Case["
						+ sElementName
						+ "] is not matched in class["
						+ new Object().getClass().getName()
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

	public static void selectResourceFromGrid_URP(String resourceName,
			String gridName) {
		boolean bResult = false;
		String gridColumnLocator = null;

		if (gridName.equalsIgnoreCase("User")) {
			BaseUtil.click(getProps().getProperty("userSearchButton"));
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty("usedrGridNameColumn");
		} else if (gridName.equalsIgnoreCase("Site")) {
			BaseUtil.click(getProps().getProperty("siteSearchButton"));
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty("siteGridNameColumn");

		} else if (gridName.equalsIgnoreCase("Tenant")) {
			BaseUtil.click(getProps().getProperty("tenantPageSearchButton"));
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty("tenantPageNameColumn");

		} else if (gridName.equalsIgnoreCase("Endpoint")) {
			BaseUtil.click(getProps().getProperty("endpointTabSearchButton"));
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty("endpointTabNameColumn");

		} else if (gridName.equalsIgnoreCase("Endpoint Firmware")) {
			BaseUtil.click(getProps().getProperty("addEndpointFirmwareButton"));
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty(
					"endpointFirmwarePageNameColumn");

		} else if (gridName.equalsIgnoreCase("Peripheral Firmware")) {
			BaseUtil.click(getProps()
					.getProperty("addPeripheralFirmwareButton"));
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty(
					"periperalFirmwarePagePeripheralTypeColumn");

		}
		logger.debug("Selecting the resource :" + resourceName);
		try {
			List<WebElement> lstSiteNames = BaseUtil
					.getMultipleElementsAfterLoaded(getProps().getProperty(
							gridColumnLocator));
			for (WebElement webElement : lstSiteNames) {
				if (webElement.getText().trim().equalsIgnoreCase(resourceName)) {
					webElement.click();
					BaseUtil.waitForSpinner();
					BaseUtil.explicitWait(10000);
					bResult = true;
					break;
				}
			}
			if (!bResult) {
				Assert.fail("Given test resource is not available");
			}
			logger.debug("resource :" + resourceName + " is selected.");
		} catch (Exception e) {
			logger.error("Failed to select resource: " + resourceName + " from"
					+ gridName + " grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the resource : " + resourceName
					+ ", see detail error message: \n"
					+ e.getStackTrace().toString());
		}
	}

	public static void checkAlert() {
		try {

			if (BaseUtil.isAlertPresent()) {
				BaseUtil.moveToAlert().accept();
			}
			// WebDriverWait wait = new WebDriverWait(getDriver(),
			// 3);
			// wait.until(ExpectedConditions.alertIsPresent());
			// Alert alert = getDriver().switchTo().alert();
			// explicitWait(3000);
			// alert.accept();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean verifyDefaultCapilibiltys(String userRole) {
		try {
			switch (userRole.toUpperCase()) {

			case "GPADMIN":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesCreatOverride"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesRunHVACTests"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesMSTRCapable"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesByPassThrottling"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesBillingComparison"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesVIEW_SITE_SAVINGS"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesVIEW_SITE_SAVINGS_MODEL"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesEDIT_SITE_SAVINGS"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesREPORT_SAVINGS"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesEDIT_XML"));
				
				// TODO : added by k on 11 may 2018 12:21 pm .....commenting to avoid compilation issues....verify
//				verifyElementDisplayed(userPageObjectRepo.get("userCapabilitiesCONFIGURE_SUPER"));
//				verifyElementDisplayed(userPageObjectRepo.get("userCapabilitiesCONFIGURE_LITE"));
//				verifyElementDisplayed(userPageObjectRepo.get("userCapabilitiesCONFIGURE_FULL"));
				
				// TODO : this code is available in k branch. commenting to avoid compilation issues....verify
//				Assert.assertTrue("CREATE_OVERRIDE capability is not default selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesCreatOverride")));
//				Assert.assertTrue("RUN_HVACTESE_capability is not default selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesRunHVACTests")));
//				Assert.assertTrue("EDIT_XML_capability is not default selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesEDIT_XML")));
//				Assert.assertTrue("EDIT_XML_capability is not default selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesCONFIGURE_SUPER")));
				
				return true;

			case "GPSUPPORT":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesCreatOverride"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesRunHVACTests"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesMSTRCapable"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesByPassThrottling"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesBillingComparison"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesEDIT_XML"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesVIEW_SITE_SAVINGS"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesVIEW_SITE_SAVINGS_MODEL"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesEDIT_SITE_SAVINGS"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesREPORT_SAVINGS"));
				
				// TODO : added by k on 11 may 2018 12:21 pm .....commenting to avoid compilation issues....verify
//				verifyElementDisplayed(userPageObjectRepo.get("userCapabilitiesCONFIGURE_SUPER"));
//				verifyElementDisplayed(userPageObjectRepo.get("userCapabilitiesCONFIGURE_LITE"));
//				verifyElementDisplayed(userPageObjectRepo.get("userCapabilitiesCONFIGURE_FULL"));
				
				// TODO : this code is available in k branch. commenting to avoid compilation issues....verify
//				Assert.assertTrue("CREATE_OVERRIDE capability is not default selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesCreatOverride")));
//				Assert.assertTrue("RUN_HVAC_TESTS capability is not default selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesRunHVACTests")));
//				Assert.assertTrue("EDIT_XML_capability is not default selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesCONFIGURE_FULL")));
				return true;

			case "GPANALYST":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesCreatOverride"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesRunHVACTests"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesBillingComparison"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesMSTRCapable"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesByPassThrottling"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesVIEW_SITE_SAVINGS"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesVIEW_SITE_SAVINGS_MODEL"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesEDIT_SITE_SAVINGS"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesREPORT_SAVINGS"));
				
				// TODO : this code is available in k branch. commenting to avoid compilation issues....verify
//				Assert.assertTrue("CREATE_OVERRIDE capability is not default selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesCreatOverride")));
//				Assert.assertTrue("RUN_HVAC_TESTS capability is not default selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesRunHVACTests")))
				
				return true;

			case "CUSTOMER ANALYST":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesMSTRCapable"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesByPassThrottling"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesREPORT_SAVINGS"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesVIEW_SITE_SAVINGS"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesVIEW_SITE_SAVINGS_MODEL"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesEDIT_SITE_SAVINGS"));
				
				// TODO : this code is available in k branch. commenting to avoid compilation issues....verify
//				Assert.assertFalse("MSTR_CAPABLE capability is selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesMSTRCapable")));
//				Assert.assertFalse("BYPASS_THROTTLING capability is selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesByPassThrottling")));
//				Assert.assertFalse("REPORT_SAVINGS capability is selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesREPORT_SAVINGS")));
//				Assert.assertFalse("VIEW_SITE_SAVINGS capability is selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesVIEW_SITE_SAVINGS")));
//				Assert.assertFalse("EDIT_SITE_SAVINGS capability is selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesEDIT_SITE_SAVINGS")));
//				Assert.assertFalse("VIEW_SITE_SAVINGS_MODEL capability is selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesVIEW_SITE_SAVINGS_MODEL")));
				return true;

			case "CUSTOMER SITE MANAGER":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesCreatOverride"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesRunHVACTests"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesMSTRCapable"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesByPassThrottling"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesREPORT_SAVINGS"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesVIEW_SITE_SAVINGS"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesVIEW_SITE_SAVINGS_MODEL"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"userCapabilitiesEDIT_SITE_SAVINGS"));
				
				// TODO : this code is available in k branch. commenting to avoid compilation issues....verify
//				Assert.assertFalse("CREATE_OVERRIDE capability is selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesCreatOverride")));
//				Assert.assertFalse("RUN_HVAC_TESTS capability is selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesRunHVACTests")));
//				Assert.assertFalse("MSTR_CAPABLE capability is selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesMSTRCapable")));
//				Assert.assertFalse("BYPASS_THROTTLING capability is selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesByPassThrottling")));
//				Assert.assertFalse("REPORT_SAVINGS capability is selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesREPORT_SAVINGS")));
//				Assert.assertFalse("VIEW_SITE_SAVINGS capability is selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesVIEW_SITE_SAVINGS")));
//				Assert.assertFalse("EDIT_SITE_SAVINGS capability is selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesEDIT_SITE_SAVINGS")));
//				Assert.assertFalse("VIEW_SITE_SAVINGS_MODEL capability is selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesVIEW_SITE_SAVINGS_MODEL")));
				return true;

			default:
				logger.error("Switch Case["
						+ userRole
						+ "] is not matched in class["
						+ new Object().getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the Capilibiltys of :" + userRole
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public static boolean isSelectDefaultCapilibiltys(String userRole) {
		try {
			switch (userRole.toUpperCase()) {
			case "GPADMIN":
				Assert.assertTrue(
						"CREATE_OVERRIDE capability is not default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesCreatOverride")));
				Assert.assertTrue(
						"RUN_HVAC_TESTS capability is not default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesRunHVACTests")));
				
				// TODO : added by k on 11 may 2018 12:21 pm .....commenting to avoid compilation issues....verify
//				Assert.assertTrue("CONFIGURE_SUPER capability is not default selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesCONFIGURE_SUPER")));
//				Assert.assertTrue("EDIT_XML capability is not default selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesEDIT_XML")));
				
				
				Assert.assertFalse(
						"MSTR_CAPABLE capability is default selected for: "
								+ userRole, BaseUtil
								.verifyCheckBoxIsSelected(getProps()
										.getProperty(
												"userCapabilitiesMSTRCapable")));
				Assert.assertFalse(
						"BYPASS_THROTTLING capability is default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesByPassThrottling")));
				Assert.assertFalse(
						"BILLING_COMPARISON capability is default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty(
										"userCapabilitiesBillingComparison")));
				Assert.assertFalse(
						"REPORT_SAVINGS capability is default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesREPORT_SAVINGS")));
				Assert.assertFalse(
						"VIEW_SITE_SAVINGS capability is default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty(
										"userCapabilitiesVIEW_SITE_SAVINGS")));
				Assert.assertFalse(
						"EDIT_SITE_SAVINGS capability is default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty(
										"userCapabilitiesEDIT_SITE_SAVINGS")));
				Assert.assertFalse(
						"VIEW_SITE_SAVINGS_MODEL capability is default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty(
										"userCapabilitiesVIEW_SITE_SAVINGS_MODEL")));
				BaseUtil.click(getProps().getProperty(
						"userCapabilitiesBillingComparison"));
				BaseUtil.click(getProps().getProperty(
						"userCapabilitiesMSTRCapable"));
				BaseUtil.click(getProps().getProperty(
						"userCapabilitiesByPassThrottling"));
				BaseUtil.click(getProps().getProperty(
						"userCapabilitiesREPORT_SAVINGS"));
				BaseUtil.click(getProps().getProperty(
						"userCapabilitiesVIEW_SITE_SAVINGS"));
				BaseUtil.click(getProps().getProperty(
						"userCapabilitiesEDIT_SITE_SAVINGS"));
				BaseUtil.click(getProps().getProperty(
						"userCapabilitiesVIEW_SITE_SAVINGS_MODEL"));
				return true;

			case "GPSUPPORT":
				Assert.assertTrue(
						"CREATE_OVERRIDE capability is not default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesCreatOverride")));
				Assert.assertTrue(
						"RUN_HVAC_TESTS capability is not default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesRunHVACTests")));
				
				// TODO : added by k on 11 may 2018 12:21 pm .....commenting to avoid compilation issues....verify
//				Assert.assertTrue("CONFIGURE_FULL capability is not default selected for: " + userRole,
//						verifyCheckBoxIsSelected(userPageObjectRepo.get("userCapabilitiesCONFIGURE_FULL")));
				
				
				Assert.assertFalse(
						"MSTR_CAPABLE capability is default selected for: "
								+ userRole, BaseUtil
								.verifyCheckBoxIsSelected(getProps()
										.getProperty(
												"userCapabilitiesMSTRCapable")));
				Assert.assertFalse(
						"BYPASS_THROTTLING capability is default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesByPassThrottling")));
				Assert.assertFalse(
						"BILLING_COMPARISON capability is default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty(
										"userCapabilitiesBillingComparison")));
				Assert.assertFalse(
						"REPORT_SAVINGS capability is default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesREPORT_SAVINGS")));
				Assert.assertFalse(
						"VIEW_SITE_SAVINGS capability is default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty(
										"userCapabilitiesVIEW_SITE_SAVINGS")));
				Assert.assertFalse(
						"EDIT_SITE_SAVINGS capability is default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty(
										"userCapabilitiesEDIT_SITE_SAVINGS")));
				Assert.assertFalse(
						"VIEW_SITE_SAVINGS_MODEL capability is default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty(
										"userCapabilitiesVIEW_SITE_SAVINGS_MODEL")));
				Assert.assertFalse(
						"EDIT_XML capability is default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesEDIT_XML")));
				BaseUtil.click(getProps().getProperty(
						"userCapabilitiesBillingComparison"));
				BaseUtil.click(getProps().getProperty(
						"userCapabilitiesMSTRCapable"));
				BaseUtil.click(getProps().getProperty(
						"userCapabilitiesByPassThrottling"));
				BaseUtil.click(getProps().getProperty(
						"userCapabilitiesREPORT_SAVINGS"));
				BaseUtil.click(getProps().getProperty(
						"userCapabilitiesVIEW_SITE_SAVINGS"));
				BaseUtil.click(getProps().getProperty(
						"userCapabilitiesEDIT_SITE_SAVINGS"));
				BaseUtil.click(getProps().getProperty(
						"userCapabilitiesVIEW_SITE_SAVINGS_MODEL"));
				BaseUtil.click(getProps().getProperty(
						"userCapabilitiesEDIT_XML"));
				return true;

			case "GPANALYST":
				Assert.assertFalse(
						"MSTR_CAPABLE capability is not default selected for: "
								+ userRole, BaseUtil
								.verifyCheckBoxIsSelected(getProps()
										.getProperty(
												"userCapabilitiesMSTRCapable")));
				Assert.assertTrue(
						"RUN_HVAC_TESTS capability is not default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesRunHVACTests")));
				Assert.assertFalse(
						"BYPASS_THROTTLING capability is not default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesByPassThrottling")));
				Assert.assertFalse(
						"BILLING_COMPARISON capability is not default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty(
										"userCapabilitiesBillingComparison")));
				Assert.assertTrue(
						"CREATE_OVERRIDE capability is not default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesCreatOverride")));
				Assert.assertFalse(
						"VIEW_SITE_SAVINGS capability is not default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty(
										"userCapabilitiesVIEW_SITE_SAVINGS")));
				Assert.assertFalse(
						"VIEW_SITE_SAVINGS_MODEL capability is not default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty(
										"userCapabilitiesVIEW_SITE_SAVINGS_MODEL")));
				Assert.assertFalse(
						"EDIT_SITE_SAVINGS capability is not default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty(
										"userCapabilitiesEDIT_SITE_SAVINGS")));
				Assert.assertFalse(
						"REPORT_SAVINGS capability is not default selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesREPORT_SAVINGS")));

				Assert.assertTrue(
						"userCapabilitiesMSTRCapable is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesMSTRCapable")));
				Assert.assertTrue(
						"userCapabilitiesByPassThrottling is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesByPassThrottling")));
				Assert.assertTrue(
						"userCapabilitiesVIEW_SITE_SAVINGS is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesVIEW_SITE_SAVINGS")));
				Assert.assertTrue(
						"userCapabilitiesVIEW_SITE_SAVINGS_MODEL is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesVIEW_SITE_SAVINGS_MODEL")));
				Assert.assertTrue(
						"userCapabilitiesEDIT_SITE_SAVINGS is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesEDIT_SITE_SAVINGS")));
				Assert.assertTrue(
						"userCapabilitiesREPORT_SAVINGS is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesREPORT_SAVINGS")));
				Assert.assertTrue(
						"userCapabilitiesBillingComparison is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesBillingComparison")));

				return true;

			case "CUSTOMER SITE MANAGER":
				Assert.assertFalse(
						"CREATE_OVERRIDE capability is selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesCreatOverride")));
				Assert.assertFalse(
						"RUN_HVAC_TESTS capability is selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesRunHVACTests")));
				Assert.assertFalse("MSTR_CAPABLE capability is selected for: "
						+ userRole, BaseUtil
						.verifyCheckBoxIsSelected(getProps().getProperty(
								"userCapabilitiesMSTRCapable")));
				Assert.assertFalse(
						"BYPASS_THROTTLING capability is selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesByPassThrottling")));
				Assert.assertFalse(
						"VIEW_SITE_SAVINGS capability is selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty(
										"userCapabilitiesVIEW_SITE_SAVINGS")));
				Assert.assertFalse(
						"VIEW_SITE_SAVINGS_MODEL capability is selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty(
										"userCapabilitiesVIEW_SITE_SAVINGS_MODEL")));
				Assert.assertFalse(
						"EDIT_SITE_SAVINGS capability is selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty(
										"userCapabilitiesEDIT_SITE_SAVINGS")));
				Assert.assertFalse(
						"REPORT_SAVINGS capability is selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesREPORT_SAVINGS")));

				Assert.assertTrue(
						"CapabilitiesCreatOverride is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesCreatOverride")));
				Assert.assertTrue(
						"userCapabilitiesRunHVACTests is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesRunHVACTests")));
				Assert.assertTrue(
						"userCapabilitiesMSTRCapable is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesMSTRCapable")));
				Assert.assertTrue(
						"userCapabilitiesByPassThrottling is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesByPassThrottling")));
				Assert.assertTrue(
						"userCapabilitiesVIEW_SITE_SAVINGS is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesVIEW_SITE_SAVINGS")));
				Assert.assertTrue(
						"userCapabilitiesVIEW_SITE_SAVINGS_MODEL is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesVIEW_SITE_SAVINGS_MODEL")));
				Assert.assertTrue(
						"userCapabilitiesEDIT_SITE_SAVINGS is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesEDIT_SITE_SAVINGS")));
				Assert.assertTrue(
						"userCapabilitiesREPORT_SAVINGS is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesREPORT_SAVINGS")));
				return true;

			case "CUSTOMER ANALYST":
				Assert.assertFalse(
						"MSTR_CAPABLE capability is not selected for: "
								+ userRole, BaseUtil
								.verifyCheckBoxIsSelected(getProps()
										.getProperty(
												"userCapabilitiesMSTRCapable")));
				Assert.assertFalse(
						"BYPASS_THROTTLING capability is not selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesByPassThrottling")));
				Assert.assertFalse(
						"REPORT_SAVINGS capability is selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty("userCapabilitiesREPORT_SAVINGS")));
				Assert.assertFalse(
						"VIEW_SITE_SAVINGS_MODEL capability is selected for: "
								+ userRole,
						BaseUtil.verifyCheckBoxIsSelected(getProps()
								.getProperty(
										"userCapabilitiesVIEW_SITE_SAVINGS_MODEL")));
				
				// TODO : added by k on 11 may 2018 12:21 pm .....commenting to avoid compilation issues....verify
//				Assert.assertTrue("userCapabilitiesEDIT_SITE_SAVINGS is not selected for: " + userRole,
//						checkIfClickable(userPageObjectRepo.get("userCapabilitiesEDIT_SITE_SAVINGS")));
//				Assert.assertTrue("userCapabilitiesEDIT_SITE_SAVINGS is not selected for: " + userRole,
//						checkIfClickable(userPageObjectRepo.get("userCapabilitiesVIEW_SITE_SAVINGS")));
				

				Assert.assertTrue(
						"userCapabilitiesMSTRCapable is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesMSTRCapable")));
				Assert.assertTrue(
						"userCapabilitiesByPassThrottling is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesByPassThrottling")));
				Assert.assertTrue(
						"userCapabilitiesREPORT_SAVINGS is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesREPORT_SAVINGS")));
				Assert.assertTrue(
						"userCapabilitiesVIEW_SITE_SAVINGS is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesVIEW_SITE_SAVINGS")));
				Assert.assertTrue(
						"userCapabilitiesEDIT_SITE_SAVINGS is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesEDIT_SITE_SAVINGS")));
				Assert.assertTrue(
						"userCapabilitiesVIEW_SITE_SAVINGS_MODEL is not selected for: "
								+ userRole,
						BaseUtil.checkIfClickable(getProps().getProperty(
								"userCapabilitiesVIEW_SITE_SAVINGS_MODEL")));
				return true;

			default:
				logger.error("Switch Case["
						+ userRole
						+ "] is not matched in class["
						+ new Object().getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the Capilibiltys of :" + userRole
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public static boolean editUserValueVerification(Map<String, String> data) {
		Set<Entry<String, String>> entry = data.entrySet();
		Iterator<Entry<String, String>> ii = entry.iterator();
		Map.Entry<String, String> temp = null;
		String locator = null;
		String flag = null;
		try {
			while (ii.hasNext()) {
				temp = ii.next();
				switch (temp.getKey().toUpperCase()) {
				case "USERNAME":
					flag = "Element";
					locator = getProps().getProperty(
							"editUserPagePopupUserNameHeader");
					break;
				case "FIRSTNAME":
					flag = "Attribute";
					locator = getProps().getProperty("addUserFirstNameField");
					break;
				case "LASTNAME":
					flag = "Attribute";
					locator = getProps().getProperty("addUserLastNameField");
					break;
				case "EMAIL":
					flag = "Attribute";
					locator = getProps().getProperty("addUserEmailField");
					break;
				case "LOCALE":
					flag = "Attribute";
					locator = getProps().getProperty("addUserLocaleField");
					break;
				case "UNIT OF TEMPERATURE":
					flag = "Attribute";
					locator = getProps()
							.getProperty("addUserUnitOfTemparature");
					break;
				case "ROLE":
					flag = "Attribute";
					locator = getProps().getProperty("addUserRoleField");
					break;
				}

				if (flag.equalsIgnoreCase("Element")) {
					return BaseUtil.getElementText(locator).contains(
							temp.getValue().trim());
				} else if (flag.equalsIgnoreCase("Attribute")) {
					return BaseUtil.getElementAttribute(locator, "value")
							.equalsIgnoreCase(temp.getValue().trim());
				}
			}
		} catch (Exception e) {
			logger.error("Failed to verify that Element [" + temp.getKey()
					+ "] is enabled.  see detail message: " + e.getMessage());
			return false;
		}
		return false;
	}

	public static void enterTestData(String num, String list) {
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

			// Site Test Data
			case "SITE ID":
				flag = "Field";
				locator = getProps().getProperty("addSiteSiteIDField");
				if (num.equals("2")) {
					testData = getProps().getProperty(
							"addSiteGPUP-26329TestData");
				} else {
					testData = getProps().getProperty(
							"addSiteSiteIDFieldTestData");
				}

				break;
			case "SITE DESCRIPTION":
				flag = "Field";
				locator = getProps().getProperty("addSiteSiteNameField");
				if (num.equals("2")) {
					testData = getProps().getProperty(
							"addSiteGPUP-26329TestData");
				} else {
					testData = getProps().getProperty(
							"addSiteSiteNameFieldTestData");
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
			case "SITE AREA":
				flag = "Field";
				locator = getProps().getProperty("addSiteAreaField");
				testData = getProps().getProperty("addSiteAreaFieldTestData");
				break;
			case "SYSTEM SIZE":
				flag = "Field";
				locator = getProps().getProperty(
						"addSiteSolarPanelSystemSizeField");
				testData = getProps().getProperty(
						"addSiteSolarPanelSystemSizeTestData");
				break;
			case "PATH":
				flag = "Field";
				locator = getProps().getProperty("addSitePathField");
				BaseUtil.click(locator);
				testData = getProps().getProperty("addSitePathFieldTestData");
				break;

			// Endpoint Firmware Test Data
			case "ENDPOINT FIRMWARE:VERSION":
				flag = "Field";
				locator = getProps().getProperty(
						"addEndpointFimrwareVersionField");
				testData = getProps().getProperty(
						"addEndpointFimrwareVersionFieldTestData");
				break;
			case "ENDPOINT FIRMWARE:ENDPOINT TYPE":
				flag = "Dropdown";
				locator = getProps().getProperty(
						"addEndpointFimrwareEndpointTypeField");
				testData = getProps().getProperty(
						"addEndpointFimrwareEndpointTypeFieldTestData");
				break;
			case "ENDPOINT FIRMWARE:RELEASE DATE":
				flag = "Field";
				locator = getProps().getProperty(
						"addEndpointFimrwareReleaseDateField");
				testData = getProps().getProperty(
						"addEndpointFimrwareReleaseDateFieldTestData");
				break;

			// Peripheral Firmware Test Data
			case "PERIPHERAL FIRMWARE:PERIPHERAL TYPE":
				flag = "Dropdown";
				locator = getProps().getProperty(
						"addPeripheralFirmwarePeripheralType");
				testData = getProps().getProperty(
						"addPeripheralFirmwarePeripheralTypeTestData");
				break;
			case "PERIPHERAL FIRMWARE:RELEASE DATE":
				flag = "Dropdown";
				locator = getProps().getProperty(
						"addPeripheralFirmwareReleaseDate");
				testData = getProps().getProperty(
						"addPeripheralFirmwareReleaseDateTestData");
				break;
			case "PERIPHERAL FIRMWARE:VERSION MAJOR":
				flag = "Field";
				locator = getProps().getProperty(
						"addPeripheralFirmwareVersionMajor");
				testData = getProps().getProperty(
						"addPeripheralFirmwareVersionMajorTestData");
				break;
			case "PERIPHERAL FIRMWARE:VERSION MINOR":
				flag = "Field";
				locator = getProps().getProperty(
						"addPeripheralFirmwareVersionMinor");
				testData = getProps().getProperty(
						"addPeripheralFirmwareVersionMinorTestData");
				break;
			case "PERIPHERAL FIRMWARE:CONTROLLER VERSION MAJOR":
				flag = "Dropdown";
				locator = getProps().getProperty(
						"addPeripheralFirmwareControllerVersionMajor");
				testData = getProps().getProperty(
						"addPeripheralFirmwareControllerVersionMajorTestData");
				break;
			case "PERIPHERAL FIRMWARE:CONTROLLER VERSION MINOR":
				flag = "Field";
				locator = getProps().getProperty(
						"addPeripheralFirmwareControllerVersionMinor");
				testData = getProps().getProperty(
						"addPeripheralFirmwareControllerVersionMinorTestData");
				break;
			case "PERIPHERAL FIRMWARE:CONTROLLER VERSION REVISION":
				flag = "Field";
				locator = getProps().getProperty(
						"addPeripheralFirmwareControllerVersionRevision");
				testData = getProps()
						.getProperty(
								"addPeripheralFirmwareControllerVersionRevisionTestData");
				break;

			default:
				logger.error("Switch Case["
						+ list
						+ "] is not matched in class["
						+ new Object().getClass().getName()
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

	public static void checkCheckBox(List<String> checkBoxList) {
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
				case "FIRST":
					locator = getProps().getProperty("");
					break;
				default:
					logger.error("Switch Case["
							+ checkbox
							+ "] is not matched in class["
							+ new Object().getClass().getName()
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

	public static boolean checkPopupExistence(String sPopName, String sExpMsg) {
		String locator = null;
		try {
			switch (sPopName.toUpperCase()) {
			case "EDIT SITE":
				locator = getProps().getProperty("editSitePagePopup");
				break;
			default:
				logger.error("Switch Case["
						+ sPopName
						+ "] is not matched in class["
						+ new Object().getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}

			return BaseUtil.verifyElementDisplayed(locator);
		} catch (Exception e) {
			logger.error("Failed to check existence of popup:" + sPopName
					+ " , detail message: " + e.getMessage());
			return false;
		}
	}

	public static boolean clickChooseButtonAndSelectFile(String buttonName,
			String tabName, String fileType, String filePath) {
		try {
			switch (tabName.toUpperCase()) {
			case "ENDPOINT FIRMWARE":
				BaseUtil.uploadFile(
						getProps().getProperty(
								"addEndpointFirmwareBrowseButton"), filePath);
				BaseUtil.explicitWait(2000);
				return true;
			case "PERIPHERAL FIRMWARE":
				BaseUtil.uploadFile(
						getProps().getProperty(
								"addperipheralFirmwareBrowseButton"), filePath);
				BaseUtil.explicitWait(2000);
				return true;
			default:
				logger.error("Switch Case["
						+ tabName
						+ "] is not matched in class["
						+ new Object().getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
			}

		} catch (Exception e) {
			logger.error("Failed to click on button :" + buttonName
					+ " , detail message: " + e.getMessage());
			return false;
		}
		return false;
	}

	public static boolean verifyUpdateEndpointFirmwareObjects(String elementName) {
		String realElementName;
		if (elementName.split(":").length == 2) {
			realElementName = elementName.split(":")[1].trim();
		} else {
			realElementName = elementName;
		}
		try {
			switch (elementName.toUpperCase()) {
			case "POPNAME:SELECT FIRMWARE VERSION":
				Assert.assertTrue(
						"Update firmware Popup Heading Heading is not showing.",
						BaseUtil.verifyElementDisplayed(getProps().getProperty(
								"updateFirmwarePopupHeading")));
				Assert.assertTrue(
						"updateFirmwarePopupHeading is not as expected.",
						BaseUtil.getElementText(
								getProps().getProperty(
										"updateFirmwarePopupHeading"))
								.equalsIgnoreCase(realElementName));
				return true;
			case "LABEL:EC1K:":
				Assert.assertTrue(
						"updateFirmwarePopupHeading Heading is not showing.",
						BaseUtil.verifyElementDisplayed(getProps().getProperty(
								"updateFirmwarePopupLebel1")));
				BaseUtil.getElementText(
						getProps().getProperty("updateFirmwarePopupLebel1"))
						.equalsIgnoreCase(realElementName);
				return true;
			case "LABEL:EC100:":
				// Assert.assertTrue("updateFirmwarePopupHeading Heading is not
				// showing.",
				// BaseUtil.verifyElementDisplayed(getProps().getProperty("updateFirmwarePopupLebel2")));
				// getElementText(getProps().getProperty("updateFirmwarePopupLebel2"))
				// .equalsIgnoreCase(realElementName);
				return true;
			case "EC1K:SELECTIONBOX":
				Assert.assertTrue(
						"updateFirmwarePopupHeading Heading is not showing.",
						BaseUtil.verifyElementDisplayed(getProps().getProperty(
								"updateFirmwarePopupSelection1")));
				return true;
			case "EC100:SELECTIONBOX":
				// Assert.assertTrue("updateFirmwarePopupHeading Heading is not
				// showing.",
				// BaseUtil.verifyElementDisplayed(getProps().getProperty("updateFirmwarePopupSelection2")));
				return true;
			case "BUTTON:CANCEL":
				Assert.assertTrue(
						"updateFirmwarePopupHeading Heading is not showing.",
						BaseUtil.verifyElementDisplayed(getProps().getProperty(
								"updateFirmwarePopupCancelButtonbtn")));
				return true;
			case "BUTTON:OK":
				Assert.assertTrue(
						"updateFirmwarePopupHeading Heading is not showing.",
						BaseUtil.verifyElementDisplayed(getProps().getProperty(
								"updateFirmwarePopupOkbtn")));
				return true;

			default:
				logger.error("Switch Case["
						+ elementName
						+ "] is not matched in class["
						+ new Object().getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid task clicked");
			}
		} catch (Exception e) {
			logger.error("Failed to click on link :" + elementName
					+ " , detail message: " + e.getMessage());
			Assert.fail(e.getMessage());
		}
		return false;
	}

	// Method to select site from site tree in portal
	public static boolean isSiteSelected(String siteType, String tabName)
			throws SQLException, IOException {
		String actalXpath = null;
		ManageTestData objDelTestdata = new ManageTestData();
		String url = getDriver().getCurrentUrl();
		if (url.contains("https://ems-dev-master.gridpoint.com")
				|| url.contains("https://ems-qa-master.aws-gridpoint.com")) {
			if (siteType.equalsIgnoreCase("GPEC")) {
				for (String siteName : sitelstDataGPEC) {
					BaseUtil.enterText(
							getProps().getProperty("siteTreeSearchBox"),
							siteName);
					BaseUtil.explicitWait(5000);
					String siteId = objDelTestdata.getID("Site", siteName);
					if (siteId != null) {
						actalXpath = "xpath===//*[contains(@id,'" + siteId
								+ "-0_anchor')]";
						BaseUtil.checkCheckBox(actalXpath);
						BaseUtil.explicitWait(5000);
						switch (tabName.toUpperCase()) {
						case "HOURS":
							if (BaseUtil
									.instantElementCheck("Display", getProps()
											.getProperty("mainHourscheckbox"))) {
								return true;
							}
							break;
						case "LIGHTING":
							if (BaseUtil.instantElementCheck("Display",
									getProps()
											.getProperty("lightingDeviceTree"))) {
								BaseUtil.clickAndWait(getProps().getProperty(
										"deviceSelectAllbtn"));
								BaseUtil.explicitWait(5000);
								if (BaseUtil.instantElementCheck("Display",
										getProps().getProperty("lightEditBtn"))

										|| BaseUtil.instantElementCheck(
												"Display",
												getProps().getProperty(
														"lightOverrideBtn"))) {
									return true;
								}
								break;
							}
							break;
						case "HVAC":
							if (BaseUtil.instantElementCheck("Display",
									getProps().getProperty("hvacDeviceTree"))) {
								BaseUtil.clickAndWait(getProps().getProperty(
										"deviceSelectAllbtn"));
								BaseUtil.explicitWait(5000);
								if (BaseUtil.instantElementCheck("Display",
										getProps().getProperty("hvacEditBtn"))

										|| BaseUtil.instantElementCheck(
												"Display",
												getProps().getProperty(
														"OverrideBtn"))) {
									return true;
								}
								break;
							}
							break;
							// TODO : Code available in k branch... verify...
//						case "HVAC_SM":
//							if (instantElementCheck("Display",userRolePermissionsPageObjectRepo.get("hvacDeviceTree"))) {
//								clickAndWait(userRolePermissionsPageObjectRepo.get("deviceSelectAllbtn"));
//								explicitWait(5000);
//								if (instantElementCheck("Display", userRolePermissionsPageObjectRepo.get("hvacEditBtn")))
//
////										|| instantElementCheck("Display",
////												userRolePermissionsPageObjectRepo.get("OverrideBtn"))) 
//								{
//									return true;
//								}
//								break;
//							}
//							return true;
						default:
							logger.error("Switch Case["
									+ tabName
									+ "] is not matched in class["
									+ new Object().getClass().getName()
									+ "] , Method["
									+ Thread.currentThread().getStackTrace()[1]
											.getMethodName() + "]");
							break;
						}

					} else {
						logger.info("Device not found for Entered site");
						BaseUtil.checkCheckBox(actalXpath);
					}
					continue;
				}
			} else if (siteType.equalsIgnoreCase("ADM")) {
				for (String siteName : sitelstDataADM) {
					BaseUtil.enterText(
							getProps().getProperty("siteTreeSearchBox"),
							siteName);
					String siteId = objDelTestdata.getID("Site", siteName);
					if (siteId != null) {
						actalXpath = "xpath===//*[contains(@id,'" + siteId
								+ "-0_anchor')]";
						BaseUtil.clickAndWait(actalXpath);
						return true;
					} else {
						logger.info("Entered site is not found");
					}
					continue;
				}
			}
		} 
		// TODO : else if condition updated by k..on 11 may 2018 12:21 pm ...verify
//		else if (url.contains("https://ems-qa-master.gridpoint.com")
//				|| url.contains("https://ems-qa-master.release-gridpoint.com")) {
		else if (url.contains("https://ems-qa-master.gridpoint.com")
				|| url.contains("https://ems-qa-release.aws-gridpoint.com")
				|| url.contains("https://ems-qa-master.awsqa-gridpoint.com")) {
			
			if (siteType.equalsIgnoreCase("GPEC")) {
				// TODO : list is updated by k..on 11 may 2018 12:21 pm ...verify
//				List<String> sitelstData = Arrays.asList("qak1", "qak2",
//						"qak3", "qak4", "qak5", "qak6", "qak10");
				List<String> sitelstData = Arrays.asList("qak1", "qak2");
				/////
				
				for (String siteName : sitelstData) {
					BaseUtil.enterText(
							getProps().getProperty("siteTreeSearchBox"),
							siteName);
					BaseUtil.explicitWait(3000);
					String siteId = objDelTestdata.getID("Site", siteName);
					// explicitWait(3000);
					if (siteId != null) {
						actalXpath = "xpath===//*[contains(@id,'n" + siteId
								+ "-0_anchor')]";
						BaseUtil.explicitWait(3000);
						BaseUtil.checkCheckBox(actalXpath);
						BaseUtil.explicitWait(5000);
						return true;
					} else {
						logger.info("Entered site is not found");
					}
					continue;
				}
			} else if (siteType.equalsIgnoreCase("ADM")) {
				// TODO : list is updated by k..on 11 may 2018 12:21 pm ...verify
//				List<String> sitelstData = Arrays.asList("qaarl1", "qaarl2",
//						"qaarl3", "qaarl4", "qaarl5", "qaarl6", "qaarl7",
//						"qaarl8", "qaarl9", "qaarl10", "qak11");
				List<String> sitelstData = Arrays.asList("QAADM01", "QAADM02");
				
				for (String siteName : sitelstData) {
					BaseUtil.enterText(
							getProps().getProperty("siteTreeSearchBox"),
							siteName);
					String siteId = objDelTestdata.getID("Site", siteName);
					if (siteId != null) {
						actalXpath = "xpath===//*[contains(@id,'" + siteId
								+ "-0_anchor')]";
						BaseUtil.clickAndWait(actalXpath);
						BaseUtil.explicitWait(5000);
						return true;
					} else {
						logger.info("Entered site is not found");
					}
					continue;
				}
			}
		}
		return false;
	}

	@SuppressWarnings("unused")
	public static boolean isElementEnabledOnURPPage(String elementName) {
		String locator = null;
		String realElementName;
		if (elementName.split(":").length == 2) {
			realElementName = elementName.split(":")[1].trim();
		} else {
			realElementName = elementName;
		}
		try {
			switch (elementName.toUpperCase()) {
			case "EDIT HVAC:SAVE":
				locator = getProps().getProperty("editHVACSavebtn");
				break;
			case "EDIT HVAC:CANCEL":
				locator = getProps().getProperty("editHVACCanceltn");
				break;
			case "EDIT":
				locator = getProps().getProperty("editHVACSchedulebtn");
				break;
			case "OVERRIDE":
				locator = getProps().getProperty("OverrideBtn");
				break;
			case "LIGHT:OVERRIDE":
				locator = getProps().getProperty("lightOverrideBtn");
				break;
			case "LIGHT:EDIT":
				locator = getProps().getProperty("lightEditBtn");
				break;
			case "HVAC SCOPE":
				locator = getProps().getProperty("HvacScopeBtn");
				break;
			case "RUN TEST":
				locator = getProps().getProperty("HvacScopeBothBtn");
				break;

			default:
				logger.error("Switch Case["
						+ elementName
						+ "] is not matched in class["
						+ new Object().getClass().getName()
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

	public static boolean instantElementCheckOnControlTab(String buttonName) {

		try {
			switch (buttonName.toUpperCase()) {
			case "EDIT":
				return BaseUtil.instantElementCheck("Display", getProps()
						.getProperty("editRegularHoursbtn"));
			case "HVAC SCOPE":
				return BaseUtil.instantElementCheck("Display", getProps()
						.getProperty("HvacScopeBtnHide"));
			case "OVERRIDE":
				return BaseUtil.instantElementCheck("Display", getProps()
						.getProperty("OverrideBtnHide"));
			case "HIDE:EDIT":
				return BaseUtil.instantElementCheck("Display", getProps()
						.getProperty("editHoursBtnHide"));
			case "HIDE:LIGHT:OVERRIDE":
				return BaseUtil.instantElementCheck("Display", getProps()
						.getProperty("hidelightOverrideBtn"));
			case "HIDE:LIGHT:EDIT":
				return BaseUtil.instantElementCheck("Display", getProps()
						.getProperty("hidelightEditBtn"));

			default:
				logger.error("Switch Case["
						+ buttonName
						+ "] is not matched in class["
						+ new Object().getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the header :" + buttonName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void isDashboardTabAvailable(String userName, String tab) {
		BaseUtil.enterText(getProps().getProperty("adminUserName_Locator"),
				userName);
		BaseUtil.enterText(getProps().getProperty("adminUserPassword_Locator"),
				PathConstants.DEFAULT_USER_PASSWORD);
		BaseUtil.click(getProps().getProperty("adminLoginbutton_Locator"));
		BaseUtil.waitForSpinner();

		if (tab.equalsIgnoreCase("DashboardTab")) {
			Assert.assertTrue(
					" Expected : Dashboard Tab should be Available. Actual : Dashboard tab is not available for user ["
							+ userName + "] ",
					BaseUtil.isElementClickable(getProps().getProperty(
							"DashboardTab")));
		} else if (tab.equalsIgnoreCase("endpointTab")) {
			Assert.assertTrue(
					" Expected : Dashboard Tab should be Available. Actual : Dashboard tab is not available for user ["
							+ userName + "] ",
					BaseUtil.isElementClickable(getProps().getProperty(
							"endpointTab")));
		}
	}

	public static boolean clickOperationOnFirmwarePage(String sButtonName) {
		try {
			// String label;
			switch (sButtonName.toUpperCase()) {
			case "UPDATE FIRMWARE":
				BaseUtil.clickAndWait(getProps().getProperty(
						"updateFirmwareBtn"));
				return true;

			default:
				logger.error("Switch Case["
						+ sButtonName
						+ "] is not matched in class["
						+ new Object().getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to click on button :" + sButtonName
					+ " , detail message: " + e.getMessage());
			return false;
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public static boolean verifyAddTenantButton(String roleName) {
		try {
			if (roleName.equalsIgnoreCase("Partner Admin")) {
				Assert.assertFalse(
						" Expected : Add Tenant button should  not be Available. Actual : Add Tenant button is available for user ["
								+ roleName + "] ",
						BaseUtil.isElementClickable(getProps().getProperty(
								"addTenantButton")));

			} else if (roleName.equalsIgnoreCase("Provisioner")) {
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addEndpointsButton"));
			}
		} catch (Exception e) {
			Assert.fail("Could not verify add Tenants");
		}
		return false;
	}

	public static void enterText_userRolePermissionsPage(String sElementName,
			String sText) {
		String sCaseOptions = "USER_SEARCH, ";
		try {
			switch (sElementName.toUpperCase()) {
			case "HVAC TEST":
				BaseUtil.enterText(getProps().getProperty("hvacTestTextArea"),
						sText);
				break;
			case "SCHEDULE":
				BaseUtil.enterText(getProps().getProperty("editHVACtextarea"),
						sText);
				break;
			case "SITE_SEARCH":
				BaseUtil.enterText(getProps().getProperty("userSearchField"),
						sText);
				BaseUtil.waitForSpinner();
				break;

			default:
				logger.error("Switch Case["
						+ sElementName
						+ "] is not matched in class["
						+ new Object().getClass().getName()
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

	public static void selectUserFromGrid_AuditPage(String userName) {
		boolean bResult = false;
		logger.debug("Selecting the site :" + userName);
		try {
			List<WebElement> lstSiteNames = BaseUtil
					.getMultipleElementsAfterLoaded(getProps().getProperty(
							"usedrGridNameColumn"));
			for (WebElement webElement : lstSiteNames) {
				if (webElement.getText().trim().equalsIgnoreCase(userName)) {
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
			logger.debug("site :" + userName + " is selected.");
		} catch (Exception e) {
			logger.error("Failed to select site: " + userName
					+ " from site grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the site : " + userName
					+ ", see detail error message: \n"
					+ e.getStackTrace().toString());
		}
	}

	public boolean clickDiffrantTabs(String button) {
		try {
			switch (button.toUpperCase()) {
			case "SITES":
				BaseUtil.instantClick(getProps().getProperty("sitesTab"));
				BaseUtil.waitForSpinner();
				return true;
			case "TENANTS":
				BaseUtil.instantClick(getProps().getProperty("tenantsTab"));
				BaseUtil.waitForSpinner();
				BaseUtil.explicitWait(2000);
				return true;
			case "ENDPOINTS":
				BaseUtil.explicitWait(2000);
				BaseUtil.instantClick(getProps().getProperty("endpointsTab"));
				BaseUtil.waitForSpinner();
				return true;
			case "FIRMWARE":
				BaseUtil.explicitWait(1000);
				BaseUtil.clickAndWait(getProps().getProperty("FirmwareTab"));
				BaseUtil.waitForSpinner();
				return true;
			case "PERIPHERAL FIRMWARE":
				BaseUtil.clickAndWait(getProps().getProperty(
						"peripheralFirmwareTab"));
				BaseUtil.explicitWait(1000);
				return true;

			default:
				logger.error("Switch Case["
						+ button
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Failed to click the ["
						+ button
						+ "] because you passed wrong parameter["
						+ button
						+ "] either in feature file or test class. \n please choose valid parameter from the following list: \n ["
						+ button + "]");
			}
		} catch (Exception e) {
			logger.error("Failed to click the [" + button
					+ "] tab. see detail error message : \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to click the [" + button
					+ "] field in endpoint page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
		return false;
	}

	// public boolean clickOperationOnSiteTab(String button, Map<String, String>
	// getProps().getProperty) {
	// try {
	// switch (button.toUpperCase()) {
	//
	// case "HIDE DISABLED":
	// BaseUtil.instantClick(getProps().getProperty("userinActiveUsersChkBox"));
	// BaseUtil.waitForSpinner();
	// return true;
	// case "ADD USER":
	// BaseUtil.instantClick(getProps().getProperty("addUserButton"));
	// return true;
	// case "USER:SEARCH":
	// BaseUtil.instantClick(getProps().getProperty("userSearchButton"));
	// BaseUtil.waitForSpinner();
	// return true;
	// case "USERS":
	// BaseUtil.instantClick(getProps().getProperty("userTab"));
	// BaseUtil.waitForSpinner();
	// return true;
	//
	// default:
	// logger.error("Switch Case[" + button + "] is not matched in class[" +
	// getClass().getName()
	// + "] , Method[" +
	// Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
	// Assert.fail("Failed to enter the text in field [" + button + "] because
	// you passed wrong parameter["
	// + button
	// + "] either in feature file or test class. \n please choose valid
	// parameter from the following list: \n ["
	// + button + "]");
	// }
	// } catch (Exception e) {
	// logger.error("Failed to enter the value in the [" + button
	// + "] field in endpoint page. see detail error message : \n" +
	// e.getStackTrace().toString());
	// Assert.fail("Failed to enter the value in the [" + button
	// + "] field in endpoint page. see detail error message \n" +
	// e.getStackTrace().toString());
	// }
	// return false;
	// }
	//
	// public boolean enterValues(Map<String, String>
	// getProps().getProperty,
	// ) {
	// try {
	// enterText(getProps().getProperty("addEndpointSerialField"),
	// getProps().getProperty("addEndpointSerialTestData"));
	// explicitWait(20000);
	// selectDropDownByValue(getProps().getProperty("addEndpointTypeDropDown"),
	// getProps().getProperty("addEndpointTypeDropDownTestData"));
	// explicitWait(2000);
	// return true;
	// } catch (Exception e) {
	// Assert.fail("Failed to enter valid values");
	// }
	// return false;
	// }

	public void enterText_ProvisionerRoleEndpointPage(String sElementName,
			String sText) {
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

	public boolean modify_Endpoint() {
		try {
			BaseUtil.selectDropDownByValue(
					getProps().getProperty("modifyEndpointTypeDropDown"),
					getProps()
							.getProperty("modifyEndpointTypeDropDownTestData"));
			return true;
		} catch (Exception e) {
			Assert.fail("failed to modify endpoint");
		}
		return false;
	}

	public boolean isElementDisplayedOnTenantPageUserRolePermissionsPage(
			String sElementName) {
		try {
			switch (sElementName.toUpperCase()) {
			case "NAME":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addTenantPopupNameField"));
			case "HOSTNAME":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addTenantPopupHostnameField"));
			case "PARENT":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addTenantPopupParentField"));
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

	// public void enterTestData(String field, String roleName, Map<String,
	// String> getProps().getProperty,
	// Map<String, String> getProps().getProperty) {
	// selectDropDownByValue(getProps().getProperty("addSiteTenantDropDownForEndpoint"),
	// getProps().getProperty("addSiteTenantDropDownTestDataForEndpoint"));
	// if (roleName.equals("CI Customer Analyst")) {
	// enterText(getProps().getProperty("addSiteNameFieldForEndpoint"),
	// getProps().getProperty("addSiteNameTestDataForEndpoint1"));
	// enterText(getProps().getProperty("addSiteDescriptionFieldForEndpoint"),
	// getProps().getProperty("addSiteDescriptionTestDataForEndpoint1"));
	// } else if (roleName.equals("Partner Admin")) {
	// enterText(getProps().getProperty("addSiteNameFieldForEndpoint"),
	// getProps().getProperty("addSiteAdditonalNameTestDataForEndpoint2"));
	// enterText(getProps().getProperty("addSiteDescriptionFieldForEndpoint"),
	// getProps().getProperty("addSiteadditionalDescriptionTestDataForEndpoint2"));
	// }
	// enterText(getProps().getProperty("addSiteAddress1FieldForEndpoint"),
	// getProps().getProperty("addSiteAddress1TestDataForEndpoint"));
	// enterText(getProps().getProperty("addSiteAddress2FieldForEndpoint"),
	// getProps().getProperty("addSiteAddress2TestDataForEndpoint"));
	// enterText(getProps().getProperty("addSiteCityFieldForEndpoint"),
	// getProps().getProperty("addSiteCityTestDataForEndpoint"));
	// enterText(getProps().getProperty("addSiteProvinceFieldForEndpoint"),
	// getProps().getProperty("addSiteProvinceTestDataForEndpoint"));
	// enterText(getProps().getProperty("addSitePostalCodeFieldForEndpoint"),
	// getProps().getProperty("addSitePostalCodeTestDataForEndpoint"));
	// selectDropDownByValue(getProps().getProperty("addSiteCountryDropDownForEndpoint"),
	// getProps().getProperty("addSiteCountryDropDownTestDataForEndpoint"));
	// selectDropDownByValue(getProps().getProperty("addSiteTimeZoneDropDownForEndpoint"),
	// getProps().getProperty("addSiteTimeZoneDropDownTestDataForEndpoint"));
	// enterText(getProps().getProperty("addSiteAreaFieldForEndpoint"),
	// getProps().getProperty("addSiteAreaTestDataForEndpoint"));
	// checkCheckBox(getProps().getProperty("addSiteSubmeteringCheckBoxForEndpoint"));
	// checkCheckBox(getProps().getProperty("addSiteControlCheckBoxForEndpoint"));
	// checkCheckBox(getProps().getProperty("addSiteElectricVehiclesCheckBoxForEndpoint"));
	// //
	// endpointPage.checkCheckBox(getProps().getProperty("addSiteSolarPanelCheckBoxForEndpoint"));
	// explicitWait(10000);
	// //
	// endpointPage.enterText(getProps().getProperty("addSiteSolarPanelSystemSizeFieldForEndpoint"),
	// //
	// endpointPageObjectTestData("addSiteSolarPanelSystemSizeTestDataForEndpoint"));
	// enterText(getProps().getProperty("addSitePathsFieldForEndpoint"),
	// getProps().getProperty("addSitePathsFieldTestDataForEndpoint"));
	//
	// }
	//
	// public boolean associateSiteTenant(String name, Map<String, String>
	// getProps().getProperty,
	// Map<String, String> getProps().getProperty) {
	// try {
	// if (name.equals("userRolePage_tenantBot1")) {
	// selectDropDownByValue(getProps().getProperty("addSiteTenantDropDownForEndpoint"),
	// getProps().getProperty("addSiteTenantDropDownTestDataForEndpoint"));
	// } else if (name.equals("userRolePermissions_CICustomerAnalystSiteBot")) {
	//
	// }
	// } catch (Exception e) {
	//
	// }
	// return false;
	// }

	public boolean editSiteValues(Map<String, String> data) {
		Set<Entry<String, String>> entry = data.entrySet();
		Iterator<Entry<String, String>> ii = entry.iterator();
		Map.Entry<String, String> temp = null;
		try {
			while (ii.hasNext()) {
				temp = ii.next();
				switch (temp.getKey().toUpperCase()) {
				case "SITE PATH":
					return BaseUtil.getElementText(
							getProps().getProperty(
									"editUserPagePopupUserNameHeader"))
							.contains(temp.getValue().trim());

				}
			}
		} catch (Exception e) {
			logger.error("Failed to verify that Edit Site Element ["
					+ temp.getKey() + "].  see detail message: "
					+ e.getMessage());
			return false;
		}
		return false;
	}

	/*
	 * @auth: Gajendra Rawat This method is created to select the devices check
	 * boxes like HVAC or Light.
	 */
	@SuppressWarnings("unused")
	public boolean isDeviceSelected(String checkBoxTypeName, String siteId)
			throws SQLException, IOException {
		ManageTestData objDelTestdata = new ManageTestData();

		String endpointId = objDelTestdata.getID("Endpoint", siteId);
		String xPathendpointId = objDelTestdata.getID("Device", siteId,
				checkBoxTypeName);
		String actualXPath = null;
		if (siteId == null) {
			logger.info("Site ID is null");
		}
		switch (checkBoxTypeName.toUpperCase()) {
		case "LIGHTING":
			for (int i = 1; i < 10; i++) {
				actualXPath = "xpath===//*[contains(@id,'LIGHTING_" + siteId
						+ "_LCP" + i + "_anchor')]";
				BaseUtil.checkCheckBox(actualXPath);
				BaseUtil.waitForSpinner();
				if (BaseUtil.isElementClickable(getProps().getProperty(
						"lightEditBtn"))) {
					return true;
				}
				BaseUtil.clickAndWait(actualXPath);
				BaseUtil.waitForSpinner();
			}
			break;

		case "HIDE ON:LIGHTING":
			for (int i = 1; i < 10; i++) {
				actualXPath = "xpath===//*[contains(@id,'LIGHTING_" + siteId
						+ "_LCP" + i + "_anchor')]";
				BaseUtil.checkCheckBox(actualXPath);
				BaseUtil.waitForSpinner();
				if (BaseUtil.isElementClickable(getProps().getProperty(
						"lightEditBtn"))) {
					return true;
				}
				BaseUtil.click(actualXPath);
				BaseUtil.waitForSpinner();
				return false;
			}
			break;

		case "HVAC-1":
			if (endpointId == null) {
				logger.info("Endpoint ID is null");
				return false;
			}
			actualXPath = "xpath===//*[contains(@id,'HVAC_" + siteId + "_"
					+ checkBoxTypeName + "_" + xPathendpointId + "_anchor')]";
			BaseUtil.checkCheckBox(actualXPath);
			BaseUtil.explicitWait(3000);
			break;
		default:
			logger.error("Switch Case[" + checkBoxTypeName
					+ "] is not matched in class[" + getClass().getName()
					+ "] , Method["
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ "]");
			return false;
		}

		return false;
	}

	public boolean isObjectAvailable(String tabName, String buttonName,
			String userName) {
		return BaseUtil.isElementLoaded(BaseUtil.getLocator(getProps()
				.getProperty("editRegularHoursbtn")));
	}

	public void verifyTabsGrid(String tabName) {
		switch (tabName.toUpperCase()) {
		case "SITES":
			String totalsites = BaseUtil.getElementText(getProps().getProperty(
					"siteTotalItemsCount"));
			Assert.assertTrue(
					"Failed: GPUP-26329: Now ROLE_ADMIN users can see see other sites",
					totalsites.equalsIgnoreCase(" 1 of 1 items"));
			break;

		case "USERS":
			String totalusers = BaseUtil.getElementText(getProps().getProperty(
					"userTotalItemsCount"));
			Assert.assertTrue(
					"Failed: GPUP-26329: Now ROLE_ADMIN users can see see other users",
					totalusers.equalsIgnoreCase("1 - 1 of 1 items"));
			break;

		case "TENANTS":
			String totaltenants = BaseUtil.getElementText(getProps()
					.getProperty("tenantTotalItemsCount"));
			Assert.assertTrue(
					"Failed: GPUP-26329: Now ROLE_ADMIN users can see see other tenants",
					totaltenants.equalsIgnoreCase("1 - 1 of 1 items"));
			break;

		case "ENDPOINTS":
			String totalendpoints = BaseUtil.getElementText(getProps()
					.getProperty("endpointTotalItemsCount"));
			Assert.assertTrue(
					"Failed: GPUP-26329: Now ROLE_ADMIN users can see see other endpoints",
					totalendpoints.equalsIgnoreCase("1 - 1 of 1 items"));
			break;

		default:
			logger.error("Switch Case[" + tabName
					+ "] is not matched in class[" + getClass().getName()
					+ "] , Method["
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ "]");
			Assert.fail("Invalid Tab name : " + tabName + " selected");
		}
	}

	public void verifyTabsGridNew(String tabName, String userName)
			throws IOException, SQLException {
		String tenantname = null;
		switch (tabName.toUpperCase()) {
		case "SITES":
			tenantname = BaseUtil.getElementText(getProps().getProperty(
					"sitePageTenantDropdown"));
			String gridsitescount = BaseUtil.getElementText(getProps()
					.getProperty("siteTotalItemsCount"));
			String totalsitecount = gridsitescount.substring(
					gridsitescount.indexOf("of") + 2,
					gridsitescount.indexOf("items") - 1).trim();
			BaseUtil.instantClick(getProps().getProperty("tenantsTab"));
			BaseUtil.enterText(getProps().getProperty("tenantsSearchField"),
					tenantname);

			tp.selectTenantFromGrid_TenantPage(tenantname);
			String totalsitesoftenant = BaseUtil.getElementText(getProps()
					.getProperty("editTenantTotalSitesDisplay"));
			String totalsitescountoftenant = totalsitesoftenant.substring(0,
					totalsitesoftenant.indexOf("View") - 1).trim();
			Assert.assertTrue(
					"Failed:GPUP-30805: Now ROLE_ADMIN users can see see other sites",
					totalsitescountoftenant.equalsIgnoreCase(totalsitecount));
			BaseUtil.click(getProps().getProperty("TenantPopupXButton"));
			BaseUtil.explicitWait(3000);
			break;

		case "USERS":
			String gridusercount = BaseUtil.getElementText(getProps()
					.getProperty("tenantTotalItemsCount"));
			String totlalusercount = gridusercount.substring(
					gridusercount.indexOf("of") + 2,
					gridusercount.indexOf("items") - 1).trim();
			Assert.assertTrue(
					"Failed:GPUP-30805: Now ROLE_ADMIN users can see other users",
					totlalusercount.equalsIgnoreCase("1"));
			break;

		case "TENANTS":
			String gridtenantcount = BaseUtil.getElementText(getProps()
					.getProperty("tenantTotalItemsCount"));
			String totlaltenantcount = gridtenantcount.substring(
					gridtenantcount.indexOf("of") + 2,
					gridtenantcount.indexOf("items") - 1).trim();
			Assert.assertTrue(
					"Failed:GPUP-30805: Now ROLE_ADMIN users can see other tenants",
					totlaltenantcount.equalsIgnoreCase("1"));
			break;

		case "ENDPOINTS":
			String gridendpointcount = BaseUtil.getElementText(getProps()
					.getProperty("tenantTotalItemsCount"));
			String totlalendpointcount = gridendpointcount.substring(
					gridendpointcount.indexOf("of") + 2,
					gridendpointcount.indexOf("items") - 1).trim();

			ManageTestData objDelTestdata = new ManageTestData();
			int totalDBEndpoint = objDelTestdata
					.getAssocatedEndpointCount(userName);
			Assert.assertTrue(
					"Failed:GPUP-30805: Now ROLE_ADMIN users can see other tenants",
					totlalendpointcount.equalsIgnoreCase(String
							.valueOf(totalDBEndpoint)));
			break;

		default:
			logger.error("Switch Case[" + tabName
					+ "] is not matched in class[" + getClass().getName()
					+ "] , Method["
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ "]");
			Assert.fail("Invalid Tab name : " + tabName + " selected");
		}
	}
	
//	TODO : Added by sonu on 11 sep 2018.... curretly commenting it to avoid compilation issues....verify
	// Sonu
//		public void enterTestDataPartnerComm(String siteType, String tenantName, String list, Map<String, String> objectRepo) {
//			try {
//				Calendar calendar;
//				Date releaseDate;
//				DateFormat outputFormatter;
//				String enteredDate = null, flag = null, locator = null, testData = null;
//				calendar = Calendar.getInstance();
//				outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
//				releaseDate = calendar.getTime();
//				enteredDate = outputFormatter.format(releaseDate);
//				switch (list.toUpperCase()) {
//
//
//				// Site Test Data
//				case "TENANT DROPDOWN":
//					flag = "ComboBox";
//					clickAndWait(objectRepo.get("addSiteTenantDropDown"));
//					explicitWait(2000);
//					if (tenantName.equalsIgnoreCase("testTenant_1")) {
//						locator = objectRepo.get("addSiteTenantDropdownListField1");  
//						testData = objectRepo.get("tenantTestData_1");
//					} else if(tenantName.equalsIgnoreCase("testTenant_2")){
//						locator = objectRepo.get("addSiteTenantDropdownListField2");
//						testData = objectRepo.get("tenantTestData_2");
//					} else if (tenantName.equalsIgnoreCase("testTenant_3")){
//						locator = objectRepo.get("addSiteTenantDropdownListField3");
//						testData = objectRepo.get("tenantTestData_3");
//					} else if (tenantName.equalsIgnoreCase("testTenant_4")){
//						locator = objectRepo.get("addSiteTenantDropdownListField4");
//						testData = objectRepo.get("tenantTestData_4");
//					}
//					break;
//				case "SITE ID":
//					flag = "Field";
//					locator = objectRepo.get("addSiteNameField");
//					if (siteType.equalsIgnoreCase("inactive with commissioning Date") && tenantName.equalsIgnoreCase("testTenant_1")) {
//						testData = objectRepo.get("siteTestData_1");
//					} else if (siteType.equalsIgnoreCase("active with No commissioning Date") && tenantName.equalsIgnoreCase("testTenant_1")) {
//						testData = objectRepo.get("siteTestData_2");
//					} else if (siteType.equalsIgnoreCase("inactive with No commissioning Date") && tenantName.equalsIgnoreCase("testTenant_1")) {
//						testData = objectRepo.get("siteTestData_3");
//					} else if (siteType.equalsIgnoreCase("inactive with commissioning Date") && tenantName.equalsIgnoreCase("testTenant_2")) {
//						testData = objectRepo.get("siteTestData_4");
//					} else if (siteType.equalsIgnoreCase("active with No commissioning Date") && tenantName.equalsIgnoreCase("testTenant_2")) {
//						testData = objectRepo.get("siteTestData_5");
//					} else if (siteType.equalsIgnoreCase("inactive with No commissioning Date") && tenantName.equalsIgnoreCase("testTenant_2")) {
//						testData = objectRepo.get("siteTestData_6");
//					} else if (siteType.equalsIgnoreCase("inactive with commissioning Date") && tenantName.equalsIgnoreCase("testTenant_3")) {
//						testData = objectRepo.get("siteTestData_7");
//					} else if (siteType.equalsIgnoreCase("active with No commissioning Date") && tenantName.equalsIgnoreCase("testTenant_3")) {
//						testData = objectRepo.get("siteTestData_8");
//					} else if (siteType.equalsIgnoreCase("inactive with No commissioning Date") && tenantName.equalsIgnoreCase("testTenant_3")) {
//						testData = objectRepo.get("siteTestData_9");
//					} else if (siteType.equalsIgnoreCase("inactive with commissioning Date") && tenantName.equalsIgnoreCase("testTenant_4")) {
//						testData = objectRepo.get("siteTestData_10");
//					} else if (siteType.equalsIgnoreCase("active with No commissioning Date") && tenantName.equalsIgnoreCase("testTenant_4")) {
//						testData = objectRepo.get("siteTestData_11");
//					} else if (siteType.equalsIgnoreCase("inactive with No commissioning Date") && tenantName.equalsIgnoreCase("testTenant_4")) {
//						testData = objectRepo.get("siteTestData_12");
//					}
//
//					break;
//				case "SITE DESCRIPTION":
//					flag = "Field";
//					locator = objectRepo.get("addSiteDescriptionField");
//					if (siteType.equalsIgnoreCase("inactive with commissioning Date") && tenantName.equalsIgnoreCase("testTenant_1")) {
//						testData = objectRepo.get("siteTestData_1");
//					} else if (siteType.equalsIgnoreCase("active with No commissioning Date") && tenantName.equalsIgnoreCase("testTenant_1")) {
//						testData = objectRepo.get("siteTestData_2");
//					} else if (siteType.equalsIgnoreCase("inactive with No commissioning Date") && tenantName.equalsIgnoreCase("testTenant_1")) {
//						testData = objectRepo.get("siteTestData_3");
//					} else if (siteType.equalsIgnoreCase("inactive with commissioning Date") && tenantName.equalsIgnoreCase("testTenant_2")) {
//						testData = objectRepo.get("siteTestData_4");
//					} else if (siteType.equalsIgnoreCase("active with No commissioning Date") && tenantName.equalsIgnoreCase("testTenant_2")) {
//						testData = objectRepo.get("siteTestData_5");
//					} else if (siteType.equalsIgnoreCase("inactive with No commissioning Date") && tenantName.equalsIgnoreCase("testTenant_2")) {
//						testData = objectRepo.get("siteTestData_6");
//					} else if (siteType.equalsIgnoreCase("inactive with commissioning Date") && tenantName.equalsIgnoreCase("testTenant_3")) {
//						testData = objectRepo.get("siteTestData_7");
//					} else if (siteType.equalsIgnoreCase("active with No commissioning Date") && tenantName.equalsIgnoreCase("testTenant_3")) {
//						testData = objectRepo.get("siteTestData_8");
//					} else if (siteType.equalsIgnoreCase("inactive with No commissioning Date") && tenantName.equalsIgnoreCase("testTenant_3")) {
//						testData = objectRepo.get("siteTestData_9");
//					} else if (siteType.equalsIgnoreCase("inactive with commissioning Date") && tenantName.equalsIgnoreCase("testTenant_4")) {
//						testData = objectRepo.get("siteTestData_10");
//					} else if (siteType.equalsIgnoreCase("active with No commissioning Date") && tenantName.equalsIgnoreCase("testTenant_4")) {
//						testData = objectRepo.get("siteTestData_11");
//					} else if (siteType.equalsIgnoreCase("inactive with No commissioning Date") && tenantName.equalsIgnoreCase("testTenant_4")) {
//						testData = objectRepo.get("siteTestData_12");
//					}
//
//					break;
//				case "ADDRESS1":
//					flag = "Field";
//					locator = objectRepo.get("addSiteAddress1Field");
//					testData = objectRepo.get("addSiteAddress1FieldTestData");
//					break;
//				case "CITY":
//					flag = "Field";
//					locator = objectRepo.get("addSiteCityField");
//					testData = objectRepo.get("addSiteCityFieldTestData");
//					break;
//				case "STATE":
//					flag = "Dropdown";
//					locator = objectRepo.get("addSiteProvinceField");
//					testData = objectRepo.get("addSiteStateFieldTestData");
//					break;
//				case "POSTAL CODE":
//					flag = "Field";
//					locator = objectRepo.get("addSitePostalCodeField");
//					testData = objectRepo.get("addSitePostalCodeFieldTestData");
//					break;
//				case "COUNTRY":
//					flag = "Dropdown";
//					locator = objectRepo.get("addSiteCountryDropDown");
//					testData = objectRepo.get("addSiteCountryFieldTestData");
//					break;
//				case "COMMISSION DATE":
//					flag = "Field";
//					locator = objectRepo.get("addSiteCommissionDateField");
//					if (siteType.equalsIgnoreCase("inactive with commissioning Date")
//							&& tenantName.equalsIgnoreCase("testTenant_1")) {
//						testData = objectRepo.get("commissioningDateTestData").replace("#index#", enteredDate);
//					} else if (siteType.equalsIgnoreCase("inactive with commissioning Date")
//							&& tenantName.equalsIgnoreCase("testTenant_2")) {
//						testData = objectRepo.get("commissioningDateTestData").replace("#index#", enteredDate);
//					} else if (siteType.equalsIgnoreCase("inactive with commissioning Date")
//							&& tenantName.equalsIgnoreCase("testTenant_3")) {
//						testData = objectRepo.get("commissioningDateTestData").replace("#index#", enteredDate);
//					} else if (siteType.equalsIgnoreCase("inactive with commissioning Date")
//							&& tenantName.equalsIgnoreCase("testTenant_4")) {
//						testData = objectRepo.get("commissioningDateTestData").replace("#index#", enteredDate);
//					}
//					break;
//
//				default:
//					logger.error("Switch Case[" + list + "] is not matched in class[" + getClass().getName() + "] , Method["
//							+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
//				}
//
////				testData = testData.replace("#index#", enteredDate);
//
//				if (flag.equalsIgnoreCase("Field")) {
//					enterText(locator, testData);
//				} else if (flag.equalsIgnoreCase("Dropdown")) {
//					selectDropDownByValue(locator, testData);
//				} else if (flag.equalsIgnoreCase("ComboBox")) {
//					selectComboBoxDropDownByValue(locator, testData);
//				}
//			} catch (Exception e) {
//				logger.error("Failed to enter text in field :" + list + " see detail message : " + e.getMessage());
//			}
//		}
//		
//		public void checkCheckBox(List<String> checkBoxList, Map<String, String> objectRepo) {
//			String message = null, locator = null;
//			for (String checkbox : checkBoxList) {
//				message = "[" + checkbox + "] is not available in the general template";
//				try {
//					switch (checkbox.toUpperCase()) {
//					case "ACTIVE":
//						locator = objectRepo.get("addSiteActiveCheckBox");
//						break;
//					default:
//						logger.error("Switch Case[" + checkbox + "] is not matched in class[" + getClass().getName()
//								+ "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
//						Assert.fail("Invalid element name: " + checkbox + " selected");
//					}
//
//					checkCheckBox(locator);
//				} catch (Exception e) {
//					logger.error("Failed to Check the Checkbox: " + checkbox + " see detail message: " + e.getMessage());
//					Assert.fail(message);
//				}
//			}
//		}
//		
//		public boolean associate_TenantsAndSites(String fields, String siteName, Map<String, String> objectRepo,
//				Map<String, String> userRolePermissionsPageTestData) {
//			try {
//				GPConnection gpcon = new GPConnection();
//				GPDataBaseUtil dbutil = new GPDataBaseUtil();
//				Connection conn = gpcon.getConnection(TestBase.getDbUrl(), TestBase.getDbUsername(),
//						TestBase.getDbPassword());
//				switch (fields.toUpperCase()) {
//				case "ASSOCIATION TENANT/SITE":
//					if (siteName.equalsIgnoreCase("testSite_12")) {
//						explicitWait(1000);
//						String getPremisesIdQuery = "SELECT premises_id FROM premises WHERE name='testSite_12'";
//						logger.info("getPremisesIdQuery : " + getPremisesIdQuery);
//						String premisesId = dbutil.getIdFromDB(getPremisesIdQuery, conn);
//						if (null == premisesId) {
//							Assert.fail("Given site" + siteName + "is not available");
//						}
//						String premisesName = siteName;
//						String premisesNameWithId = premisesName.concat("/").concat(premisesId);
//						enterText(objectRepo.get("associationSiteIdForEndpoint"), premisesNameWithId);
//						explicitWait(2000);
//						return true;
//					} else {
//						clickAndWait(objectRepo.get("modifyEndpointTenantDropDown"));
//						explicitWait(1000);
//						if (fields.equalsIgnoreCase("testEndpoint_1")) {
//							selectComboBoxDropDownByValue(objectRepo.get("modifyEndpointTenantDropDown1"),
//									userRolePermissionsPageTestData.get("modifyEndpointTenantDropDownTestData1").trim());
//						} else if (fields.equalsIgnoreCase("testEndpoint_2")) {
//							selectComboBoxDropDownByValue(objectRepo.get("modifyEndpointTenantDropDown1"),
//									userRolePermissionsPageTestData.get("modifyEndpointTenantDropDownTestData1").trim());
//						} else if (fields.equalsIgnoreCase("testEndpoint_3")) {
//							selectComboBoxDropDownByValue(objectRepo.get("modifyEndpointTenantDropDown1"),
//									userRolePermissionsPageTestData.get("modifyEndpointTenantDropDownTestData1").trim());
//						} else if (fields.equalsIgnoreCase("testEndpoint_4")) {
//							selectComboBoxDropDownByValue(objectRepo.get("modifyEndpointTenantDropDown2"),
//									userRolePermissionsPageTestData.get("modifyEndpointTenantDropDownTestData2").trim());
//						} else if (fields.equalsIgnoreCase("testEndpoint_5")) {
//							selectComboBoxDropDownByValue(objectRepo.get("modifyEndpointTenantDropDown2"),
//									userRolePermissionsPageTestData.get("modifyEndpointTenantDropDownTestData2").trim());
//						} else if (fields.equalsIgnoreCase("testEndpoint_6")) {
//							selectComboBoxDropDownByValue(objectRepo.get("modifyEndpointTenantDropDown2"),
//									userRolePermissionsPageTestData.get("modifyEndpointTenantDropDownTestData2").trim());
//						} else if (fields.equalsIgnoreCase("testEndpoint_7")) {
//							selectComboBoxDropDownByValue(objectRepo.get("modifyEndpointTenantDropDown3"),
//									userRolePermissionsPageTestData.get("modifyEndpointTenantDropDownTestData3").trim());
//						} else if (fields.equalsIgnoreCase("testEndpoint_8")) {
//							selectComboBoxDropDownByValue(objectRepo.get("modifyEndpointTenantDropDown3"),
//									userRolePermissionsPageTestData.get("modifyEndpointTenantDropDownTestData3").trim());
//						} else if (fields.equalsIgnoreCase("testEndpoint_9")) {
//							selectComboBoxDropDownByValue(objectRepo.get("modifyEndpointTenantDropDown3"),
//									userRolePermissionsPageTestData.get("modifyEndpointTenantDropDownTestData3").trim());
//						}
//
//						String getPremisesIdQuery = "SELECT premises_id FROM premises WHERE name='siteName'";
//						logger.info("getPremisesIdQuery : " + getPremisesIdQuery);
//						String premisesId = dbutil.getIdFromDB(getPremisesIdQuery, conn);
//						if (null == premisesId) {
//							Assert.fail("Given site" + siteName + "is not available");
//						}
//						String premisesName = siteName;
//						String premisesNameWithId = premisesName.concat("/").concat(premisesId);
//						enterText(objectRepo.get("modifyEndpointAssociateSiteId"), premisesNameWithId);
//						explicitWait(2000);
//						return true;
//					}
//
//				}
//			} catch (Exception e) {
//				logger.error("Failed to modify field [" + fields + "] in endpoint page. , see detail error message \n"
//						+ e.getMessage());
//				Assert.fail("Failed to modify field [" + fields + "] in endpoint page. see detail error message \n"
//						+ e.getMessage());
//			}
//			return false;
//		}
//
//		public boolean verifyExpectedEndpoint(String endpointName, Map<String, String> userRolePermissionsPageObjectRepo) {
//			boolean bResult = false;
//			try {
//				List<WebElement> lstEndpointNames = getMultipleElementsAfterLoaded(
//						userRolePermissionsPageObjectRepo.get("endpointGridSerialColumn"));
//				for (WebElement webElement : lstEndpointNames) {
//					if (webElement.getText().trim().equalsIgnoreCase(endpointName)) {
//						bResult = true;
//						return true;
//					}
//				}
//				if (!bResult) {
//					Assert.fail("Given test endpoint is not available");
//				}
//				logger.debug("endpoint :" + endpointName + " is selected.");
//
//			} catch (Exception e) {
//				logger.error("Failed to verify endpoint: " + endpointName
//						+ " from endpoint grid , see detail error message: " + e.getStackTrace().toString());
//				Assert.fail("Failed to verify endpoint : " + endpointName + ", see detail error message: \n"
//						+ e.getStackTrace().toString());
//			} finally {
//				waitForSpinner();
//			}
//			return bResult;
//		}
//		
//		public boolean verifyExpectedSite(String siteName, Map<String, String> userRolePermissionsPageObjectRepo) {
//			int iTotalNumOfPage = 0;
//			boolean bResult = false;
//			try {
//				iTotalNumOfPage = Integer.parseInt(
//						getElementText(userRolePermissionsPageObjectRepo.get("sitePaginationTotalPages")).trim().replace("/", "").trim());
//				if (iTotalNumOfPage == 1)
//					iTotalNumOfPage++;
//				for (int iNumPage = 1; iNumPage <= iTotalNumOfPage - 1; iNumPage++) {
//					List<WebElement> lstSiteNames = getMultipleElementsAfterLoaded(
//							userRolePermissionsPageObjectRepo.get("siteGridNameColumn"));
//					for (WebElement webElement : lstSiteNames) {
//						if (webElement.getText().trim().equalsIgnoreCase(siteName)) {
//							waitForSpinner();
//							bResult = true;
//							return true;
//						}
//					}
//					if (!bResult) {
//						Assert.fail("Given test site is not available");
//					}
//				}
//				logger.debug("site :" + siteName + " is selected.");
//			} catch (Exception e) {
//				logger.error("Failed to select site: " + siteName + " from site grid , see detail error message: "
//						+ e.getStackTrace().toString());
//				Assert.fail("Failed to select the site : " + siteName + ", see detail error message: \n"
//						+ e.getStackTrace().toString());
//			} finally {
//				waitForSpinner();
//			}
//			return bResult;
//		}
//
//		public boolean verifypopupMessage(String popupMessage, Map<String, String> userRolePermissionsPageObjectRepo) {
//			try {
//				 return getElementText(userRolePermissionsPageObjectRepo.get("popupErrorMessage")).equalsIgnoreCase(popupMessage);
//			} catch (Exception e) {
//				Assert.fail("failed to verify popup error message");
//			}
//			return false;
//		}
//
//		public boolean verifSuccussfullMessage(String succussfullMessage,
//				Map<String, String> userRolePermissionsPageObjectRepo) {
//			try {
//				 return getElementText(userRolePermissionsPageObjectRepo.get("popupErrorMessage")).equalsIgnoreCase(succussfullMessage);
//			} catch (Exception e) {
//				Assert.fail("failed to verify succussfull message for endpoint");
//			}
//			return false;
//		}

}
