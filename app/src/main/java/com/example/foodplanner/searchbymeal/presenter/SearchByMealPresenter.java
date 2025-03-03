package com.example.foodplanner.searchbymeal.presenter;

import android.util.Log;

import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsRepository;
import com.example.foodplanner.models.RemoteMeals;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchByMealPresenter {
    SearchByMealContract searchByMealContract;
    MealsRepository repository;
    Disposable disposable;

    public SearchByMealPresenter(SearchByMealContract searchByMealContract, MealsRepository repository) {

        this.searchByMealContract = searchByMealContract;
        this.repository = repository;

    }


    public void getSelectedCategory(String categoryName) {
        disposable =   repository.getSelectedCategories(categoryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response != null && response.getMeals() != null && !response.getMeals().isEmpty()) {
//                    RemoteMeals meal = response.getMeals().get(0);
//                  searchByMealContract.SelectedMeal(meal);

                        searchByMealContract.assignSelectedMealToAdapter(response.getMeals());
                        Log.i("getMealDetailsByCategories is exist","Categories are "+response.getMeals().size());
                    } else {
                        searchByMealContract.showToast("Meal data is empty");
                    }
                }, throwable -> {
                    searchByMealContract.showToast("Error fetching meal: " + throwable.getMessage());
                });

    }

    public void getSelectedIngredient(String ingredientName) {
        disposable =   repository.getSelectedIngredient(ingredientName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response != null && response.getMeals() != null && !response.getMeals().isEmpty()) {
//                        RemoteMeals meal = response.getMeals().get(0);
//                        searchByMealContract.SelectedMeal(meal);

                        searchByMealContract.assignSelectedMealToAdapter(response.getMeals());
                    } else {
                        searchByMealContract.showToast("Meal data is empty");
                    }
                }, throwable -> {
                    searchByMealContract.showToast("Error fetching meal: " + throwable.getMessage());
                });
    }

    public void getSelectedArea(String areaName) {
        disposable = repository.getSelectedArea(areaName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response != null && response.getMeals() != null && !response.getMeals().isEmpty()) {
//                        RemoteMeals meal = response.getMeals().get(0);
//                        searchByMealContract.SelectedMeal(meal);
                        searchByMealContract.assignSelectedMealToAdapter(response.getMeals());
                    } else {
                        searchByMealContract.showToast("Meal data is empty");
                    }
                }, throwable -> {
                    searchByMealContract.showToast("Error fetching meal: " + throwable.getMessage());
                });
    }




}