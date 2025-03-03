package com.example.foodplanner.DetailsFragment.presenter;

import android.util.Log;

import com.example.foodplanner.models.CalendarPlan;
import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsRepository;
import com.example.foodplanner.models.MealsResponse;
import com.example.foodplanner.models.RemoteMeals;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsPresenter {
    DetailsContract detailsContract;
    MealsRepository repository;
    public Meals meal;
    public CalendarPlan mealCalendarPlan;
    Disposable disposable;

    public DetailsPresenter(DetailsContract detailsContract, MealsRepository repository) {

        this.detailsContract = detailsContract;
        this.repository = repository;

    }


    public void getSelectedMeal(String mealId) {
        disposable =  repository.getSelectedMeal(mealId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response != null && response.getMeals() != null && !response.getMeals().isEmpty()) {
                        meal = new Meals(response.getMeals().get(0));
                        RemoteMeals meal = response.getMeals().get(0);
                        detailsContract.SelectedMeal(meal);
                        List<String> ingredients = extractIngredients(meal);
                        detailsContract.assignSelectedMealIngredientsToAdapter(ingredients);
                    } else {
                        detailsContract.showToast("Meal data is empty");
                    }
                }, throwable -> {
                    detailsContract.showToast("Error fetching meal: " + throwable.getMessage());
                });

    }


    private List<String> extractIngredients(RemoteMeals meal) {
        List<String> ingredientsList = new ArrayList<>();

        addIfNotEmpty(ingredientsList, meal.getIngredient1());
        addIfNotEmpty(ingredientsList, meal.getIngredient2());
        addIfNotEmpty(ingredientsList, meal.getIngredient3());
        addIfNotEmpty(ingredientsList, meal.getIngredient4());
        addIfNotEmpty(ingredientsList, meal.getIngredient5());
        addIfNotEmpty(ingredientsList, meal.getIngredient6());
        addIfNotEmpty(ingredientsList, meal.getIngredient7());
        addIfNotEmpty(ingredientsList, meal.getIngredient8());
        addIfNotEmpty(ingredientsList, meal.getIngredient9());
        addIfNotEmpty(ingredientsList, meal.getIngredient10());
        addIfNotEmpty(ingredientsList, meal.getIngredient11());
        addIfNotEmpty(ingredientsList, meal.getIngredient12());
        addIfNotEmpty(ingredientsList, meal.getIngredient13());
        addIfNotEmpty(ingredientsList, meal.getIngredient14());
        addIfNotEmpty(ingredientsList, meal.getIngredient15());
        addIfNotEmpty(ingredientsList, meal.getIngredient16());
        addIfNotEmpty(ingredientsList, meal.getIngredient17());
        addIfNotEmpty(ingredientsList, meal.getIngredient18());
        addIfNotEmpty(ingredientsList, meal.getIngredient19());
        addIfNotEmpty(ingredientsList, meal.getIngredient20());

        return ingredientsList;
    }

    private void addIfNotEmpty(List<String> list, String ingredient) {
        if (ingredient != null && !ingredient.trim().isEmpty()) {
            list.add(ingredient);
        }
    }

    public void addMealToFavorites(Meals meal) {

        disposable =  repository.insertFavoriteMeals(meal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Log.i("test", "test");
                });
    }

    public void addMealToCalendar(String date) {
         CalendarPlan mealCalendarPlan = new CalendarPlan(meal,date);
      repository.addCalendarPlanMeals(mealCalendarPlan).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
            Log.i("testpresenter", "meal" + meal);
        });


    }


}