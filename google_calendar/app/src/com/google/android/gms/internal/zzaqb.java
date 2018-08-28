// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.RecordingApi;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;

public final class zzaqb
    implements RecordingApi
{

    public zzaqb()
    {
    }

    public final PendingResult subscribe(GoogleApiClient googleapiclient, DataType datatype)
    {
        com.google.android.gms.fitness.data.Subscription.Builder builder = new com.google.android.gms.fitness.data.Subscription.Builder();
        builder.zzbhZ = datatype;
        boolean flag;
        if (builder.zzbhZ != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Must call setDataSource() or setDataType()"));
        } else
        {
            return googleapiclient.zza(new _cls4(googleapiclient, new Subscription(builder)));
        }
    }

    public final PendingResult unsubscribe(GoogleApiClient googleapiclient, DataType datatype)
    {
        return googleapiclient.zzb(new _cls6(googleapiclient, datatype));
    }

    private class _cls4 extends zzaou.zzc
    {

        private final Subscription zzbju;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb)
            throws RemoteException
        {
            zzb = (zzaou)zzb;
            zzaqe zzaqe1 = new zzaqe(this);
            ((zzapj)zzb.zzyP()).zza(new SubscribeRequest(zzbju, false, zzaqe1));
        }

        _cls4(GoogleApiClient googleapiclient, Subscription subscription)
        {
            zzbju = subscription;
            super(googleapiclient);
        }
    }


    private class _cls6 extends zzaou.zzc
    {

        private final DataType zzbji;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb)
            throws RemoteException
        {
            zzb = (zzaou)zzb;
            zzaqe zzaqe1 = new zzaqe(this);
            ((zzapj)zzb.zzyP()).zza(new UnsubscribeRequest(zzbji, null, zzaqe1));
        }

        _cls6(GoogleApiClient googleapiclient, DataType datatype)
        {
            zzbji = datatype;
            super(googleapiclient);
        }
    }

}
