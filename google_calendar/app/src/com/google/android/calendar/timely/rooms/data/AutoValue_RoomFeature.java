// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            $AutoValue_RoomFeature, RoomFeature

public final class AutoValue_RoomFeature extends $AutoValue_RoomFeature
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_RoomFeature(String s, int i, int j)
    {
        super(s, i, j);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(getName());
        parcel.writeInt(getEquipmentType());
        parcel.writeInt(getDisplayProminence());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_RoomFeature(parcel.readString(), parcel.readInt(), parcel.readInt());
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_RoomFeature[i];
        }

        _cls1()
        {
        }
    }

}
