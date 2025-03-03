package com.example.foodplanner.models;

public class Area {
    private String strArea;
    private int imgArea;

    public Area(String strArea,int imgArea) {
        this.strArea = strArea;
        this.imgArea = imgArea;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public int getImgArea() {
        return imgArea;
    }

    public void setImgArea(int imgArea) {
        this.imgArea = imgArea;
    }
}
