package com.example.foodplanner.models;

import com.google.gson.annotations.SerializedName;

public class Categories {
    @SerializedName("idMeal")
    private String idCategory;
    @SerializedName("strCategory")
    private String strCategory;
    @SerializedName("strCategoryThumb")
    private String  strCategoryThumb;

    public Categories(String idCategory, String strCategory, String strCategoryThumb) {
        this.idCategory = idCategory;
        this.strCategory = strCategory;
        this.strCategoryThumb = strCategoryThumb;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

    public void setStrCategoryThumb(String strCategoryThumb) {
        this.strCategoryThumb = strCategoryThumb;
    }


}
