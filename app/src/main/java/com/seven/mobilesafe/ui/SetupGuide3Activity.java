package com.seven.mobilesafe.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.seven.mobilesafe.R;

/**
 * Created by seven on 7/25/16.
 */

public class SetupGuide3Activity extends Activity implements View.OnClickListener {
    private GestureDetector gesture = null;
    private Button contact = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setupactivity3);

        contact = (Button) findViewById(R.id.bt_select_contact);

        contact.setOnClickListener(this);

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
                Intent intent = new Intent(this,SetupGuide4Activity.class);
                startActivity(intent);
                break;

            case 1:
                finish();
                Intent intent1 = new Intent(this,SetupGuide2Activity.class);
                startActivity(intent1);
                break;

        }
        overridePendingTransition(R.anim.alpha_in,R.anim.alpha_out);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);   //显示联系人信息
        intent.setType("vnd.android.cursor.item/phone");
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        System.out.println(uri.toString());
    }
}
