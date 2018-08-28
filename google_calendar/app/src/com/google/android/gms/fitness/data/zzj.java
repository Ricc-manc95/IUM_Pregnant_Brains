// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.fitness.data:
//            Field, DataType

public final class zzj
    implements android.os.Parcelable.Creator
{

    public zzj()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        java.util.ArrayList arraylist = null;
        int j = zzb.zzdE(parcel);
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
                    zzb.zzb(parcel, k);
                    break;

                case 1: // '\001'
                    s = zzb.zzq(parcel, k);
                    break;

                case 2: // '\002'
                    arraylist = zzb.zzc(parcel, k, Field.CREATOR);
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
                return new DataType(i, s, arraylist);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new DataType[i];
    }
}
