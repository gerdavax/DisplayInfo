package it.gerdavax.displayinfo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import it.gerdavax.displayinfo.fragment.AndroidFragment;
import it.gerdavax.displayinfo.fragment.BaseFragment;
import it.gerdavax.displayinfo.fragment.DisplayFragment;
import it.gerdavax.displayinfo.fragment.FontFragment;
import it.gerdavax.displayinfo.fragment.GeometryFragment;

/**
 * Created by gerdavax on 08/11/2017.
 */

public class InfoPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragments = new ArrayList<>();

    public InfoPagerAdapter(FragmentManager fm) {
        super(fm);

        mFragments.add(new DisplayFragment());
        mFragments.add(new GeometryFragment());
        mFragments.add(new FontFragment());
        mFragments.add(new AndroidFragment());

    }

    @Override
    public BaseFragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getItem(position).getName();
    }
}
