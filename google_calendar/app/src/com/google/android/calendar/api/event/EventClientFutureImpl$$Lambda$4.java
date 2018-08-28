// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.common.ApiOperation;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventModifications, EventClientFutureImpl, EventApiStoreImpl, Event

final class arg._cls4
    implements Callable
{

    private final EventClientFutureImpl arg$1;
    private final EventModifications arg$2;
    private final int arg$3;
    private final ion arg$4;

    public final Object call()
    {
        EventClientFutureImpl eventclientfutureimpl = arg$1;
        EventModifications eventmodifications = arg$2;
        int i = arg$3;
        ion ion = arg$4;
        if (eventmodifications.getOriginal() == null)
        {
            throw new NullPointerException();
        } else
        {
            return (Event)EventApiStoreImpl.callWithMetrics(new t>(eventclientfutureimpl.api, eventmodifications, i, ion), ApiOperation.EVENT_UPDATE);
        }
    }

    ion(EventClientFutureImpl eventclientfutureimpl, EventModifications eventmodifications, int i, ion ion)
    {
        arg$1 = eventclientfutureimpl;
        arg$2 = eventmodifications;
        arg$3 = i;
        arg$4 = ion;
    }
}
