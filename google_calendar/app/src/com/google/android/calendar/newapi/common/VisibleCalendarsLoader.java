// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common;

import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListClient;
import com.google.android.calendar.api.calendarlist.CalendarListFilterOptions;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.newapi.common:
//            AsyncTaskLoader

public final class VisibleCalendarsLoader extends AsyncTaskLoader
{

    public VisibleCalendarsLoader()
    {
    }

    private static transient Integer runInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACD9GNCO9FDHGMSPPF95N78PB7CLP3M___0()
    {
        Object obj = new CalendarListFilterOptions();
        obj.visible = Boolean.valueOf(true);
        try
        {
            obj = (Integer)CalendarApi.CalendarList.count(((CalendarListFilterOptions) (obj))).get();
        }
        catch (Exception exception)
        {
            return Integer.valueOf(-1);
        }
        return ((Integer) (obj));
    }

    public final Object getResult()
    {
        Integer integer = (Integer)super.getResult();
        int i;
        if (integer == null)
        {
            i = -1;
        } else
        {
            i = integer.intValue();
        }
        return Integer.valueOf(i);
    }

    protected final volatile Object runInBackground(Object aobj[])
    {
        return runInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACD9GNCO9FDHGMSPPF95N78PB7CLP3M___0();
    }
}
