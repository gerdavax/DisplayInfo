package it.gerdavax.displayinfo.collectors;

import android.content.Context;
import android.view.Display;

public class API15_IceCreamSandwichCollector extends DisplayInfoCollector {

	public API15_IceCreamSandwichCollector(Context context) {
		super(context);
	}

	@Override
	public void collect() {
		try {
			Display display = getWindowManager().getDefaultDisplay();

			mRealWidth = (Integer) Display.class.getMethod("getRawWidth", null).invoke(display, null);
			mRealHeight = (Integer) Display.class.getMethod("getRawHeight", null).invoke(display, null);

		} catch (Exception e) {
			mRealWidth = -1;
			mRealHeight = -1;
		}
	}



}
