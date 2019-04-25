package com.mysports.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mysports.R;
import com.mysports.adapter.StoreRecycleView;
import com.mysports.adapter.TrainerRecycleView;
import com.mysports.bean.Store;
import com.mysports.bean.Trainer;

import java.util.ArrayList;


public class TrainerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView mTrainerRV;
    ArrayList<Trainer> mTrainerList;

    public TrainerFragment() {
        // Required empty public constructor
    }

    public static TrainerFragment newInstance(String param1, String param2) {
        TrainerFragment fragment = new TrainerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trainer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTrainerRV = view.findViewById(R.id.trainer_rv);
        dataForTrainer();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        mTrainerRV.setLayoutManager(layoutManager);
        TrainerRecycleView trainerRecycleView = new TrainerRecycleView(getActivity(), mTrainerList);
        mTrainerRV.setAdapter(trainerRecycleView);
        trainerRecycleView.SetOnItemClickListener(new TrainerRecycleView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TrainerDetailFragment trainerDetailFragment = new TrainerDetailFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.trainer_ui_holder, trainerDetailFragment, "fragment_trainer_detail_tag");
                fragmentTransaction.addToBackStack("fragment_backstack_trainer_detail");
                fragmentTransaction.commit();
            }
        });
    }

    private void dataForTrainer() {
        mTrainerList = new ArrayList<>();
        String[] trainerName = {"MJ Manoj", "Johnson Oma Obrunu", "Lucas Pereira", "Denys Klymenko",
                "MJ Manoj", "Johnson Oma Obrunu", "Lucas Pereira", "Denys Klymenko"};
        String[] trainerSpec = {"Dubai Badminton Trainer", "Football & Sports Activates Director", "Football Trainer", "Football Trainer", "Dubai Badminton Trainer", "Football & Sports Activates Director", "Football Trainer", "Football Trainer"};

        String[] trainerImage = {"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPjgml6iS5oF3YHeKVK2tU0sTOLqRx1tq3PDv9NbEV7064rqcqsQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPjgml6iS5oF3YHeKVK2tU0sTOLqRx1tq3PDv9NbEV7064rqcqsQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPjgml6iS5oF3YHeKVK2tU0sTOLqRx1tq3PDv9NbEV7064rqcqsQ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1T2X5E_ZSp9f1s6eg7BYN7aDWHzgdU6O_FlUcNATIZgblqJkqQ"};
        for (int i = 0; i < trainerName.length; i++) {
            Trainer trainer = new Trainer();
            trainer.setmTrainerName(trainerName[i]);
            trainer.setmTrainerSpec(trainerSpec[i]);
            trainer.setmTrainerImage(trainerImage[i]);
            mTrainerList.add(trainer);
        }
    }
}
