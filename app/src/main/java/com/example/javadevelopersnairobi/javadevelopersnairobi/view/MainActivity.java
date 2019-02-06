package com.example.javadevelopersnairobi.javadevelopersnairobi.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.javadevelopersnairobi.javadevelopersnairobi.R;
import com.example.javadevelopersnairobi.javadevelopersnairobi.adapter.GithubUsersAdapter;
import com.example.javadevelopersnairobi.javadevelopersnairobi.model.GithubUsers;
import com.example.javadevelopersnairobi.javadevelopersnairobi.presenter.GithubPresenter;
import com.example.javadevelopersnairobi.javadevelopersnairobi.util.ConnectivityReceiver;
import com.example.javadevelopersnairobi.javadevelopersnairobi.util.MyApplication;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
		implements DeveloperView, ConnectivityReceiver.ConnectivityReceiverListener {

	RecyclerView recyclerView;
	List<GithubUsers> intialList = new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recyclerView = findViewById(R.id.users_recyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(new GithubUsersAdapter(intialList, this));

		GithubPresenter githubPresenter = new GithubPresenter();
		githubPresenter.getAllDevelopers(MainActivity.this);

		// Manually checking internet connection
		checkConnection();

	}

	@Override
	public void developersReady(List<GithubUsers> developers) {
		recyclerView.setAdapter(new GithubUsersAdapter(developers, this));
	}
	// Method to check connection status
	private void checkConnection(){
		boolean isConnected = ConnectivityReceiver.isConnected();
		if (!isConnected){
			showSnack(isConnected);
		}

	}

	// Showing the status in Snackbar
	private void showSnack (boolean isConnected){
		Snackbar snackbar = Snackbar
			.make(findViewById(R.id.users_recyclerView),
			"No internet connection, Please enable it to continue", Snackbar.LENGTH_LONG);
		TextView txt = snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
		txt.setTextColor(Color.WHITE);
		snackbar.show();

	}

	@Override
	protected void onStart() {
		super.onStart();
		checkConnection();
	}

	// register connection status listener
	@Override
	protected void onResume() {
		super.onResume();
		MyApplication.getInstance().setConnectivityListener(this);
	}

	/**
	 * Callback will be triggered when there is change in
	 * network connection
	 */
	@Override
	public void onNetworkConnectionChanged(boolean isConnected) {
		showSnack(isConnected);
	}
}
