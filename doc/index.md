# Asset-Directory-PhoneGap-Plugin

This plugin retrives the directory listing of any directory in the assets folder of Android Phonegap application
or any physical directory in the main bundle of iOS Phonegap app.
You can use the "/" separator to retrieve subfolder listing also.

## Installation

    cordova plugin add https://github.com/007-surajit/Directory-List-PhoneGap-Plugin.git

## Supported Platforms

- Android
- iOS
- WP8

## Methods

- window.plugins.directoryList.getList


## window.plugins.directoryList.getList

Returns any physical directory listing within the assets folder for Android application or within main bundle in case of iOS application

    window.plugins.directoryList.getList(physical_folder_name,
                                             onDirectoryReadSuccess,
                                             __onDirectoryReadError__);

### Parameters

- physical_folder_name: _ Any physical folder name within the application.

- __onDirectoryReadSuccess__: The callback that is passed the current position.

- __onDirectoryReadError__: The callback that executes if an error occurs.

### Example

Retrieve listing of www folder:

    // onDirectoryReadSuccess Callback
    // This method returns the directory listing in array format
    //
	function onDirectoryReadSuccess(directoryList) {
		for (var entry in directoryList) {
			if( directoryList.hasOwnProperty( entry ) ) {
			  console.log(directoryList[entry]);
			} 
		}
	}

    // onError Callback if directory does not exists or it is empty
    //
    function onDirectoryReadError(error) {
        alert('Directory Read error \n' +
              'message: ' + error);
    }

    window.plugins.directoryList.getList("www",onDirectoryReadSuccess,onDirectoryReadError);
	
Retrieve listing of www/imgs folder:

	// onDirectoryReadSuccess Callback
    // This method returns the directory listing in array format
    //
	function onDirectoryReadSuccess(directoryList) {
		for (var entry in directoryList) {
			if( directoryList.hasOwnProperty( entry ) ) {
			  console.log(directoryList[entry]);
			} 
		}
	}

    // onError Callback if directory does not exists or it is empty
    //
    function onDirectoryReadError(error) {
        alert('Directory Read error \n' +
              'message: ' + error);
    }

    window.plugins.directoryList.getList("www/imgs",onDirectoryReadSuccess,onDirectoryReadError);
