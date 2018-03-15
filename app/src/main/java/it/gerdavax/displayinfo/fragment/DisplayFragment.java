package it.gerdavax.displayinfo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import it.gerdavax.displayinfo.R;
import it.gerdavax.displayinfo.collectors.DisplayInfoCollector;

/**
 * Created by gerdavax on 08/11/2017.
 */

public class DisplayFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_display, container, false);

        ///fillUI();

        return mFragmentView;
    }

    private void fillUI() {
        ViewGroup dpContainer = (ViewGroup) mFragmentView.findViewById(R.id.dp_container);
        for (int i = 0; i < 20; i++) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.dp_item, null);
            if (i % 2 == 0) {
                view.setBackgroundColor(getResources().getColor(R.color.dark_big_block));
            } else {
                view.setBackgroundColor(getResources().getColor(R.color.light_big_block));
            }
            dpContainer.addView(view);
        }

        ViewGroup pxContainer = (ViewGroup) mFragmentView.findViewById(R.id.px_container);
        for (int i = 0; i < 40; i++) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.px_item, null);
            if (i % 2 == 0) {
                view.setBackgroundColor(getResources().getColor(R.color.dark_small_block));
            } else {
                view.setBackgroundColor(getResources().getColor(R.color.light_small_block));
            }
            pxContainer.addView(view);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        updateData();
    }

    private void updateData() {


        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                collect();

                View userContentView = getActivity().getWindow().findViewById(R.id.approot);
                TextView userContent = (TextView) findViewById(R.id.user_content);
                userContent.setText(Integer.toString(userContentView.getWidth()) + " x " + Integer.toString(userContentView.getHeight()));

            }
        }, 400);

        TableLayout table = (TableLayout) findViewById(R.id.table);
        for (int i = 0; i < table.getChildCount(); i++) {
            View view = table.getChildAt(i);

            if (view instanceof TableRow) {
                if (i % 2 == 0) {
                    view.setBackgroundColor(0xffeeeeee);
                } else {
                    view.setBackgroundColor(0xffdddddd);
                }
            }
        }

    }

    private void collect() {
        DisplayInfoCollector collector = DisplayInfoCollector.getInfo(getActivity());
        collector.collect();

        TextView linearDensityText = (TextView) findViewById(R.id.text_density_dpi_x);
        linearDensityText.setText(collector.getLinearDensity() + " dpi");

        TextView normalizedDensityText = (TextView) findViewById(R.id.text_normalized_density);
        normalizedDensityText.setText(collector.getNormalizedDensity() + " dpi");

        TextView orientation = (TextView) findViewById(R.id.orientation);
        if (collector.getNaturalOrientation() == DisplayInfoCollector.Orientation.PORTRAIT) {
            orientation.setText("portrait");
        } else {
            orientation.setText("landscape");
        }

        TextView resolution = (TextView) findViewById(R.id.resolution);
        resolution.setText(Integer.toString(collector.getRealWidth()) + " x " + Integer.toString(collector.getRealHeight()));

        int smallestWidth = getMeasuredDp(collector.getRealWidth(), collector.getRealHeight());

        TextView dp_size = (TextView) findViewById(R.id.dp_size);
        dp_size.setText(Integer.toString(smallestWidth) + " dp");

        TextView deviceClass = (TextView) findViewById(R.id.device_class);
        deviceClass.setText(smallestWidth >= 600 ? "Tablet" : "Smartphone");

        TextView hdrSupport = (TextView) findViewById(R.id.hdr_support);
        if (collector.supportsHDR() != null) {
            hdrSupport.setText(collector.supportsHDR() ? "Yes" : "No");
        } else {
            hdrSupport.setText("N/A");
        }
    }

    private int getMeasuredDp(int realWidth, int realHeight) {
        View firstDp = getActivity().getWindow().findViewById(R.id.first_dp);

        int firstDpWidth = firstDp.getWidth();

        if (realWidth > realHeight) {
            return realHeight * 100 / firstDpWidth;
        } else {
            return realWidth * 100 / firstDpWidth;
        }
    }

    @Override
    public String getName() {
        return "DISPLAY";
    }
}
