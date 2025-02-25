package com.example.foodplanner.network;

import com.example.foodplanner.models.CategoriesResponse;
import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsResponse;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealsService {
    @GET("random.php")
    Single<MealsResponse> getDailyMeals();

    @GET("search.php?s=")
    Single<MealsResponse> getRandomMeals();


    @GET("categories.php")
    Single<CategoriesResponse> getCategory();

    @GET("search.php")
    Single<MealsResponse> getRandomMeals(@Query("i") String id);


}
