// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.fitness.data:
//            Device

public final class zzl
    implements android.os.Parcelable.Creator
{

    public zzl()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        String s = null;
        int i = 0;
        int l = zzb.zzdE(parcel);
        int j = 0;
        String s1 = null;
        String s2 = null;
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
                    s2 = zzb.zzq(parcel, i1);
                    break;

                case 2: // '\002'
                    s1 = zzb.zzq(parcel, i1);
                    break;

                case 3: // '\003'
                    zzb.zzq(parcel, i1);
                    break;

                case 4: // '\004'
                    s = zzb.zzq(parcel, i1);
                    break;

                case 5: // '\005'
                    zzb.zza(parcel, i1, 4);
                    j = parcel.readInt();
                    break;

                case 6: // '\006'
                    zzb.zza(parcel, i1, 4);
                    i = parcel.readInt();
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
                return new Device(k, s2, s1, s, j, i);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new Device[i];
    }
}
