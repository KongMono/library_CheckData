package com.truelife.mobile.android.lib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class AppStatus {
	private String TYPE_WIFI = "TYPE_WIFI";
	private String TYPE_MOBILE = "TYPE_MOBILE";
	private Context context;
	ConnectivityManager connectivityManager;
	NetworkInfo wifiInfo, mobileInfo;

	boolean connected = false;

	public AppStatus(Context c) {
		context = c;
	}

	public boolean isOnline() {
		try {
			connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			NetworkInfo networkInfo = connectivityManager
					.getActiveNetworkInfo();
			connected = networkInfo != null && networkInfo.isAvailable()
					&& networkInfo.isConnected();
			return connected;

		} catch (Exception e) {
			System.out
					.println("CheckConnectivity Exception: " + e.getMessage());
			Log.v("connectivity", e.toString());
		}
		return connected;
	}

	public void ToastTypeData() {
		ConnectivityManager connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo mWifi = connManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (mWifi.isConnected()) {
			Toast.makeText(context, TYPE_WIFI, Toast.LENGTH_LONG).show();
		}

		NetworkInfo mMobile = connManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		if (mMobile.isConnected()) {
			Toast.makeText(context, TYPE_MOBILE, Toast.LENGTH_LONG).show();
		}
	}

}
