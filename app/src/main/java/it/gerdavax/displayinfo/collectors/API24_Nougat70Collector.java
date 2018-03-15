package it.gerdavax.displayinfo.collectors;

import android.content.Context;
import android.view.Display;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import it.gerdavax.displayinfo.util.Utils;

public class API24_Nougat70Collector extends DisplayInfoCollector {

	public API24_Nougat70Collector(Context context) {
		super(context);
	}

	@Override
	public void collect() {
		try {
			Class displayinfoClass = Class.forName("android.view.DisplayInfo");

			Utils.explore(displayinfoClass);

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

	
}
