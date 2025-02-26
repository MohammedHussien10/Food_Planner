package com.example.foodplanner.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.models.CalendarPlan;
import com.example.foodplanner.models.Meals;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
@Dao
public interface MealsDao {
    @Query("SELECT * FROM favourites")
    Flowable<List<Meals>> getFavouritesMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable addFavouritesMeals(Meals meal);

    @Delete
    Completable deleteFavouritesMeals(Meals meal);

    @Query("SELECT * FROM calendarplan WHERE date = :date")
    Flowable<List<CalendarPlan>> getCalendarPlanMeals(String date);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable addCalendarPlanMeals(CalendarPlan meal);

    @Delete
    Completable deleteCalendarPlanMeals(CalendarPlan meal);
}
