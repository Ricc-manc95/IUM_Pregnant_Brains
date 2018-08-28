// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.time.Time;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity, CalendarController

final class arg._cls1
    implements Consumer
{

    private final AllInOneCalendarActivity arg$1;

    public final void accept(Object obj)
    {
        AllInOneCalendarActivity allinonecalendaractivity = arg$1;
        obj = (Event)obj;
        Time time = new Time(allinonecalendaractivity.timeZone);
        long l = ((Event) (obj)).getStartMillis();
        time.impl.timezone = time.timezone;
        time.impl.set(l);
        time.impl.toMillis(true);
        time.copyFieldsFromImpl();
        time.normalizeSafe();
        allinonecalendaractivity.controller.goTo(allinonecalendaractivity, time, 0L);
    }

    (AllInOneCalendarActivity allinonecalendaractivity)
    {
        arg$1 = allinonecalendaractivity;
    }
}
