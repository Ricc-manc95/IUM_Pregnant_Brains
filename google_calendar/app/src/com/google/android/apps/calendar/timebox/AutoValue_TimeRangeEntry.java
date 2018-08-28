// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;


// Referenced classes of package com.google.android.apps.calendar.timebox:
//            TimeRangeEntry, TimeRange

public final class AutoValue_TimeRangeEntry extends TimeRangeEntry
{

    private final Object key;
    private final TimeRange range;
    private final Object value;

    public AutoValue_TimeRangeEntry(Object obj, Object obj1, TimeRange timerange)
    {
        if (obj == null)
        {
            throw new NullPointerException("Null key");
        }
        key = obj;
        if (obj1 == null)
        {
            throw new NullPointerException("Null value");
        }
        value = obj1;
        if (timerange == null)
        {
            throw new NullPointerException("Null range");
        } else
        {
            range = timerange;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof TimeRangeEntry)
            {
                if (!key.equals(((TimeRangeEntry) (obj = (TimeRangeEntry)obj)).getKey()) || !value.equals(((TimeRangeEntry) (obj)).getValue()) || !range.equals(((TimeRangeEntry) (obj)).getRange()))
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

    public final Object getKey()
    {
        return key;
    }

    public final TimeRange getRange()
    {
        return range;
    }

    public final Object getValue()
    {
        return value;
    }

    public final int hashCode()
    {
        return ((key.hashCode() ^ 0xf4243) * 0xf4243 ^ value.hashCode()) * 0xf4243 ^ range.hashCode();
    }

    public final String toString()
    {
        String s = String.valueOf(key);
        String s1 = String.valueOf(value);
        String s2 = String.valueOf(range);
        return (new StringBuilder(String.valueOf(s).length() + 36 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("TimeRangeEntry{key=").append(s).append(", value=").append(s1).append(", range=").append(s2).append("}").toString();
    }
}
