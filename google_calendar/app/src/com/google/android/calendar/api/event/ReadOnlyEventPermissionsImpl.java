// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.event.attachment.AttachmentPermissions;
import com.google.android.calendar.api.event.attachment.ReadOnlyAttachmentPermissionsImpl;
import com.google.android.calendar.api.event.attendee.AttendeePermissions;
import com.google.android.calendar.api.event.attendee.ReadOnlyAttendeePermissionsImpl;
import com.google.android.calendar.api.event.conference.ConferencePermissions;
import com.google.android.calendar.api.event.conference.ReadOnlyConferencePermissionsImpl;
import com.google.android.calendar.api.event.location.ReadOnlyStructuredLocationPermissionsImpl;
import com.google.android.calendar.api.event.location.StructuredLocationPermissions;
import com.google.android.calendar.api.event.notification.NotificationPermissions;
import com.google.android.calendar.api.event.notification.ReadOnlyNotificationPermissionsImpl;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventPermissions

public class ReadOnlyEventPermissionsImpl
    implements EventPermissions
{

    public ReadOnlyEventPermissionsImpl()
    {
    }

    public boolean canChangeOrganizer()
    {
        return false;
    }

    public boolean canDelete()
    {
        return false;
    }

    public boolean canModifyAllDayProperty()
    {
        return false;
    }

    public boolean canModifyCanAttendeesAddAttendees()
    {
        return false;
    }

    public boolean canModifyColorOverride()
    {
        return false;
    }

    public boolean canModifyDescription()
    {
        return false;
    }

    public boolean canModifyEndTime()
    {
        return false;
    }

    public boolean canModifyRecurrence()
    {
        return false;
    }

    public boolean canModifyStartTime()
    {
        return false;
    }

    public boolean canModifySummary()
    {
        return false;
    }

    public boolean canModifyTimeZone()
    {
        return false;
    }

    public List getAllowedAvailabilityValues()
    {
        return Collections.EMPTY_LIST;
    }

    public List getAllowedModificationScopes()
    {
        return Collections.EMPTY_LIST;
    }

    public List getAllowedVisibilityValues()
    {
        return Collections.EMPTY_LIST;
    }

    public AttachmentPermissions getAttachmentPermissions()
    {
        return new ReadOnlyAttachmentPermissionsImpl();
    }

    public AttendeePermissions getAttendeePermissions()
    {
        return new ReadOnlyAttendeePermissionsImpl();
    }

    public ConferencePermissions getConferencePermissions()
    {
        return new ReadOnlyConferencePermissionsImpl();
    }

    public NotificationPermissions getNotificationPermissions()
    {
        return new ReadOnlyNotificationPermissionsImpl();
    }

    public StructuredLocationPermissions getStructuredLocationPermissions()
    {
        return new ReadOnlyStructuredLocationPermissionsImpl();
    }

    public boolean isReadOnly()
    {
        return true;
    }
}
