// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.battery;

import com.google.android.libraries.performance.primes.BatteryMetricExtensionProvider;
import com.google.android.libraries.performance.primes.Supplier;

// Referenced classes of package com.google.android.libraries.performance.primes.battery:
//            SystemHealthCapture

public final class BatteryCapture
{

    public final BatteryMetricExtensionProvider metricExtensionProvider;
    public final Supplier metricStamperSupplier;
    public final TimeCapture systemClockElapsedRealtimeCapture;
    public final TimeCapture systemCurrentTimeCapture;
    public final SystemHealthCapture systemHealthCapture;

    public BatteryCapture(Supplier supplier, SystemHealthCapture systemhealthcapture, TimeCapture timecapture, TimeCapture timecapture1, BatteryMetricExtensionProvider batterymetricextensionprovider)
    {
        systemHealthCapture = systemhealthcapture;
        systemCurrentTimeCapture = timecapture;
        systemClockElapsedRealtimeCapture = timecapture1;
        metricExtensionProvider = batterymetricextensionprovider;
        metricStamperSupplier = supplier;
    }
}
