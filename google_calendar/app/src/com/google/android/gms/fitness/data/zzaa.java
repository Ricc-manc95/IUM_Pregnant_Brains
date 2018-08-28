// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.fitness.data:
//            DataSource, DataType, Subscription

public final class zzaa
    implements android.os.Parcelable.Creator
{

    public zzaa()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        DataType datatype = null;
        int i = 0;
        int k = zzb.zzdE(parcel);
        long l1 = 0L;
        DataSource datasource = null;
        int j = 0;
        do
        {
            if (parcel.dataPosition() < k)
            {
                int l = parcel.readInt();
                switch (0xffff & l)
                {
                default:
                    zzb.zzb(parcel, l);
                    break;

                case 1: // '\001'
                    datasource = (DataSource)zzb.zza(parcel, l, DataSource.CREATOR);
                    break;

                case 2: // '\002'
                    datatype = (DataType)zzb.zza(parcel, l, DataType.CREATOR);
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, l, 8);
                    l1 = parcel.readLong();
                    break;

                case 4: // '\004'
                    zzb.zza(parcel, l, 4);
                    i = parcel.readInt();
                    break;

                case 1000: 
                    zzb.zza(parcel, l, 4);
                    j = parcel.readInt();
                    break;
                }
            } else
            {
                if (parcel.dataPosition() != k)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
                }
                return new Subscription(j, datasource, datatype, l1, i);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new Subscription[i];
    }
}
