// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


final class val.listener
    implements Runnable
{

    private final stener val$listener;

    public final void run()
    {
        val$listener.transportInUse(true);
    }

    stener()
    {
        val$listener = stener;
        super();
    }
}
