package dev.sheltonfrancisco.studentassistent.ui.create;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import dev.sheltonfrancisco.studentassistent.MainActivity;
import dev.sheltonfrancisco.studentassistent.api.RetrofitConfig;
import dev.sheltonfrancisco.studentassistent.api.requests.SubjectRequest;
import dev.sheltonfrancisco.studentassistent.api.responses.SubjectResponse;
import dev.sheltonfrancisco.studentassistent.databinding.ActivityCreateBinding;
import dev.sheltonfrancisco.studentassistent.models.Subject;
import dev.sheltonfrancisco.studentassistent.ui.ProgressDialog;
import dev.sheltonfrancisco.studentassistent.ui.listeners.CreateEventListener;
import dev.sheltonfrancisco.studentassistent.utils.Storage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity implements CreateEventListener {

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
        } else {
            fm.beginTransaction().add(binding.fragmentContainer.getId(), new CreateTaskFragment()).commit();
        }
    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }

    @Override
    public void createSubject(Subject subject) {

        final ProgressDialog loadingDialog = new ProgressDialog(this);
        loadingDialog.startLoading();

        String token = Storage.getTokenFromSharedPreferences(getApplicationContext());
        if (token != null) {
            String auth = "Bearer " + token;
            SubjectRequest subjectRequest = new SubjectRequest(subject.getName(), subject.getTeacher(), subject.getGrade());
            Call<SubjectResponse> subjectResponseCall = new RetrofitConfig().getSubjectService().create(subjectRequest, auth);

            subjectResponseCall.enqueue(new Callback<SubjectResponse>() {
                @Override
                public void onResponse(Call<SubjectResponse> call, Response<SubjectResponse> response) {
                    loadingDialog.dismissDialog();
                    if (response.isSuccessful()) {
                        Toast.makeText(CreateActivity.this, "Gravado com Sucesso", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    Toast.makeText(CreateActivity.this, "Um erro ocorreu", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<SubjectResponse> call, Throwable t) {
                    loadingDialog.dismissDialog();
                    Toast.makeText(CreateActivity.this, "Um erro ocorreu", Toast.LENGTH_SHORT).show();
                    Log.e("ERROR", t.getMessage(), t);
                }
            });
        }

    }
}