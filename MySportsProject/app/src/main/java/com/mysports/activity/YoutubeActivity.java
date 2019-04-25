package com.mysports.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.mysports.R;
import com.mysports.bean.GalleryBean;
import com.mysports.utilities.TouchImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class YoutubeActivity extends YouTubeBaseActivity implements View.OnClickListener {
    ViewPager mGalleryViewpager;
    CustomPagerAdapter mCustomPagerAdapter;
    ArrayList<GalleryBean> mGalleryList;
    RelativeLayout mDoneGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        galleryimageList();
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("image_position");
        mGalleryViewpager = findViewById(R.id.gallery_viewpager);
        mCustomPagerAdapter = new CustomPagerAdapter(this);
        mGalleryViewpager.setAdapter(mCustomPagerAdapter);
        mGalleryViewpager.setCurrentItem(position);
        mDoneGallery = findViewById(R.id.done_gallery);
        mDoneGallery.setOnClickListener(this);

    }

    private void galleryimageList() {
        mGalleryList = new ArrayList<>();
        String[] galleryList = new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwUR7Qvw0YAXeNX8gP9_OCOCfkK3gmUs57zOsG7RtDtK-lCwzCHg", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKnoMpGLQQic7WRyd9k5b_OXpWRB42otzXOpBvCfelDhdwxBjYqQ",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQfqNRrsDy2DEHpnMyT5BZyDPbEcHMZEGlxuQonG7uBuA-n6Uze", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVPiFZVOzl4YtwlYueYFYeCpe9D8QtYKA0jkXdBtO8BDmwY7xADQ"};
        String[] vedioList = new String[]{"KrnG42-UyZE", "1YvAY7tF6rA", "S78DMLYb8Pw", "jWIoU3VXcNI"};
        for (int i = 0; i < galleryList.length; i++) {
            GalleryBean galleryBean = new GalleryBean();
            galleryBean.setImageURL(galleryList[i]);
            galleryBean.setVedioURL(vedioList[i]);
            mGalleryList.add(galleryBean);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.done_gallery) {
            finish();
        }
    }

    public class CustomPagerAdapter extends PagerAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;
        YouTubePlayer.OnInitializedListener onInitializedListener;
        YouTubePlayerView youTubePlayerView;
        public CustomPagerAdapter(Context context) {
            this.mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mGalleryList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ConstraintLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.vedio_image, container, false);
            youTubePlayerView = itemView.findViewById(R.id.youtube_vedio);
            Button playVedio = itemView.findViewById(R.id.vedio_play);
            playVedio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    youTubePlayerView.initialize(getString(R.string.youtube_api_key), onInitializedListener);
                }
            });

            final GalleryBean galleryBean = mGalleryList.get(position);
            onInitializedListener = new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    System.out.println("vedio_url "+galleryBean.getVedioURL());
                    youTubePlayer.loadVideo(galleryBean.getVedioURL());
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                    Toast.makeText(YoutubeActivity.this, "Vedio couldn't load", Toast.LENGTH_SHORT).show();
                }
            };
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ConstraintLayout) object);
        }
    }
}
