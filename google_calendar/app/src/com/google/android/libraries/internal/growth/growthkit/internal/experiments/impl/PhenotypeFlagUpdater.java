// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import android.content.SharedPreferences;
import com.google.android.libraries.gcoreclient.phenotype.Phenotype;
import com.google.android.libraries.gcoreclient.phenotype.PhenotypeApi;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.common.util.concurrent.ListeningExecutorService;

public final class PhenotypeFlagUpdater
{

    public static final Logger logger = new Logger();
    public final ListeningExecutorService executorService;
    public final com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.Builder googleApiClientBuilder;
    public final Phenotype phenotype;
    public final PhenotypeApi phenotypeApi;
    public final String phenotypeMendelPackage;
    public final SharedPreferences phenotypeSharedPrefs;

    public PhenotypeFlagUpdater(Phenotype phenotype1, SharedPreferences sharedpreferences, String s, ListeningExecutorService listeningexecutorservice, com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.Builder builder, PhenotypeApi phenotypeapi)
    {
        phenotype = phenotype1;
        phenotypeSharedPrefs = sharedpreferences;
        phenotypeMendelPackage = s;
        executorService = listeningexecutorservice;
        googleApiClientBuilder = builder;
        phenotypeApi = phenotypeapi;
    }

}
