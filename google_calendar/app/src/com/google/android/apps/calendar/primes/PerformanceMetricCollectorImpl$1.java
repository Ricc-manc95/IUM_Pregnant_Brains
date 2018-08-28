// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.primes;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.libraries.performance.primes.transmitter.impl.HashedNamesTransmitter;
import com.google.protobuf.nano.MessageNano;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.apps.calendar.primes:
//            PerformanceMetricCollectorImpl, PrimesManager

final class mesTransmitter extends HashedNamesTransmitter
{

    private final Context val$appContext;

    protected final void sendHashedEvent(SystemHealthMetric systemhealthmetric)
    {
        LogUtils.d(PerformanceMetricCollectorImpl.TAG, "Performance metrics %s", new Object[] {
            systemhealthmetric.toString()
        });
        Object obj = val$appContext;
        if (PrimesManager.primesManager == null)
        {
            PrimesManager.primesManager = new PrimesManager(((Context) (obj)));
        }
        obj = PrimesManager.primesManager;
        com.google.android.gms.clearcut.ClearcutLogger clearcutlogger = ((PrimesManager) (obj)).memoryLogger;
        int i = systemhealthmetric.computeSerializedSize();
        systemhealthmetric.cachedSize = i;
        byte abyte0[] = new byte[i];
        MessageNano.toByteArray(systemhealthmetric, abyte0, 0, abyte0.length);
        systemhealthmetric = new com.google.android.gms.clearcut.nit>(clearcutlogger, abyte0);
        if (((PrimesManager) (obj)).client == null)
        {
            obj.client = ((PrimesManager) (obj)).clientBuilder.();
        }
        if (!((PrimesManager) (obj)).client.isConnecting() && !((PrimesManager) (obj)).client.isConnected())
        {
            ((PrimesManager) (obj)).client.connect();
        }
        systemhealthmetric.gAsync();
    }

    mesTransmitter()
    {
        val$appContext = context;
        super();
    }
}
