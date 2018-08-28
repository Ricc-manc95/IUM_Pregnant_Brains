// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;

import com.google.common.io.BaseEncoding;
import java.util.Arrays;

public final class TraceId
    implements Comparable
{

    public static final TraceId INVALID = new TraceId(new byte[16]);
    private final byte bytes[];

    private TraceId(byte abyte0[])
    {
        bytes = abyte0;
    }

    public final int compareTo(Object obj)
    {
        boolean flag = false;
        obj = (TraceId)obj;
        int i = 0;
        do
        {
label0:
            {
label1:
                {
                    byte byte0 = flag;
                    if (i < 16)
                    {
                        if (bytes[i] == ((TraceId) (obj)).bytes[i])
                        {
                            break label0;
                        }
                        if (bytes[i] >= ((TraceId) (obj)).bytes[i])
                        {
                            break label1;
                        }
                        byte0 = -1;
                    }
                    return byte0;
                }
                return 1;
            }
            i++;
        } while (true);
    }

    public final boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (!(obj instanceof TraceId))
        {
            return false;
        } else
        {
            obj = (TraceId)obj;
            return Arrays.equals(bytes, ((TraceId) (obj)).bytes);
        }
    }

    public final int hashCode()
    {
        return Arrays.hashCode(bytes);
    }

    public final String toString()
    {
        byte abyte0[] = bytes;
        String s = BaseEncoding.BASE16.lowerCase().encode(abyte0, 0, abyte0.length);
        return (new StringBuilder(String.valueOf(s).length() + 17)).append("TraceId{traceId=").append(s).append("}").toString();
    }

}
