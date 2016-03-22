package com.blenmaterial.Layout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/** 这是从张兴业的博客中拷贝的代码,博客地址:http://blog.csdn.net/xyz_lmn/article/details/48055919
 *  实现了FloatingActionButton的旋转效果
 *
 * Created by Blensmile on 2016/3/21.
 */
public class RotateBehavior  extends CoordinatorLayout.Behavior<FloatingActionButton> {
    private static final String TAG = RotateBehavior.class.getSimpleName();

    public RotateBehavior() {
    }

    public RotateBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //自定义behavior需要重写的两个方法
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        float translationY = getFabTranslationYForSnackbar(parent, child);
        float percentComplete = -translationY / dependency.getHeight();
        child.setRotation(-45 * percentComplete);
        child.setTranslationY(translationY);
        return false;
    }

    private float getFabTranslationYForSnackbar(CoordinatorLayout parent, FloatingActionButton fab) {
        float minOffset = 0;
        final List<View> dependencies = parent.getDependencies(fab);
        for (int i = 0, z = dependencies.size(); i < z; i++) {
            final View view = dependencies.get(i);
            if (view instanceof Snackbar.SnackbarLayout && parent.doViewsOverlap(fab, view)) {
                minOffset = Math.min(minOffset,ViewCompat.getTranslationY(view) - view.getHeight());
            }
        }

        return minOffset;
    }
}
