package com.example.foodplanner.homescreenfragment.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanner.databinding.FragmentHomeScreenBinding;
import com.example.foodplanner.db.MealsLocalDataSource;
import com.example.foodplanner.homescreenfragment.presenter.HomeContract;
import com.example.foodplanner.homescreenfragment.presenter.HomePresenter;
import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsRepository;
import com.example.foodplanner.R;
import com.example.foodplanner.models.RemoteMeals;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenFragment extends Fragment implements HomeClickListener, HomeContract {
FragmentHomeScreenBinding binding;
HomeRandomMealsAdapter homeRandomMealsAdapter;
RecyclerView recyclerV_Meals;
HomePresenter homePresenter;
Meals meal;

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

        homeRandomMealsAdapter = new HomeRandomMealsAdapter(getContext(),this);
        recyclerV_Meals.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerV_Meals.setAdapter(homeRandomMealsAdapter);
        homePresenter.showDailyMeals();
        homePresenter.getRandomMeals();
//        getRandomMeal();




    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }





    @Override
    public void assignRandomMealsAdapter(List<RemoteMeals> mealsList) {
        homeRandomMealsAdapter.setMealsList(mealsList);
        homeRandomMealsAdapter.notifyDataSetChanged();

    }

    @Override
    public void showDailyMeals(RemoteMeals meal) {
        Glide.with(this).load(meal.getMealImage()).into(binding.mealDailyImage);
        binding.mealDailyName.setText(meal.getName());
        binding.mealDailyArea.setText(meal.getArea());
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

    @Override
    public void navigateToDetails(String mealId) {

        Bundle bundle = new Bundle();
        bundle.putString("mealId", mealId);
        Navigation.findNavController(requireView()).navigate(R.id.action_homeScreenFragment_to_detailsScreenFragment, bundle);
    }


}