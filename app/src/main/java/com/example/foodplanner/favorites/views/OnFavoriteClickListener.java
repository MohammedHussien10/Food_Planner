package com.example.foodplanner.favorites.views;

import com.example.foodplanner.models.Meals;

public interface OnFavoriteClickListener {

    void removeMealFromFavourite(Meals meal);
    void navigateToDetails(String mealId);
}
