// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.os.Handler;
import java.util.concurrent.Executor;

final class val.handler
    implements Executor
{

    private final Handler val$handler;

    public final void execute(Runnable runnable)
    {
        val$handler.post(runnable);
    }

    ()
    {
        val$handler = handler1;
        super();
    }
}
