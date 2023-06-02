package dev.sheltonfrancisco.studentassistent.api.services;

import dev.sheltonfrancisco.studentassistent.api.requests.AuthBody;
import dev.sheltonfrancisco.studentassistent.api.responses.AuthResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("user")
    Call<AuthResponse>create(@Body AuthBody body);

    @POST("auth/login")
    Call<AuthResponse> login(@Body AuthBody body);

}
