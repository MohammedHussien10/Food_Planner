package com.example.foodplanner.network;

import android.util.Log;

import com.example.foodplanner.homescreenfragment.presenter.HomeContract;
import com.example.foodplanner.models.AreaResponse;
import com.example.foodplanner.models.CategoriesResponse;
import com.example.foodplanner.models.IngredientsResponse;
import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsResponse;
import com.example.foodplanner.models.RemoteMeals;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSource {
    List<Meals> listOfMeals;
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    MealsService service;
    private static MealsRemoteDataSource remoteSource;

    private MealsRemoteDataSource(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        service = retrofit.create(MealsService.class);
    }


    public static MealsRemoteDataSource getInstance(){

        if(remoteSource == null){
            remoteSource = new MealsRemoteDataSource();
        }
        return remoteSource;
    }


    public Single<RemoteMeals> getDailyMeals() {
        return service.getDailyMeals()
                .map(response -> {
                    if (response != null && response.getMeals() != null && !response.getMeals().isEmpty()) {
                        return response.getMeals().get(0);
                    } else {
                        throw new NullPointerException("API response is null or empty");
                    }
                });
    }
    public Single<MealsResponse> getRandomMeals(){

        return service.getRandomMeals();
    }

    public Single<MealsResponse> getSelectedMeal(String mealId){

        return service.getSelectedMeal(mealId);
    }

    public Single<CategoriesResponse> getCategory(){

        return service.getCategory();
    }

    //getIngredientSearch screen

    public Single<IngredientsResponse> getIngredient(){

        return service.getIngredient();
    }

    //getArea
    public Single<AreaResponse> getArea(){

        return service.getArea();
    }

    public Single<MealsResponse> getSelectedCategories(String categoryName ){

        return service.getSelectedCategories(categoryName);
    }

    public Single<MealsResponse> getSelectedArea(String areaName){

        return service.getSelectedArea(areaName);
    }

    public Single<MealsResponse> getSelectedIngredient(String ingredientName){

        return service.getSelectedIngredient(ingredientName);
    }

}

