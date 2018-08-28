// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;


public final class zzq
{

    public final String zzaQE;
    public final String zzaQF;

    public zzq(String s)
    {
        this(s, null);
    }

    private zzq(String s, String s1)
    {
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("log tag cannot be null"));
        }
        boolean flag;
        if (s.length() <= 23)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.format("tag \"%s\" is longer than the %d character maximum", new Object[] {
                s, Integer.valueOf(23)
            }));
        } else
        {
            zzaQE = s;
            zzaQF = null;
            return;
        }
    }

}
