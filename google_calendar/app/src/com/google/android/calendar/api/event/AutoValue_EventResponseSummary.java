// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event:
//            $AutoValue_EventResponseSummary, EventResponseSummary

final class AutoValue_EventResponseSummary extends $AutoValue_EventResponseSummary
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    AutoValue_EventResponseSummary(int i, int j, int k, int l)
    {
        super(i, j, k, l);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(getNumAccepted());
        parcel.writeInt(getNumDeclined());
        parcel.writeInt(getNumNeedAction());
        parcel.writeInt(getNumTentative());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_EventResponseSummary(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_EventResponseSummary[i];
        }

        _cls1()
        {
        }
    }

}
