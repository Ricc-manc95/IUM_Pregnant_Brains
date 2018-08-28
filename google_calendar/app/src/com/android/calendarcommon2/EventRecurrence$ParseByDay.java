// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.calendarcommon2;

import java.util.HashMap;

// Referenced classes of package com.android.calendarcommon2:
//            EventRecurrence

final class atException extends atException
{

    private static void parseWday(String s, int ai[], int ai1[], int i)
    {
        int j = s.length() - 2;
        if (j > 0)
        {
            ai1[i] = parseIntRange(s.substring(0, j), -53, 53, false);
            ai1 = s.substring(j);
        } else
        {
            ai1 = s;
        }
        ai1 = (Integer)EventRecurrence.parseWeekdayMap.get(ai1);
        if (ai1 == null)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Invalid BYDAY value: ".concat(s);
            } else
            {
                s = new String("Invalid BYDAY value: ");
            }
            throw new atException(s);
        } else
        {
            ai[i] = ai1.intValue();
            return;
        }
    }

    public final int parsePart(String s, EventRecurrence eventrecurrence)
    {
        int i;
        int j;
        j = 0;
        i = 1;
        if (s.indexOf(",") >= 0) goto _L2; else goto _L1
_L1:
        int ai[];
        int ai1[] = new int[1];
        ai = new int[1];
        parseWday(s, ai1, ai, 0);
        s = ai1;
_L4:
        eventrecurrence.byday = s;
        eventrecurrence.bydayNum = ai;
        eventrecurrence.bydayCount = i;
        return 128;
_L2:
        String as[] = s.split(",");
        int k = as.length;
        int ai2[] = new int[k];
        int ai3[] = new int[k];
        do
        {
            i = k;
            ai = ai3;
            s = ai2;
            if (j >= k)
            {
                continue;
            }
            parseWday(as[j], ai2, ai3, j);
            j++;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    atException()
    {
    }
}
