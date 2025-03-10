package com.example.foodplanner.calendar.views;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsRepository;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.example.foodplanner.calendar.presenter.CalendarContract;
import com.example.foodplanner.calendar.presenter.CalendarPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

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

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("test").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    Log.d("Firestoretestnow", "Firestore is working!");
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Error: " + e.getMessage());
                });
//        mAuth = FirebaseAuth.getInstance();
//        if (isGuest()) {
//            showGuestAlert();
//        }
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
        Toast.makeText(requireContext(), "Meal removed from Calendar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> {
            presenter.getCalendarPlanMeals(getSelectedDate());
        }, 500); // تأخير بسيط عشان يضمن تحديث البيانات
    }


    private String getSelectedDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calendarView.getDate());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }





    @Override
    public void getCalendarPlanMeals(List<CalendarPlan> Meals) {
        adapter.setList(Meals);
        adapter.notifyDataSetChanged();
        checkMeals(Meals); // التحقق بعد التحديث
    }


    @Override
    public void navigateToDetails(String mealId) {
        Bundle bundle = new Bundle();
        bundle.putString("mealId", mealId);
        Navigation.findNavController(requireView()).navigate(R.id.action_calendarFragment_to_detailsScreenFragment, bundle);
    }

//    public boolean isGuest() {
//        FirebaseUser user = mAuth.getCurrentUser();
//        return user == null;
//    }


//    private void showGuestAlert() {
//        NavController navController = Navigation.findNavController(requireView());
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setMessage("You need to register to use Calendar Screen");
//        builder.setTitle("SignUp Or Login First!");
//        builder.setCancelable(false);
//        builder.setPositiveButton("Ok", (dialog, which) -> {
//            navController.navigate(R.id.action_calendarFragment_to_welcome_Screen_Fragment);
//        });
//        builder.setNegativeButton("Cancel", (dialog, which) -> {
//            dialog.cancel();
//            navController.navigate(R.id.action_calendarFragment_to_homeScreenFragment);
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }

    public void hideLottie(){
        lottieLoading.setVisibility(GONE);

    }

    public void showLottie(){
        lottieLoading.setVisibility(VISIBLE);

    }

    private void checkMeals(List<CalendarPlan> mealsList) {
        if (mealsList.isEmpty()) {
            showLottie();
            recyclerView.setVisibility(View.GONE);
        } else {
            hideLottie();
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

}