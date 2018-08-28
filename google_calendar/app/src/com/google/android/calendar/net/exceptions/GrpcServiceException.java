// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.net.exceptions;


public final class GrpcServiceException extends Exception
{

    public GrpcServiceException(String s)
    {
        super(s);
    }

    public GrpcServiceException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}
