package com.example.javadevelopersnairobi.javadevelopersnairobi.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.javadevelopersnairobi.javadevelopersnairobi.adapter.GithubUsersAdapter;
import com.example.javadevelopersnairobi.javadevelopersnairobi.R;
import com.example.javadevelopersnairobi.javadevelopersnairobi.model.GithubUsers;
import com.example.javadevelopersnairobi.javadevelopersnairobi.presenter.GithubPresenter;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DeveloperView {
	RecyclerView recyclerView;
	List<GithubUsers> intialList = new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recyclerView = (RecyclerView) findViewById(R.id.users_recyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(new GithubUsersAdapter(intialList, this));

		GithubPresenter githubPresenter = new GithubPresenter();
		githubPresenter.getAllDevelopers(MainActivity.this);

	}

	@Override
	public void developersReady(List<GithubUsers> developers) {
		recyclerView.setAdapter(new GithubUsersAdapter(developers, this));
	}
}
