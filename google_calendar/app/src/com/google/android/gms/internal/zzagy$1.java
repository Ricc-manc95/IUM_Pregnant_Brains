// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzf;

// Referenced classes of package com.google.android.gms.internal:
//            zzagr, zzahu, zzaho, zzagy

final class ApiClient extends f
{

    private final boolean zzbag;
    private final zzagy zzbah;

    protected final void zza(com.google.android.gms.common.api.iClient iclient)
        throws RemoteException
    {
        ((zzahu)((zzagr)iclient).zzyP()).zza(new zzaho(zzbah.zzaWH, zzbag), new d(this));
    }

    ApiClient(zzagy zzagy1, GoogleApiClient googleapiclient, boolean flag)
    {
        zzbah = zzagy1;
        zzbag = flag;
        super(googleapiclient);
    }
}
