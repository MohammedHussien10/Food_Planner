package com.example.foodplanner.models;

import java.util.List;

public class MealsResponse {
    private List<Meals> meals;

    public MealsResponse(List<Meals> meals) {

        this.meals = meals;
    }

    public List<Meals> getMeals() {

        return meals;
    }


}
