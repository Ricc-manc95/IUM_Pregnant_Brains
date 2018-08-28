// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.internal:
//            zzbdp

public class zzbdo extends zza
    implements Result
{

    public static final android.os.Parcelable.Creator CREATOR = new zzbdp();
    public final int mVersionCode;
    public int zzcmm;
    public Intent zzcmn;

    public zzbdo()
    {
        this(0, null);
    }

    zzbdo(int i, int j, Intent intent)
    {
        mVersionCode = i;
        zzcmm = j;
        zzcmn = intent;
    }

    private zzbdo(int i, Intent intent)
    {
        this(2, 0, null);
    }

    public final Status getStatus()
    {
        if (zzcmm == 0)
        {
            return Status.zzaJt;
        } else
        {
            return Status.zzaJx;
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        k = zzcmm;
        zzc.zzb(parcel, 2, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 3, zzcmn, i, false);
        zzc.zzJ(parcel, j);
    }

}
