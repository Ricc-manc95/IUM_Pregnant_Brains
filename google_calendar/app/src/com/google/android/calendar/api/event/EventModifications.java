// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcelable;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.EventColor;
import com.google.android.calendar.api.common.Feature;
import com.google.android.calendar.api.event.attachment.AttachmentModifications;
import com.google.android.calendar.api.event.attendee.AttendeeModifications;
import com.google.android.calendar.api.event.conference.ConferenceDataModifications;
import com.google.android.calendar.api.event.location.StructuredLocationModifications;
import com.google.android.calendar.api.event.notification.NotificationModifications;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.api.event.userstatus.UserStatus;

// Referenced classes of package com.google.android.calendar.api.event:
//            Event, GooglePrivateDataModification, EventDescriptor

public interface EventModifications
    extends Parcelable, Event
{

    public abstract AttachmentModifications getAttachmentModifications();

    public abstract AttendeeModifications getAttendeeModifications();

    public abstract ConferenceDataModifications getConferenceDataModifications();

    public abstract GooglePrivateDataModification getGooglePrivateDataModification();

    public abstract String getGoogleSyncIdForInsertion();

    public abstract Feature getHabitInstanceModifications();

    public abstract StructuredLocationModifications getLocationModification();

    public abstract NotificationModifications getNotificationModifications();

    public abstract Event getOriginal();

    public abstract boolean isAllDayModified();

    public abstract boolean isAvailabilityModified();

    public abstract boolean isCanAttendeesAddAttendeesModified();

    public abstract boolean isColorOverrideModified();

    public abstract boolean isDescriptionModified();

    public abstract boolean isEndModified();

    public abstract boolean isEndTimeUnspecifiedModified();

    public abstract boolean isEndTimeZoneModified();

    public abstract boolean isIcsImportOrUpdate();

    public abstract boolean isModified();

    public abstract boolean isNewEvent();

    public abstract boolean isOrganizerModified();

    public abstract boolean isParticipantStatusModified();

    public abstract boolean isRecurrenceModified();

    public abstract boolean isStartModified();

    public abstract boolean isSummaryModified();

    public abstract boolean isTimeZoneModified();

    public abstract boolean isVisibilityModified();

    public abstract EventModifications setAvailability(int i);

    public abstract EventModifications setCanAttendeesAddAttendees(boolean flag);

    public abstract EventModifications setColorOverride(EventColor eventcolor);

    public abstract EventModifications setDescription(String s);

    public abstract EventModifications setEndMillis(long l);

    public abstract EventModifications setEndTimeUnspecified(boolean flag);

    public abstract EventModifications setEndTimeZoneId(String s);

    public abstract EventModifications setForceNewEvent(boolean flag);

    public abstract EventModifications setParticipantStatus(UserStatus userstatus);

    public abstract EventModifications setRecurrence(Recurrence recurrence);

    public abstract EventModifications setStartMillis(long l);

    public abstract EventModifications setSummary(String s);

    public abstract EventModifications setTimeZoneId(String s);

    public abstract EventModifications setToAllDayWithDates(long l, long l1);

    public abstract EventModifications setToTimedWithTimes(long l, long l1);

    public abstract EventModifications setVisibility(int i);

    public abstract EventDescriptor switchCalendar(CalendarListEntry calendarlistentry);
}
