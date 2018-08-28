// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import android.accounts.Account;
import android.content.Context;
import android.os.Parcel;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.time.ByDayRecurrence;
import com.google.android.calendar.api.event.time.RecurRulePart;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.newapi.logging.ReminderEditLogMetrics;
import com.google.android.calendar.newapi.model.AccountListHolder;
import com.google.android.calendar.newapi.model.AccountModification;
import com.google.android.calendar.newapi.model.AssistInformationHolder;
import com.google.android.calendar.newapi.model.CalendarHolder;
import com.google.android.calendar.newapi.model.ColorHolder;
import com.google.android.calendar.newapi.model.RecurrenceHolder;
import com.google.android.calendar.newapi.model.SettingsMap;
import com.google.android.calendar.newapi.model.TaskHolder;
import com.google.android.calendar.newapi.model.TimeZoneHolder;
import com.google.android.calendar.newapi.model.TitleModification;
import com.google.android.calendar.newapi.model.ViewTypeHolder;
import com.google.android.calendar.newapi.model.edit.AssistInformationEditHolder;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.model.edit.EventModificationsHolder;
import com.google.android.calendar.newapi.model.edit.RecurrenceEditHolder;
import com.google.android.calendar.newapi.model.edit.SettingsHolder;
import com.google.android.calendar.newapi.model.edit.SettingsMapHolder;
import com.google.android.calendar.newapi.model.edit.TimeModification;
import com.google.android.calendar.newapi.segment.recurrence.ReminderRecurrenceConverter;
import com.google.android.calendar.newapi.segment.time.TimeUtils;
import com.google.android.calendar.task.ArpTaskDateTimeInCalendar;
import com.google.android.calendar.task.TaskUtils;
import com.google.android.calendar.task.assist.TaskAssistHolder;
import com.google.android.calendar.task.assist.TaskAssistanceUtils;
import com.google.android.calendar.time.CalendarUtils;
import com.google.android.calendar.time.DateTimeImpl;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.time.TimeZoneImpl;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.gms.reminders.model.DateTime;
import com.google.android.gms.reminders.model.RecurrenceInfo;
import com.google.android.gms.reminders.model.Task;
import com.google.android.gms.reminders.model.TaskId;
import com.google.android.gms.reminders.model.zzab;
import com.google.common.base.Platform;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.newapi.screen.reminder:
//            ReminderViewScreenModel

public final class ReminderEditScreenModel extends EditScreenModel
    implements AccountListHolder, AccountModification, AssistInformationHolder, CalendarHolder, ColorHolder, RecurrenceHolder, TaskHolder, TimeZoneHolder, TitleModification, ViewTypeHolder, AssistInformationEditHolder, EventModificationsHolder, RecurrenceEditHolder, SettingsHolder, SettingsMapHolder, TimeModification
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public Account account;
    public List accounts;
    public ReminderEditLogMetrics logMetrics;
    public Task original;
    public Recurrence recurrence;
    public SettingsMap settingsMap;
    public Task task;
    private TaskAssistHolder taskAssist;

    ReminderEditScreenModel()
    {
        logMetrics = new ReminderEditLogMetrics();
    }

    ReminderEditScreenModel(Parcel parcel)
    {
        logMetrics = new ReminderEditLogMetrics();
        account = (Account)parcel.readParcelable(android/accounts/Account.getClassLoader());
        ArrayList arraylist = new ArrayList();
        parcel.readTypedList(arraylist, Account.CREATOR);
        if (account == null)
        {
            throw new NullPointerException();
        } else
        {
            accounts = arraylist;
            settingsMap = (SettingsMap)parcel.readParcelable(com/google/android/calendar/newapi/model/SettingsMap.getClassLoader());
            original = (Task)parcel.readParcelable(com/google/android/gms/reminders/model/Task.getClassLoader());
            setTask((Task)parcel.readParcelable(com/google/android/gms/reminders/model/Task.getClassLoader()));
            setRecurrence((Recurrence)parcel.readParcelable(com/google/android/calendar/api/event/time/Recurrence.getClassLoader()));
            logMetrics = (ReminderEditLogMetrics)parcel.readParcelable(com/google/android/calendar/newapi/logging/ReminderEditLogMetrics.getClassLoader());
            return;
        }
    }

    static boolean isOverdue(DateTime datetime, long l)
    {
        return datetime == null || ArpTaskDateTimeInCalendar.getDueTimeMillis(datetime, DateTimeService.getInstance()) < l;
    }

    static Task setAllDayToday(Task task1, long l)
    {
        l = CalendarUtils.createTimeInNewTimeZoneRetainingFields(l, Utils.getTimeZone(CalendarApi.getApiAppContext()), TimeZone.getTimeZone("UTC")).getTimeInMillis();
        com.google.android.gms.reminders.model.Task.Builder builder = new com.google.android.gms.reminders.model.Task.Builder(task1);
        boolean flag = TaskUtils.shouldDueDateBeAbsolute(task1);
        if ("UTC" == null)
        {
            task1 = DateTimeService.getInstance().calendarTimeZone;
        } else
        {
            task1 = new TimeZoneImpl("UTC");
        }
        task1 = ArpTaskDateTimeInCalendar.fromCalendarDateTime(new DateTimeImpl(l, task1), true, flag);
        if (task1 != null)
        {
            task1 = (DateTime)task1.freeze();
        } else
        {
            task1 = null;
        }
        builder.zzcjl = task1;
        return builder.build();
    }

    public final boolean canModifyRecurrence(Context context)
    {
        return true;
    }

    public final boolean canModifyTitle()
    {
        return true;
    }

    public final Account getAccount()
    {
        return account;
    }

    public final List getAccounts()
    {
        return accounts;
    }

    public final TaskAssistHolder getAssistInformation()
    {
        return taskAssist;
    }

    public final int getColor(Context context)
    {
        return getColor().getValue();
    }

    public final EntityColor getColor()
    {
        Object obj = settingsMap;
        Account account1 = getAccount();
        obj = (GoogleSettings)(Settings)((SettingsMap) (obj)).settingsMap.get(account1);
        if (obj == null)
        {
            return CalendarApi.getColorFactory().defaultTaskColor();
        } else
        {
            return ((GoogleSettings) (obj)).getTaskColor();
        }
    }

    public final String getDefaultTimeZoneId(Context context)
    {
        return Utils.getTimeZoneId(context);
    }

    public final long getEnd(Context context)
    {
        return getStart(context);
    }

    public final EventModifications getEventModifications()
    {
        return null;
    }

    public final Recurrence getRecurrence()
    {
        return recurrence;
    }

    public final Settings getSettings()
    {
        SettingsMap settingsmap = settingsMap;
        Account account1 = getAccount();
        return (GoogleSettings)(Settings)settingsmap.settingsMap.get(account1);
    }

    public final SettingsMap getSettingsMap()
    {
        return settingsMap;
    }

    public final long getStart(Context context)
    {
        context = Utils.getTimeZone(context);
        Task task1 = task;
        long l;
        long l1;
        if (task1.getDueDate() == null)
        {
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
        } else
        {
            l = TaskUtils.dateTimeToMillis(context, task1.getDueDate());
        }
        l1 = l;
        if (isAllDay())
        {
            l1 = CalendarUtils.createTimeInNewTimeZoneRetainingFields(l, context, TimeZone.getTimeZone("UTC")).getTimeInMillis();
        }
        return l1;
    }

    public final Task getTask()
    {
        return task;
    }

    public final String getTimeZoneId(Context context)
    {
        return Utils.getTimeZoneId(context);
    }

    public final String getTitle()
    {
        return task.getTitle();
    }

    public final String getViewType()
    {
        return "reminder";
    }

    public final boolean isAllDay()
    {
        return task.getDueDate() == null || task.getArchived() != null && task.getArchived().booleanValue() || ArpTaskDateTimeInCalendar.isDueAllDay(task.getDueDate());
    }

    public final boolean isModified()
    {
        if (original == task || task == null)
        {
            return false;
        }
        Object obj = new com.google.android.gms.reminders.model.Task.Builder(task);
        obj.zzcjq = null;
        obj.zzcjs = null;
        Task task1 = ((com.google.android.gms.reminders.model.Task.Builder) (obj)).build();
        obj = new com.google.android.gms.reminders.model.Task.Builder(original);
        obj.zzcjq = null;
        obj.zzcjs = null;
        obj = ((com.google.android.gms.reminders.model.Task.Builder) (obj)).build();
        DateTime datetime;
        boolean flag;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        datetime = ((Task) (obj)).getDueDate();
        if (datetime == null || ArpTaskDateTimeInCalendar.getDueTimeMillis(datetime, DateTimeService.getInstance()) < l)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj = setAllDayToday(((Task) (obj)), l);
        }
        return !task1.equals(obj);
    }

    public final boolean isNew()
    {
        Task task1 = task;
        return task1.getTaskId() == null || Platform.stringIsNullOrEmpty(task1.getTaskId().getClientAssignedId());
    }

    public final void mergeModel(EditScreenModel editscreenmodel)
    {
        editscreenmodel = (ReminderEditScreenModel)editscreenmodel;
        account = editscreenmodel.getAccount();
        List list = ((ReminderEditScreenModel) (editscreenmodel)).accounts;
        if (account == null)
        {
            throw new NullPointerException();
        } else
        {
            accounts = list;
            original = ((ReminderEditScreenModel) (editscreenmodel)).original;
            settingsMap = ((ReminderEditScreenModel) (editscreenmodel)).settingsMap;
            setTask(((ReminderEditScreenModel) (editscreenmodel)).task);
            return;
        }
    }

    public final void mergeModel(Object obj)
    {
        obj = (ReminderViewScreenModel)obj;
        original = ((ReminderViewScreenModel) (obj)).task;
        account = ((ReminderViewScreenModel) (obj)).account;
        setTask(((ReminderViewScreenModel) (obj)).task);
    }

    public final void setAccount(Account account1)
    {
        account = account1;
    }

    public final void setAssistInformation(byte abyte0[])
    {
        com.google.android.gms.reminders.model.Task.Builder builder = new com.google.android.gms.reminders.model.Task.Builder(task);
        builder.zzcjs = abyte0;
        setTask(builder.build());
    }

    public final void setRecurrence(Recurrence recurrence1)
    {
        com.google.android.gms.reminders.model.Task.Builder builder = new com.google.android.gms.reminders.model.Task.Builder(task);
        builder.zzcjg = Boolean.valueOf(false);
        if (recurrence1 == null)
        {
            recurrence = null;
            if (false)
            {
                throw new NullPointerException();
            } else
            {
                builder.zzcjr = null;
                setTask(builder.build());
                return;
            }
        }
        recurrence = recurrence1;
        Object obj = original.getRecurrenceInfo();
        Object obj1;
        if (obj == null)
        {
            obj = new com.google.android.gms.reminders.model.RecurrenceInfo.Builder();
        } else
        {
            obj = new com.google.android.gms.reminders.model.RecurrenceInfo.Builder(((RecurrenceInfo) (obj)));
        }
        if (task.getDueDate() == null)
        {
            long l;
            boolean flag;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            obj1 = Calendar.getInstance();
            ((Calendar) (obj1)).setTimeInMillis(l);
            l = TimeUtils.toMidnight(((Calendar) (obj1)), false);
            flag = TaskUtils.shouldDueDateBeAbsolute(task);
            if ("UTC" == null)
            {
                obj1 = DateTimeService.getInstance().calendarTimeZone;
            } else
            {
                obj1 = new TimeZoneImpl("UTC");
            }
            obj1 = ArpTaskDateTimeInCalendar.fromCalendarDateTime(new DateTimeImpl(l, ((com.google.calendar.v2.client.service.api.time.TimeZone) (obj1))), true, flag);
        } else
        {
            obj1 = task.getDueDate();
        }
        recurrence1 = ReminderRecurrenceConverter.toReminderRecurrence(recurrence1, ((DateTime) (obj1)), DateTimeService.getInstance());
        if (recurrence1 != null)
        {
            recurrence1 = (com.google.android.gms.reminders.model.Recurrence)recurrence1.freeze();
        } else
        {
            recurrence1 = null;
        }
        obj.zzciU = recurrence1;
        recurrence1 = new zzab(((com.google.android.gms.reminders.model.RecurrenceInfo.Builder) (obj)).zzciU, ((com.google.android.gms.reminders.model.RecurrenceInfo.Builder) (obj)).zzciV, ((com.google.android.gms.reminders.model.RecurrenceInfo.Builder) (obj)).zzciW, ((com.google.android.gms.reminders.model.RecurrenceInfo.Builder) (obj)).zzciX, true);
        if (recurrence1 != null)
        {
            recurrence1 = (RecurrenceInfo)recurrence1.freeze();
        } else
        {
            recurrence1 = null;
        }
        builder.zzcjr = recurrence1;
        setTask(builder.build());
    }

    final void setTask(Task task1)
    {
        if (account == null)
        {
            throw new NullPointerException(String.valueOf("Account expected to be non-null."));
        }
        task = task1;
        RecurrenceInfo recurrenceinfo = task1.getRecurrenceInfo();
        if (TaskUtils.isRecurring(task1))
        {
            recurrence = ReminderRecurrenceConverter.toApiRecurrence(recurrenceinfo.getRecurrence());
        }
        taskAssist = TaskAssistanceUtils.createTaskAssistHolder(task.getAssistance(), task.getTitle(), account.name);
    }

    public final void setTime$5154KMH9AO______0(long l, boolean flag)
    {
        Object obj;
        int i;
        i = 7;
        boolean flag1 = true;
        Object obj1 = new com.google.android.gms.reminders.model.Task.Builder(task);
        Task task1 = task;
        boolean flag2;
        if (flag)
        {
            obj = "UTC";
        } else
        {
            obj = null;
        }
        flag2 = TaskUtils.shouldDueDateBeAbsolute(task1);
        if (obj == null)
        {
            obj = DateTimeService.getInstance().calendarTimeZone;
        } else
        {
            obj = new TimeZoneImpl(((String) (obj)));
        }
        obj = ArpTaskDateTimeInCalendar.fromCalendarDateTime(new DateTimeImpl(l, ((com.google.calendar.v2.client.service.api.time.TimeZone) (obj))), flag, flag2);
        if (obj != null)
        {
            obj = (DateTime)((DateTime) (obj)).freeze();
        } else
        {
            obj = null;
        }
        obj1.zzcjl = ((DateTime) (obj));
        obj1.zzcjg = Boolean.valueOf(false);
        setTask(((com.google.android.gms.reminders.model.Task.Builder) (obj1)).build());
        obj = recurrence;
        if (obj != null && !((Recurrence) (obj)).rrules.isEmpty()) goto _L2; else goto _L1
_L1:
        obj1 = recurrence;
        i = ((flag1) ? 1 : 0);
        Object obj2;
        RecurRulePart recurrulepart;
        int j;
        if (obj1 != obj)
        {
            if (obj1 != null && obj1.equals(obj))
            {
                i = ((flag1) ? 1 : 0);
            } else
            {
                i = 0;
            }
        }
        if (i == 0)
        {
            setRecurrence(((Recurrence) (obj)));
        }
        return;
_L2:
        recurrulepart = (RecurRulePart)((Recurrence) (obj)).rrules.get(0);
        if (recurrulepart.byDay.size() != 1 && recurrulepart.byMonthDay.size() != 1) goto _L1; else goto _L3
_L3:
        if (flag)
        {
            obj2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        } else
        {
            obj2 = Calendar.getInstance();
        }
        ((Calendar) (obj2)).setTimeInMillis(l);
        if (recurrulepart.byDay.size() != 1)
        {
            break MISSING_BLOCK_LABEL_524;
        }
        j = ((Calendar) (obj2)).get(7);
        j;
        JVM INSTR tableswitch 1 7: default 344
    //                   1 386
    //                   2 383
    //                   3 493
    //                   4 499
    //                   5 505
    //                   6 511
    //                   7 517;
           goto _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L11:
        break MISSING_BLOCK_LABEL_517;
_L5:
        break; /* Loop/switch isn't completed */
_L4:
        throw new RuntimeException((new StringBuilder(28)).append("bad day of week: ").append(j).toString());
_L6:
        i = 1;
_L12:
        if (((ByDayRecurrence)recurrulepart.byDay.get(0)).offset != null)
        {
            obj2 = Integer.valueOf((((Calendar) (obj2)).get(5) + 6) / 7);
            obj = obj2;
            if (((Integer) (obj2)).intValue() == 5)
            {
                obj = Integer.valueOf(-1);
            }
        } else
        {
            obj = null;
        }
        obj = new Recurrence(new RecurRulePart[] {
            (new com.google.android.calendar.api.event.time.RecurRulePart.Builder(recurrulepart)).setByDay(Collections.singletonList(new ByDayRecurrence(i, ((Integer) (obj))))).build()
        }, null, null, null);
          goto _L1
_L7:
        i = 2;
          goto _L12
_L8:
        i = 3;
          goto _L12
_L9:
        i = 4;
          goto _L12
_L10:
        i = 5;
          goto _L12
        i = 6;
          goto _L12
        if (recurrulepart.byMonthDay.size() == 1)
        {
            i = ((Calendar) (obj2)).get(5);
            obj = new Recurrence(new RecurRulePart[] {
                (new com.google.android.calendar.api.event.time.RecurRulePart.Builder(recurrulepart)).setByMonthDay(Collections.singletonList(Integer.valueOf(i))).build()
            }, null, null, null);
        }
          goto _L1
    }

    public final void setTitle(String s)
    {
        com.google.android.gms.reminders.model.Task.Builder builder = new com.google.android.gms.reminders.model.Task.Builder(task);
        builder.zzavt = s;
        setTask(builder.build());
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(account, i);
        parcel.writeTypedList(accounts);
        parcel.writeParcelable(settingsMap, i);
        parcel.writeParcelable(original, i);
        parcel.writeParcelable(task, i);
        parcel.writeParcelable(recurrence, i);
        parcel.writeParcelable(logMetrics, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new ReminderEditScreenModel(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new ReminderEditScreenModel[i];
        }

        _cls1()
        {
        }
    }

}
