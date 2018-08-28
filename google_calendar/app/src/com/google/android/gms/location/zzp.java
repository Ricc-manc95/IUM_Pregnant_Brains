// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.location:
//            LocationRequest

public final class zzp
    implements android.os.Parcelable.Creator
{

    public zzp()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int l = zzb.zzdE(parcel);
        int k = 0;
        int j = 102;
        long l4 = 0x36ee80L;
        long l3 = 0x927c0L;
        boolean flag = false;
        long l2 = 0x7fffffffffffffffL;
        int i = 0x7fffffff;
        float f = 0.0F;
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
                    zzb.zza(parcel, i1, 4);
                    j = parcel.readInt();
                    break;

                case 2: // '\002'
                    zzb.zza(parcel, i1, 8);
                    l4 = parcel.readLong();
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, i1, 8);
                    l3 = parcel.readLong();
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

                case 5: // '\005'
                    zzb.zza(parcel, i1, 8);
                    l2 = parcel.readLong();
                    break;

                case 6: // '\006'
                    zzb.zza(parcel, i1, 4);
                    i = parcel.readInt();
                    break;

                case 7: // '\007'
                    zzb.zza(parcel, i1, 4);
                    f = parcel.readFloat();
                    break;

                case 1000: 
                    zzb.zza(parcel, i1, 4);
                    k = parcel.readInt();
                    break;

                case 8: // '\b'
                    zzb.zza(parcel, i1, 8);
                    l1 = parcel.readLong();
                    break;
                }
            } else
            if (parcel.dataPosition() != l)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(l).toString(), parcel);
            } else
            {
                return new LocationRequest(k, j, l4, l3, flag, l2, i, f, l1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new LocationRequest[i];
    }
}
