// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            RsvpActionUserResponse

public final class RsvpAction extends GenericJson
{

    public String additionalGuestField;
    public String commentField;
    public List rsvpResponses;
    public String selectedRsvpResponseType;
    public RsvpActionUserResponse userResponse;
    public String userResponseState;

    public RsvpAction()
    {
    }

    public final volatile GenericJson clone()
    {
        return (RsvpAction)clone();
    }

    public final volatile GenericData clone()
    {
        return (RsvpAction)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (RsvpAction)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (RsvpAction)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (RsvpAction)super.set(s, obj);
    }
}
