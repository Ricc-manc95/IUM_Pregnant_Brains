// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.api;

import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;

// Referenced classes of package com.google.android.apps.calendar.util.api:
//            ListenableFutureCache

public class CalendarListEntryCache
{

    public static ListenableFutureCache instance;

    public CalendarListEntryCache()
    {
    }

    public static CalendarListEntry findByLocalId(CalendarKey calendarkey)
    {
        ListenableFutureCache listenablefuturecache = instance;
        if (listenablefuturecache == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        CalendarListEntry acalendarlistentry[] = (CalendarListEntry[])((ListenableFutureCache)listenablefuturecache).result;
        if (acalendarlistentry != null)
        {
            int j = acalendarlistentry.length;
            boolean flag;
            for (int i = 0; i < j; i++)
            {
                CalendarListEntry calendarlistentry = acalendarlistentry[i];
                CalendarKey calendarkey1 = calendarlistentry.getDescriptor().calendarKey;
                if (calendarkey1 == null)
                {
                    continue;
                }
                if (calendarkey1 == calendarkey || calendarkey1 != null && calendarkey1.equals(calendarkey))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    return calendarlistentry;
                }
            }

        }
        return null;
    }
}
