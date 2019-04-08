package com.tyurinevgeny.rustgear;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Rust Gear Assistant: Main window with character gear
 * 2019 Evgeny Tyurin
 */

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_HEAD = 1;
    public static final String SLOT_HEAD = "head";

    /** GUI controls */
    private Button headButton;
    private ImageView headImage;

    /** Window init */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GameData.init(this);
        // GUI controls init
        headButton = findViewById(R.id.buttonHead);
        headButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemsList(SLOT_HEAD, headButton.getText().toString());
            }
        });
        headImage = findViewById(R.id.imageHead);
        drawHead();
    }

    /** Run window with list of items */
     void showItemsList(String slot, String equipped) {
        Intent intent = ItemsListActivity.getIntent(MainActivity.this,
                "", slot);
        int requestCode = 0;
        switch (slot) {
            case SLOT_HEAD:
                requestCode = REQUEST_CODE_HEAD;
                break;
        }
        if (requestCode > 0)
            startActivityForResult(intent, requestCode);
    }

    /** On returning from gear list window */
    @Override
    protected void onActivityResult (int requestCode,
                                     int resultCode,
                                     Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        // Setting new equipped gear to slot
        String item = ItemsListActivity.getListItemSelected(data);
        switch (requestCode) {
            case REQUEST_CODE_HEAD:
                PrefsWork.saveSlot(SLOT_HEAD, item, this);
                drawHead();
                break;
        }
    }

    /** Redraw slot */
    private void drawSlot(String slot, Button button, ImageView imageView) {
        String gear = PrefsWork.readSlot(slot, this);
        if (!gear.equals(""))
            button.setText(gear);
        else
            button.setText(slot);
        imageView.setImageDrawable(GameData.getItemImage(gear));
    }

    /** Redraw head slot */
    private void drawHead() {
        drawSlot(SLOT_HEAD, headButton, headImage);
    }

}
