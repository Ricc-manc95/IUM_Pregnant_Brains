// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.common.http;


public final class  extends Exception
{

    public (String s)
    {
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            s = "Illegal rule: ".concat(s);
        } else
        {
            s = new String("Illegal rule: ");
        }
        super(s);
    }
}
