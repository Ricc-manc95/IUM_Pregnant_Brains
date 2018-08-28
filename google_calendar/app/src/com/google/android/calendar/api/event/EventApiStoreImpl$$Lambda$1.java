// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventApiStoreImpl, EventDescriptor, CpEventKey, ContentProviderRead

final class arg._cls2
    implements Callable
{

    private final EventApiStoreImpl arg$1;
    private final EventDescriptor arg$2;

    public final Object call()
    {
        EventApiStoreImpl eventapistoreimpl = arg$1;
        EventDescriptor eventdescriptor = arg$2;
        return eventapistoreimpl.read.readEvent((CpEventKey)eventdescriptor.getKey());
    }

    (EventApiStoreImpl eventapistoreimpl, EventDescriptor eventdescriptor)
    {
        arg$1 = eventapistoreimpl;
        arg$2 = eventdescriptor;
    }
}
