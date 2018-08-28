// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Parcel;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.api.converters.ResponseStatusConverter;
import com.google.android.calendar.api.event.EventKey;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineEvent, TimelineItem

public final class TimelineExternalEvent extends TimelineEvent
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public TimelineExternalEvent(Parcel parcel)
    {
        super(parcel);
    }

    public TimelineExternalEvent(EventKey eventkey, TimeRange timerange, int i)
    {
        super.eventKey = eventkey;
        super.timeRange = timerange;
        super.selfAttendeeStatus = ResponseStatusConverter.providerToApi(i);
    }

    public final boolean isIdentical(TimelineItem timelineitem)
    {
        throw new UnsupportedOperationException();
    }

    public final boolean isSameInstance(TimelineItem timelineitem)
    {
        throw new UnsupportedOperationException();
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new TimelineExternalEvent(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new TimelineExternalEvent[i];
        }

        _cls1()
        {
        }
    }

}
