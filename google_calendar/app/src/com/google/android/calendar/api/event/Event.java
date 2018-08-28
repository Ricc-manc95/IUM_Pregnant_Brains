// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcelable;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.color.EventColor;
import com.google.android.calendar.api.common.Feature;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.conference.ConferenceData;
import com.google.android.calendar.api.event.location.StructuredLocation;
import com.google.android.calendar.api.event.smartmail.SmartMailInfo;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.api.event.userstatus.UserStatus;
import com.google.common.collect.ImmutableList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventDescriptor, GooglePrivateData, EventResponseSummary, EventSource

public interface Event
    extends Parcelable
{

    public abstract boolean canAttendeesAddAttendees();

    public abstract boolean canAttendeesModify();

    public abstract boolean canAttendeesSeeAttendees();

    public abstract ImmutableList getAttachments();

    public abstract ImmutableList getAttendees();

    public abstract int getAvailability();

    public abstract CalendarDescriptor getCalendar();

    public abstract CalendarListEntry getCalendarListEntry();

    public abstract EntityColor getColor();

    public abstract EventColor getColorOverride();

    public abstract ConferenceData getConferenceData();

    public abstract String getCustomAppPackage();

    public abstract String getCustomAppUri();

    public abstract String getDescription();

    public abstract EventDescriptor getDescriptor();

    public abstract long getEndMillis();

    public abstract String getEndTimeZoneId();

    public abstract GooglePrivateData getGooglePrivateData();

    public abstract String getGoogleSyncId();

    public abstract Feature getHabitInstance();

    public abstract String getICalDtStamp();

    public abstract String getICalUid();

    public abstract StructuredLocation getLocation();

    public abstract List getNotifications();

    public abstract AttendeeDescriptor getOrganizer();

    public abstract UserStatus getParticipantStatus();

    public abstract Recurrence getRecurrence();

    public abstract long getRecurringFirstStartMillis();

    public abstract EventResponseSummary getResponseSummary();

    public abstract int getSequenceNumber();

    public abstract SmartMailInfo getSmartMailInfo();

    public abstract EventSource getSource();

    public abstract long getStartMillis();

    public abstract int getStatus();

    public abstract String getSummary();

    public abstract String getSyncId();

    public abstract String getTimeZoneId();

    public abstract int getVisibility();

    public abstract boolean hasLocalChanges();

    public abstract boolean isAllDayEvent();

    public abstract boolean isAttendeesOmitted();

    public abstract boolean isEndTimeUnspecified();

    public abstract boolean isHabitInstance();

    public abstract boolean isRecurring();

    public abstract boolean isSmartMailEvent();

    public abstract boolean isSocial();
}
