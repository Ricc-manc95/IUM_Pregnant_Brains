// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterMonth

final class AutoValue_AdapterMonth extends AdapterMonth
{

    private final ImmutableList days;

    AutoValue_AdapterMonth(ImmutableList immutablelist)
    {
        days = immutablelist;
    }

    public final boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj instanceof AdapterMonth)
        {
            obj = (AdapterMonth)obj;
            return days.equals(((AdapterMonth) (obj)).getDays());
        } else
        {
            return false;
        }
    }

    public final ImmutableList getDays()
    {
        return days;
    }

    public final int hashCode()
    {
        return 0xf4243 ^ days.hashCode();
    }

    public final String toString()
    {
        String s = String.valueOf(days);
        return (new StringBuilder(String.valueOf(s).length() + 19)).append("AdapterMonth{days=").append(s).append("}").toString();
    }
}
