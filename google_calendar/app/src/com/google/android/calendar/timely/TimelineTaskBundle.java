// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timebox.TimeRange;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineTaskType, TimelineTask, TimelineItem, TimelineItemOperation

public class TimelineTaskBundle
    implements TimelineTaskType
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/TimelineTaskBundle);
    private String accountName;
    private int color;
    public boolean done;
    public final String id;
    public String singleLineTitle;
    public String subtitle;
    public TimeRange timeRange;
    public ArrayList timelineTaskList;
    public String title;
    public boolean updateFinished;

    public TimelineTaskBundle(Parcel parcel)
    {
        boolean flag1 = true;
        int i = 0;
        super();
        timelineTaskList = new ArrayList();
        title = "";
        subtitle = "";
        singleLineTitle = "";
        updateFinished = false;
        color = parcel.readInt();
        title = parcel.readString();
        subtitle = parcel.readString();
        singleLineTitle = parcel.readString();
        int j;
        boolean flag;
        if (parcel.readByte() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        updateFinished = flag;
        accountName = parcel.readString();
        if (parcel.readByte() != 0)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        done = flag;
        timeRange = (TimeRange)parcel.readParcelable(com/google/android/apps/calendar/timebox/TimeRange.getClassLoader());
        j = parcel.readInt();
        timelineTaskList.ensureCapacity(j);
        for (; i < j; i++)
        {
            addTimelineTask(new TimelineTask(parcel));
        }

        id = buildId();
    }

    public TimelineTaskBundle(TimelineTask timelinetask)
    {
        timelineTaskList = new ArrayList();
        title = "";
        subtitle = "";
        singleLineTitle = "";
        updateFinished = false;
        color = timelinetask.color;
        accountName = timelinetask.accountName;
        done = timelinetask.isDone;
        timeRange = timelinetask.timeRange;
        addTimelineTask(timelinetask);
        id = buildId();
    }

    private final String buildId()
    {
        Object obj;
        obj = accountName;
        String s = Long.toString(timeRange.getStartDay());
        obj = new StringBuilder((new StringBuilder(String.valueOf(obj).length() + 1 + String.valueOf(s).length())).append(((String) (obj))).append("/").append(s).toString());
        if (!timeRange.isAllDay()) goto _L2; else goto _L1
_L1:
        ((StringBuilder) (obj)).append("/allday");
        if (!done) goto _L4; else goto _L3
_L3:
        ((StringBuilder) (obj)).append("/done");
_L6:
        return ((StringBuilder) (obj)).toString();
_L4:
        if (isUnscheduled())
        {
            ((StringBuilder) (obj)).append("/unscheduled");
        }
        continue; /* Loop/switch isn't completed */
_L2:
        ((StringBuilder) (obj)).append("/").append(Integer.toString(timeRange.getStartMinute()));
        if (true) goto _L6; else goto _L5
_L5:
    }

    private final boolean isUnscheduled()
    {
        return !timelineTaskList.isEmpty() && ((TimelineTask)timelineTaskList.get(0)).unscheduled;
    }

    public final void addTimelineTask(TimelineTask timelinetask)
    {
        if (!timelineTaskList.isEmpty() && timeRange.getUtcStartMillis() != timelinetask.timeRange.getUtcStartMillis())
        {
            LogUtils.e(TAG, "Trying to bundle tasks with different start times: %s %s", new Object[] {
                timeRange, timelinetask.timeRange
            });
        }
        TimeRange timerange;
        if (timelineTaskList.isEmpty())
        {
            timerange = timelinetask.timeRange;
        } else
        {
            timerange = timeRange.span(timelinetask.timeRange, timeRange.getTimeZone());
        }
        timeRange = timerange;
        timelineTaskList.add(timelinetask);
        updateFinished = false;
    }

    public final volatile TimelineItem clone()
    {
        return clone();
    }

    public final TimelineTaskBundle clone()
    {
        TimelineTaskBundle timelinetaskbundle;
        try
        {
            timelinetaskbundle = (TimelineTaskBundle)super.clone();
            timelinetaskbundle.timelineTaskList = new ArrayList(timelinetaskbundle.timelineTaskList);
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            throw new IllegalStateException(clonenotsupportedexception);
        }
        return timelinetaskbundle;
    }

    public Object clone()
        throws CloneNotSupportedException
    {
        return (TimelineTaskBundle)clone();
    }

    public final boolean contains(TimelineTask timelinetask)
    {
        timelinetask = (String)timelinetask.id;
        ArrayList arraylist = (ArrayList)timelineTaskList;
        int j = arraylist.size();
        for (int i = 0; i < j;)
        {
            Object obj = arraylist.get(i);
            i++;
            if (timelinetask.equals(((TimelineItem)obj).getId()))
            {
                return true;
            }
        }

        return false;
    }

    public int describeContents()
    {
        return 0;
    }

    public final String getAllTitles()
    {
        StringBuilder stringbuilder = new StringBuilder();
        ArrayList arraylist = (ArrayList)timelineTaskList;
        int j = arraylist.size();
        for (int i = 0; i < j;)
        {
            Object obj = arraylist.get(i);
            i++;
            obj = (TimelineItem)obj;
            if (stringbuilder.length() > 0)
            {
                stringbuilder.append(", ");
            }
            stringbuilder.append(((TimelineItem) (obj)).getTitle());
        }

        return stringbuilder.toString();
    }

    public final int getColor()
    {
        return color;
    }

    public final int getEndDay()
    {
        return timeRange.getEndDay();
    }

    public final long getEndMillis()
    {
        return timeRange.getUtcEndMillis();
    }

    public final int getEndTime()
    {
        return timeRange.getEndMinute();
    }

    public final com.google.android.calendar.event.image.EventImage.Resolver getEventImageResolver()
    {
        return null;
    }

    public final Object getId()
    {
        return id;
    }

    public final String getLocation()
    {
        return null;
    }

    public final String getOrganizer()
    {
        return accountName;
    }

    public final com.google.android.calendar.api.event.attendee.Response.ResponseStatus getSelfAttendeeStatus()
    {
        return com.google.android.calendar.api.event.attendee.Response.ResponseStatus.NEEDS_ACTION;
    }

    public final long getSortId()
    {
        long l = accountName.hashCode();
        int i;
        if (isUnscheduled())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        return (long)i + l;
    }

    public final TimelineItem.SortType getSortType()
    {
        if (done)
        {
            return TimelineItem.SortType.DONE_BUNDLE_REMINDER;
        } else
        {
            return TimelineItem.SortType.INCOMPLETE_BUNDLE_REMINDER;
        }
    }

    public final String getSourceAccountName()
    {
        return accountName;
    }

    public final int getStartDay()
    {
        return timeRange.getStartDay();
    }

    public final long getStartMillis()
    {
        return timeRange.getUtcStartMillis();
    }

    public final int getStartTime()
    {
        return timeRange.getStartMinute();
    }

    public final TimeRange getTimeRange()
    {
        return timeRange;
    }

    public final String getTitle()
    {
        return title;
    }

    public final boolean hasDeclinedStatus()
    {
        return done;
    }

    public final boolean hasHeadlineImage()
    {
        return false;
    }

    public final boolean hasImage()
    {
        return false;
    }

    public final boolean hasInvitedStatus()
    {
        return false;
    }

    public final boolean isAllDay()
    {
        return timeRange.isAllDay();
    }

    public final boolean isDone()
    {
        return done;
    }

    public final boolean isIdentical(TimelineItem timelineitem)
    {
        if (this == timelineitem)
        {
            return true;
        }
        if (timelineitem == null || getClass() != timelineitem.getClass())
        {
            return false;
        }
        timelineitem = (TimelineTaskBundle)timelineitem;
        if (color != ((TimelineTaskBundle) (timelineitem)).color || done != ((TimelineTaskBundle) (timelineitem)).done) goto _L2; else goto _L1
_L1:
        int i;
        Object obj = timeRange;
        Object obj1 = ((TimelineTaskBundle) (timelineitem)).timeRange;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L2; else goto _L3
_L3:
        obj = id;
        obj1 = ((TimelineTaskBundle) (timelineitem)).id;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L2; else goto _L4
_L4:
        obj = accountName;
        obj1 = ((TimelineTaskBundle) (timelineitem)).accountName;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L2; else goto _L5
_L5:
        timelineitem = ((TimelineTaskBundle) (timelineitem)).timelineTaskList;
        if (timelineTaskList.size() == timelineitem.size()) goto _L7; else goto _L6
_L6:
        i = 0;
_L8:
        if (i != 0)
        {
            return true;
        }
        break; /* Loop/switch isn't completed */
_L7:
        int j = timelineTaskList.size();
        i = 0;
        do
        {
            if (i >= j)
            {
                break;
            }
            if (!((TimelineTask)timelineTaskList.get(i)).isIdentical((TimelineItem)timelineitem.get(i)))
            {
                i = 0;
                continue; /* Loop/switch isn't completed */
            }
            i++;
        } while (true);
        i = 1;
        if (true) goto _L8; else goto _L2
_L2:
        return false;
    }

    public final boolean isSameInstance(TimelineItem timelineitem)
    {
        boolean flag1 = true;
        if (timelineitem == null || !timelineitem.getClass().equals(getClass()))
        {
            flag1 = false;
        } else
        {
            timelineitem = (TimelineTaskBundle)timelineitem;
            String s = accountName;
            String s1 = ((TimelineTaskBundle) (timelineitem)).accountName;
            boolean flag;
            if (s == s1 || s != null && s.equals(s1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag || timeRange.getUtcStartMillis() != ((TimelineTaskBundle) (timelineitem)).timeRange.getUtcStartMillis() || done != ((TimelineTaskBundle) (timelineitem)).done)
            {
                return false;
            }
        }
        return flag1;
    }

    public final transient Object perform(TimelineItemOperation timelineitemoperation, Object aobj[])
    {
        return timelineitemoperation.onReminderBundle(this, aobj);
    }

    public final void setTransientDoneState(int i)
    {
        done = true;
        timeRange = TimeRange.newAllDayFromJulianDay(timeRange.getTimeZone(), i, i);
    }

    public final boolean shouldShowOrganizerImage()
    {
        return false;
    }

    public final boolean showEndTime()
    {
        return false;
    }

    public final void sort()
    {
        ArrayList arraylist = timelineTaskList;
        Object obj;
        if (done)
        {
            obj = TimelineItem.SortType.DONE_BUNDLE_REMINDER;
        } else
        {
            obj = TimelineItem.SortType.INCOMPLETE_BUNDLE_REMINDER;
        }
        if (obj == TimelineItem.SortType.DONE_BUNDLE_REMINDER)
        {
            obj = new TaskBundleComparators.DoneTaskComparator();
        } else
        {
            obj = new TaskBundleComparators.IncompleteTaskComparator();
        }
        Collections.sort(arraylist, ((java.util.Comparator) (obj)));
    }

    public final boolean spansAtLeastOneDay()
    {
        return timeRange.isAllDay();
    }

    public String toString()
    {
        String s = getClass().getSimpleName();
        String s1 = title;
        int i = timelineTaskList.size();
        String s2 = getAllTitles();
        String s3 = String.valueOf(timeRange);
        boolean flag = done;
        return (new StringBuilder(String.valueOf(s).length() + 61 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length())).append("[type=").append(s).append(", title=").append(s1).append(", count=").append(i).append(", name=").append(s2).append(", range=").append(s3).append(", done=").append(flag).append("]").toString();
    }

    public final void updateTitles(Context context)
    {
        Object obj;
        if (updateFinished)
        {
            return;
        }
        obj = context.getResources();
        boolean flag = done;
        int j = timelineTaskList.size();
        int i;
        if (flag)
        {
            i = 0x7f120012;
        } else
        {
            i = 0x7f12003e;
        }
        obj = ((Resources) (obj)).getQuantityString(i, j, new Object[] {
            Integer.valueOf(j)
        });
        if (!done) goto _L2; else goto _L1
_L1:
        title = ((String) (obj));
_L4:
        singleLineTitle = ((String) (obj));
        updateFinished = true;
        return;
_L2:
        title = getAllTitles();
        subtitle = ((String) (obj));
        if (!timeRange.isAllDay())
        {
            String s = String.valueOf(subtitle);
            context = (new com.google.android.calendar.time.TimeUtils.TimeZoneUtils()).formatDateRange(context, timeRange.getUtcStartMillis(), timeRange.getUtcStartMillis(), 16385);
            subtitle = (new StringBuilder(String.valueOf(s).length() + 2 + String.valueOf(context).length())).append(s).append(", ").append(context).toString();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int k = 1;
        boolean flag = false;
        parcel.writeInt(color);
        parcel.writeString(title);
        parcel.writeString(subtitle);
        parcel.writeString(singleLineTitle);
        ArrayList arraylist;
        int j;
        if (updateFinished)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeByte((byte)j);
        parcel.writeString(accountName);
        if (done)
        {
            j = k;
        } else
        {
            j = 0;
        }
        parcel.writeByte((byte)j);
        parcel.writeParcelable(timeRange, i);
        parcel.writeInt(timelineTaskList.size());
        arraylist = (ArrayList)timelineTaskList;
        k = arraylist.size();
        for (j = ((flag) ? 1 : 0); j < k;)
        {
            Object obj = arraylist.get(j);
            j++;
            ((TimelineItem)obj).writeToParcel(parcel, i);
        }

    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new TimelineTaskBundle(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new TimelineTaskBundle[i];
        }

        _cls1()
        {
        }
    }

}
