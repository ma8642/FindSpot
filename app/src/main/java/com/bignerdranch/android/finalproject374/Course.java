package com.bignerdranch.android.FindSpot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Alford on 12/5/16.
 */

public class Course {
    private UUID mId;

    private Room mRoom;  //which room the course is in
    private String mTitle;  //title of course
    //private String[] mDates;  //list of dates when the class is in session (most will be the whole semester but some classes are only a few weeks long
    private List<String> mDays;  //days of the week when class is in session
    private String mStartTime;  //when class begins
    private String mEndTime;  //when class ends

    public Course() {
        //Generate unique identifier
        mDays = new ArrayList<String>();
        mTitle = "";
    }

    public Course(String startTime, String endTime) {
        mDays = new ArrayList<String>();
    }

    //GETTER FOR mID
    public UUID getCRN() {
        return mId;
    }

    //GETTER AND SETTER FOR mTITLE
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String newTitle) {
        mTitle = newTitle;
    }

    //GETTER AND SETTER FOR ROOM
    public Room getRoom() {
        return mRoom;
    }

    public void setRoom(Room mRoom) {
        this.mRoom = mRoom;
    }

    //GETTER AND SETTER FOR START AND END TIMES
    public void setClassTimes(String start, String end) {
        mStartTime = start;
        mEndTime = end;

    }

    public String[] getClassTimes() {
        String returnMe = mStartTime + "-" + mEndTime;
        return returnMe.split("-");
    }

    //GETTER AND SETTER FOR DAYS of WEEK

    public List<String> getDays() {
        return mDays;
    }

    public void addDay(String day) {
        mDays.add(day);
    }


}
