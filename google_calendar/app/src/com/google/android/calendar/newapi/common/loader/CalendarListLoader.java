// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common.loader;

import android.content.Context;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListClient;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.calendarlist.CalendarListFilterOptions;
import com.google.android.calendar.newapi.common.AsyncTaskLoader;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.timely.FindTimeUtil;
import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashMap;

public final class CalendarListLoader extends AsyncTaskLoader
{

    private final Function calendarTransformer = null;
    private CalendarListClient client;
    private final Context context;
    private final CalendarListFilterOptions filterOptions;
    private final boolean shouldLoadFatSupport;

    public CalendarListLoader(Context context1, CalendarListFilterOptions calendarlistfilteroptions, Function function, boolean flag)
    {
        client = CalendarApi.CalendarList;
        context = context1;
        filterOptions = calendarlistfilteroptions;
        shouldLoadFatSupport = flag;
    }

    private final transient CalendarList runInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2URJ5ETGN0Q9FDLNM8PBC5T1M2R35DPI62SICD5PN8EO_0()
    {
        ArrayList arraylist;
        CalendarListEntry acalendarlistentry[];
        Function function;
        int i;
        int l;
        try
        {
            acalendarlistentry = (CalendarListEntry[])client.list(filterOptions).get();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            obj = ((Exception) (obj)).getMessage();
            super.success = false;
            super.failureMessage = ((String) (obj));
            return null;
        }
        function = calendarTransformer;
        arraylist = new ArrayList();
        l = acalendarlistentry.length;
        i = 0;
        while (i < l) 
        {
            Object obj = acalendarlistentry[i];
            boolean flag;
            if (((CalendarListEntry) (obj)).isPrimary() || ((CalendarListEntry) (obj)).isVisible())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                if (function != null)
                {
                    obj = (CalendarListEntry)function.apply(obj);
                }
                arraylist.add(obj);
            }
            i++;
        }
        if (arraylist.isEmpty())
        {
            super.success = false;
            super.failureMessage = "No calendars found.";
            return null;
        }
        HashMap hashmap;
        if (shouldLoadFatSupport)
        {
            hashmap = new HashMap();
            ArrayList arraylist1 = (ArrayList)arraylist;
            int k = arraylist1.size();
            int j = 0;
            while (j < k) 
            {
                CalendarListEntry calendarlistentry = (CalendarListEntry)arraylist1.get(j);
                Object obj1 = context;
                com.google.common.collect.ImmutableList immutablelist = FindTimeUtil.FIND_TIME_SCENARIOS;
                obj1 = new com.google.android.calendar.timely.FindTimeUtil..Lambda._cls0(((Context) (obj1)), calendarlistentry);
                boolean flag1;
                if (Iterators.indexOf(immutablelist.iterator(), ((com.google.common.base.Predicate) (obj1))) != -1)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                hashmap.put(calendarlistentry.getDescriptor(), Boolean.valueOf(flag1));
                j++;
            }
        } else
        {
            hashmap = null;
        }
        return CalendarList.create(arraylist, hashmap);
    }

    protected final volatile Object runInBackground(Object aobj[])
    {
        return runInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2URJ5ETGN0Q9FDLNM8PBC5T1M2R35DPI62SICD5PN8EO_0();
    }
}
