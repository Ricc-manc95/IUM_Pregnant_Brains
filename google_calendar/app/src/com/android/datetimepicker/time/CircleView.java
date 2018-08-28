// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public final class CircleView extends View
{

    public float amPmCircleRadiusMultiplier;
    public int circleColor;
    private int circleRadius;
    public float circleRadiusMultiplier;
    public int dotColor;
    private boolean drawValuesReady;
    public boolean is24HourMode;
    public boolean isInitialized;
    private final Paint paint = new Paint();
    private int xCenter;
    private int yCenter;

    public CircleView(Context context)
    {
        super(context);
        context = context.getResources();
        circleColor = context.getColor(0x106000b);
        dotColor = context.getColor(0x7f0d0166);
        paint.setAntiAlias(true);
        isInitialized = false;
    }

    public final void onDraw(Canvas canvas)
    {
        if (getWidth() == 0 || !isInitialized)
        {
            return;
        }
        if (!drawValuesReady)
        {
            xCenter = getWidth() / 2;
            yCenter = getHeight() / 2;
            circleRadius = (int)((float)Math.min(xCenter, yCenter) * circleRadiusMultiplier);
            if (!is24HourMode)
            {
                int i = (int)((float)circleRadius * amPmCircleRadiusMultiplier);
                yCenter = yCenter - i / 2;
            }
            drawValuesReady = true;
        }
        paint.setColor(circleColor);
        canvas.drawCircle(xCenter, yCenter, circleRadius, paint);
        paint.setColor(dotColor);
        canvas.drawCircle(xCenter, yCenter, 2.0F, paint);
    }
}
