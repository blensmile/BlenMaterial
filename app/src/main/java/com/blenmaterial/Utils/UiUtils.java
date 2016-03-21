package com.blenmaterial.Utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

import com.blenmaterial.MyApplication;


public class UiUtils {

	//获取Context对象
	public static Context getContext() {
		return MyApplication.getContext();
	}

	//获取Handler对象
	public static Handler getHandler() {
		return MyApplication.getHandler();
	}

	//获取主线程id
	public static int getMainThreadId() {
		return MyApplication.getMainThreadId();
	}

	///////////////////////////////////////////////////

	//获取字符串
	public static String getString(int id) {
		return getContext().getResources().getString(id);
	}

	//获取字符串数组
	public static String[] getStringArray(int id) {
		return getContext().getResources().getStringArray(id);
	}

	//获取颜色
	public static int getColor(int id) {
		return getContext().getResources().getColor(id);
	}

	//获取颜色的状态选择器
	public static ColorStateList getColorStateList(int id) {
		return getContext().getResources().getColorStateList(id);
	}

	//获取尺寸(单位是像素)
	public static int getDimen(int id) {
		return getContext().getResources().getDimensionPixelSize(id);
	}

	//获取图片
	public static Drawable getDrawable(int id) {
		return getContext().getResources().getDrawable(id);
	}

	////////////////////////////////////////////////////////

	public static int dip2px(float dip) {
		float density = getContext().getResources().getDisplayMetrics().density;
		return (int) (dip * density + 0.5f);
	}

	public static float px2dip(int px) {
		float density = getContext().getResources().getDisplayMetrics().density;
		return px / density;
	}

	////////////////////////////////////////////////////////

	//根据布局文件id加载布局对象
	public static View inflate(int id) {
		View view = View.inflate(getContext(), id, null);
		return view;
	}

	///////////////////////////////////////////////////////

	//判断当前是否在ui线程
	public static boolean isRunOnUiThread() {
		int myTid = android.os.Process.myTid();//获取当前线程id
		if (getMainThreadId() == myTid) {
			return true;
		} else {
			return false;
		}
	}

	//运行在主线程
	public static void runOnUiThread(Runnable r) {
		if (isRunOnUiThread()) {//如果已经是主线程,就直接运行
			r.run();
		} else {
			getHandler().post(r);//使用handler发送一个可执行的对象, 此对象运行在主线程
		}
	}

}
