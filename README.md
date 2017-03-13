##Task
Implement an Android weather app. The app should meet the following minimum requirements:
* The client should be a native application.
* The interface should comprise at least three views: the Main Activity, the Location
Activity, and the Details Activity.
* The Main Activity should be the main view of the app, which the user sees when
they run the app. The view should display the current weather conditions and the
short version of the 7 day forecast. (The information found in the \title> element of
the \entry> elements.) If the user taps on the Special Weather Statement, Current
Weather Conditions, or any of the forecasts, the Details view should be displayed,
containing the respective details.
The Main Activity should provide a way to bring up the Location Activity so that the
user can change the location. If no location is selected (such as the first time the
app is run), the Main Activity should encourage the user to select a location.
* The Details Activity should display a more detailed description of the selected item
(containing the information found in the \summary> element). The user should be
able to navigate back to the Main Activity from this Activity.
* The Location Activity should display the current location for which the weather is
being reported, and provide a way for the user to change the location. At minimum,
the user should be able to select a location from a list of locations, which are listed
in the feeds.csv file. However, using the GPS to select the location would be a nice
feature as well. The user should also be able to navigate back to the Main Activity.

##Results
###Features
* On first use, the application loads the database with 839 possible Environ-
ment Canada weather forecast locations. It then prompts a user to select a 
location, which is then stored in the database.
* After selecting a location from the list, the title and next update time of 
that location is displayed on the main activity along with a list view of the 
title information parsed from the XML file.
* Selecting an item from the list view will bring the user to the details 
activity, which will display information from the summary element of that
particular entry in the XML file.
* The user is freely able to navigate between activities using selections or
the provided back buttons.

### Polish
* A toast appears on first use of the application to prompt the user to select
a location.
* The location activity features a responsive search bar. When a user begins 
to type in the search bar, the list view of locations is filtered based on 
what the user has typed.
* A background of a blue sky, clouds and sun was added to the application
activities.
* I added rounded corners to all view elements of the application.
