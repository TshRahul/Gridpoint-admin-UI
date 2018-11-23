@AdminUI_LoginPage 
Feature: Login Page Test 
	In order to test functionality of Login page
	We will test different combination of credentials to test the login functionality
	
Background: Check for page refresh requirement for each test scenario 
	And check if the refresh required for the portlet test scenario
	
@InitializeBrowserForAddTestData 
Scenario: 
	Scenario_initialize: Initialize the browser before execution of the test cases of Add Test Data 
	Given Initialize resources in the beginning of each scenario
	
@AdminUI_DeleteAndCreateTestData 
Scenario: 
	Scenario_01 : It Delete and create test data to test the Commissioning tool 
	Then "delete" the user used as test data 
		| autoAdminTest | autoSupportTest | autoAnalystTest | autoCSM_DisabledTest | autoCustomerAnalystTest | autoCSM_Test |
	Given we are on login page and we use valid username and password for login 
		| username	|  password   |
		| gpadmin		| Reston2018  |
	Then "add" the user used as test data 
		| autoAdminTest | autoSupportTest | autoAnalystTest | autoCSM_DisabledTest | autoCustomerAnalystTest | autoCSM_Test |
	Then click on the logout button and verify the login page is displayed 
	Then validate that all the test cases of scenario for portlet executed successfully 
	
@AdminUI_LoginPageLoad 
Scenario: Scenario_02 Wait for login page to Load Successfully 
	Given user is on login page to perform "Scenario_02" 
	Then verify the availability of username, password field and login button on login page 
	Then validate that all the test cases of scenario for portlet executed successfully 
	
	#GPUP-30535
@AdminUI_LoginPageBadCredential 
Scenario: 
	Scenario_03 Verify error message with invalid/unauthorized/random credentials 
	Given user is on login page to perform "Scenario_03" 
	Then verify the error message with username and password below 
		| 		username 				|  password  |      message		|
		| autoCSM_DisabledTest	 		| P@ssword1! | Your account has been disabled. If you believe this to be an error, please contact GridPoint Support (support@gridpoint.com) to have your account re-enabled. To keep your account active, please make sure to log in to GridPoint Energy Manager at least once every 60 days. Accounts that have not logged in in 6 months will be permanently deleted. |
		| 		 RANDOM 				|  RANDOM    | Bad credentials. Please note: Accounts that are inactive for longer than 6 months are routinely deleted. If a new username is needed, please contact GridPoint Support (support@gridpoint.com). |
		| autoSupportTest 				|  RANDOM    | Bad Credentials	|
		|		 RANDOM 				|  password  | Bad credentials. Please note: Accounts that are inactive for longer than 6 months are routinely deleted. If a new username is needed, please contact GridPoint Support (support@gridpoint.com). |
		| autoSupportTest				|  password  | Bad Credentials	|
		
	Then validate that all the test cases of scenario for portlet executed successfully 
	
@AdminUI_LoginPageNonAdminUser 
Scenario: 
	Scenario_04 Verify the login to portal with credentials having non-admin role 
	Given user is on login page to perform "Scenario_04" 
	Given we are on login page and we use valid username and password for login 
		| username 						|  password |
		| autoAnalystTest			 	| P@ssword1!|
	Then verify the successful login by checking the availability of home tab on portal home page 
	Then click on the logout button and verify the login page is displayed 
	Then validate that all the test cases of scenario for portlet executed successfully 
	
@AdminUI_LoginPageSuccessfulLogin 
Scenario: Scenario_05 Verify the successful login with valid credentials 
	Given user is on login page to perform "Scenario_05" 
	Given we are on login page and we use valid username and password for login 
		| username 					| password  	|
		| autoSupportTest 			| P@ssword1!	|
	Then verify the successful login by checking the availability of logout button on site page 
	Then click on the logout button and verify the login page is displayed 
	Then validate that all the test cases of scenario for portlet executed successfully 
	
	
@AdminUI_DeleteTestData 
Scenario: Scenario_06 Delete all test data use in Login.feature 
	Given user is on login page to perform "Scenario_06" 
	Then verify the availability of username, password field and login button on login page 
	Then "delete" the user used as test data 
		| autoAnalystTest | autoCSM_DisabledTest |
	Given we are on login page and we use valid username and password for login 
		| username 					| password  	|
		| autoAdminTest 	 	| P@ssword1!	|
	Then verify the successful login by checking the availability of logout button on site page 
	Then validate that all the test cases of scenario for portlet executed successfully 
	
@CloseBrowser 
Scenario: 
	Scenario_quit: Close the browser after finishing execution of the Authentication portlet test cases 
	Then close the browser 