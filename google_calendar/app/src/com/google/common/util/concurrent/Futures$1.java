// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.Future;

public final class val.scheduled
    implements Runnable
{

    private final Future val$scheduled;

    public final void run()
    {
        super..cancel(false);
    }

    public ()
    {
        val$scheduled = future;
        super();
    }
}
