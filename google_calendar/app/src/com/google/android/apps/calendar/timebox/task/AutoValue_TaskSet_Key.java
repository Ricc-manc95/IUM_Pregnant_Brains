// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.android.apps.calendar.timebox.TimeRange;

public final class AutoValue_TaskSet_Key extends TaskSet.Key
{

    private final String accountName;
    private final boolean done;
    private final TimeRange range;

    public AutoValue_TaskSet_Key(TimeRange timerange, String s, boolean flag)
    {
        if (timerange == null)
        {
            throw new NullPointerException("Null range");
        }
        range = timerange;
        if (s == null)
        {
            throw new NullPointerException("Null accountName");
        } else
        {
            accountName = s;
            done = flag;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof TaskSet.Key)
            {
                if (!range.equals(((TaskSet.Key) (obj = (TaskSet.Key)obj)).getRange()) || !accountName.equals(((TaskSet.Key) (obj)).getAccountName()) || done != ((TaskSet.Key) (obj)).isDone())
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

    final String getAccountName()
    {
        return accountName;
    }

    final TimeRange getRange()
    {
        return range;
    }

    public final int hashCode()
    {
        int i = range.hashCode();
        int j = accountName.hashCode();
        char c;
        if (done)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return c ^ ((i ^ 0xf4243) * 0xf4243 ^ j) * 0xf4243;
    }

    final boolean isDone()
    {
        return done;
    }

    public final String toString()
    {
        String s = String.valueOf(range);
        String s1 = accountName;
        boolean flag = done;
        return (new StringBuilder(String.valueOf(s).length() + 37 + String.valueOf(s1).length())).append("Key{range=").append(s).append(", accountName=").append(s1).append(", done=").append(flag).append("}").toString();
    }
}
