// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth.model;

import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.timely.RangedData;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.widgetmonth.model:
//            MonthViewWidgetModel

final class endDay
    implements RangedData
{

    public final int endDay;
    public volatile List events;
    public final int startDay;
    public volatile List tasks;
    public final MonthViewWidgetModel this$0;
    private int token;

    public final boolean containsDay(int i)
    {
        return startDay <= i && i <= endDay;
    }

    public final String getDebugTag()
    {
        return getClass().getSimpleName();
    }

    public final int getEndDay()
    {
        return endDay;
    }

    public final int getStartDay()
    {
        return startDay;
    }

    public final int getToken()
    {
        return token;
    }

    public final void recycle(int i)
    {
        boolean flag;
        if (startDay <= i && i <= endDay)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            LogUtils.wtf(MonthViewWidgetModel.TAG, "Julian day is outside the model's range: appWidgetId=%d, startDay=%d, endDay=%d, recycleJulianDay=%d", new Object[] {
                Integer.valueOf(appWidgetId), Integer.valueOf(startDay), Integer.valueOf(endDay), Integer.valueOf(i)
            });
        }
    }

    public final void setToken(int i)
    {
        token = i;
    }

    (int i, int j)
    {
        this$0 = MonthViewWidgetModel.this;
        super();
        events = Collections.emptyList();
        tasks = Collections.emptyList();
        startDay = i;
        endDay = j;
    }
}
