package com.example.foodplanner.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.Fragments.Loading_Home_Screen_Fragment;
import com.example.foodplanner.R;

public class FoodPlannerActivity extends AppCompatActivity {
    Loading_Home_Screen_Fragment homeScreenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.food_planner_main);
        homeScreenFragment = new Loading_Home_Screen_Fragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, homeScreenFragment).commit();

        }
    }
}