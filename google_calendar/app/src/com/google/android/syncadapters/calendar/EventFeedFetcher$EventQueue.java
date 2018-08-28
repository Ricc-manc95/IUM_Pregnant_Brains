// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import com.android.calendarcommon2.LogUtils;
import com.google.api.client.util.GenericData;
import com.google.api.services.calendar.model.Event;
import java.util.concurrent.LinkedBlockingQueue;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            EventFeedFetcher

static class  extends LinkedBlockingQueue
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/EventFeedFetcher$EventQueue);
    public EventFeedFetcher fetcher;

    public void put(Object obj)
        throws InterruptedException
    {
        obj = (Event)obj;
        Event event;
        if (fetcher != null)
        {
            event = (Event)((GenericData) (obj)).set("EventFeedFetcher.requestParams", fetcher.paramsOfCurrentlyProcessedRequest);
        } else
        {
            LogUtils.wtf(TAG, "Fetcher not set on EventQueue.", new Object[0]);
        }
        super.put(obj);
    }


    (int i)
    {
        super(i);
    }
}
