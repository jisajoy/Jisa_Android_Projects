package com.mysports.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.github.jksiezni.permissive.PermissionsGrantedListener;
import com.github.jksiezni.permissive.PermissionsRefusedListener;
import com.github.jksiezni.permissive.Permissive;
import com.mysports.R;
import com.mysports.adapter.HoursRateRecycleView;
import com.mysports.adapter.VenueFilterAdapter;
import com.mysports.adapter.VenueSpecificationRecycleView;
import com.mysports.bean.VenueFilterBean;
import com.mysports.bean.VenueSpecificationBean;

import java.util.ArrayList;

public class AcademicsDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager mAcademicsViewpager;
    int[] mResources;
    CustomPagerAdapter mCustomPagerAdapter;
    RecyclerView mTrainingRv;
    RecyclerView mSpecification;
    Toolbar mAcademicsToolbar;
    ImageView mBackBtn;
    RelativeLayout mContactDetail;
    Dialog contactDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academics_details);
        mContactDetail = findViewById(R.id.contact_details);
        mContactDetail.setOnClickListener(this);
        mAcademicsToolbar = findViewById(R.id.academics_toolbar);
        TextView mToolbarTitle = mAcademicsToolbar.findViewById(R.id.venue_title);
        mToolbarTitle.setText("Academies");
        mBackBtn = mAcademicsToolbar.findViewById(R.id.venue_back_btn);
        mBackBtn.setOnClickListener(this);
        mResources = new int[]{
                R.drawable.football1,
                R.drawable.football2,
                R.drawable.football3,
        };
        mCustomPagerAdapter = new CustomPagerAdapter(this);

        mAcademicsViewpager = (ViewPager) findViewById(R.id.academics_detail_pager);
        mAcademicsViewpager.setAdapter(mCustomPagerAdapter);
        mTrainingRv = findViewById(R.id.training_rv);
        mSpecification = findViewById(R.id.specific_rv);
        trainingList();
        specificationList();
    }

    private void specificationList() {
        ArrayList<VenueSpecificationBean> mSpecificationList = new ArrayList<>();
        String[] mSpecficationTitle = {"Booking", "Washroom", "Parking", "Equipments", "Indoor"};
        int[] mSpecificationIcon = {R.drawable.ic_booking, R.drawable.ic_washroom, R.drawable.ic_parking, R.drawable.ic_equipments, R.drawable.ic_indoor};
        for (int i = 0; i < mSpecficationTitle.length; i++) {
            VenueSpecificationBean specificationBean = new VenueSpecificationBean();
            specificationBean.setmSpecTitle(mSpecficationTitle[i]);
            specificationBean.setmSpecImage(mSpecificationIcon[i]);
            mSpecificationList.add(specificationBean);
        }
        VenueSpecificationRecycleView venueRecycleView = new VenueSpecificationRecycleView(this, mSpecificationList);
        mSpecification.setAdapter(venueRecycleView);
        mSpecification.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void trainingList() {
        int[] mGameImage = new int[]{R.drawable.ic_cock, R.drawable.ic_basket_court, R.drawable.ic_basket_ball, R.drawable.ic_football, R.drawable.ic_cock, R.drawable.ic_basket_court, R.drawable.ic_basket_ball, R.drawable.ic_football};
        ArrayList<VenueFilterBean> mGameArrayList = new ArrayList<>();
        for (int i = 0; i < mGameImage.length; i++) {
            VenueFilterBean venueFilterBean = new VenueFilterBean();
            venueFilterBean.setGameImage(mGameImage[i]);
            mGameArrayList.add(venueFilterBean);
        }
        VenueFilterAdapter venueFilterAdapter = new VenueFilterAdapter(this, mGameArrayList, "Academics");
        mTrainingRv.setAdapter(venueFilterAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mTrainingRv.setLayoutManager(layoutManager);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.venue_back_btn) {
            finish();
        }
        if (v.getId() == R.id.contact_details) {
            contactDetails();
        }
    }

    public class CustomPagerAdapter extends PagerAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            this.mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mResources.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ConstraintLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.image_pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.venue_image);
            imageView.setImageResource(mResources[position]);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ConstraintLayout) object);
        }
    }

    //contact layout need to design
    private void contactDetails() {
        contactDialog = new Dialog(this, R.style.Theme_AppCompat_Dialog);
        contactDialog.setTitle(null);
        Window window = contactDialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        contactDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        contactDialog.setContentView(R.layout.contact_layout);
        contactDialog.setCancelable(false);
        contactDialog.setCanceledOnTouchOutside(false);
        contactDialog.show();
        TextView phoneNumber = contactDialog.findViewById(R.id.phone_number);
        TextView emailID = contactDialog.findViewById(R.id.emailid);

        final String phone = phoneNumber.getText().toString();
        final String emailId = emailID.getText().toString();

        RelativeLayout callPhone = contactDialog.findViewById(R.id.call_phone);
        callPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneCall(phone);
                contactDialog.dismiss();
            }
        });
        RelativeLayout contactEmail = contactDialog.findViewById(R.id.contact_email);
        contactEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactEmailId(emailId);
                contactDialog.dismiss();
            }
        });

        TextView close = (TextView) contactDialog.findViewById(R.id.txt_dialog_ok);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactDialog.dismiss();

            }
        });
    }

    private void contactEmailId(String emailId) {
        Log.d("emailid", emailId);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);// if bug occur change on this line only
        String uriText = "mailto:" + emailId + "?subject=" + "&body="; //+ html;
        uriText = uriText.replace(" ", "%20");
        Uri uri = Uri.parse(uriText);
        emailIntent.setData(uri);
        startActivity(emailIntent);
    }

    private void phoneCall(final String phone) {
        if (!Permissive.checkPermission(AcademicsDetailsActivity.this, Manifest.permission.CALL_PHONE)) {
            new Permissive.Request(Manifest.permission.CALL_PHONE)
                    .whenPermissionsGranted(new PermissionsGrantedListener() {
                        @Override
                        public void onPermissionsGranted(String[] permissions) throws SecurityException {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" + phone));
                            startActivity(callIntent);
                        }
                    })
                    .whenPermissionsRefused(new PermissionsRefusedListener() {
                        @Override
                        public void onPermissionsRefused(String[] permissions) {
                            Toast.makeText(AcademicsDetailsActivity.this, "Please Grant Permission", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .execute(AcademicsDetailsActivity.this);
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phone));
            startActivity(callIntent);
        }

    }
}
