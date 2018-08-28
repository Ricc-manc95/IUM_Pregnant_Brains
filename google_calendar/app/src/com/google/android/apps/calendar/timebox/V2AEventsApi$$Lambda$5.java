// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.calendar.v2a.shared.storage.proto.EventBundle;
import com.google.calendar.v2a.shared.storage.proto.GetEventResponse;
import com.google.caribou.smartmail.Address;
import com.google.caribou.smartmail.EventReservation;
import com.google.caribou.smartmail.FlightReservation;
import com.google.caribou.smartmail.Image;
import com.google.caribou.smartmail.LodgingReservation;
import com.google.caribou.smartmail.Organization;
import com.google.caribou.smartmail.Restaurant;
import com.google.caribou.smartmail.RestaurantReservation;
import com.google.caribou.smartmail.SmartMailInfo;
import com.google.common.base.Function;
import com.google.protos.calendar.feapi.v1.Event;
import com.google.protos.calendar.feapi.v1.PrivateEventData;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            V2AEventImageDetailsAdapter

public final class pter
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        Object obj6 = null;
        Object obj5 = null;
        Organization organization = null;
        Object obj4 = null;
        obj = (GetEventResponse)obj;
        if ((((GetEventResponse) (obj)).bitField0_ & 1) == 1)
        {
            if (((GetEventResponse) (obj)).event_ == null)
            {
                obj = EventBundle.DEFAULT_INSTANCE;
            } else
            {
                obj = ((GetEventResponse) (obj)).event_;
            }
            if (((EventBundle) (obj)).baseEvent_ == null)
            {
                obj = Event.DEFAULT_INSTANCE;
            } else
            {
                obj = ((EventBundle) (obj)).baseEvent_;
            }
        } else
        {
            obj = null;
        }
        if (obj != null)
        {
            Object obj1;
            if (((Event) (obj)).privateEventData_ == null)
            {
                obj1 = PrivateEventData.DEFAULT_INSTANCE;
            } else
            {
                obj1 = ((Event) (obj)).privateEventData_;
            }
            if ((((PrivateEventData) (obj1)).bitField0_ & 1) == 1)
            {
                Object obj2;
                if (((Event) (obj)).privateEventData_ == null)
                {
                    obj1 = PrivateEventData.DEFAULT_INSTANCE;
                } else
                {
                    obj1 = ((Event) (obj)).privateEventData_;
                }
                if (((PrivateEventData) (obj1)).smartMailInfo_ == null)
                {
                    obj2 = SmartMailInfo.DEFAULT_INSTANCE;
                } else
                {
                    obj2 = ((PrivateEventData) (obj1)).smartMailInfo_;
                }
                if (((SmartMailInfo) (obj2)).eventReservation_.ize() != 0 && (((EventReservation)((SmartMailInfo) (obj2)).eventReservation_.et(0)).bitField0_ & 4) == 4)
                {
                    obj1 = (EventReservation)((SmartMailInfo) (obj2)).eventReservation_.et(0);
                    if (((EventReservation) (obj1)).event_ == null)
                    {
                        obj1 = com.google.caribou.smartmail.Event.DEFAULT_INSTANCE;
                    } else
                    {
                        obj1 = ((EventReservation) (obj1)).event_;
                    }
                } else
                {
                    obj1 = null;
                }
                if (obj1 != null)
                {
                    if ((((com.google.caribou.smartmail.Event) (obj1)).bitField0_ & 0x800) == 2048)
                    {
                        if (((com.google.caribou.smartmail.Event) (obj1)).image_ == null)
                        {
                            obj2 = Image.DEFAULT_INSTANCE;
                        } else
                        {
                            obj2 = ((com.google.caribou.smartmail.Event) (obj1)).image_;
                        }
                    } else
                    {
                        obj2 = null;
                    }
                    if ((((com.google.caribou.smartmail.Event) (obj1)).bitField0_ & 0x100) == 256)
                    {
                        if (((com.google.caribou.smartmail.Event) (obj1)).address_ == null)
                        {
                            obj4 = Address.DEFAULT_INSTANCE;
                        } else
                        {
                            obj4 = ((com.google.caribou.smartmail.Event) (obj1)).address_;
                        }
                    }
                    return V2AEventImageDetailsAdapter.createEventImageDetails(((Event) (obj)), ((Image) (obj2)), ((Address) (obj4)), MailType.TICKET);
                }
                if (((SmartMailInfo) (obj2)).flightReservation_.ize() != 0 && ((FlightReservation)((SmartMailInfo) (obj2)).flightReservation_.et(0)).flightSegment_.ize() != 0)
                {
                    obj1 = (com.google.caribou.smartmail.tSegment)((FlightReservation)((SmartMailInfo) (obj2)).flightReservation_.et(0)).flightSegment_.et(0);
                } else
                {
                    obj1 = null;
                }
                if (obj1 != null)
                {
                    if (((com.google.caribou.smartmail.tSegment) (obj1)).image_ == null)
                    {
                        obj1 = Image.DEFAULT_INSTANCE;
                    } else
                    {
                        obj1 = ((com.google.caribou.smartmail.tSegment) (obj1)).image_;
                    }
                    return V2AEventImageDetailsAdapter.createEventImageDetails(((Event) (obj)), ((Image) (obj1)), null, MailType.FLIGHT);
                }
                if (((SmartMailInfo) (obj2)).lodgingReservation_.ize() != 0)
                {
                    obj4 = (LodgingReservation)((SmartMailInfo) (obj2)).lodgingReservation_.et(0);
                } else
                {
                    obj4 = null;
                }
                if (obj4 != null)
                {
                    if ((((LodgingReservation) (obj4)).bitField0_ & 0x20) == 32)
                    {
                        if (((LodgingReservation) (obj4)).image_ == null)
                        {
                            obj1 = Image.DEFAULT_INSTANCE;
                        } else
                        {
                            obj1 = ((LodgingReservation) (obj4)).image_;
                        }
                    } else
                    {
                        obj1 = null;
                    }
                    obj2 = obj6;
                    if ((((LodgingReservation) (obj4)).bitField0_ & 4) == 4)
                    {
                        if (((LodgingReservation) (obj4)).lodging_ == null)
                        {
                            obj2 = Organization.DEFAULT_INSTANCE;
                        } else
                        {
                            obj2 = ((LodgingReservation) (obj4)).lodging_;
                        }
                        if (((Organization) (obj2)).address_ == null)
                        {
                            obj2 = Address.DEFAULT_INSTANCE;
                        } else
                        {
                            obj2 = ((Organization) (obj2)).address_;
                        }
                    }
                    return V2AEventImageDetailsAdapter.createEventImageDetails(((Event) (obj)), ((Image) (obj1)), ((Address) (obj2)), MailType.HOTEL_RESERVATION);
                }
                if (((SmartMailInfo) (obj2)).restaurantReservation_.ize() != 0 && (((RestaurantReservation)((SmartMailInfo) (obj2)).restaurantReservation_.et(0)).bitField0_ & 2) == 2)
                {
                    obj1 = (RestaurantReservation)((SmartMailInfo) (obj2)).restaurantReservation_.et(0);
                    if (((RestaurantReservation) (obj1)).foodEstablishment_ == null)
                    {
                        obj1 = Restaurant.DEFAULT_INSTANCE;
                    } else
                    {
                        obj1 = ((RestaurantReservation) (obj1)).foodEstablishment_;
                    }
                } else
                {
                    obj1 = null;
                }
                if (obj1 != null)
                {
                    if (((Restaurant) (obj1)).image_ == null)
                    {
                        obj4 = Image.DEFAULT_INSTANCE;
                    } else
                    {
                        obj4 = ((Restaurant) (obj1)).image_;
                    }
                    obj2 = obj5;
                    if ((((Restaurant) (obj1)).bitField0_ & 2) == 2)
                    {
                        if (((Restaurant) (obj1)).organization_ == null)
                        {
                            organization = Organization.DEFAULT_INSTANCE;
                        } else
                        {
                            organization = ((Restaurant) (obj1)).organization_;
                        }
                        obj2 = obj5;
                        if ((organization.bitField0_ & 4) == 4)
                        {
                            if (((Restaurant) (obj1)).organization_ == null)
                            {
                                obj1 = Organization.DEFAULT_INSTANCE;
                            } else
                            {
                                obj1 = ((Restaurant) (obj1)).organization_;
                            }
                            if (((Organization) (obj1)).address_ == null)
                            {
                                obj2 = Address.DEFAULT_INSTANCE;
                            } else
                            {
                                obj2 = ((Organization) (obj1)).address_;
                            }
                        }
                    }
                    return V2AEventImageDetailsAdapter.createEventImageDetails(((Event) (obj)), ((Image) (obj4)), ((Address) (obj2)), MailType.RESTAURANT_RESERVATION);
                }
                if (((SmartMailInfo) (obj2)).event_.ize() != 0)
                {
                    obj4 = (com.google.caribou.smartmail.Event)((SmartMailInfo) (obj2)).event_.et(0);
                } else
                {
                    obj4 = null;
                }
                if (obj4 != null)
                {
                    Image image;
                    Object obj3;
                    if ((((com.google.caribou.smartmail.Event) (obj4)).bitField0_ & 0x800) == 2048)
                    {
                        if (((com.google.caribou.smartmail.Event) (obj4)).image_ == null)
                        {
                            image = Image.DEFAULT_INSTANCE;
                        } else
                        {
                            image = ((com.google.caribou.smartmail.Event) (obj4)).image_;
                        }
                    } else
                    {
                        image = null;
                    }
                    obj3 = organization;
                    if ((((com.google.caribou.smartmail.Event) (obj4)).bitField0_ & 0x100) == 256)
                    {
                        if (((com.google.caribou.smartmail.Event) (obj4)).address_ == null)
                        {
                            obj3 = Address.DEFAULT_INSTANCE;
                        } else
                        {
                            obj3 = ((com.google.caribou.smartmail.Event) (obj4)).address_;
                        }
                    }
                    return V2AEventImageDetailsAdapter.createEventImageDetails(((Event) (obj)), image, ((Address) (obj3)), MailType.INVITATION);
                }
            }
        }
        return V2AEventImageDetailsAdapter.createEventImageDetails(((Event) (obj)), null, null, null);
    }


    private pter()
    {
    }
}
