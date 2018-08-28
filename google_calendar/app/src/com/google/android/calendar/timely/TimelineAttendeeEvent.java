// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Parcel;
import java.util.Arrays;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineEvent, TimelineItemOperation, TimelineItem

public class TimelineAttendeeEvent extends TimelineEvent
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public TimelineAttendeeEvent()
    {
    }

    public TimelineAttendeeEvent(Parcel parcel)
    {
        super(parcel);
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof TimelineAttendeeEvent))
        {
            return false;
        }
        obj = (TimelineAttendeeEvent)obj;
        Object obj1 = super.timeRange;
        Object obj2 = ((TimelineEvent) (obj)).timeRange;
        boolean flag;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || super.selfAttendeeStatus != ((TimelineEvent) (obj)).selfAttendeeStatus || super.endTimeUnspecified != ((TimelineEvent) (obj)).endTimeUnspecified || getColor() != ((TimelineEvent) (obj)).getColor())
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = super.title;
        obj2 = ((TimelineEvent) (obj)).title;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && super.isTransparent == ((TimelineEvent) (obj)).isTransparent) goto _L1; else goto _L3
_L3:
        return false;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            super.timeRange, super.selfAttendeeStatus, Boolean.valueOf(super.endTimeUnspecified), super.title, Boolean.valueOf(super.isTransparent)
        });
    }

    public boolean isIdentical(TimelineItem timelineitem)
    {
        return equals(timelineitem);
    }

    public boolean isSameInstance(TimelineItem timelineitem)
    {
        throw new UnsupportedOperationException();
    }

    public final transient Object perform(TimelineItemOperation timelineitemoperation, Object aobj[])
    {
        return timelineitemoperation.onAttendeeEvent(this, aobj);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new TimelineAttendeeEvent(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new TimelineAttendeeEvent[i];
        }

        _cls1()
        {
        }
    }

}
