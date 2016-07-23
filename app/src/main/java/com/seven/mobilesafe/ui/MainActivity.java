package com.seven.mobilesafe.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.seven.mobilesafe.R;

/**
 * Created by seven on 7/23/16.
 */

public class MainActivity extends Activity {
    private GridView gv_main = null;
    private MainUIAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gv_main = (GridView) findViewById(R.id.gv_main);
        adapter = new MainUIAdapter(this);
        gv_main.setAdapter(adapter);
    }
}
