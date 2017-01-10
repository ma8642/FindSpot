package com.bignerdranch.android.FindSpot;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.ContentResolver;
import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Wren on 12/4/2016.
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomViewHolder>{


    private Activity mActivity;
    private Context mContext;
    List<String> mIndices = Collections.emptyList();  //List that is the same size as hashmap so that we can reference a hashmap key using array index
    HashMap<String, Room> mRooms;
    HashMap<String, String> mWeekAcronym;
    private OnTapListener OnTapListener;

    public RoomAdapter(Activity activity, Context context, HashMap<String, Room> mRooms, List<String> mIndices){
        this.mActivity = activity;
        this.mContext = context;
        this.mIndices = mIndices;
        this.mRooms = mRooms;
        mWeekAcronym = new HashMap<String, String>()
        {{
            put("Mon", "M");
            put("Tue", "T");
            put("Wed", "W");
            put("Thu", "Th");
            put("Fri", "F");
            put("Sat", "S");
            put("Sun", "S");
        }};
    }

    @Override
    public int getItemCount() {
        //return roomInfoList.size();
        return mRooms.size();
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new RoomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RoomViewHolder roomViewHolder, final int i) {

        String key = mIndices.get(i);
        Room ri = mRooms.get(key);
        String next = mContext.getString(R.string.next_class_none);
        String nextTime = mContext.getString(R.string.free_all_day);

        if (getNextCourse(ri) != null) {
            Course c = getNextCourse(ri);
            next = c.getTitle();
            String[] startTime = c.getClassTimes()[0].split(" ");
            String classTime = militaryTo12Hr(Integer.parseInt(startTime[0]), startTime[1]);
            nextTime = "Free until " + classTime;
        }
        roomViewHolder.vRoom.setText(ri.getBuilding() + " " + ri.getRoomNum());
        roomViewHolder.vClassName.setText("Next class is " + next);
        roomViewHolder.vTime.setText(nextTime);

        roomViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (OnTapListener != null){
                    OnTapListener.OnTapView(i);
                }
            }
            });
    }

    public Course getNextCourse(Room r) { //This function looks in mCourses of r to find when the next closest course will begin
        String[] today = getCurrentDateAndTime(); //today = [Weekday, Hour, Minute]
        String cDay = mWeekAcronym.get(today[0]);  //translate weekday to format found in our database
        int cHr = Integer.parseInt(today[1]);
        int cMin = Integer.parseInt(today[2]);

        for (int i = 0; i < r.getCourses().size(); i++) { //loop through courses that occur in room
            Course c = r.getCourses().get(i);
            if (isAfter(c, cDay, cHr, cMin) == true) {  //if course occurs later today
                return c;
            }
        }

        return null;  //there are no courses occuring later today in room

    }

    public String[] getCurrentDateAndTime() {
        //getting current date and time using Date class
        DateFormat df = new SimpleDateFormat("E HH mm");
        Date dateObj = new Date();

        String[] today = df.format(dateObj).split(" ");

        return today;
    }

    public boolean isAfter(Course c, String cDay, int cHour, int cMin) {  //returns the name of the next class
        //Log.d("DEBUG", "Is " + c.getTitle() + " after " + cDay + " " + cHour + " " + cMin);
        List<String> days = c.getDays();
        String[] times = c.getClassTimes();
        String start = times[0];  //"HH mm"
        String[] startSplit = start.split(" ");
        int startHr = Integer.parseInt(startSplit[0]);  // "HH"
        int startMin = Integer.parseInt(startSplit[1]);  // "mm"
        String curr = cHour + " " + cMin;

        for (int i = 0; i < days.size(); i++) {

            if (cDay.equalsIgnoreCase(c.getDays().get(i))) {//if cDay == the day of the course
                if (cHour < startHr) {  //is the class starts later hour than current time
                    //Log.d("DEBUG", "Yes, " + cHour + " < " + startHr);

                    return true;

                } else if ((cHour == startHr) && (cMin < startMin)) {  //if class starts the same hour but later minute than current time
                    //Log.d("DEBUG", "Yes, " + cHour + " " + cMin + " < " + startHr + " " + startMin);

                    return true;
                }
            }
        }
        return false;  //if course does not occur during current day, then it is not happening anytime later today

    }

    public String militaryTo12Hr(int hrs, String mins) { //translates military time to 12-hour format
        if  (hrs < 12) {
            return hrs + ":" + mins + " AM";
        }
        else if (hrs == 12) {
            return hrs + ":" + mins + " PM";
        }
        else if (hrs == 24) {
            return 12 + ":" + mins + " AM";
        }
        else {
            return (hrs % 12) + ":" + mins + " PM";
        }
    }

    public void setOnTapListener(OnTapListener onTapListener) {
        this.OnTapListener = onTapListener;
    }
}
