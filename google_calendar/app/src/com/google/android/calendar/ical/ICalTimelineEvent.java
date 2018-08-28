// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.os.Parcel;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineItem;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventOperation

public final class ICalTimelineEvent extends TimelineEvent
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public ICalEventOperation operation;

    public ICalTimelineEvent(Parcel parcel)
    {
        super(parcel);
        parcel = (ICalEventOperation)parcel.readParcelable(com/google/android/calendar/ical/ICalEventOperation.getClassLoader());
        operation = parcel;
        if (parcel.canceled())
        {
            super.selfAttendeeStatus = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.DECLINED;
        }
    }

    public ICalTimelineEvent(TimelineEvent timelineevent, ICalEventOperation icaleventoperation)
    {
        super(timelineevent);
        operation = icaleventoperation;
        if (icaleventoperation.canceled())
        {
            super.selfAttendeeStatus = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.DECLINED;
        }
    }

    public final int getColor()
    {
        return ((EventModifications)operation.events().get(0)).getColor().getValue();
    }

    public final boolean hasInvitedStatus()
    {
        boolean flag;
        if (operation.getImportType() == 5 || operation.getImportType() == 6)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return !flag;
    }

    public final boolean isIdentical(TimelineItem timelineitem)
    {
        if (timelineitem instanceof ICalTimelineEvent)
        {
            Object obj = (ICalTimelineEvent)timelineitem;
            timelineitem = operation;
            obj = ((ICalTimelineEvent) (obj)).operation;
            if (timelineitem == obj || timelineitem != null && timelineitem.equals(obj))
            {
                return true;
            }
        }
        return false;
    }

    public final boolean isSameInstance(TimelineItem timelineitem)
    {
        throw new UnsupportedOperationException();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(operation, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new ICalTimelineEvent(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new ICalTimelineEvent[i];
        }

        _cls1()
        {
        }
    }

}
