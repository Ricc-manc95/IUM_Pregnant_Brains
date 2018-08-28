// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.zzazt;

// Referenced classes of package com.google.android.gms.phenotype:
//            PhenotypeApi

public final class Phenotype
{

    public static final Api API;
    public static final PhenotypeApi PhenotypeApi = new zzazt();
    private static final com.google.android.gms.common.api.Api.zzf zzahs;
    private static final com.google.android.gms.common.api.Api.zza zzaht;

    static 
    {
        zzahs = new com.google.android.gms.common.api.Api.zzf();
        zzaht = new _cls1();
        API = new Api("Phenotype.API", zzaht, zzahs);
    }

    private class _cls1 extends com.google.android.gms.common.api.Api.zza
    {

        public final com.google.android.gms.common.api.Api.zze zza(Context context, Looper looper, zzg zzg, Object obj, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            return new zzazu(context, looper, zzg, connectioncallbacks, onconnectionfailedlistener);
        }

        _cls1()
        {
        }
    }

}
