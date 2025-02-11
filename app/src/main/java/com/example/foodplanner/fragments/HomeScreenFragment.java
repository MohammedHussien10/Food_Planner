package com.example.foodplanner.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.adapters.RecyclerViewAdapter;
import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsResponse;
import com.example.foodplanner.R;
import com.example.foodplanner.services.MealService;
import com.example.foodplanner.connection.RetrofitConnection;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreenFragment extends Fragment {
List<Meals> listOfMeals;
RecyclerViewAdapter recyclerViewAdapter;
RecyclerView recyclerV_Meals;

    public HomeScreenFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerV_Meals = view.findViewById(R.id.recyclerV_Meal);
        getRandomMeal();


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void getRandomMeal(){
        MealService mealService = RetrofitConnection.getRetrofitConnection().create(MealService.class);
        Call<MealsResponse> call = mealService.getRandomMeals();
        call.enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    listOfMeals = response.body().getMeals();
                    recyclerViewAdapter = new RecyclerViewAdapter(getContext(),listOfMeals);
                    recyclerV_Meals.setAdapter(recyclerViewAdapter);
                    recyclerV_Meals.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable throwable) {
                throwable.printStackTrace();

            }
        });
    }
}