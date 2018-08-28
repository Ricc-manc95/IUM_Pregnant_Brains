// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.googlehelp;

import android.os.AsyncTask;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.googlehelp.internal.common.zza;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// Referenced classes of package com.google.android.gms.googlehelp:
//            zza

public final class zzc
{

    public static final Api API;
    private static final com.google.android.gms.common.api.Api.zzf zzahs;
    private static final com.google.android.gms.common.api.Api.zza zzaht;
    public static final com.google.android.gms.googlehelp.zza zzbwH = new zza();
    private static final Executor zzbwI = Executors.newSingleThreadExecutor();

    public static void zza(GoogleApiClient googleapiclient, zza zza1)
    {
        (new _cls2(googleapiclient, zza1)).executeOnExecutor(zzbwI, new Void[0]);
    }

    static 
    {
        zzahs = new com.google.android.gms.common.api.Api.zzf();
        zzaht = new _cls1();
        API = new Api("Help.API", zzaht, zzahs);
    }

    private class _cls2 extends AsyncTask
    {

        public final GoogleApiClient zzbwJ;
        public final zza zzbwK;

        protected final Object doInBackground(Object aobj[])
        {
            class _cls1
                implements ResultCallback
            {

                private final _cls2 zzbwL;

                public final void onResult(Result result)
                {
                    boolean flag1;
                    if (((Status)result).zzaEP <= 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (!flag1)
                    {
                        zzbwL.zzbwK.zzIF();
                    }
                    zzbwL.zzbwJ.disconnect();
                }

                _cls1()
                {
                    zzbwL = _cls2.this;
                    super();
                }
            }

            boolean flag;
            if (zzbwJ.blockingConnect().zzaEP == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                zzbwK._mth51666RRD5TJMURR7DHIIUOBECHP6UQB45TJMQSPFCDNMQRBFDONM2S395T3MURR7DHIK2S398DM6IPBEEGTIIJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UPRDECNM6RRDDLNMSBR1E1KIUK35DPI6IRJ7A9IN6TBCEGTG____0().setResultCallback(new _cls1());
            } else
            {
                zzbwK.zzIF();
                zzbwJ.disconnect();
            }
            return null;
        }

        _cls2(GoogleApiClient googleapiclient, zza zza1)
        {
            zzbwJ = googleapiclient;
            zzbwK = zza1;
            super();
        }

        private class zza
        {

            public abstract void zzIF();

            public abstract PendingResult zzq$51666RRD5TJMURR7DHIIUOBECHP6UQB45TJMQSPFCDNMQRBFDONM2S395T3MURR7DHIK2S398DM6IPBEEGTIIJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UPRDECNM6RRDDLNMSBR1E1KIUK35DPI6IRJ7A9IN6TBCEGTG____0();
        }

    }


    private class _cls1 extends com.google.android.gms.common.api.Api.zza
    {

        public final com.google.android.gms.common.api.Api.zze zza(Context context, Looper looper, zzg zzg, Object obj, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            return new zzb(context, looper, zzg, connectioncallbacks, onconnectionfailedlistener);
        }

        _cls1()
        {
        }
    }

}
