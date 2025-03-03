package com.example.foodplanner.SearchSelectbyFragment.presenter;

import com.example.foodplanner.models.Area;
import com.example.foodplanner.models.Categories;
import com.example.foodplanner.models.Ingredients;
import com.example.foodplanner.models.RemoteMeals;

import java.util.List;

public interface SearchSelectbyContract {
    void assignCategoryAdapter(List<Categories> Category);
    void assignIngredientAdapter(List<Ingredients> ingredient);
    void assignAreaAdapter(List<Area> area);

    void showToast(String toast);

}
