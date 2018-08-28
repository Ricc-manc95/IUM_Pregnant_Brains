// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import android.os.Parcelable;
import com.google.common.base.Absent;
import com.google.common.base.Optional;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            AutoValue_CreatedConference, ConferenceData

public abstract class CreatedConference
    implements Parcelable
{

    public static final CreatedConference EMPTY;

    public CreatedConference()
    {
    }

    public static CreatedConference createdConference(ConferenceData conferencedata)
    {
        return new AutoValue_CreatedConference(conferencedata, Absent.INSTANCE);
    }

    public abstract ConferenceData conferenceData();

    public abstract Optional optionalConferenceDataBlob();

    static 
    {
        EMPTY = new AutoValue_CreatedConference(ConferenceData.EMPTY, Absent.INSTANCE);
    }
}
