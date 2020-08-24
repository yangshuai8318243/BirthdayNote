package com.birthdaynote.app;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.birthdaynote.data.entity.LocationData;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Location {
    private static final String TAG = "Location";

    public static void getLocation(Context context, LocalListener localListener) {
        LocationManager systemService = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            //            private StringBuilder stringBuilder = new StringBuilder();
            private LocationData localData = new LocationData();

            @Override
            public void onLocationChanged(android.location.Location location) {

                Geocoder gc = new Geocoder(context, Locale.getDefault());
                List<Address> locationList = null;
                try {
                    locationList = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (locationList == null) {
                    return;
                }
                Address address = locationList.get(0);//得到Address实例

                if (address == null) {
                    return;
                }


                String countryName = address.getCountryName();//得到国家名称
                if (!TextUtils.isEmpty(countryName)) {
                    localData.setCountryName(countryName);
                }

                String adminArea = address.getAdminArea();//省
                if (!TextUtils.isEmpty(adminArea)) {
                    localData.setAdminArea(adminArea);
                }

                String locality = address.getLocality();//得到城市名称
                if (!TextUtils.isEmpty(locality)) {
                    localData.setLocality(locality);
                }

                for (int i = 0; address.getAddressLine(i) != null; i++) {
                    String addressLine = address.getAddressLine(i);
                    if (!TextUtils.isEmpty(addressLine)) {
                        if (addressLine.contains(countryName)) {
                            addressLine = addressLine.replace(countryName, "");
                        }
                        if (addressLine.contains(adminArea)) {
                            addressLine = addressLine.replace(adminArea, "");
                        }
                        if (addressLine.contains(locality)) {
                            addressLine = addressLine.replace(locality, "");
                        }
                        if (!TextUtils.isEmpty(addressLine)) {
                            localData.setAddressLine(addressLine);
                        }
                    }
                }

                String featureName = address.getFeatureName();//得到周边信息
                if (!TextUtils.isEmpty(featureName)) {
                    localData.setFeatureName(featureName);
                }

                localListener.onLocationChanged(localData);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.e(TAG, "-------onStatusChanged---------");
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.e(TAG, "-------onProviderEnabled---------");

            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.e(TAG, "-------onProviderDisabled---------");

            }
        };

        systemService.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener, context.getMainLooper());
    }


    public static interface LocalListener {
        void onLocationChanged(LocationData locationData);
    }
}
