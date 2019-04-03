package com.example.javadevelopersnairobi.javadevelopersnairobi.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Blob;

@Entity(tableName = "developer")
public class Developer {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;

    private String github;

    private Blob image;

    public Developer(int id, String username, String github, Blob image) {
        this.id = id;
        this.username = username;
        this.github = github;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getGithub() {
        return github;
    }

    public Blob getImage() {
        return image;
    }
}
