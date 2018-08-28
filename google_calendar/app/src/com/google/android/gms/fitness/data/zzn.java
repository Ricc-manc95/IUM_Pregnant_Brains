// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.fitness.data:
//            Field

public final class zzn
    implements android.os.Parcelable.Creator
{

    public zzn()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        Boolean boolean1 = null;
        int j = 0;
        int k = zzb.zzdE(parcel);
        String s = null;
        int i = 0;
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
                    s = zzb.zzq(parcel, l);
                    break;

                case 2: // '\002'
                    zzb.zza(parcel, l, 4);
                    j = parcel.readInt();
                    break;

                case 3: // '\003'
                    boolean1 = zzb.zzd(parcel, l);
                    break;

                case 1000: 
                    zzb.zza(parcel, l, 4);
                    i = parcel.readInt();
                    break;
                }
            } else
            {
                if (parcel.dataPosition() != k)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
                }
                return new Field(i, s, j, boolean1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new Field[i];
    }
}
