// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.logging;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.event.time.RecurRulePart;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.screen.reminder.ReminderEditScreenModel;
import com.google.android.calendar.newapi.segment.recurrence.ReminderRecurrenceConverter;
import com.google.android.calendar.task.TaskUtils;
import com.google.android.gms.reminders.model.DateTime;
import com.google.android.gms.reminders.model.RecurrenceInfo;
import com.google.android.gms.reminders.model.Task;
import java.util.List;

public class ReminderEditLogMetrics
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/logging/ReminderEditLogMetrics);
    public long loadedTime;

    public ReminderEditLogMetrics()
    {
        loadedTime = -1L;
    }

    ReminderEditLogMetrics(Parcel parcel)
    {
        loadedTime = -1L;
        loadedTime = parcel.readLong();
    }

    public static String getAction(ReminderEditScreenModel remindereditscreenmodel)
    {
        if (remindereditscreenmodel.isNew())
        {
            return "create";
        } else
        {
            return "update";
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public final void logSaveCustomDimensions(Context context, ReminderEditScreenModel remindereditscreenmodel)
    {
        AnalyticsLogger analyticslogger1;
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        analyticslogger1 = (AnalyticsLogger)analyticslogger;
        if (!remindereditscreenmodel.isNew()) goto _L2; else goto _L1
_L1:
        Object obj;
        obj = remindereditscreenmodel.task.getDueDate();
        boolean flag;
        if (((DateTime) (obj)).getAllDay().booleanValue() || ((DateTime) (obj)).getTime() == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L4; else goto _L3
_L3:
        obj = remindereditscreenmodel.task.getDueDate();
        if (Utils.getTodayJulianDay(context) != Utils.getJulianDay(((DateTime) (obj)).getYear().intValue(), ((DateTime) (obj)).getMonth().intValue() - 1, ((DateTime) (obj)).getDay().intValue())) goto _L4; else goto _L5
_L5:
        obj = "unscheduled";
_L13:
        analyticslogger1.setCustomDimension(context, 22, ((String) (obj)));
        if (!remindereditscreenmodel.isNew())
        {
            break MISSING_BLOCK_LABEL_398;
        }
        if (remindereditscreenmodel.recurrence == null || remindereditscreenmodel.recurrence.rrules.isEmpty()) goto _L7; else goto _L6
_L6:
        ((RecurRulePart)remindereditscreenmodel.recurrence.rrules.get(0)).freq;
        JVM INSTR tableswitch 3 6: default 216
    //                   3 368
    //                   4 374
    //                   5 380
    //                   6 386;
           goto _L8 _L9 _L10 _L11 _L12
_L8:
        remindereditscreenmodel = "none";
_L14:
        analyticslogger1.setCustomDimension(context, 35, remindereditscreenmodel);
        return;
_L4:
        if (remindereditscreenmodel.isAllDay())
        {
            obj = "allDay";
        } else
        {
            obj = "timed";
        }
          goto _L13
_L2:
        obj = remindereditscreenmodel.original.getDueDate();
        DateTime datetime = remindereditscreenmodel.task.getDueDate();
        boolean flag1;
        boolean flag3;
        if (obj == null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (datetime == null)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (flag1 != flag3)
        {
            flag1 = true;
        } else
        if (obj != null && TaskUtils.dateTimeToMillis(Utils.getTimeZone(context), ((DateTime) (obj))) != TaskUtils.dateTimeToMillis(Utils.getTimeZone(context), datetime))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            obj = "changed";
        } else
        {
            obj = "unchanged";
        }
          goto _L13
_L9:
        remindereditscreenmodel = "daily";
          goto _L14
_L10:
        remindereditscreenmodel = "weekly";
          goto _L14
_L11:
        remindereditscreenmodel = "monthly";
          goto _L14
_L12:
        remindereditscreenmodel = "yearly";
          goto _L14
_L7:
        remindereditscreenmodel = "none";
          goto _L14
        RecurrenceInfo recurrenceinfo = remindereditscreenmodel.original.getRecurrenceInfo();
        remindereditscreenmodel = remindereditscreenmodel.recurrence;
        boolean flag2;
        boolean flag4;
        if (recurrenceinfo == null)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (remindereditscreenmodel == null)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        if (flag2 != flag4)
        {
            flag2 = true;
        } else
        if (recurrenceinfo != null && !ReminderRecurrenceConverter.toApiRecurrence(recurrenceinfo.getRecurrence()).equals(remindereditscreenmodel))
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (flag2)
        {
            remindereditscreenmodel = "changed";
        } else
        {
            remindereditscreenmodel = "unchanged";
        }
          goto _L14
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(loadedTime);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new ReminderEditLogMetrics(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new ReminderEditLogMetrics[i];
        }

        _cls1()
        {
        }
    }

}
