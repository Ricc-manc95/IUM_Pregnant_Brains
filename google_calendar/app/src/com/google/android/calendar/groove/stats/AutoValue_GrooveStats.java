// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove.stats;


// Referenced classes of package com.google.android.calendar.groove.stats:
//            GrooveStats

public final class AutoValue_GrooveStats extends GrooveStats
{

    private final int completedSessions;
    private final int totalSessions;

    public AutoValue_GrooveStats(int i, int j)
    {
        totalSessions = i;
        completedSessions = j;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof GrooveStats)
            {
                if (totalSessions != ((GrooveStats) (obj = (GrooveStats)obj)).getTotalSessions() || completedSessions != ((GrooveStats) (obj)).getCompletedSessions())
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

    public final int getCompletedSessions()
    {
        return completedSessions;
    }

    public final int getTotalSessions()
    {
        return totalSessions;
    }

    public final int hashCode()
    {
        return (totalSessions ^ 0xf4243) * 0xf4243 ^ completedSessions;
    }

    public final String toString()
    {
        int i = totalSessions;
        int j = completedSessions;
        return (new StringBuilder(69)).append("GrooveStats{totalSessions=").append(i).append(", completedSessions=").append(j).append("}").toString();
    }
}
