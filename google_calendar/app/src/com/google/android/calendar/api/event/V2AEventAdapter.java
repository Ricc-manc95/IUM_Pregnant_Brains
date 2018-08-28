// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.event.location.StructuredLocationImpl;
import com.google.common.base.Predicate;
import com.google.protos.calendar.feapi.v1.Address;
import com.google.protos.calendar.feapi.v1.DateOrDateTime;
import com.google.protos.calendar.feapi.v1.DateTime;
import com.google.protos.calendar.feapi.v1.Event;
import com.google.protos.calendar.feapi.v1.EventLocation;
import com.google.protos.calendar.feapi.v1.GeoCoordinates;
import com.google.protos.calendar.feapi.v1.StructuredLocation;
import com.google.protos.calendar.feapi.v1.TimeChangeProposal;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

public final class V2AEventAdapter
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/api/event/V2AEventAdapter);

    private V2AEventAdapter()
    {
    }

    static final boolean bridge$lambda$0$V2AEventAdapter(com.google.protos.calendar.feapi.v1.Event.Attendee attendee)
    {
        TimeChangeProposal timechangeproposal;
        if (attendee.timeChangeProposal_ == null)
        {
            timechangeproposal = TimeChangeProposal.DEFAULT_INSTANCE;
        } else
        {
            timechangeproposal = attendee.timeChangeProposal_;
        }
        if (timechangeproposal != null)
        {
            if (attendee.timeChangeProposal_ == null)
            {
                timechangeproposal = TimeChangeProposal.DEFAULT_INSTANCE;
            } else
            {
                timechangeproposal = attendee.timeChangeProposal_;
            }
            if ((timechangeproposal.bitField0_ & 1) == 1)
            {
                if (attendee.timeChangeProposal_ == null)
                {
                    attendee = TimeChangeProposal.DEFAULT_INSTANCE;
                } else
                {
                    attendee = attendee.timeChangeProposal_;
                }
                if ((((TimeChangeProposal) (attendee)).bitField0_ & 2) == 2)
                {
                    return true;
                }
            }
        }
        return false;
    }

    static final com.google.android.calendar.api.event.location.EventLocation bridge$lambda$1$V2AEventAdapter(EventLocation eventlocation)
    {
        com.google.android.calendar.api.event.location.EventLocation.Builder builder = new com.google.android.calendar.api.event.location.EventLocation.Builder();
        if ((eventlocation.bitField0_ & 1) == 1)
        {
            String s = eventlocation.mapsClusterId_;
            if (s == null)
            {
                throw new NullPointerException();
            }
            builder.mapsClusterId = (String)s;
        }
        if ((eventlocation.bitField0_ & 2) == 2)
        {
            String s1 = eventlocation.placeId_;
            if (s1 == null)
            {
                throw new NullPointerException();
            }
            builder.placeId = (String)s1;
        }
        if ((eventlocation.bitField0_ & 4) == 4)
        {
            String s2 = eventlocation.name_;
            if (s2 == null)
            {
                throw new NullPointerException();
            }
            builder.name = (String)s2;
        }
        if ((eventlocation.bitField0_ & 8) == 8)
        {
            Object obj;
            com.google.android.calendar.api.event.location.Address.Builder builder1;
            if (eventlocation.address_ == null)
            {
                obj = Address.DEFAULT_INSTANCE;
            } else
            {
                obj = eventlocation.address_;
            }
            builder1 = new com.google.android.calendar.api.event.location.Address.Builder();
            if ((((Address) (obj)).bitField0_ & 1) == 1)
            {
                String s3 = ((Address) (obj)).formattedAddress_;
                if (s3 == null)
                {
                    throw new NullPointerException();
                }
                builder1.formattedAddress = (String)s3;
            }
            if ((((Address) (obj)).bitField0_ & 2) == 2)
            {
                String s4 = ((Address) (obj)).country_;
                if (s4 == null)
                {
                    throw new NullPointerException();
                }
                builder1.country = (String)s4;
            }
            if ((((Address) (obj)).bitField0_ & 4) == 4)
            {
                String s5 = ((Address) (obj)).locality_;
                if (s5 == null)
                {
                    throw new NullPointerException();
                }
                builder1.locality = (String)s5;
            }
            if ((((Address) (obj)).bitField0_ & 8) == 8)
            {
                String s6 = ((Address) (obj)).region_;
                if (s6 == null)
                {
                    throw new NullPointerException();
                }
                builder1.region = (String)s6;
            }
            if ((((Address) (obj)).bitField0_ & 0x10) == 16)
            {
                String s7 = ((Address) (obj)).postOfficeBoxNumber_;
                if (s7 == null)
                {
                    throw new NullPointerException();
                }
                builder1.postOfficeBoxNumber = (String)s7;
            }
            if ((((Address) (obj)).bitField0_ & 0x20) == 32)
            {
                String s8 = ((Address) (obj)).postalCode_;
                if (s8 == null)
                {
                    throw new NullPointerException();
                }
                builder1.postalCode = (String)s8;
            }
            if ((((Address) (obj)).bitField0_ & 0x40) == 64)
            {
                obj = ((Address) (obj)).streetAddress_;
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                builder1.streetAddress = (String)obj;
            }
            builder.address = new com.google.android.calendar.api.event.location.Address(builder1);
        }
        Object obj1;
        if ((eventlocation.bitField0_ & 0x10) == 16)
        {
            if (eventlocation.geo_ == null)
            {
                obj1 = GeoCoordinates.DEFAULT_INSTANCE;
            } else
            {
                obj1 = eventlocation.geo_;
            }
            builder.geo = new com.google.android.calendar.api.event.location.GeoCoordinates(((GeoCoordinates) (obj1)).latitude_, ((GeoCoordinates) (obj1)).longitude_);
        }
        if ((eventlocation.bitField0_ & 0x20) == 32)
        {
            obj1 = eventlocation.url_;
            if (obj1 == null)
            {
                throw new NullPointerException();
            }
            builder.url = (String)obj1;
        }
        if ((eventlocation.bitField0_ & 0x40) == 64)
        {
            builder.serverGeocoded = eventlocation.serverGeocoded_;
        }
        return new com.google.android.calendar.api.event.location.EventLocation(builder);
    }

    public static boolean everyoneDeclinedDismissed(Event event)
    {
        for (Iterator iterator = event.privateExtendedProperty_.iterator(); iterator.hasNext();)
        {
            com.google.protos.calendar.feapi.v1.Event.ExtendedProperty extendedproperty = (com.google.protos.calendar.feapi.v1.Event.ExtendedProperty)iterator.next();
            if ("everyoneDeclinedDismissed".equals(extendedproperty.name_))
            {
                return String.valueOf(event.sequence_).equals(extendedproperty.value_);
            }
        }

        return false;
    }

    public static boolean hasEveryoneDeclined(Event event)
    {
        Iterator iterator;
        boolean flag;
        boolean flag1;
        iterator = event.attendee_.iterator();
        flag = false;
        flag1 = false;
_L3:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        event = (com.google.protos.calendar.feapi.v1.Event.Attendee)iterator.next();
        if (!((com.google.protos.calendar.feapi.v1.Event.Attendee) (event)).self_ && !((com.google.protos.calendar.feapi.v1.Event.Attendee) (event)).resource_)
        {
            com.google.protos.calendar.feapi.v1.Event.Attendee.ResponseStatus responsestatus = com.google.protos.calendar.feapi.v1.Event.Attendee.ResponseStatus.forNumber(((com.google.protos.calendar.feapi.v1.Event.Attendee) (event)).responseStatus_);
            event = responsestatus;
            if (responsestatus == null)
            {
                event = com.google.protos.calendar.feapi.v1.Event.Attendee.ResponseStatus.NEEDS_ACTION;
            }
            if (event != com.google.protos.calendar.feapi.v1.Event.Attendee.ResponseStatus.DECLINED)
            {
                flag = true;
                flag1 = true;
            } else
            {
                flag1 = true;
            }
        }
        if (true) goto _L3; else goto _L2
_L2:
        return flag1 && !flag;
    }

    public static boolean hasTimeProposals(Event event)
    {
        event = event.attendee_;
        class .Lambda._cls1
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls1();

            public final boolean apply(Object obj)
            {
                return V2AEventAdapter.bridge$lambda$0$V2AEventAdapter((com.google.protos.calendar.feapi.v1.Event.Attendee)obj);
            }


            private .Lambda._cls1()
            {
            }
        }

        Predicate predicate = .Lambda._cls1..instance;
        Iterator iterator = event.iterator();
        if (iterator == null)
        {
            throw new NullPointerException();
        }
        if (predicate == null)
        {
            throw new NullPointerException();
        }
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_72;
            }
            event = ((Event) (iterator.next()));
        } while (!predicate.apply(event));
_L1:
        return event != null;
        event = null;
          goto _L1
    }

    public static com.google.android.calendar.api.event.attendee.Response.ResponseStatus toResponseStatus(com.google.protos.calendar.feapi.v1.Event.Attendee attendee)
    {
        com.google.protos.calendar.feapi.v1.Event.Attendee.ResponseStatus responsestatus2 = com.google.protos.calendar.feapi.v1.Event.Attendee.ResponseStatus.forNumber(attendee.responseStatus_);
        com.google.protos.calendar.feapi.v1.Event.Attendee.ResponseStatus responsestatus = responsestatus2;
        if (responsestatus2 == null)
        {
            responsestatus = com.google.protos.calendar.feapi.v1.Event.Attendee.ResponseStatus.NEEDS_ACTION;
        }
        switch (responsestatus.ordinal())
        {
        default:
            String s = TAG;
            com.google.protos.calendar.feapi.v1.Event.Attendee.ResponseStatus responsestatus1 = com.google.protos.calendar.feapi.v1.Event.Attendee.ResponseStatus.forNumber(attendee.responseStatus_);
            attendee = responsestatus1;
            if (responsestatus1 == null)
            {
                attendee = com.google.protos.calendar.feapi.v1.Event.Attendee.ResponseStatus.NEEDS_ACTION;
            }
            LogUtils.wtf(s, "Unhandled response status %s", new Object[] {
                attendee
            });
            return com.google.android.calendar.api.event.attendee.Response.ResponseStatus.TENTATIVE;

        case 3: // '\003'
            return com.google.android.calendar.api.event.attendee.Response.ResponseStatus.ACCEPTED;

        case 1: // '\001'
            return com.google.android.calendar.api.event.attendee.Response.ResponseStatus.DECLINED;

        case 0: // '\0'
            return com.google.android.calendar.api.event.attendee.Response.ResponseStatus.NEEDS_ACTION;

        case 2: // '\002'
            return com.google.android.calendar.api.event.attendee.Response.ResponseStatus.TENTATIVE;
        }
    }

    public static com.google.android.calendar.api.event.location.StructuredLocation toStructuredLocation(StructuredLocation structuredlocation)
    {
        structuredlocation = structuredlocation.location_;
        class .Lambda._cls2
            implements Function
        {

            public static final Function $instance = new .Lambda._cls2();

            public final Object apply(Object obj)
            {
                return V2AEventAdapter.bridge$lambda$1$V2AEventAdapter((EventLocation)obj);
            }


            private .Lambda._cls2()
            {
            }
        }

        Function function = .Lambda._cls2..instance;
        if (structuredlocation instanceof RandomAccess)
        {
            structuredlocation = new com.google.common.collect.Lists.TransformingRandomAccessList(structuredlocation, function);
        } else
        {
            structuredlocation = new com.google.common.collect.Lists.TransformingSequentialList(structuredlocation, function);
        }
        return new StructuredLocationImpl(structuredlocation);
    }

    public static long toUtcMillis(DateOrDateTime dateordatetime)
    {
        if ((dateordatetime.bitField0_ & 2) == 2)
        {
            if (dateordatetime.dateTime_ == null)
            {
                dateordatetime = DateTime.DEFAULT_INSTANCE;
            } else
            {
                dateordatetime = dateordatetime.dateTime_;
            }
            return ((DateTime) (dateordatetime)).timeMs_;
        } else
        {
            return dateordatetime.dateMs_;
        }
    }

    public static int toVisibility(com.google.protos.calendar.feapi.v1.Event.Visibility visibility)
    {
        switch (visibility.ordinal())
        {
        case 0: // '\0'
        default:
            return 0;

        case 2: // '\002'
        case 3: // '\003'
            return 2;

        case 1: // '\001'
            return 1;

        case 4: // '\004'
        case 5: // '\005'
            return 3;
        }
    }

}
