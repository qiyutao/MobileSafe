package com.seven.mobilesafe.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.seven.mobilesafe.R;

/**
 * Created by seven on 7/25/16.
 */

public class SetupGuide4Activity extends Activity {
    private GestureDetector gesture = null;
    private SharedPreferences sp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setupactivity4);

        gesture = new GestureDetector(this,onGestureListener);
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);

    }

    private GestureDetector.OnGestureListener onGestureListener =
            new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                       float velocityY) {
                    float x = e2.getX() - e1.getX();
                    float y = e2.getY() - e1.getY();

                    if (x > 0) {
                        doResult(1);
                    } else if (x < 0) {
                        doResult(0);
                    }
                    return true;
                }
            };

    public boolean onTouchEvent(MotionEvent event) {
        return gesture.onTouchEvent(event);
    }

    public void doResult(int action) {

        switch (action) {
            case 0:
                finish();
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("setup",true);
                editor.commit();
                Intent intent = new Intent(this,LostProtectActivity.class);
                startActivity(intent);
                break;

        }
        overridePendingTransition(R.anim.alpha_in,R.anim.alpha_out);
    }
}
