// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.state;

import android.os.Parcel;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.state:
//            $AutoValue_TimeProposal, TimeProposal

public final class AutoValue_TimeProposal extends $AutoValue_TimeProposal
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_TimeProposal(long l, long l1, String s)
    {
        super(l, l1, s);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(getStartTimeMillis());
        parcel.writeLong(getEndTimeMillis());
        parcel.writeString(getComment());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_TimeProposal(parcel.readLong(), parcel.readLong(), parcel.readString());
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_TimeProposal[i];
        }

        _cls1()
        {
        }
    }

}
