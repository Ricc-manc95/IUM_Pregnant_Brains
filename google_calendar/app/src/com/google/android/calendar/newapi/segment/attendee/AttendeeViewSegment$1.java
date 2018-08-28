// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee;

import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.newapi.model.EventHolder;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.newapi.segment.attendee:
//            AttendeeViewSegment, AttendeesUtils

final class this._cls0
    implements Comparator
{

    private final AttendeeViewSegment this$0;

    public final int compare(Object obj, Object obj1)
    {
        byte byte0 = -1;
        obj = (Attendee)obj;
        obj1 = (Attendee)obj1;
        int i;
        if (AttendeeUtils.isSameAttendee(((EventHolder)model).getEvent().getOrganizer(), ((Attendee) (obj)).attendeeDescriptor))
        {
            i = -1;
        } else
        {
            i = 0;
        }
        if (!AttendeeUtils.isSameAttendee(((EventHolder)model).getEvent().getOrganizer(), ((Attendee) (obj1)).attendeeDescriptor))
        {
            byte0 = 0;
        }
        i -= byte0;
        if (i != 0)
        {
            return i;
        } else
        {
            return AttendeesUtils.getAttendeeName(((Attendee) (obj))).compareToIgnoreCase(AttendeesUtils.getAttendeeName(((Attendee) (obj1))));
        }
    }

    ()
    {
        this$0 = AttendeeViewSegment.this;
        super();
    }
}
