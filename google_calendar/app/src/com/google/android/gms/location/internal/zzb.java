// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.location.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;

// Referenced classes of package com.google.android.gms.location.internal:
//            zzk, zzv

public class zzb extends zzl
{

    private final String zzbBV;
    public final zzv zzbBW = new _cls1();

    public zzb(Context context, Looper looper, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener, String s, zzg zzg)
    {
        super(context, looper, 23, zzg, connectioncallbacks, onconnectionfailedlistener);
        zzbBV = s;
    }

    static void zza(zzb zzb1)
    {
        if (!zzb1.isConnected())
        {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        } else
        {
            return;
        }
    }

    protected final String zzeD()
    {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    protected final String zzeE()
    {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    protected final IInterface zzi(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        if (iinterface != null && (iinterface instanceof zzk))
        {
            return (zzk)iinterface;
        } else
        {
            return new zzk.zza.zza(ibinder);
        }
    }

    protected final Bundle zzpF()
    {
        Bundle bundle = new Bundle();
        bundle.putString("client_name", zzbBV);
        return bundle;
    }

    private class _cls1
        implements zzv
    {

        private final zzb zzbBX;

        public final void zzyO()
        {
            zzb.zza(zzbBX);
        }

        public final IInterface zzyP()
            throws DeadObjectException
        {
            return (zzk)zzbBX.zzyP();
        }

        _cls1()
        {
            zzbBX = zzb.this;
            super();
        }
    }

}
