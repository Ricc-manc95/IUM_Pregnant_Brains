// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common;

import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.newapi.common:
//            AsyncTaskLoader

public final class EventLoader extends AsyncTaskLoader
{

    private EventClient eventClient;
    private EventDescriptor eventDescriptor;

    public EventLoader(EventDescriptor eventdescriptor)
    {
        eventClient = CalendarApi.Events;
        eventDescriptor = eventdescriptor;
    }

    private final transient Event runInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UOBGD4NMATJ5DPQ2UHBMCLN78EO_0()
    {
        Event event;
        try
        {
            event = (Event)eventClient.read(eventDescriptor).get();
        }
        catch (Exception exception)
        {
            String s = exception.getMessage();
            super.success = false;
            super.failureMessage = s;
            return null;
        }
        return event;
    }

    protected final volatile Object runInBackground(Object aobj[])
    {
        return runInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UOBGD4NMATJ5DPQ2UHBMCLN78EO_0();
    }
}
