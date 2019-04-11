package com.tyurinevgeny.rustgear;

/**
 * Game item
 */

class RustItem {
    String name, image, slot;
    String[] attrs;

    RustItem(String name, String image, String slot, String[] attrs) {
        this.name = name;
        this.image = image;
        this.slot = slot;
        this.attrs = attrs;
    }
}
