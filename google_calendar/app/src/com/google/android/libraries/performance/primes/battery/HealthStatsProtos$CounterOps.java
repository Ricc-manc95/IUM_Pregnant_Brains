// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.battery;

import com.google.protobuf.nano.MessageNano;
import logs.proto.wireless.performance.mobile.nano.Counter;
import logs.proto.wireless.performance.mobile.nano.HashedString;

// Referenced classes of package com.google.android.libraries.performance.primes.battery:
//            HealthStatsProtos

final class ps extends ps
{

    public static final ps INSTANCE = new <init>();

    final MessageNano convert(String s, Object obj)
    {
        int i = ((Long)obj).intValue();
        Counter counter = new Counter();
        counter.count = Integer.valueOf(i);
        boolean flag;
        if (s == null)
        {
            s = null;
        } else
        {
            obj = new HashedString();
            obj.unhashedName = s;
            s = ((String) (obj));
        }
        counter.name = s;
        s = counter.count;
        if (s == null || s.longValue() <= 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return null;
        } else
        {
            return counter;
        }
    }

    final String nameOf(MessageNano messagenano)
    {
        return ((Counter)messagenano).name.unhashedName;
    }

    final MessageNano subtract(MessageNano messagenano, MessageNano messagenano1)
    {
        boolean flag1 = true;
        messagenano = (Counter)messagenano;
        Counter counter = (Counter)messagenano1;
        if (messagenano == null || counter == null)
        {
            return messagenano;
        }
        messagenano1 = new Counter();
        messagenano1.name = ((Counter) (messagenano)).name;
        messagenano1.count = HealthStatsProtos.subtract(((Counter) (messagenano)).count, counter.count);
        messagenano = ((Counter) (messagenano1)).count;
        boolean flag;
        if (messagenano == null || messagenano.longValue() <= 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return null;
        } else
        {
            return messagenano1;
        }
    }


    private ps()
    {
        super(logs/proto/wireless/performance/mobile/nano/Counter);
    }
}
