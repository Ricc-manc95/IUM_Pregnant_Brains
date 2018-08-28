// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.event.attachment.AttachmentPermissions;
import com.google.android.calendar.api.event.attendee.AttendeePermissions;
import com.google.android.calendar.api.event.conference.ConferencePermissions;
import com.google.android.calendar.api.event.location.StructuredLocationPermissions;
import com.google.android.calendar.api.event.notification.NotificationPermissions;
import java.util.List;

public interface EventPermissions
{

    public abstract boolean canChangeOrganizer();

    public abstract boolean canDelete();

    public abstract boolean canModifyAllDayProperty();

    public abstract boolean canModifyCanAttendeesAddAttendees();

    public abstract boolean canModifyColorOverride();

    public abstract boolean canModifyDescription();

    public abstract boolean canModifyEndTime();

    public abstract boolean canModifyRecurrence();

    public abstract boolean canModifyStartTime();

    public abstract boolean canModifySummary();

    public abstract boolean canModifyTimeZone();

    public abstract List getAllowedAvailabilityValues();

    public abstract List getAllowedModificationScopes();

    public abstract List getAllowedVisibilityValues();

    public abstract AttachmentPermissions getAttachmentPermissions();

    public abstract AttendeePermissions getAttendeePermissions();

    public abstract ConferencePermissions getConferencePermissions();

    public abstract NotificationPermissions getNotificationPermissions();

    public abstract StructuredLocationPermissions getStructuredLocationPermissions();

    public abstract boolean isReadOnly();
}
