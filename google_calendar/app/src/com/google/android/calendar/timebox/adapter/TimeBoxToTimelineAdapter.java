// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timebox.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.android.apps.calendar.syncadapters.timely.type.CalendarType;
import com.google.android.apps.calendar.timebox.BirthdaySet;
import com.google.android.apps.calendar.timebox.Calendar;
import com.google.android.apps.calendar.timebox.EventItem;
import com.google.android.apps.calendar.timebox.GoalItem;
import com.google.android.apps.calendar.timebox.Item;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timebox.task.TaskItem;
import com.google.android.apps.calendar.timebox.task.TaskSet;
import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.event.CpEventDescriptor;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.experimental.ExperimentalOptions;
import com.google.android.calendar.timely.TimelineBirthday;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.android.calendar.timely.TimelineHoliday;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.calendar.timely.TimelineTaskBundle;
import com.google.android.calendar.utils.ColorUtils;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Iterator;
import java.util.List;
import javax.inject.Provider;

public final class TimeBoxToTimelineAdapter
{

    public final Provider birthdayColorProvider;
    private final String busyTitle;
    private final Context context;
    private final int defaultColor;
    private final String defaultTitle;
    private final Provider holidayColorProvider;

    public TimeBoxToTimelineAdapter(Context context1)
    {
        class .Lambda._cls0
            implements Provider
        {

            private final Context arg$1;

            public final Object get()
            {
                return Integer.valueOf(PrefService.getInstance(arg$1).holidaysColor.getValue());
            }

            .Lambda._cls0(Context context1)
            {
                arg$1 = context1;
            }
        }

        class .Lambda._cls1
            implements Provider
        {

            private final Context arg$1;

            public final Object get()
            {
                return Integer.valueOf(PreferenceUtils.getBirthdayColor(arg$1));
            }

            .Lambda._cls1(Context context1)
            {
                arg$1 = context1;
            }
        }

        this(context1, ContextCompat.getColor(context1, 0x7f0d00ae), ((Provider) (new .Lambda._cls0(context1))), ((Provider) (new .Lambda._cls1(context1))), context1.getString(0x7f1300e6), context1.getString(0x7f130358));
    }

    private TimeBoxToTimelineAdapter(Context context1, int i, Provider provider, Provider provider1, String s, String s1)
    {
        context = context1.getApplicationContext();
        defaultColor = i;
        holidayColorProvider = provider;
        birthdayColorProvider = provider1;
        busyTitle = s;
        defaultTitle = s1;
    }

    public final TimelineEvent createEvent(Item item, TimeRange timerange)
    {
        EventItem eventitem = (EventItem)item;
        com.google.android.apps.calendar.timebox.EventItem.Event event = eventitem.getEvent();
        Calendar calendar = eventitem.getEvent().getCalendar();
        int i = CalendarType.calculateType(calendar.getOwnerAccount());
        if (3 == i)
        {
            return null;
        }
        Object obj;
        if (item instanceof GoalItem)
        {
            item = (GoalItem)item;
            obj = new TimelineGroove(item.getHabitDescriptor());
            Integer integer = item.getGoal().getType();
            if (integer != null)
            {
                obj.type = integer;
            }
            obj.completed = item.getGoal().isDone();
        } else
        {
            obj = new TimelineEvent();
        }
        obj.timeRange = timerange;
        obj.eventKey = eventitem.getEventDescriptor().getKey();
        if (eventitem.getEventDescriptor() instanceof CpEventDescriptor)
        {
            obj.originalEventKey = ((CpEventDescriptor)eventitem.getEventDescriptor()).originalKey;
        }
        obj.calendarId = calendar.getKey();
        obj.accountType = calendar.getAccountType();
        obj.ownerAccount = calendar.getOwnerAccount();
        obj.sourceAccount = calendar.getAccountName();
        obj.calendarAccessLevel = calendar.getAccessLevel();
        obj.isInstanceModifiable = event.isInstanceModifiable();
        obj.color = getDisplayColor(event, i);
        if (TextUtils.isEmpty(event.getTitle()))
        {
            boolean flag;
            if (event.getAccessLevel() == 2 || CalendarAccessLevel.FREEBUSY.equals(event.getCalendar().getAccessLevel()))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                item = busyTitle;
            } else
            {
                item = defaultTitle;
            }
        } else
        {
            item = event.getTitle();
        }
        obj.title = item;
        obj.location = event.getLocation();
        obj.organizer = event.getOrganizer();
        obj.selfAttendeeStatus = event.getSelfAttendeeStatus();
        obj.hasSmartMail = event.getHasSmartMail();
        obj.hasImageData = event.getHasImageData();
        obj.endTimeUnspecified = event.getEndTimeUnspecified();
        obj.showEveryoneDeclined = event.getEveryoneDeclined();
        obj.isOutOfOffice = event.getOutOfOffice();
        obj.hasTimeProposals = event.getHasTimeProposals();
        return ((TimelineEvent) (obj));
    }

    public final TimelineItem createTimelineItem(TimeRangeEntry timerangeentry)
    {
        boolean flag1 = true;
        boolean flag = true;
        if (timerangeentry.getValue() instanceof BirthdaySet)
        {
            Object obj = (BirthdaySet)timerangeentry.getValue();
            if (((BirthdaySet) (obj)).getItems().isEmpty())
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("Birthday set is empty..."));
            }
            obj = (UnmodifiableIterator)((BirthdaySet) (obj)).getItems().iterator();
            TimelineBirthday timelinebirthday = new TimelineBirthday(createEvent((Item)((Iterator) (obj)).next(), timerangeentry.getRange()));
            for (; ((Iterator) (obj)).hasNext(); timelinebirthday.addEvent(createEvent((Item)((Iterator) (obj)).next(), timerangeentry.getRange()))) { }
            timelinebirthday.onInitialBirthdaysAdded();
            return timelinebirthday;
        }
        if (timerangeentry.getValue() instanceof TaskItem)
        {
            return new TimelineTask(((TaskItem)timerangeentry.getValue()).getTaskData());
        }
        if (timerangeentry.getValue() instanceof TaskSet)
        {
            timerangeentry = ((TaskSet)timerangeentry.getValue()).getItems().asList();
            TimelineTaskBundle timelinetaskbundle = new TimelineTaskBundle(new TimelineTask(((TaskItem)timerangeentry.get(0)).getTaskData()));
            for (int i = ((flag1) ? 1 : 0); i < timerangeentry.size(); i++)
            {
                timelinetaskbundle.addTimelineTask(new TimelineTask(((TaskItem)timerangeentry.get(i)).getTaskData()));
            }

            timelinetaskbundle.updateTitles(context);
            return timelinetaskbundle;
        }
        timerangeentry = createEvent((Item)timerangeentry.getValue(), timerangeentry.getRange());
        if (timerangeentry == null)
        {
            return null;
        }
        if (CalendarType.isHolidayCalendar(((TimelineEvent) (timerangeentry)).ownerAccount))
        {
            return new TimelineHoliday(timerangeentry);
        } else
        {
            return timerangeentry;
        }
    }

    public final List entriesToItems(List list)
    {
        EventBucketer eventbucketer;
        Iterator iterator;
        boolean flag;
        flag = false;
        if (ExperimentalOptions.isAlternateTimelineEnabled(context))
        {
            class .Lambda._cls2
                implements Function
            {

                private final TimeBoxToTimelineAdapter arg$1;

                public final Object apply(Object obj2)
                {
                    return arg$1.createTimelineItem((TimeRangeEntry)obj2);
                }

            .Lambda._cls2()
            {
                arg$1 = TimeBoxToTimelineAdapter.this;
            }
            }

            Object obj = new .Lambda._cls2();
            if (list == null)
            {
                throw new NullPointerException();
            }
            if (obj == null)
            {
                throw new NullPointerException();
            }
            list = new com.google.common.collect.Iterables._cls5(list, ((Function) (obj)));
            obj = com.google.common.base.Predicates.ObjectPredicate.NOT_NULL;
            if (list == null)
            {
                throw new NullPointerException();
            }
            if (obj == null)
            {
                throw new NullPointerException();
            } else
            {
                return Lists.newArrayList(new com.google.common.collect.Iterables._cls4(list, ((com.google.common.base.Predicate) (obj))));
            }
        }
        eventbucketer = new EventBucketer(list.size());
        iterator = list.iterator();
_L8:
        Object obj1;
        int i;
        int j;
        i = ((flag) ? 1 : 0);
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_478;
        }
        list = (TimeRangeEntry)iterator.next();
        TimelineEvent timelineevent = createEvent((Item)list.getValue(), list.getRange());
        if (timelineevent == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        switch (CalendarType.calculateType(timelineevent.ownerAccount))
        {
        default:
            eventbucketer.items.add(timelineevent);
            continue; /* Loop/switch isn't completed */

        case 1: // '\001'
            list = (TimelineBirthday)eventbucketer.birthdays.get(timelineevent.timeRange.getStartDay());
            if (list != null)
            {
                list.addEvent(timelineevent);
            } else
            {
                list = new TimelineBirthday(timelineevent);
                eventbucketer.birthdays.put(timelineevent.timeRange.getStartDay(), list);
                eventbucketer.items.add(list);
            }
            continue; /* Loop/switch isn't completed */

        case 2: // '\002'
            com.google.android.calendar.timely.TimelineHoliday.HolidayFilter holidayfilter = eventbucketer.holidayFilter;
            j = com.google.android.calendar.timely.TimelineHoliday.HolidayFilter.getShortHashCode(timelineevent);
            if (TextUtils.isEmpty(timelineevent.ownerAccount))
            {
                i = 0;
            } else
            {
                i = timelineevent.ownerAccount.hashCode();
            }
            i = j * 31 + i;
            j = com.google.android.calendar.timely.TimelineHoliday.HolidayFilter.getShortHashCode(timelineevent);
            obj1 = (SparseArray)holidayfilter.holidays.get(timelineevent.timeRange.getStartDay());
            break;
        }
        if (obj1 != null) goto _L2; else goto _L1
_L1:
        list = new SparseArray();
        holidayfilter.holidays.put(timelineevent.timeRange.getStartDay(), list);
_L6:
        obj1 = new TimelineHoliday(timelineevent);
        list.put(i, obj1);
        list.put(j, obj1);
        list = ((List) (obj1));
_L4:
        if (list != null)
        {
            eventbucketer.items.add(list);
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (((SparseArray) (obj1)).get(i) != null)
        {
            list = null;
            continue; /* Loop/switch isn't completed */
        }
        list = ((List) (obj1));
        if (((SparseArray) (obj1)).get(j) == null)
        {
            break; /* Loop/switch isn't completed */
        }
        ((SparseArray) (obj1)).put(i, (TimelineHoliday)((SparseArray) (obj1)).get(j));
        list = null;
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L6; else goto _L5
_L5:
        for (; i < eventbucketer.birthdays.size(); i++)
        {
            ((TimelineBirthday)eventbucketer.birthdays.get(eventbucketer.birthdays.keyAt(i))).onInitialBirthdaysAdded();
        }

        return eventbucketer.items;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final int getDisplayColor(com.google.android.apps.calendar.timebox.EventItem.Event event, int i)
    {
        if (i == 2)
        {
            return ((Integer)holidayColorProvider.get()).intValue();
        }
        if (event.getColor() != null)
        {
            return ColorUtils.getDisplayColorFromColor(event.getColor().intValue());
        } else
        {
            return defaultColor;
        }
    }

    private class EventBucketer
    {

        public final SparseArray birthdays = new SparseArray();
        public final com.google.android.calendar.timely.TimelineHoliday.HolidayFilter holidayFilter = new com.google.android.calendar.timely.TimelineHoliday.HolidayFilter();
        public final List items;

        EventBucketer(int i)
        {
            items = new ArrayList(i);
        }
    }

}
