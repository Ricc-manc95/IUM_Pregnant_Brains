// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.calendar.timely.settings.data.CalendarProperties;

public final class MonthBackgrounds
{

    public static final int MONTH_BACKGROUND_DRAWABLE_IDS[];
    public static final int MONTH_BANNER_DRAWABLE_IDS[];
    private static Integer hemisphereOffset;
    public static MonthBackgrounds instance;
    public final int monthBottomColorBackgrounds[];
    public final int monthTopColorBackgrounds[];

    MonthBackgrounds(Context context)
    {
        this(context.getResources());
    }

    MonthBackgrounds(Resources resources)
    {
        monthTopColorBackgrounds = new int[12];
        monthTopColorBackgrounds[0] = resources.getColor(0x7f0d0030);
        monthTopColorBackgrounds[1] = resources.getColor(0x7f0d0031);
        monthTopColorBackgrounds[2] = resources.getColor(0x7f0d0032);
        monthTopColorBackgrounds[3] = resources.getColor(0x7f0d0033);
        monthTopColorBackgrounds[4] = resources.getColor(0x7f0d0034);
        monthTopColorBackgrounds[5] = resources.getColor(0x7f0d0035);
        monthTopColorBackgrounds[6] = resources.getColor(0x7f0d0036);
        monthTopColorBackgrounds[7] = resources.getColor(0x7f0d0037);
        monthTopColorBackgrounds[8] = resources.getColor(0x7f0d0038);
        monthTopColorBackgrounds[9] = resources.getColor(0x7f0d0039);
        monthTopColorBackgrounds[10] = resources.getColor(0x7f0d003a);
        monthTopColorBackgrounds[11] = resources.getColor(0x7f0d003b);
        monthBottomColorBackgrounds = new int[12];
        monthBottomColorBackgrounds[0] = resources.getColor(0x7f0d0024);
        monthBottomColorBackgrounds[1] = resources.getColor(0x7f0d0025);
        monthBottomColorBackgrounds[2] = resources.getColor(0x7f0d0026);
        monthBottomColorBackgrounds[3] = resources.getColor(0x7f0d0027);
        monthBottomColorBackgrounds[4] = resources.getColor(0x7f0d0028);
        monthBottomColorBackgrounds[5] = resources.getColor(0x7f0d0029);
        monthBottomColorBackgrounds[6] = resources.getColor(0x7f0d002a);
        monthBottomColorBackgrounds[7] = resources.getColor(0x7f0d002b);
        monthBottomColorBackgrounds[8] = resources.getColor(0x7f0d002c);
        monthBottomColorBackgrounds[9] = resources.getColor(0x7f0d002d);
        monthBottomColorBackgrounds[10] = resources.getColor(0x7f0d002e);
        monthBottomColorBackgrounds[11] = resources.getColor(0x7f0d002f);
    }

    public static int getMonthToShow$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D4III8_0(int i)
    {
        if (hemisphereOffset == null)
        {
            CalendarProperties calendarproperties = CalendarProperties.instance;
            if (calendarproperties == null)
            {
                throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
            }
            hemisphereOffset = (Integer)((CalendarProperties)calendarproperties).getPropertyValue(1);
        }
        return (hemisphereOffset.intValue() + i) % 12;
    }

    static 
    {
        int ai[] = new int[12];
        MONTH_BACKGROUND_DRAWABLE_IDS = ai;
        ai[0] = 0x7f02005e;
        MONTH_BACKGROUND_DRAWABLE_IDS[1] = 0x7f020060;
        MONTH_BACKGROUND_DRAWABLE_IDS[2] = 0x7f020062;
        MONTH_BACKGROUND_DRAWABLE_IDS[3] = 0x7f020064;
        MONTH_BACKGROUND_DRAWABLE_IDS[4] = 0x7f020065;
        MONTH_BACKGROUND_DRAWABLE_IDS[5] = 0x7f020067;
        MONTH_BACKGROUND_DRAWABLE_IDS[6] = 0x7f020069;
        MONTH_BACKGROUND_DRAWABLE_IDS[7] = 0x7f02006b;
        MONTH_BACKGROUND_DRAWABLE_IDS[8] = 0x7f02006d;
        MONTH_BACKGROUND_DRAWABLE_IDS[9] = 0x7f02006f;
        MONTH_BACKGROUND_DRAWABLE_IDS[10] = 0x7f020071;
        MONTH_BACKGROUND_DRAWABLE_IDS[11] = 0x7f020073;
        ai = new int[12];
        MONTH_BANNER_DRAWABLE_IDS = ai;
        ai[0] = 0x7f02005d;
        MONTH_BANNER_DRAWABLE_IDS[1] = 0x7f02005f;
        MONTH_BANNER_DRAWABLE_IDS[2] = 0x7f020061;
        MONTH_BANNER_DRAWABLE_IDS[3] = 0x7f020063;
        MONTH_BANNER_DRAWABLE_IDS[4] = 0x7f020065;
        MONTH_BANNER_DRAWABLE_IDS[5] = 0x7f020066;
        MONTH_BANNER_DRAWABLE_IDS[6] = 0x7f020068;
        MONTH_BANNER_DRAWABLE_IDS[7] = 0x7f02006a;
        MONTH_BANNER_DRAWABLE_IDS[8] = 0x7f02006c;
        MONTH_BANNER_DRAWABLE_IDS[9] = 0x7f02006e;
        MONTH_BANNER_DRAWABLE_IDS[10] = 0x7f020070;
        MONTH_BANNER_DRAWABLE_IDS[11] = 0x7f020072;
    }
}
