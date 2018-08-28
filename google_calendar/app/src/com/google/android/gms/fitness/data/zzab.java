// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.fitness.data:
//            Value

public final class zzab
    implements android.os.Parcelable.Creator
{

    public zzab()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int l = zzb.zzdE(parcel);
        int j = 0;
        int i = 0;
        boolean flag = false;
        float f = 0.0F;
        String s = null;
        android.os.Bundle bundle = null;
        int ai[] = null;
        float af[] = null;
        byte abyte0[] = null;
        do
        {
            if (parcel.dataPosition() < l)
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
                    zzb.zza(parcel, k, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, k, 4);
                    f = parcel.readFloat();
                    break;

                case 4: // '\004'
                    s = zzb.zzq(parcel, k);
                    break;

                case 5: // '\005'
                    bundle = zzb.zzs(parcel, k);
                    break;

                case 6: // '\006'
                    ai = zzb.zzw(parcel, k);
                    break;

                case 7: // '\007'
                    int i1;
                    if ((0xffff0000 & k) != 0xffff0000)
                    {
                        k = k >> 16 & 0xffff;
                    } else
                    {
                        k = parcel.readInt();
                    }
                    i1 = parcel.dataPosition();
                    if (k == 0)
                    {
                        af = null;
                    } else
                    {
                        af = parcel.createFloatArray();
                        parcel.setDataPosition(k + i1);
                    }
                    break;

                case 1000: 
                    zzb.zza(parcel, k, 4);
                    j = parcel.readInt();
                    break;

                case 8: // '\b'
                    abyte0 = zzb.zzt(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != l)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(l).toString(), parcel);
            } else
            {
                return new Value(j, i, flag, f, s, bundle, ai, af, abyte0);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new Value[i];
    }
}
