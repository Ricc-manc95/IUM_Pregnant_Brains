// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.transmitter;

import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

public interface MetricTransmitter
{

    public static final MetricTransmitter NOOP_TRANSMITTER = new _cls1();

    public abstract void send(SystemHealthMetric systemhealthmetric);


    private class _cls1
        implements MetricTransmitter
    {

        public final void send(SystemHealthMetric systemhealthmetric)
        {
        }

        _cls1()
        {
        }
    }

}
