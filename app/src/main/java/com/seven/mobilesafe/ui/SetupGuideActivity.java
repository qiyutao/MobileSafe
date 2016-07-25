package com.seven.mobilesafe.ui;

import android.app.Activity;
import android.content.Intent;
import android.gesture.Gesture;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.seven.mobilesafe.R;

/**
 * Created by seven on 7/25/16.
 */

public class SetupGuideActivity extends Activity {
    private GestureDetector gesture = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setupactivity);

        gesture = new GestureDetector(this,onGestureListener);

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
                Intent intent = new Intent(this,SetupGuide2Activity.class);
                startActivity(intent);

                overridePendingTransition(R.anim.alpha_in,R.anim.alpha_out);
                break;


        }
    }
}
