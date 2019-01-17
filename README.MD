# Overview

This repository is a fun side project that utilizes the [Growstuff API](https://github.com/Growstuff/growstuff/wiki/API-Version-0) 

My main goal was to see how complete I could make this app + SDK with only 2 days of development time. While I will likely add more in the future, the development time for this is only scheduled from <b>2019-01-16</b> to <b>2019-01-17</b>

The other useful part of this project is that I am essentially writing a Java / Android SDK for Growstuff, which to my knowledge does not exist.

# Outline

Below is the current outline for what I would like to get done in the 2 day window I have alloted: 

* Manifest
	* ~~Manifest declated with permissions as well as broadcast receivers.~~
	* Will add File Providers if needed
	
* Documentation
	* All docs should be in standard Javadoc format. (will be generated at the end of the 2 days)
    * All classes should have documentation above them.
	* Applicable methods and functions should also have documentation.
	* Make sure to use //region and //endregion tags for simpler code folding
	
* ~~Data models~~
	* ~~Mirror from [website](https://github.com/Growstuff/growstuff/wiki/API-Version-0) - Will need to make individual calls in order to manually view responses.~~
	* ~~Add in ErrorModel and custom parsing.~~ 

* ~~API Calls + Wrapper~~
	* ~~For API calls class, Will likely utilize my own library's [Retrofit Parser](https://github.com/PGMacDesign/PGMacTips/blob/master/library/src/main/java/com/pgmacdesign/pgmactips/networkclasses/retrofitutilities/RetrofitParser.java)~~
	* ~~Make wrapper class for static API calls so as to mimize code in Fragments and activities~~

* ~~Connectivity Checks~~
	* ~~Add in connectivity receiver to confirm internet availability before calls~~
	* ~~Utilize a snackbar for no internet connection~~
	
* Architecture
	* ~~App will follow standard app architecture logic and have an Activity with modular fragments~~ 
	* ~~I'd like to use a navigation drawer if possible as they always look clean if done correctly~~
	* ~~Standard MyApplication class as central singleton with initilized fields upon load.~~
	* Decided to scale things back a bit for simplicity. Will leave the API Interface as is and just ignore unused endpoints.
	
* Android Jetpack
	* I'd also like to make use of the new Android Jetpack modules, specifically the following:
		* Rooms
		* ViewModel
	* Implement Observers using ViewModel + Room combo on any visible Recyclerviews
	* Utilize View models to maintain state upon rotation 

* Constants
	* ~~Utilize integer tags in combination with my library's [On Task Complete Listener](https://github.com/PGMacDesign/PGMacTips/blob/master/library/src/main/java/com/pgmacdesign/pgmactips/adaptersandlisteners/OnTaskCompleteListener.java) to ping back responses for web calls~~
	* Maintain any other needed tags, flags, or booleans within the Constants for simple reference.
	
* ~~Icons~~
	* ~~Use custom icons for the app~~
	* ~~Use various icons for the different nav bar icons to differentiate sections~~
	* ~~Make sure icons mirror Android standards of:~~
		* ~~xxxdpi~~
		* ~~xxdpi~~
		* ~~xdpi~~
		* ~~hdpi~~
		* ~~mdpi~~

* Res Dimens
	* If time permits, utilize dimens.xml to have screen-specific sizes to work with on UI element scaling
		
* UI Components
	* Make heavy use of Recyclerviews (look into making mutli-purpose adapter)
		
* Animations
	* If time permits, add in animations to UI elements
	* Also add in loading view / progress bar with custom animation built into it
	
	
	
	
Whatever I do not finish I will leave up here as a TODO for the future.	