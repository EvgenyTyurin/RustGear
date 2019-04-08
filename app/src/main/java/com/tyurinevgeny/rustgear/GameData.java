package com.tyurinevgeny.rustgear;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

/**
 * Items info, images
 */

class GameData {
    private static Activity activity;
    private static ArrayList<ClothItem> items = new ArrayList<>();

    /** Load data from XML */
    static void init(Activity activityIn) {
        if (!items.isEmpty())
            return;
        activity = activityIn;
        String[] itemsArray = activity.getResources().getStringArray(R.array.items);
        for (String itemStr : itemsArray) {
            String[] attrs = itemStr.split(",");
            if (attrs.length > 9)
                items.add(new ClothItem(attrs[0], attrs[1], attrs[2], attrs[3], attrs[4],
                        attrs[5], attrs[6], attrs[7], attrs[8], attrs[9]));
        }
        // initArray(R.array.items);
    }

    static ArrayList<String> getSlotItems(String slot) {
        ArrayList<String> slotItems = new ArrayList<>();
        for (ClothItem item : items)
            if (item.slot.equals(slot))
                slotItems.add(item.name);
        return slotItems;
    }

    static ClothItem getItem(String name) {
        for (ClothItem item : items)
            if (item.name.equals(name))
                return item;
        return null;
    }

    /** Find image file by item name*/
    static Drawable getItemImage(String itemName) {
        ClothItem item = getItem(itemName);
        if (item != null)
            return getImgByName(item.image);
        else
            return getImgByName("");
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
