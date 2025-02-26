package com.example.foodplanner.homescreenfragment.presenter;

import android.util.Log;

import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsRepository;
import com.example.foodplanner.models.MealsResponse;
import com.example.foodplanner.models.RemoteMeals;
import com.example.foodplanner.network.MealsRemoteDataSource;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter {
    HomeContract homeContract;
    MealsRepository repository;
    private RemoteMeals randomMeal;
    Disposable disposable;


    public HomePresenter(HomeContract homeContract, MealsRepository repository) {

        this.homeContract = homeContract;
        this.repository = repository;

    }


    public void showDailyMeals() {
        disposable =     repository.getDailyMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meal -> {
                    if (meal != null) {
                        randomMeal = meal;
                        homeContract.showDailyMeals(meal);
                    } else {
                        homeContract.showToast("Meal data is empty");
                    }
                }, throwable -> {
                    homeContract.showToast("Error fetching meal: " + throwable.getMessage());
                });
    }

    public void getRandomMeals() {
        disposable =       repository.getRandomMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meal -> {
                    if (meal != null) {
                        meal.getMeals().remove(0);
                        homeContract.assignRandomMealsAdapter(meal.getMeals());
                    } else {
                        homeContract.showToast("Meal data is empty");
                    }
                }, throwable -> {
                    homeContract.showToast("Error fetching meal: " + throwable.getMessage());
                });
    }

    public RemoteMeals getDailyRandomMeal() {
        return randomMeal;
    }




}