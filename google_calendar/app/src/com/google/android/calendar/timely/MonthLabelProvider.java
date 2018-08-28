// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import com.google.android.calendar.AllInOneCalendarActivity;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.alternatecalendar.AlternateCalendarUtils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.settings.common.PreferencesConstants;

public final class MonthLabelProvider
{

    private final Context context;
    public final boolean isTablet;
    public final int todayYear;

    public MonthLabelProvider(Context context1, boolean flag, int i)
    {
        context = context1;
        isTablet = flag;
        todayYear = i;
    }

    public final CharSequence getAlternateCalendarMonthLabel(int i)
    {
        if (!AlternateCalendarUtils.isAlternateCalendarEnabled(context))
        {
            return null;
        } else
        {
            int j = PreferencesConstants.getAlternateCalendarPref(context);
            return AlternateCalendarUtils.getAlternateYearMonthRangeString(i, i, context.getResources(), j);
        }
    }

    public final CharSequence getMonthLabel(Time time)
    {
        boolean flag = false;
        time.writeFieldsToImpl();
        long l = time.impl.toMillis(false);
        int i = time.year;
        boolean flag1 = isTablet;
        if (i == todayYear)
        {
            flag = true;
        }
        return AllInOneCalendarActivity.computeMonthLabel(l, Long.valueOf(l), DateTimeFormatHelper.getToolbarFormatFlags(flag1, flag));
    }
}
