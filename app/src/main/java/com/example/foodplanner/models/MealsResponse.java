package com.example.foodplanner.models;

import com.example.foodplanner.R;

import java.util.List;

public class MealsResponse {
    private List<RemoteMeals> meals;

    public MealsResponse(List<RemoteMeals> meals) {

        this.meals = meals;
    }

    public List<RemoteMeals> getMeals() {

        return meals;
    }


}
