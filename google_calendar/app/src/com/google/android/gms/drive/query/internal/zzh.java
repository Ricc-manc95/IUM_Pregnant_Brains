// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.drive.query.internal:
//            zzb, zzd, zzr, zzv, 
//            zzp, zzt, zzn, zzl, 
//            zzz, FilterHolder

public final class zzh
    implements android.os.Parcelable.Creator
{

    public zzh()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        zzz zzz1 = null;
        int j = zzb.zzdE(parcel);
        int i = 0;
        zzl zzl1 = null;
        zzn zzn1 = null;
        zzt zzt1 = null;
        zzp zzp1 = null;
        zzv zzv1 = null;
        zzr zzr1 = null;
        zzd zzd1 = null;
        com.google.android.gms.drive.query.internal.zzb zzb1 = null;
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
                    zzb1 = (com.google.android.gms.drive.query.internal.zzb)zzb.zza(parcel, k, zzb.CREATOR);
                    break;

                case 2: // '\002'
                    zzd1 = (zzd)zzb.zza(parcel, k, zzd.CREATOR);
                    break;

                case 3: // '\003'
                    zzr1 = (zzr)zzb.zza(parcel, k, zzr.CREATOR);
                    break;

                case 4: // '\004'
                    zzv1 = (zzv)zzb.zza(parcel, k, zzv.CREATOR);
                    break;

                case 5: // '\005'
                    zzp1 = (zzp)zzb.zza(parcel, k, zzp.CREATOR);
                    break;

                case 6: // '\006'
                    zzt1 = (zzt)zzb.zza(parcel, k, zzt.CREATOR);
                    break;

                case 7: // '\007'
                    zzn1 = (zzn)zzb.zza(parcel, k, zzn.CREATOR);
                    break;

                case 1000: 
                    zzb.zza(parcel, k, 4);
                    i = parcel.readInt();
                    break;

                case 8: // '\b'
                    zzl1 = (zzl)zzb.zza(parcel, k, zzl.CREATOR);
                    break;

                case 9: // '\t'
                    zzz1 = (zzz)zzb.zza(parcel, k, zzz.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new FilterHolder(i, zzb1, zzd1, zzr1, zzv1, zzp1, zzt1, zzn1, zzl1, zzz1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new FilterHolder[i];
    }
}
