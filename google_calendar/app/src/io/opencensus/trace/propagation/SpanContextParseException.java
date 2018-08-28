// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace.propagation;


public final class SpanContextParseException extends Exception
{

    public static final long serialVersionUID = 0L;

    public SpanContextParseException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}
