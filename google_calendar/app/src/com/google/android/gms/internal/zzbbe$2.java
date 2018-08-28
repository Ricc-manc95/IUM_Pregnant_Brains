// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzf;

// Referenced classes of package com.google.android.gms.internal:
//            zzbbf, zzbbd

final class ApiClient extends c
{

    protected final void zza(com.google.android.gms.common.api.iClient iclient)
        throws RemoteException
    {
        iclient = (zzbbf)iclient;
        zzbbc zzbbc = zzcfz;
        ((zzbbd)iclient.zzyP()).zza(zzbbc);
    }

    ApiClient(GoogleApiClient googleapiclient)
    {
        super(googleapiclient);
    }
}
