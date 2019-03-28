package com.tyurinevgeny.rustgear;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Window with selected and equipped items info
 */

public class ItemInfoActivity extends AppCompatActivity {
    private static final String EXTRA_SELECTED = "rust.selected";

    /** Window init */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        // GUI init
        Intent intent = getIntent();
        String selectedItem = intent.getStringExtra(EXTRA_SELECTED);
        TextView viewSelectedName = findViewById(R.id.selectedName);
        viewSelectedName.setText(selectedItem);
        Button buttonEquip = findViewById(R.id.buttonEquip);
        if (selectedItem.equals("")) {
            buttonEquip.setEnabled(false);
        } else {
            buttonEquip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Inform items list window that user equip current item
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }

    /** Another window must set input parameters before launch this window */
    public static Intent getIntent(Context context, String equipped,
                                   String selected) {
        Intent intent = new Intent(context, ItemInfoActivity.class);
        // todo put extras
        intent.putExtra(EXTRA_SELECTED, selected);
        return intent;
    }
}
