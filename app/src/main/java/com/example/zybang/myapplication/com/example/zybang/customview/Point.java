package com.example.zybang.myapplication.com.example.zybang.customview;

/**
 * Created by zybang on 2016/3/2.
 */
public class Point {
    public static int STATE_NORMAL = 0;
    public static int STATE_PRESS = 1;
    public static int STATE_ERROR = 2;

    float x;
    float y;
    int state = STATE_NORMAL;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float distance(Point point) {
        float distance = (float) Math.sqrt((point.x - x) * (point.x - x) + (point.y - y) * (point.y - y));
        return distance;
    }
}
