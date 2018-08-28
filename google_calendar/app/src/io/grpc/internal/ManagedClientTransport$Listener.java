// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Status;

public interface 
{

    public abstract void transportInUse(boolean flag);

    public abstract void transportReady();

    public abstract void transportShutdown(Status status);

    public abstract void transportTerminated();
}
