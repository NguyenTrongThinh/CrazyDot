package com.solo.crazydot;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CrazyDotView extends SurfaceView implements SurfaceHolder.Callback {
    MainThread thread;
    CharacterSprite characterSprite;
    private Context context;
    public CrazyDotView(Context context) {
        super(context);
        this.context = context;
        getHolder().addCallback(this);
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(),R.drawable.mario));
        thread = new MainThread(getHolder(), this);
        setFocusable(true);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        characterSprite.draw(canvas);
    }

    public void update(float x, float y) {
        characterSprite.update(x, y);
    }
}
