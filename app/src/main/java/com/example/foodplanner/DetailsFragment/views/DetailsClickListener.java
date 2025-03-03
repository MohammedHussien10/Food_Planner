package com.example.foodplanner.DetailsFragment.views;

import com.example.foodplanner.models.CalendarPlan;
import com.example.foodplanner.models.Meals;

public interface DetailsClickListener {
    void addMealToFavourite(Meals meal);
    void addMealToCalendar();
}
