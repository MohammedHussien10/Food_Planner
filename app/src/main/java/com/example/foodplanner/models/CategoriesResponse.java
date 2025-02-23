package com.example.foodplanner.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesResponse {
    private List<Categories> categories;

    public CategoriesResponse(List<Categories> categories) {
        this.categories = categories;
    }

    public List<Categories> getCategories() { return categories; }
}