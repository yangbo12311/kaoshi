package com.yangbo28.info;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.xutils.x;

import java.util.List;

public class JkbdApplication extends Application {

    private SharedPreferences mPreferences;
    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(base);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //注册消息类型的时候判断当前的进程是否在主进程
        if(isMainProcess()){
            //注册xutils
            x.Ext.init(this);
            mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        }
    }


    //判断是否是主进程
    public boolean isMainProcess(){
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos =  am.getRunningAppProcesses();
        int myPid = android.os.Process.myPid();
        String mainProcessName = getPackageName();
        for(ActivityManager.RunningAppProcessInfo info :processInfos){
            if(myPid == info.pid&&mainProcessName.equals(info.processName)){
                return true;
            }
        }
        return false;
    }

    private static final String KEY_NICK = "key_nick";

    //获取key_nick值
    public String getNick() {
        return getString(KEY_NICK, "");
    }
    //缓存key_nick值
    public void setNick(String key_nick) {
        saveString(KEY_NICK, key_nick);
    }



    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key, String def) {
        return mPreferences.getString(key, def);
    }


}
