// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzagp;
import com.google.android.gms.internal.zzagu;
import com.google.android.gms.internal.zzagx;
import com.google.android.gms.internal.zzagz;

// Referenced classes of package com.google.android.gms.drive:
//            DriveApi

public final class Drive
{

    public static final Api API;
    public static final DriveApi DriveApi = new zzagp();
    public static final Scope SCOPE_FULL = new Scope("https://www.googleapis.com/auth/drive");
    public static final com.google.android.gms.common.api.Api.zzf zzahs;

    static 
    {
        zzahs = new com.google.android.gms.common.api.Api.zzf();
        new Scope("https://www.googleapis.com/auth/drive.file");
        new Scope("https://www.googleapis.com/auth/drive.appdata");
        new Scope("https://www.googleapis.com/auth/drive.apps");
        API = new Api("Drive.API", new _cls1(), zzahs);
        new Api("Drive.INTERNAL_API", new _cls2(), zzahs);
        new zzagu();
        new zzagz();
        new zzagx();
    }

    private class _cls1 extends zza
    {
        private class zza extends com.google.android.gms.common.api.Api.zza
        {

            protected abstract Bundle zza(com.google.android.gms.common.api.Api.ApiOptions apioptions);

            public final com.google.android.gms.common.api.Api.zze zza(Context context, Looper looper, zzg zzg, Object obj, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
            {
                return new zzagr(context, looper, zzg, connectioncallbacks, onconnectionfailedlistener, zza((com.google.android.gms.common.api.Api.ApiOptions)obj));
            }

            public zza()
            {
            }
        }


        protected final Bundle zza(com.google.android.gms.common.api.Api.ApiOptions apioptions)
        {
            return new Bundle();
        }

        _cls1()
        {
        }
    }


    private class _cls2 extends zza
    {

        protected final Bundle zza(com.google.android.gms.common.api.Api.ApiOptions apioptions)
        {
            if ((zzb)apioptions == null)
            {
                return new Bundle();
            } else
            {
                throw new NoSuchMethodError();
            }
        }

        _cls2()
        {
        }

        private class zzb
            implements com.google.android.gms.common.api.Api.ApiOptions, com.google.android.gms.common.api.Api.ApiOptions.HasOptions
        {
        }

    }

}
