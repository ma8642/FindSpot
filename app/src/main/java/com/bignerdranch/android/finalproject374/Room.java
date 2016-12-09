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
    }

    public Room(String building, int num ) {
        mBuilding = building;
        mRoomNumber = num;
    }

    //GETTER AND SETTER FOR mID
    public UUID getId() {
        return mId;
    }

    public void setId() {
        mId = UUID.randomUUID();
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

    public boolean equals(Room other) {  //returns true if this room == other room
        if ((this.getBuilding() == other.getBuilding()) && (this.getRoomNum() == other.getRoomNum())) {
            return true;
        }
        return false;
    }
}
