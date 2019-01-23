package com.example.javadevelopersnairobi.javadevelopersnairobi.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeveloperService {
    private Retrofit retrofit = null;

    public DeveloperAPI getAPI() {
        String base_url = "https://api.github.com";

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(DeveloperAPI.class);
    }
}
