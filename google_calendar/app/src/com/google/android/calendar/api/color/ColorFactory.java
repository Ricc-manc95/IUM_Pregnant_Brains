// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.color;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.api.color:
//            CalendarColor, EventColor, NamedCalendarColor

public interface ColorFactory
{

    public abstract CalendarColor createBirthdayColor(int i);

    public abstract CalendarColor createCalendarColor(String s, int i);

    public abstract EventColor createGoogleEventColor(String s);

    public abstract CalendarColor createHolidayColor(int i);

    public abstract CalendarColor createTaskColor(int i);

    public abstract NamedCalendarColor defaultHolidayColor();

    public abstract NamedCalendarColor defaultTaskColor();

    public abstract int getBirthdayColorKey(CalendarColor calendarcolor);

    public abstract String getCalendarColorKey(NamedCalendarColor namedcalendarcolor);

    public abstract ImmutableList getCalendarColorList();

    public abstract ImmutableList getEventColorList();
}
