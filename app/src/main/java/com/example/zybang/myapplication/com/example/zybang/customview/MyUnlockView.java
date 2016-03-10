package com.example.zybang.myapplication.com.example.zybang.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.zybang.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zybang on 2016/3/2.
 */
public class MyUnlockView extends View {
    private Point[][] points = new Point[3][3];
    private boolean isinit = false;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint error_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float mouseX, mouseY;
    private float r;
    int space;
    OnDrawFinishListener listener;

    //画线用
    private ArrayList<Point> pList = new ArrayList<Point>();
    private ArrayList<Integer> passList = new ArrayList<Integer>();



    private boolean isdraw = false;
    float scaledDensity;

    private Bitmap bitmap_normal;
    private Bitmap bitmap_press;
    private Bitmap bitmap_error;

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

        if (pList.size() > 0) {
            Point a = pList.get(0);
            for (int i = 1; i < pList.size(); i++) {
                Point b = pList.get(i);
                drawLineByState(canvas, a, b);
                a = b;
            }
            if (isdraw) {
                drawLineByState(canvas, a, new Point(mouseX, mouseY));
            }
        }

    }

    void drawLineByState(Canvas canvas, Point a, Point b) {
        if (a.state == Point.STATE_PRESS) {
            canvas.drawLine(a.x, a.y, b.x, b.y, paint);
        } else if (a.state == Point.STATE_ERROR) {
            canvas.drawLine(a.x, a.y, b.x, b.y, error_paint);
        }
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

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);

        error_paint.setColor(Color.RED);
        error_paint.setStyle(Paint.Style.FILL);
        error_paint.setStrokeWidth(5);

        if ((scaledDensity = getResources().getDisplayMetrics().scaledDensity) >= 3) {
            scaledDensity = 3;
        }

        Log.e("wzb", "scaledDensity" + scaledDensity);

        int width = getWidth();
        int height = getHeight();
        int offset = Math.abs(width - height) / 2;
        int offsetX, offsetY;

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


        initBitmap();
        isinit = true;


    }

    private void initBitmap() {

        bitmap_normal = resizeImage(BitmapFactory.decodeResource(getResources(), R.drawable.normal, null), space * 2 / 3, space * 2 / 3);
        bitmap_press = resizeImage(BitmapFactory.decodeResource(getResources(), R.drawable.press, null), space * 2 / 3, space * 2 / 3);
        bitmap_error = resizeImage(BitmapFactory.decodeResource(getResources(), R.drawable.error, null), space * 2 / 3, space * 2 / 3);


        r = bitmap_normal.getWidth() / 2;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mouseX = event.getX();
        mouseY = event.getY();
        int[] ij;
        int i, j;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                resetPoints();
                ij = getSelectedPoint();
                if (ij != null) {
                    isdraw = true;
                    i = ij[0];
                    j = ij[1];
                    pList.add(points[i][j]);
                    passList.add(3 * i + j);
                    points[i][j].state = Point.STATE_PRESS;
                }

                break;
            case MotionEvent.ACTION_MOVE:
                ij = getSelectedPoint();
                if (ij != null) {
                    i = ij[0];
                    j = ij[1];
                    if (!pList.contains(points[i][j])) {
                        pList.add(points[i][j]);
                        passList.add(3 * i + j);
                        Log.e("wzb", passList.toString());
                    }
                    points[i][j].state = Point.STATE_PRESS;
                }
                break;
            case MotionEvent.ACTION_UP:
                boolean valid = false;
                if (listener != null && isdraw) {
                    valid = listener.OnDrawFinish(passList);
                }
                if (!valid) {
                    for (Point point : pList) {
                        point.state = Point.STATE_ERROR;
                    }
                }
                isdraw = false;
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
        pList.clear();
        passList.clear();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                points[i][j].state = Point.STATE_NORMAL;
            }
        }
        this.postInvalidate();
    }

    BitmapFactory.Options getOpt() {
        BitmapFactory.Options opt = new BitmapFactory.Options();

        bitmap_normal = BitmapFactory.decodeResource(getResources(), R.drawable.normal, opt);
        int newHeight = (int) ((opt.outHeight * scaledDensity / getResources().getDisplayMetrics().density));
        int newWidth = (int) ((opt.outWidth * scaledDensity / getResources().getDisplayMetrics().density));

        opt.outWidth = newWidth;
        opt.outHeight = newHeight;
        return opt;
    }

    //使用Bitmap加Matrix来缩放
    public static Bitmap resizeImage(Bitmap bitmap, int w, int h) {
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                height, matrix, true);
        return resizedBitmap;
    }

    public interface OnDrawFinishListener {
        boolean OnDrawFinish(List<Integer> passList);
    }

    public void setOnDrawFinishListener(OnDrawFinishListener listener) {
        this.listener = listener;
    }
}
