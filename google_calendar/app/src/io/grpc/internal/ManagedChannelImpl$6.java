// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            CallTracer, TimeProvider

final class val.timeProvider
    implements val.timeProvider
{

    private final TimeProvider val$timeProvider;

    public final CallTracer create()
    {
        return new CallTracer(val$timeProvider);
    }

    ()
    {
        val$timeProvider = timeprovider;
        super();
    }
}
