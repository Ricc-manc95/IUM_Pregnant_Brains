// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitModifications;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.groove:
//            CreateGrooveActivity, GrooveUtils

final class arg._cls1
    implements Callable
{

    private final CreateGrooveActivity arg$1;

    public final Object call()
    {
        CreateGrooveActivity creategrooveactivity = arg$1;
        return Integer.valueOf(GrooveUtils.getDefaultReminderMinutes(creategrooveactivity, creategrooveactivity.habitModifications.getDescriptor().calendar.account, creategrooveactivity.habitModifications.getDescriptor().calendar.calendarId));
    }

    (CreateGrooveActivity creategrooveactivity)
    {
        arg$1 = creategrooveactivity;
    }
}
