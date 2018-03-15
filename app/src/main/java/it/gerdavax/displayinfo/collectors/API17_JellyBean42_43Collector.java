package it.gerdavax.displayinfo.collectors;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.view.Display;

public class API17_JellyBean42_43Collector extends DisplayInfoCollector {

	public API17_JellyBean42_43Collector(Context context) {
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

		} catch (Exception e) {
			mRealWidth = -1;
			mRealHeight = -1;
		}
	}
	
}
