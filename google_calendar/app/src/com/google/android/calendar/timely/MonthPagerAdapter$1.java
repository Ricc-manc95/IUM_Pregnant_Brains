// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.accessibility.AccessibilityNodeProvider;
import com.google.android.calendar.utils.AccessibilityUtils;

// Referenced classes of package com.google.android.calendar.timely:
//            MonthPagerAdapter, CalendarMonthView, MonthViewFrame

final class this._cls0
    implements Runnable
{

    private final MonthPagerAdapter this$0;

    public final void run()
    {
        if (primaryMonthView != null)
        {
            Object obj = primaryMonthView;
            if (AccessibilityUtils.isAccessibilityEnabled(((CalendarMonthView) (obj)).getContext()))
            {
                obj = ((CalendarMonthView) (obj)).monthViewFrame;
                int i;
                if (((MonthViewFrame) (obj)).mHasToday)
                {
                    i = (((MonthViewFrame) (obj)).todayJulianDay - ((MonthViewFrame) (obj)).mFirstJulianDay) + 1;
                } else
                {
                    i = 1;
                }
                ((MonthViewFrame) (obj)).getAccessibilityNodeProvider().performAction(i, 64, null);
            }
        }
    }

    ()
    {
        this$0 = MonthPagerAdapter.this;
        super();
    }
}
