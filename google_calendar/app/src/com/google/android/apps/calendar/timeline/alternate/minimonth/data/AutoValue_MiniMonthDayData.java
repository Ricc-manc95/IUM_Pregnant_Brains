// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth.data;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth.data:
//            MiniMonthDayData

final class AutoValue_MiniMonthDayData extends MiniMonthDayData
{

    private final String getAlternateName;
    private final ImmutableList getColors;
    private final String getName;
    private final boolean hasOverflow;

    AutoValue_MiniMonthDayData(String s, String s1, ImmutableList immutablelist, boolean flag)
    {
        if (s == null)
        {
            throw new NullPointerException("Null getName");
        }
        getName = s;
        getAlternateName = s1;
        if (immutablelist == null)
        {
            throw new NullPointerException("Null getColors");
        } else
        {
            getColors = immutablelist;
            hasOverflow = flag;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof MiniMonthDayData)
            {
                if (!getName.equals(((MiniMonthDayData) (obj = (MiniMonthDayData)obj)).getName()) || (getAlternateName != null ? !getAlternateName.equals(((MiniMonthDayData) (obj)).getAlternateName()) : ((MiniMonthDayData) (obj)).getAlternateName() != null) || (!getColors.equals(((MiniMonthDayData) (obj)).getColors()) || hasOverflow != ((MiniMonthDayData) (obj)).hasOverflow()))
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

    public final String getAlternateName()
    {
        return getAlternateName;
    }

    public final ImmutableList getColors()
    {
        return getColors;
    }

    public final String getName()
    {
        return getName;
    }

    public final boolean hasOverflow()
    {
        return hasOverflow;
    }

    public final int hashCode()
    {
        int j = getName.hashCode();
        int i;
        char c;
        int k;
        if (getAlternateName == null)
        {
            i = 0;
        } else
        {
            i = getAlternateName.hashCode();
        }
        k = getColors.hashCode();
        if (hasOverflow)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return c ^ ((i ^ (j ^ 0xf4243) * 0xf4243) * 0xf4243 ^ k) * 0xf4243;
    }

    public final String toString()
    {
        String s = getName;
        String s1 = getAlternateName;
        String s2 = String.valueOf(getColors);
        boolean flag = hasOverflow;
        return (new StringBuilder(String.valueOf(s).length() + 76 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("MiniMonthDayData{getName=").append(s).append(", getAlternateName=").append(s1).append(", getColors=").append(s2).append(", hasOverflow=").append(flag).append("}").toString();
    }
}
