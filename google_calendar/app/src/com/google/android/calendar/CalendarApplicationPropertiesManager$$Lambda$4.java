// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar:
//            CalendarApplicationPropertiesManager

final class arg._cls1
    implements OnPropertyChangedListener
{

    private final CalendarApplicationPropertiesManager arg$1;

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        Object obj1 = arg$1;
        obj = (Integer)obj;
        obj1 = ((CalendarApplicationPropertiesManager) (obj1)).defaultDuration;
        long l;
        if (((Integer) (obj)).intValue() == -1)
        {
            l = TimeUnit.HOURS.toMillis(1L);
        } else
        {
            l = TimeUnit.MINUTES.toMillis(((Integer) (obj)).intValue());
        }
        obj = Long.valueOf(l);
        if (!((ObservableReference) (obj1)).get().equals(obj))
        {
            ((ObservableReference) (obj1)).set(obj);
        }
    }

    r(CalendarApplicationPropertiesManager calendarapplicationpropertiesmanager)
    {
        arg$1 = calendarapplicationpropertiesmanager;
    }
}
