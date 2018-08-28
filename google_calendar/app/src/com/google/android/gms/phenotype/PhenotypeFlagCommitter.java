// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.gms.phenotype:
//            Phenotype, Configuration, Flag, PhenotypeApi, 
//            Configurations

public abstract class PhenotypeFlagCommitter
{

    private final GoogleApiClient mApiClient;
    private final String mPackageName;
    private long zzUA;
    private final PhenotypeApi zzcaU;

    private PhenotypeFlagCommitter(GoogleApiClient googleapiclient, PhenotypeApi phenotypeapi, String s)
    {
        mApiClient = googleapiclient;
        zzcaU = phenotypeapi;
        mPackageName = s;
        zzUA = 2000L;
    }

    public PhenotypeFlagCommitter(GoogleApiClient googleapiclient, String s)
    {
        this(googleapiclient, Phenotype.PhenotypeApi, s);
    }

    public static transient void writeToSharedPrefs(SharedPreferences sharedpreferences, Configuration aconfiguration[])
    {
        int i;
        int l;
        sharedpreferences = sharedpreferences.edit();
        l = aconfiguration.length;
        i = 0;
_L12:
        if (i >= l) goto _L2; else goto _L1
_L1:
        Flag aflag[];
        int k;
        int i1;
        Configuration configuration = aconfiguration[i];
        if (configuration == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        String as[] = configuration.deleteFlags;
        i1 = as.length;
        for (int j = 0; j < i1; j++)
        {
            sharedpreferences.remove(as[j]);
        }

        aflag = configuration.flags;
        i1 = aflag.length;
        k = 0;
_L10:
        Flag flag;
        if (k >= i1)
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = aflag[k];
        flag.flagValueType;
        JVM INSTR tableswitch 1 5: default 132
    //                   1 141
    //                   2 181
    //                   3 221
    //                   4 262
    //                   5 302;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L8:
        break MISSING_BLOCK_LABEL_302;
_L3:
        break; /* Loop/switch isn't completed */
_L4:
        break; /* Loop/switch isn't completed */
_L11:
        k++;
        if (true) goto _L10; else goto _L9
_L9:
        String s = flag.name;
        if (flag.flagValueType != 1)
        {
            throw new IllegalArgumentException("Not a long type");
        }
        sharedpreferences.putLong(s, flag.zzcaC);
          goto _L11
_L5:
        String s1 = flag.name;
        if (flag.flagValueType != 2)
        {
            throw new IllegalArgumentException("Not a boolean type");
        }
        sharedpreferences.putBoolean(s1, flag.booleanValue);
          goto _L11
_L6:
        String s2 = flag.name;
        if (flag.flagValueType != 3)
        {
            throw new IllegalArgumentException("Not a double type");
        }
        sharedpreferences.putFloat(s2, (float)flag.doubleValue);
          goto _L11
_L7:
        String s3 = flag.name;
        if (flag.flagValueType != 4)
        {
            throw new IllegalArgumentException("Not a String type");
        }
        sharedpreferences.putString(s3, flag.stringValue);
          goto _L11
        if (flag.flagValueType != 5)
        {
            throw new IllegalArgumentException("Not a bytes type");
        }
        String s4 = Base64.encodeToString(flag.zzcaD, 3);
        sharedpreferences.putString(flag.name, s4);
          goto _L11
        i++;
          goto _L12
_L2:
        if (!sharedpreferences.commit())
        {
            Log.w("PhenotypeFlagCommitter", "Failed to commit Phenotype configs to SharedPreferences!");
        }
        return;
    }

    public abstract void handleConfigurations(Configurations configurations);

    public final boolean zzF(String s, int i)
    {
        do
        {
            if (i <= 0)
            {
                s = String.valueOf(mPackageName);
                if (s.length() != 0)
                {
                    s = "No more attempts remaining, giving up for ".concat(s);
                } else
                {
                    s = new String("No more attempts remaining, giving up for ");
                }
                Log.w("PhenotypeFlagCommitter", s);
                return false;
            }
            PhenotypeApi.ConfigurationsResult configurationsresult = (PhenotypeApi.ConfigurationsResult)zzcaU.getConfigurationSnapshot(mApiClient, mPackageName, s, null).await(zzUA, TimeUnit.MILLISECONDS);
            boolean flag;
            if (configurationsresult.getStatus().zzaEP <= 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                s = mPackageName;
                Log.e("PhenotypeFlagCommitter", (new StringBuilder(String.valueOf(s).length() + 31)).append("Retrieving snapshot for ").append(s).append(" failed").toString());
                return false;
            }
            handleConfigurations(configurationsresult.getConfigurations());
            if (((Status)zzcaU.commitToConfiguration(mApiClient, configurationsresult.getConfigurations().snapshotToken).await(zzUA, TimeUnit.MILLISECONDS)).zzaEP <= 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                String s1 = mPackageName;
                Log.w("PhenotypeFlagCommitter", (new StringBuilder(String.valueOf(s1).length() + 41)).append("Committing snapshot for ").append(s1).append(" failed, retrying").toString());
                i--;
            } else
            {
                s = String.valueOf(mPackageName);
                if (s.length() != 0)
                {
                    "Experiment Configs successfully retrieved for ".concat(s);
                } else
                {
                    new String("Experiment Configs successfully retrieved for ");
                }
                return true;
            }
        } while (true);
    }
}
