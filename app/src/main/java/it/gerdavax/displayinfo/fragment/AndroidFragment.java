package it.gerdavax.displayinfo.fragment;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.gerdavax.displayinfo.R;

/**
 * Created by gerdavax on 09/11/2017.
 */

public class AndroidFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_android, container, false);

        updateData();

        return mFragmentView;
    }

    private void updateData() {
        TextView device = (TextView) findViewById(R.id.device);
        device.setText(Build.DEVICE);

        TextView osVersion = (TextView) findViewById(R.id.os_version);
        osVersion.setText(Build.VERSION.RELEASE);

        TextView versionName = (TextView) findViewById(R.id.version_name);
        versionName.setText(getVersionName());

        TextView apiLevel = (TextView) findViewById(R.id.api_level);
        apiLevel.setText(Integer.toString(Build.VERSION.SDK_INT));

        TextView bluetooth = (TextView) findViewById(R.id.bluetooth);
        bluetooth.setText(getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH) ? "YES" : "NO");

        TextView ble = (TextView) findViewById(R.id.ble);
        ble.setText(getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE) ? "YES" : "NO");

        paintTable();
    }

    @Override
    public String getName() {
        return "ANDROID";
    }

    private String getVersionName() {
        switch (Build.VERSION.SDK_INT) {
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH:
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1:
                return "Ice Cream Sandwich";
            case Build.VERSION_CODES.JELLY_BEAN:
            case Build.VERSION_CODES.JELLY_BEAN_MR1:
            case Build.VERSION_CODES.JELLY_BEAN_MR2:
                return "Jelly Bean";
            case Build.VERSION_CODES.KITKAT:
                return "KitKat";
            case Build.VERSION_CODES.LOLLIPOP:
            case Build.VERSION_CODES.LOLLIPOP_MR1:
                return "Lollipop";
            case Build.VERSION_CODES.M:
                return "Marshmallow";
            case Build.VERSION_CODES.N:
            case Build.VERSION_CODES.N_MR1:
                return "Nougat";
            case Build.VERSION_CODES.O:
                return "Oreo";
            default:
                return "- - -";
        }
    }
}
