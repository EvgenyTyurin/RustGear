package com.tyurinevgeny.rustgear;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

        Intent intent = getIntent();
        String selectedItemName = intent.getStringExtra(EXTRA_SELECTED);
        // Selected item info
        ImageView imageSelected = findViewById(R.id.selectedImage);
        imageSelected.setImageDrawable(GameData.getItemImage(selectedItemName));
        TextView viewSelectedName = findViewById(R.id.selectedName);
        viewSelectedName.setText(selectedItemName);
        ClothItem selectedItem = GameData.getItem(selectedItemName);
        TextView textSelectedCold = findViewById(R.id.selectedCold);
        if (selectedItem.attrs.length > 0)
            textSelectedCold.setText(selectedItem.attrs[0]);
        TextView textSelectedRad = findViewById(R.id.selectedRad);
        if (selectedItem.attrs.length > 1)
            textSelectedRad.setText(selectedItem.attrs[1]);
        TextView textSelectedExpl = findViewById(R.id.selectedExplosion);
        if (selectedItem.attrs.length > 2)
            textSelectedExpl.setText(selectedItem.attrs[2]);
        TextView textSelectedStab = findViewById(R.id.selectedStab);
        if (selectedItem.attrs.length > 3)
            textSelectedStab.setText(selectedItem.attrs[3]);
        TextView textSelectedBullet = findViewById(R.id.selectedBullet);
        if (selectedItem.attrs.length > 4)
            textSelectedBullet.setText(selectedItem.attrs[4]);
        TextView textSelectedBite = findViewById(R.id.selectedBite);
        if (selectedItem.attrs.length > 5)
            textSelectedBite.setText(selectedItem.attrs[5]);
        // Equip button
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
