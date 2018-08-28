// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Parcelable;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.timely.geometry.TimelineSegment;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineItemOperation

public interface TimelineItem
    extends Parcelable, TimelineSegment, Cloneable
{

    public static final Comparator AllDayComparator = new AllDayComparator();
    public static final Comparator ItemComparator = new TimedComparator();
    public static final Comparator ItemComparatorMultidayFirst = new TimedComparatorMultidayFirst();

    public abstract TimelineItem clone();

    public abstract int getColor();

    public abstract com.google.android.calendar.event.image.EventImage.Resolver getEventImageResolver();

    public abstract Object getId();

    public abstract String getLocation();

    public abstract String getOrganizer();

    public abstract com.google.android.calendar.api.event.attendee.Response.ResponseStatus getSelfAttendeeStatus();

    public abstract long getSortId();

    public abstract SortType getSortType();

    public abstract String getSourceAccountName();

    public abstract TimeRange getTimeRange();

    public abstract String getTitle();

    public abstract boolean hasDeclinedStatus();

    public abstract boolean hasHeadlineImage();

    public abstract boolean hasImage();

    public abstract boolean hasInvitedStatus();

    public abstract boolean isIdentical(TimelineItem timelineitem);

    public abstract boolean isSameInstance(TimelineItem timelineitem);

    public transient abstract Object perform(TimelineItemOperation timelineitemoperation, Object aobj[]);

    public abstract boolean shouldShowOrganizerImage();

    public abstract boolean showEndTime();


    private class TimedComparator extends CategoricalComparator
    {
        private class CategoricalComparator
            implements Comparator
        {

            public int compare(TimelineItem timelineitem, TimelineItem timelineitem1)
            {
                if (timelineitem.getSortType() != timelineitem1.getSortType())
                {
                    return timelineitem.getSortType().compareTo(timelineitem1.getSortType());
                }
                if (timelineitem.getSortId() != timelineitem1.getSortId())
                {
                    return timelineitem.getSortId() != timelineitem1.getSortId();
                }
                if ((timelineitem instanceof TimelineEvent) && (timelineitem1 instanceof TimelineEvent))
                {
                    timelineitem = (TimelineEvent)timelineitem;
                    timelineitem1 = (TimelineEvent)timelineitem1;
                    Comparator comparator = EventKey.COMPARATOR;
                    if (((TimelineEvent) (timelineitem)).originalEventKey != null)
                    {
                        timelineitem = ((TimelineEvent) (timelineitem)).originalEventKey;
                    } else
                    {
                        timelineitem = ((TimelineEvent) (timelineitem)).eventKey;
                    }
                    if (((TimelineEvent) (timelineitem1)).originalEventKey != null)
                    {
                        timelineitem1 = ((TimelineEvent) (timelineitem1)).originalEventKey;
                    } else
                    {
                        timelineitem1 = ((TimelineEvent) (timelineitem1)).eventKey;
                    }
                    return comparator.compare(timelineitem, timelineitem1);
                } else
                {
                    return 0;
                }
            }

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((TimelineItem)obj, (TimelineItem)obj1);
            }

            public CategoricalComparator()
            {
            }

            private class SortType extends Enum
            {

                private static final SortType $VALUES[];
                public static final SortType BIRTHDAY;
                public static final SortType DONE_BUNDLE_REMINDER;
                public static final SortType DONE_SINGLE_REMINDER;
                public static final SortType EVENT;
                public static final SortType HOLIDAY;
                public static final SortType INCOMPLETE_BUNDLE_REMINDER;
                public static final SortType INCOMPLETE_SINGLE_REMINDER;

                public static SortType[] values()
                {
                    return (SortType[])$VALUES.clone();
                }

                static 
                {
                    EVENT = new SortType("EVENT", 0);
                    INCOMPLETE_SINGLE_REMINDER = new SortType("INCOMPLETE_SINGLE_REMINDER", 1);
                    INCOMPLETE_BUNDLE_REMINDER = new SortType("INCOMPLETE_BUNDLE_REMINDER", 2);
                    BIRTHDAY = new SortType("BIRTHDAY", 3);
                    HOLIDAY = new SortType("HOLIDAY", 4);
                    DONE_SINGLE_REMINDER = new SortType("DONE_SINGLE_REMINDER", 5);
                    DONE_BUNDLE_REMINDER = new SortType("DONE_BUNDLE_REMINDER", 6);
                    $VALUES = (new SortType[] {
                        EVENT, INCOMPLETE_SINGLE_REMINDER, INCOMPLETE_BUNDLE_REMINDER, BIRTHDAY, HOLIDAY, DONE_SINGLE_REMINDER, DONE_BUNDLE_REMINDER
                    });
                }

                private SortType(String s, int i)
                {
                    super(s, i);
                }
            }

        }


        public final int compare(TimelineItem timelineitem, TimelineItem timelineitem1)
        {
            if (timelineitem.getStartDay() != timelineitem1.getStartDay())
            {
                return Integer.valueOf(timelineitem.getStartDay()).compareTo(Integer.valueOf(timelineitem1.getStartDay()));
            }
            if (timelineitem.isAllDay() != timelineitem1.isAllDay())
            {
                return Boolean.valueOf(timelineitem1.isAllDay()).compareTo(Boolean.valueOf(timelineitem.isAllDay()));
            }
            if (!timelineitem.isAllDay())
            {
                if (timelineitem.getStartTime() != timelineitem1.getStartTime())
                {
                    return Integer.valueOf(timelineitem.getStartTime()).compareTo(Integer.valueOf(timelineitem1.getStartTime()));
                }
                if (timelineitem.getEndDay() != timelineitem1.getEndDay())
                {
                    return Integer.valueOf(timelineitem.getEndDay()).compareTo(Integer.valueOf(timelineitem1.getEndDay()));
                }
                if (timelineitem.getEndTime() != timelineitem1.getEndTime())
                {
                    return Integer.valueOf(timelineitem.getEndTime()).compareTo(Integer.valueOf(timelineitem1.getEndTime()));
                }
            } else
            if (timelineitem.getEndDay() != timelineitem1.getEndDay())
            {
                return Integer.valueOf(timelineitem1.getEndDay()).compareTo(Integer.valueOf(timelineitem.getEndDay()));
            }
            return super.compare(timelineitem, timelineitem1);
        }

        public final volatile int compare(Object obj, Object obj1)
        {
            return compare((TimelineItem)obj, (TimelineItem)obj1);
        }

        public TimedComparator()
        {
        }
    }


    private class AllDayComparator extends CategoricalComparator
    {

        public final int compare(TimelineItem timelineitem, TimelineItem timelineitem1)
        {
            if (timelineitem.getStartDay() != timelineitem1.getStartDay())
            {
                return Integer.compare(timelineitem.getStartDay(), timelineitem1.getStartDay());
            }
            if (timelineitem.getEndDay() != timelineitem1.getEndDay())
            {
                return Integer.compare(timelineitem1.getEndDay(), timelineitem.getEndDay());
            } else
            {
                return super.compare(timelineitem, timelineitem1);
            }
        }

        public final volatile int compare(Object obj, Object obj1)
        {
            return compare((TimelineItem)obj, (TimelineItem)obj1);
        }

        public AllDayComparator()
        {
        }
    }


    private class TimedComparatorMultidayFirst
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            obj = (TimelineItem)obj;
            obj1 = (TimelineItem)obj1;
            if (((TimelineItem) (obj)).getStartDay() != ((TimelineItem) (obj1)).getStartDay())
            {
                return Integer.compare(((TimelineItem) (obj)).getStartDay(), ((TimelineItem) (obj1)).getStartDay());
            }
            if (((TimelineItem) (obj)).getEndDay() != ((TimelineItem) (obj1)).getEndDay())
            {
                return Integer.compare(((TimelineItem) (obj1)).getEndDay(), ((TimelineItem) (obj)).getEndDay());
            } else
            {
                return TimelineItem.ItemComparator.compare(obj, obj1);
            }
        }

        public TimedComparatorMultidayFirst()
        {
        }
    }

}
