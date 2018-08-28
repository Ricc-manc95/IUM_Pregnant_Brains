// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attendee;

import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventPermissionUtils;

// Referenced classes of package com.google.android.calendar.api.event.attendee:
//            ReadOnlyAttendeePermissionsImpl, Attendee, AttendeeDescriptor

public final class MutableAttendeePermissionsImpl extends ReadOnlyAttendeePermissionsImpl
{

    private final Event event;

    public MutableAttendeePermissionsImpl(Event event1)
    {
        if (event1 == null)
        {
            throw new NullPointerException();
        } else
        {
            event = (Event)event1;
            return;
        }
    }

    public final boolean canAddAttendees()
    {
        return !event.isAttendeesOmitted();
    }

    public final boolean canModifyResponseComment()
    {
        return canModifyResponseStatus() && EventPermissionUtils.isExchangeEvent(event);
    }

    public final boolean canModifyResponseStatus()
    {
        Attendee attendee = AttendeeUtils.getCurrentAttendee(event);
        if (attendee != null && EventPermissionUtils.attendeeEmailMatchesCalendar(attendee.attendeeDescriptor, event))
        {
            boolean flag = event.getOrganizer().equals(attendee.attendeeDescriptor);
            if (event.getCalendarListEntry().canOrganizerRespond() || !flag)
            {
                return true;
            }
        }
        return false;
    }

    public final boolean canProposeNewTime()
    {
        return false;
    }

    public final boolean canRemoveAttendees()
    {
        return !event.isAttendeesOmitted();
    }

    public final boolean isReadOnly()
    {
        return false;
    }
}
