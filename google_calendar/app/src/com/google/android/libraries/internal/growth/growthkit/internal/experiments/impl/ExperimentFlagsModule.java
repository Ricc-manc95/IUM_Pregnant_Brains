// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import android.content.Context;
import com.google.android.libraries.internal.growth.growthkit.internal.experiments.ExperimentFlagsOverride;
import com.google.android.libraries.phenotype.client.PhenotypeFlag;
import java.util.Map;

public final class ExperimentFlagsModule
{

    static Boolean getFlagValue(com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory factory, String s, boolean flag)
    {
        if (ExperimentFlagsOverride.overrides.containsKey(s))
        {
            return (Boolean)ExperimentFlagsOverride.get(s, java/lang/Boolean);
        }
        try
        {
            factory = (Boolean)PhenotypeFlag.value(factory, s, flag).get();
        }
        // Misplaced declaration of an exception variable
        catch (com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory factory)
        {
            return Boolean.valueOf(flag);
        }
        return factory;
    }

    static Long getFlagValue(com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory factory, String s, long l)
    {
        if (ExperimentFlagsOverride.overrides.containsKey(s))
        {
            return (Long)ExperimentFlagsOverride.get(s, java/lang/Long);
        }
        try
        {
            factory = (Long)PhenotypeFlag.value(factory, s, l).get();
        }
        // Misplaced declaration of an exception variable
        catch (com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory factory)
        {
            return Long.valueOf(l);
        }
        return factory;
    }

    static String getFlagValue(com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory factory, String s, String s1)
    {
        if (ExperimentFlagsOverride.overrides.containsKey(s))
        {
            return (String)ExperimentFlagsOverride.get(s, java/lang/String);
        }
        try
        {
            factory = (String)PhenotypeFlag.value(factory, s, s1).get();
        }
        // Misplaced declaration of an exception variable
        catch (com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory factory)
        {
            return s1;
        }
        return factory;
    }

    static com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory providePhenotypeFlagBuilder(Context context, String s)
    {
        PhenotypeFlag.init(context);
        context = new com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory(s);
        if (!((com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory) (context)).gservicesPrefix.isEmpty())
        {
            throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
        } else
        {
            return new com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory(((com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory) (context)).sharedPrefsName, ((com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory) (context)).contentProviderUri, ((com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory) (context)).gservicesPrefix, ((com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory) (context)).phenotypePrefix, true, ((com.google.android.libraries.phenotype.client.PhenotypeFlag.Factory) (context)).preferGservices);
        }
    }
}
