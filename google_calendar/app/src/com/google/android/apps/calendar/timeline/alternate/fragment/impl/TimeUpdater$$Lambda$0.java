// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.time.clock.Clock;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.fragment.impl:
//            TimeUpdater

final class arg._cls1
    implements Runnable
{

    private final TimeUpdater arg$1;

    public final void run()
    {
        TimeUpdater timeupdater = arg$1;
        ObservableReference observablereference = timeupdater.currentTime;
        TimeUtils timeutils;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        observablereference.set(Long.valueOf(l));
        observablereference = timeupdater.currentJulianDay;
        timeutils = timeupdater.timeUtils;
        l = ((Long)timeupdater.currentTime.get()).longValue();
        observablereference.set(Integer.valueOf(TimeUtils.getJulianDay(l, TimeUnit.MILLISECONDS.toSeconds(((TimeZone)timeutils.timeZone.get()).getOffset(l)))));
    }

    (TimeUpdater timeupdater)
    {
        arg$1 = timeupdater;
    }
}
