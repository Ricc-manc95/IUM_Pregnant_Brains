// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


public final class NoPiiString
{

    private final String value;

    NoPiiString(String s)
    {
        value = s;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof NoPiiString)
        {
            obj = (NoPiiString)obj;
            return value.equals(((NoPiiString) (obj)).value);
        } else
        {
            return false;
        }
    }

    public final int hashCode()
    {
        return value.hashCode();
    }

    public final String toString()
    {
        return value;
    }
}
