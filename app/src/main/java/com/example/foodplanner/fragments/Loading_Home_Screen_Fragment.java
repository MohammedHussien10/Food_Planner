package com.example.foodplanner.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;


public class Loading_Home_Screen_Fragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_loading__home__screen_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        view.postDelayed(() -> {
            welcomeScreenFragment = new Welcome_Screen_Fragment();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, welcomeScreenFragment)
                    .commit();
        }, 5000);


    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}