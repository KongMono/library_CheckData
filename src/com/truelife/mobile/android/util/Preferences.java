package com.truelife.mobile.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class Preferences{
	public final int COUNT_START = 5;
	public final int COUNT_REMAIN = 0;
	public final int COUNT_ERROR = -1;
	public String TAG = Preferences.class.getSimpleName();
	private SharedPreferences appSharedPrefs;
	private Editor prefsEditor;
	private String CountNumber = "Count";

	/**
	 * @param context
	 */
	public Preferences(Context context) {
		this.appSharedPrefs = context.getSharedPreferences(context.getPackageName() + ".Data", 0);
		this.prefsEditor = appSharedPrefs.edit();
	}
	/**
	 * 
	 * @return Boolean
	 * @throws Exception
	 */
	public boolean isfirstTime() throws Exception{
		try {
			boolean firstTime = appSharedPrefs.getBoolean("firstTime", true);
			if (firstTime) {
				prefsEditor.putBoolean("firstTime", false);
				prefsEditor.putInt(CountNumber, COUNT_START);
				prefsEditor.commit();
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	/**
	 * 
	 * @return Boolean
	 * @throws Exception
	 */
	public boolean isActive() throws Exception {

		int count = COUNT_REMAIN;
		
		try {
			if (isfirstTime()) {
				count = appSharedPrefs.getInt(CountNumber,0);
				subCounter(count);
				return true;
			}else {
				count = appSharedPrefs.getInt(CountNumber,0);
				if (count > 0) {
					subCounter(count);
					return false;
				}else{
					resetCounter(COUNT_START);
					return true;
				}
			}
		} catch (Exception e) {
			Log.d(TAG,e.getMessage());
		}  
		return false;

	}
	/**
	 * subCounter
	 * @param num
	 */
	public void subCounter(int num) {
		int remain = num - 1;
		Log.d(TAG, "prefs SubCounter to " + remain);
		prefsEditor.putInt(CountNumber, remain);
		prefsEditor.commit();
	}
	/**
	 * resetCounter
	 * @param num
	 */
	public void resetCounter(int num) {
		Log.d(TAG, "prefs resetCounter to " + num);
		prefsEditor.putInt(CountNumber, num);
		prefsEditor.commit();
	}
	/**
	 * updateCounter
	 * @param num
	 */
	public void updateCounter(int num) {
		Log.d(TAG, "prefs updateCounter to " + num);
		prefsEditor.putInt(CountNumber, num);
		prefsEditor.commit();
	}

}
