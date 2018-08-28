// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;

// Referenced classes of package com.google.android.gms.internal:
//            zzbbd

public final class zzbbf extends zzl
{

    public zzbbf(Context context, Looper looper, zzg zzg, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        super(context, looper, 38, zzg, connectioncallbacks, onconnectionfailedlistener);
    }

    protected final String zzeD()
    {
        return "com.google.android.gms.pseudonymous.service.START";
    }

    protected final String zzeE()
    {
        return "com.google.android.gms.pseudonymous.internal.IPseudonymousIdService";
    }

    protected final IInterface zzi(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.pseudonymous.internal.IPseudonymousIdService");
        if (iinterface != null && (iinterface instanceof zzbbd))
        {
            return (zzbbd)iinterface;
        } else
        {
            return new zzbbd.zza.zza(ibinder);
        }
    }
}
