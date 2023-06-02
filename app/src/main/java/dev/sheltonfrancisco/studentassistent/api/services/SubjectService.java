package dev.sheltonfrancisco.studentassistent.api.services;


import java.util.List;

import dev.sheltonfrancisco.studentassistent.api.requests.SubjectRequest;
import dev.sheltonfrancisco.studentassistent.api.responses.SubjectResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SubjectService {

    @POST("subject")
    Call<SubjectResponse> create(@Body SubjectRequest request, @Header("Authorization") String bearerToken);

    @GET("subject")
    Call<List<SubjectResponse>> getAll(@Header("Authorization") String bearerToken);
}
