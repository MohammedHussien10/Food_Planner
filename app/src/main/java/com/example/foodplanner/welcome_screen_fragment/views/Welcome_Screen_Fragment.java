package com.example.foodplanner.welcome_screen_fragment.views;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.foodplanner.R;
import com.example.foodplanner.databinding.WelcomeScreenFragmentBinding;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import com.example.foodplanner.homescreenfragment.views.HomeScreenFragment;


public class Welcome_Screen_Fragment extends Fragment {
    private FirebaseAuth mAuth; //firebase ref
WelcomeScreenFragmentBinding binding;
HomeScreenFragment homeScreenFragment;

    private ActivityResultLauncher<Intent> signInLauncher;



    public Welcome_Screen_Fragment() {
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
        binding =  WelcomeScreenFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        onStart();


        binding.btnWelcomeLogin.setOnClickListener(v ->
                navController.navigate(R.id.action_welcome_Screen_Fragment_to_login_Fragment)
        );

        binding.btnWelcomeSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireView()).navigate(R.id.action_welcome_Screen_Fragment_to_signUp_Fragment);
            }
        });

        binding.btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireView()).navigate(R.id.action_welcome_Screen_Fragment_to_homeScreenFragment);
            }
        });



        signInLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::onSignInResult
        );

        binding.btnGoogleSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void signIn() {

        List<AuthUI.IdpConfig> providers = Arrays.asList(
             new AuthUI.IdpConfig.EmailBuilder().build(),
              new AuthUI.IdpConfig.GoogleBuilder().build()
        );


        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();
        signInLauncher.launch(signInIntent);
    }

    private void onSignInResult(androidx.activity.result.ActivityResult result) {
        IdpResponse response = IdpResponse.fromResultIntent(result.getData());
        if (result.getResultCode() == RESULT_OK) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            homeScreenFragment = new HomeScreenFragment();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, homeScreenFragment)
                    .commit();
            if (user != null) {
                Toast.makeText(getContext(), "Welcome" + user.getDisplayName(), Toast.LENGTH_SHORT).show();
            }
        } else {
            if (response == null) {
                Toast.makeText(getContext(), "Cancel", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Error" + response.getError().getErrorCode(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void signOut() {
        // [START auth_sign_out]
        Log.i("test","calling sign out");
        FirebaseAuth.getInstance().signOut();
        // [END auth_sign_out]
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();

        }
        Log.i("test","calling onStart");
    }
    private void reload() { }
}