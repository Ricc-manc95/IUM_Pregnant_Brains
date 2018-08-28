// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.internal.zze;
import com.google.android.gms.location.internal.zzh;
import com.google.android.gms.location.internal.zzn;
import com.google.android.gms.location.internal.zzw;

// Referenced classes of package com.google.android.gms.location:
//            FusedLocationProviderApi

public final class LocationServices
{

    public static final Api API;
    public static final FusedLocationProviderApi FusedLocationApi = new zze();
    private static final com.google.android.gms.common.api.Api.zzf zzahs;
    private static final com.google.android.gms.common.api.Api.zza zzaht;

    public static zzn zzr(GoogleApiClient googleapiclient)
    {
        boolean flag1 = true;
        boolean flag;
        if (googleapiclient != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("GoogleApiClient parameter is required."));
        }
        googleapiclient = (zzn)googleapiclient.zza(zzahs);
        if (googleapiclient != null)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature."));
        } else
        {
            return googleapiclient;
        }
    }

    static 
    {
        zzahs = new com.google.android.gms.common.api.Api.zzf();
        zzaht = new _cls1();
        API = new Api("LocationServices.API", zzaht, zzahs);
        new zzh();
        new zzw();
    }

    private class _cls1 extends com.google.android.gms.common.api.Api.zza
    {

        public final com.google.android.gms.common.api.Api.zze zza(Context context, Looper looper, zzg zzg, Object obj, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            return new zzn(context, looper, connectioncallbacks, onconnectionfailedlistener, "locationServices", zzg);
        }

        _cls1()
        {
        }
    }

}
