package com.example.foodplanner.SearchSelectbyFragment.presenter;

import com.example.foodplanner.models.MealsRepository;
import com.example.foodplanner.models.RemoteMeals;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchSelectbyPresenter {
    SearchSelectbyContract searchSelectbyContract;
    MealsRepository repository;

    Disposable disposable;
    public SearchSelectbyPresenter(SearchSelectbyContract searchSelectbyContract, MealsRepository repository) {

        this.searchSelectbyContract = searchSelectbyContract;
        this.repository = repository;

    }


    public void getCategory() {
        disposable=   repository.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(category -> {
                    if (category != null) {
                       searchSelectbyContract.assignCategoryAdapter(category.getCategories());
                    } else {
                        searchSelectbyContract.showToast("Meal data is empty");
                    }
                }, throwable -> {
                    searchSelectbyContract.showToast("Error fetching meal: " + throwable.getMessage());
                });
    }

    public void getIngredient() {
        disposable =    repository.getIngredient()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ingredient -> {
                    if (ingredient != null) {

                        searchSelectbyContract.assignIngredientAdapter(ingredient.getIngredients());
                    } else {
                        searchSelectbyContract.showToast("Meal data is empty");
                    }
                }, throwable -> {
                    searchSelectbyContract.showToast("Error fetching meal: " + throwable.getMessage());
                });
    }

    public void getArea() {
        repository.getArea()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(area -> {
                    if (area != null) {

                        searchSelectbyContract.assignAreaAdapter(area.getArea());
                    } else {
                        searchSelectbyContract.showToast("Meal data is empty");
                    }
                }, throwable -> {
                    searchSelectbyContract.showToast("Error fetching meal: " + throwable.getMessage());
                });
    }




}