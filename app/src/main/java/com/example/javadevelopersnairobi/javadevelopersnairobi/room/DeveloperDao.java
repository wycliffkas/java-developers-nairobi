package com.example.javadevelopersnairobi.javadevelopersnairobi.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;

@Dao
public interface DeveloperDao {

    @Insert
    void insert(Developer... developer);

    @Query("SELECT * from developer")
    LiveData<List<Developer>> getAllDevelopers();

}
