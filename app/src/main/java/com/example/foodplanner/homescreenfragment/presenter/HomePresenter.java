package com.example.foodplanner.homescreenfragment.presenter;

import android.util.Log;

import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsRepository;
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


    public void getRandomMeals() {
        repository.getAllRandomMeals()
                .subscribeOn(Schedulers.io())  // تشغيل العملية في Background Thread
                .observeOn(AndroidSchedulers.mainThread())  // 🔴 تحويل التحديثات إلى Main Thread
                .subscribe(mealsResponse -> {

                    homeContract.assignAdapter(mealsResponse.getMeals());  // ✅ تحديث RecyclerView في Main Thread

                }, throwable -> {
                    Log.e("Error", throwable.getMessage());
                });
    }

    public void addProductToFavorites(Meals meal) {
        Meals entry = new Meals(
                meal.getId(),
                meal.getName(),
                meal.getCategory(),
                meal.getArea(),
                meal.getInstructions(),
                meal.getIngredient(), meal.getMeasure(), meal.getMealImage()
        );
    }
}