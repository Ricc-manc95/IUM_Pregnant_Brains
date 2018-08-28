// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

// Referenced classes of package com.google.android.gms.drive.query.internal:
//            zza, zzc, zzi, zzj, 
//            zzx

public class zzb extends zza
{

    public static final com.google.android.gms.drive.query.internal.zzc CREATOR = new com.google.android.gms.drive.query.internal.zzc();
    public final int mVersionCode;
    public final zzx zzbdb;
    public final MetadataBundle zzbdc;
    private final MetadataField zzbdd;

    zzb(int i, zzx zzx, MetadataBundle metadatabundle)
    {
        mVersionCode = i;
        zzbdb = zzx;
        zzbdc = metadatabundle;
        zzbdd = zzi.zza(metadatabundle);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        zzc.zza(parcel, 1, zzbdb, i, false);
        zzc.zza(parcel, 2, zzbdc, i, false);
        i = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

    public final Object zza(zzj zzj1)
    {
        zzx zzx = zzbdb;
        MetadataField metadatafield = zzbdd;
        MetadataBundle metadatabundle = zzbdc;
        return zzj1.zzb(zzx, metadatafield, zzbdd.zzy(metadatabundle.zzbbM));
    }

}
