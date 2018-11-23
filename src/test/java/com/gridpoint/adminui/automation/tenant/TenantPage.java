package com.gridpoint.adminui.automation.tenant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.util.BaseUtil;

public class TenantPage extends CommonInit {
	private static Logger logger = Logger.getLogger(TenantPage.class);

	public void verifySorting_TenantPage(String sSortingType, String sColName) {
		List<String> listColumnValues = null;
		try {
			switch (sColName.toUpperCase()) {
			case "NAME":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty("tenantGridNameHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty("tenantGridNameHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps().getProperty("tenantGridNameHeader"));
				break;
			case "TENANT TYPE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty("tenantGridTenantTypeHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty("tenantGridTenantTypeHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps().getProperty("tenantGridTenantTypeHeader"));
				break;
			case "PARENT":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty("tenantGridParentHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty("tenantGridParentHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps().getProperty("tenantGridParentHeader"));
				break;
			case "PARENT TENANT TYPE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty("tenantGridParentTenantTypeHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty("tenantGridParentTenantTypeHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps().getProperty("tenantGridParentTenantTypeHeader"));
				break;
			case "ACTIVE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty("tenantGridActiveHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty("tenantGridActiveHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps().getProperty("tenantGridActiveHeader"));
				break;
			default:
				logger.error("Switch Case[" + sColName + "] is not matched in class[" + getClass().getName()
						+ "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				Assert.fail("Invalid column name: " + sColName
						+ ", Please pass valid column name from feature file or test class.");
			}

			Assert.assertTrue(sColName + " is not in " + sSortingType + " order",
					BaseUtil.isSorted(sSortingType, listColumnValues));

		} catch (Exception e) {
			logger.error("Failed to sort the column :" + sColName + " Sorting order:" + sSortingType
					+ " , detail message: " + e.getStackTrace().toString());
			Assert.fail("Failed to sort the column :" + sColName + " Sorting order:" + sSortingType
					+ " , detail message: " + e.getStackTrace().toString());
		}
	}

	public boolean clickOperationOnTenantDetailPopup(String closeButton) {
		try {
			switch (closeButton.toUpperCase()) {
			case "X":
				BaseUtil.clickAndWait(getProps().getProperty("addTenantPopupXButton"));
				return true;
			case "DEFAULT VALUE NAVIGATION":
				BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantGridDefaultPageValue"));
				return true;
			default:
				logger.error("Switch Case[" + closeButton + "] is not matched in class[" + getClass().getName()
						+ "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to click on button :" + closeButton + " , detail message: " + e.getMessage());
			return false;
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public void clickPaginationTenantPage(String tenantLinkName) {
		try {
			switch (tenantLinkName.toUpperCase()) {
			case "NEXT":
				Assert.assertTrue("Required data is not present. Next button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty("tenantPaginationNext")));
				BaseUtil.click(getProps().getProperty("tenantPaginationNext"));
				break;
			case "PREV":
				Assert.assertTrue("Required data is not present. Prev button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty("tenantPaginationPrev")));
				BaseUtil.click(getProps().getProperty("tenantPaginationPrev"));
				break;
			case "LAST":
				Assert.assertTrue("Required data is not present. Last button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty("tenantPaginationLast")));
				BaseUtil.click(getProps().getProperty("tenantPaginationLast"));
				break;
			case "FIRST":
				Assert.assertTrue("Required data is not present. Last button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty("tenantPaginationFirst")));
				BaseUtil.click(getProps().getProperty("tenantPaginationFirst"));
				break;
			default:
				logger.error("Switch Case[" + tenantLinkName + "] is not matched in class[" + getClass().getName()
						+ "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				Assert.fail("Invalid button clicked");
			}
		} catch (Exception e) {
			logger.error("Failed to click on link :" + tenantLinkName + " , detail message: " + e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	public void verifyTenantDetailGridPagination(String task) {
		String totalPages = null;
		String currentPage = null;
		try {
			switch (task.toUpperCase()) {
			case "NEXT":
				String pageNumber = BaseUtil.getElementAttribute(getProps().getProperty("tenantPaginationCurrentPage"),
						"value");
				Assert.assertTrue("Next page is not displayed", Integer.parseInt(pageNumber) > 1);
				break;
			case "PREVIOUS":
				totalPages = BaseUtil.getElementText(getProps().getProperty("tenantPaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1).trim();
				currentPage = BaseUtil.getElementAttribute(getProps().getProperty("tenantPaginationCurrentPage"),
						"value");
				Assert.assertTrue("Previous page is not displayed",
						Integer.parseInt(currentPage) < Integer.parseInt(totalPages));
				break;
			case "LAST":
				totalPages = BaseUtil.getElementText(getProps().getProperty("tenantPaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1).trim();
				currentPage = BaseUtil.getElementAttribute(getProps().getProperty("tenantPaginationCurrentPage"),
						"value");
				Assert.assertTrue("Last page is not displayed",
						(Integer.parseInt(totalPages) == Integer.parseInt(currentPage)
								&& !BaseUtil.verifyElementEnabled(getProps().getProperty("tenantPaginationNext"))
								&& !BaseUtil.verifyElementEnabled(getProps().getProperty("tenantPaginationLast"))));
				break;
			case "FIRST":
				currentPage = BaseUtil.getElementAttribute(getProps().getProperty("tenantPaginationCurrentPage"),
						"value");
				Assert.assertTrue("First page is not displayed",
						(Integer.parseInt(currentPage) == 1
								&& !BaseUtil.verifyElementEnabled(getProps().getProperty("tenantPaginationFirst"))
								&& !BaseUtil.verifyElementEnabled(getProps().getProperty("tenantPaginationPrev"))));
				break;
			default:
				logger.error("Switch Case[" + task + "] is not matched in class[" + getClass().getName() + "] , Method["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				Assert.fail("Pagination button is not availabe in the grid");
			}
		} catch (Exception e) {
			logger.error("Failed to click on link :" + task + " , detail message: " + e.getMessage());
			Assert.fail(e.getMessage());
		} finally {
			BaseUtil.explicitWait(2000);
		}
	}

	public boolean setRandomPage(int iTotalPage, int iPageNum) {
		String sArrow = "";
		int iDiff = 0;
		try {
			int iCurrentPage = Integer.parseInt(
					BaseUtil.getElementAttribute(getProps().getProperty("tenantPaginationCurrentPage"), "value"));
			if (iCurrentPage >= iPageNum) {
				iDiff = iCurrentPage - iPageNum;
				sArrow = "DOWN";
			} else {
				iDiff = iPageNum - iCurrentPage;
				sArrow = "UP";
			}
			WebElement element = BaseUtil.getElementAfterLoaded(getProps().getProperty("tenantPaginationCurrentPage"));
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
			logger.error("Some error occured while setting the number[" + iPageNum + "] in page number input box : "
					+ e.getMessage());
			return false;
		}

	}

	public boolean clickOperationOnTenantPage(String sButtonName) {
		try {
			String label;
			String locator = null;
			switch (sButtonName.toUpperCase()) {
			case "ADD TENANT":
				locator = getProps().getProperty("addTenantButton");
				break;
			case "SITES":
				locator = getProps().getProperty("sitesTab");
				break;
			case "TENANTS":
				locator = getProps().getProperty("tenantTab");
				break;
			case "DELETE TENANT":
				locator = getProps().getProperty("deleteTenantButton");
				break;
			case "TENANT:DELETE_OK":
				locator = getProps().getProperty("deleteTenantOkConfirmationButton");
				break;
			case "ADD SITE":
				BaseUtil.explicitWait(2000);
				BaseUtil.clickAndWait(getProps().getProperty("addSiteButton"));
				BaseUtil.explicitWait(3000);
				return true;
			case "ADD ENDPOINT":
				locator = getProps().getProperty("addEndpointButton");
				break;
			case "SAVE:ENDPOINT":
				locator = getProps().getProperty("addSiteSaveButton");
				break;
			case "SAVE":
				BaseUtil.clickAndWait(getProps().getProperty("addTenantPopupSaveButton"));
				BaseUtil.explicitWait(1000);
				BaseUtil.waitForSpinner();
				return true;
			case "CANCEL":
				locator = getProps().getProperty("addTenantPopupCancelButton");
				break;
			case "SEARCH":
				locator = getProps().getProperty("tenantSearchButton");
				break;
			case "DELETE":
				locator = getProps().getProperty("deleteTenantButton");
				break;
			case "OK_DELETE":
				locator = getProps().getProperty("deleteTenantOkConfirmationButton");
				break;
			case "CANCEL_DELETE":
				locator = getProps().getProperty("DeleteTenantCancelConfirmationButton");
				break;
			case "X":
				locator = getProps().getProperty("addTenantPopupXButton");
				break;
			case "HIDE INACTIVE:UNCHECKED":
				label = BaseUtil.getElementText(getProps().getProperty("hideInactiveCheckBox"));
				if (label.equals(sButtonName)) {
					Assert.assertTrue("The label text is given as =" + label, true);
				}
				BaseUtil.unCheckCheckBox(getProps().getProperty("hideInactiveCheckBox"));
				return true;
			case "HIDE INACTIVE:CHECKED":
				label = BaseUtil.getElementText(getProps().getProperty("hideInactiveLabel"));
				if (label.equals(sButtonName)) {
					Assert.assertTrue("The label text is given as =" + label, true);
				}
				BaseUtil.checkCheckBox(getProps().getProperty("hideInactiveCheckBox"));
				return true;
			case "CREATE DATAFIX":
				locator = getProps().getProperty("createDataFixBtn");
				BaseUtil.waitForSpinner();
				break;
			case "UPLOAD:TEMPLATE":
				locator = getProps().getProperty("uploadTemplateBtn");
				BaseUtil.waitForSpinner();
				break;
			case "PERMISSION":
				BaseUtil.clickAndWait(getProps().getProperty("editUserModelPermissionAccordion"));
				BaseUtil.explicitWait(1000);
				return true;
			case "ADD ALL":
				BaseUtil.explicitWait(3000);
				BaseUtil.clickAndWait(getProps().getProperty("AddAllButton"));
				BaseUtil.explicitWait(1000);
				return true;
			case "PERMISSION:SAVE AND CLOSE":
				locator = getProps().getProperty("saveAndClosePermissionUserBtn");
				break;
			case "BACK TO TENANTS":
				locator = getProps().getProperty("editTenantPopupBackButton");
				break;

			default:
				logger.error("Switch Case[" + sButtonName + "] is not matched in class[" + getClass().getName()
						+ "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return false;
			}

			BaseUtil.clickAndWait(locator);
			return true;
		} catch (Exception e) {
			logger.error("Failed to click on button :" + sButtonName + " , detail message: " + e.getMessage());
			return false;
		}
	}

	// Moved from base utils (above method has some more switch cases and remaining
	// are duplicate so can be deleted)
	// public static boolean clickOperationOnTenantPage(String sButtonName) {
	// try {
	// String label;
	// switch (sButtonName.toUpperCase()) {
	// case "ADD TENANT":
	// BaseUtil.clickAndWait(getProps().getProperty("addTenantButton"));
	// return true;
	// case "ADD SITE":
	// BaseUtil.clickAndWait(getProps().getProperty("addSiteButton"));
	// BaseUtil.explicitWait(3000);
	// return true;
	// case "ADD ENDPOINT":
	// BaseUtil.clickAndWait(getProps().getProperty(
	// "addEndpointButton"));
	// return true;
	// case "SAVE:ENDPOINT":
	// BaseUtil.clickAndWait(getProps().getProperty(
	// "addSiteSaveButton"));
	// return true;
	// case "SAVE":
	// BaseUtil.clickAndWait(getProps().getProperty(
	// "addTenantPopupSaveButton"));
	// BaseUtil.explicitWait(1000);
	// return true;
	// case "CANCEL":
	// BaseUtil.clickAndWait(getProps().getProperty(
	// "addTenantPopupCancelButton"));
	// return true;
	// case "SEARCH":
	// BaseUtil.clickAndWait(getProps().getProperty(
	// "tenantSearchButton"));
	// return true;
	// case "DELETE":
	// BaseUtil.clickAndWait(getProps().getProperty(
	// "tenantDeleteButton"));
	// return true;
	// case "CANCEL_DELETE":
	// BaseUtil.clickAndWait(getProps().getProperty(
	// "DeleteTenantCancelConfirmationButton"));
	// return true;
	// case "X":
	// BaseUtil.clickAndWait(getProps().getProperty(
	// "addTenantPopupXButton"));
	// return true;
	// case "OK_DELETE":
	// BaseUtil.clickAndWait(getProps().getProperty(
	// "DeleteTenantOkConfirmationButton"));
	// return true;
	// case "HIDE INACTIVE:UNCHECKED":
	// label = BaseUtil.getElementText(getProps().getProperty(
	// "hideInactiveCheckBox"));
	// if (label.equals(sButtonName)) {
	// Assert.assertTrue("The label text is given as =" + label,
	// true);
	// }
	// BaseUtil.unCheckCheckBox(getProps().getProperty(
	// "hideInactiveCheckBox"));
	// return true;
	// case "HIDE INACTIVE:CHECKED":
	// label = BaseUtil.getElementText(getProps().getProperty(
	// "hideInactiveLabel"));
	// if (label.equals(sButtonName)) {
	// Assert.assertTrue("The label text is given as =" + label,
	// true);
	// }
	// BaseUtil.checkCheckBox(getProps().getProperty(
	// "hideInactiveCheckBox"));
	// return true;
	// case "CREATE DATAFIX":
	// BaseUtil.clickAndWait(getProps()
	// .getProperty("createDataFixBtn"));
	// BaseUtil.waitForSpinner();
	// return true;
	// case "UPLOAD:TEMPLATE":
	// BaseUtil.clickAndWait(getProps().getProperty(
	// "uploadTemplateBtn"));
	// BaseUtil.waitForSpinner();
	// return true;
	// default:
	// logger.error("Switch Case["
	// + sButtonName
	// + "] is not matched in class["
	// + new Object().getClass().getName()
	// + "] , Method["
	// + Thread.currentThread().getStackTrace()[1]
	// .getMethodName() + "]");
	// return false;
	// }
	// } catch (Exception e) {
	// logger.error("Failed to click on button :" + sButtonName
	// + " , detail message: " + e.getMessage());
	// return false;
	// }
	// }

	public boolean isElementDisplayedOnTenantPage(String sElementName) {

		try {
			Select dropdown;
			List<WebElement> optionSelect;
			String locator = null;
			switch (sElementName.toUpperCase()) {
			case "NAME":
				locator = getProps().getProperty("addTenantPopupNameField");
				break;
			case "PARENT":
				locator = getProps().getProperty("addTenantPopupParentField");
				break;
			case "TENANT TYPE":
				locator = getProps().getProperty("addTenantPopupTenantTypeField");
				break;
			case "ACTIVE":
				locator = getProps().getProperty("addTenantPopupActiveCheckBox");
				break;
			case "LABEL:ACTIVE SITES":
				locator = getProps().getProperty("editTenantActiveSitesLable");
				break;
			case "LABEL:TOTAL SITES":
				locator = getProps().getProperty("editTenantActiveSitesDisplay");
				break;
			case "COUNT:ACTIVE SITES":
				locator = getProps().getProperty("editTenantTotalSitesLable");
				break;
			case "COUNT:TOTAL SITES":
				locator = getProps().getProperty("editTenantTotalSitesDisplay");
				break;
			case "SAVE":
				locator = getProps().getProperty("addTenantPopupSaveButton");
				break;
			case "CANCEL":
				locator = getProps().getProperty("addTenantPopupCancelButton");
				break;
			case "EDIT TENANT:SAVE":
				locator = getProps().getProperty("addTenantPopupSaveButton");
				break;
			case "EDIT TENANT:CANCEL":
				locator = getProps().getProperty("addTenantPopupCancelButton");
				break;
			case "SUBTENANT NAME":
				locator = getProps().getProperty("editTenantSubTenantSectionNameColumn");
				break;
			case "SUBTENANT TYPE":
				locator = getProps().getProperty("editTenantPopupSubtenantTypeColumn");
				break;
			case "DELETE":
				locator = getProps().getProperty("deleteTenantButton");
				break;
			case "HOSTNAME":
				locator = getProps().getProperty("editTenantPopupHostNameColumn");
			case "ROOT":
				dropdown = new Select(getDriver().findElement(By.xpath("//*[@id='selectTenantType']/select")));
				optionSelect = dropdown.getOptions();
				boolean rootFlag = true;
				for (WebElement getOption : optionSelect) {
					rootFlag = getOption.getText().equals(sElementName);
					if (!rootFlag) {
						logger.info("The " + sElementName + " element is not available in Tenant Type dropdown.");
					}
				}
				return !rootFlag;
			case "PARENT OF GRIDPOINT":
				dropdown = new Select(getDriver().findElement(By.xpath("//*[@id='selectTenantType']/select")));
				optionSelect = dropdown.getOptions();
				boolean parentOfGridpoint = true;
				for (WebElement getOption : optionSelect) {
					parentOfGridpoint = getOption.getText().equals(sElementName);
					if (!parentOfGridpoint) {
						logger.info("The " + sElementName + " element is not available in Tenant Type dropdown.");
					}
				}
				return !parentOfGridpoint;

			default:
				logger.error("Switch Case[" + sElementName + "] is not matched in class[" + getClass().getName()
						+ "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return false;
			}

			if (sElementName.equalsIgnoreCase("Delete") || sElementName.equalsIgnoreCase("Save")) {
				return BaseUtil.instantElementCheck("Display", locator);
			} else {
				return BaseUtil.verifyElementDisplayed(locator);
			}

		} catch (Exception e) {
			logger.error(
					"Failed to displayed the element :" + sElementName + " see detail message : " + e.getMessage());
			return false;
		}
	}

	public void verifyDropdownStringValues(String fieldName, List<String> listDropdownValues) {
		String locator = null;
		List<String> actualValueList = null, comparableList = null;
		try {
			switch (fieldName.toUpperCase()) {
			case "TENANT TYPE":
				locator = getProps().getProperty("addTenantPopupTenantTypeField");
				break;

			default:
				logger.error("Switch Case[" + fieldName + "] is not matched in class[" + getClass().getName()
						+ "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
			}
			actualValueList = BaseUtil.getDropDownText(locator);
			comparableList = new ArrayList<String>(listDropdownValues);
			logger.info("comparableList dropdown  values:" + comparableList);
			logger.info("actualValueList dropdown  values:" + actualValueList);
			Collections.sort(comparableList);
			Collections.sort(actualValueList);

			Assert.assertTrue("Failed to verify the dropdown values of [" + fieldName + "] field",
					comparableList.equals(actualValueList));

		} catch (Exception e) {
			Assert.fail("Failed to verify dropdown values");
		}

	}

	public void enterText_TenantPage(String sText, String sElementName) {
		String sCaseOptions = "sElementName";
		try {
			String locator = null;
			String flag = null;
			switch (sElementName.toUpperCase()) {

			case "NAME":
				flag = "Field";
				locator = getProps().getProperty("addTenantPopupNameField");
				break;
			case "SELECT_TENANT_PARENT":
				flag = "ComboBox";
				BaseUtil.clickAndWait(getProps().getProperty("addTenantPopupParentField"));
				BaseUtil.explicitWait(2000);
				locator = getProps().getProperty("addTenantPopupTenantParentList");
				break;
			case "SELECT_PARENT":
				flag = "ComboBox";
				BaseUtil.clickAndWait(getProps().getProperty("addTenantPopupParentField"));
				BaseUtil.explicitWait(2000);
				locator = getProps().getProperty("addTenantPopupParentList");
				break;
			case "TENANT_SEARCH":
				flag = "SearchField";
				locator = getProps().getProperty("tenantsSearchField");
				break;
			case "USER_SEARCH":
				flag = "SearchField";
				locator = getProps().getProperty("userSearchField");
				break;
			case "TENANT TYPE":
				flag = "Dropdown";
				locator = getProps().getProperty("addTenantPopupTenantTypeField");
				break;
			case "PARENT":
				flag = "Dropdown";
				locator = getProps().getProperty("parentDropDown");
				break;

			default:
				logger.error("Switch Case[" + sElementName + "] is not matched in class[" + getClass().getName()
						+ "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				Assert.fail("Failed to enter the text in field [" + sElementName
						+ "] because you passed wrong parameter[" + sElementName
						+ "] either in feature file or test class. \n please choose valid parameter from the following list: \n ["
						+ sCaseOptions + "]");
			}

			if (flag.equalsIgnoreCase("Field")) {
				if (sText.equalsIgnoreCase("text and delete it")) {
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"),
							RandomStringUtils.randomAlphanumeric(6));
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), "");
				} else if (sText.toLowerCase().contains("long text")) {
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), "");
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"),
							RandomStringUtils.randomAlphanumeric(30));
				} else if (sText.equalsIgnoreCase("invalid special character")) {
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), "");
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"),
							RandomStringUtils.randomAscii(10));
				} else if (sText.equalsIgnoreCase("tenantPage_tenantBot1")) {
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), "");
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), sText);
				} else if (sText.equalsIgnoreCase("pTenantPage_tenantBot1")) {
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), "");
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), sText);
				} else if (sText.equalsIgnoreCase("cPTenantPage_tenantBot1")) {
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), "");
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), sText);
				} else if (sText.equalsIgnoreCase("p_TenantPage_tenantBot1")) {
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), "");
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), sText);
				} else if (sText.equalsIgnoreCase("cP_TenantPage_tenantBot1")) {
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), "");
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), sText);
				} else if (sText.equalsIgnoreCase("c_TenantPage_tenantBot1")) {
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), "");
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), sText);
				} else if (sText.equalsIgnoreCase("p_pTenantPage_tenantBot1")) {
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), "");
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), sText);
				} else if (sText.equalsIgnoreCase("c_pTenantPage_tenantBot1")) {
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), "");
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), sText);
				} else if (sText.equalsIgnoreCase("cP_pTenantPage_tenantBot1")) {
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), "");
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), sText);
				} else if (sText.equalsIgnoreCase("p_cPTenantPage_tenantBot1")) {
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), "");
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), sText);
				} else if (sText.equalsIgnoreCase("c_cPTenantPage_tenantBot1")) {
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), "");
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), sText);
				} else if (sText.equalsIgnoreCase("cP_cPTenantPag_tenantBot1")) {
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), "");
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), sText);
				} else if (sText.equalsIgnoreCase("sitePage_tenantBot1")) {
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), "");
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), sText);
				}
			} else if (flag.equalsIgnoreCase("SearchField")) {
				BaseUtil.explicitWait(1000);
				BaseUtil.enterText(locator, sText);
				BaseUtil.waitForSpinner(1000);
			} else if (flag.equalsIgnoreCase("Dropdown")) {
				BaseUtil.selectDropDownByValue(locator, sText);
			} else if (flag.equalsIgnoreCase("ComboBox")) {
				selectComboBoxDropDownByValue(locator, sText);
			}

		} catch (Exception e) {
			logger.error("Failed to enter the value in the [" + sElementName
					+ "] field in site page. see detail error message : \n" + e.getStackTrace().toString());
			Assert.fail("Failed to enter the value in the [" + sElementName
					+ "] field in site page. see detail error message \n" + e.getStackTrace().toString());
		}
	}

	public boolean verifyDefaultStatus(String flag) {
		try {
			switch (flag.toUpperCase()) {
			case "ACTIVE":
				BaseUtil.verifyCheckBoxIsSelected(getProps().getProperty("editSiteActiveCheckbox"));
				return true;
			default:
				logger.error("Switch Case[" + flag + "] is not matched in class[" + getClass().getName() + "] , Method["
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the element :" + flag + " see detail message : " + e.getMessage());
			return false;
		}
	}

	public boolean isElementEnabledOnTenantPage(String sElementName) {
		try {
			switch (sElementName.toUpperCase()) {
			case "SAVE":
				return BaseUtil.verifyElementEnabled(getProps().getProperty("addTenantPopupSaveButton"));
			case "CANCEL":
				return BaseUtil.verifyElementEnabled(getProps().getProperty("addTenantPopupCancelButton"));
			case "TENANT_DETAIL_GRID":
				return BaseUtil.verifyElementEnabled(getProps().getProperty("tenantDetailGrid"));
			default:
				logger.error("Switch Case[" + sElementName + "] is not matched in class[" + getClass().getName()
						+ "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to verify that Element [" + sElementName + "] is enabled.  see detail message: "
					+ e.getMessage());
			return false;
		}
	}

	public boolean selectTenantFromGrid_TenantPage(String sTenantName) {
		int iTotalNumOfPage = 0;
		boolean bResult = false;
		try {
			iTotalNumOfPage = Integer
					.parseInt(BaseUtil.getElementText(getProps().getProperty("tenantPaginationTotalPages")).trim()
							.replace("/", "").trim());
			if (iTotalNumOfPage == 1)
				iTotalNumOfPage++;
			for (int iNumPage = 1; iNumPage <= iTotalNumOfPage - 1; iNumPage++) {
				List<WebElement> lstTenantNames = BaseUtil
						.getMultipleElementsAfterLoaded(getProps().getProperty("tenantGridNameColumn"));
				for (WebElement webElement : lstTenantNames) {
					if (webElement.getText().trim().equalsIgnoreCase(sTenantName)) {
						webElement.click();
						BaseUtil.waitForSpinner();
						bResult = true;
						break;
					}
				}
				if (bResult)
					break;
				BaseUtil.clickAndWait(getProps().getProperty("tenantPaginationNext"));
				BaseUtil.waitForSpinner();
			}
			return bResult;

		} catch (Exception e) {
			logger.error("Failed to select tenant:" + sTenantName
					+ " from tenant detail grid , see detail error message: " + e.getMessage());
			return false;
		}
	}

	public boolean verifyFieldsValue(String sTenantName) {
		try {
			WebElement tenantName = getDriver().findElement(By.id("tenantName"));
			String tenantnameValue = tenantName.getText();
			WebElement parent = getDriver().findElement(By.xpath("//*[@id='selectParentSection']/div"));
			String parentValue = parent.getText();
			WebElement tenantType = getDriver().findElement(By.xpath("//*[@id='selectTenantType']"));
			String tenantTypeValue = tenantType.getText();

			if (tenantnameValue.equals("tenantPage_tenantBot1") && parentValue.equals("GridPoint")
					&& tenantTypeValue.equals("CUSTOMER")) {
				logger.info("The tenant is saved successfully with correct values verified on Edit Tenant Modal");
			}
			return true;
		} catch (Exception e) {
			logger.error("Failed to verify the values for the tenant :" + sTenantName + " see detail message : "
					+ e.getMessage());
			return false;
		}
	}

	public void verifyTenantSearchFunctionality(String arg) {
		String[] arr = arg.split(":");
		String keyword = arr[1];
		List<WebElement> lstTenantNames = null;
		String totalItemsText = null;
		int initialTotalItemsCount = 0;
		int laterTotalItemsCount = 0;
		WebElement searchBox = null;
		try {
			switch (keyword.toUpperCase()) {
			case "INACTIVE":
				BaseUtil.enterText(getProps().getProperty("tenantsSearchField"), arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("tenantSearchButton"));
				BaseUtil.waitForSpinner();
				try {
					if (BaseUtil.getElementText(getProps().getProperty("tenantGridZeroRecord")).equalsIgnoreCase(
							BaseUtil.getElementText(getProps().getProperty("tenantGridZeroRecordText")))) {
						Assert.fail("No record available. Test cannot be continue.");
					}
				} catch (Exception e) {
					logger.info("Sufficient records are available");
				}
				break;
			case "ACTIVE":
				BaseUtil.enterText(getProps().getProperty("tenantsSearchField"), arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("tenantSearchButton"));
				BaseUtil.waitForSpinner();
				try {
					if (!BaseUtil.getElementText(getProps().getProperty("tenantGridZeroRecord")).equalsIgnoreCase(
							BaseUtil.getElementText(getProps().getProperty("tenantGridZeroRecordText")))) {
						Assert.fail("No record available. Test cannot be continue.");
					}
				} catch (Exception e) {
					logger.info("Sufficient records are available");
				}
				break;
			case "INIT3CHAR":
				totalItemsText = BaseUtil.getElementText(getProps().getProperty("tenantTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2, totalItemsText.indexOf("items")).trim());
				BaseUtil.enterText(getProps().getProperty("tenantsSearchField"), arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("tenantSearchButton"));
				BaseUtil.waitForSpinner();
				try {
					if (BaseUtil.getElementText(getProps().getProperty("tenantGridZeroRecord")).equalsIgnoreCase(
							BaseUtil.getElementText(getProps().getProperty("tenantGridZeroRecordText")))) {
						Assert.fail("No record available. Test cannot be continue.");
					}
				} catch (Exception e) {
					logger.info("Sufficient records are available");
				}

				totalItemsText = BaseUtil.getElementText(getProps().getProperty("tenantTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2, totalItemsText.indexOf("items")).trim());
				if (laterTotalItemsCount > initialTotalItemsCount) {
					Assert.fail("Tenant search results after entering 3 characters " + arr[0] + " is not as expected");
				}
				break;
			case "RANDOM":
				String randomSearchString = RandomStringUtils.randomAscii(10);
				BaseUtil.enterText(getProps().getProperty("tenantsSearchField"), randomSearchString);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("tenantSearchButton"));
				BaseUtil.waitForSpinner();
				try {
					Assert.assertTrue("Multiple results found for the string : " + randomSearchString,
							BaseUtil.getElementText(getProps().getProperty("tenantGridZeroRecord"))
									.equals(getProps().getProperty("tenantGridZeroRecordText")));
				} catch (Exception ex) {
					lstTenantNames = BaseUtil
							.getMultipleElementsAfterLoaded(getProps().getProperty("tenantGridNameColumn"));
					logger.info("In case RANDOM the list size is : " + lstTenantNames.size());
					for (WebElement tenantNameElement : lstTenantNames) {
						if (!tenantNameElement.getText().contains(randomSearchString)) {
							Assert.fail("Tenant search results are not as expected for string : " + randomSearchString);
						}
					}
				}
				break;
			case "LONGTEXT":
				String longTextSearchString = RandomStringUtils.randomAlphanumeric(50);
				BaseUtil.enterText(getProps().getProperty("tenantsSearchField"), longTextSearchString);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("tenantSearchButton"));
				BaseUtil.waitForSpinner();
				try {
					Assert.assertTrue("No results found for the string : " + longTextSearchString,
							BaseUtil.getElementText(getProps().getProperty("tenantGridZeroRecord"))
									.equals(getProps().getProperty("tenantGridZeroRecordText")));
				} catch (Exception ex) {
					lstTenantNames = BaseUtil
							.getMultipleElementsAfterLoaded(getProps().getProperty("tenantGridNameColumn"));
					logger.info("In case LONGTEXT the list size is : " + lstTenantNames.size());
					for (WebElement tenantNameElement : lstTenantNames) {
						if (!tenantNameElement.getText().contains(longTextSearchString)) {
							Assert.fail(
									"Tenant search results are not as expected for string : " + longTextSearchString);
						}
					}
				}
				break;
			case "BACKSPACE":
				BaseUtil.enterText(getProps().getProperty("tenantsSearchField"), arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("tenantSearchButton"));
				BaseUtil.waitForSpinner();
				lstTenantNames = BaseUtil
						.getMultipleElementsAfterLoaded(getProps().getProperty("tenantGridNameColumn"));
				logger.info("Search size before enter backspace : " + lstTenantNames.size());
				searchBox = BaseUtil.getElementAfterLoaded(getProps().getProperty("tenantsSearchField"));
				searchBox.sendKeys(Keys.BACK_SPACE);
				BaseUtil.waitForSpinner();
				List<WebElement> newSearchLstTenantNames = BaseUtil
						.getMultipleElementsAfterLoaded(getProps().getProperty("tenantGridNameColumn"));
				logger.info("search size after enter backspace: " + newSearchLstTenantNames.size());
				if (newSearchLstTenantNames.size() < lstTenantNames.size()) {
					Assert.fail("Search results are not as expected when backspace is clicked on tenant search");
				}
				break;
			case "DELETE":
				BaseUtil.enterText(getProps().getProperty("tenantsSearchField"), arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("tenantSearchButton"));
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps().getProperty("tenantTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2, totalItemsText.indexOf("items")).trim());
				searchBox = BaseUtil.getElementAfterLoaded(getProps().getProperty("tenantsSearchField"));
				searchBox.sendKeys(Keys.CONTROL + "a");
				searchBox.sendKeys(Keys.DELETE);
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps().getProperty("tenantTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2, totalItemsText.indexOf("items")).trim());
				if (laterTotalItemsCount < initialTotalItemsCount) {
					Assert.fail("Tenant search results after deleting the keyword " + arr[0] + " is not as expected");
				}
				break;
			case "PAGINATION":
				BaseUtil.enterText(getProps().getProperty("tenantsSearchField"), arr[0]);
				BaseUtil.waitForSpinner();
				boolean nextFlg = BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantPaginationNext"));
				boolean lastFlg = BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantPaginationLast"));
				boolean prevFlg = BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantPaginationPrev"));
				boolean firstFlg = BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantPaginationFirst"));
				if (!(nextFlg && lastFlg && prevFlg && firstFlg)) {
					Assert.fail("All the pagination buttons are not visible");
				}
				break;
			}
		} catch (Exception e) {
			logger.error("Some error occured while verifying the search results : " + e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	public boolean verifyElementReadOnly(String sField) {
		try {
			String locator = null;
			switch (sField.toUpperCase()) {
			case "NAME":
				locator = getProps().getProperty("addTenantPopupNameField");
				break;
			case "PARENT":
				locator = getProps().getProperty("editTenantPopupParentField");
				break;
			default:
				logger.error("Switch Case[" + sField + "] is not matched in class[" + getClass().getName()
						+ "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return false;
			}
			return BaseUtil.verifyElementIsReadOnly(locator);

		} catch (Exception e) {
			logger.error("Failed to verify that Element [" + sField + "] is read only.  see detail message: "
					+ e.getMessage());
		}
		return false;
	}

	public boolean verifyErrorMessage_TenantPage(String expErrorMsg, String fieldName) {
		try {
			switch (fieldName.toUpperCase()) {
			case "NAME":
				if (expErrorMsg.contains("Tenants name cannot be blank")) {
					BaseUtil.enterText(getProps().getProperty("addTenantPopupNameField"), "  ");
					return BaseUtil.getElementText(getProps().getProperty("addTenantPopupAlertBlankNameErr"))
							.equalsIgnoreCase(expErrorMsg);
				} else if (expErrorMsg.equals("letters allowed")) {
					return BaseUtil.getElementText(getProps().getProperty("addTenantPopupAlertLongNameErr"))
							.equalsIgnoreCase(expErrorMsg);
				} else if (expErrorMsg.contains("Tenants name must start")) {
					return BaseUtil.getElementText(getProps().getProperty("addTenantPopupAlertInvalidNameErr"))
							.equalsIgnoreCase(expErrorMsg);
				} else if (expErrorMsg.contains("Name already exists")) {
					return BaseUtil.getElementText(getProps().getProperty("addTenantPopupAlertAlreadyExistNameErr"))
							.equalsIgnoreCase(expErrorMsg);
				}
				break;
			default:
				logger.error("Switch Case[" + fieldName + "] is not matched in class[" + getClass().getName()
						+ "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("Failed to displayed the element :" + fieldName + " see detail message : " + e.getMessage());
			return false;
		}
	}

	public boolean checkPopupExistence(String sPopName, String sExpMsg) {
		try {
			switch (sPopName.toUpperCase()) {
			case "ADDTENANT_CONFIRMATION":
				if (BaseUtil.verifyElementDisplayed(getProps().getProperty("addTenantPopupAlert"))) {
					return BaseUtil.getElementText(getProps().getProperty("addTenantPopupAlertMessage"))
							.equalsIgnoreCase(sExpMsg);
				} else {
					return false;
				}
			case "ADDTENANT":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty("addTenantPopup"));
			default:
				logger.error("Switch Case[" + sPopName + "] is not matched in class[" + getClass().getName()
						+ "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to check existence of popup:" + sPopName + " , detail message: " + e.getMessage());
			return false;
		}
	}

	public boolean clickOperationOnConfirmationBox(String sButtonName) {
		try {
			switch (sButtonName.toUpperCase()) {
			case "OK":
				BaseUtil.clickAndWait(getProps().getProperty("addTenantPopupAlertOkButton"));
				return true;
			case "CANCEL":
				BaseUtil.clickAndWait(getProps().getProperty("addTenantPopupAlertCancelButton"));
				return true;
			default:
				logger.error("Switch Case[" + sButtonName + "] is not matched in class[" + getClass().getName()
						+ "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to click on button :" + sButtonName + " , detail message: " + e.getMessage());
			return false;
		}
	}

	public boolean verifyUpdatedValue(String dropdownType, String value) {
		try {
			switch (dropdownType.toUpperCase()) {
			case "TENANT TYPE":
				return BaseUtil.getSelectedDropDownValues(getProps().getProperty("addTenantPopupTenantTypeField"))
						.equals(value);
			default:
				logger.error("Switch Case[" + dropdownType + "] is not matched in class[" + getClass().getName()
						+ "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error(
					"Failed to displayed the element :" + dropdownType + " see detail message : " + e.getMessage());
			return false;
		}
	}

	public boolean selectSubtenantFromTenantGrid_TenantPage(String subTenantName) {
		int iTotalNumOfPage = 0;
		boolean bResult = false;
		try {

			iTotalNumOfPage = Integer
					.parseInt(BaseUtil.getElementText(getProps().getProperty("subtenantSectionPaginationTotalPages"))
							.trim().replace("/", "").trim());
			if (iTotalNumOfPage == 1)
				iTotalNumOfPage++;
			for (int iNumPage = 1; iNumPage <= iTotalNumOfPage - 1; iNumPage++) {
				List<WebElement> lstTenantNames = BaseUtil.getMultipleElementsAfterLoaded(
						getProps().getProperty("subtenantSectiontenantGridSiteNameColumn"));

				for (WebElement webElement : lstTenantNames) {
					String tenantName = webElement.getText();
					if (tenantName.equals(subTenantName)) {
						webElement.click();
						BaseUtil.waitForSpinner();
						bResult = true;
						break;
					}
				}
				if (bResult)
					break;
				BaseUtil.clickAndWait(getProps().getProperty("tenantPaginationNext"));
				BaseUtil.waitForSpinner();
			}
			return bResult;

		} catch (Exception e) {
			logger.error("Failed to select tenant:" + subTenantName
					+ " from Site Section of tenant detail grid , see detail error message: " + e.getMessage());
			return false;
		}
	}

	public void selectUserFromGrid_UserPage(String userName) {
		boolean bResult = false;
		logger.debug("Selecting the user :" + userName);
		try {
			List<WebElement> lstUserNames = BaseUtil
					.getMultipleElementsAfterLoaded(getProps().getProperty("gridUserNameColumn"));
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
			logger.error("Failed to select user: " + userName + " from user grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the user : " + userName + ", see detail error message: \n"
					+ e.getStackTrace().toString());
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public boolean selectTenantOnuserPage(String stextField, String tenantName) {
		// TODO : check the code
		try {
			switch (stextField.toUpperCase()) {
			case "SELECT_TENANT":
				BaseUtil.click(getProps().getProperty("editUserTenantDropDown"));
				BaseUtil.explicitWait(1000);
				if (tenantName.equalsIgnoreCase("GridPoint Test Automation")) {
					selectComboBoxDropDownByValue(getProps().getProperty("editUserTenantPopupParentListNew"),
							tenantName);
					break;
				}
				selectComboBoxDropDownByValue(getProps().getProperty("editUserTenantPopupParentList"), tenantName);
				return true;
			default:
				logger.error("Switch Case[" + stextField + "] is not matched in class[" + getClass().getName()
						+ "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the element :" + stextField + " see detail message : " + e.getMessage());
			return false;
		}
		return false;

	}

	public void verifyPaginationAndNoRecordFounds(String sSection, String sMessage) {
		String sText;
		try {
			switch (sSection.toUpperCase()) {
			case "SITES":
				Assert.assertFalse("Next button is found [enabled].",
						BaseUtil.verifyElementEnabled(getProps().getProperty("tenantPaginationNext")));
				Assert.assertFalse("Prev button is found [enabled].",
						BaseUtil.verifyElementEnabled(getProps().getProperty("tenantPaginationPrev")));
				Assert.assertFalse("Last button is found [enabled].",
						BaseUtil.verifyElementEnabled(getProps().getProperty("tenantPaginationLast")));
				Assert.assertFalse("First button is found [enabled].",
						BaseUtil.verifyElementEnabled(getProps().getProperty("tenantPaginationFirst")));
				sText = BaseUtil.getElementText(getProps().getProperty("editTenantSitesTableNoRecordFounds"));
				Assert.assertEquals("Expected was : " + sMessage + ", but in actual found: " + sText, sMessage, sText);
				break;
			case "SUBTENANTS":
				Assert.assertFalse("Next button is found [enabled].",
						BaseUtil.verifyElementEnabled(getProps().getProperty("tenantPaginationNext")));
				Assert.assertFalse("Prev button is found [enabled].",
						BaseUtil.verifyElementEnabled(getProps().getProperty("tenantPaginationPrev")));
				Assert.assertFalse("Last button is found [enabled].",
						BaseUtil.verifyElementEnabled(getProps().getProperty("tenantPaginationLast")));
				Assert.assertFalse("First button is found [enabled].",
						BaseUtil.verifyElementEnabled(getProps().getProperty("tenantPaginationFirst")));
				sText = BaseUtil.getElementText(getProps().getProperty("editTenantSubTenantsTableNoRecordFounds"));
				Assert.assertEquals("Expected was : " + sMessage + ", but in actual found: " + sText, sMessage, sText);
				break;
			default:
				logger.error("Switch Case[" + sSection + "] is not matched in class[" + getClass().getName()
						+ "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
			}
		} catch (Exception e) {
			logger.error(
					"Failed to verify pagination disabled property and message 'no record founds'.  see detail message: "
							+ e.getMessage());
		}
	}

	public void selectComboBoxDropDownByValue(String objLocator, String value) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		List<String> multipleElementTextAfterLoaded = BaseUtil.getMultipleElementTextAfterLoaded(objLocator);
		List<WebElement> multipleElementsAfterLoaded = BaseUtil.getMultipleElementsAfterLoaded(objLocator);
		int i = 0;
		boolean flg = false;
		for (String text : multipleElementTextAfterLoaded) {
			if (text.equalsIgnoreCase(value)) {
				multipleElementsAfterLoaded.get(i).click();
				flg = true;
				break;
			}
			i++;
		}

		if (!flg) {
			Assert.fail("Fail to select the given value: " + value + " from the combo box dropdown");
		}
	}

	public void clickTenantGridColumnName(String sLinkName) {
		// TODO Auto-generated method stub

	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// public boolean clickOperationOnSiteDetailPopup(String closeButton,
	// ) {
	// try {
	// switch (closeButton.toUpperCase()) {
	// case "X":
	// BaseUtil.clickAndWait(getProps().getProperty.get("editSitePopupXButton"));
	// return true;
	// default:
	// logger.error("Switch Case[" + closeButton + "] is not matched in class["
	// +
	// getClass().getName()
	// + "] , Method[" +
	// Thread.currentThread().getStackTrace()[1].getMethodName() +
	// "]");
	// return false;
	// }
	// } catch (Exception e) {
	// logger.error("Failed to click on button :" + closeButton + " , detail
	// message: " + e.getMessage());
	// return false;
	// } finally {
	// BaseUtil.waitForSpinner();
	// }
	// }
	//
	// public boolean enterNameAndParentType(String task, String fieldName,
	// ) {
	// try {
	// switch (fieldName.toUpperCase()) {
	// case "NAME":
	// if (task.equalsIgnoreCase("text and delete it")) {
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// RandomStringUtils.randomAlphanumeric(6));
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// "");
	// } else if (task.toLowerCase().contains("long text")) {
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// "");
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// RandomStringUtils.randomAlphanumeric(30));
	// } else if (task.equalsIgnoreCase("invalid special character")) {
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// "");
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// RandomStringUtils.randomAscii(10));
	// } else if (task.equalsIgnoreCase("tenantPage_tenantBot1")) {
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// "");
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// task);
	// } else if (task.equalsIgnoreCase("pTenantPage_tenantBot1")) {
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// "");
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// task);
	// } else if (task.equalsIgnoreCase("cPTenantPage_tenantBot1")) {
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// "");
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// task);
	// } else if (task.equalsIgnoreCase("p_TenantPage_tenantBot1")) {
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// "");
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// task);
	// } else if (task.equalsIgnoreCase("cP_TenantPage_tenantBot1")) {
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// "");
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// task);
	// } else if (task.equalsIgnoreCase("c_TenantPage_tenantBot1")) {
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// "");
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// task);
	// } else if (task.equalsIgnoreCase("p_pTenantPage_tenantBot1")) {
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// "");
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// task);
	// } else if (task.equalsIgnoreCase("c_pTenantPage_tenantBot1")) {
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// "");
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// task);
	// } else if (task.equalsIgnoreCase("cP_pTenantPage_tenantBot1")) {
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// "");
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// task);
	// } else if (task.equalsIgnoreCase("p_cPTenantPage_tenantBot1")) {
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// "");
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// task);
	// } else if (task.equalsIgnoreCase("c_cPTenantPage_tenantBot1")) {
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// "");
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// task);
	// } else if (task.equalsIgnoreCase("cP_cPTenantPag_tenantBot1")) {
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// "");
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// task);
	// } else if (task.equalsIgnoreCase("sitePage_tenantBot1")) {
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// "");
	// BaseUtil.enterText(getProps().getProperty.get("addTenantPopupNameField"),
	// task);
	// } else {
	// throw new Exception();
	// }
	// break;
	// default:
	// logger.error("Switch Case[" + fieldName + "] is not matched in class[" +
	// getClass().getName()
	// + "] , Method[" +
	// Thread.currentThread().getStackTrace()[1].getMethodName() +
	// "]");
	// return false;
	// }
	// return true;
	// } catch (Exception e) {
	// logger.error("Failed to Enter text: in textbox[" + fieldName + "] ,
	// detail
	// message: " + e.getMessage());
	// return false;
	// }
	// }
	//
	//
	// public boolean isElementDisplayedOnSitePage(String elementName,
	// Map<String,
	// String> getProps().getProperty) {
	// try {
	// switch (elementName.toUpperCase()) {
	// case "TENANT DROPDOWN":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteTenantDropDown"));
	// case "SITE NAME":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteNameField"));
	// case "SITE DESCRIPTION":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteDescriptionField"));
	// case "ADDRESS 1":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteAddress1Field"));
	// case "ADDRESS 2":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteAddress2Field"));
	// case "CITY":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteCityField"));
	// case "STATE/PROVINCE":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteProvinceField"));
	// case "POSTAL CODE":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSitePostalCodeField"));
	// case "COUNTRY":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteCountryDropDown"));
	// case "TIMEZONE":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteTimeZoneDropDown"));
	// case "COMMISSION DATE":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteCommissionDate"));
	// case "CONTROL DATE":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteControlDate"));
	// case "AREA":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteAreaField"));
	// case "HAS SUBMETERING":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteSubmeteringCheckBox"));
	// case "HAS CONTROL":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteControlCheckBox"));
	// case "HAS ELECTRIC VEHICAL":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteElectricVehiclesCheckBox"));
	// case "HAS SOLAR PANEL":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteSolarPanelCheckBox"));
	// case "PATHS":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSitePathsField"));
	// case "ADD PATH BUTTON":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteAddPathsButton"));
	// case "SAVE":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteSaveButton"));
	// case "CANCEL":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addSiteCancelButton"));
	// default:
	// logger.error("Switch Case[" + elementName + "] is not matched in class["
	// +
	// getClass().getName()
	// + "] , Method[" +
	// Thread.currentThread().getStackTrace()[1].getMethodName() +
	// "]");
	// return false;
	// }
	// } catch (Exception e) {
	// logger.error("Failed to displayed the element :" + elementName + " see
	// detail
	// message : " + e.getMessage());
	// return false;
	// }
	// }
	//
	// public void enterTextTenantSitePage(String list, Map<String, String>
	// getProps().getProperty,
	// Map<String, String> tenantPageObjectTestData) {
	// try {
	// switch (list.toUpperCase()) {
	// case "TENANT DROPDOWN":
	// BaseUtil.clickAndWait(getProps().getProperty.get("addSiteTenantDropDown"));
	// BaseUtil.explicitWait(4000);
	// selectComboBoxDropDownByValue(getProps().getProperty.get("addSiteTenantDropDownValue"),
	// tenantPageObjectTestData.get("addSiteTenantDropDownTestData"));
	// break;
	// case "TENANT:DROPDOWN":
	// BaseUtil.clickAndWait(getProps().getProperty.get("addSiteTenantDropDown"));
	// BaseUtil.explicitWait(5000);
	// selectComboBoxDropDownByValue(getProps().getProperty.get("addSiteInTenantPage_tenantBot1"),
	// tenantPageObjectTestData.get("addSiteTenantDropDownTestData"));
	// break;
	// case "SITE NAME":
	// BaseUtil.enterText(getProps().getProperty.get("addSiteNameField"),
	// tenantPageObjectTestData.get("addSiteTenantTestData"));
	// break;
	// case "SITE DESCRIPTION":
	// BaseUtil.enterText(getProps().getProperty.get("addSiteDescriptionField"),
	// tenantPageObjectTestData.get("addSiteTenantTestData"));
	// break;
	// case "ADDRESS 1":
	// BaseUtil.enterText(getProps().getProperty.get("addSiteAddress1Field"),
	// tenantPageObjectTestData.get("addSiteAddress1FieldTestData"));
	// break;
	// case "ADDRESS 2":
	// BaseUtil.enterText(getProps().getProperty.get("addSiteAddress2Field"),
	// tenantPageObjectTestData.get("addSiteAddress2FieldTestData"));
	// break;
	// case "CITY":
	// BaseUtil.enterText(getProps().getProperty.get("addSiteCityField"),
	// tenantPageObjectTestData.get("addSiteCityFieldTestData"));
	// break;
	// case "STATE/PROVINCE":
	// selectDropDownByValue(getProps().getProperty.get("addSiteProvinceField"),
	// tenantPageObjectTestData.get("addSiteProvinceFieldTestData"));
	// break;
	// case "POSTAL CODE":
	// BaseUtil.enterText(getProps().getProperty.get("addSitePostalCodeField"),
	// tenantPageObjectTestData.get("addSitePostalCodeFieldTestData"));
	// break;
	// case "COUNTRY":
	// selectDropDownByValue(getProps().getProperty.get("addSiteCountryDropDown"),
	// tenantPageObjectTestData.get("addSiteCountryDropDownTestData"));
	// break;
	// case "PHONE 1":
	// BaseUtil.enterText(getProps().getProperty.get("addSitePhone1Field"),
	// tenantPageObjectTestData.get("addSitePhone1TestData"));
	// break;
	// case "PHONE 2":
	// BaseUtil.enterText(getProps().getProperty.get("addSitePhone2Field"),
	// tenantPageObjectTestData.get("addSitePhone2TestData"));
	// break;
	// case "AREA":
	// BaseUtil.enterText(getProps().getProperty.get("addSiteAreaField"),
	// tenantPageObjectTestData.get("addSiteAreaFieldTestData"));
	// break;
	// case "PATHS":
	// BaseUtil.enterText(getProps().getProperty.get("addSitePathsField"),
	// tenantPageObjectTestData.get("addSitePathsFieldTestData"));
	// break;
	// case "ACTIVE":
	// checkCheckBox(getProps().getProperty.get("addSiteActiveCheckbox"));
	// break;
	// default:
	// logger.error("Switch Case[" + list + "] is not matched in class[" +
	// getClass().getName() + "] , Method["
	// + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
	// }
	// } catch (Exception e) {
	// logger.error("Failed to enetr text in field :" + list + " see detail
	// message
	// : " + e.getMessage());
	// }
	// }
	//
	//
	// public void isHomePageAvailable(String userName, Map<String, String>
	// getProps().getProperty) {
	// BaseUtil.enterText(getProps().getProperty.get("adminUserName_Locator"),
	// userName);
	// if (userName.equals("gadmin")) {
	// BaseUtil.enterText(getProps().getProperty.get("adminUserPassword_Locator"),
	// PathConstants.GADMIN_USER_PASSWORD);
	// BaseUtil.click(getProps().getProperty.get("adminLoginbutton_Locator"));
	// BaseUtil.waitForSpinner();
	// } else {
	// BaseUtil.enterText(getProps().getProperty.get("adminUserPassword_Locator"),
	// PathConstants.DEFAULT_USER_PASSWORD);
	// BaseUtil.click(getProps().getProperty.get("adminLoginbutton_Locator"));
	// BaseUtil.waitForSpinner();
	// }
	// if (userName.equals("tenantPage_GPSupportUserBot")) {
	// Assert.assertTrue(" Expected : User Tab should be Available. Actual :
	// User
	// tab is not available for user ["
	// + userName + "] ",
	// isElementClickable(getLocator(getProps().getProperty.get("UserTab"))));
	// } else if (userName.equals("botRoleCustomerAnalyst")) {
	// Assert.assertTrue(
	// " Expected : Dashboard Tab should be Available. Actual : Dashboard tab is
	// not
	// available for user ["
	// + userName + "] ",
	// !isElementClickable(getLocator(getProps().getProperty.get("DashboardTab"))));
	// } else if (userName.equals("botRoleCustomerSiteManager")) {
	// Assert.assertFalse(" Expected : Home Tab should be Available. Actual :
	// Home
	// tab is not available for user ["
	// + userName + "] ",
	// !isElementClickable(getLocator(getProps().getProperty.get("homeTab"))));
	// }
	// }
	//
	//
	//
	public boolean selectSiteTenantFromGrid_TenantPage(String sSitetName) {
		int iTotalNumOfPage = 0;
		boolean bResult = false;
		try {
			BaseUtil.waitForSpinner();
			iTotalNumOfPage = Integer
					.parseInt(BaseUtil.getElementText(getProps().getProperty("siteSectionPaginationTotalPages"))
							.trim().replace("/", "").trim());
			if (iTotalNumOfPage == 1)
				iTotalNumOfPage++;
			for (int iNumPage = 1; iNumPage <= iTotalNumOfPage - 1; iNumPage++) {
				List<WebElement> lstTenantNames = getMultipleElementsAfterLoaded(getProps().getProperty("siteSectiontenantGridSiteNameColumn"));
				for (WebElement webElement : lstTenantNames) {
					if (webElement.getText().trim().equalsIgnoreCase(sSitetName)) {
						webElement.click();
						BaseUtil.waitForSpinner();
						bResult = true;
						break;
					}
				}
				if (bResult)
					break;
				BaseUtil.clickAndWait(getProps().getProperty("tenantPaginationNext"));
				BaseUtil.waitForSpinner();
			}
			return bResult;

		} catch (Exception e) {
			logger.error("Failed to select site:" + sSitetName
					+ " from Site Section of tenant detail grid , see detail error message: " + e.getMessage());
			return false;
		}
	}
	//
	//
	//
	//
	//
	// public boolean isElementDisplayedOnTenantType(String sElementName,
	// ) {
	// try {
	// switch (sElementName.toUpperCase()) {
	// case "PARENT":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addTenantPopupTenantTypeOption1Field"));
	// case "CHANNEL PARTNER":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addTenantPopupTenantTypeOption2Field"));
	// case "CUSTOMER":
	// return
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("addTenantPopupTenantTypeOption3Field"));
	// default:
	// logger.error("Switch Case[" + sElementName + "] is not matched in class["
	// +
	// getClass().getName()
	// + "] , Method[" +
	// Thread.currentThread().getStackTrace()[1].getMethodName() +
	// "]");
	// return false;
	// }
	// } catch (Exception e) {
	// logger.error(
	// "Failed to displayed the element :" + sElementName +
	// " see detail message : "
	// + e.getMessage());
	// return false;
	// }
	// }
	//
	// public void isTenantTabAvailable(String userName, Map<String, String>
	// getProps().getProperty) {
	// BaseUtil.enterText(getProps().getProperty.get("adminUserName_Locator"),
	// userName);
	// BaseUtil.enterText(getProps().getProperty.get("adminPassword_Locator"),
	// PathConstants.DEFAULT_USER_PASSWORD);
	// BaseUtil.click(getProps().getProperty.get("adminLoginbutton_Locator"));
	// BaseUtil.waitForSpinner();
	// BaseUtil.click(getProps().getProperty.get("tenantTab"));
	// if (userName.equals("tenantPage_GPSupportUserBot")) {
	// Assert.assertTrue(
	// " Expected : Tenant Tab should be Available. Actual : Tenant tab is not
	// available for user ["
	// + userName + "] ",
	// isElementClickable(getLocator(getProps().getProperty.get("tenantTab"))));
	// }
	// }
	//
	// public boolean selectTenantTypeOnAddTenantPopup(String type, Map<String,
	// String> getProps().getProperty,
	// Map<String, String> tenantPageObjectTestData) {
	// try {
	// switch (type.toUpperCase()) {
	// case "PARENT":
	// selectDropDownByValue(getProps().getProperty.get("addTenantPopupTenantTypeField"),
	// tenantPageObjectTestData.get("addTenantPopupTenantTypeParentTestData"));
	// return true;
	// case "CUSTOMER":
	// selectDropDownByValue(getProps().getProperty.get("addTenantPopupTenantTypeField"),
	// tenantPageObjectTestData.get("addTenantPopupTenantTypeCustomerTestData"));
	// return true;
	// case "CHANNEL PARTNER":
	// selectDropDownByValue(getProps().getProperty.get("addTenantPopupTenantTypeField"),
	// tenantPageObjectTestData.get("addTenantPopupTenantTypeChannelPartnerTestData"));
	// return true;
	// default:
	// logger.error("Switch Case[" + type + "] is not matched in class[" +
	// getClass().getName() + "] , Method["
	// + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
	// return false;
	// }
	// } catch (Exception e) {
	// logger.error("Failed to displayed the element :" + type + " see detail
	// message : " + e.getMessage());
	// return false;
	// }
	// }
	//
	// public boolean verifyTenantOnTab(String tenantName, String tabName,
	// ) {
	// try {
	// WebElement tenantField = null;
	// List<WebElement> tenantSearch = null;
	// String tenandCheck = "";
	// switch (tabName.toUpperCase()) {
	// case "USERS":
	// tenantField =
	// BaseUtil.getElementAfterLoaded(getProps().getProperty.get("VerifyTenantOnUserTab"));
	// tenandCheck = tenantField.getText();
	// if (tenandCheck.equals(tenantName))
	// Assert.assertTrue(" The tenent [" + tenantName + "] is showing on [" +
	// tabName + "] Tab",
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("VerifyTenantOnUserTab")));
	// return true;
	// case "SITES":
	// BaseUtil.click(getProps().getProperty.get("siteTabTenantDropdown"));
	// BaseUtil.explicitWait(1000);
	// selectComboBoxDropDownByValue(getProps().getProperty.get("siteTabTenantDropdownValueNew"),
	// tenantName);
	// tenantSearch =
	// getMultipleElementsAfterLoaded(getProps().getProperty.get("VerifyTenantOnSiteTab"));
	// for (WebElement element : tenantSearch) {
	// tenandCheck = element.getText();
	// System.out.println("tenandCheck ="+tenandCheck);
	// System.out.println("tenantName ="+tenantName);
	// if (tenandCheck.equals(tenantName))
	// Assert.assertTrue(" The tenent [" + tenantName + "] is showing on [" +
	// tabName + "] Tab",
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("VerifyTenantOnSiteTabNew")));
	// break;
	// }
	// return true;
	// case "TENANT":
	// BaseUtil.enterText(getProps().getProperty.get("tenantsSearchField"),
	// tenantName);
	// BaseUtil.waitForSpinner();
	// tenantSearch =
	// getMultipleElementsAfterLoaded(getProps().getProperty.get("VerifyTenantOnTenantTab"));
	// for (WebElement element : tenantSearch) {
	// tenandCheck = element.getText();
	// if (tenandCheck.equals(tenantName))
	// Assert.assertTrue(" The tenent [" + tenantName + "] is showing on [" +
	// tabName + "] Tab",
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("VerifyTenantOnTenantTab")));
	// break;
	// }
	// return true;
	// case "ENDPOINT":
	// String defaultTenant =
	// BaseUtil.getElementText(getProps().getProperty.get("VerifyTenantOnEndpointTab"));
	// if (defaultTenant.equals(tenantName)) {
	// Assert.assertTrue(" The tenent [" + tenantName + "] is showing on [" +
	// tabName + "] Tab",
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("VerifyTenantOnEndpointTab")));
	// }
	//
	// return true;
	// case "CREATE DATAFIX":
	// BaseUtil.enterText(getProps().getProperty.get("siteTabSearchField"),
	// "sitePage_TenantSiteBot1");
	// BaseUtil.waitForSpinner();
	// tenantSearch =
	// getMultipleElementsAfterLoaded(getProps().getProperty.get("VerifyTenantOnDataFixTab"));
	// for (WebElement element : tenantSearch) {
	// tenandCheck = element.getText();
	// if (tenandCheck.equals(tenantName))
	// Assert.assertTrue(" The tenent [" + tenantName + "] is showing on [" +
	// tabName + "] Tab",
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("VerifyTenantOnDataFixTab")));
	// break;
	// }
	// return true;
	// case "UPLOAD TEMPLATE":
	// BaseUtil.click(getProps().getProperty.get("uploadTemplateTenantDropdown"));
	// BaseUtil.explicitWait(1000);
	// selectComboBoxDropDownByValue(getProps().getProperty.get("uploadTemplateTenantDropdownValue"),
	// tenantName);
	// tenantField =
	// BaseUtil.getElementAfterLoaded(getProps().getProperty.get("uploadTemplateTenantDropdown"));
	// tenandCheck = tenantField.getAttribute("value");
	// if (tenandCheck.equals(tenantName))
	// Assert.assertTrue(" The tenent [" + tenantName + "] is showing on [" +
	// tabName + "] Tab",
	// BaseUtil.verifyElementDisplayed(getProps().getProperty.get("uploadTemplateTenantDropdown")));
	// return true;
	// default:
	// logger.error("Switch Case[" + tabName + "] is not matched in class[" +
	// getClass().getName()
	// + "] , Method[" +
	// Thread.currentThread().getStackTrace()[1].getMethodName() +
	// "]");
	// return false;
	// }
	// } catch (Exception e) {
	// logger.error("Failed to displayed the element :" + tabName + " see detail
	// message : " + e.getMessage());
	// return false;
	// }
	// }
	//
	// public boolean enterValuesEndpointCreate(Map<String, String>
	// getProps().getProperty,
	// Map<String, String> tenantPageObjectTestData) {
	// try {
	// BaseUtil.enterText(getProps().getProperty.get("addEndpointSerialField"),
	// tenantPageObjectTestData.get("addEndpointSerialTestData"));
	// BaseUtil.explicitWait(20000);
	// selectDropDownByValue(getProps().getProperty.get("addEndpointTypeDropDown"),
	// tenantPageObjectTestData.get("addEndpointTypeDropDownTestData"));
	// BaseUtil.explicitWait(2000);
	// return true;
	// } catch (Exception e) {
	// Assert.fail("Failed to enter valid values");
	// }
	// return false;
	// }

	private List<WebElement> getMultipleElementsAfterLoaded(String property) {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO : below method added by k. cuurently commenting it to avoid compilation
	// issues in the code.verify..
	// public void clickTenantGridColumnName(String tenantLinkName, Map<String,
	// String> tenantPageObjectRepo) {
	// try {
	// switch (tenantLinkName.toUpperCase()) {
	// case "NAME":
	// Assert.assertTrue("Required data is not present.Name column is not enabled",
	// verifyElementEnabled(tenantPageObjectRepo.get("tenantGridNameHeader")));
	// click(tenantPageObjectRepo.get("tenantGridNameHeader"));
	// break;
	// case "TENANT TYPE":
	// Assert.assertTrue("Required data is not present. TENANT TYPE column is not
	// enabled",
	// verifyElementEnabled(tenantPageObjectRepo.get("tenantGridTenantTypeHeader")));
	// click(tenantPageObjectRepo.get("tenantGridTenantTypeHeader"));
	// break;
	// case "PARENT":
	// Assert.assertTrue("Required data is not present. PARENT is not enabled",
	// verifyElementEnabled(tenantPageObjectRepo.get("tenantGridParentHeader")));
	// click(tenantPageObjectRepo.get("tenantGridParentHeader"));
	// break;
	// case "FIRST":
	// Assert.assertTrue("Required data is not present. Last button is not enabled",
	// verifyElementEnabled(tenantPageObjectRepo.get("tenantPaginationFirst")));
	// click(tenantPageObjectRepo.get("tenantPaginationFirst"));
	// break;
	// default:
	// logger.error("Switch Case[" + tenantLinkName + "] is not matched in class[" +
	// getClass().getName()
	// + "] , Method[" + Thread.currentThread().getStackTrace()[1].getMethodName() +
	// "]");
	// Assert.fail("Invalid button clicked");
	// }
	// } catch (Exception e) {
	// logger.error("Failed to click on link :" + tenantLinkName + " , detail
	// message: " + e.getMessage());
	// Assert.fail(e.getMessage());
	// }
	// }

}
