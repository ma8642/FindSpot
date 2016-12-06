package com.bignerdranch.android.finalproject374;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View.OnTouchListener;


/**
 * Created by cs374 on 11/8/16.
 */

public class MainFragment extends Fragment implements OnTouchListener {
    //Context context;

    String TAG = "mainFragmentString";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstaceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        ImageView image = (ImageView) v.findViewById(R.id.campus_map);

        image.setOnTouchListener(this);

        toast("Select a building");

        return v;
    }

    @Override
    public boolean onTouch(View v, MotionEvent ev) {
        Log.d(TAG, "Touched Screen!");

        boolean returnMe = false;
        final int action = ev.getAction();

        //Here we get the coordinates of the user's touch point
        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();

        String olinToast = "OPEN OLIN FRAGMENT";
        String rkcToast = "OPEN RKC FRAGMENT";

        // If we cannot find the imageView, return.
        ImageView imageView = (ImageView) v.findViewById (R.id.campus_map);
        if (imageView == null) return false;

        switch (action) {
            case MotionEvent.ACTION_DOWN :  //The hidden image (image_areas) has two different hotspots on it.  The colors are red (rkc) and yellow (olin).

                //Given the coordinates of the touch we look up the color of a pixel in the hidden image (with the hotspots)
                int touchColor = getHotspotColor (R.id.image_areas, evX, evY);

                // Compare the touchColor to the expected values.
                ColorTool ct = new ColorTool();  // We use a Color Tool object to test whether the observed color is close enough to the real color to count as a match.
                int tolerance = 40;

                if (ct.closeMatch (-3079405, touchColor, tolerance))
                {
                    // open RKC Fragment
                    toast(rkcToast);
                    Intent i = new Intent(getActivity(), RoomListActivity.class);
                    i.putExtra("extra","RKC");
                    startActivity(i);

                }

                else if (ct.closeMatch (-802735, touchColor, tolerance))
                {
                    //open Olin Fragment
                    toast(olinToast);

                    Intent i = new Intent(getActivity(), RoomListActivity.class);
                    i.putExtra("extra","OLIN");
                    //roomName = "Olin";
                    startActivity(i);
                }

                returnMe = true;
                break;

            default:
                returnMe = false;
        } // end switch
        return returnMe;
    }


    public int getHotspotColor (int hotspotId, int x, int y) {
        Log.d(TAG, "called getHotspotColor");
        ImageView img = (ImageView) getView().findViewById(hotspotId);
        img.setDrawingCacheEnabled(true);
        Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
        img.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
    }


    public void toast(String msg)  //make toast
    {
        Toast.makeText (getActivity(), msg, Toast.LENGTH_SHORT).show();
    } // end toast
}
