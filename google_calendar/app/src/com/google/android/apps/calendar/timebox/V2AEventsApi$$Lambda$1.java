// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.apps.calendar.util.concurrent.CalendarFutures;
import com.google.common.collect.CollectPreconditions;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            V2AEventsApi

final class arg._cls1
    implements AsyncFunction
{

    private final V2AEventsApi arg$1;

    public final ListenableFuture apply(Object obj)
    {
        V2AEventsApi v2aeventsapi = arg$1;
        obj = (com.google.android.calendar.api.calendarlist.CalendarListEntry[])obj;
        HashMap hashmap = new HashMap();
        if (obj == null)
        {
            throw new NullPointerException();
        }
        int i = obj.length;
        CollectPreconditions.checkNonnegative(i, "arraySize");
        long l = 5L + (long)i + (long)(i / 10);
        ArrayList arraylist;
        if (l > 0x7fffffffL)
        {
            i = 0x7fffffff;
        } else
        if (l < 0xffffffff80000000L)
        {
            i = 0x80000000;
        } else
        {
            i = (int)l;
        }
        arraylist = new ArrayList(i);
        Collections.addAll(arraylist, ((Object []) (obj)));
        return CalendarFutures.mapFold(arraylist, new <init>(v2aeventsapi, hashmap), new HashMap(), .instance, com.google.common.util.concurrent.cutor.INSTANCE);
    }

    (V2AEventsApi v2aeventsapi)
    {
        arg$1 = v2aeventsapi;
    }
}
