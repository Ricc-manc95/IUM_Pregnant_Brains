// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            $AutoValue_CreateConferenceRequest, CreateConferenceRequest

public final class AutoValue_CreateConferenceRequest extends $AutoValue_CreateConferenceRequest
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public AutoValue_CreateConferenceRequest(String s, ConferenceSolution.Key key, CreateConferenceRequest.ConferenceRequestStatus conferencerequeststatus)
    {
        super(s, key, conferencerequeststatus);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(getRequestId());
        parcel.writeParcelable(getConferenceSolutionKey(), i);
        parcel.writeParcelable(getConferenceRequestStatus(), i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_CreateConferenceRequest(parcel.readString(), (ConferenceSolution.Key)parcel.readParcelable(com/google/android/calendar/api/event/conference/ConferenceSolution$Key.getClassLoader()), (CreateConferenceRequest.ConferenceRequestStatus)parcel.readParcelable(com/google/android/calendar/api/event/conference/CreateConferenceRequest$ConferenceRequestStatus.getClassLoader()));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_CreateConferenceRequest[i];
        }

        _cls1()
        {
        }
    }

}
