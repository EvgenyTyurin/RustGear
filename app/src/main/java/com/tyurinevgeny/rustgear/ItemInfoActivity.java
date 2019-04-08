package com.tyurinevgeny.rustgear;

import android.content.ClipData;
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
        textSelectedCold.setText(selectedItem.cold);
        TextView textSelectedRad = findViewById(R.id.selectedRad);
        textSelectedRad.setText(selectedItem.radiation);
        TextView textSelectedExpl = findViewById(R.id.selectedExplosion);
        textSelectedExpl.setText(selectedItem.explosion);
        TextView textSelectedStab = findViewById(R.id.selectedStab);
        textSelectedStab.setText(selectedItem.stab);
        TextView textSelectedBullet = findViewById(R.id.selectedBullet);
        textSelectedBullet.setText(selectedItem.bullet);
        TextView textSelectedBite = findViewById(R.id.selectedBite);
        textSelectedBite.setText(selectedItem.bite);
        TextView textSelectedIngr = findViewById(R.id.selectedIngredients);
        textSelectedIngr.setText(selectedItem.ingridients);
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
