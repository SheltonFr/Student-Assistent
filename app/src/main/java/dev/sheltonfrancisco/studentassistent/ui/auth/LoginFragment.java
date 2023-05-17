package dev.sheltonfrancisco.studentassistent.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dev.sheltonfrancisco.studentassistent.MainActivity;
import dev.sheltonfrancisco.studentassistent.databinding.FragmentLoginBinding;
import dev.sheltonfrancisco.studentassistent.ui.ProgressDialog;
import dev.sheltonfrancisco.studentassistent.ui.listeners.AuthEventListener;

public class LoginFragment extends Fragment {


    private FragmentLoginBinding binding;
    private AuthEventListener activityCallback;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            activityCallback = (AuthEventListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context + " Must implement AuthEventListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.signin.setOnClickListener(e -> {


            String email = binding.emailInput.getText().toString();
            String password = binding.passwordInput.getText().toString();

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(LoginFragment.this.getContext(), "Fill all fields for Login!", Toast.LENGTH_SHORT).show();
                return;
            }
            activityCallback.handleSignIn(email, password);
        });
    }
}