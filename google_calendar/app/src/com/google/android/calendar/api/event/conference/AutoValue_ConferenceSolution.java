// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            $AutoValue_ConferenceSolution, ConferenceSolution

public final class AutoValue_ConferenceSolution extends $AutoValue_ConferenceSolution
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_ConferenceSolution(ConferenceSolution.Key key, String s, String s1)
    {
        super(key, s, s1);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(getKey(), i);
        parcel.writeString(getName());
        parcel.writeString(getIconUri());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_ConferenceSolution((ConferenceSolution.Key)parcel.readParcelable(com/google/android/calendar/api/event/conference/ConferenceSolution$Key.getClassLoader()), parcel.readString(), parcel.readString());
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_ConferenceSolution[i];
        }

        _cls1()
        {
        }
    }

}
