package com.example.foodplanner.homescreenfragment.presenter;

import com.example.foodplanner.models.Categories;
import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.RemoteMeals;

import java.util.List;

public interface HomeContract {
    void assignRandomMealsAdapter(List<RemoteMeals> mealsList);
    void showDailyMeals(RemoteMeals meal);
    void showToast(String toast);

}
