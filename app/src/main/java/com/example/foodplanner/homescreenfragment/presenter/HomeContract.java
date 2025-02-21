package com.example.foodplanner.homescreenfragment.presenter;

import com.example.foodplanner.models.Categories;
import com.example.foodplanner.models.Meals;

import java.util.List;

public interface HomeContract {
    void assignRandomMealsAdapter(List<Meals> mealsList);
    void showDailyMeals(Meals meal);
    void showToast(String toast);
}
