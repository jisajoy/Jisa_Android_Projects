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
import com.mysports.fragments.StoreFragment;
import com.mysports.fragments.TrainerDetailFragment;
import com.mysports.fragments.TrainerFragment;

public class TrainersActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar mTrainerToolBar;
    ImageView mTrainerBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainers);
        mTrainerToolBar = findViewById(R.id.toolbar_trainers);
        TextView trainerTitle = mTrainerToolBar.findViewById(R.id.venue_title);
        trainerTitle.setText("Trainer");
        mTrainerBack = findViewById(R.id.venue_back_btn);
        mTrainerBack.setOnClickListener(this);
        TrainerFragment trainerFragment = new TrainerFragment();
        FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
        fragmentManager.add(R.id.trainer_ui_holder, trainerFragment, "fragment_trainer_list_tag");
        /*fragmentManager.addToBackStack("trainer_fragment");*/
        fragmentManager.commit();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.venue_back_btn) {
            /*TrainerFragment trainerFragment = (TrainerFragment) getSupportFragmentManager().findFragmentByTag("fragment_trainer_list_tag");
            if (trainerFragment != null && trainerFragment.isVisible()){
                finish();
            }*/
            TrainerDetailFragment trainerDetailFragment = (TrainerDetailFragment) getSupportFragmentManager().findFragmentByTag("fragment_trainer_detail_tag");
            if (trainerDetailFragment != null && trainerDetailFragment.isVisible()){
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.popBackStack("fragment_backstack_trainer_detail", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }else {
                finish();
            }
        }
    }
}
