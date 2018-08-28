// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.color.EventColor;
import com.google.android.calendar.api.common.CalendarProviderHelper;
import com.google.android.calendar.api.common.ContentProviderOperator;
import com.google.android.calendar.api.common.ExtendedPropertiesInsert;
import com.google.android.calendar.api.common.ExtendedPropertiesUtils;
import com.google.android.calendar.api.common.ExtendedPropertyPair;
import com.google.android.calendar.api.common.Feature;
import com.google.android.calendar.api.converters.EventAccessLevelConverter;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.AttendeeStoreUtils;
import com.google.android.calendar.api.event.attendee.ResponseExtrasStoreUtils;
import com.google.android.calendar.api.event.conference.ConferenceDataUtils;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.StructuredLocation;
import com.google.android.calendar.api.event.location.StructuredLocationModifications;
import com.google.android.calendar.api.event.notification.NotificationsStoreUtils;
import com.google.android.calendar.api.event.time.TimingValuesPopulator;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitIdTypeUtil;
import com.google.android.calendar.api.habit.HabitInstanceModifications;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.syncadapters.calendar.timely.userstatus.ParticipantStatusStoreUtils;
import com.google.calendar.v2a.android.util.provider.Batch;
import com.google.common.base.Optional;
import com.google.common.base.Platform;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventModifications, Event, CpEventDescriptor, CpEventKey, 
//            EventDescriptor, EnumConverter, EventStoreUtils, SyncAdapterContentValuesPopulator, 
//            EventPermissionUtils, ContentProviderShared

final class ContentProviderInsert
{

    static EventDescriptor insertEvent(EventModifications eventmodifications, GooglePrivateData.GuestNotification guestnotification)
        throws IOException
    {
        CalendarDescriptor calendardescriptor = eventmodifications.getCalendar();
        if (calendardescriptor == null)
        {
            throw new NullPointerException();
        }
        if (calendardescriptor.calendarKey == null)
        {
            throw new NullPointerException();
        }
        guestnotification = performInsertion(eventmodifications, guestnotification, eventmodifications.getGoogleSyncIdForInsertion(), false);
        if (eventmodifications.isRecurring())
        {
            return new CpEventDescriptor(CpEventKey.newInstance(((Result) (guestnotification)).localId, eventmodifications.getStartMillis()));
        } else
        {
            return new CpEventDescriptor(CpEventKey.newInstance(((Result) (guestnotification)).localId));
        }
    }

    static Result performInsertion(EventModifications eventmodifications, GooglePrivateData.GuestNotification guestnotification, String s, boolean flag)
        throws IOException
    {
        Object obj2 = null;
        android.accounts.Account account = eventmodifications.getCalendar().account;
        Object obj5 = new ContentValues();
        Object obj;
        if (flag)
        {
            if (!eventmodifications.getDescriptor().isRecurring())
            {
                throw new IllegalStateException();
            }
            ContentValues contentvalues = new ContentValues();
            obj = (CpEventDescriptor)eventmodifications.getDescriptor();
            int i;
            if (((CpEventDescriptor) (obj)).originalKey != null)
            {
                obj = ((CpEventDescriptor) (obj)).originalKey;
            } else
            {
                obj = ((CpEventDescriptor) (obj)).key;
            }
            contentvalues.put("original_id", Long.valueOf(((CpEventKey) (obj)).localId()));
            contentvalues.put("originalInstanceTime", Long.valueOf(((CpEventKey) (obj)).startMillis()));
            if (eventmodifications.getOriginal().isAllDayEvent())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            contentvalues.put("originalAllDay", Integer.valueOf(i));
            ((ContentValues) (obj5)).putAll(contentvalues);
            eventmodifications.setRecurrence(null);
        }
        contentvalues = new ContentValues();
        contentvalues.putAll(TimingValuesPopulator.populateValues(eventmodifications, true));
        if (eventmodifications.getOrganizer().type == 1 || eventmodifications.isOrganizerModified())
        {
            contentvalues.put("organizer", eventmodifications.getOrganizer().email);
            if (eventmodifications.getOrganizer().email.equalsIgnoreCase(eventmodifications.getCalendar().calendarId))
            {
                obj = "1";
            } else
            {
                obj = "0";
            }
            contentvalues.put("isOrganizer", ((String) (obj)));
        }
        contentvalues.put("title", eventmodifications.getSummary());
        contentvalues.put("accessLevel", Integer.valueOf(EventAccessLevelConverter.apiToProvider(eventmodifications.getVisibility())));
        contentvalues.put("availability", Integer.valueOf(EnumConverter.convertApiToProviderAvailability(eventmodifications.getAvailability())));
        contentvalues.put("description", eventmodifications.getDescription());
        if (eventmodifications.canAttendeesModify())
        {
            obj = "1";
        } else
        {
            obj = "0";
        }
        contentvalues.put("guestsCanModify", ((String) (obj)));
        if (eventmodifications.canAttendeesAddAttendees())
        {
            obj = "1";
        } else
        {
            obj = "0";
        }
        contentvalues.put("guestsCanInviteOthers", ((String) (obj)));
        if (eventmodifications.canAttendeesSeeAttendees())
        {
            obj = "1";
        } else
        {
            obj = "0";
        }
        contentvalues.put("guestsCanSeeGuests", ((String) (obj)));
        if (eventmodifications.isAttendeesOmitted())
        {
            obj = "0";
        } else
        {
            obj = "1";
        }
        contentvalues.put("hasAttendeeData", ((String) (obj)));
        obj = eventmodifications.getColorOverride();
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = ((EventColor) (obj)).getKey();
        }
        if (Platform.stringIsNullOrEmpty(((String) (obj))))
        {
            contentvalues.put("eventColor", null);
        }
        contentvalues.put("eventColor_index", ((String) (obj)));
        obj = eventmodifications.getLocationModification().getEventLocations();
        if (((Collection) (obj)).isEmpty())
        {
            obj = obj2;
        } else
        {
            obj = ((EventLocation)((Collection) (obj)).iterator().next()).name;
        }
        contentvalues.put("eventLocation", ((String) (obj)));
        if (s != null)
        {
            contentvalues.put("_sync_id", s);
        }
        contentvalues.put("calendar_id", Long.valueOf(eventmodifications.getCalendar().calendarKey.getLocalId()));
        contentvalues.put("dirty", Integer.valueOf(1));
        if (AccountUtil.isGoogleAccount(eventmodifications.getCalendar().account))
        {
            contentvalues.put("hasAttendeeData", Integer.valueOf(1));
        }
        ((ContentValues) (obj5)).putAll(contentvalues);
        obj = new ContentValues();
        if (eventmodifications.getHabitInstanceModifications().isSupported())
        {
            if (!eventmodifications.getHabitInstanceModifications().isSupported())
            {
                throw new IllegalArgumentException();
            }
            obj2 = (HabitInstanceModifications)eventmodifications.getHabitInstanceModifications().getValue();
            ((ContentValues) (obj)).put("sync_data9", Integer.valueOf(EnumConverter.convertStatusToHabitStoreFlags(((HabitInstanceModifications) (obj2)).getStatus()) | EventStoreUtils.habitInstanceStatusInferredToStoreFlag(((HabitInstanceModifications) (obj2)).getStatusInferred())));
            ((ContentValues) (obj)).put("sync_data8", HabitIdTypeUtil.createHabitIdTypeStringFromApiType(((HabitInstanceModifications) (obj2)).getHabitParentDescriptor().habitId, ((HabitInstanceModifications) (obj2)).getParentType()));
        } else
        {
            boolean flag1;
            if (!eventmodifications.getHabitInstanceModifications().isSupported())
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
            ((ContentValues) (obj)).put("sync_data9", Integer.valueOf(SyncAdapterContentValuesPopulator.computeEventExtrasFlags(eventmodifications)));
        }
        ((ContentValues) (obj5)).putAll(((ContentValues) (obj)));
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).notify_guests();
        if (AccountUtil.isGoogleAccount(account))
        {
            ((ContentValues) (obj5)).put("sync_data6", Integer.valueOf(guestnotification.ordinal()));
        }
        obj = new ExtendedPropertiesInsert();
        if (s != null)
        {
            guestnotification = new ExtendedPropertyPair("shouldCreateEvent", "1");
            ((ExtendedPropertiesInsert) (obj)).inserts.add(guestnotification);
        }
        if (EventPermissionUtils.isGoogleEvent(eventmodifications))
        {
            boolean flag2;
            if (eventmodifications.isEndTimeUnspecified())
            {
                guestnotification = "1";
            } else
            {
                guestnotification = "0";
            }
            guestnotification = new ExtendedPropertyPair("endTimeUnspecified", guestnotification);
            ((ExtendedPropertiesInsert) (obj)).inserts.add(guestnotification);
            if (eventmodifications.getVisibility() == 3)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (flag2)
            {
                guestnotification = "1";
            } else
            {
                guestnotification = "0";
            }
            guestnotification = new ExtendedPropertyPair("secretEvent", guestnotification);
            ((ExtendedPropertiesInsert) (obj)).inserts.add(guestnotification);
            if (eventmodifications.getNotifications() != null && eventmodifications.getNotifications().isEmpty())
            {
                guestnotification = new ExtendedPropertyPair("clearDefaultReminders", "1");
                ((ExtendedPropertiesInsert) (obj)).inserts.add(guestnotification);
            }
        }
        if (EventPermissionUtils.isGoogleExchangeEvent(eventmodifications))
        {
            guestnotification = ResponseExtrasStoreUtils.createExtendedPropertyPairs(eventmodifications);
            ((ExtendedPropertiesInsert) (obj)).inserts.addAll(guestnotification);
        }
        guestnotification = Features.instance;
        if (guestnotification == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)guestnotification).propose_new_time();
        boolean flag3;
        if (AccountUtil.isGoogleAccount(eventmodifications.getCalendar().account))
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (flag3)
        {
            guestnotification = new ExtendedPropertyPair("eventAttendeeList", ContentProviderShared.getEventAttendeeList(eventmodifications));
            ((ExtendedPropertiesInsert) (obj)).inserts.add(guestnotification);
        }
        if (!eventmodifications.getAttachments().isEmpty())
        {
            guestnotification = ExtendedPropertyPair.createForAttachments(eventmodifications.getAttachments());
            ((ExtendedPropertiesInsert) (obj)).inserts.add(guestnotification);
        }
        if (!eventmodifications.getLocation().getEventLocations().isEmpty())
        {
            guestnotification = ExtendedPropertyPair.createForLocation(eventmodifications.getLocation());
            ((ExtendedPropertiesInsert) (obj)).inserts.add(guestnotification);
        }
        if (eventmodifications.getParticipantStatus() != null)
        {
            guestnotification = new ExtendedPropertyPair("participantStatusExtra", ParticipantStatusStoreUtils.apiObjectToSerializedProto(eventmodifications.getParticipantStatus()));
            ((ExtendedPropertiesInsert) (obj)).inserts.add(guestnotification);
        }
        if (!ConferenceDataUtils.isEmptyConference(eventmodifications.getConferenceDataModifications()))
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (flag3)
        {
            guestnotification = "1";
        } else
        {
            guestnotification = "0";
        }
        guestnotification = new ExtendedPropertyPair("includeHangout", guestnotification);
        ((ExtendedPropertiesInsert) (obj)).inserts.add(guestnotification);
        if (flag3)
        {
            guestnotification = ExtendedPropertyPair.createForConferenceData(eventmodifications.getConferenceDataModifications());
            ((ExtendedPropertiesInsert) (obj)).inserts.add(guestnotification);
        }
        if (eventmodifications.isIcsImportOrUpdate())
        {
            guestnotification = new ExtendedPropertyPair("iCalUid", eventmodifications.getICalUid());
            ((ExtendedPropertiesInsert) (obj)).inserts.add(guestnotification);
            guestnotification = new ExtendedPropertyPair("sequenceNumber", String.valueOf(eventmodifications.getSequenceNumber()));
            ((ExtendedPropertiesInsert) (obj)).inserts.add(guestnotification);
            guestnotification = new ExtendedPropertyPair("private:iCalDtStamp", eventmodifications.getICalDtStamp());
            ((ExtendedPropertiesInsert) (obj)).inserts.add(guestnotification);
        }
        guestnotification = ((ExtendedPropertiesInsert) (obj)).inserts;
        if (guestnotification instanceof FluentIterable)
        {
            guestnotification = (FluentIterable)guestnotification;
        } else
        {
            guestnotification = new com.google.common.collect.FluentIterable._cls1(guestnotification, guestnotification);
        }
        obj2 = com.google.android.calendar.api.common.ExtendedPropertiesInsert..Lambda._cls0.$instance;
        guestnotification = (Iterable)((FluentIterable) (guestnotification)).iterableDelegate.or(guestnotification);
        if (guestnotification == null)
        {
            throw new NullPointerException();
        }
        if (obj2 == null)
        {
            throw new NullPointerException();
        }
        guestnotification = new com.google.common.collect.Iterables._cls5(guestnotification, ((com.google.common.base.Function) (obj2)));
        int j;
        if (guestnotification instanceof FluentIterable)
        {
            guestnotification = (FluentIterable)guestnotification;
        } else
        {
            guestnotification = new com.google.common.collect.FluentIterable._cls1(guestnotification, guestnotification);
        }
        guestnotification = (Iterable)((FluentIterable) (guestnotification)).iterableDelegate.or(guestnotification);
        if (guestnotification instanceof Collection)
        {
            guestnotification = ImmutableSet.copyOf((Collection)guestnotification);
        } else
        {
            guestnotification = guestnotification.iterator();
            if (!guestnotification.hasNext())
            {
                guestnotification = RegularImmutableSet.EMPTY;
            } else
            {
                Object obj4 = guestnotification.next();
                if (!guestnotification.hasNext())
                {
                    guestnotification = new SingletonImmutableSet(obj4);
                } else
                {
                    guestnotification = ((com.google.common.collect.ImmutableSet.Builder)((com.google.common.collect.ImmutableSet.Builder)(new com.google.common.collect.ImmutableSet.Builder()).add(obj4)).addAll(guestnotification)).build();
                }
            }
        }
        if (!ExtendedPropertiesUtils.EXTENDED_PROPERTIES_BLACK_LIST.containsAll(guestnotification))
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            ((ContentValues) (obj5)).put("hasExtendedProperties", Integer.valueOf(1));
        }
        guestnotification = new ContentProviderOperator();
        obj2 = ContentProviderOperation.newInsert(CalendarProviderHelper.withAppendedSyncAdapterFlags(android.provider.CalendarContract.Events.CONTENT_URI, account)).withValues(((ContentValues) (obj5))).build();
        obj5 = ((ContentProviderOperator) (guestnotification)).batch;
        ((Batch) (obj5)).operations.add(obj2);
        j = ((Batch) (obj5)).operations.size() - 1;
        NotificationsStoreUtils.addInsertNotificationOperationsForEventCreate(j, eventmodifications, guestnotification);
        AttendeeStoreUtils.createInsertOperations(eventmodifications, guestnotification, j, false);
        Object obj3;
        for (eventmodifications = ((ExtendedPropertiesInsert) (obj)).inserts.iterator(); eventmodifications.hasNext(); ((Batch) (obj3)).operations.size())
        {
            Object obj1 = (ExtendedPropertyPair)eventmodifications.next();
            obj3 = new ContentValues();
            ((ContentValues) (obj3)).put("name", ((ExtendedPropertyPair) (obj1)).key);
            ((ContentValues) (obj3)).put("value", ((ExtendedPropertyPair) (obj1)).value);
            obj1 = ContentProviderOperation.newInsert(ExtendedPropertiesUtils.getExtendedPropertyUri(account)).withValues(((ContentValues) (obj3))).withValueBackReference("event_id", j).build();
            obj3 = ((ContentProviderOperator) (guestnotification)).batch;
            ((Batch) (obj3)).operations.add(obj1);
        }

        eventmodifications = ((ContentProviderOperator) (guestnotification)).batch.apply(CalendarApi.getApiContentResolver(), "com.android.calendar").getLocalId(j);
        if (eventmodifications == null)
        {
            throw new IOException("Exception creation failed.");
        } else
        {
            return new Result(eventmodifications.longValue(), s);
        }
    }

    private class Result
    {

        public final long localId;

        Result(long l, String s)
        {
            localId = l;
        }
    }

}
