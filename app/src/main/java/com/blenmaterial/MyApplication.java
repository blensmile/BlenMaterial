package com.blenmaterial;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Blensmile on 2016/3/17.
 */
public class MyApplication extends Application {

    private static Context mContext;
    private static Context context;
    private static Handler handler;
    private static int mainThreadId;

    public static DisplayMetrics mMatics;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
//        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
//
//        mWindowWidth = wm.getDefaultDisplay().getWidth();
//
//        mWindowheight = wm.getDefaultDisplay().getHeight();
//
        mMatics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(mMatics);
        //初始化Context
        context = getApplicationContext();
        //初始化Handler
        handler = new Handler();
        //初始化当前线程的id,主线程(UI线程)id
        mainThreadId = android.os.Process.myTid();

    }



    public static Context getContext(){
        return mContext;
    }


    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

}

