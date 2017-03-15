## WeatherApp

This is a native Android weather application comprised of three views: the Main Activity, the Location
Activity, and the Details Activity. In general, the descriptions of these views are as follows:
* The Main Activity, the main view of the app, is what the user sees on start up. This view displays current weather warnings, current weather conditions, and the short version of the 7 day forecast. If the user taps on the Special Weather Statement, Current Weather Conditions, or any of the forecasts, the Details view is displayed. The Main Activity also provides access to the Location activity for selecting a location.
* The Details Activity displayes a more detailed description of the selected item from the Main Activity. The user can navigate back to the Main Activity from this activity.
* The Location Activity displays the currently selected location and also provides a list of all other selectable locations. This activity also allows the user to navigate back to the Main Activity without selecting a new location. 

### Features
* On first use, the application loads the database with 839 possible Environment Canada weather forecast locations. It then prompts a user to select a location, which is then stored in the database.
* After selecting a location from the list, the title and next update time of that location is displayed on the main activity along with a list view of the title information parsed from the Environment Canada XML file.
* Selecting an item from the list view will bring the user to the details activity, which will display information from the summary element of that particular entry in the Environment Canada XML file.
* The user is freely able to navigate between activities using selections or the provided back buttons.

### Polish
* A toast appears on first use of the application to prompt the user to select a location.
* The location activity features a responsive search bar. When a user begins to type in the search bar, the list view of locations is filtered based on what the user has typed.
* A background of a blue sky, clouds and sun was added to the applicationactivities.
* Rounded corners were add to all view elements of the application.
