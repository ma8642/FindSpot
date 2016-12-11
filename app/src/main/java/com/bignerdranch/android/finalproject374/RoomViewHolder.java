package com.bignerdranch.android.finalproject374;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Wren on 12/6/2016.
 */

public class RoomViewHolder extends RecyclerView.ViewHolder {
    public TextView vRoom;
    public TextView vClassName;
    public TextView vTime;

    public RoomViewHolder(View v) {
        super(v);
        vRoom = (TextView) v.findViewById(R.id.roomNameNumber);
        vClassName = (TextView)  v.findViewById(R.id.txtClassName);  //displays next class that is about to start
        vTime = (TextView) v.findViewById(R.id.txtTime);  //displays when the room is free-until
    }
}