package com.tyurinevgeny.rustgear;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import java.util.HashMap;

class GameData {
    private static HashMap<String, String> itemImages = new HashMap<>();
    private static Activity activity;

    /** Load data from XML */
    static void init(Activity activityIn) {
        activity = activityIn;
        initArray(R.array.head);
    }

    private static void initArray(int arrayId) {
        String[] items = activity.getResources().getStringArray(R.array.head);
        for (String headItem : items)
            itemImages.put(headItem.split(",")[0], headItem.split(",")[1]);
    }

    /** Find image file by item name*/
    static Drawable getItemImage(String item) {
        return getImgByName(itemImages.get(item));
    }

    /** Returns image drawable object by image name */
    static private Drawable getImgByName(String imgName) {
        if (imgName.equals("")) {
            String uri = "@android:drawable/list_selector_background";
            int imageResource = activity.getResources().getIdentifier(uri, null,
                    activity.getPackageName());
            return activity.getResources().getDrawable(imageResource);
        } else {
            String uri = "@drawable/" + imgName;
            int imageResource = activity.getResources().getIdentifier(uri, null,
                    activity.getPackageName());
            if (imageResource > 0) {
                return  activity.getResources().getDrawable(imageResource);
            }
            else {
                return getImgByName("");
            }
        }
    }
}
