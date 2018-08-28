// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;


// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            SingleEventTime

abstract class $AutoValue_SingleEventTime extends SingleEventTime
{

    private final Boolean allDay;
    private final Long end;
    private final long start;

    $AutoValue_SingleEventTime(long l, Long long1, Boolean boolean1)
    {
        start = l;
        end = long1;
        allDay = boolean1;
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(obj instanceof SingleEventTime))
            {
                break MISSING_BLOCK_LABEL_94;
            }
            obj = (SingleEventTime)obj;
            if (start == ((SingleEventTime) (obj)).getStart() && (end != null ? end.equals(((SingleEventTime) (obj)).getEnd()) : ((SingleEventTime) (obj)).getEnd() == null))
            {
                break label0;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (allDay != null) goto _L4; else goto _L3
_L3:
        if (((SingleEventTime) (obj)).getAllDay() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!allDay.equals(((SingleEventTime) (obj)).getAllDay())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final Boolean getAllDay()
    {
        return allDay;
    }

    public final Long getEnd()
    {
        return end;
    }

    public final long getStart()
    {
        return start;
    }

    public int hashCode()
    {
        int j = 0;
        int k = (int)(start >>> 32 ^ start);
        int i;
        if (end == null)
        {
            i = 0;
        } else
        {
            i = end.hashCode();
        }
        if (allDay != null)
        {
            j = allDay.hashCode();
        }
        return (i ^ (k ^ 0xf4243) * 0xf4243) * 0xf4243 ^ j;
    }

    public String toString()
    {
        long l = start;
        String s = String.valueOf(end);
        String s1 = String.valueOf(allDay);
        return (new StringBuilder(String.valueOf(s).length() + 58 + String.valueOf(s1).length())).append("SingleEventTime{start=").append(l).append(", end=").append(s).append(", allDay=").append(s1).append("}").toString();
    }
}
