package edu.neu.madcourse.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class LocatorActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private String enableGPS = "Enable GPS to get your Location";
    private String gpsLoading = "GPS is starting. Please wait or try again";
    private String latitudeLongitudePair = "Latitude: %s\nLongitude: %s";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locator);
    }

    public void getTheLocation(View view) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, getResources().getInteger(R.integer.location_request_code));
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        boolean isProviderEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!isProviderEnabled) {
            Snackbar.make(view, enableGPS, Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else {
            getUserLocation();
        }

    }

    private void getUserLocation(){
        System.out.println("here");
    }


}