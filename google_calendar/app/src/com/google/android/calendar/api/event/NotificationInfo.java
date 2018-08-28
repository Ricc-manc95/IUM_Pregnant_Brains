// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;


// Referenced classes of package com.google.android.calendar.api.event:
//            EventKey

public abstract class NotificationInfo
{

    public NotificationInfo()
    {
    }

    public abstract EventKey getEventKey();

    public abstract long getExpirationMillis();

    public abstract long getTriggerMillis();

    public abstract NotificationType getType();
}
