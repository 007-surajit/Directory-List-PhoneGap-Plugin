package in.developer.cordova.plugin;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.LOG;


public class DirectoryList extends CordovaPlugin {

    @Override
    public boolean execute (String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
       if ("getList".equals(action)) {           
        	String directory = "www";
			try {
				directory = args.getString(0);
				getList(directory,callbackContext);				
			} catch (Exception e) {
				LOG.d("Asset List", "Text parameter not valid, using default");
				getList(directory,callbackContext);
			}
        	
            return true;
        }
        // Returning false results in a "MethodNotFound" error.
        return false;
    }

    private void getList(String dirFrom,CallbackContext callbackContext) {		
		Resources res = this.cordova.getActivity().getResources(); //if you are in an activity
		AssetManager am = res.getAssets();      
		try {        	
			String fileList[] = am.list(dirFrom);
			if (fileList != null)
			{   
				JSONArray fileListArray = new JSONArray();
				for ( int i = 0;i<fileList.length;i++)
				  {
					  Log.d("",fileList[i]);
					  fileListArray.put(fileList[i]);
				  }
				//Log.w("Asset List", fileListArray.toString());
				callbackContext.success(fileListArray);
			}else{
				Log.w("Asset List", "Directory is empty or does not exists");
				callbackContext.error("Directory is empty or does not exists");
			}
		}catch (FileNotFoundException e) {
			Log.w("Asset List", "Directory does not exists: "+e.toString());
			callbackContext.error(dirFrom+" Directory does not exists: "+e.getMessage());
		}catch (IOException e) {
			Log.w("Asset List", dirFrom+" is not a directory: "+e.toString());
			callbackContext.error(dirFrom+" is not a directory: "+e.getMessage());
		}
}
