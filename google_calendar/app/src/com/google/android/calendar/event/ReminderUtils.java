// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.content.Context;
import android.content.res.Resources;
import android.text.format.DateFormat;
import android.util.SparseArray;
import com.google.calendar.v2.client.service.api.common.Reminder;
import com.google.calendar.v2.client.service.api.time.Duration;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

// Referenced classes of package com.google.android.calendar.event:
//            ReminderEntry

public final class ReminderUtils
{

    private Calendar calendar;
    private Context context;
    private String noReminder;
    private SparseArray reminderMethodLabels;

    public ReminderUtils(Context context1)
    {
        reminderMethodLabels = new SparseArray();
        calendar = Calendar.getInstance();
        context = context1;
        context1 = context1.getResources();
        int ai[] = context1.getIntArray(0x7f0b004c);
        String as[] = context1.getStringArray(0x7f0b004b);
        int j = Math.min(ai.length, as.length);
        for (int i = 0; i < j; i++)
        {
            reminderMethodLabels.append(ai[i], as[i]);
        }

        reminderMethodLabels.append(0, as[0]);
        noReminder = context1.getString(0x7f130354);
    }

    private static int addTimeInterval(Resources resources, int i, int j, int k, StringBuilder stringbuilder)
    {
        int l = k;
        if (k >= i)
        {
            if (stringbuilder.length() > 0)
            {
                stringbuilder.append(" ");
            }
            l = k / i;
            stringbuilder.append(String.format(resources.getQuantityString(j, l), new Object[] {
                Integer.valueOf(l)
            }));
            l = k % i;
        }
        return l;
    }

    public static String constructTimeIntervalString(Resources resources, int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        addTimeInterval(resources, 1, 0x7f120002, addTimeInterval(resources, 60, 0x7f120001, addTimeInterval(resources, 1440, 0x7f120000, addTimeInterval(resources, 10080, 0x7f120003, i, stringbuilder), stringbuilder), stringbuilder), stringbuilder);
        return stringbuilder.toString();
    }

    public static List reminderEntriesToReminders(List list)
    {
        ArrayList arraylist;
        Iterator iterator;
        arraylist = new ArrayList();
        iterator = list.iterator();
_L2:
        ReminderEntry reminderentry;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_112;
        }
        reminderentry = (ReminderEntry)iterator.next();
        switch (reminderentry.method)
        {
        default:
            list = com.google.calendar.v2.client.service.api.common.Reminder.DeliveryMethod.ALERT;
            break;

        case 2: // '\002'
            break; /* Loop/switch isn't completed */

        case 3: // '\003'
            break MISSING_BLOCK_LABEL_105;
        }
_L3:
        arraylist.add(new Reminder(list, new Duration((long)reminderentry.minutes * 60000L)));
        if (true) goto _L2; else goto _L1
_L1:
        list = com.google.calendar.v2.client.service.api.common.Reminder.DeliveryMethod.EMAIL;
          goto _L3
        list = com.google.calendar.v2.client.service.api.common.Reminder.DeliveryMethod.SMS;
          goto _L3
        return arraylist;
    }

    public final String constructLabel(int i, int j, boolean flag)
    {
        Resources resources = context.getResources();
        String s2 = (String)reminderMethodLabels.get(j);
        String s1;
        if (s2 == null)
        {
            s1 = noReminder;
        } else
        {
            String s;
            if (flag)
            {
                calendar.set(11, 0);
                calendar.set(12, 0);
                calendar.add(12, -i);
                if (DateFormat.is24HourFormat(context))
                {
                    s = "H:mm";
                } else
                if (calendar.get(12) == 0)
                {
                    s = "h a";
                } else
                {
                    s = "h:mm a";
                }
                s = (new SimpleDateFormat(s, Locale.getDefault())).format(calendar.getTime());
                if (i <= 0 && i > -1440)
                {
                    s = resources.getString(0x7f130365, new Object[] {
                        s
                    });
                } else
                if (i <= 1440)
                {
                    s = resources.getString(0x7f13047c, new Object[] {
                        s
                    });
                } else
                {
                    i += 1440;
                    if (i > 10080 && i % 10080 < 1440)
                    {
                        j = i / 10080;
                        i = 0x7f120003;
                    } else
                    {
                        j = i / 1440;
                        i = 0x7f120000;
                    }
                    s = resources.getString(0x7f13009e, new Object[] {
                        String.format(resources.getQuantityString(i, j), new Object[] {
                            Integer.valueOf(j)
                        }), s
                    });
                }
            } else
            if (i == 0)
            {
                s = resources.getString(0x7f1300c1);
            } else
            {
                if (i < 0)
                {
                    return noReminder;
                }
                s = resources.getString(0x7f130480, new Object[] {
                    constructTimeIntervalString(resources, i)
                });
            }
            s1 = s;
            if (!s2.equals(reminderMethodLabels.get(0)))
            {
                return resources.getString(0x7f1303e0, new Object[] {
                    s, s2
                });
            }
        }
        return s1;
    }
}
