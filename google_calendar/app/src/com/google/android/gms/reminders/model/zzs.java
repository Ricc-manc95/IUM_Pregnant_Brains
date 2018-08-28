// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.reminders.model:
//            zzf, zzd, zzt

public final class zzs
    implements android.os.Parcelable.Creator
{

    public zzs()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        zzd zzd1 = null;
        int j = zzb.zzdE(parcel);
        int i = 0;
        zzf zzf1 = null;
        Integer integer = null;
        String s = null;
        do
        {
            if (parcel.dataPosition() < j)
            {
                int k = parcel.readInt();
                switch (0xffff & k)
                {
                case 4: // '\004'
                default:
                    zzb.zzb(parcel, k);
                    break;

                case 1: // '\001'
                    zzb.zza(parcel, k, 4);
                    i = parcel.readInt();
                    break;

                case 2: // '\002'
                    s = zzb.zzq(parcel, k);
                    break;

                case 3: // '\003'
                    integer = zzb.zzh(parcel, k);
                    break;

                case 5: // '\005'
                    zzf1 = (zzf)zzb.zza(parcel, k, zzf.CREATOR);
                    break;

                case 6: // '\006'
                    zzd1 = (zzd)zzb.zza(parcel, k, zzd.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new zzt(i, s, integer, zzf1, zzd1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzt[i];
    }
}
