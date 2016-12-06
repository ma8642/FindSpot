package com.bignerdranch.android.finalproject374;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Wren on 12/4/2016.
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder>{

private List<RoomInfo> roomInfoList;

    public RoomAdapter(List<RoomInfo> roomInfoList) {
        this.roomInfoList = roomInfoList;
    }

    @Override
    public int getItemCount() {
        return roomInfoList.size();
    }

    @Override
    public void onBindViewHolder(RoomViewHolder roomViewHolder, int i) {
        RoomInfo ri = roomInfoList.get(i);
        //roomViewHolder.vRoom.setText(ri.room);
        //roomViewHolder.vProfessor.setText(ri.professor);
        //roomViewHolder.vProfessor2.setText(ri.professor2);
        //roomViewHolder.vClassName.setText(ri.professor);
        //roomViewHolder.vTime.setText(ri.time);
        roomViewHolder.vRoom.setText("ROOM NAME AND NUMBER");
        roomViewHolder.vProfessor.setText("Professor 1");
        roomViewHolder.vProfessor2.setText("Professor 2");
        roomViewHolder.vClassName.setText("Name of Class");
        roomViewHolder.vTime.setText("Time of Class");
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new RoomViewHolder(itemView);
    }

    //Display Recyler View List of FreeRooma



    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        protected TextView vRoom;
        protected TextView vProfessor;
        protected TextView vProfessor2;
        protected TextView vClassName;
        protected TextView vTime;

        public RoomViewHolder(View v) {
            super(v);
            vRoom = (TextView) v.findViewById(R.id.roomNameNumber);
            vProfessor =  (TextView) v.findViewById(R.id.txtProfessor);
            vProfessor2 = (TextView)  v.findViewById(R.id.txtProfessor2);
            vClassName = (TextView)  v.findViewById(R.id.txtClassName);
            vTime = (TextView) v.findViewById(R.id.txtTime);
        }
    }
}
