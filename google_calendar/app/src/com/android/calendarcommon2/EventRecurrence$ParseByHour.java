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
        s = parseNumberList(s, 0, 23, true);
        eventrecurrence.byhour = s;
        eventrecurrence.byhourCount = s.length;
        return 64;
    }

    ()
    {
    }
}