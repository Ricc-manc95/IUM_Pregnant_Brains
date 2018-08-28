// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;


// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            RecurringTimes, SingleEventTime

abstract class $AutoValue_RecurringTimes extends RecurringTimes
{

    private final boolean considerExceptions;
    private final SingleEventTime firstEventTime;
    private final int recurrenceOption;
    private final String recurrenceRule;
    private final String timezone;

    $AutoValue_RecurringTimes(SingleEventTime singleeventtime, String s, String s1, boolean flag, int i)
    {
        if (singleeventtime == null)
        {
            throw new NullPointerException("Null firstEventTime");
        }
        firstEventTime = singleeventtime;
        timezone = s;
        if (s1 == null)
        {
            throw new NullPointerException("Null recurrenceRule");
        } else
        {
            recurrenceRule = s1;
            considerExceptions = flag;
            recurrenceOption = i;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof RecurringTimes)
            {
                if (!firstEventTime.equals(((RecurringTimes) (obj = (RecurringTimes)obj)).getFirstEventTime()) || (timezone != null ? !timezone.equals(((RecurringTimes) (obj)).getTimezone()) : ((RecurringTimes) (obj)).getTimezone() != null) || (!recurrenceRule.equals(((RecurringTimes) (obj)).getRecurrenceRule()) || considerExceptions != ((RecurringTimes) (obj)).getConsiderExceptions() || recurrenceOption != ((RecurringTimes) (obj)).getRecurrenceOption()))
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

    public final boolean getConsiderExceptions()
    {
        return considerExceptions;
    }

    public final SingleEventTime getFirstEventTime()
    {
        return firstEventTime;
    }

    public final int getRecurrenceOption()
    {
        return recurrenceOption;
    }

    public final String getRecurrenceRule()
    {
        return recurrenceRule;
    }

    public final String getTimezone()
    {
        return timezone;
    }

    public int hashCode()
    {
        int j = firstEventTime.hashCode();
        int i;
        char c;
        int k;
        if (timezone == null)
        {
            i = 0;
        } else
        {
            i = timezone.hashCode();
        }
        k = recurrenceRule.hashCode();
        if (considerExceptions)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return (c ^ ((i ^ (j ^ 0xf4243) * 0xf4243) * 0xf4243 ^ k) * 0xf4243) * 0xf4243 ^ recurrenceOption;
    }

    public final RecurringTimes.Builder toBuilder()
    {
        class Builder extends RecurringTimes.Builder
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

            public final RecurringTimes.Builder setConsiderExceptions(boolean flag)
            {
                considerExceptions = Boolean.valueOf(flag);
                return this;
            }

            public final RecurringTimes.Builder setFirstEventTime(SingleEventTime singleeventtime)
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

            public final RecurringTimes.Builder setRecurrenceOption(int i)
            {
                recurrenceOption = Integer.valueOf(i);
                return this;
            }

            public final RecurringTimes.Builder setRecurrenceRule(String s)
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

            public final RecurringTimes.Builder setTimezone(String s)
            {
                timezone = s;
                return this;
            }

            public Builder()
            {
            }

            Builder(RecurringTimes recurringtimes)
            {
                firstEventTime = recurringtimes.getFirstEventTime();
                timezone = recurringtimes.getTimezone();
                recurrenceRule = recurringtimes.getRecurrenceRule();
                considerExceptions = Boolean.valueOf(recurringtimes.getConsiderExceptions());
                recurrenceOption = Integer.valueOf(recurringtimes.getRecurrenceOption());
            }
        }

        return new Builder(this);
    }

    public String toString()
    {
        String s = String.valueOf(firstEventTime);
        String s1 = timezone;
        String s2 = recurrenceRule;
        boolean flag = considerExceptions;
        int i = recurrenceOption;
        return (new StringBuilder(String.valueOf(s).length() + 115 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("RecurringTimes{firstEventTime=").append(s).append(", timezone=").append(s1).append(", recurrenceRule=").append(s2).append(", considerExceptions=").append(flag).append(", recurrenceOption=").append(i).append("}").toString();
    }
}
