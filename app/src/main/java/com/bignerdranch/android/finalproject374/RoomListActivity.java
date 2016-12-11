package com.bignerdranch.android.finalproject374;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by Wren on 11/17/16.
 */

public class RoomListActivity extends AppCompatActivity {
    public String buildingName;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        buildingName = getIntent().getStringExtra("extra");
        getSupportActionBar().setTitle(buildingName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //store building name in a bundle that we will pass to fragment
        Bundle bundle=new Bundle();
        bundle.putString("name", buildingName);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        Fragment fragment = new RoomListFragment();
        fragment.setArguments(bundle);

        //create fragment
        ft.add(R.id.listview_fragment_container, fragment);
        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
