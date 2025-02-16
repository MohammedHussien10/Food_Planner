package com.example.foodplanner.homescreenfragment.presenter;

import com.example.foodplanner.models.Meals;

import java.util.List;

public interface HomeContract {
    void assignAdapter(List<Meals> mealsList);
    void showToast(String toast);
}
