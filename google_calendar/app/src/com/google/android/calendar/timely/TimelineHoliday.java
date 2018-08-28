// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Parcel;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.event.image.AutoValue_StaticUrlEventImageResolver;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineEvent, TimelineItem, TimelineItemOperation

public final class TimelineHoliday extends TimelineEvent
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private String headerImageUrl;

    public TimelineHoliday(Parcel parcel)
    {
        super(parcel);
        headerImageUrl = parcel.readString();
    }

    public TimelineHoliday(TimelineEvent timelineevent)
    {
        super(timelineevent);
        headerImageUrl = FlairAllocatorFactory.getFlairUrlString(super.title);
    }

    public final com.google.android.calendar.event.image.EventImage.Resolver getEventImageResolver()
    {
        return new AutoValue_StaticUrlEventImageResolver(headerImageUrl);
    }

    public final TimelineItem.SortType getSortType()
    {
        return TimelineItem.SortType.HOLIDAY;
    }

    public final boolean hasHeadlineImage()
    {
        return headerImageUrl != null;
    }

    public final boolean hasImage()
    {
        return false;
    }

    public final boolean isIdentical(TimelineItem timelineitem)
    {
        if (this != timelineitem)
        {
            if (timelineitem == null || getClass() != timelineitem.getClass())
            {
                return false;
            }
            if (!super.isIdentical(timelineitem))
            {
                return false;
            }
            Object obj = (TimelineHoliday)timelineitem;
            timelineitem = headerImageUrl;
            obj = ((TimelineHoliday) (obj)).headerImageUrl;
            if (timelineitem != obj && (timelineitem == null || !timelineitem.equals(obj)))
            {
                return false;
            }
        }
        return true;
    }

    public final boolean isSameInstance(TimelineItem timelineitem)
    {
        boolean flag1 = true;
        if (timelineitem != null && timelineitem.getClass().equals(getClass())) goto _L2; else goto _L1
_L1:
        flag1 = false;
_L4:
        return flag1;
_L2:
        if (super.timeRange.getStartDay() != timelineitem.getStartDay())
        {
            break; /* Loop/switch isn't completed */
        }
        String s = super.title;
        timelineitem = timelineitem.getTitle();
        boolean flag;
        if (s == timelineitem || s != null && s.equals(timelineitem))
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

    public final transient Object perform(TimelineItemOperation timelineitemoperation, Object aobj[])
    {
        return timelineitemoperation.onAnyEvent(this, aobj);
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeString(headerImageUrl);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new TimelineHoliday(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new TimelineHoliday[i];
        }

        _cls1()
        {
        }
    }

}
