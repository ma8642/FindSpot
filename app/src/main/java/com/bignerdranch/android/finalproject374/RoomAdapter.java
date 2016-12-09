package com.bignerdranch.android.finalproject374;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Wren on 12/4/2016.
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomViewHolder>{


    private Activity mActivity;
    List<String> mIndices = Collections.emptyList();  //List that is the same size as hashmap so that we can reference a hashmap key using array index
    HashMap<String, Room> mRooms;
    private OnTapListener OnTapListener;

    public RoomAdapter(Activity activity, HashMap<String, Room> mRooms, List<String> mIndices){
        this.mActivity = activity;
        this.mIndices = mIndices;
        this.mRooms = mRooms;
    }

    @Override
    public int getItemCount() {
        //return roomInfoList.size();
        return mRooms.size();
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new RoomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RoomViewHolder roomViewHolder, final int i) {
        //RoomInfo ri = roomInfoList.get(i);
        String key = mIndices.get(i);
        Room ri = mRooms.get(key);
        roomViewHolder.vRoom.setText(ri.getBuilding() + " " + ri.getRoomNum());
        roomViewHolder.vProfessor.setText("Professor 1");
        roomViewHolder.vProfessor2.setText("Professor 2");
        roomViewHolder.vClassName.setText("Class Name");
        roomViewHolder.vTime.setText("Free Until:");

        roomViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (OnTapListener != null){
                    OnTapListener.OnTapView(i);
                }
            }
            });
    }



    public void setOnTapListener(OnTapListener onTapListener) {
        this.OnTapListener = onTapListener;
    }
}
