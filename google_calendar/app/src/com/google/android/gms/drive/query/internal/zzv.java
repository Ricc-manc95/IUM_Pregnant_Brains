// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.drive.query.Filter;

// Referenced classes of package com.google.android.gms.drive.query.internal:
//            zza, zzw, FilterHolder, zzj

public class zzv extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzw();
    public final int mVersionCode;
    public final FilterHolder zzbdr;

    zzv(int i, FilterHolder filterholder)
    {
        mVersionCode = i;
        zzbdr = filterholder;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        zzc.zza(parcel, 1, zzbdr, i, false);
        i = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

    public final Object zza(zzj zzj1)
    {
        return zzj1.zzF(zzbdr.zzaXj.zza(zzj1));
    }

}
