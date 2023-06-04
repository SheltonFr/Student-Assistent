package dev.sheltonfrancisco.studentassistent.ui.create;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dev.sheltonfrancisco.studentassistent.api.RetrofitConfig;
import dev.sheltonfrancisco.studentassistent.api.responses.SubjectResponse;
import dev.sheltonfrancisco.studentassistent.databinding.FragmentCreateTaskBinding;
import dev.sheltonfrancisco.studentassistent.models.Subject;
import dev.sheltonfrancisco.studentassistent.models.Task;
import dev.sheltonfrancisco.studentassistent.ui.ProgressDialog;
import dev.sheltonfrancisco.studentassistent.ui.listeners.CreateEventListener;
import dev.sheltonfrancisco.studentassistent.utils.Mapper;
import dev.sheltonfrancisco.studentassistent.utils.Storage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTaskFragment extends Fragment {


    private FragmentCreateTaskBinding binding;
    private ArrayAdapter<Subject> spinnerAdapter;
    private ArrayList<Subject> subjects;
    private CreateEventListener activityCallback;


    public CreateTaskFragment() {
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
        binding = FragmentCreateTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subjects = new ArrayList<>();
        fetchData();
        spinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, subjects);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.inputSubject.setAdapter(spinnerAdapter);


        binding.inputDeadline.setOnClickListener(e -> {
            final Calendar calendar = Calendar.getInstance();

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    binding.inputDeadline.setText(setFormattedString(dayOfMonth) + "/" + setFormattedString(month + 1) + "/" + setFormattedString(year));
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });


        binding.inputTime.setOnClickListener(e -> {
            final Calendar calendar = Calendar.getInstance();
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    binding.inputTime.setText(setFormattedString(hourOfDay) + ":" + setFormattedString(minute));
                }
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);

            timePickerDialog.show();
        });

        binding.saveTaskButton.setOnClickListener(e -> {
            String title = binding.inputTaskTitle.getText().toString();
            String description = binding.inputTaskDescription.getText().toString();
            Integer subjectId = subjects.get(binding.inputSubject.getSelectedItemPosition()).getId();
            String deadline = binding.inputDeadline.getText().toString();
            String time = binding.inputTime.getText().toString();

            if (time.isEmpty() || description.isEmpty() || title.isEmpty() || deadline.isEmpty()) {
                Toast.makeText(getContext(), "Fill all fields to continue...", Toast.LENGTH_SHORT).show();
                return;
            }

            String[] splitDate = deadline.split("/");
            String textDate = splitDate[2] + "-" + splitDate[1] + "-" + splitDate[0] + " " + time;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(textDate, formatter);

            Task task = new Task(title, description, dateTime, subjectId);
            activityCallback.createTask(task);


        });

    }


    private void fetchData() {

        final ProgressDialog loadDialog = new ProgressDialog(getActivity());
        loadDialog.startLoading();

        Call<List<SubjectResponse>> listCall = new RetrofitConfig().getSubjectService().getAll(Storage.getBearerToken(getContext()));
        listCall.enqueue(new Callback<List<SubjectResponse>>() {
            @Override
            public void onResponse(Call<List<SubjectResponse>> call, Response<List<SubjectResponse>> response) {
                loadDialog.dismissDialog();
                ArrayList<Subject> subs = new ArrayList<>();
                if (response.isSuccessful()) {
                    response.body().forEach(item -> subs.add(Mapper.mapToSubject(item)));

                    if (subs.size() < 1) {
                        Toast.makeText(getContext(), "No Subjects Found", Toast.LENGTH_LONG).show();
//                        getActivity().finish();
//                        return;
                    }

                    onSubjectListReady(subs);
                }
            }

            @Override
            public void onFailure(Call<List<SubjectResponse>> call, Throwable t) {
                loadDialog.dismissDialog();
                Toast.makeText(getContext(), "En error occured", Toast.LENGTH_LONG).show();
                Log.e("ERROR", t.getMessage(), t);
                getActivity().finish();

            }
        });
    }

    private void onSubjectListReady(ArrayList<Subject> subjects) {
        this.subjects = subjects;
        spinnerAdapter.addAll(this.subjects);

    }

    private String setFormattedString(int n) {
        return n < 10 ? "0".concat(String.valueOf(n)) : String.valueOf(n);
    }
}