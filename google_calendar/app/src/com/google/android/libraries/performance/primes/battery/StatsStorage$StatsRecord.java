// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.battery;

import logs.proto.wireless.performance.mobile.nano.MetricExtension;
import logs.proto.wireless.performance.mobile.nano.UidHealthProto;

public final class metricExtension
{

    public final Long currentTime;
    public final String customEventName;
    public final Long elapsedTime;
    public final Boolean isEventNameConstant;
    public final MetricExtension metricExtension;
    public final Long primesVersion;
    public final UidHealthProto proto;
    public final Integer sampleInfo;
    public final Long versionNameHash;

    public final String toString()
    {
        return String.format("StatsRecord:\n  elapsed: %d\n  current: %d\n  Primes version: %d\n  version name #: %d\n  customName: %s\n", new Object[] {
            elapsedTime, currentTime, primesVersion, versionNameHash, customEventName
        });
    }

    public (UidHealthProto uidhealthproto, Long long1, Long long2, Long long3, Long long4, Integer integer, String s, 
            Boolean boolean1, MetricExtension metricextension)
    {
        proto = uidhealthproto;
        elapsedTime = long1;
        currentTime = long2;
        primesVersion = long3;
        versionNameHash = long4;
        sampleInfo = integer;
        customEventName = s;
        isEventNameConstant = boolean1;
        metricExtension = metricextension;
    }
}
