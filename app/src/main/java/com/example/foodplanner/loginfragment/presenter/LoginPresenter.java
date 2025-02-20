package com.example.foodplanner.loginfragment.presenter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter {
    FirebaseAuth mAuth;
    LoginContract contract;

public LoginPresenter(LoginContract contract){
    mAuth = FirebaseAuth.getInstance();
    this.contract = contract;
}

    public void signIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        contract.navigateToHomeScreen();
                    } else {

                    }
                });
    }
}
