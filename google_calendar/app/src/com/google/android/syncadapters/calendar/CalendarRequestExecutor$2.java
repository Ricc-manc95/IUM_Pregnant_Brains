// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class 
    implements ThreadFactory
{

    private final AtomicInteger count = new AtomicInteger(1);

    public final Thread newThread(Runnable runnable)
    {
        int i = count.getAndIncrement();
        return new Thread(runnable, (new StringBuilder(27)).append("CalendarRequest-").append(i).toString());
    }

    ()
    {
    }
}
