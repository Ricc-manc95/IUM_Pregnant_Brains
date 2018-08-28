// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.calendar.api.event.EventClient;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar:
//            DeleteEventInteractiveHelper

final class ons
    implements AsyncFunction
{

    public static final AsyncFunction $instance = new <init>();

    public final ListenableFuture apply(Object obj)
    {
        obj = (ons)obj;
        return DeleteEventInteractiveHelper.eventClient.delete(((ons) (obj)).descriptor(), ((ons) (obj)).scope(), ((ons) (obj)).guestNotification());
    }


    private ons()
    {
    }
}
