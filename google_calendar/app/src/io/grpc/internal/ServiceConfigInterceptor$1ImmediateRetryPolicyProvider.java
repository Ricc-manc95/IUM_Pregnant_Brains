// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            RetryPolicy

final class val.retryPolicy
    implements val.retryPolicy
{

    private final RetryPolicy val$retryPolicy;

    public final RetryPolicy get()
    {
        return val$retryPolicy;
    }

    ()
    {
        val$retryPolicy = retrypolicy;
        super();
    }
}
