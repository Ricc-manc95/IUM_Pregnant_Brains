// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;


// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            RecurringTimes, SingleEventTime

public static abstract class 
{

    public abstract RecurringTimes build();

    public abstract  setConsiderExceptions(boolean flag);

    public abstract  setFirstEventTime(SingleEventTime singleeventtime);

    public abstract  setRecurrenceOption(int i);

    public abstract  setRecurrenceRule(String s);

    public abstract  setTimezone(String s);

    public ()
    {
    }
}
