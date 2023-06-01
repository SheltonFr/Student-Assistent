package dev.sheltonfrancisco.studentassistent.ui.create;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import dev.sheltonfrancisco.studentassistent.MainActivity;
import dev.sheltonfrancisco.studentassistent.databinding.ActivityCreateBinding;

public class CreateActivity extends AppCompatActivity {

    private ActivityCreateBinding binding;
    private FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fm = getSupportFragmentManager();

        String requiredFragmentCode = getIntent().getStringExtra(MainActivity.FABS_TAG);

        if(requiredFragmentCode == MainActivity.SUBJECT_TAG)
            fm.beginTransaction().replace(binding.fragmentContainer.getId(), new CreateSubjectFragment()).commit();
        else
            fm.beginTransaction().replace(binding.fragmentContainer.getId(), new CreateTaskFragment()).commit();
    }
}