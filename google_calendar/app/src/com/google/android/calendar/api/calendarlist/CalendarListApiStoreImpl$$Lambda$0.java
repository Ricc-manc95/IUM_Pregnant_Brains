// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.database.Cursor;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarListStoreUtils, CalendarDescriptor

final class arg._cls1
    implements com.google.android.apps.calendar.util.database.g._cls1
{

    private final CalendarDescriptor arg$1;

    public final Object extract(Cursor cursor)
    {
        return CalendarListStoreUtils.cursorToEntity(arg$1, cursor, null, null);
    }

    (CalendarDescriptor calendardescriptor)
    {
        arg$1 = calendardescriptor;
    }
}
