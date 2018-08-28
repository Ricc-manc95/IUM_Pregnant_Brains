// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.calendar.v2a.shared.storage.proto.EventBundle;
import com.google.calendar.v2a.shared.storage.proto.EventInstance;
import com.google.common.base.Function;
import java.util.TimeZone;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            V2AToEntryAdapter, Calendar

final class arg._cls4
    implements Function
{

    private final TimeZone arg$1;
    private final EventBundle arg$2;
    private final Calendar arg$3;
    private final CalendarListEntry arg$4;

    public final Object apply(Object obj)
    {
        return V2AToEntryAdapter.lambda$toEntries$0$V2AToEntryAdapter(arg$1, arg$2, arg$3, arg$4, (EventInstance)obj);
    }

    (TimeZone timezone, EventBundle eventbundle, Calendar calendar, CalendarListEntry calendarlistentry)
    {
        arg$1 = timezone;
        arg$2 = eventbundle;
        arg$3 = calendar;
        arg$4 = calendarlistentry;
    }
}
