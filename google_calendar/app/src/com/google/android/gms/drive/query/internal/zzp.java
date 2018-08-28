// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.metadata.zzb;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.google.android.gms.drive.query.internal:
//            zza, zzq, zzi, zzj

public class zzp extends zza
{

    public static final zzq CREATOR = new zzq();
    public final int mVersionCode;
    public final MetadataBundle zzbdc;
    private final zzb zzbdp;

    zzp(int i, MetadataBundle metadatabundle)
    {
        mVersionCode = i;
        zzbdc = metadatabundle;
        zzbdp = (zzb)zzi.zza(metadatabundle);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        zzc.zza(parcel, 1, zzbdc, i, false);
        i = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

    public final Object zza(zzj zzj1)
    {
        zzb zzb1 = zzbdp;
        MetadataBundle metadatabundle = zzbdc;
        return zzj1.zzb(zzb1, ((Collection)zzbdp.zzy(metadatabundle.zzbbM)).iterator().next());
    }

}
