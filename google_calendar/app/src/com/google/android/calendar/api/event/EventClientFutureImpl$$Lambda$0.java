// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.common.ApiOperation;
import com.google.common.base.Strings;
import com.google.common.base.VerifyException;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventClientFutureImpl, EventModifications, EventDescriptor, EventApiStoreImpl, 
//            Event

final class arg._cls3
    implements Callable
{

    private final EventClientFutureImpl arg$1;
    private final EventModifications arg$2;
    private final ion arg$3;

    public final Object call()
    {
        boolean flag1 = true;
        Object obj1 = arg$1;
        Object obj = arg$2;
        ion ion = arg$3;
        obj1 = ((EventClientFutureImpl) (obj1)).api;
        if (!((EventModifications) (obj)).isNewEvent())
        {
            throw new IllegalArgumentException();
        }
        boolean flag;
        if (!(((EventModifications) (obj)).getDescriptor().getKey() instanceof ))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        obj = (Event)EventApiStoreImpl.callWithMetrics(new t>(((EventApiStoreImpl) (obj1)), ((EventModifications) (obj)), ion), ApiOperation.EVENT_CREATE);
        if (obj != null)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        } else
        {
            return (Event)obj;
        }
    }

    ion(EventClientFutureImpl eventclientfutureimpl, EventModifications eventmodifications, ion ion)
    {
        arg$1 = eventclientfutureimpl;
        arg$2 = eventmodifications;
        arg$3 = ion;
    }
}
