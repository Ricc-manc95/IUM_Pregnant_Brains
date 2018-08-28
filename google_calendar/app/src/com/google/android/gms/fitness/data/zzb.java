// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;

// Referenced classes of package com.google.android.gms.fitness.data:
//            Application

public final class zzb
    implements android.os.Parcelable.Creator
{

    public zzb()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        String s1 = null;
        int j = com.google.android.gms.common.internal.safeparcel.zzb.zzdE(parcel);
        int i = 0;
        String s = null;
        do
        {
            if (parcel.dataPosition() < j)
            {
                int k = parcel.readInt();
                switch (0xffff & k)
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, k);
                    break;

                case 1: // '\001'
                    s = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, k);
                    break;

                case 2: // '\002'
                    com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, k);
                    break;

                case 3: // '\003'
                    s1 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, k);
                    break;

                case 1000: 
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, k, 4);
                    i = parcel.readInt();
                    break;
                }
            } else
            {
                if (parcel.dataPosition() != j)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
                }
                return new Application(i, s, s1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new Application[i];
    }
}
