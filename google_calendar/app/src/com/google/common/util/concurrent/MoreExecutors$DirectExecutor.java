// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.Executor;

public final class  extends Enum
    implements Executor
{

    private static final INSTANCE $VALUES[];
    public static final INSTANCE INSTANCE;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    public final void execute(Runnable runnable)
    {
        runnable.run();
    }

    public final String toString()
    {
        return "MoreExecutors.directExecutor()";
    }

    static 
    {
        INSTANCE = new <init>("INSTANCE", 0);
        $VALUES = (new .VALUES[] {
            INSTANCE
        });
    }

    private (String s, int i)
    {
        super(s, 0);
    }
}
