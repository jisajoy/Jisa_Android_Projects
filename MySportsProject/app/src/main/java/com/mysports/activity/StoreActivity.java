package com.mysports.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.mysports.R;
import com.mysports.fragments.StoreDetailFragment;
import com.mysports.fragments.StoreFragment;

public class StoreActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar mStoreToolBar;
    TextView mStoreTitle;
    ImageView mBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        mStoreToolBar = findViewById(R.id.toolbar_store);
        StoreFragment storeFragment = new StoreFragment();
        FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
        fragmentManager.add(R.id.store_ui_holder, storeFragment, "store_list_fragment_tag");
        fragmentManager.commit();
        mStoreTitle = findViewById(R.id.venue_title);
        mStoreTitle.setText("Store");
        mBackBtn = findViewById(R.id.venue_back_btn);
        mBackBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.venue_back_btn){
            StoreFragment storeFragment = (StoreFragment) getSupportFragmentManager().findFragmentByTag("store_list_fragment_tag");
           /* if (storeFragment != null && storeFragment.isVisible()){
                finish();
            }*/
            StoreDetailFragment storeDetailFragment = (StoreDetailFragment) getSupportFragmentManager().findFragmentByTag("store_detail_fragment_tag");
            if (storeDetailFragment != null && storeDetailFragment.isVisible()){
                System.out.println("StoreDetailFragment Visible");
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.popBackStack("store_detail_fragment_backstack", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }else {
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        StoreFragment storeFragment = (StoreFragment) getSupportFragmentManager().findFragmentByTag("store_list_fragment_tag");
        if (storeFragment != null && storeFragment.isVisible() && storeFragment.getChildFragmentManager().getBackStackEntryCount() > 0){
            storeFragment.getChildFragmentManager().popBackStack();
        }else {
            super.onBackPressed();
        }
    }
}
