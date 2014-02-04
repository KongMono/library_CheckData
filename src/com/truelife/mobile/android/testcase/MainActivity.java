package com.truelife.mobile.android.testcase;

import com.truelife.mobile.android.R;
import com.truelife.mobile.android.R.layout;
import com.truelife.mobile.android.R.menu;
import com.truelife.mobile.android.lib.AppStatus;
import com.truelife.mobile.android.util.Preferences;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	Preferences preferences;
	AppStatus appStatus;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
		/**
		 * Check network isonline check 
		 */
		if (appStatus.isOnline()){
			/**
			 * Check Type network 
			 * Toast TYPE_MOBILE is connected 3G
			 * Toast TYPE_WIFI is connected WIFI
			 */
			appStatus.ToastTypeData();
			
			try {
				/**
				 * check count call service
				 * if limit 5 
				 */
				if (preferences.isActive()) {
					Toast.makeText(this, "Call service", Toast.LENGTH_SHORT).show();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	private void init() {
		preferences = new Preferences(this);
		appStatus = new AppStatus(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
