// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;

public final class zzbdl
{

    public static final Api API;
    private static final com.google.android.gms.common.api.Api.zzf zzahs;
    public static final com.google.android.gms.common.api.Api.zza zzaht;
    private static final com.google.android.gms.common.api.Api.zzf zzcme;
    private static final com.google.android.gms.common.api.Api.zza zzcmf;

    static 
    {
        zzahs = new com.google.android.gms.common.api.Api.zzf();
        zzcme = new com.google.android.gms.common.api.Api.zzf();
        zzaht = new _cls1();
        zzcmf = new _cls2();
        new Scope("profile");
        new Scope("email");
        API = new Api("SignIn.API", zzaht, zzahs);
        new Api("SignIn.INTERNAL_API", zzcmf, zzcme);
    }

    private class _cls1 extends com.google.android.gms.common.api.Api.zza
    {

        public final com.google.android.gms.common.api.Api.zze zza(Context context, Looper looper, zzg zzg, Object obj, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            if ((zzbdn)obj != null);
            return new zzbdx(context, looper, true, zzg, connectioncallbacks, onconnectionfailedlistener);
        }

        _cls1()
        {
        }
    }


    private class _cls2 extends com.google.android.gms.common.api.Api.zza
    {

        public final com.google.android.gms.common.api.Api.zze zza(Context context, Looper looper, zzg zzg, Object obj, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            context = (zza)obj;
            throw new NoSuchMethodError();
        }

        _cls2()
        {
        }

        private class zza
            implements com.google.android.gms.common.api.Api.ApiOptions.HasOptions
        {
        }

    }

}
