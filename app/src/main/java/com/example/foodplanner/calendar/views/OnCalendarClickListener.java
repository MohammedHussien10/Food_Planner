package com.example.foodplanner.calendar.views;

import com.example.foodplanner.models.CalendarPlan;
import com.example.foodplanner.models.Meals;

public interface OnCalendarClickListener {

    void removeMealFromCalenderPlan(CalendarPlan meal);
    void navigateToDetails(String mealId);
}
