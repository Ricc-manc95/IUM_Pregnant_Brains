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
import com.google.android.calendar.api.common.FieldModification;
import com.google.android.calendar.api.common.TimeZoneHelper;
import com.google.android.calendar.api.event.attachment.AttachmentModifications;
import com.google.android.calendar.api.event.attachment.AttachmentModificationsImpl;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.AttendeeModifications;
import com.google.android.calendar.api.event.attendee.AttendeeModificationsImpl;
import com.google.android.calendar.api.event.conference.ConferenceData;
import com.google.android.calendar.api.event.conference.ConferenceDataModifications;
import com.google.android.calendar.api.event.conference.ConferenceDataModificationsImpl;
import com.google.android.calendar.api.event.location.StructuredLocation;
import com.google.android.calendar.api.event.location.StructuredLocationImpl;
import com.google.android.calendar.api.event.location.StructuredLocationModifications;
import com.google.android.calendar.api.event.location.StructuredLocationModificationsImpl;
import com.google.android.calendar.api.event.notification.NotificationModifications;
import com.google.android.calendar.api.event.notification.NotificationModificationsImpl;
import com.google.android.calendar.api.event.smartmail.SmartMailInfo;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.api.event.userstatus.UserStatus;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitInstance;
import com.google.android.calendar.api.habit.HabitInstanceModifications;
import com.google.android.calendar.api.habit.HabitInstanceModificationsImpl;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.calendar.v2a.android.util.time.TimestampUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.io.BaseEncoding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventModifications, Event, EventDescriptor, EventUtil, 
//            GooglePrivateDataModificationImpl, GooglePrivateDataModification, UncommittedEventDescriptor, GooglePrivateData, 
//            EventResponseSummary, EventSource

public final class EventModificationsImpl
    implements EventModifications
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private static final StructuredLocationImpl EMPTY_LOCATION = new StructuredLocationImpl(new ArrayList());
    private final AttachmentModificationsImpl attachmentModifications;
    private final AttendeeModificationsImpl attendeeModifications;
    private FieldModification availability;
    private FieldModification calendarDescriptor;
    private FieldModification calendarListEntry;
    private FieldModification canAttendeesAddAttendees;
    private FieldModification colorOverride;
    private final ConferenceDataModifications conferenceDataModifications;
    private FieldModification description;
    private EventDescriptor descriptor;
    private FieldModification endMillis;
    private FieldModification endTimeZoneId;
    private boolean forceNewEvent;
    private GooglePrivateDataModification googlePrivateDataFieldModification;
    private String googleSyncIdForInsertion;
    private HabitInstanceModifications habitInstanceModifications;
    public FieldModification iCalDtStamp;
    public FieldModification iCalUid;
    public boolean icsImportOrUpdate;
    public String icsOrganizerEmail;
    private FieldModification isAllDay;
    private FieldModification isEndTimeUnspecified;
    private final StructuredLocationModifications locationModifications;
    private final NotificationModificationsImpl notificationModifications;
    private final Event original;
    private FieldModification participantStatus;
    private FieldModification recurrence;
    public FieldModification sequenceNumber;
    private FieldModification startMillis;
    private FieldModification summary;
    private FieldModification timeZoneId;
    private FieldModification visibility;

    EventModificationsImpl(Parcel parcel)
    {
        calendarDescriptor = new FieldModification();
        calendarListEntry = new FieldModification();
        summary = new FieldModification();
        startMillis = new FieldModification();
        endMillis = new FieldModification();
        isAllDay = new FieldModification();
        isEndTimeUnspecified = new FieldModification();
        timeZoneId = new FieldModification();
        endTimeZoneId = new FieldModification();
        recurrence = new FieldModification();
        colorOverride = new FieldModification();
        visibility = new FieldModification();
        availability = new FieldModification();
        description = new FieldModification();
        canAttendeesAddAttendees = new FieldModification();
        participantStatus = new FieldModification();
        icsOrganizerEmail = null;
        iCalUid = new FieldModification();
        sequenceNumber = new FieldModification();
        iCalDtStamp = new FieldModification();
        original = (Event)parcel.readParcelable(com/google/android/calendar/api/event/Event.getClassLoader());
        descriptor = (EventDescriptor)parcel.readParcelable(com/google/android/calendar/api/event/EventDescriptor.getClassLoader());
        googleSyncIdForInsertion = parcel.readString();
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            calendarDescriptor = new com.google.android.calendar.api.common.FieldModification._cls1((CalendarDescriptor)parcel.readParcelable(com/google/android/calendar/api/calendarlist/CalendarDescriptor.getClassLoader()));
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            CalendarListEntry calendarlistentry = (CalendarListEntry)parcel.readParcelable(com/google/android/calendar/api/calendarlist/CalendarListEntry.getClassLoader());
            if (calendarlistentry == null)
            {
                throw new NullPointerException();
            }
            calendarListEntry = new com.google.android.calendar.api.common.FieldModification._cls1((CalendarListEntry)calendarlistentry);
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            summary = new com.google.android.calendar.api.common.FieldModification._cls1(parcel.readString());
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            isAllDay = new com.google.android.calendar.api.common.FieldModification._cls1((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader()));
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            setStartMillis(parcel.readLong());
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            setEndMillis(parcel.readLong());
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            isEndTimeUnspecified = new com.google.android.calendar.api.common.FieldModification._cls1((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader()));
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            String s = parcel.readString();
            boolean flag;
            if (s == null || TimeZoneHelper.isValidTimeZoneId(s))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException();
            }
            timeZoneId = new com.google.android.calendar.api.common.FieldModification._cls1(s);
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            String s1 = parcel.readString();
            boolean flag1;
            if (s1 == null || TimeZoneHelper.isValidTimeZoneId(s1))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                throw new IllegalArgumentException();
            }
            endTimeZoneId = new com.google.android.calendar.api.common.FieldModification._cls1(s1);
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            setRecurrence((Recurrence)parcel.readParcelable(com/google/android/calendar/api/event/time/Recurrence.getClassLoader()));
        }
        habitInstanceModifications = (HabitInstanceModifications)parcel.readParcelable(com/google/android/calendar/api/habit/HabitInstance.getClassLoader());
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            colorOverride = new com.google.android.calendar.api.common.FieldModification._cls1((EventColor)parcel.readParcelable(com/google/android/calendar/api/color/EventColor.getClassLoader()));
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            visibility = new com.google.android.calendar.api.common.FieldModification._cls1(Integer.valueOf(EventUtil.checkVisibility(parcel.readInt())));
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            availability = new com.google.android.calendar.api.common.FieldModification._cls1(Integer.valueOf(EventUtil.checkAvailability(parcel.readInt())));
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            description = new com.google.android.calendar.api.common.FieldModification._cls1(parcel.readString());
        }
        locationModifications = (StructuredLocationModifications)parcel.readParcelable(com/google/android/calendar/api/event/location/StructuredLocationModifications.getClassLoader());
        notificationModifications = (NotificationModificationsImpl)parcel.readParcelable(com/google/android/calendar/api/event/notification/NotificationModificationsImpl.getClassLoader());
        attachmentModifications = (AttachmentModificationsImpl)parcel.readParcelable(com/google/android/calendar/api/event/attachment/AttachmentModificationsImpl.getClassLoader());
        attendeeModifications = (AttendeeModificationsImpl)parcel.readParcelable(com/google/android/calendar/api/event/attendee/AttendeeModificationsImpl.getClassLoader());
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            canAttendeesAddAttendees = new com.google.android.calendar.api.common.FieldModification._cls1((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader()));
        }
        conferenceDataModifications = (ConferenceDataModifications)parcel.readParcelable(com/google/android/calendar/api/event/conference/ConferenceDataModificationsImpl.getClassLoader());
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            participantStatus = new com.google.android.calendar.api.common.FieldModification._cls1((UserStatus)parcel.readParcelable(com/google/android/calendar/api/event/userstatus/UserStatus.getClassLoader()));
        }
        icsImportOrUpdate = ((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue();
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            iCalUid = new com.google.android.calendar.api.common.FieldModification._cls1(parcel.readString());
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            sequenceNumber = new com.google.android.calendar.api.common.FieldModification._cls1(Integer.valueOf(parcel.readInt()));
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            iCalDtStamp = new com.google.android.calendar.api.common.FieldModification._cls1(parcel.readString());
        }
        googlePrivateDataFieldModification = (GooglePrivateDataModification)parcel.readParcelable(com/google/android/calendar/api/event/GooglePrivateDataModificationImpl.getClassLoader());
        forceNewEvent = ((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue();
    }

    public EventModificationsImpl(CalendarListEntry calendarlistentry)
    {
        calendarDescriptor = new FieldModification();
        calendarListEntry = new FieldModification();
        summary = new FieldModification();
        startMillis = new FieldModification();
        endMillis = new FieldModification();
        isAllDay = new FieldModification();
        isEndTimeUnspecified = new FieldModification();
        timeZoneId = new FieldModification();
        endTimeZoneId = new FieldModification();
        recurrence = new FieldModification();
        colorOverride = new FieldModification();
        visibility = new FieldModification();
        availability = new FieldModification();
        description = new FieldModification();
        canAttendeesAddAttendees = new FieldModification();
        participantStatus = new FieldModification();
        icsOrganizerEmail = null;
        iCalUid = new FieldModification();
        sequenceNumber = new FieldModification();
        iCalDtStamp = new FieldModification();
        original = null;
        descriptor = UncommittedEventDescriptor.INSTANCE;
        if (calendarlistentry == null)
        {
            throw new NullPointerException();
        }
        calendarListEntry = new com.google.android.calendar.api.common.FieldModification._cls1((CalendarListEntry)calendarlistentry);
        CalendarDescriptor calendardescriptor = calendarlistentry.getDescriptor();
        if (calendardescriptor == null)
        {
            throw new NullPointerException();
        }
        calendarDescriptor = new com.google.android.calendar.api.common.FieldModification._cls1((CalendarDescriptor)calendardescriptor);
        startMillis = new com.google.android.calendar.api.common.FieldModification._cls1(Long.valueOf(0L));
        endMillis = new com.google.android.calendar.api.common.FieldModification._cls1(Long.valueOf(0L));
        isAllDay = new com.google.android.calendar.api.common.FieldModification._cls1(Boolean.valueOf(false));
        isEndTimeUnspecified = new com.google.android.calendar.api.common.FieldModification._cls1(Boolean.valueOf(false));
        locationModifications = new StructuredLocationModificationsImpl(EMPTY_LOCATION);
        notificationModifications = new NotificationModificationsImpl(null);
        attachmentModifications = new AttachmentModificationsImpl(Collections.emptyList());
        attendeeModifications = new AttendeeModificationsImpl(ImmutableList.of());
        canAttendeesAddAttendees = new com.google.android.calendar.api.common.FieldModification._cls1(Boolean.valueOf(true));
        conferenceDataModifications = new ConferenceDataModificationsImpl(ConferenceData.create(Collections.emptyList()));
        if (AccountUtil.isGoogleAccount(calendarlistentry.getDescriptor().account))
        {
            googlePrivateDataFieldModification = new GooglePrivateDataModificationImpl();
        }
    }

    public EventModificationsImpl(Event event)
    {
        calendarDescriptor = new FieldModification();
        calendarListEntry = new FieldModification();
        summary = new FieldModification();
        startMillis = new FieldModification();
        endMillis = new FieldModification();
        isAllDay = new FieldModification();
        isEndTimeUnspecified = new FieldModification();
        timeZoneId = new FieldModification();
        endTimeZoneId = new FieldModification();
        recurrence = new FieldModification();
        colorOverride = new FieldModification();
        visibility = new FieldModification();
        availability = new FieldModification();
        description = new FieldModification();
        canAttendeesAddAttendees = new FieldModification();
        participantStatus = new FieldModification();
        icsOrganizerEmail = null;
        iCalUid = new FieldModification();
        sequenceNumber = new FieldModification();
        iCalDtStamp = new FieldModification();
        if (event == null)
        {
            throw new NullPointerException();
        }
        original = (Event)event;
        descriptor = original.getDescriptor();
        if (event.getHabitInstance().isSupported())
        {
            habitInstanceModifications = new HabitInstanceModificationsImpl((HabitInstance)event.getHabitInstance().getValue());
        }
        locationModifications = new StructuredLocationModificationsImpl(original.getLocation());
        notificationModifications = new NotificationModificationsImpl(original.getNotifications());
        attachmentModifications = new AttachmentModificationsImpl(original.getAttachments());
        attendeeModifications = new AttendeeModificationsImpl(original.getAttendees());
        conferenceDataModifications = new ConferenceDataModificationsImpl(event.getConferenceData());
        CalendarListEntry calendarlistentry;
        if (calendarListEntry.shouldModify())
        {
            calendarlistentry = (CalendarListEntry)calendarListEntry.getModificationValue();
        } else
        {
            calendarlistentry = original.getCalendarListEntry();
        }
        if (AccountUtil.isGoogleAccount(calendarlistentry.getDescriptor().account))
        {
            googlePrivateDataFieldModification = new GooglePrivateDataModificationImpl(event.getGooglePrivateData());
        }
    }

    EventModificationsImpl(Habit habit, CalendarListEntry calendarlistentry)
    {
        this(calendarlistentry);
        if (!habit.getDescriptor().calendar.matches(calendarlistentry.getDescriptor()))
        {
            throw new IllegalArgumentException();
        } else
        {
            habitInstanceModifications = new HabitInstanceModificationsImpl(habit.getDescriptor(), habit.getType());
            summary = new com.google.android.calendar.api.common.FieldModification._cls1(habit.getSummary());
            colorOverride = new com.google.android.calendar.api.common.FieldModification._cls1(habit.getColorOverride());
            visibility = new com.google.android.calendar.api.common.FieldModification._cls1(Integer.valueOf(habit.getVisibility()));
            googlePrivateDataFieldModification = new GooglePrivateDataModificationImpl();
            return;
        }
    }

    public final boolean canAttendeesAddAttendees()
    {
        if (canAttendeesAddAttendees.shouldModify())
        {
            return ((Boolean)canAttendeesAddAttendees.getModificationValue()).booleanValue();
        }
        if (original != null)
        {
            return original.canAttendeesAddAttendees();
        } else
        {
            return true;
        }
    }

    public final boolean canAttendeesModify()
    {
        if (original == null)
        {
            return false;
        } else
        {
            return original.canAttendeesModify();
        }
    }

    public final boolean canAttendeesSeeAttendees()
    {
        if (original == null)
        {
            return true;
        } else
        {
            return original.canAttendeesSeeAttendees();
        }
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof EventModificationsImpl)
        {
            obj = (EventModificationsImpl)obj;
            Event event = original;
            Event event1 = ((EventModificationsImpl) (obj)).original;
            boolean flag;
            if (event == event1 || event != null && event.equals(event1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                EventDescriptor eventdescriptor = descriptor;
                EventDescriptor eventdescriptor1 = ((EventModificationsImpl) (obj)).descriptor;
                if (eventdescriptor == eventdescriptor1 || eventdescriptor != null && eventdescriptor.equals(eventdescriptor1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    String s = googleSyncIdForInsertion;
                    String s2 = ((EventModificationsImpl) (obj)).googleSyncIdForInsertion;
                    if (s == s2 || s != null && s.equals(s2))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        FieldModification fieldmodification = calendarListEntry;
                        FieldModification fieldmodification18 = ((EventModificationsImpl) (obj)).calendarListEntry;
                        if (fieldmodification == fieldmodification18 || fieldmodification != null && fieldmodification.equals(fieldmodification18))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            FieldModification fieldmodification1 = summary;
                            FieldModification fieldmodification19 = ((EventModificationsImpl) (obj)).summary;
                            if (fieldmodification1 == fieldmodification19 || fieldmodification1 != null && fieldmodification1.equals(fieldmodification19))
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (flag)
                            {
                                FieldModification fieldmodification2 = startMillis;
                                FieldModification fieldmodification20 = ((EventModificationsImpl) (obj)).startMillis;
                                if (fieldmodification2 == fieldmodification20 || fieldmodification2 != null && fieldmodification2.equals(fieldmodification20))
                                {
                                    flag = true;
                                } else
                                {
                                    flag = false;
                                }
                                if (flag)
                                {
                                    FieldModification fieldmodification3 = endMillis;
                                    FieldModification fieldmodification21 = ((EventModificationsImpl) (obj)).endMillis;
                                    if (fieldmodification3 == fieldmodification21 || fieldmodification3 != null && fieldmodification3.equals(fieldmodification21))
                                    {
                                        flag = true;
                                    } else
                                    {
                                        flag = false;
                                    }
                                    if (flag)
                                    {
                                        FieldModification fieldmodification4 = isAllDay;
                                        FieldModification fieldmodification22 = ((EventModificationsImpl) (obj)).isAllDay;
                                        if (fieldmodification4 == fieldmodification22 || fieldmodification4 != null && fieldmodification4.equals(fieldmodification22))
                                        {
                                            flag = true;
                                        } else
                                        {
                                            flag = false;
                                        }
                                        if (flag)
                                        {
                                            FieldModification fieldmodification5 = isEndTimeUnspecified;
                                            FieldModification fieldmodification23 = ((EventModificationsImpl) (obj)).isEndTimeUnspecified;
                                            if (fieldmodification5 == fieldmodification23 || fieldmodification5 != null && fieldmodification5.equals(fieldmodification23))
                                            {
                                                flag = true;
                                            } else
                                            {
                                                flag = false;
                                            }
                                            if (flag)
                                            {
                                                FieldModification fieldmodification6 = timeZoneId;
                                                FieldModification fieldmodification24 = ((EventModificationsImpl) (obj)).timeZoneId;
                                                if (fieldmodification6 == fieldmodification24 || fieldmodification6 != null && fieldmodification6.equals(fieldmodification24))
                                                {
                                                    flag = true;
                                                } else
                                                {
                                                    flag = false;
                                                }
                                                if (flag)
                                                {
                                                    FieldModification fieldmodification7 = endTimeZoneId;
                                                    FieldModification fieldmodification25 = ((EventModificationsImpl) (obj)).endTimeZoneId;
                                                    if (fieldmodification7 == fieldmodification25 || fieldmodification7 != null && fieldmodification7.equals(fieldmodification25))
                                                    {
                                                        flag = true;
                                                    } else
                                                    {
                                                        flag = false;
                                                    }
                                                    if (flag)
                                                    {
                                                        FieldModification fieldmodification8 = recurrence;
                                                        FieldModification fieldmodification26 = ((EventModificationsImpl) (obj)).recurrence;
                                                        if (fieldmodification8 == fieldmodification26 || fieldmodification8 != null && fieldmodification8.equals(fieldmodification26))
                                                        {
                                                            flag = true;
                                                        } else
                                                        {
                                                            flag = false;
                                                        }
                                                        if (flag)
                                                        {
                                                            HabitInstanceModifications habitinstancemodifications = habitInstanceModifications;
                                                            HabitInstanceModifications habitinstancemodifications1 = ((EventModificationsImpl) (obj)).habitInstanceModifications;
                                                            if (habitinstancemodifications == habitinstancemodifications1 || habitinstancemodifications != null && habitinstancemodifications.equals(habitinstancemodifications1))
                                                            {
                                                                flag = true;
                                                            } else
                                                            {
                                                                flag = false;
                                                            }
                                                            if (flag)
                                                            {
                                                                FieldModification fieldmodification9 = colorOverride;
                                                                FieldModification fieldmodification27 = ((EventModificationsImpl) (obj)).colorOverride;
                                                                if (fieldmodification9 == fieldmodification27 || fieldmodification9 != null && fieldmodification9.equals(fieldmodification27))
                                                                {
                                                                    flag = true;
                                                                } else
                                                                {
                                                                    flag = false;
                                                                }
                                                                if (flag)
                                                                {
                                                                    FieldModification fieldmodification10 = visibility;
                                                                    FieldModification fieldmodification28 = ((EventModificationsImpl) (obj)).visibility;
                                                                    if (fieldmodification10 == fieldmodification28 || fieldmodification10 != null && fieldmodification10.equals(fieldmodification28))
                                                                    {
                                                                        flag = true;
                                                                    } else
                                                                    {
                                                                        flag = false;
                                                                    }
                                                                    if (flag)
                                                                    {
                                                                        FieldModification fieldmodification11 = availability;
                                                                        FieldModification fieldmodification29 = ((EventModificationsImpl) (obj)).availability;
                                                                        if (fieldmodification11 == fieldmodification29 || fieldmodification11 != null && fieldmodification11.equals(fieldmodification29))
                                                                        {
                                                                            flag = true;
                                                                        } else
                                                                        {
                                                                            flag = false;
                                                                        }
                                                                        if (flag)
                                                                        {
                                                                            FieldModification fieldmodification12 = description;
                                                                            FieldModification fieldmodification30 = ((EventModificationsImpl) (obj)).description;
                                                                            if (fieldmodification12 == fieldmodification30 || fieldmodification12 != null && fieldmodification12.equals(fieldmodification30))
                                                                            {
                                                                                flag = true;
                                                                            } else
                                                                            {
                                                                                flag = false;
                                                                            }
                                                                            if (flag)
                                                                            {
                                                                                StructuredLocationModifications structuredlocationmodifications = locationModifications;
                                                                                StructuredLocationModifications structuredlocationmodifications1 = ((EventModificationsImpl) (obj)).locationModifications;
                                                                                if (structuredlocationmodifications == structuredlocationmodifications1 || structuredlocationmodifications != null && structuredlocationmodifications.equals(structuredlocationmodifications1))
                                                                                {
                                                                                    flag = true;
                                                                                } else
                                                                                {
                                                                                    flag = false;
                                                                                }
                                                                                if (flag)
                                                                                {
                                                                                    NotificationModificationsImpl notificationmodificationsimpl = notificationModifications;
                                                                                    NotificationModificationsImpl notificationmodificationsimpl1 = ((EventModificationsImpl) (obj)).notificationModifications;
                                                                                    if (notificationmodificationsimpl == notificationmodificationsimpl1 || notificationmodificationsimpl != null && notificationmodificationsimpl.equals(notificationmodificationsimpl1))
                                                                                    {
                                                                                        flag = true;
                                                                                    } else
                                                                                    {
                                                                                        flag = false;
                                                                                    }
                                                                                    if (flag)
                                                                                    {
                                                                                        AttachmentModificationsImpl attachmentmodificationsimpl = attachmentModifications;
                                                                                        AttachmentModificationsImpl attachmentmodificationsimpl1 = ((EventModificationsImpl) (obj)).attachmentModifications;
                                                                                        if (attachmentmodificationsimpl == attachmentmodificationsimpl1 || attachmentmodificationsimpl != null && attachmentmodificationsimpl.equals(attachmentmodificationsimpl1))
                                                                                        {
                                                                                            flag = true;
                                                                                        } else
                                                                                        {
                                                                                            flag = false;
                                                                                        }
                                                                                        if (flag)
                                                                                        {
                                                                                            AttendeeModificationsImpl attendeemodificationsimpl = attendeeModifications;
                                                                                            AttendeeModificationsImpl attendeemodificationsimpl1 = ((EventModificationsImpl) (obj)).attendeeModifications;
                                                                                            if (attendeemodificationsimpl == attendeemodificationsimpl1 || attendeemodificationsimpl != null && attendeemodificationsimpl.equals(attendeemodificationsimpl1))
                                                                                            {
                                                                                                flag = true;
                                                                                            } else
                                                                                            {
                                                                                                flag = false;
                                                                                            }
                                                                                            if (flag)
                                                                                            {
                                                                                                FieldModification fieldmodification13 = canAttendeesAddAttendees;
                                                                                                FieldModification fieldmodification31 = ((EventModificationsImpl) (obj)).canAttendeesAddAttendees;
                                                                                                if (fieldmodification13 == fieldmodification31 || fieldmodification13 != null && fieldmodification13.equals(fieldmodification31))
                                                                                                {
                                                                                                    flag = true;
                                                                                                } else
                                                                                                {
                                                                                                    flag = false;
                                                                                                }
                                                                                                if (flag)
                                                                                                {
                                                                                                    ConferenceDataModifications conferencedatamodifications = conferenceDataModifications;
                                                                                                    ConferenceDataModifications conferencedatamodifications1 = ((EventModificationsImpl) (obj)).conferenceDataModifications;
                                                                                                    if (conferencedatamodifications == conferencedatamodifications1 || conferencedatamodifications != null && conferencedatamodifications.equals(conferencedatamodifications1))
                                                                                                    {
                                                                                                        flag = true;
                                                                                                    } else
                                                                                                    {
                                                                                                        flag = false;
                                                                                                    }
                                                                                                    if (flag)
                                                                                                    {
                                                                                                        FieldModification fieldmodification14 = participantStatus;
                                                                                                        FieldModification fieldmodification32 = ((EventModificationsImpl) (obj)).participantStatus;
                                                                                                        if (fieldmodification14 == fieldmodification32 || fieldmodification14 != null && fieldmodification14.equals(fieldmodification32))
                                                                                                        {
                                                                                                            flag = true;
                                                                                                        } else
                                                                                                        {
                                                                                                            flag = false;
                                                                                                        }
                                                                                                        if (flag)
                                                                                                        {
                                                                                                            String s1 = icsOrganizerEmail;
                                                                                                            String s3 = ((EventModificationsImpl) (obj)).icsOrganizerEmail;
                                                                                                            if (s1 == s3 || s1 != null && s1.equals(s3))
                                                                                                            {
                                                                                                                flag = true;
                                                                                                            } else
                                                                                                            {
                                                                                                                flag = false;
                                                                                                            }
                                                                                                            if (flag)
                                                                                                            {
                                                                                                                FieldModification fieldmodification15 = iCalUid;
                                                                                                                FieldModification fieldmodification33 = ((EventModificationsImpl) (obj)).iCalUid;
                                                                                                                if (fieldmodification15 == fieldmodification33 || fieldmodification15 != null && fieldmodification15.equals(fieldmodification33))
                                                                                                                {
                                                                                                                    flag = true;
                                                                                                                } else
                                                                                                                {
                                                                                                                    flag = false;
                                                                                                                }
                                                                                                                if (flag)
                                                                                                                {
                                                                                                                    FieldModification fieldmodification16 = sequenceNumber;
                                                                                                                    FieldModification fieldmodification34 = ((EventModificationsImpl) (obj)).sequenceNumber;
                                                                                                                    if (fieldmodification16 == fieldmodification34 || fieldmodification16 != null && fieldmodification16.equals(fieldmodification34))
                                                                                                                    {
                                                                                                                        flag = true;
                                                                                                                    } else
                                                                                                                    {
                                                                                                                        flag = false;
                                                                                                                    }
                                                                                                                    if (flag)
                                                                                                                    {
                                                                                                                        FieldModification fieldmodification17 = iCalDtStamp;
                                                                                                                        FieldModification fieldmodification35 = ((EventModificationsImpl) (obj)).iCalDtStamp;
                                                                                                                        if (fieldmodification17 == fieldmodification35 || fieldmodification17 != null && fieldmodification17.equals(fieldmodification35))
                                                                                                                        {
                                                                                                                            flag = true;
                                                                                                                        } else
                                                                                                                        {
                                                                                                                            flag = false;
                                                                                                                        }
                                                                                                                        if (flag)
                                                                                                                        {
                                                                                                                            GooglePrivateDataModification googleprivatedatamodification = googlePrivateDataFieldModification;
                                                                                                                            obj = ((EventModificationsImpl) (obj)).googlePrivateDataFieldModification;
                                                                                                                            if (googleprivatedatamodification == obj || googleprivatedatamodification != null && googleprivatedatamodification.equals(obj))
                                                                                                                            {
                                                                                                                                flag = true;
                                                                                                                            } else
                                                                                                                            {
                                                                                                                                flag = false;
                                                                                                                            }
                                                                                                                            if (flag)
                                                                                                                            {
                                                                                                                                return true;
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final AttachmentModifications getAttachmentModifications()
    {
        return attachmentModifications;
    }

    public final ImmutableList getAttachments()
    {
        return ImmutableList.copyOf(attachmentModifications.attachments);
    }

    public final AttendeeModifications getAttendeeModifications()
    {
        return attendeeModifications;
    }

    public final ImmutableList getAttendees()
    {
        AttendeeModificationsImpl attendeemodificationsimpl = attendeeModifications;
        if (attendeemodificationsimpl.isModified())
        {
            return ImmutableList.copyOf(attendeemodificationsimpl.attendees);
        } else
        {
            return attendeemodificationsimpl.original;
        }
    }

    public final int getAvailability()
    {
        if (availability.shouldModify())
        {
            return EventUtil.checkAvailability(((Integer)availability.getModificationValue()).intValue());
        }
        if (original != null)
        {
            return original.getAvailability();
        } else
        {
            return 0;
        }
    }

    public final CalendarDescriptor getCalendar()
    {
        if (calendarDescriptor.shouldModify())
        {
            return (CalendarDescriptor)calendarDescriptor.getModificationValue();
        } else
        {
            return original.getCalendar();
        }
    }

    public final CalendarListEntry getCalendarListEntry()
    {
        if (calendarListEntry.shouldModify())
        {
            return (CalendarListEntry)calendarListEntry.getModificationValue();
        } else
        {
            return original.getCalendarListEntry();
        }
    }

    public final EntityColor getColor()
    {
        if (getColorOverride() == null)
        {
            return getCalendarListEntry().getColor();
        } else
        {
            return getColorOverride();
        }
    }

    public final EventColor getColorOverride()
    {
        if (colorOverride.shouldModify())
        {
            return (EventColor)colorOverride.getModificationValue();
        }
        if (original != null)
        {
            return original.getColorOverride();
        } else
        {
            return null;
        }
    }

    public final ConferenceData getConferenceData()
    {
        return conferenceDataModifications;
    }

    public final ConferenceDataModifications getConferenceDataModifications()
    {
        return conferenceDataModifications;
    }

    public final String getCustomAppPackage()
    {
        if (original != null)
        {
            return original.getCustomAppPackage();
        } else
        {
            return "";
        }
    }

    public final String getCustomAppUri()
    {
        if (original != null)
        {
            return original.getCustomAppUri();
        } else
        {
            return "";
        }
    }

    public final String getDescription()
    {
        if (description.shouldModify())
        {
            return (String)description.getModificationValue();
        }
        if (original != null)
        {
            return original.getDescription();
        } else
        {
            return null;
        }
    }

    public final EventDescriptor getDescriptor()
    {
        if (forceNewEvent)
        {
            return UncommittedEventDescriptor.INSTANCE;
        } else
        {
            return descriptor;
        }
    }

    public final long getEndMillis()
    {
        if (endMillis.shouldModify())
        {
            return ((Long)endMillis.getModificationValue()).longValue();
        }
        if (original != null)
        {
            return original.getEndMillis();
        } else
        {
            return 0L;
        }
    }

    public final String getEndTimeZoneId()
    {
        if (endTimeZoneId.shouldModify())
        {
            return (String)endTimeZoneId.getModificationValue();
        }
        if (original != null)
        {
            return original.getEndTimeZoneId();
        } else
        {
            return null;
        }
    }

    public final GooglePrivateData getGooglePrivateData()
    {
        if (googlePrivateDataFieldModification == null)
        {
            return null;
        } else
        {
            return googlePrivateDataFieldModification.get();
        }
    }

    public final GooglePrivateDataModification getGooglePrivateDataModification()
    {
        return googlePrivateDataFieldModification;
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

    public final String getGoogleSyncIdForInsertion()
    {
        CalendarDescriptor calendardescriptor = getCalendar();
        if (calendardescriptor == null || !AccountUtil.isGoogleAccount(calendardescriptor.account) || isIcsImportOrUpdate())
        {
            return null;
        }
        if (googleSyncIdForInsertion == null)
        {
            BaseEncoding baseencoding = BaseEncoding.BASE32_HEX.lowerCase().omitPadding();
            byte abyte0[] = UUID.randomUUID().toString().getBytes();
            googleSyncIdForInsertion = baseencoding.encode(abyte0, 0, abyte0.length);
        }
        return googleSyncIdForInsertion;
    }

    public final Feature getHabitInstance()
    {
        if (habitInstanceModifications == null)
        {
            return new Feature();
        } else
        {
            return new com.google.android.calendar.api.common.Feature._cls1(habitInstanceModifications);
        }
    }

    public final Feature getHabitInstanceModifications()
    {
        if (habitInstanceModifications == null)
        {
            return new Feature();
        } else
        {
            return new com.google.android.calendar.api.common.Feature._cls1(habitInstanceModifications);
        }
    }

    public final String getICalDtStamp()
    {
        if (iCalDtStamp.shouldModify())
        {
            return (String)iCalDtStamp.getModificationValue();
        }
        if (original != null)
        {
            return original.getICalDtStamp();
        } else
        {
            return null;
        }
    }

    public final String getICalUid()
    {
        if (iCalUid.shouldModify())
        {
            return (String)iCalUid.getModificationValue();
        }
        if (original != null)
        {
            return original.getICalUid();
        } else
        {
            return null;
        }
    }

    public final StructuredLocation getLocation()
    {
        return locationModifications;
    }

    public final StructuredLocationModifications getLocationModification()
    {
        return locationModifications;
    }

    public final NotificationModifications getNotificationModifications()
    {
        return notificationModifications;
    }

    public final List getNotifications()
    {
        return notificationModifications.getNotifications();
    }

    public final AttendeeDescriptor getOrganizer()
    {
        if (isIcsImportOrUpdate())
        {
            String s;
            if (icsOrganizerEmail != null)
            {
                s = icsOrganizerEmail;
            } else
            {
                s = getCalendarListEntry().getDescriptor().calendarId;
            }
            return new AttendeeDescriptor(s);
        }
        if (calendarListEntry.shouldModify())
        {
            return new AttendeeDescriptor(getCalendarListEntry().getDescriptor().calendarId);
        } else
        {
            return original.getOrganizer();
        }
    }

    public final Event getOriginal()
    {
        return original;
    }

    public final UserStatus getParticipantStatus()
    {
        if (participantStatus.shouldModify())
        {
            return (UserStatus)participantStatus.getModificationValue();
        }
        if (original != null)
        {
            return original.getParticipantStatus();
        } else
        {
            return null;
        }
    }

    public final Recurrence getRecurrence()
    {
        if (recurrence.shouldModify())
        {
            return (Recurrence)recurrence.getModificationValue();
        }
        if (original != null)
        {
            return original.getRecurrence();
        } else
        {
            return null;
        }
    }

    public final long getRecurringFirstStartMillis()
    {
        boolean flag;
        if (getRecurrence() != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Event is not recurring."));
        }
        if (original != null && original.isRecurring())
        {
            return original.getRecurringFirstStartMillis();
        } else
        {
            return getStartMillis();
        }
    }

    public final EventResponseSummary getResponseSummary()
    {
        if (original != null)
        {
            return original.getResponseSummary();
        } else
        {
            return null;
        }
    }

    public final int getSequenceNumber()
    {
        if (sequenceNumber.shouldModify())
        {
            return ((Integer)sequenceNumber.getModificationValue()).intValue();
        }
        if (isNewEvent())
        {
            return 0;
        } else
        {
            return original.getSequenceNumber();
        }
    }

    public final SmartMailInfo getSmartMailInfo()
    {
        if (original != null)
        {
            return original.getSmartMailInfo();
        } else
        {
            return null;
        }
    }

    public final EventSource getSource()
    {
        if (original != null)
        {
            return original.getSource();
        } else
        {
            return null;
        }
    }

    public final long getStartMillis()
    {
        if (startMillis.shouldModify())
        {
            return ((Long)startMillis.getModificationValue()).longValue();
        }
        if (original != null)
        {
            return original.getStartMillis();
        } else
        {
            return 0L;
        }
    }

    public final int getStatus()
    {
        if (isNewEvent())
        {
            return 0;
        } else
        {
            return original.getStatus();
        }
    }

    public final String getSummary()
    {
        if (summary.shouldModify())
        {
            return (String)summary.getModificationValue();
        }
        if (original != null)
        {
            return original.getSummary();
        } else
        {
            return null;
        }
    }

    public final String getSyncId()
    {
        if (original == null)
        {
            return getGoogleSyncIdForInsertion();
        } else
        {
            return original.getSyncId();
        }
    }

    public final String getTimeZoneId()
    {
        if (timeZoneId.shouldModify())
        {
            return (String)timeZoneId.getModificationValue();
        }
        if (original != null)
        {
            return original.getTimeZoneId();
        } else
        {
            return null;
        }
    }

    public final int getVisibility()
    {
        if (visibility.shouldModify())
        {
            return EventUtil.checkVisibility(((Integer)visibility.getModificationValue()).intValue());
        }
        if (original != null)
        {
            return original.getVisibility();
        } else
        {
            return 0;
        }
    }

    public final boolean hasLocalChanges()
    {
        return isModified() || original == null || original.hasLocalChanges();
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            original, descriptor, googleSyncIdForInsertion, calendarListEntry, summary, startMillis, endMillis, isAllDay, isEndTimeUnspecified, timeZoneId, 
            endTimeZoneId, recurrence, habitInstanceModifications, colorOverride, visibility, availability, description, locationModifications, attachmentModifications, attendeeModifications, 
            canAttendeesAddAttendees, conferenceDataModifications, participantStatus, icsOrganizerEmail, iCalUid, sequenceNumber, iCalDtStamp, googlePrivateDataFieldModification
        });
    }

    public final boolean isAllDayEvent()
    {
        if (isAllDay.shouldModify())
        {
            return ((Boolean)isAllDay.getModificationValue()).booleanValue();
        }
        if (original != null)
        {
            return original.isAllDayEvent();
        } else
        {
            return false;
        }
    }

    public final boolean isAllDayModified()
    {
        return isAllDay.shouldModify();
    }

    public final boolean isAttendeesOmitted()
    {
        if (original != null)
        {
            return original.isAttendeesOmitted();
        } else
        {
            return false;
        }
    }

    public final boolean isAvailabilityModified()
    {
        return availability.shouldModify();
    }

    public final boolean isCanAttendeesAddAttendeesModified()
    {
        return canAttendeesAddAttendees.shouldModify();
    }

    public final boolean isColorOverrideModified()
    {
        return colorOverride.shouldModify();
    }

    public final boolean isDescriptionModified()
    {
        return description.shouldModify();
    }

    public final boolean isEndModified()
    {
        return endMillis.shouldModify();
    }

    public final boolean isEndTimeUnspecified()
    {
        if (isEndTimeUnspecified.shouldModify())
        {
            return ((Boolean)isEndTimeUnspecified.getModificationValue()).booleanValue();
        }
        if (original != null)
        {
            return original.isEndTimeUnspecified();
        } else
        {
            return false;
        }
    }

    public final boolean isEndTimeUnspecifiedModified()
    {
        return isEndTimeUnspecified.shouldModify();
    }

    public final boolean isEndTimeZoneModified()
    {
        return endTimeZoneId.shouldModify();
    }

    public final boolean isHabitInstance()
    {
        Object obj;
        if (habitInstanceModifications == null)
        {
            obj = new Feature();
        } else
        {
            obj = new com.google.android.calendar.api.common.Feature._cls1(habitInstanceModifications);
        }
        return ((Feature) (obj)).isSupported();
    }

    public final boolean isIcsImportOrUpdate()
    {
        return icsImportOrUpdate || (original instanceof EventModifications) && ((EventModifications)original).isIcsImportOrUpdate();
    }

    public final boolean isModified()
    {
label0:
        {
            boolean flag2 = false;
            Object obj;
            boolean flag;
            boolean flag1;
            if (habitInstanceModifications == null)
            {
                obj = new Feature();
            } else
            {
                obj = new com.google.android.calendar.api.common.Feature._cls1(habitInstanceModifications);
            }
            if (((Feature) (obj)).isSupported() && ((HabitInstanceModifications)((Feature) (obj)).getValue()).isModified())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!colorOverride.shouldModify() && !visibility.shouldModify() && !availability.shouldModify() && !notificationModifications.isModified() && !participantStatus.shouldModify() && !attendeeModifications.isModified() && !summary.shouldModify() && !startMillis.shouldModify() && !endMillis.shouldModify() && !isAllDay.shouldModify() && !isEndTimeUnspecified.shouldModify() && !timeZoneId.shouldModify() && !flag && !endTimeZoneId.shouldModify() && !recurrence.shouldModify() && !description.shouldModify() && !locationModifications.isModified() && !attachmentModifications.isModified() && !conferenceDataModifications.isModified() && !canAttendeesAddAttendees.shouldModify())
            {
                flag1 = flag2;
                if (googlePrivateDataFieldModification == null)
                {
                    break label0;
                }
                flag1 = flag2;
                if (!googlePrivateDataFieldModification.isModified())
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        return flag1;
    }

    public final boolean isNewEvent()
    {
        if (forceNewEvent || original == null)
        {
            return true;
        }
        if (original instanceof EventModifications)
        {
            return ((EventModifications)original).isNewEvent();
        } else
        {
            return false;
        }
    }

    public final boolean isOrganizerModified()
    {
        return calendarListEntry.shouldModify();
    }

    public final boolean isParticipantStatusModified()
    {
        return participantStatus.shouldModify();
    }

    public final boolean isRecurrenceModified()
    {
        return recurrence.shouldModify();
    }

    public final boolean isRecurring()
    {
        return getRecurrence() != null;
    }

    public final boolean isSmartMailEvent()
    {
        if (original != null)
        {
            return original.isSmartMailEvent();
        } else
        {
            return false;
        }
    }

    public final boolean isSocial()
    {
        return false;
    }

    public final boolean isStartModified()
    {
        return startMillis.shouldModify();
    }

    public final boolean isSummaryModified()
    {
        return summary.shouldModify();
    }

    public final boolean isTimeZoneModified()
    {
        return timeZoneId.shouldModify();
    }

    public final boolean isVisibilityModified()
    {
        return visibility.shouldModify();
    }

    public final EventModifications setAvailability(int i)
    {
        availability = new com.google.android.calendar.api.common.FieldModification._cls1(Integer.valueOf(i));
        return this;
    }

    public final EventModifications setCanAttendeesAddAttendees(boolean flag)
    {
        canAttendeesAddAttendees = new com.google.android.calendar.api.common.FieldModification._cls1(Boolean.valueOf(flag));
        return this;
    }

    public final EventModifications setColorOverride(EventColor eventcolor)
    {
        colorOverride = new com.google.android.calendar.api.common.FieldModification._cls1(eventcolor);
        return this;
    }

    public final EventModifications setDescription(String s)
    {
        description = new com.google.android.calendar.api.common.FieldModification._cls1(s);
        return this;
    }

    public final EventModifications setEndMillis(long l)
    {
        boolean flag;
        if (!isAllDayEvent() || TimestampUtil.isTimestampUtcMidnight(l))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            endMillis = new com.google.android.calendar.api.common.FieldModification._cls1(Long.valueOf(l));
            return this;
        }
    }

    public final EventModifications setEndTimeUnspecified(boolean flag)
    {
        isEndTimeUnspecified = new com.google.android.calendar.api.common.FieldModification._cls1(Boolean.valueOf(flag));
        return this;
    }

    public final EventModifications setEndTimeZoneId(String s)
    {
        boolean flag;
        if (s == null || TimeZoneHelper.isValidTimeZoneId(s))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            endTimeZoneId = new com.google.android.calendar.api.common.FieldModification._cls1(s);
            return this;
        }
    }

    public final EventModifications setForceNewEvent(boolean flag)
    {
        forceNewEvent = true;
        return this;
    }

    public final EventModifications setParticipantStatus(UserStatus userstatus)
    {
        participantStatus = new com.google.android.calendar.api.common.FieldModification._cls1(userstatus);
        return this;
    }

    public final EventModifications setRecurrence(Recurrence recurrence1)
    {
        if (recurrence1 != null)
        {
            if (!recurrence1.exrules.isEmpty())
            {
                throw new IllegalArgumentException(String.valueOf("EXRULEs are not allowed in API events."));
            }
            boolean flag;
            if (!recurrence1.exdates.contains(Long.valueOf(getStartMillis())))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("EXDATEs cannot contain first start time of an event."));
            }
        }
        recurrence = new com.google.android.calendar.api.common.FieldModification._cls1(recurrence1);
        return this;
    }

    public final EventModifications setStartMillis(long l)
    {
        boolean flag;
        if (!isAllDayEvent() || TimestampUtil.isTimestampUtcMidnight(l))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            startMillis = new com.google.android.calendar.api.common.FieldModification._cls1(Long.valueOf(l));
            return this;
        }
    }

    public final EventModifications setSummary(String s)
    {
        summary = new com.google.android.calendar.api.common.FieldModification._cls1(s);
        return this;
    }

    public final EventModifications setTimeZoneId(String s)
    {
        boolean flag;
        if (s == null || TimeZoneHelper.isValidTimeZoneId(s))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            timeZoneId = new com.google.android.calendar.api.common.FieldModification._cls1(s);
            return this;
        }
    }

    public final EventModifications setToAllDayWithDates(long l, long l1)
    {
        if (!TimestampUtil.isTimestampUtcMidnight(l))
        {
            throw new IllegalArgumentException();
        }
        if (!TimestampUtil.isTimestampUtcMidnight(l1))
        {
            throw new IllegalArgumentException();
        } else
        {
            setStartMillis(l);
            setEndMillis(l1);
            isAllDay = new com.google.android.calendar.api.common.FieldModification._cls1(Boolean.valueOf(true));
            return this;
        }
    }

    public final EventModifications setToTimedWithTimes(long l, long l1)
    {
        isAllDay = new com.google.android.calendar.api.common.FieldModification._cls1(Boolean.valueOf(false));
        setStartMillis(l);
        setEndMillis(l1);
        return this;
    }

    public final EventModifications setVisibility(int i)
    {
        visibility = new com.google.android.calendar.api.common.FieldModification._cls1(Integer.valueOf(i));
        return this;
    }

    public final EventDescriptor switchCalendar(CalendarListEntry calendarlistentry)
    {
        if (isNewEvent()) goto _L2; else goto _L1
_L1:
        Object obj;
        boolean flag;
        if (calendarDescriptor.shouldModify())
        {
            obj = (CalendarDescriptor)calendarDescriptor.getModificationValue();
        } else
        {
            obj = original.getCalendar();
        }
        if (obj == null) goto _L4; else goto _L3
_L3:
        obj = ((CalendarDescriptor) (obj)).calendarId;
        String s = getOrganizer().email;
        if (obj != null && ((String) (obj)).equalsIgnoreCase(s))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L4; else goto _L5
_L5:
        flag = true;
_L6:
        if (!flag)
        {
            throw new UnsupportedOperationException("Calendar can only be changed on new events or organizer copies.");
        }
        break; /* Loop/switch isn't completed */
_L4:
        flag = false;
        if (true) goto _L6; else goto _L2
_L2:
        if (calendarlistentry == null)
        {
            throw new NullPointerException();
        }
        calendarListEntry = new com.google.android.calendar.api.common.FieldModification._cls1((CalendarListEntry)calendarlistentry);
        calendarlistentry = calendarlistentry.getDescriptor();
        if (isNewEvent())
        {
            calendarDescriptor = new com.google.android.calendar.api.common.FieldModification._cls1(calendarlistentry);
        }
        if (AccountUtil.isGoogleAccount(((CalendarDescriptor) (calendarlistentry)).account)) goto _L8; else goto _L7
_L7:
        colorOverride = new com.google.android.calendar.api.common.FieldModification._cls1(null);
        googlePrivateDataFieldModification = null;
_L10:
        return descriptor;
_L8:
        if (googlePrivateDataFieldModification == null)
        {
            googlePrivateDataFieldModification = new GooglePrivateDataModificationImpl();
        }
        if (true) goto _L10; else goto _L9
_L9:
    }

    public final String toString()
    {
        Object obj = new StringBuilder("EventModificationsImpl{");
        Object obj1 = String.valueOf(original);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 10)).append("mOriginal=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(descriptor);
        obj1 = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 14)).append(", mDescriptor=").append(((String) (obj1))).toString());
        obj = String.valueOf(googleSyncIdForInsertion);
        boolean flag;
        if (((String) (obj)).length() != 0)
        {
            obj = ", mGoogleSyncIdForInsertion=".concat(((String) (obj)));
        } else
        {
            obj = new String(", mGoogleSyncIdForInsertion=");
        }
        obj = ((StringBuilder) (obj1)).append(((String) (obj)));
        obj1 = String.valueOf(calendarListEntry);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 21)).append(", mCalendarListEntry=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(summary);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 11)).append(", mSummary=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(startMillis);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 15)).append(", mStartMillis=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(endMillis);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 13)).append(", mEndMillis=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(isAllDay);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 12)).append(", mIsAllDay=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(isEndTimeUnspecified);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 24)).append(", mIsEndTimeUnspecified=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(timeZoneId);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 14)).append(", mTimeZoneId=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(endTimeZoneId);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 17)).append(", mEndTimeZoneId=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(recurrence);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 14)).append(", mRecurrence=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(habitInstanceModifications);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 30)).append(", mHabitInstanceModifications=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(colorOverride);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 17)).append(", mColorOverride=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(visibility);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 14)).append(", mVisibility=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(availability);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 16)).append(", mAvailability=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(description);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 15)).append(", mDescription=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(locationModifications);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 25)).append(", mLocationModifications=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(notificationModifications);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 29)).append(", mNotificationModifications=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(attachmentModifications);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 27)).append(", mAttachmentModifications=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(attendeeModifications);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 25)).append(", mAttendeeModifications=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(canAttendeesAddAttendees);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 28)).append(", mCanAttendeesAddAttendees=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(conferenceDataModifications);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 31)).append(", mConferenceDataModifications=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(participantStatus);
        obj1 = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 21)).append(", mParticipantStatus=").append(((String) (obj1))).toString());
        obj = String.valueOf(icsOrganizerEmail);
        if (((String) (obj)).length() != 0)
        {
            obj = ", mIcsOrganizerEmail=".concat(((String) (obj)));
        } else
        {
            obj = new String(", mIcsOrganizerEmail=");
        }
        obj = ((StringBuilder) (obj1)).append(((String) (obj)));
        flag = icsImportOrUpdate;
        obj = ((StringBuilder) (obj)).append((new StringBuilder(26)).append(", mIcsImportOrUpdate=").append(flag).toString());
        obj1 = String.valueOf(iCalUid);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 11)).append(", mICalUid=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(iCalUid);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 18)).append(", mSequenceNumber=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(iCalDtStamp);
        obj = ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 15)).append(", mICalDtStamp=").append(((String) (obj1))).toString());
        obj1 = String.valueOf(googlePrivateDataFieldModification);
        return ((StringBuilder) (obj)).append((new StringBuilder(String.valueOf(obj1).length() + 21)).append(", mGooglePrivateData=").append(((String) (obj1))).toString()).append("}").toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(original, i);
        parcel.writeParcelable(descriptor, i);
        parcel.writeString(googleSyncIdForInsertion);
        parcel.writeValue(Boolean.valueOf(calendarDescriptor.shouldModify()));
        if (calendarDescriptor.shouldModify())
        {
            parcel.writeParcelable(getCalendar(), i);
        }
        parcel.writeValue(Boolean.valueOf(calendarListEntry.shouldModify()));
        if (calendarListEntry.shouldModify())
        {
            parcel.writeParcelable(getCalendarListEntry(), i);
        }
        parcel.writeValue(Boolean.valueOf(summary.shouldModify()));
        if (summary.shouldModify())
        {
            parcel.writeString(getSummary());
        }
        parcel.writeValue(Boolean.valueOf(isAllDay.shouldModify()));
        if (isAllDay.shouldModify())
        {
            parcel.writeValue(Boolean.valueOf(isAllDayEvent()));
        }
        parcel.writeValue(Boolean.valueOf(startMillis.shouldModify()));
        if (startMillis.shouldModify())
        {
            parcel.writeLong(getStartMillis());
        }
        parcel.writeValue(Boolean.valueOf(endMillis.shouldModify()));
        if (endMillis.shouldModify())
        {
            parcel.writeLong(getEndMillis());
        }
        parcel.writeValue(Boolean.valueOf(isEndTimeUnspecified.shouldModify()));
        if (isEndTimeUnspecified.shouldModify())
        {
            parcel.writeValue(Boolean.valueOf(isEndTimeUnspecified()));
        }
        parcel.writeValue(Boolean.valueOf(timeZoneId.shouldModify()));
        if (timeZoneId.shouldModify())
        {
            parcel.writeString(getTimeZoneId());
        }
        parcel.writeValue(Boolean.valueOf(endTimeZoneId.shouldModify()));
        if (endTimeZoneId.shouldModify())
        {
            parcel.writeString(getEndTimeZoneId());
        }
        parcel.writeValue(Boolean.valueOf(recurrence.shouldModify()));
        if (recurrence.shouldModify())
        {
            parcel.writeParcelable(getRecurrence(), i);
        }
        parcel.writeParcelable(habitInstanceModifications, i);
        parcel.writeValue(Boolean.valueOf(colorOverride.shouldModify()));
        if (colorOverride.shouldModify())
        {
            parcel.writeParcelable(getColorOverride(), i);
        }
        parcel.writeValue(Boolean.valueOf(visibility.shouldModify()));
        if (visibility.shouldModify())
        {
            parcel.writeInt(getVisibility());
        }
        parcel.writeValue(Boolean.valueOf(availability.shouldModify()));
        if (availability.shouldModify())
        {
            parcel.writeInt(getAvailability());
        }
        parcel.writeValue(Boolean.valueOf(description.shouldModify()));
        if (description.shouldModify())
        {
            parcel.writeString(getDescription());
        }
        parcel.writeParcelable(locationModifications, i);
        parcel.writeParcelable(notificationModifications, i);
        parcel.writeParcelable(attachmentModifications, i);
        parcel.writeParcelable(attendeeModifications, i);
        parcel.writeValue(Boolean.valueOf(canAttendeesAddAttendees.shouldModify()));
        if (canAttendeesAddAttendees.shouldModify())
        {
            parcel.writeValue(Boolean.valueOf(canAttendeesAddAttendees()));
        }
        parcel.writeParcelable(conferenceDataModifications, i);
        parcel.writeValue(Boolean.valueOf(participantStatus.shouldModify()));
        if (participantStatus.shouldModify())
        {
            parcel.writeParcelable(getParticipantStatus(), i);
        }
        parcel.writeValue(Boolean.valueOf(icsImportOrUpdate));
        parcel.writeValue(Boolean.valueOf(iCalUid.shouldModify()));
        if (iCalUid.shouldModify())
        {
            parcel.writeString((String)iCalUid.getModificationValue());
        }
        parcel.writeValue(Boolean.valueOf(sequenceNumber.shouldModify()));
        if (sequenceNumber.shouldModify())
        {
            parcel.writeInt(((Integer)sequenceNumber.getModificationValue()).intValue());
        }
        parcel.writeValue(Boolean.valueOf(iCalDtStamp.shouldModify()));
        if (iCalDtStamp.shouldModify())
        {
            parcel.writeString((String)iCalDtStamp.getModificationValue());
        }
        parcel.writeParcelable(googlePrivateDataFieldModification, i);
        parcel.writeValue(Boolean.valueOf(forceNewEvent));
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new EventModificationsImpl(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new EventModificationsImpl[i];
        }

        _cls1()
        {
        }
    }

}
