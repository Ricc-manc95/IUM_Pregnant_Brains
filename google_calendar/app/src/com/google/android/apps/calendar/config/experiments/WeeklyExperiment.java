// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.experiments;

import android.content.Context;
import com.google.android.calendar.time.clock.Clock;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.apps.calendar.config.experiments:
//            Experiment

public final class WeeklyExperiment extends Experiment
{

    private static final int WEEK_IN_MILLIS;
    private final int weeks;

    WeeklyExperiment(int i, String s, int j, Boolean boolean1, int k)
    {
        super(i, s, j, boolean1);
        weeks = k;
    }

    protected final boolean isActiveInternal(Context context)
    {
        int i = getBucket(context, salt, weeks);
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        return i == (int)((l + ((long)getBucket(context, salt, WEEK_IN_MILLIS) - (long)(WEEK_IN_MILLIS / 2))) / (long)WEEK_IN_MILLIS) % weeks;
    }

    static 
    {
        WEEK_IN_MILLIS = (int)TimeUnit.MILLISECONDS.convert(7L, TimeUnit.DAYS);
    }
}
