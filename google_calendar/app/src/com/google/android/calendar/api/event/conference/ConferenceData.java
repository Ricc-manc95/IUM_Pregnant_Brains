// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import android.os.Parcelable;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            AutoValue_ConferenceData, CreateConferenceRequest, ConferenceSolution

public abstract class ConferenceData
    implements Parcelable
{

    public static final ConferenceData EMPTY = create(null, Collections.emptyList(), "", "", "", null);

    public ConferenceData()
    {
    }

    public static ConferenceData create(ConferenceSolution conferencesolution, List list, String s, String s1, String s2, CreateConferenceRequest createconferencerequest)
    {
        return new AutoValue_ConferenceData(conferencesolution, ImmutableList.copyOf(list), Platform.nullToEmpty(s), Platform.nullToEmpty(s1), Platform.nullToEmpty(s2), createconferencerequest);
    }

    public static ConferenceData create(List list)
    {
        if (list.isEmpty())
        {
            return EMPTY;
        } else
        {
            return new AutoValue_ConferenceData(null, ImmutableList.copyOf(list), Platform.nullToEmpty(""), Platform.nullToEmpty(""), Platform.nullToEmpty(""), null);
        }
    }

    public static ConferenceData createConferenceRequest(String s)
    {
        List list = Collections.emptyList();
        s = CreateConferenceRequest.newCreateRequest(s);
        return new AutoValue_ConferenceData(null, ImmutableList.copyOf(list), Platform.nullToEmpty(""), Platform.nullToEmpty(""), Platform.nullToEmpty(""), s);
    }

    public abstract String getConferenceId();

    public abstract ConferenceSolution getConferenceSolution();

    public abstract ImmutableList getConferences();

    public abstract CreateConferenceRequest getCreateConferenceRequest();

    public abstract String getNotes();

    public abstract String getSignature();

}
