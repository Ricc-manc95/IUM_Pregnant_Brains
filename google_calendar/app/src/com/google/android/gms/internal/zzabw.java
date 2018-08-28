// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Process;

final class zzabw
    implements Runnable
{

    private final int mPriority;
    private final Runnable zzv;

    public zzabw(Runnable runnable, int i)
    {
        zzv = runnable;
        mPriority = i;
    }

    public final void run()
    {
        Process.setThreadPriority(mPriority);
        zzv.run();
    }
}
