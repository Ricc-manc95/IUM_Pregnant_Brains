// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.calendarcommon2;

import android.text.TextUtils;
import android.text.format.Time;
import android.util.TimeFormatException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.android.calendarcommon2:
//            EventRecurrence

public final class RecurrenceSet
{

    private static final Pattern FOLD_RE = Pattern.compile(".{75}");
    private static final Pattern IGNORABLE_ICAL_WHITESPACE_RE = Pattern.compile("(?:\\r\\n?|\\n)[ \t]");
    public long exdates[];
    public EventRecurrence exrules[];
    public long rdates[];
    public EventRecurrence rrules[];

    public RecurrenceSet(String s, String s1, String s2, String s3)
        throws EventRecurrence.InvalidFormatException
    {
        boolean flag = false;
        super();
        rrules = null;
        rdates = null;
        exrules = null;
        exdates = null;
        if (!TextUtils.isEmpty(s) || !TextUtils.isEmpty(s1))
        {
            if (!TextUtils.isEmpty(s))
            {
                s = s.split("\n");
                rrules = new EventRecurrence[s.length];
                for (int i = 0; i < s.length; i++)
                {
                    EventRecurrence eventrecurrence = new EventRecurrence();
                    eventrecurrence.parse(s[i]);
                    rrules[i] = eventrecurrence;
                }

            }
            if (!TextUtils.isEmpty(s1))
            {
                rdates = parseRecurrenceDates(s1);
            }
            if (!TextUtils.isEmpty(s2))
            {
                s = s2.split("\n");
                exrules = new EventRecurrence[s.length];
                for (int j = 0; j < s.length; j++)
                {
                    s1 = new EventRecurrence();
                    s1.parse(s2);
                    exrules[j] = s1;
                }

            }
            if (!TextUtils.isEmpty(s3))
            {
                s = new ArrayList();
                s1 = s3.split("\n");
                int k1 = s1.length;
                for (int k = 0; k < k1; k++)
                {
                    s2 = parseRecurrenceDates(s1[k]);
                    int l1 = s2.length;
                    for (int i1 = 0; i1 < l1; i1++)
                    {
                        s.add(Long.valueOf(s2[i1]));
                    }

                }

                exdates = new long[s.size()];
                int j1 = s.size();
                for (int l = ((flag) ? 1 : 0); l < j1; l++)
                {
                    exdates[l] = ((Long)s.get(l)).longValue();
                }

            }
        }
    }

    public static void addPropertiesForRuleStr(ICalendar.Component component, String s, String s1)
    {
        boolean flag = false;
        if (!TextUtils.isEmpty(s1)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (s1 != null)
        {
            break; /* Loop/switch isn't completed */
        }
        s1 = new String[0];
_L5:
        int k = s1.length;
        int i = ((flag) ? 1 : 0);
        while (i < k) 
        {
            String s2 = s1[i];
            ICalendar.Property property = new ICalendar.Property(s);
            property.value = s2;
            component.addProperty(property);
            i++;
        }
        if (true) goto _L1; else goto _L3
_L3:
        String as[];
        int j;
        int l;
        as = IGNORABLE_ICAL_WHITESPACE_RE.matcher(s1).replaceAll("").split("\n");
        l = as.length;
        j = 0;
_L6:
        s1 = as;
        if (j >= l) goto _L5; else goto _L4
_L4:
        s1 = as[j];
        as[j] = FOLD_RE.matcher(s1).replaceAll("$0\r\n ");
        j++;
          goto _L6
    }

    public static void addPropertyForDateStr(ICalendar.Component component, String s, String s1)
    {
        if (TextUtils.isEmpty(s1))
        {
            return;
        }
        ICalendar.Property property = new ICalendar.Property(s);
        String s2 = null;
        int i = s1.indexOf(";");
        s = s1;
        if (i != -1)
        {
            s2 = s1.substring(0, i);
            s = s1.substring(i + 1);
        }
        if (!TextUtils.isEmpty(s2))
        {
            property.addParameter(new ICalendar.Parameter("TZID", s2));
        }
        property.value = s;
        component.addProperty(property);
    }

    public static long[] parseRecurrenceDates(String s)
        throws EventRecurrence.InvalidFormatException
    {
        String s2 = "UTC";
        int i = s.indexOf(";");
        String s1 = s;
        if (i != -1)
        {
            s2 = s.substring(0, i);
            s1 = s.substring(i + 1);
        }
        Time time = new Time(s2);
        s = s1.split(",");
        int j = s.length;
        long al[] = new long[j];
        i = 0;
        do
        {
            if (i >= j)
            {
                break;
            }
            try
            {
                time.parse(s[i]);
            }
            catch (TimeFormatException timeformatexception)
            {
                s = s[i];
                throw new EventRecurrence.InvalidFormatException((new StringBuilder(String.valueOf(s).length() + 60 + String.valueOf(s1).length())).append("TimeFormatException thrown when parsing time ").append(s).append(" in recurrence ").append(s1).toString());
            }
            al[i] = time.toMillis(false);
            time.timezone = s2;
            i++;
        } while (true);
        return al;
    }

}
