// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Context;

abstract class ContextRunnable
    implements Runnable
{

    private final Context context;

    public ContextRunnable(Context context1)
    {
        context = context1;
    }

    public final void run()
    {
        Context context1 = context.attach();
        runInContext();
        context.detach(context1);
        return;
        Exception exception;
        exception;
        context.detach(context1);
        throw exception;
    }

    public abstract void runInContext();
}
