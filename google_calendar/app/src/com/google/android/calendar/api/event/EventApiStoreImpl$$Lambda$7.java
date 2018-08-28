// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.database.Cursor;
import android.support.v4.util.Pair;
import com.google.android.calendar.api.calendarlist.CalendarListApiStoreImpl;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventApiStoreImpl, EventStoreUtils

final class arg._cls2
    implements com.google.android.apps.calendar.util.database.
{

    private final EventApiStoreImpl arg$1;
    private final Pair arg$2;

    public final Object extract(Cursor cursor)
    {
        Object obj = arg$1;
        Pair pair = arg$2;
        obj = ((EventApiStoreImpl) (obj)).read;
        String as[] = (String[])pair.second;
        obj = EventStoreUtils.cursorToEventDescriptor(cursor, as);
        com.google.android.calendar.api.calendarlist.CalendarDescriptor calendardescriptor = EventStoreUtils.cursorToCalendarDescriptor(cursor);
        return EventStoreUtils.cursorToEntity(((CpEventDescriptor) (obj)), calendardescriptor, CalendarListApiStoreImpl.read(calendardescriptor), cursor, as);
    }

    mpl(EventApiStoreImpl eventapistoreimpl, Pair pair)
    {
        arg$1 = eventapistoreimpl;
        arg$2 = pair;
    }
}
