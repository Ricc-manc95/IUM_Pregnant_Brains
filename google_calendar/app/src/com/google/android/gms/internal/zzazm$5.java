// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

// Referenced classes of package com.google.android.gms.internal:
//            zzayx, zzys

final class ApiClient extends a
{

    private final String zzbZB;
    private final int zzbZY;
    private final String zzbZv;
    private final int zzcab;

    protected final void zza(com.google.android.gms.common.api.iClient iclient)
        throws RemoteException
    {
        com.google.android.gms.common.internal.zzs zzs = ((zzayx)iclient).zzb(this, zzbZv, zzbZB, zzbZY, zzcab);
        synchronized (super.zzaJZ)
        {
            super.zzaKh = zzs;
        }
        return;
        exception;
        iclient;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ApiClient(GoogleApiClient googleapiclient, String s, String s1, int i, int j)
    {
        zzbZv = s;
        zzbZB = s1;
        zzbZY = i;
        zzcab = j;
        super(googleapiclient);
    }
}
