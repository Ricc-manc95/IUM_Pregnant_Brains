// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.calendarcommon2;


// Referenced classes of package com.android.calendarcommon2:
//            EventRecurrence

final class  extends 
{

    public final int parsePart(String s, EventRecurrence eventrecurrence)
    {
        eventrecurrence.count = parseIntRange(s, 0x80000000, 0x7fffffff, true);
        if (eventrecurrence.count < 0)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                "Invalid Count. Forcing COUNT to 1 from ".concat(s);
            } else
            {
                new String("Invalid Count. Forcing COUNT to 1 from ");
            }
            eventrecurrence.count = 1;
        }
        return 4;
    }

    ()
    {
    }
}