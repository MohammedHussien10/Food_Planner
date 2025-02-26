package com.example.foodplanner.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.models.CalendarPlan;
import com.example.foodplanner.models.Meals;

@Database(entities =  {Meals.class, CalendarPlan.class},version = 2)
public abstract class MealsDataBase extends RoomDatabase {
    private static MealsDataBase instance = null;
    public abstract MealsDao getMealsDao();

    public static synchronized MealsDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MealsDataBase.class, "mealsdb")
                    .build();
        }
        return instance;
    }
}
