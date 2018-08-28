// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.database.Cursor;
import com.google.common.base.Optional;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarListStoreUtils

final class arg._cls2
    implements com.google.android.apps.calendar.util.database.g._cls2
{

    private final com.google.android.apps.calendar.timely.store.init> arg$1;
    private final Map arg$2;

    public final Object extract(Cursor cursor)
    {
        com.google.android.apps.calendar.timely.store.rg._cls2 _lcls2 = arg$1;
        Map map = arg$2;
        return CalendarListStoreUtils.cursorToDescriptor(cursor).transform(new <init>(cursor, _lcls2, map));
    }

    (com.google.android.apps.calendar.timely.store. , Map map)
    {
        arg$1 = ;
        arg$2 = map;
    }
}
