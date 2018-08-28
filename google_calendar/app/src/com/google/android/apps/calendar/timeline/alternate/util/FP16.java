// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.util;


public final class FP16
{

    public static long fp16Ceil(long l)
    {
        long l1 = 0L;
        boolean flag;
        if ((65535L & l) != 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            l1 = 0x10000L;
        }
        return l1 + (0xffffffffffff0000L & l);
    }
}
