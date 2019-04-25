package com.mysports.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mysports.R;
import com.mysports.activity.TournamentDetailActivity;
import com.mysports.adapter.TournamentRecycleView;
import com.mysports.bean.Tournament;
import com.mysports.interfacepackage.OnFragmentInteractionListener;

import java.util.ArrayList;


public class TournamentsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String LOC_PASS = "location_pass";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OnFragmentInteractionListener mListener;
    RecyclerView mTournamentRv;
    ArrayList<Tournament> mTournamentList;

    public TournamentsFragment() {
        // Required empty public constructor
    }

    public static TournamentsFragment newInstance(String param1, String param2) {
        TournamentsFragment fragment = new TournamentsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tournaments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTournamentRv = view.findViewById(R.id.tournament_rv);
        dataForTournament();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mTournamentRv.setLayoutManager(layoutManager);

        TournamentRecycleView tournamentRecycleView = new TournamentRecycleView(getActivity(), mTournamentList);
        mTournamentRv.setAdapter(tournamentRecycleView);

        tournamentRecycleView.SetOnClickListener(new TournamentRecycleView.TournamentClickListener() {
            @Override
            public void tourclickListener(View view, int position) {
                if (view.getId() == R.id.register_tournament) {
                    Intent tournamentNewIntent = new Intent(getActivity(), TournamentDetailActivity.class);
                    startActivity(tournamentNewIntent);
                }
                if (view.getId() == R.id.load_map){
                    LocationDialogFragment locationDialogFragment = new LocationDialogFragment();
                    if (getChildFragmentManager().findFragmentById(R.id.tour_map_loader) != null) {
                        getChildFragmentManager()
                                .beginTransaction().
                                remove(getChildFragmentManager().findFragmentById(R.id.tour_map_loader)).commit();
                    }
                    Bundle args = new Bundle();
                    args.putString(LOC_PASS, "tournament");
                    locationDialogFragment.setArguments(args);
                    getChildFragmentManager()
                            .beginTransaction()
                            .addToBackStack("academics_backstack")
                            .replace(R.id.tour_map_loader, locationDialogFragment, "venue_location_fragment_tag")
                            .commit();
                }
            }
        });
    }

    private void dataForTournament() {

        mTournamentList = new ArrayList<>();
        String[] academicsMainHeading = getResources().getStringArray(R.array.name);
        String[] academicsSubHeading = getResources().getStringArray(R.array.place);
        String[] academicsDate = getResources().getStringArray(R.array.date);
        String[] tournamentImage = {"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPjgml6iS5oF3YHeKVK2tU0sTOLqRx1tq3PDv9NbEV7064rqcqsQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPjgml6iS5oF3YHeKVK2tU0sTOLqRx1tq3PDv9NbEV7064rqcqsQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPjgml6iS5oF3YHeKVK2tU0sTOLqRx1tq3PDv9NbEV7064rqcqsQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPjgml6iS5oF3YHeKVK2tU0sTOLqRx1tq3PDv9NbEV7064rqcqsQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ"};
        for (int i = 0; i < academicsMainHeading.length; i++) {
            Tournament tournament = new Tournament();
            tournament.setmTournamentMainHeading(academicsMainHeading[i]);
            tournament.setmTournamentPlace(academicsSubHeading[i]);
            tournament.setmTournamentDate(academicsDate[i]);
            tournament.setmTournamentImage(tournamentImage[i]);
            mTournamentList.add(tournament);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
