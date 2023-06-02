package dev.sheltonfrancisco.studentassistent.api;

import dev.sheltonfrancisco.studentassistent.api.services.SubjectService;
import dev.sheltonfrancisco.studentassistent.api.services.UserService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.125.77:3000/student.assistent/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public UserService getUserService() {
        return this.retrofit.create(UserService.class);
    }

    public SubjectService getSubjectService() {
        return this.retrofit.create(SubjectService.class);
    }
}