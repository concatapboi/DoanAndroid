package com.example.hien.project;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.about));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        addControls();
    }

    private void addControls() {
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng stu = new LatLng(10.738298, 106.678397);
        mMap.addMarker(new MarkerOptions().position(stu).title(getResources().getString(R.string.stuMarker)));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stu,17));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
