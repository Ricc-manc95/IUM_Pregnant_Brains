// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.zzapr;

// Referenced classes of package com.google.android.gms.fitness.request:
//            zzbj

public class SubscribeRequest extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzbj();
    public final int mVersionCode;
    public final zzapr zzbjR;
    public Subscription zzblg;
    public final boolean zzblh;

    SubscribeRequest(int i, Subscription subscription, boolean flag, IBinder ibinder)
    {
        mVersionCode = i;
        zzblg = subscription;
        zzblh = flag;
        zzbjR = com.google.android.gms.internal.zzapr.zza.zzeF(ibinder);
    }

    public SubscribeRequest(Subscription subscription, boolean flag, zzapr zzapr1)
    {
        mVersionCode = 3;
        zzblg = subscription;
        zzblh = false;
        zzbjR = zzapr1;
    }

    public String toString()
    {
        return (new com.google.android.gms.common.internal.zzaa.zza(this)).zzh("subscription", zzblg).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        zzc.zza(parcel, 1, zzblg, i, false);
        boolean flag1 = zzblh;
        zzc.zzb(parcel, 2, 4);
        IBinder ibinder;
        if (flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (zzbjR == null)
        {
            ibinder = null;
        } else
        {
            ibinder = zzbjR.asBinder();
        }
        zzc.zza(parcel, 3, ibinder, false);
        i = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

}
