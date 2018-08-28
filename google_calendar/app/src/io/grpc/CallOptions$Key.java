// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


public final class debugString
{

    private final String debugString;
    public final Object defaultValue = null;

    public static debugString create(String s)
    {
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("debugString"));
        } else
        {
            return new <init>(s, null);
        }
    }

    public final String toString()
    {
        return debugString;
    }

    eption(String s, Object obj)
    {
        debugString = s;
    }
}
