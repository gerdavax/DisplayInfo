package it.gerdavax.displayinfo.collectors;

import android.content.Context;
import android.view.Display;

public class API16_JellyBean41Collector extends DisplayInfoCollector {

	public API16_JellyBean41Collector(Context context) {
		super(context);
	}

	@Override
	public void collect() {
		Display display = getWindowManager().getDefaultDisplay();

		mRealWidth = display.getWidth();
		mRealHeight = display.getHeight();
	}

}
