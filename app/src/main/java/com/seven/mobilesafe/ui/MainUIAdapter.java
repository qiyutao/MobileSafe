package com.seven.mobilesafe.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.seven.mobilesafe.R;

/**
 * Created by seven on 7/23/16.
 */

public class MainUIAdapter extends BaseAdapter {
    private static String[] name = {"手机防盗","通讯卫士","软件管理","任务管理"
    ,"流量管理","手机杀毒","系统优化","高级工具","设置中心"};

    private static  int[] icon = {R.drawable.widget09,R.drawable.widget02,
            R.drawable.widget01,R.drawable.widget07,R.drawable.widget05,
            R.drawable.widget04,R.drawable.widget06,R.drawable.widget03,
            R.drawable.widget08};
    private Context context = null;
    private LayoutInflater inflater = null;
    private SharedPreferences sp = null;

    public MainUIAdapter(Context c) {
        context = c;
        inflater = LayoutInflater.from(context);
        sp = c.getSharedPreferences("config",Context.MODE_PRIVATE);
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = inflater.inflate(R.layout.mainscreen_item,null);
        ImageView iv_icon = (ImageView) view1.findViewById(R.id.iv_main_icon);
        TextView tv_text = (TextView) view1.findViewById(R.id.tv_main_name);

        iv_icon.setImageResource(icon[i]);
        tv_text.setText(name[i]);

        if(i==0) {
            String name = sp.getString("lost_name", null);
            if(name!=null) {
                tv_text.setText(name);
            }

        }

        return view1;
    }
}
