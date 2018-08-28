// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitModifications;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineEvent, GrooveEventImageResolver, TimelineItemOperation, TimelineItem

public class TimelineGroove extends TimelineEvent
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public boolean completed;
    private int completedThisWeek;
    public final HabitDescriptor descriptor;
    public HabitModifications mods;
    public int preInstanceMinutes;
    private int sessionNumber;
    private int totalThisWeek;
    public Integer type;

    public TimelineGroove(Parcel parcel)
    {
        super(parcel);
        preInstanceMinutes = -1;
        descriptor = (HabitDescriptor)parcel.readParcelable(com/google/android/calendar/api/habit/HabitDescriptor.getClassLoader());
        if (parcel.readInt() == 1)
        {
            type = Integer.valueOf(parcel.readInt());
        }
        boolean flag;
        boolean flag1;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            mods = (HabitModifications)parcel.readParcelable(com/google/android/calendar/api/habit/Habit.getClassLoader());
        }
        if (parcel.readInt() == 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        completed = flag1;
        sessionNumber = parcel.readInt();
        completedThisWeek = parcel.readInt();
        totalThisWeek = parcel.readInt();
        preInstanceMinutes = parcel.readInt();
    }

    public TimelineGroove(HabitDescriptor habitdescriptor)
    {
        preInstanceMinutes = -1;
        super.ownerAccount = habitdescriptor.calendar.account.name;
        descriptor = habitdescriptor;
    }

    public final com.google.android.calendar.event.image.EventImage.Resolver getEventImageResolver()
    {
        return new GrooveEventImageResolver(this);
    }

    public final boolean hasImage()
    {
        return super.calendarAccessLevel.isGreaterOrEqual(CalendarAccessLevel.OWNER) && (type == null || FlairAllocatorFactory.getGrooveFlairUrlString(type.intValue()) != null || FlairAllocatorFactory.getFlairUrlString(super.title) != null);
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
        if (!super.isIdentical(timelineitem))
        {
            return false;
        }
        timelineitem = (TimelineGroove)timelineitem;
        if (completed != ((TimelineGroove) (timelineitem)).completed || sessionNumber != ((TimelineGroove) (timelineitem)).sessionNumber || completedThisWeek != ((TimelineGroove) (timelineitem)).completedThisWeek || totalThisWeek != ((TimelineGroove) (timelineitem)).totalThisWeek || preInstanceMinutes != ((TimelineGroove) (timelineitem)).preInstanceMinutes)
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj = descriptor;
        Object obj1 = ((TimelineGroove) (timelineitem)).descriptor;
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
        obj = type;
        obj1 = ((TimelineGroove) (timelineitem)).type;
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
        obj = mods;
        timelineitem = ((TimelineGroove) (timelineitem)).mods;
        if (obj == timelineitem || obj != null && obj.equals(timelineitem))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L1; else goto _L3
_L3:
        return false;
    }

    public final transient Object perform(TimelineItemOperation timelineitemoperation, Object aobj[])
    {
        return timelineitemoperation.onGoalEvent(this, aobj);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(descriptor, i);
        int j;
        if (type == null)
        {
            j = 0;
        } else
        {
            j = 1;
        }
        parcel.writeInt(j);
        if (type != null)
        {
            parcel.writeInt(type.intValue());
        }
        if (mods == null)
        {
            j = 0;
        } else
        {
            j = 1;
        }
        parcel.writeInt(j);
        if (mods != null)
        {
            parcel.writeParcelable(mods, i);
        }
        if (completed)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        parcel.writeInt(sessionNumber);
        parcel.writeInt(completedThisWeek);
        parcel.writeInt(totalThisWeek);
        parcel.writeInt(preInstanceMinutes);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new TimelineGroove(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new TimelineGroove[i];
        }

        _cls1()
        {
        }
    }

}
