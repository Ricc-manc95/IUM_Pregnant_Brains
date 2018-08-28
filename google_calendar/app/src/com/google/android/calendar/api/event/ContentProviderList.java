// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.database.Cursor;
import com.android.calendarcommon2.LogUtils;
import java.util.Map;

class ContentProviderList
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/api/event/ContentProviderList);

    ContentProviderList()
    {
    }

    static final String lambda$executeIcsListQuery$0$ContentProviderList(Long long1)
    {
        long1 = String.valueOf(long1);
        return (new StringBuilder(String.valueOf(long1).length() + 6)).append("_id = ").append(long1).toString();
    }

    static final Object lambda$getIcsEvents$1$ContentProviderList(Map map, Cursor cursor)
    {
        long l = cursor.getLong(0);
        cursor = cursor.getString(2);
        map = (String)map.put(Long.valueOf(l), cursor);
        if (map != null && !cursor.equals(map))
        {
            LogUtils.wtf(TAG, "mismatched ical ids: %s != %s", new Object[] {
                cursor, map
            });
        }
        return null;
    }

    static final Object lambda$getIcsEvents$2$ContentProviderList(Map map, Cursor cursor)
    {
        long l = cursor.getLong(0);
        cursor = cursor.getString(1);
        map = (String)map.put(Long.valueOf(l), cursor);
        if (map != null && !cursor.equals(map))
        {
            LogUtils.wtf(TAG, "mismatched ical ids: %s != %s", new Object[] {
                cursor, map
            });
        }
        return null;
    }

}
