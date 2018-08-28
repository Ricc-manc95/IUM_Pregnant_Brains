// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            Conference, ConferenceSolution, CreateConferenceRequest, ConferenceParameters

public final class ConferenceData extends GenericJson
{

    public String conferenceId;
    public ConferenceSolution conferenceSolution;
    public List conferences;
    public CreateConferenceRequest createRequest;
    public String notes;
    public ConferenceParameters parameters;
    public String signature;

    public ConferenceData()
    {
    }

    public final volatile GenericJson clone()
    {
        return (ConferenceData)clone();
    }

    public final volatile GenericData clone()
    {
        return (ConferenceData)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (ConferenceData)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (ConferenceData)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (ConferenceData)super.set(s, obj);
    }

    static 
    {
        Data.nullOf(com/google/api/services/calendar/model/Conference);
    }
}
