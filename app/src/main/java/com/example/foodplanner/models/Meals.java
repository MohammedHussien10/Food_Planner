package com.example.foodplanner.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity(tableName = "randomMealsTable")
public class Meals {
    @SerializedName("idMeal")
    @PrimaryKey
    @NonNull
    private String id;

    @SerializedName("strMeal")
    @ColumnInfo(name= "name")
    private String name;

    @SerializedName("strCategory")
    @ColumnInfo(name= "category")
    private String category;

    @SerializedName("strArea")
    @ColumnInfo(name= "area")
    private String area;

    @SerializedName("strInstructions")
    @ColumnInfo(name= "instructions")
    private String instructions;

    @SerializedName("strMealThumb")
    @ColumnInfo(name= "mealImage")
    private String mealImage;

    @SerializedName("strIngredient1")
    @ColumnInfo(name= "ingredient")
    private String ingredient;

    @SerializedName("strMeasure1")
    @ColumnInfo(name= "measure")
    private String measure;


    public Meals(@NonNull String id, String name, String category, String area, String instructions, String mealImage, String ingredient, String measure) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.mealImage = mealImage;
        this.ingredient = ingredient;
        this.measure = measure;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getMealImage() {
        return mealImage;
    }

    public void setMealImage(String mealImage) {
        this.mealImage = mealImage;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        return "Meals{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", area='" + area + '\'' +
                ", instructions='" + instructions + '\'' +
                ", mealImage='" + mealImage + '\'' +
                ", ingredient='" + ingredient + '\'' +
                ", measure='" + measure + '\'' +
                '}';
    }

}
