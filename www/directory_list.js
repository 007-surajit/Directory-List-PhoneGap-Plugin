/*global cordova*/
module.exports = {
	
	getList: function (directory, success, error) {
        cordova.exec(success, error, "DirectoryList", "getList", [directory]);
    }
	
};
