// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;


// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            RecurringTimes, AutoValue_RecurringTimes, SingleEventTime

public final class  extends 
{

    private Boolean considerExceptions;
    private SingleEventTime firstEventTime;
    private Integer recurrenceOption;
    private String recurrenceRule;
    private String timezone;

    public final RecurringTimes build()
    {
        String s1 = "";
        if (firstEventTime == null)
        {
            s1 = String.valueOf("").concat(" firstEventTime");
        }
        String s = s1;
        if (recurrenceRule == null)
        {
            s = String.valueOf(s1).concat(" recurrenceRule");
        }
        s1 = s;
        if (considerExceptions == null)
        {
            s1 = String.valueOf(s).concat(" considerExceptions");
        }
        s = s1;
        if (recurrenceOption == null)
        {
            s = String.valueOf(s1).concat(" recurrenceOption");
        }
        if (!s.isEmpty())
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Missing required properties:".concat(s);
            } else
            {
                s = new String("Missing required properties:");
            }
            throw new IllegalStateException(s);
        } else
        {
            return new AutoValue_RecurringTimes(firstEventTime, timezone, recurrenceRule, considerExceptions.booleanValue(), recurrenceOption.intValue());
        }
    }

    public final recurrenceOption setConsiderExceptions(boolean flag)
    {
        considerExceptions = Boolean.valueOf(flag);
        return this;
    }

    public final considerExceptions setFirstEventTime(SingleEventTime singleeventtime)
    {
        if (singleeventtime == null)
        {
            throw new NullPointerException("Null firstEventTime");
        } else
        {
            firstEventTime = singleeventtime;
            return this;
        }
    }

    public final firstEventTime setRecurrenceOption(int i)
    {
        recurrenceOption = Integer.valueOf(i);
        return this;
    }

    public final recurrenceOption setRecurrenceRule(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null recurrenceRule");
        } else
        {
            recurrenceRule = s;
            return this;
        }
    }

    public final recurrenceRule setTimezone(String s)
    {
        timezone = s;
        return this;
    }

    public ()
    {
    }

    (RecurringTimes recurringtimes)
    {
        firstEventTime = recurringtimes.getFirstEventTime();
        timezone = recurringtimes.getTimezone();
        recurrenceRule = recurringtimes.getRecurrenceRule();
        considerExceptions = Boolean.valueOf(recurringtimes.getConsiderExceptions());
        recurrenceOption = Integer.valueOf(recurringtimes.getRecurrenceOption());
    }
}
