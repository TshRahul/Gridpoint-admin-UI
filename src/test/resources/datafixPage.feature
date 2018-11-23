@DatafixPage 
Feature: Datafix Page Test 
	In order to test functionality of Datafix page
	We will test different combination of credentials to test the Datafix functionality
	
Background: Initialize resources for test script 
	Given Initialize resources in the beginning of each scenario for Datafix Page 
	
@DatafixLogin 
Scenario: Scenario_01: initialize dummy data for datafix page 
	Given we launch the browser with admin-UI URL and login with valid admin credential  
	Then initialize dummydata for "DataFix" in database
	
@DatafixPermissionandUI 
Scenario: Scenario_02: Login with admin credentials and verify Datafix tab is avilable only for GP Admin 
	Given we launch the browser with admin-UI URL and login with valid admin credential 
	When we will land on Site page and we will click on Datafix tab 
	Then verify the visibility of following columns in datafix details grid 
		| Datafix Uuid | Datafix:Notes | Username | EndUserId |
	Then verify the sorting functionality of columns in datafix details grid 
		| Datafix:Notes | Username | EndUserId | 
	Then verify the datepicker functionality of "datafix" tab 
		| startDate  |
		| 09/01/2017 |
	Then verify the following button should be availabe on datepicker popup for "datafix" on datafix page 
		|datafix:apply|datafix:cancel|
	Then verify records displaying in "DataFix" on datafix page 
	Then click on logout on datafix page 
	
@DatafixMultiplyChannelData 
Scenario: Scenario_03: create datafix for multiply channel data on datafix page 
	Given we launch the browser with admin-UI URL and login with valid admin credential 
	When we will land on Site page and we will click on Datafix tab  
	When click on "create datafix" button on Datafix page 
	Then following field of "Site Details" should be displayed on create datafix page 
		|Site ID |Site Name|Address|Postal Code|Time Zone|Tanent|
	Then insert "aUITestSite" in search text box and select site from create datafix 
	Then click on "Site Details" accordion on create datafix 
	When select "multiply_channel_data" from task dropdown on create datafix page 
	When click on "save" button on datafix page 
	Then valid error message should be displayed for "empty fields" scenario on create datafix page 
		| Notes | ChannelID | Multiplicand |
	Then insert "PI-" in ticket number field on create datafix page 
	Then insert "Bot multiply datafix channel" in notes field on create datafix page 
	Then Verify the ChannelId input fields are ready only on create datafix page 
		|ChannelId|
	Then select on source channel redio on create datafix page 
	Then insert in to "multiplicand" field on create datafix page 
	When click on "Channels" accordion on create datafix 
	Then following field of channel accordion should be displayed on create datafix page 
		|Channel ID|Channel Name|Display Name|Device Name|Measure Type|Unit|Metadata Category|Total Type|Disabled from Graphs|
	Then insert "-400201" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	Then click on "select" button in channel accordion on create datafix page 
	Then verify channel should not be empty for "multiply_channel_data" on create datafix page 
	When click on "Channels" accordion on create datafix 
	Then insert dates in datepicker for "datafix" on create datafix page 
		| startDate  | endDate    |	
		| 05/01/2018 | 05/03/2018 |  
	When click on "save" button on datafix page 
	Then following field of priview model should be displayed on create datafix page 
		| Preview Model Lable | Preview Notes | Preview Ticket Number | Preview Task | Preview StartUTC | Preview EndUTC | Preview ChannelID |
	When user click on "Preview:Save" button on datafix page for channel id "-400201"
	Then user should be navigated to create Datafix page with a label saying "DataFix created successfully." 
	Then verify "multiply channel" task is performed for channel id "-400201" 
	
@DatafixMultipleMultiChannelData 
Scenario: Scenario_04: create datafix for multiply more then one channel data on datafix page 
	Given we launch the browser with admin-UI URL and login with valid admin credential 
	When we will land on Site page and we will click on Datafix tab 
	When click on "create datafix" button on Datafix page 
	Then insert "aUITestSite" in search text box and select site from create datafix 
	Then click on "Site Details" accordion on create datafix 
	When select "multiply_channel_data" from task dropdown on create datafix page 
	Then insert "PI-" in ticket number field on create datafix page 
	Then insert "Bot multiply more then one channel data" in notes field on create datafix page 
	Then click "+Add" button on create datafix page 
	When select channel1 radio button on create datafix page 
	When click on "Channels" accordion on create datafix 
	Then insert "-400201" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	Then click on "select" button in channel accordion on create datafix page 
	When select channel2 radio button on create datafix page 
	Then insert "-400223" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	Then click on "select" button in channel accordion on create datafix page 
	Then insert in to multi "multiplicand" field on create datafix page 
	Then verify channel should not be empty for "multiply_channel_data" on create datafix page 
	When click on "Channels" accordion on create datafix 
	Then insert dates in datepicker for "datafix" on create datafix page 
		| startDate  | endDate    |	
		| 04/13/2018 | 04/13/2018 | 
	When click on "save" button on datafix page 
	When user click on "Preview:Save" button on datafix page for channel "-400201" and "-400223"
	Then user should be navigated to create Datafix page with a label saying "DataFix created successfully." 
	Then verify "Multiple multiply channel" task is performed for channel "-400201" and "-400223" 
		
@DatafixDivideChannelData 
Scenario: Scenario_05: create datafix for divide chanel data on datafix page 
	Given we launch the browser with admin-UI URL and login with valid admin credential 
	When we will land on Site page and we will click on Datafix tab 
	When click on "create datafix" button on Datafix page 
	Then insert "aUITestSite" in search text box and select site from create datafix 
	Then click on "Site Details" accordion on create datafix 
	#Then insert "p-50001" in search text box of Site details accordion and click on "searchSite" button on create datafix 
	#Then click on channel button in site details accordion on create datafix page 
	When select "divide_channel_data" from task dropdown on create datafix page 
	When click on "save" button on datafix page 
	Then valid error message should be displayed for "empty fields" scenario on create datafix page 
		|Notes|ChannelID|Divisor|
	Then insert "PI-" in ticket number field on create datafix page 
	Then insert "Bot divide datafix channel" in notes field on create datafix page 
	Then Verify the ChannelId input fields are ready only on create datafix page 
		|ChannelId|
	When select on source channel redio on create datafix page 
	Then insert in "Divisor" field on create datafix page 
	When click on "Channels" accordion on create datafix 
	Then insert "-400201" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	Then click on "select" button in channel accordion on create datafix page 
	Then verify channel should not be empty for "divide_channel_data" on create datafix page 
	When click on "Channels" accordion on create datafix 
	Then insert dates in datepicker for "datafix" on create datafix page 
		| startDate  | endDate    |	
		| 04/02/2018 | 04/02/2018 |
	When click on "save" button on datafix page 
	When user click on "Preview:Save" button on datafix page for channel id "-400201" 
	Then user should be navigated to create Datafix page with a label saying "DataFix created successfully." 
	Then verify "divide channel" task is performed for channel id "-400201" 
	
@DatafixDivideMultipleChannelData 
Scenario: Scenario_06: create datafix for divide more then one chanel data on datafix page 
	Given we launch the browser with admin-UI URL and login with valid admin credential 
	When we will land on Site page and we will click on Datafix tab 
	When click on "create datafix" button on Datafix page 
	Then insert "aUITestSite" in search text box and select site from create datafix 
	Then click on "Site Details" accordion on create datafix 
	#	Then insert "p-50001" in search text box of Site details accordion and click on "searchSite" button on create datafix 
	#	Then click on channel button in site details accordion on create datafix page 
	When select "divide_channel_data" from task dropdown on create datafix page 
	Then insert "PI-" in ticket number field on create datafix page 
	Then insert "Bot divide more then one channel data" in notes field on create datafix page 
	Then click "+Add" button on create datafix page 
	When select channel1 radio button on create datafix page 
	When click on "Channels" accordion on create datafix 
	Then insert "-400201" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	Then click on "select" button in channel accordion on create datafix page 
	When select channel2 radio button on create datafix page 
	Then insert "-400223" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	Then click on "select" button in channel accordion on create datafix page 
	Then insert in to "Divisor" fields on create datafix page 
	Then verify channel should not be empty for "divide_channel_data" on create datafix page 
	When click on "Channels" accordion on create datafix
	Then insert dates in datepicker for "datafix" on create datafix page 
		| startDate  | endDate    |	
		| 04/13/2018 | 04/13/2018 | 
	When click on "save" button on datafix page 
	When user click on "Preview:Save" button on datafix page for channel "-400201" and "-400223"
	Then user should be navigated to create Datafix page with a label saying "DataFix created successfully."
	Then verify "divide channel" task is performed for channel "-400201" and "-400223" 
	
	# TODO ; added by k.Currently commenting..last 2 steps are later removed by k..verify
#	All the above and below numeric values are updated and 9 is appended in the beginning
#   #Then verify "divide channel" task is performed for channel "-9400201" and "-9400223" 
#	Then verify "divide channel" task is performed for channel "-9400201"
#	Then verify "divide channel" task is performed for channel "-9400223" 
	
@DatafixSwapChannelData 
Scenario: Scenario_07: verify the create datafix for swap data channel functionality on Datafix page 
	Given we launch the browser with admin-UI URL and login with valid admin credential 
	When we will land on Site page and we will click on Datafix tab 
	When click on "create datafix" button on Datafix page 
	Then insert "aUITestSite" in search text box and select site from create datafix
	Then click on "Site Details" accordion on create datafix  
	#	Then insert "p-50001" in search text box of Site details accordion and click on "searchSite" button on create datafix 
	#	Then click on channel button in site details accordion on create datafix page 
	When select "swap_channel_data" from task dropdown on create datafix page 
	When click on "save" button on datafix page 
	Then valid error message should be displayed for "empty fields" scenario on create datafix page 
		| Notes | ChannelId1 | ChannelId2 |
	Then insert "PI-" in ticket number field on create datafix page 
	Then insert "Bot swap channel data" in notes field on create datafix page 
	Then Verify the ChannelId input fields are ready only on create datafix page 
		| ChannelID1 | ChannelID2 |
	Then click on the "ChannelID1" radio on create datafix page 
	When click on "Channels" accordion on create datafix 
	Then insert "-400205" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	Then click on "select" button in channel accordion on create datafix page 
	Then click on the "ChannelID2" radio on create datafix page 
	Then insert "-400206" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	Then click on "select" button in channel accordion on create datafix page
	When click on "Channels" accordion on create datafix  
	Then insert dates in datepicker for "datafix" on create datafix page 
		| startDate  | endDate    |	
		| 04/13/2018 | 04/13/2018 |   
	When click on "save" button on datafix page 
	When user click on "Preview:Save" button on datafix page for channel "-400205" and "-400206" 
	Then user should be navigated to create Datafix page with a label saying "DataFix created successfully." 
	Then verify "Swap Channel" task is performed for channel "-400205" and "-400206" 
	
	# TODO ; added by k.Currently commenting.. last 2 steps are later removed by k..verify
#	#Then verify "Swap Channel" task is performed for channel "-9400205" and "-9400206" 
#	Then verify "Swap Channel" task is performed for channel "-9400205"
#	Then verify "Swap Channel" task is performed for channel "-9400206" 
	
@DatafixDeleteNegativeChannelData 
Scenario: Scenario_08: create datafix for delete negative channel data on datafix page 
	Given we launch the browser with admin-UI URL and login with valid admin credential 
	When we will land on Site page and we will click on Datafix tab 
	When click on "create datafix" button on Datafix page 
	Then insert "aUITestSite" in search text box and select site from create datafix 
	Then click on "Site Details" accordion on create datafix 
	#   Then insert "p-50001" in search text box of Site details accordion and click on "searchSite" button on create datafix 
	#   Then click on channel button in site details accordion on create datafix page 
	When select "delete_negative_channel_data" from task dropdown on create datafix page 
	When click on "save" button on datafix page 
	Then valid error message should be displayed for "empty fields" scenario on create datafix page 
		|Notes|ChannelID|
	Then insert "PI-" in ticket number field on create datafix page 
	Then insert "Bot negative channel delete" in notes field on create datafix page 
	Then Verify the ChannelId input fields are ready only on create datafix page 
		|ChannelId|
	When select on source channel redio on create datafix page 
	When click on "Channels" accordion on create datafix 
	Then insert "-400207" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	Then click on "select" button in channel accordion on create datafix page 
	Then verify channel should not be empty for "delete negative channel" on create datafix page 
	When click on "Channels" accordion on create datafix 
	Then insert dates in datepicker for "datafix" on create datafix page
		| startDate  | endDate    | 
		| 04/13/2018 | 04/13/2018 |  
	Then click on "save" button on datafix page 
	When user click on "Preview:Save" button on datafix page for channel id "-400207" 
	Then verify "delete negative channel" task is performed for channel id "-400207" 
	Then click on "DataFix:close" button on datafix page 
	
@DatafixCopyChannelWithDifferentCalcType 
Scenario: Scenario_09: verify the create datafix for copy data channel with different Calc Type functionality on Datafix page 
	Given we launch the browser with admin-UI URL and login with valid admin credential 
	When we will land on Site page and we will click on Datafix tab 
	When click on "create datafix" button on Datafix page 
	Then insert "aUITestSite" in search text box and select site from create datafix 
	Then click on "Site Details" accordion on create datafix 
	When select "copy_channel_data" from task dropdown on create datafix page 
	When click on "save" button on datafix page 
	Then valid error message should be displayed for "empty fields" scenario on create datafix page 
		| Notes | source ChannelID | destination ChannelID |
	Then insert "PI-" in ticket number field on create datafix page 
	Then insert "Bot copy_channel_data-calc" in notes field on create datafix page 
	Then click on "source ChannelID" radio on create datafix page 
	When click on "Channels" accordion on create datafix 
	Then insert "-400208" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	Then click on "select" button in channel accordion on create datafix page 
	Then click on "destination ChannelID" radio on create datafix page 
	Then insert "-400209" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	Then click on "select" button in channel accordion on create datafix page 
	Then verify channelid text box should not be empty on create datafix page
	When click on "Channels" accordion on create datafix  
	Then insert dates in datepicker for "datafix" on create datafix page 
		| startDate  | endDate    |	
		| 04/13/2018 | 04/13/2018 |  
	When click on "save" button on datafix page 
	Then error message should display with message "Copy not allowed due to different CalcType." 
	
@DatafixDeleteChannelData 
Scenario: Scenario_10: create datafix for delete channel data on datafix page 
	Given we launch the browser with admin-UI URL and login with valid admin credential 
	When we will land on Site page and we will click on Datafix tab 
	When click on "create datafix" button on Datafix page 
	Then insert "aUITestSite" in search text box and select site from create datafix 
	When select "delete_channel_data" from task dropdown on create datafix page 
	When click on "save" button on datafix page 
	Then valid error message should be displayed for "empty fields" scenario on create datafix page 
		|Notes|ChannelID|
	Then insert "PI-" in ticket number field on create datafix page 
	Then insert "Bot delete datafix channel" in notes field on create datafix page 
	Then Verify the ChannelId input fields are ready only on create datafix page 
		|ChannelId|
	When select on source channel redio on create datafix page 
	When click on "Channels" accordion on create datafix 
	Then insert "-400210" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	Then click on "select" button in channel accordion on create datafix page 
	Then verify channel should not be empty for "delete channel" on create datafix page 
	When click on "Channels" accordion on create datafix  
	Then insert dates in datepicker for "datafix" on create datafix page 
		| startDate  | endDate    |	
		| 04/13/2018 | 04/13/2018 |  
	When click on "save" button on datafix page 
	When user click on "Preview:Save" button on datafix page for channel id "-400210" 
	Then user should be navigated to create Datafix page with a label saying "DataFix created successfully." 
	Then verify "delete channel" task is performed for channel id "-400210" 
	Then click on "DataFix:close" button on datafix page 
	
@DatafixDeleteMultipleChannelData 
Scenario: Scenario_11: create datafix for divide chanel data on datafix page 
	Given we launch the browser with admin-UI URL and login with valid admin credential 
	When we will land on Site page and we will click on Datafix tab 
	When click on "create datafix" button on Datafix page 
	Then insert "aUITestSite" in search text box and select site from create datafix 
	When select "delete_channel_data" from task dropdown on create datafix page 
	Then insert "PI-" in ticket number field on create datafix page 
	Then insert "Bot delete more then one channel data" in notes field on create datafix page 
	Then click "+Add" button on create datafix page 
	When select channel1 radio button on create datafix page 
	When click on "Channels" accordion on create datafix 
	Then insert "-400211" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	Then click on "select" button in channel accordion on create datafix page 
	When select channel2 radio button on create datafix page 
	Then insert "-400212" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	Then click on "select" button in channel accordion on create datafix page 
	Then verify channel should not be empty for "divide_channel_data" on create datafix page 
	When click on "Channels" accordion on create datafix
	Then insert dates in datepicker for "datafix" on create datafix page 
		| startDate  | endDate    |	
		| 04/13/2018 | 04/13/2018 | 
	When click on "save" button on datafix page 
	When user click on "Preview:Save" button on datafix page for channel "-400211" and "-400212" 
	Then user should be navigated to create Datafix page with a label saying "DataFix created successfully." 
	Then verify "Multiple delete channel" task is performed for channel "-400211" and "-400212" 
	Then click on "DataFix:close" button on datafix page
	
@DatafixCopyChannelWithDifferentGranuarity 
Scenario: Scenario_12: verify the create datafix for copy data channel with different granularity functionality on Datafix page 
	Given we launch the browser with admin-UI URL and login with valid admin credential 
	When we will land on Site page and we will click on Datafix tab 
	When click on "Create Datafix" button on Datafix page 
	Then insert "aUITestSite" in search text box and select site from create datafix 
	Then click on "Site Details" accordion on create datafix 
	When select "COPY_CHANNEL_DATA" from task dropdown on create datafix page 
	Then insert "PI-" in ticket number field on create datafix page 
	Then insert "copy_channel_data" in notes field on create datafix page 
	Then click on the "Source Channel" radio on create datafix page 
	When click on "Channels" accordion on create datafix 
	Then insert "-400213" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	Then click on "select" button in channel accordion on create datafix page 
	Then click on the "Destination Channel" radio on create datafix page 
	Then insert "-400214" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	Then click on "select" button in channel accordion on create datafix page 
	When click on "Channels" accordion on create datafix 
	Then insert dates in datepicker for "datafix" on create datafix page 
		| startDate  | endDate    | 
		| 04/13/2018 | 04/13/2018 |   
	When click on "save" button on datafix page 
	When click on "Ok" button on datafix page
	When user click on "Preview:Save" button on datafix page for channel "-400213" and "-400214" 
	Then user should be navigated to create Datafix page with a label saying "DataFix created successfully." 
	
@DataFixRestoreCheck 
Scenario: Scenario_13: Verify the restore accordion functionality on Datafix Page 
	Given we launch the browser with admin-UI URL and login with valid admin credential 
	When we will land on Site page and we will click on Datafix tab
	Then enter "Bot multiply datafix channel" in the "search" box and click on "Search" button
	Then select row which is having notes as "Bot multiply datafix channel"
	Then verify the visibility of following columns in datafix details grid
		| DataFix:Task | DATEFIX:STARTUTC | DATEFIX:ENDUTC | DATEFIX:CHANNEL ID |
	Then click on "RESTORE:BUTTON1" button on datafix page 
	Then insert "Restore Value of channel_id:-400201" in notes field on restore datafix page
	Then click on "Save" button on restore datafix
	Then success message should be displayed on the "DataFix" model
	Then click on "Restore:Close" button on restore datafix
	
	
	
	##   53, 5
	#    Then insert "-400215" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	#    Then click on "select" button in channel accordion on create datafix page 
	#    Then click on the "Destination Channel" radio on create datafix page 
	##   69, 1
	#    Then insert "-400216" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	#    Then click on "select" button in channel accol_data_granularity1" rdion on create datafix page 
	#    Then insert "copy_channein notes field on create datafix page 
	#    Then insert dates in datepicker for "datafix" on create datafix page 
	#        | startDate  | endDate    | 
	#        | 05/15/2017 | 05/15/2017 | 
	#    When click on "save" button on datafix page 
	#    Then confirmation box should display with message "The start and end times chosen fall on interval boundaries. No Adjustment needed here." on restore page 
	#    Then click on "ok" button on confirmation box of roll up condition 
	#    When user click on "Preview:Save" button on datafix page for channel "-4000215" and "-4000216" 
	#    Then user should be navigated to create Datafix page with a label saying "DataFix created successfully." 
	#    #    Then verify "Copy Channel" task is performed for channel "-4000208" and "-4000210"
	#    
	#    Then click on the "Source Channel" radio on create datafix page 
	#    When click on "Channels" accordion on create datafix
	##   71, 5
	#    Then insert "-4000215" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	#    Then click on "select" button in channel accordion on create datafix page 
	#    Then click on the "Destination Channel" radio on create datafix page 
	#    Then insert "-4000217" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	#    Then click on "select" button in channel accordion on create datafix page 
	#    Then insert "copy_channel_data_granularity5" in notes field on create datafix page 
	#    Then insert dates in datepicker for "datafix" on create datafix page 
	#        | startDate  | endDate    | 
	#        | 05/15/2017 | 05/15/2017 | 
	#    When click on "save" button on datafix page 
	#    Then confirmation box should display with message "The start and end times chosen do not fall on interval boundaries. The system will automatically adjust" on restore page 
	#    And confirm the Adjusted start date stamp value should be "2017-05-15 10:40" 
	#    And confirm the Adjusted end date stamp value should be "2017-05-15 10:50" 
	#    Then click on "ok" button on confirmation box of roll up condition 
	#    When user click on "Preview:Save" button on datafix page for channel "-4000215" and "-4000217" 
	#    Then user should be navigated to create Datafix page with a label saying "DataFix created successfully." 
	#    #    Then verify "Copy Channel" task is performed for channel "-4000208" and "-4000211"
	#    
	#    Then click on the "Source Channel" radio on create datafix page 
	#    When click on "Channels" accordion on create datafix 
	#    Then insert "-4000215" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	#    Then click on "select" button in channel accordion on create datafix page 
	#    Then click on the "Destination Channel" radio on create datafix page 
	##    73, 15
	#    Then insert "-4000218" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	#    Then click on "select" button in channel accordion on create datafix page 
	#    Then insert "copy_channel_data_granularity15" in notes field on create datafix page 
	#    Then insert dates in datepicker for "datafix" on create datafix page 
	#        | startDate  | endDate    | 
	#        | 05/15/2017 | 05/15/2017 | 
	#    When click on "save" button on datafix page 
	#    Then confirmation box should display with message "The start and end times chosen do not fall on interval boundaries. The system will automatically adjust" on restore page 
	#    And confirm the Adjusted start date stamp value should be "2017-05-15 10:30" 
	#    And confirm the Adjusted end date stamp value should be "2017-05-15 11:00" 
	#    Then click on "ok" button on confirmation box of roll up condition 
	#    When user click on "Preview:Save" button on datafix page for channel "-4000215" and "-4000218" 
	#    Then user should be navigated to create Datafix page with a label saying "DataFix created successfully." 
	#    #    Then verify "Copy Channel" task is performed for channel "-4000208" and "-4000212"
	#    
	#    Then click on the "Source Channel" radio on create datafix page 
	#    When click on "Channels" accordion on create datafix 
	#    Then insert "-4000215" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	#    Then click on "select" button in channel accordion on create datafix page 
	#    Then click on the "Destination Channel" radio on create datafix page 
	##    75, 60 
	#    Then insert "-4000219" in search text box of channels accordion and click on "searchChannel" button on create datafix 
	#    Then click on "select" button in channel accordion on create datafix page 
	#    Then insert "copy_channel_data_granularity60" in notes field on create datafix page 
	#    Then insert dates in datepicker for "datafix" on create datafix page 
	#        | startDate  | endDate    | 
	#        | 05/15/2017 | 05/15/2017 | 
	#    When click on "save" button on datafix page 
	#    Then confirmation box should display with message "The start and end times chosen do not fall on interval boundaries. The system will automatically adjust" on restore page 
	#    And confirm the Adjusted start date stamp value should be "2017-05-15 10:00" 
	#    And confirm the Adjusted end date stamp value should be "2017-05-15 11:00" 
	#    Then click on "ok" button on confirmation box of roll up condition 
	#    When user click on "Preview:Save" button on datafix page for channel "-4000215" and "-4000219" 
	#    Then user should be navigated to create Datafix page with a label saying "DataFix created successfully." 
	#    #    Then verify "Copy Channel" task is performed for channel "-4000208" and "-4000213"
	

	