// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.timely:
//            EventRangedQueryHandler, AutoValue_EventRangedQueryHandler_QueryConfig, RangedData

final class arg._cls1
    implements Consumer
{

    private final EventRangedQueryHandler arg$1;

    public final void accept(Object obj)
    {
        EventRangedQueryHandler eventrangedqueryhandler;
        CalendarListEntry acalendarlistentry[];
        int i;
        i = 0;
        eventrangedqueryhandler = arg$1;
        acalendarlistentry = (CalendarListEntry[])obj;
        obj = eventrangedqueryhandler.queriesQueue;
        obj;
        JVM INSTR monitorenter ;
        g g;
        HashSet hashset;
        int j;
        g = eventrangedqueryhandler.queryConfig;
        hashset = new HashSet();
        j = acalendarlistentry.length;
_L2:
        CalendarListEntry calendarlistentry;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_93;
        }
        calendarlistentry = acalendarlistentry[i];
        if (calendarlistentry.isVisible() && calendarlistentry.isSyncEnabled())
        {
            hashset.add(calendarlistentry.getDescriptor().calendarKey);
        }
        break MISSING_BLOCK_LABEL_180;
        AutoValue_EventRangedQueryHandler_QueryConfig autovalue_eventrangedqueryhandler_queryconfig;
        autovalue_eventrangedqueryhandler_queryconfig = new AutoValue_EventRangedQueryHandler_QueryConfig(g.hideDeclined(), hashset);
        if (!autovalue_eventrangedqueryhandler_queryconfig.equals(eventrangedqueryhandler.queryConfig))
        {
            break MISSING_BLOCK_LABEL_122;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        eventrangedqueryhandler.queryConfig = autovalue_eventrangedqueryhandler_queryconfig;
        LogUtils.d("MonthQueryHandler", "QueryConfig %s", new Object[] {
            eventrangedqueryhandler.queryConfig
        });
        if (eventrangedqueryhandler.doingQuery)
        {
            RangedData rangeddata = eventrangedqueryhandler.rangeQuery.data;
            eventrangedqueryhandler.refreshData(rangeddata, rangeddata.getStartDay());
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        i++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    (EventRangedQueryHandler eventrangedqueryhandler)
    {
        arg$1 = eventrangedqueryhandler;
    }
}
