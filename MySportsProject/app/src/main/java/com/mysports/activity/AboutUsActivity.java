package com.mysports.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mysports.R;

public class AboutUsActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar mAboutToolBar;
    ImageView mBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        mAboutToolBar = findViewById(R.id.toolbar_about);
        TextView aboutTitle = mAboutToolBar.findViewById(R.id.toolbar_heading);
        aboutTitle.setText("About us");
        mBackBtn = mAboutToolBar.findViewById(R.id.back_arrow);
        mBackBtn.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_arrow) {
            finish();
        }
    }
}
