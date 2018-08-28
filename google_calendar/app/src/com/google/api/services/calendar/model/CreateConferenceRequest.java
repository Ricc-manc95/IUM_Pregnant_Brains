// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            ConferenceSolutionKey, ConferenceRequestStatus

public final class CreateConferenceRequest extends GenericJson
{

    public ConferenceSolutionKey conferenceSolutionKey;
    public String requestId;
    public ConferenceRequestStatus status;

    public CreateConferenceRequest()
    {
    }

    public final volatile GenericJson clone()
    {
        return (CreateConferenceRequest)clone();
    }

    public final volatile GenericData clone()
    {
        return (CreateConferenceRequest)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (CreateConferenceRequest)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (CreateConferenceRequest)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (CreateConferenceRequest)super.set(s, obj);
    }
}
