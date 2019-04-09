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
    /** Gear slots */
    private static final int REQUEST_CODE_HEAD = 1;
    private static final int REQUEST_CODE_FACE = 2;
    private static final int REQUEST_CODE_CHEST = 3;
    public static final String SLOT_HEAD = "head";
    public static final String SLOT_FACE = "face";
    public static final String SLOT_CHEST = "chest";

    /** GUI controls */
    private Button headButton;
    private ImageView headImage;
    private Button faceButton;
    private ImageView faceImage;
    private Button chestButton;
    private ImageView chestImage;

    /** Window init */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GameData.init(this);
        // Head slot init
        headButton = findViewById(R.id.buttonHead);
        headButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemsList(SLOT_HEAD, headButton.getText().toString());
            }
        });
        headImage = findViewById(R.id.imageHead);
        drawHead();
        // Face slot init
        faceButton = findViewById(R.id.buttonFace);
        faceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemsList(SLOT_FACE, faceButton.getText().toString());
            }
        });
        faceImage = findViewById(R.id.imageFace);
        drawHead();
        // Chest slot init
        chestButton = findViewById(R.id.buttonChest);
        chestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemsList(SLOT_CHEST, chestButton.getText().toString());
            }
        });
        chestImage = findViewById(R.id.imageChest);
        drawChest();
    }

    /** Run window with list of items */
     void showItemsList(String slot, String equipped) {
        Intent intent = ItemsListActivity.getIntent(MainActivity.this,
                equipped, slot);
        int requestCode = 0;
        switch (slot) {
            case SLOT_HEAD:
                requestCode = REQUEST_CODE_HEAD;
                break;
            case SLOT_FACE:
                requestCode = REQUEST_CODE_FACE;
                break;
            case SLOT_CHEST:
                requestCode = REQUEST_CODE_CHEST;
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
            case REQUEST_CODE_FACE:
                PrefsWork.saveSlot(SLOT_FACE, item, this);
                drawFace();
                break;
            case REQUEST_CODE_CHEST:
                PrefsWork.saveSlot(SLOT_CHEST, item, this);
                drawChest();
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

    /** Redraw face slot */
    private void drawFace() {
        drawSlot(SLOT_FACE, faceButton, faceImage);
    }

    /** Redraw chest slot */
    private void drawChest() {
        drawSlot(SLOT_CHEST, chestButton, chestImage);
    }
}
