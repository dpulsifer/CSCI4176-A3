###Task
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

Naturally, you may choose to create additional functionality in your weather app. Marks
will be awarded partly on how polished the application is and its feature set. There are
many extensions that you may wish to consider:
* Using graphics and visual elements.
* Using the GPS to set the current location
* Using built in sensors to report the user’s immediate conditions
* Setting user’s language preference
* Accessing radar imagery
* Reporting weather in locales outside of Canada
* Etc. The list is endless.
