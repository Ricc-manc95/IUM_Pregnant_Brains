// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            $AutoValue_Conference, Conference

final class AutoValue_Conference extends $AutoValue_Conference
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_Conference(int i, String s, String s1, String s2, String s3, String s4, String s5, 
            String s6, String s7, String s8, String s9, String s10, int j)
    {
        super(i, s, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, j);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(getType());
        parcel.writeString(getUri());
        parcel.writeString(getName());
        parcel.writeString(getPassCode());
        parcel.writeString(getRegionCode());
        parcel.writeString(getAccessCode());
        parcel.writeString(getEntryPointType());
        parcel.writeString(getLabel());
        parcel.writeString(getMeetingCode());
        parcel.writeString(getPasscode());
        parcel.writeString(getPassword());
        parcel.writeString(getPin());
        parcel.writeInt(getGatewayAccess());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_Conference(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_Conference[i];
        }

        _cls1()
        {
        }
    }

}
