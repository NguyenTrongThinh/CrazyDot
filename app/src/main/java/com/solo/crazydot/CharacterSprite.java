package com.solo.crazydot;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CharacterSprite {
    private Bitmap image;

    private float x, y;
    private int xVelocity = 10;
    private int yVelocity = 5;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;


    public CharacterSprite(Bitmap bmp) {
        image = bmp;
        x = 0;
        y = 0;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public void update(float x, float y) {

        if (x > screenWidth - image.getWidth()) {
            this.x = screenWidth - image.getWidth();
        } else if (x < 0) {
            this.x = 0;
        } else {
            this.x = x;
        }
        if (y > screenHeight - image.getHeight()) {
            this.y = screenHeight - image.getHeight();
        } else if (y < 0) {
            this.y = 0;
        } else {
            this.y = y;
        }
    }


}