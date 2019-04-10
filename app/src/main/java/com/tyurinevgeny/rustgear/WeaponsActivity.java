package com.tyurinevgeny.rustgear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WeaponsActivity extends AppCompatActivity {
    // Weapon slots
    private static final int REQUEST_CODE_PRIMARY_WEAPON = 1;
    private static final int REQUEST_CODE_SECONDARY_WEAPON = 2;
    private static final int REQUEST_CODE_BACKUP_WEAPON = 3;
    public static final String SLOT_PRIMARY_WEAPON = "primary_weapon";
    public static final String SLOT_SECONDARY_WEAPON = "secondary_weapon";
    public static final String SLOT_BACKUP_WEAPON = "backup_weapon";
    // GUI controls
    private Button pweaponButton;
    private ImageView pweaponImage;
    private Button sweaponButton;
    private ImageView sweaponImage;
    private Button bweaponButton;
    private ImageView bweaponImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapons);
        // Primary weapon slot init
        pweaponButton = findViewById(R.id.buttonWeaponPrimary);
        pweaponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemsList(SLOT_PRIMARY_WEAPON, pweaponButton.getText().toString());
            }
        });
        pweaponImage = findViewById(R.id.imageWeaponPrimary);
        drawPWeapon();
        // Secondary weapon slot init
        sweaponButton = findViewById(R.id.buttonWeaponSecondary);
        sweaponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemsList(SLOT_SECONDARY_WEAPON, sweaponButton.getText().toString());
            }
        });
        sweaponImage = findViewById(R.id.imageWeaponSecondary);
        drawSWeapon();
        // Backup weapon slot init
        bweaponButton = findViewById(R.id.buttonWeaponBackup);
        bweaponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemsList(SLOT_BACKUP_WEAPON, bweaponButton.getText().toString());
            }
        });
        bweaponImage = findViewById(R.id.imageWeaponBackup);
        drawBWeapon();
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
            case REQUEST_CODE_PRIMARY_WEAPON:
                PrefsWork.saveSlot(SLOT_PRIMARY_WEAPON, item, this);
                drawPWeapon();
                break;
            case REQUEST_CODE_SECONDARY_WEAPON:
                PrefsWork.saveSlot(SLOT_SECONDARY_WEAPON, item, this);
                drawSWeapon();
                break;
            case REQUEST_CODE_BACKUP_WEAPON:
                PrefsWork.saveSlot(SLOT_BACKUP_WEAPON, item, this);
                drawBWeapon();
                break;
        }
    }

    /** Run window with list of items */
    void showItemsList(String slot, String equipped) {
        Intent intent = ItemsListActivity.getIntent(WeaponsActivity.this,
                equipped, "weapon");
        int requestCode = 0;
        switch (slot) {
            case SLOT_PRIMARY_WEAPON:
                requestCode = REQUEST_CODE_PRIMARY_WEAPON;
                break;
            case SLOT_SECONDARY_WEAPON:
                requestCode = REQUEST_CODE_SECONDARY_WEAPON;
                break;
            case SLOT_BACKUP_WEAPON:
                requestCode = REQUEST_CODE_BACKUP_WEAPON;
                break;
        }
        if (requestCode > 0)
            startActivityForResult(intent, requestCode);
    }

    /** Redraw slot */
    private void drawSlot(String slot, Button button, ImageView imageView) {
        String gear = PrefsWork.readSlot(slot, this);
        if (gear != null && !gear.equals(""))
            button.setText(gear);
        else
            button.setText(slot);
        imageView.setImageDrawable(GameData.getItemImage(gear));
    }

    /** Redraw primary weapon slot */
    private void drawPWeapon() {
        drawSlot(SLOT_PRIMARY_WEAPON, pweaponButton, pweaponImage);
    }

    /** Redraw secondary weapon slot */
    private void drawSWeapon() {
        drawSlot(SLOT_SECONDARY_WEAPON, sweaponButton, sweaponImage);
    }

    /** Redraw backup weapon slot */
    private void drawBWeapon() {
        drawSlot(SLOT_BACKUP_WEAPON, bweaponButton, bweaponImage);
    }
}
