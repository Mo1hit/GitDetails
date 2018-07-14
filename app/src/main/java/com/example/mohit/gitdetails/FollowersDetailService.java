package com.example.mohit.gitdetails;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FollowersDetailService {

    @GET("{loginName}/followers")
    Call<ArrayList<FollowerDetails>> getFollowersDetails(@Path("loginName") String loginName);
}
