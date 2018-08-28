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
//            zzaon, zzapj

public final class zzaou extends zzaon
{

    public static final Api API;
    private static final com.google.android.gms.common.api.Api.zzf zzahs;

    public zzaou(Context context, Looper looper, zzg zzg, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        super(context, looper, 56, connectioncallbacks, onconnectionfailedlistener, zzg);
    }

    public final String zzeD()
    {
        return "com.google.android.gms.fitness.RecordingApi";
    }

    public final String zzeE()
    {
        return "com.google.android.gms.fitness.internal.IGoogleFitRecordingApi";
    }

    public final IInterface zzi(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitRecordingApi");
        if (iinterface != null && (iinterface instanceof zzapj))
        {
            return (zzapj)iinterface;
        } else
        {
            return new zzapj.zza.zza(ibinder);
        }
    }

    static 
    {
        zzahs = new com.google.android.gms.common.api.Api.zzf();
        API = new Api("Fitness.RECORDING_API", new zzb(), zzahs);
    }

    private class zzb extends com.google.android.gms.common.api.Api.zza
    {

        public final com.google.android.gms.common.api.Api.zze zza(Context context, Looper looper, zzg zzg, Object obj, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            return new zzaou(context, looper, zzg, connectioncallbacks, onconnectionfailedlistener);
        }

        public zzb()
        {
        }
    }

}
