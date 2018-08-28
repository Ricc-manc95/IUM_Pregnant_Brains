// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.color;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.color:
//            $AutoValue_NamedCalendarColor, NamedCalendarColor

final class AutoValue_NamedCalendarColor extends $AutoValue_NamedCalendarColor
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_NamedCalendarColor(int i, int j, int k)
    {
        super(i, j, k);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(super.value);
        parcel.writeInt(getNamesArray());
        parcel.writeInt(getNameIndex());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_NamedCalendarColor(parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_NamedCalendarColor[i];
        }

        _cls1()
        {
        }
    }

}
