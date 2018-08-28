// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.state;

import android.os.Parcelable;
import java.util.Calendar;
import java.util.TimeZone;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.state:
//            AutoValue_TimeProposal

public abstract class TimeProposal
    implements Parcelable
{

    public TimeProposal()
    {
    }

    public abstract String getComment();

    public abstract long getEndTimeMillis();

    public abstract long getStartTimeMillis();

    public final TimeProposal withNewStartTime(int i, int j, TimeZone timezone)
    {
        long l = getEndTimeMillis() - getStartTimeMillis();
        long l1 = getStartTimeMillis();
        timezone = Calendar.getInstance(timezone);
        timezone.setTimeInMillis(l1);
        timezone.set(11, i);
        timezone.set(12, j);
        l1 = timezone.getTimeInMillis();
        if (l >= 0L)
        {
            l = timezone.getTimeInMillis() + l;
        } else
        {
            l = getEndTimeMillis();
        }
        return new AutoValue_TimeProposal(l1, l, getComment());
    }
}
