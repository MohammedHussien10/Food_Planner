package com.example.foodplanner.models;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.R;
import com.example.foodplanner.db.MealsDao;
import com.example.foodplanner.db.MealsLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
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

    //getArea
    public Single<AreaResponse> getArea(){

        return mealsRemoteDataSource.getArea();
    }

//    public List<Area> getAreas(){
//        List<Area> areas = new ArrayList<>();
//        areas.add(new Area("American", R.drawable.america)
//        return areas;
//    }

    public Flowable<List<Meals>> getFavoriteMeals(){
        return mealsLocalDataSource.getFavouritesMeals();
    }

    public Completable insertFavoriteMeals(Meals meal){
        return mealsLocalDataSource.insertFavouritesMeals(meal);
    }

    public Completable deleteFavoriteMeals(Meals meal){

        return mealsLocalDataSource.removeFavouritesMeals(meal);
    }


    public Flowable<List<CalendarPlan>> getCalendarPlanMeals(String date){
        return mealsLocalDataSource.getCalendarPlanMeals(date);
    }

    public Completable addCalendarPlanMeals(CalendarPlan meal){
        return mealsLocalDataSource.addCalendarPlanMeals(meal);
    }

    public Completable deleteCalendarPlanMeals(CalendarPlan meal){

        return mealsLocalDataSource.deleteCalendarPlanMeals(meal);
    }



    public Single<MealsResponse> getSelectedCategories(String categoryName){

        return mealsRemoteDataSource.getSelectedCategories(categoryName);
    }

    public Single<MealsResponse> getSelectedArea(String areaName){

        return mealsRemoteDataSource.getSelectedArea(areaName);
    }

    public Single<MealsResponse> getSelectedIngredient(String ingredientName){

        return mealsRemoteDataSource.getSelectedIngredient(ingredientName);
    }




}
