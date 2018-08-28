// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public final class AmPmCirclesView extends View
{

    private int amOrPm;
    public int amOrPmPressed;
    private int amPmCircleRadius;
    public float amPmCircleRadiusMultiplier;
    public int amPmTextColor;
    private int amPmYCenter;
    public String amText;
    private int amXCenter;
    public float circleRadiusMultiplier;
    private boolean drawValuesReady;
    public boolean isInitialized;
    public final Paint paint = new Paint();
    public String pmText;
    private int pmXCenter;
    public int selectedAlpha;
    public int selectedColor;
    public int unselectedColor;

    public AmPmCirclesView(Context context)
    {
        super(context);
        isInitialized = false;
    }

    public final int getIsTouchingAmOrPm(float f, float f1)
    {
        if (drawValuesReady)
        {
            int i = (int)((f1 - (float)amPmYCenter) * (f1 - (float)amPmYCenter));
            if ((int)Math.sqrt((f - (float)amXCenter) * (f - (float)amXCenter) + (float)i) <= amPmCircleRadius)
            {
                return 0;
            }
            f1 = pmXCenter;
            float f2 = pmXCenter;
            if ((int)Math.sqrt((float)i + (f - f1) * (f - f2)) <= amPmCircleRadius)
            {
                return 1;
            }
        }
        return -1;
    }

    public final void onDraw(Canvas canvas)
    {
        int k = 255;
        if (getWidth() == 0 || !isInitialized)
        {
            return;
        }
        if (!drawValuesReady)
        {
            int i = getWidth() / 2;
            int l = getHeight() / 2;
            int j1 = (int)((float)Math.min(i, l) * circleRadiusMultiplier);
            amPmCircleRadius = (int)((float)j1 * amPmCircleRadiusMultiplier);
            int l1 = (amPmCircleRadius * 3) / 4;
            paint.setTextSize(l1);
            amPmYCenter = (l - amPmCircleRadius / 2) + j1;
            amXCenter = (i - j1) + amPmCircleRadius;
            pmXCenter = (i + j1) - amPmCircleRadius;
            drawValuesReady = true;
        }
        int k1 = unselectedColor;
        int i1 = unselectedColor;
        int j;
        int i2;
        int j2;
        if (amOrPm == 0)
        {
            k1 = selectedColor;
            j = selectedAlpha;
        } else
        if (amOrPm == 1)
        {
            i1 = selectedColor;
            k = selectedAlpha;
            j = 255;
        } else
        {
            j = 255;
        }
        if (amOrPmPressed != 0) goto _L2; else goto _L1
_L1:
        i2 = selectedColor;
        j2 = selectedAlpha;
_L4:
        paint.setColor(i2);
        paint.setAlpha(j2);
        canvas.drawCircle(amXCenter, amPmYCenter, amPmCircleRadius, paint);
        paint.setColor(i1);
        paint.setAlpha(k);
        canvas.drawCircle(pmXCenter, amPmYCenter, amPmCircleRadius, paint);
        paint.setColor(amPmTextColor);
        j = amPmYCenter - (int)(paint.descent() + paint.ascent()) / 2;
        canvas.drawText(amText, amXCenter, j, paint);
        canvas.drawText(pmText, pmXCenter, j, paint);
        return;
_L2:
        j2 = j;
        i2 = k1;
        if (amOrPmPressed == 1)
        {
            i1 = selectedColor;
            k = selectedAlpha;
            j2 = j;
            i2 = k1;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void setAmOrPm(int i)
    {
        amOrPm = i;
    }

    public final void setAmOrPmPressed(int i)
    {
        amOrPmPressed = i;
    }
}
