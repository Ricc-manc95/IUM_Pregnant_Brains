// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;

import android.content.ContentValues;
import android.text.TextUtils;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventModifications;
import com.google.calendar.v2a.android.util.time.TimeFormatUtil;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.time:
//            RecurRulePart, Recurrence, DurationUtils

public final class TimingValuesPopulator
{

    public static void addSingleEventValues(EventModifications eventmodifications, ContentValues contentvalues, boolean flag)
    {
        if (flag || eventmodifications.isStartModified() || eventmodifications.isEndModified() || eventmodifications.isAllDayModified() || eventmodifications.isTimeZoneModified() || eventmodifications.isEndTimeZoneModified())
        {
            contentvalues.put("dtstart", Long.valueOf(eventmodifications.getStartMillis()));
            contentvalues.put("dtend", Long.valueOf(eventmodifications.getEndMillis()));
            String s;
            int i;
            if (eventmodifications.isAllDayEvent())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            contentvalues.put("allDay", Integer.valueOf(i));
            s = eventmodifications.getTimeZoneId();
            if (eventmodifications.isAllDayEvent())
            {
                s = "UTC";
            }
            contentvalues.put("eventTimezone", s);
            contentvalues.put("eventEndTimezone", eventmodifications.getEndTimeZoneId());
        }
    }

    private static String formatRecurRulePartsToStoreFormat(List list)
    {
        if (list == null || list.isEmpty())
        {
            return null;
        }
        String as[] = new String[list.size()];
        for (int i = 0; i < list.size(); i++)
        {
            as[i] = ((RecurRulePart)list.get(i)).toRfc5545String();
        }

        return TextUtils.join("\n", as);
    }

    private static String formatTimestampsToStoreFormat(List list)
    {
        if (list == null || list.isEmpty())
        {
            return null;
        }
        String as[] = new String[list.size()];
        for (int i = 0; i < list.size(); i++)
        {
            as[i] = TimeFormatUtil.formatDateTimeRfc5545(((Long)list.get(i)).longValue());
        }

        return TextUtils.join(",", as);
    }

    public static ContentValues populateValues(EventModifications eventmodifications, boolean flag)
    {
        boolean flag1 = true;
        boolean flag2 = false;
        ContentValues contentvalues = new ContentValues();
        int i;
        if (eventmodifications.getOriginal() != null && eventmodifications.getOriginal().getRecurrence() != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (eventmodifications.isRecurring())
        {
            if (i == 0)
            {
                contentvalues.put("dtend", null);
                contentvalues.put("eventEndTimezone", null);
            }
            Object obj = eventmodifications.getRecurrence();
            contentvalues.put("rrule", formatRecurRulePartsToStoreFormat(((Recurrence) (obj)).rrules));
            contentvalues.put("rdate", formatTimestampsToStoreFormat(((Recurrence) (obj)).rdates));
            contentvalues.put("exrule", formatRecurRulePartsToStoreFormat(((Recurrence) (obj)).exrules));
            contentvalues.put("exdate", formatTimestampsToStoreFormat(((Recurrence) (obj)).exdates));
            contentvalues.put("dtstart", Long.valueOf(eventmodifications.getStartMillis()));
            contentvalues.put("duration", DurationUtils.toStringDuration(eventmodifications.getEndMillis() - eventmodifications.getStartMillis(), eventmodifications.isAllDayEvent()));
            if (eventmodifications.isAllDayEvent())
            {
                i = ((flag1) ? 1 : 0);
            } else
            {
                i = 0;
            }
            contentvalues.put("allDay", Integer.valueOf(i));
            obj = eventmodifications.getTimeZoneId();
            if (eventmodifications.isAllDayEvent())
            {
                obj = "UTC";
            }
            contentvalues.put("eventTimezone", ((String) (obj)));
            return contentvalues;
        }
        if (i != 0)
        {
            contentvalues.put("rrule", null);
            contentvalues.put("rdate", null);
            contentvalues.put("exrule", null);
            contentvalues.put("exdate", null);
            contentvalues.put("duration", null);
        }
        if (i != 0 || flag)
        {
            flag2 = true;
        }
        addSingleEventValues(eventmodifications, contentvalues, flag2);
        return contentvalues;
    }
}
