package com.example.javadevelopersnairobi.javadevelopersnairobi.service;

import com.example.javadevelopersnairobi.javadevelopersnairobi.model.GithubUsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DeveloperAPI {

    @GET("/search/users?q=location:nairobi+language:java")
    Call<List<GithubUsersResponse>> getDevelopers();
}
