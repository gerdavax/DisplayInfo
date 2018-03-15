package it.gerdavax.displayinfo.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

import it.gerdavax.displayinfo.R;
import it.gerdavax.displayinfo.collectors.DisplayInfoCollector;

/**
 * Created by gerdavax on 08/11/2017.
 */

public abstract class BaseFragment extends Fragment {
    protected Handler mHandler = new Handler();
    protected View mFragmentView;
    protected DisplayInfoCollector mCollector;

    protected View findViewById(int id) {
        return mFragmentView.findViewById(id);
    }

    public abstract String getName();

    protected void paintTable() {
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mCollector = DisplayInfoCollector.getInfo(context);
    }
}
