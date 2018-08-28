// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.attendee.AttendeeCopyAttendeePermissionsImpl;
import com.google.android.calendar.api.event.attendee.AttendeePermissions;
import com.google.android.calendar.api.event.notification.MutableNotificationPermissionsImpl;
import com.google.android.calendar.api.event.notification.NotificationPermissions;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event:
//            ReadOnlyEventPermissionsImpl, Event, EventPermissionUtils, EventModifications, 
//            EventDescriptor

public final class AttendeeCopyEventPermissionsImpl extends ReadOnlyEventPermissionsImpl
{

    private final Event event;

    public AttendeeCopyEventPermissionsImpl(Event event1)
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

    public final boolean canDelete()
    {
        return true;
    }

    public final boolean canModifyColorOverride()
    {
        return EventPermissionUtils.isGoogleEvent(event);
    }

    public final List getAllowedAvailabilityValues()
    {
        CalendarListEntry calendarlistentry = event.getCalendarListEntry();
        if (calendarlistentry == null)
        {
            return Collections.emptyList();
        } else
        {
            return calendarlistentry.getAllowedAvailabilityValues();
        }
    }

    public final List getAllowedModificationScopes()
    {
        EventModifications eventmodifications;
        if (event instanceof EventModifications)
        {
            eventmodifications = (EventModifications)event;
        } else
        {
            eventmodifications = null;
        }
        if (event.getDescriptor().isRecurringPhantom() && (eventmodifications == null || !eventmodifications.isAvailabilityModified() && !eventmodifications.isVisibilityModified()))
        {
            return Collections.unmodifiableList(Arrays.asList(new Integer[] {
                Integer.valueOf(2), Integer.valueOf(0)
            }));
        } else
        {
            return Collections.singletonList(Integer.valueOf(0));
        }
    }

    public final List getAllowedVisibilityValues()
    {
        return EventPermissionUtils.DEFAULT_VSIBILITITY_LIST;
    }

    public final AttendeePermissions getAttendeePermissions()
    {
        return new AttendeeCopyAttendeePermissionsImpl(event);
    }

    public final NotificationPermissions getNotificationPermissions()
    {
        return new MutableNotificationPermissionsImpl(event);
    }

    public final boolean isReadOnly()
    {
        return false;
    }
}
