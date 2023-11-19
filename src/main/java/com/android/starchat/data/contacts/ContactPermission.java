package com.android.starchat.data.contacts;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.starchat.ui.uiMain.mainActivity.MainActivity;
import com.android.starchat.util.PermissionHelper;

public class ContactPermission {
    public static boolean checkContactPermission(MainActivity mainActivity, int requestCode){
        if(ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(mainActivity,Manifest.permission.READ_CONTACTS)){
                createContactPermissionRationaleDisplay(mainActivity);
            }else{
                ActivityCompat.requestPermissions(mainActivity,new String[]{Manifest.permission.READ_CONTACTS},requestCode);
            }
            return false;
        }
        return true;
    }


    private static void createContactPermissionRationaleDisplay(MainActivity mainActivity){
        new AlertDialog.Builder(mainActivity)
                .setTitle("Contacts Permission required")
                .setMessage("Please allow the Contacts Permission. Star Chat will help you connect " +
                        "to your contacts. The information will not be used for anything else...")
                .setPositiveButton("SETTINGS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PermissionHelper.openAppSettings(mainActivity);
                    }
                })
                .setNegativeButton("CANCEL",null)
                .create()
                .show();

    }
}
