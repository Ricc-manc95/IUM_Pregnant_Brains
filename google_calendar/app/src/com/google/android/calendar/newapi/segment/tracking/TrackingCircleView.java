// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.tracking;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class TrackingCircleView extends View
{

    public float angle;
    public ValueAnimator animator;
    private Paint backgroundPaint;
    private RectF bounds;
    private int circleDiameter;
    private Paint mainPaint;
    private float strokeWidth;

    public TrackingCircleView(Context context)
    {
        super(context);
        bounds = new RectF();
        init();
    }

    public TrackingCircleView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        bounds = new RectF();
        init();
    }

    private final void init()
    {
        circleDiameter = getResources().getDimensionPixelSize(0x7f0e03cb);
        strokeWidth = getResources().getDimension(0x7f0e03cc);
        mainPaint = new Paint(1);
        mainPaint.setStyle(android.graphics.Paint.Style.STROKE);
        mainPaint.setStrokeCap(android.graphics.Paint.Cap.ROUND);
        mainPaint.setStrokeWidth(strokeWidth);
        int i = getResources().getColor(0x7f0d031a);
        backgroundPaint = new Paint(mainPaint);
        backgroundPaint.setColor(i);
        angle = 0.0F;
        invalidate();
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawArc(bounds, 0.0F, 360F, false, backgroundPaint);
        canvas.drawArc(bounds, -90F, angle, false, mainPaint);
    }

    protected void onMeasure(int i, int j)
    {
        setMeasuredDimension(circleDiameter, circleDiameter);
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        float f = strokeWidth / 2.0F;
        float f1 = Math.min(i, j);
        bounds = new RectF(f, f, f1 - f, f1 - f);
    }

    public void setColor(int i)
    {
        mainPaint.setColor(i);
        invalidate();
    }
}
