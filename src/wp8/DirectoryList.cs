using System;
using System.Windows;
using System.Runtime.Serialization;
using WPCordovaClassLib.Cordova;
using WPCordovaClassLib.Cordova.Commands;
using WPCordovaClassLib.Cordova.JSON;
using System.Diagnostics; //Debug.WriteLine

using System.Windows.Controls;
using Microsoft.Phone.Controls;
using System.Collections.Generic;
using Windows.Storage;

namespace Cordova.Extension.Commands
{
    public class DirectoryList : BaseCommand
    {
        public async void getList(string options)
        {
            string directory = "www";

            try
            {
                directory = JsonHelper.Deserialize<string[]>(options)[0];
            }
            catch (Exception e)
            {
                // todo log
                //LOG.d("Asset List", "Text parameter not valid, using default");                
            }

            var rootFolder = Windows.ApplicationModel.Package.Current.InstalledLocation;

            directory = directory.Replace("/", @"\");
            StorageFolder folder = await rootFolder.GetFolderAsync(directory);
            IReadOnlyList<StorageFile> files = await folder.GetFilesAsync();
            var fileNames = new List<string>();
            for (int i = 0; i < files.Count; i++)
            {
                fileNames.Add(files[i].Name);
            }

            DispatchCommandResult(new PluginResult(PluginResult.Status.OK, fileNames.ToArray()));

        }
    }
}