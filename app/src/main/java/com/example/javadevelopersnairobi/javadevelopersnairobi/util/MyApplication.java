package com.example.javadevelopersnairobi.javadevelopersnairobi.util;

import android.app.Application;

public class MyApplication extends Application {

	public static MyApplication mInstance;

	@Override
	public void onCreate() {
		super.onCreate();

		mInstance = this;
	}

	public static synchronized MyApplication getInstance(){
		return mInstance;
	}

	public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
		ConnectivityReceiver.connectivityReceiverListener = listener;
	}

}
