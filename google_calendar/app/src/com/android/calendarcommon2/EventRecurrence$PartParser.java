// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.calendarcommon2;


// Referenced classes of package com.android.calendarcommon2:
//            EventRecurrence

abstract class atException
{

    public static int parseIntRange(String s, int i, int j, boolean flag)
    {
        String s1;
        String s2;
        s1 = s;
        s2 = s;
        if (s.charAt(0) != '+')
        {
            break MISSING_BLOCK_LABEL_26;
        }
        s2 = s;
        s1 = s.substring(1);
        s2 = s1;
        int k = Integer.parseInt(s1);
        if (k >= i && k <= j && (k != 0 || flag))
        {
            break MISSING_BLOCK_LABEL_163;
        }
        s2 = s1;
        s = String.valueOf(s1);
        s2 = s1;
        if (s.length() == 0) goto _L2; else goto _L1
_L1:
        s2 = s1;
        s = "Integer value out of range: ".concat(s);
_L3:
        s2 = s1;
        try
        {
            throw new atException(s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            s = String.valueOf(s2);
        }
        if (s.length() != 0)
        {
            s = "Invalid integer value: ".concat(s);
        } else
        {
            s = new String("Invalid integer value: ");
        }
        throw new atException(s);
_L2:
        s2 = s1;
        s = new String("Integer value out of range: ");
          goto _L3
        return k;
    }

    public static int[] parseNumberList(String s, int i, int j, boolean flag)
    {
        int k = 0;
        if (s.indexOf(",") >= 0) goto _L2; else goto _L1
_L1:
        int ai[] = new int[1];
        ai[0] = parseIntRange(s, i, j, flag);
        s = ai;
_L4:
        return s;
_L2:
        String as[] = s.split(",");
        int l = as.length;
        int ai1[] = new int[l];
        do
        {
            s = ai1;
            if (k >= l)
            {
                continue;
            }
            ai1[k] = parseIntRange(as[k], i, j, flag);
            k++;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public abstract int parsePart(String s, EventRecurrence eventrecurrence);

    atException()
    {
    }
}
