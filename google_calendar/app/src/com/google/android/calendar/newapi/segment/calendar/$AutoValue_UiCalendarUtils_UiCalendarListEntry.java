// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.calendar;

import com.google.android.calendar.api.calendarlist.CalendarListEntry;

abstract class $AutoValue_UiCalendarUtils_UiCalendarListEntry extends UiCalendarUtils.UiCalendarListEntry
{

    public final String accountName;
    public final int color;
    public final String displayName;
    private final CalendarListEntry value;

    $AutoValue_UiCalendarUtils_UiCalendarListEntry(String s, String s1, int i, CalendarListEntry calendarlistentry)
    {
        if (s == null)
        {
            throw new NullPointerException("Null displayName");
        }
        displayName = s;
        if (s1 == null)
        {
            throw new NullPointerException("Null accountName");
        }
        accountName = s1;
        color = i;
        if (calendarlistentry == null)
        {
            throw new NullPointerException("Null value");
        } else
        {
            value = calendarlistentry;
            return;
        }
    }

    public final String accountName()
    {
        return accountName;
    }

    public final int color()
    {
        return color;
    }

    public final String displayName()
    {
        return displayName;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof UiCalendarUtils.UiCalendarListEntry)
            {
                if (!displayName.equals(((UiCalendarUtils.UiCalendarListEntry) (obj = (UiCalendarUtils.UiCalendarListEntry)obj)).displayName()) || !accountName.equals(((UiCalendarUtils.UiCalendarListEntry) (obj)).accountName()) || color != ((UiCalendarUtils.UiCalendarListEntry) (obj)).color() || !value.equals(((UiCalendarUtils.UiCalendarListEntry) (obj)).value()))
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
        return (((displayName.hashCode() ^ 0xf4243) * 0xf4243 ^ accountName.hashCode()) * 0xf4243 ^ color) * 0xf4243 ^ value.hashCode();
    }

    public String toString()
    {
        String s = displayName;
        String s1 = accountName;
        int i = color;
        String s2 = String.valueOf(value);
        return (new StringBuilder(String.valueOf(s).length() + 74 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("UiCalendarListEntry{displayName=").append(s).append(", accountName=").append(s1).append(", color=").append(i).append(", value=").append(s2).append("}").toString();
    }

    public final CalendarListEntry value()
    {
        return value;
    }
}
