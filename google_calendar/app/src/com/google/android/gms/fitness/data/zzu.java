// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.fitness.data:
//            Value, RawDataPoint

public final class zzu
    implements android.os.Parcelable.Creator
{

    public zzu()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int l = zzb.zzdE(parcel);
        int k = 0;
        long l4 = 0L;
        long l3 = 0L;
        Value avalue[] = null;
        int j = 0;
        int i = 0;
        long l2 = 0L;
        long l1 = 0L;
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
                    zzb.zza(parcel, i1, 8);
                    l4 = parcel.readLong();
                    break;

                case 2: // '\002'
                    zzb.zza(parcel, i1, 8);
                    l3 = parcel.readLong();
                    break;

                case 3: // '\003'
                    avalue = (Value[])zzb.zzb(parcel, i1, Value.CREATOR);
                    break;

                case 4: // '\004'
                    zzb.zza(parcel, i1, 4);
                    j = parcel.readInt();
                    break;

                case 5: // '\005'
                    zzb.zza(parcel, i1, 4);
                    i = parcel.readInt();
                    break;

                case 6: // '\006'
                    zzb.zza(parcel, i1, 8);
                    l2 = parcel.readLong();
                    break;

                case 7: // '\007'
                    zzb.zza(parcel, i1, 8);
                    l1 = parcel.readLong();
                    break;

                case 1000: 
                    zzb.zza(parcel, i1, 4);
                    k = parcel.readInt();
                    break;
                }
            } else
            {
                if (parcel.dataPosition() != l)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(l).toString(), parcel);
                }
                return new RawDataPoint(k, l4, l3, avalue, j, i, l2, l1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new RawDataPoint[i];
    }
}
