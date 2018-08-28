// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;


public final class CalendarListFilterOptions
{

    public Boolean googleCalendarsOnly;
    public Boolean primary;
    public Boolean visible;
    public Boolean writable;

    public CalendarListFilterOptions()
    {
        googleCalendarsOnly = null;
        primary = null;
        visible = null;
        writable = null;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (CalendarListFilterOptions)obj;
            if (googleCalendarsOnly == null ? ((CalendarListFilterOptions) (obj)).googleCalendarsOnly != null : !googleCalendarsOnly.equals(((CalendarListFilterOptions) (obj)).googleCalendarsOnly))
            {
                return false;
            }
            if (primary == null ? ((CalendarListFilterOptions) (obj)).primary != null : !primary.equals(((CalendarListFilterOptions) (obj)).primary))
            {
                return false;
            }
            if (visible == null ? ((CalendarListFilterOptions) (obj)).visible != null : !visible.equals(((CalendarListFilterOptions) (obj)).visible))
            {
                return false;
            }
            if (writable != null)
            {
                return writable.equals(((CalendarListFilterOptions) (obj)).writable);
            }
            if (((CalendarListFilterOptions) (obj)).writable != null)
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        int l = 0;
        int i;
        int j;
        int k;
        if (googleCalendarsOnly != null)
        {
            i = googleCalendarsOnly.hashCode();
        } else
        {
            i = 0;
        }
        if (primary != null)
        {
            j = primary.hashCode();
        } else
        {
            j = 0;
        }
        if (visible != null)
        {
            k = visible.hashCode();
        } else
        {
            k = 0;
        }
        if (writable != null)
        {
            l = writable.hashCode();
        }
        return (k + (j + i * 31) * 31) * 31 + l;
    }

    final boolean isEmptyFilter()
    {
        return googleCalendarsOnly == null && primary == null && visible == null && writable == null;
    }
}
