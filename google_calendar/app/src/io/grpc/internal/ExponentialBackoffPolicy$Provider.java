// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            ExponentialBackoffPolicy, BackoffPolicy

public final class 
    implements 
{

    public final BackoffPolicy get()
    {
        return new ExponentialBackoffPolicy();
    }

    public ()
    {
    }
}
