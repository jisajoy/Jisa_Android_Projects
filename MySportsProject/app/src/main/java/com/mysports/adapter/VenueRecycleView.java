package com.mysports.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mysports.R;
import com.mysports.activity.VenuesActivity;
import com.mysports.bean.VenueDetails;

import java.util.ArrayList;

/**
 * Created by Jaison Joy on 12/18/2017.
 */

public class VenueRecycleView extends RecyclerView.Adapter<VenueRecycleView.VenueHolderClass>{
    Context mContext;
    ArrayList<VenueDetails> mVenueList;
    static OnItemClickListener clickListener;
    RecyclerView mAbstractItemViewRV;
    Dialog mLocationDialog;
    GoogleMap myMap;

    public VenueRecycleView(Context mContext, ArrayList<VenueDetails> mVenueList) {
        this.mContext = mContext;
        this.mVenueList = mVenueList;
    }

    @Override
    public VenueHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardview_venues, parent, false);
        VenueHolderClass venueHolderClass = new VenueHolderClass(view);
        return venueHolderClass;
    }

    @Override
    public void onBindViewHolder(VenueHolderClass holder, int position) {
        VenueDetails venueDetails = mVenueList.get(position);
        holder.mTitleLargeHeading.setText(venueDetails.getMainHeading());
        holder.mTitleSmallHeading.setText(venueDetails.getSmallTitle());
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAbstractItemViewRV.setLayoutManager(layoutManager);
        VenueIconRecycleView venueRecycleView = new VenueIconRecycleView(mContext, venueDetails.getmCardIconList());
        mAbstractItemViewRV.setAdapter(venueRecycleView);
        Glide.with(mContext).load(venueDetails.getVenueImage()).into(holder.mVenueImage);
    }

    @Override
    public int getItemCount() {
        return mVenueList.size();
    }


    public class VenueHolderClass extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTitleLargeHeading;
        TextView mTitleSmallHeading;
        ImageView mVenueImage;
        LinearLayout mBookNow;
        LinearLayout mViewLocation;

        public VenueHolderClass(View itemView) {
            super(itemView);
            mTitleLargeHeading = itemView.findViewById(R.id.heading_textview);
            mTitleSmallHeading = itemView.findViewById(R.id.bottom_text);
            mVenueImage = itemView.findViewById(R.id.venue_image);
            mBookNow = itemView.findViewById(R.id.book_now);
            mAbstractItemViewRV = itemView.findViewById(R.id.item_into_display);
            mViewLocation = itemView.findViewById(R.id.get_location);
            mBookNow.setOnClickListener(this);
            mViewLocation.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
