package com.example.foodplanner.calendar.views;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.R;
import com.example.foodplanner.db.MealsLocalDataSource;
import com.example.foodplanner.models.CalendarPlan;
import com.example.foodplanner.models.MealsRepository;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.util.List;

import com.example.foodplanner.calendar.presenter.CalendarContract;
import com.example.foodplanner.calendar.presenter.CalendarPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CalendarFragment extends Fragment implements OnCalendarClickListener, CalendarContract {
    RecyclerView recyclerView;
    CalendarPresenter presenter;
    CalendarAdapter adapter;
    CalendarView calendarView;
    FirebaseAuth mAuth;
    LottieAnimationView lottieLoading;
    boolean isLoading = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CalendarPresenter(
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
        return inflater.inflate(R.layout.calendar, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lottieLoading = view.findViewById(R.id.loading_calendar);
        loadData();
        mAuth = FirebaseAuth.getInstance();
        if (isGuest()) {
            showGuestAlert();
        }
        calendarView = view.findViewById(R.id.calendarView);
        recyclerView = view.findViewById(R.id.calendar_rv);
        adapter = new CalendarAdapter(getContext(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView,  int year, int month, int day) {
                String selectedDate = day + "/" + (month + 1) + "/" + year;
                presenter.getCalendarPlanMeals(selectedDate);
            }
        });

    }






    @Override
    public void removeMealFromCalenderPlan(CalendarPlan meal) {
        presenter.removeCalendarPlanMeals(meal);
        Toast.makeText(requireContext(),"meal removed from Calender",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCalendarPlanMeals(List<CalendarPlan> Meals) {
        adapter.setList(Meals);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void navigateToDetails(String mealId) {
        Bundle bundle = new Bundle();
        bundle.putString("mealId", mealId);
        Navigation.findNavController(requireView()).navigate(R.id.action_calendarFragment_to_detailsScreenFragment, bundle);
    }

    public boolean isGuest() {
        FirebaseUser user = mAuth.getCurrentUser();
        return user == null;
    }


    private void showGuestAlert() {
        NavController navController = Navigation.findNavController(requireView());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("You need to register to use Calendar Screen");
        builder.setTitle("SignUp Or Login First!");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", (dialog, which) -> {
            navController.navigate(R.id.action_calendarFragment_to_welcome_Screen_Fragment);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.cancel();
            navController.navigate(R.id.action_calendarFragment_to_homeScreenFragment);
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadData() {
        showLoading();
        new Handler().postDelayed(() -> {
            isLoading = false;
        }, 3000);
    }


    private void showLoading() {
        if (isLoading) {
            lottieLoading.playAnimation();

        } else {
            lottieLoading.setVisibility(View.GONE);
            lottieLoading.cancelAnimation();

        }
    }

}