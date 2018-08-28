// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.content.Context;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.common.Feature;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.StructuredLocation;
import com.google.android.calendar.api.habit.HabitInstance;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.ical:
//            AutoValue_ICalEventOperation, ICalTimelineEvent

public abstract class ICalEventOperation
    implements Parcelable
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/ical/ICalEventOperation);

    public ICalEventOperation()
    {
    }

    static final int bridge$lambda$0$ICalEventOperation(EventModifications eventmodifications)
    {
        boolean flag1 = false;
        if (eventmodifications.isNewEvent())
        {
            return 0;
        }
        int i = eventmodifications.getSequenceNumber() - eventmodifications.getOriginal().getSequenceNumber();
        if (i < 0)
        {
            i = 7;
        } else
        if (i > 0)
        {
            i = 1;
        } else
        {
            i = 5;
        }
        if (i != 5)
        {
            return i;
        }
        boolean flag = flag1;
        if (eventmodifications.getOriginal() != null)
        {
            flag = flag1;
            if (eventmodifications.getSequenceNumber() == eventmodifications.getOriginal().getSequenceNumber())
            {
                flag = flag1;
                if (!eventmodifications.isModified())
                {
                    flag = true;
                }
            }
        }
        if (flag)
        {
            return 5;
        }
        if (eventmodifications.getOriginal().getICalDtStamp() == null || eventmodifications.getICalDtStamp() == null)
        {
            return 3;
        }
        long l = Long.parseLong(eventmodifications.getOriginal().getICalDtStamp()) - Long.parseLong(eventmodifications.getICalDtStamp());
        if (l < 1L)
        {
            return 1;
        }
        return l != 0L ? 7 : 3;
    }

    public static ICalEventOperation cancel(Collection collection)
    {
        class .Lambda._cls1
            implements Function
        {

            public static final Function $instance = new .Lambda._cls1();

            public final Object apply(Object obj)
            {
                return Integer.valueOf(ICalEventOperation.bridge$lambda$0$ICalEventOperation((EventModifications)obj));
            }


            private .Lambda._cls1()
            {
            }
        }

        Function function = .Lambda._cls1..instance;
        if (collection == null)
        {
            throw new NullPointerException();
        }
        if (function == null)
        {
            throw new NullPointerException();
        } else
        {
            int i = compareToLocalEvents(new com.google.common.collect.Iterables._cls5(collection, function));
            return new AutoValue_ICalEventOperation(ImmutableList.copyOf(collection), true, i);
        }
    }

    private static int compareToLocalEvents(Iterable iterable)
    {
        int i;
        int j;
        int k;
        if (iterable instanceof Collection)
        {
            i = ((Collection)iterable).size();
        } else
        {
            Iterator iterator = iterable.iterator();
            long l;
            for (l = 0L; iterator.hasNext(); l++)
            {
                iterator.next();
            }

            if (l > 0x7fffffffL)
            {
                i = 0x7fffffff;
            } else
            if (l < 0xffffffff80000000L)
            {
                i = 0x80000000;
            } else
            {
                i = (int)l;
            }
        }
_L11:
        iterable = iterable.iterator();
        j = 0;
        k = 0;
_L3:
        if (!iterable.hasNext()) goto _L2; else goto _L1
_L1:
        ((Integer)iterable.next()).intValue();
        JVM INSTR tableswitch 0 7: default 96
    //                   0 171
    //                   1 194
    //                   2 96
    //                   3 203
    //                   4 96
    //                   5 96
    //                   6 96
    //                   7 191;
           goto _L3 _L4 _L5 _L3 _L6 _L3 _L3 _L3 _L7
_L4:
        if (i != 1)
        {
            LogUtils.wtf(TAG, "Expected only one new event", new Object[0]);
        }
_L9:
        return 0;
_L7:
        return 7;
_L5:
        k++;
          goto _L3
_L6:
        j++;
          goto _L3
_L2:
        if (j > 0)
        {
            return j + k <= 1 ? 3 : 4;
        }
        if (k > 0)
        {
            return k <= 1 ? 1 : 2;
        }
        if (i == 0) goto _L9; else goto _L8
_L8:
        if (i == 1)
        {
            return 5;
        }
        return 6;
        if (true) goto _L11; else goto _L10
_L10:
    }

    public static ICalEventOperation create(Collection collection)
    {
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj)
            {
                return Integer.valueOf(ICalEventOperation.bridge$lambda$0$ICalEventOperation((EventModifications)obj));
            }


            private .Lambda._cls0()
            {
            }
        }

        Function function = .Lambda._cls0..instance;
        if (collection == null)
        {
            throw new NullPointerException();
        }
        if (function == null)
        {
            throw new NullPointerException();
        } else
        {
            int i = compareToLocalEvents(new com.google.common.collect.Iterables._cls5(collection, function));
            return new AutoValue_ICalEventOperation(ImmutableList.copyOf(collection), false, i);
        }
    }

    public abstract boolean canceled();

    public abstract ImmutableList events();

    public abstract int getImportType();

    final ICalTimelineEvent toTimelineEvent(Context context)
    {
        Object obj = events();
        if (obj == null)
        {
            throw new NullPointerException();
        }
        Object obj1;
        Event event;
        if (obj instanceof List)
        {
            obj = ((List)obj).get(0);
        } else
        {
            obj = ((Iterable) (obj)).iterator();
            if (obj == null)
            {
                throw new NullPointerException();
            }
            if (!((Iterator) (obj)).hasNext())
            {
                throw new IndexOutOfBoundsException((new StringBuilder(91)).append("position (").append(0).append(") must be less than the number of elements that remained (").append(0).append(")").toString());
            }
            obj = ((Iterator) (obj)).next();
        }
        event = (Event)obj;
        if (event.getHabitInstance().isSupported() && event.getHabitInstance().getValue() != null)
        {
            obj1 = (HabitInstance)event.getHabitInstance().getValue();
            obj = new TimelineGroove(((HabitInstance) (obj1)).getHabitParentDescriptor());
            Object obj2 = (TimelineGroove)obj;
            obj2.type = Integer.valueOf(((HabitInstance) (obj1)).getParentType());
            boolean flag;
            if (((HabitInstance) (obj1)).getStatus() == 3)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            obj2.completed = flag;
            obj2.calendarAccessLevel = CalendarAccessLevel.OWNER;
        } else
        {
            obj = new TimelineEvent();
        }
        if (event.getDescriptor().isCommitted())
        {
            obj.eventKey = event.getDescriptor().getKey();
        }
        obj.calendarId = event.getCalendar().calendarKey;
        if (TextUtils.isEmpty(event.getSummary()))
        {
            obj1 = context.getString(0x7f130358);
        } else
        {
            obj1 = event.getSummary();
        }
        obj.title = ((String) (obj1));
        obj.organizer = event.getOrganizer().email;
        obj2 = event.getTimeZoneId();
        obj1 = obj2;
        if (obj2 == null)
        {
            obj1 = Utils.getTimeZoneId(context);
        }
        obj.timeRange = TimeRange.newInstance(TimeZone.getTimeZone(((String) (obj1))), event.isAllDayEvent(), event.getStartMillis(), event.getEndMillis());
        obj.color = event.getColor().getValue();
        if (!event.getLocation().getEventLocations().isEmpty())
        {
            obj.location = ((EventLocation)event.getLocation().getEventLocations().iterator().next()).name;
        }
        context = AttendeeUtils.getCurrentAttendee(event);
        if (context != null)
        {
            obj.selfAttendeeStatus = ((Attendee) (context)).response.status;
        }
        obj.hasSmartMail = event.isSmartMailEvent();
        return new ICalTimelineEvent(((TimelineEvent) (obj)), this);
    }

}
