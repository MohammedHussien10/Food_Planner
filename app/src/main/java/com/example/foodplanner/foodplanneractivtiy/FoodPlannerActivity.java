package com.example.foodplanner.foodplanneractivtiy;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.foodplanner.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FoodPlannerActivity extends AppCompatActivity {
    NavController navController;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.food_planner_main);


        mAuth = FirebaseAuth.getInstance();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.fragmentContainerView);


        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        } else {
            Log.e("NavHost", "NavHostFragment NOT Found!");
        }


        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.homeScreenFragment || navDestination.getId() == R.id.favoriteFragment || navDestination.getId() == R.id.calendarFragment || navDestination.getId() == R.id.profileFragment || navDestination.getId() == R.id.searchSelectbyScreenFragment) {

                    bottomNavigationView.setVisibility(View.VISIBLE);

                } else {
                    bottomNavigationView.setVisibility(View.INVISIBLE);
                }

            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.favoriteFragment && mAuth.getCurrentUser() == null || item.getItemId() == R.id.calendarFragment && mAuth.getCurrentUser() == null ||item.getItemId() == R.id.profileFragment && mAuth.getCurrentUser() == null) {
                showGuestAlert();
                return false;
            }
            return NavigationUI.onNavDestinationSelected(item, navController);
        });




    }

    public boolean isGuest() {
        FirebaseUser user = mAuth.getCurrentUser();
        return user == null;
    }

    private void showGuestAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You need to register to use Favourites Screen");
        builder.setTitle("SignUp Or Login First!");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", (dialog, which) -> {
            navController.navigate(R.id.welcome_Screen_Fragment);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.cancel();
//            navController.navigate(R.id.homeScreenFragment);
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }




}