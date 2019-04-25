package com.mysports.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mysports.R;
import com.mysports.bean.Trainer;

import java.util.ArrayList;

/**
 * Created by Jaison Joy on 1/9/2018.
 */

public class TrainerRecycleView extends RecyclerView.Adapter<TrainerRecycleView.TrainerViewHolder> {
    Context mContext;
    ArrayList<Trainer> mTrainerList;
    static OnItemClickListener clickListener;

    public TrainerRecycleView(Context mContext, ArrayList<Trainer> mTrainerList) {
        this.mContext = mContext;
        this.mTrainerList = mTrainerList;
    }

    @Override
    public TrainerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardview_trainer, parent, false);
        TrainerViewHolder viewHolder = new TrainerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrainerViewHolder holder, int position) {
        Trainer trainer = mTrainerList.get(position);
        holder.mTrainerName.setText(trainer.getmTrainerName());
        holder.mTrainerSpec.setText(trainer.getmTrainerSpec());
        Glide.with(mContext).load(trainer.getmTrainerImage()).into(holder.mTrainerimage);
    }

    @Override
    public int getItemCount() {
        return mTrainerList.size();
    }

    public class TrainerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mTrainerimage;
        TextView mTrainerName;
        TextView mTrainerSpec;

        public TrainerViewHolder(View itemView) {
            super(itemView);
            mTrainerimage = itemView.findViewById(R.id.trainer_image);
            mTrainerName = itemView.findViewById(R.id.trainer_name);
            mTrainerSpec = itemView.findViewById(R.id.trainer_spec);
            itemView.setOnClickListener(this);
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
