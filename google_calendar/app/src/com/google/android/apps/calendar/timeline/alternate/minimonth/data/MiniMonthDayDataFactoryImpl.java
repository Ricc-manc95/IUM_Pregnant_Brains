// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth.data;

import android.content.Context;
import com.google.android.apps.calendar.syncadapters.timely.type.CalendarType;
import com.google.android.apps.calendar.timebox.BirthdaySet;
import com.google.android.apps.calendar.timebox.Calendar;
import com.google.android.apps.calendar.timebox.EventItem;
import com.google.android.apps.calendar.timebox.Item;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timebox.task.TaskData;
import com.google.android.apps.calendar.timebox.task.TaskItem;
import com.google.android.apps.calendar.timebox.task.TaskSet;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.calendar.alternatecalendar.AlternateCalendarHelper;
import com.google.android.calendar.timebox.adapter.TimeBoxToTimelineAdapter;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth.data:
//            MiniMonthDayDataFactory, AutoValue_MiniMonthDayData, MiniMonthDayData

public final class MiniMonthDayDataFactoryImpl
    implements MiniMonthDayDataFactory
{

    private final TimeBoxToTimelineAdapter adapter;
    private final AlternateCalendarHelper alternateCalendarHelper;
    private final TimeUtils timeUtils;

    MiniMonthDayDataFactoryImpl(Context context, AlternateCalendarHelper alternatecalendarhelper, TimeUtils timeutils)
    {
        adapter = new TimeBoxToTimelineAdapter(context);
        alternateCalendarHelper = alternatecalendarhelper;
        timeUtils = timeutils;
    }

    public final MiniMonthDayData createData(int i, Collection collection)
    {
        String s;
        ArrayList arraylist;
        Iterator iterator;
        s = null;
        arraylist = new ArrayList(3);
        iterator = collection.iterator();
_L3:
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj = (TimeRangeEntry)iterator.next();
        collection = adapter;
        obj = (Item)((TimeRangeEntry) (obj)).getValue();
        if (obj instanceof BirthdaySet)
        {
            collection = (Integer)((TimeBoxToTimelineAdapter) (collection)).birthdayColorProvider.get();
        } else
        if (obj instanceof TaskItem)
        {
            collection = Integer.valueOf(((TaskItem)obj).getTaskData().getColor());
        } else
        if (obj instanceof TaskSet)
        {
            collection = Integer.valueOf(((TaskItem)((UnmodifiableIterator)((TaskSet)obj).getItems().iterator()).next()).getTaskData().getColor());
        } else
        {
label0:
            {
                if (!(obj instanceof EventItem))
                {
                    break label0;
                }
                obj = ((EventItem)obj).getEvent();
                int j = CalendarType.calculateType(((com.google.android.apps.calendar.timebox.EventItem.Event) (obj)).getCalendar().getOwnerAccount());
                if (3 == j)
                {
                    break label0;
                }
                collection = Integer.valueOf(collection.getDisplayColor(((com.google.android.apps.calendar.timebox.EventItem.Event) (obj)), j));
            }
        }
_L1:
        if (collection == null || arraylist.contains(collection))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (arraylist.size() == 3)
        {
            s = String.format(Locale.getDefault(), "%d", new Object[] {
                Integer.valueOf(timeUtils.getCalendarFieldForJulianDay(i, 5))
            });
            if (alternateCalendarHelper.isEnabled())
            {
                collection = alternateCalendarHelper.getDayOfMonth(i);
            } else
            {
                collection = null;
            }
            return new AutoValue_MiniMonthDayData(s, collection, ImmutableList.copyOf(arraylist.subList(0, 2)), true);
        }
        break MISSING_BLOCK_LABEL_307;
        collection = null;
          goto _L1
        arraylist.add(collection);
        if (true) goto _L3; else goto _L2
_L2:
        String s1 = String.format(Locale.getDefault(), "%d", new Object[] {
            Integer.valueOf(timeUtils.getCalendarFieldForJulianDay(i, 5))
        });
        collection = s;
        if (alternateCalendarHelper.isEnabled())
        {
            collection = alternateCalendarHelper.getDayOfMonth(i);
        }
        return new AutoValue_MiniMonthDayData(s1, collection, ImmutableList.copyOf(arraylist), false);
    }
}
