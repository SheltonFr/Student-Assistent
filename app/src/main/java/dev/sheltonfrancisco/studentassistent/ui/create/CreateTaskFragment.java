package dev.sheltonfrancisco.studentassistent.ui.create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dev.sheltonfrancisco.studentassistent.databinding.FragmentCreateTaskBinding;

public class CreateTaskFragment extends Fragment {


    private FragmentCreateTaskBinding binding;

    public CreateTaskFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCreateTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}