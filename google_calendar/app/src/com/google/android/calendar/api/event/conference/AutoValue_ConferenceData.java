// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import android.os.Parcel;
import com.google.android.calendar.utils.datatypes.ImmutableListAdapter;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            $AutoValue_ConferenceData, ConferenceData, ConferenceSolution, CreateConferenceRequest

final class AutoValue_ConferenceData extends $AutoValue_ConferenceData
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public static final ImmutableListAdapter IMMUTABLE_LIST_ADAPTER = new ImmutableListAdapter();

    AutoValue_ConferenceData(ConferenceSolution conferencesolution, ImmutableList immutablelist, String s, String s1, String s2, CreateConferenceRequest createconferencerequest)
    {
        super(conferencesolution, immutablelist, s, s1, s2, createconferencerequest);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(getConferenceSolution(), i);
        parcel.writeList(getConferences());
        parcel.writeString(getConferenceId());
        parcel.writeString(getNotes());
        parcel.writeString(getSignature());
        parcel.writeParcelable(getCreateConferenceRequest(), i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_ConferenceData((ConferenceSolution)parcel.readParcelable(com/google/android/calendar/api/event/conference/ConferenceSolution.getClassLoader()), ImmutableList.copyOf(parcel.readArrayList(com/google/android/calendar/utils/datatypes/ImmutableListAdapter.getClassLoader())), parcel.readString(), parcel.readString(), parcel.readString(), (CreateConferenceRequest)parcel.readParcelable(com/google/android/calendar/api/event/conference/CreateConferenceRequest.getClassLoader()));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_ConferenceData[i];
        }

        _cls1()
        {
        }
    }

}
