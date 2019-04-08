package com.tyurinevgeny.rustgear;

/**
 * Cloth item
 */

public class ClothItem {
    String name, image, slot,
            cold, radiation, explosion,
            stab, bullet, bite,
            ingridients;

    public ClothItem(String name, String image, String slot,
                     String cold, String radiation, String explosion,
                     String stab, String bullet, String bite,
                     String ingridients) {
        this.name = name;
        this.image = image;
        this.slot = slot;
        this.cold = cold;
        this.radiation = radiation;
        this.explosion = explosion;
        this.stab = stab;
        this.bullet = bullet;
        this.bite = bite;
        this.ingridients = ingridients;
    }
}
