package com.example.javadevelopersnairobi.javadevelopersnairobi.service;

import com.example.javadevelopersnairobi.javadevelopersnairobi.model.Data;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DeveloperAPI {

    @GET("/search/users?q=location:nairobi+language:java")
    Call<Data> getDevelopers();
}


