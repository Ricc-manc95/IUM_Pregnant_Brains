// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.phenotype.impl;

import android.content.SharedPreferences;
import com.google.android.gms.phenotype.Configurations;
import com.google.android.gms.phenotype.PhenotypeFlagCommitter;
import com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient;
import com.google.android.libraries.gcoreclient.common.api.GcorePendingResult;
import com.google.android.libraries.gcoreclient.common.api.support.GcoreWrapper;
import com.google.android.libraries.gcoreclient.phenotype.GcoreConfigurations;

// Referenced classes of package com.google.android.libraries.gcoreclient.phenotype.impl:
//            BasePhenotypeImpl, GcoreConfigurationsImpl, BaseGcoreConfigurationsImpl

public final class PhenotypeImpl extends BasePhenotypeImpl
{

    public PhenotypeImpl()
    {
        new GcoreWrapper();
    }

    public final volatile GcorePendingResult commitToConfiguration(GcoreGoogleApiClient gcoregoogleapiclient, String s)
    {
        return super.commitToConfiguration(gcoregoogleapiclient, s);
    }

    public final volatile GcorePendingResult getConfigurationSnapshot(GcoreGoogleApiClient gcoregoogleapiclient, String s, String s1)
    {
        return super.getConfigurationSnapshot(gcoregoogleapiclient, s, s1);
    }

    public final volatile GcorePendingResult register(GcoreGoogleApiClient gcoregoogleapiclient, String s, int i, String as[], byte abyte0[])
    {
        return super.register(gcoregoogleapiclient, s, i, as, abyte0);
    }

    public final void writeToSharedPrefs(SharedPreferences sharedpreferences, GcoreConfigurations gcoreconfigurations)
    {
        PhenotypeFlagCommitter.writeToSharedPrefs(sharedpreferences, ((BaseGcoreConfigurationsImpl) ((GcoreConfigurationsImpl)gcoreconfigurations)).configurations.configurations);
        super.writeToSharedPrefs(sharedpreferences, gcoreconfigurations);
    }
}
