// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.apps.calendar.timely.store.TimelyStore;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.syncadapters.calendar.timely.contract.TimelyEventData;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.event:
//            CpEventKey

final class arg._cls2
    implements Callable
{

    private final CpEventKey arg$1;
    private final CalendarKey arg$2;

    public final Object call()
    {
        Object obj = arg$1;
        CalendarKey calendarkey = arg$2;
        obj = TimelyStore.acquire(CalendarApi.getApiAppContext()).getTimelyEventData(((CpEventKey) (obj)).localId(), calendarkey);
        if (obj == null)
        {
            return RegularImmutableMap.EMPTY;
        }
        obj = ((TimelyEventData) (obj)).eventGadget;
        if (obj == null)
        {
            return RegularImmutableMap.EMPTY;
        } else
        {
            return ImmutableMap.copyOf(((com.google.api.services.calendar.model.a.eventGadget) (obj)).eventGadget);
        }
    }

    ta(CpEventKey cpeventkey, CalendarKey calendarkey)
    {
        arg$1 = cpeventkey;
        arg$2 = calendarkey;
    }
}
