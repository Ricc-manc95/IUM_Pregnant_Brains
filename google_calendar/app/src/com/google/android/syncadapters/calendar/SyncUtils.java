// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.text.TextUtils;
import com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.calendar.api.event.userstatus.UserStatus;
import com.google.android.syncadapters.calendar.timely.contract.TimelyEventData;
import com.google.android.syncadapters.calendar.timely.userstatus.ParticipantStatusStoreUtils;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Event2;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventLocation;
import com.google.api.services.calendar.model.EventReservation;
import com.google.api.services.calendar.model.FlightReservation;
import com.google.api.services.calendar.model.FlightReservationFlightSegment;
import com.google.api.services.calendar.model.Image;
import com.google.api.services.calendar.model.LodgingReservation;
import com.google.api.services.calendar.model.Restaurant;
import com.google.api.services.calendar.model.RestaurantReservation;
import com.google.api.services.calendar.model.SmartMailInfo;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Present;
import com.google.common.collect.Iterables;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class SyncUtils
{

    public static EventExtrasFlags getEventExtrasFlagsValue(Event event, TimelyEventData timelyeventdata)
    {
        com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags.Builder builder;
        boolean flag3;
        flag3 = true;
        builder = EventExtrasFlags.builder();
        if (timelyeventdata == null) goto _L2; else goto _L1
_L1:
        Object obj;
        boolean flag2;
        class .Lambda._cls1
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls1();

            public final boolean apply(Object obj2)
            {
                return "declined".equals(((EventAttendee)obj2).responseStatus);
            }


            private .Lambda._cls1()
            {
            }
        }

        if (timelyeventdata.smartMailInfo != null)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        builder.setSmartMailAvailable(flag2);
        if (TextUtils.isEmpty(timelyeventdata.backgroundImageUrl)) goto _L4; else goto _L3
_L3:
        flag2 = true;
_L10:
        builder.setImageAvailable(flag2);
        if (timelyeventdata.getFirstEventLocation() != null)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        builder.setStructuredLocation(flag2);
        if (timelyeventdata.conferenceData != null)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        builder.setConferenceData(flag2);
        if (timelyeventdata.participantStatus != null)
        {
            builder.setParticipantStatus(true);
            timelyeventdata = ParticipantStatusStoreUtils.serializedProtoToApiObject(timelyeventdata.participantStatus);
            List list;
            boolean flag;
            if (timelyeventdata != null && timelyeventdata.getOutOfOffice() != null)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            builder.setOoo(flag2);
        }
_L2:
        if (event != null && event.endTimeUnspecified != null && event.endTimeUnspecified.booleanValue())
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        builder.setEndTimeUnspecified(flag2);
        if (!RemoteFeatureConfig.EVERYONE_DECLINED.enabled()) goto _L6; else goto _L5
_L5:
        if (event.attendees == null)
        {
            timelyeventdata = Collections.emptyList();
        } else
        {
            timelyeventdata = event.attendees;
            class .Lambda._cls0
                implements Predicate
            {

                public static final Predicate $instance = new .Lambda._cls0();

                public final boolean apply(Object obj2)
                {
                    boolean flag6 = false;
                    obj2 = (EventAttendee)obj2;
                    boolean flag4;
                    boolean flag5;
                    if (((EventAttendee) (obj2)).self == null || ((EventAttendee) (obj2)).self == Data.NULL_BOOLEAN)
                    {
                        flag5 = false;
                    } else
                    {
                        flag5 = ((EventAttendee) (obj2)).self.booleanValue();
                    }
                    flag4 = flag6;
                    if (!flag5)
                    {
                        if (((EventAttendee) (obj2)).resource == null || ((EventAttendee) (obj2)).resource == Data.NULL_BOOLEAN)
                        {
                            flag5 = false;
                        } else
                        {
                            flag5 = ((EventAttendee) (obj2)).resource.booleanValue();
                        }
                        flag4 = flag6;
                        if (!flag5)
                        {
                            flag4 = true;
                        }
                    }
                    return flag4;
                }


            private .Lambda._cls0()
            {
            }
            }

            obj = .Lambda._cls0..instance;
            if (timelyeventdata == null)
            {
                throw new NullPointerException();
            }
            if (obj == null)
            {
                throw new NullPointerException();
            }
            timelyeventdata = new com.google.common.collect.Iterables._cls4(timelyeventdata, ((Predicate) (obj)));
        }
        if (Iterables.isEmpty(timelyeventdata)) goto _L8; else goto _L7
_L7:
        obj = .Lambda._cls1..instance;
        timelyeventdata = timelyeventdata.iterator();
        if (obj == null)
        {
            throw new NullPointerException();
        }
          goto _L9
_L4:
label0:
        {
            obj = timelyeventdata.smartMailInfo;
            if (obj == null)
            {
                break label0;
            }
            list = ((SmartMailInfo) (obj)).eventReservations;
            if (list != null && list.size() > 0 && list.get(0) != null && ((EventReservation)list.get(0)).event != null)
            {
                obj = ((EventReservation)list.get(0)).event.image;
            } else
            {
                list = ((SmartMailInfo) (obj)).flightReservations;
                if (list != null && list.size() > 0 && ((FlightReservation)list.get(0)).flightSegments != null && ((FlightReservation)list.get(0)).flightSegments.size() > 0 && ((FlightReservation)list.get(0)).flightSegments.get(0) != null)
                {
                    obj = ((FlightReservationFlightSegment)((FlightReservation)list.get(0)).flightSegments.get(0)).image;
                } else
                {
                    list = ((SmartMailInfo) (obj)).lodgingReservations;
                    if (list != null && list.size() > 0 && list.get(0) != null)
                    {
                        obj = ((LodgingReservation)list.get(0)).image;
                    } else
                    {
                        list = ((SmartMailInfo) (obj)).restaurantReservations;
                        if (list != null && list.size() > 0 && list.get(0) != null && ((RestaurantReservation)list.get(0)).foodEstablishment != null)
                        {
                            obj = ((RestaurantReservation)list.get(0)).foodEstablishment.image;
                        } else
                        {
                            obj = ((SmartMailInfo) (obj)).events;
                            if (obj == null || ((List) (obj)).size() <= 0 || ((List) (obj)).get(0) == null)
                            {
                                break label0;
                            }
                            obj = ((Event2)((List) (obj)).get(0)).image;
                        }
                    }
                }
            }
        }
_L11:
        if (obj != null && ((Image) (obj)).imageUrl != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            flag2 = true;
        } else
        {
            obj = timelyeventdata.getFirstEventLocation();
            if (obj != null && ((EventLocation) (obj)).geo != null)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
        }
          goto _L10
        obj = null;
          goto _L11
_L9:
        if (!timelyeventdata.hasNext()) goto _L13; else goto _L12
_L12:
        if (((Predicate) (obj)).apply(timelyeventdata.next())) goto _L9; else goto _L14
_L14:
        boolean flag1 = false;
_L19:
        if (!flag1) goto _L8; else goto _L15
_L15:
        flag1 = true;
_L20:
        if (!flag1) goto _L17; else goto _L16
_L16:
        obj = event.attendees;
        class .Lambda._cls3
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls3();

            public final boolean apply(Object obj2)
            {
                obj2 = (EventAttendee)obj2;
                if (((EventAttendee) (obj2)).self == null || ((EventAttendee) (obj2)).self == Data.NULL_BOOLEAN)
                {
                    return false;
                } else
                {
                    return ((EventAttendee) (obj2)).self.booleanValue();
                }
            }


            private .Lambda._cls3()
            {
            }
        }

        timelyeventdata = .Lambda._cls3..instance;
        obj = ((Iterable) (obj)).iterator();
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (timelyeventdata == null)
        {
            throw new NullPointerException();
        }
          goto _L18
_L13:
        flag1 = true;
          goto _L19
_L8:
        flag1 = false;
          goto _L20
_L18:
        if (!((Iterator) (obj)).hasNext()) goto _L22; else goto _L21
_L21:
        Object obj1 = ((Iterator) (obj)).next();
        if (!timelyeventdata.apply(obj1)) goto _L18; else goto _L23
_L23:
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        timelyeventdata = new Present(obj1);
_L25:
        if (timelyeventdata.isPresent() && "declined".equals(((EventAttendee)timelyeventdata.get()).responseStatus))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1) goto _L17; else goto _L24
_L24:
        flag2 = true;
_L26:
        builder.setEveryoneDeclined(flag2);
        if (event.extendedProperties != null)
        {
            timelyeventdata = event.extendedProperties.private__;
        } else
        {
            timelyeventdata = null;
        }
        if (timelyeventdata == null)
        {
            break MISSING_BLOCK_LABEL_991;
        }
        timelyeventdata = (String)timelyeventdata.get("everyoneDeclinedDismissed");
        if (timelyeventdata == null || !timelyeventdata.equals(String.valueOf(event.sequence)))
        {
            break MISSING_BLOCK_LABEL_991;
        }
        flag2 = true;
_L27:
        builder.setEveryoneDeclinedDismissed(flag2);
_L6:
        timelyeventdata = Features.instance;
        if (timelyeventdata == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        break MISSING_BLOCK_LABEL_997;
_L22:
        timelyeventdata = Absent.INSTANCE;
          goto _L25
_L17:
        flag2 = false;
          goto _L26
        flag2 = false;
          goto _L27
        ((FeatureConfig)timelyeventdata).propose_new_time();
        event = event.attendees;
        if (event == null)
        {
            break MISSING_BLOCK_LABEL_1109;
        }
        class .Lambda._cls2
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls2();

            public final boolean apply(Object obj2)
            {
                obj2 = (EventAttendee)obj2;
                return ((EventAttendee) (obj2)).timeChangeProposal != null && ((EventAttendee) (obj2)).timeChangeProposal.startTimeMillis != null && ((EventAttendee) (obj2)).timeChangeProposal.endTimeMillis != null;
            }


            private .Lambda._cls2()
            {
            }
        }

        timelyeventdata = .Lambda._cls2..instance;
        obj = event.iterator();
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (timelyeventdata == null)
        {
            throw new NullPointerException();
        }
_L31:
        if (!((Iterator) (obj)).hasNext()) goto _L29; else goto _L28
_L28:
        event = ((Event) (((Iterator) (obj)).next()));
        if (!timelyeventdata.apply(event)) goto _L31; else goto _L30
_L30:
        if (event == null)
        {
            break MISSING_BLOCK_LABEL_1109;
        }
        flag2 = flag3;
_L32:
        builder.setHasTimeProposals(flag2);
        return new EventExtrasFlags(builder.flags);
_L29:
        event = null;
          goto _L30
        flag2 = false;
          goto _L32
    }
}
