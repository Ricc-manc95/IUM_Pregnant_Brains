// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.content.Context;
import com.google.android.apps.calendar.commonsync.analytics.api.AnalyticsLogger;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            CalendarRequestExecutorBase

public final class SyncHooksContext
{

    public final AnalyticsLogger analyticsLogger;
    public final CalendarRequestExecutorBase calendarRequestExecutor;
    public final Context context;

    public SyncHooksContext(Context context1, CalendarRequestExecutorBase calendarrequestexecutorbase, AnalyticsLogger analyticslogger)
    {
        context = context1.getApplicationContext();
        calendarRequestExecutor = calendarrequestexecutorbase;
        analyticsLogger = analyticslogger;
    }
}
