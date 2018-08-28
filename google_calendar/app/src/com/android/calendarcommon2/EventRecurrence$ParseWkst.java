// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.calendarcommon2;

import java.util.HashMap;

// Referenced classes of package com.android.calendarcommon2:
//            EventRecurrence

final class matException extends 
{

    public final int parsePart(String s, EventRecurrence eventrecurrence)
    {
        Integer integer = (Integer)EventRecurrence.parseWeekdayMap.get(s);
        if (integer == null)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Invalid WKST value: ".concat(s);
            } else
            {
                s = new String("Invalid WKST value: ");
            }
            throw new matException(s);
        } else
        {
            eventrecurrence.wkst = integer.intValue();
            return 8192;
        }
    }

    matException()
    {
    }
}
