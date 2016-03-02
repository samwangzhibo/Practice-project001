package com.example.zybang.myapplication.com.example.zybang.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.zybang.myapplication.R;

/**
 * Created by zybang on 2016/3/2.
 */
public class MyUnlockView extends View {
    private Point[][] points = new Point[3][3];
    private boolean isinit = false;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float mouseX, mouseY;
    private float r;

    private boolean isdraw = false;

    private Bitmap bitmap_normal = BitmapFactory.decodeResource(getResources(), R.drawable.normal);
    private Bitmap bitmap_press = BitmapFactory.decodeResource(getResources(), R.drawable.press);
    private Bitmap bitmap_error = BitmapFactory.decodeResource(getResources(), R.drawable.error);

    public MyUnlockView(Context context) {
        super(context);
    }

    public MyUnlockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isinit) {
            init();
        }
        drawPoints(canvas);

    }

    private void drawPoints(Canvas canvas) {
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                if (points[i][j].state == Point.STATE_NORMAL) {
                    canvas.drawBitmap(bitmap_normal, points[i][j].x - r, points[i][j].y - r, paint);
                } else if (points[i][j].state == Point.STATE_PRESS) {
                    canvas.drawBitmap(bitmap_press, points[i][j].x - r, points[i][j].y - r, paint);
                } else {
                    canvas.drawBitmap(bitmap_error, points[i][j].x - r, points[i][j].y - r, paint);
                }

            }
        }
    }

    private int[] getSelectedPoint() {
        Point pmouse = new Point(mouseX, mouseY);
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                if (points[i][j].distance(pmouse) < r) {
                    points[i][j].state = Point.STATE_PRESS;
                    int[] result = new int[2];
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }

    private void init() {

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);


        int width = getWidth();
        int height = getHeight();
        int offset = Math.abs(width - height) / 2;
        int offsetX, offsetY;
        int space;
        if (width > height) {
            space = height / 4;
            offsetX = offset;
            offsetY = 0;
        } else {
            space = width / 4;
            offsetX = 0;
            offsetY = offset;
        }
        points[0][0] = new Point(offsetX + space, offsetY + space);
        points[0][1] = new Point(offsetX + 2 * space, offsetY + space);
        points[0][2] = new Point(offsetX + 3 * space, offsetY + space);

        points[1][0] = new Point(offsetX + space, offsetY + 2 * space);
        points[1][1] = new Point(offsetX + 2 * space, offsetY + 2 * space);
        points[1][2] = new Point(offsetX + 3 * space, offsetY + 2 * space);

        points[2][0] = new Point(offsetX + 1 * space, offsetY + 3 * space);
        points[2][1] = new Point(offsetX + 2 * space, offsetY + 3 * space);
        points[2][2] = new Point(offsetX + 3 * space, offsetY + 3 * space);

        r = bitmap_normal.getWidth() / 2;
        isinit = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mouseX = event.getX();
        mouseY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //// TODO: 2016/3/2 初始化point状态 判断边界条件
                resetPoints();

                break;
            case MotionEvent.ACTION_MOVE:
                getSelectedPoint();
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("wzb", "onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void resetPoints() {
        //清空状态
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                points[i][j].state = Point.STATE_NORMAL;
            }
        }
    }
}
