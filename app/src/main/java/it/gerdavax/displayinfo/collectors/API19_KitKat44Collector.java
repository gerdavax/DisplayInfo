package it.gerdavax.displayinfo.collectors;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.view.Display;

public class API19_KitKat44Collector extends DisplayInfoCollector {

	public API19_KitKat44Collector(Context context) {
		super(context);
	}

	@Override
	public void collect() {
		try {
			Class displayinfoClass = Class.forName("android.view.DisplayInfo");

			Object displayinfo = displayinfoClass.newInstance();
			Display dsp = getWindowManager().getDefaultDisplay();
			Method getDisplayInfoMethod = Display.class.getDeclaredMethod("getDisplayInfo", displayinfoClass);
			getDisplayInfoMethod.invoke(dsp, displayinfo);

			Field logicalWidthField = displayinfoClass.getDeclaredField("logicalWidth");
			Field logicalHeightField = displayinfoClass.getDeclaredField("logicalHeight");

			mRealWidth = logicalWidthField.getInt(displayinfo);
			mRealHeight = logicalHeightField.getInt(displayinfo);

			//System.out.println("DisplayInfo: " + displayinfo);

			/*
			resolution = (TextView) findViewById(R.id.resolution);
			resolution.setText(Integer.toString(logicalWidth) + " x " + Integer.toString(logicalHeight));

			TextView orientation = (TextView) findViewById(R.id.orientation);
			if (isPortraitDevice()) {
				orientation.setText("portrait");
			} else {
				orientation.setText("landscape");
			} */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isPortraitDevice() throws Exception {
		Class displayinfoClass = Class.forName("android.view.DisplayInfo");

		Object displayinfo = Class.forName("android.view.DisplayInfo").newInstance();
		Display dsp = getWindowManager().getDefaultDisplay();
		Method getDisplayInfoMethod = Display.class.getDeclaredMethod("getDisplayInfo", Class.forName("android.view.DisplayInfo"));
		getDisplayInfoMethod.invoke(dsp, displayinfo);

		Method getNaturalWidthMethod = displayinfoClass.getDeclaredMethod("getNaturalWidth", null);
		int naturalWidth = (Integer) getNaturalWidthMethod.invoke(displayinfo, null);

		Method getNaturalHeightMethod = displayinfoClass.getDeclaredMethod("getNaturalHeight", null);
		int naturalHeight = (Integer) getNaturalHeightMethod.invoke(displayinfo, null);

		return naturalWidth < naturalHeight;
	}
	
}
