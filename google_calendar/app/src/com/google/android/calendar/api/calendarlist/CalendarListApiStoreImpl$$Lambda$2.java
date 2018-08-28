// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.database.Cursor;
import com.google.common.base.Function;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarDescriptor, CalendarListStoreUtils

final class arg._cls3
    implements Function
{

    private final Cursor arg$1;
    private final com.google.android.apps.calendar.timely.store.y arg$2;
    private final Map arg$3;

    public final Object apply(Object obj)
    {
        Cursor cursor = arg$1;
        com.google.android.apps.calendar.timely.store.rg._cls3 _lcls3 = arg$2;
        Map map = arg$3;
        return CalendarListStoreUtils.cursorToEntity((CalendarDescriptor)obj, cursor, _lcls3, map);
    }

    (Cursor cursor, com.google.android.apps.calendar.timely.store. , Map map)
    {
        arg$1 = cursor;
        arg$2 = ;
        arg$3 = map;
    }
}
