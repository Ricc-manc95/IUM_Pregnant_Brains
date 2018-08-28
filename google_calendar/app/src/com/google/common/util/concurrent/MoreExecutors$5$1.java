// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;


final class val.command
    implements Runnable
{

    private final val.command this$0;
    private final Runnable val$command;

    public final void run()
    {
        rownFromDelegate = false;
        val$command.run();
    }

    _cls9()
    {
        this$0 = final__pcls9;
        val$command = Runnable.this;
        super();
    }
}
