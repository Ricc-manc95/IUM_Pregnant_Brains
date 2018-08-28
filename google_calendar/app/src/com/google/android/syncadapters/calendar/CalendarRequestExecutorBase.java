// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import com.google.api.services.calendar.CalendarRequest;
import java.io.IOException;
import java.util.concurrent.Future;

public interface CalendarRequestExecutorBase
{

    public abstract Object execute(String s, CalendarRequest calendarrequest)
        throws IOException;

    public abstract Object executeWithFlags(String s, CalendarRequest calendarrequest, String s1)
        throws IOException;

    public abstract Future prefetch(String s, CalendarRequest calendarrequest);
}
