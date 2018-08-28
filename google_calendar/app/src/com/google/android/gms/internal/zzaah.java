// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zzl;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzaf;
import com.google.android.gms.common.internal.zzg;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            zzbdq, zzbdl, zzbdn, zzbea, 
//            zzbdm

public final class zzaah extends zzbdq
    implements com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
{

    private static com.google.android.gms.common.api.Api.zza zzaMX;
    public final Context mContext;
    public final Handler mHandler;
    public final com.google.android.gms.common.api.Api.zza zzaIG;
    public zzg zzaKD;
    public zzbdm zzaLd;
    public final boolean zzaMY;
    public zza zzaMZ;
    public Set zzalx;

    public zzaah(Context context, Handler handler)
    {
        mContext = context;
        mHandler = handler;
        context = zzl.zzae(mContext);
        context = context.zzbZ(context.zzca("defaultGoogleSignInAccount"));
        if (context == null)
        {
            context = new HashSet();
        } else
        {
            context = new HashSet(new ArrayList(((GoogleSignInOptions) (context)).zzalr));
        }
        zzalx = context;
        zzaKD = new zzg(null, zzalx, null, 0, null, null, null, zzbdn.zzcmh);
        zzaIG = zzaMX;
        zzaMY = true;
    }

    public zzaah(Context context, Handler handler, zzg zzg1, com.google.android.gms.common.api.Api.zza zza1)
    {
        mContext = context;
        mHandler = handler;
        zzaKD = zzg1;
        zzalx = zzg1.zzaIZ;
        zzaIG = zza1;
        zzaMY = false;
    }

    static void zza(zzaah zzaah1, zzbea zzbea1)
    {
        boolean flag1 = true;
        Object obj = zzbea1.zzaQR;
        boolean flag;
        if (((ConnectionResult) (obj)).zzaEP == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj = zzbea1.zzcmt;
            zzbea1 = ((zzaf) (obj)).zzaQR;
            if (((ConnectionResult) (zzbea1)).zzaEP == 0)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                obj = String.valueOf(zzbea1);
                Log.wtf("SignInCoordinator", (new StringBuilder(String.valueOf(obj).length() + 48)).append("Sign-in succeeded with resolve account failure: ").append(((String) (obj))).toString(), new Exception());
                zzaah1.zzaMZ.zzi(zzbea1);
            } else
            {
                zzaah1.zzaMZ.zzb(com.google.android.gms.common.internal.zzr.zza.zzdc(((zzaf) (obj)).zzaPk), zzaah1.zzalx);
            }
        } else
        {
            zzaah1.zzaMZ.zzi(((ConnectionResult) (obj)));
        }
        zzaah1.zzaLd.disconnect();
    }

    public final void onConnected(Bundle bundle)
    {
        zzaLd.zza(this);
    }

    public final void onConnectionFailed(ConnectionResult connectionresult)
    {
        zzaMZ.zzi(connectionresult);
    }

    public final void onConnectionSuspended(int i)
    {
        zzaLd.disconnect();
    }

    public final void zzb(zzbea zzbea1)
    {
        mHandler.post(new _cls1(zzbea1));
    }

    static 
    {
        zzaMX = zzbdl.zzaht;
    }

    private class zza
    {

        public abstract void zzb(zzr zzr, Set set);

        public abstract void zzi(ConnectionResult connectionresult);
    }


    private class _cls1
        implements Runnable
    {

        private final zzbea zzaLt;
        private final zzaah zzaNa;

        public final void run()
        {
            zzaah.zza(zzaNa, zzaLt);
        }

        _cls1(zzbea zzbea1)
        {
            zzaNa = zzaah.this;
            zzaLt = zzbea1;
            super();
        }
    }

}
