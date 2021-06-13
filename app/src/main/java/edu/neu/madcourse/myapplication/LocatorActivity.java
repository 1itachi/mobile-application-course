package edu.neu.madcourse.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

    private void getUserLocation() {
        TextView textViewLocator = findViewById(R.id.textView_locator);
        if (ActivityCompat.checkSelfPermission(LocatorActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            textViewLocator.setText(getResources().getString(R.string.Location_access_denied));
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, getResources().getInteger(R.integer.location_request_code));
        } else if (ActivityCompat.checkSelfPermission(LocatorActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            textViewLocator.setText(getResources().getString(R.string.Location_access_denied));
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, getResources().getInteger(R.integer.location_request_code));
        } else {
            textViewLocator = findViewById(R.id.textView_locator);
            Location currentGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location currentNetworkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location currentPassiveLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            if (currentGPS != null) {
                textViewLocator.setText(String.format(latitudeLongitudePair, currentGPS.getLatitude(), currentGPS.getLongitude()));
            }
            if (currentNetworkLocation != null) {
                textViewLocator.setText(String.format(latitudeLongitudePair, currentNetworkLocation.getLatitude(), currentNetworkLocation.getLongitude()));
            }
            if (currentPassiveLocation != null) {
                textViewLocator.setText(String.format(latitudeLongitudePair, currentPassiveLocation.getLatitude(), currentPassiveLocation.getLongitude()));
            } else {
                Snackbar.make(textViewLocator, gpsLoading, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, getResources().getInteger(R.integer.location_update_minimum_time), getResources().getInteger(R.integer.location_update_minimum_dist), v -> {
                });
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, getResources().getInteger(R.integer.location_update_minimum_time), getResources().getInteger(R.integer.location_update_minimum_dist), v -> {
                });
            }
        }
    }


}