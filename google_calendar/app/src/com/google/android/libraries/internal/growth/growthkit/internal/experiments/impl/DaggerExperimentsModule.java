// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.android.libraries.gcoreclient.phenotype.Phenotype;
import com.google.android.libraries.internal.growth.growthkit.inject.BroadcastReceiverInjector;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;

public class DaggerExperimentsModule
{

    private static final Logger logger = new Logger();

    static int provideAppVersionCode(Context context)
    {
        int i;
        try
        {
            i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            logger.e(context, "Did not find own package, this should be impossible.", new Object[0]);
            return 0;
        }
        return i;
    }

    static String provideMendelPackage()
    {
        return "com.google.android.libraries.internal.growth.growthkit";
    }

    static String provideMendelPerAppPackage(String s, String s1)
    {
        return (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s1).length())).append(s).append("#").append(s1).toString();
    }

    public static Phenotype providePhenotype(com.google.android.libraries.gcoreclient.phenotype.Phenotype.Factory factory)
    {
        return factory.create();
    }

    static SharedPreferences providePhenotypeSharedPreferences(Context context, String s)
    {
        return context.getSharedPreferences(s, 0);
    }

    static String provideSharedPrefs()
    {
        return "growthkit_phenotype_prefs";
    }

    static BroadcastReceiverInjector providerPhenotypeBroadcastReceiverInjector(PhenotypeBroadcastReceiver.PhenotypeBroadcastReceiverSubcomponent.Builder builder)
    {
        return builder.build();
    }

}
