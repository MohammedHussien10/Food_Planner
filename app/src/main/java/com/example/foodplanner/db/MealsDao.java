package com.example.foodplanner.db;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.foodplanner.models.Meals;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
@Dao
public interface MealsDao {
    @Query("SELECT * FROM randomMealsTable")
    Flowable<List<Meals>> getRandomMeals();
}
