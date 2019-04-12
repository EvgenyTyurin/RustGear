package com.tyurinevgeny.rustgear;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tyurinevgeny.rustgear.my_ads.MyAds;
import com.tyurinevgeny.rustgear.my_ads.MyProduct;

/**
 * Window with selected and equipped items info
 */

public class ItemInfoActivity extends AppCompatActivity {
    private static final String EXTRA_SELECTED = "rust.selected";
    private static final String EXTRA_EQUIPPED = "rust.equipped";

    /** Window init */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        // My ads init
        final MyProduct myProduct = MyAds.getInstance(this).getRandomAds();
        if (myProduct != null) {
            ImageButton btnMyAds = findViewById(R.id.buttonMyAds);
            btnMyAds.setImageDrawable(GameData.getImgByName(myProduct.getImg()));
            btnMyAds.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(myProduct.getUrl()));
                    startActivity(i);
                }
            });
        }
        // Selected item info
        Intent intent = getIntent();
        String selectedItemName = intent.getStringExtra(EXTRA_SELECTED);
        ImageView imageSelected = findViewById(R.id.selectedImage);
        imageSelected.setImageDrawable(GameData.getItemImage(selectedItemName));
        TextView viewSelectedName = findViewById(R.id.selectedName);
        viewSelectedName.setText(selectedItemName);
        RustItem selectedItem = GameData.getItem(selectedItemName);
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
        TextView textSelectedAttr6 = findViewById(R.id.selectedAttr6);
        if (selectedItem.attrs.length > 6)
            textSelectedAttr6.setText(selectedItem.attrs[6]);
        TextView textSelectedAttr7 = findViewById(R.id.selectedAttr7);
        if (selectedItem.attrs.length > 7)
            textSelectedAttr7.setText(selectedItem.attrs[7]);
        // Equipped item info
        String equippedItemName = intent.getStringExtra(EXTRA_EQUIPPED);
        RustItem equippedItem = GameData.getItem(equippedItemName);
        if (equippedItem != null) {
            ImageView imageEquipped = findViewById(R.id.equippedImage);
            imageEquipped.setImageDrawable(GameData.getItemImage(equippedItemName));
            TextView viewEquippedName = findViewById(R.id.equippedName);
            viewEquippedName.setText(equippedItemName);
            TextView textEquippedCold = findViewById(R.id.equippedCold);
            if (equippedItem.attrs.length > 0)
                textEquippedCold.setText(equippedItem.attrs[0]);
            TextView textEquippedRad = findViewById(R.id.equippedRad);
            if (equippedItem.attrs.length > 1)
                textEquippedRad.setText(equippedItem.attrs[1]);
            TextView textEquippedExpl = findViewById(R.id.equippedExplosion);
            if (equippedItem.attrs.length > 2)
                textEquippedExpl.setText(equippedItem.attrs[2]);
            TextView textEquippedStab = findViewById(R.id.equippedStab);
            if (equippedItem.attrs.length > 3)
                textEquippedStab.setText(equippedItem.attrs[3]);
            TextView textEquippedBullet = findViewById(R.id.equippedBullet);
            if (equippedItem.attrs.length > 4)
                textEquippedBullet.setText(equippedItem.attrs[4]);
            TextView textEquippedBite = findViewById(R.id.equippedBite);
            if (equippedItem.attrs.length > 5)
                textEquippedBite.setText(equippedItem.attrs[5]);
            TextView textEquippedAttr6 = findViewById(R.id.equippedAttr6);
            if (equippedItem.attrs.length > 6)
                textEquippedAttr6.setText(equippedItem.attrs[6]);
            TextView textEquippedAttr7 = findViewById(R.id.equippedAttr7);
            if (equippedItem.attrs.length > 7)
                textEquippedAttr7.setText(equippedItem.attrs[7]);
        }
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
        intent.putExtra(EXTRA_EQUIPPED, equipped);
        return intent;
    }
}
