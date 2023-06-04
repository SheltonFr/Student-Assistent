package dev.sheltonfrancisco.studentassistent.ui.agenda;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import dev.sheltonfrancisco.studentassistent.api.RetrofitConfig;
import dev.sheltonfrancisco.studentassistent.api.responses.SubjectResponse;
import dev.sheltonfrancisco.studentassistent.api.responses.TaskResponse;
import dev.sheltonfrancisco.studentassistent.databinding.FragmentAgendaBinding;
import dev.sheltonfrancisco.studentassistent.models.Subject;
import dev.sheltonfrancisco.studentassistent.models.Task;
import dev.sheltonfrancisco.studentassistent.ui.ProgressDialog;
import dev.sheltonfrancisco.studentassistent.ui.adapters.TaskListAdapter;
import dev.sheltonfrancisco.studentassistent.utils.Mapper;
import dev.sheltonfrancisco.studentassistent.utils.Storage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaFragment extends Fragment {


    private FragmentAgendaBinding binding;
    private ArrayList<Task> tasks;
    private TaskListAdapter adapter;
    public static AgendaFragment newInstance() {
        return new AgendaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAgendaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tasks = new ArrayList<>();
        adapter = new TaskListAdapter(tasks);
        binding.recylerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recylerView.setAdapter(adapter);

        fetchData();
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchData();
    }

    private void fetchData() {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.startLoading();
        Call<List<TaskResponse>> listCall = new RetrofitConfig().getTaskService().findAll(Storage.getBearerToken(getContext()));
        listCall.enqueue(new Callback<List<TaskResponse>>() {
            @Override
            public void onResponse(Call<List<TaskResponse>> call, Response<List<TaskResponse>> response) {
                progressDialog.dismissDialog();
                ArrayList<Task> tasks1 = new ArrayList<>();
                if(response.isSuccessful()) {
                    response.body().forEach(item -> tasks1.add(Mapper.mapToTask(item)));
                    onTaskListReady(tasks1);
                }
            }

            @Override
            public void onFailure(Call<List<TaskResponse>> call, Throwable t) {
                progressDialog.dismissDialog();
                Log.e("ERROR", t.getMessage(), t);
            }
        });
    }

    private void onTaskListReady(ArrayList<Task> tasks) {
        adapter.updateList(tasks);
    }
}