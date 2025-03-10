package com.example.foodplanner.calendar.presenter;

import android.util.Log;

import com.example.foodplanner.models.CalendarPlan;
import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalendarPresenter {
    FirebaseFirestore firebaseFirestore;
    FirebaseUser user;
    public MealsRepository repository;
    public Observable<List<Meals>> favoriteList;
    public CalendarContract contract;
    Disposable disposable;

    public CalendarPresenter(MealsRepository repository , CalendarContract contract){
        this.repository = repository;
        this.contract = contract;
        firebaseFirestore =  FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void getCalendarPlanMeals(String date){
        Log.d("PRESENTER", "Fetching meals for date: " + date);
        disposable = repository.getCalendarPlanMeals(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            contract.getCalendarPlanMeals(meals);
                            Log.i("PRESENTER", "Fetched " + meals.size() + " meals from DB");
                        },
                        error -> Log.e("PRESENTER", "Error fetching meals", error)
                );
    }

    public void removeCalendarPlanMeals(CalendarPlan meal){
        disposable =   repository.deleteCalendarPlanMeals(meal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{

                });
    }

    public void backupUserData(String userId) {
        repository.backupCalendarDataToFirestore(userId);
    }

    public void restoreUserData(String userId) {
        repository.restoreDataFromFirestore(userId);
    }


}
