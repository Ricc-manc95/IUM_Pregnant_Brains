// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.googlehelp;

import android.content.Intent;
import com.google.android.gms.common.api.PendingResult;

// Referenced classes of package com.google.android.gms.googlehelp:
//            GoogleHelpLauncher, zzc, zza

public final class zzbwC
    implements zzbwC
{

    private final Intent zzbwC;
    private final GoogleHelpLauncher zzbwD;

    public final void zzIF()
    {
        zzbwD.handlePlayServicesUnavailable(16, zzbwC);
    }

    public final PendingResult zzq$51666RRD5TJMURR7DHIIUOBECHP6UQB45TJMQSPFCDNMQRBFDONM2S395T3MURR7DHIK2S398DM6IPBEEGTIIJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UPRDECNM6RRDDLNMSBR1E1KIUK35DPI6IRJ7A9IN6TBCEGTG____0()
    {
        return zzc.zzbwH.zza(zzbwD.mApiClient, zzbwD.mActivity, zzbwC);
    }

    public (GoogleHelpLauncher googlehelplauncher, Intent intent)
    {
        zzbwD = googlehelplauncher;
        zzbwC = intent;
        super();
    }
}
