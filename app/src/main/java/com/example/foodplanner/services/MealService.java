package com.example.foodplanner.services;

import com.example.foodplanner.models.MealsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {
    @GET("api/json/v1/1/random.php")
    Call<MealsResponse> getRandomMeals();

}
