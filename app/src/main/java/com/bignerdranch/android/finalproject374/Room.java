package com.bignerdranch.android.finalproject374;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Marley on 12/5/16.
 */

public class Room {
    private UUID mId;
    private String mBuilding;  //Building the room is is
    private int mRoomNumber;  //Room number of room
    private boolean isEmpty;

    public Room() {
        //Generate unique identifier
        mId = UUID.randomUUID();
    }

    public Room(String building, int num ) {
        mId = UUID.randomUUID();
        mBuilding = building;
        mRoomNumber = num;
    }

    //GETTER FOR mID
    public UUID getId() {
        return mId;
    }

    //GETTER AND SETTER FOR mBuilding
    public String getBuilding() {
        return mBuilding;
    }

    public void setBuilding(String building) {
        mBuilding = building;
    }

    //GETTER AND SETTER FOR ROOM NUMBER
    public int getRoomNum() {
        return mRoomNumber;
    }

    public void setRoomNum(int num) {
        mRoomNumber = num;
    }

    //GETTER AND SETTER FOR whether or not room is empty
    public boolean getStatus() {
        return isEmpty;
    }

    public void setStatus(boolean status) {
        isEmpty = status;
    }
}
