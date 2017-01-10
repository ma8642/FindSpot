package com.bignerdranch.android.FindSpot;

import android.graphics.Color;
import android.util.Log;

/**
 * Created by Alford on 11/6/16.
 */

public class ColorTool {
    String TAG = "mainActivityString";

    public boolean closeMatch (int color1, int color2, int tolerance) {
        if ((int) Math.abs (Color.red (color1) - Color.red (color2)) > tolerance ) {
            Log.d(TAG, "red return False");
            return false;
        }
        if ((int) Math.abs (Color.green (color1) - Color.green (color2)) > tolerance ) {
            Log.d(TAG, "green return False");
            return false;
        }
        if ((int) Math.abs (Color.blue (color1) - Color.blue (color2)) > tolerance ) {
            Log.d(TAG, "blue return False");
            return false;
        }

        Log.d(TAG, "return True");
        return true;
    } // end match
}
