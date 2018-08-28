// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.timebox.TimeBoxUtil;
import com.google.android.apps.calendar.util.function.Consumer;
import java.util.Calendar;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity

final class arg._cls1
    implements Consumer
{

    private final AllInOneCalendarActivity arg$1;

    public final void accept(Object obj)
    {
        AllInOneCalendarActivity allinonecalendaractivity = arg$1;
        obj = (Integer)obj;
        TimeZone timezone = TimeZone.getTimeZone(allinonecalendaractivity.timeZone);
        Calendar calendar = Calendar.getInstance(timezone);
        calendar.setTimeInMillis(TimeBoxUtil.julianDayToMs(timezone, ((Integer) (obj)).intValue()));
        allinonecalendaractivity.onLaunchDayDetails(calendar.get(1), calendar.get(2), calendar.get(5));
    }

    (AllInOneCalendarActivity allinonecalendaractivity)
    {
        arg$1 = allinonecalendaractivity;
    }
}
