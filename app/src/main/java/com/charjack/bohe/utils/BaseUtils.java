package com.charjack.bohe.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/3/23.
 */
public class BaseUtils {

    public static boolean isFirst(Context context){
        SharedPreferences sp = context.getSharedPreferences("isFirst",Context.MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst", true);
        int versionCode = sp.getInt("versionCode",1);
        if(isFirst ||getVersionCode(context) > versionCode){
            sp.edit().putBoolean("isFirst", false).apply();
            sp.edit().putInt("versionCode",getVersionCode(context)).apply();
        }
        return isFirst;
    }

    /**
     * 获取当前应用的版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info.versionCode;
    }
    /**
     * 获取保存的tab列表
     *
     * @param
     * @return
     */
    public static String[] getTablelist(Context context) {
        String regularEx = "#";
        String[] str = null;
        SharedPreferences sp = context.getSharedPreferences("tabdata", Context.MODE_PRIVATE);
        String values;
        values = sp.getString("tabdata", "noone");

        if(values=="noone"){
            str = new String[]{"精选", "卧室","厨房","客厅","卫浴","书房"};
            ArrayList<String> tabnames=new ArrayList<String>();
            if (str != null && str.length > 0) {
                for (String value : str) {
                    tabnames.add(value);
                }
                setTablelist(context,tabnames);
            }
            values = sp.getString("tabdata", "noone");
        }

        str = values.split(regularEx);
        return str;
    }

    /**
     * 设置tab列表
     *
     * @param
     * @return
     */
    public static void setTablelist(Context context,ArrayList<String> values) {
        String regularEx = "#";
        String str = "";
        SharedPreferences sp = context.getSharedPreferences("tabdata", Context.MODE_PRIVATE);
        if (values != null && values.size() > 0) {
            for (String value : values) {
                str += value;
                str += regularEx;
            }
            SharedPreferences.Editor et = sp.edit();
            et.putString("tabdata", str);
            et.commit();
        }
    }


    public static String[] yugetTablelist(Context context) {
        String regularEx = "#";
        String[] str = null;
        SharedPreferences sp = context.getSharedPreferences("yutabdata", Context.MODE_PRIVATE);
        String values;
        values = sp.getString("yutabdata", "no");
        if(values=="no"){
            return null;
        }
        str = values.split(regularEx);
        return str;
    }

    /**
     * 设置tab列表
     *
     * @param
     * @return
     */
    public static void yusetTablelist(Context context,ArrayList<String> values) {
        String regularEx = "#";
        String str = "";
        SharedPreferences sp = context.getSharedPreferences("yutabdata", Context.MODE_PRIVATE);
        if (values != null && values.size() > 0) {
            for (String value : values) {
                str += value;
                str += regularEx;
            }
            SharedPreferences.Editor et = sp.edit();
            et.putString("yutabdata", str);
            et.commit();
        } else{
            SharedPreferences.Editor et = sp.edit();
            et.putString("yutabdata", null);
            et.commit();
        }
    }
}
