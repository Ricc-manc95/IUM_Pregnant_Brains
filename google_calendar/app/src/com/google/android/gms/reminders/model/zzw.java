// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.reminders.model:
//            zzad, zzy, zzj, zzal, 
//            zzv, zzan, zzz

public final class zzw
    implements android.os.Parcelable.Creator
{

    public zzw()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        zzan zzan1 = null;
        int j = zzb.zzdE(parcel);
        int i = 0;
        zzv zzv1 = null;
        zzal zzal1 = null;
        zzj zzj1 = null;
        zzy zzy1 = null;
        zzad zzad1 = null;
        Integer integer = null;
        Integer integer1 = null;
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
                    zzb.zza(parcel, k, 4);
                    i = parcel.readInt();
                    break;

                case 2: // '\002'
                    integer1 = zzb.zzh(parcel, k);
                    break;

                case 3: // '\003'
                    integer = zzb.zzh(parcel, k);
                    break;

                case 4: // '\004'
                    zzad1 = (zzad)zzb.zza(parcel, k, zzad.CREATOR);
                    break;

                case 5: // '\005'
                    zzy1 = (zzy)zzb.zza(parcel, k, zzy.CREATOR);
                    break;

                case 6: // '\006'
                    zzj1 = (zzj)zzb.zza(parcel, k, zzj.CREATOR);
                    break;

                case 7: // '\007'
                    zzal1 = (zzal)zzb.zza(parcel, k, zzal.CREATOR);
                    break;

                case 8: // '\b'
                    zzv1 = (zzv)zzb.zza(parcel, k, zzv.CREATOR);
                    break;

                case 9: // '\t'
                    zzan1 = (zzan)zzb.zza(parcel, k, zzan.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new zzz(i, integer1, integer, zzad1, zzy1, zzj1, zzal1, zzv1, zzan1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzz[i];
    }
}
