// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.phenotype.impl;

import android.content.SharedPreferences;
import com.google.android.gms.phenotype.PhenotypeApi;
import com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient;
import com.google.android.libraries.gcoreclient.common.api.GcorePendingResult;
import com.google.android.libraries.gcoreclient.common.api.support.GcorePendingResultImpl;
import com.google.android.libraries.gcoreclient.common.api.support.GcoreStatusImpl;
import com.google.android.libraries.gcoreclient.common.api.support.GcoreWrapper;
import com.google.android.libraries.gcoreclient.phenotype.GcoreConfigurations;
import com.google.android.libraries.gcoreclient.phenotype.Phenotype;

class BasePhenotypeImpl
    implements Phenotype
{

    private final GcoreWrapper wrapper = new GcoreWrapper();

    BasePhenotypeImpl()
    {
    }

    public GcorePendingResult commitToConfiguration(GcoreGoogleApiClient gcoregoogleapiclient, String s)
    {
        return new GcorePendingResultImpl(com.google.android.gms.phenotype.Phenotype.PhenotypeApi.commitToConfiguration(wrapper.unwrap(gcoregoogleapiclient), s), GcoreStatusImpl.STATUS_RESULT_WRAPPER);
    }

    public GcorePendingResult getConfigurationSnapshot(GcoreGoogleApiClient gcoregoogleapiclient, String s, String s1)
    {
        return new GcorePendingResultImpl(com.google.android.gms.phenotype.Phenotype.PhenotypeApi.getConfigurationSnapshot(wrapper.unwrap(gcoregoogleapiclient), s, s1), GcoreConfigurationsResultImpl.CONFIGURATIONS_RESULT_WRAPPER);
    }

    public GcorePendingResult register(GcoreGoogleApiClient gcoregoogleapiclient, String s, int i, String as[], byte abyte0[])
    {
        return wrapper.wrap(com.google.android.gms.phenotype.Phenotype.PhenotypeApi.register(wrapper.unwrap(gcoregoogleapiclient), s, i, as, abyte0));
    }

    public void writeToSharedPrefs(SharedPreferences sharedpreferences, GcoreConfigurations gcoreconfigurations)
    {
        sharedpreferences.edit().putString("__phenotype_server_token", gcoreconfigurations.getServerToken()).putString("__phenotype_snapshot_token", gcoreconfigurations.getSnapshotToken()).commit();
    }

    private class GcoreConfigurationsResultImpl
        implements com.google.android.libraries.gcoreclient.phenotype.Phenotype.GcoreConfigurationsResult
    {

        public static final ResultWrapper CONFIGURATIONS_RESULT_WRAPPER = new _cls1();
        private final com.google.android.gms.phenotype.PhenotypeApi.ConfigurationsResult configurationResult;

        public final GcoreConfigurations getConfigurations()
        {
            return new GcoreConfigurationsImpl(configurationResult.getConfigurations());
        }

        public final GcoreStatus getStatus()
        {
            return new GcoreStatusImpl(configurationResult.getStatus());
        }


        GcoreConfigurationsResultImpl(com.google.android.gms.phenotype.PhenotypeApi.ConfigurationsResult configurationsresult)
        {
            configurationResult = configurationsresult;
        }

        class _cls1
            implements ResultWrapper
        {

            public final GcoreResult wrap(Result result)
            {
                return new GcoreConfigurationsResultImpl((com.google.android.gms.phenotype.PhenotypeApi.ConfigurationsResult)result);
            }

                _cls1()
                {
                }
        }

    }

}
