// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.auth:
//            TokenData

public final class zzl
    implements android.os.Parcelable.Creator
{

    public zzl()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int k = zzb.zzdE(parcel);
        int i = 0;
        String s = null;
        Long long1 = null;
        boolean flag1 = false;
        boolean flag = false;
        java.util.ArrayList arraylist = null;
        do
        {
            if (parcel.dataPosition() < k)
            {
                int j = parcel.readInt();
                switch (0xffff & j)
                {
                default:
                    if ((0xffff0000 & j) != 0xffff0000)
                    {
                        j = j >> 16 & 0xffff;
                    } else
                    {
                        j = parcel.readInt();
                    }
                    parcel.setDataPosition(j + parcel.dataPosition());
                    break;

                case 1: // '\001'
                    zzb.zza(parcel, j, 4);
                    i = parcel.readInt();
                    break;

                case 2: // '\002'
                    s = zzb.zzq(parcel, j);
                    break;

                case 3: // '\003'
                    if ((0xffff0000 & j) != 0xffff0000)
                    {
                        j = j >> 16 & 0xffff;
                    } else
                    {
                        j = parcel.readInt();
                    }
                    if (j == 0)
                    {
                        long1 = null;
                    } else
                    {
                        zzb.zza$51662RJ4E9NMIP1FDTPIUK31E9HMAR1R954KIAAM0(parcel, j, 8);
                        long1 = Long.valueOf(parcel.readLong());
                    }
                    break;

                case 4: // '\004'
                    zzb.zza(parcel, j, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    break;

                case 5: // '\005'
                    zzb.zza(parcel, j, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    break;

                case 6: // '\006'
                    arraylist = zzb.zzE(parcel, j);
                    break;
                }
            } else
            if (parcel.dataPosition() != k)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
            } else
            {
                return new TokenData(i, s, long1, flag1, flag, arraylist);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new TokenData[i];
    }
}
