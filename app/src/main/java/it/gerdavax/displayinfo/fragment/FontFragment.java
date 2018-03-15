package it.gerdavax.displayinfo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import it.gerdavax.displayinfo.R;

/**
 * Created by gerdavax on 08/11/2017.
 */

public class FontFragment extends BaseFragment {
    private class FontAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 60;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.font_item, null);
            }

            TextView fontTextView = (TextView) convertView.findViewById(R.id.font);
            fontTextView.setTextSize(position + 1);
            fontTextView.setText(Integer.toString(position + 1) + " sp");

            return convertView;
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mFragmentView = inflater.inflate(R.layout.fragment_font, null, false);

        ListView fontList = (ListView) mFragmentView.findViewById(R.id.font_drawer);
        FontAdapter adapter = new FontAdapter();
        fontList.setAdapter(adapter);

        return mFragmentView;
    }

    @Override
    public String getName() {
        return "FONT";
    }
}
