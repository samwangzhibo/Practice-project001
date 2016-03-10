package com.example.zybang.myapplication.com.example.zybang.customview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

/**
 * Created by zybang on 2016/3/9.
 */
public class SwapBackLayout extends FrameLayout {
    VelocityTracker tracker;
    Activity activity;
    private float startX;
    private float startY;
    OnSwapListener swapListener;
    /**
     * 横向的最大距离
     */
    private static int MAX_VERTICAL_OFFSET;
    /**
     * 最小滑动的加速的
     */
    private static int MIN_SLIDE_VELOCITY;

    private boolean isEnabled = true;
    private boolean isSwapBackDetected = false;

    ViewConfiguration viewConfiguration;

    public SwapBackLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        activity = (Activity) context;
        viewConfiguration = ViewConfiguration.get(context);
        float density = context.getResources().getDisplayMetrics().density;
        MAX_VERTICAL_OFFSET = (int) (30 * density);
        MIN_SLIDE_VELOCITY = (int) (1000 * density);
    }

    public SwapBackLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //可以做查询操作
    }

    /**
     * viewgrounp的最完成事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!isEnabled) {
            return super.dispatchTouchEvent(ev);
        }
        if (activity.isFinishing()) {
            return true;
        }
        int action = ev.getAction();
        if (isSwapBackDetected) {
            return true;
        }
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (tracker == null) {
                    tracker = VelocityTracker.obtain();
                } else {
                    tracker.clear();
                }
                tracker.addMovement(ev);
                startX = ev.getRawX();
                startY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (tracker != null) {
                    tracker.addMovement(ev);
                    tracker.computeCurrentVelocity(1000);
                    if (isEnabled && ev.getRawX() - startX > viewConfiguration.getScaledPagingTouchSlop()
                            && Math.abs(ev.getRawY() - startY) < MAX_VERTICAL_OFFSET &&
                            tracker.getXVelocity() > MIN_SLIDE_VELOCITY) {
                        if (swapListener != null) {

                        }
                        activity.onBackPressed();
                        isSwapBackDetected = true;
                        if (tracker != null) {
                            tracker.recycle();
                            tracker = null;
                        }
                        //拦截掉这次事件不分发
                        return true;
                    }
                }
                break;
        }
        try {
            super.dispatchTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    void setOnSwapListener(OnSwapListener swapListener) {
        this.swapListener = swapListener;
    }

    interface OnSwapListener {
        void onswap();
    }
}
