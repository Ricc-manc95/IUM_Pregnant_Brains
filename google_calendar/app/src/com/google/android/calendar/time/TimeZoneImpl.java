// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.time;

import com.google.calendar.v2.client.service.api.time.TimeZone;

public final class TimeZoneImpl
    implements TimeZone
{

    public java.util.TimeZone timeZone;

    public TimeZoneImpl(String s)
    {
        timeZone = java.util.TimeZone.getTimeZone(s);
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof TimeZoneImpl)
            {
                Object obj1 = (TimeZoneImpl)obj;
                obj = timeZone.getID();
                obj1 = ((TimeZoneImpl) (obj1)).timeZone.getID();
                if (obj != obj1 && (obj == null || !obj.equals(obj1)))
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

    public final String getId()
    {
        return timeZone.getID();
    }

    public final int hashCode()
    {
        if (timeZone.getID() == null)
        {
            return 0;
        } else
        {
            return timeZone.getID().hashCode();
        }
    }
}
