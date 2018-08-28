// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import java.util.Arrays;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineAttendeeEvent, TimelineItem

public final class TimelineExchangeAttendeeEvent extends TimelineAttendeeEvent
{

    public int status;

    public TimelineExchangeAttendeeEvent()
    {
    }

    public final boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (!(obj instanceof TimelineExchangeAttendeeEvent))
        {
            return false;
        }
        TimelineExchangeAttendeeEvent timelineexchangeattendeeevent = (TimelineExchangeAttendeeEvent)obj;
        return super.equals(obj) && status == timelineexchangeattendeeevent.status;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(super.hashCode()), Integer.valueOf(status)
        });
    }

    public final boolean isIdentical(TimelineItem timelineitem)
    {
        return equals(timelineitem);
    }

    public final boolean isSameInstance(TimelineItem timelineitem)
    {
        throw new UnsupportedOperationException();
    }
}
