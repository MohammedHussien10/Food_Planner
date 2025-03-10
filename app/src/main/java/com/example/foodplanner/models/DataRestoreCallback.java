package com.example.foodplanner.models;

import java.util.List;

public interface DataRestoreCallback {
    void onDataRestored(List<Meals> favoriteMeals, List<CalendarPlan> calendarMeals);
}
