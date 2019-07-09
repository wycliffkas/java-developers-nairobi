package com.example.javadevelopersnairobi.javadevelopersnairobi.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.javadevelopersnairobi.javadevelopersnairobi.model.GithubUsers;

import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;

@Dao
public interface DeveloperDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GithubUsers... githubUsers);

    @Query("SELECT * from developers")
    LiveData<List<GithubUsers>> getAllDevelopers();

}
