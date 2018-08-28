// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.recurrence;

import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.newapi.segment.recurrence:
//            ReminderRecurrenceConverter

final class a
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        return ReminderRecurrenceConverter.lambda$addWeekDays$0$ReminderRecurrenceConverter((Integer)obj);
    }


    private a()
    {
    }
}
