// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import com.google.android.calendar.timely.settings.data.CalendarProperties;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridHourDrawable

public class GridHourView extends View
{

    public final GridHourDrawable gridHourDrawable;
    private final OnPropertyChangedListener onPropertyChangedListener;

    public GridHourView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        onPropertyChangedListener = new _cls1();
        attributeset = context.obtainStyledAttributes(attributeset, com.google.android.calendar.R.styleable.GridHourView);
        if (attributeset == null) goto _L2; else goto _L1
_L1:
        int i = attributeset.getDimensionPixelSize(com.google.android.calendar.R.styleable.GridHourView_text_size, context.getResources().getDimensionPixelSize(0x7f0e0259));
        attributeset.recycle();
_L4:
        gridHourDrawable = new GridHourDrawable(context, i, getResources().getDimensionPixelOffset(0x7f0e01dc));
        setBackground(gridHourDrawable);
        return;
        context;
        attributeset.recycle();
        throw context;
_L2:
        i = context.getResources().getDimensionPixelSize(0x7f0e0259);
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        onPropertyChangedListener.onCalendarPropertyChanged(10, CalendarProperties.getIntProperty(10));
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            ((CalendarProperties)calendarproperties).registerListener(10, onPropertyChangedListener);
            return;
        }
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            ((CalendarProperties)calendarproperties).unregisterListener(10, onPropertyChangedListener);
            return;
        }
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, android.view.View.MeasureSpec.makeMeasureSpec(gridHourDrawable.getIntrinsicHeight(), 0x40000000));
    }

    private class _cls1
        implements OnPropertyChangedListener
    {

        private final GridHourView this$0;

        public final void onCalendarPropertyChanged(int i, Object obj)
        {
            if (i == 10)
            {
                GridHourDrawable gridhourdrawable = gridHourDrawable;
                gridhourdrawable.intervalHeight = ((Integer)obj).intValue();
                gridhourdrawable.invalidateSelf();
                requestLayout();
            }
        }

        _cls1()
        {
            this$0 = GridHourView.this;
            super();
        }
    }

}
