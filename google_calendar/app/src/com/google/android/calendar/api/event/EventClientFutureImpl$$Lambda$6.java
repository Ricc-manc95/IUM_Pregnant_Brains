// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.common.ApiOperation;
import com.google.common.base.Strings;
import com.google.common.base.VerifyException;
import java.util.Collection;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventClientFutureImpl, EventApiStoreImpl

final class arg._cls2
    implements Callable
{

    private final EventClientFutureImpl arg$1;
    private final Collection arg$2;

    public final Object call()
    {
        boolean flag = false;
        EventClientFutureImpl eventclientfutureimpl = arg$1;
        Collection collection = arg$2;
        Event aevent[] = (Event[])EventApiStoreImpl.callWithMetrics(new t>(eventclientfutureimpl.api, collection), ApiOperation.EVENT_ICS_LIST);
        if (aevent != null)
        {
            flag = true;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        } else
        {
            return (Event[])aevent;
        }
    }

    (EventClientFutureImpl eventclientfutureimpl, Collection collection)
    {
        arg$1 = eventclientfutureimpl;
        arg$2 = collection;
    }
}
