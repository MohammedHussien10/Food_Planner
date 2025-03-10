package com.example.foodplanner.favorites.presenter;

import android.util.Log;

import com.example.foodplanner.models.CalendarPlan;
import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritesPresenter {
    public MealsRepository repository;
    public Observable<List<Meals>> favoriteList;
    public FavoritesContract contract;

    Disposable disposable;

    public FavoritesPresenter(MealsRepository repository, FavoritesContract contract) {
        this.repository = repository;
        this.contract = contract;
    }

    public void getFavoritesMeals() {
        disposable = repository.getFavoriteMeals().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                mealsList -> {
                    contract.getFavoriteMeals(mealsList);
                }
        );
    }

    public void removeFavoritesMeals(Meals meal) {
        disposable = repository.deleteFavoriteMeals(meal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Log.i("test", "good");

                });
    }

    public void backupUserData(String userId) {
        repository.backupCalendarDataToFirestore(userId);
    }

    public void restoreUserData(String userId) {
        repository.restoreDataFromFirestore(userId);
    }




}
