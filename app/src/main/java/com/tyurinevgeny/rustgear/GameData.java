package com.tyurinevgeny.rustgear;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Items info, images
 */

class GameData {
    private static Activity activity;
    private static ArrayList<RustItem> items = new ArrayList<>();

    /** Load data from XML */
    static void init(Activity activityIn) {
        if (!items.isEmpty())
            return;
        activity = activityIn;
        String[] itemsArray = activity.getResources().getStringArray(R.array.items);
        for (String itemStr : itemsArray) {
            String[] attrs = itemStr.split(",");
            if (attrs.length > 2)
                items.add(new RustItem(attrs[0], attrs[1], attrs[2],
                        Arrays.copyOfRange(attrs, 3, attrs.length)));
        }
    }

    static ArrayList<String> getSlotItems(String slot) {
        ArrayList<String> slotItems = new ArrayList<>();
        for (RustItem item : items)
            if (item.slot.equals(slot))
                slotItems.add(item.name);
        return slotItems;
    }

    static RustItem getItem(String name) {
        for (RustItem item : items)
            if (item.name.equals(name))
                return item;
        return null;
    }

    /** Find image file by item name */
    static Drawable getItemImage(String itemName) {
        RustItem item = getItem(itemName);
        if (item != null)
            return getImgByName(item.image);
        else
            return getImgByName("");
    }

    /** Returns image drawable object by image name */
    static Drawable getImgByName(String imgName) {
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
