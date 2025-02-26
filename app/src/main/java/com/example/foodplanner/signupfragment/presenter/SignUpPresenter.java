package com.example.foodplanner.signupfragment.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodplanner.loginfragment.presenter.LoginContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpPresenter {
    FirebaseAuth mAuth;
    SignUpContract contract;
    Context context;

    public SignUpPresenter(SignUpContract contract, Context context) {
        mAuth = FirebaseAuth.getInstance();
        this.contract = contract;
        this.context = context;
    }


    public void signUp(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
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
