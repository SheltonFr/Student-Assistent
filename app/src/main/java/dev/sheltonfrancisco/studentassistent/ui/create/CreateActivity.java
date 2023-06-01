package dev.sheltonfrancisco.studentassistent.ui.create;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

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


        String requiredFragmentCode = String.valueOf(getIntent().getExtras().getString(MainActivity.FABS_TAG));
        Log.d("EXTRAS", requiredFragmentCode);

        if (requiredFragmentCode.equals(MainActivity.SUBJECT_TAG)) {
            fm.beginTransaction().add(binding.fragmentContainer.getId(), new CreateSubjectFragment()).commit();
            Log.d("EXTRAS", MainActivity.SUBJECT_TAG);
        } else {
            Log.d("EXTRAS", MainActivity.TASK_TAG);
            fm.beginTransaction().add(binding.fragmentContainer.getId(), new CreateTaskFragment()).commit();
        }
    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }
}