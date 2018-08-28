// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.recurrence;

import com.google.android.calendar.api.event.time.RecurRulePart;
import com.google.android.calendar.api.event.time.Recurrence;

final class RecurrenceUtils
{

    static Recurrence createRecurrence(int i, int j)
    {
        com.google.android.calendar.api.event.time.RecurRulePart.Builder builder = new com.google.android.calendar.api.event.time.RecurRulePart.Builder(i);
        builder.wkst = Integer.valueOf(j);
        return new Recurrence(new RecurRulePart[] {
            builder.build()
        });
    }
}
