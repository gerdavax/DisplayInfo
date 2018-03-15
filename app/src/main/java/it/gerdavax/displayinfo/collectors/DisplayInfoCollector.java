package it.gerdavax.displayinfo.collectors;

import it.gerdavax.displayinfo.util.Utils;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.WindowManager;

public abstract class DisplayInfoCollector {
	public static enum Orientation {
		PORTRAIT, LANDSCAPE
	};

	protected int mRealWidth;
	protected int mRealHeight;
    protected int mLinearDensity;
    protected int mNormalizedDensity;
    protected float mDensityScale;
	protected Orientation naturalOrientation;

	protected Context mContext;

	protected DisplayInfoCollector(Context context) {
		mContext = context;

        collectMetrics();
	}

	public static DisplayInfoCollector getInfo(Context context) {

		switch (Build.VERSION.SDK_INT) {
			case Build.VERSION_CODES.ICE_CREAM_SANDWICH:
			case Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1:
				return new API15_IceCreamSandwichCollector(context);
			case Build.VERSION_CODES.JELLY_BEAN:
				return new API16_JellyBean41Collector(context);
			case Build.VERSION_CODES.JELLY_BEAN_MR1:
			case Build.VERSION_CODES.JELLY_BEAN_MR2:
				return new API17_JellyBean42_43Collector(context);
			case Build.VERSION_CODES.KITKAT:
			case Build.VERSION_CODES.LOLLIPOP:
            case Build.VERSION_CODES.M:
				return new API19_KitKat44Collector(context);
			case Build.VERSION_CODES.N:
            case Build.VERSION_CODES.N_MR1:
				return new API24_Nougat70Collector(context);
            case Build.VERSION_CODES.O:
                return new API26_OreoCollector(context);
			default:
				return new LatestCollector(context);
		}
	}

	public abstract void collect();

	protected WindowManager getWindowManager() {
		return (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
	}

	public int getRealWidth() {
		return mRealWidth;
	}

	public int getRealHeight() {
		return mRealHeight;
	}

	public int getLinearDensity() {
        return mLinearDensity;
    }

    public float getDensityScale() {
        return mDensityScale;
    }

    public int getNormalizedDensity() {
        return mNormalizedDensity;
    }

	public Orientation getNaturalOrientation() {
		int currentRotation = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();

		boolean isNatural = (currentRotation == Surface.ROTATION_0 || currentRotation == Surface.ROTATION_180);

		Utils.d("Current orientation: " + currentRotation);
		Utils.d("Is orientation natural? " + isNatural);
		
		if (isNatural) {
			if (mRealWidth > mRealHeight) {
				return Orientation.LANDSCAPE;
			} else {
				return Orientation.PORTRAIT;
			}
		} else {
			if (mRealWidth > mRealHeight) {
				return Orientation.PORTRAIT;
			} else {
				return Orientation.LANDSCAPE;
			}
		}

	}

	public Boolean supportsHDR() {
		return null;
	}

	private void collectMetrics() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        mLinearDensity = (int) metrics.xdpi;
        mNormalizedDensity = (int) metrics.densityDpi;
        mDensityScale = metrics.density;

        //FIXME
        Utils.d("Density " + metrics.density);
        Utils.d("DensityDpi " + metrics.densityDpi);
        Utils.d("heightPixels " + metrics.heightPixels);
        Utils.d("widthPixels " + metrics.widthPixels);
        Utils.d("scaledDensity " + metrics.scaledDensity);

        //Utils.d("scaledDensity " + metrics.);



    }
}
