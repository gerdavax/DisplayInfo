package it.gerdavax.displayinfo;

import it.gerdavax.displayinfo.adapter.InfoPagerAdapter;
import it.gerdavax.displayinfo.collectors.DisplayInfoCollector;
import it.gerdavax.displayinfo.collectors.DisplayInfoCollector.Orientation;
import it.gerdavax.displayinfo.util.Utils;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class DisplayInfo extends FragmentActivity {
	private static final String TAG = "DisplayInfo";
	private ViewPager mPager;
    private InfoPagerAdapter mPagerAdapter;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		if (savedInstanceState == null || savedInstanceState.getString(TAG) == null) {
			Toast.makeText(this, "Made by http://www.gerdavax.it", Toast.LENGTH_SHORT).show();
		}

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        try {
			mActionBarToolbar.setTitle("DisplayInfo v" + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
		} catch (Exception e) {
			mActionBarToolbar.setTitle("DisplayInfo");
		}
        //mActionBarToolbar.setLogo(R.drawable.ic_launcher);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setOffscreenPageLimit(4);
        mPagerAdapter = new InfoPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(mPager, true);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putString(TAG, TAG);
	}

}
