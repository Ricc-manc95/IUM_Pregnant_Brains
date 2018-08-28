// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.drive.query.Filter;

// Referenced classes of package com.google.android.gms.drive.query.internal:
//            zzh, zzb, zzd, zzr, 
//            zzv, zzp, zzt, zzn, 
//            zzl, zzz

public class FilterHolder extends zza
    implements ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new zzh();
    public final int mVersionCode;
    public final Filter zzaXj;
    public final zzb zzbdf;
    public final zzd zzbdg;
    public final zzr zzbdh;
    public final zzv zzbdi;
    public final zzp zzbdj;
    public final zzt zzbdk;
    public final zzn zzbdl;
    public final zzl zzbdm;
    public final zzz zzbdn;

    FilterHolder(int i, zzb zzb, zzd zzd, zzr zzr, zzv zzv, zzp zzp, zzt zzt, 
            zzn zzn, zzl zzl, zzz zzz)
    {
        mVersionCode = i;
        zzbdf = zzb;
        zzbdg = zzd;
        zzbdh = zzr;
        zzbdi = zzv;
        zzbdj = zzp;
        zzbdk = zzt;
        zzbdl = zzn;
        zzbdm = zzl;
        zzbdn = zzz;
        if (zzbdf != null)
        {
            zzaXj = zzbdf;
            return;
        }
        if (zzbdg != null)
        {
            zzaXj = zzbdg;
            return;
        }
        if (zzbdh != null)
        {
            zzaXj = zzbdh;
            return;
        }
        if (zzbdi != null)
        {
            zzaXj = zzbdi;
            return;
        }
        if (zzbdj != null)
        {
            zzaXj = zzbdj;
            return;
        }
        if (zzbdk != null)
        {
            zzaXj = zzbdk;
            return;
        }
        if (zzbdl != null)
        {
            zzaXj = zzbdl;
            return;
        }
        if (zzbdm != null)
        {
            zzaXj = zzbdm;
            return;
        }
        if (zzbdn != null)
        {
            zzaXj = zzbdn;
            return;
        } else
        {
            throw new IllegalArgumentException("At least one filter must be set.");
        }
    }

    public String toString()
    {
        return String.format("FilterHolder[%s]", new Object[] {
            zzaXj
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        zzc.zza(parcel, 1, zzbdf, i, false);
        zzc.zza(parcel, 2, zzbdg, i, false);
        zzc.zza(parcel, 3, zzbdh, i, false);
        zzc.zza(parcel, 4, zzbdi, i, false);
        zzc.zza(parcel, 5, zzbdj, i, false);
        zzc.zza(parcel, 6, zzbdk, i, false);
        zzc.zza(parcel, 7, zzbdl, i, false);
        int k = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 8, zzbdm, i, false);
        zzc.zza(parcel, 9, zzbdn, i, false);
        zzc.zzJ(parcel, j);
    }

}
