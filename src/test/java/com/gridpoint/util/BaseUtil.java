package com.gridpoint.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gridpoint.automation.constants.GridpointConstants;
import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.automation.tests.base.TestBase;

import cucumber.api.Scenario;

public class BaseUtil extends CommonInit {

	public static Logger logger = Logger.getLogger(BaseUtil.class);

	public static void checkCheckBox(String objLocator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		WebElement element = getElementAfterLoaded(objLocator);
		if (!element.isSelected()) {
			element.click();
			logger.debug("The Given Element is clicked");
		}
	}

	public static void unCheckCheckBox(String objLocator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		WebElement element = getElementAfterLoaded(objLocator);
		if (element.isSelected()) {
			element.click();
			logger.debug("The Given Element is clicked");
		}
	}

	public static boolean verifyCheckBoxIsSelected(String objLocator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		WebElement element = getElementAfterLoaded(objLocator);
		if (element.isSelected()) {
			logger.debug("Given checkbox is selected");
			return true;
		}
		return false;
	}

	// public static void click(String objLocator) {
	// logger.debug("Executing Test Step::" + new Object() {
	// }.getClass().getEnclosingMethod().getName());
	//
	// WebElement element = getElementAfterLoaded(objLocator);
	// element.click();
	// logger.info("The Given Element is clicked");
	// }

	public static void click(String objLocator) {
		final long t1 = System.currentTimeMillis();
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		long t3 = System.currentTimeMillis();
		if (isElementClickable(objLocator)) {
			getElementAfterLoaded(objLocator).click();
		} else {
			Assert.fail("Button with xpath [" + objLocator.split("===")[1]
					+ "] is not clickable");
		}
		long t4 = System.currentTimeMillis();
		logger.debug("Time taken in click on the element only : " + (t4 - t3));
		final long t2 = System.currentTimeMillis();
		logger.debug("The Given Element is clicked");
		logger.debug("Time taken in click method : " + (t2 - t1));
	}

	public static void click(String objLocator, int index) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		WebElement element = getMultipleElementsAfterLoaded(objLocator).get(
				index);
		element.click();
		logger.info("The Given Element is clicked");
	}

	public static void clickAndWait(String objLocator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		WebElement element = waitAndgetElement(objLocator);
		element.click();
		logger.debug("After waiting, the Given Element is clicked");
	}

	public static void clickElement(String objLocator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		WebElement element = getElementAfterLoaded(objLocator);
		element.sendKeys(Keys.ENTER);
		logger.debug("The Given Element is clicked");
	}

	public static void enterText(String objLocator, String text) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		WebElement element = getElementAfterLoaded(objLocator);
		logger.info("The data to be filled in the Textbox is " + text);
		element.clear();
		element.sendKeys(text);
		logger.info("The data is entered to the Text Field");
	}

	public static String getElementText(String objLocator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		WebElement element = getElementAfterLoaded(objLocator);
		logger.info("Element is having the text... " + element.getText());
		return element.getText();
	}

	public static String getElementText(String objLocator, int index) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		WebElement element = getMultipleElementsAfterLoaded(objLocator).get(
				index);
		logger.info("Element is having the text... " + element.getText());
		return element.getText();
	}

	// TODO : all the dropdown methods must be combine as much as possible to
	// avoid
	// logiv duplicacy
	public static void selectDropDown(final String selectionType,
			final String objLocator, final String value, boolean trimFlg) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		Select select = new Select(getElementAfterLoaded(objLocator));
		List<WebElement> list = select.getOptions();
		boolean notFoundFlg = true;
		int index = 0;
		boolean condition;
		for (int i = 0; i < list.size(); i++) {
			if (trimFlg) {
				condition = list.get(i).getText().trim()
						.equalsIgnoreCase(value.trim());
			} else {
				condition = list.get(i).getText().equalsIgnoreCase(value);
			}
			if (condition) {
				logger.debug(list.get(i).getText() + "=" + value);
				notFoundFlg = false;
				index = i;
				break;
			}
		}
		if (notFoundFlg) {
			Assert.fail("Given value [" + value
					+ "] is not available in the dropdown");
		}
		if (selectionType.equals(GridpointConstants.DROPDOWN_BYINDEX)) {
			select.selectByIndex(index);
		} else if (selectionType.equals(GridpointConstants.DROPDOWN_BYVALUE)) {
			select.selectByValue(value);
		} else if (selectionType
				.equals(GridpointConstants.DROPDOWN_BYVISIBLETEXT)) {
			select.selectByVisibleText(value);
		} else {
			list.get(index).click();
		}
	}

	public static void selectDropdownElement(String objLocater, String endpointType)
	{
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());
		Select select = new Select(getElementAfterLoaded(objLocater));
		select.selectByVisibleText(endpointType);
	}
	public static void selectDropDownByValue(String objLocator, String value) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		Select select = new Select(getElementAfterLoaded(objLocator));
		//List<WebElement> list = select.getOptions();
		select.selectByVisibleText("EC100");
//		boolean notFoundFlg = true;
//		for (int i = 0; i < list.size(); i++) {
//			if (list.get(i).getText().equalsIgnoreCase(value)) {
//				logger.debug(list.get(i).getText() + "=" + value);
//				// select.selectByIndex(i);
//				
//				// select.selectByValue(value);
//				// list.get(i).click();
//				notFoundFlg = false;
//				break;
//			}
//		}
//		if (notFoundFlg) {
//			Assert.fail("Given value [" + value
//					+ "] is not available in the dropdown");
//		}
	}

	// public static void selectDropDownByValue(String objLocator, String value)
	// {
	// logger.debug("Executing Test Step::" + new Object() {
	// }.getClass().getEnclosingMethod().getName());
	//
	// Select select = new Select(getElementAfterLoaded(objLocator));
	// List<WebElement> list = select.getOptions();
	// boolean flg = false;
	// for (int i = 0; i < list.size(); i++) {
	// if (list.get(i).getText().trim().equalsIgnoreCase(value)) {
	// logger.info(list.get(i).getText() + "=" + value);
	// select.selectByIndex(i);
	// explicitWait(4000);
	// flg = true;
	// break;
	// }
	// }
	//
	// if (!flg) {
	// Assert.fail("Fail to select the given value: " + value + " from the
	// dropdown");
	// }
	// }

	public static void selectDropDownByIndex(String objLocator, int index) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		Select select = new Select(getElementAfterLoaded(objLocator));
		select.selectByIndex(index);
	}

	public static List<WebElement> getDropDownValues(String objLocator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		click(objLocator);
		Select select = new Select(getElementAfterLoaded(objLocator));
		return select.getOptions();
	}

	
	public static List<String> getDropDownText(String objLocator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		Select select = new Select(getElementAfterLoaded(objLocator));
		List<String> dropDownText = new ArrayList<>();
		for (WebElement element : select.getOptions()) {
			dropDownText.add(element.getText());
		}
		return dropDownText;
	}

	public static String getSelectedDropDownValues(String objLocator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		Select select = new Select(getElementAfterLoaded(objLocator));
		logger.info("Selected option is the drop down is : "
				+ select.getFirstSelectedOption().getText());
		return select.getFirstSelectedOption().getText();
	}

	public static void selectComboBoxDropDownByValue(String objLocator,
			String value) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		List<String> multipleElementTextAfterLoaded = getMultipleElementTextAfterLoaded(objLocator);
		List<WebElement> multipleElementsAfterLoaded = getMultipleElementsAfterLoaded(objLocator);
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
			Assert.fail("Fail to select the given value: " + value
					+ " from the combo box dropdown");
		}
	}

	public static void selectComboBoxDropDownByValueNew(String objLocator,
			String value) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		List<String> multipleElementTextAfterLoaded = getMultipleElementTextAfterLoaded(objLocator);
		List<WebElement> multipleElementsAfterLoaded = getMultipleElementsAfterLoaded(objLocator);
		int i = 0;
		boolean flg = false;
		for (String text : multipleElementTextAfterLoaded) {
			if (text.equalsIgnoreCase(value)) {
				// scrollTab(getWebDriver(),
				// "xpath===//*[@id='addSitesSection']/div/form/div[1]/div[1]/section/div[1]/div/div/div/ul/li/ul/li/a/span[contains(text(),
				// 'GridPoint Test Automation')]");
				multipleElementsAfterLoaded.get(i).click();
				flg = true;
				break;
			}
			i++;
		}

		if (!flg) {
			Assert.fail("Fail to select the given value: " + value
					+ " from the combo box dropdown");
		}
	}

	public static void selectComboBoxDropDownByIndex(String objLocator,
			int index) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		getMultipleElementsAfterLoaded(objLocator).get(index).click();
	}

	// ////////////////

	public static void uploadFile(String objLocator, String filePath) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		WebElement element = getElementAfterLoaded(objLocator);
		element.sendKeys(filePath);
	}

	public static boolean verifyElementDisplayed(String objLocator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());
		WebElement element = null;
		try {
			element = getElementAfterLoaded(objLocator);
			TestBase.getWebDriverWait()
					.ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.visibilityOf(element));
		} catch (ElementNotVisibleException e) {
			logger.error("Element with locator [" + objLocator
					+ "] is not visible on the page");
			return false;
		} catch (TimeoutException t) {
			logger.error("Element with locator [" + objLocator
					+ "] is not available on the page in the given timeout ["
					+ TestBase.getTimeout() + "]");
			return false;
		} catch (NoSuchElementException n) {
			logger.error("No such element with locator [" + objLocator
					+ "] is located on the page");
			return false;
		} catch (ArrayIndexOutOfBoundsException e) {
			logger.error("Error in locator : " + objLocator);
			return false;
		} catch (StaleElementReferenceException e) {
			logger.error("Stale element exception accoured while accessing the element for first time. Retrying to access it for one more time...");
			explicitWait(2000);
			element = getElementAfterLoaded(objLocator);
			logger.info("Element object is reinitialized for locator : "
					+ objLocator + ". Trying to verify its display.");
		} catch (Exception e) {
			logger.error("Some error occured while locating element with locator ["
					+ objLocator + "] on the page : " + e.getMessage());
			return false;
		}

		return element.isDisplayed();
	}

	public static boolean verifyElementEnabled(String objLocator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());
		WebElement element = null;
		try {
			element = getElementAfterLoaded(objLocator);
			TestBase.getWebDriverWait()
					.ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(element));
		} catch (ElementNotVisibleException e) {
			logger.error("Element with locator [" + objLocator
					+ "] is not visible on the page");
			return false;
		} catch (TimeoutException t) {
			logger.error("Element with locator [" + objLocator
					+ "] is not available on the page in the given timeout ["
					+ TestBase.getTimeout() + "]");
			return false;
		} catch (NoSuchElementException n) {
			logger.error("No such element with locator [" + objLocator
					+ "] is located on the page");
			return false;
		} catch (ArrayIndexOutOfBoundsException e) {
			logger.error("Error in locator : " + objLocator);
			return false;
		} catch (StaleElementReferenceException e) {
			logger.error("Stale element exception accoured while accessing the element for first time. Retrying to access it for one more time...");
			explicitWait(2000);
			element = getElementAfterLoaded(objLocator);
			logger.info("Element object is reinitialized for locator : "
					+ objLocator + ". Trying to verify its display.");
		} catch (Exception e) {
			logger.error("Some error occured while locating element with locator ["
					+ objLocator + "] on the page : " + e.getMessage());
			return false;
		}
		return element.isEnabled();
	}

	// public static boolean verifyElementDisplayed(String objLocator) {
	// logger.debug("Executing Test Step::" + new Object() {
	// }.getClass().getEnclosingMethod().getName());
	//
	// WebElement element = getElementAfterLoaded(objLocator);
	// return element.isDisplayed() ? true : false;
	// }

	public static boolean verifyElementDisplayed(String objLocator, int index) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		WebElement element = getMultipleElementsAfterLoaded(objLocator).get(
				index);
		return element.isDisplayed() ? true : false;
	}

	// public static boolean verifyElementEnabled(String objLocator) {
	// logger.debug("Executing Test Step::" + new Object() {
	// }.getClass().getEnclosingMethod().getName());
	//
	// WebElement element = getElementAfterLoaded(objLocator);
	// return element.isEnabled() ? true : false;
	// }

	public static String getElementAttribute(String objLocator, String attribute) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		WebElement element = getElementAfterLoaded(objLocator);
		logger.debug("The value of attribute [" + attribute + "] is : "
				+ element.getAttribute(attribute));
		return element.getAttribute(attribute);
	}

	public static void explicitWait(long milliSec) {
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

	public static void mouseOver(String objLocator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		WebElement element = getElementAfterLoaded(objLocator);
		element.click();

		Actions action = new Actions(TestBase.getWebDriver());
		action.moveToElement(element).build().perform();
	}

	public static boolean verifyPresenceOfText(String objLocator, String text) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		WebElement elementLocator = getElementAfterLoaded(objLocator);
		logger.debug("The text present in the element is : "
				+ elementLocator.getText());
		if (elementLocator.getText().contains(getElementText(objLocator))) {
			return true;
		}
		return false;
	}

	// public static WebElement getElementAfterLoaded(final String objLocator) {
	// logger.debug("Executing Test Step::" + new Object() {
	// }.getClass().getEnclosingMethod().getName());
	//
	// By byValue = getLocator(objLocator);
	// isElementLoaded(byValue);
	// return TestBase.getWebDriver().findElement(byValue);
	// }

	public static WebElement getElementAfterLoaded(final String objLocator) {
		final long t1 = System.currentTimeMillis();
		logger.debug("Executing Test Step::" + new Object() {
			
		}.getClass().getEnclosingMethod().getName());

		By byValue = getLocator(objLocator);
		isElementDisplayed(byValue);
		// scrollTab(objLocator, 50);
		WebElement element = getLoadedElementAfterFluentWait(byValue);
		final long t2 = System.currentTimeMillis();
		logger.debug("Time taken in getElementAfterLoaded method : "
				+ (t2 - t1));
		return element;
	}

	public static WebElement waitAndgetElement(final String objLocator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		By byValue = getLocator(objLocator);
		isElementClickable(byValue);
		return TestBase.getWebDriver().findElement(byValue);
	}

	public static List<WebElement> getMultipleElementsAfterLoaded(
			final String objLocator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		By byValue = getLocator(objLocator);
		isElementLoaded(byValue);
		return TestBase.getWebDriver().findElements(byValue);
	}

	public static boolean isElementLoaded(final By byValue) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		logger.debug("Waiting for element to be loaded. Timeout:"
				+ TestBase.getTimeout());
		Wait<WebDriver> wait = new FluentWait<WebDriver>(
				TestBase.getWebDriver())
				.withTimeout(Duration.ofMillis(TestBase.getTimeout()))
				.pollingEvery(Duration.ofMillis(TestBase.getPollingFrequency()))
				.ignoring(NoSuchElementException.class);
		ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				WebElement element = d.findElement(byValue);
				return element.isDisplayed();
			}
		};
		return wait.until(condition);
	}

	public static boolean isElementClickable(final String locator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());
		By byValue = getLocator(locator);
		return isElementClickable(byValue);
	}

	public static List<String> getMultipleElementTextAfterLoaded(
			final String objLocator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		By byValue = getLocator(objLocator);
		isElementLoaded(byValue);
		List<WebElement> elements = TestBase.getWebDriver().findElements(
				byValue);
		List<String> result = new ArrayList<>();
		for (WebElement element : elements) {
			result.add(element.getText());
		}
		return result;
	}

	private static boolean isElementDisplayed(By byValue) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());
		WebElement element = isElementAvailable(byValue);
		return element == null ? false : element.isDisplayed();
	}

	// public static boolean isElementClickable(By byValue) {
	// logger.debug("Executing Test Step::" + new Object() {
	// }.getClass().getEnclosingMethod().getName());
	//
	// try {
	// explicitWait(1000);
	// logger.debug("Waiting for element to be clickable. Timeout:" +
	// TestBase.getTimeout());
	// return
	// TestBase.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(byValue)).isEnabled();
	// } catch (Exception e) {
	// return false;
	// }
	// }

	private static boolean isElementClickable(By byValue) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		try {
			explicitWait(1000);
			logger.debug("Waiting for element [" + byValue.toString()
					+ "] to be clickable. Timeout:" + TestBase.getTimeout());
			// scrollTab(byValue, 300);
			return TestBase.getWebDriverWait()
					.ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(byValue))
					.isEnabled();
		} catch (StaleElementReferenceException e) {
			logger.error("Stale element exception accoured while accessing the element for first time. Retrying to access it for one more time...");
			explicitWait(2000);
			logger.info("Element object is reinitialized for By : " + byValue
					+ ". Trying to verify if it is clickable.");
			return TestBase.getWebDriverWait()
					.ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(byValue))
					.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isElementEnabled(final By byValue) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		Wait<WebDriver> wait = new FluentWait<WebDriver>(
				TestBase.getWebDriver())
				.withTimeout(Duration.ofMillis(TestBase.getTimeout()))
				.pollingEvery(Duration.ofMillis(TestBase.getPollingFrequency()))
				.ignoring(NoSuchElementException.class);
		ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				WebElement element = d.findElement(byValue);
				return element.isEnabled();
			}
		};
		return wait.until(condition);
	}

	private static WebElement isElementAvailable(final By byValue) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());
		WebElement element = null;
		try {
			element = getLoadedElementAfterFluentWait(byValue);
		} catch (ElementNotVisibleException e) {
			logger.debug("Element with locator [" + byValue
					+ "] is not visible on the page");
		} catch (TimeoutException t) {
			logger.debug("Element with locator [" + byValue
					+ "] is not available on the page in the given timeout ["
					+ TestBase.getTimeout() + "]");
		} catch (NoSuchElementException n) {
			logger.debug("No such element with locator [" + byValue
					+ "] is located on the page");
		} catch (StaleElementReferenceException e) {
			logger.error("Stale element exception accoured while accessing the element for first time. Retrying to access it for one more time...");
			explicitWait(2000);
			element = getLoadedElementAfterFluentWait(byValue);
			logger.info("Element object is reinitialized for By : " + byValue
					+ ". Trying to verify its display.");
		} catch (Exception e) {
			logger.debug("Some error occured while locating element with locator ["
					+ byValue + "] on the page : " + e.getMessage());
		}
		return element;
	}

	private static WebElement getLoadedElementAfterFluentWait(final By byValue) {
		final long t1 = System.currentTimeMillis();
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		Wait<WebDriver> wait = new FluentWait<WebDriver>(
				TestBase.getWebDriver())
				.withTimeout(Duration.ofMillis(TestBase.getTimeout()))
				.pollingEvery(Duration.ofMillis(TestBase.getPollingFrequency()))
				.ignoring(NoSuchElementException.class)
				.ignoring(ElementNotVisibleException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(WebDriverException.class);
		ExpectedCondition<WebElement> condition = new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(byValue);
			}
		};

		WebElement element = null;
		try {
			element = wait.until(condition);
		} catch (TimeoutException ex) {
			logger.error("Element [" + byValue
					+ "] is not available on the page");
		} finally {
			final long t2 = System.currentTimeMillis();
			logger.info("Time taken for element ["
					+ byValue
					+ "] in getLoadedElementAfterFluentWait method for the max timeout ["
					+ TestBase.getTimeout() + "] is : " + (t2 - t1));
		}
		return element;
	}

	public static void overrideTimeout(String timeout) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		if (!(null == timeout || timeout.equals(""))) {
			TestBase.setTimeout(Long.parseLong(timeout));
		}
	}

	public static void overridePollingfrequency(String pollingFrequency) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		if (!(null == pollingFrequency || pollingFrequency.equals(""))) {
			TestBase.setPollingFrequency(Long.parseLong(pollingFrequency));
		}
	}

	public static By getLocator(String objLocator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		String[] str = objLocator.split("===");
		logger.info("Object Identifier " + str[0]
				+ "\t Object Identifier Value " + str[1]);
		By byValue = null;
		switch (str[0].toUpperCase()) {
		case "ID":
			byValue = By.id(str[1]);
			break;
		case "XPATH":
			byValue = By.xpath(str[1]);
			break;
		case "NAME":
			byValue = By.name(str[1]);
			break;
		case "LINKTEXT":
			byValue = By.linkText(str[1]);
			break;
		case "CSS":
			byValue = By.cssSelector(str[1]);
			break;
		case "CLASSNAME":
			byValue = By.className(str[1]);
			break;
		case "TAGNAME":
			byValue = By.tagName(str[1]);
			break;
		}
		return byValue;
	}
	public static int getTableRows(String size)
	{
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());
		
		WebElement element =getElementAfterLoaded(size);
		int rowSize=element.findElements(By.xpath(size)).size();
		return rowSize;
	}

	
	public static void waitForSpinner() {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());
		explicitWait(1000);
		long iCount = 0;
		logger.debug("Waiting for page spinner...");
		while (iCount < TestBase.getTimeout()) {
			if (instantElementCheck("Display",
					By.xpath("//*[@id='pageSpinner']/div"))) {
				try {
					Thread.sleep(TestBase.getPollingFrequency());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				break;
			}
			iCount += TestBase.getPollingFrequency();
		}
		explicitWait(1000);
	}

	public static void waitForSpinner(int milliSecond) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		explicitWait(milliSecond);
		long iCount = 0;
		logger.debug("Waiting for page spinner...");
		while (iCount < TestBase.getTimeout()) {
			if (instantElementCheck("Display",
					By.xpath("//*[@id='pageSpinner']/div"))) {
				try {
					Thread.sleep(TestBase.getPollingFrequency());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				break;
			}
			iCount += TestBase.getPollingFrequency();
		}
		explicitWait(milliSecond);
	}

	public static boolean explicitWaitForElement(int milliSecond,
			String elementStatus, String elementXpath) {
		long t1 = System.currentTimeMillis();
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());
		explicitWait(milliSecond);
		long iCount = 0;
		while (iCount < TestBase.getTimeout()) {
			if (instantElementCheck(elementStatus, elementXpath)) {
				return true;
			} else {
				explicitWait(TestBase.getPollingFrequency());
			}
			iCount += TestBase.getPollingFrequency();
		}
		long t2 = System.currentTimeMillis();
		logger.debug("Time taken in explicit wait for checking the status ["
				+ elementStatus + "] for element with timeout["
				+ TestBase.getTimeout() + "] and polling frequency["
				+ TestBase.getPollingFrequency() + "] is: " + (t2 - t1));
		return false;
	}

	// public static boolean instantElementCheck(String type, By byValue) {
	// logger.debug("Executing Test Step::" + new Object() {
	// }.getClass().getEnclosingMethod().getName());
	//
	// try {
	// WebElement element = TestBase.getWebDriver().findElement(byValue);
	// if (type.equalsIgnoreCase("Display")) {
	// return element.isDisplayed();
	// } else if (type.equalsIgnoreCase("Enable")) {
	// return element.isEnabled();
	// } else if (type.equalsIgnoreCase("Select")) {
	// return element.isSelected();
	// } else {
	// return false;
	// }
	// } catch (Exception e) {
	// return false;
	// }
	// }

	public static boolean instantElementCheck(String type, By byValue) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		logger.info("Instant checking for [" + type + "] for element ["
				+ byValue.toString() + "] on the page");
		WebElement element = null;
		try {
			// setImplicitTime(0);
			element = TestBase.getWebDriver().findElement(byValue);
			// scrollTab(byValue, 100);
			if (type.equalsIgnoreCase("Display")) {
				return element.isDisplayed();
			} else if (type.equalsIgnoreCase("Enable")) {
				return element.isEnabled();
			} else if (type.equalsIgnoreCase("Select")) {
				return element.isSelected();
			} else {
				return false;
			}
		} catch (NoSuchElementException e) {
			logger.error("Element [" + byValue.toString()
					+ "] is not available on the page");
			return false;
		} catch (Exception e) {
			logger.error("Some error occured while instant checking for the element ["
					+ byValue.toString() + "] : " + e.getMessage());
			return false;
		}
		// finally {
		// setImplicitTime(10000);
		// }
	}

	// public static boolean instantElementCheckWithoutScroll(String type,
	// By byValue) {
	// logger.debug("Executing Test Step::" + new Object() {
	// }.getClass().getEnclosingMethod().getName());
	//
	// try {
	// setImplicitTime(0);
	// WebElement element = TestBase.getWebDriver().findElement(byValue);
	// if (type.equalsIgnoreCase("Display")) {
	// return element.isDisplayed();
	// } else if (type.equalsIgnoreCase("Enable")) {
	// return element.isEnabled();
	// } else if (type.equalsIgnoreCase("Select")) {
	// return element.isSelected();
	// } else {
	// return false;
	// }
	// } catch (Exception e) {
	// return false;
	// } finally {
	// setImplicitTime(GridpointConstants.implicitTimeout);
	// }
	// }

	public static void setImplicitTime(long timeInMillisec) {
		TestBase.getWebDriver().manage().timeouts()
				.implicitlyWait(timeInMillisec, TimeUnit.MILLISECONDS);
	}

	public static boolean instantElementCheck(String type, String locator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());
		By byValue = getLocator(locator);
		return instantElementCheck(type, byValue);
	}

	// public static boolean instantElementCheckWithoutScroll(String type,
	// String objLocator) {
	// logger.debug("Executing Test Step::" + new Object() {
	// }.getClass().getEnclosingMethod().getName());
	// By byValue = getLocator(objLocator);
	// return instantElementCheckWithoutScroll(type, byValue);
	// }

	public static void takeScreenshots(Scenario scenario) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		if (scenario.isFailed() && TestBase.getWebDriver() != null) {
			logger.error("Scenario " + scenario.getName()
					+ " is failed. Taking screenshot..");
			try {
				byte[] screenshot = ((TakesScreenshot) TestBase.getWebDriver())
						.getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (UnhandledAlertException e) {
				Alert alert = TestBase.getWebDriver().switchTo().alert();
				alert.dismiss();
				takeScreenshots(scenario);
			} catch (TimeoutException e) {
				logger.error("******* Time out exception occured while accessing webdriver : "
						+ e.getMessage() + " *******");
				TestBase.setWebDriver(null);
				TestBase.getInstance();
				new CommonInit();
			} catch (Exception e) {
				logger.error("Some error occured while taking screenshot : "
						+ e.getMessage());
			}
		}
	}

	public static void setElementAttribute(String objLocator, String value) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		WebElement element = getElementAfterLoaded(objLocator);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a"), value);
	}

	public static boolean verifyElementIsReadOnly(String objLocator) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		WebElement element = getElementAfterLoaded(objLocator);
		// return element.getAttribute("readonly").equals("true") ? true :
		// false;
		return element.isEnabled();
	}

	public static void instantClick(String objLocator)
			throws ElementNotFoundException, ElementNotVisibleException {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		WebElement element = TestBase.getWebDriver().findElement(
				getLocator(objLocator));
		element.click();
		logger.debug("The given element is clicked instantly without wait");
	}

	// if (str1.compareTo(str2) < 0) {
	// System.out.println("str1 is greater than str2");
	// }
	public static boolean isSorted(String sortingType, List<String> list) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		boolean sorted = true;
		if (sortingType.equalsIgnoreCase("Ascending")) {
			for (int i = 1; i < list.size(); i++) {
				if (list.get(i - 1).compareTo(list.get(i)) != 0) {
					if (list.get(i - 1).compareTo(list.get(i)) < 0) {
						sorted = false;
					}
				}
			}
		} else if (sortingType.equalsIgnoreCase("Descending")) {
			for (int i = 1; i < list.size(); i++) {
				if (list.get(i - 1).compareTo(list.get(i)) != 0) {
					if (list.get(i - 1).compareTo(list.get(i)) > 0) {
						sorted = false;
					}
				}
			}
		}
		return sorted;
	}

	public static boolean checkIfClickable(String objLocator)
			throws InterruptedException {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		int i = 0;
		boolean flg = false;
		while (i < 2) {
			try {
				click(objLocator);
				flg = true;
				break;
			} catch (Exception e) {
				logger.error("element not clickable");
				Thread.sleep(2000);
			}
			i++;
		}
		return flg;
	}

	public static void switchToNewBrowserTab(int tabNumber) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		ArrayList<String> newTab = new ArrayList<String>(TestBase
				.getWebDriver().getWindowHandles());
		String oldTab = TestBase.getWebDriver().getWindowHandle();
		newTab.remove(oldTab);
		TestBase.getWebDriver().switchTo().window(newTab.get(tabNumber));
		logger.info("controls are switched to new browser tab no: " + tabNumber);
	}

	public static void closeResources(Scenario scenario) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		takeScreenshots(scenario);
		if (null != TestBase.getWebDriver()) {
			TestBase.getWebDriver().quit();
			TestBase.setWebDriver(null);
		}
	}

	public static void checkPageIsReady(String locator, int waitTime) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info("Executing Test Step::" + methodName);
		String identifier[] = locator.split("===");

		for (int i = 1; i <= waitTime; i++) {
			try {
				Thread.sleep(1000);
				if (TestBase.getWebDriver().findElement(By.id(identifier[1]))
						.isDisplayed()) {
				} else {
					break;
				}
			} catch (Exception e) {
				break;
			}
		}
	}

	public static void waitTillElementIsVisible(String locator, int waitTime) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info("Executing Test Step::" + methodName);
		String identifier[] = locator.split("===");

		for (int i = 1; i <= waitTime; i++) {
			try {
				Thread.sleep(1000);
				if (TestBase.getWebDriver().findElement(By.id(identifier[1]))
						.isDisplayed()) {
					break;
				}
			} catch (Exception e) {
				break;
			}
		}
	}

	public static boolean isAlertPresent() {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		try {
			TestBase.getWebDriver().switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public static Alert moveToAlert() {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		return TestBase.getWebDriver().switchTo().alert();
	}

	public static boolean waitForElementPresentVisible(String locator) {
		boolean flag = false;
		int poolFlag = 10;
		int i = 1;
		try {
			while (flag == false && i <= poolFlag) {
				logger.info("Inside While Loop" + i);
				if (TestBase
						.getWebDriverWait()
						.until(ExpectedConditions
								.visibilityOfElementLocated(getLocator(locator)))
						.isDisplayed()) {
					logger.info("The Element is Found on the Page");
					flag = true;
					break;
				}
				i = 1;
				break;
			}
		} catch (Exception e) {
			logger.info("In the Catch Block");
			i = i + 1;
			logger.info("The Control to Catch block--" + i);
			if (i <= poolFlag) {
				waitForElementPresentVisible(locator);
			} else {
				return flag;
			}
		}
		return flag;
	}

	public static boolean waitForElementEnabled(String locator) {
		boolean flag = false;
		int poolFlag = 10;
		int i = 1;

		try {
			while (flag == false && i <= poolFlag) {
				logger.info("Inside While Loop" + i);
				if (TestBase
						.getWebDriverWait()
						.until(ExpectedConditions
								.elementToBeClickable(getLocator(locator)))
						.isEnabled()) {
					logger.info("The Element is Found on the Page");
					flag = true;
					break;
				}
				i = 1;
				break;
			}
		} catch (Exception e) {
			logger.info("In the Catch Block");
			i = i + 1;
			logger.info("The Control to Catch block--" + i);
			if (i <= poolFlag) {
				waitForElementEnabled(locator);
			} else {
				return flag;
			}
		}
		return flag;
	}

	public static boolean assertElementDisplayed(String objLocator,
			String message) {
		if (!BaseUtil.verifyElementDisplayed(objLocator)) {
			Assert.fail(message);
		}
		return true;
	}

	public static String getDownloadsPath() {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());

		String userDir = System.getProperty("user.home");
		char cforwardslash = (char) 47;
		char cbackslash = (char) 92;
		String path = userDir.replace(cbackslash, cforwardslash);
		logger.info("The download path is : " + path + "/Downloads/");
		return path + "/Downloads/";
	}

	public static boolean isTableVisible(String endpointTable) {
		logger.debug("Executing Test Step::" + new Object() {
		}.getClass().getEnclosingMethod().getName());
		WebElement element = getElementAfterLoaded(endpointTable);
		boolean tableVisibility;
		if(element.isDisplayed())
		{
			tableVisibility=true;
		}
		else
		{
			tableVisibility=false;
		}
		return tableVisibility;
	}

}