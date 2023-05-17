package dev.sheltonfrancisco.studentassistent.ui.auth;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import dev.sheltonfrancisco.studentassistent.R;
import dev.sheltonfrancisco.studentassistent.databinding.FragmentRegisterBinding;
import dev.sheltonfrancisco.studentassistent.ui.listeners.AuthEventListener;


public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private AuthEventListener activityCallback;

    public RegisterFragment() {
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
      binding = FragmentRegisterBinding.inflate(inflater, container, false);
      return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.signin.setOnClickListener(e -> {
            String email = binding.emailInput.getText().toString();
            String username = binding.usernameInput.getText().toString();
            String passeord = binding.passwordInput.getText().toString();

            if(email.isEmpty() || username.isEmpty() || passeord.isEmpty()) {
                Toast.makeText(RegisterFragment.this.getContext(), "Fill all fields for registration!", Toast.LENGTH_SHORT).show();
                return;
            }

            activityCallback.handleRegistration(email, username, passeord);
        });
    }
}