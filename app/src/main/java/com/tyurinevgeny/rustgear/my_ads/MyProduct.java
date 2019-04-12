package com.tyurinevgeny.rustgear.my_ads;

public class MyProduct {
    private String img, url;

    public MyProduct(String img, String url) {
        this.img = img;
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public String getUrl() {
        return url;
    }
}
