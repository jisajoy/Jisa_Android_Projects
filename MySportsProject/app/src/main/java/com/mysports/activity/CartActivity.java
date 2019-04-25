package com.mysports.activity;

import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mysports.R;
import com.mysports.adapter.CartRecycleView;
import com.mysports.adapter.MyFavourtiesRecycleView;
import com.mysports.bean.CartBean;
import com.mysports.bean.MyFav;
import com.mysports.fragments.CartListFragment;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        CartListFragment cartListFragment = new CartListFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.cart_holder, cartListFragment, "fragment_cartlist_tag");
        fragmentTransaction.commit();
    }

}
