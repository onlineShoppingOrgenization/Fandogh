package com.app.behin.fandogh.adapter;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by najme_sa on 29/07/2016.
 */
public class Item {
    private String name;
    private int image;

    public Item() {
    }

    public Item(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
