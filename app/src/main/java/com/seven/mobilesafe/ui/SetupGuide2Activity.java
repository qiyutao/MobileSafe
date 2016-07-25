package com.seven.mobilesafe.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.seven.mobilesafe.R;

/**
 * Created by seven on 7/25/16.
 */

public class SetupGuide2Activity extends Activity implements View.OnClickListener {
    private GestureDetector gesture = null;
    private Button btn = null;
    private CheckBox cb = null;
    private SharedPreferences sp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setupactivity2);

        gesture = new GestureDetector(this,onGestureListener);

        sp = getSharedPreferences("config", Context.MODE_PRIVATE) ;

        btn = (Button) findViewById(R.id.bt_setup2_bind);
        cb = (CheckBox) findViewById(R.id.cb_setup2);

        cb.setEnabled(false);

        btn.setOnClickListener(this);

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
                Intent intent = new Intent(this,SetupGuide3Activity.class);
                startActivity(intent);
                break;
            case 1:
                finish();
                Intent intent1 = new Intent(this,SetupGuideActivity.class);
                startActivity(intent1);
                break;
        }
        overridePendingTransition(R.anim.alpha_in,R.anim.alpha_out);
    }

    @Override
    public void onClick(View v) {
        TelephonyManager manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String sim = manager.getSimSerialNumber();

        Toast.makeText(getApplicationContext(),"SIM卡信息："+sim,Toast.LENGTH_SHORT).show();

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("sim",sim);
        editor.commit();

        cb.setChecked(true);
        cb.setText("已绑定");

    }
}
