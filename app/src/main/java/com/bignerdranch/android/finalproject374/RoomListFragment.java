package com.bignerdranch.android.finalproject374;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


/**
 * Created by Wren on 11/7/2016.
 */

public class RoomListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private DatabaseHelper mDatabaseHelper;
    private ArrayList<String> mRoomArrayList = new ArrayList<String>(); //helps reference hashmap by storing each new roomname at a unique index
    private HashMap<String, Room> mRoomHashMap = new HashMap<>();
    //private HashMap<Room, ArrayList<String>> mRoomCourse = new HashMap<>();
    private Cursor mCursor;
    private Cursor nCursor;
    private RoomAdapter mRoomAdapter;
    //public static String bName;
    private String buildingClicked;

    Integer endHour = 0;
    Integer endMinute = 0;

    Integer startHour = 0;
    Integer startMinute = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.roomlist_fragment, parent, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
        buildingClicked = getArguments().getString("name");
        getBuildingName();
        return rootView;
    }


    public int getItemCount() {
        return mRoomHashMap.size();
    }  //ARRAYLIST TO POPULATE RECYCLERVIEW

    //RETURNS TRUE IF CURRENT TIME IS BEFORE OR AFTER A CLASS TIME
    public boolean chooseRoom(String cDay, int cHour, int cMin, String[] days, int startHour, int startMin, int endHour, int endMin) {
        if (!Arrays.asList(days).contains(cDay)) { //if the course is not happening today, room is empty!
            return true;
        }
        else { //if it's the same day, we can still compare times to see if it's currently empty
            if (cHour < startHour) {
                //Log.d("TAG", "CURRENT TIME: " + cHour + ":" + cMin + " START TIME: " + startHour + ":" + startMin + " END TIME: " + endHour + ":" + endMin);

                //Log.d("TAG", cHour + " " + cMin + " is earlier than " + startHour);
                return true;
            } else if (cHour == startHour && cMin < startMin) {
                //Log.d("TAG", "CURRENT TIME: " + cHour + ":" + cMin + " START TIME: " + startHour + ":" + startMin + " END TIME: " + endHour + ":" + endMin);

                //Log.d("TAG", cHour + " " + cMin + " is earlier than " + startHour + " " + startMin);
                return true;
            } else if (cHour > endHour) {
                //Log.d("TAG", "CURRENT TIME: " + cHour + ":" + cMin + " START TIME: " + startHour + ":" + startMin + " END TIME: " + endHour + ":" + endMin);

                //Log.d("TAG", cHour + " " + cMin + " is later than " + endHour);
                return true;
            } else if (cHour == endHour && cMin > endMin) {
                //Log.d("TAG", "CURRENT TIME: " + cHour + ":" + cMin + " START TIME: " + startHour + ":" + startMin + " END TIME: " + endHour + ":" + endMin);

                //Log.d("TAG", cHour + " " + cMin + " is later than " + endHour + " " + endMin);
                return true;
            } else {
                return false;
            }
        }
    }

    public String intToWeekDay(int val) {  //converts integer value to a string of that weekday
        String weekday = "";
        if (val == 1) {
            weekday = "M";
        }
        else if (val == 2) {
            weekday = "T";
        }
        else if (val == 3) {
            weekday = "W";
        }
        else if (val == 4) {
            weekday = "Th";
        }
        else if (val == 5) {
            weekday = "F";
        }
        else {
            weekday = "S";  //satuday or sunday for debug
        }

        return weekday;
    }


    // Function to get LIST ITEMS
    public void getBuildingName() {
        mDatabaseHelper = new DatabaseHelper(getActivity());
        try {
            mDatabaseHelper.checkAndCoptDatabase();
            mDatabaseHelper.openDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        //new GregorianCalendar().get(Calendar.DAY_OF_WEEK);

        String weekDay = "";

        String endHr = "";
        String endMin = "";

        String databaseHr = "";
        String databaseMin = "";

        Integer currentHour = 0;
        Integer currentMinute = 0;
        String currentHr = "";
        String currentMin = "";
        String currentTime = "";
        int currentDay = 0;
        int cHour = 0;
        int cMinute = 0;
        String cDay = "";
        String selectCurrentTimeQ = "SELECT STRFTIME('%H','NOW','LOCALTIME') AS HOUR, STRFTIME('%M','NOW','LOCALTIME') AS MINUTE, STRFTIME('%w','NOW','LOCALTIME') AS WEEK ";
        Cursor timeCursor = mDatabaseHelper.QueryData(selectCurrentTimeQ);
        String[] currentTimeSplit = new String[2];

        if (timeCursor.moveToFirst()) {
            currentHour = Integer.parseInt(timeCursor.getString(0));
            currentMinute = Integer.parseInt(timeCursor.getString(1));
            currentDay = Integer.parseInt(timeCursor.getString(2));
            currentTime = currentHour + ":" + currentMinute;
            currentTimeSplit = currentTime.split(":");
            currentHr = (currentTimeSplit[0]);
            currentMin = (currentTimeSplit[1]);
            cHour = Integer.parseInt(currentHr); //CURRENT HOUR STORED HERE FOR COMPARISON
            cMinute = Integer.parseInt(currentMin); //CURRENT MINUTE STORED HERE FOR COMPARISON
            //Log.d("WEEKDAY", String.valueOf(currentDay));
            //Log.d("WEEKDAY", intToWeekDay(currentDay));
            cDay = intToWeekDay(currentDay);  //CURRENT DAY OF WEEK (e.g. "M" or Th") STORED HERE FOR COMPARISON
        }
        timeCursor.close();
        String[] databaseTimeSplit2;
        String[] databaseTimeSplit;
        String[] courseDays;
                //Populate RecyclerView with relevant Information
                //search through columns that have the correct building name
                mCursor = mDatabaseHelper.QueryData("Select * from Final_Project_Courses_DB as FPC WHERE FPC.Building LIKE '"+ buildingClicked +"'");
                if (mCursor != null) {
                    if (mCursor.moveToFirst()) {
                        do {
                            //GET TITLE
                            String courseName = mCursor.getString(mCursor.getColumnIndexOrThrow("Course Name"));

                            //GET DATES AND START AND END TIME
                            String dates = mCursor.getString(mCursor.getColumnIndexOrThrow("Dates"));
                            String startTime = mCursor.getString(mCursor.getColumnIndexOrThrow("Start_time"));
                            String endTime = mCursor.getString(mCursor.getColumnIndexOrThrow("End_time"));

                            //GET ROOM NAME
                            String Building = mCursor.getString(mCursor.getColumnIndexOrThrow("Building"));
                            Integer RoomNumber = mCursor.getInt(mCursor.getColumnIndexOrThrow("Room Number"));

                            //add AM to morning time && add PM to afternoon time
                            databaseTimeSplit2 = endTime.split(" ");
                            endHr = (databaseTimeSplit2[0]);
                            endMin = (databaseTimeSplit2[1]);
                            endHour = Integer.parseInt(endHr);
                            endMinute = Integer.parseInt(endMin);

                            databaseTimeSplit = startTime.split(" ");
                            databaseHr = (databaseTimeSplit[0]);
                            databaseMin = (databaseTimeSplit[1]);
                            startHour = Integer.parseInt(databaseHr);
                            startMinute = Integer.parseInt(databaseMin);

                            courseDays = dates.split(" ");
                            //Log.d("TAG","START TIME: " + startHour + ":" + startMinute + "  " + "END TIME: " + endHour + ":" + endMinute);

                            //FILTER OUT ROOMS THAT HAVE CLASSES HAPPENING IN THEM RIGHT NOW
                            if (chooseRoom(cDay, cHour, cMinute, courseDays, startHour, startMinute, endHour, endMinute)) {
                                Room room = new Room();
                                room.setBuilding(Building);
                                room.setRoomNum(RoomNumber);

                                String weekdays = "";

                                for (int i = 0; i < courseDays.length; i++) {
                                    weekdays += courseDays[i];
                                }

                                //CREATE STRING WITH ALL INFO ABOUT
                                String course = weekdays + String.valueOf(startHour) + String.valueOf(startMinute) + String.valueOf(endHour) + String.valueOf(endMinute);

                                String key = Building + RoomNumber;
                                Room test = mRoomHashMap.get(key);

                                if (test != null) { //if that key is already present in the Hashmap
                                    //Log.d("TAG", Building + RoomNumber + " already in list");
                                }
                                else {
                                    //Log.d("TAG", Building + RoomNumber + " not in list.  Add.");
                                    mRoomArrayList.add(key);  //helps us reference hashmap
                                    mRoomHashMap.put(key, room);
                                }

                                //create course object from course info
                                Course c = new Course();
                                c.setTitle(courseName);
                                c.setClassTimes(startTime, endTime);

                                for (int i = 0; i < courseDays.length; i++) {  //add all the weekdays that the course occurs
                                    c.addDay(courseDays[i]);
                                }

                                mRoomHashMap.get(key).addCourse(c);  //add course to room's mCourses list
                            }
                        }

                        while (mCursor.moveToNext());
                    }
                }

            //Fake Room, fake course
            Room test = new Room();
            test.setBuilding("RKC");
            test.setRoomNum(5000);
            String key = "RKC5000";
            mRoomArrayList.add(key);  //helps us reference hashmap
            mRoomHashMap.put(key, test);
            Course c = new Course();
            c.setTitle("Petty 101");
            c.setClassTimes("15 00", "19 45");
            c.addDay("S");
            mRoomHashMap.get(key).addCourse(c);  //add course to room's mCourses list



            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            mRoomAdapter = new RoomAdapter(getActivity(), mRoomHashMap, mRoomArrayList); //TODO
            mRoomAdapter.setOnTapListener(new OnTapListener() {
                @Override
                public void OnTapView(int position) {
                    Toast.makeText(getContext(), "Click to " + position, Toast.LENGTH_SHORT).show();
                }
            });
            {
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.setAdapter(mRoomAdapter);

            }



    }
}



