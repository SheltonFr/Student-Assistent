package dev.sheltonfrancisco.studentassistent.ui.create;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dev.sheltonfrancisco.studentassistent.databinding.FragmentCreateSubjectBinding;
import dev.sheltonfrancisco.studentassistent.models.Subject;
import dev.sheltonfrancisco.studentassistent.ui.listeners.CreateEventListener;


public class CreateSubjectFragment extends Fragment {

    private FragmentCreateSubjectBinding binding;
    private CreateEventListener activityCallback;

    public CreateSubjectFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            activityCallback = (CreateEventListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(e.getMessage());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCreateSubjectBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.button.setOnClickListener(e -> {
            String grade = binding.inputGrade.getText().toString();
            String sName = binding.inputSubjectName.getText().toString();
            String teacher = binding.inputTeacherName.getText().toString();

            if (grade.isEmpty() || sName.isEmpty() || teacher.isEmpty()) {
                Toast.makeText(getActivity().getApplicationContext(), "Um erro ocorreu", Toast.LENGTH_SHORT).show();
                return;
            }

            Subject subject = new Subject(sName, teacher, Integer.parseInt(grade));
            activityCallback.createSubject(subject);
        });
    }
}