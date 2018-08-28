// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.transmitter.impl;

import android.text.TextUtils;
import com.google.android.libraries.performance.primes.Hashing;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import logs.proto.wireless.performance.mobile.nano.BatteryUsageMetric;
import logs.proto.wireless.performance.mobile.nano.NetworkEventUsage;
import logs.proto.wireless.performance.mobile.nano.NetworkUsageMetric;
import logs.proto.wireless.performance.mobile.nano.PackageMetric;
import logs.proto.wireless.performance.mobile.nano.PrimesTrace;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

public abstract class HashedNamesTransmitter
    implements MetricTransmitter
{

    private static final MetricNameAccess BATTERY_METRIC_NAME_ACCESS = new _cls2();
    private static final MetricNameAccess SHM_METRIC_NAME_ACCESS = new _cls1();
    private static final MetricNameAccess SPAN_METRIC_NAME_ACCESS = new _cls3();

    public HashedNamesTransmitter()
    {
    }

    private static void ensureNoPiiName(MetricNameAccess metricnameaccess, Object obj)
    {
        if (TextUtils.isEmpty(metricnameaccess.getConstantName(obj)))
        {
            metricnameaccess.setHashedName(obj, Hashing.hash(metricnameaccess.getCustomName(obj)));
        } else
        {
            metricnameaccess.setHashedName(obj, null);
        }
        metricnameaccess.setCustomName(obj, null);
    }

    private static long[] hashTokens(String s)
    {
        s = s.replaceFirst("^/+", "").split("/+");
        long al[] = new long[s.length];
        for (int i = 0; i < al.length; i++)
        {
            al[i] = Hashing.hash(s[i]).longValue();
        }

        return al;
    }

    public final void send(SystemHealthMetric systemhealthmetric)
    {
        boolean flag = false;
        ensureNoPiiName(SHM_METRIC_NAME_ACCESS, systemhealthmetric);
        if (systemhealthmetric.batteryUsageMetric != null && systemhealthmetric.batteryUsageMetric.batteryStatsDiff != null)
        {
            ensureNoPiiName(BATTERY_METRIC_NAME_ACCESS, systemhealthmetric.batteryUsageMetric.batteryStatsDiff);
        }
        if (systemhealthmetric.packageMetric != null && systemhealthmetric.packageMetric.dirStats != null)
        {
            logs.proto.wireless.performance.mobile.nano.PackageMetric.DirStats adirstats[] = systemhealthmetric.packageMetric.dirStats;
            int l = adirstats.length;
            int i = 0;
            while (i < l) 
            {
                logs.proto.wireless.performance.mobile.nano.PackageMetric.DirStats dirstats = adirstats[i];
                if (!TextUtils.isEmpty(dirstats.dirPath))
                {
                    dirstats.hashedDirPath = hashTokens(dirstats.dirPath);
                }
                dirstats.dirPath = null;
                i++;
            }
        }
        if (systemhealthmetric.networkUsageMetric != null && systemhealthmetric.networkUsageMetric.networkEventUsage != null)
        {
            NetworkEventUsage anetworkeventusage[] = systemhealthmetric.networkUsageMetric.networkEventUsage;
            int i1 = anetworkeventusage.length;
            int j = 0;
            while (j < i1) 
            {
                NetworkEventUsage networkeventusage = anetworkeventusage[j];
                if (!TextUtils.isEmpty(networkeventusage.rpcPath))
                {
                    networkeventusage.hashedRpcPath = hashTokens(networkeventusage.rpcPath);
                }
                networkeventusage.rpcPath = null;
                j++;
            }
        }
        if (systemhealthmetric.primesTrace != null && systemhealthmetric.primesTrace.spans != null)
        {
            logs.proto.wireless.performance.mobile.nano.Span aspan[] = systemhealthmetric.primesTrace.spans;
            int j1 = aspan.length;
            int k = ((flag) ? 1 : 0);
            while (k < j1) 
            {
                logs.proto.wireless.performance.mobile.nano.Span span = aspan[k];
                ensureNoPiiName(SPAN_METRIC_NAME_ACCESS, span);
                k++;
            }
        }
        sendHashedEvent(systemhealthmetric);
    }

    public abstract void sendHashedEvent(SystemHealthMetric systemhealthmetric);


    private class MetricNameAccess
    {

        public abstract String getConstantName(Object obj);

        public abstract String getCustomName(Object obj);

        public abstract void setCustomName(Object obj, String s);

        public abstract void setHashedName(Object obj, Long long1);
    }


    private class _cls1
        implements MetricNameAccess
    {

        public final String getConstantName(Object obj)
        {
            return ((SystemHealthMetric)obj).constantEventName;
        }

        public final String getCustomName(Object obj)
        {
            return ((SystemHealthMetric)obj).customEventName;
        }

        public final void setCustomName(Object obj, String s)
        {
            ((SystemHealthMetric)obj).customEventName = s;
        }

        public final void setHashedName(Object obj, Long long1)
        {
            ((SystemHealthMetric)obj).hashedCustomEventName = long1;
        }

        _cls1()
        {
        }
    }


    private class _cls2
        implements MetricNameAccess
    {

        public final String getConstantName(Object obj)
        {
            return ((BatteryStatsDiff)obj).startConstantEventName;
        }

        public final String getCustomName(Object obj)
        {
            return ((BatteryStatsDiff)obj).startCustomEventName;
        }

        public final void setCustomName(Object obj, String s)
        {
            ((BatteryStatsDiff)obj).startCustomEventName = s;
        }

        public final void setHashedName(Object obj, Long long1)
        {
            ((BatteryStatsDiff)obj).startHashedCustomEventName = long1;
        }

        _cls2()
        {
        }
    }


    private class _cls3
        implements MetricNameAccess
    {

        public final String getConstantName(Object obj)
        {
            return ((Span)obj).constantName;
        }

        public final String getCustomName(Object obj)
        {
            return ((Span)obj).name;
        }

        public final void setCustomName(Object obj, String s)
        {
            ((Span)obj).name = s;
        }

        public final void setHashedName(Object obj, Long long1)
        {
            ((Span)obj).hashedName = long1;
        }

        _cls3()
        {
        }
    }

}
