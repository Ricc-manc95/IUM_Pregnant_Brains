// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotifications;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.usernotifications:
//            EventNotificationPlugin

final class arg._cls2
    implements AsyncFunction
{

    private final long arg$1;
    private final long arg$2;

    public final ListenableFuture apply(Object obj)
    {
        return EventNotificationPlugin.lambda$getRelevantNotifications$1$EventNotificationPlugin(arg$1, arg$2, (com.google.android.calendar.api.calendarlist.CalendarListEntry[])obj);
    }

    (long l, long l1)
    {
        arg$1 = l;
        arg$2 = l1;
    }
}
