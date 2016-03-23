package com.blenmaterial.Layout;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.blenmaterial.MyApplication;


/**
 * Created by Blensmile on 2016/2/22.
 */
public class SwipeLayout extends FrameLayout {


    private ViewDragHelper mDragHelper;
    private View mBackView; // 后布局
    private View mFrontView; // 前布局
    private int mHeight; // 控件高度
    private int mWidth; // 控件宽度
    private int mRange; // 拖拽范围
    private Status status = Status.Close; // 状态
    private OnSwipeListener onSwipeListener;
    public void setOnSwipeListener(OnSwipeListener onSwipeListener) {
        this.onSwipeListener = onSwipeListener;
    }

    public OnSwipeListener getOnSwipeListener() {
        return onSwipeListener;
    }


    public enum Status {
        Close,
        Open,
        Swiping
    }

    public interface OnSwipeListener {
        // 关闭了
        void onClose(SwipeLayout layout);
        // 打开了
        void onOpen(SwipeLayout layout);

        // 将要打开
        void onStartOpen(SwipeLayout layout);
        // 将要关闭
        void onStartClose(SwipeLayout layout);
    }

    public SwipeLayout(Context context) {
        this(context, null);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // 1. 创建ViewDragHelper对象
        mDragHelper = ViewDragHelper.create(this, callback);

    }
    // 3. 处理监听回调
    ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {

        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        public int getViewHorizontalDragRange(View child) {
            return mRange;
        }

        // 修正将要移动到的位置
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            // left 将要移动到的位置
            if(child == mFrontView){
                if(left < -mRange){
                    // 限定前布局左边界
                    left = -mRange;
                }else if (left > 0) {
                    // 限定前布局右边界
                    left = 0;
                }
            }else if (child == mBackView) {

                if(left < mWidth - mRange){
                    // 限定后布局左边界
                    left = mWidth- mRange;
                }else if (left > mWidth) {
                    // 限定后布局右边界
                    left = mWidth;
                }
            }

            return left;
        }

        // 控件位置变化之后, 传递给另一个
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            // left 移动到的最新位置
            // dx 刚刚发生的偏移量
            if(changedView == mFrontView){
                // 将前布局的变化量传递给后布局
                mBackView.offsetLeftAndRight(dx);
            }else if (changedView == mBackView) {
                // 将后布局的变化量传递给前布局
                mFrontView.offsetLeftAndRight(dx);
            }

            dispathSwipeEvent();

            invalidate(); // 兼容低版本
        }

        // 松手之后, 开关动画
        public void onViewReleased(View releasedChild, float xvel, float yvel) {

            // xvel 速度, 左 - , 右 +
            if(Math.abs(xvel) <= 20 && mFrontView.getLeft() < (- mRange * 0.5f)){
                open();
            }else if (xvel < -20) {
                open();
            }else {
                close();
            }

        }

    };

    protected void onFinishInflate() {
        super.onFinishInflate();

        mBackView = getChildAt(0);
        mFrontView = getChildAt(1);
    }

    /**
     * 更新状态, 执行回调
     */
    protected void dispathSwipeEvent() {

        Status lastStatus = status;
        status = updateStatus();

        // 状态发生变化, 执行监听回调
        if(lastStatus != status && onSwipeListener != null){
            if(status == Status.Open){
                onSwipeListener.onOpen(this);
            }else if (status == Status.Close) {
                onSwipeListener.onClose(this);
            }else {
                if(lastStatus == Status.Close){
                    onSwipeListener.onStartOpen(this);
                }else if (lastStatus == Status.Open) {
                    onSwipeListener.onStartClose(this);
                }
            }
        }

    }

    /**
     * @return 最新状态
     */
    private Status updateStatus() {
        int left = mFrontView.getLeft();
        if(left == - mRange){
            return Status.Open;
        }else if (left == 0) {
            return Status.Close;
        }
        return Status.Swiping;
    }

    /**
     * 关闭
     */
    protected void close() {
        close(true);
    }
    public void close(boolean isSmooth){
        int finalLeft = 0;
        if(isSmooth){
            // 1. 触发平滑动画
            if(mDragHelper.smoothSlideViewTo(mFrontView, finalLeft, 0)){
                ViewCompat.postInvalidateOnAnimation(this);// this表示被移动的控件所在的布局
            }

        }else {
            layoutContent(false);
        }
    }

    /**
     * 打开
     */
    protected void open() {
        open(true);
    }
    public void open(boolean isSmooth){
        int finalLeft = -mRange;
        if(isSmooth){
            // 1. 触发平滑动画
            if(mDragHelper.smoothSlideViewTo(mFrontView, finalLeft, 0)){
                ViewCompat.postInvalidateOnAnimation(this);// this表示被移动的控件所在的布局
            }

        }else {
            layoutContent(true);
        }
    }

    // 2. 维持平滑动画的继续
    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);// this表示被移动的控件所在的布局
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();

        mRange = mBackView.getMeasuredWidth();
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        layoutContent(false);
    }

    /**
     * 根据当前的开关状态, 摆放内容
     * @param isOpen 是否打开
     */
    private void layoutContent(boolean isOpen) {
        // 计算摆放前布局
        Rect frontRect = computeFrontRect(isOpen);
        mFrontView.layout(frontRect.left, frontRect.top, frontRect.right, frontRect.bottom);

        // 计算摆放后布局
        Rect backRect = computeBackRectViaFront(frontRect);
        mBackView.layout(backRect.left, backRect.top, backRect.right, backRect.bottom);

        // 将前布局前置
        bringChildToFront(mFrontView);
    }

    // 计算后布局的矩形区域
    private Rect computeBackRectViaFront(Rect frontRect) {
        int left = frontRect.right;
        return new Rect(left, 0, left + mRange, 0 + mHeight);
    }

    // 计算前布局的矩形区域
    private Rect computeFrontRect(boolean isOpen) {
        int left = 0;
        if(isOpen){
            left = - mRange;
        }
        return new Rect(left, 0, left + mWidth, 0 + mHeight);
    }

    private float mStartX;
    private float mStartY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //TODO 这里太霸道了,不能全部拦截,不然没法上下滚动了
        //getParent().requestDisallowInterceptTouchEvent(true);
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getX();
                mStartY = ev.getY();
                //return true;//这里不能返回true,太独了
                //getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = ev.getX();
                float endY = ev.getY();

                float dx = endX - mStartX;
                float dy = endY - mStartY;

                // 判断是否向左滑动
                if (Math.abs(dx) > Math.abs(dy) && mStartX > 30f && mStartX< MyApplication.mMatics.widthPixels-30) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    // here interrupt the flow of delivering TouchEvent, But I can't get a better solution
                    return /*this.onInterceptTouchEvent(ev);//*/this.onTouchEvent(ev);

                }
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            default:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    // 2. 转交拦截判断, 触摸事件
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //this is neccesary for onClick.
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        try {
            // 处理触摸事件
            mDragHelper.processTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }



}
