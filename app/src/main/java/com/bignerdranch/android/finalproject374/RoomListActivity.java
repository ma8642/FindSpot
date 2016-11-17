package com.bignerdranch.android.finalproject374;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by sysadmin on 11/17/16.
 */

public class RoomListActivity extends FragmentActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.listview_fragment_container, new RoomListFragment());
        ft.commit();


    }

}
