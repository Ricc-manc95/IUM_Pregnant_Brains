// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;

// Referenced classes of package com.google.android.calendar:
//            CalendarApplicationPropertiesManager

final class arg._cls1
    implements OnPropertyChangedListener
{

    private final CalendarApplicationPropertiesManager arg$1;

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        ObservableReference observablereference = arg$1.defaultCalendarColor;
        obj = (Integer)obj;
        if (!observablereference.get().equals(obj))
        {
            observablereference.set(obj);
        }
    }

    r(CalendarApplicationPropertiesManager calendarapplicationpropertiesmanager)
    {
        arg$1 = calendarapplicationpropertiesmanager;
    }
}
