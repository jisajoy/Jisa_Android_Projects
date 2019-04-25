package com.mysports.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.mysports.R;
import com.mysports.fragments.VenueImagesFragment;
import com.mysports.fragments.VenueVideosFragment;
import com.mysports.interfacepackage.OnVenueFragmentInterationListener;

import java.util.ArrayList;
import java.util.List;

public class ImageVedioActivity extends AppCompatActivity implements OnVenueFragmentInterationListener, View.OnClickListener {
    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar mImageVedioToolBar;
    ImageView mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_vedio);
        mImageVedioToolBar = findViewById(R.id.toolbar_imagevedio);
        TextView mToolBarTitle = mImageVedioToolBar.findViewById(R.id.venue_title);
        mToolBarTitle.setText("My Sports");
        mBackButton = mImageVedioToolBar.findViewById(R.id.venue_back_btn);
        mBackButton.setOnClickListener(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager2);
        createViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#CA2027"));
        createTabIcons();
    }

    private void createTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Images");
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Videos");
        tabLayout.getTabAt(1).setCustomView(tabTwo);
    }

    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new VenueImagesFragment(), "Images");
        adapter.addFrag(new VenueVideosFragment(), "Videos");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.venue_back_btn) {
            finish();
        }
    }


    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        /*@Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }*/

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }


        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }

    @Override
    public void onChangeTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
