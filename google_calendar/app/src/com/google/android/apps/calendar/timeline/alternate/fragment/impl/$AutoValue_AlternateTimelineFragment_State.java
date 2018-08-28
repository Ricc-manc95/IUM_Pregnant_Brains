// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;


abstract class $AutoValue_AlternateTimelineFragment_State extends AlternateTimelineFragment.State
{

    private final Integer requestedJulianDay;
    private final com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType viewType;

    $AutoValue_AlternateTimelineFragment_State(com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType viewtype, Integer integer)
    {
        if (viewtype == null)
        {
            throw new NullPointerException("Null viewType");
        }
        viewType = viewtype;
        if (integer == null)
        {
            throw new NullPointerException("Null requestedJulianDay");
        } else
        {
            requestedJulianDay = integer;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof AlternateTimelineFragment.State)
            {
                if (!viewType.equals(((AlternateTimelineFragment.State) (obj = (AlternateTimelineFragment.State)obj)).viewType()) || !requestedJulianDay.equals(((AlternateTimelineFragment.State) (obj)).requestedJulianDay()))
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

    public int hashCode()
    {
        return (viewType.hashCode() ^ 0xf4243) * 0xf4243 ^ requestedJulianDay.hashCode();
    }

    final Integer requestedJulianDay()
    {
        return requestedJulianDay;
    }

    public String toString()
    {
        String s = String.valueOf(viewType);
        String s1 = String.valueOf(requestedJulianDay);
        return (new StringBuilder(String.valueOf(s).length() + 37 + String.valueOf(s1).length())).append("State{viewType=").append(s).append(", requestedJulianDay=").append(s1).append("}").toString();
    }

    final com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType viewType()
    {
        return viewType;
    }
}
