// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import com.google.android.apps.calendar.util.database.Cursors;
import com.google.android.calendar.api.CalendarApi;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.event:
//            CpEventKey, EventDescriptor, EventApiStoreImpl

final class arg._cls2
    implements Callable
{

    private final EventApiStoreImpl arg$1;
    private final CpEventKey arg$2;

    public final Object call()
    {
        Object obj = arg$1;
        obj = arg$2;
        android.database.Cursor cursor = CalendarApi.getApiAppContext().getContentResolver().query(ContentUris.withAppendedId(android.provider.ENT_URI, ((CpEventKey) (obj)).localId()), new String[] {
            "dtstart", "original_id", "originalInstanceTime", "rrule"
        }, null, null, null);
        return (EventDescriptor)Cursors.extractSingleEntry(cursor, new _cls2(((CpEventKey) (obj)), cursor), "EventDescriptor");
    }

    _cls2(EventApiStoreImpl eventapistoreimpl, CpEventKey cpeventkey)
    {
        arg$1 = eventapistoreimpl;
        arg$2 = cpeventkey;
    }
}
