// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2.client.service.api.time;


// Referenced classes of package com.google.calendar.v2.client.service.api.time:
//            Instant

public abstract class BaseInstant
    implements Instant
{

    public BaseInstant()
    {
    }

    public int compareTo(Object obj)
    {
        obj = (Instant)obj;
        if (obj == null)
        {
            return 1;
        } else
        {
            return Long.signum(getMillis() - ((Instant) (obj)).getMillis());
        }
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof Instant)
        {
            if (getMillis() == ((Instant) (obj = (Instant)obj)).getMillis() && getTimeZone().equals(((Instant) (obj)).getTimeZone()))
            {
                return true;
            }
        }
        return false;
    }

    public int hashCode()
    {
        return (int)getMillis();
    }

    public final boolean isAfter(Instant instant)
    {
        return instant == null || getMillis() > instant.getMillis();
    }
}
