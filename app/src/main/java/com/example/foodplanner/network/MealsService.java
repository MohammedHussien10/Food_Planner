package com.example.foodplanner.network;

import com.example.foodplanner.models.MealsResponse;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MealsService {
    @GET("random.php")
    Single<MealsResponse> getRandomMeals();

}
