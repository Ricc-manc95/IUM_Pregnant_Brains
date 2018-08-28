// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.battery;

import android.os.health.HealthStats;
import com.google.protobuf.nano.MessageNano;
import logs.proto.wireless.performance.mobile.nano.HashedString;
import logs.proto.wireless.performance.mobile.nano.ServiceHealthProto;

// Referenced classes of package com.google.android.libraries.performance.primes.battery:
//            HealthStatsProtos

final class ps extends ps
{

    public static final ent INSTANCE = new <init>();

    final MessageNano convert(String s, Object obj)
    {
        HealthStats healthstats = (HealthStats)obj;
        ServiceHealthProto servicehealthproto = new ServiceHealthProto();
        obj = HealthStatsProtos.getMeasurement(healthstats, 50001);
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = Integer.valueOf(((Long) (obj)).intValue());
        }
        servicehealthproto.startServiceCount = ((Integer) (obj));
        obj = HealthStatsProtos.getMeasurement(healthstats, 50002);
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = Integer.valueOf(((Long) (obj)).intValue());
        }
        servicehealthproto.launchCount = ((Integer) (obj));
        if (s == null)
        {
            s = null;
        } else
        {
            obj = new HashedString();
            obj.unhashedName = s;
            s = ((String) (obj));
        }
        servicehealthproto.name = s;
        if (HealthStatsProtos.isZero(servicehealthproto))
        {
            return null;
        } else
        {
            return servicehealthproto;
        }
    }

    final String nameOf(MessageNano messagenano)
    {
        return ((ServiceHealthProto)messagenano).name.unhashedName;
    }

    final MessageNano subtract(MessageNano messagenano, MessageNano messagenano1)
    {
        messagenano = (ServiceHealthProto)messagenano;
        messagenano1 = (ServiceHealthProto)messagenano1;
        if (messagenano == null || messagenano1 == null)
        {
            return messagenano;
        }
        ServiceHealthProto servicehealthproto = new ServiceHealthProto();
        servicehealthproto.name = ((ServiceHealthProto) (messagenano)).name;
        servicehealthproto.startServiceCount = HealthStatsProtos.subtract(((ServiceHealthProto) (messagenano)).startServiceCount, ((ServiceHealthProto) (messagenano1)).startServiceCount);
        servicehealthproto.launchCount = HealthStatsProtos.subtract(((ServiceHealthProto) (messagenano)).launchCount, ((ServiceHealthProto) (messagenano1)).launchCount);
        if (HealthStatsProtos.isZero(servicehealthproto))
        {
            return null;
        } else
        {
            return servicehealthproto;
        }
    }


    private ps()
    {
        super(logs/proto/wireless/performance/mobile/nano/ServiceHealthProto);
    }
}
