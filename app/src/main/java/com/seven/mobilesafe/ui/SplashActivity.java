package com.seven.mobilesafe.ui;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seven.mobilesafe.R;

public class SplashActivity extends Activity {
    private TextView tv_splash_version = null;
    private LinearLayout main_splash = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_PROGRESS);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash);

        tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
        String version = getVersion();
        tv_splash_version.setText(version);

        main_splash = (LinearLayout) findViewById(R.id.main_splash);
        AlphaAnimation aa = new AlphaAnimation(0.0f,1.0f);
        aa.setDuration(200);
        main_splash.setAnimation(aa);
    }

    private String getVersion() {
       PackageManager manager =  getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(),0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "Unknow Version";
        }
    }
}
