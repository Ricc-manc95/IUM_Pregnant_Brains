// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.IllegalFieldValueException;

public final class FieldUtils
{

    public static long safeAdd(long l, long l1)
    {
        long l2 = l + l1;
        if ((l ^ l2) < 0L && (l ^ l1) >= 0L)
        {
            throw new ArithmeticException((new StringBuilder(79)).append("The calculation caused an overflow: ").append(l).append(" + ").append(l1).toString());
        } else
        {
            return l2;
        }
    }

    public static long safeMultiply(long l, int i)
    {
        long l1 = l;
        switch (i)
        {
        default:
            l1 = (long)i * l;
            if (l1 / (long)i != l)
            {
                throw new ArithmeticException((new StringBuilder(67)).append("Multiplication overflows a long: ").append(l).append(" * ").append(i).toString());
            } else
            {
                return l1;
            }

        case -1: 
            if (l == 0x8000000000000000L)
            {
                throw new ArithmeticException((new StringBuilder(67)).append("Multiplication overflows a long: ").append(l).append(" * ").append(i).toString());
            }
            l1 = -l;
            // fall through

        case 1: // '\001'
            return l1;

        case 0: // '\0'
            return 0L;
        }
    }

    public static int safeToInt(long l)
    {
        if (0xffffffff80000000L <= l && l <= 0x7fffffffL)
        {
            return (int)l;
        } else
        {
            throw new ArithmeticException((new StringBuilder(48)).append("Value cannot fit in an int: ").append(l).toString());
        }
    }

    public static void verifyValueBounds(DateTimeField datetimefield, int i, int j, int k)
    {
        if (i < j || i > k)
        {
            throw new IllegalFieldValueException(datetimefield.getType(), Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k));
        } else
        {
            return;
        }
    }
}
