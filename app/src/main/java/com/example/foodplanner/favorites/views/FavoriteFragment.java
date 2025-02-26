package com.example.foodplanner.favorites.views;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.db.MealsLocalDataSource;
import com.example.foodplanner.favorites.presenter.FavoritesContract;
import com.example.foodplanner.favorites.presenter.FavoritesPresenter;
import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsRepository;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.List;

public class FavoriteFragment extends Fragment implements OnFavoriteClickListener , FavoritesContract {
    RecyclerView recyclerView;
    FavoritesPresenter presenter;
    FavoriteAdapter adapter;
    FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FavoritesPresenter(
                MealsRepository.getInstance(
                        MealsLocalDataSource.getInstance(getActivity()),
                        MealsRemoteDataSource.getInstance()
                ), this
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        if (isGuest()) {
            showGuestAlert();
        }
        recyclerView = view.findViewById(R.id.calendar_rv);
        adapter = new FavoriteAdapter(getContext(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        presenter.getFavoritesMeals();

    }


    @Override
    public void removeMealFromFavourite(Meals Meal) {
        presenter.removeFavoritesMeals(Meal);
        Toast.makeText(requireContext(),"meal removed from favorites",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void getFavoriteMeals(List<Meals> MealstList) {
        adapter.setList(MealstList);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void navigateToDetails(String mealId) {
        Bundle bundle = new Bundle();
        bundle.putString("mealId", mealId);
        Navigation.findNavController(requireView()).navigate(R.id.action_favoriteFragment_to_detailsScreenFragment, bundle);
    }

    public boolean isGuest() {
        FirebaseUser user = mAuth.getCurrentUser();
        return user == null;
    }


    private void showGuestAlert() {
        NavController navController = Navigation.findNavController(requireView());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("You need to register to use  Favourites Screen");
        builder.setTitle("SignUp Or Login First!");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", (dialog, which) -> {
            navController.navigate(R.id.action_favoriteFragment_to_welcome_Screen_Fragment);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.cancel();
            navController.navigate(R.id.action_favoriteFragment_to_homeScreenFragment);
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}