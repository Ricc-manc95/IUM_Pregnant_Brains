// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

public final class zzabu
    implements Executor
{

    private final Handler mHandler;

    public zzabu(Looper looper)
    {
        mHandler = new Handler(looper);
    }

    public final void execute(Runnable runnable)
    {
        mHandler.post(runnable);
    }
}
