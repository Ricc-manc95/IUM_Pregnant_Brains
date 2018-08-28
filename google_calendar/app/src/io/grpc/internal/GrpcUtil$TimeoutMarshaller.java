// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.concurrent.TimeUnit;

// Referenced classes of package io.grpc.internal:
//            GrpcUtil

static final class 
    implements io.grpc.rshaller
{

    public final Object parseAsciiString(String s)
    {
        boolean flag;
        if (s.length() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("empty timeout"));
        }
        if (s.length() <= 9)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("bad timeout format"));
        }
        long l = Long.parseLong(s.substring(0, s.length() - 1));
        char c = s.charAt(s.length() - 1);
        switch (c)
        {
        default:
            throw new IllegalArgumentException(String.format("Invalid timeout unit: %s", new Object[] {
                Character.valueOf(c)
            }));

        case 110: // 'n'
            return Long.valueOf(l);

        case 117: // 'u'
            return Long.valueOf(TimeUnit.MICROSECONDS.toNanos(l));

        case 109: // 'm'
            return Long.valueOf(TimeUnit.MILLISECONDS.toNanos(l));

        case 83: // 'S'
            return Long.valueOf(TimeUnit.SECONDS.toNanos(l));

        case 77: // 'M'
            return Long.valueOf(TimeUnit.MINUTES.toNanos(l));

        case 72: // 'H'
            return Long.valueOf(TimeUnit.HOURS.toNanos(l));
        }
    }

    public final String toAsciiString(Object obj)
    {
        obj = (Long)obj;
        TimeUnit timeunit = TimeUnit.NANOSECONDS;
        if (((Long) (obj)).longValue() < 0L)
        {
            throw new IllegalArgumentException("Timeout too small");
        }
        if (((Long) (obj)).longValue() < 0x5f5e100L)
        {
            obj = String.valueOf(obj);
            return (new StringBuilder(String.valueOf(obj).length() + 1)).append(((String) (obj))).append("n").toString();
        }
        if (((Long) (obj)).longValue() < 0x174876e800L)
        {
            long l = timeunit.toMicros(((Long) (obj)).longValue());
            return (new StringBuilder(21)).append(l).append("u").toString();
        }
        if (((Long) (obj)).longValue() < 0x5af3107a4000L)
        {
            long l1 = timeunit.toMillis(((Long) (obj)).longValue());
            return (new StringBuilder(21)).append(l1).append("m").toString();
        }
        if (((Long) (obj)).longValue() < 0x16345785d8a0000L)
        {
            long l2 = timeunit.toSeconds(((Long) (obj)).longValue());
            return (new StringBuilder(21)).append(l2).append("S").toString();
        }
        if (((Long) (obj)).longValue() < 0x53444835ec580000L)
        {
            long l3 = timeunit.toMinutes(((Long) (obj)).longValue());
            return (new StringBuilder(21)).append(l3).append("M").toString();
        } else
        {
            long l4 = timeunit.toHours(((Long) (obj)).longValue());
            return (new StringBuilder(21)).append(l4).append("H").toString();
        }
    }

    ()
    {
    }
}
