// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;


final class val.listener
    implements com.google.android.calendar.event.alog.OnNotificationSetListener
{

    private final com.google.android.calendar.settings.NotificationListener val$listener;

    public final void onCancel()
    {
    }

    public final void onCustomNotificationSet(int i, int j)
    {
        val$listener.onSet(i, j);
    }

    nListener()
    {
        val$listener = notificationlistener;
        super();
    }
}
