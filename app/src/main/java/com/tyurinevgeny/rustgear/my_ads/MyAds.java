package com.tyurinevgeny.rustgear.my_ads;

import android.app.Activity;

import com.tyurinevgeny.rustgear.R;

import java.util.ArrayList;
import java.util.Random;

public class MyAds {
    private static MyAds myAds;
    private static ArrayList<MyProduct> products;
    private static Random random;

    private MyAds(Activity activity) {
        products = new ArrayList<>();
        String[] productStrs = activity.getResources().getStringArray(R.array.my_adds);
        for (String productStr : productStrs) {
            String[] attrs = productStr.split(",");
            if (attrs.length > 1) {
                products.add(new MyProduct(attrs[0], attrs[1]));
            }
        }
        random = new Random();
    }

    public static MyAds getInstance(Activity activity) {
        if (myAds == null)
            myAds = new MyAds(activity);
        return myAds;
    }

    public MyProduct getRandomAds() {
        if (products.size() > 0)
            return products.get(random.nextInt(products.size()));
        else
            return null;
    }
}
