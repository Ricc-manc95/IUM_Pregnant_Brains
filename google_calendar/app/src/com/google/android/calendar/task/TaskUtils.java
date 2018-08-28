// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;
import com.google.android.apps.calendar.timebox.TimeBoxUtil;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.timebox.task.TaskData;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.time.TimeZoneImpl;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.gms.reminders.model.DateTime;
import com.google.android.gms.reminders.model.RecurrenceInfo;
import com.google.android.gms.reminders.model.Task;
import com.google.android.gms.reminders.model.TaskId;
import com.google.android.gms.reminders.model.Time;
import com.google.android.gms.reminders.model.zzaj;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.task:
//            ArpTaskDateTimeInCalendar

public final class TaskUtils
{

    public static TaskData createTaskData(Task task, String s, int i, DateTimeService datetimeservice, long l)
    {
        Object obj1 = null;
        boolean flag2 = false;
        ArpTaskDateTimeInCalendar arptaskdatetimeincalendar = new ArpTaskDateTimeInCalendar(task, datetimeservice, l);
        boolean flag1 = flag2;
        Object obj;
        byte abyte0[];
        long l1;
        if (task.getRecurrenceInfo() != null)
        {
            flag1 = flag2;
            if (task.getDueDate() != null)
            {
                obj = task.getDueDate();
                Long long1 = ((DateTime) (obj)).getAbsoluteTimeMs();
                boolean flag;
                if (long1 != null)
                {
                    if (datetimeservice.convertLocalToAllDayLocal(long1.longValue()) == l)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                } else
                if (datetimeservice.getMillis(((DateTime) (obj)).getYear().intValue(), ((DateTime) (obj)).getMonth().intValue() - 1, ((DateTime) (obj)).getDay().intValue(), 0, 0) == l)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                flag1 = flag2;
                if (flag)
                {
                    flag1 = true;
                }
            }
        }
        if (task.getRecurrenceInfo() == null)
        {
            obj = null;
        } else
        {
            obj = task.getRecurrenceInfo().getRecurrenceId();
        }
        if (task.getArchived().booleanValue())
        {
            abyte0 = null;
        } else
        {
            abyte0 = task.getAssistance();
        }
        obj = (new com.google.android.apps.calendar.timebox.task..AutoValue_TaskData.Builder()).setId(task.getTaskId().getClientAssignedId()).setAccountName(s).setColor(i).setTitle(Platform.nullToEmpty(task.getTitle())).setDone(task.getArchived().booleanValue()).setUnscheduled(arptaskdatetimeincalendar.unscheduled).setRecurringSometimeToday(flag1).setRecurrenceId(((String) (obj)));
        s = obj1;
        if (abyte0 != null)
        {
            s = Arrays.copyOf(abyte0, abyte0.length);
        }
        task = ((com.google.android.apps.calendar.timebox.task.TaskData.Builder) (obj)).setTaskAssistanceProtoBytesInternal(s).setArchivedTime(task.getArchivedTimeMs()).setCreatedTime(task.getCreatedTimeMillis()).setOriginalDueTimeMillis(arptaskdatetimeincalendar.originalStartMillis);
        s = TimeZone.getTimeZone(datetimeservice.calendarTimeZone.timeZone.getID());
        flag1 = arptaskdatetimeincalendar.allDay;
        if (flag1)
        {
            l = datetimeservice.convertLocalToAllDayUtc(arptaskdatetimeincalendar.startMillis);
        } else
        {
            l = arptaskdatetimeincalendar.startMillis;
        }
        if (flag1)
        {
            l1 = TimeBoxUtil.utcJulianDayToMs(TimeBoxUtil.msToUtcJulianDay(l) + 1);
        } else
        {
            l1 = 0x1b7740L + l;
        }
        return task.setTimeRange(TimeRange.newInstance(s, flag1, l, l1)).build();
    }

    public static long dateTimeToMillis(TimeZone timezone, DateTime datetime)
    {
        Calendar calendar = Calendar.getInstance(timezone);
        calendar.set(1, datetime.getYear().intValue());
        calendar.set(2, datetime.getMonth().intValue() - 1);
        calendar.set(5, datetime.getDay().intValue());
        timezone = datetime.getTime();
        if (timezone == null)
        {
            timezone = new com.google.android.gms.reminders.model.Time.Builder();
            timezone.zzcjG = Integer.valueOf(0);
            timezone.zzcjH = Integer.valueOf(0);
            timezone.zzcjI = Integer.valueOf(0);
            timezone = new zzaj(((com.google.android.gms.reminders.model.Time.Builder) (timezone)).zzcjG, ((com.google.android.gms.reminders.model.Time.Builder) (timezone)).zzcjH, ((com.google.android.gms.reminders.model.Time.Builder) (timezone)).zzcjI);
        }
        calendar.set(11, timezone.getHour().intValue());
        calendar.set(12, timezone.getMinute().intValue());
        calendar.set(13, timezone.getSecond().intValue());
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static int getTaskCalendarColor(String s)
    {
        Object obj = SettingsCache.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        obj = (ImmutableMap)((ListenableFutureCache)obj).result;
        s = AccountUtil.newGoogleAccount(s);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        s = (Settings)((ImmutableMap) (obj)).get(s);
        if (!(s instanceof GoogleSettings))
        {
            break MISSING_BLOCK_LABEL_69;
        }
        s = (GoogleSettings)s;
_L1:
        if (s == null)
        {
            return 0;
        } else
        {
            return s.getTaskColor().getValue();
        }
        s = null;
          goto _L1
    }

    public static boolean isRecurring(Task task)
    {
        return task != null && task.getRecurrenceInfo() != null && task.getRecurrenceInfo().getRecurrence() != null;
    }

    public static boolean shouldDueDateBeAbsolute(Task task)
    {
        return task.getTaskList() != null && (task.getTaskList().intValue() == 1 || task.getTaskList().intValue() == 8);
    }

    public static boolean shouldTaskBeInCalendar(Task task)
    {
        while (task.getArchived().booleanValue() && task.getArchivedTimeMs() == null && task.getCreatedTimeMillis() == null || task.getDueDate() != null && task.getDueDate().getUnspecifiedFutureTime().booleanValue() || task.getSnoozed().booleanValue() && task.getDueDate() == null || task.getRecurrenceInfo() != null && task.getRecurrenceInfo().getMaster().booleanValue()) 
        {
            return false;
        }
        return true;
    }

    public static void showReminderToast(Context context, int i, boolean flag, Long long1)
    {
        Resources resources = context.getResources();
        if (!flag)
        {
            long l1 = DateTimeService.getInstance().convertLocalToAllDayUtc(long1.longValue());
            long l;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            long1 = resources.getString(0x7f130343, new Object[] {
                Utils.getDisplayedDatetime(l1, l1, l, Utils.getTimeZoneId(context), true, false, context)
            });
        } else
        {
            long1 = resources.getQuantityString(0x7f12003d, i, new Object[] {
                Integer.valueOf(i)
            });
        }
        Toast.makeText(context, long1, 0).show();
    }
}
