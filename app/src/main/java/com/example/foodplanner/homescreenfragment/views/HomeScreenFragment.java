package com.example.foodplanner.homescreenfragment.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.databinding.FragmentHomeScreenBinding;
import com.example.foodplanner.db.MealsLocalDataSource;
import com.example.foodplanner.homescreenfragment.presenter.HomeContract;
import com.example.foodplanner.homescreenfragment.presenter.HomePresenter;
import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsRepository;
import com.example.foodplanner.models.MealsResponse;
import com.example.foodplanner.R;
import com.example.foodplanner.network.MealsService;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreenFragment extends Fragment implements HomeClickListener, HomeContract {
FragmentHomeScreenBinding binding;
HomeAdapter homeAdapter;
RecyclerView recyclerV_Meals;
HomePresenter homePresenter;

    public HomeScreenFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenter = new HomePresenter(this,
                MealsRepository.getInstance(MealsLocalDataSource
                        .getInstance(requireContext()), MealsRemoteDataSource.getInstance()));


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerV_Meals = view.findViewById(R.id.recyclerV_Meal);


        homePresenter.getRandomMeals();
//        getRandomMeal();

//        binding.signout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signOut();
//            }
//        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }





    @Override
    public void assignAdapter(List<Meals> mealsList) {
        recyclerV_Meals.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        homeAdapter = new HomeAdapter(getContext(),mealsList,this);
        recyclerV_Meals.setAdapter(homeAdapter);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addMealToHomeAdapter(Meals meal) {
       // homePresenter.addProductToFavorites(meal);
        showToast("The Day of The Meal");
    }


}