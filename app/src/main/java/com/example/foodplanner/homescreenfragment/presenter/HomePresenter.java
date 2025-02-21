package com.example.foodplanner.homescreenfragment.presenter;

import android.util.Log;

import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsRepository;
import com.example.foodplanner.models.MealsResponse;
import com.example.foodplanner.network.MealsRemoteDataSource;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter {
    HomeContract homeContract;
    MealsRemoteDataSource mealsRemoteDataSource;
    MealsRepository repository;


    public HomePresenter(HomeContract homeContract, MealsRepository repository) {

        this.homeContract = homeContract;
        this.repository = repository;

    }


    public void showDailyMeals() {
        repository.getDailyMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meal -> {
                    if (meal != null) {
                        homeContract.showDailyMeals(meal);
                    } else {
                        homeContract.showToast("Meal data is empty");
                    }
                }, throwable -> {
                    homeContract.showToast("Error fetching meal: " + throwable.getMessage());
                });
    }

    public void getRandomMeals() {
        repository.getRandomMeals()
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





//    public void get_Categories() {
//        repository.getCategories()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(categoriesResponse -> {
//
//                    homeContract.assignCategoriesAdapter(categoriesResponse.getCategories());
//
//                }, throwable -> {
//                    Log.e("Error", throwable.getMessage());
//                });
//    }

//    public void addProductToFavorites(Meals meal) {
//        Meals entry = new Meals(
//                meal.getId(),
//                meal.getName(),
//                meal.getCategory(),
//                meal.getArea(),
//                meal.getInstructions(),
//                meal.getIngredient(), meal.getMeasure(), meal.getMealImage()
//        );
//    }
}