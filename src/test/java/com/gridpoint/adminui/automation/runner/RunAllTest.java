package com.gridpoint.adminui.automation.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
// @ExtendedCucumberOptions(jsonUsageReport = "target/cucumber-usage.json",
// usageReport = true, outputFolder = "target")
@CucumberOptions(

		plugin = { "json:target/cucumber.json", "pretty" }, tags = {

		}, snippets = SnippetType.CAMELCASE, monochrome = true, features = {
				/*"src/test/resources/a_loginPage.feature",*/
				"src\\test\\resources\\e_userPage.feature"}, glue = { "com.gridpoint" })
public class RunAllTest {

}
