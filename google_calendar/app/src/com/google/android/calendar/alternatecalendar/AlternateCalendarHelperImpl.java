// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alternatecalendar;

import android.content.Context;
import com.google.android.calendar.timely.settings.common.PreferencesConstants;

// Referenced classes of package com.google.android.calendar.alternatecalendar:
//            AlternateCalendarHelper, AlternateCalendarUtils

public final class AlternateCalendarHelperImpl
    implements AlternateCalendarHelper
{

    private final Context context;

    public AlternateCalendarHelperImpl(Context context1)
    {
        context = context1;
    }

    public final String getDayOfMonth(int i)
    {
        return AlternateCalendarUtils.getAlternateDayOfMonthString(i, context.getResources(), PreferencesConstants.getAlternateCalendarPref(context));
    }

    public final String getMonthAndDay(int i)
    {
        return AlternateCalendarUtils.getAlternateMonthDayString(i, context.getResources(), PreferencesConstants.getAlternateCalendarPref(context));
    }

    public final boolean isEnabled()
    {
        return AlternateCalendarUtils.isAlternateCalendarEnabled(context);
    }
}
