package com.mysports.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mysports.R;
import com.mysports.fragments.AcademiesFragment;
import com.mysports.fragments.HomeFragment;
import com.mysports.fragments.ListWithUsFragment;
import com.mysports.fragments.LocationDialogFragment;
import com.mysports.fragments.TournamentsFragment;
import com.mysports.interfacepackage.OnFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener, View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Toolbar mMainToolbar;
    TextView tabOne;
    TextView tabTwo;
    TextView tabThree;
    TextView tabFour;
    TextView mToolBarHeading;
    boolean doubleBackToExitPressedOnce = false;
    RelativeLayout mChooseCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainToolbar = findViewById(R.id.drawer_toolbar);
        mToolBarHeading = mMainToolbar.findViewById(R.id.main_toolbar_heading);
        mChooseCart = mMainToolbar.findViewById(R.id.choose_cart);
        mChooseCart.setOnClickListener(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mMainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        viewPager = (ViewPager) findViewById(R.id.viewpager2);
        createViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#CA2027"));
        createTabIcons();
        selectTabIcon(1);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Fragment academicsFragment = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager2 + ":" + viewPager.getCurrentItem());
            Fragment tournamentFragment = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager2 + ":" + viewPager.getCurrentItem());
            if (academicsFragment.getChildFragmentManager().getBackStackEntryCount() > 0 || tournamentFragment.getChildFragmentManager().getBackStackEntryCount() > 0) {
                if (viewPager.getCurrentItem() == 1) {
                    academicsFragment.getChildFragmentManager().popBackStack();
                }
                if (viewPager.getCurrentItem() == 2) {
                    tournamentFragment.getChildFragmentManager().popBackStack();
                }
            } else {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    return;
                }
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Please click BACK again to exit MySports", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }

        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_spots_store) {

        } else if (id == R.id.nav_academics) {
            Intent newAcademicsIntent = new Intent(this, CartActivity.class);
            startActivity(newAcademicsIntent);
        } else if (id == R.id.nav_tournament) {

        } else if (id == R.id.nav_listwithus) {

        } else if (id == R.id.nav_myaccount) {
            Intent myAccountIntent = new Intent(this, MyAccountActivity.class);
            startActivity(myAccountIntent);
        } else if (id == R.id.nav_mywallet) {

        } else if (id == R.id.nav_mytournament) {

        } else if (id == R.id.nav_myfavourite) {
            Intent newMYFavIntent = new Intent(this, MyFavourtiesActivity.class);
            startActivity(newMYFavIntent);

        } else if (id == R.id.nav_aboutus) {
            Intent newAboutUsIntent = new Intent(this, AboutUsActivity.class);
            startActivity(newAboutUsIntent);
        } else if (id == R.id.nav_trainers) {
            Intent trainersNewIntent = new Intent(this, TrainersActivity.class);
            startActivity(trainersNewIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new HomeFragment(), "Home");
        adapter.addFrag(new AcademiesFragment(), "Academies");
        adapter.addFrag(new TournamentsFragment(), "Tournaments");
        adapter.addFrag(new ListWithUsFragment(), "ListWithUs");
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    selectTabIcon(1);
                }
                if (position == 1) {
                    selectTabIcon(2);
                }
                if (position == 2) {
                    selectTabIcon(3);
                }
                if (position == 3) {
                    selectTabIcon(4);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void createTabIcons() {

        tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Home");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_home, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Academies");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_home, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("Tournaments");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_tour, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFour.setText("List With Us");
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_listwithus, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);
    }

    @Override
    public void onChangeTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.choose_cart){
            Intent cartFilter = new Intent(this, CartActivity.class);
            startActivity(cartFilter);
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


    public void selectTabIcon(int position) {
        switch (position) {
            case 1:
                mToolBarHeading.setText("My Sports");
                tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_home_w, 0, 0);
                tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_home, 0, 0);
                tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_tour, 0, 0);
                tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_listwithus, 0, 0);
                break;
            case 2:
                mToolBarHeading.setText("Academies");
                tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_home, 0, 0);
                tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_home_w, 0, 0);
                tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_tour, 0, 0);
                tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_listwithus, 0, 0);
                break;
            case 3:
                mToolBarHeading.setText("Tournaments");
                tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_home, 0, 0);
                tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_home, 0, 0);
                tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_tour_w, 0, 0);
                tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_listwithus, 0, 0);
                break;
            case 4:
                mToolBarHeading.setText("ListWithUs");
                tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_home, 0, 0);
                tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_home, 0, 0);
                tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_tour, 0, 0);
                tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_listwithus_w, 0, 0);
                break;
        }
    }
}
