package com.example.foodplanner.searchbymeal.presenter;

import com.example.foodplanner.models.Area;
import com.example.foodplanner.models.Categories;
import com.example.foodplanner.models.Ingredients;
import com.example.foodplanner.models.RemoteMeals;

import java.util.List;

public interface SearchByMealContract {
    void assignSelectedMealToAdapter(List<RemoteMeals> mealList);
//    void SelectedMeal(RemoteMeals meal);
    void getMealDetailsByCategories();
    void getMealDetailsByArea();
    void getMealDetailsByIngredient();
    void showToast(String toast);
}
