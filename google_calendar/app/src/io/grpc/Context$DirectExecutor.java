// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.concurrent.Executor;

// Referenced classes of package io.grpc:
//            Context

static final class  extends Enum
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
        return "Context.DirectExecutor";
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
