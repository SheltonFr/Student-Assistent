package dev.sheltonfrancisco.studentassistent.ui.subject;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import dev.sheltonfrancisco.studentassistent.R;
import dev.sheltonfrancisco.studentassistent.api.RetrofitConfig;
import dev.sheltonfrancisco.studentassistent.api.responses.SubjectResponse;
import dev.sheltonfrancisco.studentassistent.databinding.FragmentCreateSubjectBinding;
import dev.sheltonfrancisco.studentassistent.databinding.FragmentSubjectBinding;
import dev.sheltonfrancisco.studentassistent.models.Subject;
import dev.sheltonfrancisco.studentassistent.ui.adapters.SubjectListAdapter;
import dev.sheltonfrancisco.studentassistent.utils.Mapper;
import dev.sheltonfrancisco.studentassistent.utils.Storage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubjectFragment extends Fragment {

    private FragmentSubjectBinding binding;
    private SubjectListAdapter adapter;
    private ArrayList<Subject> subjects;

    public static SubjectFragment newInstance() {
        return new SubjectFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSubjectBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subjects = new ArrayList<>();
        adapter = new SubjectListAdapter(subjects);
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
        Call<List<SubjectResponse>> listCall = new RetrofitConfig().getSubjectService().getAll(Storage.getBearerToken(getContext()));
        listCall.enqueue(new Callback<List<SubjectResponse>>() {
            @Override
            public void onResponse(Call<List<SubjectResponse>> call, Response<List<SubjectResponse>> response) {
                ArrayList<Subject> subs = new ArrayList<>();
                if(response.isSuccessful()) {
                    response.body().forEach(item -> subs.add(Mapper.mapToSubject(item)));
                    onSubjectListReady(subs);
                }
            }

            @Override
            public void onFailure(Call<List<SubjectResponse>> call, Throwable t) {

            }
        });
    }
    private void onSubjectListReady(ArrayList<Subject> subjects) {
        adapter.updateList(subjects);
        System.out.println(adapter.getItemCount());
    }
}