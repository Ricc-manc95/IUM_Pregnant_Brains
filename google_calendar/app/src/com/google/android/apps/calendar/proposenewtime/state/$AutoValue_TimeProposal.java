// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.state;


// Referenced classes of package com.google.android.apps.calendar.proposenewtime.state:
//            TimeProposal

abstract class $AutoValue_TimeProposal extends TimeProposal
{

    private final String comment;
    private final long endTimeMillis;
    private final long startTimeMillis;

    $AutoValue_TimeProposal(long l, long l1, String s)
    {
        startTimeMillis = l;
        endTimeMillis = l1;
        if (s == null)
        {
            throw new NullPointerException("Null comment");
        } else
        {
            comment = s;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof TimeProposal)
            {
                if (startTimeMillis != ((TimeProposal) (obj = (TimeProposal)obj)).getStartTimeMillis() || endTimeMillis != ((TimeProposal) (obj)).getEndTimeMillis() || !comment.equals(((TimeProposal) (obj)).getComment()))
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

    public final String getComment()
    {
        return comment;
    }

    public final long getEndTimeMillis()
    {
        return endTimeMillis;
    }

    public final long getStartTimeMillis()
    {
        return startTimeMillis;
    }

    public int hashCode()
    {
        return (((int)(startTimeMillis >>> 32 ^ startTimeMillis) ^ 0xf4243) * 0xf4243 ^ (int)(endTimeMillis >>> 32 ^ endTimeMillis)) * 0xf4243 ^ comment.hashCode();
    }

    public String toString()
    {
        long l = startTimeMillis;
        long l1 = endTimeMillis;
        String s = comment;
        return (new StringBuilder(String.valueOf(s).length() + 96)).append("TimeProposal{startTimeMillis=").append(l).append(", endTimeMillis=").append(l1).append(", comment=").append(s).append("}").toString();
    }
}
