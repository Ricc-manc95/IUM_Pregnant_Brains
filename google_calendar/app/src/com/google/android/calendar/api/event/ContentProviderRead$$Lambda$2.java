// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.database.Cursor;

// Referenced classes of package com.google.android.calendar.api.event:
//            CpEventKey, EventStoreUtils

final class arg._cls2
    implements com.google.android.apps.calendar.util.database._01_
{

    private final CpEventKey arg$1;
    private final Cursor arg$2;

    public final Object extract(Cursor cursor)
    {
        cursor = arg$1;
        Cursor cursor1 = arg$2;
        long l1 = cursor.localId();
        long l;
        if (cursor.hasStartMillis())
        {
            l = cursor.startMillis();
        } else
        {
            l = cursor1.getLong(0);
        }
        return EventStoreUtils.createEventDescriptor(l1, l, cursor1.getLong(1), cursor1.getLong(2), cursor1.getString(3));
    }

    I(CpEventKey cpeventkey, Cursor cursor)
    {
        arg$1 = cpeventkey;
        arg$2 = cursor;
    }
}
