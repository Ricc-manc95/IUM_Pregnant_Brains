// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;


// Referenced classes of package com.google.common.util.concurrent:
//            AbstractFuture

static final class cause
{

    public static final <init> CAUSELESS_CANCELLED;
    public static final <init> CAUSELESS_INTERRUPTED;
    public final Throwable cause;
    public final boolean wasInterrupted;

    static 
    {
        if (AbstractFuture.GENERATE_CANCELLATION_CAUSES)
        {
            CAUSELESS_CANCELLED = null;
            CAUSELESS_INTERRUPTED = null;
        } else
        {
            CAUSELESS_CANCELLED = new <init>(false, null);
            CAUSELESS_INTERRUPTED = new <init>(true, null);
        }
    }

    (boolean flag, Throwable throwable)
    {
        wasInterrupted = flag;
        cause = throwable;
    }
}
