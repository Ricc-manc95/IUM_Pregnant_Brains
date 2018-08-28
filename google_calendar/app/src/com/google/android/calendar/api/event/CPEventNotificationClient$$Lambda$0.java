// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.event:
//            CPEventNotificationClient

final class arg._cls4
    implements Callable
{

    private final CPEventNotificationClient arg$1;
    private final Iterable arg$2;
    private final long arg$3;
    private final long arg$4;

    public final Object call()
    {
        return arg$1.getEventNotificationsAndHabitInstances(arg$2, arg$3, arg$4);
    }

    (CPEventNotificationClient cpeventnotificationclient, Iterable iterable, long l, long l1)
    {
        arg$1 = cpeventnotificationclient;
        arg$2 = iterable;
        arg$3 = l;
        arg$4 = l1;
    }
}
