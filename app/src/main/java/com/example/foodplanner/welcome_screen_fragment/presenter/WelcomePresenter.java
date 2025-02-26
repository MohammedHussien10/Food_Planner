package com.example.foodplanner.welcome_screen_fragment.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.foodplanner.signupfragment.presenter.SignUpContract;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class WelcomePresenter {

    FirebaseAuth mAuth;
    WelcomeContract contract;
    Context context;

    public WelcomePresenter(WelcomeContract contract, Context context) {
        mAuth = FirebaseAuth.getInstance();
        this.contract = contract;
        this.context = context;
    }

    public void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        contract.navigateToHomeScreen();
                    } else {
                        Toast.makeText(context, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();

                    }
                });
    }

}
