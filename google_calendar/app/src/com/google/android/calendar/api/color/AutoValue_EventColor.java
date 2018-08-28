// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.color;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.color:
//            $AutoValue_EventColor, EventColor

final class AutoValue_EventColor extends $AutoValue_EventColor
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_EventColor(int i, String s, String s1)
    {
        super(i, s, s1);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(super.value);
        parcel.writeString(getKey());
        parcel.writeString(getName());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_EventColor(parcel.readInt(), parcel.readString(), parcel.readString());
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_EventColor[i];
        }

        _cls1()
        {
        }
    }

}
