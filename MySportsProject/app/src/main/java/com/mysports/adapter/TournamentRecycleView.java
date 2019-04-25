package com.mysports.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mysports.R;
import com.mysports.bean.Tournament;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Jaison Joy on 1/2/2018.
 */

public class TournamentRecycleView extends RecyclerView.Adapter<TournamentRecycleView.TournamentViewHolder> {
    Context mContext;
    ArrayList<Tournament> mTournamentList;
    static TournamentClickListener tournamentClickListener;

    public TournamentRecycleView(Context mContext, ArrayList<Tournament> mTournamentList) {
        this.mContext = mContext;
        this.mTournamentList = mTournamentList;
    }

    @Override
    public TournamentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardview_tournaments, parent, false);
        TournamentViewHolder viewHolder = new TournamentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TournamentViewHolder holder, int position) {
        Tournament tournament = mTournamentList.get(position);
        holder.mTournamanetMainHeading.setText(tournament.getmTournamentMainHeading());
        holder.mTournamanetDate.setText(tournament.getmTournamentDate());
        holder.mTournamanetPlace.setText(tournament.getmTournamentPlace());
        Picasso.with(mContext).load(tournament.getmTournamentImage()).into(holder.mTournamentImage);
    }

    @Override
    public int getItemCount() {
        return mTournamentList.size();
    }

    public class TournamentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mTournamentImage;
        TextView mTournamanetMainHeading;
        TextView mTournamanetPlace;
        TextView mTournamanetDate;
        LinearLayout mTournamentRegister;
LinearLayout mLoadMap;
        public TournamentViewHolder(View itemView) {
            super(itemView);
            mTournamanetMainHeading = itemView.findViewById(R.id.tournament_mainheading);
            mTournamentImage = itemView.findViewById(R.id.tournament_image);
            mTournamanetPlace = itemView.findViewById(R.id.tournament_place);
            mTournamanetDate = itemView.findViewById(R.id.tournament_date);
            mTournamentRegister = itemView.findViewById(R.id.register_tournament);
            mLoadMap = itemView.findViewById(R.id.load_map);
            mTournamentRegister.setOnClickListener(this);
            mLoadMap.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            tournamentClickListener.tourclickListener(v, getAdapterPosition());
        }
    }

    public interface TournamentClickListener {
        public void tourclickListener(View view, int position);
    }

    public void SetOnClickListener(TournamentClickListener tournamentClickListener){
        this.tournamentClickListener = tournamentClickListener;
    }
}
