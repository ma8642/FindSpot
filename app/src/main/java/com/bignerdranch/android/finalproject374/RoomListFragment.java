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



/**
 * Created by Wren on 11/7/2016.
 */

public class RoomListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private DatabaseHelper mDatabaseHelper;
    private ArrayList<Room> mRoomArrayList = new ArrayList<Room>();
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
        return mRoomArrayList.size();
    }  //ARRAYLIST TO POPULATE RECYCLERVIEW

    //RETURNS TRUE IF CURRENT TIME IS BEFORE OR AFTER A CLASS TIME
    public boolean chooseRoom(int cHour, int cMin, int startHour, int startMin, int endHour, int endMin) {
        if (cHour < startHour) {
            Log.d("TAG", "CURRENT TIME: " + cHour + ":" + cMin + " START TIME: " + startHour + ":" + startMin + " END TIME: " + endHour + ":" + endMin);

            Log.d("TAG", cHour + " " + cMin + " is earlier than " + startHour);
            return true;
        } else if (cHour == startHour && cMin < startMin) {
            Log.d("TAG", "CURRENT TIME: " + cHour + ":" + cMin + " START TIME: " + startHour + ":" + startMin + " END TIME: " + endHour + ":" + endMin);

            Log.d("TAG", cHour + " " + cMin + " is earlier than " + startHour + " " + startMin);
            return true;
        } else if (cHour > endHour) {
            Log.d("TAG", "CURRENT TIME: " + cHour + ":" + cMin + " START TIME: " + startHour + ":" + startMin + " END TIME: " + endHour + ":" + endMin);

            Log.d("TAG", cHour + " " + cMin + " is later than " + endHour);
            return true;
        } else if (cHour == endHour && cMin > endMin) {
            Log.d("TAG", "CURRENT TIME: " + cHour + ":" + cMin + " START TIME: " + startHour + ":" + startMin + " END TIME: " + endHour + ":" + endMin);

            Log.d("TAG", cHour + " " + cMin + " is later than " + endHour + " " + endMin);
            return true;
        } else {
            return false;
        }
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

        String dTime = "";
        //Integer endHour = 0;
        //Integer endMinute = 0;
        String endHr = "";
        String endMin = "";

        String databaseHr = "";
        String databaseMin = "";

        ///try {
        //Get current hour and min
        Integer currentHour = 0;
        Integer currentMinute = 0;
        String currentHr = "";
        String currentMin = "";
        String currentTime = "";
        int cHour = 0;
        int cMinute = 0;
        String selectCurrentTimeQ = "SELECT STRFTIME('%H','NOW','LOCALTIME') AS HOUR, STRFTIME('%M','NOW','LOCALTIME') AS MINUTE ";
        Cursor timeCursor = mDatabaseHelper.QueryData(selectCurrentTimeQ);
        String[] currentTimeSplit = new String[2];

        if (timeCursor.moveToFirst()) {
            currentHour = Integer.parseInt(timeCursor.getString(0));
            currentMinute = Integer.parseInt(timeCursor.getString(1));
            currentTime = currentHour + ":" + currentMinute;
            currentTimeSplit = currentTime.split(":");
            currentHr = (currentTimeSplit[0]);
            currentMin = (currentTimeSplit[1]);
            cHour = Integer.parseInt(currentHr); //CURRENT HOUR STORED HERE FOR COMPARISON
            cMinute = Integer.parseInt(currentMin); //CURRENT MINUTE STORED HERE FOR COMPARISON
        }
        timeCursor.close();
        String[] databaseTimeSplit2;
        String[] databaseTimeSplit;
                //Populate RecyclerView with relevant Information
                //search through columns that have the correct building name
                mCursor = mDatabaseHelper.QueryData("Select * from Final_Project_Courses_DB as FPC WHERE FPC.Building LIKE '"+ buildingClicked +"'");
                if (mCursor != null) {
                    if (mCursor.moveToFirst()) {
                        do {

                            //GET START AND END TIME
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
                            Log.d("TAG","START TIME: " + startHour + ":" + startMinute + "  " + "END TIME: " + endHour + ":" + endMinute);

                          // getBothTimes();
                            //FILTER OUT ROOMS THAT HAVE CLASSES HAPPENING IN THEM RIGHT NOW
                            if (chooseRoom(cHour, cMinute, startHour, startMinute, endHour, endMinute)) {
                                Room room = new Room();
//                                room.setBuilding(Building);
//                                room.setRoomNum(RoomNumber);
                                room.setBuilding("TESTB");
                                room.setRoomNum(5000);
                                if (!mRoomArrayList.contains(room)) {  //CHECK THAT ROOM ISN'T ALREADY ADDED  //TODO figure out how to compare rooms because it doesn't seem to be able to tell when rooms are the same
                                    Log.d("TAG", Building + RoomNumber);
                                    mRoomArrayList.add(room);
                                }
                            }
                        }
                        while (mCursor.moveToNext());
                    }
                }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            mRoomAdapter = new RoomAdapter(getActivity(), mRoomArrayList);
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



