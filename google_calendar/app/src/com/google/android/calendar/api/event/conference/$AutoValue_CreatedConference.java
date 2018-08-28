// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import com.google.common.base.Optional;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            CreatedConference, ConferenceData

abstract class $AutoValue_CreatedConference extends CreatedConference
{

    private final ConferenceData conferenceData;
    private final Optional optionalConferenceDataBlob;

    $AutoValue_CreatedConference(ConferenceData conferencedata, Optional optional)
    {
        if (conferencedata == null)
        {
            throw new NullPointerException("Null conferenceData");
        }
        conferenceData = conferencedata;
        if (optional == null)
        {
            throw new NullPointerException("Null optionalConferenceDataBlob");
        } else
        {
            optionalConferenceDataBlob = optional;
            return;
        }
    }

    public final ConferenceData conferenceData()
    {
        return conferenceData;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof CreatedConference)
            {
                if (!conferenceData.equals(((CreatedConference) (obj = (CreatedConference)obj)).conferenceData()) || !optionalConferenceDataBlob.equals(((CreatedConference) (obj)).optionalConferenceDataBlob()))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        return (conferenceData.hashCode() ^ 0xf4243) * 0xf4243 ^ optionalConferenceDataBlob.hashCode();
    }

    public final Optional optionalConferenceDataBlob()
    {
        return optionalConferenceDataBlob;
    }

    public String toString()
    {
        String s = String.valueOf(conferenceData);
        String s1 = String.valueOf(optionalConferenceDataBlob);
        return (new StringBuilder(String.valueOf(s).length() + 63 + String.valueOf(s1).length())).append("CreatedConference{conferenceData=").append(s).append(", optionalConferenceDataBlob=").append(s1).append("}").toString();
    }
}
