package com.example.mymapsap;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener  {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private boolean providerEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //new
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
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
    public void onMapReady(GoogleMap googleMap) throws SecurityException {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        */

        //Add birthplace marker
        LatLng LA = new LatLng(33.7, -118);
        mMap.addMarker(new MarkerOptions().position(LA).title("My birthplace"));


        //Add current location marker
        LatLng current = new LatLng(33, -117.18);
        mMap.addMarker(new MarkerOptions().position(current).title("You are here"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
    }

    @Override
    public void onLocationChanged(Location location)
    {
        LatLng temp = new LatLng(location.getLongitude(), location.getLatitude());
        mMap.addMarker(new MarkerOptions().position(temp));
        Log.d("MapsActivity: ", "\tLongitude: " + temp.longitude +"\tLatitude: " + temp.latitude);
    }

    @Override
    public void onProviderDisabled(String provider)
    {
        providerEnabled = false;
        Log.d("MapsActivity: ", "provider disabled");
    }

    @Override
    public void onProviderEnabled(String provider)
    {
        providerEnabled = true;
        Log.d("MapsActivity: ", "provider enabled");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }
}
