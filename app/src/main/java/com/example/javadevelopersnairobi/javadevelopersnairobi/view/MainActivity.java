package com.example.javadevelopersnairobi.javadevelopersnairobi.view;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.javadevelopersnairobi.javadevelopersnairobi.R;
import com.example.javadevelopersnairobi.javadevelopersnairobi.adapter.GithubUsersAdapter;
import com.example.javadevelopersnairobi.javadevelopersnairobi.model.GithubUsers;
import com.example.javadevelopersnairobi.javadevelopersnairobi.presenter.GithubPresenter;
import com.example.javadevelopersnairobi.javadevelopersnairobi.util.ConnectivityReceiver;
import com.example.javadevelopersnairobi.javadevelopersnairobi.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

public class MainActivity extends AppCompatActivity
		implements DeveloperView, ConnectivityReceiver.ConnectivityReceiverListener {

	public final static String LIST_STATE_KEY = "recycler_list_state";
	private SwipeRefreshLayout swipeRefreshLayout;
	private GithubPresenter githubPresenter;
	private ProgressBar progressBar;
	private GridLayoutManager uLayoutManager;
	Parcelable listState;

	RecyclerView recyclerView;
	List<GithubUsers> intialList = new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recyclerView = findViewById(R.id.users_recyclerView);
		progressBar = findViewById(R.id.progressBar);

		githubPresenter = new GithubPresenter();
		githubPresenter.getAllDevelopers(this);

		//sets rows for the different orientations
		if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
			uLayoutManager = new GridLayoutManager(this, 2);
		} else{
			uLayoutManager = new GridLayoutManager(this, 3);
		}

		recyclerView.setLayoutManager(uLayoutManager);

		// Manually checking internet connection
		checkConnection();

		swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
		swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.YELLOW, Color.RED);
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				githubPresenter.getAllDevelopers(MainActivity.this);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
			case R.id.menu_refresh:
				swipeRefreshLayout.setRefreshing(true);
				githubPresenter.getAllDevelopers(MainActivity.this);
				return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void getAllDevelopers(List<GithubUsers> developers){

		recyclerView.setAdapter(new GithubUsersAdapter(developers, this));
		swipeRefreshLayout.setRefreshing(false);
		progressBar.setVisibility(View.GONE);
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


	@Override
	protected void onSaveInstanceState(Bundle state) {
		super.onSaveInstanceState(state);
		listState = uLayoutManager.onSaveInstanceState();
		state.putParcelable(LIST_STATE_KEY, listState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle state) {
		super.onRestoreInstanceState(state);
		if(state != null){
			listState =state.getParcelable(LIST_STATE_KEY);
		}
	}

	// register connection status listener
	@Override
	protected void onResume() {
		super.onResume();
		MyApplication.getInstance().setConnectivityListener(this);

		if (listState != null){
			uLayoutManager.onRestoreInstanceState(listState);
		}
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