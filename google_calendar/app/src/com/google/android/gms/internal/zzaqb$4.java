// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.fitness.request.SubscribeRequest;

// Referenced classes of package com.google.android.gms.internal:
//            zzaou, zzaqe, zzapj

final class ApiClient extends c
{

    private final Subscription zzbju;

    protected final void zza(com.google.android.gms.common.api.iClient iclient)
        throws RemoteException
    {
        iclient = (zzaou)iclient;
        zzaqe zzaqe1 = new zzaqe(this);
        ((zzapj)iclient.zzyP()).zza(new SubscribeRequest(zzbju, false, zzaqe1));
    }

    ApiClient(GoogleApiClient googleapiclient, Subscription subscription)
    {
        zzbju = subscription;
        super(googleapiclient);
    }
}
