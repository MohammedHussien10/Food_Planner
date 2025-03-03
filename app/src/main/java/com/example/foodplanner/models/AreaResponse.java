package com.example.foodplanner.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AreaResponse {
    @SerializedName("meals")
    private List<Area> Area;

    public AreaResponse(List<Area> area) {
        Area = area;
    }

    public List<Area> getArea() {
        return Area;
    }

    public void setArea(List<Area> area) {
        Area = area;
    }


}