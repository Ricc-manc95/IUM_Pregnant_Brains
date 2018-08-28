// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcel;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.color.EventColor;
import com.google.android.calendar.api.common.Feature;
import com.google.android.calendar.api.common.TimeZoneHelper;
import com.google.android.calendar.api.event.attachment.Attachment;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.conference.ConferenceData;
import com.google.android.calendar.api.event.location.StructuredLocation;
import com.google.android.calendar.api.event.notification.Notification;
import com.google.android.calendar.api.event.smartmail.SmartMailInfo;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.api.event.userstatus.UserStatus;
import com.google.android.calendar.api.habit.HabitInstance;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.calendar.v2a.android.util.time.TimestampUtil;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event:
//            Event, EventDescriptor, EventUtil, EventResponseSummary, 
//            EventSource, GooglePrivateData

public final class EventImpl
    implements Event
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final ImmutableList attachments;
    private final ImmutableList attendees;
    private final boolean attendeesCanAddAttendees;
    private final boolean attendeesCanModify;
    private final boolean attendeesCanSeeAttendees;
    private final boolean attendeesOmitted;
    private final int availability;
    private final CalendarDescriptor calendarDescriptor;
    private final CalendarListEntry calendarListEntry;
    private final EventColor colorOverride;
    private final ConferenceData conferenceData;
    private final String customAppPackage;
    private final String customAppUri;
    private final String description;
    private final EventDescriptor descriptor;
    private final String dtStamp;
    private final long endMillis;
    private final String endTimeZoneId;
    private final String fingerprint;
    private final GooglePrivateData googlePrivateData;
    private final HabitInstance habitInstance;
    private final boolean hasLocalChanges;
    private final String iCalUid;
    private final boolean isAllDay;
    private final boolean isEndTimeUnspecified;
    private final StructuredLocation location;
    private final List notifications;
    private final AttendeeDescriptor organizer;
    private final UserStatus participantStatus;
    private final Recurrence recurrence;
    private final String recurrenceParentSyncId;
    private final long recurringFirstStartMillis;
    private final EventResponseSummary responseSummary;
    private final int sequenceNumber;
    private final SmartMailInfo smartMailInfo;
    private final EventSource source;
    private final long startMillis;
    private final int status;
    private final String summary;
    private final String syncId;
    private final String timeZoneId;
    private final int visibility;

    EventImpl(Parcel parcel)
    {
        EventDescriptor eventdescriptor = (EventDescriptor)parcel.readParcelable(com/google/android/calendar/api/event/EventDescriptor.getClassLoader());
        String s = parcel.readString();
        String s1 = parcel.readString();
        CalendarDescriptor calendardescriptor = (CalendarDescriptor)parcel.readParcelable(com/google/android/calendar/api/calendarlist/CalendarDescriptor.getClassLoader());
        CalendarListEntry calendarlistentry = (CalendarListEntry)parcel.readParcelable(com/google/android/calendar/api/calendarlist/CalendarListEntry.getClassLoader());
        AttendeeDescriptor attendeedescriptor = (AttendeeDescriptor)parcel.readParcelable(com/google/android/calendar/api/event/attendee/AttendeeDescriptor.getClassLoader());
        int i = parcel.readInt();
        String s2;
        switch (i)
        {
        default:
            throw new IllegalStateException("Invalid status value");

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
            s2 = parcel.readString();
            break;
        }
        long l = parcel.readLong();
        long l1 = parcel.readLong();
        boolean flag = ((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue();
        boolean flag1 = ((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue();
        String s3 = parcel.readString();
        String s4 = parcel.readString();
        Recurrence recurrence1 = (Recurrence)parcel.readParcelable(com/google/android/calendar/api/event/time/Recurrence.getClassLoader());
        long l2 = parcel.readLong();
        HabitInstance habitinstance = (HabitInstance)parcel.readParcelable(com/google/android/calendar/api/habit/HabitInstance.getClassLoader());
        EventColor eventcolor = (EventColor)parcel.readParcelable(com/google/android/calendar/api/color/EventColor.getClassLoader());
        int j = EventUtil.checkVisibility(parcel.readInt());
        int k = EventUtil.checkAvailability(parcel.readInt());
        String s5 = parcel.readString();
        StructuredLocation structuredlocation = (StructuredLocation)parcel.readParcelable(com/google/android/calendar/api/event/location/StructuredLocation.getClassLoader());
        Notification anotification[];
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            anotification = (Notification[])parcel.createTypedArray(Notification.CREATOR);
        } else
        {
            anotification = null;
        }
        this(eventdescriptor, s, s1, calendardescriptor, calendarlistentry, attendeedescriptor, i, s2, l, l1, flag, flag1, s3, s4, recurrence1, l2, habitinstance, eventcolor, j, k, s5, structuredlocation, anotification, (Attachment[])parcel.createTypedArray(Attachment.CREATOR), (Attendee[])parcel.createTypedArray(Attendee.CREATOR), ((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue(), (EventResponseSummary)parcel.readParcelable(com/google/android/calendar/api/event/EventResponseSummary.getClassLoader()), ((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue(), ((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue(), ((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue(), (ConferenceData)parcel.readParcelable(com/google/android/calendar/api/event/conference/ConferenceData.getClassLoader()), (EventSource)parcel.readParcelable(com/google/android/calendar/api/event/EventSource.getClassLoader()), (SmartMailInfo)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailInfo.getClassLoader()), (UserStatus)parcel.readParcelable(com/google/android/calendar/api/event/userstatus/UserStatus.getClassLoader()), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), ((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue(), (GooglePrivateData)parcel.readParcelable(com/google/android/calendar/api/event/GooglePrivateData.getClassLoader()));
    }

    EventImpl(EventDescriptor eventdescriptor, String s, String s1, CalendarDescriptor calendardescriptor, CalendarListEntry calendarlistentry, AttendeeDescriptor attendeedescriptor, int i, 
            String s2, long l, long l1, boolean flag, boolean flag1, 
            String s3, String s4, Recurrence recurrence1, long l2, HabitInstance habitinstance, EventColor eventcolor, 
            int j, int k, String s5, StructuredLocation structuredlocation, Notification anotification[], Attachment aattachment[], Attendee aattendee[], 
            boolean flag2, EventResponseSummary eventresponsesummary, boolean flag3, boolean flag4, boolean flag5, ConferenceData conferencedata, EventSource eventsource, 
            SmartMailInfo smartmailinfo, UserStatus userstatus, String s6, String s7, String s8, int i1, String s9, 
            String s10, boolean flag6, GooglePrivateData googleprivatedata)
    {
        if (eventdescriptor == null)
        {
            throw new NullPointerException();
        }
        descriptor = (EventDescriptor)eventdescriptor;
        syncId = s;
        recurrenceParentSyncId = s1;
        if (calendardescriptor == null)
        {
            throw new NullPointerException();
        }
        calendarDescriptor = (CalendarDescriptor)calendardescriptor;
        if (calendarlistentry == null)
        {
            throw new NullPointerException();
        }
        calendarListEntry = (CalendarListEntry)calendarlistentry;
        if (attendeedescriptor == null)
        {
            throw new NullPointerException();
        }
        organizer = (AttendeeDescriptor)attendeedescriptor;
        status = i;
        summary = s2;
        if (l <= l1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            throw new IllegalArgumentException();
        }
        startMillis = l;
        endMillis = l1;
        isAllDay = flag;
        isEndTimeUnspecified = flag1;
        if (s3 == null || TimeZoneHelper.isValidTimeZoneId(s3))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            throw new IllegalArgumentException();
        }
        timeZoneId = s3;
        if (s4 == null || TimeZoneHelper.isValidTimeZoneId(s4))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            throw new IllegalArgumentException();
        }
        endTimeZoneId = s4;
        if (isAllDay)
        {
            if (!TimestampUtil.isTimestampUtcMidnight(startMillis))
            {
                throw new IllegalArgumentException();
            }
            if (!TimestampUtil.isTimestampUtcMidnight(endMillis))
            {
                throw new IllegalArgumentException();
            }
            if (timeZoneId == null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                throw new IllegalArgumentException();
            }
            if (endTimeZoneId == null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                throw new IllegalArgumentException();
            }
        }
        recurrence = recurrence1;
        recurringFirstStartMillis = l2;
        habitInstance = habitinstance;
        colorOverride = eventcolor;
        visibility = j;
        availability = k;
        description = s5;
        if (structuredlocation == null)
        {
            throw new NullPointerException();
        }
        location = (StructuredLocation)structuredlocation;
        if (anotification != null)
        {
            eventdescriptor = Collections.unmodifiableList(Arrays.asList(anotification));
        } else
        {
            eventdescriptor = null;
        }
        notifications = eventdescriptor;
        if (aattachment != null)
        {
            eventdescriptor = ImmutableList.copyOf(aattachment);
        } else
        {
            eventdescriptor = ImmutableList.of();
        }
        attachments = eventdescriptor;
        if (aattendee != null)
        {
            eventdescriptor = ImmutableList.copyOf(aattendee);
        } else
        {
            eventdescriptor = ImmutableList.of();
        }
        attendees = eventdescriptor;
        attendeesOmitted = flag2;
        responseSummary = eventresponsesummary;
        attendeesCanModify = flag3;
        attendeesCanAddAttendees = flag4;
        attendeesCanSeeAttendees = flag5;
        if (conferencedata == null)
        {
            throw new NullPointerException();
        }
        conferenceData = (ConferenceData)conferencedata;
        source = eventsource;
        smartMailInfo = smartmailinfo;
        participantStatus = userstatus;
        if (s6 == null)
        {
            throw new NullPointerException();
        }
        customAppPackage = (String)s6;
        if (s7 == null)
        {
            throw new NullPointerException();
        } else
        {
            customAppUri = (String)s7;
            iCalUid = s8;
            sequenceNumber = i1;
            dtStamp = s9;
            fingerprint = s10;
            hasLocalChanges = flag6;
            googlePrivateData = googleprivatedata;
            return;
        }
    }

    public final boolean canAttendeesAddAttendees()
    {
        return attendeesCanAddAttendees;
    }

    public final boolean canAttendeesModify()
    {
        return attendeesCanModify;
    }

    public final boolean canAttendeesSeeAttendees()
    {
        return attendeesCanSeeAttendees;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final ImmutableList getAttachments()
    {
        return attachments;
    }

    public final ImmutableList getAttendees()
    {
        return attendees;
    }

    public final int getAvailability()
    {
        return availability;
    }

    public final CalendarDescriptor getCalendar()
    {
        return calendarDescriptor;
    }

    public final CalendarListEntry getCalendarListEntry()
    {
        return calendarListEntry;
    }

    public final EntityColor getColor()
    {
        if (colorOverride == null)
        {
            return calendarListEntry.getColor();
        } else
        {
            return colorOverride;
        }
    }

    public final EventColor getColorOverride()
    {
        return colorOverride;
    }

    public final ConferenceData getConferenceData()
    {
        return conferenceData;
    }

    public final String getCustomAppPackage()
    {
        return customAppPackage;
    }

    public final String getCustomAppUri()
    {
        return customAppUri;
    }

    public final String getDescription()
    {
        return description;
    }

    public final EventDescriptor getDescriptor()
    {
        return descriptor;
    }

    public final long getEndMillis()
    {
        return endMillis;
    }

    public final String getEndTimeZoneId()
    {
        return endTimeZoneId;
    }

    public final GooglePrivateData getGooglePrivateData()
    {
        return googlePrivateData;
    }

    public final String getGoogleSyncId()
    {
        CalendarDescriptor calendardescriptor = getCalendar();
        if (calendardescriptor == null || !AccountUtil.isGoogleAccount(calendardescriptor.account))
        {
            return null;
        } else
        {
            return getSyncId();
        }
    }

    public final Feature getHabitInstance()
    {
        if (habitInstance != null && AccountUtil.isGoogleAccount(calendarDescriptor.account))
        {
            return new com.google.android.calendar.api.common.Feature._cls1(habitInstance);
        } else
        {
            return new Feature();
        }
    }

    public final String getICalDtStamp()
    {
        return dtStamp;
    }

    public final String getICalUid()
    {
        return iCalUid;
    }

    public final StructuredLocation getLocation()
    {
        return location;
    }

    public final List getNotifications()
    {
        return notifications;
    }

    public final AttendeeDescriptor getOrganizer()
    {
        return organizer;
    }

    public final UserStatus getParticipantStatus()
    {
        return participantStatus;
    }

    public final Recurrence getRecurrence()
    {
        return recurrence;
    }

    public final long getRecurringFirstStartMillis()
    {
        boolean flag;
        if (recurrence != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Event is not recurring."));
        } else
        {
            return recurringFirstStartMillis;
        }
    }

    public final EventResponseSummary getResponseSummary()
    {
        return responseSummary;
    }

    public final int getSequenceNumber()
    {
        return sequenceNumber;
    }

    public final SmartMailInfo getSmartMailInfo()
    {
        return smartMailInfo;
    }

    public final EventSource getSource()
    {
        return source;
    }

    public final long getStartMillis()
    {
        return startMillis;
    }

    public final int getStatus()
    {
        return status;
    }

    public final String getSummary()
    {
        return summary;
    }

    public final String getSyncId()
    {
        return syncId;
    }

    public final String getTimeZoneId()
    {
        return timeZoneId;
    }

    public final int getVisibility()
    {
        return visibility;
    }

    public final boolean hasLocalChanges()
    {
        return hasLocalChanges;
    }

    public final boolean isAllDayEvent()
    {
        return isAllDay;
    }

    public final boolean isAttendeesOmitted()
    {
        return attendeesOmitted;
    }

    public final boolean isEndTimeUnspecified()
    {
        return isEndTimeUnspecified;
    }

    public final boolean isHabitInstance()
    {
        return getHabitInstance().isSupported();
    }

    public final boolean isRecurring()
    {
        return recurrence != null;
    }

    public final boolean isSmartMailEvent()
    {
        return smartMailInfo != null;
    }

    public final boolean isSocial()
    {
        return false;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(descriptor, i);
        parcel.writeString(syncId);
        parcel.writeString(recurrenceParentSyncId);
        parcel.writeParcelable(calendarDescriptor, i);
        parcel.writeParcelable(calendarListEntry, i);
        parcel.writeParcelable(organizer, i);
        parcel.writeInt(status);
        parcel.writeString(summary);
        parcel.writeLong(startMillis);
        parcel.writeLong(endMillis);
        parcel.writeValue(Boolean.valueOf(isAllDay));
        parcel.writeValue(Boolean.valueOf(isEndTimeUnspecified));
        parcel.writeString(timeZoneId);
        parcel.writeString(endTimeZoneId);
        parcel.writeParcelable(recurrence, i);
        parcel.writeLong(recurringFirstStartMillis);
        parcel.writeParcelable(habitInstance, i);
        parcel.writeParcelable(colorOverride, i);
        parcel.writeInt(visibility);
        parcel.writeInt(availability);
        parcel.writeString(description);
        parcel.writeParcelable(location, i);
        boolean flag;
        if (notifications != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        parcel.writeValue(Boolean.valueOf(flag));
        if (notifications != null)
        {
            parcel.writeTypedArray((Notification[])notifications.toArray(new Notification[notifications.size()]), i);
        }
        parcel.writeTypedArray((Attachment[])attachments.toArray(new Attachment[attachments.size()]), i);
        parcel.writeTypedArray((Attendee[])attendees.toArray(new Attendee[attendees.size()]), i);
        parcel.writeValue(Boolean.valueOf(attendeesOmitted));
        parcel.writeParcelable(responseSummary, i);
        parcel.writeValue(Boolean.valueOf(attendeesCanModify));
        parcel.writeValue(Boolean.valueOf(attendeesCanAddAttendees));
        parcel.writeValue(Boolean.valueOf(attendeesCanSeeAttendees));
        parcel.writeParcelable(conferenceData, i);
        parcel.writeParcelable(source, i);
        parcel.writeParcelable(smartMailInfo, i);
        parcel.writeParcelable(participantStatus, i);
        parcel.writeString(customAppPackage);
        parcel.writeString(customAppUri);
        parcel.writeString(iCalUid);
        parcel.writeInt(sequenceNumber);
        parcel.writeString(dtStamp);
        parcel.writeString(fingerprint);
        parcel.writeValue(Boolean.valueOf(hasLocalChanges));
        parcel.writeParcelable(googlePrivateData, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new EventImpl(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new EventImpl[i];
        }

        _cls1()
        {
        }
    }

}
