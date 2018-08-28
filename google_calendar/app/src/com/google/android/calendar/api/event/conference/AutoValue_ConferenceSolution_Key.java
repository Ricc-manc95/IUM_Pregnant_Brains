// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            $AutoValue_ConferenceSolution_Key

public final class AutoValue_ConferenceSolution_Key extends $AutoValue_ConferenceSolution_Key
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_ConferenceSolution_Key(String s, ConferenceSolution.Key.AddOnId addonid)
    {
        super(s, addonid);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(getType());
        parcel.writeParcelable(getAddOnId(), i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_ConferenceSolution_Key(parcel.readString(), (ConferenceSolution.Key.AddOnId)parcel.readParcelable(com/google/android/calendar/api/event/conference/ConferenceSolution$Key$AddOnId.getClassLoader()));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_ConferenceSolution_Key[i];
        }

        _cls1()
        {
        }
    }

}
