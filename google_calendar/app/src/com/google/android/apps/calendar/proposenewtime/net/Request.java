// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.net;

import com.google.common.collect.ImmutableList;
import java.util.TimeZone;

public abstract class Request
{

    public Request()
    {
    }

    public abstract ImmutableList getAttendees();

    public abstract String getCalendarId();

    public abstract long getEndTimeMillis();

    public abstract String getEventId();

    public abstract long getStartTimeMillis();

    public abstract TimeZone getTimeZone();
}
