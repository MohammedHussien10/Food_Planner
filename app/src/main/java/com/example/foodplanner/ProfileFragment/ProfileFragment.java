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
import com.example.foodplanner.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends Fragment {
    FirebaseAuth mAuth;
    FragmentProfileBinding fragmentProfileBinding;


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
        if (isGuest()) {
            showGuestAlert();
        }
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
    }




    public boolean isGuest() {
        FirebaseUser user = mAuth.getCurrentUser();
        return user == null;
    }


    private void showGuestAlert() {
        NavController navController = Navigation.findNavController(requireView());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("You need to register to use Profile Screen");
        builder.setTitle("SignUp Or Login First!");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", (dialog, which) -> {
            navController.navigate(R.id.action_profileFragment_to_welcome_Screen_Fragment);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.cancel();
            navController.navigate(R.id.action_profileFragment_to_homeScreenFragment);
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}