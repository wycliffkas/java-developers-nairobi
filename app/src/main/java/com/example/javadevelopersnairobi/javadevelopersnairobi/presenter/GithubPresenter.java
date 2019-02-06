package com.example.javadevelopersnairobi.javadevelopersnairobi.presenter;


import android.util.Log;

import com.example.javadevelopersnairobi.javadevelopersnairobi.model.Data;
import com.example.javadevelopersnairobi.javadevelopersnairobi.model.GithubUsers;
import com.example.javadevelopersnairobi.javadevelopersnairobi.service.DeveloperService;
import com.example.javadevelopersnairobi.javadevelopersnairobi.view.DeveloperView;
import com.example.javadevelopersnairobi.javadevelopersnairobi.view.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubPresenter {

	private final static String TAG = GithubPresenter.class.getSimpleName();
	private DeveloperView developerView;
	private DeveloperService developerService;

	public GithubPresenter(DeveloperView view){
		this.developerView = view;

		if(this.developerView == null) {
			this.developerService = new DeveloperService();
		}
	}

	public GithubPresenter(){
		this.developerService = new DeveloperService();
	}


	public void getAllDevelopers(final MainActivity mainActivity){

		developerService
			.getAPI()
			.getDevelopers()
			.enqueue(new Callback<Data>() {
				@Override
				public void onResponse(Call<Data> call, Response<Data> response) {
					Data data = response.body();
					if(data != null ){
						List<GithubUsers> result = data.getResults();
						mainActivity.getAllDevelopers(result);
					}
				}

				@Override
				public void onFailure(Call<Data> call, Throwable t) {
					Log.e(TAG, t.toString());
				}
			});

	}

}
