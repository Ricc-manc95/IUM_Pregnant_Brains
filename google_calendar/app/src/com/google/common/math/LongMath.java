// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.math;


public final class LongMath
{

    public static long checkedAdd(long l, long l1)
    {
        boolean flag1 = true;
        long l2 = l + l1;
        boolean flag;
        if ((l ^ l1) < 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if ((l ^ l2) < 0L)
        {
            flag1 = false;
        }
        if (!(flag1 | flag))
        {
            throw new ArithmeticException((new StringBuilder(String.valueOf("checkedAdd").length() + 54)).append("overflow: ").append("checkedAdd").append("(").append(l).append(", ").append(l1).append(")").toString());
        } else
        {
            return l2;
        }
    }

}
