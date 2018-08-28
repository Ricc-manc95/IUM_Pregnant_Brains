// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2.client.service.api.time;


public final class Duration
    implements Comparable
{

    public final long millis;

    public Duration(long l)
    {
        millis = l;
    }

    public final int compareTo(Object obj)
    {
        obj = (Duration)obj;
        return Long.signum(millis - ((Duration) (obj)).millis);
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof Duration)
        {
            if (millis == ((Duration) (obj = (Duration)obj)).millis)
            {
                return true;
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return (int)millis;
    }

    public final String toString()
    {
        com.google.common.base.MoreObjects.ToStringHelper tostringhelper = new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName());
        long l = millis;
        com.google.common.base.MoreObjects.ToStringHelper.ValueHolder valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = String.valueOf(l);
        if ("Millis" == null)
        {
            throw new NullPointerException();
        } else
        {
            valueholder.name = (String)"Millis";
            return tostringhelper.toString();
        }
    }
}
