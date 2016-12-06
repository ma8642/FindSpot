package com.bignerdranch.android.finalproject374;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wren on 11/7/2016.
 */

public class RoomListFragment extends Fragment {

    public String [] ItemData;
    public Adapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.roomlist_fragment, parent, false);
        // 1. get a reference to recyclerView
        RecyclerView roomList = (RecyclerView) rootView.findViewById(R.id.cardList);
        // 2. set layoutManger
        roomList.setLayoutManager(new LinearLayoutManager(getActivity()));
        roomList.setHasFixedSize(true);
        RoomAdapter ra = new RoomAdapter(createList(20));
        roomList.setAdapter(ra);

        return rootView;
    }




//DUMMY FUNCTION TO POPULATE RECYCLER VIEW ROOM LIST
    private List<RoomInfo> createList(int size) {

        List<RoomInfo> result = new ArrayList<RoomInfo>();
        for (int i=1; i <= size; i++) {
            RoomInfo ri = new RoomInfo();
            ri.room = RoomInfo.Room_PREFIX ;
            ri.professor = RoomInfo.Professor_PREFIX ;
            ri.professor2 = RoomInfo.Professor2_PREFIX ;
            ri.className = RoomInfo.ClassName_PREFIX ;
            ri.time = RoomInfo.TIME_PREFIX ;

            result.add(ri);

        }

        return result;
    }


}



