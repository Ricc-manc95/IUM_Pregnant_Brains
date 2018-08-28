// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.net;

import com.google.common.collect.ImmutableList;
import java.util.TimeZone;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.net:
//            Request

public abstract class I
{

    public abstract Request build();

    public abstract I setAttendees(ImmutableList immutablelist);

    public abstract I setCalendarId(String s);

    public abstract I setEndTimeMillis(long l);

    public abstract I setEventId(String s);

    public abstract I setStartTimeMillis(long l);

    public abstract I setTimeZone(TimeZone timezone);

    public I()
    {
    }
}
