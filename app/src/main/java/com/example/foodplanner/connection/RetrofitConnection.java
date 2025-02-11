package com.example.foodplanner.connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnection {

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitConnection(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
