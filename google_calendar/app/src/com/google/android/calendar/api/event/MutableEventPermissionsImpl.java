// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.attachment.AttachmentPermissions;
import com.google.android.calendar.api.event.attachment.MutableAttachmentPermissionsImpl;
import com.google.android.calendar.api.event.attendee.AttendeePermissions;
import com.google.android.calendar.api.event.attendee.MutableAttendeePermissionsImpl;
import com.google.android.calendar.api.event.conference.ConferencePermissions;
import com.google.android.calendar.api.event.conference.MutableConferencePermissionsImpl;
import com.google.android.calendar.api.event.location.MutableStructuredLocationPermissionsImpl;
import com.google.android.calendar.api.event.location.StructuredLocationPermissions;
import com.google.android.calendar.api.event.notification.MutableNotificationPermissionsImpl;
import com.google.android.calendar.api.event.notification.NotificationPermissions;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event:
//            ReadOnlyEventPermissionsImpl, Event, EventPermissionUtils, EventDescriptor

public final class MutableEventPermissionsImpl extends ReadOnlyEventPermissionsImpl
{

    private final Event event;

    public MutableEventPermissionsImpl(Event event1)
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

    public final boolean canChangeOrganizer()
    {
        return EventPermissionUtils.canChangeOrganizer(event);
    }

    public final boolean canDelete()
    {
        return true;
    }

    public final boolean canModifyAllDayProperty()
    {
        return true;
    }

    public final boolean canModifyCanAttendeesAddAttendees()
    {
        return EventPermissionUtils.isGoogleEvent(event);
    }

    public final boolean canModifyColorOverride()
    {
        return EventPermissionUtils.isGoogleEvent(event);
    }

    public final boolean canModifyDescription()
    {
        return true;
    }

    public final boolean canModifyEndTime()
    {
        return true;
    }

    public final boolean canModifyRecurrence()
    {
        return !event.getDescriptor().isRecurringException();
    }

    public final boolean canModifyStartTime()
    {
        return true;
    }

    public final boolean canModifySummary()
    {
        return true;
    }

    public final boolean canModifyTimeZone()
    {
        return !event.isAllDayEvent();
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
        return EventPermissionUtils.getAllowedModificationScopesForEvent(event);
    }

    public final List getAllowedVisibilityValues()
    {
        return EventPermissionUtils.DEFAULT_VSIBILITITY_LIST;
    }

    public final AttachmentPermissions getAttachmentPermissions()
    {
        return new MutableAttachmentPermissionsImpl(event);
    }

    public final AttendeePermissions getAttendeePermissions()
    {
        return new MutableAttendeePermissionsImpl(event);
    }

    public final ConferencePermissions getConferencePermissions()
    {
        return new MutableConferencePermissionsImpl(event);
    }

    public final NotificationPermissions getNotificationPermissions()
    {
        return new MutableNotificationPermissionsImpl(event);
    }

    public final StructuredLocationPermissions getStructuredLocationPermissions()
    {
        return new MutableStructuredLocationPermissionsImpl(event);
    }

    public final boolean isReadOnly()
    {
        return false;
    }
}
