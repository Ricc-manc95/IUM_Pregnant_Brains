// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.holidays;


final class AutoValue_HolidayViewModel_HolidayCalendar extends HolidayViewModel.HolidayCalendar
{

    private final String displayName;
    private final String id;

    AutoValue_HolidayViewModel_HolidayCalendar(String s, String s1)
    {
        if (s == null)
        {
            throw new NullPointerException("Null id");
        }
        id = s;
        if (s1 == null)
        {
            throw new NullPointerException("Null displayName");
        } else
        {
            displayName = s1;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof HolidayViewModel.HolidayCalendar)
            {
                if (!id.equals(((HolidayViewModel.HolidayCalendar) (obj = (HolidayViewModel.HolidayCalendar)obj)).getId()) || !displayName.equals(((HolidayViewModel.HolidayCalendar) (obj)).getDisplayName()))
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

    final String getDisplayName()
    {
        return displayName;
    }

    final String getId()
    {
        return id;
    }

    public final int hashCode()
    {
        return (id.hashCode() ^ 0xf4243) * 0xf4243 ^ displayName.hashCode();
    }

    public final String toString()
    {
        String s = id;
        String s1 = displayName;
        return (new StringBuilder(String.valueOf(s).length() + 34 + String.valueOf(s1).length())).append("HolidayCalendar{id=").append(s).append(", displayName=").append(s1).append("}").toString();
    }
}
