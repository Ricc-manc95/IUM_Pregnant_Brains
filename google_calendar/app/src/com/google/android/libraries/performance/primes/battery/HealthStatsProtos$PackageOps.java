// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.battery;

import android.os.health.HealthStats;
import com.google.protobuf.nano.MessageNano;
import logs.proto.wireless.performance.mobile.nano.HashedString;
import logs.proto.wireless.performance.mobile.nano.PackageHealthProto;

public final class ps extends ps
{

    public static final ps.subtract INSTANCE = new <init>();

    final MessageNano convert(String s, Object obj)
    {
        HealthStats healthstats = (HealthStats)obj;
        PackageHealthProto packagehealthproto = new PackageHealthProto();
        boolean flag;
        if (healthstats != null && healthstats.hasStats(40001))
        {
            obj = healthstats.getStats(40001);
        } else
        {
            obj = null;
        }
        packagehealthproto.statsServices = (logs.proto.wireless.performance.mobile.nano.ServiceHealthProto[])INSTANCE.convert(((java.util.Map) (obj)));
        if (healthstats != null && healthstats.hasMeasurements(40002))
        {
            obj = healthstats.getMeasurements(40002);
        } else
        {
            obj = null;
        }
        packagehealthproto.wakeupAlarmsCount = (logs.proto.wireless.performance.mobile.nano.Counter[])INSTANCE.convert(((java.util.Map) (obj)));
        if (s == null)
        {
            s = null;
        } else
        {
            obj = new HashedString();
            obj.unhashedName = s;
            s = ((String) (obj));
        }
        packagehealthproto.name = s;
        if (packagehealthproto.wakeupAlarmsCount == null)
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
            return packagehealthproto;
        }
    }

    final String nameOf(MessageNano messagenano)
    {
        return ((PackageHealthProto)messagenano).name.unhashedName;
    }

    final MessageNano subtract(MessageNano messagenano, MessageNano messagenano1)
    {
        PackageHealthProto packagehealthproto = (PackageHealthProto)messagenano;
        messagenano1 = (PackageHealthProto)messagenano1;
        if (packagehealthproto == null || messagenano1 == null)
        {
            return packagehealthproto;
        }
        messagenano = new PackageHealthProto();
        messagenano.name = packagehealthproto.name;
        logs.proto.wireless.performance.mobile.nano.ServiceHealthProto aservicehealthproto[] = packagehealthproto.statsServices;
        logs.proto.wireless.performance.mobile.nano.ServiceHealthProto aservicehealthproto1[] = ((PackageHealthProto) (messagenano1)).statsServices;
        messagenano.statsServices = (logs.proto.wireless.performance.mobile.nano.ServiceHealthProto[])INSTANCE.subtract(aservicehealthproto, aservicehealthproto1);
        logs.proto.wireless.performance.mobile.nano.Counter acounter[] = packagehealthproto.wakeupAlarmsCount;
        messagenano1 = ((PackageHealthProto) (messagenano1)).wakeupAlarmsCount;
        messagenano.wakeupAlarmsCount = (logs.proto.wireless.performance.mobile.nano.Counter[])INSTANCE.subtract(acounter, messagenano1);
        boolean flag;
        if (((PackageHealthProto) (messagenano)).wakeupAlarmsCount == null)
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
            return messagenano;
        }
    }


    private ps()
    {
        super(logs/proto/wireless/performance/mobile/nano/PackageHealthProto);
    }
}
