// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;

import java.util.Arrays;

public final class TraceOptions
{

    public static final TraceOptions DEFAULT = new TraceOptions((byte)0);
    public final byte options = 0;

    private TraceOptions(byte byte0)
    {
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof TraceOptions))
            {
                return false;
            }
            obj = (TraceOptions)obj;
            if (options != ((TraceOptions) (obj)).options)
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new byte[] {
            options
        });
    }

    public final String toString()
    {
        boolean flag;
        if ((options & 1) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return (new StringBuilder(27)).append("TraceOptions{sampled=").append(flag).append("}").toString();
    }

}
