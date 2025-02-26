package com.example.foodplanner.welcome_screen_fragment.views;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import com.example.foodplanner.signupfragment.presenter.SignUpPresenter;
import com.example.foodplanner.welcome_screen_fragment.presenter.WelcomeContract;
import com.example.foodplanner.welcome_screen_fragment.presenter.WelcomePresenter;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import com.example.foodplanner.homescreenfragment.views.HomeScreenFragment;
import com.google.firebase.auth.GoogleAuthProvider;


public class Welcome_Screen_Fragment extends Fragment implements WelcomeContract {
     FirebaseAuth mAuth;
    WelcomeScreenFragmentBinding binding;
     GoogleSignInClient googleSignInClient;
     ActivityResultLauncher<Intent> googleSignInLauncher;
     WelcomePresenter welcomePresenter;


    public Welcome_Screen_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        welcomePresenter = new WelcomePresenter(this,requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = WelcomeScreenFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("715026430091-ue656i1cfrnqmjcsefldkn3140ncnpos.apps.googleusercontent.com")
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso);

        googleSignInLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == AppCompatActivity.RESULT_OK && result.getData() != null) {
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                        try {
                            GoogleSignInAccount account = task.getResult(ApiException.class);
                            if (account != null) {
                                String idToken = account.getIdToken();
                                welcomePresenter.firebaseAuthWithGoogle(idToken);
                            }
                        } catch (ApiException e) {
                            Toast.makeText(getContext(), "Google Sign-In Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );




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


        binding.btnGoogleSignin.setOnClickListener(v -> signInWithGoogle());


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }



    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        googleSignInLauncher.launch(signInIntent);
    }


    @Override
    public void navigateToHomeScreen() {
        Navigation.findNavController(requireView()).navigate(R.id.action_welcome_Screen_Fragment_to_homeScreenFragment);
    }
}