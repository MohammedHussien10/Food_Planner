package com.example.foodplanner.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.models.Meals;

@Database(entities =  {Meals.class},version = 1)
public abstract class RandomMealsDataBase extends RoomDatabase {
    private static RandomMealsDataBase instance = null;
    public abstract MealsDao getMealsDao();

    public static synchronized RandomMealsDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), RandomMealsDataBase.class, "randommealsdb")
                    .build();
        }
        return instance;
    }
}
