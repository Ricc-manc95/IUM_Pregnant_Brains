// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.cp;

import android.database.Cursor;
import com.google.android.apps.calendar.util.database.Cursors;
import com.google.android.apps.calendar.util.function.BiFunction;
import com.google.common.base.Supplier;
import java.util.TimeZone;

// Referenced classes of package com.google.android.apps.calendar.timebox.cp:
//            CursorToEntryAdapter, CpEventsApi

final class arg._cls1
    implements BiFunction
{

    private final CpEventsApi arg$1;

    public final Object apply(Object obj, Object obj1)
    {
        CpEventsApi cpeventsapi = arg$1;
        obj = (com.google.android.calendar.api.calendarlist.CalendarListEntry[])obj;
        obj1 = (Cursor)obj1;
        if (obj1 == null)
        {
            throw new NullPointerException("Cursor is null");
        } else
        {
            return Cursors.apply(((Cursor) (obj1)), new CursorToEntryAdapter((TimeZone)cpeventsapi.timeZoneSupplier.get(), ((com.google.android.calendar.api.calendarlist.CalendarListEntry []) (obj))));
        }
    }

    (CpEventsApi cpeventsapi)
    {
        arg$1 = cpeventsapi;
    }
}
