// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Parcel;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.timebox.task.TaskData;
import com.google.android.calendar.event.image.AutoValue_StaticUrlEventImageResolver;
import com.google.android.calendar.task.assist.TaskAssistHolder;
import com.google.android.calendar.task.assist.TaskAssistanceUtils;
import java.util.Arrays;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineTaskType, TimelineItemOperation, TimelineItem

public class TimelineTask
    implements TimelineTaskType
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final String accountName;
    public final Long archivedTimeMillis;
    public final int color;
    public final Long createdTimeMillis;
    public final String id;
    public boolean isDone;
    public final Long originalDueMillis;
    public final String recurrenceId;
    public final boolean recurringSometimeToday;
    public final TaskAssistHolder taskAssistHolder;
    private final byte taskAssistanceProtoBytes[];
    public TimeRange timeRange;
    private final String title;
    public final boolean unscheduled;

    public TimelineTask(Parcel parcel)
    {
        boolean flag1 = true;
        super();
        accountName = parcel.readString();
        color = parcel.readInt();
        title = parcel.readString();
        int i;
        boolean flag;
        if (parcel.readInt() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        isDone = flag;
        i = parcel.readInt();
        if (i == -1)
        {
            taskAssistanceProtoBytes = null;
        } else
        {
            taskAssistanceProtoBytes = new byte[i];
            parcel.readByteArray(taskAssistanceProtoBytes);
        }
        id = parcel.readString();
        if (parcel.readInt() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        unscheduled = flag;
        if (parcel.readInt() != 0)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        recurringSometimeToday = flag;
        recurrenceId = parcel.readString();
        taskAssistHolder = TaskAssistanceUtils.createTaskAssistHolder(taskAssistanceProtoBytes, title, accountName);
        archivedTimeMillis = (Long)parcel.readSerializable();
        createdTimeMillis = (Long)parcel.readSerializable();
        originalDueMillis = (Long)parcel.readSerializable();
        timeRange = (TimeRange)parcel.readParcelable(com/google/android/apps/calendar/timebox/TimeRange.getClassLoader());
    }

    public TimelineTask(TaskData taskdata)
    {
        accountName = taskdata.getAccountName();
        color = taskdata.getColor();
        title = taskdata.getTitle();
        isDone = taskdata.isDone();
        taskAssistanceProtoBytes = taskdata.getTaskAssistanceProtoBytes();
        timeRange = taskdata.getTimeRange();
        id = taskdata.getId();
        recurrenceId = taskdata.getRecurrenceId();
        taskAssistHolder = TaskAssistanceUtils.createTaskAssistHolder(taskAssistanceProtoBytes, taskdata.getTitle(), accountName);
        unscheduled = taskdata.isUnscheduled();
        recurringSometimeToday = taskdata.isRecurringSometimeToday();
        archivedTimeMillis = taskdata.getArchivedTime();
        createdTimeMillis = taskdata.getCreatedTime();
        originalDueMillis = taskdata.getOriginalDueTimeMillis();
    }

    public final volatile TimelineItem clone()
    {
        return clone();
    }

    public final TimelineTask clone()
    {
        TimelineTask timelinetask;
        try
        {
            timelinetask = (TimelineTask)super.clone();
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            throw new IllegalStateException(clonenotsupportedexception);
        }
        return timelinetask;
    }

    public Object clone()
        throws CloneNotSupportedException
    {
        return (TimelineTask)clone();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag1 = true;
        if (obj != null && obj.getClass().equals(getClass())) goto _L2; else goto _L1
_L1:
        flag1 = false;
_L4:
        return flag1;
_L2:
        obj = (TimelineTask)obj;
        Object obj1 = id;
        Object obj2 = ((TimelineTask) (obj)).id;
        boolean flag;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = accountName;
        obj2 = ((TimelineTask) (obj)).accountName;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = Integer.valueOf(color);
        obj2 = Integer.valueOf(((TimelineTask) (obj)).color);
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = timeRange;
        obj2 = ((TimelineTask) (obj)).timeRange;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = Boolean.valueOf(isDone);
        obj2 = Boolean.valueOf(((TimelineTask) (obj)).isDone);
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || !Arrays.equals(taskAssistanceProtoBytes, ((TimelineTask) (obj)).taskAssistanceProtoBytes))
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = title;
        obj = ((TimelineTask) (obj)).title;
        if (obj1 == obj || obj1 != null && obj1.equals(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L4; else goto _L3
_L3:
        return false;
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
        return new AutoValue_StaticUrlEventImageResolver(taskAssistHolder.getImageUrl());
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
        return (long)accountName.hashCode();
    }

    public final TimelineItem.SortType getSortType()
    {
        if (isDone)
        {
            return TimelineItem.SortType.DONE_SINGLE_REMINDER;
        } else
        {
            return TimelineItem.SortType.INCOMPLETE_SINGLE_REMINDER;
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
        return isDone;
    }

    public final boolean hasHeadlineImage()
    {
        return taskAssistHolder != null;
    }

    public final boolean hasImage()
    {
        return taskAssistHolder != null;
    }

    public final boolean hasInvitedStatus()
    {
        return false;
    }

    public int hashCode()
    {
        return id.hashCode();
    }

    public final boolean isAllDay()
    {
        return timeRange.isAllDay();
    }

    public final boolean isDone()
    {
        return isDone;
    }

    public final boolean isIdentical(TimelineItem timelineitem)
    {
        if (this != timelineitem) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (timelineitem == null || getClass() != timelineitem.getClass())
        {
            return false;
        }
        timelineitem = (TimelineTask)timelineitem;
        if (unscheduled != ((TimelineTask) (timelineitem)).unscheduled || recurringSometimeToday != ((TimelineTask) (timelineitem)).recurringSometimeToday || color != ((TimelineTask) (timelineitem)).color || isDone != ((TimelineTask) (timelineitem)).isDone)
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj = timeRange;
        Object obj1 = ((TimelineTask) (timelineitem)).timeRange;
        boolean flag;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = id;
        obj1 = ((TimelineTask) (timelineitem)).id;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = recurrenceId;
        obj1 = ((TimelineTask) (timelineitem)).recurrenceId;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = archivedTimeMillis;
        obj1 = ((TimelineTask) (timelineitem)).archivedTimeMillis;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = createdTimeMillis;
        obj1 = ((TimelineTask) (timelineitem)).createdTimeMillis;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = originalDueMillis;
        obj1 = ((TimelineTask) (timelineitem)).originalDueMillis;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = accountName;
        obj1 = ((TimelineTask) (timelineitem)).accountName;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = title;
        obj1 = ((TimelineTask) (timelineitem)).title;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && Arrays.equals(taskAssistanceProtoBytes, ((TimelineTask) (timelineitem)).taskAssistanceProtoBytes)) goto _L1; else goto _L3
_L3:
        return false;
    }

    public final boolean isSameInstance(TimelineItem timelineitem)
    {
        if (timelineitem == null || !timelineitem.getClass().equals(getClass()))
        {
            return false;
        }
        TimelineTask timelinetask = (TimelineTask)timelineitem;
        timelineitem = accountName;
        String s = timelinetask.accountName;
        boolean flag;
        if (timelineitem == s || timelineitem != null && timelineitem.equals(s))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Object obj;
            if (recurrenceId != null)
            {
                timelineitem = recurrenceId;
            } else
            {
                timelineitem = (String)id;
            }
            if (timelinetask.recurrenceId != null)
            {
                obj = timelinetask.recurrenceId;
            } else
            {
                obj = (String)timelinetask.id;
            }
            if (timelineitem == obj || timelineitem != null && timelineitem.equals(obj))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                long l;
                if (originalDueMillis != null)
                {
                    l = originalDueMillis.longValue();
                } else
                {
                    l = timeRange.getUtcStartMillis();
                }
                timelineitem = Long.valueOf(l);
                if (timelinetask.originalDueMillis != null)
                {
                    l = timelinetask.originalDueMillis.longValue();
                } else
                {
                    l = timelinetask.timeRange.getUtcStartMillis();
                }
                obj = Long.valueOf(l);
                if (timelineitem == obj || timelineitem != null && timelineitem.equals(obj))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public final transient Object perform(TimelineItemOperation timelineitemoperation, Object aobj[])
    {
        return timelineitemoperation.onSingleReminder(this, aobj);
    }

    public final void setTransientDoneState(int i)
    {
        isDone = true;
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

    public final boolean spansAtLeastOneDay()
    {
        return timeRange.isAllDay();
    }

    public String toString()
    {
        return String.format("%s(title=%s, id=%s)", new Object[] {
            getClass().getSimpleName(), title, (String)id
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeString(accountName);
        parcel.writeInt(color);
        parcel.writeString(title);
        int j;
        if (isDone)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        if (taskAssistanceProtoBytes == null)
        {
            parcel.writeInt(-1);
        } else
        {
            parcel.writeInt(taskAssistanceProtoBytes.length);
            parcel.writeByteArray(taskAssistanceProtoBytes);
        }
        parcel.writeString(id);
        if (unscheduled)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        if (recurringSometimeToday)
        {
            j = ((flag) ? 1 : 0);
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        parcel.writeString(recurrenceId);
        parcel.writeSerializable(archivedTimeMillis);
        parcel.writeSerializable(createdTimeMillis);
        parcel.writeSerializable(originalDueMillis);
        parcel.writeParcelable(timeRange, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new TimelineTask(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new TimelineTask[i];
        }

        _cls1()
        {
        }
    }

}
