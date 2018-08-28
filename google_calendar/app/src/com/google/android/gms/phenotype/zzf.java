// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.phenotype:
//            Flag, FlagOverride

public final class zzf
    implements android.os.Parcelable.Creator
{

    public zzf()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        Flag flag = null;
        int j = zzb.zzdE(parcel);
        boolean flag1 = false;
        String s = null;
        String s1 = null;
        int i = 0;
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
                    s1 = zzb.zzq(parcel, k);
                    break;

                case 3: // '\003'
                    s = zzb.zzq(parcel, k);
                    break;

                case 4: // '\004'
                    flag = (Flag)zzb.zza(parcel, k, Flag.CREATOR);
                    break;

                case 5: // '\005'
                    zzb.zza(parcel, k, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    break;
                }
            } else
            {
                if (parcel.dataPosition() != j)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
                }
                return new FlagOverride(i, s1, s, flag, flag1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new FlagOverride[i];
    }
}
