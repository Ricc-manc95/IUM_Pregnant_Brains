// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import android.accounts.Account;
import android.content.Context;
import android.os.Parcel;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.newapi.model.AssistInformationHolder;
import com.google.android.calendar.newapi.model.CalendarHolder;
import com.google.android.calendar.newapi.model.RecurrenceHolder;
import com.google.android.calendar.newapi.model.TaskHolder;
import com.google.android.calendar.newapi.model.TimeHolder;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.segment.recurrence.ReminderRecurrenceConverter;
import com.google.android.calendar.task.ArpTaskDateTimeInCalendar;
import com.google.android.calendar.task.TaskUtils;
import com.google.android.calendar.task.assist.TaskAssistHolder;
import com.google.android.calendar.task.assist.TaskAssistanceUtils;
import com.google.android.calendar.time.DateTimeImpl;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.TimeUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.gms.reminders.model.RecurrenceInfo;
import com.google.android.gms.reminders.model.Task;

public final class ReminderViewScreenModel extends ViewScreenModel
    implements AssistInformationHolder, CalendarHolder, RecurrenceHolder, TaskHolder, TimeHolder
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public Account account;
    private ArpTaskDateTimeInCalendar dateTimeInformation;
    private Recurrence recurrence;
    public GoogleSettings settings;
    public Task task;
    private TaskAssistHolder taskAssist;

    ReminderViewScreenModel(Parcel parcel)
    {
        super(parcel);
        setTask((Task)parcel.readParcelable(com/google/android/gms/reminders/model/Task.getClassLoader()));
        settings = (GoogleSettings)parcel.readParcelable(com/google/android/calendar/api/settings/GoogleSettings.getClassLoader());
    }

    ReminderViewScreenModel(TimelineTask timelinetask)
    {
        super(timelinetask);
    }

    public final Account getAccount()
    {
        return account;
    }

    public final TaskAssistHolder getAssistInformation()
    {
        return taskAssist;
    }

    public final String getCategory()
    {
        return "reminder";
    }

    public final int getColor(Context context)
    {
        if (settings != null)
        {
            return settings.getTaskColor().getValue();
        } else
        {
            return super.getColor(context);
        }
    }

    public final long getEnd(Context context)
    {
        return getStart(context);
    }

    public final Recurrence getRecurrence()
    {
        return recurrence;
    }

    public final long getStart(Context context)
    {
        if (dateTimeInformation.allDay)
        {
            return TimeUtils.convertAlldayLocalToUTC(new Time(), dateTimeInformation.startMillis, Utils.getTimeZoneId(context));
        } else
        {
            return dateTimeInformation.startMillis;
        }
    }

    public final Task getTask()
    {
        return task;
    }

    protected final Class getTimelineItemClass()
    {
        return com/google/android/calendar/timely/TimelineTask;
    }

    public final String getTitle()
    {
        if (task == null)
        {
            return super.getTitle();
        } else
        {
            return task.getTitle();
        }
    }

    public final String getViewType()
    {
        return "reminder";
    }

    public final boolean isAllDay()
    {
        return dateTimeInformation.allDay;
    }

    public final boolean isEditable()
    {
        return true;
    }

    public final void mergeModel(ViewScreenModel viewscreenmodel)
    {
        super.mergeModel(viewscreenmodel);
        viewscreenmodel = (ReminderViewScreenModel)viewscreenmodel;
        setTask(((ReminderViewScreenModel) (viewscreenmodel)).task);
        settings = ((ReminderViewScreenModel) (viewscreenmodel)).settings;
    }

    public final void setTask(Task task1)
    {
        task = task1;
        taskAssist = TaskAssistanceUtils.createTaskAssistHolder(task.getAssistance(), task.getTitle(), ((TimelineTask)super.timelineItem).accountName);
        dateTimeInformation = new ArpTaskDateTimeInCalendar(task, DateTimeService.getInstance());
        task1 = task.getRecurrenceInfo();
        String s;
        DateTimeService datetimeservice;
        Time time;
        int i;
        long l;
        if (task1 != null && task1.getRecurrence() != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            recurrence = ReminderRecurrenceConverter.toApiRecurrence(task1.getRecurrence());
        } else
        {
            recurrence = null;
        }
        task1 = task;
        s = account.name;
        i = ((TimelineTask)super.timelineItem).color;
        datetimeservice = DateTimeService.getInstance();
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        time = DateTimeService.toDateTimeImpl((new DateTimeImpl(l, datetimeservice.calendarTimeZone)).withTime(0, 0, 0)).time;
        time.writeFieldsToImpl();
        super.setTimelineItem(new TimelineTask(TaskUtils.createTaskData(task1, s, i, datetimeservice, time.impl.toMillis(false))));
        account = AccountUtil.newGoogleAccount(((TimelineTask)super.timelineItem).accountName);
    }

    public final void setTimelineItem(TimelineItem timelineitem)
    {
        super.setTimelineItem((TimelineTask)timelineitem);
        account = AccountUtil.newGoogleAccount(((TimelineTask)super.timelineItem).accountName);
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(task, i);
        parcel.writeParcelable(settings, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new ReminderViewScreenModel(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new ReminderViewScreenModel[i];
        }

        _cls1()
        {
        }
    }

}
