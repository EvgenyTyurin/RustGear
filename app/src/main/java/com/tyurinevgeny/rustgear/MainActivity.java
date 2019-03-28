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
                showItemsList(R.array.head, headButton.getText().toString());
            }
        });
        headImage = findViewById(R.id.imageHead);
    }

    /** Run window with list of items */
     void showItemsList(int listId, String equipped) {
        Intent intent = ItemsListActivity.getIntent(MainActivity.this,
                "", listId);
        int requestCode = 0;
        switch (listId) {
            case R.array.head:
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
                PrefsWork.saveSlot("head", item, this);
                drawHead();
                break;
        }
    }

    /** Redraw slot */
    private void drawSlot(String slot, Button button, ImageView imageView) {
        String gear = PrefsWork.readSlot(slot, this);
        button.setText(gear);
        imageView.setImageDrawable(GameData.getItemImage(gear));
    }

    /** Redraw head slot */
    private void drawHead() {
        drawSlot("head", headButton, headImage);
    }

}
