// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import java.util.Set;

final class AutoValue_EventRangedQueryHandler_QueryConfig extends EventRangedQueryHandler.QueryConfig
{

    private final Set getVisibleSyncedCalendarIds;
    private final boolean hideDeclined;

    AutoValue_EventRangedQueryHandler_QueryConfig(boolean flag, Set set)
    {
        hideDeclined = flag;
        if (set == null)
        {
            throw new NullPointerException("Null getVisibleSyncedCalendarIds");
        } else
        {
            getVisibleSyncedCalendarIds = set;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof EventRangedQueryHandler.QueryConfig)
            {
                if (hideDeclined != ((EventRangedQueryHandler.QueryConfig) (obj = (EventRangedQueryHandler.QueryConfig)obj)).hideDeclined() || !getVisibleSyncedCalendarIds.equals(((EventRangedQueryHandler.QueryConfig) (obj)).getVisibleSyncedCalendarIds()))
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

    final Set getVisibleSyncedCalendarIds()
    {
        return getVisibleSyncedCalendarIds;
    }

    public final int hashCode()
    {
        char c;
        if (hideDeclined)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return (c ^ 0xf4243) * 0xf4243 ^ getVisibleSyncedCalendarIds.hashCode();
    }

    final boolean hideDeclined()
    {
        return hideDeclined;
    }

    public final String toString()
    {
        boolean flag = hideDeclined;
        String s = String.valueOf(getVisibleSyncedCalendarIds);
        return (new StringBuilder(String.valueOf(s).length() + 61)).append("QueryConfig{hideDeclined=").append(flag).append(", getVisibleSyncedCalendarIds=").append(s).append("}").toString();
    }
}
