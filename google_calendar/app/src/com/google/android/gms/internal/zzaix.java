// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.internal:
//            zzaiy, zzajl

public class zzaix extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzaiy();
    public final int mVersionCode;
    public final zzajl zzbbl;

    zzaix(int i, zzajl zzajl)
    {
        mVersionCode = i;
        zzbbl = zzajl;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzbbl, i, false);
        zzc.zzJ(parcel, j);
    }

}
