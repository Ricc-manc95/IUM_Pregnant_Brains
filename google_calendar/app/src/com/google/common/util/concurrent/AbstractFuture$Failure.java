// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;


// Referenced classes of package com.google.common.util.concurrent:
//            AbstractFuture

static final class exception
{

    public static final exception FALLBACK_INSTANCE = new <init>(new Throwable("Failure occurred while trying to finish a future.") {

        public Throwable fillInStackTrace()
        {
            this;
            JVM INSTR monitorenter ;
            return this;
        }

    });
    public final Throwable exception;


    _cls1(Throwable throwable)
    {
        if (throwable == null)
        {
            throw new NullPointerException();
        } else
        {
            exception = (Throwable)throwable;
            return;
        }
    }
}
