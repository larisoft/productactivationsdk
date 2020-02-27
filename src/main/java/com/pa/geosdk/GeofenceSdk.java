package com.pa.geosdk;

import io.radar.sdk.Radar;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.content.ContextCompat;

public class GeofenceSdk {

    private static GeofenceSdk instance = null;

    private Context appContext;

    private int requestCode = 1;
    private String radar_api_key = "prj_live_pk_4fb9d494fd14401117079572640b88ba67819c73";
    private GeofenceSdk(Context appContext){

        this.appContext = appContext;

    }

    public void initialize(Activity activity){

        String packageName  = this.appContext.getPackageName();

        Radar.initialize(radar_api_key);
        Radar.setUserId(packageName);

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, requestCode);
            }
        }


    }


    public void start(){

        Radar.startTracking();
    }



    public static GeofenceSdk getInstance(Context appContext){

        if(instance==null) instance = new GeofenceSdk(appContext);
        return instance;
    }
}
