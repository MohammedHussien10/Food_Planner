package com.example.foodplanner.DetailsFragment.presenter;

import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsResponse;
import com.example.foodplanner.models.RemoteMeals;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface DetailsContract {
   void assignSelectedMealIngredientsToAdapter(List<String> Ingredients);
 void SelectedMeal(RemoteMeals meal);
   void showToast(String toast);
    void getMealDetails();
}
