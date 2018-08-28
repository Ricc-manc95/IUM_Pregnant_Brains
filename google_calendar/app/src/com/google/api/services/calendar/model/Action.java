// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar.model:
//            AssignedId, BarcodeAction, ButtonAction, CallAction, 
//            DonateEmailAction, EmailAction, Time, ActionResponse, 
//            CalendarGoTo, IntentAction, OpenConversationInClusterAction, RsvpAction, 
//            ViewMapAction

public final class Action extends GenericJson
{

    public AssignedId assignedId;
    public BarcodeAction barcodeAction;
    public ButtonAction buttonAction;
    public CallAction callAction;
    public DonateEmailAction donateEmailAction;
    public EmailAction emailAction;
    public Time expirationTime;
    public ActionResponse externalServiceResponse;
    public CalendarGoTo goToAction;
    public IntentAction intentAction;
    public OpenConversationInClusterAction openConversationInClusterAction;
    public RsvpAction rsvpAction;
    public Time startTime;
    public ViewMapAction viewMapAction;

    public Action()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Action)clone();
    }

    public final volatile GenericData clone()
    {
        return (Action)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Action)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Action)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Action)super.set(s, obj);
    }
}
