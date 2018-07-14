package com.example.mohit.gitdetails;

import com.google.gson.annotations.SerializedName;

public class GitProfileDetails {

    @SerializedName("url")
    private String url;

    @SerializedName("name")
    private String name;

    @SerializedName("login")
    private String loginName;

    @SerializedName("public_repos")
    private int publicRepos;

    @SerializedName("public_gists")
    private int publicGists;

    @SerializedName("following")
    private int following;

    @SerializedName("followers")
    private int follower;

    @SerializedName("followers_url")
    private String followersURL;

    @SerializedName("avatar_url")
    private String image;

    @SerializedName("message")
    private String message;

    public String getName() {
        return name;
    }

    public String getLoginName() {
        return loginName;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public int getPublicGists() {
        return publicGists;
    }

    public int getFollowing() {
        return following;
    }

    public int getFollower() {
        return follower;
    }

    public String getMessage() {
        return message;
    }

    public String getImage() {
        return image;
    }

    public String getFollowersURL() {
        return followersURL;
    }
}