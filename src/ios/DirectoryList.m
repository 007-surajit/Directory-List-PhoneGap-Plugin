#import "DirectoryList.h"
#import <Cordova/CDV.h>

@implementation DirectoryList

- (void)getList:(CDVInvokedUrlCommand*)command {

    CDVPluginResult* pluginResult = nil;	
	NSString *directory  = [command.arguments objectAtIndex:0]; 
	if (directory == nil || [directory isEqual: @""]) {
		directory = @"www";
    }
	NSString *bundlePathName = [[NSBundle mainBundle] bundlePath];
	NSString *dataPathName = [bundlePathName stringByAppendingPathComponent:directory];
	NSFileManager *fileManager = [NSFileManager defaultManager];
    if ([fileManager fileExistsAtPath:dataPathName]) {
        NSLog(@"%@ exists", dataPathName);
        BOOL isDir = NO;
        [fileManager fileExistsAtPath:dataPathName isDirectory:(&isDir)];
        if (isDir == YES) {
            NSLog(@"%@ is a directory", dataPathName);
            NSArray *contents;
            contents = [fileManager contentsOfDirectoryAtPath:dataPathName error:nil];
            for (NSString *entity in contents) {
                NSLog(@"%@ is within", entity);
            }
			pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArray:contents];
        } else {
			NSLog(@"%@ is not a directory", dataPathName);
            NSString *errorMessage = [NSString stringWithFormat:@"%@ is not a directory", dataPathName];			
			pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:errorMessage];

        }
    } else {
        NSLog(@"%@ does not exist", dataPathName);
		NSString *errorMessage = [NSString stringWithFormat:@"%@ does not exist", dataPathName];			
		pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:errorMessage];
    }
	[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end