package com.example.foodplanner.calendar.presenter;

import android.util.Log;

import com.example.foodplanner.models.CalendarPlan;
import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalendarPresenter {
    public MealsRepository repository;
    public Observable<List<Meals>> favoriteList;
    public CalendarContract contract;
    Disposable disposable;

    public CalendarPresenter(MealsRepository repository , CalendarContract contract){
        this.repository = repository;
        this.contract = contract;
    }

    public void getCalendarPlanMeals(String date){
        disposable = repository.getCalendarPlanMeals(date).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                meals -> {contract.getCalendarPlanMeals(meals);
                    Log.i("test","good" +meals.size());
                }

        );
    }

    public void removeCalendarPlanMeals(CalendarPlan meal){
        disposable =   repository.deleteCalendarPlanMeals(meal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{

                });
    }


}
