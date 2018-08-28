// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.calendarcommon2;


// Referenced classes of package com.android.calendarcommon2:
//            DateException

public final class Duration
{

    private int days;
    private int hours;
    private int minutes;
    private int seconds;
    private int sign;
    private int weeks;

    public Duration()
    {
        sign = 1;
    }

    public final long getMillis()
    {
        return 1000L * (long)sign * (long)(0x93a80 * weeks + 0x15180 * days + hours * 3600 + minutes * 60 + seconds);
    }

    public final void parse(String s)
        throws DateException
    {
        int i;
        int k;
        i = 1;
        sign = 1;
        weeks = 0;
        days = 0;
        hours = 0;
        minutes = 0;
        seconds = 0;
        k = s.length();
        if (k > 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int j;
        j = s.charAt(0);
        if (j == '-')
        {
            sign = -1;
        } else
        if (j != '+')
        {
            i = 0;
        }
        if (k < i)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (s.charAt(i) != 'P')
        {
            throw new DateException((new StringBuilder(String.valueOf(s).length() + 56)).append("Duration.parse(str='").append(s).append("') expected 'P' at index=").append(i).toString());
        }
        j = i + 1;
        i = j;
        if (s.charAt(j) == 'T')
        {
            i = j + 1;
        }
        boolean flag = false;
        j = i;
        i = ((flag) ? 1 : 0);
_L3:
        char c;
        if (j >= k)
        {
            continue; /* Loop/switch isn't completed */
        }
        c = s.charAt(j);
        if (c >= '0' && c <= '9')
        {
            i = i * 10 + (c - 48);
        } else
        if (c == 'W')
        {
            weeks = i;
            i = 0;
        } else
        if (c == 'H')
        {
            hours = i;
            i = 0;
        } else
        if (c == 'M')
        {
            minutes = i;
            i = 0;
        } else
        if (c == 'S')
        {
            seconds = i;
            i = 0;
        } else
        {
            if (c != 'D')
            {
                continue; /* Loop/switch isn't completed */
            }
            days = i;
            i = 0;
        }
_L5:
        j++;
          goto _L3
        continue; /* Loop/switch isn't completed */
        if (c == 'T') goto _L5; else goto _L4
_L4:
        throw new DateException((new StringBuilder(String.valueOf(s).length() + 63)).append("Duration.parse(str='").append(s).append("') unexpected char '").append(c).append("' at index=").append(j).toString());
        if (true) goto _L1; else goto _L6
_L6:
    }
}
