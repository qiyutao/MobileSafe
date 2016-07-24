package com.seven.mobilesafe.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.seven.mobilesafe.R;

/**
 * Created by seven on 7/23/16.
 */

public class MainActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private GridView gv_main = null;
    private MainUIAdapter adapter = null;
    private SharedPreferences share = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        share = getSharedPreferences("config", Context.MODE_PRIVATE);

        gv_main = (GridView) findViewById(R.id.gv_main);
        adapter = new MainUIAdapter(this);
        gv_main.setAdapter(adapter);
        gv_main.setOnItemLongClickListener(this);
        gv_main.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i) {
                case 0:
                    Intent intent = new Intent(this,LostProtectActivity.class);
                    startActivity(intent);
                    //Log.i("2323333","sdfsdf");
                    break;
            }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, final View view, int i, long l) {
        if(i==0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("设置");
            builder.setMessage("请输入要更改的名称");

            final EditText text = new EditText(this);
            text.setHint("请输入文本");
            builder.setView(text);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SharedPreferences.Editor edit = share.edit();
                    edit.putString("lost_name",text.getText().toString());
                    edit.commit();

                    TextView tv = (TextView) view.findViewById(R.id.tv_main_name);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create().show();
        }


        return false;
    }
}
