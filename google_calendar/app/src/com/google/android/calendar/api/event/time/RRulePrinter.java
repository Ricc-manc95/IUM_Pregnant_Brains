// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;

import android.text.TextUtils;
import java.util.List;

final class RRulePrinter
{

    static void appendByXxxField(String s, List list, StringBuilder stringbuilder)
    {
        if (!list.isEmpty())
        {
            stringbuilder.append(';').append(s).append('=').append(TextUtils.join(",", list));
        }
    }

    static String getWeekdayString(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException("Unknown recurrence rule weekday");

        case 1: // '\001'
            return "MO";

        case 2: // '\002'
            return "TU";

        case 3: // '\003'
            return "WE";

        case 4: // '\004'
            return "TH";

        case 5: // '\005'
            return "FR";

        case 6: // '\006'
            return "SA";

        case 7: // '\007'
            return "SU";
        }
    }
}
