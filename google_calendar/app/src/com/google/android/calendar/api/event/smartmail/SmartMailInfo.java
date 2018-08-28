// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.smartmail:
//            EmailMessage, SmartMailAction, FlightReservation, LodgingReservation, 
//            SmartMailEvent, EventReservation, RestaurantReservation

public class SmartMailInfo
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final List actions;
    private final List emailMessages;
    public final List eventReservations;
    public final List events;
    public final List flightReservations;
    public final List lodgingReservations;
    public final List restaurantReservations;

    SmartMailInfo(Parcel parcel)
    {
        Object obj = parcel.createTypedArrayList(EmailMessage.CREATOR);
        if (((List) (obj)).isEmpty())
        {
            obj = Collections.emptyList();
        } else
        {
            obj = Collections.unmodifiableList(((List) (obj)));
        }
        emailMessages = ((List) (obj));
        obj = parcel.createTypedArrayList(SmartMailAction.CREATOR);
        if (((List) (obj)).isEmpty())
        {
            obj = Collections.emptyList();
        } else
        {
            obj = Collections.unmodifiableList(((List) (obj)));
        }
        actions = ((List) (obj));
        obj = parcel.createTypedArrayList(FlightReservation.CREATOR);
        if (((List) (obj)).isEmpty())
        {
            obj = Collections.emptyList();
        } else
        {
            obj = Collections.unmodifiableList(((List) (obj)));
        }
        flightReservations = ((List) (obj));
        obj = parcel.createTypedArrayList(LodgingReservation.CREATOR);
        if (((List) (obj)).isEmpty())
        {
            obj = Collections.emptyList();
        } else
        {
            obj = Collections.unmodifiableList(((List) (obj)));
        }
        lodgingReservations = ((List) (obj));
        obj = parcel.createTypedArrayList(SmartMailEvent.CREATOR);
        if (((List) (obj)).isEmpty())
        {
            obj = Collections.emptyList();
        } else
        {
            obj = Collections.unmodifiableList(((List) (obj)));
        }
        events = ((List) (obj));
        obj = parcel.createTypedArrayList(EventReservation.CREATOR);
        if (((List) (obj)).isEmpty())
        {
            obj = Collections.emptyList();
        } else
        {
            obj = Collections.unmodifiableList(((List) (obj)));
        }
        eventReservations = ((List) (obj));
        parcel = parcel.createTypedArrayList(RestaurantReservation.CREATOR);
        if (parcel.isEmpty())
        {
            parcel = Collections.emptyList();
        } else
        {
            parcel = Collections.unmodifiableList(parcel);
        }
        restaurantReservations = parcel;
    }

    public SmartMailInfo(List list, List list1, List list2, List list3, List list4, List list5, List list6)
    {
        if (list.isEmpty())
        {
            list = Collections.emptyList();
        } else
        {
            list = Collections.unmodifiableList(new ArrayList(list));
        }
        emailMessages = list;
        if (list1.isEmpty())
        {
            list = Collections.emptyList();
        } else
        {
            list = Collections.unmodifiableList(new ArrayList(list1));
        }
        actions = list;
        if (list2.isEmpty())
        {
            list = Collections.emptyList();
        } else
        {
            list = Collections.unmodifiableList(new ArrayList(list2));
        }
        flightReservations = list;
        if (list3.isEmpty())
        {
            list = Collections.emptyList();
        } else
        {
            list = Collections.unmodifiableList(new ArrayList(list3));
        }
        lodgingReservations = list;
        if (list4.isEmpty())
        {
            list = Collections.emptyList();
        } else
        {
            list = Collections.unmodifiableList(new ArrayList(list4));
        }
        events = list;
        if (list5.isEmpty())
        {
            list = Collections.emptyList();
        } else
        {
            list = Collections.unmodifiableList(new ArrayList(list5));
        }
        eventReservations = list;
        if (list6.isEmpty())
        {
            list = Collections.emptyList();
        } else
        {
            list = Collections.unmodifiableList(new ArrayList(list6));
        }
        restaurantReservations = list;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag1 = false;
        boolean flag;
        if (this == obj)
        {
            flag = true;
        } else
        {
            flag = flag1;
            if (obj != null)
            {
                flag = flag1;
                if (getClass() == obj.getClass())
                {
                    obj = (SmartMailInfo)obj;
                    flag = flag1;
                    if (emailMessages.equals(((SmartMailInfo) (obj)).emailMessages))
                    {
                        flag = flag1;
                        if (actions.equals(((SmartMailInfo) (obj)).actions))
                        {
                            flag = flag1;
                            if (flightReservations.equals(((SmartMailInfo) (obj)).flightReservations))
                            {
                                flag = flag1;
                                if (lodgingReservations.equals(((SmartMailInfo) (obj)).lodgingReservations))
                                {
                                    flag = flag1;
                                    if (events.equals(((SmartMailInfo) (obj)).events))
                                    {
                                        flag = flag1;
                                        if (eventReservations.equals(((SmartMailInfo) (obj)).eventReservations))
                                        {
                                            return restaurantReservations.equals(((SmartMailInfo) (obj)).restaurantReservations);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    public int hashCode()
    {
        return (((((emailMessages.hashCode() * 31 + actions.hashCode()) * 31 + flightReservations.hashCode()) * 31 + lodgingReservations.hashCode()) * 31 + events.hashCode()) * 31 + eventReservations.hashCode()) * 31 + restaurantReservations.hashCode();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("SmartMailInfo{emailMessage=");
        if (!emailMessages.isEmpty())
        {
            stringbuilder.append(", emailMessages=").append(emailMessages);
        }
        if (!actions.isEmpty())
        {
            stringbuilder.append(", actions=").append(actions);
        }
        if (!flightReservations.isEmpty())
        {
            stringbuilder.append(", flightReservations=").append(flightReservations);
        }
        if (!lodgingReservations.isEmpty())
        {
            stringbuilder.append(", lodgingReservations=").append(lodgingReservations);
        }
        if (!events.isEmpty())
        {
            stringbuilder.append(", events=").append(events);
        }
        if (!eventReservations.isEmpty())
        {
            stringbuilder.append(", eventReservations=").append(eventReservations);
        }
        if (!restaurantReservations.isEmpty())
        {
            stringbuilder.append(", restaurantReservation=").append(restaurantReservations);
        }
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedList(emailMessages);
        parcel.writeTypedList(actions);
        parcel.writeTypedList(flightReservations);
        parcel.writeTypedList(lodgingReservations);
        parcel.writeTypedList(events);
        parcel.writeTypedList(eventReservations);
        parcel.writeTypedList(restaurantReservations);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new SmartMailInfo(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new SmartMailInfo[i];
        }

        _cls1()
        {
        }
    }

}
