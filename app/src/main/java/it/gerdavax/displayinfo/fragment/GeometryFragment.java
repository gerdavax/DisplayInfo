package it.gerdavax.displayinfo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import it.gerdavax.displayinfo.R;

/**
 * Created by gerdavax on 17/11/2017.
 */

public class GeometryFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_geometry, container, false);

        fillUI();

        return mFragmentView;
    }

    private void fillUI() {
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

        ViewGroup dpContainer = (ViewGroup) mFragmentView.findViewById(R.id.dp_container);
        for (int i = 0; i < 20; i++) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.dp_item, null);
            if (i % 2 == 0) {
                view.setBackgroundColor(Color.YELLOW);
            } else {
                view.setBackgroundColor(Color.RED);
            }
            dpContainer.addView(view);
        }

        ViewGroup dpRealContainer = (ViewGroup) mFragmentView.findViewById(R.id.dp_real_container);
        int lato = (int) (getResources().getDimension(R.dimen.size_100dp) * (3.0 / mCollector.getDensityScale()));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(lato, lato);
        dpRealContainer.findViewById(R.id.first_dp_real).setLayoutParams(params);
        for (int i = 0; i < 20; i++) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.dp_item_real, null);
            view.setLayoutParams(params);
            //view.getLayoutParams().height = (int) (view.getLayoutParams().height * mCollector.getDensityScale());

            if (i % 2 == 0) {
                view.setBackgroundColor(getResources().getColor(R.color.dark_big_block));
            } else {
                view.setBackgroundColor(getResources().getColor(R.color.light_big_block));
            }
            dpRealContainer.addView(view);
        }

    }

    @Override
    public String getName() {
        return "GEOM";
    }
}
