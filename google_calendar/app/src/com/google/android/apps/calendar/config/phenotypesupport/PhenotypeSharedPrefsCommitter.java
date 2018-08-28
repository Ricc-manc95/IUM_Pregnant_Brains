// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.phenotypesupport;

import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.phenotype.Configurations;
import com.google.android.gms.phenotype.PhenotypeFlagCommitter;

public final class PhenotypeSharedPrefsCommitter extends PhenotypeFlagCommitter
{

    private final Context context;

    public PhenotypeSharedPrefsCommitter(Context context1, GoogleApiClient googleapiclient)
    {
        super(googleapiclient, "com.google.android.calendar");
        context = context1;
    }

    protected final void handleConfigurations(Configurations configurations)
    {
        PhenotypeFlagCommitter.writeToSharedPrefs(context.getSharedPreferences("phenotype_preferences", 0), configurations.configurations);
    }
}
