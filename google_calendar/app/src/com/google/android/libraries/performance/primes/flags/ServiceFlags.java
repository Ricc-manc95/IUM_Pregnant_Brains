// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.flags;

import android.content.Context;
import com.google.android.libraries.performance.primes.PrimesFlags;
import com.google.android.libraries.phenotype.client.PhenotypeFlag;
import java.util.HashMap;
import java.util.Map;

public final class ServiceFlags
{

    private static Map initFlags(Context context, PrimesFlags primesflags)
    {
        com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory factory = new com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory("primes-ph");
        factory = (new com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory(factory.sharedPrefsName, factory.contentProviderUri, factory.gservicesPrefix, "PrimesFlagsFeature__", factory.skipGservices, factory.preferGservices)).withGservicePrefix(String.format("primes:%s:", new Object[] {
            context.getPackageName()
        }));
        factory = new com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory(factory.sharedPrefsName, factory.contentProviderUri, factory.gservicesPrefix, factory.phenotypePrefix, factory.skipGservices, true);
        com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory factory1 = new com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory("primes-ph");
        factory1 = (new com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory(factory1.sharedPrefsName, factory1.contentProviderUri, factory1.gservicesPrefix, "PrimesFlagsFeature__", factory1.skipGservices, factory1.preferGservices)).withGservicePrefix("primes:");
        factory1 = new com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory(factory1.sharedPrefsName, factory1.contentProviderUri, factory1.gservicesPrefix, factory1.phenotypePrefix, factory1.skipGservices, true);
        FlagWithDefault flagwithdefault = new FlagWithDefault("enable_leak_detection", primesflags.leakDetectionEnabled);
        FlagWithDefault flagwithdefault1 = new FlagWithDefault("enable_leak_detection_v2", primesflags.leakDetectionV2Enabled);
        FlagWithDefault flagwithdefault2 = new FlagWithDefault("enable_battery_experiment", primesflags.batteryExperimentEnabled);
        FlagWithDefault flagwithdefault3 = new FlagWithDefault("enable_magic_eye_log", primesflags.magicEyeLogEnabled);
        FlagWithDefault flagwithdefault4 = new FlagWithDefault("enable_startup_trace", primesflags.startupTraceEnabled);
        FlagWithDefault flagwithdefault5 = new FlagWithDefault("enable_url_auto_sanitization", primesflags.urlAutoSanitizationEnabled);
        FlagWithDefault flagwithdefault6 = new FlagWithDefault("enable_persist_crash_stats", primesflags.persistCrashStatsEnabled);
        FlagWithDefault flagwithdefault7 = new FlagWithDefault("enable_primes_for_primes", primesflags.primesForPrimesEnabled);
        primesflags = new FlagWithDefault("enable_primes_trace", primesflags.primesTraceEnabled);
        HashMap hashmap = new HashMap();
        for (int i = 0; i < 9; i++)
        {
            FlagWithDefault flagwithdefault8 = (new FlagWithDefault[] {
                flagwithdefault, flagwithdefault1, flagwithdefault2, flagwithdefault3, flagwithdefault4, flagwithdefault5, flagwithdefault6, flagwithdefault7, primesflags
            })[i];
            hashmap.put(flagwithdefault8.flagName, PhenotypeFlag.value(factory, flagwithdefault8.flagName, flagwithdefault8.defaultValue));
        }

        hashmap.put("disable_memory_summary_metrics", PhenotypeFlag.value(factory1, "disable_memory_summary_metrics", false));
        PhenotypeFlag.init(context);
        return hashmap;
    }

    static PrimesFlags readPrimesFlags(Context context, PrimesFlags primesflags)
    {
        Object obj = initFlags(context, primesflags);
        context = (Boolean)((PhenotypeFlag)((Map) (obj)).get("enable_leak_detection_v2")).get();
        primesflags = (Boolean)((PhenotypeFlag)((Map) (obj)).get("disable_memory_summary_metrics")).get();
        Boolean boolean1 = (Boolean)((PhenotypeFlag)((Map) (obj)).get("enable_leak_detection")).get();
        Boolean boolean2 = (Boolean)((PhenotypeFlag)((Map) (obj)).get("enable_battery_experiment")).get();
        Boolean boolean3 = (Boolean)((PhenotypeFlag)((Map) (obj)).get("enable_magic_eye_log")).get();
        Boolean boolean4 = (Boolean)((PhenotypeFlag)((Map) (obj)).get("enable_persist_crash_stats")).get();
        Boolean boolean5 = (Boolean)((PhenotypeFlag)((Map) (obj)).get("enable_startup_trace")).get();
        Boolean boolean6 = (Boolean)((PhenotypeFlag)((Map) (obj)).get("enable_url_auto_sanitization")).get();
        Boolean boolean7 = (Boolean)((PhenotypeFlag)((Map) (obj)).get("enable_primes_for_primes")).get();
        obj = (Boolean)((PhenotypeFlag)((Map) (obj)).get("enable_primes_trace")).get();
        com.google.android.libraries.performance.primes.PrimesFlags.Builder builder = new com.google.android.libraries.performance.primes.PrimesFlags.Builder();
        builder.leakDetectionEnable = boolean1.booleanValue();
        builder.leakDetectionV2Enable = context.booleanValue();
        builder.memorySummaryDisable = primesflags.booleanValue();
        builder.batteryExperimentEnable = boolean2.booleanValue();
        builder.magicEyeLogEnable = boolean3.booleanValue();
        builder.persistCrashStatsEnable = boolean4.booleanValue();
        builder.startupTraceEnable = boolean5.booleanValue();
        builder.urlAutoSanitizationEnable = boolean6.booleanValue();
        builder.primesForPrimesEnabled = boolean7.booleanValue();
        builder.primesTraceEnabled = ((Boolean) (obj)).booleanValue();
        context = builder.build();
        return context;
        context;
        throw context;
    }

    private class FlagWithDefault
    {

        public final boolean defaultValue;
        public final String flagName;

        FlagWithDefault(String s, boolean flag)
        {
            flagName = s;
            defaultValue = flag;
        }
    }

}
