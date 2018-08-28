// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.common.ApiOperation;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventClientFutureImpl, CpEventDescriptor, EventApiStoreImpl, EventDescriptor

final class arg._cls4
    implements Callable
{

    private final EventClientFutureImpl arg$1;
    private final EventDescriptor arg$2;
    private final int arg$3;
    private final ion arg$4;

    public final Object call()
    {
        EventClientFutureImpl eventclientfutureimpl = arg$1;
        EventDescriptor eventdescriptor = arg$2;
        int i = arg$3;
        ion ion = arg$4;
        ((Boolean)EventApiStoreImpl.callWithMetrics(new t>(eventclientfutureimpl.api, (CpEventDescriptor)eventdescriptor, i, ion), ApiOperation.EVENT_DELETE)).booleanValue();
        return null;
    }

    ion(EventClientFutureImpl eventclientfutureimpl, EventDescriptor eventdescriptor, int i, ion ion)
    {
        arg$1 = eventclientfutureimpl;
        arg$2 = eventdescriptor;
        arg$3 = i;
        arg$4 = ion;
    }
}
