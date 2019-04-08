package com.tyurinevgeny.rustgear;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Window with list of items
 */

public class ItemsListActivity extends ListActivity {
    private static final String EXTRA_SLOT = "rust.slot";
    private static final String EXTRA_SELECTED = "rust.selected";
    private static final int REQUEST_CODE_ITEM = 1;
    String selected;

    /** Window init */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemslist);

        // Init listview with game data
        final ListAdapter arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                GameData.getSlotItems(getIntent().getStringExtra(EXTRA_SLOT)));
        ListView listItems = getListView();
        listItems.setAdapter(arrayAdapter);

        // Show item info when item selected
        listItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = String.valueOf(arrayAdapter.getItem(position));
                Intent intent = ItemInfoActivity.getIntent(ItemsListActivity.this,
                        "", selected);
                startActivityForResult(intent, REQUEST_CODE_ITEM);
            }
        });
    }

    /** Another window must set input parameters before launch this window */
    public static Intent getIntent(Context context, String equipped,
                                   String slot) {
        Intent intent = new Intent(context, ItemsListActivity.class);
        // todo put extras
        intent.putExtra(EXTRA_SLOT, slot);
        return intent;
    }

    /** On returning from item window */
    @Override
    protected void onActivityResult (int requestCode,
                                     int resultCode,
                                     Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_CODE_ITEM:
                // Inform main window that user equip new item
                Intent intent = new Intent();
                intent.putExtra(EXTRA_SELECTED, selected);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    /** Gets clicked item from intent */
    public static String getListItemSelected(Intent intent) {
        return intent.getStringExtra(EXTRA_SELECTED);
    }
}
