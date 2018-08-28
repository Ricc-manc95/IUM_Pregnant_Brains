// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

// Referenced classes of package com.android.datetimepicker.date:
//            MonthView

public final class SimpleMonthView extends MonthView
{

    public SimpleMonthView(Context context)
    {
        super(context);
    }

    public final void drawMonthDay(Canvas canvas, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1, int i2)
    {
        if (mSelectedDay == k)
        {
            canvas.drawCircle(l, i1 - MINI_DAY_NUMBER_TEXT_SIZE / 3, DAY_SELECTED_CIRCLE_SIZE, mSelectedCirclePaint);
        }
        if (isOutOfRange(new MonthAdapter.CalendarDay(i, j, k)))
        {
            mMonthNumPaint.setColor(mDisabledDayTextColor);
        } else
        if (mHasToday && mToday == k)
        {
            mMonthNumPaint.setColor(mTodayNumberColor);
        } else
        {
            mMonthNumPaint.setColor(mDayTextColor);
        }
        canvas.drawText(String.format("%d", new Object[] {
            Integer.valueOf(k)
        }), l, i1, mMonthNumPaint);
    }
}
