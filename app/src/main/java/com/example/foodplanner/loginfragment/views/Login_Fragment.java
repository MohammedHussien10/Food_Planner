package com.example.foodplanner.loginfragment.views;

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
import com.example.foodplanner.databinding.FragmentLoginBinding;
import com.example.foodplanner.databinding.WelcomeScreenFragmentBinding;
import com.example.foodplanner.loginfragment.presenter.LoginContract;
import com.example.foodplanner.loginfragment.presenter.LoginPresenter;


public class Login_Fragment extends Fragment implements LoginContract {
    FragmentLoginBinding binding;
    LoginPresenter presenter;
    EditText editText_Username, editText_Password;
    NavController navController;

    public Login_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this, requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(requireView());
        editText_Username = view.findViewById(R.id.et_Screen_Login_Yourname);
        editText_Password = view.findViewById(R.id.et_Screen_Login_Password);

        binding.btnScreenLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editText_Username.getText().toString();
                String password = editText_Password.getText().toString();
                presenter.signIn(email, password);
            }
        });

        binding.btnWelcomeLoginS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_login_Fragment_to_signUp_Fragment);
            }
        });


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void navigateToHomeScreen() {
        Navigation.findNavController(requireView()).navigate(R.id.action_login_Fragment_to_homeScreenFragment);
    }
}