// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotifications;

import com.google.android.calendar.api.event.Event;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.usernotifications:
//            EventNotificationPlugin

final class 
    implements AsyncFunction
{

    public static final AsyncFunction $instance = new <init>();

    public final ListenableFuture apply(Object obj)
    {
        return EventNotificationPlugin.lambda$show$4$EventNotificationPlugin((Event)obj);
    }


    private ()
    {
    }
}
