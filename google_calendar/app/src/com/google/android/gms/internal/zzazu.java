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
//            zzazs

public final class zzazu extends zzl
{

    public zzazu(Context context, Looper looper, zzg zzg, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        super(context, looper, 51, zzg, connectioncallbacks, onconnectionfailedlistener);
    }

    protected final String zzeD()
    {
        return "com.google.android.gms.phenotype.service.START";
    }

    protected final String zzeE()
    {
        return "com.google.android.gms.phenotype.internal.IPhenotypeService";
    }

    protected final IInterface zzi(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.phenotype.internal.IPhenotypeService");
        if (iinterface != null && (iinterface instanceof zzazs))
        {
            return (zzazs)iinterface;
        } else
        {
            return new zzazs.zza.zza(ibinder);
        }
    }
}
