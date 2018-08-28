// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timeline.alternate.DefaultBundleFactory;
import java.util.Calendar;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity

final class arg._cls2
    implements com.google.android.apps.calendar.timeline.alternate.view.api.nClickListener
{

    private final AllInOneCalendarActivity arg$1;
    private final DefaultBundleFactory arg$2;

    public final void onClick(int i)
    {
        AllInOneCalendarActivity allinonecalendaractivity = arg$1;
        Object obj = arg$2;
        ((DefaultBundleFactory) (obj)).calendar.clear();
        ((DefaultBundleFactory) (obj)).calendar.setTimeZone((TimeZone)((DefaultBundleFactory) (obj)).timeUtils.timeZone.get());
        Calendar calendar = ((DefaultBundleFactory) (obj)).calendar;
        int j;
        int k;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        calendar.setTimeInMillis(l);
        j = ((DefaultBundleFactory) (obj)).calendar.get(11);
        k = ((DefaultBundleFactory) (obj)).calendar.get(12);
        ((DefaultBundleFactory) (obj)).calendar.setTimeInMillis(((DefaultBundleFactory) (obj)).timeUtils.julianDateToMs(i));
        ((DefaultBundleFactory) (obj)).calendar.set(11, j);
        ((DefaultBundleFactory) (obj)).calendar.set(12, k);
        obj = ((DefaultBundleFactory) (obj)).calendar;
        ((Calendar) (obj)).set(13, 0);
        ((Calendar) (obj)).set(14, 0);
        if (((Calendar) (obj)).get(12) >= 30)
        {
            ((Calendar) (obj)).set(12, 0);
            ((Calendar) (obj)).add(10, 1);
        } else
        {
            ((Calendar) (obj)).set(12, 30);
        }
        allinonecalendaractivity.onLaunchInsertOrEdit(DefaultBundleFactory.createDefaultBundleForTime(((Calendar) (obj)).getTimeInMillis()));
    }

    actory(AllInOneCalendarActivity allinonecalendaractivity, DefaultBundleFactory defaultbundlefactory)
    {
        arg$1 = allinonecalendaractivity;
        arg$2 = defaultbundlefactory;
    }
}
