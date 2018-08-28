// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            EventAttendee

public static final class Groups extends GenericJson
{
    public static final class Groups extends GenericJson
    {

        public String email;

        public final volatile GenericJson clone()
        {
            return (Groups)clone();
        }

        public final volatile GenericData clone()
        {
            return (Groups)clone();
        }

        public final Object clone()
            throws CloneNotSupportedException
        {
            return (Groups)super.clone();
        }

        public final volatile GenericJson set(String s, Object obj)
        {
            return (Groups)set(s, obj);
        }

        public final GenericData set(String s, Object obj)
        {
            return (Groups)super.set(s, obj);
        }

        public Groups()
        {
        }
    }


    public List groups;
    public Boolean invitedExplicitly;

    public final volatile GenericJson clone()
    {
        return (Groups)clone();
    }

    public final volatile GenericData clone()
    {
        return (Groups)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Groups)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Groups)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Groups)super.set(s, obj);
    }

    static 
    {
        Data.nullOf(com/google/api/services/calendar/model/EventAttendee$MembershipInfo$Groups);
    }

    public Groups()
    {
    }
}
