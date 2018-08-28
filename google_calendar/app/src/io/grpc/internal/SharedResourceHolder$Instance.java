// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.concurrent.ScheduledFuture;

final class payload
{

    public ScheduledFuture destroyTask;
    public final Object payload;
    public int refcount;

    (Object obj)
    {
        payload = obj;
    }
}
