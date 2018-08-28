// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.database.Cursor;
import android.support.v4.util.Pair;
import com.google.android.calendar.api.calendarlist.CalendarListApiStoreImpl;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventStoreUtils, ContentProviderRead

final class arg._cls2
    implements com.google.android.apps.calendar.util.database.
{

    private final ContentProviderRead arg$1;
    private final Pair arg$2;

    public final Object extract(Cursor cursor)
    {
        ContentProviderRead contentproviderread = arg$1;
        String as[] = (String[])arg$2.second;
        CpEventDescriptor cpeventdescriptor = EventStoreUtils.cursorToEventDescriptor(cursor, as);
        com.google.android.calendar.api.calendarlist.CalendarDescriptor calendardescriptor = EventStoreUtils.cursorToCalendarDescriptor(cursor);
        return EventStoreUtils.cursorToEntity(cpeventdescriptor, calendardescriptor, CalendarListApiStoreImpl.read(calendardescriptor), cursor, as);
    }

    l(ContentProviderRead contentproviderread, Pair pair)
    {
        arg$1 = contentproviderread;
        arg$2 = pair;
    }
}
