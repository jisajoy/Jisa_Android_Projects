package com.mysports.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.mysports.R;
import com.mysports.adapter.AcademicsRecycleView;
import com.mysports.adapter.MyFavourtiesRecycleView;
import com.mysports.bean.Academics;
import com.mysports.bean.MyFav;

import java.util.ArrayList;

public class MyFavourtiesActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView mFavourtiesRv;
    ArrayList<MyFav> mFavList;
    ImageView mBackBtn;
    Toolbar mFavToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourties);
        mFavToolBar = findViewById(R.id.toolbar_favorite);
        mFavourtiesRv = findViewById(R.id.favorites_RV);
        mBackBtn = mFavToolBar.findViewById(R.id.back_arrow);
        mBackBtn.setOnClickListener(this);
        dataForFav();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mFavourtiesRv.setLayoutManager(layoutManager);

        MyFavourtiesRecycleView academicsRecycleView = new MyFavourtiesRecycleView(this, mFavList);
        mFavourtiesRv.setAdapter(academicsRecycleView);
    }

    private void dataForFav() {
        mFavList = new ArrayList<>();
        String[] favProductName = {"Nike Mens 454816-488 FS T-Shirt Tight XL 80 AED", "Nike Mens 454816-488 FS T-Shirt Tight XL 80 AED", "Nike Mens 454816-488 FS T-Shirt Tight XL 80 AED"};
        int[] favProductImage = {R.drawable.game_logo, R.drawable.game_logo, R.drawable.game_logo};
        for (int i = 0; i < favProductName.length; i++) {
            MyFav myFav = new MyFav();
            myFav.setmFavProductName(favProductName[i]);
            myFav.setmFavProductImage(favProductImage[i]);
            mFavList.add(myFav);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_arrow) {
            finish();
        }
    }
}
