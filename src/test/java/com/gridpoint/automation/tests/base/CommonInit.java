package com.gridpoint.automation.tests.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class CommonInit {
	private static TestBase testBase;
	private static WebDriver driver;
	private static Properties props = new Properties();

	public CommonInit() {
		if (null == testBase) {
			testBase = TestBase.getInstance();
		}
		if (null == TestBase.getWebDriver() || null == driver) {
			driver = TestBase.getWebDriver();
		}
	}

	public static TestBase getTestBase() {
		return testBase;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static Properties getProps() {
		return props;
	}

	public static Properties readProps() {
		return props;
	}
}
