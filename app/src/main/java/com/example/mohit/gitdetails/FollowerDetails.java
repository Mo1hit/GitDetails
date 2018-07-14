package com.example.mohit.gitdetails;

import com.google.gson.annotations.SerializedName;

public class FollowerDetails {

    @SerializedName("avatar_url")
    private String image;

    @SerializedName("login")
    private String login;

    public String getImage() {
        return image;
    }

    public String getLogin() {
        return login;
    }
}
