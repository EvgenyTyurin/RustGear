package com.tyurinevgeny.rustgear;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Window with list of items
 */

public class ItemsListActivity extends ListActivity {
    public static final String EXTRA_LIST_ID = "rust.list_id";

    /** Window init */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemslist);
        // Get items from xml file to ListView
        String[] items =
                getResources().getStringArray(getIntent().
                        getIntExtra(EXTRA_LIST_ID, R.array.head));
        for (int idx = 0; idx < items.length; idx++ )
            items[idx] = items[idx].split(",")[0];
        ListAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, items);
        ListView listItems = getListView();
        listItems.setAdapter(arrayAdapter);
    }

    /** Another window must set input parameters before launch this window */
    public static Intent getIntent(Context context, String equipped,
                                   int listId) {
        Intent intent = new Intent(context, ItemsListActivity.class);
        // todo put extras
        intent.putExtra(EXTRA_LIST_ID, listId);
        return intent;
    }
}
