// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.calendarcommon2;


// Referenced classes of package com.android.calendarcommon2:
//            EventRecurrence

final class A extends A
{

    public final int parsePart(String s, EventRecurrence eventrecurrence)
    {
        s = parseNumberList(s, -31, 31, false);
        eventrecurrence.bymonthday = s;
        eventrecurrence.bymonthdayCount = s.length;
        return 256;
    }

    A()
    {
    }
}
