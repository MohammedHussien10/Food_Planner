package com.example.foodplanner.models;

import com.google.gson.annotations.SerializedName;

public class Ingredients {
    @SerializedName("idIngredient")
    private String idIngredient;
    @SerializedName("strIngredient")
    private String strIngredient;
    @SerializedName("strDescription")
    private String  strDescription;

    public Ingredients(String idIngredient, String strIngredient, String strDescription) {
        this.idIngredient = idIngredient;
        this.strIngredient = strIngredient;
        this.strDescription = strDescription;
    }

    public String getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(String idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }
}
