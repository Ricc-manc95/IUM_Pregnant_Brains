// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.net.findatime.utils;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.timely.TimelineAttendeeEvent;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineItem;
import com.google.calendar.suggest.v2.Event;
import com.google.calendar.suggest.v2.SingleEventTime;
import com.google.common.collect.ImmutableList;
import com.google.protobuf.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

public final class FindTimeRequestUtils
{

    public static TimelineAttendeeEvent convertToTimelineAttendeeEvent(Context context, Event event, TimeZone timezone)
    {
        TimelineAttendeeEvent timelineattendeeevent = new TimelineAttendeeEvent();
        timelineattendeeevent.title = event.summary_;
        int i;
        boolean flag;
        if (TextUtils.isEmpty(((TimelineEvent) (timelineattendeeevent)).title))
        {
            if (event.private_)
            {
                i = 0x7f1300e6;
            } else
            {
                i = 0x7f130358;
            }
            timelineattendeeevent.title = context.getString(i);
        }
        if (event.time_ == null)
        {
            context = SingleEventTime.DEFAULT_INSTANCE;
        } else
        {
            context = event.time_;
        }
        timelineattendeeevent.timeRange = toTimeRange(timezone, context);
        if (event.time_ == null)
        {
            context = SingleEventTime.DEFAULT_INSTANCE;
        } else
        {
            context = event.time_;
        }
        if (((SingleEventTime) (context)).endTime_ != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        timelineattendeeevent.endTimeUnspecified = flag;
        if (event.declined_)
        {
            timelineattendeeevent.selfAttendeeStatus = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.DECLINED;
        }
        timelineattendeeevent.isTransparent = event.transparent_;
        timelineattendeeevent.location = event.location_;
        return timelineattendeeevent;
    }

    public static ImmutableList convertToTimelineAttendeeEventList(Context context, List list, TimeZone timezone)
    {
        ArrayList arraylist = new ArrayList();
        for (list = list.iterator(); list.hasNext(); arraylist.add(convertToTimelineAttendeeEvent(context, (Event)list.next(), timezone))) { }
        Collections.sort(arraylist, TimelineItem.ItemComparator);
        return ImmutableList.copyOf(arraylist);
    }

    public static TimeRange toTimeRange(TimeZone timezone, SingleEventTime singleeventtime)
    {
        long l1 = 0L;
        boolean flag1 = singleeventtime.allDay_;
        boolean flag;
        long l;
        if (singleeventtime.startTime_ != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Timestamp timestamp;
            if (singleeventtime.startTime_ == null)
            {
                timestamp = Timestamp.DEFAULT_INSTANCE;
            } else
            {
                timestamp = singleeventtime.startTime_;
            }
            l = timestamp.seconds_ * 1000L + (long)timestamp.nanos_ / 0xf4240L;
        } else
        {
            l = 0L;
        }
        if (singleeventtime.endTime_ != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (singleeventtime.endTime_ == null)
            {
                singleeventtime = Timestamp.DEFAULT_INSTANCE;
            } else
            {
                singleeventtime = singleeventtime.endTime_;
            }
            l1 = ((Timestamp) (singleeventtime)).seconds_ * 1000L + (long)((Timestamp) (singleeventtime)).nanos_ / 0xf4240L;
        }
        return TimeRange.newInstance(timezone, flag1, l, l1);
    }
}
