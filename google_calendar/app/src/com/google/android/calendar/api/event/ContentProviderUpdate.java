// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.color.EventColor;
import com.google.android.calendar.api.common.CalendarProviderHelper;
import com.google.android.calendar.api.common.ContentProviderOperator;
import com.google.android.calendar.api.common.ExtendedPropertiesUpdate;
import com.google.android.calendar.api.common.ExtendedPropertiesUtils;
import com.google.android.calendar.api.common.ExtendedPropertyPair;
import com.google.android.calendar.api.common.Feature;
import com.google.android.calendar.api.converters.EventAccessLevelConverter;
import com.google.android.calendar.api.event.attachment.AttachmentModifications;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.AttendeeModifications;
import com.google.android.calendar.api.event.attendee.AttendeeStoreUtils;
import com.google.android.calendar.api.event.attendee.ResponseExtrasStoreUtils;
import com.google.android.calendar.api.event.conference.ConferenceDataModifications;
import com.google.android.calendar.api.event.conference.ConferenceDataUtils;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.StructuredLocationModifications;
import com.google.android.calendar.api.event.notification.NotificationModifications;
import com.google.android.calendar.api.event.notification.NotificationsStoreUtils;
import com.google.android.calendar.api.event.time.TimingValuesPopulator;
import com.google.android.calendar.api.habit.HabitInstanceModifications;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.syncadapters.calendar.timely.userstatus.ParticipantStatusStoreUtils;
import com.google.calendar.v2a.android.util.provider.Batch;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Platform;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventModifications, EventStoreUtils, EventPermissionUtils, ContentProviderShared, 
//            Event, GooglePrivateData, EventDescriptor, EnumConverter, 
//            SyncAdapterContentValuesPopulator, CpEventDescriptor, CpEventKey

final class ContentProviderUpdate
{

    static Result updateStemEvent(EventModifications eventmodifications, GooglePrivateData.GuestNotification guestnotification)
        throws IOException
    {
        Object obj;
        Object obj1;
        ContentProviderOperator contentprovideroperator;
        boolean flag;
        boolean flag5;
        obj1 = null;
        flag5 = true;
        contentprovideroperator = new ContentProviderOperator();
        android.accounts.Account account = eventmodifications.getCalendar().account;
        long l = EventStoreUtils.localId(eventmodifications);
        Object obj5 = new ExtendedPropertiesUpdate();
        if (EventPermissionUtils.isGoogleEvent(eventmodifications))
        {
            if (eventmodifications.isEndTimeUnspecifiedModified())
            {
                if (eventmodifications.isEndTimeUnspecified())
                {
                    obj = "1";
                } else
                {
                    obj = "0";
                }
                obj = new ExtendedPropertyPair("endTimeUnspecified", ((String) (obj)));
                ((ExtendedPropertiesUpdate) (obj5)).updates.add(obj);
            }
            if (eventmodifications.isVisibilityModified())
            {
                if (eventmodifications.getVisibility() == 3)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    obj = "1";
                } else
                {
                    obj = "0";
                }
                obj = new ExtendedPropertyPair("secretEvent", ((String) (obj)));
                ((ExtendedPropertiesUpdate) (obj5)).updates.add(obj);
            }
        }
        if (EventPermissionUtils.isGoogleExchangeEvent(eventmodifications))
        {
            if (ResponseExtrasStoreUtils.isResponseOfCurrentAttendeeModified(eventmodifications))
            {
                obj = Arrays.asList(ResponseExtrasStoreUtils.RESPONSE_EXTRAS_EXTENDED_PROPERTIES);
                ((ExtendedPropertiesUpdate) (obj5)).deletes.addAll(((Collection) (obj)));
            }
            obj = ResponseExtrasStoreUtils.createExtendedPropertyPairs(eventmodifications);
            ((ExtendedPropertiesUpdate) (obj5)).updates.addAll(((Collection) (obj)));
        }
        if (ContentProviderShared.canProposeTimeWithGoogleAccount(eventmodifications))
        {
            if (ResponseExtrasStoreUtils.isResponseCommentOfCurrentAttendeeModified(eventmodifications) || ResponseExtrasStoreUtils.isResponseTimeProposalOfCurrentAttendeeModified(eventmodifications))
            {
                obj = Arrays.asList(new String[] {
                    "eventAttendeeList"
                });
                ((ExtendedPropertiesUpdate) (obj5)).deletes.addAll(((Collection) (obj)));
            }
            obj = new ExtendedPropertyPair("eventAttendeeList", ContentProviderShared.getEventAttendeeList(eventmodifications));
            ((ExtendedPropertiesUpdate) (obj5)).updates.add(obj);
        }
        if (eventmodifications.getNotificationModifications().isModified())
        {
            NotificationsStoreUtils.addDeleteNotificationOperations(l, contentprovideroperator);
            NotificationsStoreUtils.addInsertNotificationOperationsForEventUpdate(l, eventmodifications, contentprovideroperator);
            if (EventPermissionUtils.isGoogleEvent(eventmodifications))
            {
                if (eventmodifications.getNotifications() != null && eventmodifications.getNotifications().isEmpty())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    obj = "1";
                } else
                {
                    obj = "0";
                }
                obj = new ExtendedPropertyPair("clearDefaultReminders", ((String) (obj)));
                ((ExtendedPropertiesUpdate) (obj5)).updates.add(obj);
            }
        }
        if (eventmodifications.getAttachmentModifications().isModified())
        {
            obj = ExtendedPropertyPair.createForAttachments(eventmodifications.getAttachments());
            ((ExtendedPropertiesUpdate) (obj5)).updates.add(obj);
        }
        if (eventmodifications.getLocationModification().isModified())
        {
            obj = ExtendedPropertyPair.createForLocation(eventmodifications.getLocation());
            ((ExtendedPropertiesUpdate) (obj5)).updates.add(obj);
        }
        if (eventmodifications.isParticipantStatusModified())
        {
            obj = new ExtendedPropertyPair("participantStatusExtra", ParticipantStatusStoreUtils.apiObjectToSerializedProto(eventmodifications.getParticipantStatus()));
            ((ExtendedPropertiesUpdate) (obj5)).updates.add(obj);
        }
        if (eventmodifications.getAttendeeModifications().isModified())
        {
            if (contentprovideroperator == null)
            {
                throw new NullPointerException();
            }
            obj = ContentProviderOperation.newDelete(android.provider.CalendarContract.Attendees.CONTENT_URI).withSelection("event_id=?", new String[] {
                String.valueOf(l)
            }).build();
            Batch batch = contentprovideroperator.batch;
            batch.operations.add(obj);
            batch.operations.size();
            AttendeeStoreUtils.createInsertOperations(eventmodifications, contentprovideroperator, l, true);
        }
        if (eventmodifications.getConferenceDataModifications().isModified())
        {
            Object obj2;
            Object obj6;
            if (!ConferenceDataUtils.isEmptyConference(eventmodifications.getConferenceDataModifications()))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                obj = "1";
            } else
            {
                obj = "0";
            }
            obj = new ExtendedPropertyPair("includeHangout", ((String) (obj)));
            ((ExtendedPropertiesUpdate) (obj5)).updates.add(obj);
            if (flag)
            {
                obj = ExtendedPropertyPair.createForConferenceData(eventmodifications.getConferenceDataModifications());
                ((ExtendedPropertiesUpdate) (obj5)).updates.add(obj);
            }
        }
        if (eventmodifications.isIcsImportOrUpdate())
        {
            obj = new ExtendedPropertyPair("iCalUid", eventmodifications.getICalUid());
            ((ExtendedPropertiesUpdate) (obj5)).updates.add(obj);
            obj = new ExtendedPropertyPair("sequenceNumber", String.valueOf(eventmodifications.getSequenceNumber()));
            ((ExtendedPropertiesUpdate) (obj5)).updates.add(obj);
            obj = new ExtendedPropertyPair("private:iCalDtStamp", eventmodifications.getICalDtStamp());
            ((ExtendedPropertiesUpdate) (obj5)).updates.add(obj);
        }
        obj6 = GooglePrivateDataHelper..Lambda._cls2.$instance;
        obj2 = eventmodifications.getGooglePrivateData();
        obj = obj2;
        if (obj2 == null)
        {
            obj = GooglePrivateData.DEFAULT;
        }
        if (((Boolean)((Function) (obj6)).apply(obj)).booleanValue())
        {
            obj = new ExtendedPropertyPair("everyoneDeclinedDismissed", String.valueOf(eventmodifications.getSequenceNumber()));
            ((ExtendedPropertiesUpdate) (obj5)).updates.add(obj);
        }
        for (obj = ((ExtendedPropertiesUpdate) (obj5)).deletes.iterator(); ((Iterator) (obj)).hasNext(); ((Batch) (obj6)).operations.size())
        {
            obj2 = ExtendedPropertiesUpdate.createDeleteOperation((String)((Iterator) (obj)).next(), account, l);
            obj6 = contentprovideroperator.batch;
            ((Batch) (obj6)).operations.add(obj2);
        }

        Object obj7;
        for (obj = ((ExtendedPropertiesUpdate) (obj5)).updates.iterator(); ((Iterator) (obj)).hasNext(); ((Batch) (obj7)).operations.size())
        {
            Object obj3 = (ExtendedPropertyPair)((Iterator) (obj)).next();
            obj7 = ExtendedPropertiesUpdate.createDeleteOperation(((ExtendedPropertyPair) (obj3)).key, account, l);
            Batch batch1 = contentprovideroperator.batch;
            batch1.operations.add(obj7);
            batch1.operations.size();
            obj7 = new ContentValues();
            ((ContentValues) (obj7)).put("name", ((ExtendedPropertyPair) (obj3)).key);
            ((ContentValues) (obj7)).put("value", ((ExtendedPropertyPair) (obj3)).value);
            ((ContentValues) (obj7)).put("event_id", Long.valueOf(l));
            obj3 = ContentProviderOperation.newInsert(ExtendedPropertiesUtils.getExtendedPropertyUri(account)).withValues(((ContentValues) (obj7))).build();
            obj7 = contentprovideroperator.batch;
            ((Batch) (obj7)).operations.add(obj3);
        }

        EventDescriptor eventdescriptor;
        if (!((ExtendedPropertiesUpdate) (obj5)).updates.isEmpty() || !((ExtendedPropertiesUpdate) (obj5)).deletes.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        eventdescriptor = eventmodifications.getDescriptor();
        obj = ((ExtendedPropertiesUpdate) (obj5)).updates;
        if (obj instanceof FluentIterable)
        {
            obj = (FluentIterable)obj;
        } else
        {
            obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
        }
        obj5 = com.google.android.calendar.api.common.ExtendedPropertiesUpdate..Lambda._cls0.$instance;
        obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (obj5 == null)
        {
            throw new NullPointerException();
        }
        obj = new com.google.common.collect.Iterables._cls5(((Iterable) (obj)), ((Function) (obj5)));
        boolean flag3;
        boolean flag6;
        if (obj instanceof FluentIterable)
        {
            obj = (FluentIterable)obj;
        } else
        {
            obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
        }
        obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
        if (obj instanceof Collection)
        {
            obj = ImmutableSet.copyOf((Collection)obj);
        } else
        {
            obj = ((Iterable) (obj)).iterator();
            if (!((Iterator) (obj)).hasNext())
            {
                obj = RegularImmutableSet.EMPTY;
            } else
            {
                obj5 = ((Iterator) (obj)).next();
                if (!((Iterator) (obj)).hasNext())
                {
                    obj = new SingletonImmutableSet(obj5);
                } else
                {
                    obj = ((com.google.common.collect.ImmutableSet.Builder)((com.google.common.collect.ImmutableSet.Builder)(new com.google.common.collect.ImmutableSet.Builder()).add(obj5)).addAll(((Iterator) (obj)))).build();
                }
            }
        }
        if (!ExtendedPropertiesUtils.EXTENDED_PROPERTIES_BLACK_LIST.containsAll(((Collection) (obj))))
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        flag6 = eventdescriptor.isRecurringException();
        obj5 = new ContentValues();
        if (flag6)
        {
            obj = new ContentValues();
            TimingValuesPopulator.addSingleEventValues(eventmodifications, ((ContentValues) (obj)), false);
            ((ContentValues) (obj5)).putAll(((ContentValues) (obj)));
        } else
        {
            ((ContentValues) (obj5)).putAll(TimingValuesPopulator.populateValues(eventmodifications, false));
        }
        if (eventmodifications.isOrganizerModified())
        {
            ((ContentValues) (obj5)).put("organizer", eventmodifications.getOrganizer().email);
            long l1;
            if (eventmodifications.getOrganizer().email.equalsIgnoreCase(eventmodifications.getCalendar().calendarId))
            {
                obj = "1";
            } else
            {
                obj = "0";
            }
            ((ContentValues) (obj5)).put("isOrganizer", ((String) (obj)));
        }
        if (eventmodifications.isSummaryModified())
        {
            ((ContentValues) (obj5)).put("title", eventmodifications.getSummary());
        }
        if (eventmodifications.isVisibilityModified())
        {
            ((ContentValues) (obj5)).put("accessLevel", Integer.valueOf(EventAccessLevelConverter.apiToProvider(eventmodifications.getVisibility())));
        }
        if (eventmodifications.isAvailabilityModified())
        {
            ((ContentValues) (obj5)).put("availability", Integer.valueOf(EnumConverter.convertApiToProviderAvailability(eventmodifications.getAvailability())));
        }
        if (eventmodifications.isDescriptionModified())
        {
            ((ContentValues) (obj5)).put("description", eventmodifications.getDescription());
        }
        if (eventmodifications.isCanAttendeesAddAttendeesModified())
        {
            if (eventmodifications.canAttendeesAddAttendees())
            {
                obj = "1";
            } else
            {
                obj = "0";
            }
            ((ContentValues) (obj5)).put("guestsCanInviteOthers", ((String) (obj)));
        }
        if (eventmodifications.isNewEvent())
        {
            if (eventmodifications.isAttendeesOmitted())
            {
                obj = "0";
            } else
            {
                obj = "1";
            }
            ((ContentValues) (obj5)).put("hasAttendeeData", ((String) (obj)));
        }
        if (eventmodifications.isColorOverrideModified())
        {
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
                ((ContentValues) (obj5)).put("eventColor", null);
            }
            ((ContentValues) (obj5)).put("eventColor_index", ((String) (obj)));
        }
        if (eventmodifications.getLocationModification().isModified())
        {
            obj = eventmodifications.getLocationModification().getEventLocations();
            if (((Collection) (obj)).isEmpty())
            {
                obj = obj1;
            } else
            {
                obj = ((EventLocation)((Collection) (obj)).iterator().next()).name;
            }
            ((ContentValues) (obj5)).put("eventLocation", ((String) (obj)));
        }
        if (flag3)
        {
            ((ContentValues) (obj5)).put("hasExtendedProperties", Integer.valueOf(1));
        }
        if (flag && ((ContentValues) (obj5)).size() == 0)
        {
            ((ContentValues) (obj5)).put("title", eventmodifications.getSummary());
        }
        if (((ContentValues) (obj5)).size() > 0)
        {
            l1 = EventStoreUtils.localId(eventdescriptor);
            obj = ContentProviderOperation.newUpdate(ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, l1)).withValues(((ContentValues) (obj5))).build();
            obj1 = contentprovideroperator.batch;
            ((Batch) (obj1)).operations.add(obj);
            ((Batch) (obj1)).operations.size();
        }
        obj = eventmodifications.getCalendar().account;
        obj1 = new ContentValues();
        if (!eventmodifications.getHabitInstanceModifications().isSupported()) goto _L2; else goto _L1
_L1:
        if (!eventmodifications.getHabitInstanceModifications().isSupported())
        {
            throw new IllegalArgumentException();
        }
        HabitInstanceModifications habitinstancemodifications = (HabitInstanceModifications)eventmodifications.getHabitInstanceModifications().getValue();
        if (habitinstancemodifications.isStatusModified())
        {
            int i = EnumConverter.convertStatusToHabitStoreFlags(habitinstancemodifications.getStatus());
            ((ContentValues) (obj1)).put("sync_data9", Integer.valueOf(EventStoreUtils.habitInstanceStatusInferredToStoreFlag(habitinstancemodifications.getStatusInferred()) | i));
        }
_L4:
        FeatureConfig featureconfig;
        if (((ContentValues) (obj1)).size() > 0 || flag)
        {
            ((ContentValues) (obj1)).put("dirty", Integer.valueOf(1));
        }
        featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        break; /* Loop/switch isn't completed */
_L2:
        boolean flag4;
        if (!eventmodifications.getHabitInstanceModifications().isSupported())
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        if (!flag4)
        {
            throw new IllegalArgumentException();
        }
        int j = SyncAdapterContentValuesPopulator.computeEventExtrasFlags(eventmodifications);
        if (j != SyncAdapterContentValuesPopulator.computeEventExtrasFlags(eventmodifications.getOriginal()))
        {
            ((ContentValues) (obj1)).put("sync_data9", Integer.valueOf(j));
        }
        if (true) goto _L4; else goto _L3
_L3:
        ((FeatureConfig)featureconfig).notify_guests();
        boolean flag1;
        if (AccountUtil.isGoogleAccount(((android.accounts.Account) (obj))))
        {
            GooglePrivateData.GuestNotification guestnotification1 = ContentProviderShared.getGuestNotification(eventmodifications.getOriginal());
            long l2;
            boolean flag7;
            if (guestnotification != guestnotification1)
            {
                if (guestnotification == GooglePrivateData.GuestNotification.ENABLED || guestnotification1 == GooglePrivateData.GuestNotification.ENABLED)
                {
                    guestnotification = GooglePrivateData.GuestNotification.ENABLED;
                } else
                {
                    guestnotification = GooglePrivateData.GuestNotification.DISABLED;
                }
            }
            ((ContentValues) (obj1)).put("sync_data6", Integer.valueOf(guestnotification.ordinal()));
        }
        if (eventmodifications.getHabitInstanceModifications().isSupported() && eventmodifications.getHabitInstanceModifications().getValue() != null && ((HabitInstanceModifications)eventmodifications.getHabitInstanceModifications().getValue()).getStatus() == 2)
        {
            guestnotification = eventmodifications.getCalendar().calendarId;
            long l3 = ((CpEventKey)eventmodifications.getDescriptor().getKey()).localId();
            Object obj4 = new ContentValues();
            ((ContentValues) (obj4)).put("attendeeStatus", Integer.valueOf(2));
            guestnotification = ContentProviderOperation.newUpdate(android.provider.CalendarContract.Attendees.CONTENT_URI).withValues(((ContentValues) (obj4))).withSelection("event_id=? AND attendeeEmail=?", new String[] {
                String.valueOf(l3), guestnotification
            }).build();
            obj4 = contentprovideroperator.batch;
            ((Batch) (obj4)).operations.add(guestnotification);
            ((Batch) (obj4)).operations.size();
        }
        if (((ContentValues) (obj1)).size() > 0)
        {
            l2 = EventStoreUtils.localId(eventmodifications);
            guestnotification = ContentProviderOperation.newUpdate(CalendarProviderHelper.withAppendedSyncAdapterFlags(ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, l2), ((android.accounts.Account) (obj)))).withValues(((ContentValues) (obj1))).build();
            obj = contentprovideroperator.batch;
            ((Batch) (obj)).operations.add(guestnotification);
            ((Batch) (obj)).operations.size();
        }
        flag7 = contentprovideroperator.batch.apply(CalendarApi.getApiContentResolver(), "com.android.calendar").hasAnyRowChanged();
        obj = (CpEventDescriptor)eventmodifications.getDescriptor();
        if (((CpEventDescriptor) (obj)).originalKey == null && !((CpEventDescriptor) (obj)).key.hasStartMillis())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1 || eventmodifications.getRecurrence() == null) goto _L6; else goto _L5
_L5:
        guestnotification = ((CpEventDescriptor) (obj)).derivePhantomDescriptor(eventmodifications.getStartMillis());
_L8:
        return new Result(guestnotification, flag7);
_L6:
        boolean flag2;
        if (((CpEventDescriptor) (obj)).originalKey == null && ((CpEventDescriptor) (obj)).key.hasStartMillis())
        {
            flag2 = flag5;
        } else
        {
            flag2 = false;
        }
        guestnotification = ((GooglePrivateData.GuestNotification) (obj));
        if (flag2)
        {
            if (eventmodifications.getRecurrence() == null)
            {
                guestnotification = new CpEventDescriptor(CpEventKey.newInstance(EventStoreUtils.localId(((EventDescriptor) (obj)))));
            } else
            {
                guestnotification = ((CpEventDescriptor) (obj)).derivePhantomDescriptor(eventmodifications.getStartMillis());
            }
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    private class Result
    {

        public final boolean changed;
        public final EventDescriptor updatedDescriptor;

        Result(EventDescriptor eventdescriptor, boolean flag)
        {
            updatedDescriptor = eventdescriptor;
            changed = flag;
        }
    }

}
