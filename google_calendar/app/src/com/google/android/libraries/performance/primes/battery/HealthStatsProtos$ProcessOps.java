// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.battery;

import android.os.health.HealthStats;
import com.google.protobuf.nano.MessageNano;
import logs.proto.wireless.performance.mobile.nano.HashedString;
import logs.proto.wireless.performance.mobile.nano.ProcessHealthProto;

// Referenced classes of package com.google.android.libraries.performance.primes.battery:
//            HealthStatsProtos

public final class ps extends ps
{

    public static final ent INSTANCE = new <init>();

    final MessageNano convert(String s, Object obj)
    {
        obj = (HealthStats)obj;
        ProcessHealthProto processhealthproto = new ProcessHealthProto();
        processhealthproto.userTimeMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj)), 30001);
        processhealthproto.systemTimeMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj)), 30002);
        processhealthproto.startsCount = HealthStatsProtos.getMeasurement(((HealthStats) (obj)), 30003);
        processhealthproto.crashesCount = HealthStatsProtos.getMeasurement(((HealthStats) (obj)), 30004);
        processhealthproto.anrCount = HealthStatsProtos.getMeasurement(((HealthStats) (obj)), 30005);
        processhealthproto.foregroundMs = HealthStatsProtos.getMeasurement(((HealthStats) (obj)), 30006);
        if (s == null)
        {
            s = null;
        } else
        {
            obj = new HashedString();
            obj.unhashedName = s;
            s = ((String) (obj));
        }
        processhealthproto.name = s;
        if (HealthStatsProtos.isZero(processhealthproto))
        {
            return null;
        } else
        {
            return processhealthproto;
        }
    }

    final String nameOf(MessageNano messagenano)
    {
        return ((ProcessHealthProto)messagenano).name.unhashedName;
    }

    final MessageNano subtract(MessageNano messagenano, MessageNano messagenano1)
    {
        messagenano = (ProcessHealthProto)messagenano;
        messagenano1 = (ProcessHealthProto)messagenano1;
        if (messagenano == null || messagenano1 == null)
        {
            return messagenano;
        }
        ProcessHealthProto processhealthproto = new ProcessHealthProto();
        processhealthproto.name = ((ProcessHealthProto) (messagenano)).name;
        processhealthproto.userTimeMs = HealthStatsProtos.subtract(((ProcessHealthProto) (messagenano)).userTimeMs, ((ProcessHealthProto) (messagenano1)).userTimeMs);
        processhealthproto.systemTimeMs = HealthStatsProtos.subtract(((ProcessHealthProto) (messagenano)).systemTimeMs, ((ProcessHealthProto) (messagenano1)).systemTimeMs);
        processhealthproto.startsCount = HealthStatsProtos.subtract(((ProcessHealthProto) (messagenano)).startsCount, ((ProcessHealthProto) (messagenano1)).startsCount);
        processhealthproto.crashesCount = HealthStatsProtos.subtract(((ProcessHealthProto) (messagenano)).crashesCount, ((ProcessHealthProto) (messagenano1)).crashesCount);
        processhealthproto.anrCount = HealthStatsProtos.subtract(((ProcessHealthProto) (messagenano)).anrCount, ((ProcessHealthProto) (messagenano1)).anrCount);
        processhealthproto.foregroundMs = HealthStatsProtos.subtract(((ProcessHealthProto) (messagenano)).foregroundMs, ((ProcessHealthProto) (messagenano1)).foregroundMs);
        if (HealthStatsProtos.isZero(processhealthproto))
        {
            return null;
        } else
        {
            return processhealthproto;
        }
    }


    private ps()
    {
        super(logs/proto/wireless/performance/mobile/nano/ProcessHealthProto);
    }
}
