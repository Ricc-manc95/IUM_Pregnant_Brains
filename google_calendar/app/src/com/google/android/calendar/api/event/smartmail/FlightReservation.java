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
//            FlightSegment

public final class FlightReservation
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final List segments;

    FlightReservation(Parcel parcel)
    {
        parcel = parcel.createTypedArrayList(FlightSegment.CREATOR);
        if (parcel.isEmpty())
        {
            parcel = Collections.emptyList();
        } else
        {
            parcel = Collections.unmodifiableList(parcel);
        }
        segments = parcel;
    }

    public FlightReservation(List list)
    {
        if (list.isEmpty())
        {
            list = Collections.emptyList();
        } else
        {
            list = Collections.unmodifiableList(new ArrayList(list));
        }
        segments = list;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        } else
        {
            obj = (FlightReservation)obj;
            return segments.equals(((FlightReservation) (obj)).segments);
        }
    }

    public final int hashCode()
    {
        return segments.hashCode();
    }

    public final String toString()
    {
        return String.format("FlightReservation{segments=%s}", new Object[] {
            segments
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedList(segments);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new FlightReservation(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new FlightReservation[i];
        }

        _cls1()
        {
        }
    }

}
