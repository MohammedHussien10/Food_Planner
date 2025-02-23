package com.example.foodplanner.models;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.db.MealsDao;
import com.example.foodplanner.db.MealsLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class MealsRepository {


     MealsRemoteDataSource mealsRemoteDataSource;
     MealsLocalDataSource mealsLocalDataSource;
    private static MealsRepository repository = null;

    public MealsRepository(MealsRemoteDataSource mealsRemoteDataSource,MealsLocalDataSource mealsLocalDataSource){
       this.mealsRemoteDataSource = mealsRemoteDataSource;
        this.mealsLocalDataSource = mealsLocalDataSource;

    }
    public static MealsRepository getInstance(
            MealsLocalDataSource localSource,
            MealsRemoteDataSource remoteSource) {
        if (repository == null) {
            repository = new MealsRepository(remoteSource,localSource);
        }
        return repository;
    }
    public Single<RemoteMeals> getDailyMeals(){
        return mealsRemoteDataSource.getDailyMeals();

    }

    public Single<MealsResponse> getRandomMeals(){
        return mealsRemoteDataSource.getRandomMeals();
    }

    public Single<MealsResponse> getSelectedMeal(String mealId){

        return mealsRemoteDataSource.getSelectedMeal(mealId);
    }

    public Single<CategoriesResponse> getCategory(){

        return mealsRemoteDataSource.getCategory();
    }
    //getIngredientSearch screen
    public Single<IngredientsResponse> getIngredient(){

        return mealsRemoteDataSource.getIngredient();
    }


}
