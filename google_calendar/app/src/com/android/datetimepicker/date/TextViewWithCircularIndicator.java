// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewWithCircularIndicator extends TextView
{

    private final int circleColor;
    private Paint circlePaint;
    public boolean drawCircle;
    private final String itemIsSelectedText;

    public TextViewWithCircularIndicator(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        circlePaint = new Paint();
        attributeset = context.getResources();
        circleColor = attributeset.getColor(0x7f0d003c);
        attributeset.getDimensionPixelOffset(0x7f0e02a4);
        itemIsSelectedText = context.getResources().getString(0x7f130311);
        circlePaint.setFakeBoldText(true);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(circleColor);
        circlePaint.setTextAlign(android.graphics.Paint.Align.CENTER);
        circlePaint.setStyle(android.graphics.Paint.Style.FILL);
        circlePaint.setAlpha(60);
    }

    public CharSequence getContentDescription()
    {
        CharSequence charsequence = getText();
        Object obj = charsequence;
        if (drawCircle)
        {
            obj = String.format(itemIsSelectedText, new Object[] {
                charsequence
            });
        }
        return ((CharSequence) (obj));
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (drawCircle)
        {
            int i = getWidth();
            int j = getHeight();
            int k = Math.min(i, j) / 2;
            canvas.drawCircle(i / 2, j / 2, k, circlePaint);
        }
    }
}
