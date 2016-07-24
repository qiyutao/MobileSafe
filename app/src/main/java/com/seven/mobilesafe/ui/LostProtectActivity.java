package com.seven.mobilesafe.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.seven.mobilesafe.R;

/**
 * Created by seven on 7/24/16.
 */

public class LostProtectActivity extends Activity implements View.OnClickListener {
    private SharedPreferences sp = null;
    private Dialog dialog = null;
    private EditText et_pwd = null;
    private EditText et_pwd_confirm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lostprotectactivity);

        sp = getSharedPreferences("password", Context.MODE_PRIVATE);

        if(isPWDSetup()) {
            showNormalDialog();
        } else {
            showFirstEntryDialog();
        }
    }

    private void showNormalDialog() {
        dialog = new Dialog(this,R.style.MyDialog);
        View view = View.inflate(this,R.layout.normal_entry_dialog,null);

        et_pwd = (EditText) view.findViewById(R.id.et_normal_entry_pwd);
        //et_pwd_confirm = (EditText) view.findViewById(R.id.et_first_entry_pwd_confirm);

        Button bt_ok = (Button) view.findViewById(R.id.bt_normal_dialog_ok);
        Button bt_cancel = (Button) view.findViewById(R.id.bt_normal_dialog_cancel);

        bt_ok.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);

        dialog.setContentView(view);
        dialog.show();
    }

    private boolean isPWDSetup() {
        String passwd = sp.getString("password",null);
        if(passwd!=null)
            return true;
        return false;
    }

    private void showFirstEntryDialog() {
        dialog = new Dialog(this,R.style.MyDialog);
        View view = View.inflate(this,R.layout.first_entry_dialog,null);

        et_pwd = (EditText) view.findViewById(R.id.et_first_entry_pwd);
        et_pwd_confirm = (EditText) view.findViewById(R.id.et_first_entry_pwd_confirm);

        Button bt_ok = (Button) view.findViewById(R.id.bt_first_dialog_ok);
        Button bt_cancel = (Button) view.findViewById(R.id.bt_first_dialog_cancel);

        bt_ok.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);

        dialog.setContentView(view);
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_first_dialog_ok:
                String pwd = et_pwd.getText().toString();
                String confirm = et_pwd_confirm.getText().toString();

                if("".equals(pwd)||"".equals(confirm)) {
                    Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                    return ;
                } else {
                    if(confirm.equals(pwd)) {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("password",pwd);
                        editor.commit();
                    } else {
                        Toast.makeText(getApplicationContext(),"两次密码不同",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                dialog.dismiss();
                break;
            case R.id.bt_first_dialog_cancel:
                dialog.dismiss();

                break;
            case R.id.bt_normal_dialog_ok:
                //Log.i("111111111111","sdasda");
                String pwd1 = et_pwd.getText().toString();
                if("".equals(pwd1)) {
                    Toast.makeText(getApplicationContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String save = sp.getString("password",null);
                    if(save.equals(pwd1)) {
                        dialog.dismiss();
                        return;
                    } else {
                        Toast.makeText(getApplicationContext(),"密码错误",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

            case R.id.bt_normal_dialog_cancel:
                dialog.dismiss();
                break;
        }
        finish();
    }
}
