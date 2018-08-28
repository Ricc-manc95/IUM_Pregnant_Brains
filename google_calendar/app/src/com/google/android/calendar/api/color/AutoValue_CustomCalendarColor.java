// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.color;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.color:
//            $AutoValue_CustomCalendarColor, CustomCalendarColor

public final class AutoValue_CustomCalendarColor extends $AutoValue_CustomCalendarColor
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_CustomCalendarColor(int i, String s)
    {
        super(i, s);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(getOriginalValue());
        parcel.writeString(getColorId());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_CustomCalendarColor(parcel.readInt(), parcel.readString());
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_CustomCalendarColor[i];
        }

        _cls1()
        {
        }
    }

}
