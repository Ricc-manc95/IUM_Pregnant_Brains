// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.gms.internal:
//            zzabw

public final class zzabv
    implements ThreadFactory
{

    private final int mPriority;
    private final String zzaTF;
    private final AtomicInteger zzaTG;
    private final ThreadFactory zzaTH;

    public zzabv(String s)
    {
        this(s, 0);
    }

    private zzabv(String s, int i)
    {
        zzaTG = new AtomicInteger();
        zzaTH = Executors.defaultThreadFactory();
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("Name must not be null"));
        } else
        {
            zzaTF = (String)s;
            mPriority = 0;
            return;
        }
    }

    public final Thread newThread(Runnable runnable)
    {
        runnable = zzaTH.newThread(new zzabw(runnable, mPriority));
        String s = zzaTF;
        int i = zzaTG.getAndIncrement();
        runnable.setName((new StringBuilder(String.valueOf(s).length() + 13)).append(s).append("[").append(i).append("]").toString());
        return runnable;
    }
}
