@userRolePermissionsPage 
Feature: User Role Permissions test 
	Here we will test the User Role Permissions functionality.
	For this, we will login with various role's credentials and will test
	the different scenarios including behavior and function test of the User Role Permissions.
	
####################################### Role GPAdmin  ##################################################################
# Automation need to do for: BILLING_COMPARISON_REPORT, BYPASS_THROTTLING, EDIT_CONFIGURATION, DOWNLOAD_CONFIGURATION, SEARCH_AUDIT, UPDATE_ENDPOINT_PASSWORD, VIEW_COMMISSIONING
	
#@AddDiffrentRolesUsers
#Scenario: Scenario_01: Login into the application as Role GPAdmin user and create test data for diffrent users roles 
#	When initialize dummy users in database for different roles and remove dependency with "userRolePage_tenantBot1" 
#		| URP_GPAdmin_testBot | URP_GPSupport_testBot | URP_GPAnalyst_testBot |  URP_CustomerAnalyst_testBot | URP_CustomerSiteManager_testBot |
#	Then create dummy test data and assocated with dummy users 
#		| URP_GPAdmin_testBot | URP_GPSupport_testBot | URP_GPAnalyst_testBot |  URP_CustomerAnalyst_testBot | URP_CustomerSiteManager_testBot |
		
@URP_GPAdminEditUserPageCapability
Scenario: Scenario_01: Login into the application as Role GPAdmin user and verify default Capability then assign all Capability 
	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_01" 
	Given we are on "User" page and search field is enabled and visible for URP  
	Then verify the default available tabs for role "autoAdminTest" user 
		| Sites | Users | Tenants | Commissioning | Endpoints | Firmware | DataFix | Audit | 
	Then enter user name "autoAdminTest" in search field 
	Then click "search" button on user list page 
	Then select user "autoAdminTest" from the "User" details grid 
	Then click "Capabilities" accordion on edit user
	Then verify the default available capilibiltys of role "GPAdmin"  
	Then verify default selected capabilities and then select all available capabilities of role "GPAdmin"  
	Then click "User:Save and Close" button on edit user page

@URP_GPAdminAddUserCapability 
Scenario: Scenario_02: Login into application as Role GPAdmin user and verify ADD USER capability
    Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_02" 
	Given we are on "User" page and search field is enabled and visible for URP 
	Then delete the "Users" data used as test data for URP page     
		| URP_GPAdmin_testUserBot1 |
		
     # GPAdmin ADD_USER Capability
	Then click "Add User" button on "User" list page   
	Then enter valid values on add user page 
		| 	PROPERTIES 			| VALUES 						|
		| 	UserName 			| URP_GPAdmin_testUserBot1		|
		|	FirstName			| userRoleFirstName 			|
		|	LastName			| userRoleLastName	 			|
		|	Email				| gajendra.rawat@gridpoint.com 	|
		|	Locale				| English (United States) 		|	
		|	Unit of Temperature	| FAHRENHEIT 					|
		|	Role				| ROLE_GP_SUPPORT 				|	
	Then click on the "User:Save" button on add user popup 
	Then verify on "EDIT USER" model have below "PROPERTIES" values 
		| 	PROPERTIES 			| VALUES 						|
		| 	UserName 			| URP_GPAdmin_testUserBot1 		|
		|	FirstName			| userRoleFirstName 			|
		|	LastName			| userRoleLastName	 			|
		|	Email				| gajendra.rawat@gridpoint.com 	|
		|	Locale				| English (United States) 		|	
		|	Unit of Temperature	| FAHRENHEIT 					|
		|	Role				| ROLE_GP_SUPPORT 				|
			
			
@URP_GPAdminEditUserCapability 
Scenario: Scenario_03: Login into application as Role GPAdmin user and verify the EDIT USER capability 
	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_03" 
	Given we are on "User" page and search field is enabled and visible for URP
	Then enter user name "autoSupportTest" in search field 
	Then select user "autoSupportTest" from the "User" details grid 
	Then click "Permission" accordion on edit user
	Then click "Permission:Save and Close" button on edit user 
	Then enter user name "autoCSM_Test" in search field 
	Then select user "autoCSM_Test" from the "User" details grid 
	Then click "Permission" accordion on edit user
	Then click "Permission:Save and Close" button on edit user 
	Then enter user name "autoCustomerAnalystTest" in search field 
	Then select user "autoCustomerAnalystTest" from the "User" details grid 
	Then click "Permission" accordion on edit user
	Then click "Permission:Save and Close" button on edit user  
	Then enter user name "autoAnalystTest" in search field 
	Then select user "autoAnalystTest" from the "User" details grid 
	Then click "Permission" accordion on edit user
	Then click "Permission:Save and Close" button on edit user  
		
@URP_GPAdminSiteCapability 
Scenario: Scenario_04: Verify SITE capability of GPAdmin user 
    Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_04" 
	Given we are on "Site" page and search field is enabled and visible for URP
	Then delete the "Sites" data used as test data for URP page
		| sitePage_siteBot1 |
	#GPAdmin ADD_SITE
	Then click "Add Site" button on "Site" list page 
#	Then enter valid values for the test site "1"  
    Then enter the test data "1" for the following fields to create new testData for URP  
	    | Site ID  | Site Description | Address1 | Address2 | City | State | Postal Code | Country | Site Area | System Size | Path |
	Then select the following check boxes for URP
		| Has Control | Solar Panel | Active |     
	Then click "SITE:SAVE" button on add_edit site model 
	Then click "Accept Verified Data" button on address verification model 
	Then click "SITE:SAVE" button on add_edit site model 
	Then user should be navigated on sites page with a label saying "Site sitePage_siteBot1 saved." 
	 
	#GPAdmin SEARCH/VIEW/EDIT_SITE
	When enter the site name "sitePage_siteBot1" into search field 
	Then select the site "sitePage_siteBot1" from the "Site" details grid for URP 
	And verify "Edit Site" page should be open 
	Then click on "SITE:PATHS" accordion of site
	Then verify "Site Path" text box and "Add Path" button is avilable on edit site model 
	Then click in the "Site Path" text box 
	Then updates the site path values "/GridPointTestAutomation/botAutomationSitePath" on edit site model 
	Then click "Site:Save And Close" button on add_edit site model 
	Then user should be navigated on sites page with a label saying "Site sitePage_siteBot1 saved." 
	When enter the site name "sitePage_siteBot1" into search field
	Then select the site "sitePage_siteBot1" from the "Site" details grid for URP
	Then click on "SITE:PATHS" accordion of site
	Then verify updated site path is "/GridPointTestAutomation/botAutomationSitePath" on edit site model
	Then click "Site:Save And Close" button on add_edit site model 		
					
@URP_GPAdminVirtualChannelCapability
Scenario: Scenario_05: Verify VIRTUAL_CHANNEL capability of GPAdmin user 
	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_05" 
	Given we are on "Site" page and search field is enabled and visible for URP
	When enter the site name "sitePage_siteBot1" into search field 
	Then select the site "sitePage_siteBot1" from the "Site" details grid for URP 
	Then click on "Details" accordion of site
	
	#GPAdmin ADD_VIRTUAL_CHANNEL Capability
	Then click on "Channels" accordion of site
	Then search virtual channel "URP_testSiteBot1VC1" before add by "GPAdmin"     
	Then click "Add Virtual Channel" button 
	Then select "DEGREES_FAHRENHEIT" from type selection box on add virtual channel popup 
	Then click "Add" buttons of available channels 
	Then enter the virtual channel name "URP_testSiteBot1VC1" into name field 
	Then select measure type "2" on add virtual channel popup       
	Then select metadata category on edit virtual channel popup 
	Then click "Virtual Channel:Save" button on add virtual channel model 
		
	# ADD Duplicate Channel
	Then click on "Channels" accordion of site
	Then click "Add Virtual Channel" button 
	Then select "DEGREES_FAHRENHEIT" from type selection box on add virtual channel popup 
	Then click "Add" buttons of available channels 
	
	#GPAdmin SEARCH_VIRTUAL_CHANNEL Capability
	Then enter the virtual channel name "URP_testSiteBot1VC1" into name field 
	Then select measure type "2" on add virtual channel popup 
	Then select metadata category on edit virtual channel popup 
	Then click "Virtual Channel:Save" button on add virtual channel model 
	Then verify "Please correct the following errors:" message popup window is displayed 
	Then click "Virtual Channel:Close" button on add virtual channel model 
	Then click "X VIRTUAL CHANNEL" button on add virtual channel model 

	#GPAdmin EDIT_VIRTUAL_CHANNEL Capability
	Then click "Virtual Channel:Edit" button of virtual channel 
	Then update the virtual channel name "URP_testSiteBot1VC2_UPDATE" 
	Then click "Virtual Channel:Update" button of virtual channel 
	
	#GPAdmin VIEW_VIRTUAL_CHANNEL Capability
	Then click "Virtual Channel:View" button of virtual channel 
	
	#GPAdmin DELETE_VIRTUAL_CHANNEL Capability
	Then click "Virtual Channel:Remove" button of virtual channel 
	Then click "Virtual Channel:OK" button of virtual channel 
	
	# TODO : check below steps till end : 1/5/2018 09:05:24 PM
	#GPAdmin DELETE_SITE Capability
#	Then click "Delete" button on edit site model 
#	Then click "Delete:OK" button on edit site model 
#	When enter the site name "URP_GPAdminTestSiteBot2" into search field 
#	Then select the site "URP_GPAdminTestSiteBot2" from the site details grid page
#	Then click "Delete" button on edit site model 
#	Then click "Delete:OK" button on edit site model 
	
@URP_GPAdminTenantCapability 
Scenario: Scenario_06: Verify TENANTS capabilities of GPAdmin user
    Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_06" 
	Given we are on "Tenant" page and search field is enabled and visible for URP
	#GPAdminAddTenantsCapability
	
    Then delete the "Tenant" data used as test data for URP page 
		| URP_testTenantBot1 |		
	Then click "Add Tenant" button on tenant tab
	Then enter the "URP_testTenantBot1" in "Name" textbox on add tenant model 
	Then click "Tenant:Save" button on add tenant popup
	Then enter the tenant name "URP_testTenantBot1" in the search field 
	Then select the tenant "URP_testTenantBot1" from "Tenant" details grid for URP
	
	#GPAdminEditTenantsCapability
	
	Then update the tenant name "URP_testTenantBot1Updated" on tenant detail model 
	Then click "TENANT:SAVE" button on edit tenant popup
	Then enter the tenant name "URP_testTenantBot1Updated" in the search field 
	Then select the tenant "URP_testTenantBot1Updated" from "Tenant" details grid for URP
	Then verify the tenant "Name" is "URP_testTenantBot1Updated" on tenant detail model 
	
	#GPAdminDeleteTenantsCapability
	Then click "DELETE TENANT" button on edit tenant popup
	Then click "TENANT:DELETE_OK" button for delete confirmation 
	Then enter the tenant name "URP_testTenantBot1Updated" in the search field and verify "No records found." 
		
@URP_GPAdminEndpointCapability 
Scenario: Scenario_07: Verify ENDPOINTS capabilities of GPAdmin user
    Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_07" 
	Given we are on "Endpoint" page and search field is enabled and visible for URP 
	
	#GPAdminAddEndpointCapability
	Then delete the "Endpoint" data used as test data for URP page 
		| URP_testEndpointBot1 | URP_testEndpointBot2 |	
			
	Then click "Add Endpoint" button on endpoint page 
	Then enter the "URP_testEndpointBot1" in "Serial" field on add endpoint model 
	Then select "EC100" from type selection box on "add" enpoint popup 
	Then click the "Endpoint:Save" button on the add endpoint popup 
	Then click "Add Endpoint" button on endpoint page 
	Then enter the "URP_testEndpointBot2" in "Serial" field on add endpoint model 
	Then select "EC1000" from type selection box on "add" enpoint popup 
	Then click the "Endpoint:Save" button on the add endpoint popup 
	Then enter the endpoint name "URP_testEndpointBot1" in the search field  
	Then click "Endpoint:Search" button on endpoint page 
	Then select the entered endpoint id "URP_testEndpointBot1" from the "Endpoint" details grid page 
	Then verify the endpoint name "URP_testEndpointBot1" and type "EC100" is on edit endpoint model 
	
	#GPAdminEditEndpointCapability	
	Then select "EC1000" from type selection box on "edit" enpoint popup 
	Then click the "Endpoint:Save And Close" button on the edit endpoint popup 
	Then select the entered endpoint id "URP_testEndpointBot1" from the "Endpoint" details grid page 
	Then verify the endpoint name "URP_testEndpointBot1" and type "EC1000" is on edit endpoint model 
	 	 
@URP_GPAdminEndpointFirmwareCapability 
Scenario: Scenario_8: Verify ENDPOINTS FIRMWARE capabilities of GPAdmin user 
	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_08" 
	Given we are on "Endpoint Firmware" page and search field is enabled and visible for URP
	Then delete the "Endpoint Firmware" data used as test data for URP page  
		| URPtestEndpointFirmwareBot1 | 
	
	#GPAdminAddEndpointFirmwareCapability
	Then click "Add Endpoint Firmware" button on endpoint firmware page 
	Then click on the "Choose File" button on add "Endpoint Firmware" popup and select "Valid" file for URP  
	Then enter "URPtestEndpointFirmwareBot1" in "Name" field on add endpointfriamware model 
	Then enter the test data "1" for the following fields to create new firmware for URP     
		 | Endpoint Firmware:Version | Endpoint Firmware:Endpoint Type | Endpoint Firmware:Release Date |
	Then click the "Save Endpoint Firmware" button on the add endpointfirware popup 
	Then enter firmware name "URPtestEndpointFirmwareBot1" in "endpointfirmware_search" search field 
	Then click on "Endpoint Firmware:Search" button on Endpointfirmware page 
	Then select the endpoint firmware "URPtestEndpointFirmwareBot1" from the "Endpoint Firmware" details grid for URP 
	Then verify the endpoint firmware name "URPtestEndpointFirmwareBot1" on edit endpoint firmware model 
	
	#GPAdminEditEndpointFirmwareCapability
	Then update the endpoint firmware name "URPtestEndpointFirmwareBot1Updated" on edit enpoint firmware detail model 
	Then click the "Edit Endpoint Firmware:Save" button on the edit endpointfirware popup 
	Then enter firmware name "URPtestEndpointFirmwareBot1Updated" in "endpointfirmware_search" search field 
	Then click on "Endpoint Firmware:Search" button on Endpointfirmware page 
	Then select the endpoint firmware "URPtestEndpointFirmwareBot1Updated" from the "Endpoint Firmware" details grid for URP 
	Then verify the endpoint firmware name "URPtestEndpointFirmwareBot1Updated" on edit endpoint firmware model 
	
	#GPAdminDeleteEndpointFirmwareCapability
	Then click the "Delete Endpoint Firmware" button on the edit endpointfirware popup 
	Then click "ENDPOINT FIRMWARE:DELETE_OK" button for delete confirmation 
	Then enter firmware name "URPtestEndpointFirmwareBot1Updated" in "endpointfirmware_search" search field 
	Then click on "Endpoint Firmware:Search" button on Endpointfirmware page 
	Then enter endpoint friamware name "URPtestEndpointFirmwareBot1Updated" in the search field and verify "No records found." 
	
	#GPUP-31870:Bulk for Firmware Upgrade 
	#GPAdminViewUpdateEndpointFirmware
@URP_GPAdminUpdateEndpointFirmwareCapability 
Scenario: Scenario_9: Login into the application as Role GPAdmin user and verify Firmware capabilitys 
	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_09" 
	Given we are on "Endpoint Firmware" page and search field is enabled and visible for URP 
	Then verify the avilable sub tabs of "Firmware" tab for role "URP_GPAdmin_testBot" users 
		| Endpoint Firmware | Peripheral Firmware | Update Endpoint Firmware | 	
	Then click on the "Update Endpoint Firmware" tab of adminui 
	Then select "First" checkbox on update endpoint sub tab
	Then verify the "Update Firmware" button on "Update Endpoint Firmware" tab for role "GPAdmin" 
	Then click "Update Firmware" button on update endpoint firmware	
	Then verify "Select Firmware Version" popup is open and below objects are available on this popup
		| PopUpName:Select Firmware Version | Label:EC1K: | Label:EC100: | EC1K:SelectionBox | EC100:SelectionBox | Button:Cancel | Button:OK | 	

@URP_GPAdminPeripheralFirmwareCapability 
Scenario: Scenario_10: Verify PERIPHERAL FIRMWARE capabilities of GPAdmin user 
	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_10" 
	Given we are on "Peripheral Firmware" page and search field is enabled and visible for URP
	Then delete the "Peripheral Firmware" data used as test data for URP page 
		| -3.-3 | 
		
	#GPAdminAddPeripheralFirmwareCapability
	Then click "Add Peripheral Firmware" button on peripheral firmware page 
	Then click on the "Choose File" button on add "Peripheral Firmware" popup and select "Valid" file for URP
	Then enter the test data "1" for the following fields to create new firmware for URP  
		| Peripheral Firmware:Peripheral Type | Peripheral Firmware:Release Date | Peripheral Firmware:Version Major | Peripheral Firmware:Version Minor | Peripheral Firmware:Controller Version Major | Peripheral Firmware:Controller Version Minor | Peripheral Firmware:Controller Version Revision |
	When click on the "Peripheral Firmware:Apply" button on add peripheralfirmware popup 
	
	#GPAdminDeletePeripheralFirmwareCapability
	When enter controller min version "-3.-3" in peripheralfirmware search field 
	Then click on "Peripheral Firmware:Search" button on Peripheralfirmware page 
	Then select the peripheral firmware "-3.-3.-3" from the "Peripheral Firmware" details grid 
	Then verify the details of the selected firmware in following elements on firmware detail popup for URP 
		| Peripheral Firmware:Peripheral Type | Peripheral Firmware:Peripheral Type ID | Peripheral Firmware:Release Date | Peripheral Firmware:Version | Peripheral Firmware:Min Controller Version |
#	Then click on "Delete Peripheral Firmware" button of selected peripheral firmware 
		
@URP_GPAdminPortalCapabilitys 
Scenario: Scenario_11: Verify GPAdmin can navigate into the portal and after performing tasks comeback in admin ui 
	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_11" 
	Given we are on "Site" page and search field is enabled and visible for URP
	Then click on the portal button which navigate to HTML Portal 
	Then verify the default available tabs for role "URP_GPAdmin_testBot" user 
		| HOME | CONTROLS | ALARMS | PROJECTS | Data |	
	
	#GPAdminEditHVACCapability
	
	Then click site tree "clear" button 
	Then click the "Control" tab in the portal	
	Then click the "HVAC" tab in the portal 
	Then enter the "GPEC" site name in the site search box and select it to check "HVAC" capability
	Then click on "Select All:Devices"
	Then verify following buttons are enabled 
		| Override | Edit |
	Then click the "Edit" button and verify is device "HVAC1" model open successfully 
	Then verify "Edit HVAC:Save" button should be disabled and "Edit HVAC:Cancel" button should be enabled 
	Then enter "URP_Test data" in "Schedule" notes text area 
	Then verify following buttons are enabled 
		| Edit HVAC:Save | Edit HVAC:Cancel |
	Then click the "Edit HVAC:Cancel" button of edit hvac 
	
	#GPAdminEditLightCapability
	Then click the "Lighting/Loads" tab in the portal 
	Then click site tree "clear" button 
	Then enter the "GPEC" site name in the site search box and select it to check "LIGHTING" capability 
	Then click on "Select All:Devices"
	Then verify following buttons are enabled 
		| Light:Override | Light:Edit |
	
	#GPAdminVIEW_REPORTS_Capability
	Then click the "Data" tab in the portal 
	Then click the "Reports" tab in the portal 
	
	# TODO : below option was also there preivously. check and validate : 1/8/2018 8:32:19 PM
	| Energy Usage Report |
	Then verify following reports are display 
		| Voltage Imbalance Report | Override Report | Site Schedules Report | Site Schedules History Report | HVAC Test Report |
	      
	#GPAdminEditSiteHoursCapability
	Then click site tree "clear" button 
	Then click the "Control" tab in the portal 
	Then click in the "Portal:Site Search" text box in the portal 
	Then enter the "GPEC" site name in the site search box and select it to check "Hours" capability 
	Then verify the "Edit" button on "Regular Hours" tab
	Then click on "Edit Regular Hours Button" 
	Then click on "Regular Hours Cancel Button" 
	Then click on "Custom Hours Tab" 
	Then click on "Edit Custom Hours Button" 
	Then click on "Custom Hours Cancel Button" 
	Then click on the admin button which navigate to Admin UI 
	
	
#@URP_GPAdminUsersViewCapabilitys 
#Scenario: Scenario_12:GPUP-26329: Verify GPAdmin users no longer can see other role admin users 
#	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_12" 
#	Given we are on "Site" page and search field is enabled and visible for URP
#	Then delete the "Tenant" data used as test data for URP page 
#		| GPUP-26329_testTenantBot |
#		
#	Then click on "Tenants" tab
#	Then click "Add Tenant" button on tenant tab
#	Then enter the "GPUP-26329_testTenantBot" in "Name" textbox on add tenant model 
#	Then click "Save" button on add tenant popup
#	Then click on "Sites" tab
#	
#	Then delete the "Sites" data used as test data for URP page
#		| GPUP-26329_testSiteBot1 |
#	Then click "Add Site" button on "Site" list page 
#    Then enter the test data "2" for the following fields to create new testData for URP  
#	    | Site ID  | Site Description | Address1 | Address2 | City | State | Postal Code | Country | Site Area | System Size | Path |
#	Then select the following check boxes for URP
#		| Has Control | Solar Panel | Active | 
#	Then click "SITE:SAVE" button on add_edit site model
#	Then click "Accept Verified Data" button on address verification model
#	Then click "SITE:SAVE" button on add_edit site model 
#	Then update site "GPUP-26329_testSiteBot1" tenant name "GPUP-26329_testTenantBot"	
#	Then click on "Users" tab
#	Then click "Add User" button on "User" list page 
#	Then enter valid values on add user page 
#		| 	PROPERTIES 			| VALUES 						|
#		| 	UserName 			| GPUP-26329_testUser			|
#		|	FirstName			| testUserFirstNameBot 			|
#		|	LastName			| testUserLastNameBot	 		|
#		|	Email				| gajendra.rawat@gridpoint.com 	|
#		|	Role				| ROLE_GP_SUPPORT 				|	
#	Then click on the "Save" button on add user popup 
#	Then click on the "X" button on add user popup
#	Then click "Add User" button on "User" list page 
#	Then enter valid values on add user page 
#		| 	PROPERTIES 			| VALUES 						|
#		| 	UserName 			| GPUP-30805_testUser			|
#		|	FirstName			| testUserFirstNameBot 			|
#		|	LastName			| testUserLastNameBot	 		|
#		|	Email				| gajendra.rawat@gridpoint.com 	|
#		|	Role				| ROLE_GP_ADMIN 				|	
#	Then click on the "Save" button on add user popup 
#	Then click on the "X" button on add user popup
#	Then update test users 
#		| GPUP-26329_testUser | GPUP-30805_testUser |
#	Then assign tenant "GPUP-26329_testTenantBot" to user "GPUP-26329_testUser"
#	Then assign tenant "GridPoint Test Automation" to user "GPUP-30805_testUser"
#	Then click on the portal button to logout from application
#	When login with user "GPUP-26329_testUser" in admin ui 
#	Then verify the "Sites" for user "GPUP-26329_testUser"
#	Then click on "USERS" tab
#	Then click "Hide Disabled" checkbox on user tab
#	Then click on "Tenants" tab
#	Then click "Hide Inactive:Unchecked" checkbox on tenants tab
#	Then verify the "Tenants" for user "GPUP-26329_testUser"
#	Then click on "Endpoints" tab
	
	
	
	
	
	
	
	
	
######################################## Role GPSupport ##################################################################
# Automation need to do for: DELETE_ENDPOINT_FIRMWARE, DELETE_SITE, DELETE_CHANNEL, EDIT_ENDPOINT, EDIT_TENANTS

@URP_GPSupportDefaultCapability 
Scenario: Scenario_13: Verify default capabilities of newly creatd Role GPSupport user on Edit User accordion 
	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_13" 
	Given we are on "Site" page and search field is enabled and visible for URP
	Then click on the portal button to logout from application
	When login with user "autoSupportTest" in admin ui 
	Then verify the default available tabs for role "GPSupport" user 
		| Users | Tenants | Sites | Endpoints | Firmware | Commissioning |
	Then click on "USERS" tab 
	Then enter user name "autoSupportTest" in search field 
	Then select user "autoSupportTest" from the "User" details grid 
	Then click "Capabilities" accordion on edit user
	Then verify the default available capilibiltys of role "GPSupport" 
	
@URP_GPSupportAssignAllCapability
Scenario: Scenario_14: Verify assign all capabilities to GPSupport user by GPAdmin user from capabilities accordian of edit user model 
	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_14" 
	Given we are on "Site" page and search field is enabled and visible for URP
	Then click on "USERS" tab 
	Then enter user name "autoSupportTest" in search field 
	Then select user "autoSupportTest" from the "User" details grid 
	Then click "Capabilities" accordion on edit user
	
	# Assigning all Capability to GPSupport
	Then verify default selected capabilities and then select all available capabilities of role "GPSupport" 
	Then click "Capabilities:Save And Close" button on edit user 
	
	
@URP_GPSupportSiteCapabilities 
Scenario: Scenario_15: Verify SITE capabilities of GPSupport user 
    Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_15" 
	Given we are on "Site" page and search field is enabled and visible for URP
	Then click on the portal button to logout from application
	When login with user "autoSupportTest" in admin ui 
	Then delete the "Sites" data used as test data for URP page
		| sitePage_siteBot1 |
		
	# GPSupport ADD_SITE Capability
	Then click "Add Site" button 
    Then enter the test data "1" for the following fields to create new testData for URP  
	    | Site ID  | Site Description | Address1 | Address2 | City | State | Postal Code | Country | Site Area | System Size | Path |
	Then select the following check boxes for URP
		| Has Control | Solar Panel | Active |  
	Then click "SITE:SAVE" button on add_edit site model
	Then click "Accept Verified Data" button on address verification model 
	Then click "SITE:SAVE" button on add_edit site model 
	Then user should be navigated on sites page with a label saying "Site sitePage_siteBot1 saved."
	
	#GPAdmin SEARCH_SITE/VIEW_SITE/EDIT_SITE/EDIT_SITE_PATH Capability
	When enter the site name "sitePage_siteBot1" into search field 
	Then select the site "sitePage_siteBot1" from the "Site" details grid for URP 
	And verify "Edit Site" page should be open
	Then click on "SITE:PATHS" accordion of site
	Then verify "Site Path" text box and "Add Path" button is avilable on edit site model 
	Then click in the "Site Path" text box 
	Then updates the site path values "/GridPointTestAutomation/botAutomationSitePath" on edit site model 
	Then click "Site:Save And Close" button on add_edit site model 
	Then user should be navigated on sites page with a label saying "Site sitePage_siteBot1 saved." 
	Then select the site "sitePage_siteBot1" from the "Site" details grid for URP 
	Then click on "Paths" accordion of site
	Then verify updated site path is "/GridPointTestAutomation/botAutomationSitePath" on edit site model
	Then click "Site:Save And Close" button on add_edit site model
	
	
@URP_GPSupportVirtualChannelCapabilities
Scenario: Scenario_16: Verify Virtual Channel capabilities of GPSupport user 
    Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_16" 
	Given we are on "Site" page and search field is enabled and visible for URP
	Then click on the portal button to logout from application
	When login with user "autoSupportTest" in admin ui
	
	# GPSupport ADD_VIRTUAL_CHANNEL Capability
	When enter the site name "sitePage_siteBot1" into search field 
	Then click "Search" button on add_edit site model
	Then select the site "sitePage_siteBot1" from the "Site" details grid for URP 
	Then click on "Channels" accordion of site
	#Then search virtual channel "URP_testSiteBot1VC1" before add 
	Then search virtual channel "URP_testSiteBot1VC2" before add by "GPSupport"
	Then click "Add Virtual Channel" button 
	Then select "DEGREES_FAHRENHEIT" from type selection box on add virtual channel popup 
	Then click "Add" buttons of available channels 
	Then enter the virtual channel name "URP_testSiteBot1VC2" into name field 
	Then select measure type "2" on add virtual channel popup
	Then select metadata category on edit virtual channel popup 
	Then click "Virtual Channel:Save" button on add virtual channel model 
	
	# GPSupport SEARCH_VIRTUAL_CHANNEL Capability
	Then click on "Channels" accordion of site
	Then enter the virtual channel name "URP_testSiteBot1VC2" into search field  
	
	# GPSupport EDIT_VIRTUAL_CHANNEL Capability
	Then click "Virtual Channel:Edit" button  
	Then update the virtual channel name "URP_testSiteBot1VC2_UPDATE"
	Then click "Virtual Channel:Update" button 
	
	# GPSupport VIEW_VIRTUAL_CHANNEL Capability
	Then click "Virtual Channel:View" button 
	Then click "Virtual Channel:Cancel" button of virtual channel 
	
	#Delete test site from database because GPSupport can't delete site from ui. 
	Then delete the "Sites" data used as test data for URP page 
		| sitePage_siteBot1 |
	
	
@URP_GPSupportAddEditUserCapability 
Scenario: Scenario_17: Login into application as Role GPSupport user and verify ADD USER capability 
	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_17" 
	Given we are on "Site" page and search field is enabled and visible for URP
	Then click on the portal button to logout from application
	When login with user "autoSupportTest" in admin ui 
	Then click on "Users" tab
	
	Then delete the "Users" data used as test data for URP page     
		| URP_GPSupport_testUserBot |
	Then click "Add User" button on "User" list page 
	Then enter valid values on add user page 
		| 	PROPERTIES 			| VALUES 						|
		| 	UserName 			| URP_GPSupport_testUserBot		|
		|	FirstName			| userRoleFirstName  			|
		|	LastName			| userRoleLastName	 			|
		|	Email				| gajendra.rawat@gridpoint.com 	|
		|	Role				| ROLE_GP_ANALYST  				|	
	Then click on the "User:Save" button on add user popup 
	Then verify on "EDIT USER" model have below "PROPERTIES" values 
		| 	PROPERTIES 			| VALUES 						|
		| 	User Name 			| URP_GPSupport_testUserBot		|
		|	First Name			| userRoleFirstName 			|
		|	Last Name			| userRoleLastName	 			|
		|	Email				| gajendra.rawat@gridpoint.com 	|
		|	Locale				| French (Canada)		 		|	
		|	Unit of Temperature	| CELSIUS	 					|
		|	Role				| ROLE_GP_ANALYST 				|
	Then click "User:Save and Close" button on edit user tab 
	
	#@GPSupportEditUserCapability
	Then enter user name "URP_CustomerAnalyst_testBot" in search field 
	Then select user "URP_GPSupport_testUserBot" from the "User" details grid 
	Then click "Permission" accordion on edit user
	Then click on "Permission:Save and Close" 
#	Then close the "Edit User" model 
	
	
@URP_GPSupportEndpointCapability 
Scenario: Scenario_18: Verify ENDPOINTS capabilities of GPSupport user 
	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_18" 
	Given we are on "Site" page and search field is enabled and visible for URP 
	Then click on the portal button to logout from application
	When login with user "autoSupportTest" in admin ui 
	Then click the "Endpoints" tab 
	
	#GPSupportEditEndpointCapability (Need to Improve: only show associated endpoints)		
#	Then enter the endpoint name "URP_testEndpointBot2" in the search field 
#	Then click "Search" button on endpoint page 
#	Then select the entered endpoint id "URP_testEndpointBot2" from the endpoint details grid page 
#	Then verify the enpoint name "URP_testEndpointBot2" and type "EC1000" is on edit endpoint model 
#	Then select "EC100" from type selection box on "Edit" enpoint popup 
#	Then click the "EDIT ENDPOINT CANCEL" button on the edit endpoint popup 
#	Then click the "OK_POPUP" button on the edit endpoint popup	

@URP_GPSupportEndpointFirmwareCapability 
Scenario: Scenario_19: Verify ENDPOINTS FIRMWARE capabilities of GPSupport user 
	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_19" 
	Given we are on "Endpoint Firmware" page and search field is enabled and visible for URP
	Then delete the "Endpoint Firmware" data used as test data for URP page  
		| URPtestEndpointFirmwareBot2 | 
	Then click "Add Endpoint Firmware" button on endpoint firmware page 
	Then click on the "Choose File" button on add "Endpoint Firmware" popup and select "Valid" file for URP 
	Then enter "URPtestEndpointFirmwareBot2" in "Name" field on add endpointfriamware model 
	Then enter the test data "1" for the following fields to create new firmware for URP     
		 | Endpoint Firmware:Version | Endpoint Firmware:Endpoint Type | Endpoint Firmware:Release Date |
	Then click the "Save Endpoint Firmware" button on the add endpointfirware popup 
	Then click on the portal button to logout from application
	Then login with user "autoSupportTest" in admin ui 
	Then click on the "Firmware" tab of adminui 
	
	#GPSupport Search/View ENDPOINTFIRMWARE_SEARCH Capability
	Then enter firmware name "URPtestEndpointFirmwareBot2" in "endpointfirmware_search" search field 
	Then click on "Endpoint Firmware:Search" button on Endpointfirmware page 
	Then select the endpoint firmware "URPtestEndpointFirmwareBot2" from the "Endpoint Firmware" details grid for URP 
	Then verify the endpoint firmware name "URPtestEndpointFirmwareBot2" on edit endpoint firmware model 
	
@GPSupportPeripheralFirmwareCapability @local
Scenario: Scenario_20: Verify PERIPHERAL FIRMWARE capabilities of GPSupport user 
    Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_20"
	Given we are on "Peripheral Firmware" page and search field is enabled and visible for URP
	Then click on the portal button to logout from application
	When login with user "autoSupportTest" in admin ui 
	Then delete the "Peripheral Firmware" data used as test data for URP page 
		| -3.-3 | 
		
	#GPSupportAddPeripheralFirmwareCapability
	Then click "Add Peripheral Firmware" button on peripheral firmware page 
	Then click on the "Choose File" button on add "Peripheral Firmware" popup and select "Valid" file for URP
	Then enter the test data "1" for the following fields to create new firmware for URP  
		| Peripheral Firmware:Peripheral Type | Peripheral Firmware:Release Date | Peripheral Firmware:Version Major | Peripheral Firmware:Version Minor | Peripheral Firmware:Controller Version Major | Peripheral Firmware:Controller Version Minor | Peripheral Firmware:Controller Version Revision |
	When click on the "Peripheral Firmware:Apply" button on add peripheralfirmware popup 
	
	#GPSupportDeletePeripheralFirmwareCapability
	When enter controller min version "-3.-3" in peripheralfirmware search field 
	Then click on "Peripheral Firmware:Search" button on Peripheralfirmware page 
	Then select the peripheral firmware "-3.-3.-3" from the "Peripheral Firmware" details grid 
	Then verify the details of the selected firmware in following elements on firmware detail popup for URP 
		| Peripheral Firmware:Peripheral Type | Peripheral Firmware:Peripheral Type ID | Peripheral Firmware:Release Date | Peripheral Firmware:Version | Peripheral Firmware:Min Controller Version |
#	Then click on "Delete Peripheral Firmware" button of selected peripheral firmware
		
	#GPUP-31870:Bulk for Firmware Upgrade 
	#GPSupportViewUpdateEndpointFirmwareSubTab
		
@URP_GPSupportUpdateEndpointFirmwareCapability
Scenario: Scenario_21: Login into the application as Role GPSupport user and verify Firmware capabilitys 
	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_21"
	Given we are on "Peripheral Firmware" page and search field is enabled and visible for URP
	Then click on the portal button to logout from application
	When login with user "autoSupportTest" in admin ui 
	Given we are on "Peripheral Firmware" page and search field is enabled and visible for URP
	Then verify the avilable sub tabs of "Firmware" tab for role "URP_GPSupport_testBot" users 
		| Endpoint Firmware | Peripheral Firmware | Update Endpoint Firmware | 
	Then click on the "Update Endpoint Firmware" tab of adminui 
	Then select "FIRST" checkbox on update endpoint sub tab
	Then verify the "Update Firmware" button on "Update Endpoint Firmware" tab for role "GPSupport"
	Then click "Update Firmware" button on update endpoint firmware	
	Then verify "Select Firmware Version" popup is open and below objects are available on this popup
		| PopName:Select Firmware Version | Label:EC1K: | Label:EC100: | EC1K:SelectionBox| EC100:SelectionBox | Button:Cancel | Button:OK| 

@GPSupportPortalCapabilitys 
Scenario: Scenario_22: Verify GSupport user can navigate into the portal and after performing tasks comeback in admin ui 
    Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_22" 
	Given we are on "Site" page and search field is enabled and visible for URP
	Then click on the portal button to logout from application
	When login with user "URP_GPSupport_testBot" in admin ui 
	Then click on the portal button which navigate to HTML Portal 
	Then verify the default available tabs for role "URP_GPSupport_testBot" user 
		| HOME | CONTROLS | ALARMS | PROJECTS | Data |
	Then click site tree "clear" button
	Then click in the "Portal:Site Search" text box in the portal
	Then click the "Control" tab in the portal 
	
	#GPSupportEditHVAC Capability
	Then click the "HVAC" tab in the portal 
	Then enter the "GPEC" site name in the site search box and select it to check "HVAC" capability
	Then click on "Select All:Devices"
	Then verify following buttons are enabled 
		| Override | Edit |
	Then click the "Edit" button and verify is device "HVAC1" model open successfully 
	Then verify "Edit HVAC:Save" button should be disabled and "Edit HVAC:Cancel" button should be enabled 
	Then enter "URP_Test data" in "Schedule" notes text area  
	Then verify following buttons are enabled 
		| Edit HVAC:Save | Edit HVAC:Cancel |
	Then click the "Edit HVAC:Cancel" button of edit hvac 
	
	#GPSupportEditSiteHoursCapability
	Then click the "Hours" tab in the portal 
	Then click site tree "clear" button
	Then enter the "GPEC" site name in the site search box and select it to check "Hours" capability  
	Then verify the "Edit" button on "Regular Hours" tab 
	Then click on "Edit Regular Hours Button" 
	Then click on "Regular Hours Cancel Button" 
	Then click on "Custom Hours Tab" 
	Then click on "Edit Custom Hours Button" 
	Then click on "Custom Hours Cancel Button" 

		
@URP_GPSupportPortalCapabilitys2 
Scenario: Scenario_23: Verify GSupport user can navigate into the portal and after performing tasks comeback in admin ui 
	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_23" 
	Given we are on "Site" page and search field is enabled and visible for URP
	Then click on the portal button to logout from application
	When login with user "URP_GPSupport_testBot" in admin ui 
	Then click on the portal button which navigate to HTML Portal 
	
	#GPSupportVIEW_REPORTS_Capability
	Then click the "Data" tab in the portal 
	Then click the "Reports" tab in the portal 
	Then verify following reports are display 
		| Energy Usage Report | Voltage Imbalance Report | Override Report | Site Schedules Report | Site Schedules History Report | HVAC Test Report |
	
	#GPAdminEditLightCapability
	Then click site tree "clear" button 
	Then click the "Control" tab in the portal 
	Then click the "Lighting/Loads" tab in the portal 
	Then click in the "Portal:Site Search" text box in the portal 
	Then enter the "GPEC" site name in the site search box and select it to check "Lighting" capability 
	Then click on the admin button which navigate to Admin UI 
	
	
####################################### Role Customer SiteManager ##################################################################
# Automation need to do for: CREATE_OVERRIDE, BYPASS_THROTTLING, MSTR_CAPABLE, RUN_HVAC_TESTS capabilities 
		
@URP_CustomerSiteManagerDefaultCapability 
Scenario: Scenario_24: Verify default capabilities of newly creatd Role Customer Site Manager user on Edit User accordion 
	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_24" 
	Given we are on "Site" page and search field is enabled and visible for URP
	Then click on "Users" tab 
	Then enter user name "autoCSM_Test" in search field 
	Then select user "autoCSM_Test" from the "User" details grid 
	Then click "Capabilities" accordion on edit user
	Then verify the default available capilibiltys of role "CUSTOMER SITE MANAGER"
	Then verify default selected capabilities and then select all available capabilities of role "CUSTOMER SITE MANAGER" 
	Then click "Capabilities:Save and Close" button on edit user 
	
		
@URP_CustomerSiteManagerPortalCapability
Scenario: Scenario_25: Verify DEFAULT TABS of Role Customer Site Manager 
    Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_25" 
	Given we are on "Site" page and search field is enabled and visible for URP
	Then click on the portal button to logout from application
	When login with user "URP_CustomerSiteManager_testBot" in portal ui  
	Then verify the default available tabs for role "Customer Site Manager" user 
		| HOME | CONTROLS | ALARMS | PROJECTS | Data |
	Then click site tree "clear" button 
	Then click the "Control" tab in the portal 
	Then click in the "Portal:Site Search" text box in the portal 
		
	#EditSiteHoursCapability 	
	Then enter the "GPEC" site name in the site search box and select it to check "Hours" capability 
	Then verify the "Edit" button on "Regular Hours" tab 
	Then click on "Edit Regular Hours Button" 
	Then click on "Regular Hours Cancel Button" 
	Then click on "Custom Hours Tab" 
	Then click on "Edit Custom Hours Button" 
	Then click on "Custom Hours Cancel Button" 
	
	#EditHVACCapability
	Then click the "HVAC" tab in the portal 
	Then enter the "GPEC" site name in the site search box and select it to check "HVAC" capability
	Then click on "Select All:Devices"
	Then verify following buttons are enabled 
		| Edit |
	Then click the "Edit" button and verify is device "HVAC1" model open successfully 
	Then verify "Edit HVAC:Save" button should be disabled and "Edit HVAC:Cancel" button should be enabled 
	Then enter "URP_Test data" in "Schedule" notes text area 
	Then verify following buttons are enabled 
		| Edit HVAC:Save | Edit HVAC:Cancel |
	Then click the "Edit HVAC:Cancel" button of edit hvac 
	
	#EditLightCapability
	Then click the "Lighting/Loads" tab in the portal 
	Then click site tree "clear" button 
	Then enter the "GPEC" site name in the site search box and select it to check "LIGHTING" capability 
	Then click on "Select All:Devices"
	Then verify following buttons are enabled 
		| Light:Edit |
			
	#VIEW_REPORTS_Capability
	Then click site tree "clear" button 
	Then click the "CROSS ICON" icon of site tree in the portal
	Then click site tree "Select All" button
	Then click the "Data" tab in the portal 
	Then click the "Reports" tab in the portal 
	Then verify following reports are display 
		| Energy Usage Report | Voltage Imbalance Report | Solar Report | Override Report | Site Schedules Report | Site Schedules History Report |
		
	
		
####################################### Role GPAnalyst ##################################################################
#Automation need to do for:	MSTR_CAPABLE, RUN_HVAC_TESTS	
 
@URP_GPAnalystDefaultCapability 
Scenario: Scenario_26: Verify default capabilities of newly creatd Role GPAnalyst user on Edit User accordion 
	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_26" 
	Given we are on "Users" page and search field is enabled and visible for URP
	Then click on "Users" tab 
	Then enter user name "autoAnalystTest" in search field 
	Then click "User:Search" button 
	Then select user "autoAnalystTest" from the "User" details grid 
	Then click "Capabilities" accordion on edit user
	Then verify the default available capilibiltys of role "GPAnalyst" 
	Then verify default selected capabilities and then select all available capabilities of role "GPAnalyst" 
	Then click "Capabilities:Save And Close" button on edit user 
	
	
@URP_GPAnalystPortalCapability 
Scenario: Scenario_27: Verify DEFAULT TABS of Role GP Analyst
    Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_27" 
	Given we are on "Site" page and search field is enabled and visible for URP
	Then click on the portal button to logout from application
	When login with user "URP_GPAnalyst_testBot" in portal ui
	Then verify the default available tabs for role "GPAnalyst" user 
		| HOME | CONTROLS | ALARMS | PROJECTS | Data |
		
	#GPAnalystEditSiteHoursCapability 
	Then click site tree "clear" button 
	Then click the "Control" tab in the portal 
	Then click in the "Portal:Site Search" text box in the portal 
	Then enter the "GPEC" site name in the site search box and select it to check "Hours" capability 
	Then verify the "Edit" button on "Regular Hours" tab 
	Then click on "Edit Regular Hours Button" 
	Then click on "Regular Hours Cancel Button" 
	Then click on "Custom Hours Tab" 
	Then click on "Edit Custom Hours Button" 
	Then click on "Custom Hours Cancel Button" 
	
	#GPAnalystEditSiteHvacSchedulesCapability 
	Then click the "HVAC" tab in the portal 
	#Then enter the "GPEC" site name in the site search box and select it to check "HVAC" capability
	Then click site tree "Select All:Devices" button	
	Then verify following buttons are enabled 
		| HVAC Scope | Override | Edit |
		#TODO: check the step
	Then click the "Edit" button and verify is device "HVAC1" model open successfully 
	Then verify "Edit HVAC:Save" button should be disabled and "Edit HVAC:Cancel" button should be enabled 
	Then enter "URP_Test data" in "Schedule" notes text area 
	Then verify following buttons are enabled 
		| Edit HVAC:Save | Edit HVAC:Cancel |
	Then click the "Edit HVAC:Cancel" button of edit hvac 
	
	#GPAnalystEditSiteLightingLoadsCapability 
	Then click the "Lighting/Loads" tab in the portal 
	#Then enter the "GPEC" site name in the site search box and select it to check "LIGHTING" capability 
	Then click site tree "Select All:Devices" button
	Then verify following buttons are enabled 
		| Light:Override | Light:Edit |
	#TODO : step to be added				
	
####################################### Role Customer Analyst ##################################################################		 
@URP_CustomerAnalystDefaultCapability 
Scenario: Scenario_28: Verify default capabilities of newly creatd Role Customer Analyst user on Edit User accordion 
	Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_28" 
	Given we are on "Users" page and search field is enabled and visible for URP
	Then click on "Users" tab 
	Then enter user name "autoCustomerAnalystTest" in search field 
	Then select user "autoCustomerAnalystTest" from the "User" details grid 
	Then click "Capabilities" accordion on edit user
	Then verify the default available capilibiltys of role "Customer Analyst" 
	Then verify default selected capabilities and then select all available capabilities of role "Customer Analyst" 
	Then click "Capabilities:Save And Close" button on edit user 
	
@URP_CustomerAnalystPortalCapability 
Scenario: Scenario_29: Verify DEFAULT TABS of Role Customer Analyst
    Given User is already logged in to Admin URL and is already present at the Site Tab for "Scenario_29" 
	Given we are on "Site" page and search field is enabled and visible for URP
	Then click on the portal button to logout from application
	When login with user "URP_CustomerAnalyst_testBot" in portal ui
	Then verify the default available tabs for role "URP_CustomerAnalyst_testBot" user 
		| HOME | CONTROLS | ALARMS | PROJECTS | Data |
		
	#CustomerAnalystEditSiteHoursCapability 
	Then click site tree "clear" button 
	Then click the "Control" tab in the portal 
	Then click in the "Portal:Site Search" text box in the portal 
	Then enter the "GPEC" site name in the site search box and select it to check "Hours" capability 
	Then verify the "Edit" button should not be available on "Regular Hours" tab 
	Then click on "Custom Hours Tab" 
	Then verify the "Edit" button should not be available on "Custom Hours Tab" tab 
	
	#CustomerAnalystEditSiteHvacSchedulesCapability
	Then click the "HVAC" tab in the portal 
	Then enter the "GPEC" site name in the site search box and select it to check "Hours" capability 
	Then click site tree "Select All" button
	Then verify following buttons should not be available on "HVAC" tab 
		| HVAC Scope | Override | Hide:Edit |
		
	#CustomerAnalystEditSiteLightingLoadsCapability
	Then click the "Lighting/Loads" tab in the portal 
	Then enter the "GPEC" site name in the site search box and select it to check "Hours" capability
	Then click site tree "Select All" button
	Then verify following buttons should not be available on "HVAC" tab 
		| Hide:Light:Override | Hide:Light:Edit | 
		
		

## TODO : Added by sonu on 11 sep 2018.... curretly commenting it to avoid compilation issues....verify

###################################### Role Partner Commissioner ##################################################################		
#@PartnerCommissionerCapabilityWithoutProvisioningSite
#Scenario: Scenario_91: Verify DEFAULT TABS of Partner Commissioner Role
#    When login with user "URP_GPAdmin_testBot1" in admin ui
#    
#    #Adding Test Users
#    When initialize dummy users in database for different roles and remove dependency with "testTenant_1"  
#		| testUser_1 | 		
#    When initialize dummy users in database for different roles and remove dependency with "testTenant_2"  
#		| testUser_2 | 
#	When initialize dummy users in database for different roles and remove dependency with "testTenant_3"  
#		| testUser_3| 
#    Then create dummy test data and associated with dummy users
#        | testUser_1 | testUser_2 | testUser_3 |
#        
#    #Adding Test Sites
#	Then delete the "Sites" used as urp test data
#		| testSite_1 | testSite_2 | testSite_3 | testSite_4 | testSite_5 | testSite_6 | testSite_7 | testSite_8 | testSite_9 |
#		
#	#Adding Site for testTenant_1
#	Then click "Add Site" button 
#	Then enter valid values for following fields for the site which is "inactive with commissioning Date" for "testTenant_1"
#	     | Tenant Dropdown | Site ID  | Site Description | Address1 | City | State | Postal Code | Country |                
#	Then click "Save" button on add_edit site model 
#	Then click "Accept Verified Data" button on address verification model 
#	Then click "Save" button on add_edit site model 
#	Then user should be navigated on sites page with a label saying "Site testSite_1 saved." 
#	
#	Then click "Add Site" button
#	Then select the following check boxes for Site Page for Partner Commissioner 
#	   | Active |  
#	Then enter valid values for following fields for the site which is "active with No commissioning Date" for "testTenant_1"
#	     | Tenant Dropdown | Site ID  | Site Description | Address1 | City | State | Postal Code | Country |                
#	Then click "Save" button on add_edit site model 
#	Then click "Accept Verified Data" button on address verification model 
#	Then click "Save" button on add_edit site model 
#	Then user should be navigated on sites page with a label saying "Site testSite_2 saved." 
#	
#	Then click "Add Site" button 
#	Then enter valid values for following fields for the site which is "inactive with No commissioning Date" for "testTenant_1"
#	     | Tenant Dropdown | Site ID  | Site Description | Address1 | City | State | Postal Code | Country |                
#	Then click "Save" button on add_edit site model 
#	Then click "Accept Verified Data" button on address verification model 
#	Then click "Save" button on add_edit site model 
#	Then user should be navigated on sites page with a label saying "Site testSite_3 saved."
#	
#	#Adding Site for testTenant_2
#	Then click "Add Site" button 
#	Then enter valid values for following fields for the site which is "inactive with commissioning Date" for "testTenant_2"
#	     | Tenant Dropdown | Site ID  | Site Description | Address1 | City | State | Postal Code | Country |                
#	Then click "Save" button on add_edit site model 
#	Then click "Accept Verified Data" button on address verification model 
#	Then click "Save" button on add_edit site model 
#	Then user should be navigated on sites page with a label saying "Site testSite_4 saved." 
#	
#	Then click "Add Site" button 
#	Then select the following check boxes for Site Page for Partner Commissioner 
#	   | Active | 
#	Then enter valid values for following fields for the site which is "active with No commissioning Date" for "testTenant_2"
#	     | Tenant Dropdown | Site ID  | Site Description | Address1 | City | State | Postal Code | Country |                
#	Then click "Save" button on add_edit site model 
#	Then click "Accept Verified Data" button on address verification model 
#	Then click "Save" button on add_edit site model 
#	Then user should be navigated on sites page with a label saying "Site testSite_5 saved." 
#	
#	Then click "Add Site" button 
#	Then enter valid values for following fields for the site which is "inactive with No commissioning Date" for "testTenant_2"
#	     | Tenant Dropdown | Site ID  | Site Description | Address1 | City | State | Postal Code | Country |                
#	Then click "Save" button on add_edit site model 
#	Then click "Accept Verified Data" button on address verification model 
#	Then click "Save" button on add_edit site model 
#	Then user should be navigated on sites page with a label saying "Site testSite_6 saved."
#	
#	#Adding Site for testTenant_3
#	Then click "Add Site" button 
#	Then enter valid values for following fields for the site which is "inactive with commissioning Date" for "testTenant_3"
#	     | Tenant Dropdown | Site ID  | Site Description | Address1 | City | State | Postal Code | Country |                
#	Then click "Save" button on add_edit site model 
#	Then click "Accept Verified Data" button on address verification model 
#	Then click "Save" button on add_edit site model 
#	Then user should be navigated on sites page with a label saying "Site testSite_7 saved." 
#	
#	Then click "Add Site" button
#	Then select the following check boxes for Site Page for Partner Commissioner 
#	   | Active | 
#	Then enter valid values for following fields for the site which is "active with No commissioning Date" for "testTenant_3"
#	     | Tenant Dropdown | Site ID  | Site Description | Address1 | City | State | Postal Code | Country |                
#	Then click "Save" button on add_edit site model 
#	Then click "Accept Verified Data" button on address verification model 
#	Then click "Save" button on add_edit site model 
#	Then user should be navigated on sites page with a label saying "Site testSite_8 saved." 
#	
#	Then click "Add Site" button 
#	Then enter valid values for following fields for the site which is "inactive with No commissioning Date" for "testTenant_3"
#	     | Tenant Dropdown | Site ID  | Site Description | Address1 | City | State | Postal Code | Country |                
#	Then click "Save" button on add_edit site model 
#	Then click "Accept Verified Data" button on address verification model 
#	Then click "Save" button on add_edit site model 
#	Then user should be navigated on sites page with a label saying "Site testSite_9 saved."    
#     
#    #Adding Test Endpoints   
#    Then click on "Endpoints" tab
#    Then click "Add Endpoint" button on endpoint page 
#	Then enter the "testEndpoint_1" in "Serial" field on add endpoint model 
#	Then select "EC1000" from type selection box on "add" enpoint popup
#	Then click the "Save" button on the add endpoint popup 
#	
#	Then click "Add Endpoint" button on endpoint page 
#	Then enter the "testEndpoint_2" in "Serial" field on add endpoint model 
#	Then select "EC1000" from type selection box on "add" enpoint popup 
#	Then click the "Save" button on the add endpoint popup 
#	
#	Then click "Add Endpoint" button on endpoint page 
#	Then enter the "testEndpoint_3" in "Serial" field on add endpoint model 
#	Then select "EC1000" from type selection box on "add" enpoint popup 
#	Then click the "Save" button on the add endpoint popup
#	
#	Then click "Add Endpoint" button on endpoint page 
#	Then enter the "testEndpoint_4" in "Serial" field on add endpoint model 
#	Then select "EC1000" from type selection box on "add" enpoint popup 
#	Then click the "Save" button on the add endpoint popup
#	
#	Then click "Add Endpoint" button on endpoint page 
#	Then enter the "testEndpoint_5" in "Serial" field on add endpoint model 
#	Then select "EC1000" from type selection box on "add" enpoint popup 
#	Then click the "Save" button on the add endpoint popup
#	
#	Then click "Add Endpoint" button on endpoint page 
#	Then enter the "testEndpoint_6" in "Serial" field on add endpoint model 
#	Then select "EC1000" from type selection box on "add" enpoint popup 
#	Then click the "Save" button on the add endpoint popup
#	
#	Then click "Add Endpoint" button on endpoint page 
#	Then enter the "testEndpoint_7" in "Serial" field on add endpoint model 
#	Then select "EC1000" from type selection box on "add" enpoint popup 
#	Then click the "Save" button on the add endpoint popup
#	
#	Then click "Add Endpoint" button on endpoint page 
#	Then enter the "testEndpoint_8" in "Serial" field on add endpoint model 
#	Then select "EC1000" from type selection box on "add" enpoint popup 
#	Then click the "Save" button on the add endpoint popup
#	
#	Then click "Add Endpoint" button on endpoint page 
#	Then enter the "testEndpoint_9" in "Serial" field on add endpoint model 
#	Then select "EC1000" from type selection box on "add" enpoint popup 
#	Then click the "Save" button on the add endpoint popup
#	
#	
#	#Adding Test Tenants
#    Then click on "Tenants" tab
#    Then click "Add Tenant" button on tenant tab
#	Then enter the "testTenant_1" in "Name" textbox on add tenant model 
#	Then click "Save" button on add tenant popup
#	Then click "Add Tenant" button on tenant tab
#	Then enter the "testTenant_2" in "Name" textbox on add tenant model 
#	Then click "Save" button on add tenant popup
#	Then click "Add Tenant" button on tenant tab
#	Then enter the "testTenant_3" in "Name" textbox on add tenant model 
#	Then click "Save" button on add tenant popup   
#	
#	#Associating testTenant_1 with user testUser_1
#	Then click on "USERS" tab 
#	Then enter user name "testUser_1" in search field 
#	Then select user "testUser_1" from the user details grid 
#	Then click "Permission" accordion on edit user
#	Then select tenant "testTenant_1" from tenant combo Box 
#	Then click on "testTenant_1" checkbox in Available Sites area    
#	When click on "ADD" button on add user popup 
#	Then click "Permission:Save and Close" button on edit user
#	
#	
#	#Associating testTenant_2 with user testUser_2
#	Then enter user name "testUser_2" in search field 
#	Then select user "testUser_2" from the user details grid 
#	Then click "Permission" accordion on edit user
#	Then select tenant "testTenant_2" from tenant combo Box 
#	Then click on "testTenant_2" checkbox in Available Sites area    
#	When click on "ADD" button on add user popup 
#	Then click "Permission:Save and Close" button on edit user
#	
#	#Associating testTenant_3 with user testUser_3
#	Then enter user name "testUser_3" in search field 
#	Then select user "testUser_3" from the user details grid 
#	Then click "Permission" accordion on edit user
#	Then select tenant "testTenant_3" from tenant combo Box 
#	Then click on "testTenant_3" checkbox in Available Sites area    
#	When click on "ADD" button on add user popup 
#	Then click "Permission:Save and Close" button on edit user
#
#    #Association of testEndpoint_1 with testSite_1
#    Then click on "Endpoints" tab
#    When enter the endpoint name "testEndpoint_1" in the search field  
#	Then click "Search" button on endpoint page
#	Then select the entered endpoint id "testEndpoint_1" from the endpoint details grid page 
#	Then user modify following fields with site "testSite_1"      
#	     | ASSOCIATION TENANT/SITE |
#	Then click on "Save and Close" button on endpoint page
#	
#	#Association of testEndpoint_2 with testSite_2
#	When enter the endpoint name "testEndpoint_2" in the search field  
#	Then click "Search" button on endpoint page
#	Then select the entered endpoint id "testEndpoint_2" from the endpoint details grid page 
#	Then user modify following fields with site "testSite_2"  
#	     | ASSOCIATION TENANT/SITE |
#	Then click on "Save and Close" button on endpoint page
#	
#	#Association of testEndpoint_3 with testSite_3
#	When enter the endpoint name "testEndpoint_3" in the search field  
#	Then click "Search" button on endpoint page
#	Then select the entered endpoint id "testEndpoint_3" from the endpoint details grid page 
#	Then user modify following fields with site "testSite_3"  
#	     | ASSOCIATION TENANT/SITE |
#	Then click on "Save and Close" button on endpoint page   
#	
#	#Association of testEndpoint_4 with testSite_4
#	When enter the endpoint name "testEndpoint_4" in the search field  
#	Then click "Search" button on endpoint page
#	Then select the entered endpoint id "testEndpoint_4" from the endpoint details grid page 
#	Then user modify following fields with site "testSite_4"  
#	     | ASSOCIATION TENANT/SITE |
#	Then click on "Save and Close" button on endpoint page   
#	
#	#Association of testEndpoint_5 with testSite_5
#	When enter the endpoint name "testEndpoint_5" in the search field  
#	Then click "Search" button on endpoint page
#	Then select the entered endpoint id "testEndpoint_5" from the endpoint details grid page 
#	Then user modify following fields with site "testSite_5"  
#	     | ASSOCIATION TENANT/SITE |
#	Then click on "Save and Close" button on endpoint page   
#	
#	#Association of testEndpoint_6 with testSite_6
#	When enter the endpoint name "testEndpoint_6" in the search field  
#	Then click "Search" button on endpoint page
#	Then select the entered endpoint id "testEndpoint_6" from the endpoint details grid page 
#	Then user modify following fields with site "testSite_6"  
#	     | ASSOCIATION TENANT/SITE |
#	Then click on "Save and Close" button on endpoint page   
#	
#	#Association of testEndpoint_7 with testSite_7
#	When enter the endpoint name "testEndpoint_7" in the search field  
#	Then click "Search" button on endpoint page
#	Then select the entered endpoint id "testEndpoint_7" from the endpoint details grid page 
#	Then user modify following fields with site "testSite_7"  
#	     | ASSOCIATION TENANT/SITE |
#	Then click on "Save and Close" button on endpoint page   
#	
#	#Association of testEndpoint_8 with testSite_8
#	When enter the endpoint name "testEndpoint_8" in the search field  
#	Then click "Search" button on endpoint page
#	Then select the entered endpoint id "testEndpoint_8" from the endpoint details grid page 
#	Then user modify following fields with site "testSite_8"  
#	     | ASSOCIATION TENANT/SITE |
#	Then click on "Save and Close" button on endpoint page   
#	
#	#Association of testEndpoint_9 with testSite_9
#	When enter the endpoint name "testEndpoint_9" in the search field 
#	Then click "Search" button on endpoint page
#	Then select the entered endpoint id "testEndpoint_9" from the endpoint details grid page 
#	Then user modify following fields with site "testSite_9"  
#	     | ASSOCIATION TENANT/SITE |
#	Then click on "Save and Close" button on endpoint page   
#	
#	##Testing Started for testUser_1
#	##Testing of Default Tabs for Partner Commissioner Role
#	Then click on the portal button to logout from application
#	Then login with user "testUser_1" in admin ui
#	Then verify the default avilable tabs for role "Partner Commissioner" user 
#		| Commissioning | Firmware |
#		
#	#Testing Endpoint Search with testUser_1
#	 When enter the endpoint name "testEndpoint_" in the search field 
#	 And verify following endpoints should be shown in the list 
#	     | testEndpoint_3 |
#	     
#	#Testing Site Search with testUser_1
#	 When enter the site name "testSite_" into search field 
#	 And verify following Sites should be shown in the list  
#	     | testSite_3 |
#	     
#	##Testing Started for testUser_2
#	##Testing of Default Tabs for Partner Commissioner Role
#	Then click on the portal button to logout from application
#	Then login with user "testUser_2" in admin ui
#	Then verify the default avilable tabs for role "Partner Commissioner" user 
#		| Commissioning | Firmware |
#		
#	#Testing Endpoint Search with testUser_2
#	 When enter the endpoint name "testEndpoint_" in the search field 
#	 And verify following endpoints should be shown in the list 
#	     | testEndpoint_6 |
#	     
#	#Testing Site Search with testUser_2
#	 When enter the site name "testSite_" into search field 
#	 And verify following Sites should be shown in the list  
#	     | testSite_6 |      
#	     
#	 ##Testing Started for testUser_3
#	##Testing of Default Tabs for Partner Commissioner Role
#	Then click on the portal button to logout from application
#	Then login with user "testUser_3" in admin ui
#	Then verify the default avilable tabs for role "Partner Commissioner" user 
#		| Commissioning | Firmware |
#		
#	#Testing Endpoint Search with testUser_3
#	 When enter the endpoint name "testEndpoint_" in the search field 
#	 And verify following endpoints should be shown in the list 
#	     | testEndpoint_9 |
#	     
#	#Testing Site Search with testUser_3
#	 When enter the site name "testSite_" into search field 
#	 And verify following Sites should be shown in the list  
#	     | testSite_9 |          
#	     
#
#@PartnerCommissionerCapabilityWithProvisioningSite
#Scenario: Scenario_92: Verify DEFAULT TABS of Partner Commissioner Role
#    When login with user "URP_GPAdmin_testBot1" in admin ui
#    
#    #Adding Test Users
#    When initialize dummy users in database for different roles and remove dependency with "testTenant_4"  
#		| testUser_4 | 		
#    Then create dummy test data and associated with dummy users
#        | testUser_4 |
#        
#    #Adding Test Sites
#	Then delete the "Sites" used as urp test data
#		| testSite_10 | testSite_11 | testSite_12 |
#		
#	#Adding Test Tenants
#    Then click on "Tenants" tab
#    Then click "Add Tenant" button on tenant tab
#	Then enter the "testTenant_4" in "Name" textbox on add tenant model 
#	Then click "Save" button on add tenant popup
#		
#	#Adding Site for testTenant_1
#	Then click "Add Site" button 
#	Then enter valid values for following fields for the site which is "inactive with commissioning Date" for "testTenant_4"
#	     | Tenant Dropdown | Site ID  | Site Description | Address1 | Address2 | City | State | Postal Code | Country |                
#	Then click "Save" button on add_edit site model 
#	Then click "Accept Verified Data" button on address verification model 
#	Then click "Save" button on add_edit site model 
#	Then user should be navigated on sites page with a label saying "Site testSite_10 saved." 
#	
#	Then click "Add Site" button
#	Then select the following check boxes for Site Page for Partner Commissioner 
#	   | Active |  
#	Then enter valid values for following fields for the site which is "active with No commissioning Date" for "testTenant_4"
#	     | Tenant Dropdown | Site ID  | Site Description | Address1 | Address2 | City | State | Postal Code | Country |                
#	Then click "Save" button on add_edit site model 
#	Then click "Accept Verified Data" button on address verification model 
#	Then click "Save" button on add_edit site model 
#	Then user should be navigated on sites page with a label saying "Site testSite_11 saved." 
#	
#	Then click "Add Site" button 
#	Then enter valid values for following fields for the site which is "inactive with No commissioning Date" for "testTenant_4"
#	     | Tenant Dropdown | Site ID  | Site Description | Address1 | Address2 | City | State | Postal Code | Country |                
#	Then click "Save" button on add_edit site model 
#	Then click "Accept Verified Data" button on address verification model 
#	Then click "Save" button on add_edit site model 
#	Then user should be navigated on sites page with a label saying "Site testSite_12 saved."
#	
#     
#    #Adding Test Endpoints   
#    Then click on "Endpoints" tab
#    Then click "Add Endpoint" button on endpoint page 
#	Then enter the "testEndpoint_10" in "Serial" field on add endpoint model 
#	Then select "EC1000" from type selection box on "add" enpoint popup
#	Then click the "Save" button on the add endpoint popup 
#	
#    Then click "Add Endpoint" button on endpoint page 
#	Then enter the "testEndpoint_11" in "Serial" field on add endpoint model 
#	Then select "EC1000" from type selection box on "add" enpoint popup
#	Then click the "Save" button on the add endpoint popup 
#	
#    Then click "Add Endpoint" button on endpoint page 
#	Then enter the "testEndpoint_12" in "Serial" field on add endpoint model 
#	Then select "EC1000" from type selection box on "add" enpoint popup
#	Then click the "Save" button on the add endpoint popup 
#	
#	#Associating testTenant_1 with user testUser_1
#	Then click on "USERS" tab 
#	Then enter user name "testUser_4" in search field 
#	Then select user "testUser_4" from the user details grid 
#	Then click "Permission" accordion on edit user
#	Then select tenant "testTenant_4" from tenant combo Box 
#	Then click on "testTenant_4" checkbox in Available Sites area on Permission Accordion    
#	When click on "ADD" button on add user popup on Permission Accordion  
#	Then click "Permission:Save and Close" button on edit user
#	
#	##Testing Started
#	##Testing of Default Tabs for Partner Commissioner Role
#	Then click on the portal button to logout from application
#	Then login with user "testUser_4" in admin ui
#	Then verify the default avilable tabs for role "Partner Commissioner" user 
#		| Commissioning | Firmware |
#		
#	#Testing Endpoint Search with testUser_4
#	 When enter the endpoint name "testEndpoint_" in the search field 
#	 And verify following endpoints should be shown in the list 
#	     | testEndpoint_10 | testEndpoint_11 | testEndpoint_12 | 
#	 Then select the entered endpoint id "testEndpoint_12" from the endpoint details grid page which is associated with Provisioning Site     
#	 Then verify a pop up for Site Assosiation is displayed with message "You need to associate the endpoint to a site to continue<"    
#	 Then user modify following fields with site "testSite_12"  
#	     | ASSOCIATION TENANT/SITE |
#	 Then click on "OK" button on Site Association Model          
#     Then a block should appear saying "Endpoint is succussfully associated to a site"      