// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.calendar;

import android.accounts.Account;

abstract class $AutoValue_UiCalendarUtils_UiReminderCalendar extends UiCalendarUtils.UiReminderCalendar
{

    public final String accountName;
    public final int color;
    public final String displayName;
    private final Account value;

    $AutoValue_UiCalendarUtils_UiReminderCalendar(String s, String s1, int i, Account account)
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
        if (account == null)
        {
            throw new NullPointerException("Null value");
        } else
        {
            value = account;
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
            if (obj instanceof UiCalendarUtils.UiReminderCalendar)
            {
                if (!displayName.equals(((UiCalendarUtils.UiReminderCalendar) (obj = (UiCalendarUtils.UiReminderCalendar)obj)).displayName()) || !accountName.equals(((UiCalendarUtils.UiReminderCalendar) (obj)).accountName()) || color != ((UiCalendarUtils.UiReminderCalendar) (obj)).color() || !value.equals(((UiCalendarUtils.UiReminderCalendar) (obj)).value()))
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
        return (new StringBuilder(String.valueOf(s).length() + 73 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("UiReminderCalendar{displayName=").append(s).append(", accountName=").append(s1).append(", color=").append(i).append(", value=").append(s2).append("}").toString();
    }

    public final Account value()
    {
        return value;
    }
}
