// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzg;

// Referenced classes of package com.google.android.gms.internal:
//            zzaon, zzaph

public final class zzaos extends zzaon
{

    public static final Api API;
    private static final com.google.android.gms.common.api.Api.zzf zzahs;

    public zzaos(Context context, Looper looper, zzg zzg, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        super(context, looper, 57, connectioncallbacks, onconnectionfailedlistener, zzg);
    }

    public final String zzeD()
    {
        return "com.google.android.gms.fitness.HistoryApi";
    }

    public final String zzeE()
    {
        return "com.google.android.gms.fitness.internal.IGoogleFitHistoryApi";
    }

    public final IInterface zzi(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitHistoryApi");
        if (iinterface != null && (iinterface instanceof zzaph))
        {
            return (zzaph)iinterface;
        } else
        {
            return new zzaph.zza.zza(ibinder);
        }
    }

    static 
    {
        zzahs = new com.google.android.gms.common.api.Api.zzf();
        API = new Api("Fitness.API", new zzb(), zzahs);
    }

    private class zzb extends com.google.android.gms.common.api.Api.zza
    {

        public final com.google.android.gms.common.api.Api.zze zza(Context context, Looper looper, zzg zzg, Object obj, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            return new zzaos(context, looper, zzg, connectioncallbacks, onconnectionfailedlistener);
        }

        public zzb()
        {
        }
    }

}
