package com.mysports.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mysports.R;
import com.mysports.bean.Academics;
import com.mysports.bean.VenueDetails;
import com.mysports.fragments.AcademiesFragment;

import java.util.ArrayList;

/**
 * Created by Jaison Joy on 1/2/2018.
 */

public class AcademicsRecycleView extends RecyclerView.Adapter<AcademicsRecycleView.AcademicsHolder> {
    Context mContext;
    ArrayList<Academics> mAcademicsList;
    RecyclerView mAcademicsGameRV;
    static OnItemClickListener onItemClickListener;

    public AcademicsRecycleView(Context mContext, ArrayList<Academics> mAcademicsList) {
        this.mContext = mContext;
        this.mAcademicsList = mAcademicsList;
    }

    @Override
    public AcademicsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardview_academies, parent, false);
        AcademicsHolder academicsHolderClass = new AcademicsHolder(view);
        return academicsHolderClass;
    }


    @Override
    public void onBindViewHolder(AcademicsHolder holder, int position) {
        Academics academics = mAcademicsList.get(position);
        holder.mMainHeading.setText(academics.getMainHeading());
        holder.subHeading.setText(academics.getSubHeading());
        holder.mAcademicsImage.setImageResource(academics.getmAcademicsImage());
        LinearLayoutManager venueDiplayItemlayoutManager = new LinearLayoutManager(mContext);
        venueDiplayItemlayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAcademicsGameRV.setLayoutManager(venueDiplayItemlayoutManager);
        AcademicsGameListRecycleView specificationRecycleView = new AcademicsGameListRecycleView(mContext, academics.getmGameList());
        mAcademicsGameRV.setAdapter(specificationRecycleView);
    }

    @Override
    public int getItemCount() {
        return mAcademicsList.size();
    }

    public class AcademicsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mMainHeading;
        TextView subHeading;
        ImageView mAcademicsImage;
        LinearLayout mAcademicsRegister;
        LinearLayout mLocation;

        public AcademicsHolder(View itemView) {
            super(itemView);
            mMainHeading = itemView.findViewById(R.id.main_heading);
            subHeading = itemView.findViewById(R.id.sub_title);
            mAcademicsGameRV = itemView.findViewById(R.id.academics_gamelist_rv);
            mAcademicsImage = itemView.findViewById(R.id.academics_image);
            mAcademicsRegister = itemView.findViewById(R.id.academics_register);
            mLocation = itemView.findViewById(R.id.load_map);
            mLocation.setOnClickListener(this);
            mAcademicsRegister.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
