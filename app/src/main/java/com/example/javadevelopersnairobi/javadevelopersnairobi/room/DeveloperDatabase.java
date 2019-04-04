package com.example.javadevelopersnairobi.javadevelopersnairobi.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Developer.class}, version = 1)
public abstract class DeveloperDatabase extends RoomDatabase {

    public static DeveloperDatabase instance;
    public abstract DeveloperDao developerDao();

    public static synchronized DeveloperDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DeveloperDatabase.class, "developer_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;

    }

}
