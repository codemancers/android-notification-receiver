package com.imakhilc.notificationreader.models;

import android.graphics.Bitmap;

public class NotificationModel {
    String name;
    Bitmap imaBitmap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return imaBitmap;
    }

    public void setImage(Bitmap imaBitmap) {
        this.imaBitmap = imaBitmap;
    }
}