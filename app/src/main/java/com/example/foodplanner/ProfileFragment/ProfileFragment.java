package com.example.foodplanner.ProfileFragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.calendar.presenter.CalendarContract;
import com.example.foodplanner.calendar.presenter.CalendarPresenter;
import com.example.foodplanner.databinding.FragmentProfileBinding;
import com.example.foodplanner.db.MealsLocalDataSource;
import com.example.foodplanner.favorites.presenter.FavoritesContract;
import com.example.foodplanner.favorites.presenter.FavoritesPresenter;
import com.example.foodplanner.models.CalendarPlan;
import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsRepository;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.Objects;


public class ProfileFragment extends Fragment implements FavoritesContract , CalendarContract {
    FirebaseAuth mAuth;
    FragmentProfileBinding fragmentProfileBinding;
    FavoritesPresenter favoritesPresenter;
    CalendarPresenter calendarPresenter;


    public ProfileFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false);
       return fragmentProfileBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        favoritesPresenter = new FavoritesPresenter(
                MealsRepository.getInstance(
                        MealsLocalDataSource.getInstance(getActivity()),
                        MealsRemoteDataSource.getInstance()
                ), this
        );

        calendarPresenter = new CalendarPresenter(
                MealsRepository.getInstance(
                        MealsLocalDataSource.getInstance(getActivity()),
                        MealsRemoteDataSource.getInstance()
                ),this
        );
        NavController navController = Navigation.findNavController(view);
        fragmentProfileBinding.btnProfileFavo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_profileFragment_to_favoriteFragment);
            }
        });

        fragmentProfileBinding.btnProfilePlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_profileFragment_to_calendarFragment);
            }
        });

        fragmentProfileBinding.btnProfileLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                navController.navigate(R.id.action_profileFragment_to_welcome_Screen_Fragment);
                Toast.makeText(requireContext(), "you logged out", Toast.LENGTH_SHORT).show();
            }
        });

//        fragmentProfileBinding.btnBackup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackupClicked(view);
//            }
//        });

//        fragmentProfileBinding.btnRestore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onRestoreClicked(view);
//            }
//        });
    }

    public void onBackupClicked(View view) {
        String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        calendarPresenter.backupUserData(userId);
        favoritesPresenter.backupUserData(userId);
        Toast.makeText(getContext(), "Backup Successful", Toast.LENGTH_SHORT).show();
    }

    public void onRestoreClicked(View view) {
        String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        favoritesPresenter.restoreUserData(userId);
        calendarPresenter.restoreUserData(userId);
        Toast.makeText(getContext(), "Data Restored", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCalendarPlanMeals(List<CalendarPlan> Meals) {

    }

    @Override
    public void getFavoriteMeals(List<Meals> MealstList) {

    }


//    public boolean isGuest() {
//        FirebaseUser user = mAuth.getCurrentUser();
//        return user == null;
//    }
//
//
//    private void showGuestAlert() {
//        NavController navController = Navigation.findNavController(requireView());
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setMessage("You need to register to use Profile Screen");
//        builder.setTitle("SignUp Or Login First!");
//        builder.setCancelable(false);
//        builder.setPositiveButton("Ok", (dialog, which) -> {
//            navController.navigate(R.id.action_profileFragment_to_welcome_Screen_Fragment);
//        });
//        builder.setNegativeButton("Cancel", (dialog, which) -> {
//            dialog.cancel();
//            navController.navigate(R.id.action_profileFragment_to_homeScreenFragment);
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
}