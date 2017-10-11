package tech.ryanfehr.androiddevfinalprojectapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepSensor;

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    LatLng coordinates = new LatLng(39.165557, -86.523599);
    List<Circle> path = new ArrayList<>();
    float totalStepsTaken = 0;
    float stepsTakenSinceRestart = 0;
    boolean tracking = true;

    GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Setup the gesture listener for double tap events
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        mainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gd.onTouchEvent(event);
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Init the GPS tracker
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null)
                    coordinates = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.clear();
                goToLocationZoom(coordinates, 16);

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                Log.v("GPS", "onStatusChanged");
            }

            @Override
            public void onProviderEnabled(String s) {
                Log.v("GPS", "onProviderEnabled");
            }

            @Override
            public void onProviderDisabled(String s) {
                Log.v("GPS", "onProviderDisabled");
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        // Initialize step counter
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);

        // Ask for GPS and network permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 10);
            }
            return;
        } else {
            Log.v("GPS", "onCreate else");
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }

        // Initialize gesture detector
        gd = new GestureDetector(MapsActivity.this, new GestureDetector.SimpleOnGestureListener(){


            //here is the method for double tap


            @Override
            public boolean onDoubleTap(MotionEvent e) {
                TextView stepsTextView = (TextView) findViewById(R.id.stepsTextView);
                if(tracking) {

                    String display = "Steps taken: " + String.valueOf(totalStepsTaken-stepsTakenSinceRestart);
                    stepsTextView.setText(display);
                    for (Circle circle : path) {
                        mMap.addCircle(new CircleOptions()
                                .center(circle.getCenter())
                                .radius(circle.getRadius())
                                .strokeColor(circle.getStrokeColor())
                                .fillColor(circle.getFillColor()));
                    }
                    tracking = false;
                }
                else {

                    mMap.clear();
                    Circle lastLocation = path.get(path.size()-1);
                    mMap.addCircle(new CircleOptions()
                            .center(lastLocation.getCenter())
                            .radius(lastLocation.getRadius())
                            .strokeColor(lastLocation.getStrokeColor())
                            .fillColor(lastLocation.getFillColor()));
                    path = new ArrayList<>();
                    stepsTakenSinceRestart = totalStepsTaken;
                    tracking = true;
                    String display = "Steps taken: " + String.valueOf(totalStepsTaken-stepsTakenSinceRestart);
                    stepsTextView.setText(display);
                }

                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);

            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }


        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Location location = getLastBestLocation();
        if (location != null)
            coordinates = new LatLng(location.getLatitude(), location.getLongitude());
        goToLocationZoom(coordinates, 16);
    }

    // Goes to location with default zoom
    public void goToLocation(LatLng latLng) {
        mMap.addCircle(new CircleOptions()
                .center(latLng)
                .radius(3)
                .strokeColor(0xFF42AAEF)
                .fillColor(0xAA42AAEF));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    // Goes to location with specified zoom
    public void goToLocationZoom(LatLng latLng, int zoom) {
        Circle circle = mMap.addCircle(new CircleOptions()
                .center(latLng)
                .radius(3)
                .strokeColor(0xFF42AAEF)
                .fillColor(0xAA42AAEF));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        path.add(circle);
    }


    /**
     * @return the last known best location
     * Source: StackOverFlow
     */
    private Location getLastBestLocation() {
        Location locationGPS = null;
        Location locationNet = null;
        try{
            locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } catch(SecurityException e) {
            Log.e("No GPS Permission", e.getMessage());
        }
        try{
            locationNet = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        } catch(SecurityException e) {
            Log.e("No Network Permission",e.getMessage());
        }


        long GPSLocationTime = 0;
        if (null != locationGPS) { GPSLocationTime = locationGPS.getTime(); }

        long NetLocationTime = 0;

        if (null != locationNet) {
            NetLocationTime = locationNet.getTime();
        }

        if ( 0 < GPSLocationTime - NetLocationTime ) {
            return locationGPS;
        }
        else {
            return locationNet;
        }
    }

    // Handles result of a permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.v("GPS", "onRequestPermissionsResult" + ",RQ:" + requestCode + ",GR:" + grantResults.length);
        switch (requestCode) {
            case (10):
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                }
                break;
        }
    }

    // Keeps track of steps from step counter sensor
    @Override
    public void onSensorChanged(SensorEvent event) {
        totalStepsTaken = event.values[0];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
