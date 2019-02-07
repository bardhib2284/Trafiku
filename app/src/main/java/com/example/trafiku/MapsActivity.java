package com.example.trafiku;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.trafiku.api.ApiService;
import com.example.trafiku.api.Servicefactory;
import com.example.trafiku.models.Bus;
import com.example.trafiku.models.BusResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        APICall();
    }

    public void APICall()
    {
        ApiService apiService = Servicefactory.retrofit.create(ApiService.class);
        Call<BusResponse> call = apiService.getBuses();
        call.enqueue(new Callback<BusResponse>() {
            @Override
            public void onResponse(Call<BusResponse> call, Response<BusResponse> response) {
                try
                {
                    System.out.println( " WORKED MUAHAHA ");
                    UpdateBuses(response.body().getResults().getBuses());
                }catch (Exception e)
                {

                }
            }

            @Override
            public void onFailure(Call<BusResponse> call, Throwable t) {
                System.out.println("NOT WORKING");
            }
        });
    }

    public void UpdateBuses(List<Bus> buses)
    {
        for (int i = 0;i< buses.size();i++)
        {
            LatLng bus = new LatLng(buses.get(i).getLatitude(), buses.get(i).getLongitude());
            mMap.addMarker(new MarkerOptions().position(bus)
                    .title(buses.get(i).getLine().getName())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.yellow_bus)));
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng pr = new LatLng(42,31);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pr));
    }
}
