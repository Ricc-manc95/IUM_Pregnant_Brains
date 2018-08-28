// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.primes;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollector;
import com.google.android.libraries.performance.primes.Primes;
import com.google.android.libraries.performance.primes.PrimesApi;
import com.google.android.libraries.performance.primes.PrimesApiProvider;
import com.google.android.libraries.performance.primes.PrimesMemoryConfigurations;
import com.google.android.libraries.performance.primes.TimerEvent;
import com.google.common.base.Optional;
import java.util.Locale;
import logs.proto.wireless.performance.mobile.nano.CalendarExtension;
import logs.proto.wireless.performance.mobile.nano.MetricExtension;

public class PerformanceMetricCollectorImpl
    implements PerformanceMetricCollector
{
    static final class MetricExtensionBuilder
    {

        public final CalendarExtension calendarExtension = new CalendarExtension();

        MetricExtensionBuilder()
        {
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/primes/PerformanceMetricCollectorImpl);
    public int activeExperiments[];
    private boolean extendedMemoryMonitoring;

    public PerformanceMetricCollectorImpl()
    {
    }

    public final TimerEvent initializeTimer()
    {
        if (Primes.primes == Primes.DEFAULT_PRIMES && Primes.warningNotYetLogged)
        {
            Primes.warningNotYetLogged = false;
            String s = "Primes not initialized, returning default (no-op) Primes instance which will ignore all calls. Please call Primes.initialize(...) before using any Primes API.";
            Object aobj[] = new Object[0];
            if (aobj.length != 0)
            {
                s = String.format(Locale.US, "Primes not initialized, returning default (no-op) Primes instance which will ignore all calls. Please call Primes.initialize(...) before using any Primes API.", aobj);
            }
            Log.w("Primes", s);
        }
        return Primes.primes.primesApi.startTimer();
    }

    public final void logTime(TimerEvent timerevent, String s)
    {
        LogUtils.i(TAG, "Logging duration: %s", new Object[] {
            s
        });
        if (Primes.primes == Primes.DEFAULT_PRIMES && Primes.warningNotYetLogged)
        {
            Primes.warningNotYetLogged = false;
            Object obj = "Primes not initialized, returning default (no-op) Primes instance which will ignore all calls. Please call Primes.initialize(...) before using any Primes API.";
            Object obj1 = ((Object) (new Object[0]));
            int ai[];
            MetricExtension metricextension;
            if (obj1.length != 0)
            {
                obj = String.format(Locale.US, "Primes not initialized, returning default (no-op) Primes instance which will ignore all calls. Please call Primes.initialize(...) before using any Primes API.", ((Object []) (obj1)));
            }
            Log.w("Primes", ((String) (obj)));
        }
        obj = Primes.primes;
        obj1 = new MetricExtensionBuilder();
        ai = activeExperiments;
        ((MetricExtensionBuilder) (obj1)).calendarExtension.activeExperiments = ai;
        ((MetricExtensionBuilder) (obj1)).calendarExtension.remoteFeatureConfig = RemoteFeatureConfig.getEnabledFeaturesArray();
        metricextension = new MetricExtension();
        metricextension.calendarExtension = ((MetricExtensionBuilder) (obj1)).calendarExtension;
        ((Primes) (obj)).stopTimer(timerevent, s, metricextension);
    }

    public final void logTime(TimerEvent timerevent, String s, com.google.calendar.v2a.android.util.metric.MetricUtils.Result result)
    {
        int i;
        LogUtils.i(TAG, "Logging duration: %s withResult: %s", new Object[] {
            s, result
        });
        if (Primes.primes == Primes.DEFAULT_PRIMES && Primes.warningNotYetLogged)
        {
            Primes.warningNotYetLogged = false;
            Object obj = "Primes not initialized, returning default (no-op) Primes instance which will ignore all calls. Please call Primes.initialize(...) before using any Primes API.";
            Object obj1 = ((Object) (new Object[0]));
            int ai[];
            CalendarExtension calendarextension;
            if (obj1.length != 0)
            {
                obj = String.format(Locale.US, "Primes not initialized, returning default (no-op) Primes instance which will ignore all calls. Please call Primes.initialize(...) before using any Primes API.", ((Object []) (obj1)));
            }
            Log.w("Primes", ((String) (obj)));
        }
        obj = Primes.primes;
        obj1 = new MetricExtensionBuilder();
        ai = activeExperiments;
        ((MetricExtensionBuilder) (obj1)).calendarExtension.activeExperiments = ai;
        ((MetricExtensionBuilder) (obj1)).calendarExtension.remoteFeatureConfig = RemoteFeatureConfig.getEnabledFeaturesArray();
        calendarextension = ((MetricExtensionBuilder) (obj1)).calendarExtension;
        result.status().ordinal();
        JVM INSTR tableswitch 0 2: default 144
    //                   0 244
    //                   1 250
    //                   2 256;
           goto _L1 _L2 _L3 _L4
_L1:
        i = 0x80000000;
_L6:
        calendarextension.resultStatus = i;
        ((MetricExtensionBuilder) (obj1)).calendarExtension.resultSource = (Integer)result.source().transform(com.google.calendar.v2a.android.util.metric.MetricUtils.Result..Lambda._cls1.$instance).orNull();
        ((MetricExtensionBuilder) (obj1)).calendarExtension.resultCode = (Integer)result.code().transform(com.google.calendar.v2a.android.util.metric.MetricUtils.Result..Lambda._cls3.$instance).orNull();
        result = new MetricExtension();
        result.calendarExtension = ((MetricExtensionBuilder) (obj1)).calendarExtension;
        ((Primes) (obj)).stopTimer(timerevent, s, result);
        return;
_L2:
        i = 1;
        continue; /* Loop/switch isn't completed */
_L3:
        i = 2;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 3;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void recordMemory(String s)
    {
        if (!extendedMemoryMonitoring)
        {
            return;
        }
        LogUtils.i(TAG, "Logging memory usage: %s", new Object[] {
            s
        });
        if (Primes.primes == Primes.DEFAULT_PRIMES && Primes.warningNotYetLogged)
        {
            Primes.warningNotYetLogged = false;
            String s1 = "Primes not initialized, returning default (no-op) Primes instance which will ignore all calls. Please call Primes.initialize(...) before using any Primes API.";
            Object aobj[] = new Object[0];
            if (aobj.length != 0)
            {
                s1 = String.format(Locale.US, "Primes not initialized, returning default (no-op) Primes instance which will ignore all calls. Please call Primes.initialize(...) before using any Primes API.", aobj);
            }
            Log.w("Primes", s1);
        }
        Primes.primes.primesApi.recordMemory(s, false);
    }

    public final void start(Context context, int ai[], boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4)
    {
        boolean flag5 = true;
        this;
        JVM INSTR monitorenter ;
        if (Primes.primes != Primes.DEFAULT_PRIMES || !Primes.warningNotYetLogged) goto _L2; else goto _L1
_L1:
        Primes.warningNotYetLogged = false;
        Object obj = "Primes not initialized, returning default (no-op) Primes instance which will ignore all calls. Please call Primes.initialize(...) before using any Primes API.";
        Object obj1 = ((Object) (new Object[0]));
        if (obj1.length != 0) goto _L4; else goto _L3
_L3:
        Log.w("Primes", ((String) (obj)));
_L2:
        obj = Primes.primes;
        obj1 = Primes.DEFAULT_PRIMES;
        if (obj == obj1)
        {
            flag5 = false;
        }
        if (!flag5) goto _L6; else goto _L5
_L5:
        this;
        JVM INSTR monitorexit ;
        return;
_L4:
        obj = String.format(Locale.US, "Primes not initialized, returning default (no-op) Primes instance which will ignore all calls. Please call Primes.initialize(...) before using any Primes API.", ((Object []) (obj1)));
          goto _L3
_L6:
        extendedMemoryMonitoring = flag1;
        activeExperiments = ai;
        LogUtils.i(TAG, "Logging experiments memory-%b, lantency-%b", new Object[] {
            Boolean.valueOf(flag), Boolean.valueOf(flag2)
        });
        if (!flag && !flag2 && !flag3 && !flag4) goto _L5; else goto _L7
_L7:
        context = context.getApplicationContext();
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_168;
        }
        LogUtils.i(TAG, "Start memory usage monitoring.", new Object[0]);
        if (!flag2)
        {
            break MISSING_BLOCK_LABEL_186;
        }
        LogUtils.i(TAG, "Start UI latency monitoring.", new Object[0]);
        if (!flag3)
        {
            break MISSING_BLOCK_LABEL_204;
        }
        LogUtils.i(TAG, "Start crash monitoring.", new Object[0]);
        if (!flag4)
        {
            break MISSING_BLOCK_LABEL_222;
        }
        LogUtils.i(TAG, "Start packagestats monitoring.", new Object[0]);
        class .Lambda._cls0
            implements MemoryMetricExtensionProvider
        {

            private final PerformanceMetricCollectorImpl arg$1;

            public final MetricExtension getMetricExtension(String s, int i)
            {
                Object obj2 = arg$1;
                s = new MetricExtensionBuilder();
                int ai1[] = ((PerformanceMetricCollectorImpl) (obj2)).activeExperiments;
                ((MetricExtensionBuilder) (s)).calendarExtension.activeExperiments = ai1;
                ((MetricExtensionBuilder) (s)).calendarExtension.remoteFeatureConfig = RemoteFeatureConfig.getEnabledFeaturesArray();
                ai1 = new MetricExtension();
                ai1.calendarExtension = ((MetricExtensionBuilder) (s)).calendarExtension;
                return ai1;
            }

            .Lambda._cls0()
            {
                arg$1 = PerformanceMetricCollectorImpl.this;
            }
        }

        ai = new PrimesMemoryConfigurations(flag, 3, false, new .Lambda._cls0());
        class .Lambda._cls1
            implements PrimesConfigurationsProvider
        {

            private final PerformanceMetricCollectorImpl arg$1;
            private final Context arg$2;
            private final PrimesMemoryConfigurations arg$3;
            private final boolean arg$4;
            private final boolean arg$5;
            private final boolean arg$6;

            public final PrimesConfigurations get()
            {
                final Context appContext = arg$1;
                appContext = arg$2;
                PrimesMemoryConfigurations primesmemoryconfigurations = arg$3;
                boolean flag6 = arg$4;
                boolean flag7 = arg$5;
                boolean flag8 = arg$6;
                com.google.android.libraries.performance.primes.PrimesConfigurations.Builder builder = new com.google.android.libraries.performance.primes.PrimesConfigurations.Builder();
                builder.metricTransmitter = new _cls1();
                builder.memoryConfigs = primesmemoryconfigurations;
                builder.timerConfigs = new PrimesTimerConfigurations(flag6, 20);
                builder.crashConfigs = new PrimesCrashConfigurations(flag7);
                builder.packageConfigs = new PrimesPackageConfigurations(flag8);
                appContext = new com.google.android.libraries.performance.primes.PrimesConfigurations.FromBuilder(builder.metricTransmitter, null, builder.memoryConfigs, builder.timerConfigs, builder.crashConfigs, null, builder.packageConfigs, null, null, null, null, null, null, null, null);
                if (appContext instanceof com.google.android.libraries.performance.primes.PrimesConfigurations.LazyValid)
                {
                    return appContext;
                } else
                {
                    return new com.google.android.libraries.performance.primes.PrimesConfigurations.LazyValid(appContext);
                }
            }

            .Lambda._cls1(Context context, PrimesMemoryConfigurations primesmemoryconfigurations, boolean flag, boolean flag1, boolean flag2)
            {
                arg$1 = PerformanceMetricCollectorImpl.this;
                arg$2 = context;
                arg$3 = primesmemoryconfigurations;
                arg$4 = flag;
                arg$5 = flag1;
                arg$6 = flag2;
            }

            private class _cls1 extends HashedNamesTransmitter
            {

                private final Context val$appContext;

                protected final void sendHashedEvent(SystemHealthMetric systemhealthmetric)
                {
                    LogUtils.d(PerformanceMetricCollectorImpl.TAG, "Performance metrics %s", new Object[] {
                        systemhealthmetric.toString()
                    });
                    Object obj = appContext;
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
                    systemhealthmetric = new com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder(clearcutlogger, abyte0);
                    if (((PrimesManager) (obj)).client == null)
                    {
                        obj.client = ((PrimesManager) (obj)).clientBuilder.build();
                    }
                    if (!((PrimesManager) (obj)).client.isConnecting() && !((PrimesManager) (obj)).client.isConnected())
                    {
                        ((PrimesManager) (obj)).client.connect();
                    }
                    systemhealthmetric.logAsync();
                }

                _cls1()
                {
                    appContext = context;
                    super();
                }
            }

        }

        Primes.initialize(PrimesApiProvider.newInstance((Application)context, new .Lambda._cls1(context, ai, flag2, flag3, flag4))).primesApi.startMemoryMonitor();
          goto _L5
        context;
        throw context;
          goto _L3
    }

    public final Thread.UncaughtExceptionHandler wrapCrashReportingIntoUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtexceptionhandler)
    {
        if (Primes.primes == Primes.DEFAULT_PRIMES && Primes.warningNotYetLogged)
        {
            Primes.warningNotYetLogged = false;
            String s = "Primes not initialized, returning default (no-op) Primes instance which will ignore all calls. Please call Primes.initialize(...) before using any Primes API.";
            Object aobj[] = new Object[0];
            if (aobj.length != 0)
            {
                s = String.format(Locale.US, "Primes not initialized, returning default (no-op) Primes instance which will ignore all calls. Please call Primes.initialize(...) before using any Primes API.", aobj);
            }
            Log.w("Primes", s);
        }
        return Primes.primes.primesApi.wrapCrashReportingIntoUncaughtExceptionHandler(uncaughtexceptionhandler);
    }

}
