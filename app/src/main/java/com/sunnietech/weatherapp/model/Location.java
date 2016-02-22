package com.sunnietech.weatherapp.model;

/**
 * Created by qlzh727 on 2/21/16.
 */
public class Location {
    int image;
    String name;

    public Location (int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
