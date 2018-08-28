// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.event.smartmail:
//            Restaurant, SmartMailTime

public final class RestaurantReservation
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final Restaurant foodEstablishment;
    public final Integer partySize;
    public final SmartMailTime startTime;

    RestaurantReservation(Parcel parcel)
    {
        this((Restaurant)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/Restaurant.getClassLoader()), (Integer)parcel.readValue(java/lang/Integer.getClassLoader()), (SmartMailTime)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailTime.getClassLoader()));
    }

    public RestaurantReservation(Restaurant restaurant, Integer integer, SmartMailTime smartmailtime)
    {
        foodEstablishment = restaurant;
        partySize = integer;
        startTime = smartmailtime;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (RestaurantReservation)obj;
            if (foodEstablishment == null ? ((RestaurantReservation) (obj)).foodEstablishment != null : !foodEstablishment.equals(((RestaurantReservation) (obj)).foodEstablishment))
            {
                return false;
            }
            if (partySize == null ? ((RestaurantReservation) (obj)).partySize != null : !partySize.equals(((RestaurantReservation) (obj)).partySize))
            {
                return false;
            }
            if (startTime != null)
            {
                return startTime.equals(((RestaurantReservation) (obj)).startTime);
            }
            if (((RestaurantReservation) (obj)).startTime != null)
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        int k = 0;
        int i;
        int j;
        if (foodEstablishment != null)
        {
            i = foodEstablishment.hashCode();
        } else
        {
            i = 0;
        }
        if (partySize != null)
        {
            j = partySize.hashCode();
        } else
        {
            j = 0;
        }
        if (startTime != null)
        {
            k = startTime.hashCode();
        }
        return (j + i * 31) * 31 + k;
    }

    public final String toString()
    {
        return String.format("RestaurantReservation{foodEstablishment=%s, partySize=%s, startTime=%s}", new Object[] {
            foodEstablishment, partySize, startTime
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(foodEstablishment, i);
        parcel.writeValue(partySize);
        parcel.writeParcelable(startTime, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new RestaurantReservation(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new RestaurantReservation[i];
        }

        _cls1()
        {
        }
    }

}
