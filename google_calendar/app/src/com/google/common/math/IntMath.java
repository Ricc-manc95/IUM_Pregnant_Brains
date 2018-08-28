// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.math;

import java.math.RoundingMode;

public final class IntMath
{

    public static int divide(int i, int j, RoundingMode roundingmode)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        int k;
        int l;
        int i1;
        flag = true;
        flag2 = true;
        flag1 = false;
        if (roundingmode == null)
        {
            throw new NullPointerException();
        }
        if (j == 0)
        {
            throw new ArithmeticException("/ by zero");
        }
        k = i / j;
        i1 = i - j * k;
        if (i1 == 0)
        {
            return k;
        }
        l = (i ^ j) >> 31 | 1;
        i = ((flag) ? 1 : 0);
        _cls1..SwitchMap.java.math.RoundingMode[roundingmode.ordinal()];
        JVM INSTR tableswitch 1 8: default 120
    //                   1 128
    //                   2 155
    //                   3 179
    //                   4 157
    //                   5 167
    //                   6 191
    //                   7 191
    //                   8 191;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L7 _L7
_L5:
        break; /* Loop/switch isn't completed */
_L1:
        throw new AssertionError();
_L2:
        if (i1 == 0)
        {
            i = ((flag2) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
_L3:
        i = 0;
_L8:
        if (i != 0)
        {
            return k + l;
        } else
        {
            return k;
        }
_L6:
        i = ((flag) ? 1 : 0);
        if (l <= 0)
        {
            i = 0;
        }
          goto _L8
_L4:
        i = ((flag) ? 1 : 0);
        if (l >= 0)
        {
            i = 0;
        }
          goto _L8
_L7:
        i = Math.abs(i1);
        j = i - (Math.abs(j) - i);
        if (j != 0)
        {
            break MISSING_BLOCK_LABEL_259;
        }
        if (roundingmode == RoundingMode.HALF_UP) goto _L10; else goto _L9
_L9:
        if (roundingmode == RoundingMode.HALF_EVEN)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if ((k & 1) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        i = ((flag1) ? 1 : 0);
        if ((flag & j) == 0) goto _L8; else goto _L10
_L10:
        i = 1;
          goto _L8
        i = ((flag) ? 1 : 0);
        if (j <= 0)
        {
            i = 0;
        }
          goto _L8
    }

    public static int log2(int i, RoundingMode roundingmode)
    {
        boolean flag1 = true;
        if (i <= 0)
        {
            throw new IllegalArgumentException((new StringBuilder(String.valueOf("x").length() + 26)).append("x").append(" (").append(i).append(") must be > 0").toString());
        }
        switch (_cls1..SwitchMap.java.math.RoundingMode[roundingmode.ordinal()])
        {
        default:
            throw new AssertionError();

        case 1: // '\001'
            boolean flag;
            if (i > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if ((i - 1 & i) != 0)
            {
                flag1 = false;
            }
            if (!(flag1 & flag))
            {
                throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
            }
            // fall through

        case 2: // '\002'
        case 3: // '\003'
            return 31 - Integer.numberOfLeadingZeros(i);

        case 4: // '\004'
        case 5: // '\005'
            return 32 - Integer.numberOfLeadingZeros(i - 1);

        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
            int j = Integer.numberOfLeadingZeros(i);
            return (31 - j) + (~~((0xb504f333 >>> j) - i) >>> 31);
        }
    }

    public static int saturatedAdd(int i, int j)
    {
        long l = (long)i + 1L;
        if (l > 0x7fffffffL)
        {
            return 0x7fffffff;
        }
        if (l < 0xffffffff80000000L)
        {
            return 0x80000000;
        } else
        {
            return (int)l;
        }
    }

    public static int saturatedMultiply(int i, int j)
    {
        long l = (long)i << 1;
        if (l > 0x7fffffffL)
        {
            return 0x7fffffff;
        }
        if (l < 0xffffffff80000000L)
        {
            return 0x80000000;
        } else
        {
            return (int)l;
        }
    }

    private class _cls1
    {

        public static final int $SwitchMap$java$math$RoundingMode[];

        static 
        {
            $SwitchMap$java$math$RoundingMode = new int[RoundingMode.values().length];
            try
            {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UNNECESSARY.ordinal()] = 1;
            }
            catch (NoSuchFieldError nosuchfielderror7) { }
            try
            {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 2;
            }
            catch (NoSuchFieldError nosuchfielderror6) { }
            try
            {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 3;
            }
            catch (NoSuchFieldError nosuchfielderror5) { }
            try
            {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 4;
            }
            catch (NoSuchFieldError nosuchfielderror4) { }
            try
            {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 5;
            }
            catch (NoSuchFieldError nosuchfielderror3) { }
            try
            {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 6;
            }
            catch (NoSuchFieldError nosuchfielderror2) { }
            try
            {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            }
            catch (NoSuchFieldError nosuchfielderror1) { }
            try
            {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 8;
            }
            catch (NoSuchFieldError nosuchfielderror)
            {
                return;
            }
        }
    }

}
