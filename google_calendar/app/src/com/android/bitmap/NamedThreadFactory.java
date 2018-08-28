// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public final class NamedThreadFactory
    implements ThreadFactory
{

    private final String baseName;
    private final AtomicInteger count = new AtomicInteger(0);
    private final ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();

    public NamedThreadFactory(String s)
    {
        baseName = s;
    }

    public final Thread newThread(Runnable runnable)
    {
        runnable = defaultThreadFactory.newThread(runnable);
        String s = baseName;
        int i = count.getAndIncrement();
        runnable.setName((new StringBuilder(String.valueOf(s).length() + 12)).append(s).append("-").append(i).toString());
        return runnable;
    }
}
