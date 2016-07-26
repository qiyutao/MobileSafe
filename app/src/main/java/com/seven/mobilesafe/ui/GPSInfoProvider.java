package com.seven.mobilesafe.ui;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.util.List;

/**
 * Created by seven on 7/26/16.
 */

public class GPSInfoProvider {
    private static GPSInfoProvider instance = null;
    private static Context context = null;
    private static LocationListener listener = null;
    private LocationManager manager = null;
    private SharedPreferences sp = null;


    private GPSInfoProvider() {

    }

    public static synchronized GPSInfoProvider getInstance(Context c) {
        if (instance == null) {
            instance = new GPSInfoProvider();
            context = c;
        }
        return instance;
    }

    public String getLocation() {
        manager =
                (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        String provider = getProvider(manager);

        listener = new MyLocationListener();


        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        manager.requestLocationUpdates(provider, 60000, 50, listener);

        sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        String location = sp.getString("location", "");
        // manager.requestLocationUpdates();
        return location;
    }

    private String getProvider(LocationManager manager) {
        Criteria crit = new Criteria();
        crit.setAccuracy(Criteria.ACCURACY_FINE);
        crit.setAltitudeRequired(false);
        crit.setPowerRequirement(Criteria.POWER_MEDIUM);
        crit.setSpeedRequired(true);
        crit.setCostAllowed(false);

        return manager.getBestProvider(crit, true);
    }


    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            double lat = location.getLatitude();
            double longTit = location.getLongitude();


            SharedPreferences.Editor ed = sp.edit();
            ed.putString("location", "经度：" + longTit + "\t纬度：" + lat);
            ed.commit();
            System.out.println("经度：" + longTit + "\t纬度：" + lat);
            //Toast.makeText(context.getApplicationContext(),"location","经度："+longTit+"\t纬度："+lat,Toast.LENGTH_SHORT);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }


    }

    public void stopGPSListener() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        manager.removeUpdates(listener);
    }

}
