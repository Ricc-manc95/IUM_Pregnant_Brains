// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

// Referenced classes of package com.google.android.gms.drive.query.internal:
//            zzx, zzb

public final class zzc
    implements android.os.Parcelable.Creator
{

    public zzc()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int j = zzb.zzdE(parcel);
        zzx zzx1 = null;
        int i = 0;
        MetadataBundle metadatabundle = null;
        do
        {
            if (parcel.dataPosition() < j)
            {
                int k = parcel.readInt();
                switch (0xffff & k)
                {
                default:
                    zzb.zzb(parcel, k);
                    break;

                case 1: // '\001'
                    zzx1 = (zzx)zzb.zza(parcel, k, zzx.CREATOR);
                    break;

                case 2: // '\002'
                    metadatabundle = (MetadataBundle)zzb.zza(parcel, k, MetadataBundle.CREATOR);
                    break;

                case 1000: 
                    zzb.zza(parcel, k, 4);
                    i = parcel.readInt();
                    break;
                }
            } else
            {
                if (parcel.dataPosition() != j)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
                }
                return new com.google.android.gms.drive.query.internal.zzb(i, zzx1, metadatabundle);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new com.google.android.gms.drive.query.internal.zzb[i];
    }
}