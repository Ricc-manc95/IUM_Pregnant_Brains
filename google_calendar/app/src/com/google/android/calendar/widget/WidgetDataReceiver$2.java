// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.SparseArray;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.TimelineItem;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package com.google.android.calendar.widget:
//            CalendarAppWidgetModel, WidgetDataReceiver, CalendarAppWidgetService, WidgetUtils

final class val.result
    implements rocessCompleteListener
{

    private final WidgetDataReceiver this$0;
    private final AtomicBoolean val$isResultFinished;
    private final android.content.dingResult val$result;

    public final void onProcessComplete(CalendarAppWidgetModel calendarappwidgetmodel)
    {
        Object obj1;
        Object obj2;
        WidgetDataReceiver widgetdatareceiver;
        int i;
        int i1;
label0:
        {
            l.RangedEventResults rangedeventresults = calendarappwidgetmodel.getStorage();
            long l1;
            if (rangedeventresults != null)
            {
                i = rangedeventresults.allDayBuckets.size();
                i = rangedeventresults.timedBuckets.size() + i;
            } else
            {
                i = 0;
            }
            obj = new ArrayList(i + 15);
            widgetdatareceiver = WidgetDataReceiver.this;
            LogUtils.d("CalendarWidget", "... fillRows from ....", new Object[0]);
            obj1 = new Time(widgetdatareceiver.timezone);
            if (Clock.mockedTimestamp > 0L)
            {
                l1 = Clock.mockedTimestamp;
            } else
            {
                l1 = System.currentTimeMillis();
            }
            ((Time) (obj1)).set(l1);
            i1 = calendarappwidgetmodel.todayJulianDay;
            obj2 = DateTimeFormatHelper.instance;
            if (obj2 == null)
            {
                throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
            }
            obj2 = (DateTimeFormatHelper)obj2;
            if (calendarappwidgetmodel.rangedEventResults != null)
            {
                l.RangedEventResults rangedeventresults1 = calendarappwidgetmodel.rangedEventResults;
                long l4;
                if (rangedeventresults1.allDayBuckets.size() == 0 && rangedeventresults1.timedBuckets.size() == 0)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    break MISSING_BLOCK_LABEL_430;
                }
            }
            i = 1;
            break label0;
        }
        i = 0;
        continue;
        calendarappwidgetmodel;
        obj;
        JVM INSTR monitorexit ;
        throw calendarappwidgetmodel;
    }

    l()
    {
        this$0 = final_widgetdatareceiver;
        val$isResultFinished = atomicboolean;
        val$result = android.content.dingResult.this;
        super();
    }
}
