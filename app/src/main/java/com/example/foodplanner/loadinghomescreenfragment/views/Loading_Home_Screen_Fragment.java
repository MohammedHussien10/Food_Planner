package com.example.foodplanner.loadinghomescreenfragment.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.databinding.FragmentLoadingHomeScreenBinding;
import com.example.foodplanner.welcome_screen_fragment.views.Welcome_Screen_Fragment;


public class Loading_Home_Screen_Fragment extends Fragment {
    FragmentLoadingHomeScreenBinding binding;

    Welcome_Screen_Fragment welcomeScreenFragment;

    public Loading_Home_Screen_Fragment() {
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
        binding = FragmentLoadingHomeScreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.lottieAnimationView.playAnimation();
        view.postDelayed(() -> {

            Navigation.findNavController(view).navigate(R.id.action_loading_Home_Screen_Fragment_to_welcome_Screen_Fragment);
        }, 5500);


    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}