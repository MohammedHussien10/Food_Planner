package com.example.foodplanner.db;

import android.content.Context;

import com.example.foodplanner.models.Meals;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class MealsLocalDataSource {
    private MealsDao mealsDao;

    private static MealsLocalDataSource localDataSource = null;

    private MealsLocalDataSource(Context context) {
        RandomMealsDataBase db = RandomMealsDataBase.getInstance(context);
        mealsDao = db.getMealsDao();




    }

    public static MealsLocalDataSource getInstance(Context context){
        if (localDataSource == null){
            localDataSource = new MealsLocalDataSource(context);
        }
        return localDataSource;
    }

    public Flowable<List<Meals>>getRandomMeals(){
        return mealsDao.getRandomMeals();
    }
}
