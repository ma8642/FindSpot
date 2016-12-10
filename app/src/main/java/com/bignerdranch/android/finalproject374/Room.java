package com.bignerdranch.android.finalproject374;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Marley on 12/5/16.
 */

public class Room {
    private UUID mId;
    private String mBuilding;  //Building the room is is
    private int mRoomNumber;  //Room number of room
    private ArrayList<Course> mCourses;
    private boolean isEmpty;
    private int freeTime;


    public Room() {
        mCourses = new ArrayList<Course>();
    }

    public Room(String building, int num ) {
        mBuilding = building;
        mRoomNumber = num;
        mCourses = new ArrayList<Course>();
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

    //GETTER AND SETTER FOR FREE TIME
    public int getFreeTime() { return freeTime;}

    public void setFreeTime(int freeTime) {this.freeTime = freeTime; }

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

    public void addCourse(Course c) {
        mCourses.add(c);
    }

    public ArrayList<Course> getCourses() {
        return this.mCourses;
    }

//    public boolean hasCourse(Course c) { //check if there is already a course in mCourses with title of course c
//        for (int i = 0; i < mCourses.size(); i++) {
//            if (c.getTitle() == mCourses.get(i).getTitle()) {
//                return true;
//            }
//        }
//        return false;
//    }
}
