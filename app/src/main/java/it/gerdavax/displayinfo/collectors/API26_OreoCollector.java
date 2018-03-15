package it.gerdavax.displayinfo.collectors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by gerdavax on 10/11/2017.
 */

@SuppressLint("NewApi")
public class API26_OreoCollector extends API24_Nougat70Collector {
    public API26_OreoCollector(Context context) {
        super(context);
    }

    @Override
    public Boolean supportsHDR() {
        return ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().isHdr();
    }
}
