package com.example.zybang.myapplication.com.example.zybang.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;

/**
 * Created by zybang on 2016/3/24.
 */
public class SingleItemScrollView extends ScrollView implements View.OnClickListener {
    private int mScreenHeight;
    private ViewGroup mContainer;
    private Adapter mAdapter;
    private int mItemCount;
    private int mItemHeight;

    public SingleItemScrollView(Context context) {
        super(context);
    }

    public SingleItemScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //计算屏幕的高度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
        //mScreenHeight -= getStatusHeight(context);
    }

    public SingleItemScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mContainer = (ViewGroup) getChildAt(0);
        if (mAdapter != null) {
            mItemCount = mAdapter.getCount();
            mItemHeight = mScreenHeight / mItemCount;
            mContainer.removeAllViews();
            for (int i = 0; i < mAdapter.getCount(); i++) {
                addChildView(i);
            }
        }
        addChildView(0);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 在容器末尾添加一个Item
     *
     * @param i
     */
    private void addChildView(int i) {
        View item = mAdapter.getView(this, i);
        //设置参数
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight);
        item.setLayoutParams(lp);
        //设置tag
        item.setTag(i);
        //添加事件
        item.setOnClickListener(this);
        mContainer.addView(item);
    }

    /**
     * 在底部添加一个View,并移除第一个View
     */
    private void addChildToLast() {
        Log.e("wzb", "addChildToLast");
        int pos = (Integer) mContainer.getChildAt(1).getTag();
        addChildView(pos);
        mContainer.removeViewAt(0);
        this.scrollTo(0, 0);
    }

    /**
     * 在顶部添加一个View，并移除最后一个View
     */
    private void addchildToFirst() {
        Log.e("wzb", "addchildToFirst");
        int pos = (Integer) mContainer.getChildAt(1).getTag();
        addChildView(pos);
        mContainer.removeViewAt(mContainer.getChildCount() - 1);
        this.scrollTo(0, mItemCount);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        int scrollY = getScrollY();
        Log.e("wzb --- X:", ev.getX() + "");
        Log.e("wzb --- Y:", ev.getY() + "");
        Log.e("wzb ----- scrollY", scrollY + "\n");

        switch (action) {
            case MotionEvent.ACTION_MOVE:
                //
                if (scrollY == 0) {

                }
                break;
            case MotionEvent.ACTION_UP:
                return true;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 适配器
     */
    public static abstract class Adapter {
        public abstract View getView(SingleItemScrollView parent, int pos);

        public abstract int getCount();
    }
}
