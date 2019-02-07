package com.example.trafiku.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicefactory {

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://pribus.appbit.es")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
