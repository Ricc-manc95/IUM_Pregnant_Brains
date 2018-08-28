// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            $AutoValue_RoomBookingFilterParams, RoomBookingFilterParams

public final class AutoValue_RoomBookingFilterParams extends $AutoValue_RoomBookingFilterParams
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_RoomBookingFilterParams(boolean flag, Integer integer)
    {
        super(flag, integer);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        if (showOnlyAvailable())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (getRecurrenceOption() == null)
        {
            parcel.writeInt(1);
            return;
        } else
        {
            parcel.writeInt(0);
            parcel.writeInt(getRecurrenceOption().intValue());
            return;
        }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            boolean flag = true;
            if (parcel.readInt() != 1)
            {
                flag = false;
            }
            if (parcel.readInt() == 0)
            {
                parcel = Integer.valueOf(parcel.readInt());
            } else
            {
                parcel = null;
            }
            return new AutoValue_RoomBookingFilterParams(flag, parcel);
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_RoomBookingFilterParams[i];
        }

        _cls1()
        {
        }
    }

}
