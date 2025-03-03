package com.example.foodplanner.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "calendarplan")
public class CalendarPlan {
    @SerializedName("idMeal")
    @PrimaryKey
    @NonNull
    private String id;

    @SerializedName("strMeal")
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("strCategory")
    @ColumnInfo(name = "category")
    private String category;

    @SerializedName("strArea")
    @ColumnInfo(name = "area")
    private String area;

    @SerializedName("strInstructions")
    @ColumnInfo(name = "instructions")
    private String instructions;

    @SerializedName("strMealThumb")
    @ColumnInfo(name = "mealImage")
    private String mealImage;


    @SerializedName("strYoutube")
    @ColumnInfo(name = "youtubeLink")
    private String youtubeLink;

    @SerializedName("strIngredient1")
    @ColumnInfo(name = "ingredient1")
    private String ingredient1;

    @SerializedName("strIngredient2")
    @ColumnInfo(name = "ingredient2")
    private String ingredient2;

    @SerializedName("strIngredient3")
    @ColumnInfo(name = "ingredient3")
    private String ingredient3;

    @SerializedName("strIngredient4")
    @ColumnInfo(name = "ingredient4")
    private String ingredient4;

    @SerializedName("strIngredient5")
    @ColumnInfo(name = "ingredient5")
    private String ingredient5;

    @SerializedName("strIngredient6")
    @ColumnInfo(name = "ingredient6")
    private String ingredient6;

    @SerializedName("strIngredient7")
    @ColumnInfo(name = "ingredient7")
    private String ingredient7;

    @SerializedName("strIngredient8")
    @ColumnInfo(name = "ingredient8")
    private String ingredient8;

    @SerializedName("strIngredient9")
    @ColumnInfo(name = "ingredient9")
    private String ingredient9;

    @SerializedName("strIngredient10")
    @ColumnInfo(name = "ingredient10")
    private String ingredient10;

    @SerializedName("strIngredient11")
    @ColumnInfo(name = "ingredient11")
    private String ingredient11;

    @SerializedName("strIngredient12")
    @ColumnInfo(name = "ingredient12")
    private String ingredient12;

    @SerializedName("strIngredient13")
    @ColumnInfo(name = "ingredient13")
    private String ingredient13;

    @SerializedName("strIngredient14")
    @ColumnInfo(name = "ingredient14")
    private String ingredient14;

    @SerializedName("strIngredient15")
    @ColumnInfo(name = "ingredient15")
    private String ingredient15;

    @SerializedName("strIngredient16")
    @ColumnInfo(name = "ingredient16")
    private String ingredient16;

    @SerializedName("strIngredient17")
    @ColumnInfo(name = "ingredient17")
    private String ingredient17;

    @SerializedName("strIngredient18")
    @ColumnInfo(name = "ingredient18")
    private String ingredient18;

    @SerializedName("strIngredient19")
    @ColumnInfo(name = "ingredient19")
    private String ingredient19;

    @SerializedName("strIngredient20")
    @ColumnInfo(name = "ingredient20")
    private String ingredient20;

    @SerializedName("strMeasure1")
    @ColumnInfo(name = "measure1")
    private String measure1;

    @SerializedName("strMeasure2")
    @ColumnInfo(name = "measure2")
    private String measure2;

    @SerializedName("strMeasure3")
    @ColumnInfo(name = "measure3")
    private String measure3;

    @SerializedName("strMeasure4")
    @ColumnInfo(name = "measure4")
    private String measure4;

    @SerializedName("strMeasure5")
    @ColumnInfo(name = "measure5")
    private String measure5;

    @SerializedName("strMeasure6")
    @ColumnInfo(name = "measure6")
    private String measure6;

    @SerializedName("strMeasure7")
    @ColumnInfo(name = "measure7")
    private String measure7;

    @SerializedName("strMeasure8")
    @ColumnInfo(name = "measure8")
    private String measure8;

    @SerializedName("strMeasure9")
    @ColumnInfo(name = "measure9")
    private String measure9;

    @SerializedName("strMeasure10")
    @ColumnInfo(name = "measure10")
    private String measure10;

    @SerializedName("strMeasure11")
    @ColumnInfo(name = "measure11")
    private String measure11;

    @SerializedName("strMeasure12")
    @ColumnInfo(name = "measure12")
    private String measure12;

    @SerializedName("strMeasure13")
    @ColumnInfo(name = "measure13")
    private String measure13;

    @SerializedName("strMeasure14")
    @ColumnInfo(name = "measure14")
    private String measure14;

    @SerializedName("strMeasure15")
    @ColumnInfo(name = "measure15")
    private String measure15;

    @SerializedName("strMeasure16")
    @ColumnInfo(name = "measure16")
    private String measure16;

    @SerializedName("strMeasure17")
    @ColumnInfo(name = "measure17")
    private String measure17;

    @SerializedName("strMeasure18")
    @ColumnInfo(name = "measure18")
    private String measure18;

    @SerializedName("strMeasure19")
    @ColumnInfo(name = "measure19")
    private String measure19;

    @SerializedName("strMeasure20")
    @ColumnInfo(name = "measure20")
    private String measure20;
    private String date;


    public CalendarPlan(Meals meal,String date){
        this.id = meal.getId();
        this.name = meal.getName();
        this.category = meal.getCategory();
        this.area = meal.getCategory();
        this.instructions = meal.getInstructions();
        this.mealImage =  meal.getMealImage();
        this.youtubeLink =  meal.getYoutubeLink();
        this.ingredient1 = meal.getIngredient1();
        this.ingredient2 = meal.getIngredient2();
        this.ingredient3 = meal.getIngredient3();
        this.ingredient4 = meal.getIngredient4();
        this.ingredient5 = meal.getIngredient5();
        this.ingredient6 = meal.getIngredient6();
        this.ingredient7 = meal.getIngredient7();
        this.ingredient8 = meal.getIngredient8();
        this.ingredient9 = meal.getIngredient9();
        this.ingredient10 = meal.getIngredient10();
        this.ingredient11 = meal.getIngredient11();
        this.ingredient12 = meal.getIngredient12();
        this.ingredient13 = meal.getIngredient13();
        this.ingredient14 = meal.getIngredient14();
        this.ingredient15 = meal.getIngredient15();
        this.ingredient16 = meal.getIngredient16();
        this.ingredient17 = meal.getIngredient17();
        this.ingredient18 = meal.getIngredient18();
        this.ingredient19 = meal.getIngredient19();
        this.ingredient20 = meal.getIngredient20();
        this.measure1 = meal.getMeasure1();
        this.measure2 = meal.getMeasure2();
        this.measure3 = meal.getMeasure3();
        this.measure4 = meal.getMeasure4();
        this.measure5 = meal.getMeasure5();
        this.measure6 = meal.getMeasure6();
        this.measure7 = meal.getMeasure7();
        this.measure8 = meal.getMeasure8();
        this.measure9 = meal.getMeasure9();
        this.measure10 = meal.getMeasure10();
        this.measure11 = meal.getMeasure11();
        this.measure12 = meal.getMeasure12();
        this.measure13 = meal.getMeasure13();
        this.measure14 = meal.getMeasure14();
        this.measure15 = meal.getMeasure15();
        this.measure16 = meal.getMeasure16();
        this.measure17 = meal.getMeasure17();
        this.measure18 = meal.getMeasure18();
        this.measure19 = meal.getMeasure19();
        this.measure20 = meal.getMeasure20();
        this.date = date;
    }
    public CalendarPlan(@NonNull String id, String name, String category, String area, String instructions, String mealImage, String youtubeLink, String ingredient1, String ingredient2, String ingredient3, String ingredient4, String ingredient5, String ingredient6, String ingredient7, String ingredient8, String ingredient9, String ingredient10, String ingredient11, String ingredient12, String ingredient13, String ingredient14, String ingredient15, String ingredient16, String ingredient17, String ingredient18, String ingredient19, String ingredient20, String measure1, String measure2, String measure3, String measure4, String measure5, String measure6, String measure7, String measure8, String measure9, String measure10, String measure11, String measure12, String measure13, String measure14, String measure15, String measure16, String measure17, String measure18, String measure19, String measure20, String date) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.mealImage = mealImage;
        this.youtubeLink = youtubeLink;
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
        this.ingredient3 = ingredient3;
        this.ingredient4 = ingredient4;
        this.ingredient5 = ingredient5;
        this.ingredient6 = ingredient6;
        this.ingredient7 = ingredient7;
        this.ingredient8 = ingredient8;
        this.ingredient9 = ingredient9;
        this.ingredient10 = ingredient10;
        this.ingredient11 = ingredient11;
        this.ingredient12 = ingredient12;
        this.ingredient13 = ingredient13;
        this.ingredient14 = ingredient14;
        this.ingredient15 = ingredient15;
        this.ingredient16 = ingredient16;
        this.ingredient17 = ingredient17;
        this.ingredient18 = ingredient18;
        this.ingredient19 = ingredient19;
        this.ingredient20 = ingredient20;
        this.measure1 = measure1;
        this.measure2 = measure2;
        this.measure3 = measure3;
        this.measure4 = measure4;
        this.measure5 = measure5;
        this.measure6 = measure6;
        this.measure7 = measure7;
        this.measure8 = measure8;
        this.measure9 = measure9;
        this.measure10 = measure10;
        this.measure11 = measure11;
        this.measure12 = measure12;
        this.measure13 = measure13;
        this.measure14 = measure14;
        this.measure15 = measure15;
        this.measure16 = measure16;
        this.measure17 = measure17;
        this.measure18 = measure18;
        this.measure19 = measure19;
        this.measure20 = measure20;
        this.date = date;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIngredient1() {
        return ingredient1;
    }

    public void setIngredient1(String ingredient1) {
        this.ingredient1 = ingredient1;
    }

    public String getIngredient2() {
        return ingredient2;
    }

    public void setIngredient2(String ingredient2) {
        this.ingredient2 = ingredient2;
    }

    public String getIngredient3() {
        return ingredient3;
    }

    public void setIngredient3(String ingredient3) {
        this.ingredient3 = ingredient3;
    }

    public String getIngredient4() {
        return ingredient4;
    }

    public void setIngredient4(String ingredient4) {
        this.ingredient4 = ingredient4;
    }

    public String getIngredient5() {
        return ingredient5;
    }

    public void setIngredient5(String ingredient5) {
        this.ingredient5 = ingredient5;
    }

    public String getIngredient6() {
        return ingredient6;
    }

    public void setIngredient6(String ingredient6) {
        this.ingredient6 = ingredient6;
    }

    public String getIngredient7() {
        return ingredient7;
    }

    public void setIngredient7(String ingredient7) {
        this.ingredient7 = ingredient7;
    }

    public String getIngredient8() {
        return ingredient8;
    }

    public void setIngredient8(String ingredient8) {
        this.ingredient8 = ingredient8;
    }

    public String getIngredient9() {
        return ingredient9;
    }

    public void setIngredient9(String ingredient9) {
        this.ingredient9 = ingredient9;
    }

    public String getIngredient10() {
        return ingredient10;
    }

    public void setIngredient10(String ingredient10) {
        this.ingredient10 = ingredient10;
    }

    public String getIngredient11() {
        return ingredient11;
    }

    public void setIngredient11(String ingredient11) {
        this.ingredient11 = ingredient11;
    }

    public String getIngredient12() {
        return ingredient12;
    }

    public void setIngredient12(String ingredient12) {
        this.ingredient12 = ingredient12;
    }

    public String getIngredient13() {
        return ingredient13;
    }

    public void setIngredient13(String ingredient13) {
        this.ingredient13 = ingredient13;
    }

    public String getIngredient14() {
        return ingredient14;
    }

    public void setIngredient14(String ingredient14) {
        this.ingredient14 = ingredient14;
    }

    public String getIngredient15() {
        return ingredient15;
    }

    public void setIngredient15(String ingredient15) {
        this.ingredient15 = ingredient15;
    }

    public String getIngredient16() {
        return ingredient16;
    }

    public void setIngredient16(String ingredient16) {
        this.ingredient16 = ingredient16;
    }

    public String getIngredient17() {
        return ingredient17;
    }

    public void setIngredient17(String ingredient17) {
        this.ingredient17 = ingredient17;
    }

    public String getIngredient18() {
        return ingredient18;
    }

    public void setIngredient18(String ingredient18) {
        this.ingredient18 = ingredient18;
    }

    public String getIngredient19() {
        return ingredient19;
    }

    public void setIngredient19(String ingredient19) {
        this.ingredient19 = ingredient19;
    }

    public String getIngredient20() {
        return ingredient20;
    }

    public void setIngredient20(String ingredient20) {
        this.ingredient20 = ingredient20;
    }

    public String getMeasure2() {
        return measure2;
    }

    public void setMeasure2(String measure2) {
        this.measure2 = measure2;
    }

    public String getMeasure3() {
        return measure3;
    }

    public void setMeasure3(String measure3) {
        this.measure3 = measure3;
    }

    public String getMeasure4() {
        return measure4;
    }

    public void setMeasure4(String measure4) {
        this.measure4 = measure4;
    }

    public String getMeasure5() {
        return measure5;
    }

    public void setMeasure5(String measure5) {
        this.measure5 = measure5;
    }

    public String getMeasure6() {
        return measure6;
    }

    public void setMeasure6(String measure6) {
        this.measure6 = measure6;
    }

    public String getMeasure7() {
        return measure7;
    }

    public void setMeasure7(String measure7) {
        this.measure7 = measure7;
    }

    public String getMeasure8() {
        return measure8;
    }

    public void setMeasure8(String measure8) {
        this.measure8 = measure8;
    }

    public String getMeasure9() {
        return measure9;
    }

    public void setMeasure9(String measure9) {
        this.measure9 = measure9;
    }

    public String getMeasure10() {
        return measure10;
    }

    public void setMeasure10(String measure10) {
        this.measure10 = measure10;
    }

    public String getMeasure11() {
        return measure11;
    }

    public void setMeasure11(String measure11) {
        this.measure11 = measure11;
    }

    public String getMeasure12() {
        return measure12;
    }

    public void setMeasure12(String measure12) {
        this.measure12 = measure12;
    }

    public String getMeasure13() {
        return measure13;
    }

    public void setMeasure13(String measure13) {
        this.measure13 = measure13;
    }

    public String getMeasure14() {
        return measure14;
    }

    public void setMeasure14(String measure14) {
        this.measure14 = measure14;
    }

    public String getMeasure15() {
        return measure15;
    }

    public void setMeasure15(String measure15) {
        this.measure15 = measure15;
    }

    public String getMeasure16() {
        return measure16;
    }

    public void setMeasure16(String measure16) {
        this.measure16 = measure16;
    }

    public String getMeasure17() {
        return measure17;
    }

    public void setMeasure17(String measure17) {
        this.measure17 = measure17;
    }

    public String getMeasure18() {
        return measure18;
    }

    public void setMeasure18(String measure18) {
        this.measure18 = measure18;
    }

    public String getMeasure19() {
        return measure19;
    }

    public void setMeasure19(String measure19) {
        this.measure19 = measure19;
    }

    public String getMeasure20() {
        return measure20;
    }

    public void setMeasure20(String measure20) {
        this.measure20 = measure20;
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


    public String getMeasure1() {
        return measure1;
    }

    public void setMeasure1(String measure1) {
        this.measure1 = measure1;
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


    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }


}
