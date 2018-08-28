// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.battery;

import android.os.health.TimerStat;
import com.google.protobuf.nano.MessageNano;
import logs.proto.wireless.performance.mobile.nano.HashedString;
import logs.proto.wireless.performance.mobile.nano.Timer;

// Referenced classes of package com.google.android.libraries.performance.primes.battery:
//            HealthStatsProtos

public final class sOps extends sOps
{

    public static final sOps INSTANCE = new <init>();

    final MessageNano convert(String s, Object obj)
    {
        return HealthStatsProtos.timer(s, (TimerStat)obj);
    }

    final String nameOf(MessageNano messagenano)
    {
        messagenano = (Timer)messagenano;
        String s = ((Timer) (messagenano)).name.unhashedName;
        if (s != null)
        {
            return s;
        } else
        {
            return Long.toHexString(((Timer) (messagenano)).name.hash.longValue());
        }
    }

    final MessageNano subtract(MessageNano messagenano, MessageNano messagenano1)
    {
        return HealthStatsProtos.subtract((Timer)messagenano, (Timer)messagenano1);
    }


    private sOps()
    {
        super(logs/proto/wireless/performance/mobile/nano/Timer);
    }
}
