package com.example.foodplanner.models;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.R;
import com.example.foodplanner.db.MealsDao;
import com.example.foodplanner.db.MealsLocalDataSource;
import com.example.foodplanner.network.CheckNetworking;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsRepository {

    MealsRemoteDataSource mealsRemoteDataSource;
    MealsLocalDataSource mealsLocalDataSource;
    private static MealsRepository repository = null;

    public MealsRepository(MealsRemoteDataSource mealsRemoteDataSource, MealsLocalDataSource mealsLocalDataSource) {
        this.mealsRemoteDataSource = mealsRemoteDataSource;
        this.mealsLocalDataSource = mealsLocalDataSource;

    }

    public static MealsRepository getInstance(
            MealsLocalDataSource localSource,
            MealsRemoteDataSource remoteSource) {
        if (repository == null) {
            repository = new MealsRepository(remoteSource, localSource);
        }
        return repository;
    }

    public Single<RemoteMeals> getDailyMeals(Context context) {
        if (CheckNetworking.isNetworkAvailable(context)) {
            return mealsRemoteDataSource.getDailyMeals();
        } else {
            return Single.error(new Throwable("No Internet Connection"));
        }

    }

    public Single<MealsResponse> getRandomMeals(Context context) {
        if (CheckNetworking.isNetworkAvailable(context)) {
            return mealsRemoteDataSource.getRandomMeals();
        } else {
            return Single.error(new Throwable("No Internet Connection"));
        }
    }

    public Single<MealsResponse> getSelectedMeal(String mealId, Context context) {
        if (CheckNetworking.isNetworkAvailable(context)) {
            return mealsRemoteDataSource.getSelectedMeal(mealId);
        } else {
            return Single.error(new Throwable("No Internet Connection"));
        }

    }

    public Single<CategoriesResponse> getCategory(Context context) {
        if (CheckNetworking.isNetworkAvailable(context)) {
            return mealsRemoteDataSource.getCategory();
        } else {
            return Single.error(new Throwable("No Internet Connection"));
        }
    }


    //getIngredientSearch screen
    public Single<IngredientsResponse> getIngredient(Context context) {
        if (CheckNetworking.isNetworkAvailable(context)) {
            return mealsRemoteDataSource.getIngredient();
        } else {
            return Single.error(new Throwable("No Internet Connection"));
        }

    }

    //getArea
    public Single<AreaResponse> getArea(Context context) {
        if (CheckNetworking.isNetworkAvailable(context)) {
            return mealsRemoteDataSource.getArea();
        } else {
            return Single.error(new Throwable("No Internet Connection"));
        }
    }


//    public List<Area> getAreas(){
//        List<Area> areas = new ArrayList<>();
//        areas.add(new Area("American", R.drawable.america)
//        return areas;
//    }

    public Flowable<List<Meals>> getFavoriteMeals() {
        return mealsLocalDataSource.getFavouritesMeals();
    }

    public Completable insertFavoriteMeals(Meals meal) {
        return mealsLocalDataSource.insertFavouritesMeals(meal);
    }

    public Completable deleteFavoriteMeals(Meals meal) {

        return mealsLocalDataSource.removeFavouritesMeals(meal);
    }


    public Flowable<List<CalendarPlan>> getCalendarPlanMeals(String date) {
        return mealsLocalDataSource.getCalendarPlanMeals(date)
                .doOnNext(meals -> Log.d("DB CHECK", "Fetched " + meals.size() + " meals from DB"))
                .doOnError(e -> Log.e("DB CHECK", "Error fetching meals", e));
    }


    public Completable addCalendarPlanMeals(CalendarPlan meal) {
        Log.d("DB INSERT", "Trying to insert meal: " + meal.getName() + " | Date: " + meal.getDate());
        return mealsLocalDataSource.addCalendarPlanMeals(meal).doOnComplete(() -> Log.d("DB INSERT", "Meal inserted successfully"))
                .doOnError(e -> Log.e("DB INSERT", "Error inserting meal", e));

    }

    public Completable deleteCalendarPlanMeals(CalendarPlan meal) {

        return mealsLocalDataSource.deleteCalendarPlanMeals(meal);
    }


    public Single<MealsResponse> getSelectedCategories(String categoryName, Context context) {
        if (CheckNetworking.isNetworkAvailable(context)) {
            return mealsRemoteDataSource.getSelectedCategories(categoryName);
        } else {
            return Single.error(new Throwable("No Internet Connection"));
        }
    }

    public Single<MealsResponse> getSelectedArea(String areaName, Context context) {
        if (CheckNetworking.isNetworkAvailable(context)) {
            return mealsRemoteDataSource.getSelectedArea(areaName);
        } else {
            return Single.error(new Throwable("No Internet Connection"));
        }
    }


    public Single<MealsResponse> getSelectedIngredient(String ingredientName, Context context) {
        if (CheckNetworking.isNetworkAvailable(context)) {
            return mealsRemoteDataSource.getSelectedIngredient(ingredientName);
        } else {
            return Single.error(new Throwable("No Internet Connection"));
        }
    }
    public void backupCalendarDataToFirestore(String userId) {
        getAllCalendarPlanMeals() // ✅ اجلب كل البيانات بدون فلترة بالتاريخ
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .firstOrError()
                .subscribe(
                        meals -> {
                            if (meals.isEmpty()) {
                                Log.e("Firestore Debug", "❌ No calendar meals found in local DB!");
                                return;
                            }

                            Log.d("Firestore Debug", "✅ Meals count in local DB before backup: " + meals.size());

                            for (CalendarPlan meal : meals) {
                                Log.d("Firestore Debug", "🔹 Meal: " + meal.getName() + " | Date: " + meal.getDate());
                            }

                            String calendarJson = new Gson().toJson(meals);
                            Log.d("Firestore Debug", "📌 Converted calendar JSON: " + calendarJson);

                            FirebaseFirestore.getInstance()
                                    .collection("users")
                                    .document(userId)
                                    .set(Collections.singletonMap("calendar", calendarJson), SetOptions.merge()) // ✅ يستخدم set() مع merge()
                                    .addOnSuccessListener(aVoid -> Log.d("Firestore Debug", "✅ Calendar data successfully backed up to Firestore"))
                                    .addOnFailureListener(e -> Log.e("Firestore Debug", "❌ Error backing up calendar data", e));
                        },
                        throwable -> Log.e("Firestore Debug", "❌ Error fetching calendar meals", throwable)
                );
    }

    public Flowable<List<CalendarPlan>> getAllCalendarPlanMeals() {
        return mealsLocalDataSource.getAllCalendarMeals();
    }

    public void restoreDataFromFirestore(String userId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (!documentSnapshot.exists()) {
                        Log.e("Firestore", "❌ No document found for user: " + userId);
                        return;
                    }

                    String favJson = documentSnapshot.getString("favorites");
                    String calendarJson = documentSnapshot.getString("calendar");

                    Gson gson = new Gson();
                    Type mealListType = new TypeToken<List<Meals>>() {}.getType();
                    Type calendarListType = new TypeToken<List<CalendarPlan>>() {}.getType();

                    List<Meals> favoriteMeals = gson.fromJson(favJson, mealListType);
                    List<CalendarPlan> calendarMeals = gson.fromJson(calendarJson, calendarListType);

                    Log.d("Firestore", "✅ Restored " + (favoriteMeals != null ? favoriteMeals.size() : 0) + " favorites and "
                            + (calendarMeals != null ? calendarMeals.size() : 0) + " calendar meals");

                    Completable.mergeArray(
                            insertFavoriteMeals(favoriteMeals),
                            insertCalendarMeals(calendarMeals)
                    ).subscribe(
                            () -> Log.d("Firestore", "✅ Restore completed"),
                            throwable -> Log.e("Firestore", "❌ Restore failed", throwable)
                    );
                })
                .addOnFailureListener(e -> Log.e("Firestore", "❌ Restore failed", e));
    }

    public void checkCalendarMeals() {
        getAllCalendarPlanMeals() // ✅ استخدام الدالة الجديدة
                .firstOrError()
                .subscribe(
                        meals -> {
                            Log.d("DB CHECK", "Total Calendar Meals: " + meals.size());
                            for (CalendarPlan meal : meals) {
                                Log.d("DB CHECK", "Meal: " + meal.getName() + " | Date: " + meal.getDate());
                            }
                        },
                        throwable -> Log.e("DB CHECK", "Error checking calendar meals", throwable)
                );
    }


    // دالة لإدراج المفضلات
    private Completable insertFavoriteMeals(List<Meals> meals) {
        return Completable.fromAction(() -> {
            for (Meals meal : meals) {
                insertFavoriteMeals(meal).blockingAwait(); // تنفيذ الإدراج واحدًا تلو الآخر
            }
        });
    }

    // دالة لإدراج الوجبات المخططة في التقويم
    private Completable insertCalendarMeals(List<CalendarPlan> plans) {
        return Completable.fromAction(() -> {
            for (CalendarPlan plan : plans) {
                addCalendarPlanMeals(plan).blockingAwait(); // تنفيذ الإدراج واحدًا تلو الآخر
            }
        });
    }


}
