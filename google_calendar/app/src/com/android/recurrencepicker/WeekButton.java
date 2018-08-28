// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.recurrencepicker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ToggleButton;

public class WeekButton extends ToggleButton
{

    public WeekButton(Context context)
    {
        super(context);
    }

    public WeekButton(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public WeekButton(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    public static void setSuggestedWidth(int i)
    {
    }

    protected void onMeasure(int i, int j)
    {
        int k;
        int l;
        super.onMeasure(i, j);
        i = getMeasuredHeight();
        j = getMeasuredWidth();
        k = j;
        l = i;
        if (i <= 0) goto _L2; else goto _L1
_L1:
        k = j;
        l = i;
        if (j <= 0) goto _L2; else goto _L3
_L3:
        if (j >= i) goto _L5; else goto _L4
_L4:
        k = j;
        l = i;
        if (android.view.View.MeasureSpec.getMode(getMeasuredHeightAndState()) != 0x40000000)
        {
            l = j;
            k = j;
        }
_L2:
        setMeasuredDimension(k, l);
        return;
_L5:
        k = j;
        l = i;
        if (i < j)
        {
            k = j;
            l = i;
            if (android.view.View.MeasureSpec.getMode(getMeasuredWidthAndState()) != 0x40000000)
            {
                k = i;
                l = i;
            }
        }
        if (true) goto _L2; else goto _L6
_L6:
    }
}
