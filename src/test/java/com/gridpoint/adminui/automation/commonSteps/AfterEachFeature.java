package com.gridpoint.adminui.automation.commonSteps;

import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.util.BaseUtil;

import cucumber.api.Scenario;
import cucumber.api.java.After;

public class AfterEachFeature extends CommonInit {

	@After("@AdminUI_LoginPage,@AdminUI_AuditPage,@AdminUI_FirmwarePage,@AdminUI_TenantPage,@DatafixPage,@UserPage,@EndpointPage,@userRolePermissionsPage,@SitePage")
	public void afterClass(Scenario scenario) {
		BaseUtil.takeScreenshots(scenario);
	}
}
