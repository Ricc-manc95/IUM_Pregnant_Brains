// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.common.ApiOperation;
import com.google.common.base.Strings;
import com.google.common.base.VerifyException;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventClientFutureImpl, EventApiStoreImpl, EventDescriptor, CpEventKey

final class arg._cls2
    implements Callable
{

    private final EventClientFutureImpl arg$1;
    private final CpEventKey arg$2;

    public final Object call()
    {
        boolean flag = false;
        Object obj = arg$1;
        CpEventKey cpeventkey = arg$2;
        obj = (EventDescriptor)EventApiStoreImpl.callWithMetrics(new t>(((EventClientFutureImpl) (obj)).api, cpeventkey), ApiOperation.EVENT_CREATE_DESCRIPTOR);
        if (obj != null)
        {
            flag = true;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        } else
        {
            return (EventDescriptor)obj;
        }
    }

    (EventClientFutureImpl eventclientfutureimpl, CpEventKey cpeventkey)
    {
        arg$1 = eventclientfutureimpl;
        arg$2 = cpeventkey;
    }
}
