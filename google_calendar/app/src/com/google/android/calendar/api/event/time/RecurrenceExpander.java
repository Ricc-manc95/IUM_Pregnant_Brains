// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;

import android.text.format.Time;
import com.android.calendarcommon2.DateException;
import com.android.calendarcommon2.RecurrenceProcessor;
import com.android.calendarcommon2.RecurrenceSet;

// Referenced classes of package com.google.android.calendar.api.event.time:
//            RecurRulePart

final class RecurrenceExpander
{

    static long[] expandRule(RecurRulePart recurrulepart, long l, String s, long l1)
    {
        RecurrenceSet recurrenceset = new RecurrenceSet(recurrulepart.toRfc5545String(), null, null, null);
        RecurrenceProcessor recurrenceprocessor = new RecurrenceProcessor();
        if (s == null)
        {
            recurrulepart = new Time();
        } else
        {
            recurrulepart = new Time(s);
        }
        recurrulepart.set(l);
        try
        {
            recurrulepart = recurrenceprocessor.expand(recurrulepart, recurrenceset, l, l1);
        }
        // Misplaced declaration of an exception variable
        catch (RecurRulePart recurrulepart)
        {
            throw new IllegalStateException("Recurrence expansion failed", recurrulepart);
        }
        return recurrulepart;
    }
}
