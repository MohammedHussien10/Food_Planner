package com.example.foodplanner.loginfragment.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.airbnb.lottie.animation.content.Content;
import com.example.foodplanner.loginfragment.views.Login_Fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter {
    FirebaseAuth mAuth;
    LoginContract contract;
    Context context;

public LoginPresenter(LoginContract contract ,Context context){
    mAuth = FirebaseAuth.getInstance();
    this.contract = contract;
    this.context = context;
}

    public void signIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        contract.navigateToHomeScreen();
                    } else {
                        Log.i("tag", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(context, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();


                    }
                });
    }
}
