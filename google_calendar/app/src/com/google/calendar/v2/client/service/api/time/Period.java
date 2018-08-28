// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2.client.service.api.time;


public final class Period
{

    public final int days;
    public final int hours;
    public final long millis = 0L;
    public final int minutes = 0;
    public final int months = 0;
    public final int weeks = 0;
    public final int years = 0;

    public Period(int i, int j, int k, int l, int i1, int j1, long l1)
    {
        days = l;
        hours = i1;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof Period)
        {
            if (years == ((Period) (obj = (Period)obj)).years && months == ((Period) (obj)).months && weeks == ((Period) (obj)).weeks && days == ((Period) (obj)).days && hours == ((Period) (obj)).hours && minutes == ((Period) (obj)).minutes && millis == ((Period) (obj)).millis)
            {
                return true;
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return years + months + weeks + days + hours + minutes + (int)millis;
    }

    public final String toString()
    {
        com.google.common.base.MoreObjects.ToStringHelper tostringhelper = new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName());
        int i = years;
        com.google.common.base.MoreObjects.ToStringHelper.ValueHolder valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = String.valueOf(i);
        if ("Years" == null)
        {
            throw new NullPointerException();
        }
        valueholder.name = (String)"Years";
        i = months;
        valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = String.valueOf(i);
        if ("Months" == null)
        {
            throw new NullPointerException();
        }
        valueholder.name = (String)"Months";
        i = weeks;
        valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = String.valueOf(i);
        if ("Weeks" == null)
        {
            throw new NullPointerException();
        }
        valueholder.name = (String)"Weeks";
        i = days;
        valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = String.valueOf(i);
        if ("Days" == null)
        {
            throw new NullPointerException();
        }
        valueholder.name = (String)"Days";
        i = hours;
        valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = String.valueOf(i);
        if ("Hours" == null)
        {
            throw new NullPointerException();
        }
        valueholder.name = (String)"Hours";
        i = minutes;
        valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = String.valueOf(i);
        if ("Minutes" == null)
        {
            throw new NullPointerException();
        }
        valueholder.name = (String)"Minutes";
        long l = millis;
        valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
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
