// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.habit.HabitDescriptor;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventKey

final class AutoValue_HabitNotificationClient_HabitInstance extends HabitNotificationClient.HabitInstance
{

    private final HabitDescriptor descriptor;
    private final long endMillis;
    private final EventKey eventKey;
    private final boolean resolvedByFit;
    private final long startMillis;

    AutoValue_HabitNotificationClient_HabitInstance(HabitDescriptor habitdescriptor, EventKey eventkey, long l, long l1, boolean flag)
    {
        if (habitdescriptor == null)
        {
            throw new NullPointerException("Null descriptor");
        }
        descriptor = habitdescriptor;
        if (eventkey == null)
        {
            throw new NullPointerException("Null eventKey");
        } else
        {
            eventKey = eventkey;
            startMillis = l;
            endMillis = l1;
            resolvedByFit = flag;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof HabitNotificationClient.HabitInstance)
            {
                if (!descriptor.equals(((HabitNotificationClient.HabitInstance) (obj = (HabitNotificationClient.HabitInstance)obj)).getDescriptor()) || !eventKey.equals(((HabitNotificationClient.HabitInstance) (obj)).getEventKey()) || startMillis != ((HabitNotificationClient.HabitInstance) (obj)).getStartMillis() || endMillis != ((HabitNotificationClient.HabitInstance) (obj)).getEndMillis() || resolvedByFit != ((HabitNotificationClient.HabitInstance) (obj)).getResolvedByFit())
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

    public final HabitDescriptor getDescriptor()
    {
        return descriptor;
    }

    public final long getEndMillis()
    {
        return endMillis;
    }

    public final EventKey getEventKey()
    {
        return eventKey;
    }

    public final boolean getResolvedByFit()
    {
        return resolvedByFit;
    }

    public final long getStartMillis()
    {
        return startMillis;
    }

    public final int hashCode()
    {
        int i = descriptor.hashCode();
        int j = eventKey.hashCode();
        int k = (int)(startMillis >>> 32 ^ startMillis);
        int l = (int)(endMillis >>> 32 ^ endMillis);
        char c;
        if (resolvedByFit)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return c ^ ((((i ^ 0xf4243) * 0xf4243 ^ j) * 0xf4243 ^ k) * 0xf4243 ^ l) * 0xf4243;
    }

    public final String toString()
    {
        String s = String.valueOf(descriptor);
        String s1 = String.valueOf(eventKey);
        long l = startMillis;
        long l1 = endMillis;
        boolean flag = resolvedByFit;
        return (new StringBuilder(String.valueOf(s).length() + 124 + String.valueOf(s1).length())).append("HabitInstance{descriptor=").append(s).append(", eventKey=").append(s1).append(", startMillis=").append(l).append(", endMillis=").append(l1).append(", resolvedByFit=").append(flag).append("}").toString();
    }
}
