// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            TimeChangeProposal

public final class EventAttendee extends GenericJson
{
    public static final class MembershipInfo extends GenericJson
    {

        public List groups;
        public Boolean invitedExplicitly;

        public final volatile GenericJson clone()
        {
            return (MembershipInfo)clone();
        }

        public final volatile GenericData clone()
        {
            return (MembershipInfo)clone();
        }

        public final Object clone()
            throws CloneNotSupportedException
        {
            return (MembershipInfo)super.clone();
        }

        public final volatile GenericJson set(String s, Object obj)
        {
            return (MembershipInfo)set(s, obj);
        }

        public final GenericData set(String s, Object obj)
        {
            return (MembershipInfo)super.set(s, obj);
        }

        static 
        {
            Data.nullOf(com/google/api/services/calendar/model/EventAttendee$MembershipInfo$Groups);
        }

        public MembershipInfo()
        {
        }
    }

    public static final class MembershipInfo.Groups extends GenericJson
    {

        public String email;

        public final volatile GenericJson clone()
        {
            return (MembershipInfo.Groups)clone();
        }

        public final volatile GenericData clone()
        {
            return (MembershipInfo.Groups)clone();
        }

        public final Object clone()
            throws CloneNotSupportedException
        {
            return (MembershipInfo.Groups)super.clone();
        }

        public final volatile GenericJson set(String s, Object obj)
        {
            return (MembershipInfo.Groups)set(s, obj);
        }

        public final GenericData set(String s, Object obj)
        {
            return (MembershipInfo.Groups)super.set(s, obj);
        }

        public MembershipInfo.Groups()
        {
        }
    }


    public Integer additionalGuests;
    public String comment;
    public String displayName;
    public String email;
    public Boolean expandedGroup;
    public String id;
    public MembershipInfo membershipInfo;
    public Boolean optional;
    public Boolean organizer;
    public Boolean resource;
    public String responseStatus;
    public Boolean self;
    public TimeChangeProposal timeChangeProposal;

    public EventAttendee()
    {
    }

    public final volatile GenericJson clone()
    {
        return (EventAttendee)clone();
    }

    public final volatile GenericData clone()
    {
        return (EventAttendee)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (EventAttendee)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (EventAttendee)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (EventAttendee)super.set(s, obj);
    }
}
