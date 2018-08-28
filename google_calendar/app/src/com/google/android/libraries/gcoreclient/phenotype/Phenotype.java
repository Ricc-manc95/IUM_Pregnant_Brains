// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.phenotype;

import android.content.SharedPreferences;
import com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient;
import com.google.android.libraries.gcoreclient.common.api.GcorePendingResult;

// Referenced classes of package com.google.android.libraries.gcoreclient.phenotype:
//            GcoreConfigurations

public interface Phenotype
{

    public abstract GcorePendingResult commitToConfiguration(GcoreGoogleApiClient gcoregoogleapiclient, String s);

    public abstract GcorePendingResult getConfigurationSnapshot(GcoreGoogleApiClient gcoregoogleapiclient, String s, String s1);

    public abstract GcorePendingResult register(GcoreGoogleApiClient gcoregoogleapiclient, String s, int i, String as[], byte abyte0[]);

    public abstract void writeToSharedPrefs(SharedPreferences sharedpreferences, GcoreConfigurations gcoreconfigurations);
}
