// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.common;


// Referenced classes of package io.opencensus.common:
//            Timestamp

public final class AutoValue_Timestamp extends Timestamp
{

    private final int nanos;
    private final long seconds;

    public AutoValue_Timestamp(long l, int i)
    {
        seconds = l;
        nanos = i;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof Timestamp)
            {
                if (seconds != ((Timestamp) (obj = (Timestamp)obj)).getSeconds() || nanos != ((Timestamp) (obj)).getNanos())
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

    public final int getNanos()
    {
        return nanos;
    }

    public final long getSeconds()
    {
        return seconds;
    }

    public final int hashCode()
    {
        return ((int)(seconds >>> 32 ^ seconds) ^ 0xf4243) * 0xf4243 ^ nanos;
    }

    public final String toString()
    {
        long l = seconds;
        int i = nanos;
        return (new StringBuilder(58)).append("Timestamp{seconds=").append(l).append(", nanos=").append(i).append("}").toString();
    }
}
