package com.example.foodplanner.DetailsFragment.views;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.DetailsFragment.presenter.DetailsContract;
import com.example.foodplanner.DetailsFragment.presenter.DetailsPresenter;
import com.example.foodplanner.R;
import com.example.foodplanner.databinding.DetailsBinding;
import com.example.foodplanner.databinding.FragmentHomeScreenBinding;
import com.example.foodplanner.db.MealsLocalDataSource;
import com.example.foodplanner.models.CalendarPlan;
import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsRepository;
import com.example.foodplanner.models.RemoteMeals;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Calendar;
import java.util.List;

public class DetailsScreenFragment extends Fragment implements DetailsClickListener, DetailsContract {
    DetailsBinding detailsBinding;
    DetailsIngredientsAdapter detailsIngredientsAdapter;
    RecyclerView recyclerV_Selected_Meals;
    DetailsPresenter detailsPresenter;
    Calendar calendar;
    YouTubePlayerView youTubePlayerView;
    FirebaseAuth mAuth;


    public DetailsScreenFragment() {


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsPresenter = new DetailsPresenter(this,
                MealsRepository.getInstance(MealsLocalDataSource
                        .getInstance(requireContext()), MealsRemoteDataSource.getInstance()));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        detailsBinding = DetailsBinding.inflate(inflater, container, false);
        return detailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        youTubePlayerView = view.findViewById(R.id.youtubePlayerView);
        recyclerV_Selected_Meals = view.findViewById(R.id.recyclerV_Selected_Meal);
        detailsIngredientsAdapter = new DetailsIngredientsAdapter(getContext(), this);
        recyclerV_Selected_Meals.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerV_Selected_Meals.setAdapter(detailsIngredientsAdapter);


        getMealDetails();


        detailsBinding.iconBtnAddtoFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("AuthCheck", "User is guest: " + isGuest());
                if (isGuest()) {
                    showGuestAlertFavourites();
                } else {
                    addMealToFavourite(detailsPresenter.meal);
                    Toast.makeText(requireContext(), "Meal added to favorites", Toast.LENGTH_SHORT).show();
                }
            }
        });


        detailsBinding.iconBtnAddtoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isGuest()) {
                    showGuestAlertCalendar();
                } else {
                    addMealToCalendar();

                }

            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void assignSelectedMealIngredientsToAdapter(List<String> Ingredients) {
        detailsIngredientsAdapter.setIngredientsList(Ingredients);
        detailsIngredientsAdapter.notifyDataSetChanged();
    }

    @Override
    public void SelectedMeal(RemoteMeals meal) {

        detailsBinding.tvMealName.setText(meal.getName());
        Glide.with(this).load(meal.getMealImage()).into(detailsBinding.imgMealSelected);
        detailsBinding.tvCategory.setText(meal.getCategory());
        detailsBinding.tvCountry.setText(meal.getArea());
        detailsBinding.tvDetailsInstructions.setText(meal.getInstructions());
        playVideo(meal.getYoutubeLink());

    }

    @Override
    public void getMealDetails() {
        if (getArguments() != null) {
            String mealId = getArguments().getString("mealId");
            if (mealId != null) {
                detailsPresenter.getSelectedMeal(mealId);
            }
        }
    }

    @Override
    public void showToast(String toast) {

    }

    @Override
    public void addMealToFavourite(Meals meal) {

        detailsPresenter.addMealToFavorites(meal);
    }

    @Override
    public void addMealToCalendar() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        detailsPresenter.addMealToCalendar(selectedDate);
                        Toast.makeText(requireContext(), "Meal added to Calendar", Toast.LENGTH_SHORT).show();
                    }
                }, year, month, day);

        datePickerDialog.show();
    }

    //Deal with Plan

    public void playVideo(String videoUrl) {
        String videoId = extractVideoId(videoUrl);

        youTubePlayerView.addYouTubePlayerListener(new YouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0);
            }

            @Override
            public void onStateChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerState playerState) {

            }

            @Override
            public void onPlaybackQualityChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlaybackQuality playbackQuality) {

            }

            @Override
            public void onPlaybackRateChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlaybackRate playbackRate) {

            }

            @Override
            public void onError(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerError playerError) {

            }

            @Override
            public void onCurrentSecond(@NonNull YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onVideoDuration(@NonNull YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onVideoLoadedFraction(@NonNull YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onVideoId(@NonNull YouTubePlayer youTubePlayer, @NonNull String s) {

            }

            @Override
            public void onApiChange(@NonNull YouTubePlayer youTubePlayer) {

            }
        });
    }

    private String extractVideoId(String url) {
        Uri uri = Uri.parse(url);
        return uri.getQueryParameter("v");
    }

    public boolean isGuest() {
        FirebaseUser user = mAuth.getCurrentUser();
        return user == null;
    }


    private void showGuestAlertFavourites() {
        NavController navController = Navigation.findNavController(requireView());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("You need to register to use add Your favourites Meals");
        builder.setTitle("SignUp Or Login First!");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", (dialog, which) -> {
            navController.navigate(R.id.action_detailsScreenFragment_to_welcome_Screen_Fragment);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showGuestAlertCalendar() {
        NavController navController = Navigation.findNavController(requireView());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("You need to register to use add Your Plans Meals");
        builder.setTitle("SignUp Or Login First!");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", (dialog, which) -> {
            navController.navigate(R.id.action_detailsScreenFragment_to_welcome_Screen_Fragment);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}







