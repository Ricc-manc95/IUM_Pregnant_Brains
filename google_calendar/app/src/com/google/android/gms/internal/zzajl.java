// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.internal:
//            zzajm

public class zzajl extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzajm();
    public final int mVersionCode;
    public final boolean zzaXu;
    public final int zzaXv;
    public final int zzbaK;

    zzajl(int i, int j, int k, boolean flag)
    {
        mVersionCode = i;
        zzbaK = j;
        zzaXv = k;
        zzaXu = flag;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        i = 1;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        k = zzbaK;
        zzc.zzb(parcel, 2, 4);
        parcel.writeInt(k);
        k = zzaXv;
        zzc.zzb(parcel, 3, 4);
        parcel.writeInt(k);
        boolean flag = zzaXu;
        zzc.zzb(parcel, 4, 4);
        if (!flag)
        {
            i = 0;
        }
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

}
