package dev.sheltonfrancisco.studentassistent.api.services;

import java.util.List;

import dev.sheltonfrancisco.studentassistent.api.requests.TaskBody;
import dev.sheltonfrancisco.studentassistent.api.responses.TaskResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TaskService {

    @POST("task")
    Call<TaskResponse> create(@Body TaskBody body, @Header("Authorization") String bearerToken);

    @GET("task")
    Call<List<TaskResponse>> findAll(@Header("Authorization") String bearerToken);
}
