// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;


public abstract class NotificationManagerWrapper
{

    public NotificationManagerWrapper()
    {
    }

    public abstract void cancel(String s, int i);

    public abstract void cancelAllOc();

    public abstract void notify(String s, int i, AlertServiceHelper.EventNotificationWrapper eventnotificationwrapper);
}
