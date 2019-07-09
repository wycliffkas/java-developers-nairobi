package com.example.javadevelopersnairobi.javadevelopersnairobi.presenter;


import android.app.Application;
import android.util.Log;

import com.example.javadevelopersnairobi.javadevelopersnairobi.model.Data;
import com.example.javadevelopersnairobi.javadevelopersnairobi.model.GithubUsers;
import com.example.javadevelopersnairobi.javadevelopersnairobi.room.Developer;
import com.example.javadevelopersnairobi.javadevelopersnairobi.room.DeveloperDao;
import com.example.javadevelopersnairobi.javadevelopersnairobi.room.DeveloperDatabase;
import com.example.javadevelopersnairobi.javadevelopersnairobi.room.DeveloperRepository;
import com.example.javadevelopersnairobi.javadevelopersnairobi.service.DeveloperService;
import com.example.javadevelopersnairobi.javadevelopersnairobi.view.DeveloperView;
import com.example.javadevelopersnairobi.javadevelopersnairobi.view.MainActivity;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubPresenter {

	private final static String TAG = GithubPresenter.class.getSimpleName();
	private DeveloperService developerService;

	private DeveloperRepository repository;
	public DeveloperDao developerDao;

	public GithubPresenter(Application application){

		this.developerService = new DeveloperService();
		DeveloperDatabase database = DeveloperDatabase.getInstance(application);
		developerDao = database.developerDao();
		repository = new DeveloperRepository(application);
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
						insert(result);
						mainActivity.getAllDevelopers(result);
					}
				}

				@Override
				public void onFailure(Call<Data> call, Throwable t) {
					Log.e(TAG, t.toString());
				}
			});

	}

	public void insert(List<GithubUsers> developer){
		repository.insert(developer);
	}

	public List<GithubUsers> getOfflineDevelopers(MainActivity mainActivity) throws ExecutionException, InterruptedException {
		Callable<List<GithubUsers>> callable = new Callable<List<GithubUsers>>() {
            @Override
            public List<GithubUsers> call() throws Exception {
                return developerDao.getAllDevelopers().getValue();
            }
        };

        Future<List<GithubUsers>> future = Executors.newSingleThreadExecutor().submit(callable);


        return future.get();
	}



}
