package com.example.foodplanner.db;

import android.content.Context;

import com.example.foodplanner.models.CalendarPlan;
import com.example.foodplanner.models.Meals;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsLocalDataSource {
    private MealsDao mealsDao;

    private static MealsLocalDataSource localDataSource = null;

    private MealsLocalDataSource(Context context) {
        MealsDataBase db = MealsDataBase.getInstance(context);
        mealsDao = db.getMealsDao();




    }

    public static MealsLocalDataSource getInstance(Context context){
        if (localDataSource == null){
            localDataSource = new MealsLocalDataSource(context);
        }
        return localDataSource;
    }

    public Flowable<List<Meals>>getFavouritesMeals(){
        return mealsDao.getFavouritesMeals();
    }



    public Completable removeFavouritesMeals(Meals meal) {
        return mealsDao.deleteFavouritesMeals(meal).subscribeOn(Schedulers.io());

    }

    public Completable insertFavouritesMeals(Meals meal) {

        return  mealsDao.addFavouritesMeals(meal).subscribeOn(Schedulers.io());

    }

    public Flowable<List<CalendarPlan>>getCalendarPlanMeals(String date){
        return mealsDao.getCalendarPlanMeals(date);
    }



    public Completable deleteCalendarPlanMeals(CalendarPlan meal) {
        return mealsDao.deleteCalendarPlanMeals(meal).subscribeOn(Schedulers.io());

    }

    public Completable addCalendarPlanMeals(CalendarPlan meal) {

        return  mealsDao.addCalendarPlanMeals(meal).subscribeOn(Schedulers.io());

    }
}
