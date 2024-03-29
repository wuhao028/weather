package com.wuhao.weather.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

public class WindView extends View {

    private float width, height;
    private float centerX, centerY;
    private float x1, y1, x2, y2, x3, y3, x4, y4, x5, y5;
    private RotateAnimation mAnimation;
    private int mWindVelocity = 1;
    private Paint mWindPaint;

    public WindView(Context context) {
        this(context, null);
    }

    public WindView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WindView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mWindPaint = new Paint();
        mWindPaint.setStyle(Paint.Style.FILL);
        mWindPaint.setStrokeWidth(2);
        mWindPaint.setAntiAlias(true);
        mWindPaint.setColor(Color.WHITE);
        mAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimation.setRepeatCount(-1);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setFillAfter(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        if (height > width) {
            height = width;
        } else {
            width = height;
        }
        measure();
        setMeasuredDimension((int) width, (int) height);
    }

    private void measure() {
        x1 = width / 2;
        y1 = width / 2 - getFitSize(20);
        x2 = width / 2 + getFitSize(20);
        y2 = width / 2 - getFitSize(50);
        x3 = x2;
        y3 = width / 2 - getFitSize(60);
        x4 = width / 2;
        y4 = 0;
        x5 = width / 2 - getFitSize(10);
        y5 = y2 / 2;
        centerX = width / 2;
        centerY = width / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        drawPoint(canvas);
        drawFan(canvas);

        canvas.restore();
    }

    private void drawPoint(Canvas canvas) {
        canvas.drawCircle(centerX, centerY, getFitSize(20), mWindPaint);
    }

    private void drawFan(Canvas canvas) {
        Path path = new Path();
        path.moveTo(x1, y1);

        path.cubicTo(x2, y2, x3, y3, x4, y4);
        path.quadTo(x5, y5, x1, y1);
        canvas.drawPath(path, mWindPaint);

        canvas.rotate(120, centerX, centerY);
        canvas.drawPath(path, mWindPaint);

        canvas.rotate(120, centerX, centerY);
        canvas.drawPath(path, mWindPaint);

    }

    public void setWindVelocity(int value) {
        if (value == 0) {
            value = 1;
        }
        if (value > 17) {
            value = 17;
        }
        this.mWindVelocity = value;
    }

    public void startAnim() {
        stopAnim();
        mAnimation.setDuration(1800 - mWindVelocity * 100);
        startAnimation(mAnimation);
    }

    public void stopAnim() {
        clearAnimation();
    }

    private float getFitSize(float orgSize) {
        return orgSize * width / 496;
    }

}