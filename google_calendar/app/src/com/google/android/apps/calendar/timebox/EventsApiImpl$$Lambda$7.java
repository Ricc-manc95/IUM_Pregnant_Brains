// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.apps.calendar.timely.store.TimelyStore;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.event.CpEventKey;
import com.google.android.syncadapters.calendar.timely.contract.TimelyEventData;
import com.google.api.services.calendar.model.Event2;
import com.google.api.services.calendar.model.EventReservation;
import com.google.api.services.calendar.model.FlightReservation;
import com.google.api.services.calendar.model.FlightReservationFlightSegment;
import com.google.api.services.calendar.model.LodgingReservation;
import com.google.api.services.calendar.model.Organization;
import com.google.api.services.calendar.model.Restaurant;
import com.google.api.services.calendar.model.RestaurantReservation;
import com.google.api.services.calendar.model.SmartMailInfo;
import java.util.Iterator;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            EventsApiImpl, EventImageDetailsAdapter

public final class arg._cls3
    implements Callable
{

    private final EventsApiImpl arg$1;
    private final CpEventKey arg$2;
    private final CalendarKey arg$3;

    public final Object call()
    {
        com.google.api.services.calendar.model.Image image = null;
        Object obj2 = null;
        Object obj = arg$1;
        Object obj1 = arg$2;
        Object obj3 = arg$3;
        obj3 = TimelyStore.acquire(((EventsApiImpl) (obj)).context).getTimelyEventData(((CpEventKey) (obj1)).localId(), ((CalendarKey) (obj3)));
        if (obj3 == null)
        {
            obj = null;
        } else
        {
            obj = ((TimelyEventData) (obj3)).smartMailInfo;
        }
        if (obj == null || ((SmartMailInfo) (obj)).eventReservations == null)
        {
            obj1 = null;
        } else
        {
            obj1 = ((SmartMailInfo) (obj)).eventReservations.iterator();
            if (((Iterator) (obj1)).hasNext())
            {
                obj1 = ((Iterator) (obj1)).next();
            } else
            {
                obj1 = null;
            }
            obj1 = (EventReservation)obj1;
        }
        if (obj1 == null)
        {
            obj1 = null;
        } else
        {
            obj1 = ((EventReservation) (obj1)).event;
        }
        if (obj1 != null)
        {
            return EventImageDetailsAdapter.createEventImageDetails(((TimelyEventData) (obj3)), ((Event2) (obj1)).image, ((Event2) (obj1)).address, ailType.TICKET);
        }
        if (obj == null || ((SmartMailInfo) (obj)).flightReservations == null)
        {
            obj1 = null;
        } else
        {
            obj1 = ((SmartMailInfo) (obj)).flightReservations.iterator();
            if (((Iterator) (obj1)).hasNext())
            {
                obj1 = ((Iterator) (obj1)).next();
            } else
            {
                obj1 = null;
            }
            obj1 = (FlightReservation)obj1;
        }
        if (obj1 == null)
        {
            obj1 = null;
        } else
        {
            obj1 = ((FlightReservation) (obj1)).flightSegments.iterator();
            if (((Iterator) (obj1)).hasNext())
            {
                obj1 = ((Iterator) (obj1)).next();
            } else
            {
                obj1 = null;
            }
            obj1 = (FlightReservationFlightSegment)obj1;
        }
        if (obj1 != null)
        {
            return EventImageDetailsAdapter.createEventImageDetails(((TimelyEventData) (obj3)), ((FlightReservationFlightSegment) (obj1)).image, null, ailType.FLIGHT);
        }
        if (obj == null || ((SmartMailInfo) (obj)).lodgingReservations == null)
        {
            obj1 = null;
        } else
        {
            obj1 = ((SmartMailInfo) (obj)).lodgingReservations.iterator();
            if (((Iterator) (obj1)).hasNext())
            {
                obj1 = ((Iterator) (obj1)).next();
            } else
            {
                obj1 = null;
            }
            obj1 = (LodgingReservation)obj1;
        }
        if (obj1 != null)
        {
            image = ((LodgingReservation) (obj1)).image;
            if (((LodgingReservation) (obj1)).lodging == null)
            {
                obj = obj2;
            } else
            {
                obj = ((LodgingReservation) (obj1)).lodging.address;
            }
            return EventImageDetailsAdapter.createEventImageDetails(((TimelyEventData) (obj3)), image, ((com.google.api.services.calendar.model.SmartMailAddress) (obj)), ailType.HOTEL_RESERVATION);
        }
        if (obj == null || ((SmartMailInfo) (obj)).restaurantReservations == null)
        {
            obj1 = null;
        } else
        {
            obj1 = ((SmartMailInfo) (obj)).restaurantReservations.iterator();
            if (((Iterator) (obj1)).hasNext())
            {
                obj1 = ((Iterator) (obj1)).next();
            } else
            {
                obj1 = null;
            }
            obj1 = (RestaurantReservation)obj1;
        }
        if (obj1 == null)
        {
            obj1 = null;
        } else
        {
            obj1 = ((RestaurantReservation) (obj1)).foodEstablishment;
        }
        if (obj1 != null)
        {
            com.google.api.services.calendar.model.Image image1 = ((Restaurant) (obj1)).image;
            if (((Restaurant) (obj1)).organization == null)
            {
                obj = image;
            } else
            {
                obj = ((Restaurant) (obj1)).organization.address;
            }
            return EventImageDetailsAdapter.createEventImageDetails(((TimelyEventData) (obj3)), image1, ((com.google.api.services.calendar.model.SmartMailAddress) (obj)), ailType.RESTAURANT_RESERVATION);
        }
        if (obj == null || ((SmartMailInfo) (obj)).events == null)
        {
            obj = null;
        } else
        {
            obj = ((SmartMailInfo) (obj)).events.iterator();
            if (((Iterator) (obj)).hasNext())
            {
                obj = ((Iterator) (obj)).next();
            } else
            {
                obj = null;
            }
            obj = (Event2)obj;
        }
        if (obj != null)
        {
            return EventImageDetailsAdapter.createEventImageDetails(((TimelyEventData) (obj3)), ((Event2) (obj)).image, ((Event2) (obj)).address, ailType.INVITATION);
        } else
        {
            return EventImageDetailsAdapter.createEventImageDetails(((TimelyEventData) (obj3)), null, null, null);
        }
    }

    public ment(EventsApiImpl eventsapiimpl, CpEventKey cpeventkey, CalendarKey calendarkey)
    {
        arg$1 = eventsapiimpl;
        arg$2 = cpeventkey;
        arg$3 = calendarkey;
    }
}
