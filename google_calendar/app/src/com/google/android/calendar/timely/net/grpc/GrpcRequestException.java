// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.net.grpc;

import io.grpc.Status;

public class GrpcRequestException extends Exception
{

    public final Status status;

    public GrpcRequestException(Status status1, String s, Throwable throwable)
    {
        super(s, throwable);
        status = status1;
    }
}
