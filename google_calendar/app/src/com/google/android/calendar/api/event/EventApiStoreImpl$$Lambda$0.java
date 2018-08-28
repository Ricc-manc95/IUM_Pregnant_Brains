// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.event:
//            ContentProviderInsert, EventApiStoreImpl, EventDescriptor, CpEventKey, 
//            ContentProviderRead, EventModifications

final class arg._cls3
    implements Callable
{

    private final EventApiStoreImpl arg$1;
    private final EventModifications arg$2;
    private final ication arg$3;

    public final Object call()
    {
        EventApiStoreImpl eventapistoreimpl = arg$1;
        EventDescriptor eventdescriptor = ContentProviderInsert.insertEvent(arg$2, arg$3);
        if (eventdescriptor == null)
        {
            return null;
        } else
        {
            EventApiStoreImpl.notifyWidgetAndForceUpsync();
            return eventapistoreimpl.read.readEvent((CpEventKey)eventdescriptor.getKey());
        }
    }

    ication(EventApiStoreImpl eventapistoreimpl, EventModifications eventmodifications, ication ication)
    {
        arg$1 = eventapistoreimpl;
        arg$2 = eventmodifications;
        arg$3 = ication;
    }
}
