// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import com.android.calendarcommon2.LogUtils;
import com.google.api.services.calendar.model.FlightReservation;
import com.google.api.services.calendar.model.FlightReservationFlightSegment;
import com.google.api.services.calendar.model.FlightReservationFlightSegmentFlightPassengerInfo;
import com.google.api.services.calendar.model.Image;
import com.google.api.services.calendar.model.Person;
import com.google.common.base.Platform;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.smartmail:
//            SmartMailStoreUtils, FlightEndpoint, FlightSegment, FlightPassenger, 
//            Person, SmartMailImage, FlightReservation

public class FlightReservationStoreUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/api/event/smartmail/FlightReservationStoreUtils);

    private FlightReservationStoreUtils()
    {
    }

    public static List convertFlightReservations(List list)
    {
        ArrayList arraylist;
        Iterator iterator;
        if (list == null || list.isEmpty())
        {
            return Collections.emptyList();
        }
        arraylist = new ArrayList(list.size());
        iterator = list.iterator();
_L21:
        FlightReservationFlightSegment flightreservationflightsegment;
        int j;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        FlightReservation flightreservation = (FlightReservation)iterator.next();
        if (flightreservation == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (flightreservation == null)
        {
            throw new NullPointerException();
        }
        if (flightreservation.flightSegments.isEmpty())
        {
            list = Collections.emptyList();
        } else
        {
            list = new ArrayList(flightreservation.flightSegments.size());
        }
        j = 0;
_L5:
        if (j >= flightreservation.flightSegments.size())
        {
            break MISSING_BLOCK_LABEL_966;
        }
        flightreservationflightsegment = (FlightReservationFlightSegment)flightreservation.flightSegments.get(j);
        if (flightreservationflightsegment == null) goto _L2; else goto _L1
_L1:
        Object obj;
        Object obj1;
        if (flightreservationflightsegment == null)
        {
            throw new NullPointerException();
        }
        if (flightreservationflightsegment == null)
        {
            throw new NullPointerException();
        }
        obj = SmartMailStoreUtils.toApiTime(flightreservationflightsegment.departureTime);
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = new FlightEndpoint(Platform.nullToEmpty(flightreservationflightsegment.departureAirportCode), Platform.nullToEmpty(flightreservationflightsegment.departureCity), Platform.nullToEmpty(flightreservationflightsegment.departureTerminal), Platform.nullToEmpty(flightreservationflightsegment.departureGate), ((SmartMailTime) (obj)), SmartMailStoreUtils.toApiTime(flightreservationflightsegment.actualDepartureTime));
        }
        if (flightreservationflightsegment == null)
        {
            throw new NullPointerException();
        }
        obj1 = SmartMailStoreUtils.toApiTime(flightreservationflightsegment.arrivalTime);
        if (obj1 == null)
        {
            obj1 = null;
        } else
        {
            obj1 = new FlightEndpoint(Platform.nullToEmpty(flightreservationflightsegment.arrivalAirportCode), Platform.nullToEmpty(flightreservationflightsegment.arrivalCity), Platform.nullToEmpty(flightreservationflightsegment.arrivalTerminal), Platform.nullToEmpty(flightreservationflightsegment.arrivalGate), ((SmartMailTime) (obj1)), SmartMailStoreUtils.toApiTime(flightreservationflightsegment.actualArrivalTime));
        }
        if (obj != null && obj1 != null) goto _L4; else goto _L3
_L3:
        obj = null;
_L8:
        Object obj2;
        int i;
        if (obj != null)
        {
            list.add(obj);
        } else
        {
            LogUtils.w(TAG, "Skipping segment because of missing data: %s", new Object[] {
                flightreservationflightsegment
            });
        }
_L2:
        j++;
          goto _L5
_L4:
        obj2 = flightreservationflightsegment.statusCode;
        if (obj2 != null) goto _L7; else goto _L6
_L6:
        i = 0;
_L19:
        String s = Platform.nullToEmpty(flightreservationflightsegment.bookingReference);
        String s1 = Platform.nullToEmpty(flightreservationflightsegment.airlineName);
        String s2 = Platform.nullToEmpty(flightreservationflightsegment.airlineCode);
        String s3 = Platform.nullToEmpty(flightreservationflightsegment.flightNumber);
        List list1 = flightreservationflightsegment.passengerInfos;
        Object obj3;
        if (list1 == null || list1.isEmpty())
        {
            obj2 = Collections.emptyList();
        } else
        {
            obj3 = new ArrayList(list1.size());
            int k = 0;
            while (k < list1.size()) 
            {
                FlightReservationFlightSegmentFlightPassengerInfo flightreservationflightsegmentflightpassengerinfo = (FlightReservationFlightSegmentFlightPassengerInfo)list1.get(k);
                if (flightreservationflightsegmentflightpassengerinfo != null)
                {
                    if (flightreservationflightsegmentflightpassengerinfo == null)
                    {
                        obj2 = null;
                    } else
                    {
                        obj2 = flightreservationflightsegmentflightpassengerinfo.passenger;
                        if (obj2 == null)
                        {
                            obj2 = null;
                        } else
                        {
                            obj2 = new com.google.android.calendar.api.event.smartmail.Person(Platform.nullToEmpty(((Person) (obj2)).name), Platform.nullToEmpty(((Person) (obj2)).firstName), Platform.nullToEmpty(((Person) (obj2)).lastName));
                        }
                        obj2 = new FlightPassenger(((com.google.android.calendar.api.event.smartmail.Person) (obj2)), Platform.nullToEmpty(flightreservationflightsegmentflightpassengerinfo.seatNumber));
                    }
                    ((List) (obj3)).add(obj2);
                }
                k++;
            }
            obj2 = obj3;
        }
        obj3 = flightreservationflightsegment.image;
        if (obj3 == null)
        {
            obj3 = null;
        } else
        {
            obj3 = new SmartMailImage(Platform.nullToEmpty(((Image) (obj3)).imageUrl), Platform.nullToEmpty(((Image) (obj3)).imageMetadataUrl));
        }
        obj = new FlightSegment(i, s, s1, s2, s3, ((List) (obj2)), ((SmartMailImage) (obj3)), ((FlightEndpoint) (obj)), ((FlightEndpoint) (obj1)));
          goto _L8
_L7:
        i = -1;
        ((String) (obj2)).hashCode();
        JVM INSTR lookupswitch 7: default 556
    //                   -1110061110: 653
    //                   -1012937396: 638
    //                   -284840886: 713
    //                   -160710483: 623
    //                   476588369: 683
    //                   1449033083: 698
    //                   1550348642: 668;
           goto _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16
_L9:
        break; /* Loop/switch isn't completed */
_L12:
        break MISSING_BLOCK_LABEL_713;
_L17:
        switch (i)
        {
        default:
            LogUtils.w(TAG, "unknown FlightSegment.StatusCode read from store: '%s'", new Object[] {
                obj2
            });
            i = 0;
            break;

        case 0: // '\0'
            i = 1;
            break;

        case 1: // '\001'
            i = 2;
            break;

        case 2: // '\002'
            i = 3;
            break;

        case 3: // '\003'
            i = 4;
            break;

        case 4: // '\004'
            i = 5;
            break;

        case 5: // '\005'
            i = 6;
            break;

        case 6: // '\006'
            i = 0;
            break;
        }
        continue; /* Loop/switch isn't completed */
_L13:
        if (((String) (obj2)).equals("scheduled"))
        {
            i = 0;
        }
          goto _L17
_L11:
        if (((String) (obj2)).equals("onTime"))
        {
            i = 1;
        }
          goto _L17
_L10:
        if (((String) (obj2)).equals("landed"))
        {
            i = 2;
        }
          goto _L17
_L16:
        if (((String) (obj2)).equals("delayed"))
        {
            i = 3;
        }
          goto _L17
_L14:
        if (((String) (obj2)).equals("cancelled"))
        {
            i = 4;
        }
          goto _L17
_L15:
        if (((String) (obj2)).equals("redirected"))
        {
            i = 5;
        }
          goto _L17
        if (((String) (obj2)).equals("unknown"))
        {
            i = 6;
        }
          goto _L17
        if (true) goto _L19; else goto _L18
_L18:
        arraylist.add(new com.google.android.calendar.api.event.smartmail.FlightReservation(list));
        if (true) goto _L21; else goto _L20
_L20:
        return arraylist;
    }

}
