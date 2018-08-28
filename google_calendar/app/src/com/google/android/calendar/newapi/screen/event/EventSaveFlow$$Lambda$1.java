// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.syncadapters.timely.type.CalendarType;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.EventPermissionsFactory;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.AttendeeModifications;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.StructuredLocationModifications;
import com.google.android.calendar.api.event.location.StructuredLocationPermissions;
import com.google.android.calendar.api.event.notification.NotificationModifications;
import com.google.android.calendar.api.event.userstatus.AutoValue_UserStatus;
import com.google.android.calendar.api.event.userstatus.OutOfOffice;
import com.google.android.calendar.api.event.userstatus.UserStatus;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.calendar.newapi.common.ApiUtils;
import com.google.android.calendar.newapi.segment.room.RoomUtils;
import com.google.android.calendar.newapi.utils.LegacyUtils;
import com.google.android.calendar.time.CalendarUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.newapi.screen.event:
//            EventSaveFlow

final class arg._cls1
    implements Consumer
{

    private final EventSaveFlow arg$1;

    public final void accept(Object obj)
    {
        Object obj1;
        Object obj2;
        EventModifications eventmodifications;
        Object obj3;
        boolean flag4;
        flag4 = true;
        obj1 = arg$1;
        obj2 = (com.google.android.calendar.api.event.otification)obj;
        LatencyLoggerHolder.get().markAt(4);
        ApiUtils.setDefaultCalendar(((EventSaveFlow) (obj1)).event.getCalendarListEntry());
        obj3 = ((Fragment) (obj1)).getContext();
        eventmodifications = ((EventSaveFlow) (obj1)).event;
        if (!eventmodifications.isAllDayEvent() && eventmodifications.getTimeZoneId() == null)
        {
            eventmodifications.setTimeZoneId(TimeZone.getTimeZone(Utils.getTimeZoneId(((android.content.Context) (obj3)))).getID());
        }
        if (eventmodifications.getVisibility() == 3)
        {
            eventmodifications.setAvailability(1);
        }
        if (!eventmodifications.getAttendees().isEmpty() && eventmodifications.getAttendeeModifications().isModified() && CalendarType.calculateType(eventmodifications.getOrganizer().email) == 6)
        {
            obj = eventmodifications.getAttendeeModifications();
            AttendeeDescriptor attendeedescriptor = eventmodifications.getOrganizer();
            String s = attendeedescriptor.email;
            com.google.android.calendar.api.event.attendee.on on = new com.google.android.calendar.api.event.attendee.();
            on. = com.google.android.calendar.api.event.attendee.ACCEPTED;
            ((AttendeeModifications) (obj)).addAttendee(new Attendee(attendeedescriptor, s, 1, 1, new Response(on)));
        }
        if (eventmodifications.isDescriptionModified() && !TextUtils.isEmpty(eventmodifications.getDescription()))
        {
            eventmodifications.setDescription(eventmodifications.getDescription().trim());
        }
        if (!eventmodifications.getAttendeeModifications().isModified() && !eventmodifications.getLocationModification().isModified()) goto _L2; else goto _L1
_L1:
        obj = CalendarApi.EventPermissionsFactory.create(eventmodifications);
        if (((EventPermissions) (obj)).getStructuredLocationPermissions().canAddLocations() && ((EventPermissions) (obj)).getStructuredLocationPermissions().canRemoveLocation()) goto _L3; else goto _L2
_L2:
        obj = Features.instance;
        StructuredLocationModifications structuredlocationmodifications;
        Object obj4;
        boolean flag;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        if (((FeatureConfig)obj).ooo())
        {
            boolean flag1;
            if (eventmodifications != null && eventmodifications.getParticipantStatus() != null && eventmodifications.getParticipantStatus().getOutOfOffice() != null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1 && eventmodifications.isAllDayEvent())
            {
                obj = TimeZone.getTimeZone("UTC");
                TimeZone timezone = Utils.getTimeZone(((android.content.Context) (obj3)));
                obj3 = CalendarUtils.createTimeInNewTimeZoneRetainingFields(eventmodifications.getStartMillis(), ((TimeZone) (obj)), timezone);
                obj = CalendarUtils.createTimeInNewTimeZoneRetainingFields(eventmodifications.getEndMillis(), ((TimeZone) (obj)), timezone);
                eventmodifications.setToTimedWithTimes(((Calendar) (obj3)).getTimeInMillis(), ((Calendar) (obj)).getTimeInMillis());
            }
        }
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
          goto _L4
_L3:
        structuredlocationmodifications = eventmodifications.getLocationModification();
        obj = structuredlocationmodifications.getEventLocations().iterator();
        if (((Iterator) (obj)).hasNext())
        {
            obj = ((Iterator) (obj)).next();
        } else
        {
            obj = null;
        }
        obj4 = (EventLocation)obj;
        if (obj4 == null)
        {
            obj = RoomUtils.addRoomsToLocation(RoomUtils.removeRoomsFromLocation(null, RoomUtils.getOriginalRooms(eventmodifications)), RoomUtils.getRooms(eventmodifications));
            if (!TextUtils.isEmpty(((CharSequence) (obj))))
            {
                obj4 = new com.google.android.calendar.api.event.location.nit>();
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                obj4.me = (String)obj;
                structuredlocationmodifications.addEventLocation(new EventLocation(((com.google.android.calendar.api.event.location.) (obj4))));
            }
        } else
        {
            structuredlocationmodifications.removeEventLocation(((EventLocation) (obj4)));
            obj = new com.google.android.calendar.api.event.location.nit>(((EventLocation) (obj4)));
            obj4 = RoomUtils.addRoomsToLocation(RoomUtils.removeRoomsFromLocation(((EventLocation) (obj4)).name, RoomUtils.getOriginalRooms(eventmodifications)), RoomUtils.getRooms(eventmodifications));
            if (obj4 == null)
            {
                throw new NullPointerException();
            }
            obj.me = (String)obj4;
            obj = new EventLocation(((com.google.android.calendar.api.event.location.me) (obj)));
            if (LegacyUtils.isLegacyLocationOrEmpty(((EventLocation) (obj))) && TextUtils.isEmpty(((EventLocation) (obj)).name))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                structuredlocationmodifications.addEventLocation(((EventLocation) (obj)));
            }
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (!((FeatureConfig)obj).ooo()) goto _L6; else goto _L5
_L5:
        boolean flag2;
        if (eventmodifications != null && eventmodifications.getParticipantStatus() != null && eventmodifications.getParticipantStatus().getOutOfOffice() != null)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (flag2) goto _L7; else goto _L6
_L6:
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        break; /* Loop/switch isn't completed */
_L7:
        obj = eventmodifications.getParticipantStatus().getOutOfOffice();
        if (!((OutOfOffice) (obj)).isAutoDeclineEnabled() && ((OutOfOffice) (obj)).getCalendarDeclineMessage() != null)
        {
            eventmodifications.setParticipantStatus(new AutoValue_UserStatus(((OutOfOffice) (obj)).toBuilder().alendarDeclineMessage(null).d()));
        }
        if (true) goto _L6; else goto _L8
_L8:
        if (((FeatureConfig)obj).ooo())
        {
            boolean flag3;
            if (eventmodifications != null && eventmodifications.getParticipantStatus() != null && eventmodifications.getParticipantStatus().getOutOfOffice() != null)
            {
                flag3 = flag4;
            } else
            {
                flag3 = false;
            }
            if (flag3)
            {
                eventmodifications.getNotificationModifications().setNotifications(Collections.emptyList());
            }
        }
        if (((EventSaveFlow) (obj1)).event.isNewEvent())
        {
            obj = ((EventSaveFlow) (obj1)).eventClient.create(((EventSaveFlow) (obj1)).event, ((com.google.android.calendar.api.event.otification) (obj2)));
        } else
        {
            obj = ((EventSaveFlow) (obj1)).eventClient.update(((EventSaveFlow) (obj1)).event, ((EventSaveFlow) (obj1)).scope, ((com.google.android.calendar.api.event.otification) (obj2)));
        }
        obj1 = new n(((EventSaveFlow) (obj1)), ((com.google.android.calendar.api.event.otification) (obj2)));
        obj2 = CalendarExecutor.MAIN;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.(((java.util.concurrent.Future) (obj)), ((com.google.common.util.concurrent.FutureCallback) (obj1))), ((java.util.concurrent.Executor) (obj2)));
        return;
        if (true) goto _L2; else goto _L9
_L9:
    }

    ions(EventSaveFlow eventsaveflow)
    {
        arg$1 = eventsaveflow;
    }
}
