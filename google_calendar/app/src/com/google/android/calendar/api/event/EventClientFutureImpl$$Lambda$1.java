// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.common.ApiOperation;
import com.google.common.base.Strings;
import com.google.common.base.VerifyException;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventClientFutureImpl, EventDescriptor, EventApiStoreImpl, Event

final class arg._cls2
    implements Callable
{

    private final EventClientFutureImpl arg$1;
    private final EventDescriptor arg$2;

    public final Object call()
    {
        boolean flag = false;
        Object obj1 = arg$1;
        Object obj = arg$2;
        obj1 = ((EventClientFutureImpl) (obj1)).api;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        obj = (Event)EventApiStoreImpl.callWithMetrics(new t>(((EventApiStoreImpl) (obj1)), (EventDescriptor)obj), ApiOperation.EVENT_READ);
        if (obj != null)
        {
            flag = true;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        } else
        {
            return (Event)obj;
        }
    }

    (EventClientFutureImpl eventclientfutureimpl, EventDescriptor eventdescriptor)
    {
        arg$1 = eventclientfutureimpl;
        arg$2 = eventdescriptor;
    }
}
