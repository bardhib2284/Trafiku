package com.example.trafiku.api;

import com.example.trafiku.models.BusResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/api/v1/buses/")
    Call<BusResponse> getBuses();

}
//wifi: auk-campus
//pass: ritkosova
