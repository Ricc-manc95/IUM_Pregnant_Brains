// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.apps.calendar.util.function.BiFunction;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.calendar.v2a.shared.storage.proto.EventBundle;
import com.google.calendar.v2a.shared.storage.proto.GetEventResponse;
import com.google.common.base.Supplier;
import java.util.Iterator;
import java.util.TimeZone;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            V2AEventsApi, V2AToEntryAdapter, TimeRangeEntry

final class arg._cls1
    implements BiFunction
{

    private final V2AEventsApi arg$1;

    public final Object apply(Object obj, Object obj1)
    {
        boolean flag = true;
        V2AEventsApi v2aeventsapi = arg$1;
        CalendarListEntry calendarlistentry = (CalendarListEntry)obj;
        obj = (GetEventResponse)obj1;
        if ((((GetEventResponse) (obj)).bitField0_ & 1) != 1)
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        obj1 = (TimeZone)v2aeventsapi.timeZoneSupplier.get();
        if (((GetEventResponse) (obj)).event_ == null)
        {
            obj = EventBundle.DEFAULT_INSTANCE;
        } else
        {
            obj = ((GetEventResponse) (obj)).event_;
        }
        obj = V2AToEntryAdapter.toEntries(((TimeZone) (obj1)), ((EventBundle) (obj)), calendarlistentry).iterator();
        obj1 = ((Iterator) (obj)).next();
        if (!((Iterator) (obj)).hasNext())
        {
            return (TimeRangeEntry)obj1;
        }
        obj1 = (new StringBuilder("expected one element but was: <")).append(obj1);
        for (int i = 0; i < 4 && ((Iterator) (obj)).hasNext(); i++)
        {
            ((StringBuilder) (obj1)).append(", ").append(((Iterator) (obj)).next());
        }

        if (((Iterator) (obj)).hasNext())
        {
            ((StringBuilder) (obj1)).append(", ...");
        }
        ((StringBuilder) (obj1)).append('>');
        throw new IllegalArgumentException(((StringBuilder) (obj1)).toString());
    }

    (V2AEventsApi v2aeventsapi)
    {
        arg$1 = v2aeventsapi;
    }
}
