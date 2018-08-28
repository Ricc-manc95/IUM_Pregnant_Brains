// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime;

import com.google.android.apps.calendar.timebox.EventsApiImpl;
import com.google.common.base.Function;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime:
//            ProposeNewTimeFragment

final class arg._cls1
    implements Function
{

    private final ProposeNewTimeFragment arg$1;

    public final Object apply(Object obj)
    {
        ProposeNewTimeFragment proposenewtimefragment = arg$1;
        int i = ((Integer)obj).intValue();
        return (FluentFuture)AbstractTransformFuture.create(proposenewtimefragment.eventsApi.getAsync(i, i, true), new (proposenewtimefragment), com.google.common.util.concurrent.ANCE);
    }

    (ProposeNewTimeFragment proposenewtimefragment)
    {
        arg$1 = proposenewtimefragment;
    }
}
