// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.experiments;

import android.content.Context;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.common.util.isemulator.IsEmulator;

// Referenced classes of package com.google.android.apps.calendar.config.experiments:
//            Experiment

public final class ExperimentConfiguration
{

    private static final boolean BUGFOOD_OR_EMULATOR;
    public static final Experiment CONSISTENCY_CHECK;
    private static final Experiment EXPERIMENTS[];
    private static final boolean FISHFOOD;
    public static final Experiment HATS_PERCENTAGE;
    public static final Experiment HATS_WEEKS;
    public static final Experiment PRIMES_CRASH_INSTRUMENTATION;
    public static final Experiment PRIMES_MEMORY_INSTRUMENTATION;
    public static final Experiment PRIMES_PACKAGESTATS_INSTRUMENTATION;
    public static final Experiment PRIMES_UI_LATENCY_INSTRUMENTATION;
    public static final Experiment SSA_SILENT_FEEDBACK;
    public static final Experiment SYNC_OPERATION_LOGGING;

    public static String getActiveExperiments(Context context)
    {
        Experiment aexperiment[] = EXPERIMENTS;
        StringBuilder stringbuilder = new StringBuilder();
        int j = aexperiment.length;
        for (int i = 0; i < j; i++)
        {
            Experiment experiment = aexperiment[i];
            if (experiment.isActive(context))
            {
                stringbuilder.append("[").append(experiment.name).append("]");
            }
        }

        return stringbuilder.toString();
    }

    public static int[] getActiveExperimentsIds(Context context)
    {
        boolean flag = false;
        Experiment aexperiment[] = EXPERIMENTS;
        int i1 = aexperiment.length;
        int i = 0;
        int j;
        int k;
        for (j = 0; i < i1; j = k)
        {
            k = j;
            if (aexperiment[i].isActive(context))
            {
                k = j + 1;
            }
            i++;
        }

        int ai[] = new int[j];
        i1 = aexperiment.length;
        j = 0;
        i = ((flag) ? 1 : 0);
        for (; j < i1; j++)
        {
            Experiment experiment = aexperiment[j];
            if (experiment.isActive(context))
            {
                int l = i + 1;
                ai[i] = experiment.id;
                i = l;
            }
        }

        return ai;
    }

    static 
    {
        Object obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).bugfood_build();
        boolean flag;
        if (IsEmulator.isProbablyEmulator())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        BUGFOOD_OR_EMULATOR = flag;
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        FISHFOOD = ((FeatureConfig)obj).fishfood_build();
        obj = new PercentageExperiment.Builder(8, "PMI", 0x3031357c, 0, 5);
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        if (((FeatureConfig)featureconfig).enable_memory_monitoring_unsampled())
        {
            obj.mForceActive = Boolean.valueOf(true);
        }
        if (BUGFOOD_OR_EMULATOR)
        {
            obj.mForceActive = Boolean.valueOf(false);
        }
        ((Experiment.Builder) (obj)).checkConstraints();
        PRIMES_MEMORY_INSTRUMENTATION = ((Experiment.Builder) (obj)).buildInternal();
        obj = new PercentageExperiment.Builder(9, "PLI", 0x8127d8dd, 0, 5);
        featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        if (((FeatureConfig)featureconfig).primes_ui_latency_instrumentation_unsampled())
        {
            obj.mForceActive = Boolean.valueOf(true);
        }
        if (BUGFOOD_OR_EMULATOR)
        {
            obj.mForceActive = Boolean.valueOf(false);
        }
        ((Experiment.Builder) (obj)).checkConstraints();
        PRIMES_UI_LATENCY_INSTRUMENTATION = ((Experiment.Builder) (obj)).buildInternal();
        obj = new PercentageExperiment.Builder(10, "PCI", 0x6912d490, 0, 5);
        featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        if (((FeatureConfig)featureconfig).enable_primes_crash_monitoring_unsampled())
        {
            obj.mForceActive = Boolean.valueOf(true);
        }
        if (BUGFOOD_OR_EMULATOR)
        {
            obj.mForceActive = Boolean.valueOf(false);
        }
        ((Experiment.Builder) (obj)).checkConstraints();
        PRIMES_CRASH_INSTRUMENTATION = ((Experiment.Builder) (obj)).buildInternal();
        obj = new PercentageExperiment.Builder(16, "SSF", 0x3e2a4394, 0, 1, 1000);
        if (BUGFOOD_OR_EMULATOR)
        {
            obj.mForceActive = Boolean.valueOf(false);
        }
        ((Experiment.Builder) (obj)).checkConstraints();
        SSA_SILENT_FEEDBACK = ((Experiment.Builder) (obj)).buildInternal();
        obj = new PercentageExperiment.Builder(17, "PPS", 0xa48c40d1, 0, 5);
        featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        if (((FeatureConfig)featureconfig).enable_primes_packagestats_monitoring_unsampled())
        {
            obj.mForceActive = Boolean.valueOf(true);
        }
        if (BUGFOOD_OR_EMULATOR)
        {
            obj.mForceActive = Boolean.valueOf(false);
        }
        ((Experiment.Builder) (obj)).checkConstraints();
        PRIMES_PACKAGESTATS_INSTRUMENTATION = ((Experiment.Builder) (obj)).buildInternal();
        obj = new PercentageExperiment.Builder(18, "CCK", 0x8c76726c, 0, 1);
        if (BUGFOOD_OR_EMULATOR)
        {
            obj.mForceActive = Boolean.valueOf(false);
        }
        ((Experiment.Builder) (obj)).checkConstraints();
        CONSISTENCY_CHECK = ((Experiment.Builder) (obj)).buildInternal();
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).dogfood_features();
        obj = new PercentageExperiment.Builder(19, "HTSP", 0x5ce5d059, 0, 1, 200);
        if (BUGFOOD_OR_EMULATOR)
        {
            obj.mForceActive = Boolean.valueOf(true);
        }
        if (FISHFOOD)
        {
            obj.mForceActive = Boolean.valueOf(true);
        }
        ((Experiment.Builder) (obj)).checkConstraints();
        HATS_PERCENTAGE = ((Experiment.Builder) (obj)).buildInternal();
        obj = new WeeklyExperiment.Builder(20, "HTSW", 0x26284187, 13);
        ((Experiment.Builder) (obj)).checkConstraints();
        HATS_WEEKS = ((Experiment.Builder) (obj)).buildInternal();
        obj = new PercentageExperiment.Builder(22, "SOLO", 0xf85f7884, 0, 1);
        featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).public_release();
        if (BUGFOOD_OR_EMULATOR)
        {
            obj.mForceActive = Boolean.valueOf(false);
        }
        ((Experiment.Builder) (obj)).checkConstraints();
        SYNC_OPERATION_LOGGING = ((Experiment.Builder) (obj)).buildInternal();
        EXPERIMENTS = (new Experiment[] {
            PRIMES_MEMORY_INSTRUMENTATION, PRIMES_UI_LATENCY_INSTRUMENTATION, PRIMES_CRASH_INSTRUMENTATION, SSA_SILENT_FEEDBACK, PRIMES_PACKAGESTATS_INSTRUMENTATION, CONSISTENCY_CHECK, HATS_PERCENTAGE, HATS_WEEKS, SYNC_OPERATION_LOGGING
        });
    }
}
