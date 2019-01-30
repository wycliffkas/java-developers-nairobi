package com.example.javadevelopersnairobi.javadevelopersnairobi.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityReceiver {

	public static ConnectivityReceiverListener connectivityReceiverListener;

	public ConnectivityReceiver(){
		super();
	}

	public static boolean isConnected() {

		ConnectivityManager connectivityManager = (ConnectivityManager)
				MyApplication.getInstance().getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

		return activeNetwork != null
				&& activeNetwork.isConnectedOrConnecting();

	}

	public interface ConnectivityReceiverListener {
		void onNetworkConnectionChanged(boolean isConnected);
	}




}
