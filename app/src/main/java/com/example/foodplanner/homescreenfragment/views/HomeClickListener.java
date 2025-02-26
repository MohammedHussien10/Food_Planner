package com.example.foodplanner.homescreenfragment.views;

import com.example.foodplanner.models.Meals;

public interface HomeClickListener{
    void addMealToHomeAdapter(Meals meal);
    void navigateToDetails(String mealId);

}
