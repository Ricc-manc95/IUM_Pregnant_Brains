// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;


public final class ExecutionError extends Error
{

    public static final long serialVersionUID = 0L;

    protected ExecutionError()
    {
    }

    public ExecutionError(Error error)
    {
        super(error);
    }
}
