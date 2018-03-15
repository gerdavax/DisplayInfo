package it.gerdavax.displayinfo.util;

import android.util.Log;

import java.lang.reflect.Method;

public class Utils {
	private static final String TAG = "displayinfo";
	private static boolean LOG_ENABLED = true;

	public static void d(String message) {
		if (LOG_ENABLED) {
			Log.d(TAG, message);
		}
	}
	
	public static void d(Throwable t) {
		if (LOG_ENABLED) {
			Log.d(TAG, "", t);
		}
	}

	public static void explore(Class clazz) {
		if (LOG_ENABLED) {
			for (Method method: clazz.getDeclaredMethods()) {
                d("- " + method.getName());
            }
		}
	}
}
