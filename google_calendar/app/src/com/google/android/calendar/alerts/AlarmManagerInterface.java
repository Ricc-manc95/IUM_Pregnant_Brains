// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.app.PendingIntent;

public interface AlarmManagerInterface
{

    public abstract void cancel(PendingIntent pendingintent);

    public abstract void setExact(int i, long l, PendingIntent pendingintent);

    public abstract void setExactAndAllowWhileIdle(int i, long l, PendingIntent pendingintent);
}
