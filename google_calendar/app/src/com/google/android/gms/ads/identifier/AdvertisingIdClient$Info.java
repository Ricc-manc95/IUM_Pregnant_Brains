// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.ads.identifier;


public final class zzsB
{

    public final String zzsA;
    public final boolean zzsB;

    public final String toString()
    {
        String s = zzsA;
        boolean flag = zzsB;
        return (new StringBuilder(String.valueOf(s).length() + 7)).append("{").append(s).append("}").append(flag).toString();
    }

    public (String s, boolean flag)
    {
        zzsA = s;
        zzsB = flag;
    }
}
