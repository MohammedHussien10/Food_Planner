package com.example.foodplanner.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;


public class Welcome_Screen_Fragment extends Fragment {

   private Button gmail;
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
        return inflater.inflate(R.layout.welcome_screen_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gmail = view.findViewById(R.id.btn_Gmail);

        signInLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::onSignInResult
        );

        gmail.setOnClickListener(new View.OnClickListener() {
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
                    .replace(R.id.fragmentContainer, homeScreenFragment)
                    .commit();
            if (user != null) {
                Toast.makeText(getContext(), "مرحبًا " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
            }
        } else {
            if (response == null) {
                Toast.makeText(getContext(), "تم إلغاء تسجيل الدخول!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "خطأ: " + response.getError().getErrorCode(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}