package com.example.foodplanner.network;

import com.example.foodplanner.models.AreaResponse;
import com.example.foodplanner.models.CategoriesResponse;
import com.example.foodplanner.models.IngredientsResponse;
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

    //getIngredientSearchScreen
    @GET("list.php?i=list")
    Single<IngredientsResponse> getIngredient();

    @GET("lookup.php")
    Single<MealsResponse> getSelectedMeal(@Query("i") String mealId);

    //getArea
    @GET("list.php?a=list")
    Single<AreaResponse> getArea();


    @GET("filter.php")
    Single<MealsResponse> getSelectedCategories(@Query("c") String categoryName);

    @GET("filter.php")
    Single<MealsResponse> getSelectedArea(@Query("a") String areaName);

    @GET("filter.php")
    Single<MealsResponse> getSelectedIngredient(@Query("i") String ingredientName);


}
