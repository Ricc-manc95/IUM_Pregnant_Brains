// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.accounts.Account;
import android.content.ContentResolver;
import android.database.Cursor;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.timely.store.TimelyStore;
import com.google.android.apps.calendar.util.database.Cursors;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.common.ExtendedPropertiesUtils;
import com.google.android.calendar.api.converters.EventAccessLevelConverter;
import com.google.android.calendar.api.event.attachment.Attachment;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.AttendeeStoreUtils;
import com.google.android.calendar.api.event.conference.ConferenceDataUtils;
import com.google.android.calendar.api.event.conference.ConferenceStoreUtils;
import com.google.android.calendar.api.event.location.StructuredLocationImpl;
import com.google.android.calendar.api.event.location.StructuredLocationTimelyStoreUtils;
import com.google.android.calendar.api.event.notification.NotificationsStoreUtils;
import com.google.android.calendar.api.event.smartmail.FlightReservationStoreUtils;
import com.google.android.calendar.api.event.smartmail.SeatingInformation;
import com.google.android.calendar.api.event.smartmail.SmartMailAction;
import com.google.android.calendar.api.event.smartmail.SmartMailEventStoreUtils;
import com.google.android.calendar.api.event.smartmail.SmartMailImage;
import com.google.android.calendar.api.event.smartmail.SmartMailStoreUtils;
import com.google.android.calendar.api.event.time.EventTiming;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitIdTypeUtil;
import com.google.android.calendar.api.habit.HabitInstanceImpl;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.json.JsonUtils;
import com.google.android.syncadapters.calendar.timely.contract.TimelyEventData;
import com.google.android.syncadapters.calendar.timely.userstatus.ParticipantStatusStoreUtils;
import com.google.api.services.calendar.model.Action;
import com.google.api.services.calendar.model.ConferenceData;
import com.google.api.services.calendar.model.EmailMessage;
import com.google.api.services.calendar.model.Event2;
import com.google.api.services.calendar.model.EventAttachment;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventLocation;
import com.google.api.services.calendar.model.EventReservation;
import com.google.api.services.calendar.model.EventReservationSeatingInformation;
import com.google.api.services.calendar.model.Image;
import com.google.api.services.calendar.model.LodgingReservation;
import com.google.api.services.calendar.model.Restaurant;
import com.google.api.services.calendar.model.RestaurantReservation;
import com.google.api.services.calendar.model.SmartMailInfo;
import com.google.api.services.calendar.model.StructuredLocation;
import com.google.calendar.common.rtc.ConferenceDescriptionUtils;
import com.google.common.base.Platform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.api.event:
//            CpEventDescriptor, CpEventKey, EventDescriptor, EnumConverter, 
//            LoadDetailsConstants, AutoValue_EventResponseSummary, EventSource, GooglePrivateData, 
//            EventImpl, EventUtil, Event

public class EventStoreUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/api/event/EventStoreUtils);

    private EventStoreUtils()
    {
    }

    static boolean convertHabitStoreFlagsToStatusInferredValue(String s)
    {
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_41;
        }
        int i = Integer.decode(s).intValue();
        if ((i & 0x200) != 0)
        {
            return true;
        }
        break MISSING_BLOCK_LABEL_41;
        NumberFormatException numberformatexception;
        numberformatexception;
        LogUtils.v(TAG, numberformatexception, "Unable to decode: %s", new Object[] {
            s
        });
        return false;
    }

    static CpEventDescriptor createEventDescriptor(long l, long l1, long l2, long l3, 
            String s)
    {
        byte byte0;
        if (l2 != 0L)
        {
            byte0 = 2;
        } else
        if (s != null)
        {
            byte0 = 1;
        } else
        {
            byte0 = 0;
        }
        switch (byte0)
        {
        default:
            throw new IllegalArgumentException("Invalid cursor: Cannot be mapped to event kind.");

        case 0: // '\0'
            return new CpEventDescriptor(CpEventKey.newInstance(l));

        case 2: // '\002'
            return new CpEventDescriptor(CpEventKey.newInstance(l), CpEventKey.newInstance(l2, l3));

        case 1: // '\001'
            return new CpEventDescriptor(CpEventKey.newInstance(l, l1));
        }
    }

    static CalendarDescriptor cursorToCalendarDescriptor(Cursor cursor)
    {
        long l = cursor.getLong(7);
        String s = cursor.getString(33);
        String s1 = cursor.getString(34);
        cursor = Platform.nullToEmpty(cursor.getString(17));
        return new CalendarDescriptor(new Account(s, s1), cursor, CalendarKey.newInstance(l));
    }

    static Event cursorToEntity(CpEventDescriptor cpeventdescriptor, CalendarDescriptor calendardescriptor, CalendarListEntry calendarlistentry, Cursor cursor, String as[])
        throws IOException
    {
label0:
        {
            if (cpeventdescriptor == null)
            {
                throw new NullPointerException();
            }
            if (calendarlistentry == null)
            {
                throw new NullPointerException();
            }
            if (cursor.getInt(35) == 1)
            {
                return null;
            }
            Object obj;
            int i;
            int j;
            long l1;
            if (cpeventdescriptor.originalKey == null && !cpeventdescriptor.key.hasStartMillis())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                i = 0;
            } else
            {
                if (cpeventdescriptor.originalKey != null)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    i = 2;
                } else
                {
                    if (cpeventdescriptor.originalKey == null && cpeventdescriptor.key.hasStartMillis())
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0)
                    {
                        i = 1;
                    } else
                    {
                        throw new IllegalArgumentException("Descriptor must point to stored event.");
                    }
                }
            }
            l1 = cursor.getLong(23);
            obj = cursor.getString(10);
            if (l1 != 0L)
            {
                j = 2;
            } else
            if (obj != null)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (i != j)
            {
                return null;
            }
            obj = calendarlistentry.getDescriptor().account;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            Account account = (Account)obj;
            Object obj4 = cursor.getString(20);
            if (TextUtils.isEmpty(((CharSequence) (obj4))))
            {
                obj4 = calendarlistentry.getDescriptor().calendarId;
            }
            String s1 = cursor.getString(2);
            EventTiming eventtiming = EventTiming.create(cursor, as);
            Object obj13 = ExtendedPropertiesUtils.loadExtendedProperties(((CpEventKey)cpeventdescriptor.getKey()).localId());
            HabitInstanceImpl habitinstanceimpl;
            Object obj1;
            Object obj2;
            Attachment aattachment[];
            Object obj3;
            Object obj5;
            Object obj6;
            Object obj7;
            Object obj8;
            Object obj9;
            Object obj10;
            Object obj11;
            String s;
            Object obj12;
            com.google.android.calendar.api.event.notification.Notification anotification[];
            Attendee aattendee[];
            List list;
            Object obj14;
            Object obj15;
            Object obj16;
            boolean flag;
            int k;
            int l;
            int i1;
            int j1;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            boolean flag4;
            boolean flag5;
            boolean flag6;
            boolean flag7;
            boolean flag8;
            boolean flag9;
            boolean flag10;
            if (AccountUtil.isExchangeAccount(account) && hasExchangeCanceledOverride(((Map) (obj13))))
            {
                j = 2;
            } else
            if (!cursor.isNull(25))
            {
                j = EnumConverter.convertProviderToApiStatus(cursor.getInt(25));
            } else
            {
                j = 0;
            }
            if ("1".equals(((Map) (obj13)).get("secretEvent")))
            {
                k = 3;
            } else
            {
                k = EventAccessLevelConverter.providerToApi(cursor.getInt(16));
            }
            flag10 = "1".equals(((Map) (obj13)).get("endTimeUnspecified"));
            as = calendarlistentry.getAccessLevel();
            if (k == 1)
            {
                break label0;
            }
            if (CalendarAccessLevel.FREEBUSY.equals(as))
            {
                flag = true;
            } else
            {
                if (!CalendarAccessLevel.READER.equals(as))
                {
                    break label0;
                }
                if (k == 2 || k == 3)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            }
        }
        obj = null;
        habitinstanceimpl = ((HabitInstanceImpl) (obj));
        if (!flag)
        {
            habitinstanceimpl = ((HabitInstanceImpl) (obj));
            if (AccountUtil.isGoogleAccount(account))
            {
                obj1 = cursor.getString(32);
                as = HabitIdTypeUtil.parseHabitIdAndType(((String) (obj1)));
                if (as == null || as.length == 0)
                {
                    as = null;
                } else
                {
                    as = as[0];
                }
                habitinstanceimpl = ((HabitInstanceImpl) (obj));
                if (!TextUtils.isEmpty(as))
                {
                    as = new HabitDescriptor(calendarlistentry.getDescriptor(), as);
                    obj = cursor.getString(30);
                    habitinstanceimpl = new HabitInstanceImpl(as, HabitIdTypeUtil.parseHabitType(((String) (obj1))), EnumConverter.convertHabitStoreFlagsToStatus(((String) (obj))), convertHabitStoreFlagsToStatusInferredValue(((String) (obj))));
                }
            }
        }
        obj1 = null;
        if (AccountUtil.isGoogleAccount(account))
        {
            obj1 = CalendarApi.getColorFactory().createGoogleEventColor(cursor.getString(26));
        }
        i1 = EnumConverter.convertProviderToApiAvailability(cursor.getInt(15));
        s = cursor.getString(3);
        if (flag)
        {
            obj3 = new TimelyEventData();
        } else
        {
            obj3 = TimelyStore.acquire(CalendarApi.getApiAppContext()).getTimelyEventData(cursor.getString(14), cursor.getString(2), cursor.getLong(7), ((Map) (obj13)));
        }
        if (obj3 == null)
        {
            as = null;
        } else
        {
            as = ((TimelyEventData) (obj3)).structuredLocation;
            if (as == null || ((StructuredLocation) (as)).locations.isEmpty())
            {
                as = null;
            } else
            {
                obj = new ArrayList();
                for (flag = false; flag < ((StructuredLocation) (as)).locations.size(); flag++)
                {
                    ((ArrayList) (obj)).add(StructuredLocationTimelyStoreUtils.toApiEventLocation((EventLocation)((StructuredLocation) (as)).locations.get(flag)));
                }

                as = new StructuredLocationImpl(((java.util.Collection) (obj)));
            }
        }
        if (as != null)
        {
            obj2 = as;
        } else
        {
            as = cursor.getString(4);
            obj = new ArrayList();
            if (as != null)
            {
                obj2 = new com.google.android.calendar.api.event.location.EventLocation.Builder();
                if (as == null)
                {
                    throw new NullPointerException();
                }
                obj2.name = (String)as;
                ((ArrayList) (obj)).add(new com.google.android.calendar.api.event.location.EventLocation(((com.google.android.calendar.api.event.location.EventLocation.Builder) (obj2))));
            }
            obj2 = new StructuredLocationImpl(((java.util.Collection) (obj)));
        }
        l1 = cursor.getLong(41);
        anotification = NotificationsStoreUtils.loadEventNotifications(l1);
        aattachment = null;
        if (obj3 != null)
        {
            obj = ((TimelyEventData) (obj3)).attachments;
            if (obj == null)
            {
                as = null;
            } else
            {
                l = ((List) (obj)).size();
                as = new Attachment[l];
                flag = false;
                while (flag < l) 
                {
                    aattachment = (EventAttachment)((List) (obj)).get(flag);
                    obj5 = new com.google.android.calendar.api.event.attachment.Attachment.Builder();
                    obj5.fileId = Platform.nullToEmpty(((EventAttachment) (aattachment)).fileId);
                    obj5.fileUrl = Platform.nullToEmpty(((EventAttachment) (aattachment)).fileUrl);
                    obj5.iconLink = Platform.nullToEmpty(((EventAttachment) (aattachment)).iconLink);
                    obj5.mimeType = ((EventAttachment) (aattachment)).mimeType;
                    obj5.title = Platform.nullToEmpty(((EventAttachment) (aattachment)).title);
                    as[flag] = new Attachment(((com.google.android.calendar.api.event.attachment.Attachment.Builder) (obj5)).fileId, ((com.google.android.calendar.api.event.attachment.Attachment.Builder) (obj5)).fileUrl, ((com.google.android.calendar.api.event.attachment.Attachment.Builder) (obj5)).iconLink, ((com.google.android.calendar.api.event.attachment.Attachment.Builder) (obj5)).mimeType, ((com.google.android.calendar.api.event.attachment.Attachment.Builder) (obj5)).title);
                    flag++;
                }
            }
            aattachment = as;
        }
        if (obj3 != null)
        {
            as = ((TimelyEventData) (obj3)).attendees;
        } else
        {
            as = null;
        }
        obj6 = calendarlistentry.getDescriptor().calendarId;
        obj8 = CalendarApi.getApiContentResolver().query(android.provider.CalendarContract.Attendees.CONTENT_URI, LoadDetailsConstants.ATTENDEES_PROJECTION, "event_id=?", new String[] {
            String.valueOf(l1)
        }, "attendeeName ASC, attendeeEmail ASC");
        obj9 = new HashMap();
        if (as != null)
        {
            obj10 = as.iterator();
            while (((Iterator) (obj10)).hasNext()) 
            {
                obj11 = (EventAttendee)((Iterator) (obj10)).next();
                obj5 = Platform.nullToEmpty(((EventAttendee) (obj11)).email);
                as = "";
                obj = "";
                obj7 = ((EventAttendee) (obj11)).id;
                if (obj7 != null)
                {
                    as = String.valueOf("gprofile:");
                    obj = String.valueOf(obj7);
                    if (((String) (obj)).length() != 0)
                    {
                        obj = as.concat(((String) (obj)));
                    } else
                    {
                        obj = new String(as);
                    }
                    if (Boolean.TRUE.equals(((EventAttendee) (obj11)).self))
                    {
                        as = ((String []) (obj6));
                    } else
                    {
                        as = String.valueOf(obj7);
                        obj5 = String.valueOf("@profile.calendar.google.com");
                        if (((String) (obj5)).length() != 0)
                        {
                            as = as.concat(((String) (obj5)));
                        } else
                        {
                            as = new String(as);
                        }
                    }
                    obj7 = "com.google";
                    obj5 = as;
                    as = ((String []) (obj7));
                }
                ((Map) (obj9)).put(AttendeeStoreUtils.toApiAttendeeDescriptor(((String) (obj5)), ((String) (obj)), as), obj11);
            }
        }
        break MISSING_BLOCK_LABEL_1392;
        flag = false;
        break MISSING_BLOCK_LABEL_379;
        aattendee = (Attendee[])Cursors.apply(((Cursor) (obj8)), new com.google.android.calendar.api.event.attendee.AttendeeStoreUtils..Lambda._cls0(((Map) (obj9))), "Attendee").toArray(new Attendee[0]);
        if (AccountUtil.isGoogleAccount(account))
        {
            flag3 = "0".equals(cursor.getString(18));
        } else
        {
            flag3 = false;
        }
        if (cursor.getInt(21) == 1)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        if (cursor.getInt(22) == 1)
        {
            flag5 = true;
        } else
        {
            flag5 = false;
        }
        if (cursor.getInt(36) != 0)
        {
            flag6 = true;
        } else
        {
            flag6 = false;
        }
        if (obj4 == null)
        {
            throw new NullPointerException();
        }
        if (aattendee == null)
        {
            throw new NullPointerException();
        }
        j1 = aattendee.length;
        flag = false;
_L38:
        if (flag >= j1) goto _L2; else goto _L1
_L1:
        as = aattendee[flag];
        obj = ((Attendee) (as)).attendeeDescriptor.email;
        if (obj4 != null && ((String) (obj4)).equalsIgnoreCase(((String) (obj))))
        {
            l = 1;
        } else
        {
            l = 0;
        }
        if (!l) goto _L4; else goto _L3
_L3:
        obj4 = ((Attendee) (as)).attendeeDescriptor;
_L5:
        obj5 = calendarlistentry.getDescriptor();
        obj6 = (String)((Map) (obj13)).get("includeHangout");
        obj = ConferenceStoreUtils.toApiConferenceData((ConferenceData)JsonUtils.fromString((String)((Map) (obj13)).get("conferenceData"), com/google/api/services/calendar/model/ConferenceData, null));
        flag1 = ConferenceDataUtils.isEmptyConference(((com.google.android.calendar.api.event.conference.ConferenceData) (obj)));
        if (obj3 == null)
        {
            as = null;
        } else
        {
            as = ((TimelyEventData) (obj3)).conferenceData;
        }
        as = ConferenceStoreUtils.toApiConferenceData(as);
        flag2 = ConferenceDataUtils.isEmptyConference(as);
        if ("1".equals(obj6) && (flag2 || !flag1))
        {
            if (!flag1)
            {
                as = ((String []) (obj));
            } else
            {
                as = ConferenceDataUtils.createDefaultConferenceData(((CalendarDescriptor) (obj5)));
            }
        } else
        if ("0".equals(obj6))
        {
            as = com.google.android.calendar.api.event.conference.ConferenceData.create(Collections.emptyList());
        }
        if (obj3 == null || ((TimelyEventData) (obj3)).responseSummary == null)
        {
            obj5 = null;
        } else
        {
            obj = ((TimelyEventData) (obj3)).responseSummary;
            obj5 = new AutoValue_EventResponseSummary(((com.google.api.services.calendar.model.Event.ResponseSummary) (obj)).numAccepted.intValue(), ((com.google.api.services.calendar.model.Event.ResponseSummary) (obj)).numDeclined.intValue(), ((com.google.api.services.calendar.model.Event.ResponseSummary) (obj)).numNeedsAction.intValue(), ((com.google.api.services.calendar.model.Event.ResponseSummary) (obj)).numTentative.intValue());
        }
        if (obj3 == null || ((TimelyEventData) (obj3)).eventSource == null)
        {
            obj6 = null;
        } else
        {
            obj = ((TimelyEventData) (obj3)).eventSource;
            obj6 = new EventSource(Platform.nullToEmpty(((com.google.api.services.calendar.model.Event.Source) (obj)).url), Platform.nullToEmpty(((com.google.api.services.calendar.model.Event.Source) (obj)).title));
        }
        if (obj3 == null)
        {
            obj = null;
        } else
        {
label1:
            {
                obj12 = ((TimelyEventData) (obj3)).smartMailInfo;
                if (obj12 != null)
                {
                    break label1;
                }
                obj = null;
            }
        }
_L18:
        if (obj3 == null || ((TimelyEventData) (obj3)).participantStatus == null)
        {
            obj3 = null;
        } else
        {
            obj3 = ParticipantStatusStoreUtils.serializedProtoToApiObject(((TimelyEventData) (obj3)).participantStatus);
        }
        obj11 = Platform.nullToEmpty(cursor.getString(28));
        obj12 = Platform.nullToEmpty(cursor.getString(29));
        obj7 = (String)((Map) (obj13)).get("iCalUid");
        if (Platform.stringIsNullOrEmpty(((String) (obj7))))
        {
            obj7 = cursor.getString(39);
        }
        flag = extractSequenceNumber(((Map) (obj13)), cursor);
        obj13 = (String)((Map) (obj13)).get("private:iCalDtStamp");
        obj8 = null;
        if (AccountUtil.isGoogleAccount(account))
        {
            obj8 = cursor.getString(38);
        }
        if (cursor.getLong(40) != 0L)
        {
            flag7 = true;
        } else
        {
            flag7 = false;
        }
        obj9 = null;
        if (!AccountUtil.isGoogleAccount(account))
        {
            break MISSING_BLOCK_LABEL_3358;
        }
        obj9 = Features.instance;
        if (obj9 == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        break MISSING_BLOCK_LABEL_3181;
_L4:
        flag++;
        continue; /* Loop/switch isn't completed */
_L2:
        obj4 = new AttendeeDescriptor(((String) (obj4)));
          goto _L5
        obj8 = ((SmartMailInfo) (obj12)).emailMessages;
        if (obj8 != null && !((List) (obj8)).isEmpty()) goto _L7; else goto _L6
_L6:
        obj = Collections.emptyList();
_L20:
        obj7 = ((SmartMailInfo) (obj12)).actions;
        if (obj7 != null && !((List) (obj7)).isEmpty()) goto _L9; else goto _L8
_L8:
        obj7 = Collections.emptyList();
_L23:
        list = FlightReservationStoreUtils.convertFlightReservations(((SmartMailInfo) (obj12)).flightReservations);
        obj8 = ((SmartMailInfo) (obj12)).lodgingReservations;
        if (obj8 != null && !((List) (obj8)).isEmpty()) goto _L11; else goto _L10
_L10:
        obj8 = Collections.emptyList();
_L26:
        obj11 = ((SmartMailInfo) (obj12)).events;
        if (obj11 != null && !((List) (obj11)).isEmpty()) goto _L13; else goto _L12
_L12:
        obj9 = Collections.emptyList();
_L29:
        obj14 = ((SmartMailInfo) (obj12)).eventReservations;
        if (obj14 != null && !((List) (obj14)).isEmpty()) goto _L15; else goto _L14
_L14:
        obj10 = Collections.emptyList();
_L32:
        obj14 = ((SmartMailInfo) (obj12)).restaurantReservations;
        if (obj14 != null && !((List) (obj14)).isEmpty()) goto _L17; else goto _L16
_L16:
        obj11 = Collections.emptyList();
_L35:
        obj = new com.google.android.calendar.api.event.smartmail.SmartMailInfo(((List) (obj)), ((List) (obj7)), list, ((List) (obj8)), ((List) (obj9)), ((List) (obj10)), ((List) (obj11)));
          goto _L18
_L7:
        obj7 = new ArrayList(((List) (obj8)).size());
        flag = false;
_L21:
        obj = obj7;
        if (flag >= ((List) (obj8)).size()) goto _L20; else goto _L19
_L19:
        obj = (EmailMessage)((List) (obj8)).get(flag);
        if (obj != null)
        {
            ((List) (obj7)).add(new com.google.android.calendar.api.event.smartmail.EmailMessage(SmartMailStoreUtils.toApiOrganization(((EmailMessage) (obj)).publisher)));
        }
        flag++;
          goto _L21
          goto _L20
_L9:
        obj8 = new ArrayList(((List) (obj7)).size());
        obj9 = ((List) (obj7)).iterator();
_L24:
        obj7 = obj8;
        if (!((Iterator) (obj9)).hasNext()) goto _L23; else goto _L22
_L22:
        obj7 = (Action)((Iterator) (obj9)).next();
        if (obj7 != null)
        {
            if (obj7 == null)
            {
                throw new NullPointerException();
            }
            ((List) (obj8)).add(new SmartMailAction(SmartMailStoreUtils.toApiTime(((Action) (obj7)).expirationTime), SmartMailStoreUtils.toApiActionTarget(((Action) (obj7)).goToAction)));
        }
          goto _L24
          goto _L23
_L11:
        obj9 = new ArrayList(((List) (obj8)).size());
        obj10 = ((List) (obj8)).iterator();
_L27:
        obj8 = obj9;
        if (!((Iterator) (obj10)).hasNext()) goto _L26; else goto _L25
_L25:
        obj8 = (LodgingReservation)((Iterator) (obj10)).next();
        if (obj8 != null)
        {
            if (obj8 == null)
            {
                throw new NullPointerException();
            }
            obj11 = SmartMailStoreUtils.toApiTime(((LodgingReservation) (obj8)).checkinDate);
            obj14 = SmartMailStoreUtils.toApiTime(((LodgingReservation) (obj8)).checkoutDate);
            obj15 = SmartMailStoreUtils.toApiOrganization(((LodgingReservation) (obj8)).lodging);
            obj8 = ((LodgingReservation) (obj8)).image;
            if (obj8 == null)
            {
                obj8 = null;
            } else
            {
                obj8 = new SmartMailImage(Platform.nullToEmpty(((Image) (obj8)).imageUrl), Platform.nullToEmpty(((Image) (obj8)).imageMetadataUrl));
            }
            ((List) (obj9)).add(new com.google.android.calendar.api.event.smartmail.LodgingReservation(((com.google.android.calendar.api.event.smartmail.SmartMailTime) (obj11)), ((com.google.android.calendar.api.event.smartmail.SmartMailTime) (obj14)), ((com.google.android.calendar.api.event.smartmail.Organization) (obj15)), ((SmartMailImage) (obj8))));
        }
          goto _L27
          goto _L26
_L13:
        obj10 = new ArrayList(((List) (obj11)).size());
        flag = false;
_L30:
        obj9 = obj10;
        if (flag >= ((List) (obj11)).size()) goto _L29; else goto _L28
_L28:
        obj9 = (Event2)((List) (obj11)).get(flag);
        if (obj9 != null)
        {
            ((List) (obj10)).add(SmartMailEventStoreUtils.toApiSmartMailEvent(((Event2) (obj9))));
        }
        flag++;
          goto _L30
          goto _L29
_L15:
        obj11 = new ArrayList(((List) (obj14)).size());
        flag = false;
_L33:
        obj10 = obj11;
        if (flag >= ((List) (obj14)).size()) goto _L32; else goto _L31
_L31:
        obj15 = (EventReservation)((List) (obj14)).get(flag);
        if (obj15 != null)
        {
            if (((EventReservation) (obj15)).seatingInformations == null || ((EventReservation) (obj15)).seatingInformations.isEmpty())
            {
                obj10 = Collections.emptyList();
            } else
            {
                obj10 = new ArrayList(((EventReservation) (obj15)).seatingInformations.size());
                l = 0;
                while (l < ((EventReservation) (obj15)).seatingInformations.size()) 
                {
                    obj16 = (EventReservationSeatingInformation)((EventReservation) (obj15)).seatingInformations.get(l);
                    if (obj16 != null)
                    {
                        ((List) (obj10)).add(new SeatingInformation(Platform.nullToEmpty(((EventReservationSeatingInformation) (obj16)).section), Platform.nullToEmpty(((EventReservationSeatingInformation) (obj16)).row), Platform.nullToEmpty(((EventReservationSeatingInformation) (obj16)).seat)));
                    }
                    l++;
                }
            }
            ((List) (obj11)).add(new com.google.android.calendar.api.event.smartmail.EventReservation(Platform.nullToEmpty(((EventReservation) (obj15)).reservationNumber), ((EventReservation) (obj15)).seatCount, SmartMailEventStoreUtils.toApiSmartMailEvent(((EventReservation) (obj15)).event), ((List) (obj10))));
        }
        flag++;
          goto _L33
          goto _L32
_L17:
        obj12 = new ArrayList(((List) (obj14)).size());
        flag = false;
_L36:
        obj11 = obj12;
        if (flag >= ((List) (obj14)).size()) goto _L35; else goto _L34
_L34:
        obj15 = (RestaurantReservation)((List) (obj14)).get(flag);
        if (obj15 == null)
        {
            throw new NullPointerException();
        }
        obj11 = ((RestaurantReservation) (obj15)).foodEstablishment;
        if (obj11 == null)
        {
            obj11 = null;
        } else
        {
            obj16 = SmartMailStoreUtils.toApiOrganization(((Restaurant) (obj11)).organization);
            obj11 = ((Restaurant) (obj11)).image;
            if (obj11 == null)
            {
                obj11 = null;
            } else
            {
                obj11 = new SmartMailImage(Platform.nullToEmpty(((Image) (obj11)).imageUrl), Platform.nullToEmpty(((Image) (obj11)).imageMetadataUrl));
            }
            obj11 = new com.google.android.calendar.api.event.smartmail.Restaurant(((com.google.android.calendar.api.event.smartmail.Organization) (obj16)), ((SmartMailImage) (obj11)));
        }
        obj11 = new com.google.android.calendar.api.event.smartmail.RestaurantReservation(((com.google.android.calendar.api.event.smartmail.Restaurant) (obj11)), ((RestaurantReservation) (obj15)).partySize, SmartMailStoreUtils.toApiTime(((RestaurantReservation) (obj15)).startTime));
        if (obj11 != null)
        {
            ((List) (obj12)).add(obj11);
        }
        flag++;
          goto _L36
          goto _L35
        ((FeatureConfig)obj9).notify_guests();
        obj9 = GooglePrivateData.GuestNotification.UNDECIDED;
        obj10 = Features.instance;
        if (obj10 == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj10).notify_guests();
        if (!cursor.isNull(42))
        {
            obj9 = GooglePrivateData.GuestNotification.getGuestNotificationFromInteger(cursor.getInt(42));
        }
        flag8 = false;
        flag9 = false;
        flag2 = flag8;
        flag1 = flag9;
        if (RemoteFeatureConfig.EVERYONE_DECLINED.enabled())
        {
            flag2 = flag8;
            flag1 = flag9;
            if (!flag7)
            {
                flag2 = flag8;
                flag1 = flag9;
                if (calendarlistentry.isPrimary())
                {
                    obj10 = EventExtrasFlags.fromCursor(cursor, 30);
                    if ((((EventExtrasFlags) (obj10)).flags & 0x800) != 0)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    if ((((EventExtrasFlags) (obj10)).flags & 0x2000) != 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                }
            }
        }
        obj9 = GooglePrivateData.create(((GooglePrivateData.GuestNotification) (obj9)), flag2, flag1);
        obj10 = s;
        if (s != null)
        {
            obj10 = ConferenceDescriptionUtils.removeAutoGeneratedDescription(s);
        }
        return new EventImpl(cpeventdescriptor, Platform.emptyToNull(cursor.getString(14)), Platform.emptyToNull(cursor.getString(19)), calendardescriptor, calendarlistentry, ((AttendeeDescriptor) (obj4)), j, s1, eventtiming.startMillisUtc, eventtiming.endMillisUtc, eventtiming.allDay, flag10, eventtiming.timeZoneId, eventtiming.endTimeZoneId, eventtiming.recurrence, eventtiming.firstStartMillisUtc, habitinstanceimpl, ((com.google.android.calendar.api.color.EventColor) (obj1)), EventUtil.checkVisibility(k), EventUtil.checkAvailability(i1), ((String) (obj10)), ((com.google.android.calendar.api.event.location.StructuredLocation) (obj2)), anotification, aattachment, aattendee, flag3, ((EventResponseSummary) (obj5)), flag4, flag5, flag6, as, ((EventSource) (obj6)), ((com.google.android.calendar.api.event.smartmail.SmartMailInfo) (obj)), ((com.google.android.calendar.api.event.userstatus.UserStatus) (obj3)), ((String) (obj11)), ((String) (obj12)), ((String) (obj7)), flag, ((String) (obj13)), ((String) (obj8)), flag7, ((GooglePrivateData) (obj9)));
        if (true) goto _L38; else goto _L37
_L37:
    }

    static CpEventDescriptor cursorToEventDescriptor(Cursor cursor, String as[])
        throws IOException
    {
        as = EventTiming.create(cursor, as);
        long l = cursor.getLong(41);
        long l1 = cursor.getLong(23);
        long l2 = cursor.getLong(24);
        return createEventDescriptor(l, ((EventTiming) (as)).startMillisUtc, l1, l2, cursor.getString(10));
    }

    private static int extractSequenceNumber(Map map, Cursor cursor)
    {
        map = (String)map.get("sequenceNumber");
        if (TextUtils.isEmpty(map))
        {
            break MISSING_BLOCK_LABEL_43;
        }
        int i = Integer.parseInt(map);
        return i;
        map;
        LogUtils.e(TAG, map, "Cannot parse sequence number", new Object[0]);
        return cursor.getInt(37);
    }

    static int habitInstanceStatusInferredToStoreFlag(boolean flag)
    {
        return !flag ? 0 : 512;
    }

    private static boolean hasExchangeCanceledOverride(Map map)
    {
        if (!map.containsKey("meeting_status"))
        {
            return false;
        }
        int i;
        try
        {
            i = Integer.parseInt((String)map.get("meeting_status"));
        }
        // Misplaced declaration of an exception variable
        catch (Map map)
        {
            LogUtils.e(TAG, map, "Could not parse meeting status", new Object[0]);
            return false;
        }
        return (i & 4) != 0;
    }

    public static long localId(Event event)
    {
        return ((CpEventKey)event.getDescriptor().getKey()).localId();
    }

    static long localId(EventDescriptor eventdescriptor)
    {
        return ((CpEventKey)eventdescriptor.getKey()).localId();
    }

}
