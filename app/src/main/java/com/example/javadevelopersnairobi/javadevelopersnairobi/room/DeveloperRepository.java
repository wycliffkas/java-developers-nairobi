package com.example.javadevelopersnairobi.javadevelopersnairobi.room;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.javadevelopersnairobi.javadevelopersnairobi.model.GithubUsers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DeveloperRepository {
    public DeveloperDao developerDao;

    public DeveloperRepository(Application application){
        DeveloperDatabase database = DeveloperDatabase.getInstance(application);
        developerDao = database.developerDao();
    }

    public void insert(List<GithubUsers> developers){
        for (GithubUsers developer: developers) {
            new InsertDeveloperAsyncTask(developerDao).execute(developer);
        }
    }

    public List<GithubUsers> getAllDevelopers() throws ExecutionException, InterruptedException {
//        Callable<List<GithubUsers>> callable = new Callable<List<GithubUsers>>() {
//            @Override
//            public List<GithubUsers> call() throws Exception {
//                return developerDao.getAllDevelopers().getValue();
//            }
//        };
//
//        Future<List<GithubUsers>> future = Executors.newSingleThreadExecutor().submit(callable);
//
//        return future.get();
        return developerDao.getAllDevelopers().getValue();
    }

    private static class InsertDeveloperAsyncTask extends AsyncTask<GithubUsers, Void, Void>{

        private DeveloperDao developerDao;

        private InsertDeveloperAsyncTask(DeveloperDao developerDao){
            this.developerDao = developerDao;
        }

        @Override
        protected Void doInBackground(GithubUsers... developers) {
            developerDao.insert(developers[0]);
            return null;
        }
    }


}
