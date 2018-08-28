// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.remote;

import com.google.android.gms.phenotype.PhenotypeFlag;

// Referenced classes of package com.google.android.apps.calendar.config.remote:
//            RemoteFeature

public final class PrimesApiLoggingFeature extends RemoteFeature
{

    public final PhenotypeFlag mSamplingApiEventListFlag;
    public final PhenotypeFlag mSamplingApiFlag;
    public final PhenotypeFlag mSamplingSyncFlag;

    public PrimesApiLoggingFeature()
    {
        super("PrimesApiLogging", "PALJ", false);
        com.google.android.gms.phenotype.PhenotypeFlag.Factory factory = super.flagBuilder;
        String s = String.valueOf(factory.zzcaQ);
        String s1 = String.valueOf("sampling_api");
        String s2;
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        s1 = String.valueOf(factory.zzcaR);
        s2 = String.valueOf("sampling_api");
        if (s2.length() != 0)
        {
            s1 = s1.concat(s2);
        } else
        {
            s1 = new String(s1);
        }
        mSamplingApiFlag = PhenotypeFlag.zzb(s, s1, factory.zzcaO, factory.zzagh, 0.20000000000000001D);
        factory = super.flagBuilder;
        s = String.valueOf(factory.zzcaQ);
        s1 = String.valueOf("sampling_api_event_list");
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        s1 = String.valueOf(factory.zzcaR);
        s2 = String.valueOf("sampling_api_event_list");
        if (s2.length() != 0)
        {
            s1 = s1.concat(s2);
        } else
        {
            s1 = new String(s1);
        }
        mSamplingApiEventListFlag = PhenotypeFlag.zzb(s, s1, factory.zzcaO, factory.zzagh, 0.20000000000000001D);
        factory = super.flagBuilder;
        s = String.valueOf(factory.zzcaQ);
        s1 = String.valueOf("sampling_sync");
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        s1 = String.valueOf(factory.zzcaR);
        s2 = String.valueOf("sampling_sync");
        if (s2.length() != 0)
        {
            s1 = s1.concat(s2);
        } else
        {
            s1 = new String(s1);
        }
        mSamplingSyncFlag = PhenotypeFlag.zzb(s, s1, factory.zzcaO, factory.zzagh, 1.0D);
    }
}
