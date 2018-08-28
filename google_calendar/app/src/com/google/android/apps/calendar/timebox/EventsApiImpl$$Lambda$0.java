// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            EventsApiImpl

final class 
    implements Supplier
{

    public static final Supplier $instance = new <init>();

    public final Object get()
    {
        return EventsApiImpl.lambda$createCalendarsFutureSupplier$0$EventsApiImpl();
    }


    private ()
    {
    }
}
