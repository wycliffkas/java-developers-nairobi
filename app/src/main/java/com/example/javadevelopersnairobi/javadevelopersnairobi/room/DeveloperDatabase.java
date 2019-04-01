package com.example.javadevelopersnairobi.javadevelopersnairobi.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {Developer.class}, version = 1)
public abstract class DeveloperDatabase extends RoomDatabase {


}
