// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            CallTracer, TimeProvider

final class ctory
    implements ctory
{

    public final CallTracer create()
    {
        return new CallTracer(TimeProvider.SYSTEM_TIME_PROVIDER);
    }

    ctory()
    {
    }
}
