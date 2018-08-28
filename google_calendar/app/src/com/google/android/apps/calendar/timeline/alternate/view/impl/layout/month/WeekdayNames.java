// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.month;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public final class WeekdayNames
{

    private final boolean isTablet;
    private String lastTimeZoneID;
    private String names[];
    private final SimpleDateFormat shortestWeekdayFormat = new SimpleDateFormat("EEEEE", Locale.getDefault());
    private final TimeUtils timeUtils;

    WeekdayNames(ObservableReference observablereference, TimeUtils timeutils)
    {
        timeUtils = timeutils;
        boolean flag;
        boolean flag1;
        if ((ScreenType)observablereference.get() == ScreenType.PHONE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        isTablet = flag1;
        shortestWeekdayFormat.setTimeZone((TimeZone)timeutils.timeZone.get());
    }

    public final String get(int i)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        TimeZone timezone;
        Object obj1;
        timezone = (TimeZone)timeUtils.timeZone.get();
        obj = timezone.getID();
        obj1 = lastTimeZoneID;
        if (obj == obj1) goto _L2; else goto _L1
_L1:
        if (obj == null) goto _L4; else goto _L3
_L3:
        if (!obj.equals(obj1)) goto _L4; else goto _L2
_L14:
        int j;
        if (j != 0) goto _L6; else goto _L5
_L5:
        String as[];
        boolean flag;
        obj1 = Calendar.getInstance();
        timeUtils.initCalendar(((Calendar) (obj1)));
        shortestWeekdayFormat.setTimeZone(timezone);
        flag = isTablet;
        as = new String[8];
        j = 1;
_L12:
        if (j > 7) goto _L8; else goto _L7
_L7:
        ((Calendar) (obj1)).set(7, j);
        if (!flag) goto _L10; else goto _L9
_L9:
        obj = ((Calendar) (obj1)).getDisplayName(7, 1, Locale.getDefault());
          goto _L11
_L10:
        obj = shortestWeekdayFormat.format(((Calendar) (obj1)).getTime()).toUpperCase(Locale.getDefault());
          goto _L11
_L8:
        names = as;
        lastTimeZoneID = timezone.getID();
_L6:
        obj = names[i];
        this;
        JVM INSTR monitorexit ;
        return ((String) (obj));
        obj;
        throw obj;
_L2:
        j = 1;
        continue; /* Loop/switch isn't completed */
_L11:
        as[j] = ((String) (obj));
        j++;
        if (true) goto _L12; else goto _L4
_L4:
        j = 0;
        if (true) goto _L14; else goto _L13
_L13:
    }
}
