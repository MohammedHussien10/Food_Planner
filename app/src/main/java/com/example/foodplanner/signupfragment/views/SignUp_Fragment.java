package com.example.foodplanner.signupfragment.views;

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
import android.widget.EditText;

import com.example.foodplanner.R;
import com.example.foodplanner.databinding.FragmentSignUpBinding;
import com.example.foodplanner.loginfragment.presenter.LoginPresenter;
import com.example.foodplanner.signupfragment.presenter.SignUpContract;
import com.example.foodplanner.signupfragment.presenter.SignUpPresenter;

public class SignUp_Fragment extends Fragment implements SignUpContract {
SignUpPresenter signUpPresenter;
    EditText editText_Username, editText_Password;
    FragmentSignUpBinding fragmentSignUpBinding;
    NavController navController;
    public SignUp_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpPresenter = new SignUpPresenter(this,requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentSignUpBinding = FragmentSignUpBinding.inflate(inflater, container, false);
        return fragmentSignUpBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController   = Navigation.findNavController(requireView());
        editText_Username = view.findViewById(R.id.et_Screen_Signup_Email);
        editText_Password = view.findViewById(R.id.et_Screen_Signup_Password);

        fragmentSignUpBinding.btnScreenSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editText_Username.getText().toString();
                String password = editText_Password.getText().toString();
                signUpPresenter.signUp(email, password);

            }
        });


        fragmentSignUpBinding.btnSignupLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    navController.navigate(R.id.action_signUp_Fragment_to_login_Fragment);

            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void navigateToHomeScreen() {
        Navigation.findNavController(requireView()).navigate(R.id.action_signUp_Fragment_to_homeScreenFragment);
    }


}