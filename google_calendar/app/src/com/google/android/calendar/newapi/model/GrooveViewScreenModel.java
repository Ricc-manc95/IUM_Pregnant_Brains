// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model;

import android.accounts.Account;
import android.content.Context;
import android.os.Parcel;
import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.common.Feature;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitContract;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitInstance;
import com.google.android.calendar.newapi.segment.tracking.GrooveTrackingData;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineGroove;

// Referenced classes of package com.google.android.calendar.newapi.model:
//            BasicViewScreenModel, GrooveHolder, GrooveInstanceHolder, GrooveTrackingDataHolder, 
//            ViewScreenModel

public final class GrooveViewScreenModel extends BasicViewScreenModel
    implements GrooveHolder, GrooveInstanceHolder, GrooveTrackingDataHolder
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public Habit habit;
    public GrooveTrackingData trackingData;

    protected GrooveViewScreenModel(Parcel parcel)
    {
        super(parcel);
        habit = (Habit)parcel.readParcelable(com/google/android/calendar/api/habit/Habit.getClassLoader());
        trackingData = (GrooveTrackingData)parcel.readParcelable(com/google/android/calendar/newapi/segment/tracking/GrooveTrackingData.getClassLoader());
    }

    public GrooveViewScreenModel(Habit habit1, Event event, TimelineGroove timelinegroove, int i, GrooveTrackingData groovetrackingdata)
    {
        super(event, timelinegroove, i);
        habit = habit1;
        trackingData = groovetrackingdata;
    }

    public GrooveViewScreenModel(TimelineGroove timelinegroove)
    {
        super(timelinegroove);
    }

    public final Account getAccount()
    {
        if (showSimplifiedScreen())
        {
            return super.event.getCalendar().account;
        } else
        {
            return habit.getDescriptor().calendar.account;
        }
    }

    public final String getCategory()
    {
        return "groove";
    }

    public final int getColor(Context context)
    {
        Object obj;
        boolean flag;
        if (super.event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (habit != null)
            {
                obj = habit.getColorOverride();
            } else
            {
                obj = super.event.getColor();
            }
        } else
        {
            obj = null;
        }
        if (obj == null)
        {
            return super.getColor(context);
        } else
        {
            return ((EntityColor) (obj)).getValue();
        }
    }

    public final Habit getHabit()
    {
        return habit;
    }

    public final HabitInstance getHabitInstance()
    {
        if (super.event.getHabitInstance().isSupported())
        {
            return (HabitInstance)super.event.getHabitInstance().getValue();
        } else
        {
            return null;
        }
    }

    protected final Class getTimelineItemClass()
    {
        return com/google/android/calendar/timely/TimelineGroove;
    }

    public final String getTitle()
    {
        boolean flag;
        if (super.event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return super.getTitle();
        }
        if (showSimplifiedScreen())
        {
            return super.event.getSummary();
        } else
        {
            return habit.getSummary();
        }
    }

    public final GrooveTrackingData getTrackingData()
    {
        return trackingData;
    }

    public final String getViewType()
    {
        return "groove";
    }

    public final int getVisibility()
    {
        if (showSimplifiedScreen())
        {
            return super.event.getVisibility();
        } else
        {
            return habit.getVisibility();
        }
    }

    public final boolean hasImage(Context context)
    {
        boolean flag;
        if (super.event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return ((TimelineEvent) ((TimelineGroove)super.timelineItem)).calendarAccessLevel.isGreaterOrEqual(CalendarAccessLevel.OWNER) && super.hasImage(context);
        }
        return !showSimplifiedScreen() && super.hasImage(context);
    }

    public final boolean isAllDay()
    {
        return false;
    }

    public final void mergeModel(ViewScreenModel viewscreenmodel)
    {
        super.mergeModel(viewscreenmodel);
        viewscreenmodel = (GrooveViewScreenModel)viewscreenmodel;
        setHabit(((GrooveViewScreenModel) (viewscreenmodel)).habit);
        trackingData = ((GrooveViewScreenModel) (viewscreenmodel)).trackingData;
    }

    public final void setHabit(Habit habit1)
    {
        habit = habit1;
        if (trackingData != null)
        {
            trackingData.numInstancesPerInterval = habit.getContract().getNumInstancesPerInterval();
        }
    }

    public final boolean showSimplifiedScreen()
    {
        boolean flag1;
        boolean flag2;
        flag2 = false;
        boolean flag;
        if (super.event != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        flag1 = super.showSimplifiedScreen();
_L4:
        return flag1;
_L2:
        Event event;
        Habit habit1 = habit;
        event = super.event;
        if (habit1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag1 = flag2;
        if (event == null) goto _L4; else goto _L3
_L3:
        flag1 = flag2;
        if (event.getCalendarListEntry().isPrimary()) goto _L4; else goto _L5
_L5:
        return true;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(habit, i);
        parcel.writeParcelable(trackingData, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new GrooveViewScreenModel(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new GrooveViewScreenModel[i];
        }

        _cls1()
        {
        }
    }

}
