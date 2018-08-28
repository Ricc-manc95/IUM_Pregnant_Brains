// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import android.os.Parcel;
import com.google.android.calendar.utils.datatypes.OptionalAdapter;
import com.google.common.base.Optional;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            $AutoValue_CreatedConference, CreatedConference, ConferenceData

final class AutoValue_CreatedConference extends $AutoValue_CreatedConference
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public static final OptionalAdapter OPTIONAL_ADAPTER = new OptionalAdapter();

    AutoValue_CreatedConference(ConferenceData conferencedata, Optional optional)
    {
        super(conferencedata, optional);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(conferenceData(), i);
        Optional optional = optionalConferenceDataBlob();
        if (optional.isPresent())
        {
            parcel.writeInt(1);
            parcel.writeValue(optional.get());
            return;
        } else
        {
            parcel.writeInt(0);
            return;
        }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            ConferenceData conferencedata = (ConferenceData)parcel.readParcelable(com/google/android/calendar/api/event/conference/ConferenceData.getClassLoader());
            if (parcel.readInt() == 0)
            {
                parcel = Absent.INSTANCE;
            } else
            {
                parcel = ((Parcel) (parcel.readValue(com/google/android/calendar/utils/datatypes/OptionalAdapter.getClassLoader())));
                if (parcel == null)
                {
                    parcel = Absent.INSTANCE;
                } else
                {
                    parcel = new Present(parcel);
                }
            }
            return new AutoValue_CreatedConference(conferencedata, parcel);
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_CreatedConference[i];
        }

        _cls1()
        {
        }
    }

}
