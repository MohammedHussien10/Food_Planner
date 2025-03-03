package com.example.foodplanner.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientsResponse {
 @SerializedName("meals")
    private List<Ingredients> ingredients;

    public IngredientsResponse(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }
}
