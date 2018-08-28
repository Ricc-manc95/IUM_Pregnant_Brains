// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            $AutoValue_CreateConferenceRequest_ConferenceRequestStatus

public final class AutoValue_CreateConferenceRequest_ConferenceRequestStatus extends $AutoValue_CreateConferenceRequest_ConferenceRequestStatus
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_CreateConferenceRequest_ConferenceRequestStatus(int i)
    {
        super(i);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(getStatusCode());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_CreateConferenceRequest_ConferenceRequestStatus(parcel.readInt());
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_CreateConferenceRequest_ConferenceRequestStatus[i];
        }

        _cls1()
        {
        }
    }

}
