package com.tyurinevgeny.rustgear;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Window with character gear
 */

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ITEM = 1;

    /** GUI controls */
    private Button headButton;

    /** Window init */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // GUI controls init
        headButton = findViewById(R.id.buttonHead);
        headButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemsList(R.array.head);
            }
        });
    }

    /** Run window with list of items */
    private void showItemsList(int listId) {
        Intent intent = ItemsListActivity.getIntent(MainActivity.this,
                "", listId);
        startActivityForResult(intent, REQUEST_CODE_ITEM);
    }

    /** User returns from another window */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
