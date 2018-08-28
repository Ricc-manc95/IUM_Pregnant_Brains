// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ScrollView;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderView;
import com.google.android.calendar.timely.gridviews.allday.ExpandableChipColumnView;

public class AllDayScrollView extends ScrollView
{

    private final Paint paint = new Paint();

    public AllDayScrollView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        context = context.getResources();
        paint.setStyle(android.graphics.Paint.Style.FILL);
        paint.setAntiAlias(false);
        paint.setColor(context.getColor(0x7f0d011a));
        paint.setStrokeWidth(context.getDimensionPixelOffset(0x7f0e01dc));
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        AllDayHeaderView alldayheaderview = (AllDayHeaderView)getChildAt(0);
        int j = getHeight();
        float f1 = (float)alldayheaderview.getMeasuredWidth() / (float)((ExpandableChipColumnView) (alldayheaderview)).columnCount;
        int k = ((ExpandableChipColumnView) (alldayheaderview)).columnCount;
        int i = 1;
        float f = f1;
        for (; i < k; i++)
        {
            canvas.drawLine(f, 0.0F, f, j, paint);
            f += f1;
        }

    }
}
