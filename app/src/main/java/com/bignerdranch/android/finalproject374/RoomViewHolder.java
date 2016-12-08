package com.bignerdranch.android.finalproject374;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Wren on 12/6/2016.
 */

public class RoomViewHolder extends RecyclerView.ViewHolder {
    public TextView vRoom;
    public TextView vProfessor;
    public TextView vProfessor2;
    public TextView vClassName;
    public TextView vTime;

    public RoomViewHolder(View v) {
        super(v);
        vRoom = (TextView) v.findViewById(R.id.roomNameNumber);
        vProfessor =  (TextView) v.findViewById(R.id.txtProfessor);
        vProfessor2 = (TextView)  v.findViewById(R.id.txtProfessor2);
        vClassName = (TextView)  v.findViewById(R.id.txtClassName);
        vTime = (TextView) v.findViewById(R.id.txtTime);
    }
}