// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            $AutoValue_ConferenceSolution_Key_AddOnId

public final class AutoValue_ConferenceSolution_Key_AddOnId extends $AutoValue_ConferenceSolution_Key_AddOnId
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_ConferenceSolution_Key_AddOnId(String s, String s1)
    {
        super(s, s1);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(getDeploymentId());
        parcel.writeString(getSolutionId());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_ConferenceSolution_Key_AddOnId(parcel.readString(), parcel.readString());
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_ConferenceSolution_Key_AddOnId[i];
        }

        _cls1()
        {
        }
    }

}
