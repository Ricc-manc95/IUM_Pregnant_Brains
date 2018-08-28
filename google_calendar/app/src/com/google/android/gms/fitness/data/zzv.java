// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.fitness.data:
//            RawDataPoint, RawDataSet

public final class zzv
    implements android.os.Parcelable.Creator
{

    public zzv()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int l = zzb.zzdE(parcel);
        java.util.ArrayList arraylist = null;
        boolean flag = false;
        int i = 0;
        int j = 0;
        int k = 0;
        do
        {
            if (parcel.dataPosition() < l)
            {
                int i1 = parcel.readInt();
                switch (0xffff & i1)
                {
                default:
                    zzb.zzb(parcel, i1);
                    break;

                case 1: // '\001'
                    zzb.zza(parcel, i1, 4);
                    j = parcel.readInt();
                    break;

                case 2: // '\002'
                    zzb.zza(parcel, i1, 4);
                    i = parcel.readInt();
                    break;

                case 3: // '\003'
                    arraylist = zzb.zzc(parcel, i1, RawDataPoint.CREATOR);
                    break;

                case 4: // '\004'
                    zzb.zza(parcel, i1, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    break;

                case 1000: 
                    zzb.zza(parcel, i1, 4);
                    k = parcel.readInt();
                    break;
                }
            } else
            if (parcel.dataPosition() != l)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(l).toString(), parcel);
            } else
            {
                return new RawDataSet(k, j, i, arraylist, flag);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new RawDataSet[i];
    }
}
