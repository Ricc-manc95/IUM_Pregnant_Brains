// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.googlehelp;

import android.os.AsyncTask;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

final class zzbwK extends AsyncTask
{

    public final GoogleApiClient zzbwJ;
    public final a zzbwK;

    protected final Object doInBackground(Object aobj[])
    {
        class _cls1
            implements ResultCallback
        {

            private final zzc._cls2 zzbwL;

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
                zzbwL = zzc._cls2.this;
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

    a(GoogleApiClient googleapiclient, a a)
    {
        zzbwJ = googleapiclient;
        zzbwK = a;
        super();
    }
}
