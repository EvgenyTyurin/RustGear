package com.tyurinevgeny.rustgear;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Functions to work with preferences
 */

public class PrefsWork {
    /** Save gear in slot */
    static void saveSlot(String slot, String gear, Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(slot, gear);
        editor.apply();
    }

    /** Read gear in slot */
    static String readSlot(String slot, Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(slot, "");
    }
}