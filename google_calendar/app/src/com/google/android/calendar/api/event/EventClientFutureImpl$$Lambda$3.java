// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventClientFutureImpl, CpEventKey

final class arg._cls2
    implements Callable
{

    private final EventClientFutureImpl arg$1;
    private final CpEventKey arg$2;

    public final Object call()
    {
        return arg$1.lambda$read$3$EventClientFutureImpl(arg$2);
    }

    (EventClientFutureImpl eventclientfutureimpl, CpEventKey cpeventkey)
    {
        arg$1 = eventclientfutureimpl;
        arg$2 = cpeventkey;
    }
}
