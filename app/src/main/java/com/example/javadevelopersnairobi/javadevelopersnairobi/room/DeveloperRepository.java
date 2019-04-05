package com.example.javadevelopersnairobi.javadevelopersnairobi.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class DeveloperRepository {
    private DeveloperDao developerDao;
    private LiveData<List<Developer>> allDevelopers;

    public DeveloperRepository(Application application){
        DeveloperDatabase database = DeveloperDatabase.getInstance(application);
        developerDao = database.developerDao();
        allDevelopers = developerDao.getAllDevelopers();
    }

    public void insert(Developer developer){
        new InsertDeveloperAsyncTask(developerDao).execute(developer);

    }

    public LiveData<List<Developer>> getAllDevelopers() {
        return allDevelopers;
    }

    private static class InsertDeveloperAsyncTask extends AsyncTask<Developer, Void, Void>{

        private DeveloperDao developerDao;

        private InsertDeveloperAsyncTask(DeveloperDao developerDao){
            this.developerDao = developerDao;
        }

        @Override
        protected Void doInBackground(Developer... developers) {
            developerDao.insert(developers[0]);
            return null;
        }
    }


}
