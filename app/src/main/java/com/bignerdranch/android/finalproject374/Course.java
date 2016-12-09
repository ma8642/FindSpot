package com.bignerdranch.android.finalproject374;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Marley on 12/5/16.
 */

public class Course {
    private UUID mId;

    private Room mRoom;  //which room the course is in
    private String mTitle;  //title of course
    private String[] mDates;  //list of dates when the class is in session (most will be the whole semester but some classes are only a few weeks long
    private String[] mTimes;
    private List<String> mDays;  //days of the week when class is in session
    private String mStartTime;  //when class begins
    private String mEndTime;  //when class ends

    public Course() {
        //Generate unique identifier
        mDays = new ArrayList<String>();
    }

    public Course(String startTime, String endTime) {
        mTimes = new String[2];
        mTimes[0] = startTime;
        mTimes[1] = endTime;
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


    //GETTER AND SETTER FOR mDATES

    public String[] getDates() {
        return mDates;
    }

    public void setDates(String startDate, String endDate) {
        mDates[0] = startDate;
        mDates[1] = endDate;
    }

    //GETTER AND SETTER FOR DAYS of WEEK

    public List<String> getDays() {
        return mDays;
    }

    public void addDay(String day) {
        mDays.add(day);
    }


}
