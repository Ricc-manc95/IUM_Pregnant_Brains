// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.event.notification.Notification;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventNotificationClientBase

final class Q
    implements Predicate
{

    public static final Predicate $instance = new <init>();

    public final boolean apply(Object obj)
    {
        return EventNotificationClientBase.lambda$getDefaultReminders$3$EventNotificationClientBase((Notification)obj);
    }


    private Q()
    {
    }
}
