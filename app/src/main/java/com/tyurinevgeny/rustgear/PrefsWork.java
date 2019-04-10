package com.tyurinevgeny.rustgear;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Functions to work with preferences
 */

public class PrefsWork {

    public static final String PREFS_NAME = "prefs";

    /** Save gear in slot */
    static void saveSlot(String slot, String gear, Activity activity) {
        if (gear != null) {
            SharedPreferences sharedPref = activity.getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(slot, gear);
            editor.apply();
        }
    }

    /** Read gear in slot */
    static String readSlot(String slot, Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        return sharedPref.getString(slot, "");
    }

}