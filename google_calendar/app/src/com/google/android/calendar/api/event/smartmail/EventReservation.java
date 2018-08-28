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
//            SmartMailEvent, SeatingInformation

public final class EventReservation
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final SmartMailEvent event;
    public final String reservationNumber;
    public final Integer seatCount;
    public final List seatingInformationList;

    EventReservation(Parcel parcel)
    {
        String s = parcel.readString();
        if (s == null)
        {
            throw new NullPointerException();
        }
        reservationNumber = (String)s;
        seatCount = (Integer)parcel.readValue(java/lang/Integer.getClassLoader());
        event = (SmartMailEvent)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailEvent.getClassLoader());
        parcel = parcel.createTypedArrayList(SeatingInformation.CREATOR);
        if (parcel.isEmpty())
        {
            parcel = Collections.emptyList();
        } else
        {
            parcel = Collections.unmodifiableList(parcel);
        }
        seatingInformationList = parcel;
    }

    public EventReservation(String s, Integer integer, SmartMailEvent smartmailevent, List list)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        reservationNumber = (String)s;
        seatCount = integer;
        event = smartmailevent;
        if (list.isEmpty())
        {
            s = Collections.emptyList();
        } else
        {
            s = Collections.unmodifiableList(new ArrayList(list));
        }
        seatingInformationList = s;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        boolean flag1 = false;
        if (this != obj) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        flag = flag1;
        if (obj == null) goto _L4; else goto _L3
_L3:
        flag = flag1;
        if (getClass() != obj.getClass()) goto _L4; else goto _L5
_L5:
        obj = (EventReservation)obj;
        flag = flag1;
        if (!reservationNumber.equals(((EventReservation) (obj)).reservationNumber)) goto _L4; else goto _L6
_L6:
        if (seatCount == null) goto _L8; else goto _L7
_L7:
        flag = flag1;
        if (!seatCount.equals(((EventReservation) (obj)).seatCount)) goto _L4; else goto _L9
_L9:
        if (event == null)
        {
            break MISSING_BLOCK_LABEL_120;
        }
        flag = flag1;
        if (!event.equals(((EventReservation) (obj)).event)) goto _L4; else goto _L10
_L10:
        return seatingInformationList.equals(((EventReservation) (obj)).seatingInformationList);
_L8:
        if (((EventReservation) (obj)).seatCount != null)
        {
            return false;
        }
          goto _L9
        if (((EventReservation) (obj)).event != null)
        {
            return false;
        }
          goto _L10
    }

    public final int hashCode()
    {
        int j = 0;
        int k = reservationNumber.hashCode();
        int i;
        if (seatCount != null)
        {
            i = seatCount.hashCode();
        } else
        {
            i = 0;
        }
        if (event != null)
        {
            j = event.hashCode();
        }
        return ((i + k * 31) * 31 + j) * 31 + seatingInformationList.hashCode();
    }

    public final String toString()
    {
        return String.format("EventReservation{reservationNumber='%s', seatCount=%s, event=%s, seatingInformationList=%s}", new Object[] {
            reservationNumber, seatCount, event, seatingInformationList
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(reservationNumber);
        parcel.writeValue(seatCount);
        parcel.writeParcelable(event, i);
        parcel.writeTypedList(seatingInformationList);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new EventReservation(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new EventReservation[i];
        }

        _cls1()
        {
        }
    }

}
