package com.example.mohit.gitdetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProfileDetailService {
    @GET("{name}")
    Call<GitProfileDetails> getProfileDetails(@Path("name") String name);
}
