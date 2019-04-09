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
    private static final int REQUEST_CODE_LEGS = 4;
    private static final int REQUEST_CODE_FEET = 5;
    private static final int REQUEST_CODE_GLOVES = 6;
    public static final String SLOT_HEAD = "head";
    public static final String SLOT_FACE = "face";
    public static final String SLOT_CHEST = "chest";
    public static final String SLOT_LEGS = "legs";
    public static final String SLOT_FEET = "feet";
    public static final String SLOT_GLOVES = "gloves";

    /** GUI controls */
    private Button headButton;
    private ImageView headImage;
    private Button faceButton;
    private ImageView faceImage;
    private Button chestButton;
    private ImageView chestImage;
    private Button legsButton;
    private ImageView legsImage;
    private Button feetButton;
    private ImageView feetImage;
    private Button glovesButton;
    private ImageView glovesImage;

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
        drawFace();
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
        // Legs slot init
        legsButton = findViewById(R.id.buttonLegs);
        legsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemsList(SLOT_LEGS, legsButton.getText().toString());
            }
        });
        legsImage = findViewById(R.id.imageLegs);
        drawLegs();
        // Feet slot init
        feetButton = findViewById(R.id.buttonFeet);
        feetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemsList(SLOT_FEET, feetButton.getText().toString());
            }
        });
        feetImage = findViewById(R.id.imageFeet);
        drawFeet();
        // Gloves slot init
        glovesButton = findViewById(R.id.buttonGloves);
        glovesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemsList(SLOT_GLOVES, glovesButton.getText().toString());
            }
        });
        glovesImage = findViewById(R.id.imageGloves);
        drawGloves();
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
            case SLOT_LEGS:
                requestCode = REQUEST_CODE_LEGS;
                break;
            case SLOT_FEET:
                requestCode = REQUEST_CODE_FEET;
                break;
            case SLOT_GLOVES:
                requestCode = REQUEST_CODE_GLOVES;
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
            case REQUEST_CODE_LEGS:
                PrefsWork.saveSlot(SLOT_LEGS, item, this);
                drawLegs();
                break;
            case REQUEST_CODE_FEET:
                PrefsWork.saveSlot(SLOT_FEET, item, this);
                drawFeet();
                break;
            case REQUEST_CODE_GLOVES:
                PrefsWork.saveSlot(SLOT_GLOVES, item, this);
                drawGloves();
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

    /** Redraw legs slot */
    private void drawLegs() {
        drawSlot(SLOT_LEGS, legsButton, legsImage);
    }

    /** Redraw feet slot */
    private void drawFeet() {
        drawSlot(SLOT_FEET, feetButton, feetImage);
    }

    /** Redraw gloves slot */
    private void drawGloves() {
        drawSlot(SLOT_GLOVES, glovesButton, glovesImage);
    }
}