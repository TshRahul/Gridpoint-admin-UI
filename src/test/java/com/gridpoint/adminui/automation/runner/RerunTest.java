package com.gridpoint.adminui.automation.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "@target/rerun.txt" }, plugin = { "pretty", "html:target/cucumber-rerun-htmlreport",
		"json:target/cucumber.json" }, glue = {"com.gridpoint.adminui.automation.tenant","com.gridpoint.adminui.automation.login"})
public class RerunTest {
}