// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.fitness.data:
//            DataSource, Value, DataPoint

public final class zzg
    implements android.os.Parcelable.Creator
{

    public zzg()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int j = zzb.zzdE(parcel);
        int i = 0;
        DataSource datasource1 = null;
        long l3 = 0L;
        long l2 = 0L;
        Value avalue[] = null;
        DataSource datasource = null;
        long l1 = 0L;
        long l = 0L;
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
                    datasource1 = (DataSource)zzb.zza(parcel, k, DataSource.CREATOR);
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, k, 8);
                    l3 = parcel.readLong();
                    break;

                case 4: // '\004'
                    zzb.zza(parcel, k, 8);
                    l2 = parcel.readLong();
                    break;

                case 5: // '\005'
                    avalue = (Value[])zzb.zzb(parcel, k, Value.CREATOR);
                    break;

                case 6: // '\006'
                    datasource = (DataSource)zzb.zza(parcel, k, DataSource.CREATOR);
                    break;

                case 7: // '\007'
                    zzb.zza(parcel, k, 8);
                    l1 = parcel.readLong();
                    break;

                case 1000: 
                    zzb.zza(parcel, k, 4);
                    i = parcel.readInt();
                    break;

                case 8: // '\b'
                    zzb.zza(parcel, k, 8);
                    l = parcel.readLong();
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new DataPoint(i, datasource1, l3, l2, avalue, datasource, l1, l);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new DataPoint[i];
    }
}
