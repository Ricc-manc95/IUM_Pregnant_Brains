// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.syncadapters.timely.type;


public final class CalendarType
{

    private static final String BLACKLISTED_VIRTUAL_CALENDAR_DOMAINS[] = {
        "countdown", "daynum", "gadget", "hebrew", "jewish", "moonphase", "primes", "stardate", "sunrise", "ticker", 
        "weather", "weeknum"
    };

    public static int calculateType(String s)
    {
        boolean flag = true;
        if (s != null) goto _L2; else goto _L1
_L1:
        return 5;
_L2:
        if (s.endsWith("resource.calendar.google.com"))
        {
            return 0;
        }
        if (s.endsWith("#contacts@group.v.calendar.google.com"))
        {
            return 1;
        }
        if (s.endsWith("#holiday@group.v.calendar.google.com") || s.endsWith("@holiday.calendar.google.com"))
        {
            return 2;
        }
        if (!s.equals("ht3jlfaac5lfd6263ulfh4tql8@group.calendar.google.com")) goto _L4; else goto _L3
_L3:
        int i = ((flag) ? 1 : 0);
_L6:
        if (i != 0)
        {
            return 3;
        }
        break; /* Loop/switch isn't completed */
_L4:
        if (s.endsWith("@group.v.calendar.google.com"))
        {
            i = s.lastIndexOf("#");
            if (i >= 0)
            {
                int j = s.indexOf("@", i);
                if (j >= 0)
                {
                    String s1 = s.substring(i + 1, j);
                    String as[] = BLACKLISTED_VIRTUAL_CALENDAR_DOMAINS;
                    int l = as.length;
                    for (int k = 0; k < l; k++)
                    {
                        i = ((flag) ? 1 : 0);
                        if (as[k].equals(s1))
                        {
                            continue; /* Loop/switch isn't completed */
                        }
                    }

                }
            }
        }
        i = 0;
        if (true) goto _L6; else goto _L5
_L5:
        if (s.endsWith("group.calendar.google.com"))
        {
            return 4;
        }
        if (!s.endsWith(".calendar.google.com"))
        {
            return 6;
        }
        if (true) goto _L1; else goto _L7
_L7:
    }

    public static String getBirthdayCalendarId(boolean flag)
    {
        if (flag)
        {
            return "#contacts@group.v.calendar.google.com";
        } else
        {
            return "addressbook#contacts@group.v.calendar.google.com";
        }
    }

    public static boolean isBirthdayCalendar(String s)
    {
        return "#contacts@group.v.calendar.google.com".equals(s) || "addressbook#contacts@group.v.calendar.google.com".equals(s);
    }

    public static boolean isBirthdayCalendar(String s, boolean flag)
    {
        String s1;
        if (flag)
        {
            s1 = "#contacts@group.v.calendar.google.com";
        } else
        {
            s1 = "addressbook#contacts@group.v.calendar.google.com";
        }
        return s1.equals(s);
    }

    public static boolean isHolidayCalendar(String s)
    {
        while (s == null || 2 != calculateType(s)) 
        {
            return false;
        }
        return true;
    }

}
